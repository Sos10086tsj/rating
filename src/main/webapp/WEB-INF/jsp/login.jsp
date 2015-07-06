<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="/WEB-INF/jsp/base/baselib.jspf" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>评分考核系统</title>
</head>
<body >
	<div style="margin:0 auto;margin-top:200px;width:350px;">
	<div class="easyui-panel" title="用户登录" style="width:350px; ">
		<div style="padding:10px 60px 20px 60px;">
			<form id="js_login_form" method="post" name="js_login_form" action="${ctx }/login" onkeydown="rating.login.keyDown(event);">
				<table cellpadding="5">
					<tr>
                    	<td>用户名</td>
                    	<td><input class="easyui-textbox" type="text" name="username" data-options="required:true,iconCls:'icon-man',iconWidth:38"></input></td>
                	</tr>
                	<tr>
                    	<td>密码</td>
                    	<td><input class="easyui-textbox" type="password" name="password" data-options="required:true,iconCls:'icon-lock',iconWidth:38"></input></td>
                	</tr>
				</table>
				<%--<input type="text" style="display:none;" onkeydown="rating.login.keyDown(e);"/> --%>
			</form>
			<div style="float:right;margin-right:5px;padding:10px">
            	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="rating.login.login()">登录</a>
        	</div>
		</div>
	</div>
	</div>
</body>
<%@include file="/WEB-INF/jsp/base/gridlib.jspf" %>
<script type="text/javascript" src="${ctx}/resources/js/login.js"></script>
</html>