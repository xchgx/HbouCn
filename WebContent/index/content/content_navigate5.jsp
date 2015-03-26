<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<center>
	<div style="text-align: center;">
		<form action="user/doLogin.do" method="post">
			<table align="center" border="0" cellpadding="3" cellspacing="0">
				<tr>
					<td colspan="3"><span style="font-size: 18px;"><b>管理员登录</b></span></td>
				</tr>
				<tr>
					<td colspan="3"><span style="font-size: 12px; color: red;">目前没有开放注册功能，<br>登录用户仅限于内部工作人员实用。
					</span></td>
				</tr>
				<tr>
					<td align="right">用户名：</td>
					<td><input type="text" name="name" block="content_index_block"
						class="ui-state-default"></td>
					<td><form:errors path="name" cssStyle="color: red"></form:errors> </td>
				</tr>
				<tr>
					<td align="right">密码：</td>
					<td><input type="password" name="password"
						block="content_index_block" class="ui-state-default"></td>
					<td><form:errors path="password" cssStyle="color: red"></form:errors></td>
				</tr>
				<tr>
					<td colspan="3"><input type="submit" value="登录">${errorMsg }<form:errors path="*"></form:errors> </td>
				</tr>
			</table>
		</form>
	</div>
</center>