<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
// 	response.setHeader("Pragma", "No-cache");
// 	response.setHeader("Cache-Control", "no-cache");
// 	response.setDateHeader("Expires", 0);
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>新闻管理</title>
<link href="<c:url value="/jquery-ui-1.11.1/jquery-ui.css" />" rel="stylesheet">
<link href="<c:url value="/jquery-ui-1.11.1/jquery-ui.theme.css" />" rel="stylesheet">
<script src="<c:url value="/jquery-ui-1.11.1/external/jquery/jquery.js" />"></script>
<script src="<c:url value="/jquery-ui-1.11.1/jquery-ui.js" />"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/manager/js/sysmana/global.js"></script>
</head>
<body>
<table>
	<tr>
		<td valign="top">
				<div id="newsManagerAccordion"
					style="width: 170px; font-size: 12px;">
					<h3>部门新闻列表</h3>
					<div style="text-align: center;">
						<c:forEach items="${departments }" var="department">
							<div hrefid="${department.id }" block="content_newsList_block"
							class="ui-state-default" >${department.name }</div>
						</c:forEach>
					</div>
				</div>
			</td>
		<td valign="top" align="left">
		<div id="newsList"></div>
		</td>
	</tr>
</table>
<script>
// function createNewWindow(id){
// 	window.parent.Core.createNewWindow(100,"编辑文章","../manager",600,800);
// }
// function parentWindowClose(){
// 	var obj = this.parent.window.document.getElementById("window_2_warp");
// 	//alert($(obj).attr("window"));
// 	var w = $(obj).attr("window");
// 	$('.task-window li[window="'+w+'"]', this.parent.window.document).remove();
// 	$(obj).remove();
// }
	var icons = {
		header : "ui-icon-circle-arrow-e",
		activeHeader : "ui-icon-circle-arrow-s"
	};
	$("#newsManagerAccordion").accordion({
		heightStyle : "content",
		icons : icons
	});
	$("[block='content_newsMenu_block']").on({
		mouseenter:function(){
			$(this).addClass("ui-state-highlight ui-corner-all");
		},mouseleave: function(){
			$(this).removeClass("ui-state-highlight ui-corner-all");
		},click:function(){
			$("#newsList").html(getLoadingGifPath());
			$("#newsList").load("<%=request.getContextPath()%>/manager/news/list.do?departmentType=" + $(this).attr("hrefid"));
// 			$("div[name='newsList']").css({"display":"none"});
// 			$("#newsList"+$(this).attr("hrefid")).css({"display":"inline"});
		}
	});
	$("[block='content_newsList_block']").on({
		mouseenter:function(){
			$(this).addClass("ui-state-highlight ui-corner-all");
		},mouseleave: function(){
			$(this).removeClass("ui-state-highlight ui-corner-all");
		},click:function(){
			$("#newsList").html(getLoadingGifPath());
			$("#newsList").load("<%=request.getContextPath()%>/manager/news/list.do?departmentId=" + $(this).attr("hrefid"));
// 			$("div[name='newsList']").css({"display":"none"});
// 			$("#newsList"+$(this).attr("hrefid")).css({"display":"inline"});
		}
	});
	
	$("[block='content_newsMenu_block']").css({"cursor":"pointer","margin-bottom":"5px"});//cursor: pointer;
	$("[block='content_newsList_block']").css({"cursor":"pointer","margin-bottom":"5px"});//cursor: pointer;
</script>
</body>
</html>