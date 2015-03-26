/**
 * 废弃该方法，
 * //area是选择数据在那个位置显示，这里需要标签的ID属性值。
//ID值为部门数据的唯一ID号，在部门管理中可以查看,
//size：从后台获取几条新闻或文章，这里是数量，根据页面大小自由调整
//width：显示内容的宽度
 */
function loadDynArticleListByDepartmentId(tagid, id){
	$("#"+tagid).load(getBasePath()+"/index/articleList/preview/previewDepartmentArticleList"+id+".jsp");
}
function loadDynArticleList(tagid, id, size, width, page){
	//Integer pageNo, Integer pageSize, Integer departmentId
	if(!width){
		width=240;
	}
	if(!page){
		page = 1;
	}
	////alert(arguments[0]+","+arguments[1]+","+arguments[2]+","+arguments[3]+","+arguments[4]+","+width+","+page);
	var jsonData = {pageNo:page, pageSize:size, departmentId:id};
	var dptorsec = getDepartmentOrSection();
	////alert(getBasePath()+"/article/getPageJsonArticlesBy"+dptorsec+"Id.do"+jsonData.pageNo+","+jsonData.pageSize+","+jsonData.departmentId);
	 $.ajax({
		type: "GET",
		contentType:'application/json;charset=UTF-8',
		url: getBasePath()+"/article/getPageJsonArticlesBy"+dptorsec+"Id.do",
		data:jsonData,
		dataType:"json",
		success:function(msg){
				 $("#"+tagid).html(getHtmlByArticleList(msg.result, width));
		},
		error:function(){
			 //alert("error");
		}
	});
}
/**
 * 从Page对象的List中遍历article，拼装成文章列表。
 * @param list
 * @param width
 * @returns html
 */
function getHtmlByArticleList(list, width){
	 var artList = "<br>";
	 var fontsize = 16;
	 $.each(list, function(i, value){
		 var tt = value.title;
			if((tt.length*fontsize) >=(width-30)){
				////alert("tt.length:"+tt.length+",width:"+width+"length*9="+tt.length*9+",width-30="+width-30);
				tt = tt.substring(0,((width-30)/fontsize))+"...";
			}
			var url = getBasePath()+"/navigation/article/"+value.id+".do";
			artList+="<li style='display:block;text-align:left;width:"+(width-10)+"px;'><span style='float:right;text-align:right;font-size:14px;width:25%;'>"+value.date+"</span><a href='"+url+"' target='_blank' title='"+value.title+"'  style='float:left; font-size:12px;text-align:left;width:75%;'>"+tt+"</a></li>";
	 });
	 return artList;
}
/**
 * 加载公告通知
 */
function initNotice(){
	$.ajax({
		type: "GET",
		contentType:'application/json;charset=UTF-8',
		url: getBasePath()+"/article/notice.do",
		dataType:"json",
		success: function(msg){
			$("#content_gonggao_articlecontent").html(msg.content);
		},
		error:function(){
		}
	});
}
/**
 * 加载公告通知列表（3个）
 */
