/**
 * searchUsers
 */

function selectTableSearch(){

	var selectValue = $("#manSysUserSearchTable").val();
	var inputValue = $("#manSysUserSearchText").val();
	
	var url = buildUrl("doSearchUsers");
	var jsonstr ={"table":selectValue, "search":inputValue};
//	alert(jsonstr.table +","+ jsonstr.search);
	if(selectValue == "User"){
		$.ajax({
			type: "GET",
			contentType:'application/json;charset=UTF-8',
			url: url,
			data:jsonstr,
			dataType:"json",
			success:resultTableSearchUser,
			error:function(){
				alert("error") ;
			}
		});
	}else if(selectValue == "Teacher"){
		$.ajax({
			type: "GET",
			contentType:'application/json;charset=UTF-8',
			url: url,
			data:jsonstr,
			dataType:"json",
			success:resultTableSearchTeacher,
			error:function(){
				alert("error") ;
			}
		});
	}else if(selectValue == "Department"){
		$.ajax({
			type: "GET",
			contentType:'application/json;charset=UTF-8',
			url: url,
			data:jsonstr,
			dataType:"json",
			success:resultTableSearchDepartment,
			error:function(){
				alert("error") ;
			}
		});
	}else if(selectValue == "Article"){
		$.ajax({
			type: "GET",
			contentType:'application/json;charset=UTF-8',
			url: url,
			data:jsonstr,
			dataType:"json",
			success:resultTableSearchArticle,
			error:function(){
				alert("error") ;
			}
		});
	}else if(selectValue == "DepartmentType"){
		$.ajax({
			type: "GET",
			contentType:'application/json;charset=UTF-8',
			url: url,
			data:jsonstr,
			dataType:"json",
			success:resultTableSearchDepartmentType,
			error:function(){
				alert("error") ;
			}
		});
	}else if(selectValue == "Permission"){
		$.ajax({
			type: "GET",
			contentType:'application/json;charset=UTF-8',
			url: url,
			data:jsonstr,
			dataType:"json",
			success:resultTableSearchPermission,
			error:function(){
				alert("error") ;
			}
		});
	}else if(selectValue == "Shortcut"){
		$.ajax({
			type: "GET",
			contentType:'application/json;charset=UTF-8',
			url: url,
			data:jsonstr,
			dataType:"json",
			success:resultTableSearchShortcut,
			error:function(){
				alert("error") ;
			}
		});
	}else if(selectValue == "Section"){
		$.ajax({
			type: "GET",
			contentType:'application/json;charset=UTF-8',
			url: url,
			data:jsonstr,
			dataType:"json",
			success:resultTableSearchSection,
			error:function(){
				alert("error") ;
			}
		});
	}else if(selectValue == "Employee"){
		$.ajax({
			type: "GET",
			contentType:'application/json;charset=UTF-8',
			url: url,
			data:jsonstr,
			dataType:"json",
			success:resultTableSearchEmployee,
			error:function(){
				alert("error") ;
			}
		});
	}else if(selectValue == "Student"){
		$.ajax({
			type: "GET",
			contentType:'application/json;charset=UTF-8',
			url: url,
			data:jsonstr,
			dataType:"json",
			success:resultTableSearchStudent,
			error:function(){
				alert("error") ;
			}
		});
	}else if(selectValue == "Work"){
		$.ajax({
			type: "GET",
			contentType:'application/json;charset=UTF-8',
			url: url,
			data:jsonstr,
			dataType:"json",
			success:resultTableSearchWork,
			error:function(){
				alert("error") ;
			}
		});
	}else if(selectValue == "Address"){
		$.ajax({
			type: "GET",
			contentType:'application/json;charset=UTF-8',
			url: url,
			data:jsonstr,
			dataType:"json",
			success:resultTableSearchAddress,
			error:function(){
				alert("error") ;
			}
		});
	}else if(selectValue == "LoginLog"){
		$.ajax({
			type: "GET",
			contentType:'application/json;charset=UTF-8',
			url: url,
			data:jsonstr,
			dataType:"json",
			success:resultTableSearchLoginLog,
			error:function(){
				alert("error") ;
			}
		});
	}

}

