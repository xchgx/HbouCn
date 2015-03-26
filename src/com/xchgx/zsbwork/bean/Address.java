package com.xchgx.zsbwork.bean;

import java.io.Serializable;


public class Address implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8569338622309007487L;
	private Integer id;
	private String name;
	private String mapUrl;
	private String city;// 市
	private String region;// 区
	private String county;// 县
	private String street;// 街道
	private  School school;
	private String description;

	public Address() {
	}
	
	public Address(String name, String mapUrl, String city,
			String region, String county, String street, School school,
			String description) {
		this.name = name;
		this.mapUrl = mapUrl;
		this.city = city;
		this.region = region;
		this.county = county;
		this.street = street;
		this.school = school;
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

	public String getMapUrl() {
		return mapUrl;
	}

	public void setMapUrl(String mapUrl) {
		this.mapUrl = mapUrl;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getCounty() {
		return county;
	}

	public void setCounty(String county) {
		this.county = county;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public School getSchool() {
		return school;
	}

	public void setSchool(School school) {
		this.school = school;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
