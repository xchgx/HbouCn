package com.xchgx.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.xchgx.zsbwork.bean.Department;

public class Navigation implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2616344444967799871L;
	private Integer id;
	private String name=""; //名称
	private Integer level=1; //级别，一级导航目录，二级导航目录。
	private String url = "#";    //链接地址
	private String tip=""; 	//鼠标提示内容
	private String ico=""; 	//导航栏图标，默认为空
	private String style="";	//单独的导航样式表
	private Integer sort=1; //导航菜单排序。数字越小越靠前，默认从小到大排序。
	private boolean targetBlank= false; 	//默认在本页打开链接(false)，true：用新窗口打开链接(true)
	private boolean display = true; //菜单是否在首页显示开关
	private Navigation fatherNavigation; //父导航栏目
	private Set<Navigation> childNavigations = new HashSet<Navigation>(); //下一级导航栏目
	private String description=""; //备注
	private Set<Department> departments = new HashSet<Department>(); //部门导航
	private Set<Section> sections = new HashSet<Section>(); //板块（子部门）导航
	private Integer boss =1;//是否为第一首页(0)，特殊首页，不属于部门管理。可扩展，0为默认首页，其他数字暂时忽略。
	
	public Navigation() {
	}
	public Navigation(
			 Integer id,
			 String name,
			 Integer level,
			 String url ,
			 String tip,
			 String ico,
			 String style,
			 Integer sort,
			 boolean targetBlank,
			 boolean display,
			 String description,
			 Integer boss
			){
		this. id= id ;
		 this. name= name ;
		 this. level=  level;
		 this. url = url ;
		 this. tip= tip ;
		 this. ico= ico ;
		 this. style=style  ;
		 this. sort= sort ;
		 this. targetBlank= targetBlank ;
		 this. display= display ;
		 this. description= description ;
		 this. boss= boss;
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
	public Integer getLevel() {
		return level;
	}
	public void setLevel(Integer level) {
		this.level = level;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getTip() {
		return tip;
	}
	public void setTip(String tip) {
		this.tip = tip;
	}
	public String getIco() {
		return ico;
	}
	public void setIco(String ico) {
		this.ico = ico;
	}
	public String getStyle() {
		return style;
	}
	public void setStyle(String style) {
		this.style = style;
	}
	public Integer getSort() {
		return sort;
	}
	public void setSort(Integer sort) {
		this.sort = sort;
	}
	public boolean isTargetBlank() {
		return targetBlank;
	}
	public void setTargetBlank(boolean targetBlank) {
		this.targetBlank = targetBlank;
	}
	public boolean isDisplay() {
		return display;
	}
	public void setDisplay(boolean display) {
		this.display = display;
	}
	public Navigation getFatherNavigation() {
		return fatherNavigation;
	}
	public void setFatherNavigation(Navigation fatherNavigation) {
		this.fatherNavigation = fatherNavigation;
	}
	public Set<Navigation> getChildNavigations() {
		return childNavigations;
	}
	public void setChildNavigations(Set<Navigation> childNavigations) {
		this.childNavigations = childNavigations;
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
	public Set<Section> getSections() {
		return sections;
	}
	public void setSections(Set<Section> sections) {
		this.sections = sections;
	}
	public Integer getBoss() {
		return boss;
	}
	public void setBoss(Integer boss) {
		this.boss = boss;
	}

	
}
