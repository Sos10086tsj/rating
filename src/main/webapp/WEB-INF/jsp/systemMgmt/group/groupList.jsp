<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<table 	id="js_mgmt_group_dg" 
		title="组管理" 
		class="easyui-datagrid" 
		style="padding-left: 200px;min-height:400px;" 
		url="${ctx }/system/group/list" 
		toolbar="#s_mgmt_group_toolbar" 
		pagination="false" 
        rownumbers="true" 
        fitcolumns="true" 
        singleselect="true">
        
        <thead>
        	<tr>
        		<th field="id"  hidden="true">ID#</th>  
        		<th field="level" hidden="true">类别ID</th>
        		<th field="levelName"  width="50">类别</th>
        		<th field="name"  width="50">名称</th>
        	</tr>
        </thead>
</table>

<div id="s_mgmt_group_toolbar">
	<a href="javascript:void(0)" class="easyui-linkbutton"iconcls="icon-add" plain="true"onclick="rating.groupmgmt.newGroup()">添加</a>
	<a href="javascript:void(0)" class="easyui-linkbutton"iconcls="icon-edit" plain="true"onclick="rating.groupmgmt.editGroup()">修改</a>  
    <!-- <a href="javascript:void(0)" class="easyui-linkbutton"iconcls="icon-remove" plain="true"onclick="rating.usermgmt.destroyUser()">删除</a> -->  
</div>