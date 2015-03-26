package com.xchgx.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xchgx.cons.CommonConstant;
import com.xchgx.dao.ArticleDao;
import com.xchgx.dao.Page;
import com.xchgx.domain.Article;
import com.xchgx.domain.Section;
import com.xchgx.zsbwork.bean.Department;
import com.xchgx.zsbwork.bean.Teacher;

@Service
public class ArticleService {

	@Autowired
	private ArticleDao articleDao;
	
	public void saveArticle(Article article){
		articleDao.save(article);
	}
	public void mergeArticle(Article article){
		articleDao.merge(article);
	}
	/**
	 * 查询所有文章
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Article> list(){
		String hql = "from Article order by date desc, id desc";
		return articleDao.find(hql);
	}
	public List<Article> getAllArticles(){
		return list();
	}
	/**
	 * 删除主键=ID的 文章
	 * @param id
	 */
	public void remove(Integer id){
		articleDao.remove(articleDao.get(id));
	}
	public void deleteArticleById(Integer id){
		Article article = articleDao.get(id);
		articleDao.remove(article);
	}
	/**
	 * 通过主键获取文章对象
	 * @param id
	 * @return
	 */
	public Article getArticle(Integer id){
		return articleDao.get(id);
	}
	public Article getArticleById(Integer id){
		return articleDao.get(id);
	}
	/**
	 * 获取所有文章的id号 
	 * @return list<Integer>
	 */
	@SuppressWarnings("unchecked")
	public List<Integer> getAllArticleIds(){
		return articleDao.find("select id from Article  order by date desc, id desc");
	}
	/**
	 * 获取所有文章的id号 
	 * @return list<Integer>
	 */
	@SuppressWarnings("unchecked")
	public List<Integer> getAllArticleIdsByDepartmentId(Integer departmentId){
		return articleDao.find("select a.id from Article a where a.department.id="+departmentId+"  order by a.date desc, a.id desc");
	}
	/**
	 * 获取所有文章的id号 
	 * @return list<Integer>
	 */
	@SuppressWarnings("unchecked")
	public List<Integer> getAllArticleIdsBySectionId(Integer sectionId){
		return articleDao.find("select a.id from Article a where a.section.id="+sectionId+"  order by a.date desc, a.id desc");
	}
	/**
	 * 最新的通知公告
	 */
	@SuppressWarnings("unchecked")
	public List<Article> getNoticNewest(Integer size){
		String hql = "from Article a where a.type=1 and a.display=true order by a.date desc, a.id desc";
		return articleDao.find(hql, size);
	}
	
	/**
	 * 搜索文章
	 * @param search
	 * @return
	 */
	public List<Article> searchArticlesByLike(String search){
		List<Article> articles = new ArrayList<Article>();
		String hql = "from Article t where t.title like ? or cast(t.type as string) like ? or t.titleStyle like ? or t.content like ? or t.date like ? or t.ip like ? or t.description like ? ";
		search = "%"+search+"%  order by t.date desc, t.id desc";
		
		System.err.println("进入搜索Article表的方法searchArticlesByLike...");
		@SuppressWarnings("unchecked")
		List<Article> dbArticles = articleDao.find(hql, search, search,search,search, search,search,search);
		System.err.println("dbArticles.size"+dbArticles.size());
		for(Article o: dbArticles){
			System.out.println("查询结果，进入循环，构建JSON。");
			Article article = new Article(o.getTitle(), o.getType(), o.getTitleStyle(), o.isDisplay(), o.getContent(), o.getDate(), o.getIp(), o.getDescription());
			article.setId(o.getId());
			if(o.getAuthor() != null){
				Teacher teacher = new Teacher(o.getAuthor().getJgh(), o.getAuthor().getName(), o.getAuthor().getSex(), o.getAuthor().getAge(), o.getAuthor().getPosition(), o.getAuthor().getPhone(), o.getAuthor().getDescription());
				teacher.setId(o.getAuthor().getId());
				article.setAuthor(teacher);
			}
			if(o.getReleaseTime() != null){
				article.setReleaseTime(o.getReleaseTime());
			}
			if(o.getDepartment() != null){
				Department department = new Department(o.getDepartment().getName(), o.getDepartment().getDescription(), o.getDepartment().getLevel());
				department.setId(o.getDepartment().getId());
				article.setDepartment(department);
			}
			if(o.getSection() != null){
				Section section = new Section(o.getSection().getName(), o.getSection().getDescription());
				section.setId(o.getSection().getId());
				article.setSection(section);
			}
			articles.add(article);
		}
		return articles;
	}
	
	
	public Page getFirstAllPage(){
		return articleDao.pagedQuery("from Article order by date desc, id desc", 1, 20);
	}
	public Page getNumberAllPage(Integer num){
		return articleDao.pagedQuery("from Article order by date desc, id desc", num , 20);
	}
	/**
	 * 获取指定子部门板块下的所有文章
	 * @param sectionId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Article> getAllSectionArticles(Integer sectionId){
		return articleDao.find("from Article a where a.section.id=? order by a.date desc, a.id desc",new Object[]{sectionId});
	}

	/**
	 * 通过部门ID获取部门下的文章。
	 * @param pageNo   	页码
	 * @param pageSize 	每页多少篇文章
	 * @param departmentId	部门ID
	 * @return Page
	 * @author cg
	 */
	
