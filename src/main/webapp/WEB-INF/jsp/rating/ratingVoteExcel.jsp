<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="/WEB-INF/jsp/base/baselib.jspf" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet"  href="${ctx }/resources/components/handsontable/handsontable.full.css?${version}">
<title>评分考核系统</title>
</head>
<body>
	<div>
		<div style="text-align:center;font-size:16px;line-height:16px;height:16px;color:red;margin-bottom: 10px;">请逐行为每个人员进行投票，每项0.0~5.0分。“本地保存”后再“提交”</div>
		<div style="text-align:center;font-size:16px;line-height:16px;height:16px;color:red;margin-bottom: 10px;">得分项：●表现突出(5分)●表现较好(4分)●表现一般(3分)●表现较差(2分)●表现很差(1分)●表现极差(0分)</div>
	</div>
	
	
	
	<div id="js_rating_vote_tb" style="height:auto">
		<a href="javascript:void(0)" class="easyui-linkbutton" onclick="rating.userrating.vote()">提交</a>
	</div>
	
	<div id="js_handsontable"></div>

</body>
<%@include file="/WEB-INF/jsp/base/gridlib.jspf" %>
<script src="${ctx }/resources/components/handsontable/handsontable.full.js?${version}"></script>
<script>
	var glb_tmplId = '${tmplId}';
</script>
<script type="text/javascript" src="${ctx}/resources/js/rating/userRatingExcel.js?${version}"></script>
</html>