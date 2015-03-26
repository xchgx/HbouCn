function getBasePath(){
	var location = (window.location+'').split('/');
	//var basePath = location[0]+'//'+location[2]+'/'+location[3];
	var basePath ='/'+location[3];
	//alert("/manager/js/sysmana/"+basePath);
	return basePath;
}
function getLoadingGifPath(){
	return "<img src='"+getBasePath()+"/img/loading.gif' width='200' height='200'>";
}
function getLoadingGif(hw){
	return "<img src='"+getBasePath()+"/img/loading.gif' width='"+hw+"' height='"+hw+"'>";
}
function buildUrl(url) {
	var u = getBasePath()+"/manager/sys/" + url + ".do";
return u;
}
function buildsiteManaUrl(url) {
	var u = getBasePath()+"/manager/site/" + url + ".do";
return u;
}
function parentWindowClose(){
	var obj = this.parent.window.document.getElementById("window_2_warp");
	//alert($(obj).attr("window"));
	var w = $(obj).attr("window");
	$('.task-window li[window="'+w+'"]', this.parent.window.document).remove();
	$(obj).remove();
}
function tableColor(){
	   var dtSelector = ".list";
	    var tbList = $(dtSelector);

	    tbList.each(function() {
	        var self = this;
	        $("tr:even:not(:first)", $(self)).addClass("normalEvenTD"); // 从标头行下一行开始的奇数行，行数：（1，3，5...）
	        $("tr:odd", $(self)).addClass("normalOddTD"); // 从标头行下一行开始的偶数行，行数：（2，4，6...）

	        // 鼠标经过的行变色
	        
			$("tr:not(:first)", $(self)).hover(function() {
				$(this).addClass('hoverTD');
				$(this).removeClass('table-tr-content');
			}, function() {
				$(this).removeClass('hoverTD');
				$(this).addClass('table-tr-content');
			});

			// 选择行变色
			$("tr", $(self)).click(function() {
				var trThis = this;
				$(self).find(".trSelected").removeClass('trSelected');
				if ($(trThis).get(0) == $("tr:first", $(self)).get(0)) {
					return;
				}
				$(trThis).addClass('trSelected');
			});
		});
}