package com.xchgx.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.xchgx.domain.DepartmentType;

@Repository
public class DepartmentTypeDao extends BaseDao<DepartmentType>{

	@SuppressWarnings("unchecked")
	public List<DepartmentType> list(){
		return find("from DepartmentType");
	}
}
