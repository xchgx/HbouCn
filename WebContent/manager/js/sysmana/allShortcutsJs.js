/**
 * allShortcuts.jsp
 */
function allShortcutsOperatorCreate(){
	var url = getBasePath()+"/manager/sys/shortcutCreateView.do";
	$("#systemManageContent").html(getLoadingGifPath());
	$("#systemManageContent").load(url);
}
function allShortcutsOperatorEdit(id){
	var url = getBasePath()+"/manager/sys/shortcutEditView.do?shortcutId="+id;
	$("#systemManageContent").html(getLoadingGifPath());
	$("#systemManageContent").load(url);
}
function allShortcutsOperatorDelete(id){
	if(confirm('确实要删除该内容吗?')){
	var url =  getBasePath()+"/manager/sys/shortcutDelete.do?shortcutId="+id;
	$("#systemManageContent").html(getLoadingGifPath());
	$("#systemManageContent").load(url);
	}
}
$(function() {
	//拥有我这个图标的有哪些权限
	$("a[class='allShortcutListTagApermissions']").on({
		mouseenter : function(e) {
			var id = $(this).attr("tip");
			var title = $("#allShortcutListTagApermissions"+id).html();
			title = title.replace(/\s/gm,'');
			title = title.replace(/<br>/gm,'\r');
			$(this).attr({"title":title});
		} 
	});
});