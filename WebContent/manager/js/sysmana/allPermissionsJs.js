/**
 * allPermissions.jsp
 */
function allPermissionsOperatorCreate(){
	var url = getBasePath()+"/manager/sys/permissionCreateView.do";
	$("#systemManageContent").html(getLoadingGifPath());
	$("#systemManageContent").load(url);
}
function allPermissionsOperatorEdit(id){
	var url = getBasePath()+"/manager/sys/permissionEditView.do?permissionId="+id;
	$("#systemManageContent").html(getLoadingGifPath());
	$("#systemManageContent").load(url);
}
function allPermissionsOperatorDelete(id){
	if(confirm('确实要删除该内容吗?')){
	var url =  getBasePath()+"/manager/sys/permissionDelete.do?permissionId="+id;
	$("#systemManageContent").html(getLoadingGifPath());
	$("#systemManageContent").load(url);
	}
}
$(function() {
	//所有授权图标
	$("a[class='allPermissionListTagAshortcuts']").on({
		mouseenter : function(e) {
			var id = $(this).attr("tip");
			var title = $("#allPermissionListTagAshortcuts"+id).html();
			title = title.replace(/\s/gm,'');
			title = title.replace(/<br>/gm,'\r');
			$(this).attr({"title":title});
		} 
	});
});