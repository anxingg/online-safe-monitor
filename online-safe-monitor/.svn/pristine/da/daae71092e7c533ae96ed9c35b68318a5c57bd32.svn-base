    //REN
    $(document).ready(function() {
    	dynamicBinding( $("#functions").get(0) );
        //部门职能长度
//        $("#functions").maxLength(200);
        //单击部门主管添加
        $("#directorSelect").click(function(){
            selectUserDirector();
            return false;
        });
        //单击部门主管移除
        $("#directorRemove").click(function(){
            $("#directorId").val("");
            $("#director").val("");
            return false;
        });
        
        var parentId= $.trim($("#parentId").val());
       
        if(parentId==""||parentId=="0"){
        	 $("#sjpartMent").attr("style","display:none");
        }
        //单击部门助理添加
        $("#assistantSelect").click(function(){
            selectUserAssistant();
            return false;
        });
        //单击部门助理移除
        $("#assistantRemove").click(function(){
            $("#assistantId").val("");
            $("#assistant").val("");
            return false;
        });
        //单击上级主管领导添加
        $("#topDirectorSelect").click(function(){
            selectUserTopDirector();
            return false;
        });
        //单击上级主管领导移除
        $("#topDirectorRemove").click(function(){
            $("#topDirectorId").val("");
            $("#topDirector").val("");
            return false;
        });
        //单击上级分管领导添加
        $("#topChangeSelect").click(function(){
            selectUserTopChange();
            return false;
        });
        //单击上级分管领导移除
        $("#topChangeRemove").click(function(){
            $("#topChangeId").val("");
            $("#topChange").val("");
            return false;
        });
        //单击部门添加
        $("#parentSelect").click(function(){
            selectGroupParent();
            return false;
        });
        //单击部门移除
        $("#parentRemove").click(function(){
            $("#parentId").val("");
            $("#groupSel").val("");
            return false;
        });
        //更新组
        $("#groupUpdate").click(function() {
        	  var parentId= $.trim($("#parentId").val());
              if(parentId==""||parentId==0){
                  $("#groupSel").removeAttr("valid");
              }
            var valid=validator($("#groupForm")[0]);
            if(valid){
               submitForm();
            }
            return false;
        });
        //新建部门
        $("#forwordGroupAdd").click(function(){
             window.location.href=basePath+"logined/group/groupAdd.jsp";
             return false;
        });
        //删除部门
        $("#groupDelete").click(function(){
             art.dialog.confirm("确定删除部门！",function(){
                 groupDelete();
             },function(){
             });
             return false;
        });
    });
    //提交更新表单
    function submitForm(){
    	if($("#functions").html().length > parseInt( $("#functions").attr("fmaxlength"), 10) ){
			showObjError($("#functions"), 'maxLength.answercontent_max_length');
			$("#nextStep").attr("disabled",false);
			return ;
		}else{
			hideError($("#functions"));
		}
        //得到部门id
        var groupId= $.trim($("#groupId").val());
        //部门排序号
        var groupOrder= $.trim($("#groupOrder").val());
        //部门名称
        var groupName= $.trim($("#groupName").val());
        //电话
        var groupPhone= $.trim($("#groupPhone").val());
        //上级部门
        var parentId= $.trim($("#parentId").val());
        if(parentId==""){
            parentId="0";
        }
        if(groupId==parentId){
            art.dialog.alert(sprintf("group.parent_group_not_self_group"));
            return;
        }
        //是否分支机构 
        var branch = $(":radio[name='branch']:checked").val();
        //部门主管
        var directorId= $.trim($("#directorId").val());
        //部门助理
        var assistantId= $.trim($("#assistantId").val());
        //上级主管领导
        var topDirectorId= $.trim($("#topDirectorId").val());
        //上级分管领导
        var topChangeId= $.trim($("#topChangeId").val());
        //部门职能
        var functions= $.trim($("#functions").val());
        //是否分支机构
        var isForkGroup = $("input[name='isForkGroup']:checked").val();
        //改为验证框架
//        if(functions.length>200){
//            artDialog.alert("部门职能字数不能大于200");
//            return;
//        }
        
        var paramData={
            'group.groupId':groupId,
            'group.groupType':1,
            'group.groupState':0,
            'group.orderIndex':groupOrder,
            'group.groupName':groupName,
            'group.phone':groupPhone,
            'group.parentId':parentId,
            'group.branch':branch,
            'group.directorId':directorId,
            'group.assistantId':assistantId,
            'group.topDirectorId':topDirectorId,
            'group.topChangeId':topChangeId,
            'group.isForkGroup':isForkGroup,
            'group.functions':functions
        }; 
        $.ajax({
            url : basePath+"group/updateGroup.action",
            type : "post",
            dataType :'text',
            data:paramData,
            success : function(data) {
                if(data==1)
                {             
                	var mainWindow=window.parent;
                    mainWindow.openSelectTreeUser(mainWindow.zTreeOnCheckResult, null, "gid_"+groupId);
                    art.dialog.tips('更新部门信息成功！');
                  //更新部门is_fork_group字段
//                	var paramDataName = {
//                			"groupName":groupName
//                	};
//                	$.ajax({
//                		url : basePath+"hotlineuser/updateGroupIsforkgroup.action",
//                        type : "post",
//                        dataType :'text',
//                        data:paramDataName,
//                        success : function(data) {
//                        	if(data=="0"){
//                        		art.dialog.tips('更新部门信息成功！');
//                        	}
//                        }
//                	});
                } else if (data==2){
                    art.dialog.alert('部门名称已存在！');
                }else if(data==3){
                	 art.dialog.alert('您没有修改该部门的权限！');
                } else {
                    art.dialog.alert('更新部门信息失败！');
                }
            }});
    }
    //提交删除
    function groupDelete(){
        //是否有子组
        var isHasChild=$("#isHasChild").val();
        if(isHasChild==1){
             art.dialog.alert('含有子部门不能删除！');
             return;
        }
        //组下是否有人
        var isHasGroupUser=$("#isHasGroupUser").val();
        if(isHasGroupUser==1){
             art.dialog.alert('部门包含人员不能删除！');
             return;
        }
        //得到部门id
        var groupId= $.trim($("#groupId").val());
        var paramData={
            'groupId':groupId
        }; 
        $.ajax({
            url : basePath+"group/deleteGroup.action",
            type : "post",
            dataType :'text',
            data:paramData,
            success : function(data) {
                if(data==1)
                {             
                    var timer;
                    art.dialog({
                        content: '删除部门成功！',
                        height : 109,
                        width : 317,
                        init: function () {
                            var mainWindow=window.parent;
                            mainWindow.openSelectTreeUser(mainWindow.zTreeOnCheckResult); 
                            var that = this, i = 2;
                            var fn = function () {
                                //that.title('提示');
                                !i && that.close();
                                i --;
                            };
                            timer = setInterval(fn, 1000);
                        },
                        close: function () {
                            clearInterval(timer);
                            window.location.href=basePath+"logined/group/groupAdd.jsp";
                        }
                    }).show();                   
                } else {
                    art.dialog.alert('删除部门失败！');
                }
            }});
    }
