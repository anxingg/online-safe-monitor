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
				$("#subject").val(training.subject);
				$("#trainObject").val(training.trainObject);
				$("#trainTime").val(training.trainTime);
				$("#speaker").val(training.speaker);
				$("#address").val(training.address);
				$("#school").val(training.school);
				$("#details").val(training.details);
				$("#effect").val(training.effect);
				$("#num").val(training.num);
			} else {
				artDialog.alert("加载安全培训记录信息失败！");
			}
		}
		
	});
}
/**
 * 修改
 */
function updateTraining(){
	if(!validator(document.getElementById("form1"))){
		return;
	}
	var trainingId = $("#trainingId").val();
	var subject = $("#subject").val();
    var trainObject = $("#trainObject").val();
    var trainTime = $("#trainTime").val();
    var speaker = $("#speaker").val();
    var address = $("#address").val();
    var school = $("#school").val();
    var details = $("#details").val();
    var effect = $("#effect").val();
    var num = $("#num").val();
	$(".formButton_green").attr("disabled", "disabled");
	//保存
	$.ajax({
		url : basePath + "training/addOrUpdateTraining.action",
		type : "post",
		dataType : 'json',
		data : {
			"trainingId":trainingId,
			"training.subject" : subject,
			"training.trainObject" : trainObject,
			"training.trainTime" : trainTime,
			"training.speaker" : speaker,
			"training.address" : address,
			"training.school" : school,
			"training.details" : details,
			"training.effect" : effect,
			"training.num" : num,
			"operType":"update",
			"trainingType":1
		},
		success : function(data) {
			if (data == 2) {
				window.location.href = basePath+"wh/logined/training/trainingRecordList.jsp"; 
				//artDialog.alert("新增成功！",function(){					
					//重新加载列表
//					window.location.href = basePath+"wh/logined/training/companyProductList.jsp"; 
				//});
			} else if (data == 0){
				artDialog.alert("修改失败！");
				$(".formButton_green").removeAttr("disabled");
			}
		}
		
	});
}

	
