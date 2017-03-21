// JavaScript Document
$(document).ready(function(){
	
	
	//保存坐席功能设置
	$("#saveSeatFunSet").click(function(){
    	saveSeatFunction();
        return false;
    });
	//点击话后处理事件
	$("#isAfterCallDeal").click(function(){
//		alert(this.checked);
		if(this.checked){
			//选中
			//支持,显示话后处理时长框
			$("#dealLength").show();
			//设置话后处理时长：15秒
			$("#dealLengthIn").val("15");
		}else{
			//不支持,不显示话后处理时长框
			$("#dealLength").hide();
			//设置话后处理时长：15秒
			$("#dealLengthIn").val("0");
		}
		
	});

	
//	alert("1");
	//读取坐席功能的初始化设置
	initSeatFunction();
});

function initSeatFunction(){
	
	$.ajax({
	    url : basePath + "platform/initSeatFunction.action",
	    type : "post",
	    dataType : "text",
//	    data : paramData,
	    success : function(data) {
//		alert(data);
		    if ( data!==null ) {
		    	var obj = eval( "(" + data + ")" );//转换后的JSON对象
		    	if(obj.isThreeCall==1)
		    		document.getElementById("threecall_threecall").checked = true;
		    	if(obj.freeSeatThreeCall==1)
		    		document.getElementById("threecall_freeseat").checked = true;
		    	if(obj.addressBookThreeCall==1)
		    		document.getElementById("threecall_addressbook").checked = true;
		    	if(obj.definePhoneThreeCall==1)
		    		document.getElementById("threecall_definephone").checked = true;
		    	if(obj.isTransferCall==1)
		    		document.getElementById("transfer_transfer").checked = true;
		    	//通话转接方式
		    	if(obj.transferWay==1) {
		    		document.getElementById("way_1").checked = true;
		    	}else{
		    		document.getElementById("way_2").checked = true;
		    	}
		    	
		    	
		    	if(obj.freeSeatTransfer==1)
		    		document.getElementById("transfer_freeseat").checked = true;
		    	if(obj.addressBookTransfer==1)
		    		document.getElementById("transfer_addressbook").checked = true;
		    	if(obj.definePhoneTransfer==1)
		    		document.getElementById("transfer_definephone").checked = true;
		    	if(obj.trunQueueTransFer==1)
		    		document.getElementById("transfer_trunqueue").checked = true;
		    	if(obj.isOutCallManual==1)
		    		document.getElementById("outcallManaul").checked = true;
		    	if(obj.isKeepOrRecall==1)
		    		document.getElementById("keepOrRecall").checked = true;
		    	if(obj.isHangUp==1)
		    		document.getElementById("isHangup").checked = true;
		    	if(obj.isBusyOrIdle==1)
		    		document.getElementById("isBusyOrIdle").checked = true;
		    	if(obj.isMonitor==1)
		    		document.getElementById("isMonitor").checked = true;
		    	if(obj.isAfterCallDeal==1){
		    		document.getElementById("isAfterCallDeal").checked = true;
		    		//话后处理时间
			    	if(obj.dealLength!==0)
//			    		alert(obj.dealLength);
//			    		document.getElementById("dealLength").html(obj.dealLength);
			    		$("#dealLengthIn").val(obj.dealLength);
		    	}else {
		    		document.getElementById("isAfterCallDeal").checked = false;
		    		//没有话后处理,隐藏处理时长
		    		$("#dealLength").hide();
		    	}
		    	
		    	if(obj.isAddressBook==1)
		    		document.getElementById("isAddressBook").checked = true;
		    	if(obj.isKnowledge==1)
		    		document.getElementById("isKnowledge").checked = true;
		    	if(obj.isNotice==1)
		    		document.getElementById("isNotice").checked = true;
		    	if(obj.isNewWorkOrder==1)
		    		document.getElementById("isNewWorkOrder").checked = true;
		    	
		    	
		    } else {
//			    art.dialog.alert("坐席功能设置！");
		    }
	    }
	});
}

