rating.statistics = {
	//投票列表页href格式
	tempalteFormat : function(value, rec, index){
		var link = "";
		if(value && value.length > 0){
			var ab = "";
			var cd = "";
			for(var i = 0; i < value.length; i++){
				var vote = value[i];
				if(vote.code == 'A' || vote.code == 'B'){
					ab += vote.id + ',';
				}else{
					cd += vote.id + ',';
				}
				
//				var single  = "<a href=\"javascript:void(0)\" onclick=\"rating.addTab('" 
//				+ vote.name + "投票统计结果','" + ctx + "/rating/statistics/detail/" + vote.id + "')\">" 
//				+ vote.name + symble + "</a>        ";
//				link += single;
			}
			//var symble = "(查看)";
			var symble = "";
			var abUrl = "<a href=\"javascript:void(0)\" onclick=\"rating.addTab('组员结果','"
			+ ctx + "/rating/statistics/detail/" + ab + "')\">" 
			+ '组员结果' + symble + "</a>                ";
			var cdUrl = "<a href=\"javascript:void(0)\" onclick=\"rating.addTab('组长/总体组结果','"
			+ ctx + "/rating/statistics/detail/" + cd + "')\">" 
			+ '组长/总体组结果' + symble + "</a>                ";
			
			var chart = "<a href=\"javascript:void(0)\" onclick=\"rating.addTab('统计图','"
			+ ctx + "/rating/statistics/chart/" + rec.id + "')\">" 
			+ '统计图' + symble + "</a>                ";
			
			var exportExcel = "<a href='" + ctx + "/rating/statistics/export/" + rec.id + "'>" + '导出报表' + "</a>";
			
			link = abUrl + cdUrl + chart + exportExcel;
		}
		return link;
	},
	
	detailNameFormat : function(value, rec, index){
//		console.info("value:"+value);
//		console.info("rec:"+rec);
//		console.info("index:"+index);
		var link = "<a href=\"javascript:void(0)\" onclick=\"rating.addTab('投票明细--" 
		+ value + "','" + ctx + "/rating/statistics/" + $("#js_statistics_user_rating_id_hidden").val() 
		+  "/" + rec.user_id  + "')\">" + value + "</a>        ";
		return link;
	}
};

$(function(){

});