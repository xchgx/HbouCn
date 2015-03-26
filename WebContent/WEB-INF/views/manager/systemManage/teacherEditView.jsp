<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<form id="teacherEditForm">
	<table border="1">
		<tr>
			<td><input type="hidden" name="id" value="${teacher.id }">教工号：</td>
			<td><input type="text" name="jgh" value="${teacher.jgh }"></td>
		</tr>
		<tr>
			<td>姓名：</td>
			<td><input type="text" name="name" value="${teacher.name }"></td>
		</tr>
		<tr>
			<td>性别：</td>
			<td>
			<input type="radio" name="sex" value="男" <c:if test="${teacher.sex=='男' }">checked="checked"</c:if>><label>男</label>
			<input type="radio" name="sex" value="女" <c:if test="${teacher.sex=='女' }">checked="checked"</c:if>><label>女</label>
			</td>
		</tr>
		<tr>
			<td>年龄：</td>
			<td><input type="text" name="age" value="${teacher.age }"></td>
		</tr>
		<tr>
			<td>职位：</td>
			<td><input type="text" name="position" value="${teacher.position }"></td>
		</tr>
		<tr>
			<td>手机：</td>
			<td><input type="text" name="phone" value="${teacher.phone }"></td>
		</tr>
		<tr>
			<td>备注：</td>
			<td><input type="text" name="description" value="${teacher.description }"></td>
		</tr>
		<tr>
			<td>高校招生人：</td>
			<td>
			<input type="radio" name="employeeId" id="employee00" value="" checked="checked"><label for="employee00">无</label>
			<c:forEach items="${employees }" var="employee">
			<input type="radio" name="employeeId" id="employee${employee.id }" value="${employee.id }" <c:if test="${employee.id==teacher.employee.id }">checked="checked"</c:if>><label for="employee${employee.id }">${employee.name}</label>
			</c:forEach>
			</td>
		</tr>
		<tr>
			<td>归属学校：</td>
			<td>
			<input type="radio" name="schoolId" id="schoolId00" value=""><label for="schoolId00">无</label>
			<c:forEach items="${schools }" var="school">
			<input type="radio" name="schoolId" id="school${school.id }" value="${school.id }" <c:if test="${school.id==teacher.school.id }">checked="checked"</c:if>><label for="school${school.id }">${school.name}</label>
			</c:forEach>
			</td>
		</tr>
		<tr>
			<td>归属部门：</td>
			<td>
			<table  border="0"><tr>
			<c:forEach items="${departments }" var="department" varStatus="stat">
			<c:if test="${stat.index%5==0}"></tr><tr></c:if><td>
			<input type="checkbox" name="departmentId" id="department${department.id }" value="${department.id }"  
				<c:forEach items="${teacher.departments }" var="teacherDepartment">
				<c:if test="${department.id==teacherDepartment.id }">
				checked="checked"
				</c:if>
			</c:forEach>
			><label for="department${department.id }">${department.name}</label>
			</td></c:forEach></tr></table>
			</td>
		</tr>
		<tr>
			<td>管理下的部门：</td>
			<td>
			<table  border="0"><tr>
			<c:forEach items="${departments }" var="department" varStatus="stat">
			<c:if test="${stat.index%5==0}"></tr><tr></c:if><td>
			<input type="checkbox" name="managerDepartmentId" id="managerDepartment${department.id }" value="${department.id }" 
			<c:forEach items="${teacher.managerDepartments}" var="managerDepartment">
				<c:if test="${department.id==managerDepartment.id }">
				checked="checked"
				</c:if>
			</c:forEach>
			><label for="managerDepartment${department.id }">${department.name}</label>
			</td></c:forEach></tr></table>
			</td>
		</tr>
		<tr>
			<td>登陆用户：</td>
			<td>
			<c:forEach items="${users }" var="user">
			<input type="radio" name="userId" id="user${user.id }" value="${user.id }" <c:if test="${user.id==teacher.user.id }">checked="checked"</c:if>><label for="user${user.id }">${user.name}</label>
			</c:forEach>
			</td>
		</tr>
		
		<tr>
			<td>拥有发表权限的部门：</td>
			<td>
			<table  border="0"><tr>
			<c:forEach items="${departments }" var="department" varStatus="stat">
			<c:if test="${stat.index%5==0}"></tr><tr></c:if><td>
			<input type="checkbox" name="articleDepartmentId" id="articleDepartment${department.id }" value="${department.id }" 
			<c:forEach items="${teacher.articleDepartments}" var="teacherDepartment">
				<c:if test="${department.id==teacherDepartment.id }">
				checked="checked"
				</c:if>
			</c:forEach>
			><label for="articleDepartment${department.id }">${department.name}</label>
			</td></c:forEach></tr></table>
			</td>
		</tr>
		<tr><td colspan="2"><input type="button" onclick="teacherEditSubmit();" value="提交保存"></td></tr>
	</table>
</form>
<script type="text/javascript">
function teacherEditSubmit(){
	var formParam = $("#teacherEditForm").serialize();//序列化表单内容为字符串
//	var formParam = $("#teacherEditForm").serializeArray();//序列化表单内容为字符串
// 	alert(JSON.stringify(formParam));
// 	alert(formParam);
	var url = "<%=request.getContextPath()%>/manager/sys/teacherEdit.do";
	$.ajax({
		type:"POST",
		url: url,
		cache:false,
		data: formParam,
		//dataType:"json",
		//contentType:"application/json",  
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