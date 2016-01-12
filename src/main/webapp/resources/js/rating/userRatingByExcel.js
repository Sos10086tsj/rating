var userRatingByExcel = {
	uploadVoteExcel : function(){
		var fileName = uploadFile.getText();
		if(userRatingByExcel.validationExcel(fileName)){
			//上传附件
//			$("#tag_file_upload_form").submit(); 
			var ajax_option={
					type: 'post',
		            url: ctx + '/rating/uploadVoteExcel/' + glb_tmplId,
		            success: function(data) { 
		            	$.messager.alert({
		    				title:'提示',
		    				msg:'保存成功'
		    			});
		            },
		            failure : function(){
		            	$.messager.alert({
		    				title:'提示',
		    				msg:'保存失败，请重试'
		    			});
		            }
			};
			$('#tag_file_upload_form').ajaxSubmit(ajax_option);
			return false;
		}else{
			$.messager.alert({
				title:'提示',
				msg:'请上传excel'
			});
		}
	},
	
	validationExcel : function(fileName){
		if(fileName.endWith(".xls") || fileName.endWith(".xlsx")){
			return true;
		}
		return false;
	}
}

$(function(){
	
})