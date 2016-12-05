var userRatingByExcel = {
	uploadVoteExcel : function(){
		var fileName = uploadFile.getText();
		if(userRatingByExcel.validationExcel(fileName)){
			//上传附件
//			$("#tag_file_upload_form").submit(); 
			var ajax_option={
					type: 'post',
		            url: ctx + '/rating/uploadVoteExcel/' + glb_tmplId,
		            beforeSend:rating.ajax.loading(),
		            success: function(data) {
		            	$('#js_rating_excel_id').hide();
		            	if(typeof data != 'undefined'){
		            		data = $.parseJSON(data);
		            		if(data.success){
		            			//头部
		            			var tableThead = $('#js_rating_excel_result_table_id thead');
		            			tableThead.empty();
		            			var nameTr = $("<tr></tr>");
		            			nameTr.appendTo(tableThead);
		            			var scorerTd=$("<th>得分人</th>");
		            			scorerTd.appendTo(nameTr);
		            			
		            			var options = data.options;
		            			for(var i in options){
		            				var option = options[i];
		            				var titleTd=$("<th>" + option.label + "</th>");
		            				titleTd.appendTo(nameTr);
		            			}
		            			//得分情况
		            			var tableBody = $('#js_rating_excel_result_table_id tbody');
		            			tableBody.empty();

		            			var results = data.results.success;
		            			for(var i in results){
		            				var rowTr = $("<tr></tr>");
			            			rowTr.appendTo(tableBody);
		            				
		            				var result = results[i];
		            				var nameTd = $("<td>" + result.name +"</td>");
		            				nameTd.appendTo(rowTr);
		            				
		            				var scores = result.scores;
		            				for(var j in scores){
		            					var score = scores[j];
		            					var scoreTd = $("<td>" + score +"</td>");
		            					scoreTd.appendTo(rowTr);
		            				}
		            			}
		            			
		            			var ignores = data.results.ignore;
		            			var ignoresHtml = '';
		            			if(typeof ignores != undefined && null != ignores && ignores.length > 0){
		            				for(var i in ignores){
		            					ignoresHtml += ignores[i] 
		            							+ '<br/>';
		            				}
		            				$('#js_rating_excel_result_detail_id').html(ignoresHtml);
		            				$('#js_rating_excel_result_id').show();
		            			}else{
		            				$('#js_rating_excel_result_detail_id').html(ignoresHtml);
		            				$('#js_rating_excel_result_id').hide();
		            			}
		            			
		            			$('#js_rating_excel_id').show();
		            			rating.ajax.stopLoading();
		            			$.messager.alert({
				    				title:'提示',
				    				msg:'保存成功'
				    			});
		            			return false;
		            		}
		            	}
		            	$.messager.alert({
		    				title:'提示',
		    				msg:'保存失败，请重试'
		    			});
		            	return false;
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