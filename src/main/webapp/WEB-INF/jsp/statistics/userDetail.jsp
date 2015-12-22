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
		<div style="text-align:left;font-size:16px;line-height:16px;height:16px;margin-bottom: 10px;">
			<span >我的得分：</span>
			<span style="color:red;">${myScore }&nbsp;&nbsp;&nbsp;&nbsp;</span>
			<span >最高分：</span>
			<span style="color:red;">${max }&nbsp;&nbsp;&nbsp;&nbsp;</span>
			<span >最低分：</span>
			<span style="color:red;">${min }&nbsp;&nbsp;&nbsp;&nbsp;</span>
		</div>
	</div>
	<table 	id="js_statistics_detail_dg" 
		title="得分明细" 
		class="easyui-datagrid" 
		style="padding-left: 200px;min-height:400px;"
		data-options="
		url:'${ctx }/rating/statistics/detail/${ratingId}/${userId}',
		pagination:false ,
        rownumbers:true,
        fitcolumns:true,
        singleselect:true"
        >
        
        <thead>
        	<tr>
        		<th data-options="field:'source',width:120,hidden:true">来源</th> 
        		<c:forEach items="${options }" var="item">
        			<th data-options="field:'${item.value }',width:${item.width }">${item.label }</th>
        		</c:forEach>
        	</tr>
        </thead>
	</table>
</body>
<%@include file="/WEB-INF/jsp/base/gridlib.jspf" %>
</html>