function initNoticeList(){
	//content_gonggao_articlelist
	$.ajax({
		type: "GET",
		contentType:'application/json;charset=UTF-8',
		url: getBasePath()+"/article/noticeNewest.do",																																																			
		dataType:"json",
		success: function(msg){
			var data = "<table border=\"0\"   style=\"margin : 0; padding: 0;width:100%;\" >";
			$.each(msg,function(index,value){

	             //其中index相当于JAVA中的LIST中的索引，VALUE为索引对应的值，其中索引从0开始
				var tt = value.title;
				if(value.title.length >=10){
					tt = tt.substring(0,10)+"...";
				}
				var nr = value.content;
				nr = nr.replace(/\s+/g," ");
				nr = nr.replace(/<(\w*)>|<\/(\w*)>|<(\w*)(\s*)\/>/g,"");
//				var url = getBasePath()+"/navigation/article/"+value.id+".do";
//				data += "<li style='display:block;text-align:left;width:230px;'><span style='float:right;text-align:left;font-size:12px;width: 25%;'>"+value.date+"</span><a href='"+url+"' target='_blank' title='"+value.title+"("+nr+")"+"' style='font-size:12px;text-align:left;width: 75%;'>"+tt+"</a></li>";
				data += "<tr><td style=\"padding: 0px;margin: 0px\" align=\"left\">";
				if(value.titleStyle == "2"){
					data += "<a href='"+getBasePath()+"/index/article/art"+value.id+".jsp' title='"+value.title+"("+nr+")'  style='font-size:12px;color:red;'>";
				}else if(value.titleStyle == "3"){
					data +="<a href='"+getBasePath()+"/index/article/art"+value.id+".jsp' title='"+value.title+"("+nr+")'   style='font-size:12px;font-style: italic;'>";
				}else{
					data += "<a href='"+getBasePath()+"/index/article/art"+value.id+".jsp' title='"+value.title+"("+nr+")'   style='font-size:12px;'>";
				}
				data +=""+tt;
				data += "</a></td><td   style='width:100px;margin: 0px; padding: 0px; font-size:12px;' align='right'>"+value.date+"</td></tr>";
				////alert(index+"   "+value);
	      });
	      data += "</table>";
	        	//data += "<li><a href='#'>"+dataObj[i].title+"</a></li>";
			$("#content_gonggao_articlelist").html(data);
		},
		error:function(){
			////alert("error") ;
		}
	});
}
//加载幻灯片
function initHuanDengPian(){
	$('.bxslider').bxSlider({
		auto: true,
		mode: 'fade'
	});//加载幻灯片
}
//加载教务学工新闻
function initJW_XG(){
	//cg_clas_content_r1c3_title标题反色
	$(".cg_clas_content_r1c3_title[hrefid=1]").addClass("cg_clas_content_r1c3_title_hove");
	//cg_clas_content_r1c3_hrefid
	$(".cg_clas_content_r1c3_hrefid").css({display:"none"});
	$("#cg_id_content_r1c3_hrefid1").css({display:"block"});

	$(".cg_clas_content_r1c3_title").on({
		mouseenter : function() {
			$(".cg_clas_content_r1c3_title").removeClass("cg_clas_content_r1c3_title_hove");
			$(this).addClass("cg_clas_content_r1c3_title_hove");

			$(".cg_clas_content_r1c3_hrefid").css({display:"none"});
			var id = $(this).attr("hrefid");
			$("#cg_id_content_r1c3_hrefid"+id).css({display:"block"});
			var text = "更多 "+$(this).children().text();
			$("#cg_id_content_r1c3_a").attr({"href":"http://www.baidu.com?s="+id, "title":text});
		}
	});
	//加载教务处动态新闻,
	//tagid是选择数据在那个位置显示，这里需要标签的ID属性值。
	//ID值为部门数据的唯一ID号，在部门管理中可以查看,
	//size：从后台获取几条新闻或文章，这里是数量，根据页面大小自由调整
	//加载教务处动态新闻,教务处的ID是10；
//	loadDynArticleList("cg_id_content_r1c3_hrefid1", 10, 10);
	//学工处的ID是
//	loadDynArticleList("cg_id_content_r1c3_hrefid2", 11, 10);
	loadDynArticleListByDepartmentId("cg_id_content_r1c3_hrefid1", 10);
	loadDynArticleListByDepartmentId("cg_id_content_r1c3_hrefid2", 11);
}
//加载校办发布的新闻动态
function initXBdongtai(){
	//loadDynArticleList("content_r2c2dongtai_description", 7, 10, 360,1);
	loadDynArticleListByDepartmentId("content_r2c2dongtai_description", 7);
}
//加载自主查询，搜索站内文章
function initKeywordsInHboucnSite(){
	$("#keywordsInHboucnSiteButton").button().click(function(){
		//alert($("#keywordsInHboucnSite").val());
	});
}
/**
 * 加载友情链接导航地图
 */
