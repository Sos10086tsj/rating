rating.usermgmt = {
	//创建用户
	newUser : function(){
		$('#dlg').dialog('open').dialog('setTitle','New User');
		$('#fm').form('clear');
		$('#fm').attr("url",ctx + '/system/user/create');
		//url = 'save_user.php';
	},
	//修改用户
	editUser : function(){
		var row = $('#dg').datagrid('getSelected');
		if (row){
			$('#dlg').dialog('open').dialog('setTitle','Edit User');
			$('#fm').form('load',row);
			$('#fm').attr("url",ctx + '/system/user/update');
		}
	},
	saveUser : function(){
		 $('#fm').form('submit',{
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