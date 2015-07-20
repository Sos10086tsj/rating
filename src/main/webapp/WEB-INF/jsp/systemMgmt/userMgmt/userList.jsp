<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<table 	id="dg" 
		title="系统用户" 
		class="easyui-datagrid" 
		style="padding-left: 200px;" 
		url="${ctx }/system/user/list" 
		toolbar="#toolbar" 
		pagination="false"  
        rownumbers="true" 
        fitcolumns="true" 
        singleselect="true">
        
        <thead>
        	<tr>
        		<th field="id" hidden="true">ID#</th>  
        		<th field="username"  width="50">用户名</th>
        		<th field="name"  width="50">姓名</th>
        		<th field="groupId"  hidden="true">组Id</th>
        		<th field="groupName"  width="50">组</th>
        		<th field="positionId"  hidden="true">职位Id</th>
        		<th field="positionName"  width="50">职位</th>
        		<th field="phone"  width="50">电话</th>
        		<%--<th field="status"  width="50">状态</th> --%>
        	</tr>
        </thead>
</table>

<div id="toolbar">
	<a href="javascript:void(0)" class="easyui-linkbutton"iconcls="icon-add" plain="true"onclick="rating.usermgmt.newUser()">添加</a>
	<a href="javascript:void(0)" class="easyui-linkbutton"iconcls="icon-edit" plain="true"onclick="rating.usermgmt.editUser()">修改</a>  
    <!-- <a href="javascript:void(0)" class="easyui-linkbutton"iconcls="icon-remove" plain="true"onclick="rating.usermgmt.destroyUser()">删除</a> -->  
</div>