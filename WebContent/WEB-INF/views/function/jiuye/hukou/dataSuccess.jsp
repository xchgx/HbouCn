<%-- 
    Document   : dataSuccess
    Created on : 2015-3-9, 16:44:31
    Author     : cg
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        ${path}<br>
        ${error}<br>
        上传的文件
        <c:choose>
            <c:when test="${support}" >支持查询</c:when>
            <c:otherwise>不支持查询，请确保Excel文件是2003版本的xls文件，可以尝试将文件另存为2003版本。</c:otherwise>
        </c:choose>
    </body>
</html>
