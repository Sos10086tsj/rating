<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<table 	id="dg" 
		title="定性指标基础信息" 
		class="easyui-datagrid" 
		style="width:900px; height: 400px; padding-left: 200px;" 
		url="${ctx }/syste/user/list" 
		toolbar="#toolbar" 
		pagination="true"  
        rownumbers="true" 
        fitcolumns="true" 
        singleselect="true">
        
        <thead>
        	<tr>
        		<th data-options="">单位名称</th>  
        		<th data-options="">单位名称</th>  
        	</tr>
        </thead>
</table>

<div id="toolbar">
	<a href="javascript:void(0)" class="easyui-linkbutton"iconcls="icon-add" plain="true"onclick="newUser()">添加</a>
	<a href="javascript:void(0)" class="easyui-linkbutton"iconcls="icon-edit" plain="true"onclick="editUser()">修改</a>  
    <a href="javascript:void(0)" class="easyui-linkbutton"iconcls="icon-remove" plain="true"onclick="destroyUser()">删除</a>  
</div>