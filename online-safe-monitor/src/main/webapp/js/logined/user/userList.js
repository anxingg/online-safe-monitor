/**
 * @author REN

 */
var pd=0;
var _userCount=0;
$(document).ready(function() {
	// 清除cookie中的分页信息
//	$.removeTableCookie('SpryMedia_DataTables_userTable_userList.jsp');
	// 获取人员信息列表
	getInfo();
	$("#searchButton").click(function(){
		$.removeTableCookie('SpryMedia_DataTables_userTable_userList.jsp');
		pd=1;
		// 获取人员信息列表
		if ($("#type").val() == 'view'){
			_checkedIds="";
			getViewDataTable();
		}else{
			_checkedIds="";
			getDataTable();
		}
	});
	
	// 打开新增页面
	$("#btnAddUser").click(function() {
				document.location = basePath + "logined/user/add.jsp";
				return false;
	});
	// 头部全选复选框
	$("#userTable").delegate("#total", "click", function(event) {
				checkTotal();
				event.stopPropagation();
			});
	// 第一列复选按钮
	$("#userTable").delegate("input:checkbox[name='chk']", "click",
			function(event) {
				check();
				event.stopPropagation();
			});
	// 单击删除
	$("#userDelete").click(function() {
				deleteUser();
				return false;
			});
	// 单击新增
	$("#userAdd").click(function() {
		window.location.href = basePath + "logined/user/userAdd.jsp?groupId="
				+ $("#groupId").val();
		return false;
	});
	// 重置密码
	$("#initPassword").click(function() {
				setPassword();
				return false;
			});
	// 登录设置
	$("#userTable").delegate("a[name='loginUrl']", "click", function(event) {
				var userState = $(this).attr("userState");
				var userId = $(this).attr("userId");
				setUserState(userId, userState);
				return false;
			});
	
	
	// 单击导出
	$("#userExport").click(function() {
		if(_userCount<=0){
			art.dialog.alert("没有要导出的数据");
			return false;
		}
		 var data = $("#userSelectFrom").serialize();
		 data = "userVo." + data;
		 var re = /&/g;
		 var sex = $.trim($("#sex").val());
		 data = data.replace(re, "&userVo.");
		 data +="&userVo.sex="+sex;
		 var loginName = $.trim($("#searchName").val());
		 data +="&userVo.loginName="+loginName;
		 data +="&selectedIds="+_checkedIds;
		/**   var data = "";
		   var chkbox = document.getElementsByName("chk");
			var userIds = "";
			var selLen = 0;
			for (var i = 0; i < chkbox.length; i++) {
				if (chkbox[i].checked) {
					userIds += chkbox[i].value + ",";
					selLen++;
				}
			}
			if(selLen<=0){
				art.dialog.alert("请勾选要导出的数据");
				return false;
			}
			
			data="userIds="+userIds;
			*/
		  document.location.href=basePath + "user/exportUserInfo.action?" + data;
		 //   document.location.href=basePath + "user/exportUserInfoCheck.action?" + data;
		//window.open(basePath + "user/exportUserInfo.action?"
		//		+ data);
		return false;
		 
	});
	
	// 捕获回车事件
//    $("html").die().live("keydown", function(event) {
//        if (event.keyCode == 13) {
//        	$.removeTableCookie('SpryMedia_DataTables_userTable_userList.jsp');
//        	pd=1;
//    		// 获取人员信息列表
//    		if ($("#type").val() == 'view'){
//    			_checkedIds="";
//    			getViewDataTable();
//    		}else{
//    			_checkedIds="";
//    			getDataTable();
//    		}
//        }
//    });
    
 // 回车事件
	document.onkeydown = function(e) {
		// 兼容FF和IE和Opera
		var theEvent = e || window.event;
		var code = theEvent.keyCode || theEvent.which || theEvent.charCode;
		if (code == 13) {
			$.removeTableCookie('SpryMedia_DataTables_userTable_userList.jsp');
        	pd=1;
    		// 获取人员信息列表
    		if ($("#type").val() == 'view'){
    			_checkedIds="";
    			getViewDataTable();
    		}else{
    			_checkedIds="";
    			getDataTable();
    		}
		}
	};
	
	
	
});

function getInfo(){
	if ($("#type").val() == 'view'){
		getViewDataTable();

	}else{
		getDataTable();

	}
}

/**
 * 获取管理员信息列表
 */
