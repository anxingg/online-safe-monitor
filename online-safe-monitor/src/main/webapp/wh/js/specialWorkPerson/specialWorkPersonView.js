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
				$("#name").html(person.name);
				$("#companyName").html(person.companyName);
				$("#groupName").html(person.groupName);
				$("#idNumber").html(person.idNumber);
				$("#startTime").html(person.startTime);
				$("#workType").html(person.workType);
				$("#issuingAuthority").html(person.issuingAuthority);
				$("#certificateNum").html(person.certificateNum);
				$("#replaceDate").html(person.replaceDate);
				$("#certificateGetTime").html(person.certificateGetTime);
				$("#memo").html(person.memo);
			} else {
				artDialog.alert("加载人员信息失败！");
			}
		}
		
	});
}

