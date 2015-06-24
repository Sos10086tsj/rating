<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<table 	id="dg" 
		title="定性指标基础信息" 
		class="easyui-datagrid" 
		style="width:900px; height: 400px; padding-left: 200px;" 
		url="${ctx }/system/rating/list" 
		toolbar="#toolbar" 
		pagination="true"  
        rownumbers="true" 
        fitcolumns="true" 
        singleselect="true">
        
        <thead>
        	<tr>
        		<th field="id"  width="50">ID#</th>  
        		<th field="name"  width="50">姓名</th>
        		<th field="effFrom"  width="50">开始时间</th>
        		<th field="effTo"  width="50">结束时间</th>
        		<th field="status"  width="50">状态</th>
        	</tr>
        </thead>
</table>

<div id="toolbar">
	<a href="javascript:void(0)" class="easyui-linkbutton"iconcls="icon-add" plain="true"onclick="rating.ratingmgmt.newRating()">添加</a>
	<a href="javascript:void(0)" class="easyui-linkbutton"iconcls="icon-edit" plain="true"onclick="rating.ratingmgmt.editRating()">修改</a>  
    <!-- <a href="javascript:void(0)" class="easyui-linkbutton"iconcls="icon-remove" plain="true"onclick="rating.usermgmt.destroyUser()">删除</a> -->  
</div>