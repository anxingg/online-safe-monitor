
/**
 * 提交发送
 */
function subSendMessage(prompt){
	window.top.sumbitButton = true;
	var moduleName = $("#moduleName").val();//模块名称
	var sendPhone = $("#sendPhone").val();//接受手机
	var contentInfo = $.trim($("#contentInfo").val());//短信内容
	var valid=validator($("#sendMessage")[0]);
	if(valid){
		var paramData = {
				"moduleName" : moduleName,
				"sendPhone" :sendPhone,
				"contentInfo" : contentInfo
		}
		$.ajax({
			url:basePath + "jbpmworkorder/sendMessage.action",
			type:'post',
			dataType:'text',
			data:paramData,
			success:function(data){
				if(data==0){
					window.top.sumbitButton = false;
					prompt(data);
					if(parent.art.dialog.list['sendMessage']){
						parent.art.dialog.list['sendMessage'].close();
					}
				}else{
					window.top.sumbitButton = false;
					art.dialog.alert("发送失败!");
				}
			}
		});
	}else{
		window.top.sumbitButton = false;
	}
	
}