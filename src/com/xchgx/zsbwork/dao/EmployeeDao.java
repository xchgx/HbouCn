package com.xchgx.zsbwork.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.xchgx.dao.BaseDao;
import com.xchgx.zsbwork.bean.Employee;

@Repository
public class EmployeeDao extends BaseDao<Employee>{

	@SuppressWarnings("unchecked")
	public List<Employee> list(){
		return find("from Employee");
	}
}
