/**
 * 人员更新
 */

$(document).ready(function() {
	document.getElementById("userName").focus();
	// 单击主角色
	$("#roleSelect").click(function() {
				selectUserRole();
				return false;
			});
	// 单击主角色清除
	$("#roleRemove").click(function() {
				$("#roleId").val("");
				$("#role").val("");
				return false;
			});
	// 单击选择部门
	$("#groupSelect").click(function() {
				selectGroup();
				return false;
			});
	// 单击部门清除
	$("#groupRemove").click(function() {
				$("#groupId").val("");
				$("#group").val("");
				return false;
			});

	
});
function addUser(){
	var valid=validator($("#userForm")[0]);
    if(valid){
       submitForm();
    }
    return false;
	
}

/**
 * 添加管理员信息
 */
function submitForm() {
	// 登录名
	var loginName = $.trim($("#loginName").val());
	// 密码
	var password = $.trim($("#password").val());
	// 姓名
	var userName = $.trim($("#userName").val());
	// 部门id
	var groupId = $.trim($("#groupId").val());
//	if(!groupId||groupId==0){
//	 art.dialog.alert("请选择部门！");
//	 return false;
//	}
	
	// 手机
	var phone = $.trim($("#phone").val());
	
	// 主角色
	//var roleIds = $.trim($("#roleId").val());
	var roleIds = $.trim($("#myroleId").val());
	
	var paramData = {
		'user.loginName' : loginName,
		'user.userName' : userName,
		'user.loginPass' : password,
		'groupId' : groupId,
		'user.phone' : phone,
		'roleIds' : roleIds,
		'type' : "add"
	};
	$.ajax({
				type : 'post',
				url : basePath + "user/addUser.action",
				data : paramData,
				dataType : 'text',
				success : function(data) {
					if (data == "") {		
						art.dialog.alert("添加人员成功！",function(){
							var win = artDialog.open.origin;//来源页面
							win.art.dialog({id: 'addUser'}).close();
							win.getDataTable();
						});
					} else {
						art.dialog.alert(data);
					}

				}
			});
}


