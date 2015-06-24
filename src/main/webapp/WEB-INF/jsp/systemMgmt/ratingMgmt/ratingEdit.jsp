<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/base/baselib.jspf" %>
<div id="dlg" class="easyui-dialog" style="width:400px;height:280px;padding:10px 20px"
	closed="true" buttons="#dlg-buttons">
	<div class="ftitle">User Information</div>
	<form id="fm" method="post" novalidate>
		<div class="fitem">
			<label>投票事件名称</label>
			<input name="name" class="easyui-textbox" required="true">
		</div>
		<div class="fitem">
			<label>起始时间</label>
			<input name="effFrom" class="easyui-textbox" required="true">
		</div>
		<div class="fitem">
			<label>结束时间</label>
			<input name="effTo" class="easyui-textbox" required="true">
		</div>
		<div class="fitem">
        	<label>职位</label>
        	<input type="checkbox" name="template" value="A" checked="checked">
        	<input type="checkbox" name="template" value="B" checked="checked">
        	<input type="checkbox" name="template" value="C" checked="checked">
        	<input type="checkbox" name="template" value="D" checked="checked">
       	</div>
	</form>
</div>
<div id="dlg-buttons">
	<a href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok" onclick="rating.ratingmgmt.saveRating()" style="width:90px">Save</a>
	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')" style="width:90px">Cancel</a>
</div>