rating.ratingmgmt = {
	//跳转新建页
	newRating : function(){
		window.open(ctx + '/system/rating/create' , '_blank');
	},
	//修改用户
	editRating : function(){
		var row = $('#dg').datagrid('getSelected');
		if (row){
			$('#dlg').dialog('open').dialog('setTitle','Edit User');
			$('#fm').form('load',row);
			$('#fm').attr("url",ctx + '/system/rating/update');
		}
	},
	saveRating : function(){
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