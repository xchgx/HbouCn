<%@page import="com.xchgx.cons.PropertyFileOperation"%>
<%@page import="java.util.Formatter"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="description"
	content="湖北开放职业学院是湖北省人民政府批准、教育部备案的全日制普通高等院校。招生热线：027—51356220" />
<meta name="keywords" content="湖北开放职业学院" />
<title>湖北开放职业学院 官网</title>
<link rel="icon" href="<%=request.getContextPath() %>/img/favicon.ico" type="image/x-icon" />
<meta name="keywords" content="sailing, theme, web design, free templates, full-site, website templates, CSS, HTML" />
<meta name="description" content="Sailing Theme is a free full-site template provided by templatemo.com" />
<link href="templatemo_style.css" rel="stylesheet" type="text/css" />

<link rel="stylesheet" type="text/css" href="content_slider_style.css" />

<link href="../css/global.css" rel="stylesheet" />
<link href="../jquery-ui-1.11.1/jquery-ui.css" rel="stylesheet">
<link href="../jquery-ui-1.11.1/jquery-ui.theme.css" rel="stylesheet">
<script src="../jquery-ui-1.11.1/external/jquery/jquery.js"></script>
<script src="../jquery-ui-1.11.1/jquery-ui.js"></script>
<script src="../js/global.js"></script>

<!-- bxSlider Javascript file -->
<script src="../bxslider/jquery.bxslider.min.js"></script>
<!-- bxSlider CSS file -->
<link href="../bxslider/jquery.bxslider.css" rel="stylesheet" />
<script src="../js/functionDynLoadData.js"></script>
<script src="../js/navIdx.js"></script>
<script src="../js/indexDynLoadData.js"></script>

<script type="text/javascript">
// 	$(document).ready(function(){
// 		$("#featured > ul").tabs({fx:{opacity: "toggle"}}).tabs("rotate", 5000, true);
// 	});
</script>

</head>
<body>

<div id="templatemo_wrapper_outer">

<div id="templatemo_wrapper">
	
    <div id="templatemo_header">
		<%@include file="../head.jsp" %>
	</div> <!-- end of templatemo_header -->
    
    <!-- 导航 -->
     <div>
          <div class="content_navigate"><%@include file="../nav.jsp"%></div>
        </div>
        
        
    
    <div id="templatemo_slider">
     	 <div><%@include file="../content_r1c2huandengpian.jsp"%></div>
     	 <div><%@include file="../content_r1c1gonggao.jsp" %></div>
	</div> <!-- end of templatemo_slider -->
    
    
    <!-- 中间板块栏目   -->
    <div id="templatemo_content">
    	
        <div class="col_w300">
		
        	<div >
        <h1>板块1</h1>
            </div>
            <div class="cleaner"></div>
            <div class="btn_more"><%@include file="../content_r1c3.jsp" %></div>	
        </div>
        
        <div class="col_w300">
        <h1>板块2</h1>
            <div class="cleaner"></div>
            <div class="btn_more"><a href="services.html">Read more</a></div>
        </div>
        
        <div class="col_w300 col_last">
            <h1>板块3</h1>
    	<div class="cleaner"></div>
    </div>
    
	<div class="cleaner"></div>
</div>
</div>

<!--   底部链接栏    -->
<div id="templatemo_footer_wrapper">
	<div id="footer_top"></div>
	
	<div id="templatemo_footer">
    
        <div class="col_w220" style="background-color: yellow;">
       <h5>链接1</h5>
            
        </div>
        
        <div class="col_w220">
            <h5>Our Pages</h5>
            <ul class="tmo_list">
                <li><a href="index.html">Home</a></li>
                <li><a href="services.html">Services</a></li>
                <li><a href="news.html">News</a></li>
                <li><a href="about.html">About us</a></li>
                <li><a href="contact.html">Contact</a></li>
            </ul>  
 
        </div>
        
        <div class="col_w220">
            <h5>Partners</h5>
			<ul class="tmo_list">
                <li><a href="http://sc.chinaz.com">Free CSS Templates</a></li>
                <li><a href="http://www.koflash.com">Websites Gallery</a></li>
                <li><a href="http://www.flashmo.com/store">Premiun Templates</a></li>
                <li><a href="http://sc.chinaz.com/page/1">Website Layouts</a></li>
				<li><a href="http://www.flashmo.com">Flash Templates</a></li>
            </ul>
 
        </div>
        
        <div class="col_w220 col_last">
            <h5>Links</h5>
            <ul class="tmo_list">
                <li><a href="#">Aenean rhoncus leo ut eros</a></li>
                <li><a href="#">Consectetur adipiscing elit</a></li>
                <li><a href="#">Lorem ipsum dolor sit amet</a></li>
                <li><a href="#">Pellentesque vel est ut leo</a></li>
				<li><a href="#">Sed fringilla sollicitudin nisi</a></li>
            </ul>    
 
        </div>
        
        <div class="cleaner"></div>
    </div> <!-- end of footer -->
	
    <div id="templatemo_copyright">
    
        Copyright © 2048 <a href="#">Your Company Name</a> | <a href="http://www.iwebsitetemplate.com" target="_parent">Website Templates</a> by <a href="http://sc.chinaz.com" target="_parent">站长素材</a>
    
    </div>
</div>

<div style="display:none"><script src='http://v7.cnzz.com/stat.php?id=155540&web_id=155540' language='JavaScript' charset='gb2312'></script></div></body>
</html>