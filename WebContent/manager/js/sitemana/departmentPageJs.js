/**
 * departmentPageJs.js
 */
$(function() {
	// $("#departmentPageTabs").tabs({
	// beforeLoad: function(event, ui){
	// ui.jqXHR.error(function(){
	// ui.panel.html("出错，不能加载，请联系管理员解决。");
	// });
	// }
	// });
	//TODO 前期准备工作
	$("#departmentPageTabs").tabs();

	var per = 0;
	var totalCount =0;
	var timeoutId ;
	var departmentId = $("#departmentPageDepartmentId").val();
//	$("#departmentPagetabs-2").load( buildsiteManaUrl("allNavigations") + "?departmentId=" + departmentId);
	//	$("#departmentPagetabs-2").load( buildsiteManaUrl("allNavigations") + "?departmentId=" + departmentId,function(){
//		发布导航栏
//		$("#navigationRelease").on("click", function(){
//			var id = $("#departmentPageDepartmentId").val();
//			alert("部门唯一ID："+id);
//			var jsonData = {departmentId:id};
//			$.ajax({
//				type: "GET",
//				contentType:'application/json;charset=UTF-8',
//				url: getBasePath()+"/manager/site/html/releaseFreemarkerDepartmentNav.do",
//				data:jsonData,
//				dataType:"json",
//				success:function(msg){
//					if(msg == 1){
//						alert("生成全部导航栏完成！");
//					}else{
//						alert("导航生成失败！");
//					}
//				},
//				error:function(){
//					alert("departmentPageDepartmentId error");
//				}
//			});
//		});
//	});
	//TODO 进度对话框
	$("#department2HtmlStatusWindow").dialog({
		autoOpen : false,
		closeOnEscape : false,
		resizable : false, 
		modal: true,
		open : function() {
			departmentId = $("#departmentPageDepartmentId").val();
			$("#d2hsw_progressbar").progressbar("value",0);
			getDepartmentTotalPercentage(departmentId);
			progress();
		},
		beforeClose : function() {
			$("#btn_department2Html").button("option", {
				disabled : false,
				label : "开始静态化"
			});
		}
	});
	$("#btn_department2Html").button().on("click", function() {
		$(this).button("option", {
			disabled : true,
			label : "部门正在静态化中..."
		});
		$("#department2HtmlStatusWindow").dialog("option",
				"buttons", [ ]);
		$("#department2HtmlStatusWindow").dialog("open");
	});
	//btn_department2HtmlClear
	$("#btn_department2HtmlClear").button().on("click", function() {
		$(this).button("option", {
			disabled : false,
			label : "进度状态清空..."
		});
		$(".progress-label2").html("已经清空....");
		
	});

	$("#d2hsw_progressbar").progressbar(
			{
				value : false,
				change : function() {
//					if(totalCount == 0){
//						$(".progress-label").text( "部门静态化进程，请不要刷新页面 :" + $("#d2hsw_progressbar").progressbar( "value") + "%");
//					}else{
						$(".progress-label").text( "部门静态化进程"+per+"/"+totalCount+"，请不要刷新页面 :" + $("#d2hsw_progressbar").progressbar( "value") + "%");
//					}
					
				},
				complete : function() {
					window.clearTimeout(timeoutId);//停止计时器
					$(".progress-label").text("静态化完成!");
					$("#department2HtmlStatusWindow").dialog("option",
							"buttons", [ {
								text : "完毕",
								click : function(){
									$("#department2HtmlStatusWindow").dialog("close");
									$("#d2hsw_progressbar").progressbar("value",false);
								}
							} ]);
					$(".ui-dialog button").last().focus();
				}
			});
	function progress() {
		var val = $("#d2hsw_progressbar").progressbar("value") || 0;
		if(totalCount != 0 && val < Math.ceil(per/totalCount*100)  ){
			$("#d2hsw_progressbar").progressbar("value", val+1);
		}
		if(val >= 100)return;
		timeoutId =window.setTimeout(progress, 20);
	} 
	
	//TODO 静态化函数
	/**
	 * 获取部门总任务数
	 */
	function getDepartmentTotalPercentage(dptid){
		var jsonData = {departmentId:dptid};
		 $.ajax({
				type: "GET",
				contentType:'application/json;charset=UTF-8',
				url: getBasePath()+"/manager/site/html/getDepartmentTotalPercentage.do",
				data:jsonData,
				dataType:"json",
				success:function(msg){
					totalCount = msg;
					$(".progress-label2").html("静态化已经启动，总任务量"+totalCount+"....");
					per = 0;
					releaseFreemarkerDepartmentIndex(dptid);
				},
				error:function(){
					$(".progress-label2").html("getDepartmentTotalPercentage getServerProgressStatus error");
				}
		 });			
	}
	/**
	 * 静态化部门页首页
	 */
	function releaseFreemarkerDepartmentIndex(dptid){
		//alert(dptid);
		$(".progress-label2").html($(".progress-label2").html()+"<br>正在生成首页---");
		var jsonData = {departmentId:dptid};
		 $.ajax({
				type: "GET",
				contentType:'application/json;charset=UTF-8',
				url: getBasePath()+"/manager/site/html/releaseFreemarkerDepartmentIndex.do",
				data:jsonData,
				dataType:"json",
				success:function(msg){
					per += msg;
					$(".progress-label2").html($(".progress-label2").html()+"完成("+per+")");
					releaseFreemarkerDynPreviewArticleList(dptid,10, 8);
				},
				error:function(){
					$(".progress-label2").html("releaseFreemarkerDepartmentIndex getServerProgressStatus error");
				}
		 });			
	}

	 /**
	  * 动态预览页面，在首页显示的。
	  */
	function releaseFreemarkerDynPreviewArticleList( dptid,  size, strLength){
		//alert(dptid);
		var jsonData = {departmentId:dptid, size:size,  strLength:strLength};
		 $.ajax({
				type: "GET",
				contentType:'application/json;charset=UTF-8',
				url: getBasePath()+"/manager/site/html/releaseFreemarkerDynPreviewArticleList.do",
				data:jsonData,
				dataType:"json",
				success:function(msg){
					per += msg;
					$(".progress-index-label2").html($(".progress-index-label2").html()+departmentId+":完成<br>");
					releaseFreemarkerDepartmentWebPath(dptid);
				},
				error:function(){
					$(".progress-index-label2").html("releaseFreemarkerDynPreviewArticleList"+departmentId+","+size+","+strLength+" getServerProgressStatus error");
				}
		 });			
	}
	/**
	 * 静态化部门页web路径
	 */
	function releaseFreemarkerDepartmentWebPath(dptid){
		$(".progress-label2").html($(".progress-label2").html()+"<br>正在生成web路径---");
		//alert(dptid);
		var jsonData = {departmentId:dptid};
		 $.ajax({
				type: "GET",
				contentType:'application/json;charset=UTF-8',
				url: getBasePath()+"/manager/site/html/releaseFreemarkerDepartmentWebPath.do",
				data:jsonData,
				dataType:"json",
				success:function(msg){
					per += msg;
					$(".progress-label2").html($(".progress-label2").html()+"完成("+per+")");
					releaseFreemarkerDepartmentNav(dptid);
				},
				error:function(){
					$(".progress-label2").html("releaseFreemarkerDepartmentWebPath getServerProgressStatus error");
				}
		 });			
	}
	/**
	 * 静态化部门页导航页
	 */
	function releaseFreemarkerDepartmentNav(dptid){
		$(".progress-label2").html($(".progress-label2").html()+"<br>正在生成部门导航---");
		//alert(dptid);
		var jsonData = {departmentId:dptid};
		 $.ajax({
				type: "GET",
				contentType:'application/json;charset=UTF-8',
				url: getBasePath()+"/manager/site/html/releaseFreemarkerDepartmentNav.do",
				data:jsonData,
				dataType:"json",
				success:function(msg){
					per += msg;
					$(".progress-label2").html($(".progress-label2").html()+"完成("+per+")");
					 releaseFreemarkerDepartmentSections(dptid);
				},
				error:function(){
					$(".progress-label2").html("releaseFreemarkerDepartmentNav getServerProgressStatus error");
				}
		 });			
	}
	/**
	 * 静态化部门页部门下的板块页
	 */
	function releaseFreemarkerDepartmentSections(dptid){
		$(".progress-label2").html($(".progress-label2").html()+"<br>正在生成部门子版块列表---");
		//alert(dptid);
		var jsonData = {departmentId:dptid};
		 $.ajax({
				type: "GET",
				contentType:'application/json;charset=UTF-8',
				url: getBasePath()+"/manager/site/html/releaseFreemarkerDepartmentSections.do",
				data:jsonData,
				dataType:"json",
				success:function(msg){
					per += msg;
					$(".progress-label2").html($(".progress-label2").html()+"完成("+per+")");
					releaseFreemarkerDepartmentArticles(dptid);
				},
				error:function(){
					$(".progress-label2").html("releaseFreemarkerDepartmentSections getServerProgressStatus error");
				}
		 });			
	}
	/**
	 *   静态化 部门下的文章列表
	 */
	function releaseFreemarkerDepartmentArticles(dptid){
		$(".progress-label2").html($(".progress-label2").html()+"<br>正在生成部门新闻列表---");
		var jsonData = {departmentId:dptid};
		 $.ajax({
				type: "GET",
				contentType:'application/json;charset=UTF-8',
				url: getBasePath()+"/manager/site/html/releaseFreemarkerDepartmentArticles.do",
				data:jsonData,
				dataType:"json",
				success:function(msg){
					per += msg;
					$(".progress-label2").html($(".progress-label2").html()+"完成("+per+")");
					getDepartmentSectionsList(dptid);
				},
				error:function(){
					$(".progress-label2").html("releaseFreemarkerDepartmentArticles getServerProgressStatus error");
				}
		 });		
	}
	/**
	 *  静态化部门页下的板块文章列表
	 */
	function getDepartmentSectionsList(dptid){
		$(".progress-label2").html($(".progress-label2").html()+"<br>正在生成部门子版块新闻列表[-");
		//alert(dptid);
		var jsonData = {departmentId:dptid};
		 $.ajax({
				type: "GET",
				contentType:'application/json;charset=UTF-8',
				url: getBasePath()+"/manager/site/html/getDepartmentSectionsList.do",
				data:jsonData,
				dataType:"json",
				success:function(msg){
					$(".progress-label2").html($(".progress-label2").html()+"<br>版块新闻列表预计任务数："+ msg.length);
					var initValue = 0;
					if(initValue >= msg.length)
						return;
					else{
						releaseFreemarkerSectionArticlesList(msg,  initValue, msg.length, dptid);
						}
				},
				error:function(){
					$(".progress-label2").html("getDepartmentSectionsList getServerProgressStatus error");
				}
		 });			
	}
	/**
	 * 静态化 生成板块首页
	 * 升级版发布,完成之后调用releaseFreemarkerSectionIndex();
	 */
	function releaseFreemarkerSectionArticlesList(v, i, max, dptid){
		if(i>=max){
			i=0;
			releaseFreemarkerSectionIndex(v, i, max, dptid);
			return;
		}else{
			var jsonData = {sectionId:v[i]};
			$.ajax({
				type: "GET",
				contentType:'application/json;charset=UTF-8',
				url: getBasePath()+"/manager/site/html/releaseFreemarkerSectionArticles.do",
				data:jsonData,
				dataType:"json",
				success:function(msg){
					per += msg;
					releaseFreemarkerSectionArticlesList(v, i+1, max, dptid)
				},
				error:function(){
					$(".progress-label2").html("releaseFreemarkerSectionArticlesList getServerProgressStatus error");
				}
			});		
		}
	}
	/**
	 * 板块首页,升级版， 完成之后调用getAllArticleIdsByDepartmentId(dptid);
	 */
	 function releaseFreemarkerSectionIndex(v, i, max, dptid){
		 if(i >= max){
			 getAllArticleIdsByDepartmentId(dptid);
			 return;
		 }else{
			 var jsonData = {sectionId:v[i]};
			 $.ajax({
				 type: "GET",
				 contentType:'application/json;charset=UTF-8',
				 url: getBasePath()+"/manager/site/html/releaseFreemarkerSectionIndex.do",
				 data:jsonData,
				 dataType:"json",
				 success:function(msg){
					 per += msg;
					 releaseFreemarkerSectionIndex(v, i+1, max, dptid);
				 },
				 error:function(){
					 $(".progress-label2").html("releaseFreemarkerSectionIndex getServerProgressStatus error");
				 }
			 });		
		 }
	 } 
	 /**
	  * 获得部门下的所有文章ID list
	  */
		function getAllArticleIdsByDepartmentId(dptid){
			var jsonData = {departmentId:dptid};
			 $.ajax({
					type: "GET",
					contentType:'application/json;charset=UTF-8',
					url: getBasePath()+"/manager/site/html/getAllArticleIdsByDepartmentId.do",
					data:jsonData,
					dataType:"json",
					success:function(msg){
						$(".progress-label2").html($(".progress-label2").html()+"<br>部门文章篇数预计任务数："+ msg.length);
						var initValue = 0;
						if(initValue >= msg.length)
							return;
						else{
							releaseFreemarkerArticle(msg,  initValue, msg.length, dptid);
							}
					},
					error:function(){
						$(".progress-label2").html("getAllArticleIdsByDepartmentId getServerProgressStatus error");
					}
			 });			
		}
		/**
		 *   生成文章div
		 * 升级版，完成之后在调用:releaseFreemarkerArticleIndex
		 */
		function releaseFreemarkerArticle(v, i, max, dptid){
			if(i >= max){
				i=0;
				releaseFreemarkerArticleIndex(v, i, max, dptid);
				return;
			}else{
				var jsonData = {articleId:v[i]};
				$.ajax({
					type: "GET",
					contentType:'application/json;charset=UTF-8',
					url: getBasePath()+"/manager/site/html/releaseFreemarkerArticle.do",
					data:jsonData,
					dataType:"json",
					success:function(msg){
						per += msg;
						 releaseFreemarkerArticle(v, i+1, max, dptid);
					},
					error:function(){
						$(".progress-label2").html("releaseFreemarkerArticle getServerProgressStatus error");
					}
				});		
			}
		}
		/**
		 * 文章首页
		 * 升级版 自调用。这里是最后一个静态发布。
		 */
		function releaseFreemarkerArticleIndex(v, i, max, dptid){
			if(i >= max){
				return;
			}else{
				var jsonData = {articleId:v[i]};
				$.ajax({
					type: "GET",
					contentType:'application/json;charset=UTF-8',
					url: getBasePath()+"/manager/site/html/releaseFreemarkerArticleIndex.do",
					data:jsonData,
					dataType:"json",
					success:function(msg){
						per += msg;
						releaseFreemarkerArticleIndex(v, i+1, max, dptid)
					},
					error:function(){
						$(".progress-label2").html("releaseFreemarkerArticleIndex getServerProgressStatus error");
					}
				});		
			}
		}
});







