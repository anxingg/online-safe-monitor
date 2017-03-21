$(document).ready(function(){

});

function addPerson(){
	
	// 框架校验
	if (!validator(document.getElementById("form1"))) {
		return;
	}
	var idNumber = $.trim($("#idNumber").val());
	var isIdNum = checkIdcard(idNumber);
	if(isIdNum!=true){
		artDialog.alert(isIdNum);
		return;
	}
	var name = $.trim($("#name").val());
	var groupName = $.trim($("#groupName").val());
	var startTime = $.trim($("#startTime").val());
	startTime += " 00:00:00";
	var workType = $.trim($("#workType").val());
	var issuingAuthority = $.trim($("#issuingAuthority").val());
	var certificateNum = $.trim($("#certificateNum").val());
	var replaceDate = $.trim($("#replaceDate").val());
	if(replaceDate!=null && replaceDate!=""){
		replaceDate += " 00:00:00";		
	}
	var certificateGetTime = $.trim($("#certificateGetTime").val());
	if(certificateGetTime!=null && certificateGetTime!=""){
		certificateGetTime += " 00:00:00";		
	}
	
	var memo = $.trim($("#memo").val());
	
	$(".formButton_green").attr("disabled", "disabled");
	//异步添加
	$.ajax({
		url : basePath + "companywh/addSpecialWorkPerson.action",
		type : "post",
		dataType : 'json',
		data : {
			name : name,
			groupName : groupName,
			idNumber : idNumber,
			startTime : startTime,
			workType : workType,
			issuingAuthority : issuingAuthority,
			certificateNum : certificateNum,
			replaceDate : replaceDate,
			certificateGetTime : certificateGetTime,
			memo : memo
		},
		success : function(data) {
			if (data == 1) {
				//artDialog.alert("新增成功！", function(){
					//重新加载列表
					window.location.href = basePath+"wh/logined/specialWorkPerson/specialWorkPersonList.jsp";
				//});
			} else if (data == 0){
				artDialog.alert("新增失败！");
				$(".formButton_green").removeAttr("disabled");
			}
		}
		
	});
}