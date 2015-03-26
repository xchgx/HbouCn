/**
 * departmentPageJs.js
 */
$(function() {
	var per = 0;
	var totalCount =0;
	var timeoutId ;

	$("#index2HtmlStatusWindow").dialog({
		autoOpen : false,
		closeOnEscape : false,
		resizable : false, 
		modal: true,
		open : function() {
			$("#d2hsw_progress-indexbar").progressbar("value",0);
			releaseFreemarkerIndexTop();
			progress();
		},
		beforeClose : function() {
			$("#btn_index2Html").button("option", {
				disabled : false,
				label : "点击生成首页静态文件"
			});
		}
	});
	$("#btn_index2Html").button().on("click", function() {
		$(this).button("option", {
			disabled : true,
			label : "部门正在静态化中..."
		});
		$("#index2HtmlStatusWindow").dialog("open");
		
		$("#index2HtmlStatusWindow").dialog("option",
				"buttons", [ ]);
	});
	
	$("#d2hsw_progress-indexbar").progressbar(
			{
				value : false,
				change : function() {
					if(totalCount == 0){
						$(".progress-index-label").text( "部门静态化进程，请不要刷新页面 :" + $("#d2hsw_progress-indexbar").progressbar( "value") + "%");
					}else{
						$(".progress-index-label").text( "部门静态化进程"+per+"/"+totalCount+"，请不要刷新页面 :" + $("#d2hsw_progress-indexbar").progressbar( "value") + "%");
					}
					
				},
				complete : function() {
					window.clearTimeout(timeoutId);//停止计时器
					$(".progress-index-label").text("静态化完成!");
					$("#index2HtmlStatusWindow").dialog("option",
							"buttons", [ {
								text : "完毕",
								click : function(){
									$("#index2HtmlStatusWindow").dialog("close");
									$("#d2hsw_progress-indexbar").progressbar("value",false);
								}
							} ]);
					$(".ui-dialog button").last().focus();
				}
			});
	function progress() {
		var val = $("#d2hsw_progress-indexbar").progressbar("value") || 0;
		if(totalCount != 0 && val < Math.ceil(per/totalCount*100)  ){
			$("#d2hsw_progress-indexbar").progressbar("value", val+1);
		}
		if(val >= 100)return;
		timeoutId =window.setTimeout(progress, 20);
	}
	//1
	function releaseFreemarkerIndexTop(){
		totalCount =8;
		$(".progress-index-label2").html("正在生成首页顶部栏---");
		per = 0;
		 $.ajax({
				type: "GET",
				contentType:'application/json;charset=UTF-8',
				url: getBasePath()+"/manager/site/html/releaseFreemarkerIndexTop.do",
				dataType:"json",
				success:function(msg){
					per +=msg;
					$(".progress-index-label2").html($(".progress-index-label2").html()+"完成<br>正在生成首页导航栏---");
					releaseFreemarkerDepartmentNav();
				},
				error:function(){
					$(".progress-index-label2").html("releaseFreemarkerIndexTop getServerProgressStatus error");
				}
		 });			
	}
	/**
	 * 2
	 */
	function releaseFreemarkerDepartmentNav(){
		//alert(dptid);
		var jsonData = {departmentId:0};
		 $.ajax({
				type: "GET",
				contentType:'application/json;charset=UTF-8',
				url: getBasePath()+"/manager/site/html/releaseFreemarkerDepartmentNav.do",
				data:jsonData,
				dataType:"json",
				success:function(msg){
					per += msg;
					$(".progress-index-label2").html($(".progress-index-label2").html()+"完成<br>正在生成公告栏---");
					releaseFreemarkerIndexGongGao();
				},
				error:function(){
					$(".progress-index-label2").html("releaseFreemarkerDepartmentNav getServerProgressStatus error");
				}
		 });			
	}
 
	/**
	 * 3
	 */
	function releaseFreemarkerIndexGongGao(){
		//alert(dptid);
		 $.ajax({
				type: "GET",
				contentType:'application/json;charset=UTF-8',
				url: getBasePath()+"/manager/site/html/releaseFreemarkerIndexGongGao.do",
				dataType:"json",
				success:function(msg){
					per += msg;
					$(".progress-index-label2").html($(".progress-index-label2").html()+"完成<br>正在生成动态预览页");
					releaseFreemarkerDynPreviewArticleList( 7, 10, 19);//校办的校园动态
					releaseFreemarkerDynPreviewArticleList( 10, 15, 8);//教务的教务动态
					releaseFreemarkerDynPreviewArticleList( 11, 15, 8);//学工的学生动态
					releaseFreemarkerIndexFoot();//底部栏
				},
				error:function(){
					$(".progress-index-label2").html("releaseFreemarkerIndexGongGao getServerProgressStatus error");
				}
		 });			
	}
	/**
	 * 456
	 */
	function releaseFreemarkerDynPreviewArticleList( departmentId,  size, strLength){
		//alert(dptid);
		var jsonData = {departmentId:departmentId, size:size,  strLength:strLength};
		 $.ajax({
				type: "GET",
				contentType:'application/json;charset=UTF-8',
				url: getBasePath()+"/manager/site/html/releaseFreemarkerDynPreviewArticleList.do",
				data:jsonData,
				dataType:"json",
				success:function(msg){
					per += msg;
					$(".progress-index-label2").html($(".progress-index-label2").html()+departmentId+":完成<br>");
				},
				error:function(){
					$(".progress-index-label2").html("releaseFreemarkerDynPreviewArticleList"+departmentId+","+size+","+strLength+" getServerProgressStatus error");
				}
		 });			
	}
	//7
	function releaseFreemarkerIndexFoot(){
		//alert(dptid);
		$(".progress-index-label2").html($(".progress-index-label2").html()+"<br>正在部门业生成---");
		 $.ajax({
				type: "GET",
				contentType:'application/json;charset=UTF-8',
				url: getBasePath()+"/manager/site/html/releaseFreemarkerIndexFoot.do",
				dataType:"json",
				success:function(msg){
					per += msg;
					$(".progress-index-label2").html($(".progress-index-label2").html()+"完成");
					$(".progress-index-label").text( "部门静态化进程"+per+"/"+totalCount+"，请不要刷新页面 :" + $("#d2hsw_progress-indexbar").progressbar( "value") + "%");
					releaseFreemarkerIndexQuickMap();
				},
				error:function(){
					$(".progress-index-label2").html("releaseFreemarkerIndexFoot getServerProgressStatus error");
				}
		 });			
	}
	//8releaseFreemarkerIndexQuickMap
	function releaseFreemarkerIndexQuickMap(){
		//alert(dptid);
		$(".progress-index-label2").html($(".progress-index-label2").html()+"<br>正在生成快速通道---");
		 $.ajax({
				type: "GET",
				contentType:'application/json;charset=UTF-8',
				url: getBasePath()+"/manager/site/html/releaseFreemarkerIndexQuickMap.do",
				dataType:"json",
				success:function(msg){
					per += msg;
					$(".progress-index-label2").html($(".progress-index-label2").html()+"完成");
				},
				error:function(){
					$(".progress-index-label2").html("releaseFreemarkerIndexQuickMap getServerProgressStatus error");
				}
		 });			
	}
});