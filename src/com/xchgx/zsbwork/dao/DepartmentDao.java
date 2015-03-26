package com.xchgx.zsbwork.dao;

import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Repository;

import com.xchgx.dao.BaseDao;
import com.xchgx.zsbwork.bean.Department;

@Repository
public class DepartmentDao extends BaseDao<Department>{

	@SuppressWarnings("unchecked")
	public List<Department> list(){
		return find("from Department d where d.departmentType is not null or d.departmentType.name <> '系统'  ");
	}
	
	@SuppressWarnings("unchecked")
	public Department getDepartmentByName(String name){
		List<Department> departments = (List<Department>) find("from Department t where t.name=? and t.departmentType is not null or d.departmentType.name <> '系统'  ", name);
		if(departments.size() != 1){
			return null;
		}else{
			return departments.get(0);
		}
	}
	/**
	 * 查询部门ID 
	 * @return list
	 */
	@SuppressWarnings("unchecked")
	public List<Integer> getAllDepartmentIds(){
		return find("select d.id from Department d where d.departmentType is not null or d.departmentType.name <> '系统'  ");
	}
	public boolean hasChildDepartment(Department department){
		if(department.getChildDepartments() == null || department.getChildDepartments().size() == 0){
			return false;
		}
		return true;
	}
	public Set<Department> getChildDepartment(Department department){
		return department.getChildDepartments();
	}
}
