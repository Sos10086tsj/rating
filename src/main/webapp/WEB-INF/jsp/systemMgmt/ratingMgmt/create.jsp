<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="/WEB-INF/jsp/base/baselib.jspf" %>
<%@taglib prefix="form" uri="/WEB-INF/tld/spring-form.tld" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>评分考核系统</title>
</head>
<body>
	<rating:menu/>
	
	<form:form id="js_rating_cr_form" method="post" commandName="m" action="${ctx }/system/rating/create">
		<div>
			<form:label path="name">标题：</form:label>
			<form:input path="name" placeholder=""/>
		</div>
		<div> 
			<form:label path="templateIds">选用模板：</form:label>
			<c:forEach items="${suppTemplates }" var="item">
				<label><form:checkbox path="templateIds" checked="checked" value="${ item.value}"/>${ item.label}</label>
			</c:forEach> 
		</div>
		<div>
			<form:label path="effFrom">投票时间：</form:label>
			<form:input path="effFrom" type="text" class="easyui-datebox" />
			至
			<form:input path="effTo" type="text" class="easyui-datebox" />
		</div>
		<div>
			<form:button type="submit">发起投票</form:button>
		</div>
	</form:form>
</body>
<%@include file="/WEB-INF/jsp/base/gridlib.jspf" %>
</html>