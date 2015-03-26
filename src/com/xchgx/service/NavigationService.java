package com.xchgx.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xchgx.dao.NavigationDao;
import com.xchgx.dao.SectionDao;
import com.xchgx.domain.Navigation;
import com.xchgx.domain.Section;
import com.xchgx.zsbwork.bean.Department;
import com.xchgx.zsbwork.dao.DepartmentDao;

@Service
public class NavigationService {

	@Autowired
	private NavigationDao navigationDao;
	@Autowired
	private DepartmentDao departmentDao;
	@Autowired
	private SectionDao sectionDao;
	
	@SuppressWarnings("unchecked")
	public List<Navigation> getAllNavigations(Integer departmentId){
		if(departmentId == 0){
			return navigationDao.find("from Navigation n where n.boss=0 order by n.sort");
		}else{
			return navigationDao.find("from Navigation n where n.boss=? order by n.sort", new Object[]{departmentId});
		}
	}
	/**
	 * 由于事务增强的原因，除了Service层，不能保持数据的持久性。所以这里返回的是new对象，重新生成与数据库无关联的对象。
	 * @param navigation
	 * @param fatherNavigationId
	 * @return
	 */
	public Navigation  saveNavigation(Navigation navigation,Integer fatherNavigationId){
		if(fatherNavigationId != null){
			Navigation father = navigationDao.get(fatherNavigationId);
			navigation.setFatherNavigation(father);
		}else{
			navigation.setFatherNavigation(null);
			navigation.setLevel(1);
		}
		if(navigation.getBoss() != 0){ //！=0 表示这个导航属于部门级导航。不是首页导航
			System.out.println("这是部门导航，部门ID："+navigation.getBoss());
			navigationDao.save(navigation);
			Department department =departmentDao.get(navigation.getBoss());
			department.getNavigations().add(navigation);
//			navigation.getDepartments().add(department);
			departmentDao.save(department);
		}else{
			System.out.println("这是首页导航，直接保存。");
//			navigationDao.save(navigation);
			navigationDao.merge(navigation);
		}
		
		Navigation nav = new Navigation();
		nav.setId(navigation.getId());
		nav.setName(navigation.getName());
		nav.setLevel(navigation.getLevel());
		nav.setUrl(navigation.getUrl());
		nav.setTip(navigation.getTip());
		nav.setIco(navigation.getIco());
		nav.setStyle(navigation.getStyle());
		nav.setSort(navigation.getSort());
		nav.setTargetBlank(navigation.isTargetBlank());
		nav.setDisplay(navigation.isDisplay());
		if(navigation.getFatherNavigation() != null){
			Navigation father = new Navigation();
			father.setId(navigation.getFatherNavigation().getId()); 
			father.setName(navigation.getFatherNavigation().getName());
			nav.setFatherNavigation(father);
		}
		nav.setDescription(navigation.getDescription());
		nav.setBoss(navigation.getBoss());
		return nav;
	}

	public void update(Navigation navigation){
		navigationDao.update(navigation);
	}
	public Navigation getNavigationById(Integer navigationId){
		return navigationDao.get(navigationId);
	}
	public void mergeNavigation(Navigation navigation){
		navigationDao.merge(navigation);
	}
	/**
	 * 根据导航ID删除导航。。如果有子导航，同时一起删除。
	 * @param navigationId
	 */
	public void deleteNavigationById(Integer navigationId){
		Navigation nav =navigationDao.get(navigationId);
		if(nav == null){
			System.out.println("找不到导航"+navigationId);
			return ;
		}
		for(Department d : nav.getDepartments()){
			d.getNavigations().remove(nav);
			departmentDao.save(d);
		}
		for(Section s : nav.getSections()){
			s.getNavigations().remove(nav);
			sectionDao.save(s);
		}
		navigationDao.remove(nav); 
	}
	/**
	 * 获取指定的navLevel级别的导航
	 * @param levle
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Navigation> getFirstNavigations(Integer navLevel, Integer departmentId){
		return navigationDao.listByLevel(navLevel, departmentId);
	}
	/**
	 * 获取第一级别的导航
	 * @param levle
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Navigation> getFirstNavigations(Integer departmentId){
		return navigationDao.listByLevel(1, departmentId);
	}	
	/**
	 * 生成所有导航栏
	 * @param navList
	 * @return
	 */
	public boolean releaseAllNavigations(List<Navigation> navList){
		
		return true;
	}
}
