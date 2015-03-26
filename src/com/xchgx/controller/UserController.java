package com.xchgx.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.xchgx.cons.CommonConstant;
import com.xchgx.domain.LoginLog;
import com.xchgx.domain.User;
import com.xchgx.exception.UserExistException;
import com.xchgx.service.UserService;

/**
 * 
 * <br>
 * <b>类描述:</b>
 * 
 * <pre>
 *   论坛管理，这部分功能由论坛管理员操作，包括：创建论坛版块、指定论坛版块管理员、
 * 用户锁定/解锁。
 * </pre>
 * 
 * @see
 * @since
 */
@Controller
@RequestMapping("/user")
public class UserController extends BaseController {

	@Autowired
	private UserService userService;
	
	/**
	 * 用户注册
	 * @param request
	 * @param user
	 * @return
	 */
	@RequestMapping(value="/doRegister",method=RequestMethod.POST)
	public ModelAndView register(HttpServletRequest request, User user, String password2){
//		System.out.println("register controller ... -> user/register");
		ModelAndView mav = new ModelAndView(); 
		mav.setViewName("user/registerView");
		if(!user.getPassword().equals(password2)){
			mav.addObject("errorMsg","两次输入密码不一致");
			return mav;
		}
		if(user.getName().length()<6){
			mav.addObject("errorMsg","您输入的用户名长度少于6位");
			return mav;
		}
		if(user.getPassword().length()<6){
			mav.addObject("errorMsg","您输入的密码长度少于6位");
			return mav;
		}

		mav.setViewName("user/success");
		try{
			userService.register(user);
		}catch(UserExistException e){
			mav.addObject("errorMsg", "用户已经存在，请选择其他名字。");
			mav.setViewName("forward:/user/register.jsp");
		}
		setSessionUser(request, user);
		return mav;
	}
	
	 /**
     * 用户登陆
     * @param request
     * @param user
     * @return
     */
	@RequestMapping(value="/doLogin",method=RequestMethod.POST)
	public ModelAndView login(HttpServletRequest request, HttpServletResponse response,@Valid @ModelAttribute("user") User user, BindingResult errors){
		response.setCharacterEncoding("UTF-8");
		setSessionUser(request, null);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("user/loginView");
		if(errors.hasErrors()){
			return mav;
		}
		User dbUser = userService.getUserByName(user.getName());
		System.out.println("查询用户名结果");
		if(null == dbUser){
			mav.addObject("nameError", "用户不存在");
		}else if(!dbUser.getPassword().equals(user.getPassword())){
			mav.addObject("passwordError", "用户密码不正确");
		}else if(dbUser.getLocked() == User.USER_LOCK){
			mav.addObject("lockError", "用户已经被锁定，不能登陆");
		}else{
			LoginLog ll = new LoginLog();
			ll.setDate(new Date());
			ll.setIp(request.getRemoteAddr());
			ll.setUser(dbUser);
			userService.loginSuccess(dbUser, ll);
			setSessionUser(request, dbUser);
			request.getSession().setAttribute("shortcuts", userService.getInitShortcut(dbUser, request));
//			if(!userService.initShortcut(dbUser,request)){
//				System.out.println("无任何权限");
//			}
			/*
			 * String toUrl = (String) request.getSession().getAttribute(CommonConstant.LOGIN_TO_URL);
			 
			request.getSession().removeAttribute(CommonConstant.LOGIN_TO_URL);
			//如果当前会话中没有保存登陆之前的请求URL，则直接跳转到主页
			if(StringUtils.isEmpty(toUrl)){
				toUrl = "/manager/main.jsp";
			}
			*/
			//mav.setViewName("redirect:"+toUrl);
			mav.setViewName("redirect:/manager/main.jsp");
		}
		return mav;
	}
	/**
	 * 登录注销
	 * @param session
	 * @return
	 */
	@RequestMapping("/doLogout")
	public String logout(HttpSession session){
		session.removeAttribute(CommonConstant.USER_CONTEXT);
		return "redirect:/index.jsp";
	}
}