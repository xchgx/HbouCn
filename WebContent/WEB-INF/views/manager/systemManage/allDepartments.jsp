<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<div><button onclick="allDepartmentsOperatorCreate();">快速添加</button></div>
<table class="list" style="word-break: break-all" border="0"
		align="center" cellpadding="0" cellspacing="1" bgcolor="#c0de98">
	<tr class="table-tr-title">
		<th  nowrap="nowrap">ID</th>
		<th  nowrap="nowrap">[级别]名称</th>
		<th  nowrap="nowrap">成员</th>
		<th  nowrap="nowrap">授权人</th>
		<th  nowrap="nowrap">类型</th>
		<th  nowrap="nowrap">负责人</th>
		<th  nowrap="nowrap">上级部门</th>
		<th  nowrap="nowrap">下级部门</th>
		<th  nowrap="nowrap">文章数量</th>
		<th  nowrap="nowrap">板块</th>
		<th  nowrap="nowrap">操作</th>
	</tr>
	<c:forEach items="${departments}" var="department">
		<tr class="table-tr-content">
			<td  nowrap="nowrap">${department.id }</td>
			<td  nowrap="nowrap">[${department.level}]${department.name }</td>
			<td  nowrap="nowrap">
			<a href="#" class="allDepartmentListTagATeacher"  tip="${department.id }">${fn:length(department.teachers) }</a>
			<div class="allDepartmentListTagATip" id="allDepartmentListTagATeacher${department.id }">
				部门下的教职工：
				<c:forEach items="${department.teachers }" var="teacher">
				<br>${teacher.name }
				</c:forEach>
			</div>
			</td>
			<td  nowrap="nowrap">
			
			<a href="#" class="allDepartmentListTagAarticleTeachers"  tip="${department.id }">${fn:length(department.articleTeachers) }</a>
				<div class="allDepartmentListTagATip" id="allDepartmentListTagAarticleTeachers${department.id }">
				有发表文章权限的人员：
			<c:forEach items="${department.articleTeachers }" var="articleTeacher">
			<br>${articleTeacher.name }(${articleTeacher.id })
			</c:forEach>
			</div>

			</td>
			<td  nowrap="nowrap"><c:if test="${!empty department.departmentType }">${department.departmentType.name }</c:if> </td>
			<td  nowrap="nowrap"><c:if test="${!empty department.manager }">${department.manager.name }</c:if> </td>
			<td  nowrap="nowrap"><c:if test="${!empty department.fatherDepartment }">${department.fatherDepartment.name } </c:if> </td>
			<td  nowrap="nowrap">
			
			<a href="#" class="allDepartmentListTagAchildDepartments"  tip="${department.id }">${fn:length(department.childDepartments) }</a>
			<div class="allDepartmentListTagATip" id="allDepartmentListTagAchildDepartments${department.id }">
				子部门列表：
 			<c:forEach items="${department.childDepartments }" var="childDepartment">
			<br>${childDepartment.name }(${childDepartment.id })
			</c:forEach>
			</div>
			
			</td>
			<td  nowrap="nowrap">
			
			<a href="#" class="allDepartmentListTagAarticles"  tip="${department.id }">${fn:length(department.articles) }</a>
			<div class="allDepartmentListTagATip" id="allDepartmentListTagAarticles${department.id }">
				文章列表：
 			<c:forEach items="${department.articles }" var="article">
			<br>${article.title }(${article.date })
			</c:forEach>
			</div>
			
			</td>
			<td  nowrap="nowrap">
			
						<a href="#" class="allDepartmentListTagAsections"  tip="${department.id }">${fn:length(department.sections) }</a>
			<div class="allDepartmentListTagATip" id="allDepartmentListTagAsections${department.id }">
				部门下的板块列表：
 			<c:forEach items="${department.sections }" var="section">
			<br>${section.name }(${section.id })
			</c:forEach>
			</div>

			</td>
			<td  nowrap="nowrap">
			<b class="imgOperator" onclick="allDepartmentsOperatorEdit(${department.id});" title="编辑"><img class="imgOperator" border="0" width="25" height="25" src="<%=request.getContextPath()%>/img/sysmanage/edit.png"></b>
			<b class="imgOperator" onclick="allDepartmentsOperatorDelete(${department.id});" title="删除"><img class="imgOperator" border="0" width="25" height="25" src="<%=request.getContextPath()%>/img/sysmanage/delete.png"></b>
			</td>
		</tr>
	</c:forEach>
</table>
<script type="text/javascript" src="<%=request.getContextPath()%>/manager/js/sysmana/allDepartmentsJs.js"></script>

