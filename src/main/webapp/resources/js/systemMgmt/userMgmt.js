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
	},
	
	//操作栏format
	operationFieldFormat : function(value, rec, index){
		var link = "<a href=\"javascript:void(0)\" onclick=\"rating.usermgmt.showDeleteUserTab("
		+ rec.id + ")\">删除</a>";
		return link;
	},
	
	showDeleteUserTab : function(userId){
		$.messager.confirm('提示','确认删除用户？',function(r){
    		if (r){
        		$.ajax({ 
					type: 'POST',  
					url:ctx + '/system/user/delete',
			data: {userId : userId},
			timeout : 600000,
			beforeSend : rating.ajax.loading(),
			success: function(robj){ 
				rating.ajax.stopLoading();
				$.messager.alert({
						title:'提示',
						msg:'删除成功'
				});
				$('#dg').datagrid('reload');
			},
			failure: function(error){
				rating.ajax.stopLoading();
				$.messager.alert({
						title:'提示',
						msg:'删除失败，请重试'
				});
			}
		});
    		}
		});
	}
};

$(function(){
	
});