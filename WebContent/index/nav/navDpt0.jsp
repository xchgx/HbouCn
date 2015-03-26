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
	background: url(/HbouCn/img/nav2/navbg.gif) repeat-x;
		z-index: 9999;
}

.nav li {
	position: relative;
	float: left;
		z-index: 9999;
}

.nav .bg {
	background: url(/HbouCn/img/nav2/nav_xian.gif) no-repeat;
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
	background: url(/HbouCn/img/nav2/navbg_a.gif) repeat-x;
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
	background: url(/HbouCn/img/nav2/navbg_a.gif) repeat-x;
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
		<li class="first"><a href="/HbouCn">办学特色</a></li>
		<li class="bg"></li>
			<li><a href=""   title=""	>校园概况</a>
			<div class="nav-w">
				<div class="section-nav1">
				<ul>
	      			<li><a href=""   title=""	>学校简介</a></li>
	      			<li><a href=""   title=""	>学校领导</a></li>
	      			<li><a href=""   title=""	>机构设置</a></li>
	      		</ul>
				</div>
			</div>
			</li>
			<li class="bg"></li>
			<li><a href=""   title=""	>院校设置</a>
			<div class="nav-w">
				<div class="section-nav1">
				<ul>
	      			<li><a href="/HbouCn/navigation/department/1.do"   title=""	>信息机电工程学院</a></li>
	      			<li><a href="/HbouCn/navigation/department/3.do"   title=""	>财经学院</a></li>
	      			<li><a href="/HbouCn/navigation/department/4.do"   title=""	>管理学院</a></li>
	      			<li><a href="/HbouCn/navigation/department/2.do"   title=""	>人文艺术学院</a></li>
	      			<li><a href="/HbouCn/navigation/department/5.do"   title=""	>继续教育学院</a></li>
	      			<li><a href="/HbouCn/navigation/department/6.do"   title=""	>科技培训中心学院</a></li>
	      		</ul>
				</div>
			</div>
			</li>
			<li class="bg"></li>
			<li><a href=""   title=""	>招生就业</a>
			<div class="nav-w">
				<div class="section-nav1">
				<ul>
	      			<li><a href="/HbouCn/navigation/department/8.do"   title=""	>招生办</a></li>
	      			<li><a href="/HbouCn/navigation/department/9.do"   title=""	>就业办</a></li>
	      		</ul>
				</div>
			</div>
			</li>
			<li class="bg"></li>
			<li><a href=""   title=""	>行政部门</a>
			<div class="nav-w">
				<div class="section-nav1">
				<ul>
	      			<li><a href="/HbouCn/navigation/department/7.do"   title=""	>校办</a></li>
	      			<li><a href="/HbouCn/navigation/department/10.do"   title=""	>教务处</a></li>
	      			<li><a href="/HbouCn/navigation/department/11.do"   title=""	>学工处</a></li>
	      			<li><a href="/HbouCn/navigation/department/16.do"   title=""	>总务处</a></li>
	      			<li><a href="/HbouCn/navigation/department/17.do"   title=""	>保卫处</a></li>
	      			<li><a href="/HbouCn/navigation/department/18.do"   title=""	>后勤处</a></li>
	      			<li><a href="/HbouCn/navigation/department/12.do"   title=""	>人事处</a></li>
	      			<li><a href="/HbouCn/navigation/department/13.do"   title=""	>财务处</a></li>
	      			<li><a href="/HbouCn/navigation/department/15.do"   title=""	>图书馆</a></li>
	      			<li><a href="/HbouCn/navigation/department/19.do"   title=""	>校刊校报</a></li>
	      		</ul>
				</div>
			</div>
			</li>
			<li class="bg"></li>
			<li><a href=""   title=""	>产教融合</a>
			<div class="nav-w">
				<div class="section-nav1">
				<ul>
	      			<li><a href="/HbouCn/navigation/department/20.do"   title=""	>电子商务实训室</a></li>
	      			<li><a href="/HbouCn/navigation/department/21.do"   title=""	>物流实训室</a></li>
	      			<li><a href="/HbouCn/navigation/department/22.do"   title=""	>软件工作室</a></li>
	      		</ul>
				</div>
			</div>
			</li>
			<li class="bg"></li>
			<li><a href=""   title=""	>校企合作</a>
			<li><a href=""   title=""	>学子风采</a>
		<li><a href="/HbouCn/user/loginView.do" target="_blank">管理登陆</a></li>
		<li class="bg"></li>
	</ul>
</div>
