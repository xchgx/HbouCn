<%@page import="com.xchgx.zsbwork.bean.Department"%>
<%@page import="java.util.List"%>
<%@page import="com.xchgx.domain.Article"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="com.xchgx.domain.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%
// 	response.setHeader("Pragma", "No-cache");
// 	response.setHeader("Cache-Control", "no-cache");
// 	response.setDateHeader("Expires", 0);
	User user = (User) session.getAttribute("USER_CONTEXT");
	Article article =(Article) request.getAttribute("article");
	if(article == null){
		article = new Article();
	}
	if(article.getContent() == null){
		article.setContent("");
	}
	if (user == null) {
		response.sendRedirect(request.getContextPath());
	}

%>
<%
	//=request.getContextPath()
%>
<link rel="stylesheet" href="<%=request.getContextPath()%>/kindeditor/themes/default/default.css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/kindeditor/plugins/code/prettify.css" />
<script charset="utf-8" src="<%=request.getContextPath()%>/kindeditor/kindeditor.js"></script>
<script charset="utf-8" src="<%=request.getContextPath()%>/kindeditor/lang/zh_CN.js"></script>
<script charset="utf-8" src="<%=request.getContextPath()%>/kindeditor/plugins/code/prettify.js"></script>
<link rel="stylesheet" href="<%=request.getContextPath()%>/jquery-ui-1.11.1/jquery-ui.css" />

<style type="text/css">
* {
	font-size: 12px;
	font-style: normal;
	font-family: sans-serif;
}

table {
	margin: 0px;
	padding: 0px;
	background-color: #EFEFFF;
}
td {
	margin: 0px;
	padding: 0px;
}
tr{
margin-bottom: 10px;
padding: 0px;
}
.releaseTable {
	 
}

.tdtitle {
	width: 100px;
	font-size: 14px;
}
 .ui-autocomplete {
max-height: 100px;
overflow-y: auto;
/* prevent horizontal scrollbar */
overflow-x: hidden;
}
/* IE 6 doesn't support max-height
* we use height instead, but this forces the menu to always be this tall
*/
* html .ui-autocomplete {
height: 100px;
}
</style>
<div>
	<table border="0" cellpadding="0" cellspacing="0">
		<tr>
			<td style="border-right-color: blue;border-right-width: 2px;border-right-style: solid;margin-right: 3px;"></td>
			<td style="margin-left: 3px; padding-left: 3px;">
				<form name="example" method="post" action="<%=request.getContextPath()%>/manager/sys/articleEdit.do" onsubmit="return articleSubmit();">
					<table class="releaseTable" cellpadding="3" cellspacing="0">
						<tr>
							<td class="tdtitle" colspan="2">标题:<input name="id" readonly="readonly" type="hidden" value="<%=article.getId()==null?"":article.getId()%>"></td>
						</tr>
						<tr block="content_index_block">
							<td colspan="2"><input class="ui-corner-all" type="text"
								style="width: 500px;border-style: solid;border-width: 1px;border-color: black;" id="articleTitleId" name="title" value="<%=article.getTitle()==null?"":article.getTitle()%>"></td>
						</tr>
						
						<tr>
						<td  class="tdtitle">标题颜色：</td>
						<td><select name="titleStyle">
							<option value="1" <%=article.getTitleStyle().equals("1")?" selected=\"selected\"":""%> >普通</option>
							<option value="2" <%=article.getTitleStyle().equals("2")?" selected=\"selected\"":""%> >红色</option>
							<option value="3" <%=article.getTitleStyle().equals("3")?" selected=\"selected\"":""%> >斜体</option>
						</select></td>
						</tr>
						
						<tr>
						<td  class="tdtitle">文章类型</td>
						<td><select name="type">
							<option value="2" <%=article.getType()==2?" selected=\"selected\"":""%>>普通文章</option>
							<option value="1" <%=article.getType()==1?" selected=\"selected\"":""%>>公告·通知</option>
						</select></td>
						</tr> 
						
						<tr id="ggshow">
						<td  class="tdtitle">显示内容</td>
						<td><select name="display">
							<option value="0" <%=article.isDisplay()== false?" selected=\"selected\"":""%>>不显示</option>
							<option value="1" <%=article.isDisplay()== true?" selected=\"selected\"":""%>>显示</option>
						</select><span style="color: red">当文章类型为“普通文章”时，该设置无效；该设置只针对公告·通知 类型有效</span></td>
						</tr>
						
						<tr block="content_index_block">
							<td class="tdtitle">作者:</td>
							<td align="left"><span>${teacher.name }</span></td>
						</tr>
						<tr block="content_index_block">
							<td class="tdtitle">部门:</td>
							<td align="left"><span><input id="autocomplete" title="删除里面的所有字就可看见列表。比如： &quot;院&quot;" value="${article.department.name }"><input id="copyAutocomplete" type="hidden" name="departmentId"  value="${article.department.id }">
							${article.section.name }</span><span id="selectDepartmentSections">
							<select name="sectionId" id="articleEditSectionId">
							<c:forEach items="${article.department.sections }" var="section">
								<option value="${section.id }">${section.name }</option>
							</c:forEach>
							</select><a href='#' id='create-section' onclick='openDialog();'>添加新栏目</a>
							</span></td>
						</tr>
						<tr block="content_index_block">
							<td class="tdtitle">发表时间:</td>
							<td align="left"><input class="ui-corner-all" name="date" id="date"
								readonly="readonly" style="width: 100px;border-style: solid;border-width: 1px;border-color: black;" value="<%=new SimpleDateFormat("yyyy-MM-dd").format(new Date()) %>"></td>
						</tr>
						<tr block="content_index_block">
							<td colspan="2"><textarea name="content" cols="100" rows="8"
									style="width: 700px; height: 200px; visibility: hidden;"><%=htmlspecialchars(article.getContent())%></textarea>
								<br /> <input type="submit" name="button" value="提交内容" />
								(提交快捷键: Ctrl + Enter)</td>
						</tr>
					</table>
				</form>
			</td>
		<tr>
	</table>
