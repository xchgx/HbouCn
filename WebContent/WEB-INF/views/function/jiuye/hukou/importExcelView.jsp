<%-- 
    Document   : fileUpload
    Created on : 2015-3-9, 16:14:05
    Author     : cg
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>文件导入</title>
        <link href="/jquery-ui-1.11.1/jquery-ui.css" rel="stylesheet">
        <link href="/jquery-ui-1.11.1/jquery-ui.theme.css" rel="stylesheet">
        <script src="/jquery-ui-1.11.1/external/jquery/jquery.js"></script>
        <script src="/jquery-ui-1.11.1/jquery-ui.js"></script>
    </head>
    <body>
        <h1>导入</h1>
        <form action="<%=request.getContextPath()%>/hukou/doImportExcel.do" method="post" enctype="multipart/form-data">
            <input  type="file" name="fileUpload" id="fileUpload" onchange="change(this);"  size="20" />
            <select name="dbName" >
                <c:forEach items="${year}" var="n" >
                    <option value="${n}">${n}届毕业生报到证、档案托管去向</option>
                </c:forEach>
            </select>
            <br><br>
            <input type="submit" value="上传" />
        </form>

        <script>
//            alert($("#fileUpload"));
            
            $(function(){
                alert("hi");
            });

//            $(function () {
//                alert("hello");
//                $("#fileUpload").change(function () {
//                    var fileName = $("#fileUpload").val();
//                    alert(fileName);
//                });
//            });
            function change(i) {
//                alert(i.value + "  nimabi");
            }
        </script>
    </body>
</html>
