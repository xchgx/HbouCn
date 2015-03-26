package com.xchgx.domain;

import java.io.Serializable;
import java.util.Set;
import java.util.HashSet;

import com.xchgx.zsbwork.bean.Department;

public class DepartmentType implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7992608728221583878L;
	private Integer id;
	private String name;//类型名称，比如说：行政部门，院系，班级。
	private String description;
	private   Set<Department> departments = new HashSet<Department>();
	
	public DepartmentType() {
	}
	
	public DepartmentType(String name,String description){
		this.name=name;
		this.description=description;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	} 
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Set<Department> getDepartments() {
		return departments;
	}
	public void setDepartments(Set<Department> departments) {
		this.departments = departments;
	}
}