		public Page getPageJsonDepartmentArticles(Integer pageNo, Integer pageSize, Integer departmentId){
		//return articleDao.find("from Article a where a.department.id=?",new Object[]{departmentId});
		
		Page page = articleDao.pagedQuery("from Article a where a.department.id=? order by a.date desc, a.id desc", pageNo, pageSize, departmentId);
		@SuppressWarnings("unchecked")
		List<Article> artList = CommonConstant.convertHbmArticlesListToArrList(page.getResult());
		page.setData(artList);
		return page;
	}
		/**
		 * 通过部门ID获取部门下的文章。
		 * @param pageNo   	页码
		 * @param pageSize 	每页多少篇文章
		 * @param departmentId	部门ID
		 * @return Page
		 * @author cg
		 */
		
			public Page getPageJsonDepartmentArticlesSimple(Integer pageNo, Integer pageSize, Integer departmentId){
			//return articleDao.find("from Article a where a.department.id=?",new Object[]{departmentId});
			
			Page page = articleDao.pagedQuery("select new com.xchgx.domain.Article(a.id,a.title,a.titleStyle,a.date) from Article a where a.department.id=? order by a.date desc, a.id desc", pageNo, pageSize, departmentId);
			@SuppressWarnings("unchecked")
			List<Article> artList = CommonConstant.convertHbmArticlesListToArrList(page.getResult());
			page.setData(artList);
			return page;
		}
	/**
	 * 通过子部门板块ID获取部门下的文章。
	 * @param pageNo   	页码
	 * @param pageSize 	每页多少篇文章
	 * @param sectionId	子部门板块ID
	 * @return Page
	 * @author cg
	 */
	public Page getPageJsonSectionArticles(Integer pageNo, Integer pageSize, Integer sectionId){
		Page page = articleDao.pagedQuery("from Article a where a.section.id=? order by a.date desc, a.id desc", pageNo, pageSize, sectionId);
		@SuppressWarnings("unchecked")
		List<Article> artList = CommonConstant.convertHbmArticlesListToArrList(page.getResult());
		page.setData(artList);
		return page;
	}
	/**
	 * 通过子部门板块ID获取部门下的文章。
	 * @param pageNo   	页码
	 * @param pageSize 	每页多少篇文章
	 * @param sectionId	子部门板块ID
	 * @return Page
	 * @author cg
	 */
	public Page getPageJsonSectionArticlesSimple(Integer pageNo, Integer pageSize, Integer sectionId){
		Page page = articleDao.pagedQuery("select new com.xchgx.domain.Article(a.id,a.title,a.titleStyle,a.date) from Article a where a.section.id=? order by a.date desc, a.id desc", pageNo, pageSize, sectionId);
		@SuppressWarnings("unchecked")
		List<Article> artList = CommonConstant.convertHbmArticlesListToArrList(page.getResult());
		page.setData(artList);
		return page;
	}
}