/**
 *TODO allNavigations.jsp 导航栏设置
 */
/**
 * 编辑导航选项
 */
function allNavigationsOperatorEdit(id) {
	 $("#navigationId").val(id);
    var jsonData = {navigationId:id};
    	 $.ajax({
			type: "GET",
			contentType:'application/json;charset=UTF-8',
			url: getBasePath()+"/manager/site/navigationEditView.do",
			data:jsonData,
			dataType:"json",
			success:function(msg){
				$("#dialog-addNavigationForm" ).dialog("open");
				 //alert(id+"$(#navigationId).val(id);"+$("#navigationId").val());
				 $("#navigationId").val(msg.id);
				 $("#navigationName").val(msg.name);
				 $("#navigationLevel").val(msg.level);
				 if(msg.level == 1){
					 $("#divNavigationFatherNavigations").css({display:"none"});
				 }else{
					 $("#divNavigationFatherNavigations").css({display:"block"});
					 if(!msg.fatherNavigation)
						 alert("父导航加载失败！");
					 else
						 $(":radio[name='navigationFatherNavigations'][value='"+msg.fatherNavigation.id+"']").prop("checked",true);
				 }
				 $("#navigationUrlValue").val(msg.url);
				 $("#navigationTip").val(msg.tip);
				 $("#navigationIco").val(msg.ico);
				 $("#navigationStyle").val(msg.style);
				 $("#navigationSort").val(msg.sort);
				 if(msg.targetBlank)
					 $("#navigationTargetBlank1").prop("checked",true);
				 else
					 $("#navigationTargetBlank0").prop("checked",true);
				 if(msg.display){
					$("#navigationDisplay1").prop("checked",true);
				 }else{
					$("#navigationDisplay0").prop("checked",true);
				 }
				 $("#navigationDescription").val(msg.description);
			},
			error:function(){
				 alert("error");
			}
	 });			
}
/**
 * 创建导航栏
 */
