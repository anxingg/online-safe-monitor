// JavaScript Document
$(document).ready(function(){
	//保存坐席功能设置
	$("#saveIVRSet").click(function(){
		saveIVRSet();
        return false;
    });
	
//	alert("1");
	//读取ivr功能的初始化设置
	initIVRSet();
});


//ivr初始化设置
function initIVRSet(){
	
	$.ajax({
	    url : basePath + "platform/initIVRSet.action",
	    type : "post",
	    dataType : "text",
////	    data : paramData,
	    success : function(data) {
////		alert(data);
		    if ( data!==null ) {
		    	var obj = eval( "(" + data + ")" );//转换后的JSON对象
		    	if(obj.isSeatRecord==1)
		    		document.getElementById("isSeatRecord").checked = true;
		    	if(obj.isCustomVoice==1)
		    		document.getElementById("isCustomVoice").checked = true;
		    	if(obj.remotePhoneZero==1)
		    		document.getElementById("remotePhoneZero").checked = true;
		    	if(obj.isLeaveMessage==1)
		    		document.getElementById("isLeaveMessage").checked = true;
		    	if(obj.isSatifyCheck==1)
		    		document.getElementById("isSatifyCheck").checked = true;
		    	if(obj.isMultiAccessNum==1)
		    		document.getElementById("isMultiAccessNum").checked = true;
		    	if(obj.isBlack==1)
		    		document.getElementById("isBlack").checked = true;
 	

		    	
		    	
		    } else {
//			    art.dialog.alert("IVR和ACD功能设置！");
		    }
	    }
	});
}

function saveIVRSet(){
	
	var isSeatRecord=document.getElementById("isSeatRecord").checked==true?1:0;
	var isCustomVoice=document.getElementById("isCustomVoice").checked==true?1:0;
	var remotePhoneZero=document.getElementById("remotePhoneZero").checked==true?1:0;
	var isLeaveMessage=document.getElementById("isLeaveMessage").checked==true?1:0;
	var isSatifyCheck=document.getElementById("isSatifyCheck").checked==true?1:0;
	var isMultiAccessNum=document.getElementById("isMultiAccessNum").checked==true?1:0;
	var isBlack=document.getElementById("isBlack").checked==true?1:0;
	
//	alert(isBlack);
	var paramData = {
	    'ivrSet.isSeatRecord' : isSeatRecord,
	    'ivrSet.isCustomVoice' : isCustomVoice,
	    'ivrSet.remotePhoneZero' : remotePhoneZero,
	    'ivrSet.isLeaveMessage' : isLeaveMessage,
	    'ivrSet.isSatifyCheck' : isSatifyCheck,
	    'ivrSet.isMultiAccessNum' : isMultiAccessNum,//
	    'ivrSet.isBlack' : isBlack
	   
	};
	$.ajax({
	    url : basePath + "platform/saveIVRSet.action",
	    type : "post",
	    dataType : "text",
	    data : paramData,
	    success : function(data) {
//		alert(data);
		    if (1 == data) {
		    	art.dialog.alert("IVR和ACD设置成功！", function(){
		    		//var getDataTable = art.dialog.data("getDataTable");
		    		//getDataTable();
		    	});
		    } else {
			    art.dialog.alert("IVR和ACD设置失败！");
		    }
	    }
	});
}
