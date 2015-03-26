function getObjectList(url){
	$("#systemManageContent").html(getLoadingGifPath());
	$("#systemManageContent").load(url,function(){
		//给表格着色
		tableColor();
	});
}
$(function() {
	var icons = {
			header : "ui-icon-circle-arrow-e",
			activeHeader : "ui-icon-circle-arrow-s"
		};
	//菜单栏可折叠
	$("#sysManaPaneMenuAccordion").accordion({
		heightStyle : "content",
		icons : icons
	});
	//菜单可点击
	$(".menuElement").on({
		click:function(){
			getObjectList(buildUrl($(this).attr("href")));
		}
	});
});