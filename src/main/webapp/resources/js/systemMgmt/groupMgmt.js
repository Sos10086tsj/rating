rating.groupmgmt = {
	//创建用户
	newGroup : function(){
		$('#js_group_edit_form').form('clear');
		$('#js_group_edit_dg').dialog('open').dialog('setTitle','新建组');
	},
	//修改用户
	editGroup : function(){
		var row = $('#js_mgmt_group_dg').datagrid('getSelected');
		if (row){
			$("#js_group_id_hidden").val(row.id);
			$('#js_group_edit_form').form('load',row);
			$('#js_group_edit_dg').dialog('open').dialog('setTitle','修改组');
		}
	},
	saveGroup : function(){
		 $('#js_group_edit_form').form('submit',{
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
            		$('#js_group_edit_dg').dialog('close');
            		$('#js_mgmt_group_dg').datagrid('reload');
            	}
            }
		 });
	}
};

$(function(){
	
});