<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<form id="userEditForm">
	<table border="1">
		<tr>
			<td><input type="hidden" name="id" value="${user.id }">用户名：</td>
			<td><input type="text" name="name" value="${user.name }"><form:form commandName="user"> 
									<form:errors path="name" cssStyle="color: red"></form:errors>
								</form:form></td>
		</tr>
		<tr>
			<td>密码：</td>
			<td><input type="password" name="password" value="${user.password }"><form:form commandName="user"> 
									<form:errors path="password" cssStyle="color: red"></form:errors>
								</form:form></td>
		</tr>
		<tr>
			<td>名称：</td>
			<td><input type="text" name="xm" value="${user.xm }"></td>
		</tr>
		<tr>
			<td>锁定：</td>
			<td>
			<select name="locked">
			<option value="1"
			<c:if test="${user.locked == 1}">
				 selected="selected"
			</c:if>
			>锁定用户</option>
			<option value="0"
			<c:if test="${user.locked == 0}">
				 selected="selected"
			</c:if>
			>正常使用</option>
			</select>
			</td>
		</tr>
		<tr>
			<td>积分：</td>
			<td><input type="text" name="credit" value="${user.credit }"></td>
		</tr>
		<tr>
			<td>权限：</td>
			<td>
			<c:forEach items="${permissions }" var="permission">
			<li style="display:block;float:left; width: 70px;"><input type="radio" name="permissionId" value="${permission.id }" <c:if test="${permission.id==user.permission.id }">checked="checked"</c:if>><label>${permission.name}</label></li>
			</c:forEach>
			</td>
		</tr>
		<tr>
			<td>使用者：</td>
			<td>
			<c:forEach items="${teachers }" var="teacher">
			<li style="display:block;float:left; width: 50px;"><input type="radio" name="teacherId" value="${teacher.id }" <c:if test="${teacher.id==user.teacher.id }">checked="checked"</c:if>><label>${teacher.name }</label></li>
			</c:forEach>
			</td>
		</tr>
		<tr><td colspan="2"><input type="button" onclick="userEditSubmit();" value="提交保存"></td></tr>
	</table>
</form>
<script type="text/javascript">
function userEditSubmit(){
	var formParam = $("#userEditForm").serialize();//序列化表单内容为字符串
	var url = "<%=request.getContextPath()%>/manager/sys/userEdit.do";
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