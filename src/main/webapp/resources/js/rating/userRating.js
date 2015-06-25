rating.userrating = {
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
				var single = "<a href='" + ctx + "/rating/vote/'" + vote.id + ">" + vote.name + symble + "</a>  ";
				link += single;
			}
		}
		return link;
	}
};