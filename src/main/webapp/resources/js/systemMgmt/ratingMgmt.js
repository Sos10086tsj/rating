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
				var single  = "<a href=\"javascript:void(0)\" onclick=\"rating.addTab('" + template.name + "-权重','" + ctx + "/rating/template/weight/" + template.id + "')\">" 
				+ template.name + symble + "</a>        ";
				link += single;
			}
		}
		return link;
	}
};

$(function(){
	
});