<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>更新日志</title>
<link href="<%=request.getContextPath() %>/jquery-ui-1.11.1/jquery-ui.css" rel="stylesheet">
<script src="<%=request.getContextPath() %>/jquery-ui-1.11.1/external/jquery/jquery.js"></script>
<script src="<%=request.getContextPath() %>/jquery-ui-1.11.1/jquery-ui.js"></script>
<style>
*{
	font-size: 10px;
	font-style: normal;
	font-weight: 20;
}
.bug{
	font-size: 18px;
	font-style: italic;
	font-weight: 20;
}
.update{
	font-size: 22px;
	font-style: normal;
	font-weight: 20;
	color: green;
}
ol{
	magin:0px;
	padding-left: 15px;;
}
</style>
</head>
<body>
 <script>
$(function() {
$( "#accordion" ).accordion({
heightStyle: "content"
});
});
</script>
</head>
<body>
<div id="accordion">

	<h3>V1.5  2015年1月14日 更新日志</h3>
	<div>
		<fieldset class="bug"><legend>变更记录</legend>
			<ol>
				<li>文章标题字体缩小</li>
			</ol>
		</fieldset>
		<fieldset class="update"><legend>新增内容</legend>
			<ol>
				<li>加入<a href="<%=request.getContextPath()%>/index/hbouzhiliangbaogao/index.html">《湖北开放职业学院高等职业教育质量年度报告（2015）》</a>浏览页</li>
			</ol>
		</fieldset>
	</div>
	
	<h3>V1.4  2015年1月14日 更新日志</h3>
	<div>
		<fieldset class="bug"><legend>变更记录</legend>
			<ol>
				<li>已修复： 首页动态预览（教务/学工/校园动态  新闻列表右侧的日期错位问题</li>
				<li>已修复： 首页通知公告下方列表日期错位问题</li>
			</ol>
		</fieldset>
		<fieldset class="update"><legend>新增内容</legend>
			<ol>
				<li>无</li>
			</ol>
		</fieldset>
	</div>
	
	<h3>V1.3  2015年1月9日 更新日志</h3>
	<div>
		<fieldset class="bug"><legend>变更记录</legend>
			<ol>
				<li>修改鼠标提示方式，避免了通知公告提示中显示的代码段</li>
			</ol>
		</fieldset>
		<fieldset class="update"><legend>新增内容</legend>
			<ol>
				<li>无</li>
			</ol>
		</fieldset>
	</div>
	
<h3>V1.2  2015年1月8日 更新日志</h3>
	<div>
		<fieldset class="bug"><legend>变更记录</legend>
			<ol>
				<li>新增三个校企合作：天泽、新隆、东林汇</li>
				<li>修改首页公告两个标题，鉴于用户习惯，去掉超大字体的标题，用户发表文章时在正文中自行添加公告标题</li>
				<li>可单独添加普通用户，该用户只有“我的电脑”和“发表文章”的权限，非管理者使用</li>
			</ol>
		</fieldset>
		<fieldset class="update"><legend>新增内容</legend>
			<ol>
				<li>部门左下角的栏目新增部门和栏目链接，方便点击进入，功能与当前文章路径链接类似</li>
			</ol>
		</fieldset>
	</div>

	<h3>V1.1  2014年12月25日 更新日志</h3>
	<div>
		<fieldset class="bug"><legend>变更记录</legend>
			<ol>
				<li>修复文章后台删除后前台依然存在</li>
				<li>党办与校办共一个部门主页，由校办管理</li>
				<li>取消后勤处，更改为工会</li>
				<li>电教中心和网络中心共一个部门主页，由电教中心管理</li>
			</ol>
		</fieldset>
		<fieldset class="update"><legend>新增内容</legend>
			<ol>
				<li>无</li>
			</ol>
		</fieldset>
	</div>

	<h3>V1.0  2014年12月24日 更新日志</h3>
	<div>
		<fieldset class="bug"><legend>修复已知错误</legend>
			<ol>
				<li>修复首页校园动态长度超出限制</li>
				<li>修复首页二级导航编辑时偶尔失败</li>
			</ol>
		</fieldset>
		<fieldset class="update"><legend>新增内容</legend>
			<ol>
				<li>自主查询添加学籍查询、毕业证书查询、成绩查询；</li>
				<li>添加新文章保存后弹出提示询问是否继续添加文章。</li>
			</ol>
		</fieldset>
	</div>
	
</div>
</body>
</html>