function getDataTable() {
	$("#total").prop("checked", false);
	$('#userTable').dataTable({
		"bProcessing" : true,
		'bServerSide' : true,
		'fnServerParams' : function(aoData) {
			var loginName = $.trim($("#searchName").val());
			if (loginName == "") {
				loginName = null;
			}
			var sex = $.trim($("#sex").val());
			if (sex == "") {
				sex = null;
			}
			var roleId = $.trim($("#roleId").val());
			if (roleId == "") {
				roleId = null;
			}
			var groupId = $.trim($("#groupId").val());
			if (groupId == "") {
				groupId = null;
			}
			
			var loginOrder = $.trim($("#loginOrder").val());
			if (loginOrder == "") {
				loginOrder = null;
			}
//			alert("loginName="+loginName+"; sex="+sex+"; roleId="+roleId+"; groupId="+groupId+"; loginOrder="+loginOrder+"; pd="+pd);
			aoData.push({
						"name" : "userVo.loginName",
						"value" : loginName
					}, {
						"name" : "userVo.userName",
						"value" : loginName
					}, {
						"name" : "userVo.sex",
						"value" : sex
					}, {
						"name" : "userVo.groupId",
						"value" : groupId
					}, {
						"name" : "userVo.roleId",
						"value" : roleId
					}, {
						"name" : "loginOrder",
						"value" : loginOrder
					}, {
						"name" : "pd",
						"value" : pd
					},{
						"name":"userVo.phone",
						"value":loginName
					});
		},
		 
		"sAjaxSource" : basePath + "user/getUserListByGroupId.action",// 获取管理员列表
		"sServerMethod" : "POST",
		"sPaginationType" : "full_numbers",
		"bPaginate" : true, // 翻页功能
		"bStateSave" : true, // 状态保存
		"bLengthChange" : false, // 改变每页显示数据数量
		"bFilter" : false, // 过滤功能
		"bSort" : false, // 排序功能
		"bInfo" : true,// 页脚信息
		"bAutoWidth" : false,// 自动宽度
		"bDestroy" : true,
		"iDisplayLength" : 15, // 每页显示多少行
		"aoColumns" : [{
					"sTitle" : "<input type='checkbox' id='total'/>",
					"mDataProp" : null,
					"sClass" : "chk"
				}, {
					"sTitle" : '序号',
					"mDataProp" : "no",
					"sClass" : "num"
//				}, {
//					"sTitle" : '用户名',
//					"mDataProp" : "loginName",
//					"sWidth" : "50%",
//					"sClass" : "tdCenter"
				}, {
					"sTitle" : '姓名',
					"mDataProp" : "userName",
					"sWidth" : "20%",
					"sClass" : "tdCenter"
				}, {
					"sTitle" : '性别',
					"mDataProp" : null,
					"sWidth" : "60",
					"sClass" : "tdCenter"
				}, {
					"sTitle" : '手机号码',
					"mDataProp" : "phone",
					"sWidth" : "20%",
					"sClass" : "tdCenter"
				}, {
					"sTitle" : '登录用户',
					"mDataProp" : null,
					"sWidth" : "20%",
					"sClass" : "tdCenter"
				}, {
					"sTitle" : '加入日期',
					"mDataProp" : "createTime",
					"sWidth" : "20%",
					"sClass" : "tdCenter"
				}, {
					"sTitle" : '最后登录',
					"mDataProp" : "lastLoginTime",
					"sWidth" : "20%",
					"sClass" : "tdCenter"
				}, {
					"sTitle" : '操作',
					"mDataProp" : null,
					"sWidth" : "70",
					 "sClass" : "right_bdr0"
					/*"sClass" : "oper"*/
				}],
		"oLanguage" : {
			"sUrl" : basePath + "plugins/datatable/cn.txt" // 中文包
		},
		"fnDrawCallback" : function(oSettings) {
			$("#totalNum").html(oSettings.fnRecordsDisplay());
			$("#companyAll").html(oSettings.fnRecordsDisplay());
			$("#selectedNum").html(0);
			$("#total").prop("checked", false);
			$(".longTxt").each(function(){
   				this.setAttribute('title', $(this).text());
   			});
			// 重置iframe高度
			window.parent.frameResize();
			_userCount =   oSettings.fnRecordsDisplay();
			//_getChecked();
		},
		"fnInitComplete" : function() {
			// 重置iframe高度
			window.parent.frameResize();
		},
		"aoColumnDefs" : [{
			"aTargets" : [0],
			"fnRender" : function(oObj) {
				var isDefault = oObj.aData.isDefault;
				if (isDefault == 0) {
					return '<input name="chk" disabled="disabled" value="'
							+ oObj.aData.id + '" type="checkbox" />';
				} else {
					return '<input name="chk" value="' + oObj.aData.id
							+ '" type="checkbox" />';
				}

			}
		}, {
			"aTargets" : [3],
			"fnRender" : function(oObj) {
				var sex = oObj.aData.sex;
				var sexName = "";
				if (sex == 1) {
					sexName = "男";
				} else if (sex == 0){
					sexName = "女";
				} 
			 
				return sexName;
			}
		}, {
			"aTargets" : [5],
			"fnRender" : function(oObj) {
				var userState = oObj.aData.userState;
				var userStateName = "";
				if (userState == 0 || userState == 1) {
					userStateName = "是";
				} else {
					userStateName = "否";
				}
				return userStateName;
			}
		}, {
			"aTargets" : [8],
			"fnRender" : function(oObj) {
				var managerId = oObj.aData.id;
				var updateStr = basePath + "user/getUserById.action?userId="
						+ managerId;

				var userState = oObj.aData.userState;

				var isDefault = oObj.aData.isDefault;

				var loginStr = "";
				var loginStrA = "";
//				if (isDefault != 0) {
//					if (userState == 1) {
//						loginStrA = '<a href="#" class="view_url" name="loginUrl" userState="0" userId="'
//								+ managerId + '">允许登录</a>';
//					} else {
//						loginStrA = '<a href="#" class="view_url" name="loginUrl" userState="1" userId="'
//								+ managerId + '">禁止登录</a>';
//					}
//				}
				return '<a href="' + updateStr + '" class="view_url" id="updateUrl">修改</a>' + loginStrA;
				
			}
		}]
	});
}
/**
 * 头部全选记录
 */
