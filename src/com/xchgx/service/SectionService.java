package com.xchgx.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xchgx.dao.ArticleDao;
import com.xchgx.dao.SectionDao;
import com.xchgx.domain.Article;
import com.xchgx.domain.Section;
import com.xchgx.zsbwork.bean.Department;
import com.xchgx.zsbwork.dao.DepartmentDao;

@Service
public class SectionService {

	@Autowired
	private SectionDao sectionDao;
	@Autowired
	private DepartmentDao departmentDao;
	@Autowired
	private ArticleDao articleDao;
	/**
	 * 通过ID获取Section对象
	 * @param id
	 * @return
	 */
	public Section get(Integer id){
		return sectionDao.get(id);
	}
	public Section getSectionById(Integer id){
		return sectionDao.get(id);
	}

	/**
	 * 通过文章的id获得section对象
	 * @param articleId
	 * @return
	 */
	public Section getSectionByArticleId(Integer articleId){
		return articleDao.get(articleId).getSection();
	}
	@SuppressWarnings("unchecked")
	public List<Section> getSectionsByDepartmentId(Integer id){
		return departmentDao.find("from Section s where s.department.id=?", new Object[]{id});
	}
	/**
	 * 保存部门ID下的栏目板块。
	 * @param section
	 * @param departmentId
	 */
	public Section saveSection(Section section, Integer departmentId){
		// ----防止相同部门下新建同名的栏目  start{
		Department d = departmentDao.get(departmentId);
		for(Section s : d.getSections()){
			if(s.getName().equals(section.getName()) && s.getDescription().equals(section.getDescription())){
				return null;
			}
		}
		//  }-----防止相同部门下新建同名的栏目  stop
		
		//  ----判断section的ID是否为空来决定创建或者修改  start{  
		if(section.getId() == null){//创建
			System.out.println("创建section"+section.getName());
			section.setDepartment(d);
			sectionDao.save(section);
		}else{ //修改
			Section sec  =  sectionDao.get(section.getId());
			sec.setName(section.getName());
			sec.setDescription(section.getDescription());
			sectionDao.save(sec);
		}
		// -----判断section的ID是否为空来决定创建或者修改  stop
		
		System.out.println("保存section"+section.getName()+"成功。");
		Section sec = new Section(section.getName(), section.getDescription());
		sec.setId(section.getId());
		
		return sec;
	}
	/**
	 * 获取所有板块
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Section> getAllSections(){
		return sectionDao.find("from Section");
	}
	public void deleteSectionById(Integer sectionId){
		Section section = sectionDao.get(sectionId);
		for(Article a : section.getArticles()){
			System.out.println(section.getDepartment().getName()+"子栏目("+section.getName()+")文章:"+a.getTitle()+"被删除！");
			articleDao.remove(a);
		}
		System.out.println(section.getName()+"被删除！");
		sectionDao.remove(section);
	}
	/**
	 * 获取所有板块ID
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Integer> getAllSectionIds(){
		return sectionDao.find("select id from Section");
	}
	/**
	 * 获取部门下的所有板块ID
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Integer> getAllSectionIdsByDepartmentId(Integer departmentId){
		return sectionDao.find("select s.id from Section s where s.department.id="+departmentId);
	}

	/**
	 * 搜索板块
	 * @param search
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Section> searchSectionsByLike(String search){
		List<Section> sections = new ArrayList<Section>();
		String hql = "from Section t where cast(t.icoid as string) like ? or  t.name like ? or t.ico like ? or t.url like ? or cast(t.height as string) like ? or cast(t.width as string) like ? or t.description like ? ";
		search = "%"+search+"%";
		
		System.err.println("进入搜索Section表的方法searchSectionsByLike...");
		List<Section> dbSections = sectionDao.find(hql, search, search,search,search, search,search,search);
		System.err.println("dbSections.size"+dbSections.size());
		for(Section o: dbSections){
			System.out.println("查询结果，进入循环，构建JSON。");
			Section section = new Section(o.getName(), o.getDescription());
			section.setId(o.getId());
			 if(o.getDepartment() != null){
				 Department department = new Department(o.getDepartment().getName(), o.getDepartment().getDescription(), o.getDepartment().getLevel());
				 section.setDepartment(department);
			 }
			 if(o.getFatherSection() != null){
				 Section father = o.getFatherSection();
				 Section f = new Section(father.getName(), father.getDescription());
				 section.setFatherSection(f);
			 }
//			 if(o.getArticles().size() > 0 ){
//				 
//			 }
			sections.add(section);
		}
		return sections;
	}
}
