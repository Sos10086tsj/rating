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
	<div style="margin:0 auto;margin-top:200px;width:450px;height:200px;">
	<div class="easyui-panel" title="用户登录" style="width:450px;height:200px;">
		<div style="padding:10px 60px 20px 60px;">
			<form id="js_login_form" method="post" name="js_login_form" action="${ctx }/login" onkeydown="rating.login.keyDown(event);">
				<div><font color="#FF0000">${errorMessage }</font></div>
				<table cellpadding="5">			
					<tr style="font-size:16px;width:">
                    	<td >用户名</td>
                    	<td><input class="easyui-textbox" type="text" name="username" data-options="required:true,iconCls:'icon-man',iconWidth:38,width:200,height:30"></input></td>
                	</tr>
                	<tr style="font-size:16px;">
                    	<td>密码</td>
                    	<td><input class="easyui-textbox" type="password" name="password" data-options="required:true,iconCls:'icon-lock',iconWidth:38,width:200,height:30"></input></td>
                	</tr>
				</table>
			</form>
			<div style="float:right;margin-right:50px;padding:10px;margin-top:15px;">
            	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="rating.login.login()">登录</a>
        	</div>
		</div>
	</div>
	</div>
	<div style="margin:200px auto;text-align:center;font-size:12px;">
		<label>Copyright © 2015 宁波光之瞳光电科技有限公司. All Rights Reserved.</label>
	</div>
</body>
<%@include file="/WEB-INF/jsp/base/gridlib.jspf" %>
<script type="text/javascript" src="${ctx}/resources/js/login.js"></script>
</html>