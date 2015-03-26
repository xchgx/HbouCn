package com.xchgx.domain;

import java.io.Serializable;
import java.util.Date;

public class LoginLog implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6690564233479875529L;

	private Integer id; // 日志id

	private Date date; // 登陆时间

	private  User user; // 发表者ID

	private String ip; // 登陆IP

	public LoginLog() {
	}
	
	public LoginLog( Date date, User user, String ip) {
this.date=date;
this.user=user;
this.ip=ip;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}
}
