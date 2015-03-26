<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>毕业生报到证、档案托管去向查询系统后台</title>
<link	href="<%=request.getContextPath()%>/jquery-ui-1.11.1/jquery-ui.css"	rel="stylesheet">
<link	href="<%=request.getContextPath()%>/jquery-ui-1.11.1/jquery-ui.theme.css"	rel="stylesheet">
<script	src="<%=request.getContextPath()%>/jquery-ui-1.11.1/external/jquery/jquery.js"></script>
<script	src="<%=request.getContextPath()%>/jquery-ui-1.11.1/jquery-ui.js"></script>
<script	src="<%=request.getContextPath()%>/js/global.js"></script>
<link	href="<%=request.getContextPath()%>/manager/function/jiuye/hukou/main.css"	rel="stylesheet">
<script	src="<%=request.getContextPath()%>/manager/function/jiuye/hukou/main.js"></script>

</head>
<body>
	<table border="0" cellspace="0">
		<tr>
			<td valign="top">
				<div class="jiuye_hukou_manage_panel">
					<div class="jiuye_hukouManagerPanelMenu">
						<div id="jiuye_hukouPaneMenuAccordion">
							<h3>毕业生报到证、档案托管去向系统</h3>
							<div>
								<div class="ui-state-default menuElement" href="search">信息查询</div>
								<div class="ui-state-default menuElement" href="categorization">分类查看</div>
								<div class="ui-state-default menuElement" href="importexport">导入导出</div>
							</div>
						</div>
					</div>
				</div>
			</td>
			<td valign="top" align="left">
				<div id="jiuye_hukouContent" align="center">请点击左侧链接，查看具体内容</div>
			</td>
		</tr>
	</table>
</body>
</html>