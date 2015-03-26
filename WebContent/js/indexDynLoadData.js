$(function() {
	initNotice();//加载公告通知
	initNoticeList();//加载公告通知列表（3个）
	initHuanDengPian();//加载幻灯片
	initJW_XG();//加载教务学工新闻
	initXBdongtai();//加载校办发布的新闻动态。
//	initKeywordsInHboucnSite();//加载自主查询，搜索站内文章
//	initLinkMap();//快速通道
	/**
	 * 其他数据抓取已经使用FreeMarker自动生成。
	 * 
	 */
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
