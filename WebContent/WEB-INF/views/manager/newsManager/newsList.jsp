<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/jquery-ui-1.11.1/external/jquery/jquery.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/manager/js/sysmana/global.js"></script>

<script type="text/javascript" >
$(document).ready(function(){
	///////datagrid选中行变色与鼠标经过行变色///////////////
    var dtSelector = ".list";
    var tbList = $(dtSelector);

    tbList.each(function() {
        var self = this;
        $("tr:even:not(:first)", $(self)).addClass("normalEvenTD"); // 从标头行下一行开始的奇数行，行数：（1，3，5...）
        $("tr:odd", $(self)).addClass("normalOddTD"); // 从标头行下一行开始的偶数行，行数：（2，4，6...）

        // 鼠标经过的行变色
        $("tr:not(:first)", $(self)).hover(
            function () { $(this).addClass('hoverTD');$(this).removeClass('table-tr-content'); },
            function () { $(this).removeClass('hoverTD');$(this).addClass('table-tr-content'); }
        );

        // 选择行变色
        $("tr", $(self)).click(function (){
        	var trThis = this;
        	$(self).find(".trSelected").removeClass('trSelected');
        	if ($(trThis).get(0) == $("tr:first", $(self)).get(0)){
                return;
            }
            $(trThis).addClass('trSelected');
        });
    });
});
function operator(url){
	$("#newsList_content").html(getLoadingGifPath());
	$("#newsList_content").load(url,function(){
		histroy.go(-2);
	});
}
function editArticle(id){
// 	$("#newsList_content").html(getLoadingGifPath());
// 	location.href=url;
var url ="<%=request.getContextPath()%>/manager/sys/articleEditView.do?articleId="+id;
	window.parent.Core.createNewWindow(100+id,"编辑文章",url,700,650);
}
</script>
<style type="text/css">

.table-tr-title{
	height: 26px;
	font-size: 12px;
	text-align: center;
}
.table-tr-content{
	height: 18px;
	background: #FFFFFF;
	text-align: center;
	font-size: 12px;
}
.normalEvenTD{
	background: #DFD8D8;
}
.normalOddTD{
	background: #FFFFFF;
}
.hoverTD{
	background-color: #eafcd5;
	height: 18px;
	text-align: center;
	font-size: 12px;
}
.trSelected{
	background-color: #51b2f6;
	height: 18px;
	text-align: center;
	font-size: 12px;
}
td{
	padding: 1px 3px;
}
a:HOVER {
	color: blue;
}
a:VISITED {
	color:blue;
}
a:LINK {
	color: blue;
}
a:ACTIVE {
	color:blue;
}
</style>
<div id="newsList_content">
<table width="99%" class="list" style="word-break: break-all" border="0"
		align="center" cellpadding="0" cellspacing="1" bgcolor="#c0de98">
		<tr class="table-tr-title">
			<td nowrap="nowrap">ID</td>
			<td nowrap="nowrap">标题</td>
			<td nowrap="nowrap">类型</td>
			<td nowrap="nowrap">是否显示</td>
			<td nowrap="nowrap">部门</td>
			<td nowrap="nowrap">作者</td>
			<td nowrap="nowrap">发表日期</td>
			<td nowrap="nowrap">操作</td>
		</tr>
		<c:forEach items="${articles }" var="article">
			<tr class="table-tr-content">
				<td nowrap="nowrap">${article.id }</td>
				<td nowrap="nowrap">${article.title }</td>
				<td nowrap="nowrap">${article.type }</td>
				<td nowrap="nowrap">${article.display }</td>
				<td nowrap="nowrap">${department.name }</td>
				<td nowrap="nowrap">${article.author.name }</td>
				<td nowrap="nowrap">${article.date }</td>
				<td nowrap="nowrap">
				<a href="#" onclick="editArticle(${article.id});">编辑</a>
				<a href="#" onclick="operator('<%=request.getContextPath()%>/manager/sys/articleDelete.do?articleId=${article.id}');">删除</a>
				</td>
			</tr>
		</c:forEach>
	</table>
	
<%-- 		<h1>(${article.id})${article.title }</h1> --%>
<%-- 		<h3>${teacher.name }</h3> --%>
<%-- 		<h3>${article.ip }</h3> --%>
<%-- 		<h3>${department.name}</h3> --%>
<%-- 		<span>${article.content }</span> --%>
</div>
