$(document).ready(function(){
	
	initSelect("cardType","cardType");
	
	//保存企业信息
	$("#submit").click(function(){
		// 框架校验
		if (!validator(document.getElementById("form1"))) {
			return;
		}
		var cardType = $.trim($("#cardType").val());
		var certificateNum = $.trim($("#certificateNum").val());
		if(cardType==1){//身份证
			var isIdNum = checkIdcard(certificateNum);
			if(isIdNum!=true){
				artDialog.alert(isIdNum);
				return;
			}
		}
		art.dialog.confirm('确定要保存修改吗？', function() {
			submit();
			
		}, function() {
			return;
		});
	});
	
	
	$("#cardType").change(function(){
		$("#hicardType").val($("#cardType").val());
		$("p").hide();//隐藏所有P标签 隐藏错误信息提示
	});
	//上传按钮
	$(document).ready(function() {
		var fileupload = qytx.app.fileupload({
			id:"file_upload",
			hiddenID:"attachmentId",
			queueID:"queue",
			moduleName:"company",
			queueSizeLimit:"1",
			fileTypeExts:"*.jpg;*.jpeg;*.png;*.gif",
			callback:function(data){
				var attachmentPath = "upload/"+data.attachFile;
				$("#img").attr("src",downPath+attachmentPath);
				$("#photo").val(data.attachFile);
			}
		});
	});
	getPersonInfo();
	
	
	
});

/**
 * 获取人员信息
 */
function getPersonInfo(){
	//异步获取人员信息
	$.ajax({
		url : basePath + "companywh/getLegalPersonInfo.action",
		type : "post",
		dataType : 'json',
		data : {
		},
		success : function(data) {
			if (data != null) {
				var person = eval(data);
				$("#name").val(person.name);
				$("#phone").val(person.phone);
				$("#certificateNum").val(person.certificateNum);
				$("#cardType").val(person.cardType);
				$("#cardType").change();
				$("#job").val(person.job);
				$("#photo").val(person.photo);
				if(person.photo!=undefined && person.photo!=""){
					$("#img").attr("src",downPath+"upload/"+person.photo);
				}
			}
		}
		
	});
}

/**
 * 保存修改
 */
function submit(){
	
	var name = $.trim($("#name").val());
	var phone = $.trim($("#phone").val());
	var cardType = $.trim($("#cardType").val());
	var certificateNum = $.trim($("#certificateNum").val());
	var job = $.trim($("#job").val());
	var photo = $.trim($("#photo").val());

	
	//保存修改
	$.ajax({
		url : basePath + "companywh/updateLegalPerson.action",
		type : "post",
		dataType : 'json',
		data : {
			name : name,
			phone : phone,
			cardType : cardType,
			certificateNum : certificateNum,
			job : job,
			photo : photo
		},
		success : function(data) {
			if (data == 1) {
				//artDialog.alert("修改成功！");
				window.location.href = basePath+"wh/logined/company/legalPersonView.jsp?groupId="+$.trim($("#groupId").val());
			} else if (data == 0){
				artDialog.alert("修改失败！");
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

function deleteImg(){
	$("#img").attr("src",basePath+"wh/images/up.jpg");
	$("#attachmentId").val("");
	$("#photo").val("");
	
}
