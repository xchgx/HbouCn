<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<style>
img {
	border: 0px;
	margin: 0px;
	padding: 0px
}

.logo {
	float: left;
	position: relative;
	margin-left: 0px;
	margin-top: 10px;
	text-align: left;
}

.logopic {
	width: 90px;
	height: 90px;
	margin-left: 20px;
	margin-top: 10px;
}

.logotext {
	width: 490px;
	height: 80px;
}

.logoenglish {
	margin-top: -17px;
	width: 400px;
	height: 45px;
}
</style>
<script type="text/javascript">
	
</script>
<div class="top_panel">
	<div class="logo">
		<div style="float: left;">
			<img class="logopic" src="${basePath}/img/xiaohui.png">
		</div>
		<div style="float: left;">
			<img class="logotext" src="${basePath}/img/hbkfzyxy.png"><br>
			<img class="logoenglish" src="${basePath}/img/text/englishxiaoming.png">
		</div>
		<div style="height:5px;"></div>
		<div id="indexTopPageImgId"  style="margin-left: 80px; margin-top: 100px;"></div>
	</div>
	<div class="xiaoxun">
		<img src="${basePath}/img/text/xiaoxun.png">
	</div>
</div>