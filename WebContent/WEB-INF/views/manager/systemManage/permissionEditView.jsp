<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<form id="permissionEditForm">
	<table border="1">
		<tr>
			<td><input type="hidden" name="id" value="${permission.id }">名称：</td>
			<td><input type="text" name="name" value="${permission.name }"> </td>
		</tr>
		<tr>
			<td>级别：</td>
			<td><input type="text" name="level" value="${permission.level }"> </td>
		</tr>
		<tr>
			<td>桌面图标：</td>
			<td>
			<table  border="0"><tr>
			<c:forEach items="${shortcuts }" var="shortcut" varStatus="stat">
			<c:if test="${stat.index%5==0}"></tr><tr></c:if><td>
			<input type="checkbox" name="shortcutId" id="shortcut${shortcut.id }" value="${shortcut.id }"  
				<c:forEach items="${permission.shortcuts }" var="sc">
				<c:if test="${shortcut.id==sc.id }">
				checked="checked"
				</c:if>
			</c:forEach>
			><label for="shortcut${shortcut.id }">${shortcut.name}</label>
			</td></c:forEach></tr></table>
			</td>
		</tr>
		<tr>
			<td>备注</td>
			<td><input type="text" name="description" value="${permission.description }"></td>
		</tr>
		<tr><td colspan="2"><input type="button" onclick="permissionEditSubmit();" value="提交保存"></td></tr>
	</table>
</form>
<script type="text/javascript">
function permissionEditSubmit(){
	var formParam = $("#permissionEditForm").serialize();//序列化表单内容为字符串
	var url = "<%=request.getContextPath()%>/manager/sys/permissionEdit.do";
	$.ajax({
		type:"POST",
		url: url,
		cache:false,
		data: formParam,
		dataType:"html",
		async: false,
		success: function(data){
			$("#systemManageContent").html(data);
		},
		error:function(){
			alert("error");
		}
	
	});
}
</script>