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

<table id="js_mgmt_tmpl_edit_dg" class="easyui-datagrid"
	title="模板得分项管理" style="padding-left: 200px;min-height:400px;"
	data-options="
                singleSelect:true,
                rownumbers:true,
                fitColumns:true,
                url:'${ctx }/system/template/edit/${tmplId}',
                view:groupview,
                groupField:'categoryCode',
                toolbar:'#js_mgmt_tmpl_edit_toolbar',
                groupFormatter:rating.templatemgmt.editGroupRowFormat,
                onDblClickCell:rating.templatemgmt.onSuppTmplDbClickRow
            ">
        
        <thead>
        	<tr>
        		<th field="id"  hidden="true">ID#</th>  
        		<th field="name" width="50">得分项</th>
        		<th field="categoryCode" hidden="true">categoryCode</th>
        		<th field="categoryName" hidden="true">categoryName</th>
        		<th field="weight" width="50" editor="{type:'numberbox',options:{required:true,precision:2,min:0,max:100}}">权重</th>
        	</tr>
        </thead>
</table>

<div id="js_mgmt_tmpl_edit_toolbar">
	<a href="javascript:void(0)" class="easyui-linkbutton"iconcls="icon-add" plain="true"onclick="rating.templatemgmt.addOption()">添加</a>
	<a href="javascript:void(0)" class="easyui-linkbutton"iconcls="icon-edit" plain="true"onclick="rating.templatemgmt.removeOption()">删除</a>  
	<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-save',plain:true" onclick="rating.templatemgmt.acceptOption()">本地保存</a> 
	 <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-save',plain:true" onclick="rating.templatemgmt.submitOption()">提交</a>
</div>

	<%@include file="templateAdd.jsp"%>

</body>
<%@include file="/WEB-INF/jsp/base/gridlib.jspf" %>
<script type="text/javascript" src="${ctx}/resources/js/systemMgmt/templateMgmt.js?${version}"></script>
<script>
	var glb_supp_tmpl_id = ${tmplId};
</script>
</html>