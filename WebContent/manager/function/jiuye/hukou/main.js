/**
 * 
 */
$(function() {
	var icons = {
		header : "ui-icon-circle-arrow-e",
		activeHeader : "ui-icon-circle-arrow-s"
	};
	// 菜单栏可折叠
	$("#jiuye_hukouPaneMenuAccordion").accordion({
		heightStyle : "content",
		icons : icons
	});
	// 菜单可点击
	$(".menuElement").on({
		click : function() {
			getObjectList(buildUrl($(this).attr("href")));
		}
	});

	/**
	 * 构建Url地址
	 */
	function buildUrl(url) {
		var u = getBasePath() + "/manager/sys/" + url + ".do";
		return u;
	}
	
	/**
	 * 返回结果
	 */
	function getObjectList(url){
		$("#systemManageContent").html(getLoadingGifPath());
		$("#systemManageContent").load(url,function(){
		});
	}
});