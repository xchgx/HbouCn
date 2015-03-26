<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<div><button onclick="allPermissionsOperatorCreate();">快速添加</button></div>
<table class="list" style="word-break: break-all" border="0"
		align="center" cellpadding="0" cellspacing="1" bgcolor="#c0de98">
	<tr class="table-tr-title">
		<th  nowrap="nowrap">ID</th>
		<th  nowrap="nowrap">名称</th>
		<th  nowrap="nowrap">桌面图标</th>
		<th  nowrap="nowrap">备注</th>
		<th  nowrap="nowrap">操作</th>
	</tr>
	<c:forEach items="${permissions}" var="permission">
		<tr class="table-tr-content">
			<td  nowrap="nowrap">${permission.id }</td>
			<td  nowrap="nowrap">[${permission.level }]${permission.name }</td>
			<td  nowrap="nowrap">
			
			<a href="#" class="allPermissionListTagAshortcuts"  tip="${permission.id }">${fn:length(permission.shortcuts) }</a>
			<div class="allPermissionListTagATip" id="allPermissionListTagAshortcuts${permission.id }">
				拥有下列功能图标：
 			<c:forEach items="${permission.shortcuts }" var="shortcut">
			<br>${shortcut.name }(${shortcut.id })
			</c:forEach>
			</div>
			
			</td>
			<td  nowrap="nowrap">${permission.description }</td>
			<td  nowrap="nowrap" nowrap="nowrap">
			<a href="#" onclick="allPermissionsOperatorEdit(${permission.id});" title="编辑"><img border="0" width="25" height="25" src="<%=request.getContextPath()%>/img/sysmanage/edit.png"></a>
			<a href="#" onclick="allPermissionsOperatorDelete(${permission.id});" title="删除"><img border="0" width="25" height="25" src="<%=request.getContextPath()%>/img/sysmanage/delete.png"></a>
			</td>
		</tr>
	</c:forEach>
</table>
<script type="text/javascript" src="<%=request.getContextPath()%>/manager/js/sysmana/allPermissionsJs.js"></script>