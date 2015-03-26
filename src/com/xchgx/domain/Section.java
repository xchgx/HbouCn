package com.xchgx.domain;

import java.io.Serializable;
import java.util.Set;
import java.util.HashSet;

import com.xchgx.zsbwork.bean.Department;

public class Section implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4341295523054655429L;
	private Integer id;
	private String name; //栏目名
	private   Department department;//所属部门
	private String description;//备注
	private   Set<Article> articles = new HashSet<Article>();//该栏目下的文章
	private   Section fatherSection;//父栏目
	private   Set<Section> childSections = new HashSet<Section>();//子栏目
	private Set<Navigation> navigations = new HashSet<Navigation>();//导航
	public Section() {
	}
	
	public Section(String name, String description) {
		this.name = name;
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
	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Set<Article> getArticles() {
		return articles;
	}
	public void setArticles(Set<Article> articles) {
		this.articles = articles;
	}
	public Section getFatherSection() {
		return fatherSection;
	}
	public void setFatherSection(Section fatherSection) {
		this.fatherSection = fatherSection;
	}
	public Set<Section> getChildSections() {
		return childSections;
	}
	public void setChildSections(Set<Section> childSections) {
		this.childSections = childSections;
	}
	public Set<Navigation> getNavigations() {
		return navigations;
	}
	public void setNavigations(Set<Navigation> navigations) {
		this.navigations = navigations;
	}
}
