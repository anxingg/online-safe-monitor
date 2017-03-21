$(document).ready(function(){

	//保存新增
	$("#submit").click(function(){
		// 框架校验
		if (!validator(document.getElementById("form1"))) {
			return;
		}
		submit();

	});
	
});



/**
 * 保存
 */
function submit(){
	var professorType = $.trim($("#professorType").val())
	if(professorType==null || professorType==""){
		professorType = $.trim($("#type").val());
	}
	var parmas = {
		"professor.vid" : $.trim($("#vid").val()),
		"professor.name" : $.trim($("#name").val()),
		"professor.specialties" : $.trim($("#specialties").val()),
		"professor.title" : $.trim($("#title").val()),
		"professor.workCompany" : $.trim($("#workCompany").val()),
		"professor.phone" : $.trim($("#phone").val()),
		"professor.professorType" : professorType,
		"professor.memo" : $.trim($("#memo").val())
	};
	
	$(".formButton_green").attr("disabled", "disabled");
	$.ajax({
		url : basePath + "professor/addOrUp.action",
		type : "post",
		data : parmas,
		success : function(data){
			if (data == 1) {
				window.location.href = basePath+"wh/logined/professor/professorList.jsp?type="+professorType;
			}else{
				artDialog.alert("保存失败！");
				$(".formButton_green").removeAttr("disabled");
			}
		}
	});
	
	
}

