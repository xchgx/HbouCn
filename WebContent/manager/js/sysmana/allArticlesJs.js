/**
 * allArticles.jsp
 */
function allArticlesOperatorCreate(){
//	//var url = getBasePath()+"/manager/sys/articleCreateView.do";
//	var url = getBasePath()+"/manager/news/entranceView.do";
//	
////	$("#systemManageContent").html(getLoadingGifPath());
////	$("#systemManageContent").load(url);
//	
//	location.href=url;
	var url = getBasePath()+"/manager/sys/articleEditView.do";

	window.parent.Core.createNewWindow(1000,"编辑文章",url,700,650);
}
function allArticlesOperatorEdit(id){

	var url = getBasePath()+"/manager/sys/articleEditView.do?articleId="+id;

	window.parent.Core.createNewWindow(100+id,"编辑文章",url,700,650);
//	location.href=url;
//	$("#systemManageContent").html(getLoadingGifPath());
//	$("#systemManageContent").load(url);
	//创建个人信息固定窗口
	//Core.createPersonInfoWindow(110,"文章修改",url,500,800);
//	opt = {num:110, title:"文章修改",url:url,width:"500",height:"800",resize:true};
//	Core.create(""+int_windowNum,opt);
	//Core.createPersonInfoWindow = function(int_windowNum,t,www,w,h){
}
function allArticlesOperatorDelete(id){
	if(confirm('确实要删除该内容吗?')){
	var url =  getBasePath()+"/manager/sys/articleDelete.do?articleId="+id;
	$("#systemManageContent").html(getLoadingGifPath());
	$("#systemManageContent").load(url);
	}
}
function jumpPage(index){
	var url =  getBasePath()+"/manager/sys/allArticlesPage.do?index="+index;
	$("#systemManageContent").html(getLoadingGifPath());
	$("#systemManageContent").load(url);
}
function inputJumpPage(currentPageNo){
	var i = $("#allArticlePageInputValue option:selected").val();
	if(i == currentPageNo){
		return;
	}
	jumpPage(i);
}
