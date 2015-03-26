/**
 * allUsers.jsp
 */
function allUsersOperatorCreate(){
	var url = getBasePath()+"/manager/sys/userCreateView.do";
	$("#systemManageContent").load(url);
}
function allUsersOperatorEdit(id){
	var url = getBasePath()+"/manager/sys/userEditView.do?userId="+id;
	$("#systemManageContent").load(url);
}
function allUsersOperatorDelete(id){
	if(confirm('确实要删除该内容吗?')){
		var url = getBasePath()+"/manager/sys/userDelete.do?userId="+id;
		$("#systemManageContent").load(url);		
	}
	
}