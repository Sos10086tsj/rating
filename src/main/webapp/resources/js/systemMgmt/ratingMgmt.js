rating.ratingmgmt = {
	//创建投票
	newRating : function(){
//		$('#js_rating_mgmt_edit').form('clear');
//		$(".js_templateIds").each(function(){
//			$(this).attr("checked", "checked");
//		});
		$("#js_rating_name").val("");
		$("#js_rating_eff_from").val("");
		$("#js_rating_eff_to").val("");
		$('#js_rating_mgmt_dlg').dialog('open').dialog('setTitle','新建投票');
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
		 $('#js_rating_mgmt_edit').form('submit',{
		 	url: ctx + '/system/rating/create',
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
            		$('#js_rating_mgmt_dlg').dialog('close');
            		$('#js_rating_mgmt_dg').datagrid('reload');
            	}
            }
		 });
	},
	
	//rating已有模板format
	templateFormat:function(value, rec, index){
		var link = "";
		var symble = "(权重)";
		if(value && value.length > 0){
			for(var i = 0; i < value.length; i++){
				var template = value[i];
				var single  = "<a href=\"javascript:void(0)\" onclick=\"rating.addTab('" + template.label + "-权重','" + ctx + "/rating/template/weight/" + template.value + "')\">" 
				+ template.label + symble + "</a>        ";
				link += single;
			}
		}
		return link;
	},
	
	//权重维护
	groupRowFormat:function(value,rows){
		var percent = 0;
		for(var i = 0; i < rows.length; i++){
			percent += parseFloat(rows[i].weight);
		}
		var title = rows[0].category + '（' + percent + "%" + '）';
		if(percent > 100){
			$.messager.alert({
				title:'提示',
				msg: '<font color="#FF0000">' + rows[0].category + '</font>' + '得分项总权重超过100%，请确认无误后再提交保存！'
			});
		}
		return title;
	},
	
	weightEditIndex : null,
	//开始编辑得分项权重
	onWeightDbClickRow : function(index){
		if (rating.ratingmgmt.weightEditIndex != index){
			if (rating.ratingmgmt.endWeightEditing()){
				$('#js_rating_weight_mgmt_dg').datagrid('selectRow', index).datagrid('beginEdit', index);
					rating.ratingmgmt.weightEditIndex = index;
			} else {
				$('#js_rating_weight_mgmt_dg').datagrid('selectRow', rating.ratingmgmt.weightEditIndex);
			}
		}
	},
	//结束权重编辑
	endWeightEditing:	function (){
		if (rating.ratingmgmt.weightEditIndex == undefined){
			return true;
		}
		if ($('#js_rating_weight_mgmt_dg').datagrid('validateRow', rating.ratingmgmt.weightEditIndex)){
			$('#js_rating_weight_mgmt_dg').datagrid('endEdit', rating.ratingmgmt.weightEditIndex);
			rating.ratingmgmt.weightEditIndex = undefined;
			return true;
		} else {
			return false;
		}
	},
	//临时保存编辑
	acceptWeight:function(){
		if (rating.ratingmgmt.endWeightEditing()){
			$("#js_rating_weight_mgmt_dg").datagrid('acceptChanges');
		}
	},
	//提交编辑结果
	submitWeight:function(){
		rating.ratingmgmt.endWeightEditing();
		var dg = $("#js_rating_weight_mgmt_dg");
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
			url:ctx + '/rating/weight/update',
			data: {
				tmplId : glb_tmpl_id,
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
	//增加得分项
	addWeight:function(categoryCode){
		if (rating.ratingmgmt.endWeightEditing()){
			$('#js_rating_weight_mgmt_dg').datagrid('appendRow',{'categoryCode':categoryCode, weight:0});
			rating.ratingmgmt.weightEditIndex = $('#js_rating_weight_mgmt_dg').datagrid('getRows').length-1;
			$('#js_rating_weight_mgmt_dg').datagrid('selectRow',rating.ratingmgmt.weightEditIndex).datagrid('beginEdit', rating.ratingmgmt.weightEditIndex);
			$("#js_selected_category_hidden").val(categoryCode);
		}
	},
	//删除得分项
	removeWeight: function(){
		if (rating.ratingmgmt.weightEditIndex == undefined){
			return;
		};
		var row = $('#js_rating_weight_mgmt_dg').datagrid('getRowIndex',rating.ratingmgmt.weightEditIndex);
		$("#js_selected_category_hidden").val(row.categoryCode);
		$('#js_rating_weight_mgmt_dg').datagrid('cancelEdit', rating.ratingmgmt.weightEditIndex).datagrid('deleteRow', rating.ratingmgmt.weightEditIndex);
		rating.ratingmgmt.weightEditIndex = undefined;
	}
};

$(function(){
	
});