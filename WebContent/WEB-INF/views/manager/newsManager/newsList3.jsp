<%@page import="com.xchgx.domain.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
	response.setHeader("Pragma", "No-cache");
	response.setHeader("Cache-Control", "no-cache");
	response.setDateHeader("Expires", 0);
	User user = (User)session.getAttribute("USER_CONTEXT");
	if(user == null){
		response.sendRedirect(request.getContextPath());
	}
%>
   <%//=request.getContextPath()%>
	<link rel="stylesheet" href="<%=request.getContextPath()%>/kindeditor/themes/default/default.css" />
	<link rel="stylesheet" href="<%=request.getContextPath()%>/kindeditor/plugins/code/prettify.css" />
	<script charset="utf-8" src="<%=request.getContextPath()%>/kindeditor/kindeditor.js" ></script>
	<script charset="utf-8" src="<%=request.getContextPath()%>/kindeditor/lang/zh_CN.js"></script>
	<script charset="utf-8" src="<%=request.getContextPath()%>/kindeditor/plugins/code/prettify.js"></script>
		<script>
		KindEditor.ready(function(K) {
			var editor1 = K.create('textarea[name="newsmanagercontent"]', {
				cssPath : ' <%=request.getContextPath()%>/kindeditor/plugins/code/prettify.css',
				uploadJson : '<%=request.getContextPath()%>/kindeditor/jsp/upload_json.jsp',
				fileManagerJson : '<%=request.getContextPath()%>/kindeditor/jsp/file_manager_json.jsp',
				allowFileManager : true,
				afterCreate : function() {
					var self = this;
					K.ctrl(document, 13, function() {
						self.sync();
						document.forms['example'].submit();
					});
					K.ctrl(self.edit.doc, 13, function() {
						self.sync();
						document.forms['example'].submit();
					});
				}
			});
			prettyPrint();
		});
	</script>
	<div>
		<form name="example" method="post"
			action="<%=request.getContextPath()%>/manager/news/release.do">
			<table border="0">
				<tr>
					<td>标题3</td>
					<td><input type="text" width="100%" name="title" value=""></td>
				</tr>
				<tr>
					<td>作者</td>
					<td><span>${USER_CONTEXT.name }</span></td>
				</tr>
				<tr>
					<td>部门</td>
					<td><span>招生办</span></td>
				</tr>
				<tr>
					<td colspan="2"><textarea name="newsmanagercontent" cols="100" rows="8"
							style="width: 700px; height: 200px; visibility: hidden;"><%=htmlspecialchars("")%></textarea>
						<br /> <input type="submit" name="button" value="提交内容" />
						(提交快捷键: Ctrl + Enter)</td>
				</tr>
			</table>
		</form>
	</div>
	<%!private String htmlspecialchars(String str) {
		str = str.replaceAll("&", "&amp;");
		str = str.replaceAll("<", "&lt;");
		str = str.replaceAll(">", "&gt;");
		str = str.replaceAll("\"", "&quot;");
		return str;
	}%>
