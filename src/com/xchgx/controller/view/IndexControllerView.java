package com.xchgx.controller.view;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.xchgx.controller.BaseController;
import com.xchgx.domain.Permission;
import com.xchgx.domain.User;
import com.xchgx.service.DepartmentService;

@Controller
@RequestMapping("/")
public class IndexControllerView extends BaseController{

	@Autowired
	private DepartmentService departmentService;
	
	/**
	 * 新闻管理首页
	 * @return
	 */
	@RequestMapping("manager/newsManager/main")
	public ModelAndView newsMainView(HttpServletRequest request){
		User user = getSessionUser(request);
		Permission permission = user.getPermission();
		ModelAndView mav = new ModelAndView();
		if(permission.getLevel()<=2){
			mav.addObject("departments", departmentService.list());
			mav.setViewName("/manager/building");
		}else{
			mav.addObject("departments", user.getTeacher().getManagerDepartments());
		}
		mav.setViewName("/manager/newsManager/main");
		return mav;
	}
	/**
	 * 站点正在建设中。。。
	 * @return
	 */
	@RequestMapping("manager/building")
	public ModelAndView building(){
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/manager/building");
		return mav;
	}
	/**
	 * 系统控制面板
	 * @return
	 */
	@RequestMapping(value="manager/systemManage")
	public ModelAndView systemManage(HttpServletRequest request){
		User user = getSessionUser(request);
		Permission permission = user.getPermission();
		ModelAndView mav = new ModelAndView();
		if(permission.getLevel()>2){//权限级别低于校级（2），则不能访问系统管理页面。
			mav.setViewName("/manager/building");
		}else{
			mav.setViewName("/manager/systemManage/main");
		}
		return mav;
	}
	/**
	 * 站点管理面板
	 * @return
	 */
	@RequestMapping(value="manager/siteManage")
	public ModelAndView siteManage(HttpServletRequest request){
		User user = getSessionUser(request);
		Permission permission = user.getPermission();
		ModelAndView mav = new ModelAndView();
		if(permission.getLevel()>4){
			mav.setViewName("/manager/building");
		}else{
			mav.setViewName("/manager/siteManage/main");
		}
		if(permission.getLevel()<=2){ 
			mav.addObject("permission", 1);
		}else{
			mav.addObject("permission", 0);
		}
		return mav;
	}
	@RequestMapping(value="/updateLog", method=RequestMethod.GET)
	public ModelAndView initDb(HttpServletRequest request){
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/logs/updateLog");
		return mav;
	}
	
//	/**
//	 * 初始化是数据库。测试阶段使用
//	 * @param request
//	 * @return
//	 */
//	@RequestMapping(value="/initDbView", method=RequestMethod.GET)
//	public ModelAndView initDb(HttpServletRequest request){
//		ModelAndView mav = new ModelAndView();
//		User user = getSessionUser(request);
//		Permission permission = user.getPermission();
//		if(permission.getLevel()!=1){
//			mav.setViewName("/manager/building");
//		}else{
//			mav.setViewName("manager/init/initDatabaseView");
//		}
////		String path = request.getSession().getServletContext().getRealPath("/manager/js/shortcut.js");
////		File file = new File(path);
////		System.out.println(file.getAbsolutePath());
//		return mav;
//	}
	
}
