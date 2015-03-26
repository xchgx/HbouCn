package com.xchgx.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xchgx.dao.UserDao;
import com.xchgx.zsbwork.bean.School;
import com.xchgx.zsbwork.dao.SchoolDao;
import com.xchgx.zsbwork.dao.TeacherDao;

@Service
public class SchoolService {
	@Autowired
	private UserDao userDao;
	@Autowired
	private TeacherDao teacherDao;
	@Autowired
	private SchoolDao schoolDao;
	
	public List<School> getAllSchool(){
		return schoolDao.list();
	}
	
	/**
	 * 通过ID查找School对象
	 * @param schoolId
	 * @return
	 */
	public School getSchoolById(Integer schoolId){
		return schoolDao.get(schoolId);
	}
	/**
	 * 搜索学校
	 * @param search
	 * @return
	 */
//	private   Set<Department> departments = new HashSet<Department>();// 学校有哪些部门（班级，教务处等)
//	private   Set<Teacher> teachers = new HashSet<Teacher>();
//	private   Set<Address> addresses = new HashSet<Address>();
//	private   Set<Work> works = new HashSet<Work>();
//	private String description;
//	
	@SuppressWarnings("unchecked")
	public List<School> searchSchoolsByLike(String search){
		List<School> schools = new ArrayList<School>();
		String hql = "from School t where t.name like ? or t.type like ? or t.description like ? ";
		search = "%"+search+"%";
		
		System.err.println("进入搜索School表的方法searchSchoolsByLike...");
		List<School> dbSchools = schoolDao.find(hql, search, search,search);
		System.err.println("dbSchools.size"+dbSchools.size());
		for(School o: dbSchools){
			System.out.println("查询结果，进入循环，构建JSON。");
			School school = new School(o.getName(), o.getType(), o.getDescription());
			school.setId(o.getId());
//			 if(o.getArticles().size() > 0 ){
//				 
//			 }
			schools.add(school);
		}
		return schools;
	}
	
}
