package com.xchgx.zsbwork.bean;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.xchgx.domain.Article;
import com.xchgx.domain.User;

public class Teacher implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2695314808228660187L;
	private Integer id;
	private String jgh;
	private String name;
	private String sex;
	private Integer age;
	private String position;// 职位
	private String phone;
	private String description;
	private Employee employee; // 我谁的联系人
	private School school; // 我所在的学校
	private Set<Department> departments = new HashSet<Department>();// 我所在的部门（班级或者其他部门）
	private Set<Department> managerDepartments = new HashSet<Department>();// 教职工管理多个部门，是多个部门的负责人。
	private User user; // 用于登陆的User 用户
	private Set<Article> articles = new HashSet<Article>();
	private Set<Department> articleDepartments = new HashSet<Department>();
	private Set<Student> zsbStudent = new HashSet<Student>();// 老师帮我们招了多少学生

	public Teacher() {
	}

	public Teacher(String jgh, String name, String sex, Integer age,
			String position, String phone, String description) {
		this.jgh = jgh;
		this.name = name;
		this.age = age;
		this.sex = sex;
		this.position = position;
		this.phone = phone;
		this.description = description;

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getJgh() {
		return jgh;
	}

	public void setJgh(String jgh) {
		this.jgh = jgh;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public School getSchool() {
		return school;
	}

	public void setSchool(School school) {
		this.school = school;
	}

	public Set<Department> getDepartments() {
		return departments;
	}

	public void setDepartments(Set<Department> departments) {
		this.departments = departments;
	}

	public Set<Department> getManagerDepartments() {
		return managerDepartments;
	}

	public void setManagerDepartments(Set<Department> managerDepartments) {
		this.managerDepartments = managerDepartments;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Set<Article> getArticles() {
		return articles;
	}

	public void setArticles(Set<Article> articles) {
		this.articles = articles;
	}

	public Set<Department> getArticleDepartments() {
		return articleDepartments;
	}

	public void setArticleDepartments(Set<Department> articleDepartments) {
		this.articleDepartments = articleDepartments;
	}

	public Set<Student> getZsbStudent() {
		return zsbStudent;
	}

	public void setZsbStudent(Set<Student> zsbStudent) {
		this.zsbStudent = zsbStudent;
	}

	@Override
	public String toString() {
		String result = "name:" + this.getName() + "jgh:" + this.getJgh()
				+ "phone:" + this.getPhone() + "posion:" + this.getPosition() + "description:"
				+ this.getDescription() + "sex:" + this.getSex() + "age:"
				+ this.getAge() + "id:" + this.getId() + "articleDepartments.size:"
				+ this.getArticleDepartments().size() + "article.size:" + this.getArticles().size() + "department.size:"
				+ this.getDepartments().size() ;
		return result;
	}
}