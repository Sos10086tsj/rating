rating.usermgmt = {
	//创建用户
	newUser : function(){
		$('#dlg').dialog('open').dialog('setTitle','New User');
		$('#fm').form('clear');
		//url = 'save_user.php';
	},
	//修改用户
	editUser : function(){
		var row = $('#dg').datagrid('getSelected');
		if (row){
			$('#dlg').dialog('open').dialog('setTitle','Edit User');
			 $('#fm').form('load',row);
			 url = 'update_user.php?id='+row.id;
		}
	},
	saveUser : function(){
		var url = ctx + '/system/user/create';
		 $('#fm').form('submit',{
		 	url: url,
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