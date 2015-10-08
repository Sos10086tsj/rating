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
			height : 150,
			closed : false,
			cache : false,
			modal : true,
			closable : false,
			buttons : [ {
				text : '确定',
				handler : function() {
					var pass = $("#js_mac_pass").val();
					if(pass.length <= 0){
						$.messager.alert({
							title:'提示',
							msg:'请输入激活码'
						});
					}
					$.ajax({ 
						type: 'GET',  
						async: false,
						url:ctx + '/authorise?macPass=' +pass,
						timeout : 600000,
						beforeSend : rating.ajax.loading(),
						success: function(data){ 
							rating.ajax.stopLoading();
							var success = $.parseJSON(data).obj.authorised;
							if(success == 0){
								$.messager.alert({
									title:'提示',
									msg:'恭喜您激活系统成功！'
								});
								$('#dd').dialog('close');
							}else{
								$.messager.alert({
									title:'提示',
									msg:'对不起，您的激活码错误！'
								});
							}
						},
						failure: function(error){
							rating.ajax.stopLoading();
							$.messager.alert({
									title:'提示',
									msg:'系统错误，请重试'
							});
						}
					});
				}
			} ]
		});
	}
});