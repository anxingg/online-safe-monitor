

$(document).ready(function() {
	//技术说明书下载附件绑定事件
	$("#technicalName").live("click", function() {
		//技术说明书ID
		var attachmentId = $("#technicalId").val();
		attachmentId = attachmentId.replace(new RegExp(",","g"),'');
		downLoadAttachment(attachmentId);
	});
	
	//安全标签附件下载绑定事件
	$("#securityName").live("click", function() {
		//安全标签ID
		var attachmentId = $("#securityId").val();
		attachmentId = attachmentId.replace(new RegExp(",","g"),'');
		downLoadAttachment(attachmentId);
	});
});


/**
 * 取消操作
 * @returns {Boolean}
 */
function goback(){
	var url = document.referrer;
	window.location.href = url;
	return false;
}