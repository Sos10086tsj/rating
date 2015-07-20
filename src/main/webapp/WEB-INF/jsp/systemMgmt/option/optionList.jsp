<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<table id="js_mgmt_option_dg" class="easyui-datagrid"
	title="得分项管理" style="padding-left: 200px;min-height:400px;"
	data-options="
                singleSelect:true,
                rownumbers:true,
                fitColumns:true,
                url:'${ctx }/system/option/list',
                view:groupview,
                groupField:'categoryCode',
                toolbar:'#js_mgmt_option_toolbar',
                groupFormatter:rating.optionmgmt.groupRowFormat
            ">
        
        <thead>
        	<tr>
        		<th field="id"  hidden="true">ID#</th>  
        		<th field="name" width="250">得分项</th>
        		<th field="categoryCode" hidden="true">categoryCode</th>
        		<th field="categoryName" hidden="true">categoryName</th>
        	</tr>
        </thead>
</table>

<div id="js_mgmt_option_toolbar">
	<a href="javascript:void(0)" class="easyui-linkbutton"iconcls="icon-add" plain="true"onclick="rating.optionmgmt.newOption()">添加</a>
	<a href="javascript:void(0)" class="easyui-linkbutton"iconcls="icon-edit" plain="true"onclick="rating.optionmgmt.editOption()">修改</a>  
</div>