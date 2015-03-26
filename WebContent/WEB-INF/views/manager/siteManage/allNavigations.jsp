<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%
// 	response.setHeader("Pragma", "No-cache");
// 	response.setHeader("Cache-Control", "no-cache");
// 	response.setDateHeader("Expires", 0);
%>
<div>
<input id="navigationDepartmentId" value="${departmentId }" type="hidden">
<button id="allNavigationKuaiSuAdd">快速添加</button>
<button id="navigationRelease">生成静态导航</button>
</div>
<div id="dialog-addNavigationForm" title="添加导航栏" style="display: none;">
<p>添加导航栏</p>
<fieldset>
<label for="navigationUrl">链接目标地址（如：输入部门名称或子栏目名称，必填）</label><br>
<button  name="navigationUrl" id="navigationUrl"  class="ui-widget-content ui-corner-all" >点击快速设置</button>
<br>
<input type="hidden" name="navigationId" id="navigationId" value="">
<label for="navigationName">导航名称（必填）</label><br>
<input type="text" name="navigationName" id="navigationName" value="" width="100%" class="text ui-widget-content ui-corner-all"><br>
<label for="navigationLevel">导航级别（必选）</label><br>
<select name="navigationLevel" id="navigationLevel" class="text ui-widget-content ui-corner-all" title="一级导航将显示在页面导航菜单的第一行，二级导航将显示在一级导航菜单的下拉选项中。<br>如果这里显示出来的内容不一致，您可以将导航级别改为“一级导航”，然后在选择“二级导航”这样可以刷新。">
<option value="1">一级导航</option>
<option value="2">二级导航</option>
</select><br>
<div id="divNavigationFatherNavigations" style="display: none;">
<label>上一级导航（必填）</label><br>
<c:if test="${fn:length(firstNavigations) != 0}">
<c:forEach items="${firstNavigations }" var="firstNavigation">
<input type="radio" name="navigationFatherNavigations" id="navigationFatherNavigations${firstNavigation.id }" value="${firstNavigation.id }" width="100%" class="text ui-widget-content ui-corner-all">
<label for="navigationFatherNavigations${firstNavigation.id }">${firstNavigation.name }</label>
<br>
</c:forEach>
</c:if>
</div>
<input id="navigationUrlValue" type="text" width="100%" class="text ui-widget-content ui-corner-all"><br>
<!-- <label for="navigationTip">鼠标悬停提示</label><br> -->
<!-- <input type="text" name="navigationTip" id="navigationTip" value="" width="100%" class="text ui-widget-content ui-corner-all"> <br> -->
<!-- <label for="navigationIco">图标（默认为空）</label><br> -->
<!-- <input type="text" name="navigationIco" id="navigationIco" value="" width="100%" class="text ui-widget-content ui-corner-all"> <br> -->
<!-- <label for="navigationStyle">导航样式（一般保持为空不要改动。除非你精通CSS）</label><br> -->
<!-- <input type="text" name="navigationStyle" id="navigationStyle" value="" width="100%" class="text ui-widget-content ui-corner-all"> <br> -->
<label for="navigationSort">排序，数字越小越靠前</label><br>
<input type="text" name="navigationSort" id="navigationSort" value="1" width="100%" class="text ui-widget-content ui-corner-all"> <br>
<label >打开链接方式（新窗口还是本页打开）</label><br>
<input type="radio" name="navigationTargetBlank" id="navigationTargetBlank0" value="0" checked="checked" width="100%" class="text ui-widget-content ui-corner-all">
<label for="navigationTargetBlank0">本页打开（默认）</label><br>
<input type="radio" name="navigationTargetBlank" id="navigationTargetBlank1" value="1" width="100%" class="text ui-widget-content ui-corner-all">
<label for="navigationTargetBlank1"> 新窗口打开</label><br>
<label >是否显示在导航栏中</label><br>
<input type="radio" name="navigationDisplay" id="navigationDisplay1"  value="1" checked="checked" width="100%" class="text ui-widget-content ui-corner-all">
<label for="navigationDisplay1">显示</label><br>
<input type="radio" name="navigationDisplay" id="navigationDisplay0" value="0" width="100%" class="text ui-widget-content ui-corner-all"> 
<label for="navigationDisplay0">隐藏</label><br>
<label for="navigationDescription">备注</label><br>
<input type="text" name="navigationDescription" id="navigationDescription" value="" width="100%" class="text ui-widget-content ui-corner-all"> <br>
</fieldset>
</div>
<div id="dialog-addNavigationUrl" title="设置链接目标" style="display: none;">
<p>设置导航链接目标</p>
<fieldset>
<label>请选择链接目标的类型</label><br>
<input type="radio" name="navigationUrlTypeRadio" id="navigationUrlType1" target="Department" checked="checked"><label for="navigationUrlType1">部门地址</label>
<input type="radio" name="navigationUrlTypeRadio" id="navigationUrlType2" target="Section"><label for="navigationUrlType2">子栏目地址</label>
<input type="radio" name="navigationUrlTypeRadio" id="navigationUrlType3" target="Article"><label for="navigationUrlType3">文章地址</label>
</fieldset>
<fieldset>
<div id="navigationUrlTypeDepartment" name="navigationUrlTypeContent">
<label>请输入部门名称（按DEL或Backspace键可自动搜索）</label>
<input type="text" id="navUrlDepartmentList" title="比如： &quot;院&quot;"><input type="text" id="navUrlDepartmentListId" readonly="readonly">
</div>
<div id="navigationUrlTypeSection" name="navigationUrlTypeContent" style="display: none;">
<label>请输入子部门板块名称（按DEL或Backspace键可自动搜索）</label>
<input type="text" id="navUrlSectionList" title="比如： &quot;通知&quot;"><input type="text" id="navUrlSectionListId" readonly="readonly">
</div>
<div id="navigationUrlTypeArticle" name="navigationUrlTypeContent" style="display: none;">
<label>请输入文章标题（按DEL或Backspace键可自动搜索）</label>
<input type="text" id="navUrlArticleList" title="比如： &quot;简介&quot;"><input type="text" id="navUrlArticleListId" readonly="readonly">
</div>
</fieldset>
</div>
<div id="allDataPage">
<table class="list" id="allNavigationTableData" style="word-break: break-all" border="0"
		align="center" cellpadding="0" cellspacing="1" bgcolor="#c0de98">
	<tr class="table-tr-title">
		<th nowrap="nowrap">ID</th>
		<th nowrap="nowrap">名称</th>
		<th nowrap="nowrap">导航级别</th>
		<th nowrap="nowrap">链接地址</th>
