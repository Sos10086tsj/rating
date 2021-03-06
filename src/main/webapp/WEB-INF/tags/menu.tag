<%@tag pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/base/baselib.jspf" %>
<div style="margin:10px auto;text-align:center;">
	<label >欢迎您，<font style=" text-decoration:underline;"> ${currentUser }</font>！</label>
</div>
<div class="easyui-panel" style="padding:5px;margin:10px auto;">
	<ul class="easyui-tree" id="js_menu_tree">
		<c:forEach items="${menus }" var="menu">
			<li>
				<span>${menu.name }</span>
				<ul>
					<c:forEach items="${menu.subMenu }" var="subMenu">
						<li data-options="attributes:{'url':'${ctx }/${subMenu.url }','title':'${subMenu.name }'}">${subMenu.name }</li>
					</c:forEach>
				</ul>
			</li>
		</c:forEach>
	</ul>
</div>
<div style="margin:10px auto;text-align:center;">
	<a href="javascript:void(0)" class="easyui-linkbutton" data-options="plain:true" 
	onclick="rating.menu.showProfile()">修改个人信息</a>
</div>
<div style="margin:10px auto;text-align:center;">
	<a href="javascript:void(0)" class="easyui-linkbutton" data-options="plain:true" 
	onclick="rating.menu.logout()">退&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp出</a>
</div>
<!-- 个人信息部分 -->
<div id="js_usr_profile_update" class="easyui-dialog" style="width:400px;height:320px;padding:10px 20px;"
	closed="true" buttons="#js_usr_profile_update-buttons">
	<div class="ftitle div_title">修改个人信息</div>
	<form id="js_user_profile_edit" method="post" action="${ctx }/system/user/updateProfile">
		<div class="break_line"></div>
		<div class="fitem">
			<div class="user_edit_label float_left">
				<label >用户名</label>
			</div>
			<div>
				<label >${profile.username }</label>
			</div>
		</div>
		<div class="break_line"></div>
		<div class="fitem">
			<div class="user_edit_label float_left">
				<label>姓名</label>
			</div>
			<div>
				<input name="name" class="easyui-textbox user_edit_input" data-options="width:250" required="true" value="${profile.name }">
			</div>
		</div>
		<div class="break_line"></div>
       	<div class="fitem">
       		<div class="user_edit_label float_left">
				<label>联系电话</label>
			</div>
        	<div>
        		<input name="phone" class="easyui-textbox user_edit_input" data-options="width:250" value="${profile.phone }">
        	</div>
       	</div>
       	<div class="break_line"></div>
       	<div class="fitem">
       		<div class="user_edit_label float_left">
				<label>旧密码</label>
			</div>
        	<div>
        		<input name="oldPassword" class="easyui-textbox user_edit_input js_pass_clear" data-options="width:250" required="true" value="">
        	</div>
       	</div>
       	<div class="break_line"></div>
       	<div class="fitem">
       		<div class="user_edit_label float_left">
				<label>新密码</label>
			</div>
        	<div>
        		<input id="js_profile_pass" type="password" name="newPassword" data-options="width:250" class="easyui-textbox user_edit_input js_pass_clear" value="">
        	</div>
       	</div>
       	<div class="break_line"></div>
       	<div class="fitem">
       		<div class="user_edit_label float_left">
				<label>确认新密码</label>
			</div>
        	<div>
        		<input id="js_profile_pass_confirm" type="password" name="confirmNewPassword" class="easyui-textbox user_edit_input js_pass_clear" data-options="width:250"  value="">
        	</div>
       	</div>
       	<div class="break_line"></div>
	</form>
	<input id="js_hidden_url" type="hidden"/>
</div>
<div id="js_usr_profile_update-buttons">
	<a href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok" onclick="rating.menu.submitProfile()" style="width:90px">保存</a>
	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')" style="width:90px">取消</a>
</div>