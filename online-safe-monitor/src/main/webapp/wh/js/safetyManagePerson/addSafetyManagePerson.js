$(document).ready(function(){
	
	initSelect("cardType","cardType");
	
	$("#cardType").change(function(){
		$("#hicardType").val($("#cardType").val());
		$("p").hide();//隐藏所有P标签 隐藏错误信息提示
	});
	
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
	var sex=$('input:radio[name="sex"]:checked').val();
	var educationDegree = $.trim($("#educationDegree").val());
	var job = $.trim($("#job").val());
	var certificateNum = $.trim($("#certificateNum").val());
	var replaceDate = $.trim($("#replaceDate").val());
	replaceDate += " 00:00:00";
	var phone = $.trim($("#phone").val());
	var jobTitle = $("#jobTitle").val();
	var memo = $.trim($("#memo").val());
	var cardType = $.trim($("#cardType").val());
	

	$(".formButton_green").attr("disabled", "disabled");
	//异步添加
	$.ajax({
		url : basePath + "companywh/addPerson.action",
		type : "post",
		dataType : 'json',
		data : {
			name : name,
			sex : sex,
			idNumber : idNumber,
			educationDegree : educationDegree,
			job : job,
			certificateNum : certificateNum,
			replaceDate : replaceDate,
			phone : phone,
			jobTitle : jobTitle,
			memo : memo,
			cardType : cardType
		},
		success : function(data) {
			if (data == 1) {
				//artDialog.alert("新增成功！",function(){
					//重新加载列表
					window.location.href = basePath+"wh/logined/safetyManagePerson/safetyManagePersonList.jsp"; 					
				//});
			} else if (data == 0){
				artDialog.alert("新增失败！");
				$(".formButton_green").removeAttr("disabled");
			}
		}
		
	});
}
/**
 * 初始化数据字段下拉框
 * @return
 */
function initSelect(typeName,id) {
	var paramData = {
		'infoType' : typeName,
		'sysTag' : 1
	};
	qytx.app.ajax({
		url : basePath + "dict/getDicts.action",
		type : "post",
		dataType : "text",
		data : paramData,
		success : function(data) {
			//data是一个数组
			var jsonData = eval('(' + data + ')');
			$("#"+id).empty();
			$("#"+id).append('<option value="">请选择</option>');
			for (var i = 0; i < jsonData.length; i++) {
				$("#"+id).append("<option value='"
						+ jsonData[i].value + "'>" + jsonData[i].name
						+ "</option>");
			}
		}
	});
}

