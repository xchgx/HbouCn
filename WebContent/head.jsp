<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div>
	<table border="0" cellpadding="0" cellspacing="0" width="100%">
		<tr>
			<td style="height: 100px; width: 100px"><a
				href="<c:url value="/index.jsp" />"><img border="0"
					src="<c:url value="/img/xiaohui.png" />" width="100" height="100"></a>
			</td>
			<td style="background-color: #248DC6;height: 100px; width: 200px;">
				<div style="font-size: 22px; color: white;">湖北开放职业学院</div>
				<div
					style="font-size: 20px; text-align: right; font-style: italic; color: white;">软件工作室</div>
			</td>
			<td style="background-color: #248DC6;height: 100px;" align="right"><c:choose>
					<c:when test="${empty sessionScope.USER_CONTEXT}">
						<a href="<c:url value="/user/loginView.do" />" target="blank"
							block="content_index_block" class="ui-state-default">管理员登录</a>
					</c:when>
					<c:otherwise>
					${USER_CONTEXT.name }
						<a href="<c:url value="/manager/main.jsp" />" target="blank"
							block="content_index_block" class="ui-state-default">进入管理平台</a>
						<a href="<c:url value="/user/doLogout.do" />" target="blank"
							block="content_index_block" class="ui-state-default">注销登录</a>
					</c:otherwise>
				</c:choose></td>
			<td style="background-color: #248DC6;height: 100px;" align="right" width="200"><img
				src="<c:url value="/img/xiaobenbuditu3d.jpg" />" border="0" height="100"
				width="200"></td>
		</tr>
	</table>
	<h3></h3>
</div>