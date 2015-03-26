<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>免费注册</title>
<script type="text/javascript">
function check(){
	alert("hi");
	
}
</script>
</head>
<body>
<center>
<form action="<c:url value="/user/doRegister.html"></c:url>" method="post">
<table>
<tr><td>用户名</td><td><input type="text" name="name"></td></tr>
<tr><td>密码</td><td><input type="password" name="password"></td></tr>
<tr><td>确认密码</td><td><input type="password" name="password2"></td></tr>
<tr><td colspan="2"><input type="submit" value="提交注册" onclick="check();"></td></tr>
<tr><td colspan="2"><a href="<c:url value="/user/login.html" />" title="返回登陆">我有账号</a></tr>
</table>
</form>
</center>
</body>
</html>