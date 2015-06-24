<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/base/baselib.jspf" %>
<div id="dlg" class="easyui-dialog" style="width:400px;height:280px;padding:10px 20px"
	closed="true" buttons="#dlg-buttons">
	<div class="ftitle">User Information</div>
	<form id="fm" method="post" novalidate>
		<div class="fitem">
			<label>用户名</label>
			<input name="username" class="easyui-textbox" required="true">
		</div>
		<div class="fitem">
			<label>姓名</label>
			<input name="name" class="easyui-textbox" required="true">
		</div>
		<div class="fitem">
			<label>组</label>
			<select name="groupId">
				<c:forEach items="${ groups}" var="item">
					<option value="${item.value }" selected="selected">${item.label }</option>
				</c:forEach>
			</select>
		</div>
		<div class="fitem">
        	<label>职位</label>
        	<select name="positionId">
				<c:forEach items="${positions}" var="item">
					<option value="${item.value }" selected="selected">${item.label }</option>
				</c:forEach>
			</select>
       	</div>
       	<div class="fitem">
        	<label>联系电话</label>
         	<input name="phone" class="easyui-textbox" >
       	</div>
	</form>
</div>
<div id="dlg-buttons">
	<a href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok" onclick="rating.usermgmt.saveUser()" style="width:90px">Save</a>
	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')" style="width:90px">Cancel</a>
</div>