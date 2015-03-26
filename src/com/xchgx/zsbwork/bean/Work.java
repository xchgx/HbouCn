package com.xchgx.zsbwork.bean;

import java.io.Serializable;

public class Work implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6522046107129978547L;
	private Integer id;
	private  Employee employeeArea;//区域负责人
	private   Employee employee;//跑点工作人员
	private   School school;
	private String contract;	//合同
	private String comming;
	private String difficulty;
	private String time;
	private String detail;//Details，评价评论，详细情况
	private String description;
	
	public Work() {
	}
	
	public Work(Employee employeeArea,Employee employee,School school,String contract,String comming,String difficulty,String time,String detail,String description){
		this.employeeArea=employeeArea;
		this.employee=employee;
		this.school=school;
		this.contract=contract;
		this.comming=comming;
		this.difficulty=difficulty;
		this.time=time;
		this.detail=detail;
		this.description=description;
		
	}

	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Employee getEmployeeArea() {
		return employeeArea;
	}
	public void setEmployeeArea(Employee employeeArea) {
		this.employeeArea = employeeArea;
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
	public String getContract() {
		return contract;
	}
	public void setContract(String contract) {
		this.contract = contract;
	}
	public String getComming() {
		return comming;
	}
	public void setComming(String comming) {
		this.comming = comming;
	}
	public String getDifficulty() {
		return difficulty;
	}
	public void setDifficulty(String difficulty) {
		this.difficulty = difficulty;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}
