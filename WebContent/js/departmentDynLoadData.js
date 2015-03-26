$(function() {
	initNotice();//加载公告通知
	initNoticeList();//加载公告通知列表（3个） 
	
	var departmentId = $("#departmentIndexDptId").val();
	//initSectionListByDepartmentId(departmentId, "content_r2c1_sectionList");//左侧下方显示部门下的板块列表
	
	//initWebPathEtc(departmentId, "content_r1c2_webPath");//加载web路径
	
	//加载文章列表
	//initDepartmentNewestArticle(departmentId,"content_r2c2_articleList");
	initDepartmentNameByTopPage(departmentId);
	
	$( document ).tooltip({
		items: "[title]",
		content: function() {
			var element = $( this );
			if ( element.is( "[title]" ) ) {
				return element.attr( "title" );
			}
		}
	});
});
 