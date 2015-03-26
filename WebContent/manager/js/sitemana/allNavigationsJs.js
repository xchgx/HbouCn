/**
 * allNavigations.jsp
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
 * 为操作列添加单击事件
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
