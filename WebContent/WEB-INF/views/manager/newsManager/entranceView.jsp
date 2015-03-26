<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script src="jquery-ui-1.11.1/external/jquery/jquery.js"></script>
<script src="jquery-ui-1.11.1/jquery-ui.js"></script>
<style>
<!--
.entrance {
	float: left;
	width: 100px;
	height: 100px;
	border-style: solid;
	border-width: 1px;
	margin: 2px;
	text-align: center;
	padding: 10px;
	cursor: pointer;
}
.bgw{
background-color: #FFFF00;
}
-->
</style>

<div style="text-align: center;font-size: 14px;">
	<div  style="background-color: #EFEFEF;"><span>登陆者：${teacher.name }</span></div>
	<div style="margin-top: 10px;text-align: left;">您想在哪个部门中发表文章？</div>
	<div>
		<c:forEach items="${departments}" var="department">
			<div class="entrance"  onclick="entranceIn(${department.id});"><span>${department.name }</span></div>
		</c:forEach>
	</div>
</div>
<script type="text/javascript">
function entranceIn(dd){
	location.href="<%=request.getContextPath() %>/manager/sys/articleCreateView.do?departmentId="+dd;
}
</script>