<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <div class="content1">
	<div class="dropshadow dropshadow17">
		<div class="ui-corner-all innerbox innerbox17">
			<div>
				<div
					style="background-image: url('${basePath}/img/content_title_left.png'); background-repeat: no-repeat; width: 134px; height: 34px; float: left;margin-left: -4px;"><span style="font-size: 16px;padding-left: 5px; color: white;line-height: 40px;">新闻列表</span></div>
				<div
					style="background-image: url('${basePath}/img/content_title_right.png'); background-repeat: repeat-x; height: 34px;margin-right: 3px;padding-right: 3px;"><div style="float: right;line-height: 40px;"></div></div>
			</div>
			<div id="content_r2c2_articleList" style="padding: 1px 20px 1px 1px; width:740px;">
				<table border="0"  style="magin: 1; padding: 10px 20px 20px 1px;width:740px;">
				<#list page.result as art>
				<tr><td>
				<#if art.titleStyle == "2">
					<a href='${basePath }/index/article/art${art.id?c}.jsp' target='_blank' title='${art.title ?default("")}'  style='font-size:12px;color:red;'>
				<#elseif art.titleStyle == "3">
					<a href='${basePath }/index/article/art${art.id?c}.jsp' title='${art.title ?default("")}'  style='font-size:12px;font-style: italic;'>
				<#else>
					<a href='${basePath }/index/article/art${art.id?c}.jsp' title='${art.title ?default("")}'  style='font-size:12px;'>
				</#if>
				<#if art.title?length gt strLength>
				${art.title[0..strLength] }...
				<#else>
				${art.title }
				</#if>
				</a></td><td   style="width:100px;" align="right">${art.date?default("--") }</td></tr>
				</#list>
				</table>
				<#if page.currentPageNo gt 1>
				<a href="#" onclick="sectionArticlesPageUp('${basePath }/index/department/content_r2c2_list_dpt${departmentId}_${page.currentPageNo - 1}.jsp');">上一页</a>
				</#if>
				 ${page.currentPageNo} / ${page.totalPageCount } 
				 <#if page.currentPageNo lt page.totalPageCount>
				 <a href="#" onclick="sectionArticlesPageDown('${basePath }/index/department/content_r2c2_list_dpt${departmentId}_${page.currentPageNo + 1}.jsp');">下一页</a>
				</#if>

			</div>
		</div>
	</div>
</div>