package com.xchgx.zsbwork.bean;

import java.io.Serializable;
import java.util.Set;
import java.util.HashSet;

import com.xchgx.domain.Article;
import com.xchgx.domain.DepartmentType;
import com.xchgx.domain.Navigation;
import com.xchgx.domain.Section;

public class Department implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2319019809770500312L;
	private Integer id;
	private String name;
	private   School school;
	private   Set<Teacher> teachers = new HashSet<Teacher>();//部门下的教职工
	private   Set<Teacher> articleTeachers = new HashSet<Teacher>();//由发表文章权利的教职工
	private String description;// 备注
	private   DepartmentType departmentType;//部门类型
	private Integer level;//部门级别
	private   Teacher manager;//部门负责人。
	private   Department fatherDepartment;//主管部门，上级部门
	private   Set<Department> childDepartments  = new HashSet<Department>();//下级部门，下属部门
	private   Set<Article> articles = new HashSet<Article>();
	private   Set<Section> sections = new HashSet<Section>();
	private Set<Navigation> navigations = new HashSet<Navigation>();
	public Department() {
	}
	
	public Department(String name,String description, Integer level) {
		this.level = level;
		this.name = name;
		this.description = description;
	}
	public Department(Integer id, String name){
		this.id = id;
		this.name = name;
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

	public School getSchool() {
		return school;
	}

	public void setSchool(School school) {
		this.school = school;
	}

	public Set<Teacher> getTeachers() {
		return teachers;
	}

	public void setTeachers(Set<Teacher> teachers) {
		this.teachers = teachers;
	}

	public Set<Teacher> getArticleTeachers() {
		return articleTeachers;
	}

	public void setArticleTeachers(Set<Teacher> articleTeachers) {
		this.articleTeachers = articleTeachers;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public DepartmentType getDepartmentType() {
		return departmentType;
	}

	public void setDepartmentType(DepartmentType departmentType) {
		this.departmentType = departmentType;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public Teacher getManager() {
		return manager;
	}

	public void setManager(Teacher manager) {
		this.manager = manager;
	}

	public Department getFatherDepartment() {
		return fatherDepartment;
	}

	public void setFatherDepartment(Department fatherDepartment) {
		this.fatherDepartment = fatherDepartment;
	}

	public Set<Department> getChildDepartments() {
		return childDepartments;
	}

	public void setChildDepartments(Set<Department> childDepartments) {
		this.childDepartments = childDepartments;
	}

	public Set<Article> getArticles() {
		return articles;
	}

	public void setArticles(Set<Article> articles) {
		this.articles = articles;
	}

	public Set<Section> getSections() {
		return sections;
	}

	public void setSections(Set<Section> sections) {
		this.sections = sections;
	}

	public Set<Navigation> getNavigations() {
		return navigations;
	}

	public void setNavigations(Set<Navigation> navigations) {
		this.navigations = navigations;
	}
	
}
