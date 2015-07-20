<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<table id="js_rating_weight_mgmt_dg" class="easyui-datagrid"
	title="投票主题权重管理"  style="padding-left: 200px;min-height:400px;"
	data-options="
                singleSelect:true,
                rownumbers:true,
                fitColumns:true,
                url:'${ctx }/rating/template/weight/detail/${templateId}',
                view:groupview,
                groupField:'categoryCode',
                toolbar:'#js_rating_weight_mgmt_tb',
                groupFormatter:rating.ratingmgmt.groupRowFormat,
                onDblClickCell:rating.ratingmgmt.onWeightDbClickRow
            ">
	<thead>
		<tr>
			<th field="categoryCode" hidden="true">类型</th>
			<th field="category" hidden="true">类型名称</th>
			<th field="id" hidden="true">ID#</th>
			<th field="name" width="100" >得分项</th>
			<th field="weight"
				editor="{type:'numberbox',options:{required:true,precision:2,min:0,max:100}}"
				width="50">权重（%）</th>
		</tr>
	</thead>
</table>
<div id="js_rating_weight_mgmt_tb" style="height: auto">
	 <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-save',plain:true" onclick="rating.ratingmgmt.acceptWeight()">本地保存</a> 
	 <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-save',plain:true" onclick="rating.ratingmgmt.submitWeight()">提交</a>
</div>
<script>
	var glb_tmpl_id = ${templateId};
</script>