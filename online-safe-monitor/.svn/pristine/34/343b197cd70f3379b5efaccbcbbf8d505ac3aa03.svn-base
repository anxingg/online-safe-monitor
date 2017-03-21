$(document).ready(function(){
	
	getPersonInfo();
});

/**
 * 获取人员信息
 */
function getPersonInfo(){
	var personId = $.trim($("#personId").val());
	//异步获取人员信息
	$.ajax({
		url : basePath + "companywh/getPersonInfo.action",
		type : "post",
		dataType : 'json',
		data : {
			personId : personId
		},
		success : function(data) {
			if (data != null) {
				var person = eval(data);
				$("#name").val(person.name);
				$("#groupName").val(person.groupName);
				$("#idNumber").val(person.idNumber);
				$("#startTime").val(person.startTime);
				$("#workType").val(person.workType);
				$("#issuingAuthority").val(person.issuingAuthority);
				$("#certificateNum").val(person.certificateNum);
				$("#replaceDate").val(person.replaceDate);
				$("#certificateGetTime").val(person.certificateGetTime);
				$("#memo").val(person.memo);
			} else {
				artDialog.alert("加载人员信息失败！");
			}
		}
		
	});
}

/**
 * 修改
 */
function updatePerson(){
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
	var personId = $.trim($("#personId").val());
	var name = $.trim($("#name").val());
	var groupName = $.trim($("#groupName").val());
	var startTime = $.trim($("#startTime").val());
	var workType = $.trim($("#workType").val());
	var issuingAuthority = $.trim($("#issuingAuthority").val());
	var certificateNum = $.trim($("#certificateNum").val());
	var replaceDate = $.trim($("#replaceDate").val());
	var certificateGetTime = $.trim($("#certificateGetTime").val());
	var memo = $.trim($("#memo").val());
	startTime += " 00:00:00";
	if(replaceDate!=null && replaceDate!=""){
		replaceDate += " 00:00:00";		
	}
	if(certificateGetTime!=null && certificateGetTime!=""){
		certificateGetTime += " 00:00:00";		
	}
	
	
	$(".formButton_green").attr("disabled", "disabled");
	//保存修改
	$.ajax({
		url : basePath + "companywh/updateSpecialWorkPerson.action",
		type : "post",
		dataType : 'json',
		data : {
			personId : personId,
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
				//artDialog.alert("修改成功！",function(){					
					//重新加载列表
					window.location.href = basePath+"wh/logined/specialWorkPerson/specialWorkPersonList.jsp"; 
				//});
			} else if (data == 0){
				artDialog.alert("修改失败！");
				$(".formButton_green").removeAttr("disabled");
			}
		}
		
	});
}