var getDataTable;
$(document).ready(function() {
	setTimeout(function(){
		$("#loginName").focus();
	}, 200);
	 
	
    // 初始化是否显示
	var roleCodes = $("#roleCodes").val();
	if (null != roleCodes && "" != roleCodes){
		if (roleCodes.indexOf(",siter,") >= 0 || roleCodes.indexOf(",seatLeader,") >= 0){
			$("#seatTypeTr").show();
		}else{
			$("#seatTypeTr").hide();
		}
	}
	// 选择用户角色
	$("#selectRole").click(function() {
		openSelectUser(2, selectCallBackForm, null, $("#roleIds").val());// 选择人员
	});

	// 选择用户
	$("#userSelect").click(function() {
		openOneSelectUser(3, selectUserCallBack, $("#oldUserId").val());// 选择人员
	});

	// 取消关闭按钮
	$("#cancleBtn").click(function() {
		art.dialog.close();
        return false;
	});
	
	getDataTable = art.dialog.data('getDataTable');
	
	//选择人员不能点击回车
    $("#userName").keydown(function(e) {
        if(e.which==8){  
            return false;       
        }
    });
    
    $("#roleNames").keydown(function(e) {
        if(e.which==8){  
            return false;       
        }
    });
    
    $("#sex").keydown(function(e) {
        if(e.which==8){  
            return false;       
        }
    });
    
    $("#groupName").keydown(function(e) {
        if(e.which==8){  
            return false;       
        }
    });
    $("#jod").keydown(function(e) {
        if(e.which==8){  
            return false;       
        }
    });
    $("#phone").keydown(function(e) {
        if(e.which==8){  
            return false;       
        }
    });
    $("#email").keydown(function(e) {
        if(e.which==8){  
            return false;       
        }
    });
});

/**
 * 部门人员选择的回调函数
 */
function selectCallBackForm(data, param) {
	var roleIds = "";
	var roleNames = "";
	if (data) {
		data.forEach(function(value, key) {
			roleIds += value.Id + ",";
			roleNames += value.Name + ",";
		});
		if(roleNames != ""){
			roleNames = roleNames.substring(0,roleNames.lastIndexOf(","));
		}
		$("#roleIds").val(roleIds);
		$("#roleNames").val(roleNames);
	}
}

/**
 * 部门人员选择的回调函数
 */
function selectCallBackForm(data, param) {
	var roleIds = "";
	var roleNames = "";
	var isSeat = 0;
	if (data) {
		data.forEach(function(value, key) {
			getRoleCodeById(value.Id);
			roleIds += value.Id + ",";
			roleNames += value.Name + ",";
//            // 如果选择为坐席时,可以选择是否为离线坐席
//			if (value.Id == '2' || value.Id == '5'){
//				isSeat = 1;
//			}
		});
//		if (isSeat == 1){
//			$("#seatTypeTr").show();
//			// 默认选中第一个
//		}else{
//			$("#seatTypeTr").hide();
//		}
		if(roleNames != ""){
			roleNames = roleNames.substring(0,roleNames.lastIndexOf(","));
		}
		$("#roleIds").val(roleIds);
		$("#roleNames").val(roleNames);
	}

}

/**
 * 更具角色id得到角色code
 * @param roleId
 */
function getRoleCodeById(roleId){
	var paramData = {
			"roleId":roleId
	};
	$.ajax({
		url : basePath+"hotlineuser/getRoleCodeById.action",
        type : "post",
        dataType :'text',
        data:paramData,
        success : function(data) {
        	if(data!=null&&data!=""){
        		var roleCodes = $("#roleCodes").val();
        		$("#roleCodes").val(roleCodes+data+",");
        		showOrhidenType();
        	}
        }
	});
}
/**
 * 显示的坐席类型
 */
