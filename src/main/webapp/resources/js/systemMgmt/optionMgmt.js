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
	},
	
	//操作栏format
	operationFieldFormat : function(value, rec, index){
		var link = "<a href=\"javascript:void(0)\" onclick=\"rating.optionmgmt.showDeleteOptionTab("
		+ rec.id + ")\">删除</a>";
		return link;
	},
	
	showDeleteOptionTab : function(id){
		$.messager.confirm('提示','确认删除得分项？',function(r){
    		if (r){
        		$.ajax({ 
					type: 'POST',  
					url:ctx + '/system/option/delete',
					data: {id : id},
					timeout : 600000,
					beforeSend : rating.ajax.loading(),
					success: function(robj){ 
						rating.ajax.stopLoading();
						$.messager.alert({
							title:'提示',
							msg:'删除成功'
						});
						$('#js_mgmt_option_dg').datagrid('reload');
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