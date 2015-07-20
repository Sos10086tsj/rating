<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<table 	id="js_rating_mgmt_dg" 
		title="投票主题管理" 
		class="easyui-datagrid" 
		style="padding-left: 200px;min-height:400px;" 
		url="${ctx }/system/rating/list" 
		toolbar="#toolbar" 
		pagination="false"  
        rownumbers="true" 
        fitcolumns="true" 
        singleselect="true">
        
        <thead>
        	<tr>
        		<!--<th field="id"  width="50">ID#</th>  -->
        		<th field="name"  width="50">主题</th>
        		<th field="effFrom" formatter="rating.dateFormat" width="50">开始时间</th>
        		<th field="effTo" formatter="rating.dateFormat" width="50">结束时间</th>
        		<!-- <th field="status"  width="50">状态</th> -->
        		<th field="templates" formatter="rating.ratingmgmt.templateFormat" width="50">权重管理</th>
        	</tr>
        </thead>
</table>

<div id="toolbar">
	<a href="javascript:void(0)" class="easyui-linkbutton"iconcls="icon-add" plain="true"onclick="rating.ratingmgmt.newRating()">添加</a>
</div>