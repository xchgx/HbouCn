function getObjectList(url){
	$("#systemManageContent").html(getLoadingGifPath());
	$("#systemManageContent").load(url,function(){
		//给表格着色
		tableColor();
	});
}
$(function() {
	$.ajaxSetup({cache:false});//开启缓存，防止js的随机数
	var icons = {
			header : "ui-icon-circle-arrow-e",
			activeHeader : "ui-icon-circle-arrow-s"
		};
	//加载部门列表
	loadAllDepartments();
	
	//菜单栏可折叠
	$("#sysManaPaneMenuAccordion").accordion({
		heightStyle : "content",
		icons : icons
	});
	
});
//菜单可点击
function addClickEventToLiTag(){
	$(".menuElement").on({
		click:function(){
			getObjectList(buildsiteManaUrl($(this).attr("href")) + "?departmentId="+$(this).attr("dptId"));
		}
	});
}
//左侧折叠菜单加载部门列表
function loadAllDepartments(){
	$.ajax({
		type:"GET",
		contentType:'application/json;charset=UTF-8',
		url: getBasePath()+"/manager/site/allManageDepartmentsJson.do",
		dataType:"json",
		success:function(msg){
			var htm = "";
			$.each(msg, function(i, dpt){
				htm += "<div class=\"ui-state-default menuElement\" href=\"departmentPage\" dptId=\""+dpt.id+"\">"+dpt.name+"</div>";
			});
			$("#sysManaMainDepartmentOption").html(htm);
			addClickEventToLiTag();
		},
		error:function(){
			alert("main.js loadAllDepartments error");
		}
	});
}