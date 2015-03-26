package com.xchgx.domain;

import java.io.Serializable;
import java.util.Date;

import com.xchgx.zsbwork.bean.Department;
import com.xchgx.zsbwork.bean.Teacher;

public class Article implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2590241264459471843L;
	private Integer id;
	private String title="";	//标题
	private Integer type=2;//1：公告通知  2：普通文章
	private String titleStyle="1";// 1: 标准  2:红色  3:斜体 
	private boolean display=false;//是否显示内容，1：显示true  0：不显示false
	private String content="";//内容
	private   Teacher author;//作者
	private String date=""; //发表时间
	private String ip=""; //发表IP
	private String description=""; //备注
	private Date releaseTime;//实际发表时间
	private Department department;//新闻所属板块。
	private Section section;//栏目 
	public Article() {
	}

	public Article( String title,Integer type,  String titleStyle, boolean display, String content, 
			String date, String ip, String description) {
		this.title=title;
		this.type = type;
		this.titleStyle = titleStyle;
		this.display = display;
		this.content=content;
		this.date=date;
		this.ip=ip;
		this.description=description;
	}

	public Article(Integer id, String title, String date){
		this.id = id;
		this.title = title;
		this.date = date;
	}
	public Article(Integer id, String title, String titleStyle, String date){
		this.id = id;
		this.title = title;
		this.titleStyle = titleStyle;
		this.date = date;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getReleaseTime() {
		return releaseTime;
	}

	public void setReleaseTime(Date releaseTime) {
		this.releaseTime = releaseTime;
	}

	public String getTitleStyle() {
		return titleStyle;
	}

	public void setTitleStyle(String titleStyle) {
		this.titleStyle = titleStyle;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
 
	public Teacher getAuthor() {
		return author;
	}

	public void setAuthor(Teacher author) {
		this.author = author;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}
	public boolean isDisplay() {
		return display;
	}
	public void setDisplay(boolean display) {
		this.display = display;
	}

	public Section getSection() {
		return section;
	}

	public void setSection(Section section) {
		this.section = section;
	}
}
