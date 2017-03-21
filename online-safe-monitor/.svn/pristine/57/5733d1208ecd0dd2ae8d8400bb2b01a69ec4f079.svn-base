$(document).ready(function(){
	
	getTrainingInfo();
});


/**
 * 获取培训信息
 */
function getTrainingInfo(){
	var trainingId = $("#trainingId").val();
	$.ajax({
		url : basePath + "training/findTraining.action",
		type : "post",
		dataType : 'json',
		data : {
			trainingId : trainingId
		},
		success : function(data) {
			if (data != null) {
				var training = eval(data);
				$("#trainYear").html(training.trainYear);
				$("#details").html(training.details);
				$("#charge").html(training.charge);
				$("#trainRate").html(training.trainRate);
				$("#trainObject").html(training.trainObject);
				$("#trainForm").html(training.trainForm);
				$("#memo").html(training.memo);
				var attachmentIds = training.attachmentIds;
				var html = '';
				if(attachmentIds == null || attachmentIds == undefined || attachmentIds == '' || attachmentIds == '-'){
					html = '无附件';
				}else {
					html = '<a href="javascript:;" onclick="downloadReport(\''+attachmentIds+'\');">'+training.attachName+'</a>';
				}
				$("#att").html(html);
			} else {
				artDialog.alert("加载年度培训管理信息失败！");
			}
		}
		
	});
}

/**
 * 下载附件
 * @param id
 */
function downloadReport(id){
	var attachmentId = new String(id);
	attachmentId = attachmentId.replace(new RegExp(",","g"),'');
	downLoadAttachment(attachmentId);
}

	
