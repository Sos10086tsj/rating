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
		<div style="text-align:center;font-size:16px;line-height:16px;height:16px;color:red;margin-bottom: 10px;">请逐行为每个人员进行投票，每项0.0~5.0分。“本地保存”后再“提交”</div>
		<div style="text-align:center;font-size:16px;line-height:16px;height:16px;color:red;margin-bottom: 10px;">得分项：●表现突出(5分)●表现较好(4分)●表现一般(3分)●表现较差(2分)●表现很差(1分)●表现极差(0分)</div>
	</div>
	<table 	id="js_rating_vote_dg" 
		title="投票：${votePage.ratingName }-${votePage.tmplName }" 
		class="easyui-datagrid" 
		style="width:${gridWidth + 40}px; height: 900px; padding-left: 400px;"
		data-options="
		url:'${ctx }/rating/vote/user/${votePage.tmplId}',
		toolbar:'#js_rating_vote_tb',
		pagination:false ,
        rownumbers:true,
        fitcolumns:true,
        singleselect:true,
        onDblClickCell:rating.userrating.onDbClickRow"
        >
        
        <thead>
        	<tr>
        		<th field="scorerId"  hidden="true">ID</th>  
        		<th field="scorerName">人员</th>
        	<%--
        		<th data-options="field:'scorerId',width:120,
        				formatter:function(value,row){
        					var datasource = eval(${usersJson });
        					for(var i=0; i < datasource.length ; i++){
        						if(datasource[i].value == value){
        							return datasource[i].label;
        						}
        					}
        					return value;
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
						}">姓名</th> --%>
        		<c:forEach items="${options }" var="item">
        			<%--<th data-options="field:'${item.value }',width:${item.width },
        				formatter:function(value,row){
							var datasource = eval(${scores });
        					for(var i=0; i < datasource.length ; i++){
        						if(datasource[i].value == value){
        							return datasource[i].label;
        						}
        					}
        					return value;
						},editor:{
							type:'combobox',
							options:{
								valueField:'value',
								textField:'label',
								data : ${scores },
								panelHeight:'auto',
								required:true
							}
						}">${item.label }</th> --%>
						<th field="${item.value }" width="${item.width }" 
						editor="{type:'numberbox',options:{required:true,precision:1,min:0,max:5}}" >${item.label }</th>
        		</c:forEach>
        	</tr>
        </thead>
	</table>
	<div id="js_rating_vote_tb" style="height:auto">
		<%--<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="rating.userrating.addVote()">增加</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" onclick="rating.userrating.removeVote()">删除</a> --%>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-save',plain:true" onclick="rating.userrating.accept()">本地保存</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-save',plain:true" onclick="rating.userrating.vote()">提交</a>
	</div>
<label id="js_option_json_hidden" style="display:none;">${optionsJson }</label>	
<label id="js_tmpl_id_hidden" style="display:none;">${votePage.tmplId}</label>	

</body>
<%@include file="/WEB-INF/jsp/base/gridlib.jspf" %>
<script type="text/javascript" src="${ctx}/resources/js/rating/userRating.js"></script>
</html>