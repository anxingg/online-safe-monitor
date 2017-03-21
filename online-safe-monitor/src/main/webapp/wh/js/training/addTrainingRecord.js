
/**
 * 保存添加
 */
function addTraining(){
	if(!validator(document.getElementById("form1"))){
		return;
	}
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
			"training.subject" : subject,
			"training.trainObject" : trainObject,
			"training.trainTime" : trainTime,
			"training.speaker" : speaker,
			"training.address" : address,
			"training.school" : school,
			"training.details" : details,
			"training.effect" : effect,
			"training.num" : num,
			"operType":"add",
			"trainingType":1
		},
		success : function(data) {
			if (data == 1) {
				window.location.href = basePath+"wh/logined/training/trainingRecordList.jsp"; 
				//artDialog.alert("新增成功！",function(){					
					//重新加载列表
//					window.location.href = basePath+"wh/logined/training/companyProductList.jsp"; 
				//});
			} else if (data == 0){
				artDialog.alert("新增失败！");
				$(".formButton_green").removeAttr("disabled");
			}
		}
		
	});
}

	
