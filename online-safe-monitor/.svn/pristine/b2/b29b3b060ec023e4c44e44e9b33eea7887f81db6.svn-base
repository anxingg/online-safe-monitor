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
				$("#subject").html(training.subject);
				$("#trainObject").html(training.trainObject);
				$("#trainTime").html(training.trainTime);
				$("#speaker").html(training.speaker);
				$("#address").html(training.address);
				$("#school").html(training.school);
				$("#details").html(training.details);
				$("#effect").html(training.effect);
				$("#num").html(training.num);
			} else {
				artDialog.alert("加载安全培训记录信息失败！");
			}
		}
		
	});
}

	
