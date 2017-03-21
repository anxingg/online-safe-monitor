// JavaScript Document
$(document).ready(function(){
	//保存外呼功能设置
	$("#saveOutcall").click(function(){
		saveOutcallSet();
        return false;
    });
	
//	alert("1");
	//读取外呼功能的初始化设置
	initOutcallFun();
});


//ivr初始化设置
function initOutcallFun(){
	
//	alert("999");
	$.ajax({
	    url : basePath + "platform/initOutcallFunction.action",
	    type : "post",
	    dataType : "text",
//	    data : paramData,
	    success : function(data) {
//		alert(data);
		    if ( data!==null ) {
		    	var obj = eval( "(" + data + ")" );//转换后的JSON对象
		    	
		    	if(obj.isEnableOutcall==1) {
		    		//不启用外呼
		    		document.getElementById("isEnableOutcall_1").checked = true;
		    	}else{
		    		//启用外呼
		    		document.getElementById("isEnableOutcall_2").checked = true;
		    		
		    		//将启用选项显示出来
		    		 $(".shos").show();
		    		 
		    		if(obj.customScript==1)
		    		document.getElementById("customScript").checked = true;
		    		if(obj.customData==1)
		    		document.getElementById("customData").checked = true;
		    		if(obj.definePhone==1)
		    		document.getElementById("definePhone").checked = true;
		    		if(obj.forecastPhone==1)
		    		document.getElementById("forecastPhone").checked = true;
		    	}
		    	
		    	
		    } else {
//			    art.dialog.alert("IVR和ACD功能设置！");
		    }
	    }
	});
}

function saveOutcallSet(){
	
	var isEnable = document.getElementsByName("isEnableOutcall");
	//获取是否启用外呼功能    1：不启用 2：启用
	var isEnableValue;
	for(var i=0;i<isEnable.length;i++){
		if(isEnable[i].checked) {
//		alert(zj[i].value);
		isEnableValue=isEnable[i].value;
		}
	}
	
//	alert(isEnableValue);
	
	if(isEnableValue==2){
		//如果启用外呼
		var customScript=document.getElementById("customScript").checked==true?1:0;
		var customData=document.getElementById("customData").checked==true?1:0;
		var definePhone=document.getElementById("definePhone").checked==true?1:0;
		var forecastPhone=document.getElementById("forecastPhone").checked==true?1:0;
	}else{
		//如果不启用外呼
		var customScript=0;
		var customData=0;
		var definePhone=0;
		var forecastPhone=0;
	}
	
	
	var paramData = {
	    'outcallFun.isEnableOutcall' : isEnableValue,
	    'outcallFun.customScript' : customScript,
	    'outcallFun.customData' : customData,
	    'outcallFun.definePhone' : definePhone,
	    'outcallFun.forecastPhone' : forecastPhone
	   
	};
	$.ajax({
	    url : basePath + "platform/saveOutcallFunction.action",
	    type : "post",
	    dataType : "text",
	    data : paramData,
	    success : function(data) {
//		alert(data);
		    if (1 == data) {
		    	art.dialog.alert("外呼功能设置成功！", function(){
		    	});
		    } else {
			    art.dialog.alert("外呼功能设置失败！");
		    }
	    }
	});
}
