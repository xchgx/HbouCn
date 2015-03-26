<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
// 	response.setHeader("Pragma", "No-cache");
// 	response.setHeader("Cache-Control", "no-cache");
// 	response.setDateHeader("Expires", 0);
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<link href="<c:url value="/jquery-ui-1.11.1/jquery-ui.css" />" rel="stylesheet">
<link href="<c:url value="/jquery-ui-1.11.1/jquery-ui.theme.css" />" rel="stylesheet">

<link  href="<%=request.getContextPath() %>/manager/js/sysmana/global.css" rel="stylesheet">
<link  href="<%=request.getContextPath() %>/manager/js/sysmana/main.css" rel="stylesheet"> 
<link  href="<%=request.getContextPath() %>/manager/js/sitemana/allNavigationsCss.css" rel="stylesheet"> 

<script src="<c:url value="/jquery-ui-1.11.1/external/jquery/jquery.js" />"></script>
<script src="<c:url value="/jquery-ui-1.11.1/jquery-ui.js" />"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/manager/js/sysmana/global.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/manager/js/sitemana/main.js"></script>

<table border="0" cellspace="0">
<tr><td valign="top">
<div class="user_manage_computer_panel">
				<div class="userManageComputerPanelMenu">

					<div id="usrManaComputerPaneMenuAccordion">
						<h3>首页</h3>
						<div>
							<div class="ui-state-default menuElement" href="allNavigations" dptId="0">导航栏</div>
							<div class="ui-state-default menuElement" href="releaseFreemarkerIndex" dptId="0">生成首页</div>
						</div>

						<h3>部门页</h3>
						<div id="sysManaMainDepartmentOption"></div>

<!-- 						<h3>全局</h3> -->
<!-- 						<div> -->
<!-- 							<div class="ui-state-default menuElement" href="htmlSite">全部静态化</div> -->
<!-- 						</div> -->

					</div>
				</div>
			</div>
		</td><td valign="top" align="left">
	<div id="systemManageContent" align="center">请点击左侧链接，查看具体内容</div>
		</td></tr></table>
</body>