<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div><button onclick="allUsersOperatorCreate();">快速添加</button></div>
<table class="list" style="word-break: break-all" border="0"
		align="center" cellpadding="0" cellspacing="1" bgcolor="#c0de98">
	<tr class="table-tr-title">
		<th>ID</th>
		<th  nowrap="nowrap">姓名</th>
		<th  nowrap="nowrap">用户名</th>
		<th  nowrap="nowrap">密码</th>
		<th  nowrap="nowrap">锁定</th>
		<th  nowrap="nowrap">积分</th>
		<th  nowrap="nowrap">权限</th>
		<th  nowrap="nowrap">我是谁</th>
		<th  nowrap="nowrap">操作</th>
	</tr>
	<c:forEach items="${users}" var="u">
		<tr class="table-tr-content">
			<td  nowrap="nowrap">${u.id }</td>
			<td  nowrap="nowrap">${u.xm }</td>
			<td  nowrap="nowrap">${u.name }</td>
			<td  nowrap="nowrap">${u.password }</td>
			<td  nowrap="nowrap">${u.locked }</td>
			<td  nowrap="nowrap">${u.credit }</td>
			<td  nowrap="nowrap"><c:if test="${!empty u.permission }"> ${u.permission.name }(${u.permission.id})</c:if></td>
			<td  nowrap="nowrap"><c:if test="${!empty u.teacher }">${u.teacher.name }(${u.teacher.id })</c:if></td>
			<td  nowrap="nowrap">
			<a href="#" onclick="allUsersOperatorEdit(${u.id});" title="编辑"><img border="0" width="25" height="25" src="<%=request.getContextPath()%>/img/sysmanage/edit.png"></a>
			<a href="#" onclick="allUsersOperatorDelete(${u.id});" title="删除"><img border="0" width="25" height="25" src="<%=request.getContextPath()%>/img/sysmanage/delete.png"></a>
			</td>
		</tr>
	</c:forEach>
</table>
<script type="text/javascript" src="<%=request.getContextPath()%>/manager/js/sysmana/allUsersJs.js"></script>