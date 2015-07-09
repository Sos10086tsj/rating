rating.userrating = {
	//投票列表页href格式
	tempalteFormat : function(value, rec, index){
		var link = "";
		if(value && value.length > 0){
			for(var i = 0; i < value.length; i++){
				var vote = value[i];
				var symble = "";
				if(vote.voted){//已投
					symble = "(重投)";
				}else{
					symble = "(投票)";
				}
				//var single = "<a href='" + ctx + "/rating/vote/'" + vote.id + ">" + vote.name + symble + "</a>  ";<A href="javascript:void(0)
				var single  = "<a href=\"javascript:void(0)\" onclick=\"rating.addTab('" + vote.name + "投票','" + ctx + "/rating/vote/" + vote.id + "')\">" 
				+ vote.name + symble + "</a>        ";
				link += single;
			}
		}
		
		//个人明细
		/*
		var personUrl = "<a href=\"javascript:void(0)\" onclick=\"rating.addTab('我的得分','" 
		 + ctx + "/rating/statistics/" + rec.id 
		+  "/" + $("#js_vote_user_id_hidden").val()  + "')\">我的得分</a>        ";*/
		var personUrl = "<a href=\"javascript:void(0)\">我的得分(未开启)</a>";
		
		link += personUrl;
		
		return link;
	},
	
	editIndex : undefined,
	onDbClickRow : function(index){
		if (rating.userrating.editIndex != index){
			if (rating.userrating.endEditing()){
				$('#js_rating_vote_dg').datagrid('selectRow', index).datagrid('beginEdit', index);
					rating.userrating.editIndex = index;
			} else {
				$('#js_rating_vote_dg').datagrid('selectRow', rating.userrating.editIndex);
			}
		}
	},
	endEditing:	function (){
		if (rating.userrating.editIndex == undefined){
			return true;
		}
		if ($('#js_rating_vote_dg').datagrid('validateRow', rating.userrating.editIndex)){
//			var ed = $('#js_rating_vote_dg').datagrid('getEditor', {index:rating.userrating.editIndex,field:'itemid'});
//			var productname = $(ed.target).combobox('getText');
//			$('#js_rating_vote_dg').datagrid('getRows')[rating.userrating.editIndex]['productname'] = productname;
			$('#js_rating_vote_dg').datagrid('endEdit', rating.userrating.editIndex);
			rating.userrating.editIndex = undefined;
			return true;
		} else {
			return false;
		}
	},
	accept:function(){
		if (rating.userrating.endEditing()){
			$("#js_rating_vote_dg").datagrid('acceptChanges');
		}
	},
	//增加一个投票
	addVote:function(){
		if (rating.userrating.endEditing()){
			$('#js_rating_vote_dg').datagrid('appendRow',{});
			rating.userrating.editIndex = $('#js_rating_vote_dg').datagrid('getRows').length-1;
			$('#js_rating_vote_dg').datagrid('selectRow', rating.userrating.editIndex).datagrid('beginEdit', rating.userrating.editIndex);
		}
	},
	//删除行
	removeVote: function(){
		if (rating.userrating.editIndex == undefined){
			return;
		};
		$('#js_rating_vote_dg').datagrid('cancelEdit', rating.userrating.editIndex).datagrid('deleteRow', rating.userrating.editIndex);
		rating.userrating.editIndex = undefined;
	},
//	accept: function(){
//		if (rating.userrating.endEditing()){
//			$('#js_rating_vote_dg').datagrid('acceptChanges');
//		}
//	},
	//提交
	vote:function(){
		rating.userrating.endEditing();
		var datasource = eval($("#js_option_json_hidden").html());
		var dg = $("#js_rating_vote_dg");
		dg.datagrid('acceptChanges');
		var rows = dg.datagrid("getRows");
		var items = new Array();
		for(var i=0;i<rows.length;i++){
			var item = {};
			var rowData = rows[i];
			for(attribute in rowData){
				item[attribute] = rowData[attribute];
			}
			items.push(item);
		}
		//console.log("data:" + JSON.stringify(items));
		$.ajax({ 
			type: 'POST',  
			url:ctx + '/rating/userVote/' + $("#js_tmpl_id_hidden").html(),
			data: {votes : JSON.stringify(items)},
			timeout : 600000,
			beforeSend : rating.ajax.loading(),
			success: function(robj){ 
				rating.ajax.stopLoading();
				$.messager.alert({
						title:'提示',
						msg:'保存成功'
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
//		$.post(ctx + '/rating/userVote/' + $("#js_tmpl_id_hidden").html(),{"votes":JSON.stringify(items)}, 
//			function(data, textStatus, jqXHR){
//				$.messager.alert({
//						title:'提示',
//						msg:'保存成功'
//				});
//			}
//		);
	}
};

$(function(){
//	$("#js_rating_vote_dg").datagrid({
//		onClickRow: function (rowIndex, rowData){
//			
//		}
//	});
});