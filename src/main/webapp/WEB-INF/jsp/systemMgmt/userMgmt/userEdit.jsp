<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/base/baselib.jspf" %>
<div id="dlg" class="easyui-dialog" style="width:400px;height:280px;padding:10px 20px"
	closed="true" buttons="#dlg-buttons">
	<div class="ftitle">创建/修改用户信息</div>
	<form id="js_user_edit" method="post">
		<div class="break_line"></div>
		<div class="fitem">
			<div class="user_edit_label float_left">
				<label >用户名</label>
			</div>
			<div>
				<input name="username" id="js_username" class="easyui-textbox user_edit_input" required="true" >
				<span id="js_pass_remind"><font color="#FF0000">(默认密码:123456)</font></span>
			</div>
		</div>
		<div class="break_line"></div>
		<div class="fitem">
			<div class="user_edit_label float_left">
				<label>姓名</label>
			</div>
			<div>
				<input name="name" class="easyui-textbox user_edit_input" required="true">
			</div>
		</div>
		<div class="break_line"></div>
		<div class="fitem">
			<div class="user_edit_label float_left">
				<label>组</label>
			</div>
			<div>
				<select name="groupId" class="easyui-combobox user_edit_input" data-options="panelHeight:'auto',required:true">
					<c:forEach items="${ groups}" var="item">
						<option value="${item.value }">${item.label }</option>
					</c:forEach>
				</select>
			</div>
		</div>
		<div class="break_line"></div>
		<div class="fitem">
			<div class="user_edit_label float_left">
				<label>职位</label>
			</div>
        	<div>
        		<select name="positionId" class="easyui-combobox user_edit_input" data-options="panelHeight:'auto',required:true">
					<c:forEach items="${positions}" var="item">
						<option value="${item.value }" selected="selected">${item.label }</option>
					</c:forEach>
				</select>
        	</div>
       	</div>
       	<div class="break_line"></div>
       	<div class="fitem">
       		<div class="user_edit_label float_left">
				<label>联系电话</label>
			</div>
        	<div>
        		<input name="phone" class="easyui-textbox user_edit_input" >
        	</div>
       	</div>
       	<div class="break_line"></div>
	</form>
	<input id="js_hidden_url" type="hidden"/>
</div>
<div id="dlg-buttons">
	<a href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok" onclick="rating.usermgmt.saveUser()" style="width:90px">保存</a>
	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')" style="width:90px">取消</a>
</div>