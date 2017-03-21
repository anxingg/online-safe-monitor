$(document).ready(function(){
	
	
	getCompanyInfo();
	
	//
	$("#submit").click(function(){
		// 框架校验
		if (!validator(document.getElementById("form1"))) {
			return;
		}
		var password1 = $("#password1").val();
		var password2 = $("#password2").val();
		if(password1!=password2){
			artDialog.alert("两次密码不一致！");
			return;
		}
		art.dialog.confirm('确定要修改企业密码吗？', function() {
			reset();
		}, function() {
			return;
		});
	});
	
});


/**
 * 重置密码
 */
function reset(){
	
	var password1 = $("#password1").val().trim();
	
		//保存修改
		$.ajax({
			url : basePath + "companywh/resetPass.action",
			type : "post",
			dataType : 'json',
			data : {
				loginPass : password1,
				groupId : $("#groupId").val().trim()
			},
			success : function(data) {
				if (data == 1) {
					//artDialog.alert("修改密码成功！",function(){						
						window.location.href = basePath+"wh/logined/company/companyList.jsp"; 
					//});
				} else if (data == 0){
					artDialog.alert("修改密码失败！");
				}
			}
			
		});

}
/**
 * 获取企业信息
 */
function getCompanyInfo(){
	var paramData = {groupId:$("#groupId").val().trim()};
	qytx.app.ajax({
		url : basePath + "companywh/getWHCompanyInfo.action",
		type : "post",
		dataType : "html",
		data : paramData,
		success : function(data) {
			var company = eval("(" + data + ")");
			$("#companyName").html(company.companyName);
			$("#companyId").html(company.companyId);
			$("#loginName").html(company.loginName);
		}
	});
}

