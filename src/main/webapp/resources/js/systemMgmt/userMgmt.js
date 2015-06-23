rating.usermgmt = {
	init : function(){
		$('#dg').edatagrid({
                url: ctx + '/syste/user/list',
                saveUrl: 'save_user.php',
                updateUrl: 'update_user.php',
                destroyUrl: 'destroy_user.php'
            });
	}
};

$(function(){
	rating.usermgmt.init();
});