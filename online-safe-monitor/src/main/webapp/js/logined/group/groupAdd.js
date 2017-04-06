    //REN
    $(document).ready(function() {
    	dynamicBinding( $("#functions").get(0) );
        /*//部门职能长度
        $("#functions").maxLength(200);*/
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
        //添加组
        $("#groupAdd").click(function() {
            var valid=validator($("#groupForm")[0]);
            
            if(valid){
               submitForm();
            }
            return false;
        });
    });
    //提交表单
    function submitForm(){
    	if($("#functions").html().length > parseInt( $("#functions").attr("fmaxlength"), 10) ){
			showObjError($("#functions"), 'maxLength.answercontent_max_length');
			$("#nextStep").attr("disabled",false);
			return ;
		}else{
			hideError($("#functions"));
		}
    	
        //部门排序号
        var groupOrder= $.trim($("#groupOrder").val());
        //部门名称
        var groupName= $.trim($("#groupName").val());
        //电话
        var groupPhone= $.trim($("#groupPhone").val());
        //上级部门
        var parentId= $.trim($("#parentId").val());
        if(parentId == ""){
           // parentId = '1';
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
        //是否是分支机构
        var isForkGroup = $("input[name='isForkGroup']:checked").val();
        //机构类型
        var groupType = $("input[name='groupType']:checked").val();
//        if(functions.length>200){
//            artDialog.alert("部门职能字数不能大于200");
//            return;
//        }
        var paramData={
            'group.groupType':groupType,
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
            'group.functions':functions,
            'group.forkGroupFlag':1
        }; 
        $.ajax({
            url : basePath+"group/addGroup.action",
            type : "post",
            dataType :'text',
            data:paramData,
            success : function(data) {
                if(data==1)
                {            
                	var mainWindow=window.parent;
                	mainWindow.openSelectTreeOrganize(mainWindow.zTreeOnCheckResult);
                	art.dialog.tips('保存机构信息成功！');
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
//                        		art.dialog.tips('保存部门信息成功！');
//                        	}
//                        }
//                	});
                    
                }else if (data==2){
                    art.dialog.tips('机构名称已存在！');
                }else if(data == 3){
                	 art.dialog.tips('您没有权限在此机构下添加子机构！');
                } else {
                    art.dialog.alert('保存机构信息失败！');
                }
            }});
    }