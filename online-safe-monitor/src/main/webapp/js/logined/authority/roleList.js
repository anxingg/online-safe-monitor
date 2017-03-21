/**
 * @author REN
 */
$(document).ready(function() {
	// 清除cookie中的分页信息
	$.removeTableCookie('SpryMedia_DataTables_myTable_roleList.jsp');
	_checkedIds="";
	// 获取权限信息列表
	getDataTable();
	// 打开新增页面
	$("#addRole").click(function() {
	        	art.dialog.data("getDataTable", getDataTable);
				art.dialog.open(basePath + "logined/authority/roleAdd.jsp", 
					{
						title: '新增角色' ,
						width:600,
						height:200,
						button: [{name: '确定',
							callback: function () {
									var iframe = this.iframe.contentWindow;
						    		iframe.addRole();
						    		return false;
								},
								focus: true},{name: '关闭'}]});
				return false;
			});
	// 全选
	$("#selectAll").click(function() {
				checkAll();
				return false;
			});
	// 反选
	$("#unselectAll").click(function() {
				reverseCheck();
				return false;
			});
	// 头部全选复选框
	$("#myTable").delegate("#total", "click", function(event) {
				checkTotal();
				event.stopPropagation();
			});
	// 第一列复选按钮
	$("#myTable").delegate("input:checkbox[name='chk']", "click",
			function(event) {
				check();
				event.stopPropagation();
			});
	// 单击删除
	$("#deleteRole").click(function() {
				deleteRole();
				return false;
			});
	// 单击主角色选择
//	$("#myTable").delegate("a[name='roleUsers']", "click", function(event) {
//				$("#roleId").val($(this).attr("roleId"));
//				selectRoleUsers($(this).attr("usersId"));
//				return false;
//			});
	// 单击辅助角色选择
	$("#myTable").delegate("a[name='assistUsers']", "click", function(event) {
				$("#roleId").val($(this).attr("roleId"));
				selectRoleAssists($(this).attr("usersId"));
				return false;
			});
	
	// 单击主角色选择
	$("#myTable").delegate("a[name='roleUsers']", "click", function(event) {
		$("#roleId").val($(this).attr("roleId"));
		var paramData = {
				"roleId":$(this).attr("roleId") 
		};
		$.ajax({
			url : basePath + "authority/findRoleUserAjax.action",
			type : "post",
			dataType : 'text',
			data : paramData,
			success : function(data) {
				selectRoleUsers(data);
			}
		});
		return false;
	});
});
/**
 * 获取角色信息列表
 */