function initLinkMap(){
//	 $.ajax({
//			type: "GET",
//			contentType:'application/json;charset=UTF-8',
//			url: getBasePath()+"/article/getPageJsonArticlesByDepartmentId.do",
//			data:jsonData,
//			dataType:"json",
//			success:function(msg){
//				 ////alert(msg.totalCount);
//				 var artList = "<br>";
//				 $.each(msg.result, function(i, value){
//					 //artList+= art.id+","+art.title;
//					 var tt = value.title;
//						if(tt.length >=10){
//							tt = tt.substring(0,10)+"...";
//						}
//					 artList+="<li style='display:block;text-align:left;width:"+(width-10)+"px;'><span style='float:right;text-align:right;font-size:12px;width: 25%;'>"+value.date+"</span><a href='#' title='"+value.title+"'  style='float:left; font-size:12px;text-align:left;width: 75%;'>"+tt+"</a></li>";
//				 });
//				 $("#"+tagid).html(artList);
//			},
//			error:function(){
//				 //alert("error");
//			}
//		});
}
/**
 * 进入部门页后，左侧下方的显示子部门板块列表。
 * departmentId: 部门ID
 * tagid: 操作的标签对象，通常是div
 */
function initSectionListByDepartmentId(departmentId, tagid){
	var jsonData = {departmentId:departmentId};
//	 $.ajax({
//			type: "GET",
//			contentType:'application/json;charset=UTF-8',
//			url: getBasePath()+"/section/getSectionsByDepartmentId.do",
//			data:jsonData,
//			dataType:"json",
//			success:function(msg){
//				var htm = "";
//				 $.each(msg, function(i, value){
//					 var url = getBasePath()+"/navigation/section/"+value.id+".do";
//					 htm += "<li class='ajaxJsonSectionListByDepartment'><a href='"+url+"' >"+value.name+"</a></li>";
//				 });
//				 $("#"+tagid).html(htm);
//			},
//			error:function(){
//				 //alert("error");
//			}
//		});
	
}
/**
 * 上面显示该文章或部门的网络路径。
 * departmentId: 部门ID
 * tagid: 操作的标签对象，通常是div
 */
function initWebPathEtc(departmentId, tagid){
	var jsonData = {departmentId:departmentId };
	var dptORsec = getDepartmentOrSection();
	
	 $.ajax({
			type: "GET",
			contentType:'application/json;charset=UTF-8',
			url: getBasePath()+"/getWebPathBy"+dptORsec+".do",
			data:jsonData,
			dataType:"json",
			success:function(msg){
				var htm = "";
				$.each(msg, function(i,value){
					htm += value;
				});
				 $("#"+tagid).html(htm);
			},
			error:function(){
				 //alert("上面显示该文章或部门的网络路径，出现错误！");
			}
		});
}
/**
 * 从网址URL中获取当前页面是部门页还是子部门板块页。返回department,section,article
 */
function getDepartmentOrSection(){
	var location = (window.location+'').split('/');
	//var basePath = location[0]+'//'+location[2]+'/'+location[3];
	////alert("location[5] : "+location[5]);
	if(!location[5])  return "Department";
	else return location[5].substring(0,1).toUpperCase()+location[5].substring(1);
	
}
/**
 * 加载部门右侧的文章列表
 * @param departmentId 
 * @param tagid 
 */
function initDepartmentNewestArticle(departmentId, tagid,width){
	var jsonData = {departmentId:departmentId };
	var dptORsec = getDepartmentOrSection();
	if(!width){
		width = 240;
	}
	 $.ajax({
			type: "GET",
			contentType:'application/json;charset=UTF-8',
			url: getBasePath()+"/getPageJsonArticlesBy"+dptORsec+"Id.do",
			data:jsonData,
			dataType:"json",
			success:function(msg){
				var htm = getHtmlByArticleList(msg.result, width);
				 $("#"+tagid).html(htm);
			},
			error:function(){
//				 //alert("上面显示该文章或部门的网络路径，出现错误！");
			}
		});
}
/**
 * 动态加载Head的部门名称！！！
 * @param dptId
 */
function initDepartmentNameByTopPage(dptId){
	im = "<img src='"+getBasePath()+"/img/nav2/"+dptId+".png' alt=''>";
	$("#indexTopPageImgId").html(im);
}
/**
 * 栏目文章列表上一页
 * @param dptId
 */
function sectionArticlesPageUp(url){
	$("#contentArticleListId").load(url);
}
/**
 * 栏目文章列表下一页
 * @param dptId
 */
function sectionArticlesPageDown(url){
	$("#contentArticleListId").load(url);
}

/**
 * 启动时加载的第一个页面，也就是第一页
 * @param dptId
 */
function initLoadFirstPage(dptId){
	//因为默认加载的时候就已经加载了第一个页面，所以，这里可以不需要了。
}





