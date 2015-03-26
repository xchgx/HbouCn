<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<form id="shortcutEditForm">
	<table border="1">
		<tr>
			<td><input type="hidden" name="id" value="${shortcut.id }">名称：</td>
			<td><input type="text" name="name" value="${shortcut.name }"> </td>
		</tr>
				<tr>
			<td>图标级别：</td>
			<td><input type="text" name="icoid" value="${shortcut.icoid }"> </td>
		</tr>
				<tr>
			<td>ico图片路径：</td>
			<td><input type="text" name="ico" value="${shortcut.ico }"> </td>
		</tr>
				<tr>
			<td>入口网址：</td>
			<td><input type="text" name="url" value="${shortcut.url }"> </td>
		</tr>
				<tr>
			<td>高度：</td>
			<td><input type="text" name="height" value="${shortcut.height }"> </td>
		</tr>
				<tr>
			<td>宽度：</td>
			<td><input type="text" name="width" value="${shortcut.width }"> </td>
		</tr>
		<tr>
			<td>使用权：</td>
			<td>
			<table  border="0"><tr>
			<c:forEach items="${permissions }" var="permission" varStatus="stat">
			<c:if test="${stat.index%5==0}"></tr><tr></c:if><td>
			<input type="checkbox" name="permissionId" id="permission${permission.id }" value="${permission.id }"  
				<c:forEach items="${shortcut.permissions }" var="perms">
				<c:if test="${permission.id==perms.id }">
				checked="checked"
				</c:if>
			</c:forEach>
			><label for="shortcut${permission.id }">${permission.name}</label>
			</td></c:forEach></tr></table>
			</td>
		</tr>
		<tr>
			<td>备注</td>
			<td><input type="text" name="description" value="${shortcut.description }"></td>
		</tr>
		<tr><td colspan="2"><input type="button" onclick="shortcutEditSubmit();" value="提交保存"></td></tr>
	</table>
</form>
<script type="text/javascript">
function shortcutEditSubmit(){
	var formParam = $("#shortcutEditForm").serialize();//序列化表单内容为字符串
	var url = "<%=request.getContextPath()%>/manager/sys/shortcutEdit.do";
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