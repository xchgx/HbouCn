<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<style>
</style>

<div class="content1">
	<div class="dropshadow dropshadow2">
		<div class="ui-corner-all innerbox innerbox2">
			<div>
				<div
					style="background-image: url('${basePath}/img/content_title_left.png'); background-repeat: no-repeat; width: 134px; height: 34px; float: left;margin-left: -4px;"><span style="font-size: 16px; color: white; padding-left: 5px;line-height: 40px;">通知·公告</span></div>
				<div
					style="background-image: url('${basePath}/img/content_title_right.png'); background-repeat: repeat-x; height: 34px;margin-right: 3px;padding-right: 3px;"><div style="float: right;line-height: 40px;"> </div></div>
			</div>
			<div>
				<div style="background-image: url('${basePath}/img/gonggaobg2.jpg');background-repeat:no-repeat;float: left;position: relative;margin: 0px;width: 230px;height: 250px;">
				<div id="content_gonggao_articlecontent" style="margin-left: 10px; margin-top: 30px;width:210px;height: 205px;float:left;  overflow:auto;">
				<h7>${article.title }</h7>${article.content}
				</div>
				<div id="content_gonggao_articlelist" style="float:left; margin-top: 20px;width:100%;">
				<table border="0"   style="margin : 0; padding: 0;width:100%;" >
				<#list articles as art>
				<tr><td>
				<#if art.titleStyle == '2'>
					<a href='${basePath }/index/article/art${art.id?c}.jsp' title='${art.title ?default("")}'  style='font-size:12px;color:red;'>
				<#elseif art.titleStyle == '3'>
					<a href='${basePath }/index/article/art${art.id?c}.jsp' title='${art.title ?default("")}'  style='font-size:12px;font-style: italic;'>
				<#else>
					<a href='${basePath }/index/article/art${art.id?c}.jsp' title='${art.title ?default("")}'  style='font-size:12px;'>
				</#if>
				<#if art.title?length gte 10>
				${art.title[0..8] }...
				<#else>
				${art.title }
				</#if>
				</a></td><td   style="width:100px;" align="right">${art.date?default("--") }</td></tr>
				</#list>
				</table>
				</div>
				</div>
			</div>
		</div>
	</div>
</div>
