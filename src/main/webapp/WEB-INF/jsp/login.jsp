<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="/WEB-INF/jsp/base/baselib.jspf" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>评分考核系统</title>
<style type="text/css">
.cust_title_font {
font-family : SimSun;
font-size:32px;
}
.cust_sub_title_font {
font-family : SimSun;
font-size:30px;
}
</style>
</head>
<body style="overflow-y: hidden">
	
	<div style="margin:0 auto;margin-top:100px;width:450px;height:200px;">
	<div style="text-align:center;" class="cust_title_font">
		中国科学院上海技术物理研究所
	</div>
	<div style="text-align:center;" class="cust_sub_title_font">
		评分考核系统
	</div>
	<div style="height:50px;"></div>
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
	<div style="margin:400px auto;text-align:center;font-size:12px;">
		<div>
			<label>Copyright © 2015 宁波光之瞳光电科技有限公司. All Rights Reserved.</label>
		</div>
		<div style="margin:-125px 0 0 10px;text-align:left;font-size:12px;">
			
			<img src="${ctx }/resources/images/wechat.jpg" width="128" height="128">
		</div>
	</div>
	
	
	<div id="dd">
		<div style="margin:10px 0 0 60px;font-size:16px;line-height:16px;height:16px;">您的系统<font style="color:red;">未经授权</font>，请输入激活码!</div>
		<div style="margin:10px 0 0 60px;font-size:16px;line-height:16px;height:16px;">MAC：<font style="color:red;">${mac}</font></div>
		<div style="margin:10px 0 0 15px;font-size:16px;line-height:16px;height:16px;"><input id="js_mac_pass" style="width:300px" placeholder="请输入激活码"/></div>
	</div>
	
</body>
<%@include file="/WEB-INF/jsp/base/gridlib.jspf" %>
<script>
	var var_js_authorised = '${authorised}';
	var var_js_mac = '${mac}';
</script>
<script type="text/javascript" src="${ctx}/resources/js/login.js"></script>
</html>