package com.xchgx.controller.view;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xchgx.controller.BaseController;
import com.xchgx.domain.User;
import com.xchgx.service.PermissionService;
import com.xchgx.service.TeacherService;
import com.xchgx.service.UserService;

@Controller
@RequestMapping("/")
public class UserControllerView extends BaseController{

	@Autowired
	private UserService userService;
	@Autowired
	private PermissionService permissionService;
	@Autowired
	private TeacherService teacherService;
	
	@RequestMapping("user/reigsterView")
	public ModelAndView registerView(){
		ModelAndView mav = new ModelAndView();
		mav.setViewName("user/registerView");
		return mav;
	}
	@RequestMapping("user/loginView")
	public ModelAndView loginView(){
		ModelAndView mav = new ModelAndView();
		User user = new User();
		mav.setViewName("user/loginView");
		mav.addObject("user",user);
		return mav;
	}
	/**
	 * 个人信息，我的电脑。
	 * @return
	 */
	@RequestMapping("/manager/user/personInfo")
	public ModelAndView personInfo(HttpServletRequest request){
		ModelAndView mav = new ModelAndView();
		mav.setViewName("manager/userManager/personInfo");
		mav.addObject("user",getSessionUser(request));
		return mav;
	}
	/**
	 * 修改当前用户密码。
	 * @return
	 */
	@RequestMapping("/manager/user/modifyPassword")
	public @ResponseBody Integer modifyPassword(HttpServletRequest request, String old, String pass, String pass2){
		System.out.println("old:"+old+",pass:"+pass+",pass2:"+pass2);
		User user = getSessionUser(request);
		if(user == null){
			return -1;
		}else if(old == null || pass==null || pass2 == null){
			return 0;
		}else if(!user.getPassword().equals(old)){
			return -2;
		}else if(!pass.equals(pass2)){
			return -3;
		}else{
			user.setPassword(pass2);
			userService.save(user);
			return 1;
		}
	}
}
