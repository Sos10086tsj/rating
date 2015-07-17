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
    <table id="js_rating_weight_mgmt_dg" class="easyui-datagrid" title="权重管理" style="width:900px; height: 600px; padding-left: 200px;"
            data-options="
                singleSelect:true,
                rownumbers:true,
                fitColumns:true,
                url:'${ctx }/rating/template/weight/detail/${templateId}',
                view:groupview,
                groupField:'categoryCode',
                toolbar:'#js_rating_weight_mgmt_tb',
                groupFormatter:rating.ratingmgmt.groupRowFormat
            ">
        <thead>
            <tr>
            	<th field="categoryCode"  hidden="true">类型</th>
                <th field="category"  width="50">类型</th>
        		<th field="id" hidden="true">ID#</th>
        		<th field="name" width="50">得分项</th>
        		<th field="weight" editor="{type:'numberbox',options:{required:true,precision:2,min:0,max:100}}" width="50">权重（%）</th>
            </tr>
        </thead>
    </table>
    <div id="js_rating_weight_mgmt_tb" style="height:auto">
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-save',plain:true" onclick="rating.userrating.vote()">提交</a>
	</div>
</body>
<%@include file="/WEB-INF/jsp/base/gridlib.jspf" %>
<script type="text/javascript" src="${ctx}/resources/js/systemMgmt/ratingMgmt.js"></script>
</html>