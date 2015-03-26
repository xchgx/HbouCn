<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="icon" href="<%=request.getContextPath() %>/img/favicon.ico" type="image/x-icon" />
<link REL="SHORTCUT ICON" HREF="<%=request.getContextPath() %>/img/favicon.ico" type="image/x-icon" />
<link href="<%=request.getContextPath() %>/jquery-ui-1.11.1/jquery-ui.css" rel="stylesheet">
<link href="<%=request.getContextPath() %>/jquery-ui-1.11.1/jquery-ui.theme.css" rel="stylesheet">
<script src="<%=request.getContextPath() %>/jquery-ui-1.11.1/external/jquery/jquery.js"></script>
<script src="<%=request.getContextPath() %>/jquery-ui-1.11.1/jquery-ui.js"></script>
<script type="text/javascript">
function getBasePath(){
	var location = (window.location+'').split('/');
	//var basePath = location[0]+'//'+location[2]+'/'+location[3];
	var basePath ='/'+location[3];
	//alert("/manager/js/sysmana/"+basePath);
	return basePath;
}
</script>
<style>
 .ui-tooltip {
width: 210px;
font-size: 12px;
}
*{
	font-size:12px;
}
</style>
</head>
<body>
<table border="1" cellpadding="0" cellspacing="0">
<caption>个人信息</caption>
<tr><td>用户名:</td><td><a title="这只是登陆用的名称，与教师姓名是不一样的！">${user.name }</a></td></tr>
<tr><td>姓名:</td><td><a title="教师姓名">${user.teacher.name }</a></td></tr>
<tr><td>权限:</td><td><a title="权限级别名称">${user.permission.name }</a></td></tr>
<tr><td>所属部门</td><td><a title="本人属于哪个部门">${fn:length(user.teacher.departments) }</a></td></tr>
<tr><td>管理部门</td><td><a title="">${fn:length(user.teacher.managerDepartments) }</a></td></tr>
<tr><td>可在哪些位置发表文章</td><td><a title="">${fn:length(user.teacher.articleDepartments) }</a></td></tr>
<tr><td align="center" colspan="2"><button id="btn_modifyPass">修改密码</button></td></tr>
</table>
<div id="dialog-modifyPassword" title="修改当前用户密码">
<p>修改当前用户密码</p>
<fieldset>
<label for="currentPassword">当前密码</label><br>
<input type="password" name="currentPassword" id="currentPassword" title="如果是第一次修改密码，默认密码是123456。否则请输入当前正在使用的密码。" value="" width="100%" class="text ui-widget-content ui-corner-all"><br>
<label for="newsPassword">新密码</label><br>
<input type="password" name="newsPassword" id="newsPassword"  title="请输入新密码，密码长度大于6位，密码由数字,字符,下划线自由搭配。" value="" width="100%" class="text ui-widget-content ui-corner-all"> <br>
<label for="newsPassword2">确认密码</label><br>
<input type="password" name="newsPassword" id="newsPassword2" title="请再次输入一遍，刚才上面输入的密码。"  value=""  width="100%" class="text ui-widget-content ui-corner-all"> 
</fieldset>
</div>
<script type="text/javascript">
$(function(){
	 var tooltips = $( "[title]" ).tooltip({
		 position: {
			 my: "left top",
			 at: "left+5 bottom+5"
			 }
			 });
	var dialog = $( "#dialog-modifyPassword" ).dialog({
		 autoOpen: false,
		 height: 300,
		 width: 350,
		 modal: true,
		 buttons: {
		 "添加": function(){
				var old=$("#currentPassword").val();
				var pass=$("#newsPassword").val();
				var pass2=$("#newsPassword2").val();
			submitModifyPass(old, pass, pass2);
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
	 
	 $("#btn_modifyPass").button().click(function(){
		 $( "#dialog-modifyPassword" ).dialog("open");
	 });
	 
	 
	 function submitModifyPass(old, newpass, newpass2){
			if(!old || !newpass || !newpass2){
				alert("请输入密码");
				return;
			}else if(newpass != newpass2){
				alert("两次输入的新密码不一致!!");
				return;
			}
			
		var jsonData = {old:old, pass:newpass, pass2:newpass2};
		 //
		$.ajax({
			type: "GET",
			contentType:'application/json;charset=UTF-8',
			url: getBasePath()+"/manager/user/modifyPassword.do",
			data:jsonData,
			dataType:"json",
			success:function(msg){
				if(msg == 1){
					alert("密码修改成功");
				}else if (msg == 0){
					alert("参数传递有空值");
				}else if (msg == -1){
					alert("当前用户没有登陆");
				}else if(msg == -2){
					alert("当前输入的密码与原密码不相符。");
				}else if(msg == -3){
					alert("两次输入的新密码不一致");
				}
			},
			error:function(){
				alert("submitModifyPass error");
			}
	 });
	 }
});
</script>
</body>
</html>