/**
 * @author REN

 */
$(document).ready(function() {
	$("#search").click(function(){
		$.removeTableCookie('SpryMedia_DataTables_userTable_userList.jsp');
		getDataTable();
		return false;
	});
	
	//回车事件
	$(document).keydown(function(event){
		var code=event.which;
		if (code == 13) {
			$.removeTableCookie('SpryMedia_DataTables_userTable_userList.jsp');
			getDataTable();
			return false;
		}
	}); 
	
	
	// 清除cookie中的分页信息
	$.removeTableCookie('SpryMedia_DataTables_userTable_userList.jsp');
	//添加人员
    $("#btnAddUser").click(function(){
    	/*var groupId=$("#groupId").val();
        if(groupId=="" || groupId==0)
        {
            art.dialog.alert("请选择要添加人员的群组!");
            return;
        }
        selectUser('userNames','userIds');*/
    	var url = basePath + "logined/group_ext/userAdd.jsp?groupId="+$("#groupId").val();
    	art.dialog.open(url,
    	        {	id : "addUser",
    	            title:"新增人员",
    	            width : 600,
    	            height : 300,
    			    opacity: 0.08,
    			    close:function(){
    			    	//refreshTree("gid_0");
    			    },
    			    button: [{name: '确定',focus: true,callback :function(){
    			    	var iframe = this.iframe.contentWindow;
    			    	iframe.addUser();
    			    	return false;
    			    }},{name: '取消',callback:function(){
    			    	//refreshTree("gid_0");
    			    	return true;
    			    }}]
    	        });

    });
    
    //移动人员到另一群组
    $("#moveToGroup").click(function(){
    	var ids = _checkedIds;
    	if(ids.length <= 1){
    		art.dialog.alert('请选择要移动的人员！');
    		return false;
    	}
    	openSelectUser(1,callBackMoveGroup,ids);//选择人员
    });
  //---------------------------------------------------移动到群组
    
   function callBackMoveGroup(data)
    {
        if(data){
        	var userIds = [];
        	var userNames = [];
            data.forEach(function(value) {
               userIds.push(value.Id);
               userNames.push(value.Name);
            });
            var ids = _checkedIds; //选择人员Id
            var groupId = $("#groupId").val();
            var url = basePath+"group/moveUserToGroup.action";
            var paramData = {"userIds":ids,
            				  "groupId":userIds[0],
            				  "groupName":userNames[0],
            				  "oldGroupId":groupId
            				};
            $.ajax({
            	url:url,
            	data:paramData,
            	type:"POST",
            	dataType:"html",
            	success:function(data){
            		if(data == 1){
            			art.dialog.alert("移动人员到群组操作失败,请稍后重试!");
            		}else{
            			// 刷新左边的人员树
    					window.parent.refreshTree("gid_" + $("#groupId").val());
    					getDataTable();
            		}
            	}
            });
        }
    }
    function openSelectUser(showType,callback,defaultSelectId) {
        var url = basePath + "/logined/group_ext/selectuserSign.jsp?showType="+showType+"&defaultSelectId="+defaultSelectId;
        var title="选择人员";
        if(showType==1)
        {
            title="选择部门";
        }
        else if(showType==2)
        {
            title="选择角色";
        }
        art.dialog.open(url,
            {
                title:title,
                width : 360,
    			height : 407,
    			lock : true,
    		    opacity: 0.08,
//    		    resize:false,
//    		    drag:false,
                button:[
                    {
                        name:'确定',
                        callback:function () {
                            var userMap =art.dialog.data("userMap");
                            callback(userMap);
                            return true;
                        }
                    },
                    {
                        name:'取消',
                        callback:function () {
                            return true;
                        }
                    }
                ]
            }, false);

    }
    
   //移动到群组结束---------------------------------------------------- 
    
    
    
    
    $("#btnDeleteUser").click(function(){
    	deleteUser();
    });
	
	getDataTable();
	$("#searchButton").click(function(){
		getDataTable();
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
	
	
});
/**
 * 获取管理员信息列表
 */
function getDataTable() {
	_checkedIds="";
	$("#total").prop("checked", false);
	$('#userTable').dataTable({
		"bProcessing" : true,
		'bServerSide' : true,
		'fnServerParams' : function(aoData) {
			var searchName = $.trim($("#searchName").val());
			var groupId = $.trim($("#groupId").val());
			if (groupId == "") {
				groupId = null;
			}
			var sex = $.trim($("#sex").val());
			if (sex == "") {
				sex = null;
			}
			var mobileViewState = $("#mobileViewState").val();
			aoData.push({
						"name" : "userVo.userName",
						"value" : searchName
					},{
						"name" : "userVo.loginName",
						"value" : searchName
					},{
						"name" : "userVo.groupId",
						"value" : groupId
					}, {
						"name" : "userVo.sex",
						"value" : sex
					}, {
						"name" : "mobileViewState",
						"value" : mobileViewState
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
					"sWidth" : "25",
					"sClass" : "chk"
				}, {
					"mDataProp" : "no",
					"sClass" : "num"
				}, {
					"mDataProp" : "userName",
					"sClass" : "tdCenter longTxt"
				}, {
					"mDataProp" : "loginName",
					"sClass" : "tdCenter longTxt"
				},{
					"mDataProp" : "phone",
					"sClass" : "tdCenter"
				},{
					"mDataProp" : 'groupName',
					"sClass" : "tdCenter longTxt"
				},{
					"mDataProp" : null,
					"sClass" : "right_bdr0"
				}],
		"oLanguage" : {
			"sUrl" : basePath + "plugins/datatable/cn.txt" // 中文包
		},
		"fnDrawCallback" : function(oSettings) {
			$("#total").prop("checked", false);
			// 重置iframe高度
			$(".longTxt").each(function(){
   				this.setAttribute('title', $(this).text());
   			});
			_getChecked();
		},
		"fnInitComplete" : function() {
			// 重置iframe高度
//			window.parent.frameResize();
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
		},{
			"aTargets" : [6],
			"fnRender" : function(oObj) {
				var whroletype = $("#whroletype").val();
				var currentUserId = $("#currentUserId").val();
				var id = oObj.aData.id;
				//alert('currentUserId='+currentUserId+', id='+id+', currentUserId==id?  '+(currentUserId == id));
				var html = '';
				if(whroletype == 3){
					if(currentUserId == id){
						html = '<a href="javascript:void(0)" onclick="updateUser('+id+')">编辑</a>&nbsp;<a href="javascript:void(0)" onclick="updatePassword('+id+')">修改密码</a>';
					}else {
						//html = '<a href="javascript:void(0);" style="color:#666">编辑</a>&nbsp;<a href="javascript:void(0);" style="color:#666">修改密码</a>';
						html = '--';
					}
				}else {
					html = '<a href="javascript:void(0)" onclick="updateUser('+id+')">编辑</a>&nbsp;<a href="javascript:void(0)" onclick="updatePassword('+id+')">修改密码</a>';
				}
				return html;
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

function deleteUser(){
	var groupId = $.trim($("#groupId").val());
	/*if(groupId == ""||groupId == 0){
		art.dialog.alert('请选择部门！');
		return false;
	}*/
	
	var ids = _checkedIds;
	if(ids.length <= 1){
		art.dialog.alert('请选择要删除的人员信息！');
		return false;
	}
	
	art.dialog.confirm('确定删除已选择的人员吗？', function() {
		$.ajax({
			type:	"post",
			url:	basePath + "companywh/deleteLoginUser.action",
			data:	{"userIds":ids,"groupId":groupId},
			dataType:"text",
			success:function(data){
				if(data == 1){
					//art.dialog.alert('删除人员成功！');
					// 刷新左边的人员树
//					window.parent.refreshTree("gid_" + $("#groupId").val());
					getDataTable();
				}else{
					art.dialog.alert('删除失败！');
				}
			}
		});
	});
}


document.onkeydown = function(){
	if(event.keyCode == 13){
		$("#searchButton").click();
	}
}


/**
 *   打开人员选择树
 */
function selectUser(forName, forId) {
    var param = new HashMap();
    param.set("forName", forName);
    param.set("forId", forId);
    openSelectUser(3, selectCallBack, param,$("#"+forId).val());
}

function cleanUser(forName, forId) {
    $("#" + forName).val("");
    $("#" + forId).val("");
}
/**
 *人员选择树回调方法
 */
function selectCallBack(data, param) {
	 var userIds = '';
     var userNames = '';
     data.forEach(function (value, key) {
            var userId = value.Id;
            var userName = value.Name;
            userIds += userId+",";
            userNames += userName+",";
     });
     var forName = param.get("forName");
     var forId = param.get("forId");
     $("#" + forName).val(userNames);
     $("#" + forId).val(userIds);
     formSave(userNames,userIds);
}


/**
 * 保存
 */
function formSave(userNames,userIds){
	//var userIds = $("#userIds").val();
	//var userNames = $("#userNames").val();
	var userIds = userIds;
	var userNames = userNames;
	var groupId = $("#groupId").val();
	$.ajax({
		type:	"post",
		url:	basePath + "groupExt/moveUserToGroup.action",
		data:	{"userIds":userIds,"groupId":groupId},
		dataType:"text",
		success:function(data){
			var win = art.dialog.open.origin;// 来源页
			if(data==1||data==2){
				win.document.location = basePath + "logined/group_ext/userList.jsp?groupId="+groupId;
				//art.dialog.alert("保存成功！",function(){});
			}
		}
	});
}
/**
 * 修改人员信息
 */
function updateUser(id){
	var url = basePath + "user/getUserById.action?type=wh&userId="+id;
	art.dialog.open(url,
	        {	id : "updateUser",
	            title:"编辑人员",
	            width : 600,
	            height : 250,
			    opacity: 0.08,
			    close:function(){
			    	//refreshTree("gid_0");
			    },
			    button: [{name: '确定',focus: true,callback :function(){
			    	var iframe = this.iframe.contentWindow;
			    	iframe.submitForm();
			    	return false;
			    }},{name: '取消',callback:function(){
			    	//refreshTree("gid_0");
			    	return true;
			    }}]
	        });
}
/**
 * 修改密码
 */
function updatePassword(userId){
	var url = basePath + "sysset/toPwdSet.action?userId="+userId;
	art.dialog.open(url,
	        {	id : "updatePassword",
	            title:"修改密码",
	            width : "630px",
	            height : "300px",
			    opacity: 0.08,
			    button: [{name: '确定',focus: true,callback :function(){
			    	var iframe = this.iframe.contentWindow;
			    	iframe.pwdSet();
			    	return false;
			    }},{name: '取消',callback:function(){
			    	//refreshTree("gid_0");
			    	return true;
			    }}]
	        });
	
}

