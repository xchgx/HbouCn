package com.xchgx.domain;

import java.io.Serializable;

import javax.validation.constraints.Pattern;

import com.xchgx.zsbwork.bean.Teacher;

public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1328092093645519652L;
	
	/**
	 * 锁定用户对应的状态值
	 */
	public static final int USER_LOCK = 1;
	/**
	 * 用户解锁对应的状态值
	 */
	public static final int USER_UNLOCK = 0;
	
	private Integer id;	//用户ID
	@Pattern(regexp="[a-zA-Z0-9_]{5,20}", message="{user.name.error}")
	private String name;//用户名
	private String xm;	//姓名
	@Pattern(regexp="[\\@A-Za-z0-9\\!\\#\\$\\%\\^\\&\\*\\.\\~]{6,20}", message="{user.password.error}")
	private String password;	//密码
	private Integer locked;		//0：未锁定  1：锁定
	private Integer credit;		//积分
	private  Permission permission;
	private  Teacher teacher;
	
	public User() {
	}
	
	public User(String name, String xm , String  password,Integer locked, Integer credit ){
		this.name =name;
		this.xm = xm;
		this.password =password;
		this.locked = locked;
		this.credit=credit;
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
	public String getXm() {
		return xm;
	}
	public void setXm(String xm) {
		this.xm = xm;
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
	public Integer getLocked() {
		return locked;
	}
	public void setLocked(Integer locked) {
		this.locked = locked;
	}
	public Integer getCredit() {
		return credit;
	}
	public void setCredit(Integer credit) {
		this.credit = credit;
	}
	public Permission getPermission() {
		return permission;
	}
	public void setPermission(Permission permission) {
		this.permission = permission;
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}	

}
