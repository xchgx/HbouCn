<%@page import="java.io.File"%>
<%@page import="com.xchgx.cons.PropertyFileOperation"%>
<%@page import="java.util.Formatter"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- <meta HTTP-EQUIV="pragma" CONTENT="no-cache"> -->
<!-- <meta HTTP-EQUIV="Cache-Control" CONTENT="no-cache, must-revalidate"> -->
<!-- <meta HTTP-EQUIV="expires" CONTENT="0"> -->
<meta name="description"
	content="湖北开放职业学院是湖北省人民政府批准、教育部备案的全日制普通高等院校。招生热线：027—51356220" />
<meta name="keywords" content="湖北开放职业学院" />
<title>湖北开放职业学院 官网</title>
<link rel="icon" href="<%=request.getContextPath() %>/img/favicon.ico" type="image/x-icon" />
<link rel="SHORTCUT ICON" href="<%=request.getContextPath() %>/img/favicon.ico" type="image/x-icon" />
<link href="<%=request.getContextPath() %>/css/global.css" rel="stylesheet" />
<link href="<%=request.getContextPath() %>/jquery-ui-1.11.1/jquery-ui.css" rel="stylesheet">
<link href="<%=request.getContextPath() %>/jquery-ui-1.11.1/jquery-ui.theme.css" rel="stylesheet">
<script src="<%=request.getContextPath() %>/jquery-ui-1.11.1/external/jquery/jquery.js"></script>
<script src="<%=request.getContextPath() %>/jquery-ui-1.11.1/jquery-ui.js"></script>
<script src="<%=request.getContextPath() %>/js/global.js"></script>
<!-- bxSlider Javascript file -->
<script src="<%=request.getContextPath() %>/bxslider/jquery.bxslider.min.js"></script>
<!-- bxSlider CSS file -->
<link href="<%=request.getContextPath() %>/bxslider/jquery.bxslider.css" rel="stylesheet" />
<script src="<%=request.getContextPath() %>/js/functionDynLoadData.js"></script>
<script src="<%=request.getContextPath() %>/js/navIdx.js"></script>
<script src="<%=request.getContextPath() %>/js/indexDynLoadData.js"></script>
<style type="text/css">
 .ui-tooltip {
width: 210px;
font-size: 12px;
}
</style>
</head>
<body>
	<div class="indexbg">
		<div class="ui-corner-all top"><%@include file="top.jsp"%></div>
		<div class="content_navigate"><%@include file="nav.jsp"%></div>
		<div class="content_main">
			<div><%@include file="content_r1c1gonggao.jsp"%>
				<%@include file="content_r1c2huandengpian.jsp"%>
				<%@include file="content_r1c3.jsp"%>
			</div>
			<div style="float: left;height: 220px; width:1010px;background-color: #2788ba;"><img src="./img/xiaoqing.png" style="width:1010px; height:220px;"></div>
			<div>
				<%@include file="content_r2c1zizhuchaxun.jsp"%>
				<%@include file="content_r2c2dongtai.jsp"%>
				<%@include file="content_r2c3.jsp"%>
			</div>
			<div class="foot"><%@include file="foot.jsp"%></div>
		</div>
	</div>
	<div>
	</div>
 
</body>
</html>