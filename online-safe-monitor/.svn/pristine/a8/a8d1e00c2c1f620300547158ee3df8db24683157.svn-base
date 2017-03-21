/**
 * 登录js
 */
$(document).ready(function() {
	//删除头像
    $("#deletePhoto").live("click",function(){
        $("#photo").val("");
        var photoUrl = basePath + "/images/default_photo.png";
        $("#photoImg").attr("src", photoUrl);
        $("#deletePhoto").hide();        
    });
	
	
			$("#submitButton").bind("click", function() {
						recordSet();
					});

		});

function recordSet() {
	// 框架校验
	if (!validator(document.getElementById("form1"))) {
		return;
	}
	var userId = $("#userId").val();
	var recordId = $("#recordId").val();
	var userName = $("#userName").val();
	var sex = $("input[name='sex'][type='radio']:checked").val();
	var birthDay = $("#birthDay").val();// 日志时间
	var phone = $("#phone").val();
	var phone2 = $("#phone2").val();
	var email = $("#email").val();
	var photo = $("#photo").val();
	var alterName = $("#alterName").val();
	//签章类型
//	var signType = $("#userSign").val();
	var signType =0;
	var signUrl = $("#imgSignUrl").val();
	var sign = $("#sign").val();
	var office = $("#office").val();
	var print = $("#print").val();
	 if(!birthDay){
		 birthDay="";
	 } 
	 var groupId = $("#groupId").val();
	var paramData = {
		'userId' : userId,
		'recordId' : recordId,
		'user.userName' : userName,
		'user.sex' : sex,
		'birthDay' : birthDay,
		'user.phone' : phone,
		'user.phone2' : phone2,
		'user.email' : email,
		'user.alterName' : alterName,
		'user.signType':signType,
		'user.signUrl':signUrl,
		'user.taoDa':print,
		'user.photo':photo,
		'user.officeWidget':office,
		'user.sinWidget':sign,
		'user.groupId':groupId
	};
	var urlStr = basePath + "sysset/sysset_updateRecord.action";
	$.ajax({
				url : urlStr,
				type : "post",
				dataType : 'json',
				data : paramData,
				success : function(data) {
					if (data == 0) {
//						qytx.app.dialog.tips("个人资料修改成功！");
						art.dialog.tips("个人资料修改成功！");

//						art.dialog({
//							   title:"消息",
//							   content:"个人资料修改成功！",
//							   width:320,
//							   height:110,
//							   icon:"succeed",
//							   opacity:0.3,
//							   ok:function(){},
//							   close:function(){
//								   window.document.location.reload();
//							   }
//							});
					}
				}
			});
}

/**
 * 预览照片
 */
function displayPhoto() {
    var photoName = $("#photo").val();
    if (photoName != "") {
        var photoUrl = basePath + "filemanager/prevViewByPath.action?filePath=" + photoName;
        $("#photoImg").attr("src", photoUrl);
        $("#deletePhoto").show();
    }
}