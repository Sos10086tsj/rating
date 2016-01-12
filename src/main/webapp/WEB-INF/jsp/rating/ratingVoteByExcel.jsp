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
		<div style="font-size:16px;line-height:16px;height:16px;color:red;margin-bottom: 10px;">请下载评分excel，评分后上传。</div>
		<div style="font-size:16px;line-height:16px;height:16px;color:red;margin-bottom: 10px;">请逐行为每个人员进行投票，每项0.0~5.0分。</div>
		<div style="font-size:16px;line-height:16px;height:16px;color:red;margin-bottom: 10px;">得分项：●表现突出(5分)●表现较好(4分)●表现一般(3分)●表现较差(2分)●表现很差(1分)●表现极差(0分)</div>	</div>
	
	<rating:excelFile url="${ctx }/rating/downloadVoteExcel/${tmplId }" text="下载：${fileName }"/>
	
	<button>上传</button>
	
</body>
<%@include file="/WEB-INF/jsp/base/gridlib.jspf" %>
<script>
	var glb_tmplId = '${tmplId}';
</script>
<script type="text/javascript" src="${ctx}/resources/js/rating/userRatingByExcel.js"></script>
</html>