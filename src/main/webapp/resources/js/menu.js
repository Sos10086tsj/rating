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
		//验证密码
		var pass = $("#js_profile_pass").val();
		var passConfirm = $("#js_profile_pass_confirm").val();
		if(pass.length > 0){
			var msg = "";
			if(!(passConfirm.length > 0 && passConfirm == pass)){
				msg = "确认密码未填或者不一致";
				$.messager.alert({
            		title: '提示',
            		msg: msg
            	});
            	return;
			}
		}
		$('#js_user_profile_edit').form('submit',{
		 	onSubmit: function(){
            	return $(this).form('validate');
            },
            success: function(result){
            	var result = eval('('+result+')');
            	if (result.errorMsg){
            		 $.messager.alert({
            		 	title: '提示',
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