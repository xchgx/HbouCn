<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- <meta HTTP-EQUIV="pragma" CONTENT="no-cache"> -->
<!-- <meta HTTP-EQUIV="Cache-Control" CONTENT="no-cache, must-revalidate"> -->
<!-- <meta HTTP-EQUIV="expires" CONTENT="0"> -->
<meta name="keywords" content="湖北开放,开放,开放学院,湖北开放职业学院,开院,027-51356220">
<meta name="description" content="湖北开放职业学院是湖北省人民政府批准、教育部备案的全日制普通高等院校。招生热线：027—51356220,湖北开放职业学院是由原湖北函授大学更名改制而成的普通高等职业院校。其前身湖北函授大学是于1984年11月9日经湖北省教育厅鄂教工农[（1984）016号]文批准成立；1988年3月10日经原国家教委[（86）教计学045号]文批准备案。2002年5月10日湖北省人民政府以鄂政函[（2002）59号]文批准，将湖北函授大学更名改制为民办性质的普通高等学院湖北开放职业学院。具有高等学历教育招生资格的全日制普通高等学校。办学近三十年，己培养各类大专毕业生遍布神州大地。[1] ">
 <meta name="robots" content="all">
 <meta name="AUTHOR" content="17902055@qq.com">
<title>${department.name } -湖北开放职业学院 </title>
<link rel="icon" href="${basePath}/img/favicon.ico" type="image/x-icon" />
<link REL="SHORTCUT ICON" HREF="${basePath}/img/favicon.ico" type="image/x-icon" />
<link href="${basePath}/css/global.css" rel="stylesheet" />
<link href="${basePath}/css/indexsection.css" rel="stylesheet" />
<link href="${basePath}/jquery-ui-1.11.1/jquery-ui.css" rel="stylesheet">
<link href="${basePath}/jquery-ui-1.11.1/jquery-ui.theme.css" rel="stylesheet">
<script src="${basePath}/jquery-ui-1.11.1/external/jquery/jquery.js"></script>
<script src="${basePath}/jquery-ui-1.11.1/jquery-ui.js"></script>
<script src="${basePath}/js/global.js"></script>
<style type="text/css">
 .ui-tooltip {
width: 210px;
font-size: 12px;
}
</style>
</head>
<body>
	<div class="indexbg">
	<input type="hidden" value="${department.id?c }"  id="departmentIndexDptId">
		<div class="ui-corner-all top"><%@include file="../../../../top.jsp"%></div>
		<div class="content_navigate"><%@include file="../../../../nav.jsp"%></div>
		<div class="content_main">
			<div class="cg_clas_section_content_left">
			<%@include file="../../../../content_r1c1gonggao.jsp"%>
			<%@include file="content_r2c1_dpt${department.id?c }.jsp"%>
			</div>
			<div>
				<%@include file="content_r1c2_dpt${department.id?c }.jsp"%>
				<div id="contentArticleListId"><%@include file="content_r2c2_list_dpt${department.id?c }_1.jsp"%></div>
			</div>
			<div class="foot"><%@include file="../../../../foot.jsp"%></div>
		</div>
	</div>
<script src="${basePath}/js/functionDynLoadData.js"></script>
<script src="${basePath}/js/navDpt.js"></script>
<script src="${basePath}/js/departmentDynLoadData.js"></script>
</body>
</html>