function checkTotal() {
	var isTotalChecked = $("input:checkbox[id='total']").prop("checked");
	var listCheckbox = $("input:checkbox[name='chk']");
	if (isTotalChecked) {
		listCheckbox.prop("checked", function(i, val) {
					if (!$(this).prop("disabled")) {
						return true;
					}
				});
	} else {
		listCheckbox.prop("checked", false);
	}
}
/**
 * 选择记录
 */
function check() {
	var checkTotalNum = $("input:checkbox[name='chk']");
	var checkNum = 0;
	var checkNumAll = true;
	checkTotalNum.each(function(i, val) {
				if ($(checkTotalNum[i]).prop("checked")) {
					checkNum++;
				} else {
					checkNumAll = false;
				}
			});
	if (!checkNumAll) {
		$("#total").prop("checked", false);
	} else {
		$("#total").prop("checked", true);
	}
}
/**
 * 删除人员
 */
function deleteUser() {
	// 获取选择管理员id
	var chkbox = document.getElementsByName("chk");
	var userIds = _checkedIds;
	var selLen = _checkedIds.length;
	if (selLen <= 1) {
		art.dialog.alert('请至少选择一项！');
		return;
	}
	var groupId=$("#groupId").val();
	var paramData = {
		'userIds' : userIds,
		'groupId' : groupId
	};
	// 删除管理员
	art.dialog.confirm('确定要删除吗？', function() {
				$.ajax({
							url : basePath + "hotlineuser/deleteUser.action",
							type : "post",
							dataType : 'text',
							data : paramData,
							success : function(data) {
								if (data == "success") {
									// 刷新左边的人员树
//									art.dialog.alert('删除人员成功！');
									window.parent.refreshTree("gid_" + $("#groupId").val());
									_checkedIds="";
									getDataTable(); // 刷新人员信息
								}else{
									art.dialog.alert('操作失败！');
								}
							}
						});
			});
}
/**
 * 重置密码
 */
function setPassword() {
	// 获取选择管理员id
	var chkbox = document.getElementsByName("chk");
	var userIds = "";
	var selLen = 0;
	for (var i = 0; i < chkbox.length; i++) {
		if (chkbox[i].checked) {
			userIds += chkbox[i].value + ",";
			selLen++;
		}
	}
	if (selLen == 0) {
		art.dialog.alert('请选择要重置密码的人员！');
		return;
	}
	var paramData = {
		'userIds' : userIds
	};
	art.dialog.confirm('确定重置密码吗？', function() {
				$.ajax({
							url : basePath + "user/updateUserPassword.action",
							type : "post",
							dataType : 'text',
							data : paramData,
							success : function(data) {
								if (data == "") {
									art.dialog.alert('重置密码成功！');
								} else {
									art.dialog.alert(data);
								}
							}
						});
			});
}
/**
 * 禁止登录
 */
function setUserState(userId, userState) {
	var paramData = {
		'userId' : userId,
		'userState' : userState
	};
	var userStateTip = "";
	if (userState == 1) {
		userStateTip = '确定禁止该人员登录吗？';
	} else {
		userStateTip = '确定允许该人员登录吗？';
	}
	art.dialog.confirm(userStateTip, function() {
				$.ajax({
							url : basePath + "user/updateUserState.action",
							type : "post",
							dataType : 'text',
							data : paramData,
							success : function(data) {
								if (data == "") {
									art.dialog.alert('设置成功！');
									getDataTable();
								} else {
									art.dialog.alert(data);
								}
							}
						});
			});
}


