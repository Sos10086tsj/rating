rating.templatemgmt = {
	//supp模板列表页操作按钮
	listOprFormat : function(value, rec, index){
		var link = "<a href=\"javascript:void(0)\" onclick=\"rating.addTab('" + value + "-管理','" + ctx + "/system/template/showedit/" + rec.id + "')\">" 
				+ value + "-管理</a>        ";
		return link;
	},
	//跳转某个模板时的option group
	editGroupRowFormat : function(value,rows){
		var percent = 0;
		for(var i = 0; i < rows.length; i++){
			percent += parseFloat(rows[i].weight);
		}
		var title = rows[0].categoryName + '（' + percent + "%" + '）';
		if(percent > 100){
			$.messager.alert({
				title:'提示',
				msg: '<font color="#FF0000">' + rows[0].categoryName + '</font>' + '得分项总权重超过100%，请确认无误后再提交保存！'
			});
		}
		return title;
	},
	//双击行进入编辑状态
	optionEditIndex : null,
	onSuppTmplDbClickRow : function(index){
		if (rating.templatemgmt.optionEditIndex != index){
			if (rating.templatemgmt.endOptionEditing()){
				$('#js_mgmt_tmpl_edit_dg').datagrid('selectRow', index).datagrid('beginEdit', index);
					rating.templatemgmt.optionEditIndex = index;
			} else {
				$('#js_mgmt_tmpl_edit_dg').datagrid('selectRow', rating.templatemgmt.optionEditIndex);
			}
		}
	},
	//结束编辑
	endOptionEditing:	function (){
		if (rating.templatemgmt.optionEditIndex == undefined){
			return true;
		}
		if ($('#js_mgmt_tmpl_edit_dg').datagrid('validateRow', rating.templatemgmt.optionEditIndex)){
			$('#js_mgmt_tmpl_edit_dg').datagrid('endEdit', rating.templatemgmt.optionEditIndex);
			rating.templatemgmt.optionEditIndex = undefined;
			return true;
		} else {
			return false;
		}
	},
	//本地保存
	acceptOption:function(){
		if (rating.templatemgmt.optionEditIndex){
			$("#js_mgmt_tmpl_edit_dg").datagrid('acceptChanges');
		}
	},
	//删除得分项
	removeOption: function(){
		if (rating.templatemgmt.optionEditIndex == undefined){
			return;
		};
		var row = $('#js_mgmt_tmpl_edit_dg').datagrid('getRowIndex',rating.templatemgmt.optionEditIndex);
		$('#js_mgmt_tmpl_edit_dg').datagrid('cancelEdit', rating.templatemgmt.optionEditIndex).datagrid('deleteRow', rating.templatemgmt.optionEditIndex);
		rating.templatemgmt.optionEditIndex = undefined;
	},
	//提交编辑结果
	submitOption:function(){
		rating.templatemgmt.endOptionEditing();
		var dg = $("#js_mgmt_tmpl_edit_dg");
		dg.datagrid('acceptChanges');
		var rows = dg.datagrid("getRows");
		var items = new Array();
		for(var i=0;i<rows.length;i++){
			var rowData = rows[i];
			var item = {
				id:rowData.id,
				weight:rowData.weight
			};
			items.push(item);
		}
		$.ajax({ 
			type: 'POST',  
			url:ctx + '/system/template/suppupdate/' + glb_supp_tmpl_id,
			data: {
				options : JSON.stringify(items)
			},
			timeout : 600000,
			beforeSend : rating.ajax.loading(),
			success: function(robj){ 
				rating.ajax.stopLoading();
				$.messager.confirm('提示', '保存成功', function(r){
					dg.datagrid('reload');
				});
			},
			failure: function(error){
				rating.ajax.stopLoading();
				$.messager.alert({
						title:'提示',
						msg:'保存失败，请重试'
				});
			}
		});
	},
	
	//增加一个投票
	addOption:function(){
		$('#js_mgmt_supp_tmpl_add_form').form('clear');
		$('#js_mgmt_supp_tmpl_add_dg').dialog('open').dialog('setTitle','添加得分项');
	},
	submitAddOption:function(){
		var url = ctx + '/system/template/suppadd/' + glb_supp_tmpl_id;
		 $('#js_mgmt_supp_tmpl_add_form').form('submit',{
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
            		$('#js_mgmt_supp_tmpl_add_dg').dialog('close');
            		$('#js_mgmt_tmpl_edit_dg').datagrid('reload');
            	}
            }
		 });
	}
};

$(function(){
	
});