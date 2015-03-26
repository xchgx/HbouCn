<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<button id="btn_htmlSiteHtml">点击生成整个网站静态文件</button>
<div id="htmlSiteHtmlStatusWindow" title="整站静态化" style="display: none;">
<div class="progress-htmlSite-label">准备中...</div>
<div id="d2hsw_progress-htmlSitebar"></div>
<div class="progress-htmlSite-label2">准备中...</div>
</div>

<script type="text/javascript" src="<%=request.getContextPath()%>/manager/js/sitemana/htmlSitePageJs.js"></script>