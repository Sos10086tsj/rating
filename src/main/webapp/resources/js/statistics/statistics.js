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
			var symble = "(查看)";
			var abUrl = "<a href=\"javascript:void(0)\" onclick=\"rating.addTab('组员投票结果','"
			+ ctx + "/rating/statistics/detail/" + ab + "')\">" 
			+ '组员投票结果' + symble + "</a>        ";
			var cdUrl = "<a href=\"javascript:void(0)\" onclick=\"rating.addTab('组长/总体组投票结果','"
			+ ctx + "/rating/statistics/detail/" + cd + "')\">" 
			+ '组长/总体组投票结果' + symble + "</a>        ";
			
			var chart = "<a href=\"javascript:void(0)\" onclick=\"rating.addTab('饼图','"
			+ ctx + "/rating/statistics/chart/demo')\">" 
			+ '饼图' + symble + "</a>        ";
			
			
			link = abUrl + cdUrl + chart;
		}
		return link;
	},
	
	detailNameFormat : function(value, rec, index){
//		console.info("value:"+value);
//		console.info("rec:"+rec);
//		console.info("index:"+index);
		var link = "<a href=\"javascript:void(0)\" onclick=\"rating.addTab('投票明细--" 
		+ value + "','" + ctx + "/rating/statistics/" + $("#js_statistics_detail_tmpl_id_hidden").val() 
		+  "/" + rec.user_id  + "')\">" + value + "</a>        ";
		return link;
	}
};

$(function(){

});