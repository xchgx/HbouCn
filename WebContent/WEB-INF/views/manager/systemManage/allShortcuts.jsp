<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<div><button onclick="allShortcutsOperatorCreate();">快速添加</button></div>
<table class="list" style="word-break: break-all" border="0"
		align="center" cellpadding="0" cellspacing="1" bgcolor="#c0de98">
	<tr class="table-tr-title">
		<th  nowrap="nowrap">ID</th>
		<th  nowrap="nowrap">名称</th>
		<th  nowrap="nowrap">图片</th>
		<th  nowrap="nowrap">入口网址</th>
		<th  nowrap="nowrap">窗口高度</th>
		<th  nowrap="nowrap">窗口宽度</th>
		<th  nowrap="nowrap">归类</th>
		<th  nowrap="nowrap">备注</th>
		<th  nowrap="nowrap">操作</th>
	</tr>
	<c:forEach items="${shortcuts}" var="shortcut">
		<tr class="table-tr-content">
			<td  nowrap="nowrap">${shortcut.id }</td>
			<td  nowrap="nowrap">[${shortcut.icoid }]${shortcut.name }</td>
			<td  nowrap="nowrap">${shortcut.ico }</td>
			<td  nowrap="nowrap">${shortcut.url }</td>
			<td  nowrap="nowrap">${shortcut.height }</td>
			<td  nowrap="nowrap">${shortcut.width }</td>
			<td  nowrap="nowrap">
			
			<a href="#" class="allShortcutListTagApermissions"  tip="${shortcut.id }">${fn:length(shortcut.permissions) }</a>
			<div class="allShortcutListTagATip" id="allShortcutListTagApermissions${shortcut.id }">
				哪些权限使用了我这个图标：
 			<c:forEach items="${shortcut.permissions }" var="permission">
			<br>${permission.name }(${permission.id })
			</c:forEach>
			</div>
			
			</td>
			<td  nowrap="nowrap">${shortcut.description }</td>
			<td  nowrap="nowrap" nowrap="nowrap">
			<a href="#" onclick="allShortcutsOperatorEdit(${shortcut.id});" title="编辑"><img border="0" width="25" height="25" src="<%=request.getContextPath()%>/img/sysmanage/edit.png"></a>
			<a href="#" onclick="allShortcutsOperatorDelete(${shortcut.id});" title="删除"><img border="0" width="25" height="25" src="<%=request.getContextPath()%>/img/sysmanage/delete.png"></a>
			</td>
		</tr>
	</c:forEach>
</table>
<script type="text/javascript" src="<%=request.getContextPath()%>/manager/js/sysmana/allShortcutsJs.js"></script>