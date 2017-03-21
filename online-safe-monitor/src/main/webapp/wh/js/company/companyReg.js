function addCompany(){
	// 框架校验
	if (!validator(document.getElementById("form1"))) {
		return;
	}
	var companyName = $.trim($("#companyName").val());
	var loginName = $.trim($("#loginName").val());
	check(loginName,companyName);
	
}

function add(){
	var companyName = $.trim($("#companyName").val());
	var loginName = $.trim($("#loginName").val());
	
	var hylx="";
	$('input[name="hylx"]:checked').each(function(){ 
		hylx+=$(this).val()+",";
	});
	var loginPass = $("#loginPass").val();
	var userName = $.trim($("#userName").val());
	var phone = $.trim($("#phone").val());
	var memo = $.trim($("#memo").val());
	$(".formButton_green").attr("disabled", "disabled");
	//新增用户
	$.ajax({
		url : basePath + "companywh/addCompany.action",
		type : "post",
		dataType : 'json',
		data : {
			companyName : companyName,
			hylx : hylx,
			loginName : loginName,
			loginPass : loginPass,
			userName : userName,
			phone : phone,
			memo : memo
		},
		success : function(data) {
			if (data == 1) {
				//artDialog.alert("注册成功",function(){					
					window.location.href = basePath+"wh/logined/company/companyList.jsp"; 
				//});
			} else if (data == 0){
				artDialog.alert("注册失败");
				$(".formButton_green").removeAttr("disabled");
				return false;
			}
		}
		
	});
}

/**
 * 验证登录用户名和公司名称是否重复
 * @param loginName
 * @param companyName
 */
function check(loginName,companyName){
	//异步验证登录用户名
	$.ajax({
		url : basePath + "companywh/ajaxCheckLoginName.action",
		type : "post",
		dataType : 'json',
		data : {
			loginName : loginName
		},
		success : function(data) {
			if (data == 0) {
				//异步验证公司名称
				$.ajax({
					url : basePath + "companywh/ajaxCheckCompanyName.action",
					type : "post",
					dataType : 'json',
					data : {
						companyName : companyName
					},
					success : function(data) {
						if (data == 0) {
							//通过验证，添加用户
							add();
						} else if (data == 1){
							artDialog.alert("企业名称已被注册，请重新输入");
						}
					}
					
				});
			} else if (data == 1){
				artDialog.alert("企业登录账号已被使用，请重新输入");
			}
		}
		
	});
}