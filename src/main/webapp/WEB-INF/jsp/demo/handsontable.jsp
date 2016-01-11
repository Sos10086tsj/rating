<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="/WEB-INF/jsp/base/baselib.jspf" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet"  href="${ctx }/resources/components/handsontable/handsontable.full.css">
<title>评分考核系统</title>

</head>
<body >
	<rating:menu/>
	<div id="js_handsontable"></div>
	
</body>
<%@include file="/WEB-INF/jsp/base/gridlib.jspf" %>
<script src="${ctx }/resources/components/handsontable/handsontable.full.js"></script>
<script src="${ctx }/resources/js/demo/demo.js"></script>
</html>