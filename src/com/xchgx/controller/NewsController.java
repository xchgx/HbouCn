package com.xchgx.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.xchgx.controller.view.NewsControllerView;
import com.xchgx.domain.Article;
import com.xchgx.domain.User;
import com.xchgx.service.ArticleService;
import com.xchgx.service.DepartmentService;
import com.xchgx.service.TeacherService;
import com.xchgx.zsbwork.bean.Department;
import com.xchgx.zsbwork.bean.Teacher;

@Controller
@RequestMapping(value="/manager/news/")
public class NewsController extends BaseController {

	@Autowired
	private ArticleService articleService;
	@Autowired
	private DepartmentService departmentService;
	@Autowired
	private TeacherService teacherService;
	@Autowired
	private NewsControllerView newsControllerView;
	
	/**
	 * 发表文章
	 * @param request
	 * @param article
	 * @param departmentId
	 * @return
	 */
	@RequestMapping(value="/releaseArticle", method=RequestMethod.POST)
	public ModelAndView releaseArticle(HttpServletRequest request , Article article, @RequestParam("departmentId") Integer departmentId){
		System.out.println("进入Newsontroller... releaseArticle()...");
		User user = getSessionUser(request);
		System.out.println(article.getTitle());
		System.out.println(article.getType());
		System.out.println(article.getTitleStyle());
		System.out.println(article.getDate());
		System.out.println(article.getContent());
		System.out.println(article.getAuthor());
		Department department = departmentService.getDepartmentById(departmentId);
		if(!departmentService.isUserManagerDepartment(user, departmentId)){
			System.out.println("越权访问");
			return null;
		}
		Teacher teacher = teacherService.getTeacherByUser(user);
		article.setAuthor(teacher);
		article.setDepartment(department);
		article.setIp(request.getRemoteAddr());
		article.setReleaseTime(new Date());
		articleService.saveArticle(article);
		return newsControllerView.list(request, departmentId);
	}
	
	/**
	 * 删除文章
	 * @param request
	 * @param operator 操作必须等于delete
	 * @param articleId 文章的主键
	 * @param departmentId 部门id
	 * @return
	 */
	@RequestMapping(value="operator", params={"operator=delete", "articleId", "departmentId"} , method={RequestMethod.GET})
	public ModelAndView articleOperatorDelete(HttpServletRequest request, @RequestParam("operator") String operator,@RequestParam("articleId") Integer articleId, @RequestParam("departmentId") Integer departmentId){
		articleService.remove(articleId);
		System.out.println("操作是　ｄｅｌｅｔｅ");
		return newsControllerView.list(request, departmentId);
	}
	/**
	 * 修改文章
	 * @param request
	 * @param operator
	 * @param articleId
	 * @param departmentId
	 * @return
	 */
	@RequestMapping(value="operator", params={"operator=modify", "articleId", "departmentId"} , method={RequestMethod.GET})
	public ModelAndView articleOperatorModify(HttpServletRequest request, @RequestParam("operator") String operator,@RequestParam("articleId") Integer articleId, @RequestParam("departmentId") Integer departmentId){
		System.out.println("操作是modify");
		return newsControllerView.releaseArticleView(request, departmentId,articleId);
	}
}
