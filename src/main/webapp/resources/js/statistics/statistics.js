rating.statistics = {
	//投票列表页href格式
	tempalteFormat : function(value, rec, index){
		var link = "";
		if(value && value.length > 0){
			for(var i = 0; i < value.length; i++){
				var vote = value[i];
				var symble = "(查看)";
				var single  = "<a href=\"javascript:void(0)\" onclick=\"rating.addTab('" 
				+ vote.name + "投票统计结果','" + ctx + "/rating/statistics/detail/" + vote.id + "')\">" 
				+ vote.name + symble + "</a>        ";
				link += single;
			}
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