function saveSeatFunction(){
	
	var isThreeCall=document.getElementById("threecall_threecall").checked==true?1:0;
	var freeSeatThreeCall=document.getElementById("threecall_freeseat").checked==true?1:0;
	var addressBookThreeCall=document.getElementById("threecall_addressbook").checked==true?1:0;
	var definePhoneThreeCall=document.getElementById("threecall_definephone").checked==true?1:0;
	var isTransferCall=document.getElementById("transfer_transfer").checked==true?1:0;
	//获取转接方式
	var zj = document.getElementsByName("transferWay");
	var zjValue;
	for(var i=0;i<zj.length;i++){
		if(zj[i].checked) {
//		alert(zj[i].value);
		zjValue=zj[i].value;
		}
	}
	
	var freeSeatTransfer=document.getElementById("transfer_freeseat").checked==true?1:0;
	var addressBookTransfer=document.getElementById("transfer_addressbook").checked==true?1:0;
	var definePhoneTransfer=document.getElementById("transfer_definephone").checked==true?1:0;
	var trunQueueTransFer=document.getElementById("transfer_trunqueue").checked==true?1:0;
	var isOutCallManual=document.getElementById("outcallManaul").checked==true?1:0;
	var isKeepOrRecall=document.getElementById("keepOrRecall").checked==true?1:0;
	var isHangUp=document.getElementById("isHangup").checked==true?1:0;
	
	var isBusyOrIdle=document.getElementById("isBusyOrIdle").checked==true?1:0;
	var isMonitor=document.getElementById("isMonitor").checked==true?1:0;
	//是否支持  话后处理
	var isAfterCallDeal=document.getElementById("isAfterCallDeal").checked==true?1:0;
	if(isAfterCallDeal==1){
		
	}
	//话后处理时长
	var dealLength=document.getElementById("dealLengthIn").value;
	if(dealLength==""){
		dealLength=0;
//		document.getElementById("dealLengthIn").val(1);
//		$("#dealLengthIn").val(1);
		}
//	alert(document.getElementById("dealLengthIn").value);
	//处理话后处理时长 的输入 1：只能为数字 2：范围1--99
	/* 李立泼 2014年10月09日修改：增加了$("#dealLengthIn").is(":visible")，用来判断这个对象是否显示。当显示时，返回true；当隐藏时，返回false。 */
	if( $("#dealLengthIn").is(":visible") && document.getElementById("dealLengthIn").value=="" ){
		art.dialog.alert("请输入话后处理的时长！"); 
		   return ;
	}
	
	  var val=/^[0-9]{0,1}[0-9]{1}$/; 
	  /* 李立泼 2014年10月09日修改：增加了$("#dealLengthIn").is(":visible")，用来判断这个对象是否显示。当显示时，返回true；当隐藏时，返回false。 */
	  if( $("#dealLengthIn").is(":visible") && !val.exec(document.getElementById("dealLengthIn").value) ){
	   art.dialog.alert("话后处理时长只能是1-99秒之内！"); 
	   return ;
	  } 
	  
	
	var isAddressBook=document.getElementById("isAddressBook").checked==true?1:0;
	var isKnowledge=document.getElementById("isKnowledge").checked==true?1:0;
	var isNotice=document.getElementById("isNotice").checked==true?1:0;
	var isNewWorkOrder=document.getElementById("isNewWorkOrder").checked==true?1:0;
	
	var paramData = {
	    'seatFun.isThreeCall' : isThreeCall,
	    'seatFun.freeSeatThreeCall' : freeSeatThreeCall,
	    'seatFun.addressBookThreeCall' : addressBookThreeCall,
	    'seatFun.definePhoneThreeCall' : definePhoneThreeCall,
	    'seatFun.isTransferCall' : isTransferCall,
	    'seatFun.transferWay' : zjValue,//1:单步转接 2：咨询转接
	    
	    'seatFun.freeSeatTransfer' : freeSeatTransfer,
	    'seatFun.addressBookTransfer' : addressBookTransfer,
	    'seatFun.definePhoneTransfer' : definePhoneTransfer,
	    'seatFun.trunQueueTransFer' : trunQueueTransFer,
	    'seatFun.isOutCallManual' : isOutCallManual,
	    
	    'seatFun.isKeepOrRecall' : isKeepOrRecall,
	    'seatFun.isHangUp' : isHangUp,
	    'seatFun.isBusyOrIdle' : isBusyOrIdle,
	    'seatFun.isMonitor' : isMonitor,
	    'seatFun.isAfterCallDeal' : isAfterCallDeal,
	    'seatFun.dealLength' : dealLength,
	    'seatFun.isAddressBook' : isAddressBook,
	    'seatFun.isKnowledge' : isKnowledge,
	    'seatFun.isNotice' : isNotice,
	    'seatFun.isNewWorkOrder' : isNewWorkOrder
	};
	$.ajax({
	    url : basePath + "platform/saveSeatFunction.action",
	    type : "post",
	    dataType : "text",
	    data : paramData,
	    success : function(data) {
//		alert(data);
		    if (1 == data) {
		    	art.dialog.alert("坐席功能设置成功！", function(){
		    		//var getDataTable = art.dialog.data("getDataTable");
		    		//getDataTable();
		    	});
		    } else {
			    art.dialog.alert("坐席功能设置失败！");
		    }
	    }
	});
}

