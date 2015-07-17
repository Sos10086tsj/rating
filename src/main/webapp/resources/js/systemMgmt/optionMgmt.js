rating.optionmgmt = {
	//得分项分组显示
	groupRowFormat:function(value,rows){
		var percent = 0;
		for(var i = 0; i < rows.length; i++){
			percent ++;
		}
		var title = rows[0].categoryName + '（' + percent + '）';
		return title;
	},
	
	//得分项弹窗
	newOption : function(){
		$('#js_option_edit_form').form('clear');
		$('#js_option_edit_dg').dialog('open').dialog('setTitle','新增得分项');
	},
	//保存得分项
	saveOption : function(){
		$('#js_option_edit_form').form('submit',{
			url : ctx + '/system/option/update',
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
            		$.messager.confirm('提示', '保存成功', function(r){
						$('#js_option_edit_dg').dialog('close');
            			$('#js_mgmt_option_dg').datagrid('reload');
					});
            	}
            }
		});
	},
	//修改得分项
	editOption:function(){
		var row = $('#js_mgmt_option_dg').datagrid('getSelected');
		if (row){
			$('#js_option_edit_form').form('load',row);
			$('#js_option_edit_dg').dialog('open').dialog('setTitle','修改得分项');
		}
	}
};

$(function(){
	
});