rating.templatemgmt = {
	//得分项分组显示
	groupRowFormat:function(value,rows){
		var percent = 0;
		for(var i = 0; i < rows.length; i++){
			percent ++;
		}
		var title = rows[0].categoryName + '（' + percent + '）';
		return title;
	}
};

$(function(){
	
});