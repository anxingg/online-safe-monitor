
$(document).ready(function(){
	
	$("#addSeat").click(function(){
		$("#adMemberSpan").next().remove();
		var userIdList=$("#userIdList").val();
		//alert(userIdList);
		showAllSeat(userIdList);
	});
	
	$("#clearAllSeat").click(function(){
		$("#userNameList").html("");
		$("#userIdList").val("");
	});
	
	$("#submitButton").click(function(){
//		var valid=validator($("#queueEditForm")[0]);
//		if(valid){
			submitOperation();
//		}
	});
	
});

function submitOperation(){
	$("#submitButton").attr("disabled",true);
	if (!validator(document.getElementById("queueEditForm"))) {
//		$("#saveTemp").attr("disabled",false);
		$("#submitButton").attr("disabled",false);
		return;
	}
	var vid=$("#vid").val();
	var userIdList=$("#userIdList").val();
	var serviceName=$("#serviceName").val();
	//alert(vid+" "+userIdList+"  "+serviceName);
	var dataParam = {
			'vid' : vid,
			'userIdList' : userIdList,
			'serviceName' : serviceName
		};
		$.ajax({
		    type : 'post',
		    url : basePath + "seatQueue/queue_save.action",
		    data : dataParam,
		    dataType : 'text',
		    success : function(data) {
		    	if(data=="0"){
//		    		art.dialog.alert("修改成功。",function(){
						location.href = basePath + "logined/queue/queueSeting.jsp";
//					});
		    	}else{
		    		if(data == "修改失败。"){
						art.dialog.alert("修改失败。");
					}else{
						art.dialog.alert("修改失败，请联系管理员处理。");
					}
		    	}
		    }
		});
}


function showAllSeat(userIds){
	openSelectUser(3,selectCallBack,"user",userIds);//选择人员
}

function selectCallBack(data, param) {
	var userIds = ",";
	var userNames = "";
	data.forEach(function(value, key) {
				// alert("key="+key+",name="+value.Name+",id="+value.Id+",data="+value.Data+",type="+value.Type);
				userIds += value.Id + ",";
				userNames += value.Name + ",";
			});

	if (userIds == ",") {
		userIds = "";
	}
	//alert(userIds);
	$("#userNameList").html(userNames);
	$("#userIdList").val(userIds);
}
