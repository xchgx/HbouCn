/**
 * allDepartments.jsp
 */
function allDepartmentsOperatorCreate(){
	var url = getBasePath()+"/manager/sys/departmentCreateView.do";
	$("#systemManageContent").html(getLoadingGifPath());
	$("#systemManageContent").load(url);
}
function allDepartmentsOperatorEdit(id){
	var url = getBasePath()+"/manager/sys/departmentEditView.do?departmentId="+id;
	$("#systemManageContent").load(url);
}
function allDepartmentsOperatorDelete(id){
	if(confirm('确实要删除该内容吗?')){
	var url =  getBasePath()+"/manager/sys/departmentDelete.do?departmentId="+id;
	$("#systemManageContent").load(url);
	}
}
$(function() {
	//超链接显示下属成员
	$("a[class='allDepartmentListTagATeacher']").on({
		mouseenter : function(e) {
			var id = $(this).attr("tip");
			var title = $("#allDepartmentListTagATeacher"+id).html();
			title = title.replace(/\s/gm,'');
			title = title.replace(/<br>/gm,'\r');
			$(this).attr({"title":title});
		} 
	});
	//显示由权限发表文章的人员。
	$("a[class='allDepartmentListTagAarticleTeachers']").on({
		mouseenter : function(e) {
			var id = $(this).attr("tip");
			var title = $("#allDepartmentListTagAarticleTeachers"+id).html();
			title = title.replace(/\s/gm,'');
			title = title.replace(/<br>/gm,'\r');
			$(this).attr({"title":title});
		} 
	});
	//部门下的子部门
	$("a[class='allDepartmentListTagAchildDepartments']").on({
		mouseenter : function(e) {
			var id = $(this).attr("tip");
			var title = $("#allDepartmentListTagAchildDepartments"+id).html();
			title = title.replace(/\s/gm,'');
			title = title.replace(/<br>/gm,'\r');
			$(this).attr({"title":title});
		} 
	});
	//部门下存放的所有文章
	$("a[class='allDepartmentListTagAarticles']").on({
		mouseenter : function(e) {
			var id = $(this).attr("tip");
			var title = $("#allDepartmentListTagAarticles"+id).html();
			title = title.replace(/\s/gm,'');
			title = title.replace(/<br>/gm,'\r');
			$(this).attr({"title":title});
		} 
	});
	//部门下划分的板块。
	$("a[class='allDepartmentListTagAsections']").on({
		mouseenter : function(e) {
			var id = $(this).attr("tip");
			var title = $("#allDepartmentListTagAsections"+id).html();
			title = title.replace(/\s/gm,'');
			title = title.replace(/<br>/gm,'\r');
			$(this).attr({"title":title});
		} 
	});
});