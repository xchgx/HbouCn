package com.xchgx.zsbwork.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.xchgx.dao.BaseDao;
import com.xchgx.zsbwork.bean.School;

@Repository
public class SchoolDao extends BaseDao<School>{

	@SuppressWarnings("unchecked")
	public List<School> list(){
		return find("from School");
	}
	/**
	 * 通过School Name find School对象。
	 * @param name school.name
	 * @return School
	 */
	@SuppressWarnings("unchecked")
	public School getSchoolByName(String name){
		//private final String GET_USER_BY_USERNAME = "from User u where u.name = ?";
		List<School> schools = (List<School>) find("from School t where t.name=?", name);
		if(schools.size() != 1){
			return null;
		}else{
			return schools.get(0);
		}
	}
}
