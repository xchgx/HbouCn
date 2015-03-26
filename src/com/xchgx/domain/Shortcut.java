package com.xchgx.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Shortcut implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -263074043049265356L;
	private Integer id;
	private Integer icoid;
	private String name;
	private String ico;
	private String url;
	private Integer height;
	private Integer width;
	private String description;
	private Set<Permission> permissions = new HashSet<Permission>();
	public Shortcut() {
	}
	
	public Shortcut(Integer icoid, String name, String ico,
			String url, Integer height, Integer width, String description) {
		this.icoid= icoid;
		this. name=name;
		this.ico=ico;
		this.url=url;
		this.height= height;
		 this.width=width;
		 this.description= description;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getIcoid() {
		return icoid;
	}

	public void setIcoid(Integer icoid) {
		this.icoid = icoid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getIco() {
		return ico;
	}

	public void setIco(String ico) {
		this.ico = ico;
	}

	public Integer getHeight() {
		return height;
	}

	public void setHeight(Integer height) {
		this.height = height;
	}

	public Integer getWidth() {
		return width;
	}

	public void setWidth(Integer width) {
		this.width = width;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<Permission> getPermissions() {
		return permissions;
	}

	public void setPermissions(Set<Permission> permissions) {
		this.permissions = permissions;
	}
}
