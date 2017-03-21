$(document).ready(function() {
	//短信预览
	$("#dataBaseSetSave").click(function() {
		dataBaseSetSave();
		return false;
	});
});


//开始数据库参数的保存操作
function dataBaseSetSave(){
	if(!checkParm()){
		//判断参数的合法化
	 return;
	}
	var hostIp=$("#dbHostIp").val();
	var port=$("#dbPort").val();
	var dbName=$("#dbName").val();
	var uName=$("#dbUsername").val();
	var psw=$("#dbPsw").val();
	var paramData = {
	        'dbset.dbHostIp' : hostIp,  
	        'dbset.dbPort':port,
	        'dbset.dbUsername':uName,
	        'dbset.dbName':dbName,
	        'dbset.dbPsw':psw
	    };
	
	var title ='数据库参数设置';
    var content ='是否保存数据库参数配置？';
    var successContent='保存数据库参数配置成功！';
	
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
                url : basePath + "databaseSet/dataBaseSetSave.action",
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
                            	window.location.href=basePath+'databaseSet/dataBaseSetView.action';
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


function checkParm(){
	var hostIp=$("#dbHostIp").val();
	var port=$("#dbPort").val();
	var dbName=$("#dbName").val();
	var uName=$("#dbUsername").val();
	var psw=$("#dbPsw").val();
	
	if(!checkIsIp(hostIp)){
		artDialog.alert("IP地址为空或者格式错误");
		return false;
	}
	if(!checkIsPortNum(port)){
		artDialog.alert("端口为空或者格式错误");
		return false;
	}
	if(dbName==null||dbName==""){
		artDialog.alert("数据库名不能为空");
		 return false;
	}
	if(uName==null||uName==""){
		artDialog.alert("数据库用户名不能为空");
		 return false;
	}
	if(psw==null||psw==""){
		artDialog.alert("数据库密码不能为空");
		 return false;
	}
	return true;
}

function  checkIsIp(hostIp){
	   var exp=/^(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])$/;
       var reg = hostIp.match(exp);
       if(reg==null)
       {
               return false;
       } 
	return true;
}

function checkIsPortNum(port){
	var re = /^([0-9]|[1-9]\d|[1-9]\d{2}|[1-9]\d{3}|[1-5]\d{4}|6[0-4]\d{3}|65[0-4]\d{2}|655[0-2]\d|6553[0-5])$/;
	if(!re.test(port)){
		return false;
	}
	return true;
}
function bb(){
	
	artDialog.alert("数据库密码不能为空");
	
	}

