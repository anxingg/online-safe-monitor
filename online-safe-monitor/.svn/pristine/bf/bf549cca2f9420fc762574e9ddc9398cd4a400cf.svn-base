$(document).ready(function(){
	//保存黑名单设置
	$("#saveBlacklist").click(function(){
		saveBlacklistSet();
        return false;
    });
	//读取外呼功能的初始化设置
	initBlacklistFun();
});
/**
 * 初始化黑名单
 */
function initBlacklistFun(){
	$.ajax({
	    url : basePath + "platform/blacklist_initBlacklistFun.action",
	    type : "post",
	    dataType : "text",
	    success : function(data) {
		    if ( data!==null ) {
		    	var obj = eval( "(" + data + ")" );//转换后的JSON对象
		    	if(obj.isEnableBlacklist==2) {
		    		//启用黑名单
		    		document.getElementById("isEnableBlacklist_2").checked = true;
		    	}else{
		    		//不启用黑名单
		    		document.getElementById("isEnableBlacklist_1").checked = true;
		    	}
		    } else {
		    }
	    }
	});
}

function saveBlacklistSet(){
	var isEnable = document.getElementsByName("isEnableBlacklist");
	//获取是否启用黑名单    1：不启用 2：启用
	var isEnableValue;
	for(var i=0;i<isEnable.length;i++){
		if(isEnable[i].checked) {
		isEnableValue=isEnable[i].value;
		}
	}
	var paramData = {
	    'blacklistFun.isEnableBlacklist' : isEnableValue
	};
	$.ajax({
	    url : basePath + "platform/blacklist_saveBlacklistSet.action",
	    type : "post",
	    dataType : "text",
	    data : paramData,
	    success : function(data) {
		    if (1 == data) {
		    	art.dialog.alert("黑名单设置成功！");
		    } else {
			    art.dialog.alert("黑名单设置失败！");
		    }
	    }
	});
}
