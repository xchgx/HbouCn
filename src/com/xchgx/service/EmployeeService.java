package com.xchgx.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xchgx.zsbwork.bean.Employee;
import com.xchgx.zsbwork.dao.EmployeeDao;

@Service
public class EmployeeService {
	@Autowired
	private EmployeeDao employeeDao;
	
	public List<Employee> getAllEmployees(){
		return employeeDao.list();
	}

	public Employee getEmployeeById(Integer employeeId){
		return employeeDao.get(employeeId);
	}
}
