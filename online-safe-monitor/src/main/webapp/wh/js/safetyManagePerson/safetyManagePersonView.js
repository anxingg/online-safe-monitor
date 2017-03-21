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
				if(person.sex==1){
					$("#sex").html("男");
				}else{
					$("#sex").html("女");	
				}
				$("#idNumber").html(person.idNumber);
				$("#educationDegree").html(person.educationDegree);
				$("#job").html(person.job);
				$("#certificateNum").html(person.certificateNum);
				$("#replaceDate").html(person.replaceDate);
				$("#phone").html(person.phone);
				$("#jobTitle").html(person.jobTitle);
				$("#memo").html(person.memo);
				$("#cardTypeName").html(person.cardTypeName);
				
			} else {
				artDialog.alert("加载人员信息失败！");
			}
		}
		
	});
}