function allNavigationsOperatorCreate(){
	$( "#dialog-addNavigationForm" ).css({display:"block"});
	$("#navigationId").val("");
	 $("#navigationName").val("");
	 $("#navigationLevel").val("1");
	 $("#divNavigationFatherNavigations").css({display:"none"});
	 //alert(id+"$(#navigationId).val(id);"+$("#navigationId").val());
	 $("#navigationUrlValue").val("");
	 $("#navigationTip").val("");
	 $("#navigationIco").val("");
	 $("#navigationStyle").val("");
	 $("#navigationSort").val(1);
	 $("#navigationTargetBlank0").prop("checked",true);
	 $("#navigationDisplay1").prop("checked",true);
	 $("#navigationDescription").val("");

		$("#dialog-addNavigationForm" ).dialog("open");
	
}
/**
 * 删除导航栏
 * @param id
 */
function allNavigationsOperatorDelete(id) {
	//var url =  getBasePath() + "/manager/site/articleDelete.do?articleId=" + id;
	var jsonData = {navigationId:id};
	 $.ajax({
			type: "GET",
			contentType:'application/json;charset=UTF-8',
			url: getBasePath()+"/manager/site/navigationDelete.do",
			data:jsonData,
			dataType:"json",
			success:function(msg){
				  $("#tableTrContentNavId"+msg).remove();
			},
			error:function(){
				 alert("error");
			}
	 });			
}
$(function() {
	//超链接显示下一级栏目
	$("a[class='allNavigationListTagATeacher']").on({
		mouseenter : function(e) {
			var id = $(this).attr("tip");
			var title = $("#allNavigationListTagATeacher"+id).html();
			title = title.replace(/\s/gm,'');
			title = title.replace(/<br>/gm,'\r');
			$(this).attr({"title":title});
		} 
	});
	
	//var dialog = 
		$( "#dialog-addNavigationForm" ).dialog({
		 autoOpen: false,
		 height: 480,
		 width: 350,
		 modal: true,
		 buttons: {
		 "添加": function(){
			 var yesno = submitCreateNavigation();
			// alert("submitCreateNavigation() yesno="+yesno);
			if(yesno != "N"){
				$("#dialog-addNavigationForm" ).dialog("close");
				
				//$("#allDataPage").load(getBasePath()+"/manager/site/allNavigations.do?departmentId="+yesno);
			}
		 },
		 Cancel: function() {
			 $("#dialog-addNavigationForm" ).dialog("close");
		 }
		 },
		 close: function() {
			 $("#dialog-addNavigationForm" ).dialog("close");
		 }
		 });
	
	
//	$("#allNavigationKuaiSuAdd").on("click",function(){
//		$( "#dialog-addNavigationForm" ).css({display:"block"});
//		$("#navigationId").val("");
//		dialog.dialog("open");
//	});
	//快速添加按钮事件
	 $( "#allNavigationKuaiSuAdd" ) .button() .click(function( event ) {		
//		 $( "#dialog-addNavigationForm" ).css({display:"block"});
//		$("#navigationId").val("");
//		$("#dialog-addNavigationForm" ).dialog("open");
		 allNavigationsOperatorCreate();
	 });
	
	$("#navigationLevel").on("change",function(){
		if($(this).find("option:selected").val()==2){
			$("#divNavigationFatherNavigations").css({display:"block"});
			$("#divNavigationFatherNavigations").html(getLoadingGif(10));
			loadFirstNavigations();
		}else{
			$("#divNavigationFatherNavigations").css({display:"none"});
		}
	});
	addFunctionForLiLink();//为操作列添加单击事件。
	
	$("#navigationRelease").on("click", function(){
		var id = $("#navigationDepartmentId").val();
//		alert("部门唯一ID："+id);
		var jsonData = {departmentId:id};
		$.ajax({
			type: "GET",
			contentType:'application/json;charset=UTF-8',
			url: getBasePath()+"/manager/site/html/releaseFreemarkerDepartmentNav.do",
			data:jsonData,
			dataType:"json",
			success:function(msg){
				if(msg == 1){
					alert("生成全部导航栏完成！");
				}else{
					alert("导航生成失败！");
				}
			},
			error:function(){
				alert("navigationRelease error");
			}
	 });
	});

	 //设置导航链接目标地址
		//var dialog_navUrl = 
			$( "#dialog-addNavigationUrl" ).dialog({
			 autoOpen: false,
			 height: 350,
			 width: 350,
			 modal: true,
			 buttons: {
			 "添加": function(){
				var yesno= addNavigationUrl();
				if(yesno) {
					$( "#dialog-addNavigationUrl" ).dialog( "close" );
				}
			 },
			 Cancel: function() {
				 $( "#dialog-addNavigationUrl" ).dialog( "close" );
			 }
			 },
			 close: function() {
				 $( "#dialog-addNavigationUrl" ).dialog( "close" );
			 }
			 });
		//设置导航链接目标地址对话框
//		$("#navigationUrl").on("click",function(){
//			$( "#dialog-addNavigationUrl" ).css({display:"block"});
//			dialog_navUrl.dialog("open");
//		});
		 $( "#navigationUrl" ) .button() .click(function( event ) {		
				$( "#dialog-addNavigationUrl" ).css({display:"block"});
				$( "#dialog-addNavigationUrl" ).dialog( "open" );
		 });
		 
		//导航链接目标地址类型Radio添加点击事件
		$("input[name='navigationUrlTypeRadio']").on("click",function(){
			if($(this).attr("target") == "Department"){
				$("#navigationUrlTypeSection").css({display:"none"});
				$("#navigationUrlTypeArticle").css({display:"none"});
				$("#navigationUrlTypeDepartment").css({display:"block"});
			}else if($(this).attr("target") == "Section"){
				$("#navigationUrlTypeDepartment").css({display:"block"});
				$("#navigationUrlTypeSection").css({display:"block"});
				$("#navigationUrlTypeArticle").css({display:"none"});
			}else if($(this).attr("target") == "Article"){
				$("#navigationUrlTypeDepartment").css({display:"block"});
				$("#navigationUrlTypeSection").css({display:"block"});
				$("#navigationUrlTypeArticle").css({display:"block"});
			}
		})
		//加载部门数据源
		loadDepartmentAutocompleteSource();

});

