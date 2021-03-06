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
	<table 	id="dg" 
		title="参与投票" 
		class="easyui-datagrid" 
		style="padding-left: 200px;" 
		url="${ctx }/rating/list" 
		pagination="false" 
        rownumbers="true" 
        fitcolumns="true" 
        singleselect="true">
        
        <thead>
        	<tr>
        		<!-- <th field="id"  width="50">ID#</th>   -->
        		<th field="name"  width="50">投票事件</th>
        		<th field="effFrom" formatter="rating.dateFormat" width="50">开始时间</th>
        		<th field="effTo" formatter="rating.dateFormat" width="50">结束时间</th>
        		<th field="templates" formatter="rating.userrating.tempalteFormat" width="50">投票</th>
        	</tr>
        </thead>
</table>
<input id="js_vote_user_id_hidden" type="hidden" value="${currentUser}">
</body>
<%@include file="/WEB-INF/jsp/base/gridlib.jspf" %>
<script type="text/javascript" src="${ctx}/resources/js/rating/userRating.js?${version}"></script>
</html>