function getDataTable() {
	_checkedIds="";
	$("#total").prop("checked", false);
	$('#myTable').dataTable({
		"bDestroy" : true,
		"bProcessing" : true,
		'bServerSide' : true,
		'fnServerParams' : function(aoData) {
			aoData.push();
		},
		"sAjaxSource" : basePath + "authority/findRoleList.action",// 获取角色列表
		"sServerMethod" : "POST",
		"sPaginationType" : "full_numbers",
		"bPaginate" : true, // 翻页功能
		"bStateSave" : true, // 状态保存
		"bLengthChange" : false, // 改变每页显示数据数量
		"bFilter" : false, // 过滤功能
		"bSort" : false, // 排序功能
		"bInfo" : true,// 页脚信息
		"bAutoWidth" : false,// 自动宽度
		"iDisplayLength" : tableDisplayLength, // 每页显示多少行
		"aoColumns" : [{
					"sTitle" : "<input class='chk' type='checkbox' id='total'/>",
					"mDataProp" : null,
					"sWidth" : "30",
					"sClass" : "num"
				}, {
					"sTitle" : '序号',
					"mDataProp" : "no",
					"sWidth" : "50",
					"sClass" : "tdCenter"
				}, {
					"sTitle" : '角色名称',
					"mDataProp" : "roleName",
					"sWidth" : "150",
					"sClass" : "data_l"
				}, {
					"sTitle" : '角色说明',
					"mDataProp" : "memo",
					"sWidth" : "30%",
					"sClass" : "longTxt"
				},
				/*{
					"sTitle" : '所属地市',
					"mDataProp" : "address",
					"sWidth" : "30%",
					"sClass" : "longTxt"
				}, */
				/*{
					"sTitle" : '用户列表',
					"mDataProp" : null,
					"sWidth" : "70%",
					"sClass" : "longTxt"
				}, */
				{
					"sTitle" : '操作',
					"mDataProp" : null,
					"sWidth" : "170",
					"sClass" : "right_bdr0 data_l"
				}],
		"oLanguage" : {
			"sUrl" : basePath + "plugins/datatable/cn.txt" // 中文包
		},
		"fnDrawCallback" : function(oSettings) {
			$('#myTable tbody  tr td[class="longTxt"]').each(function() {
				this.setAttribute('title', $(this).text());
			});
			_getChecked();
		},
		"aoColumnDefs" : [{
			"aTargets" : [0],// 覆盖第一列
			"fnRender" : function(oObj) {
				var roleType = oObj.aData.roleType;
				if (roleType == 0) {
					return '<input name="chk" value="' + oObj.aData.id
							+ '" type="checkbox" disabled="disabled"/>';
				} else {

					// 角色下有成员的不允许删除
					var usersList = oObj.aData.usersList;
					var usersListAssist = oObj.aData.usersListAssist;
					if ((usersList && usersList.length > 0)
							|| (null != usersListAssist && usersListAssist.length > 0)) {
						return '<input name="chk" value="' + oObj.aData.id
								+ '" type="checkbox" disabled="disabled"/>';
					}
					return '<input name="chk" value="' + oObj.aData.id + '" type="checkbox" />';
				}
			}
		}, 
/*		{
			"aTargets" : [4],// 覆盖第5列
			"fnRender" : function(oObj) {
				var usersName = [];
				var usersList = oObj.aData.usersList;
				if (usersList) {
					for (var i = 0; i < usersList.length; i++) {
						usersName[i] = usersList[i].userName;
					};
				}
				return  usersName.toString();
				
			}
		}, */
		{
			"aTargets" : [4],// 覆盖第6列
			"fnRender" : function(oObj) {
				var usersId = [];
				var usersList = oObj.aData.usersList;
				if (usersList) {
					for (var i = 0; i < usersList.length; i++) {
						usersId.push(usersList[i].userId);
					};
				}
				var usersIdAssist = [];
				var usersListAssist = oObj.aData.usersListAssist;
				if (usersListAssist) {
					for (var i = 0; i < usersListAssist.length; i++) {
						usersIdAssist.push(usersListAssist[i].userId);
					};
				}
				var returnStr = "";
				var roleId = oObj.aData.id;
				var roleType = oObj.aData.roleType;
				var roleName = oObj.aData.roleNameView;
				var roleCode = oObj.aData.roleCode;
				var roleUserUrl = basePath
						+ "logined/authority/groupUserAdd.jsp?roleId=" + roleId
						+ "&roleName=" + roleName;
				var assistUserUrl = basePath
						+ "logined/authority/groupUserAdd.jsp?roleId=" + roleId
						+ "&roleName=" + roleName;
//				var roleModuleUrl = basePath
//						+ "authority/loadRoleModuleView.action?roleId="
//						+ roleId + "&roleName=" + roleName + "&sysName=ec";
				var roleModuleUrl = basePath
						+ "logined/authority/authorityList.jsp?roleId="
						+ roleId + "&roleName=" + roleName + "&sysName=ec";
				var updateStr = basePath
						+ "authority/loadRoleUpdateView.action?id=" + roleId;
				returnStr += '<a href="javascript:updateRole(\'' + updateStr
						+ '\')" id="updateUrl">编辑</a>';

				// 增加收文管理员的判断,收文管理员固定为gather_register 具体咨询陈秋利

				if (roleType == 1) {
					return '<a href="'
					+ roleUserUrl
					+ '"  id="updateUrl" name="roleUsers" roleId="'
					+ roleId
					+ '" usersId="'
					+ usersId.toString()
					+ '">成员维护</a>'
					+ '<a href="' + roleModuleUrl
					+ '"  id="updateUrl">功能授权</a>'
					+ returnStr
					+ '<a href="javascript:;" onclick="showUserList('+roleId+');" >用户列表</a>';
				} else if (roleType == 0 && roleCode != "administrator"){
					return '<a href="' + roleModuleUrl
					+ '" id="updateUrl">功能授权</a>'
					+ '<a href="javascript:;" onclick="showUserList('+roleId+');" >用户列表</a>';
				} else {
					return '<a href="'
					+ roleUserUrl
					+ '"  id="updateUrl" name="roleUsers" roleId="'
					+ roleId
					+ '" usersId="'
					+ usersId.toString()
					+ '">成员维护</a>'
					+ '<a href="' + roleModuleUrl
					+ '" id="updateUrl">功能授权</a>'
					+ '<a href="javascript:;" onclick="showUserList('+roleId+');" >用户列表</a>';
				}
			}
		}]
	});
}

