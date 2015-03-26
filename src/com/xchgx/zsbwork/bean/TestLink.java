package com.xchgx.zsbwork.bean;

/**
 * 用于c3p0测试连接的数据表，此表中不能有数据
 * @author chengang
 *
 */
public class TestLink{

	private Integer id;
	private String name;
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
	
}
