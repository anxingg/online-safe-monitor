jQuery(document).ready(function(){
	
	//提交表单
	$("#submitButton").click(function(){
			if(validator(document.getElementById("form1"))){
					if($("#introduction").val().length>200){
						showError("introduction","record.introduction_out_of_max_length");
						return false;
					}
					$.post(basePath+"/company/update.action",$("#form1").serialize(),function(data){
							if(data == 'success'){
								art.dialog({
									title : '消息',
									content : '修改成功！',
									icon : 'succeed',
									height : 109,
									width : 317,
									ok:true
								});
							}
					});
			}
		});
		
		//上传附件
	$("#file_upload").uploadify({
        //和存放队列的DIV的id一致
        'queueID':'queue',
        //服务器端脚本使用的文件对象的名称 $_FILES个['upload']
        'fileObjName':'fileupload',
        //上传处理程序
        'uploader':basePath+'filemanager/uploadfile.action',
        //按钮文字
        'buttonText' : '上传附件...',
        //附带值
        'formData':{
            'userid':'用户id',
            'username':'用户名',
            'rnd':'加密密文',
            'module':'LOGO'
        },
        //浏览按钮的背景图片路径
        'buttonImage':basePath+'flat/images/upload.png',
        //取消上传文件的按钮图片，就是个叉叉
        'cancel': basePath+'plugins/upload/upbutton.png',
        //浏览按钮的宽度
        'width':'100',
        //浏览按钮的高度
        'height':'25',
        //在浏览窗口底部的文件类型下拉菜单中显示的文本
        'fileTypeDesc':'支持的格式:',
        //允许上传的文件后缀
        'fileTypeExts':'*.jpg;*.jpge;*.gif;*.png',
        //上传文件的大小限制
        'fileSizeLimit':'200K',
        //上传数量
        'queueSizeLimit' : 25,
        //开启调试
        'debug' : false,
        //是否自动上传
        'auto':true,
        //上传后是否删除
        'removeComplete':false,
        //清除
        removeTimeout : 0,
        langFile:basePath+'plugins/upload/ZH_cn.js',
        //超时时间
        'successTimeout':99999,
        //flash
        'swf': basePath+'plugins/upload/uploadify.swf',
        //不执行默认的onSelect事件
        'overrideEvents' : ['onDialogClose'],
        //每次更新上载的文件的进展
        'onUploadProgress' : function(file, bytesUploaded, bytesTotal, totalBytesUploaded, totalBytesTotal) {
        },
        //选择上传文件后调用
        'onSelect' : function(file) {
            return true;
        },
        //返回一个错误，选择文件的时候触发
        'onSelectError':function(file, errorCode, errorMsg){
        	 switch (errorCode) {
             case -100:
             	art.dialog.alert("上传的文件数量已经超出系统限制的" + $('#file_upload').uploadify('settings', 'queueSizeLimit') + "个文件！");
                 break;
             case -110:
             	art.dialog.alert("文件 [" + file.name + "] 大小超出系统限制的" + $('#file_upload').uploadify('settings', 'fileSizeLimit') + "大小！");
                 break;
             case -120:
             	art.dialog.alert("文件 [" + file.name + "] 大小异常！");
                 break;
             case -130:
             	art.dialog.alert("文件 [" + file.name + "] 类型不正确！");
                 break;
         }
        },
        //检测FLASH失败调用
        'onFallback':function(){
        	art.dialog.alert("您未安装FLASH控件，无法上传图片！请安装FLASH控件后再试。");
        },
        //上传到服务器，服务器返回相应信息到data里
        'onUploadSuccess':function(file, data, response){
        	var attach = eval("("+data+")");
        	$("#logUrl").val(attach.attachFile);
        	prewVeiw();
        },
        //上传前取消文件
        'onCancel' : function(file) {
            // alert('The file ' + file.name + ' was cancelled.');
        }
    });
	
	//预览
	function prewVeiw(){
		var url = $("#logUrl").val();
		if(url){
			$("#view").attr("src",basePath+"filemanager/downview.action?attachPath="+url);
			$("#view").show();
			$("#clean").show();
		}else{
			$("#view").hide();
			$("#clean").hide();
		}
	}
	
	prewVeiw();
		
	$("#clean").click(function(){
		$("#logUrl").val("");
		prewVeiw();
	});
});