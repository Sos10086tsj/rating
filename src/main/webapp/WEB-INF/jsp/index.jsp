<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="/WEB-INF/jsp/base/baselib.jspf" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>评分考核系统</title>
</head>
<body class="easyui-layout">
	<div data-options="region:'west',split:true,title:'West'" style="width:150px;padding:2px;">
	<!-- 菜单 -->
	<rating:menu/>
	<!-- 菜单结束 -->
	</div>
	<div data-options="region:'center',title:'Center'"></div>
</body>
<%@include file="/WEB-INF/jsp/base/gridlib.jspf" %>
<script type="text/javascript" src="${ctx}/resources/js/menu/menu.js"></script>
</html>