function getViewDataTable() {
	$('#userTable').dataTable({
		"bProcessing" : true,
		'bServerSide' : true,
		'fnServerParams' : function(aoData) {
			var loginName = $.trim($("#searchName").val());
			if (loginName == "") {
				loginName = null;
			}
			var userName = $.trim($("#searchName").val());
			if (userName == "") {
				userName = null;
			}
			var sex = $.trim($("#sex").val());
			if (sex == "") {
				sex = null;
			}
			var roleId = $.trim($("#roleId").val());
			if (roleId == "") {
				roleId = null;
			}
			var groupId = $.trim($("#groupId").val());
			if (groupId == "") {
				groupId = null;
			}
			var loginOrder = $.trim($("#loginOrder").val());
			if (loginOrder == "") {
				loginOrder = null;
			}
			aoData.push({
						"name" : "userVo.loginName",
						"value" : loginName
					}, {
						"name" : "userVo.userName",
						"value" : userName
					}, {
						"name" : "userVo.sex",
						"value" : sex
					}, {
						"name" : "userVo.groupId",
						"value" : groupId
					}, {
						"name" : "userVo.roleId",
						"value" : roleId
					}, {
						"name" : "loginOrder",
						"value" : loginOrder
					}, {
						"name" : "pd",
						"value" : pd
					},{
						"name":"userVo.phone",
						"value":loginName
					});
		},
		"sAjaxSource" : basePath + "user/getUserListByGroupId.action", // 获取管理员列表
		"sServerMethod" : "POST",
		"sPaginationType" : "full_numbers",
		"bPaginate" : true, // 翻页功能
		"bStateSave" : true, // 状态保存
		"bLengthChange" : false, // 改变每页显示数据数量
		"bFilter" : false, // 过滤功能
		"bSort" : false, // 排序功能
		"bInfo" : true,// 页脚信息
		"bAutoWidth" : false,// 自动宽度
		"bDestroy" : true,
		"iDisplayLength" : 15, // 每页显示多少行
		"aoColumns" : [{
					"sTitle" : '序号',
					"mDataProp" : "no",
					"sClass" : "num"
//				}, {
//					"sTitle" : '用户名',
//					"mDataProp" : "loginName",
//					"sWidth" : "50%",
//					"sClass" : "tdCenter"
				}, {
					"sTitle" : '姓名',
					"mDataProp" : "userName",
					"sWidth" : "90",
					"sClass" : "tdCenter"
				}, {
					"sTitle" : '性别',
					"mDataProp" : null,
					"sWidth" : "100",
					"sClass" : "tdCenter"
				}, {
					"sTitle" : '职务',
					"mDataProp" : "job",
					"sWidth" : "20%",
					"sClass" : "tdCenter"
				}, {
					"sTitle" : '手机号码',
					"mDataProp" : "phone",
					"sWidth" : "20%",
					"sClass" : "tdCenter"
				}, {
					"sTitle" : '登录用户',
					"mDataProp" : null,
					"sWidth" : "20%",
					"sClass" : "tdCenter"
				}, {
					"sTitle" : '加入日期',
					"mDataProp" : "createTime",
					"sWidth" : "20%",
					"sClass" : "tdCenter"
				}, {
					"sTitle" : '最后登录',
					"mDataProp" : "lastLoginTime",
					"sWidth" : "20%",
					"sClass" : "tdCenter"
				}],
		"oLanguage" : {
			"sUrl" : basePath + "plugins/datatable/cn.txt" // 中文包
		},
		"fnDrawCallback" : function(oSettings) {
			$("#totalNum").html(oSettings.fnRecordsDisplay());
			$("#companyAll").html(oSettings.fnRecordsDisplay());
			$("#selectedNum").html(0);
			$("#total").prop("checked", false);
			// 重置iframe高度
			window.parent.frameResize();
			
			_userCount =   oSettings.fnRecordsDisplay();
			//_getChecked();
		},
		"fnInitComplete" : function() {
			// 重置iframe高度
			window.parent.frameResize();
		},
		"aoColumnDefs" : [{
			"aTargets" : [2],
			"fnRender" : function(oObj) {
				var sex = oObj.aData.sex;
				var sexName = "";
				if (sex == 1) {
					sexName = "男";
				} else {
					sexName = "女";
				}
				return sexName;
			}
		}, {
			"aTargets" : [4],
			"fnRender" : function(oObj) {
				var phone = oObj.aData.phone;
				var phonePublic = oObj.aData.phonePublic;
				if (phonePublic == '0') {
					return "";
				} else {
					return phone;
				}
			}
		}, {
			"aTargets" : [5],
			"fnRender" : function(oObj) {
				var userState = oObj.aData.userState;
				var userStateName = "";
				if (userState == 0 || userState == 1) {
					userStateName = "是";
				} else {
					userStateName = "否";
				}

				return userStateName;
			}
		}]
	});
}
document.onkeydown = function(){
	if(event.keyCode == 13){
		$("#searchButton").click();
	}
}