//加载部门数据源
function loadDepartmentAutocompleteSource(){
	var departments=new Array();
	 $.ajax({
			type: "GET",
			contentType:'application/json;charset=UTF-8',
			url: getBasePath()+"/manager/site/allDepartmentsJson.do",
			dataType:"json",
			success:function(msg){
				$.each(msg,function(i, department){
					var d = new Object();
					d.label=department.name;
					d.value=department.id;
					departments.push(d);
				});
				 
				$( "#navUrlDepartmentList" ).autocomplete({
					minLength: 0,
					source: departments, 
					select: function( event, ui ) {
						 $( "#navUrlDepartmentList" ).val( ui.item.label );
						 $( "#navUrlDepartmentListId" ).val( ui.item.value );
						 loadSectionAutocompleteSource(ui.item.value);
						 return false;
						 }
				});
			},
			error:function(){
				alert("loadDepartmentAutocompleteSource error");
			}
	 });
}
//加载子部门板块自动提示源数据
function loadSectionAutocompleteSource(dptId){
	var sections=new Array();
	var jsonData = {departmentId:dptId};
	 $.ajax({
			type: "GET",
			contentType:'application/json;charset=UTF-8',
			url: getBasePath()+"/manager/site/getDepartmentSectionsJson.do",
			data:jsonData,
			dataType:"json",
			success:function(msg){
				 $( "#navUrlSectionList" ).val( "" );
				 $( "#navUrlSectionListId" ).val( "" );
				$.each(msg,function(i, section){
					var d = new Object();
					d.label=section.name;
					d.value=section.id;
					sections.push(d);
				});
				 
				$( "#navUrlSectionList" ).autocomplete({
					minLength: 0,
					source: sections, 
					select: function( event, ui ) {
						 $( "#navUrlSectionList" ).val( ui.item.label );
						 $( "#navUrlSectionListId" ).val( ui.item.value );
						 loadArticleAutocompleteSource( ui.item.value );
						 return false;
						 }
				});
			},
			error:function(){
				alert("loadSectionAutocompleteSource error");
			}
	 });
}
//加载文章自动提示源数据
function loadArticleAutocompleteSource(secId){
	var articles=new Array();
	var jsonData = {sectionId:secId};
	 $.ajax({
			type: "GET",
			contentType:'application/json;charset=UTF-8',
			url: getBasePath()+"/manager/site/getSectionArticlesJson.do",
			data:jsonData,
			dataType:"json",
			success:function(msg){
				 $( "#navUrlArticleList" ).val( "" );
				 $( "#navUrlArticleListId" ).val( "" );
				$.each(msg,function(i, article){
					var d = new Object();
					d.label=article.title;
					d.value=article.id;
					articles.push(d);
				});
				 
				$( "#navUrlArticleList" ).autocomplete({
					minLength: 0,
					source: articles, 
					select: function( event, ui ) {
						 $( "#navUrlArticleList" ).val( ui.item.label );
						 $( "#navUrlArticleListId" ).val( ui.item.value );
						 return false;
						 }
				});
			},
			error:function(){
			}
	 });
}
//添加导航地址
function addNavigationUrl(){
	var radioNavType = $("input[name='navigationUrlTypeRadio']:checked").attr("target");
	var dptId= $("#navUrlDepartmentListId").val();
	var secId=$("#navUrlSectionListId").val();
	var artId = $("#navUrlArticleListId").val();
	var url = "";
	//alert(radioNavType);
	if(radioNavType == "Department"){
		//当前选择导航链接目标类型为部门地址
		if(!dptId){alert("部门不能为空");return false;}
		url = getBasePath()+"/navigation/department/"+dptId+".do";
		$("#navigationName").val( $( "#navUrlDepartmentList" ).val());
	}
	else if(radioNavType == "Section"){
		//当前选择导航链接目标类型为子部门栏目	地址
		if(!dptId){alert("部门不能为空");return false;}
		if(!secId){alert("子部门板块不能为空");return false;}
		url = getBasePath() + "/navigation/section/"+secId+".do";
		$("#navigationName").val( $( "#navUrlSectionList" ).val());
	}
	else if(radioNavType == "Article"){
		//当前选择导航链接目标类型为文章地址
		if(!dptId){alert("部门不能为空");return false;}
		if(!secId){alert("子部门板块不能为空");return false;}
		if(!artId){alert("文章标题不能为空");return false;}
		url = getBasePath() + "/navigation/article/"+artId+".do";
		$("#navigationName").val( $( "#navUrlArticleList" ).val());
	}
	$("#navigationUrlValue").val(url);
	return true;
}
/**
 * 为导航操作列添加单击事件
 */
