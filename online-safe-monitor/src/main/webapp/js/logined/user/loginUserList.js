/**
 * @author REN
 */
$(document).ready(function() {
	// 清除cookie中的分页信息
//	$.removeTableCookie('SpryMedia_DataTables_userTable_loginUserList.jsp');

	// 获取人员信息列表
	getDataTable();
	
	// 打开新增页面
	$("#addLoginUser").click(function() {
		updateLoginUser();
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
	
	// 查询
	$("#searchLoginUser").click(function() {
		$.removeTableCookie('SpryMedia_DataTables_userTable_loginUserList.jsp');
		getDataTable();//要触发的方法
	    return false;
    });
	
	 //回车事件
	$(document).keydown(function(event){
		var code=event.which;
		if (code == 13) {
			// 清除cookie中的分页信息
			$.removeTableCookie('SpryMedia_DataTables_userTable_loginUserList.jsp');
			getDataTable();//要触发的方法
        }
	});
});
/**
 * 获取管理员信息列表
 */
function getDataTable() {

	$("#total").prop("checked", false);
	$('#userTable').dataTable({
		"bProcessing" : true,
		'bServerSide' : true,
		'fnServerParams' : function(aoData) {
			//新增关键字查询
			var key_word = $.trim($("#key_word").val());
			if (key_word == "") {
				key_word = null;
			}
			var loginName = $.trim($("#loginName").val());
			if (loginName == "") {
				loginName = null;
			}
			var userName = $.trim($("#userName").val());
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
						"name" : "userVo.keyWordForLogin_hotline",
						"value" : key_word
					}, {
						"name" : "userVo.from",
						"value" : 'loginManager'
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
		"iDisplayLength" : tableDisplayLength, // 每页显示多少行
		"aoColumns" :  // 排序 用户名 姓名 性别 单位/部门 职务 角色 创建日期 状态 操作
			  [{
					"sTitle" : '序号',
					"mDataProp" : "no"
				}, {
					"sTitle" : '登录名',
					"mDataProp" : "loginName",
					"sClass":"data_l"
				},  {
					"sTitle" : '姓名',
					"mDataProp" : "userName"
				}, {
					"sTitle" : '性别',
					"mDataProp" : null
				}, {
					"sTitle" : '手机号码',
					"mDataProp" : "phone"
				}, {
					"sTitle" : '单位/部门',
					"mDataProp" : "groupName",
					"sClass" : "data_l"
				}, {
					"sTitle" : '职务',
					"mDataProp" : 'job',
					"sClass" : "data_l"
				}, {
					"sTitle" : '角色',  
					"mDataProp" : "role",
					"sClass" : "longTxt"
				}, {
					"sTitle" : '创建日期',
					"mDataProp" : "registerTime"
				}, {
					"sTitle" : '状态',
					"mDataProp" : null
				}, {
					"sTitle" : '操作',
					"mDataProp" : null,
					"sClass" : "right_bdr0"
				}],  
		"oLanguage" : {
			"sUrl" : basePath + "plugins/datatable/cn.txt" // 中文包
		},
		"fnDrawCallback" : function(oSettings) {
			$("#totalNum").html(oSettings.fnRecordsDisplay());
			$("#companyAll").html(oSettings.fnRecordsDisplay());
			$("#selectedNum").html(0);
			
			$('#userTable tbody  tr td[class="longTxt"]').each(function() {
       			this.setAttribute('title', $(this).text());
       		});
			 
			$("#total").prop("checked", false);
			// 重置iframe高度
			frameResize();
		},
		"fnInitComplete" : function() {
			// 重置iframe高度
			frameResize();
		},
		"aoColumnDefs" : [{
			"aTargets" : [1],
            "fnRender" : function(oObj) {				
				var userId = oObj.aData.id;
				var loginName = oObj.aData.loginName;
				
				return '<a href="javascript:void(0);" onclick=detailLoginUser("'+userId+'")>'+loginName+'</a>';
			}

		},{
			"aTargets" : [3],
			"fnRender" : function(oObj) {
				var sex = oObj.aData.sex;
				var sexName;
				if (sex == "0"){
					sexName = "女"; 
				}else{
					sexName = "男"; 
				}
				return sexName;
			}
		}, {
			"aTargets" : [9],
			"fnRender" : function(oObj) {
				var userState = oObj.aData.userState;
				var userStateName = "";
				if (userState == 1) {
					userStateName = "不启用";
				} else if(userState == 0) {
					userStateName = "启用";
				}

				return userStateName;
			}
		}, {
			"aTargets" : [10],
			"fnRender" : function(oObj) {
				var managerId = oObj.aData.id;	
				var isDefault = oObj.aData.isDefault;
				if (isDefault == '0'){
					// 默认用户不允许修改和删除
					return '';
					/*return '<a "="" style="color:#888" href="javascript:void(0)">修改</a>'+
					'<a "="" style="color:#888" href="javascript:void(0)">删除</a>';*/
				}
				return '<a href="javascript:void(0);" class="view_url" onclick=updateLoginUser("'+managerId+'") id="updateUrl">修改</a>'
				+ '&nbsp;<a href="javascript:void(0);" class="view_url" onclick=deleteLoginUser("'+managerId+'") id="updateUrl">删除</a>';
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
	var userIds = "";
	var selLen = 0;
	for (var i = 0; i < chkbox.length; i++) {
		if (chkbox[i].checked) {
			userIds += chkbox[i].value + ",";
			selLen++;
		}
	}
	if (selLen == 0) {
		art.dialog.alert('请选择要删除的人员信息！');
		return;
	}
	var paramData = {
		'userIds' : userIds
	};
	// 删除管理员
	art.dialog.confirm('确定删除该人员吗？', function() {
				$.ajax({
							url : basePath + "user/deleteUser.action",
							type : "post",
							dataType : 'text',
							data : paramData,
							success : function(data) {
								if (data == "success") {
									getDataTable(); // 刷新人员信息
								}else{
									art.dialog.alert(data);
								}
							}
						});
			});
}

/**
 * 删除登录用户配置信息
 * 
 * @param userId
 *            用户Id
 */
function deleteLoginUser(userId){
	var paramData = {
			'userIds' : userId
		};
	// 删除管理员
	art.dialog.confirm('确定删除该人员吗？', function() {
		$.ajax({
					url : basePath + "hotlineuser/deleteLoginUser.action",
					type : "post",
					dataType : 'text',
					data : paramData,
					success : function(data) {
						if (data == "success") {
							getDataTable(); // 刷新人员信息
						}else{
							art.dialog.alert(data);
						} 
					}
				});
	});
}

function updateLoginUser(userId){
	art.dialog.data('getDataTable', getDataTable);
	var url ;
	var titleVal ;
	var idVal;
	if (null == userId){
		url = "logined/user/loginUserAddOrUpdate.jsp";
		titleVal= "新增登录用户";
		idVal="addLoginUser";
	}else{
		url = "hotlineuser/detailLoginUser.action?type=update&user.userId="+userId;
		titleVal= "修改登录用户";
		idVal="updateLoginUser";
	}
	art.dialog.open(basePath + url, 
		{title: titleVal ,
			width:800,opacity: 0.08,lock : true,height:510,button: [{name: '确定',callback: function () {
				var iframe = this.iframe.contentWindow;
		    	iframe.addOrUpdateuser();
	        return false;
	    },focus: true},{name: '取消'}]});
}

function detailLoginUser(userId){
	var url  = "hotlineuser/detailLoginUser.action?type=detail&user.userId="+userId;
	art.dialog.open(basePath + url, {title: '查看登录用户' ,width:800,height:470,button: [{name: '关闭',focus: true}]});
}
