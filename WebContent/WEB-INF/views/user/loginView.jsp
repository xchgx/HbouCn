<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- <meta HTTP-EQUIV="pragma" CONTENT="no-cache"> -->
<!-- <meta HTTP-EQUIV="Cache-Control" CONTENT="no-cache, must-revalidate"> -->
<!-- <meta HTTP-EQUIV="expires" CONTENT="0"> -->
<title>湖北开放职业学院 软件工作室</title>
<link rel="icon" href="<%=request.getContextPath() %>/img/favicon.ico" type="image/x-icon" />
<link REL="SHORTCUT ICON" HREF="<%=request.getContextPath() %>/img/favicon.ico" type="image/x-icon" />
<link href="<c:url value="/jquery-ui-1.11.1/jquery-ui.css" />"
	rel="stylesheet">
<link href="<c:url value="/jquery-ui-1.11.1/jquery-ui.theme.css" />"
	rel="stylesheet">
<script
	src="<c:url value="/jquery-ui-1.11.1/external/jquery/jquery.js" />"></script>
<script src="<c:url value="/jquery-ui-1.11.1/jquery-ui.js" />"></script>
<style type="text/css">
*{
	margin: 0px;
	padding: 0px;
}
body{
	margin: 0px;
	padding: 0px;
	font-size: 12px;
	font-style: normal;
	font-family: sans-serif; 
}
.login_panel {
	background-image: url("../img/login/login_bg.jpg");
	background-repeat: repeat-x;
	height: 700px;
	margin: 0px;
	padding: 0px;
}

.login_main {
	position:absolute;
	background-image: url("../img/login/login_main.jpg");
	background-repeat: no-repeat;
	margin-top:222px;
	margin-left: 25%;
	width: 471px;
	height: 376px;
}
.login_form{
	position: absolute;
	margin-top: 80px;
	margin-left: 90px;
	width: 290px;
	height: 120px;
}
input{
	width: 150px;
}
</style>
</head>
<body>
	<div class="login_panel">
		<div class="login_main">
		<div class="login_form">
			<form action="doLogin.do" method="post" >
					<table align="center" border="0" cellpadding="3" cellspacing="0">
						<tr>
							<td colspan="4" align="center"><span style="font-size: 12px; color: red;text-align: center;">目前没有开放注册功能，<br>登录用户仅限于内部工作人员使用。
							</span></td>
						</tr>
						<tr>
							<td align="right" nowrap="nowrap">用户名：</td>
							<td>
									<input name="name" type="text" block="content_index_block" class="ui-state-default" tabindex="1">
							</td>
							<td rowspan="4"><input type="submit" block=" tabindex="3"  value="" style="background-image: url('../img/login/login_button.jpg'); background-repeat:no-repeat;width: 90px; height: 90px;margin-left: 5px;"></td> 
						</tr>
						<tr><td colspan="2" align="right">&nbsp;</td></tr>
						<tr>
							<td align="right" nowrap="nowrap">密码：</td>
							<td>
								<input type="password" name="password"  block="content_index_block" class="ui-state-default" tabindex="2">
							</td>
						</tr>
						<tr><td colspan="2" align="right"><font color="red">${passwordError }&nbsp; ${lockError }</font></td></tr>
					</table>
				</form>
		</div>
		</div>
	</div>
</body>
</html>