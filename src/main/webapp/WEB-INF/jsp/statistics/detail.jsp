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
		title="${rptVo.name}    <c:if test="${not empty statisticsTime }"><font color='red'>统计计算时间：<fmt:formatDate value="${statisticsTime }" pattern="yyyy-MM-dd HH:mm:ss"/></font></c:if>
		&nbsp;&nbsp;&nbsp;&nbsp;<a href='${ctx }/rating/statistics/detail/${tmplIds}?realTime=0'>实时计算</a>" 
		class="easyui-datagrid" 
		style="padding-left: 200px;min-height:400px;"
		data-options="
		url:'${ctx }/rating/statistics/${tmplIds}?realTime=${realTime }',
		pagination:false ,
        rownumbers:true,
        fitcolumns:true,
        singleselect:true"
        >
        
        <thead>
        	<tr>
        		<th data-options="field:'name',width:120" formatter="rating.statistics.detailNameFormat">人员</th>
        		<th field="total" width="50">总分</th>
        		<c:forEach items="${options }" var="item">
        			<th data-options="field:'${item.value }',width:${item.width }">${item.label }</th>
        		</c:forEach>
        	</tr>
        </thead>
	</table>
<input id="js_statistics_user_rating_id_hidden" type="hidden" value="${ratingId}">
</body>
<%@include file="/WEB-INF/jsp/base/gridlib.jspf" %>
<script type="text/javascript" src="${ctx}/resources/js/statistics/statistics.js?${version}"></script>
</html>