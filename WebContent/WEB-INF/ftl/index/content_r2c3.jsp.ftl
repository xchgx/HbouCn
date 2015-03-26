<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<style>
</style>
<div class="content1">
	<div class="dropshadow dropshadow5">
		<div class="ui-corner-all  innerbox innerbox5">
			<div>
				<div
					style="background-image: url('<%=request.getContextPath()%>/img/content_title_left.png'); background-repeat: no-repeat; width: 134px; height: 34px; float: left; margin-left: -4px;">快速通道</div>
				<div
					style="background-image: url('<%=request.getContextPath()%>/img/content_title_right.png'); background-repeat: repeat-x; height: 34px; margin-right: 3px; padding-right: 3px;"></div>
			</div>
			<div style="padding-right:10px;">
			${dt1Name }<br>
			<#list dpt1List as d1>
			<a href="${basePath }/index/department/dpt${d1.id?c}.jsp">${d1.name }</a>
			</#list>
			<br>${dt2Name }<br>
			<#list dpt2List as d2>
			<a href="${basePath }/index/department/dpt${d2.id?c}.jsp">${d2.name }</a>
			</#list>
			<br>${dt3Name }<br>
			<#list dpt3List as d3>
			<a href="${basePath }/index/department/dpt${d3.id?c}.jsp">${d3.name }</a>
			</#list>
			</div>
		</div>
	</div>
</div>
