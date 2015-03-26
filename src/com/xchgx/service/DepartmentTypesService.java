package com.xchgx.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xchgx.dao.DepartmentTypeDao;
import com.xchgx.dao.UserDao;
import com.xchgx.domain.DepartmentType;
import com.xchgx.zsbwork.dao.DepartmentDao;

@Service
public class DepartmentTypesService {
	
	@Autowired
	private UserDao userDao;
	@Autowired
	private DepartmentDao departmentDao;
	@Autowired
	private DepartmentTypeDao departmentTypeDao;
	/**
	 * 获取所有部门
	 * @return
	 */
	public List<DepartmentType> getAllDepartmentTypes(){
		return departmentTypeDao.list();
	}
	
	public DepartmentType getDepartmentTypeById(Integer departmentTypeId){
		return departmentTypeDao.get(departmentTypeId);
	}
}