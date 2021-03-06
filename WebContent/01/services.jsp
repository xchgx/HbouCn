<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Sailing Theme - Services Page</title>
<meta name="keywords" content="sailing, services, web design, free templates, website templates, CSS, HTML" />
<meta name="description" content="Sailing Services - free website template by templatemo.com" />
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
                <li><a href="services.jsp" class="current">Services</a></li>
                <li><a href="news.jsp">News</a></li>
                <li><a href="about.jsp">About us</a></li>
                <li><a href="contact.jsp">Contact</a></li>
            </ul>    	
        </div> <!-- end of templatemo_menu -->

    </div> <!-- end of templatemo_header -->
    
    <div id="templatemo_slider">
    
		<div id="featured" >
			<ul class="ui-tabs-nav">
				<li class="ui-tabs-nav-item ui-tabs-selected" id="nav-fragment-1"><a href="#fragment-1"><img src="images/content_slider/image1-small.jpg" alt="Image1small" /><span>Lorem ipsum dolor sit amet, consectetur adipiscing elit</span></a></li>
				<li class="ui-tabs-nav-item" id="nav-fragment-2"><a href="#fragment-2"><img src="images/content_slider/image2-small.jpg" alt="Image2small" /><span>Vestibulum ante ipsum primis in faucibus orci luctus et</span></a></li>
				<li class="ui-tabs-nav-item" id="nav-fragment-3"><a href="#fragment-3"><img src="images/content_slider/image3-small.jpg" alt="Image3small" /><span>Nullam commodo, lorem id varius hendrerit</span></a></li>
				<li class="ui-tabs-nav-item" id="nav-fragment-4"><a href="#fragment-4"><img src="images/content_slider/image4-small.jpg" alt="Image3small" /><span>Etiam congue, leo sit amet iaculis interdum</span></a></li>
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
					<p>Nulla arcu turpis, ultricies a tempor at, vehicula consequat mi. Vivamus venenatis dui eget lacus adipiscing ornare. Praesent ultrices molestie nulla at semper. Morbi turpis lacus....<a href="#" >read more</a></p>
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
        	
         	<div class="service_box">
            	
                <img src="images/onebit_15.png" alt="Service 1" />
                <div class="sb_right">
                	<h4>Web Design</h4>
                    <p>Credit goes to <a href="http://www.icojoy.com/articles/46/" target="_blank">icojoy.com</a> for icons. Morbi sed nulla ac est cursus suscipit. Nullam consectetur posuere porta. Aliquam laoreet, libero ac placerat  tempor.</p>
                
                    <ul class="tmo_list">
                    	<li>Vestibulum pretium convallis diam sit amet </li>
                        <li>Donec consequat, lacus eget accumsan volutpat</li>
                        <li>Fusce sem nulla, rutrum ac suscipit eget vitae</li>
                       
                    </ul>
                </div>
                <div class="cleaner"></div>
                <div class="btn_more float_r"><a href="#">More</a></div>
                <div class="cleaner"></div>
            </div>
            
             <div class="service_box">
            	
                <img src="images/onebit_16.png" alt="Service 2" />
                <div class="sb_right">
                	<h4>Online Advertising</h4>
                    <p>Integer consectetur enim eget diam tincidunt vehicula. Aenean scelerisque tellus vitae tortor placerat egestas.</p>
                
                    <ul class="tmo_list">
                        <li>Praesent lacus metus, aliquet sit </li>
                        <li>Nunc eget turpis mattis nisi auctor</li>
                        <li>Vestibulum pharetra tortor vitae velit </li>
                    </ul>
                </div>
                <div class="cleaner"></div>
                <div class="btn_more float_r"><a href="#">More</a></div>
                <div class="cleaner"></div>
            </div>
            
            <div class="cleaner"></div>
            
            <div class="service_box">
            	
                <img src="images/onebit_19.png" alt="Service 3" />
                <div class="sb_right">
                	<h4>Global Reaching</h4>
                    <p>Praesent gravida gravida pellentesque. Fusce lectus nulla, adipiscing at aliquam vitae, placerat sed leo. </p>
                
                    <ul class="tmo_list">
						<li>Quisque purus sem, egestas nec tincidunt hendrerit</li>
                        <li>Excepteur sint occaecat cupidatat non proident </li>
                        <li>Nam id nisl vel magna tempus faucibus fermentum iaculis mi</li>
                    </ul>
					
                </div>
                <div class="cleaner"></div>
                <div class="btn_more float_r"><a href="#">More</a></div>
                <div class="cleaner"></div>
            </div>  
            
        </div>
        
        <div class="col_w300 col_last">
            <h2>ABOUT US</h2>
            <div class="image_wrapper"><img width="240" height="110" src="images/templatemo_image_02.jpg" alt="Image 02" /></div>
            <p><em>Etiam ut urna ante, ut pulvinar ante. Vivamus a metus quam. Aenean non eros nunc. </em></p>
            <p>Integer ultrices. Donec hendrerit. Maecenas nisl ante, mollis et, tincidunt vitae, feugiat sit amet, mi. Vestibulum urna. Phasellus tempus, justo et laoreet varius, odio mi ultrices libero, nec faucibus dui felis eu dui. Cras ac odio ac mi imperdiet sollicitudin. In hac habitasse platea dictumst. Curabitur euismod scelerisque.  Validate <a href="http://validator.w3.org/check?uri=referer" rel="nofollow"><strong>XHTML</strong></a> &amp; <a href="http://jigsaw.w3.org/css-validator/check/referer" rel="nofollow"><strong>CSS</strong></a>.</p>
          <div class="btn_more"><a href="about.jsp">Read more</a></div>
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
            <p>Curabitur enim tellus, iaculis at imperdiet eget, pretium sit amet purus. Nullam eleifend ultricies consequat.</p>
            
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