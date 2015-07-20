<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/base/baselib.jspf" %>
<div id="js_mgmt_supp_tmpl_add_dg" class="easyui-dialog" style="width:400px;height:280px;padding:10px 20px"
	closed="true" buttons="#js_mgmt_supp_tmpl_add_dlg-buttons">
	<div class="ftitle">添加得分项</div>
	<form id="js_mgmt_supp_tmpl_add_form" method="post" action="${ctx }/system/template/suppadd/${tmplId}">
		<div class="break_line"></div>
		<div class="fitem">
			<div class="user_edit_label float_left">
				<label>得分项</label>
			</div>
			<div>
				<select name="optionId" class="easyui-combobox user_edit_input" data-options="panelHeight:'auto',required:true">
					<c:forEach items="${ options}" var="item">
						<option value="${item.id }">${item.name }</option>
					</c:forEach>
				</select>
			</div>
		</div>
		<div class="break_line"></div>
		<div class="fitem">
			<div class="user_edit_label float_left">
				<label >权重</label>
			</div>
			<div>
				<input name="weight" type="text" class="easyui-numberbox user_edit_input" value="100" data-options="required:true,precision:2,min:0,max:100">
			</div>
		</div>
		<div class="break_line"></div>
	</form>
</div>
<div id="js_mgmt_supp_tmpl_add_dlg-buttons">
	<a href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok" onclick="rating.templatemgmt.submitAddOption()" style="width:90px">保存</a>
	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#js_group_edit_dg').dialog('close')" style="width:90px">取消</a>
</div>