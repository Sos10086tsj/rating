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
	
	<form id="js_rating_cr_form" method="post" commandName="m" action="${ctx }/system/rating/create">
		<div class="break_line"></div>
		<div class="fitem">
			<div class="user_edit_label float_left">
				<label >投票主题</label>
			</div>
			<div style="margin-left:90px;">
				<input name="name" class="easyui-textbox user_edit_input" required="true" >
			</div>
		</div>
		<div class="break_line"></div>
		<div>
			<div class="user_edit_label float_left">
				<label >选用模板</label>
			</div>
			<div style="margin-left:90px;">
				<c:forEach items="${suppTemplates }" var="item">
					<label><input type="checkbox" name="templateIds" checked="checked" value="${ item.value}"/>${ item.label}卷</label>
				</c:forEach> 
			</div>
		</div>
		<div class="break_line"></div>
		<div>
			<div class="user_edit_label float_left">
				<label >投票时间</label>
			</div>
			<div style="margin-left:90px;">
				<input name="effFromStr" type="text" class="easyui-datebox" />
				至
				<input name="effToStr" type="text" class="easyui-datebox" />	
			</div>
		</div>
		<div class="break_line"></div>
		<div>
			<button type="submit">发起投票</button>
		</div>
	</form>
</body>
<%@include file="/WEB-INF/jsp/base/gridlib.jspf" %>
</html>