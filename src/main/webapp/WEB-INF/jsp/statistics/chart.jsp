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
<div id="js_chart"></div>
</body>
<%@include file="/WEB-INF/jsp/base/gridlib.jspf" %>
<script type="text/javascript" src="${ctx}/resources/components/highcharts/highcharts.js?${version}"></script>
<script type="text/javascript" src="${ctx}/resources/js/statistics/chart.js?${version}"></script>
<script>
	var js_chart_title = '${title}';
	var js_chart_data = '${data}';
</script>
</html>