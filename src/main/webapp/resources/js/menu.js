rating.menu = {
	logout: function(){
		$.messager.confirm('提示','确认退出？',function(r){
    		if (r){
        		window.location.href = ctx + "/logout";
    		}
		});
	},
	
	showProfile : function(){
		//$('#js_usr_profile_update').form('clear');
		$(".js_pass_clear").each(function(){
			$(this).textbox('clear');
		});
		//刷新数据
		$('#js_usr_profile_update').dialog('open').dialog('setTitle','修改个人信息');
	},
	
	submitProfile:function(){
		$('#js_user_profile_edit').form('submit',{
		 	onSubmit: function(){
            	return $(this).form('validate');
            },
            success: function(result){
            	var result = eval('('+result+')');
            	if (result.errorMsg){
            		 $.messager.alert({
            		 	title: 'Error',
            		 	msg: result.errorMsg
            		 });
            	}else{
            		$('#js_usr_profile_update').dialog('close');
            		$.messager.alert({
						title:'提示',
						msg:'保存成功'
					});
            	}
            }
		 });
	}
};