<!-- 		<th nowrap="nowrap">提示内容</th> -->
<!-- 		<th nowrap="nowrap">图标地址</th> -->
<!-- 		<th nowrap="nowrap">样式表</th> -->
		<th nowrap="nowrap">位置顺序</th>
		<th nowrap="nowrap">开新窗口</th>
		<th nowrap="nowrap">是否显示</th>
		<th nowrap="nowrap">父导航</th>
		<th nowrap="nowrap">下一级导航</th>
		<th nowrap="nowrap">备注</th>
		<th nowrap="nowrap">操作</th>
	</tr>
	<c:forEach items="${navigations}" var="navigation">
		<tr class="table-tr-content" id="tableTrContentNavId${navigation.id }">
			<td nowrap="nowrap">${navigation.id }</td>
			<td nowrap="nowrap">${navigation.name }</td>
			<td nowrap="nowrap">${navigation.level }</td>
			<td nowrap="nowrap">${navigation.url }</td>
<%-- 			<td nowrap="nowrap">${navigation.tip }</td> --%>
<%-- 			<td nowrap="nowrap">${navigation.ico }</td> --%>
<%-- 			<td nowrap="nowrap">${navigation.style }</td> --%>
			<td nowrap="nowrap">${navigation.sort }</td>
			<td nowrap="nowrap">${navigation.targetBlank }</td>
			<td nowrap="nowrap">${navigation.display }</td>
			<td nowrap="nowrap">${navigation.fatherNavigation.name }</td>
			<td nowrap="nowrap">
			<a href="#" class="allNavigationListTagAsections"  tip="${navigation.id }">${fn:length(navigation.childNavigations)}</a>
			<div class="allNavigationListTagATip" id="allNavigationListTagAsections${navigation.id }">
				下一级导航列表
 			<c:forEach items="${navigation.childNavigations }" var="childNavigation">
			<br>${childNavigation.name }(${childNavigation.id })
			</c:forEach>
			</div>
			</td>
			
			<td nowrap="nowrap">${navigation.description }</td>
			<td nowrap="nowrap" width="50">
			<li class="imgOperator" operation="edit" value="${navigation.id }"  title="编辑">编辑</li><li>|</li>
			<li class="imgOperator" operation="del" value="${navigation.id }"   title="删除该导航，如果该导航存在子导航，将一起删除。。">删除</li>
			</td>
		</tr>
	</c:forEach>
</table>
</div>
<div id="previewNavigation"></div>
<script type="text/javascript" src="<%=request.getContextPath()%>/manager/js/sitemana/allNavigationsJs.js"></script>
