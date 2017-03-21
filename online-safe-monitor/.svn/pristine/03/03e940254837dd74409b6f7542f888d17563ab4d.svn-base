$(document).ready(function() {
	//短信预览
	$("#systemBasisSetSave").click(function() {
		dataBaseSetSave();
		return false;
	});
});

//开始数据库参数的保存操作
function dataBaseSetSave(){
	if(!checkSysParm()){
		//判断参数的合法化
	 return;
	}
	var receptionName=$("#receptionName").val();
	var backstageName=$("#backstageName").val();
	var organizationsRootName=$("#organizationsRootName").val();
	var copyrightInformation=$("#copyrightInformation").val();
	var servicePhoneNumber = $("#servicePhoneNumber").val();
	var telephoneHotline = $("#telephoneHotline").val();
	var unitInformation=$("#unitInformation").val();
	var isSubUnits=0;
	if(document.getElementById("isSubUnits").checked){
		 isSubUnits=1;
	}
	
	var personalInformation=$("#personalInformation").val();
	var paramData = {
	        'sysSet.receptionName' : receptionName,  
	        'sysSet.backstageName':backstageName,
	        'sysSet.organizationsRootName':organizationsRootName,
	        'sysSet.copyrightInformation':copyrightInformation,
	        'sysSet.personalInformation':personalInformation,
	        'sysSet.unitInformation':unitInformation,
	        'sysSet.isSubUnits':isSubUnits,
	        'sysSet.servicePhoneNumber':servicePhoneNumber,
	        'sysSet.telephoneHotline':telephoneHotline
	    };
	
	var title ='系统基础设置';
    var content ='是否保存系统基础设置参数配置？';
    var successContent='保存系统基础设置参数配置成功！';
	
    art.dialog({
        lock : true,
        background : '#000',
        opacity : 0.1,
        title : title,
        content : content,
        icon : 'question',
        ok : function() {
            $.ajax({
                type : 'post',                
                url : basePath + "systemBasisSet/systemBasisSetSave.action",
                data : paramData,
                dataType : 'text',
                success : function(data) {
                	if (data >= 1) {
                        art.dialog({
                            lock : true,
                            background : '#000',
                            opacity : 0.1,
                            title : '提示',
                            content : successContent,
                            icon : 'succeed',
                            ok : function() {
                            },
                            close : function() {
                            	window.location.href=basePath+'systemBasisSet/systemBasisSetView.action';
                            }
                        });
                    } else {
                        art.dialog.alert("操作失败！");
                    }
                }
            });
        }
    });
	
	
}

function checkSysParm(){
	var receptionName=$("#receptionName").val();
	var backstageName=$("#backstageName").val();
	var organizationsRootName=$("#organizationsRootName").val();
	var copyrightInformation=$("#copyrightInformation").val();
	var unitInformation=$("#unitInformation").val();
	var isSubUnits=$("#isSubUnits").val();
	var personalInformation=$("#personalInformation").val();
	
	if(receptionName==null||receptionName==""){
		artDialog.alert("前台名称不能为空！");
		return false;
	}
	
	if(backstageName==null||backstageName==""){
		artDialog.alert("后台名称不能为空！");
		return false;
	}
	if(organizationsRootName==null||organizationsRootName==""){
		artDialog.alert("组织结构根目录不能为空！");
		return false;
	}
	if(copyrightInformation==null||copyrightInformation==""){
		artDialog.alert("版权信息不能为空！");
		return false;
	}
	if(unitInformation==null||unitInformation==""){
		artDialog.alert("单位信息字段不能为空！");
		return false;
	}
	if(personalInformation==null||personalInformation==""){
		artDialog.alert("个人信息字段不能为空！");
		return false;
	}
	if(isSubUnits==null||isSubUnits==""){
		artDialog.alert("请选择是否支持二级单位！");
		return false;
	}
	return true;
}

