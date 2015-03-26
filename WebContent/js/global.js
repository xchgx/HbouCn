/**
 * 获取项目名称
 * @returns {String}
 */
function getBasePath(){
	var location = (window.location+'').split('/');
	//var basePath = location[0]+'//'+location[2]+'/'+location[3];
	var basePath ='/'+location[3];
	//alert("/js/"+basePath);
	return basePath;
}
/**
 * 返回加载中动画
 * @returns {String}
 */
function getLoadingGifPath(){
	return "<img src='"+getBasePath()+"/img/loading.gif' width='200' height='200'>";
}
