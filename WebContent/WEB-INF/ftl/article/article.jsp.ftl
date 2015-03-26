<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 
   <div class="content1">
	<div class="dropshadow dropshadow17">
		<div class="ui-corner-all innerbox innerbox17">
			<div>
				<div
					style="background-image: url('${basePath}/img/content_title_left.png'); background-repeat: no-repeat; width: 134px; height: 34px; float: left;margin-left: -4px;"><span style="font-size: 16px;padding-left: 5px; color: white;line-height: 40px;">文章内容</span></div>
				<div
					style="background-image: url('${basePath}/img/content_title_right.png'); background-repeat: repeat-x; height: 34px;margin-right: 3px;padding-right: 3px;"><div style="float: right;line-height: 40px;"></div></div>
			</div>
			<div id="content_r2c2_articleList">
			<center><h3> ${article.title?default("无标题") }   </h3></center>
			<div align="right" style="margin-left:10px; margin-right:20px;"><font color=blue>${article.date?default("---") }</font></div>
		 <hr>
		 <div style="font-size: 12px;font-family: sans-serif;font-style: normal;">
		 ${article.content?default("")}
		 </div>
			</div>
		</div>
	</div>
</div>