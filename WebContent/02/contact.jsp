﻿
<%@page import="com.xchgx.cons.PropertyFileOperation"%>
<%@page import="java.util.Formatter"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Sailing Theme - Contact Form</title>
<meta name="keywords" content="sailing, contact form, web design, free templates, website templates, CSS, HTML" />
<meta name="description" content="Sailing Contact Form - free website template provided by templatemo.com" />
<link href="templatemo_style.css" rel="stylesheet" type="text/css" />

<link rel="stylesheet" type="text/css" href="content_slider_style.css" />
<script type="text/javascript" src="js/jquery.1.3.2.min.js" ></script>
<script type="text/javascript" src="js/jquery-ui.min.js" ></script>
<script type="text/javascript">
	$(document).ready(function(){
		$("#featured > ul").tabs({fx:{opacity: "toggle"}}).tabs("rotate", 5000, true);
	});
</script>

</head>
<body>

<div id="templatemo_wrapper_outer">

<div id="templatemo_wrapper">
	
    <div id="templatemo_header">

    	<div id="site_title">
            <h1><a href="http://sc.chinaz.com"><img src="images/templatemo_logo.png" alt="logo" /><span>Free CSS Templates</span></a></h1>
        </div> <!-- end of site_title -->
        
        <div id="templatemo_menu">
            <ul>
                <li><a href="index.jsp">Home</a></li>
                <li><a href="services.jsp">Services</a></li>
                <li><a href="news.jsp">News</a></li>
                <li><a href="about.jsp">About us</a></li>
                <li><a href="contact.jsp" class="current">Contact</a></li>
            </ul>    	
        </div> <!-- end of templatemo_menu -->

    </div> <!-- end of templatemo_header -->
    
    <div id="templatemo_slider">
    
		<div id="featured" >
			<ul class="ui-tabs-nav">
				<li class="ui-tabs-nav-item ui-tabs-selected" id="nav-fragment-1"><a href="#fragment-1"><img src="images/content_slider/image1-small.jpg" alt="Image1small" /><span>Lorem ipsum dolor sit amet, consectetur adipiscing elit</span></a></li>
				<li class="ui-tabs-nav-item" id="nav-fragment-2"><a href="#fragment-2"><img src="images/content_slider/image2-small.jpg" alt="Image2small" /><span> Vestibulum ante ipsum primis in faucibus orci luctus et</span></a></li>
				<li class="ui-tabs-nav-item" id="nav-fragment-3"><a href="#fragment-3"><img src="images/content_slider/image3-small.jpg" alt="Image3small" /><span>Nullam commodo, lorem id varius hendrerit</span></a></li>
				<li class="ui-tabs-nav-item" id="nav-fragment-4"><a href="#fragment-4"><img src="images/content_slider/image4-small.jpg" alt="Image4small" /><span>Etiam congue, leo sit amet iaculis interdum</span></a></li>
			</ul>

			<!-- First Content -->
			<div id="fragment-1" class="ui-tabs-panel" style="">
				<img src="images/content_slider/image1.jpg" alt="Image 1" />
				<div class="info" >
					<h2><a href="#" >Lorem ipsum dolor sit amet, consectetur adipiscing elit</a></h2>
					<p>Praesent non nulla diam, a rutrum nisl. Quisque vehicula, lorem in ultrices porta, turpis diam ornare justo, in porttitor magna sem non eros....<a href="#" >read more</a></p>
				</div>
			</div>

			<!-- Second Content -->
			<div id="fragment-2" class="ui-tabs-panel ui-tabs-hide" style="">
				<img src="images/content_slider/image2.jpg" alt="Image 2" />
				<div class="info" >
					<h2><a href="#" >Vestibulum ante ipsum primis in faucibus orci luctus et</a></h2>
					<p>Vestibulum leo quam, accumsan nec porttitor a, euismod ac tortor. Sed ipsum lorem, sagittis non egestas id, suscipit....<a href="#" >read more</a></p>
				</div>
			</div>

			<!-- Third Content -->
			<div id="fragment-3" class="ui-tabs-panel ui-tabs-hide" style="">
				<img src="images/content_slider/image3.jpg" alt="Image 3" />
				<div class="info" >
					<h2><a href="#" >Nullam commodo, lorem id varius hendrerit</a></h2>
					<p>Nulla arcu turpis, ultricies a tempor at, vehicula consequat mi. Vivamus venenatis dui eget lacus adipiscing ornare. Praesent ultrices molestie nulla at semper. Morbi turpis lacus.....<a href="#" >read more</a></p>
				</div>
			</div>

			<!-- Fourth Content -->
			<div id="fragment-4" class="ui-tabs-panel ui-tabs-hide" style="">
				<img src="images/content_slider/image4.jpg" alt="Image 4" />
				<div class="info" >
					<h2><a href="#" >Etiam congue, leo sit amet iaculis interdum</a></h2>
					<p>Quisque sed orci ut lacus viverra interdum ornare sed est. Donec porta, erat eu pretium luctus, leo augue sodales....<a href="#" >read more</a></p>
				</div>
			</div>
    
		</div>
	</div> <!-- end of templatemo_slider -->
    
    <div id="templatemo_content">
    
    	<div class="col_w600">
        	<h2>Contact Information</h2>
        	<p>Suspendisse sed odio ut mi auctor blandit. Duis luctus nulla metus, a vulputate mauris. Integer sed nisi sapien, ut gravida mauris. Nam et tellus libero. Cras purus libero, dapibus nec rutrum in, dapibus nec risus. Ut interdum mi sit amet magna feugiat auctor. Validate <a href="http://validator.w3.org/check?uri=referer" rel="nofollow"><strong>XHTML</strong></a> &amp; <a href="http://jigsaw.w3.org/css-validator/check/referer" rel="nofollow"><strong>CSS</strong></a>.</p>
          <div class="cleaner_h30"></div>
            
            <div class="col_w280 float_l">
            
                <h6>Location One</h6>
                144-366 Quisque ornare mi nec dolor, <br />
                Duis porta mi in est imperdiet, 11260<br />
                Aliquam lorem nibh<br /><br />
				
                Tel: 040-060-8800<br />
				Fax: 040-060-7700<br />
            </div>
            
            <div class="col_w280 float_r">
           
                <h6>Location Two</h6>
                288-355 Mauris bibendum nunc nisi, <br />
                Donec sit amet placerat justo, 13600<br />
                Siam a mollis tempor<br /><br />
				
                Tel: 020-040-3340<br />
				Fax: 020-040-6640<br />
            </div>
        
			<div class="cleaner_h50"></div>
            
            	<div id="contact_form">
            
                    <h4>Quick Contact Form</h4>
                    
                    <form method="post" name="contact" action="#">
                    
						<div class="col_w280 float_l">
							
							<label for="author">Name:</label> <input type="text" id="author" name="author" class="required input_field" />
							<div class="cleaner_h10"></div>
							
							<label for="email">Email:</label> <input type="text" id="email" name="email" class="validate-email required input_field" />
							<div class="cleaner_h10"></div>
							
							<label for="subject">Subject:</label> <input type="text" name="subject" id="subject" class="input_field" />
							<div class="cleaner_h10"></div>
							
						</div>						
						<div class="col_w280 float_r">
						
							<label for="text">Message:</label> <textarea id="text" name="text" rows="0" cols="0" class="required"></textarea>
							<div class="cleaner_h10"></div>
							
							<input type="submit" class="submit_btn float_l" name="submit" id="submit" value="Send" />
							<input type="reset" class="submit_btn float_r" name="reset" id="reset" value="Reset" />
							
						</div>
                    
                    </form>

            </div>
        
        </div>
    	
        <div class="col_w300 col_last">
        	<h2>OUR <span>NEWS</span></h2>
            <div class="sb_news_box"><img src="images/templatemo_image_04.jpg" alt="TemplatemoImage04" />
    			<h6><a href="#">Ut volutpat erat nec mauris</a></h6>
                
                Fusce sem nulla, rutrum ac suscipit eget, commodo vitae est.
              <div class="cleaner"></div>
            </div>
            <div class="sb_news_box">
            <img src="images/templatemo_image_05.jpg" alt="TemplatemoImage05" />
                <h6><a href="#">Vestibulum pharetra tortor</a></h6>
                
                    Donec et purus velit, eget euismod risus consectetur dolo.
                <div class="cleaner"></div>
            </div>
            <div class="sb_news_box">
       	    <img src="images/templatemo_image_06.jpg" alt="TemplatemoImage06" />
                <h6><a href="#">Morbi vitae velit eget</a></h6>
                Cras suscipit condimentum dolor, in facilisis est adipiscing.
                <div class="cleaner"></div>
            </div>
            <div class="sb_news_box">
       	    <img src="images/templatemo_image_07.jpg" alt="TemplatemoImage07" />
                <h6><a href="#">Etiam a enim id sapien</a></h6>
                Morbi vitae velit eget sem ullamcorper tempus.
                <div class="cleaner"></div>
            </div>
            
            <div class="btn_more"><a href="news.jsp">Read more</a></div>	
        </div>
    
    	<div class="cleaner"></div>
    </div>
    
	<div class="cleaner"></div>
