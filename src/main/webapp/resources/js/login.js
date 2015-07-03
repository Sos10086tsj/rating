rating.login = {
	login: function(){
		if($("#js_login_form").form('validate')){
			$("#js_login_form").submit();
		}
		//console.log("validate:" + $("#js_login_form").form('validate'));
	},
	
	keyDown : function(event){
		if(event.keyCode==13){
			rating.login.login();
		}
	}
};