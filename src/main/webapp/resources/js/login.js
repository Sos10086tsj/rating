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

$(function(){
	if (var_js_authorised == 1) {
		$('#dd').dialog({
			title : '授权提醒',
			width : 350,
			height : 180,
			closed : false,
			cache : false,
			modal : true,
			closable : false,
			buttons : [ {
				text : '确定',
				handler : function() {
					$('#dd').dialog('close');
				}
			} ]
		});
	}else if(var_js_authorised == -1){
		$('#dd').dialog({
			title : '授权提醒',
			width : 350,
			height : 80,
			closed : false,
			cache : false,
			modal : true,
			closable : false
		});
	}
});