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
	margin-top:10px;
	width: 450px;
	height: 70px;
}

.logoenglish {
	margin-top: -5px;
	width: 450px;
	height: 30px;
}
</style>
<script type="text/javascript">
	
</script>
<div class="top_panel">
	<div class="logo">
		<div style="float: left;">
			<img class="logopic" src="<%=request.getContextPath()%>/img/xiaohui.png" style="width:100px;height:100px;">
		</div>
		<div style="float: left;">
			<img class="logotext" src="<%=request.getContextPath()%>/img/hbkfzyxy.png"><br>
			<img class="logoenglish" src="<%=request.getContextPath()%>/img/text/englishxiaoming.png" >
		</div>
		<div style="height:5px;"></div>
		<div id="indexTopPageImgId"  style="margin-left: 80px; margin-top: 100px;"></div>
	</div>
	<div class="xiaoxun">
		<img src="<%=request.getContextPath()%>/img/text/xiaoxun.png">
	</div>
</div>