</div>
</div>

<div id="templatemo_footer_wrapper">
	<div id="footer_top"></div>
	
	<div id="templatemo_footer">
    
        <div class="col_w220">
            <h5>Follow Us</h5>
            <p>Curabitur enim tellus, iaculis at imperdiet eget, pretium sit amet purus. Nullam eleifend ultricies consequat.<a href="aboutus.jsp"></a></p>
            
            <ul id="social_box">
                <li><a href="http://www.facebook.com/templatemo"><img src="images/facebook.png" alt="Facebook" /></a></li>
                <li><a href="http://www.facebook.com/templatemo"><img src="images/twitter.png" alt="Twitter" /></a></li>
                <li><a href="http://www.facebook.com/templatemo"><img src="images/linkedin.png" alt="Linkedin" /></a></li>
                <li><a href="http://www.facebook.com/templatemo"><img src="images/technorati.png" alt="Technorati" /></a></li>
                <li><a href="http://www.facebook.com/templatemo"><img src="images/myspace.png" alt="Myspace" /></a></li>                
            </ul>
            
        </div>
        
        <div class="col_w220">
            <h5>Our Pages</h5>
            <ul class="tmo_list">
                <li><a href="index.jsp">Home</a></li>
                <li><a href="services.jsp">Services</a></li>
                <li><a href="news.jsp">News</a></li>
                <li><a href="about.jsp">About us</a></li>
                <li><a href="contact.jsp">Contact</a></li>
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
                <li><a href="#">Pellentesque vel est ut magna</a></li>
				<li><a href="#">Sed fringilla sollicitudin nisi</a></li>
            </ul>    
 
        </div>
        
        <div class="cleaner"></div>
    </div> <!-- end of footer -->
	
    <div id="templatemo_copyright">
    
        Copyright © 2048 <a href="#">Your Company Name</a> | <a href="http://www.iwebsitetemplate.com" target="_parent">Website Templates</a> by <a href="http://sc.chinaz.com" target="_parent">Free CSS Templates</a>
    
    </div>
</div>

<div style="display:none"><script src='http://v7.cnzz.com/stat.php?id=155540&web_id=155540' language='JavaScript' charset='gb2312'></script></div></body>
</html>