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
<link  href="<%=request.getContextPath() %>/manager/js/sysmana/allDepartmentsCss.css" rel="stylesheet">
<link  href="<%=request.getContextPath() %>/manager/js/sysmana/allTeachersCss.css" rel="stylesheet">
<link  href="<%=request.getContextPath() %>/manager/js/sysmana/allPermissionsCss.css" rel="stylesheet">
<link  href="<%=request.getContextPath() %>/manager/js/sysmana/allShortcutsCss.css" rel="stylesheet">
<%-- <link  href="<%=request.getContextPath() %>/manager/js/sysmana/allArticlesCss.css" rel="stylesheet"> --%>

<script src="<c:url value="/jquery-ui-1.11.1/external/jquery/jquery.js" />"></script>
<script src="<c:url value="/jquery-ui-1.11.1/jquery-ui.js" />"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/manager/js/sysmana/global.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/manager/js/sysmana/main.js"></script>

<table border="0" cellspace="0">
<tr><td valign="top">
<div class="system_manage_panel">
	<div class="systemManagePanelMenu">

		<div id="sysManaPaneMenuAccordion">
			<h3>用户管理</h3>
			<div>
				<div class="ui-state-default menuElement" href="allUsers">所有用户</div>
				<div class="ui-state-default menuElement" href="noTeacherUsers">无效用户</div>
				<div class="ui-state-default menuElement" href="searchUsers">搜索用户</div>
			</div>
			
			<h3>部门管理</h3>
			<div>
				<div class="ui-state-default menuElement" href="allDepartments">所有部门</div>
				<div class="ui-state-default menuElement" href="allOneDepartments">一级部门</div>
				<div class="ui-state-default menuElement" href="allTwoDepartments">二级部门</div>
			</div>
			
			<h3>教师管理</h3>
			<div>
				<div class="ui-state-default menuElement" href="allTeachers">所有教师</div>
				<div class="ui-state-default menuElement" href="teacherOperator">部门主管</div>
				<div class="ui-state-default menuElement" href="allInTeachers">校内教师</div>
				<div class="ui-state-default menuElement" href="allOutTeachers">校外教师</div>
			</div>
			
			<h3>权限管理</h3>
			<div>
				<div class="ui-state-default menuElement" href="allPermissions">权限列表</div>
				<div class="ui-state-default menuElement" href="allHavePermissionUsers">授权用户</div>
				<div class="ui-state-default menuElement" href="allNoPermissionUsers">无权用户</div>
			</div>
			
			<h3>桌面图标</h3>
			<div>
				<div class="ui-state-default menuElement" href="allShortcuts">所有图标</div>
			</div>
			
			<h3>文章管理</h3>
			<div>
				<div class="ui-state-default menuElement" href="allArticles">所有文章</div>
				<div class="ui-state-default menuElement" href="allArticlesNoOwer">无归属文章</div>
				<div class="ui-state-default menuElement" href="allArticlesFenLei">文章分类</div>
			</div>
			
			<h3>地点管理（招生）</h3>
			<div>
				<div class="ui-state-default menuElement" href="allAddresses">所有地点</div>
				<div class="ui-state-default menuElement" href="AddressGroupByArea">分区查看</div>
			</div>
			
			<h3>业务员管理（招生）</h3>
			<div>
				<div class="ui-state-default menuElement" href="allEmployees">业务员列表</div>
				<div class="ui-state-default menuElement" href="EmployeeGroupByArea">管辖区域</div>
			</div>
			
			<h3>学校管理（招生）</h3>
			<div>
				<div class="ui-state-default menuElement" href="allSchools">所有学校</div>
				<div class="ui-state-default menuElement" href="schoolGroupByArea">分区查看</div>
			</div>
			
			<h3>招生管理（招生）</h3>
			<div>
				<div class="ui-state-default menuElement" href="allWorks">所有工作记录</div>
				<div class="ui-state-default menuElement" href="workGroupByArea">分区查看</div>
				<div class="ui-state-default menuElement" href="workGroupByEmployee">分人查看</div>
			</div>
			
			<h3>扩展功能</h3>
			<div>
				<div class="ui-state-default menuElement">教务选修课系统</div>
				<div class="ui-state-default menuElement">学籍查询系统</div>
				<div class="ui-state-default menuElement">成绩查询系统</div>
				<div class="ui-state-default menuElement">就业查询系统</div>
				<div class="ui-state-default menuElement">图书管理系统</div>
				<div class="ui-state-default menuElement">在线报名系统</div>
				<div class="ui-state-default menuElement">在线考试系统</div>
			</div>
			
			<h3>日志管理</h3>
			<div>
				<div class="ui-state-default menuElement" href="allLoginLogs">查看日志</div>
				<div class="ui-state-default menuElement" href="backupLog">备份日志</div>
				<div class="ui-state-default menuElement" href="clearLog">清空日志</div>
			</div>
			
			<h3>数据操作</h3>
			<div>
				<div class="ui-state-default menuElement" href="initDatabase">初始化</div>
				<div class="ui-state-default menuElement" href="backupDatabase">备份数据</div>
				<div class="ui-state-default menuElement" href="restoreDatabase">还原数据</div>
			</div>
			
			<h3>配置文件</h3>
			<div>
				<div class="ui-state-default menuElement" href="initPropertiesFile">初始化配置文件</div>
				<div class="ui-state-default menuElement" href="backupDatabase">备份数据</div>
				<div class="ui-state-default menuElement" href="restoreDatabase">还原数据</div>
			</div>
		</div>
		

	</div>
</div>
</td><td valign="top" align="left">
	<div id="systemManageContent" align="center">请点击左侧链接，查看具体内容</div>
		</td></tr></table>
</body>