var uploadFile = {
	init : function(){
		$("#tag_file_box").filebox({
		    buttonText: '选择文件'
		});
	},
	
	getText : function(){
		return $("#tag_file_box").filebox("getText")
	},
	
	getValue : function(){
		return $("#tag_file_box").filebox("getValue");
	}
}

$(function(){
	uploadFile.init();
});