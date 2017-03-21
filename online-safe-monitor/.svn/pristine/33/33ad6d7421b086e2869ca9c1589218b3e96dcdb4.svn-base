/**
 * 人员更新
 */

$(document).ready(function() {
	document.getElementById("userName").focus();
	   		//删除头像
		    $("#deletePhoto").live("click",function(){
		        $("#photo").val("");
		        var photoUrl = basePath + "/images/default_photo.png";
		        $("#photoImg").attr("src", photoUrl);
		        $("#deletePhoto").hide();        
		    });
	
			// 单击更新
			$("#userInfoUpdate").click(function() {
						var cr = check();
						if(cr){
							var valid = validator($("#userForm")[0]);
							if (valid) {
								submitForm();
							}
							return false;
						}
					});
			// 单击取消
			$("#back").click(function() {
				window.location.href = document.referrer;
						return false;
					});
			// 显示辅助选项
			$("#showAssist").click(function() {
						$("#assistContent").toggle();
						return false;
					});
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
			// 单击辅助角色
			$("#assistSelect").click(function() {
						selectUserAssist();
						return false;
					});
			// 单击辅助角色清除
			$("#assistRemove").click(function() {
						$("#assistId").val("");
						$("#assist").val("");
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

			// 用户权限信息-管理范围-选择
			$("#managementRange").bind("change click", function() {
						if ($(this).val() == "5") {
							$("#appointGroupTr").show();
						} else {
							$("#appointGroupTr").hide();
							$("#appointGroupIds").val("");
							$("#appointGroupNames").val("");
						}
					});

			// 管理范围-部门-单击添加
			$("#appointGroupAdd").click(function() {
						selectAppointGroup();
						return false;
					});
			// 管理范围-部门-单击清空
			$("#appointGroupRemove").click(function() {
						$("#appointGroupIds").val("");
						$("#appointGroupNames").val("");
					});
			
		});
/**
 * 添加管理员信息
 */
function submitForm() {
	// 登录名
	var loginName = $.trim($("#loginName").val());
	// 姓名
	var userName = $.trim($("#userName").val());
	// 部门id
	var groupId = $.trim($("#groupId").val());
	if(!groupId||groupId==0){
	 art.dialog.alert("请选择部门！");
	 return false;
	}
	// 用户排序号
	var userOrder = $.trim($("#userOrder").val());
	// 别名
	var alterName = $.trim($("#alterName").val());
	var job = $.trim($("#job").val());
	// 性别
	var sex =  $('input[name="sex"]:checked').val();
	// 生日
	var birthDay = $.trim($("#birthDay").val());
//	if (birthDay != "") {
//		birthDay += " 00:00:00.000";
//	}
	// 手机
	var phone = $.trim($("#phone").val());
	// 手机号是否公开1公开，0不公开
	var phonePublic = 1;
	if ($("#phonePublic").prop("checked")) {
		phonePublic = 0;
	}
	// 电子邮件
	var email = $.trim($("#email").val());
	// 工作电话
	var phone2 = $.trim($("#phone2").val());
	// 主角色
	var roleIds = $.trim($("#roleId").val());
	//签章类型
	var signType = $("#userSign").val();
	var signUrl = $("#imgSignUrl").val();
	var sign = $("#sign").val();
	var office = $("#office").val();
	var print = $("#print").val();
	var photo = $("#photo").val();
	var paramData = {
		'user.loginName' : loginName,
		'user.userName' : userName,
		'groupId' : groupId,
		'user.orderIndex' : userOrder,
		'user.alterName' : alterName,
		'user.sex' : sex,
		'birthDay' : birthDay,
		'user.phone' : phone,
		'user.phonePublic' : phonePublic,
		'user.email' : email,
		'user.phone2' : phone2,
		'roleIds' : roleIds,
		'user.job':job,
//		'managementRange' : $("#managementRange").val(),
//		'appointGroupIds' : $("#appointGroupIds").val(),
//		'appointGroupNames' : $("#appointGroupNames").val(),
		'type' : 'address',
		'user.signType':signType,
		'user.signUrl':signUrl,
		'user.taoDa':print,
		'user.officeWidget':office,
		'user.photo':photo,
		'user.sinWidget':sign
	};
	$.ajax({
				type : 'post',
				url : basePath + "user/addUser.action",
				data : paramData,
				dataType : 'text',
				success : function(data) {
					if (data == "") {		
//						art.dialog.alert("添加人员成功！",function(){
							window.location.href = document.referrer;
							var refreshTree = window.parent.refreshTree;
							if (undefined != refreshTree && "" != refreshTree){
								refreshTree("gid_"+groupId);
							}
							//更新人员is_fork_group字段
//							var paramDataName = {
//								"phone":phone
//							};
//							$.ajax({
//								url : basePath+"hotlineuser/updateUserIsforkgroup.action",
//		                        type : "post",
//		                        dataType :'text',
//		                        data:paramDataName
//							});
//						});
					} else {
						art.dialog.alert(data);
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