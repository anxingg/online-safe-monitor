$(document).ready(function() {
	//短信预览
	$("#knowledgeFileSetSave").click(function() {
		knowledgeFileSetSave();
		return false;
	});
});


//开始数据库参数的保存操作
function knowledgeFileSetSave(){
	if(!checkParm()){
		//判断参数的合法化
	 return;
	}
	var lunceneRoot=$("#lunceneRoot").val();
	var lunceneInitFile=$("#lunceneInitFile").val();
	var knowledgeUpload = $("#knowledgeUpload").val();
	var paramData = {
	        'kfs.lunceneRoot' : lunceneRoot,  
	        'kfs.lunceneInitFile':lunceneInitFile,
	        'kfs.knowledgeUpload':knowledgeUpload
	    };
	
	var title ='知识库文件参数设置';
    var content ='是否保存知识库文件参数配置？';
    var successContent='保存知识库文件参数配置成功！';
	
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
                url : basePath + "knowledgeSet/knowledgeFileSetSave.action",
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
                            	window.location.href=basePath+'knowledgeSet/knowledgeFileSetView.action';
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
	var lunceneRoot=$.trim($("#lunceneRoot").val());
	var lunceneInitFile=$.trim($("#lunceneInitFile").val());
	var knowledgeUpload = $.trim($("#knowledgeUpload").val());
	
	if(!checkIsNull(lunceneRoot)){
		artDialog.alert("luncene根目录为空");
		return false;
	}
	if(!checkIsNull(lunceneInitFile)){
		artDialog.alert("初始化地址为空");
		return false;
	}
	if(!checkIsNull(knowledgeUpload)){
		artDialog.alert("上传路径为空");
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

function checkIsNull(content){
	if(content==null||content==""){
		return false;
	}
	return true;
}

