<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<div>
<div><button id="allSectionKuaiSuAdd">快速添加</button></div>
<table class="list" id="allSectionTableData" style="word-break: break-all" border="0"
		align="center" cellpadding="0" cellspacing="1" bgcolor="#c0de98">
	<tr class="table-tr-title">
		<th nowrap="nowrap">ID</th>
		<th nowrap="nowrap">栏目名</th>
		<th nowrap="nowrap">文章数</th>
		<th nowrap="nowrap">简介</th>
		<th nowrap="nowrap">操作</th>
	</tr>
	<c:forEach items="${sections}" var="section">
		<tr class="table-tr-content" id="tableTrContentId${section.id }">
			<td nowrap="nowrap">${section.id }</td>
			<td nowrap="nowrap">${section.name }</td>
			<td nowrap="nowrap">
			<a href="#" class="allSectionListTagAarticles"  tip="${section.id }">${fn:length(section.articles)}</a>
			<div class="allNavigationListTagATip" id="allSectionListTagAarticles${section.id }">
				文章标题列表：
 			<c:forEach items="${section.articles }" var="article">
			<br>${article.title }(${article.id })
			</c:forEach>
			</div>
			</td>
			<td nowrap="nowrap">${section.description }</td>
			<td nowrap="nowrap" width="50">
			<li class="imgOperator" operation="sectionEdit" value="${section.id }"  title="编辑" >编辑</li><li>|</li>
			<li class="imgOperator" operation="sectionDel" value="${section.id }"   title="删除该栏目">删除</li>
			</td>
		</tr>
	</c:forEach>
</table>
<div id="dialogContent"></div>
</div>