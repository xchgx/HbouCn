<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户创建成功</title>
</head>
<body>
恭喜，用户${user.name }创建成功。
<a href="<c:url value="/user/login.html" />">返回登陆</a>
</body>
</html>