/**
 * 人员更新
 */

$(document).ready(function() {
	
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
		window.location.href = basePath+"/logined/user/userList.jsp";
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
	// 添加用户
	$("#forwordUserAdd").click(function() {
		window.location.href = basePath + "logined/user/userAdd.jsp?groupId="
				+ $("#defaultgroupId").val();
		return false;
	});

	// 用户权限信息-管理范围-选择
	var managementRange = $("#managementRange_hidden").val();
	if (managementRange != undefined && $.trim(managementRange) != "") {
		$("#managementRange").val(managementRange);
		if (managementRange == "appointGroup") {
			$("#appointGroupTr").show();
		}
	} else {
		// 未设置管理范围的用户,默认范围为全体部门
		$("#managementRange").val(4);
	}
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

	// 单击‘按模块设置管理范围’
	$("#moduleManagementRange").click(function() {
		var appBoxURL = basePath
				+ "user/moduleManagementRangePage.action?userId="
				+ $.trim($("#userId").val());
		art.dialog.open(appBoxURL, {
					id : "setManagementRangeByModuleDialog",
					title : "按模块设置管理范围",
					lock : true,
				    opacity: 0.08,
				    width : 800,
				    height : 450
				})
	});
});
/**
 * 添加管理员信息
 */
function submitForm() {
	var valid = validator($("#userForm")[0]);
	if (!valid) {
		return false;
	}
	// 用户id
	var userId = $.trim($("#userId").val());
	// 登录名
	var loginName = $.trim($("#loginName").val());
	// 姓名
	var userName = $.trim($("#userName").val());
	// 部门id
	var groupId = $.trim($("#groupId").val());
	// 手机
	var phone = $.trim($("#phone").val());
	// 主角色
	var roleIds = $.trim($("#roleId").val());
	var paramData = {
		'user.userId' : userId,
		'user.loginName' : loginName,
		'user.userName' : userName,
		'groupId' : groupId,
		'user.phone' : phone,
		'roleIds' : roleIds,
		'type' : 'address'  // 类型等于维护通讯录,至修改通讯录基本信息
	};
	$.ajax({
				type : 'post',
				url : basePath + "user/updateUser.action",
				data : paramData,
				dataType : 'text',
				success : function(data) {
					if (data == "success") {
						art.dialog.alert("更新人员成功！",function(){
							var win = artDialog.open.origin;//来源页面
							win.art.dialog({id: 'updateUser'}).close();
							win.getDataTable();
						});
					} else {
						art.dialog.alert(data);
					}
				}
			});
}

