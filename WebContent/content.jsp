<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    <div>
    <table border="0" cellpadding="0" cellspacing="0" width="100%">
    		<tr>
			<td>
				<div id="tabs">
					<ul>
						<li><a href="#tabs-1">首页</a></li>
						<li><a href="#tabs-2">项目</a></li>
						<li><a href="#tabs-3">联系我们</a></li>
						<li><a href="#tabs-4">关于我们</a></li>
					</ul>
					<div id="tabs-1"><%@include file="index/content/content_navigate1.jsp" %></div>
					<div id="tabs-2"><%@include file="index/content/content_navigate2.jsp" %></div>
					<div id="tabs-3"><%@include file="index/content/content_navigate3.jsp" %></div>
					<div id="tabs-4"><%@include file="index/content/content_navigate4.jsp" %></div>
				</div>
			</td>
		</tr>
    </table>
    </div>