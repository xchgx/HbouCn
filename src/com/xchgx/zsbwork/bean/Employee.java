package com.xchgx.zsbwork.bean;

import java.io.Serializable;
import java.util.Set;
import java.util.HashSet;

public class Employee implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4556280822968096073L;
	private Integer id;
	private String name;
	private String password;
	private String sex;
	private String phone;
	private Integer age;
	private String email;
	private   Set<Teacher> agentTeachers = new HashSet<Teacher>();// 联系人
	private   Set<Work> works = new HashSet<Work>();
	private   Set<Work> workManagers = new HashSet<Work>();
	private String description;

	public Employee() {
	}
	
	public Employee(String name, String password, String sex,
			String phone, Integer age, String email, String description) {
		this.name=name;
		this.password=password;
		this.sex=sex;
		this.phone=phone;
		this.age=age;
		this.email=email;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Set<Teacher> getAgentTeachers() {
		return agentTeachers;
	}

	public void setAgentTeachers(Set<Teacher> agentTeachers) {
		this.agentTeachers = agentTeachers;
	}

	public Set<Work> getWorks() {
		return works;
	}

	public void setWorks(Set<Work> works) {
		this.works = works;
	}

	public Set<Work> getWorkManagers() {
		return workManagers;
	}

	public void setWorkManagers(Set<Work> workManagers) {
		this.workManagers = workManagers;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
