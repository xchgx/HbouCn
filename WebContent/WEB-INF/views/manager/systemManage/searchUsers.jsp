<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div><select id="manSysUserSearchTable" title="请选择要查找的数据库">
<option value="User">用户表</option>
<option value="Teacher">教师表</option>
<option value="Department">部门表</option>
<option value="Article">文章表</option>
<option value="DepartmentType">部门类型表</option>
<option value="Permission">权限表</option>
<option value="Shortcut">桌面图标表</option>
<option value="Section">板块表</option>
<option value="Employee">招生人员表</option>
<option value="Student">录取库</option>
<option value="Work">招办工作表</option>
<option value="Address">地址表</option>
<option value="LoginLog">登陆日志表</option>
</select>
<input type="text" id="manSysUserSearchText"  maxlength="100"><button onclick="selectTableSearch();">搜索</button></div>
<div id="manSysSearchTableData">

</div>

<script type="text/javascript" src="<%=request.getContextPath()%>/manager/js/sysmana/searchTableDataJs.js"></script>
