

$(document).ready(function() {
	
	//保存按钮绑定事件
	$("#attachmentName").bind("click", function() {
		//事故报告ID
		var attachmentId = $("#attachmentId").val();
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