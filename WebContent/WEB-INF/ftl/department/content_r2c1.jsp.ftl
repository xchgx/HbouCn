<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="content1">
	<div class="dropshadow dropshadow2">
		<div class="ui-corner-all innerbox innerbox2">
			<div>
				<div
					style="background-image: url('${basePath}/img/content_title_left.png'); background-repeat: no-repeat; width: 134px; height: 34px; float: left;margin-left: -4px;"><span style="font-size: 12px; color: white;line-height: 40px;">${department.name }栏目</span></div>
				<div
					style="background-image: url('${basePath}/img/content_title_right.png'); background-repeat: repeat-x; height: 34px;margin-right: 3px;padding-right: 3px;"><div style="float: right;line-height: 40px;">更多>></div></div>
			</div>
			<div id="content_r2c1_sectionList" style="word-wrap: break-all; overflow: auto; width: 230px; magin:0px;">
			<#list sections as sec>
			<li class='ajaxJsonSectionListByDepartment'><a href='${basePath}/index/section/sec${sec.id?c}.jsp' >${sec.name }</a></li>
			</#list>
			</div>
		</div>
	</div>
</div>