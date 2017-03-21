$(document).ready(function(){
	
	getPersonInfo();
});

/**
 * 获取人员信息
 */
function getPersonInfo(){
	var groupId = $.trim($("#groupId").val());
	//异步获取人员信息
	$.ajax({
		url : basePath + "companywh/getLegalPersonInfo.action",
		type : "post",
		dataType : 'json',
		data : {
			group_id : groupId
		},
		success : function(data) {
			if (data != null) {
				var person = eval(data);
				$("#name").html(person.name);
				$("#phone").html(person.phone);
				$("#certificateNum").html(person.certificateNum);
				$("#cardType").html(person.cardTypeName);
				$("#job").html(person.job);
				//$("#photo").html(person.photo);
				if(person.photo!=undefined && person.photo!=""){
					$("#img").attr("src",downPath+"upload/"+person.photo);
				}
			}
		}
		
	});
}