function addFunctionForLiLink(){
	$("li[operation='edit']").on("click",function(){
		//alert("edit:"+$(this).attr("value"));
		allNavigationsOperatorEdit($(this).attr("value"));
	});
	$("li[operation='del']").on("click", function(){
		//alert("delete:"+$(this).attr("value"));
		allNavigationsOperatorDelete($(this).attr("value"));
	});
}
//提交创建导航栏
function submitCreateNavigation(){
	var id = $("#navigationId").val();
	var name = $("#navigationName").val();
	var level = $("#navigationLevel").val();
	var father = $("input[name='navigationFatherNavigations']:checked").val();
	var url = $("#navigationUrlValue").val();
	var tip = $("#navigationTip").val();
	var ico = $("#navigationIco").val();
	var style = $("#navigationStyle").val();
	var sort = $("#navigationSort").val();
	var target = $("input[name='navigationTargetBlank']:checked").val();
	var display = $("input[name='navigationDisplay']:checked").val();
	var boss = $("#navigationDepartmentId").val();
	var description = $("#navigationDescription").val();
	var jsonData;
	var result;
	if(level != 1)
		jsonData = {id:id,name:name, level:level, url:url, tip:tip, ico:ico, style:style, sort:sort, targetBlank:target, display:display,  description:description,boss:boss,fatherNavigationId:father};
	else
		jsonData = {id:id,name:name, level:level, url:url, tip:tip, ico:ico, style:style, sort:sort, targetBlank:target, display:display, description:description, boss:boss};
	//alert("名称:"+jsonData.name+"\n级别:"+jsonData.level+"\n链接地址:"+jsonData.url+"\n提示:"+jsonData.tip+"\n图标:"+jsonData.ico+"\n样式:"+jsonData.style+"\n排序:"+jsonData.sort+"\n打开链接方式:"+jsonData.targetBlank+"\n是否显示:"+jsonData.display+"\n备注:"+jsonData.description+"\n首页:"+jsonData.boss);
	 $.ajax({
			type: "GET",
			contentType:'application/json;charset=UTF-8',
			url: getBasePath()+"/manager/site/createNavigation.do",
			data:jsonData,
			dataType:"json",
			success:function(msg){
				var fatherNav = 0;
				var childNav = 0;
				if(!msg.fatherNavigation){
					fatherNav = 0;
				}else{
					fatherNav = msg.fatherNavigation.name;
				}
				if(!msg.childNavigations){
					childNav = 0;
				}else{
					childNav = msg.childNavigations.length;
				}
				//alert("ID:"+msg.id+"名称:"+msg.name+"\n级别:"+msg.level+"\n链接地址:"+msg.url+"\n提示:"+msg.tip+"\n图标:"+msg.ico+"\n样式:"+msg.style+"\n排序:"+msg.sort+"\n打开链接方式:"+msg.targetBlank+"\n是否显示:"+msg.display+"\n备注:"+msg.description+"\n首页:"+msg.boss+"\n父导航:"+fatherNav+"\n子导航:"+childNav);
				var tr = "<tr class=\"table-tr-content\"  id=\"tableTrContentNavId"+msg.id+"\"><td nowrap=\"nowrap\">"+msg.id
				+"</td><td nowrap=\"nowrap\">"+msg.name
				+"</td><td nowrap=\"nowrap\">"+msg.level
				+"</td><td nowrap=\"nowrap\">"+msg.url
//				+"</td><td nowrap=\"nowrap\">"+msg.tip
//				+"</td><td nowrap=\"nowrap\">"+msg.ico
//				+"</td><td nowrap=\"nowrap\">"+msg.style
				+"</td><td nowrap=\"nowrap\">"+msg.sort
				+"</td><td nowrap=\"nowrap\">"+msg.targetBlank
				+"</td><td nowrap=\"nowrap\">"+msg.display
				+"</td><td nowrap=\"nowrap\">"+fatherNav
				+"</td><td nowrap=\"nowrap\">"+childNav
				+"</td><td nowrap=\"nowrap\">"+msg.description
				+"</td><td nowrap=\"nowrap\"><li class=\"imgOperator\" operation=\"edit\" value=\""+msg.id+"\" title=\"编辑\">编辑</li><li>|</li>"
				+"<li class=\"imgOperator\" operation=\"del\" value=\""+msg.id+"\" title=\"删除\">删除</li>"
				+"</td></tr>";
				var id=$("#navigationId").val();
				if(!id){//创建导航，id==null;
					$("#allNavigationTableData").append(tr);
				}else{
					$("#tableTrContentNavId"+msg.id).remove();
					$("#allNavigationTableData").append(tr);
				}
				addFunctionForLiLink();
				result = ""+ msg.boss;//msg;
				//alert("success:result="+result);
			},
			error:function(){
				alert("error");
				result =  "N";
			}
	 });				
	 //alert("ajax over : result ="+result);
	 return result;
}
//加载一级导航
function loadFirstNavigations(){
	var id = $("#navigationDepartmentId").val();
	var jsonData = {departmentId:id};
	 $.ajax({
			type: "GET",
			contentType:'application/json;charset=UTF-8',
			url: getBasePath()+"/manager/site/allFirstNavigationsJson.do",
			data:jsonData,
			dataType:"json",
			success:function(msg){
				var data = "<label>上一级导航（必填）</label><br>";
				var navId = $("#navigationId").val();
				 $.each(msg,function(i,nav){
					 if(nav.id != navId){
						 data += "<input type=\"radio\" name=\"navigationFatherNavigations\" id=\"navigationFatherNavigations"+nav.id+"\" value=\""+nav.id+"\" width=\"100%\" class=\"text ui-widget-content ui-corner-all\">";
						 data += "<label for=\"navigationFatherNavigations"+nav.id+"\">"+nav.name+"</label>";
						 data += "<br>";
					 }
				 });
				 $("#divNavigationFatherNavigations").html(data);
			},
			error:function(){
				alert("error");
			}
	 });			
}



