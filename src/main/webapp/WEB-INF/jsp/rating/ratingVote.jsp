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
	<table 	id="js_rating_vote_dg" 
		title="投票：${votePage.ratingName }-${votePage.tmplName }" 
		class="easyui-datagrid" 
		style="width:${gridWidth}px; height: 600px; padding-left: 200px;" 
		url="${ctx }/rating/vote/user/${votePage.tmplId}" 
		toolbar="#js_rating_vote_tb" 
		pagination="true"  
        rownumbers="true" 
        fitcolumns="true" 
        singleselect="true"
        onClickRow = "rating.userrating.onClickRow">
        
        <thead>
        	<tr>
        		<th data-options="field:'scorerId',width:120,
        				formatter:function(value,row){
							return row.label;
						},
						editor:{
							type:'combobox',
							options:{
								data:${usersJson },
								valueField:'value',
								textField:'label',
								panelHeight:'auto',
								required:true
							}
						}">姓名</th>
        		<c:forEach items="${options }" var="item">
        			<th data-options="field:'${item.value }',width:100,
        				formatter:function(value,row){
							return row.label;
						},editor:{
							type:'combobox',
							options:{
								valueField:'value',
								textField:'label',
								data : ${scores },
								required:true
							}
						}">${item.label }</th>
        			<%--<th class="js_score_option" field="${item.value }" formatter="rating.userrating.scoreFormat" width="50"
        			editor="{'type':'combobox','options':{valueField:'value',textField:'label',required:true,data='eval(${options})'}"
        			>${item.label }</th> --%>
        		</c:forEach>
        	</tr>
        </thead>
	</table>
	<div id="js_rating_vote_tb" style="height:auto">
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="rating.userrating.addVote()">增加</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" onclick="rating.userrating.removeVote()">删除</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-save',plain:true" onclick="rating.userrating.vote()">提交</a>
	</div>
	
</body>
<%@include file="/WEB-INF/jsp/base/gridlib.jspf" %>
<script type="text/javascript" src="${ctx}/resources/js/rating/userRating.js"></script>
</html>