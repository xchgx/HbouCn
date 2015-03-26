<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<div><button onclick="allArticlesOperatorCreate();">快速添加</button></div>

<div id="allDataPage">
<li><c:if test="${articlesPage.hasPreviousPage }"><a href="#" onclick="jumpPage(${articlesPage.currentPageNo -1});" ></c:if>上一页<c:if test="${articlesPage.hasPreviousPage }"></a></c:if></li>
<li>${articlesPage.currentPageNo }/${articlesPage.totalPageCount }</li>
<li><c:if test="${articlesPage.hasNextPage }"><a href="#" onclick="jumpPage(${articlesPage.currentPageNo +1});" ></c:if>下一页<c:if test="${articlesPage.hasNextPage }"></a></c:if></li>
<li>
<select id="allArticlePageInputValue" onchange="inputJumpPage(${articlesPage.currentPageNo });">
<c:forEach begin="1" end="${articlesPage.totalPageCount }" step="1" var="p">
<option value="${p }"
<c:if test="${p == articlesPage.currentPageNo }">
selected="selected"
</c:if>
 >第${p }页</option>
</c:forEach>
</select>
</li>
<br>
<table class="list" style="word-break: break-all" border="0"
		align="center" cellpadding="0" cellspacing="1" bgcolor="#c0de98">
	<tr class="table-tr-title">
		<th nowrap="nowrap">ID</th>
		<th nowrap="nowrap">标题</th>
		<th nowrap="nowrap">文章类型</th>
		<th nowrap="nowrap">标题样式</th>
		<th nowrap="nowrap">显示内容</th>
		<th nowrap="nowrap">内容</th>
		<th nowrap="nowrap">作者</th>
		<th nowrap="nowrap">发表时间</th>
		<th nowrap="nowrap">发表IP</th>
		<th nowrap="nowrap">所属部门</th>
		<th nowrap="nowrap">所属栏目</th>
		<th nowrap="nowrap">备注</th>
		<th nowrap="nowrap">操作</th>
	</tr>
	<c:forEach items="${articlesPage.result}" var="article">
		<tr class="table-tr-content">
			<td nowrap="nowrap">${article.id }</td>
			<td nowrap="nowrap">${article.title }</td>
			<td nowrap="nowrap">${article.type }</td>
			<td nowrap="nowrap">${article.titleStyle }</td>
			<td nowrap="nowrap">${article.display }</td>
			<td nowrap="nowrap">${fn:length(article.content)}</td>
			<td nowrap="nowrap">${article.author.name }</td>
			<td nowrap="nowrap">${article.date }</td>
			<td nowrap="nowrap">${article.ip }</td>
			<td nowrap="nowrap">${article.department.name }</td>
			<td nowrap="nowrap">${article.section.name }</td>
			<td nowrap="nowrap">${article.description }</td>
			<td nowrap="nowrap" width="50">
			<li class="imgOperator" onclick="allArticlesOperatorEdit(${article.id});" title="编辑"><img class="imgOperator" border="0" width="25" height="25" src="<%=request.getContextPath()%>/img/sysmanage/edit.png"></li>
			<li class="imgOperator" onclick="allArticlesOperatorDelete(${article.id});" title="删除"><img class="imgOperator" border="0" width="25" height="25" src="<%=request.getContextPath()%>/img/sysmanage/delete.png"></li>
			</td>
		</tr>
	</c:forEach>
</table>
<li><c:if test="${articlesPage.hasPreviousPage }"><a href="#" onclick="jumpPage(${articlesPage.currentPageNo -1});" ></c:if>上一页<c:if test="${articlesPage.hasPreviousPage }"></a></c:if></li>
<li>${articlesPage.currentPageNo }/${articlesPage.totalPageCount }</li>
<li><c:if test="${articlesPage.hasNextPage }"><a href="#" onclick="jumpPage(${articlesPage.currentPageNo +1});" ></c:if>下一页<c:if test="${articlesPage.hasNextPage }"></a></c:if></li>
<li>
<select id="allArticlePageInputValue" onchange="inputJumpPage(${articlesPage.currentPageNo });">
<c:forEach begin="1" end="${articlesPage.totalPageCount }" step="1" var="p">
<option value="${p }"
<c:if test="${p == articlesPage.currentPageNo }">
selected="selected"
</c:if>
 >第${p }页</option>
</c:forEach>
</select>
</li>
</div>
<script type="text/javascript" src="<%=request.getContextPath()%>/manager/js/sysmana/allArticlesJs.js"></script>

