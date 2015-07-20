rating.usermgmt = {
	//创建用户
	newUser : function(){
		$('#js_username').textbox('readonly',false);
		$("#js_pass_remind").show();
		$('#js_user_edit').form('clear');
		$("#js_hidden_url").val(ctx + '/system/user/create');
		$('#dlg').dialog('open').dialog('setTitle','新建用户');
		//url = 'save_user.php';
	},
	//修改用户
	editUser : function(){
		var row = $('#dg').datagrid('getSelected');
		if (row){
			$("#js_username").textbox('readonly',true);
			$("#js_pass_remind").hide();
			$('#js_user_edit').form('load',row);
			$("#js_hidden_url").val(ctx + '/system/user/update');
			$('#dlg').dialog('open').dialog('setTitle','修改用户');
		}
	},
	saveUser : function(){
		var url = $("#js_hidden_url").val();
		 $('#js_user_edit').form('submit',{
		 	url : url,
		 	onSubmit: function(){
            	return $(this).form('validate');
            },
            success: function(result){
            	var result = eval('('+result+')');
            	if (result.errorMsg){
            		 $.messager.show({
            		 	title: 'Error',
            		 	msg: result.errorMsg
            		 });
            	}else{
            		$('#dlg').dialog('close');
            		$('#dg').datagrid('reload');
            	}
            }
		 });
	}
};

$(function(){
	
});