// TODO 子部门板块管理 增删改查 
/**
 * 准备工作。
 */
$(function() {
	
	//初始化按钮 快速添加栏目板块
	$("#allSectionKuaiSuAdd").button().click(function(){
		allSectionsOperatorCreate();
	});

	$("a[class='allSectionListTagAarticles']").on({
		mouseenter : function(e) {
			var id = $(this).attr("tip");
			var title = $("#allSectionListTagAarticles"+id).html();
			title = title.replace(/\s/gm,'');
			title = title.replace(/<br>/gm,'\r');
			$(this).attr({"title":title});
		} 
	});
	
	/**
	 * 为导航操作列添加单击事件
	 */
	operationLinkAddClickEvent();
	function operationLinkAddClickEvent(){
	$("li[operation='sectionEdit']").off("click").on("click",function(){
		//alert("edit:"+$(this).attr("value"));
		$("li[operation='sectionEdit']").css({"visibility":"hidden"});
		allSectionsOperatorEdit($(this).attr("value"));
		
	});
	$("li[operation='sectionDel']").off("click").on("click", function(){
		//alert("delete:"+$(this).attr("value"));
		allSectionsOperatorDelete($(this).attr("value"));
	});
	}
		//创建栏目板块。----开始
		function allSectionsOperatorCreate(){
			//为弹出对话框显示内容做好准备工作。
			var htmldata = "";
			htmldata += "栏目名称：<input id='sectionOperatorEditTempSname' type='text' value=''><br>";
			htmldata += "栏目备注：<input id='sectionOperatorEditTempSdesc' type='text' value=''>";
			//临时创建对话框关闭自动销毁，不留痕迹 ----对话框开始{
			$("<div id='sectionEditDialogId'></div>").dialog({
				autoOpen: true,
				buttons:{
					"添加":function(){
						var name = $("#sectionOperatorEditTempSname").val();
						var description = $("#sectionOperatorEditTempSdesc").val();
						//传输参数
						jsonData = {name:name, description:description, departmentId:$("#departmentPageDepartmentId").val()};
						//---提交 开始{
						$.ajax({
							type: "GET",
							contentType:'application/json;charset=UTF-8',
							url: getBasePath()+"/manager/site/sectionEdit.do",
							data:jsonData,
							dataType:"json",
							success:function(msg){
								//成功。。更新界面开始
								$("#allSectionTableData").append("<tr><td>"+msg.id+"</td><td>"+msg.name+"</td><td>"+0+"</td><td>"+msg.description+"</td><td>"+
										"<li class=\"imgOperator\" operation=\"sectionEdit\" value=\""+msg.id+"\"  title=\"编辑\">编辑</li><li>|</li>"+
										"<li class=\"imgOperator\" operation=\"sectionDel\" value=\""+msg.id+"\"   title=\"删除该栏目\">删除</li>"+
								"</td></tr>");
								operationLinkAddClickEvent();//更新操作列链接单击事件。
//								$("#allNavigationTableData").append(tr);
								//成功。。更新界面结束
							},
							error:function(){
								alert("allSectionsOperatorDelete error");
							}
						});	
						//}--提交结束
						$(this).dialog("destroy").remove();	
					},
					"取消":function(){
						$(this).dialog("destroy").remove();
					}
				},
				modal:true,
				title:"添加子栏目板块",
				draggable: true,
				resizable: true,
				width:300,
//				height:"auto",
//				position:['center','top'],
				open:function(event, ui){
					//data = "<input id='sectionEditNameId' value='"++"'>";
					$("#sectionEditDialogId").html(htmldata);
				},
				close:function(event, ui){
					$(this).dialog("destroy").remove();
				}			
			});
			//临时创建对话框关闭自动销毁，不留痕迹 ----对话框结束
		}
		//} 创建栏目板块。----结束
		
	//编辑栏目板块。开始
	function allSectionsOperatorEdit(secId){
		//为弹出对话框显示内容做好准备工作。
		var htmldata = "";
		 
		var jsonData = {sectionId:secId};
		 $.ajax({
				type: "GET",
				contentType:'application/json;charset=UTF-8',
				url: getBasePath()+"/manager/site/getSectionById.do",
				data:jsonData,
				dataType:"json",
				success:function(msg){
					htmldata += "<input id='sectionOperatorEditTempSid' type='hidden' value='"+msg.id+"'>";
					htmldata += "栏目名称：<input id='sectionOperatorEditTempSname' type='text' value='"+msg.name+"'><br>";
					htmldata += "栏目简介：<input id='sectionOperatorEditTempSdesc' type='text' value='"+msg.description+"'>";
					//临时创建对话框关闭自动销毁，不留痕迹
					
					$("<div id='sectionEditDialogId'></div>").dialog({
						autoOpen: true,
						buttons:{
							"修改":function(){
								var id = $("#sectionOperatorEditTempSid").val();
								var name = $("#sectionOperatorEditTempSname").val();
								var description = $("#sectionOperatorEditTempSdesc").val();
								//传输参数
								jsonData = {id:id, name:name, description:description, departmentId:$("#departmentPageDepartmentId").val()};
								//---提交 开始
								 $.ajax({
										type: "GET",
										contentType:'application/json;charset=UTF-8',
										url: getBasePath()+"/manager/site/sectionEdit.do",
										data:jsonData,
										dataType:"json",
										success:function(msg){
											//成功。。更新界面开始
											$("#tableTrContentId"+secId).find("td:eq(1)").text(msg.name);
											$("#tableTrContentId"+secId).find("td:eq(3)").text(msg.description);
											//成功。。更新界面结束
										},
										error:function(){
											 alert("allSectionsOperatorDelete error");
										}
								 });	
								 //--提交结束
								 $("li[operation='sectionEdit']").css({"visibility":"visible"});
								$(this).dialog("destroy").remove();	
							},
							"取消":function(){
								$("li[operation='sectionEdit']").css({"visibility":"visible"});
								$(this).dialog("destroy").remove();
							}
						},
						modal:true,
						title:"添加子栏目板块",
						draggable: true,
						resizable: true,
						width:300,
//						height:"auto",
//						position:['center','top'],
						open:function(event, ui){
							//data = "<input id='sectionEditNameId' value='"++"'>";
							$("#sectionEditDialogId").html(htmldata);
						},
						close:function(event, ui){
							$("li[operation='sectionEdit']").css({"visibility":"visible"});
							$(this).dialog("destroy").remove();
						}			
					});

				},
				error:function(){
					 alert("添加重复项，请另取一个名字");
				}
		 });		
		 
	}
	//编辑栏目板块。结束
	
	//删除栏目板块  开始
	function allSectionsOperatorDelete(secId){
		//var url =  getBasePath() + "/manager/site/articleDelete.do?articleId=" + id;
		if(confirm("删除该栏目，会将该栏目下的文章一并删除，您确定要这样做？")){
			var jsonData = {sectionId:secId};
			 $.ajax({
					type: "GET",
					contentType:'application/json;charset=UTF-8',
					url: getBasePath()+"/manager/site/sectionDelete.do",
					data:jsonData,
					dataType:"json",
					success:function(msg){
						  $("#tableTrContentId"+secId).remove();
						  alert("你够狠的！该栏目下的文章已经全部删除！");
					},
					error:function(){
						 alert("allSectionsOperatorDelete error");
					}
			 });		
		}else{
			alert("你要把文章转移到其他栏目下，或者对栏目改个名字。");
			return;
		}
			
	}
	//删除栏目板块  结束
});