</div>
<div id="dialog-addSectionForm" title="添加新栏目">
<p>添加新栏目</p>
<fieldset>
<label for="sectionName">栏目名称</label><br>
<input type="text" name="sectionName" id="sectionName" value="" width="100%" class="text ui-widget-content ui-corner-all"><br>
<label for="sectionDescription">备注</label><br>
<input type="text" name="sectionDescription" id="sectionDescription" value="" width="100%" class="text ui-widget-content ui-corner-all"> 
</fieldset>
</div>
<script src="<%=request.getContextPath()%>/jquery-ui-1.11.1/external/jquery/jquery.js"></script>
<script charset="utf-8" src="<%=request.getContextPath()%>/jquery-ui-1.11.1/jquery-ui.js"></script>
<script charset="utf-8" src="<%=request.getContextPath()%>/jquery-ui-1.11.1/zh_CN.js"></script>
<script charset="utf-8" src="<%=request.getContextPath()%>//manager/js/sysmana/global.js"></script>
<script>
		KindEditor.ready(function(K) {
			var editor1 = K.create('textarea[name="content"]', {
				cssPath : ' <%=request.getContextPath()%>/kindeditor/plugins/code/prettify.css',
				uploadJson : '<%=request.getContextPath()%>/kindeditor/jsp/upload_json.jsp',
				fileManagerJson : '<%=request.getContextPath()%>/kindeditor/jsp/file_manager_json.jsp',
									allowFileManager : true,
									afterCreate : function() {
										var self = this;
										K.ctrl(document, 13, function() {
											self.sync();
											document.forms['example'].submit();
										});
										K.ctrl(self.edit.doc, 13, function() {
											self.sync();
											document.forms['example'].submit();
										});
									}
								});
				prettyPrint();
// 					 $( "#create-section" ).button().on( "click", function() {
// 						 	dialog.dialog( "open" );
// 					 });
					 
			});
		var dialog ;
		<%
		String avai = "[";
		List<Department> departments = (List<Department>)request.getAttribute("departments");
		for(int i=0; i<departments.size(); i++){
			avai+="{value:\""+departments.get(i).getId()+"\",label:\""+departments.get(i).getName()+"\"}";
			if(i!=departments.size()-1){
				avai+=",";
			}
		}
		avai+="]";
		%>
		var availableTags=<%=avai%>;
		$(function(){
			$("#date").datepicker({
				dateFormat : 'yy-mm-dd',
				changeMonth : true,
				changeYear : true,
				inline : true
			});

			$( "#autocomplete" ).autocomplete({
				minLength: 0,
				source: availableTags,
// 				focus: function( event, ui ) {
// 					 $( "#autocomplete" ).val( ui.item.label );
// 					 return false;
// 					 },
				select: function( event, ui ) {
					 $( "#autocomplete" ).val( ui.item.label );
					 $( "#copyAutocomplete" ).val( ui.item.value );
					 changeSelectOptionDepartmentSection(ui.item.value);
					 return false;
					 }
			});
			
			
			var sectionName = $("#sectionName");
			var sectionDescription = $("#sectionDescription");
			  dialog = $( "#dialog-addSectionForm" ).dialog({
				 autoOpen: false,
				 height: 300,
				 width: 350,
				 modal: true,
				 buttons: {
				 "添加": function(){
					var dptid=$("#copyAutocomplete").val();
					 //alert("部门ID："+dptid+"，栏目名称："+sectionName.val()+"，栏目备注："+sectionDescription.val());
					 createSection(dptid,sectionName.val(), sectionDescription.val());
					 dialog.dialog("close");
				 },
				 Cancel: function() {
				 dialog.dialog( "close" );
				 }
				 },
				 close: function() {
					 dialog.dialog( "close" );
				 }
				 });
			 
// 			 $("#addSectionFormId").on( "submit", function( event ) {
// 				 event.preventDefault();
// 				 createSection(1);
// 				 });
		});
		function openDialog(){
			dialog.dialog( "open" );
		}
		function changeSelectOptionDepartmentSection(dptid){
			var url = buildUrl("getDepartmentSections");
			var jsonstr ={"departmentId":dptid};
			 $.ajax({
					type: "GET",
					contentType:'application/json;charset=UTF-8',
					url: url,
					data:jsonstr,
					dataType:"json",
					success:function(msg){
						dynSections(msg);
					},
					error:function(){
						alert("error") ;
					}
			 });
		}
		function createSection(dptid,sectionName, sectionDescription){
			var url = buildUrl("doCreateSection");
			var jsonstr ={"departmentId":dptid,"sectionName":sectionName,"sectionDescription":sectionDescription};
			 $.ajax({
					type: "GET",
					contentType:'application/json;charset=UTF-8',
					url: url,
					data:jsonstr,
					dataType:"json",
					success:function(msg){
						dynSections(msg);
					},
					error:function(){
						alert("error") ;
					}
			 });
		}
		function dynSections(msg){
			if(msg){
				var html = "<select name=\"sectionId\" id=\"articleEditSectionId\">";
				html+="";
				$.each(msg,function(i, section){
					html += "<option value=\""+section.id+"\">"+section.name+"</option>";
				});
				html+="</select><a href='#' id='create-section' onclick='openDialog();'>添加新栏目</a>";
				$("#selectDepartmentSections").html(html);
			}
		}
		function articleSubmit(){
			if(!$("#articleTitleId").val()){
				alert("您还没有输入标题呢！");
				return false;
			}else if(!$("#autocomplete").val()){
				alert("这个文章属于哪个部门请输入：学院试试看。");
				return false;
			}else if(!$("#articleEditSectionId  option:selected").val()){
				alert("没有选择板块，您可以创建一个新的!");
				return false;
			}
			else{
				return true;
			}
		}
</script>

<%!private String htmlspecialchars(String str) {
		str = str.replaceAll("&", "&amp;");
		str = str.replaceAll("<", "&lt;");
		str = str.replaceAll(">", "&gt;");
		str = str.replaceAll("\"", "&quot;");
		return str;
	}%>
