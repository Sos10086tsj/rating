rating.statistics = {
	//投票列表页href格式
	tempalteFormat : function(value, rec, index){
		var link = "";
		if(value && value.length > 0){
			for(var i = 0; i < value.length; i++){
				var vote = value[i];
				var symble = "";
				if(vote.voted){//已投
					symble = "(查看)";
				}else{
					symble = "(查看)";
				}
				//var single = "<a href='" + ctx + "/rating/vote/'" + vote.id + ">" + vote.name + symble + "</a>  ";<A href="javascript:void(0)
				var single  = "<a href=\"javascript:void(0)\" onclick=\"rating.addTab('" + vote.name + "投票','" + ctx + "/rating/vote/" + vote.id + "')\">" 
				+ vote.name + symble + "</a>        ";
				link += single;
			}
		}
		return link;
	}
};

$(function(){

});