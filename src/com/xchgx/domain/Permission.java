package com.xchgx.domain;

import java.io.Serializable;
import java.util.Set;
import java.util.HashSet;

public class Permission implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1182609012788073563L;
	private Integer id;
	private Integer level;
	private String name;
	private  Set<Shortcut> shortcuts = new HashSet<Shortcut>();
	private String description;

	public Permission() {
	}
	
	public Permission( Integer level, String name,
			Set<Shortcut> shortcuts, String description) {
		this.level =  level;
		this.name =  name;
		this. shortcuts=  shortcuts;
		this. description=  description;
	}
	public Permission( Integer level, String name,
			 String description) {
		this.level =  level;
		this.name =  name;
		this. description=  description;
	}
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Shortcut> getShortcuts() {
		return shortcuts;
	}

	public void setShortcuts(Set<Shortcut> shortcuts) {
		this.shortcuts = shortcuts;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
