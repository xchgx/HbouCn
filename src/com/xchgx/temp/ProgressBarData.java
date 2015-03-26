package com.xchgx.temp;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class ProgressBarData implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -581222192903613647L;
	
	private Integer length=1; //总长度
	private Integer per=0;//完成度
	private List<String> listData = new ArrayList<String>();//数据
	private Map<String, Object> mapData = new HashMap<String, Object>();
	private Integer finish=1;//是否完成，1：完成 ； 0：未完成
	private HttpServletRequest request;
	private HttpSession session;
	private Integer departmentId; // 部门ID
	private List<Integer> secIds;	//部门子版块ID列表
	private String rootPath;				//网站根目录
	private String basePath;		//项目名称，如： /HbouCn
	private String name;				//名称
	private Thread thread;			//线程
	
	public Integer getLength() {
		return length;
	}
	public void setLength(Integer length) {
		this.length = length;
	}
	public Integer getPer() {
		return per;
	}
	public void setPer(Integer per) {
		this.per = per;
	}
	public List<String> getListData() {
		return listData;
	}
	public void setListData(List<String> listData) {
		this.listData = listData;
	}
	public Map<String, Object> getMapData() {
		return mapData;
	}
	public void setMapData(Map<String, Object> mapData) {
		this.mapData = mapData;
	}
	public Integer getFinish() {
		return finish;
	}
	public void setFinish(Integer finish) {
		this.finish = finish;
	}
	public HttpServletRequest getRequest() {
		return request;
	}
	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}
	public HttpSession getSession() {
		return session;
	}
	public void setSession(HttpSession session) {
		this.session = session;
	}
	public Integer getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}
	public List<Integer> getSecIds() {
		return secIds;
	}
	public void setSecIds(List<Integer> secIds) {
		this.secIds = secIds;
	}
	public String getRootPath() {
		return rootPath;
	}
	public void setRootPath(String rootPath) {
		this.rootPath = rootPath;
	}
	public String getBasePath() {
		return basePath;
	}
	public void setBasePath(String basePath) {
		this.basePath = basePath;
	}
	public Integer addPer(){
		 return ++per;
	}
	public Integer addLength(){
		return ++length;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Thread getThread() {
		return thread;
	}
	public void setThread(Thread thread) {
		this.thread = thread;
	}
}
