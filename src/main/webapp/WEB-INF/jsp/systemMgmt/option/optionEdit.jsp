<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/base/baselib.jspf" %>
<div id="js_option_edit_dg" class="easyui-dialog" style="width:400px;height:280px;padding:10px 20px"
	closed="true" buttons="#dlg-buttons">
	<div class="ftitle">新增/修改得分项</div>
	<form id="js_option_edit_form" method="post" action="${ctx }/system/option/update">
		<div class="break_line"></div>
		<div class="fitem">
			<div class="user_edit_label float_left">
				<label >得分项</label>
			</div>
			<div>
				<input name="name" class="easyui-textbox user_edit_input" required="true" >
			</div>
		</div>
		<div class="break_line"></div>

		<div class="fitem">
			<div class="user_edit_label float_left">
				<label>类别</label>
			</div>
			<div>
				<select name="categoryCode" class="easyui-combobox user_edit_input" data-options="panelHeight:'auto',required:true">
					<c:forEach items="${ categories}" var="item">
						<option value="${item.code }">${item.label }</option>
					</c:forEach>
				</select>
			</div>
		</div>
		<div class="break_line"></div>
		<input name="id" type="hidden">
		<input name="categoryName" type="hidden">
	</form>
</div>
<div id="dlg-buttons">
	<a href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok" onclick="rating.optionmgmt.saveOption()" style="width:90px">保存</a>
	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#js_group_edit_dg').dialog('close')" style="width:90px">取消</a>
</div>