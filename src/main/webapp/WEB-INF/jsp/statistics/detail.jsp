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
	<table 	id="js_statistics_detail_dg" 
		title="投票：${rptVo.name}" 
		class="easyui-datagrid" 
		style="width:${gridWidth + 40}px; height: 600px; padding-left: 200px;"
		data-options="
		url:'${ctx }/rating/statistics/${tmplId}',
		pagination:true ,
        rownumbers:true,
        fitcolumns:true,
        singleselect:true"
        >
        
        <thead>
        	<tr>
        		<th data-options="field:'name',width:120" formatter="rating.statistics.detailNameFormat">姓名</th>
        		<c:forEach items="${options }" var="item">
        			<th data-options="field:'${item.value }',width:${item.width }">${item.label }</th>
        		</c:forEach>
        	</tr>
        </thead>
	</table>
<input id="js_statistics_detail_tmpl_id_hidden" type="hidden" value="${tmplId}">
</body>
<%@include file="/WEB-INF/jsp/base/gridlib.jspf" %>
<script type="text/javascript" src="${ctx}/resources/js/statistics/statistics.js"></script>
</html>