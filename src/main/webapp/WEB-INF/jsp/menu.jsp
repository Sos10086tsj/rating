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
	<div>
		您已登录
		<c:forEach items="${menus }" var="menu">
			${menu.name }|${menu.url }
			<c:forEach items="${menu.subMenu }" var="subMenu">
				${subMenu.name }|${subMenu.url }
			</c:forEach>
		</c:forEach>
	</div>
</body>
</html>