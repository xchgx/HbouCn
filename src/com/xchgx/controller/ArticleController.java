package com.xchgx.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xchgx.dao.Page;
import com.xchgx.domain.Article;
import com.xchgx.service.ArticleService;

@Controller
@RequestMapping("/article")
public class ArticleController extends BaseController {

	@Autowired
	private ArticleService articleService;
	/**
	 * 当前显示内容的文章
	 * @return
	 */
	@RequestMapping(value="notice", method={RequestMethod.GET})
	public @ResponseBody Article getNoticeArticle(){
		List<Article> articleList = articleService.getNoticNewest(1);
		Article article = new Article(articleList.get(0).getTitle(), articleList.get(0).getType(), articleList.get(0).getTitleStyle(), articleList.get(0).isDisplay(), articleList.get(0).getContent(), articleList.get(0).getDate(), articleList.get(0).getIp(), articleList.get(0).getDescription());
		return article;
	}
	/**
	 * 最新的通知公告列表
	 * @return
	 */
	@RequestMapping(value="noticeNewest", method={RequestMethod.GET})
	public @ResponseBody List<Article> getNoticeNewestArticle(){
		List<Article> articleList = articleService.getNoticNewest(3);
		List<Article> al = new ArrayList<Article>();
		for(Article a : articleList){
			Article article = new Article(a.getTitle(), a.getType(), a.getTitleStyle(), a.isDisplay(), a.getContent(), a.getDate(), a.getIp(), a.getDescription());
			article.setId(a.getId());
			al.add(article);
		}
		return al;
	}
	/**
	 * 通过部门ID查找
	 * @param departmentId
	 * @return
	 */
	@RequestMapping(value="getPageJsonArticlesByDepartmentId", method={RequestMethod.GET})
	public @ResponseBody Page getPageJsonArticlesByDepartmentId(Integer pageNo, Integer pageSize, Integer departmentId){
		//System.err.println("首页获取部门下的文章列表");
		return articleService.getPageJsonDepartmentArticles(pageNo, pageSize, departmentId);
	}
	/**
	 * 通过子部门板块ID查找文章
	 * @param pageNo
	 * @param pageSize
	 * @param departmentId //这里其实是section ID。
	 * @return
	 */
	@RequestMapping(value="getPageJsonArticlesBySectionId", method={RequestMethod.GET})
	public @ResponseBody Page getPageJsonArticlesBySectionId(Integer pageNo, Integer pageSize, Integer departmentId){
		return articleService.getPageJsonSectionArticles(pageNo, pageSize, departmentId);
	}
}