function resultTableSearchUser(msg){
	var html = "";
	var top = "<table class=\"list\" style=\"word-break: break-all\" border=\"0\"	align=\"center\" cellpadding=\"0\" cellspacing=\"1\" bgcolor=\"#c0de98\">";
	var End = "</table>";
	var th = "<th>ID</th><th>姓名</th><th>用户名</th><th>锁定</th><th>积分</th><th>权限</th><th>我是谁</th><th>操作</th>";
	html+=top+th;
	$.each(msg, function(i, user){
		html+="<tr  class=\"table-tr-content\">";
		html+="<td>"+user.id+"</td>";
		html+="<td>"+user.name+"</td>";
		html+="<td>"+user.xm+"</td>";
		html+="<td>"+user.locked+"</td>";
		html+="<td>"+user.credit+"</td>";
		if (!user.permission)
		{
			html+="<td>无权限</td>";
		}else{
			html+="<td>"+user.permission.name+"</td>";
		}
		if (!user.teacher)
		{
			html+="<td>无人</td>";
		}else{
			html+="<td>"+user.teacher.name+"</td>";
		}
		html+="<td>";
		html += "<a href='#' onclick='allUsersOperatorEdit("+user.id+");' title='编辑'><img border='0' width='25' height='25' src='"+getBasePath()+"/img/sysmanage/edit.png'></a>";
		html += "<a href='#' onclick='allUsersOperatorDelete("+user.id+");' title='删除'><img border='0' width='25' height='25' src='"+getBasePath()+"/img/sysmanage/delete.png'></a>";
		html+="</td>";
		html+="</tr>";
	});
	html+=End;
	html+="<script type=\"text/javascript\" src=\""+getBasePath()+"/manager/js/sysmana/allUsersJs.js\"></script>";
	$("#manSysSearchTableData").html(html);
} 
function resultTableSearchTeacher(msg){
	var html = "";
	var top = "<table class=\"list\" style=\"word-break: break-all\" border=\"0\"	align=\"center\" cellpadding=\"0\" cellspacing=\"1\" bgcolor=\"#c0de98\">";
	var End = "</table>";
	var th = "<tr class=\"table-tr-title\"><th>ID</th><th>教工号</th><th>姓名</th><th>性别</th><th>年龄</th><th>职位</th><th>手机</th><th>所属学校</th><th>所属部门</th><th>管理下的部门</th><th>拥有登陆账户</th><th>发表的文章</th><th>发表文章权限</th><th>备注</th><th>操作</th></tr>";
	html+=top+th;
	$.each(msg, function(i, teacher){
		html+="<tr  class=\"table-tr-content\">";
		html+="<td>"+teacher.id+"</td>";
		html+="<td>"+teacher.jgh+"</td>";
		html+="<td>"+teacher.name+"</td>";
		html+="<td>"+teacher.sex+"</td>";
		html+="<td>"+teacher.age+"</td>";
		html+="<td>"+teacher.position+"</td>";
		html+="<td>"+teacher.phone+"</td>";
		if (!teacher.school)
		{
			html+="<td>无</td>";
		}else{
			html+="<td>"+teacher.school.name+"</td>";
		}
		if (!teacher.departments)
		{
			html+="<td>无</td>";
		}else{
			html+="<td>"+teacher.departments.length+"</td>";
		}
		if (!teacher.managerDepartments)
		{
			html+="<td>无</td>";
		}else{
			html+="<td>"+teacher.managerDepartments.length+"</td>";
		}
		if (!teacher.user)
		{
			html+="<td>无</td>";
		}else{
			html+="<td>"+teacher.user.name+"</td>";
		}
		if (!teacher.articles)
		{
			html+="<td>无</td>";
		}else{
			html+="<td>"+teacher.articles.length+"</td>";
		}
		if (!teacher.articleDepartments)
		{
			html+="<td>无</td>";
		}else{
			html+="<td>"+teacher.articleDepartments.length+"</td>";
		}
		//managerDepartments
		html+="<td>"+teacher.description+"</td>";
		html+="<td>";
		html += "<a href='#' onclick='allTeachersOperatorEdit("+teacher.id+");' title='编辑'><img border='0' width='25' height='25' src='"+getBasePath()+"/img/sysmanage/edit.png'></a>";
		html += "<a href='#' onclick='allTeachersOperatorDelete("+teacher.id+");' title='删除'><img border='0' width='25' height='25' src='"+getBasePath()+"/img/sysmanage/delete.png'></a>";
		html+="</td>";
		html+="</tr>";
	});
	html+=End;
	html+="<script type=\"text/javascript\" src=\""+getBasePath()+"/manager/js/sysmana/allTeachersJs.js\"></script>";
	$("#manSysSearchTableData").html(html);
}
function resultTableSearchDepartment(msg){
	var html = "";
	var top = "<table class=\"list\" style=\"word-break: break-all\" border=\"0\"	align=\"center\" cellpadding=\"0\" cellspacing=\"1\" bgcolor=\"#c0de98\">";
	var End = "</table>";
	var th = "<tr class=\"table-tr-title\"><th>ID</th><th>[级别]名称</th><th>成员</th><th>授权人</th><th>类型</th><th>负责人</th><th>上级部门</th><th>下级部门</th><th>文章数量</th><th>板块</th><th>操作</th></tr>";
	html+=top+th;
	$.each(msg, function(i, department){
		html+="<tr  class=\"table-tr-content\">";
		html+="<td>"+department.id+"</td>";
		html+="<td>["+department.level+"]"+department.name+"</td>";
		if(!department.teachers){
			html+="<td>无</td>";
		}else{
			html+="<td>"+department.teachers.length+"</td>";
		}
		if(!department.articleTeachers){
			html+="<td>无</td>";
		}else{
			html+="<td>"+department.articleTeachers.length+"</td>";
		}
		if(!department.departmentType){
			html+="<td>无</td>";
		}else{
			html+="<td>"+department.departmentType.name+"</td>";
		}
		if(!department.manager){
			html+="<td>无</td>";
		}else{
			html+="<td>"+department.manager.name+"</td>";
		}
		if(!department.fatherDepartment){
			html+="<td>无</td>";
		}else{
			html+="<td>"+department.fatherDepartment.name+"</td>";
		}
		if(!department.childDepartments){
			html+="<td>无</td>";
		}else{
			html+="<td>"+department.childDepartments.length+"</td>";
		}
		if(!department.articles){
			html+="<td>无</td>";
		}else{
			html+="<td>"+department.articles.length+"</td>";
		}
		if(!department.sections){
			html+="<td>无</td>";
		}else{
			html+="<td>"+department.sections.length+"</td>";
		}
		html+="<td>";
		html += "<a href='#' onclick='allDepartmentsOperatorEdit("+department.id+");' title='编辑'><img border='0' width='25' height='25' src='"+getBasePath()+"/img/sysmanage/edit.png'></a>";
		html += "<a href='#' onclick='allDepartmentsOperatorDelete("+department.id+");' title='删除'><img border='0' width='25' height='25' src='"+getBasePath()+"/img/sysmanage/delete.png'></a>";
		html+="</td>";
		html+="</tr>";
	});
	html+=End;
	html+="<script type=\"text/javascript\" src=\""+getBasePath()+"/manager/js/sysmana/allDepartmentsJs.js\"></script>";
	$("#manSysSearchTableData").html(html);
}
function resultTableSearchArticle(msg){
	var html = "";
	var top = "<table class=\"list\" style=\"word-break: break-all\" border=\"0\"	align=\"center\" cellpadding=\"0\" cellspacing=\"1\" bgcolor=\"#c0de98\">";
	var End = "</table>";
	var th = "<tr class=\"table-tr-title\"><td >ID</td><td >标题</td><td >类型</td><td >是否显示</td><td >部门</td><td >作者</td><td >发表日期</td><td >操作</td></tr>";
	html+=top+th;
	$.each(msg, function(i, article){
		html+="<tr  class=\"table-tr-content\">";
		html+="<td>"+article.id+"</td>";
		html+="<td>"+article.title+"</td>";
		html+="<td>"+article.type+"</td>";
		html+="<td>"+article.display+"</td>";
		if(!article.department){
			html+="<td>无</td>";
		}else{
			html+="<td>"+article.department.name+"</td>";
		}
		if(!article.author){
			html+="<td>无</td>";
		}else{
			html+="<td>"+article.author.name+"</td>";
		}
		html+="<td>"+article.date+"</td>";
		html+="<td>";
		html += "<a href='#' onclick='allArticlesOperatorEdit("+article.id+");' title='编辑'><img border='0' width='25' height='25' src='"+getBasePath()+"/img/sysmanage/edit.png'></a>";
		html += "<a href='#' onclick='allArticlesOperatorDelete("+article.id+");' title='删除'><img border='0' width='25' height='25' src='"+getBasePath()+"/img/sysmanage/delete.png'></a>";
		html+="</td>";
		html+="</tr>";
	});
	html+=End;
	html+="<script type=\"text/javascript\" src=\""+getBasePath()+"/manager/js/sysmana/allArticlesJs.js\"></script>";
	$("#manSysSearchTableData").html(html);
}
function resultTableSearchDepartmentType(msg){
	
}
function resultTableSearchPermission(msg){
	
}
function resultTableSearchShortcut(msg){
	
}
function resultTableSearchSection(msg){
	
}
function resultTableSearchEmployee(msg){
	
}
function resultTableSearchStudent(msg){
	
}
function resultTableSearchWork(msg){
	
}
function resultTableSearchAddress(msg){
	
}
function resultTableSearchLoginLog(msg){
	
}