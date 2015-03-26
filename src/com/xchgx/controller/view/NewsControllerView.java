package com.xchgx.controller.view;

import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.xchgx.controller.BaseController;
import com.xchgx.domain.Article;
import com.xchgx.domain.User;
import com.xchgx.service.ArticleService;
import com.xchgx.service.DepartmentService;
import com.xchgx.service.TeacherService;
import com.xchgx.zsbwork.bean.Department;
import com.xchgx.zsbwork.bean.Teacher;

@Controller
@RequestMapping("/manager/news")
public class NewsControllerView extends BaseController{
	
	@Autowired
	private DepartmentService departmentService;
	@Autowired
	private TeacherService teacherService;
	@Autowired
	private ArticleService articleService;
//	
//	@RequestMapping("/content/{contentValue}")
//	public ModelAndView content(@PathVariable String contentValue){
//		ModelAndView mav = new ModelAndView();
//		Article article = new Article();
//		mav.setViewName("/manager/newsManager/"+contentValue);
//		mav.addObject("article",article);
//		return mav;
//	}
	/**
	 * 根据部门ID查找部门板块下所有的文章。
	 * @param request
	 * @param departmentId
	 * @return
	 */
	@RequestMapping(value="/list", params="departmentId", method={RequestMethod.GET})
	public ModelAndView list(HttpServletRequest request, @RequestParam("departmentId") Integer departmentId){
		ModelAndView mav = new ModelAndView();
		Set<Article> articleSet = departmentService.getArticlesByDepartment(departmentId);
		Department department = departmentService.getDepartmentById(departmentId);
		mav.addObject("articles",articleSet);
		mav.addObject("department",department);
		mav.setViewName("/manager/newsManager/newsList");
		return mav;
	}
	/**
	 * 发表一篇新文章
	 * @param request
	 * @param departmentId
	 * @return
	 */
	@RequestMapping(value="/releaseArticleView", params="departmentId", method={RequestMethod.GET})
	public ModelAndView releaseArticleView(HttpServletRequest request, @RequestParam("departmentId") Integer departmentId){
		ModelAndView mav = new ModelAndView();
		User user = getSessionUser(request);
		if(!departmentService.isUserManagerDepartment(user, departmentId)){
			return entrance(request);
		}
		Article article = new Article();
		article.setContent("");
		article.setTitle("");
		article.setDate("");
		article.setDisplay(false);
		article.setTitleStyle("1");
		article.setType(1);
		mav.setViewName("/manager/newsManager/release/releaseArticleView");
		mav.addObject("article",article);
		mav.addObject("path",departmentService.getDepartmentPath(departmentId));
		mav.addObject("teacher", teacherService.getTeacherByUser(user));
		mav.addObject("department", departmentService.getDepartmentById(departmentId));
		return mav;
	}
	/**
	 * 修改一篇文章
	 * @param request
	 * @param departmentId
	 * @param articleId
	 * @return
	 */
	@RequestMapping(value="/releaseArticleView", params="departmentId, articleId", method={RequestMethod.GET})
	public ModelAndView releaseArticleView(HttpServletRequest request, @RequestParam("departmentId") Integer departmentId, @RequestParam("articleId") Integer articleId){
		ModelAndView mav = new ModelAndView();
		User user = getSessionUser(request);
		if(!departmentService.isUserManagerDepartment(user, departmentId)){
			return entrance(request);
		}
		Article article = articleService.getArticle(articleId);
		mav.setViewName("/manager/newsManager/release/releaseArticleView");
		mav.addObject("article",article);
		mav.addObject("path",departmentService.getDepartmentPath(departmentId));
		mav.addObject("teacher", teacherService.getTeacherByUser(user));
		mav.addObject("department", departmentService.getDepartmentById(departmentId));
		return mav;
	}
	/**
	 * 部门入口
	 * @param request
	 * @return
	 */
	@RequestMapping("/entranceView")
	public ModelAndView entrance(HttpServletRequest request){
		ModelAndView mav = new ModelAndView();
		User user = getSessionUser(request);
		if(user == null){
			mav.setViewName("redirect:/index.jsp");
			return mav;
		}
		mav.setViewName("/manager/newsManager/entranceView");
		Teacher teacher = teacherService.getTeacherByUser(user);
		Set<Department> departments = departmentService.getArticleDepartmentsByUser(user);
		mav.addObject("teacher", teacher);
		mav.addObject("departments", departments);
		return mav;
	}
}
