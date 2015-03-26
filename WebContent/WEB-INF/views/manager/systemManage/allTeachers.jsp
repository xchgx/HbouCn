<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<div><button onclick="allTeachersOperatorCreate();">快速添加</button></div>
<table class="list" style="word-break: break-all" border="0"
		align="center" cellpadding="0" cellspacing="1" bgcolor="#c0de98">
	<tr class="table-tr-title">
		<th  nowrap="nowrap">ID</th>
		<th  nowrap="nowrap">教工号</th>
		<th  nowrap="nowrap">姓名</th>
		<th  nowrap="nowrap">性别</th>
		<th  nowrap="nowrap">年龄</th>
		<th  nowrap="nowrap">职位</th>
		<th  nowrap="nowrap">手机</th>
		<th  nowrap="nowrap">高校招生人员</th>
		<th  nowrap="nowrap">所属学校</th>
		<th  nowrap="nowrap">所属部门</th>
		<th  nowrap="nowrap">管理下的部门</th>
		<th  nowrap="nowrap">拥有登陆账户</th>
		<th  nowrap="nowrap">发表的文章</th>
		<th  nowrap="nowrap">发表文章权限</th>
		<th  nowrap="nowrap">备注</th>
		<th  nowrap="nowrap">操作</th>
	</tr>
	<c:forEach items="${teachers}" var="teacher">
		<tr class="table-tr-content">
			<td  nowrap="nowrap">${teacher.id }</td>
			<td  nowrap="nowrap">${teacher.jgh }</td>
			<td  nowrap="nowrap">${teacher.name }</td>
			<td  nowrap="nowrap">${teacher.sex }</td>
			<td  nowrap="nowrap">${teacher.age }</td>
			<td  nowrap="nowrap">${teacher.position }</td>
			<td  nowrap="nowrap">${teacher.phone }</td>
			<td  nowrap="nowrap">${teacher.employee.name }</td>
			<td  nowrap="nowrap">${teacher.school.name }</td>
			<td  nowrap="nowrap">
			
			<a href="#" class="allTeacherListTagAdepartments"  tip="${teacher.id }">${fn:length(teacher.departments) }</a>
			<div class="allTeacherListTagATip" id="allTeacherListTagAdepartments${teacher.id }">
				归属与下面的部门管辖：
 			<c:forEach items="${teacher.departments }" var="department">
			<br>${department.name }(${department.id })
			</c:forEach>
			</div>
			
			</td>
			<td  nowrap="nowrap">
			<a href="#" class="allTeacherListTagAmanagerDepartments"  tip="${teacher.id }">${fn:length(teacher.managerDepartments) }</a>
			<div class="allTeacherListTagATip" id="allTeacherListTagAmanagerDepartments${teacher.id }">
				管理下面的部门：
 			<c:forEach items="${teacher.managerDepartments }" var="managerDepartment">
			<br>${managerDepartment.name }(${managerDepartment.id })
			</c:forEach>
			</div>
			</td>
			
			<td  nowrap="nowrap">${teacher.user.name }</td>
			<td  nowrap="nowrap">
			<a href="#" class="allTeacherListTagAarticles"  tip="${teacher.id }">${fn:length(teacher.articles) }</a>
			<div class="allTeacherListTagATip" id="allTeacherListTagAarticles${teacher.id }">
				发表的文章列表：
 			<c:forEach items="${teacher.articles }" var="article">
			<br>${article.title }(${article.date })
			</c:forEach>
			</div>
			</td>
			<td  nowrap="nowrap">
			
			<a href="#" class="allTeacherListTagAarticleDepartments"  tip="${teacher.id }">${fn:length(teacher.articleDepartments) }</a>
			<div class="allTeacherListTagATip" id="allTeacherListTagAarticleDepartments${teacher.id }">
				拥有发表文章权限的部门：
 			<c:forEach items="${teacher.articleDepartments }" var="articleDepartment">
			<br>${articleDepartment.name }
			</c:forEach>
			</div>
			
			</td>
			<td  nowrap="nowrap">${teacher.description }</td>
			<td  nowrap="nowrap">
			<b class="imgOperator" onclick="allTeachersOperatorEdit(${teacher.id});" title="编辑"><img class="imgOperator" border="0" width="25" height="25" src="<%=request.getContextPath()%>/img/sysmanage/edit.png"></b>
			<b class="imgOperator" onclick="allTeachersOperatorDelete(${teacher.id});" title="删除"><img class="imgOperator" border="0" width="25" height="25" src="<%=request.getContextPath()%>/img/sysmanage/delete.png"></b>
			</td>
		</tr>
	</c:forEach>
</table>
<script type="text/javascript" src="<%=request.getContextPath()%>/manager/js/sysmana/allTeachersJs.js"></script>

