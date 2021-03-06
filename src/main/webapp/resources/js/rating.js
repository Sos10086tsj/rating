var rating = {
	dateFormat : function(value, rec, index){
		if (value == null || value == '') {  
                return '';  
		}  
        var dt;  
        if (value instanceof Date) {  
        	dt = value;  
        } else {  
			dt = new Date(value);  
		}  
		return dt.format("yyyy-MM-dd"); //扩展的Date的format方法(上述插件实现) 
	},
	
	//增加tab方法
	addTab :function(title, url){
//		var tabs = parent.$('#js_tabs', parent.document);
//		if (tabs.tabs('exists', title)){
//        	tabs.tabs('select', title);
//        	rating.refreshTab({tabTitle:title,url:url});
//    	} else {
//        	var content = '<iframe scrolling="auto" frameborder="0"  src="'+url+'" style="width:100%;height:100%;"></iframe>';
//        	tabs.tabs('add',{
//            	title:title,
//            	content:content,
//            	closable:true
//        	});
//    	}
    	
    	var tabs = parent.$('#js_tabs', parent.document);
		if (tabs.tabs('exists', title)){
        	//tabs.tabs('select', title);
        	//rating.refreshTab({tabTitle:title,url:url});
			tabs.tabs('close', title); 
    	}
    	var content = '<iframe scrolling="auto" frameborder="0"  src="'+url+'" style="width:100%;height:100%;"></iframe>';
        	tabs.tabs('add',{
            	title:title,
            	content:content,
            	closable:true
        });
	},
	//刷新
	refreshTab: function(cfg){  
    	var refresh_tab = cfg.tabTitle?$('#js_tabs').tabs('getTab',cfg.tabTitle):$('#js_tabs').tabs('getSelected');  
    	if(refresh_tab && refresh_tab.find('iframe').length > 0){  
    		var _refresh_ifram = refresh_tab.find('iframe')[0];  
    		var refresh_url = cfg.url?cfg.url:_refresh_ifram.src;  
    		//_refresh_ifram.src = refresh_url;  
    		_refresh_ifram.contentWindow.location.href=refresh_url;  
    	}
	},
	
	formatCombobox:function(value, datasource){
		var ds = eval(datasource);
		for(var i=0; i < ds.length ; i++){
        	if(ds[i].value == value){
        		return ds[i].label;
        	}
   		}
    	return value;
	},
	
	ajax : {
		loading : function(){
			$("<div class=\"datagrid-mask\"></div>").css(
				{display:"block",width:"100%",height:$(window).height()}).appendTo("body");  
    			$("<div class=\"datagrid-mask-msg\"></div>").html("正在处理，请稍候。。。")
    			.appendTo("body").css(
    				{display:"block",
    				left:($(document.body).outerWidth(true) - 190) / 2,top:($(window).height() - 45) / 2});  
 		},
 		stopLoading : function(){
 			$(".datagrid-mask").remove();
 			$(".datagrid-mask-msg").remove(); 
 		}
	}
};

$(function(){
	//时间扩展扩展
	Date.prototype.format = function(format) {  
    var o = {  
        "M+" : this.getMonth() + 1, // 月  
        "d+" : this.getDate(), // 天  
        "h+" : this.getHours(), // 时  
        "m+" : this.getMinutes(), // 分  
        "s+" : this.getSeconds(), // 秒  
        "q+" : Math.floor((this.getMonth() + 3) / 3), // 刻  
        "S" : this.getMilliseconds() //毫秒  
    // millisecond  
    };
    if (/(y+)/.test(format))  
        format = format.replace(RegExp.$1, (this.getFullYear() + "")  
                .substr(4 - RegExp.$1.length));  
    for ( var k in o)  
        if (new RegExp("(" + k + ")").test(format))  
            format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k]  
                    : ("00" + o[k]).substr(("" + o[k]).length));  
    return format;  
	};
	
	//菜单点击事件
	$('#js_menu_tree').tree({
		onClick: function(node){
			if(node.attributes && node.attributes != undefined){
				rating.addTab(node.attributes.title,node.attributes.url);
			}
		}
	});
	

	// 扩展string startWith, endsWith
	String.prototype.endWith = function(str) {
		if (str == null || str == "" || this.length == 0
				|| str.length > this.length)
			return false;
		if (this.substring(this.length - str.length) == str)
			return true;
		else
			return false;
		return true;
	}
	String.prototype.startWith = function(str) {
		if (str == null || str == "" || this.length == 0
				|| str.length > this.length)
			return false;
		if (this.substr(0, str.length) == str)
			return true;
		else
			return false;
		return true;
	}
});