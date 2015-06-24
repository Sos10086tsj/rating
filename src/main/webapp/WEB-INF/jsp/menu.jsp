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
	<div id='cssmenu'>
		<ul>
			<li class="active"><a href='#'>首页</a></li>
			<c:forEach items="${menus }" var="menu">
				<li <c:if test="${not empty menu.subMenu}" >class='has-sub'</c:if>>
					<a href='#'>${menu.name } ></a>
					<c:forEach items="${menu.subMenu }" var="subMenu">
						<ul>
							<li><a href='${ctx }/${subMenu.url }' target="rating_iframe">${subMenu.name }</a></li>
						</ul>
					</c:forEach>
				</li>
			</c:forEach>
		</ul>
	</div>
</body>
<script type="text/javascript" src="${ctx}/resources/js/menu/menu.js"></script>
<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/menu.css" />
</html>