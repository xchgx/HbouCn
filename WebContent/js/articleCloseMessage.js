/**
 * 
 */
function articleCloseMessage(artid) {
	// str='\u6587\u7ae0\u6dfb\u52a0\u6210\u529f\uff0c\u662f\u5426\u7ee7\u7eed\u6dfb\u52a0\u6587\u7ae0\uff1f';
//	var str = "文章添加成功，是否继续添加文章？";
	if (confirm("文章添加成功，是否继续添加文章？")) {
		history.go(-1);
	} else {
		var obj = this.parent.window.document
				.getElementById("window_"+artId+"_warp");
		var w = $(obj).attr('window');
		$(".task-window li[window='" + w + "']", this.parent.window.document).remove();
		$(obj).remove();
	}
}