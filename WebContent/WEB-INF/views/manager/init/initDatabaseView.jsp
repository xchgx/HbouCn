<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <script>
    function initDb(){
		location.href="<%=request.getContextPath()%>/initDb.do";
    }
    function initRelationDb(){
		location.href="<%=request.getContextPath()%>/initRelationDb.do";
    }
    function initClearDb(){
		location.href="<%=request.getContextPath()%>/initClearDb.do";
    }
    </script>
<div>
<button onclick="initClearDb();">清空数据库</button>
<button onclick="initDb();">初始化数据库</button>
<button onclick="initRelationDb();">初始化关系数据库</button>
</div>
<div id="initDbResult">${result }</div>