/**
 * 查看用户列表
 */
function showUserList(roleId){
	//alert("roleId="+roleId);
	/*art.dialog.open({
		url	:	basePath+"authority/findRoleUser.action?roleId="+roleId,
		title:	"用户列表",
		customButton: [{name: '关闭'}]
	});*/
	art.dialog.open(basePath+"authority/findRoleUser.action?roleId="+roleId,{
		id:"userlist",
		width:'600px',
		height:'300px',
		title: '用户列表',
		lock:true,
		opacity: 0.0, //设为0.0时，锁屏时屏幕不变黑
		button : [{
			name : '确定'
		}],
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
 * 全选
 * 
 * @param name
 */
function checkAll() {
	var checkNum = 0;
	$("input:checkbox[name='chk']").prop("checked", function(i, val) {
				checkNum = checkNum + 1;
				return true;
			});
	$("input:checkbox[name='total']").prop("checked", true);
	$("#selectedNum").html(checkNum);
}
/**
 * 反选
 */
function reverseCheck() {
	var checkNum = 0;
	$("input:checkbox[name='chk']").prop("checked", function(i, val) {
				if (!val) {
					checkNum = checkNum + 1;
				}
				return !val;
			});
	$("#selectedNum").html(checkNum);
}
/**
 * 删除角色
 */
function deleteRole() {
	// 获取选择角色id
	var chkbox = document.getElementsByName("chk");
	var roleIds = "";
	var selLen = 0;
	var arr;
	if(_checkedIds!=null&&_checkedIds!=""){
		var checkedIds=_checkedIds.substring(0, _checkedIds.length-1);
		var arr=checkedIds.split(",");
		for (var i = 0; i < arr.length; i++) {
			roleIds += '&roleIds='+arr[i];
			selLen++;
		}
	}
	if (selLen == 0) {
		art.dialog.alert('请至少选择一项！');
		return;
	}

	// 删除角色
	art.dialog.confirm('确定要删除吗？', function() {
				$.ajax({
							url : basePath + "authority/deleteRole.action",
							type : "post",
							dataType : 'text',
							data : roleIds,
							success : function(data) {
								if (data == "2") {
									art.dialog.alert("该角色下存在用户,删除失败!");
									getDataTable();
								} else if (data != "") {
								//	art.dialog.alert('删除角色成功！');
									getDataTable();
								} else {
									art.dialog.alert('操作失败！');
									getDataTable();
								}
							}
						});
			}, function() {
				return;
			});
}

function updateRole(url){
	art.dialog.data("getDataTable", getDataTable);
	art.dialog.open(url, {
		title: '修改角色' ,
		width:600,
		height:200,
		button: [{name: '确定',
			callback: function () {
					var iframe = this.iframe.contentWindow;
		    		iframe.updateRole();
		    		return false;
				},
				focus: true},{name: '关闭'}]});
}