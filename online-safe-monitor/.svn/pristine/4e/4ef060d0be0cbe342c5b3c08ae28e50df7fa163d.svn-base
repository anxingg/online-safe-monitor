    //提交表单
    function submitForm(){
    	 // 框架校验
    	if(!validator(document.getElementById("groupForm"))){
            return false;
        }
        //部门排序号
        var groupOrder= $.trim($("#groupOrder").val());
        //部门名称
        var groupName= $.trim($("#groupName").val());
        var parentId = $.trim($("#parentId").val());
        var paramData={
            'group.groupType':6,
            'group.groupState':0,
            'group.orderIndex':groupOrder,
            'group.groupName':groupName,
            'group.parentId':parentId
        }; 
        $.ajax({
            url : basePath+"group/addGroup.action",
            type : "post",
            dataType :'text',
            data:paramData,
            success : function(data) {
                if(data==1)
                {   
                    art.dialog.close("addGroup");
                } else if (data==2){
                    art.dialog.alert('部门名称已存在！');
                } else {
                    art.dialog.alert('保存部门信息失败！');
                }
            }});
    }