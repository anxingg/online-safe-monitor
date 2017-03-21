$(document).ready(function() {
	//增加按钮单击事件
	$("#save").bind("click", function(){
		save();
	});
});


/**
 * 保存添加
 */
function save(){
	if(!validator(document.getElementById("form1"))){
		return;
	}
	var emerId = $("#emergencyId").val();
	var whroletype = $("#whroletype").val();
	var departName = $("#departName").val();
	//应急机构类型
	var type = 1;
	var isShow = 0;
	if(whroletype==1||whroletype==3){
		type = 3;
		isShow = 1;
	}else{
		$(":checkbox[name='isDepart']:checked").each(function(){ 
			type = $(this).val();
		})
	}
    var phone = $("#phone").val();
    var groupNum = $("#groupNum").val();
    var job = $("#job").val();
    var url = basePath + "emergencyDepartment/saveOrUpdateEmergencyDepartment.action";
	$(".formButton_green").attr("disabled", "disabled");
	//保存
	$.ajax({
		url : url,
		type : "post",
		dataType : 'json',
		data : {
			"info.id" : emerId,
			"info.departName" : departName,
			"info.departType" : type,
			"info.phone" : phone,
			"info.groupNumber" : groupNum,
			"info.job" : job,
			"info.isShow" : isShow
		},
		success : function(data) {
			if (data == 1) {
				if(whroletype==2){
					window.location.href = basePath+"wh/logined/emergencyDepartment/emergencyList.jsp";
				}else{
					window.location.href = basePath+"wh/logined/emergencyDepartment/emergencyCompanyList.jsp"; 
				}
			} else if (data == 0){
				artDialog.alert("新增失败！");
				$(".formButton_green").removeAttr("disabled");
			}
		}
		
	});
}

	
