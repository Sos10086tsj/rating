rating.userrating = {
	//投票列表页href格式
	tempalteFormat : function(value, rec, index){
		var link = "";
		if(value && value.length > 0){
			for(var i = 0; i < value.length; i++){
				var vote = value[i];
				var symble = "";
				if(vote.voted){//已投
					symble = "(已投)";
				}else{
					symble = "(未投)";
				}
				//var single = "<a href='" + ctx + "/rating/vote/'" + vote.id + ">" + vote.name + symble + "</a>  ";<A href="javascript:void(0)
				var single  = "<a href=\"javascript:void(0)\" onclick=\"rating.addTab('" + vote.name + "投票','" + ctx + "/rating/vote/" + vote.id + "')\">" 
				+ vote.name + symble + "</a>        ";
				link += single;
			}
		}
		return link;
	},
	
	editIndex : undefined,
	onClickRow : function(){
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
		if (rating.userrating.editIndex == undefined){return true}
		if ($('#js_rating_vote_dg').datagrid('validateRow', rating.userrating.editIndex)){
//			var ed = $('#js_rating_vote_dg').datagrid('getEditor', {index:rating.userrating.editIndex,field:'itemid'});
//			var productname = $(ed.target).combobox('getText');
//			$('#js_rating_vote_dg').datagrid('getRows')[rating.userrating.editIndex]['productname'] = productname;
//			$('#js_rating_vote_dg').datagrid('endEdit', rating.userrating.editIndex);
			rating.userrating.editIndex = undefined;
			return true;
		} else {
			return false;
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
		if (rating.userrating.editIndex == undefined){return}
			$('#js_rating_vote_dg').datagrid('cancelEdit', rating.userrating.editIndex).datagrid('deleteRow', rating.userrating.editIndex);
			rating.userrating.editIndex = undefined;
	},
	//保存
	save: function(){
		if (rating.userrating.endEditing()){
			$('#js_rating_vote_dg').datagrid('acceptChanges');
		}
	},
	//提交
	vote:function(){
		rating.userrating.endEditing();
		var dg = $("#js_rating_vote_dg");
		var rows = dg.datagrid("getRows");
		for(var i=0;i<rows.length;i++){
			alert(rows[i].itemid);
		}
	}
};

$(function(){
//	$("#js_rating_vote_dg").datagrid({
//		onClickRow: function (rowIndex, rowData){
//			
//		}
//	});
});