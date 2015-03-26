/**
 * allTeachers.jsp
 */
function allTeachersOperatorCreate(){
	var url = getBasePath()+"/manager/sys/teacherCreateView.do";
	$("#systemManageContent").html(getLoadingGifPath());
	$("#systemManageContent").load(url);
}
function allTeachersOperatorEdit(id){
	var url = getBasePath()+"/manager/sys/teacherEditView.do?teacherId="+id;
	$("#systemManageContent").html(getLoadingGifPath());
	$("#systemManageContent").load(url);
}
function allTeachersOperatorDelete(id){
	if(confirm('删除这名老师，将会连带的删除这名老师发表的所有文章。确实要删除吗?')){
	var url =  getBasePath()+"/manager/sys/teacherDelete.do?teacherId="+id;
	$("#systemManageContent").html(getLoadingGifPath());
	$("#systemManageContent").load(url);
	}
}
$(function() {
	//归属哪个部门管辖
	$("a[class='allTeacherListTagAdepartments']").on({
		mouseenter : function(e) {
			var id = $(this).attr("tip");
			var title = $("#allTeacherListTagAdepartments"+id).html();
			title = title.replace(/\s/gm,'');
			title = title.replace(/<br>/gm,'\r');
			$(this).attr({"title":title});
		} 
	});
	//管理着下面的部门
	$("a[class='allTeacherListTagAmanagerDepartments']").on({
		mouseenter : function(e) {
			var id = $(this).attr("tip");
			var title = $("#allTeacherListTagAmanagerDepartments"+id).html();
			title = title.replace(/\s/gm,'');
			title = title.replace(/<br>/gm,'\r');
			$(this).attr({"title":title});
		} 
	});
	//发表过的文章
	$("a[class='allTeacherListTagAarticles']").on({
		mouseenter : function(e) {
			var id = $(this).attr("tip");
			var title = $("#allTeacherListTagAarticles"+id).html();
			title = title.replace(/\s/gm,'');
			title = title.replace(/<br>/gm,'\r');
			$(this).attr({"title":title});
		} 
	});
	//有发表权限的部门。
	$("a[class='allTeacherListTagAarticleDepartments']").on({
		mouseenter : function(e) {
			var id = $(this).attr("tip");
			var title = $("#allTeacherListTagAarticleDepartments"+id).html();
			title = title.replace(/\s/gm,'');
			title = title.replace(/<br>/gm,'\r');
			$(this).attr({"title":title});
		} 
	});
});