<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/base/baselib.jspf" %>
<div id="js_group_edit_dg" class="easyui-dialog" style="width:400px;height:250px;padding:10px 20px"
	closed="true" buttons="#dlg-buttons">
	<div class="ftitle div_title">组/部门维护</div>
	<form id="js_group_edit_form" method="post" action="${ctx }/system/group/create">
		<div class="break_line"></div>
		<div class="fitem" style="margin-top:20px;">
			<div class="user_edit_label float_left">
				<label >名称</label>
			</div>
			<div>
				<input name="name" class="easyui-textbox user_edit_input" data-options="width:250" required="true" >
			</div>
		</div>
		<div class="break_line"></div>

		<div class="fitem">
			<div class="user_edit_label float_left">
				<label>类别</label>
			</div>
			<div>
				<select name="level" class="easyui-combobox user_edit_input" data-options="panelHeight:'auto',required:true,width:250">
					<c:forEach items="${ levels}" var="item">
						<option value="${item.value }">${item.label }</option>
					</c:forEach>
				</select>
			</div>
		</div>
		<div class="break_line"></div>
		<input name="id" type="hidden" id="js_group_id_hidden">
	</form>
</div>
<div id="dlg-buttons">
	<a href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok" onclick="rating.groupmgmt.saveGroup()" style="width:90px">保存</a>
	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#js_group_edit_dg').dialog('close')" style="width:90px">取消</a>
</div>