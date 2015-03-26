package com.xchgx.zsbwork.bean;

import java.io.Serializable;
import java.util.Set;
import java.util.HashSet;

public class School implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5696214722621402336L;
	private Integer id;
	private String name;
	private String type;// 小学中学大学中职等类型
	private   Set<Department> departments = new HashSet<Department>();// 学校有哪些部门（班级，教务处等)
	private   Set<Teacher> teachers = new HashSet<Teacher>();
	private   Set<Address> addresses = new HashSet<Address>();
	private   Set<Work> works = new HashSet<Work>();
	private String description;

	public School() {
	}
	
	public School(String name, String type,
			 String description) {
		this.name=name;
		this.type=type;
		this.description = description;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Set<Department> getDepartments() {
		return departments;
	}

	public void setDepartments(Set<Department> departments) {
		this.departments = departments;
	}

	public Set<Teacher> getTeachers() {
		return teachers;
	}

	public void setTeachers(Set<Teacher> teachers) {
		this.teachers = teachers;
	}

	public Set<Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(Set<Address> addresses) {
		this.addresses = addresses;
	}

	public Set<Work> getWorks() {
		return works;
	}

	public void setWorks(Set<Work> works) {
		this.works = works;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
