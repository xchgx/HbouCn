<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%> 
				<p>
				<table border="0"  style="magin: 0; padding: 0;width:100%;">
				<#list articles as art>
				<tr><td style="padding: 0px;margin: 0px" align="left"> 
				<#if art.titleStyle == '2'>
					<a href='${basePath }/index/article/art${art.id?c}.jsp' title='${art.title ?default("")}'  style='font-size:12px;color:red;'>
				<#elseif art.titleStyle == '3'>
					<a href='${basePath }/index/article/art${art.id?c}.jsp' title='${art.title ?default("")}'  style='font-size:12px;font-style: italic;'>
				<#else>
					<a href='${basePath }/index/article/art${art.id?c}.jsp' title='${art.title ?default("")}'  style='font-size:12px;'>
				</#if>
				<#if art.title?length gt strLength>
				${art.title[0..strLength] }...
				<#else>
				${art.title }
				</#if>
				</a></td><td  style="width:100px;padding-right:5px;font-size:12px;" align="right">${art.date?default("--") }</td></tr>
				</#list>
				</table>
				</p>
