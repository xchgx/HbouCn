<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<style>
img {
	border: 0px;
	margin: 0px;
	padding: 0px
}

ul, li, p {
	list-style: none;
	margin: 0px;
	padding: 0px;
	z-index: 9999;
}

a {
	font-family: Arial, Helvetica, sans-serif;
	font-size: 12px;
	color: #555;
	margin: 0px;
	padding: 0px;
	text-decoration: none;
	z-index: 9999;
}

a:hover {
	color: #cc0000;
	text-decoration: none
}

#header {
	width: 1024px;
	margin: 0px auto 1 auto;
	font-family: Arial, Helvetica, sans-serif;
	font-size: 12px;
	text-align: left;
	color: #555;
	line-height: 20px;
	/*background: #dadada;*/
		z-index: 9999;
}

.nav {
	position: absolute;
	width: 1024px;
	height: 33px;
	background: url(${basePath}/img/nav2/navbg.gif) repeat-x;
		z-index: 9999;
}

.nav li {
	position: relative;
	float: left;
		z-index: 9999;
}

.nav .bg {
	background: url(${basePath}/img/nav2/nav_xian.gif) no-repeat;
	width: 2px;
	height: 33px;
	padding: 0px;
	display: block;
}

.nav li a {
	line-height: 30px;
	padding: 0px 21px;
	float: left;
	height: 33px;
	color: #fff;
	font-size: 14px;
		z-index: 9999;
}

.nav li a:hover {
	background: url(${basePath}/img/nav2/navbg_a.gif) repeat-x;
	color: #fff;
}

.nav li .nav-w {
	z-index: 21;
	position: absolute;
	display: none;
	float: left;
	clear: both;
	overflow: hidden;
	top: 33px;
	background: #fff;
	border: #faa651 1px solid;
	left: -1px;
}

.nav li:hover a {
	background: url(${basePath}/img/nav2/navbg_a.gif) repeat-x;
	color: #fff;
}

.nav li .section-nav1 {
	padding: 10px;
	height: auto;
	overflow: hidden;
}

.nav li .section-nav1 ul {
	position: static;
	padding: 0px;
	margin: 0px;
	width: 160px;
	overflow: hidden;
}

.nav li .section-nav1 ul li {
	position: static;
	padding: 0px;
	margin-bottom: 3px;
	width: 100%;
	background: none;
	float: left;
}

.nav li .section-nav1 ul li a {
	border: #f0f0f0 1px solid;
	text-align: left;
	padding: 0px;
	line-height: 22px;
	width: auto;
	padding-left: 3px;
	display: block;
	background: none;
	float: none;
	height: auto;
	color: #3167a5;
	font-size: 12px;
	overflow: hidden;
	cursor: pointer;
}

.nav li .section-nav1 ul li a:hover {
	border: #faa651 1px solid;
	background: none;
	color: #faa651;
}
</style>
<div id="header">
	<ul class="nav">
		<li class="first"><a href="${basePath}">办学特色</a></li>
		<li class="bg"></li>
		<#list navigations as navigation>
			<li><a href="${navigation.url }" ${navigation.targetBlank?string("target=\"_blank\"", "")}  title="${navigation.title ! '' }"	>${navigation.name }</a>
			<#if (navigation.childNavigations?size > 0)>
			<div class="nav-w">
				<div class="section-nav1">
				<ul>
	      		<#list navigation.childNavigations as child>
	      			<li><a href="${child.url }" ${child.targetBlank?string("target=\"_blank\"", "")}  title="${child.title ! '' }"	>${child.name }</a></li>
	      		</#list>
	      		</ul>
				</div>
			</div>
			</li>
			<li class="bg"></li>
			</#if>
		</#list>
		<li><a href="${basePath }/user/loginView.do" target="_blank">管理登陆</a></li>
		<li class="bg"></li>
	</ul>
</div>