function goSelect(id){

	var $menuModule = $("#"+id);
	
	var value = $menuModule.val();
	var list = value.split(",");

	var ischeck=document.getElementById(id).checked;

	//获取的三方通话的id
	var threeId=$("input[id^="+list[0]+"][id$="+list[0]+"]").attr("id");
	
	if(list[0]==list[1]){
		//操作的是"三方通话"
		//alert(document.getElementById(id).checked);
		
		
		//alert(ischeck);
		if(ischeck){
			//* 当选中"三方通话"的时候，所有子元素都被选中
			$("input[id^="+list[0]+"]:not([id$="+list[0]+"])").attr("checked","checked");
			
		}else{
			//alert("ooooooo");
			//* 当不选中"三方通话"的时候，所有子元素都不被选中
			//$("input[id^="+list[0]+"]:not([id$="+list[0]+"])").attr("checked",null);
			$("input[id^="+list[0]+"]:not([id$="+list[0]+"])").each(function(index,domEle){
				var currId=$(domEle).attr("id");
				document.getElementById(currId).checked=false;
				
			});
		}
		
	}else{
		//操作的是所有子元素
		//* 当任意一个子元素被选中的时候，"三方通话"也被选中
		if(ischeck){
			//var threeId=$("input[id^="+list[0]+"][id$="+list[0]+"]").attr("id");
			//$("input[id^="+list[0]+"][id$="+list[0]+"]").attr("checked","checked");
			//alert(document.getElementById(threeId).checked);
			document.getElementById(threeId).checked=true;
		}
		
			//* 当所有子元素都不被选中的时候，"三方通话"也不被选中
		var flag = false;
		
		$("input[id^="+list[0]+"]:not([id$="+list[0]+"])").each(function(index,domEle){
			var currId=$(domEle).attr("id");
			//alert(currId);
			//alert(document.getElementById(currId).checked);
			//if($(domEle).attr("checked")){
			if(document.getElementById(currId).checked){
				flag = true;
			}
			
		});

		if(!flag){
			//var threeId2=$("input[id^="+list[0]+"][id$="+list[0]+"]").attr("id");
			//$("input[id^="+list[0]+"][id$="+list[0]+"]").attr("checked",null);
			document.getElementById(threeId).checked=false;
		}
	}
}