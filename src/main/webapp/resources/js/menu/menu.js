rating.menu = {
	open: function(url){
		console.log(url);
		//$("#js_login_form").submit();
	}
};

$(function(){
	$("#js_menu_tree").tree({
		onClick: function(node){
			console.log(1)
			if($("#js_menu_tree").tree('isLeaf',node.target)){
				console.log(1)
			}else{
				console.log(12)
			}
		}
	});
});