function showOrhidenType(){
	var roleCodes = $("#roleCodes").val();
	if (null != roleCodes && "" != roleCodes){
		if (roleCodes.indexOf(",siter,") >= 0 || roleCodes.indexOf(",seatLeader,") >= 0){
			$("#seatTypeTr").show();
		}else{
			$("#seatTypeTr").hide();
		}
	}
}
/**
 * 部门人员选择的回调函数
 */
function selectUserCallBack(data, param) {
	if (data) {
		data.forEach(function(value, key) {
			$("#userId").val(value.Id);
			$("#userName").val(value.Name);
			$.ajax({
			    url : basePath + "/user/ajaxUserById.action",
			    type : "post",
			    dataType : 'json',
			    data : {
				    userId : value.Id
			    },
			    success : function(data) {
				    if (null != data) {
				    	hideError($("#userId"));
				    	
					    // 性别
					    var sex = data["sex"];
					    if ('0' == sex) {
						    $("#sex").val("女");
					    } else {
						    $("#sex").val("男");
					    }

					    // 职务
					    $("#job").val(data["job"]);

					    // 手机
					    $("#phone").val(data["phone"]);

					    // 邮件
					    $("#email").val(data["email"]);
					    
					    // 部门/单位
					    $("#groupName").val(data["groupPath"]);
				    }
			    }

			});

		});
	}
}

function addOrUpdateuser() {
	// 框架校验
	if (!validator(document.getElementById("userInfoFomr"))) {
		return;
	}

	// 登录名
	var loginName = $.trim($("#loginName").val());

	// 密码
	var password = $.trim($("#password").val());
	
	// 确认密码
	var confirmPass = $.trim($("#confirmPass").val());
	
	// 密码和确认密码必须一致
	if (password != confirmPass){
		showObjError($("#confirmPass"), 'user.password_must_be_consistent');
		return;
	}else {
		hideError($("#confirmPass"));
	}
	
	// 角色
	var roleIds = $.trim($("#roleIds").val());
	
	// 登录状态
	var userState = $("input:radio[name='state']:checked").val();
	
	// 选择用户Id
	var userId = $("#userId").val();
	if (null == userId || "" == userId){
		showObjError($("#userId"), 'user.user_not_null');
		return;
	}
	var seatType = $('input:radio[name="seatType"]:checked').val();
	var type = $("#type").val();
	if (null == type || '' == type){
		type = 'add';
	}
	var paramData = {
	    'user.loginName' : loginName,
	    'user.loginPass' : password,
	    'roleIds':roleIds,
	    'user.userId':userId,
	    'user.userState':userState,
	    'oldUserId':$("#oldUserId").val(),
	    'type' : type,
	    'seatType' : seatType
	};
	$.ajax({
	    type : 'post',
	    url : basePath + "hotlineuser/updateLoginUser.action",
	    data : paramData,
	    dataType : 'text',
	    success : function(data) {
		    if (data == "success") {
			    getDataTable();
			    art.dialog.close();
		    } else if (data == 'user.user_is_exist'){
		    	art.dialog.alert(sprintf("user.user_is_exist"));
		    } else if(data =='nolimit'){
		    	art.dialog.alert("添加失败，你没有权限在该部门下添加人员!");
		    } else if (data == 'user.loginName_is_exist'){
		    	art.dialog.alert(sprintf("user.loginName_is_exist"));
		    } else {
		    	if(type == 'add'){
		    		 art.dialog.alert("新增登录用户失败!");
		    	}else{
		    		 art.dialog.alert("修改登录用户失败!");
		    	}
			   
		    }
	    }
	});
}

function openOneSelectUser(showType,callback,defaultSelectId) {
    var url = basePath + "/logined/user/selectuserSign.jsp?showType="+showType+"&defaultSelectId="+defaultSelectId;
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
            height : 437,
            lock : true,
		    opacity: 0.1,
//		    resize:false,
//		    drag:false,
            button:[
                {
                    name:'确定',
                    focus: true,
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