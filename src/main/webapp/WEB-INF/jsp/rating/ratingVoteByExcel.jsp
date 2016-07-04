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
	
	<div style="margin:20px 0 10px 10px;">
		<div style="float:left;">
			<label>下载评分excel：</label>
		</div>
		<div>
			<rating:excelFile url="${ctx }/rating/downloadVoteExcel/${tmplId }" text="下载：${fileName }"/>
		</div>
	</div>
	
	<div style="margin:30px 0 10px 10px;">
		<div style="float:left;padding-top: 3px;">
			<label>上传评分excel：</label>
		</div>
		<div >
			<rating:uploadFile uploadFunc="userRatingByExcel.uploadVoteExcel()" filedName="voteExcel" />
		</div>
	</div>
	
	<!-- 结果展示 -->
	<div id="js_rating_excel_id" style="display:none;">
		<div style="font-size:14px;color:#666;">
			投票结果
		</div>
		<table id="js_rating_excel_result_table_id" class="excel">
			<thead>
			</thead>
			<tbody>
			</tbody>
		</table>
	</div>
</body>
<%@include file="/WEB-INF/jsp/base/gridlib.jspf" %>
<script>
	var glb_tmplId = '${tmplId}';
</script>
<script type="text/javascript" src="${ctx}/resources/js/uploadFile.js?${version}"></script>
<script type="text/javascript" src="${ctx}/resources/components/jQuery/jquery.form.min.js?${version}"></script>
<script type="text/javascript" src="${ctx}/resources/js/rating/userRatingByExcel.js?${version}"></script>
</html>