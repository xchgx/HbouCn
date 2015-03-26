<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- <meta HTTP-EQUIV="pragma" CONTENT="no-cache"> -->
<!-- <meta HTTP-EQUIV="Cache-Control" CONTENT="no-cache, must-revalidate"> -->
<!-- <meta HTTP-EQUIV="expires" CONTENT="0"> -->
<meta name="description"
	content="湖北开放职业学院是湖北省人民政府批准、教育部备案的全日制普通高等院校。招生热线：027—51356220" />
<meta name="keywords" content="湖北开放职业学院" />
<title>${department.name } -湖北开放职业学院 </title>
<link rel="icon" href="<%=request.getContextPath() %>/img/favicon.ico" type="image/x-icon" />
<link REL="SHORTCUT ICON" HREF="<%=request.getContextPath() %>/img/favicon.ico" type="image/x-icon" />
<link href="<%=request.getContextPath() %>/css/global.css" rel="stylesheet" />
<link href="<%=request.getContextPath() %>/css/indexsection.css" rel="stylesheet" />
<link href="<%=request.getContextPath() %>/jquery-ui-1.11.1/jquery-ui.css" rel="stylesheet">
<link href="<%=request.getContextPath() %>/jquery-ui-1.11.1/jquery-ui.theme.css" rel="stylesheet">
<script src="<%=request.getContextPath() %>/jquery-ui-1.11.1/external/jquery/jquery.js"></script>
<script src="<%=request.getContextPath() %>/jquery-ui-1.11.1/jquery-ui.js"></script>
<script src="<%=request.getContextPath() %>/js/global.js"></script>
</head>
<body>
	<div class="indexbg">
	<input type="hidden" value="${departmentId }"  id="departmentIndexDptId">
		<div class="ui-corner-all top"><%@include file="../../../../top.jsp"%></div>
		<div class="content_navigate"><%@include file="../../../../nav.jsp"%></div>
		<div class="content_main">
			<div class="cg_clas_section_content_left">
			<%@include file="../../../../content_r1c1gonggao.jsp"%>
			<%@include file="content_r2c1.jsp"%>
			</div>
			<div>
				<%@include file="content_r1c2.jsp"%>
				<%@include file="content_r2c2_list.jsp"%>
			</div>
			<div class="foot"><%@include file="../../../../foot.jsp"%></div>
		</div>
	</div>
<script src="<%=request.getContextPath() %>/js/navDpt.js"></script>
</body>
</html>