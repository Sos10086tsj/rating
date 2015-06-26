<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="/WEB-INF/jsp/base/baselib.jspf" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>评分考核系统</title>
</head>
<body>
	<div class="easyui-panel" title="用户登录" style="width:400px">
		<div style="padding:10px 60px 20px 60px">
			<form id="js_login_form" method="post" name="js_login_form" action="${ctx }/login">
				<table cellpadding="5">
					<tr>
                    	<td>用户名</td>
                    	<td><input class="easyui-textbox" type="text" name="username" data-options="required:true"></input></td>
                	</tr>
                	<tr>
                    	<td>密码</td>
                    	<td><input class="easyui-textbox" type="text" name="password" data-options="required:true"></input></td>
                	</tr>
				</table>
			</form>
			<div style="text-align:center;padding:5px">
            	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="rating.login.login()">登录</a>
        	</div>
		</div>
	</div>
</body>
<%@include file="/WEB-INF/jsp/base/gridlib.jspf" %>
<script type="text/javascript" src="${ctx}/resources/js/login.js"></script>
</html>