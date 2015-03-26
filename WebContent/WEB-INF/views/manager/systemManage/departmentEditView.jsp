<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
	<%/*
		department部门
		id 唯一主键
		name 部门名称
		schoolId 部门归属学校
			#teachers 下属员工，不在部门编辑JSP页面中修改
			#articleTeachers 有发表文章权限的职工，不在部门编辑JSP页面中修改
		departmentTypeId  部门类型
			#manager 部门负责人头头2，不在部门编辑JSP页面中修改
		fatherDepartment 上级主管部门
		#childDepartment  下级部门
			#articles 文章列表，不在部门编辑JSP页面中修改，也无法修改。
			#section 板块，不在部门编辑JSP页面中修改。
		提交给 /manager/sys/departmentEdit.do
		*/ %>
<style>
<!--
li{
	display: block;
}
-->
</style>
<form id="departmentEditForm">
	<table border="1">
		<tr>
			<td  nowrap="nowrap"><input type="hidden" name="id"  id="dptId" value="${department.id }">部门名称：</td>
			<td  nowrap="nowrap"><input type="text" name="name" value="${department.name }"> </td>
		</tr>
		<tr>
			<td  nowrap="nowrap">部门级别：</td>
			<td  nowrap="nowrap"><input type="text" name="level" value="${department.level }"> </td>
		</tr>
		<tr>
		
			<td  nowrap="nowrap">所属学校：</td>
			<td  nowrap="nowrap">
			<c:forEach items="${schools }" var="school">
			<input type="radio" name="schoolId" value="${school.id }" <c:if test="${school.id==department.school.id }">checked="checked"</c:if>><label>${school.name}</label>
			</c:forEach>
			</td>
		</tr>
		<tr>
			<td  nowrap="nowrap">下属员工：</td>
			<td  nowrap="nowrap">
			<div style="color: blue;">请在教师管理中设置</div>
			<div style="max-height: 100px;max-width:100px;overflow: auto;">
				<c:forEach items="${department.teachers }" var="teacher">
				<li>${teacher.name }(${teacher.id})</li>
				</c:forEach>
			</div>
			</td>
		</tr>
		<tr>
			<td  nowrap="nowrap">有发表文章权的职工：</td>
			<td  nowrap="nowrap">
				<div style="color: blue;">请在教师管理中设置</div>
				<c:forEach items="${department.articleTeachers }" var="teacher">
				<li>${teacher.name }(${teacher.id})</li>
				</c:forEach>
			</td>
		</tr>
		<tr>
			<td  nowrap="nowrap">部门类型：</td>
			<td  nowrap="nowrap">
			<c:forEach items="${departmentTypes }" var="departmentType">
			<input type="radio" name="departmentTypeId" value="${departmentType.id }" <c:if test="${departmentType.id==department.departmentType.id }">checked="checked"</c:if>><label>${departmentType.name}</label>
			</c:forEach>
			</td>
		</tr>
		<tr>
			<td  nowrap="nowrap">部门负责人：</td>
			<td  nowrap="nowrap">
			<div style="color: blue;">请在教师管理中设置</div>
			${department.manager.name }(${department.manager.id })
			</td>
		</tr>
		<tr>
			<td  nowrap="nowrap">上级主管部门：</td>
			<td  nowrap="nowrap">
			<li><input type="radio" name="fatherDepartmentId" value="" checked="checked"><label>无</label></li>
			<c:forEach items="${departments }" var="fatherDepartment">
			<li style="display:block;float:left;margin-right: 8px;"><input type="radio" name="fatherDepartmentId" value="${fatherDepartment.id }" <c:if test="${fatherDepartment.id==department.fatherDepartment.id }">checked="checked"</c:if>><label>${fatherDepartment.name }</label></li>
			</c:forEach>
			</td>
		</tr>
				<tr>
			<td  nowrap="nowrap">下属部门：</td>
			<td  nowrap="nowrap">
			<c:forEach items="${childDepartments }" var="childDepartment">
			<li>${childDepartment.name }(${childDepartment.id })</li>
			</c:forEach>
			&nbsp;
			</td>
		</tr>
				<tr>
			<td  nowrap="nowrap">部门文章数量：</td>
			<td  nowrap="nowrap">
			${fn:length(department.articles) }
			</td>
		</tr>
				<tr>
			<td  nowrap="nowrap">部门板块：</td>
			<td  nowrap="nowrap">
			<span id="departmentSections">
			<c:forEach items="${department.sections }" var="section">
			<li>${section.name }(${section.id })</li>
			</c:forEach>
			<a href="#" onclick="openDialog();">+添加</a>
			</span>
			</td>
		</tr>
		<tr>
			<td  nowrap="nowrap">备注：</td>
			<td  nowrap="nowrap"><input type="text" name="description" value="${department.description }"> </td>
		</tr>
		<tr><td  nowrap="nowrap" colspan="2" align="center"><input type="button" onclick="departmentEditSubmit();" value="提交保存"></td></tr>
	</table>
</form>
<div id="dialog-addSectionForm" title="添加新栏目">
<p>添加新栏目</p>
<fieldset>
<label for="sectionName">栏目名称</label><br>
<input type="text" name="sectionName" id="sectionName" value="" width="100%" class="text ui-widget-content ui-corner-all"><br>
<label for="sectionDescription">备注</label><br>
<input type="text" name="sectionDescription" id="sectionDescription" value="" width="100%" class="text ui-widget-content ui-corner-all"> 
</fieldset>
</div>

<script type="text/javascript">

var dialog;
var sectionName = $("#sectionName");
var sectionDescription = $("#sectionDescription");

function departmentEditSubmit(){
	var formParam = $("#departmentEditForm").serialize();//序列化表单内容为字符串
	var url = "<%=request.getContextPath()%>/manager/sys/departmentEdit.do";
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
function createSection(dptid,sectionName, sectionDescription){
	var url = buildUrl("doCreateSection");
	var jsonstr ={"departmentId":dptid,"sectionName":sectionName,"sectionDescription":sectionDescription};
	 $.ajax({
			type: "GET",
			contentType:'application/json;charset=UTF-8',
			url: url,
			data:jsonstr,
			dataType:"json",
			success:function(msg){
				dynSections(msg);
			},
			error:function(){
				alert("error") ;
			}
	 });
}

$(function(){
	dialog = $( "#dialog-addSectionForm" ).dialog({
		 autoOpen: false,
		 height: 300,
		 width: 350,
		 modal: true,
		 buttons: {
		 "添加": function(){
			var dptid=$("#dptId").val();
			 //alert("部门ID："+dptid+"，栏目名称："+sectionName.val()+"，栏目备注："+sectionDescription.val());
			 createSection(dptid,sectionName.val(), sectionDescription.val());
			 dialog.dialog("close");
		 },
		 Cancel: function() {
		 dialog.dialog( "close" );
		 }
		 },
		 close: function() {
			 dialog.dialog( "close" );
		 }
		 });
});
function openDialog(){
	dialog.dialog("open");
}
function dynSections(msg){
	if(msg){
		var html = "";
		$.each(msg,function(i, section){
			html += "<li>"+section.name+"("+section.id+")</li>";
		});
		html+="<li><a href='#' id='create-section' onclick='openDialog();'>添加新栏目</a></li>";
		$("#departmentSections").html(html);
	}
}
</script>