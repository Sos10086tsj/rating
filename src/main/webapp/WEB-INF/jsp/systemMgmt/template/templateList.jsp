<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<table id="js_mgmt_tmpl_dg" class="easyui-datagrid"
	title="模板管理" padding-left: 200px;"
	data-options="
                singleSelect:true,
                rownumbers:true,
                fitColumns:true,
                url:'${ctx }/system/template/list',
            ">
        
        <thead>
        	<tr>
        		<th field="id"  hidden="true">ID#</th>  
        		<th field="name" width="50">模板名称</th>
        		<th field="code" width="50" formatter="rating.templatemgmt.listOprFormat">操作</th>
        	</tr>
        </thead>
</table>