<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="description"
	content="湖北开放职业学院是湖北省人民政府批准、教育部备案的全日制普通高等院校。招生热线：027—51356220" />
<meta name="keywords" content="湖北开放职业学院" />
<title>毕业生报到证、档案托管去向 查询</title>
<link href="<%=request.getContextPath() %>/jquery-ui-1.11.1/jquery-ui.css" rel="stylesheet">
<link href="<%=request.getContextPath() %>/jquery-ui-1.11.1/jquery-ui.theme.css" rel="stylesheet">
<script src="<%=request.getContextPath() %>/jquery-ui-1.11.1/external/jquery/jquery.js"></script>
<script src="<%=request.getContextPath() %>/jquery-ui-1.11.1/jquery-ui.js"></script>
<script src="<%=request.getContextPath() %>/js/global.js"></script>
<link href="style.css" rel="stylesheet">
</head>
<body>
<div class="main">
	<div class="title">
		<div class="title_left"><img id="title_bk_left" alt="" src="title_background_left.png"><span id="title_bk_right"><a href="http://www.hbou.cn/HbouCn" title="湖北开放职业学院 官网首页">湖北开放职业学院</a>:[毕业就业办公室]-毕业生报到证、档案托管去向查询系统</span></div>
	</div>
	<div class="search">
		<div class="label">请输入身份证号码</div>
		<div class="input"><input type="text" class="input_sfz ui-corner-all" ></div>
		<div class="btn"><a href="javascript:void();" title="点击我开始查询"   class="btn_search"><img class="btn_search_img" src="btn_search.png"></a></div>
		<div class="status ui-corner-all"></div>
	</div>
	<div class="result ui-corner-all"></div>
</div>
<script src="index.js"></script>
</body>
</html>