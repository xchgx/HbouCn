package com.xchgx.controller.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.xchgx.controller.BaseController;
import com.xchgx.service.ArticleService;
import com.xchgx.service.DepartmentService;
import com.xchgx.service.SectionService;

@Controller
@RequestMapping("/navigation")
public class NavigationControllerView extends BaseController{

	@Autowired
	private DepartmentService departmentService;
	@Autowired
	private SectionService sectionService;
	@Autowired
	private ArticleService articleService;
	
	/**
	 * 准备废弃这个方法
	 * @return
	 */
	@RequestMapping(value="indexNavigations")
	public ModelAndView loadIndexNavigationsView(){
		ModelAndView mav = new ModelAndView();
		
		mav.setViewName("/department/index");
		return mav;
	}
	/**
	 * 部门页
	 * @param departmentId
	 * @return
	 */
	@RequestMapping(value="department/{departmentId}")
	public ModelAndView loadDepartmentNavigationsView(@PathVariable Integer departmentId){
		ModelAndView mav = new ModelAndView();
//		Department department = departmentService.getDepartmentById(departmentId);
//		mav.setViewName("/department/department/index");
		mav.setViewName("redirect:/index/department/dpt"+departmentId+".jsp");
//		mav.addObject("department", department);
//		mav.addObject("departmentId", departmentId);
		return mav;
	}
	/**
	 * 子部门板块页
	 * @param sectionId
	 * @return
	 */
	@RequestMapping(value="section/{sectionId}")
	public ModelAndView loadSectionView(@PathVariable Integer sectionId){
		ModelAndView mav = new ModelAndView();
//		Section section = sectionService.get(sectionId);
//		mav.setViewName("/index/section/sec"+sectionId);
//		mav.addObject("section",section);
//		mav.addObject("departmentId", section.getDepartment().getId());
		mav.setViewName("redirect:/index/section/sec"+sectionId+".jsp");
		return mav;
	}
	/**
	 * 文章页
	 * @param articleId
	 * @return
	 */
	@RequestMapping(value="article/{articleId}")
	public ModelAndView loadArticleView(@PathVariable Integer articleId){
		ModelAndView mav = new ModelAndView();
//		Article article = articleService.getArticleById(articleId);
//		mav.setViewName("/index/article/index");
//		mav.addObject("article",article);
//		mav.addObject("departmentId", article.getDepartment().getId());

		mav.setViewName("redirect:/index/article/art"+articleId+".jsp");
		return mav;
	}
}
