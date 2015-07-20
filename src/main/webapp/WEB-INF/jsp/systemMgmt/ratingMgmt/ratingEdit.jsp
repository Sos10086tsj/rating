<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/base/baselib.jspf" %>
<div id="js_rating_mgmt_dlg" class="easyui-dialog" style="width:500px;height:280px;padding:10px 20px"
	closed="true" buttons="#dlg-buttons">
	<div class="ftitle div_title">创建/修改投票主题</div>
	<form id="js_rating_mgmt_edit" method="post">
		<div class="break_line"></div>
		<div class="fitem">
			<div class="user_edit_label float_left">
				<label >投票主题</label>
			</div>
			<div>
				<input name="name" id="js_rating_name" class="easyui-textbox" data-options="panelHeight:'auto',required:true,width:280">
				<span id="js_pass_remind"><font color="#FF0000">(请勿重复)</font></span>
			</div>
		</div>
		<div class="break_line"></div>
		<div>
			<div class="user_edit_label float_left">
				<label >投票时间</label>
			</div>
			<div>
				<input id="js_rating_eff_from" name="effFromStr" type="text" class="easyui-datebox" />
				至
				<input id="js_rating_eff_to" name="effToStr" type="text" class="easyui-datebox" />	
			</div>
		</div>
		<div class="break_line"></div>
		<div class="fitem">
			<div class="user_edit_label float_left">
				<label>投票表格</label>
			</div>
			<div>
				<c:forEach items="${suppTemplates }" var="item">
					<input type="checkbox" checked="checked" class="js_templateIds" name="templateIds" value="${ item.value}"/><span>${ item.label}卷</span>
				</c:forEach> 
			</div>
		</div>
       	<div class="break_line"></div>
	</form>
	<input id="js_hidden_url" type="hidden"/>
</div>
<div id="dlg-buttons">
	<a href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok" onclick="rating.ratingmgmt.saveRating()" style="width:90px">保存</a>
	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#js_rating_mgmt_dlg').dialog('close')" style="width:90px">取消</a>
</div>