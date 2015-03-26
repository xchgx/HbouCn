<%@page import="com.xchgx.domain.User"%>
<%@page import="com.xchgx.cons.CommonConstant"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta HTTP-EQUIV="pragma" CONTENT="no-cache">
		<meta HTTP-EQUIV="Cache-Control" CONTENT="no-cache, must-revalidate">
		<meta HTTP-EQUIV="expires" CONTENT="0">
		<title>我的Web桌面系统</title>
		<!--<link rel="stylesheet" href="css/themes/base/jquery.ui.all.css">-->
		<link rel="icon" href="<%=request.getContextPath() %>/img/favicon.ico" type="image/x-icon" />
		<link REL="SHORTCUT ICON" HREF="<%=request.getContextPath() %>/img/favicon.ico" type="image/x-icon" />
		<link rel="stylesheet" href="js/contextMenu/jquery.contextMenu.css">
		<link rel="stylesheet" href="js/jquery.tool.css">
		<link rel="stylesheet" href="css/base2.css">
		<link rel="stylesheet" href="js/css.css">
		<script type="text/javascript" src="js/jquery-1.10.2.js"></script>
		<script type="text/javascript" src="js/jquery-ui-1.10.4.custom.js"></script>
		<!--
		<script type="text/javascript" src="js/ui/jquery.ui.core.js"></script>
		<script type="text/javascript" src="js/ui/jquery.ui.widget.js"></script>
		<script type="text/javascript" src="js/ui/jquery.ui.mouse.js"></script>
		<script type="text/javascript" src="js/ui/jquery.ui.button.js"></script>
		<script type="text/javascript" src="js/ui/jquery.ui.draggable.js"></script>
		<script type="text/javascript" src="js/ui/jquery.ui.position.js"></script>
		<script type="text/javascript" src="js/ui/jquery.ui.resizable.js"></script>
		<script type="text/javascript" src="js/ui/jquery.ui.button.js"></script>
		<script type="text/javascript" src="js/ui/jquery.ui.dialog.js"></script>-->
		
		<script type="text/javascript" src="js/contextMenu/jquery.ui.position.js"></script>
		<script type="text/javascript" src="js/contextMenu/jquery.contextMenu.js"></script>
		<!--
		<script type="text/javascript" src="js/jquery.dialogextend.js"></script>
		<script type="text/javascript" src="js/cgCore.js"></script>
		-->

		<script type="text/javascript" src="js/jquery.tool.js"></script>
		<script type="text/javascript" src="js/templates.js"></script>
<!-- 		<script type="text/javascript" src="js/shortcut.js"></script> -->
		<!-- <script type="text/javascript" src="js/cgDialogOperation.js"></script>-->
		<script type="text/javascript">
		function getBasePath(){
			var location = (window.location+'').split('/');
			//var basePath = location[0]+'//'+location[2]+'/'+location[3];
			var basePath ='/'+location[3];
			//alert("/manager/js/sysmana/"+basePath);
			return basePath;
		}
		</script>
		<script type="text/javascript" src="js/core.js"></script>
		<%=session.getAttribute("shortcuts") %>
		<script>
			$(function(){

				//IE下禁止选中
				//document.body.onselectstart = document.body.ondrag = function(){
				//return false;
			//}
			//初始化
			Core.init();
		});
	</script>
	</head>
	<body style="background:url(images/background/background.jpg) repeat right bottom transparent;">
		<!-- <div id="desk">
		<ul id="deskIcon">
		<li class="desktop_icon" id="icon0" path="http://www.baidu.com/">
		<span class="icon"><img src="images/icon/icon0.png"></span>
		<div class="text_title"><div class="left_cron"></div>
		<div class="text_cron">我的电脑</div>
		<div class="right_cron"></div>
		</div>
		</li>
		<li class="desktop_icon" id="icon1" path="http://www.sina.com.cn/">
		<span class="icon"><img src="images/icon/huishouzhanKong.png"></span>
		<div class="text_title"><div class="left_cron"></div>
		<div class="text_cron">回收站</div>
		<div class="right_cron"></div>
		</div>
		</li>
		</ul>
		</div>
		-->
		<%
		User user = (User)session.getAttribute(CommonConstant.USER_CONTEXT);
		%>
		<input type="hidden" id="userId" value="<%=user.getId()%>">
		<div id="desk"><ul></ul></div>

		<div id="task-bar">
			<div class="taskBar_startBtn_Over" id="taskBar_startBtn"></div><ul class="task-window"></ul><div id="taskbar_datetime">2014年9月13日18时57分55秒</div>
		</div>
		<!--
		<div class="taskBar_startBtn_Over" id="taskBar_startBtn"></div>
		<div id="taskBar_spaceArea"></div>
		<div id="taskBar_clockDate"></div>
		-->


		<!--
		<div id="win0">win0</div><div id="win1">win1</div><div id="shutdown">shutdown</div><div id="systemAttrDialog"></div><div id="systemConfig"></div>
		-->
	</body>
</html>