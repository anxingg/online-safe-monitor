
var ue;
$(document).ready(function() {
//	ue = UE.getEditor('contentInfo', {
//				initialFrameWidth : "100%"
//	});
	
	initButton();
	getNotifySetInfo();
	setNotifyType();
	//发送提醒
	var s = $("#hidAffair").val();
	if(s!=null&&s!=''){
		var a = s.split("</th>");
		var b = a[1].split("</tr>")[0];
		var c = b.split("<td>");
		var d = c[1].split("</td>")[0];
		$("#sendRemind").html(d);
	}
	$("#showDate").hide();

});
/**
 * 初始化按钮
 * 
 * @return
 */
function initButton() {
	var columnId = $("#columnId").val();
	$.ajax({
		type : 'post',
			url : basePath + "notify/notify_initButtion.action",
			data:{"columnId":columnId},
			dataType : 'html',
			success : function(data) {
				if (data == '1') {
					$("#tjsp").show();
		//			setAuditer();
				} else if (data == '0') {
		//			$("#sms_remind_tr").remove();
					$("#fb").show();
				}
			}
		});
}

//设置审批人
function setAuditer(){
	$.ajax({
		url : basePath + "notify/notify_getAuditer.action",
		type : "post",
		data:{"columnId":$("#columnId").val()},
		dataType : "html",
		success : function(data) {
			jsonData = eval("(" + data + ")");
			$("#auditer").empty();
			var auditerVal="";
			for (var i = 0; i < jsonData.length; i++) {
				auditerVal += "<option value='"
						+ jsonData[i].userId + "'>"
						+ jsonData[i].userName + "</option>";
			}
			$("#auditer").append(auditerVal);
		}
	});
}

/**
 * 初始公告类型
 * 
 * @return
 */
function setNotifyType() {
	var paramData = {
		'infoType' : "notifyType"+$("#columnId").val(),
		'sysTag' : 1
	};
	$.ajax({
				url : basePath + "dict/getDicts.action",
				type : "post",
				dataType : "html",
				data : paramData,
				success : function(data) {
					jsonData = eval("(" + data + ")");
					$("#notifyType").empty();
					$("#notifyType").append("<option value=''>请选择</option>");

					for (var i = 0; i < jsonData.length; i++) {
						$("#notifyType").append("<option value='"
								+ jsonData[i].value + "'>" + jsonData[i].name
								+ "</option>");
					}
				}
			});
}


/**
 * 提交操作
 * 
 * @param status
 * @return
 */
function tijiao(status) {
	// 框架校验
	if (!validator(document.getElementById("form1"))) {
		return;
	}
	var subject = $("#subject").val();
	var limitNum = $("input[name='limit']:checked").val();
	var begDate = "";
	var endDate = "";
	if(limitNum==0){
		begDate = $("#begDate").val();
		if(begDate==null || begDate==""){
			showObjError($("#begDate"), 'notify.notify_begDate_not_null');
			return ;
		}
		if(begDate != ''){
			begDate += ' 00:00:00';
		}
		endDate = $("#endDate").val();
		if(endDate != ''){
			endDate += ' 23:59:59';
		}
	}
	
	var banbuDate = "";//颁布时间
	if($("#columnId").val()==2){
		begDate = "";//实施时间
		
		begDate = $("#beginDate").val();
		
		banbuDate = $("#banbuDate").val();
		if(banbuDate != ''){
			banbuDate += ' 00:00:00';
		}
		if(begDate != ''){
			begDate += ' 00:00:00';
		}
	}
	

	var isTop = document.getElementById("isTop").checked == true?1:0;
	var auditer = $("#auditer").val();
	var summary = $("#summary").val();
	var attment = $("#attachmentId").val();
	if (null == attment || "," == attment || "" == attment) {
		attment = "";
	}
	var images = $("#imageId").val();
	if(images==null||images==","||images==""){
		images = "";
	}
	var publishScopeUserIds = $("#publishScopeUserIds").val();
	var publishScopeUserNames = $("#publishScopeUserNames").val();
//	if (isObjEmpty(publishScopeUserIds)) {
//		showObjError($("#publishScopeUserNames"), 'notify.notify_userName_not_null');
//		return;
//	} else {
//		hideError($("#publishScopeUserNames"));
//	}
	var notifyType = $("#notifyType").val();
	if($.trim(notifyType)==""){
		showObjError($("#notifyType"), 'notify.notify_notifyType_not_null');
		return false;
	}
	var isSmipleText=$("#isSmipleText").val();
	var content
	if(isSmipleText==0){
	   content=ue.getContent();
	   if (null == content || '' == content) {
			showObjError($("#contentInfoInput"), 'notify.notify_content_not_null');
			return;
		}else {
			hideError($("#contentInfoInput"));
		}
	}else{
		content = $("#contentInfo2").val();
		if (null == content || '' == content) {
			showObjError($("#contentInfo2"), 'notify.notify_content_not_null');
			return;
		}else if(content.length > 1000){
			showObjError($("#contentInfo2"), 'notify.notify_content_length');
			return;
		} else {
			hideError($("#contentInfo2"));
		}	
	}
	
	var columnId = $("#columnId").val();
	
	var memo = $("#memo").val();
	
	var banbuGroup = $("#banbuGroup").val();

	dataParam = {
		'notify.subject' : subject,
		'notify.content' : content,
		'notify.summary' : summary,
		'notify.banbuGroup' : banbuGroup,
		'startDateStr':begDate,
		'banbuDateStr':banbuDate,
		'endDateStr' : endDate,
		'notify.notifyType' : notifyType,
		'notify.isTop' : isTop,
		'notify.status':status,
		'notify.auditer':auditer,
		'notify.attment' : attment,
		'notify.publishScopeUserIds':publishScopeUserIds,
		'notify.publishScopeUserNames':publishScopeUserNames,
		'notify.columnId':columnId,
		'notify.images':images,
		'notify.viewCount':0,
		'notify.memo' : memo,
		'notify.sendType':getSendType()
	};
	$.ajax({
				type : 'post',
				url : basePath + "notify/notify_save.action",
				data : dataParam,
				dataType : 'json',
				async : false,
			    beforeSend:function(){
					//$("body").lock();
				},
				complete:function(){
					//$("body").unlock();
				},
				success : function(data) {
					window.location.href=basePath+"logined/notify/list.jsp?id="+$("#columnId").val();
				}
	});
}

/**
 * 判断是否为空
 * 
 * @param obj
 * @return
 */
function isObjEmpty(obj) {
	if (obj == null || obj == "") {
		return true;
	}
	return false;
}

/**
 * 添加按钮
 * 
 * @param obj
 * @return
 */
function selectAuthor(obj) {
	if (obj == 'group') {
		openSelectUser(1, selectAuthorCallBack, null, $("#groupId").val(),'notify');
	} else if (obj == 'role') {
		openSelectUser(2, selectAuthorCallBack, null, $("#roleId").val(),'notify');
	} else if (obj == 'user') {
		openSelectUser(3, selectAuthorCallBack, null, $("#publishScopeUserIds").val(),'notify');
	}
}

/**
 * 添加按钮(回调函数)
 * 
 * @param data
 * @return
 */
function selectAuthorCallBack(data) {
	var ids = '';
	var names = '';
	var type = '';
	data.forEach(function(value, key) {
				ids += value.Id + ',';
				names += value.Name + ',';
				type = value.Type;
			});
	if (type == 'group') {
		$("#groupId").val(ids);
		$("#groupName").val(names);
	}else if(type==''){
		$("#publishScopeUserIds").val("");
		$("#publishScopeUserNames").val("");
	} else if (type == 'user') {
		if(ids!=null && ids!=""){
			$("#publishScopeUserIds").val(ids);
			$("#publishScopeUserNames").val(names);
			hideError($("#publishScopeUserNames"));
		}else{
			$("#publishScopeUserIds").val("");
			$("#publishScopeUserNames").val("");
		}
	} else if (type == 'role') {
		$("#roleId").val(ids);
		$("#roleName").val(names);
	}
}

/**
 * 清空操作
 * 
 * @param obj
 * @return
 */
function clearAuthor(obj) {
	if (obj == 'group') {
		$("#groupId").val('');
		$("#groupName").val('');
	} else if (obj == 'role') {
		$("#roleId").val('');
		$("#roleName").val('');
	} else if (obj == 'user') {
		$("#publishScopeUserIds").val('');
		$("#publishScopeUserNames").val('');
	}
}

/**
 * 上传操作
 */
$(document).ready(function() {
	$("#file_upload").uploadify({
		// 和存放队列的DIV的id一致
		'queueID' : 'queue',
		// 服务器端脚本使用的文件对象的名称 $_FILES个['upload']
		'fileObjName' : 'fileupload',
		// 上传处理程序
		'uploader' : basePath +  'filemanager/uploadfile.action?module=notify',
		// 按钮文字
		'buttonText' : '上传附件...',
		// 附带值
		'formData' : {
			'userid' : '用户id',
			'username' : '用户名',
			'rnd' : '加密密文'
		},
		// 浏览按钮的背景图片路径
		'buttonImage' : basePath + 'flat/images/upload.png',
		// 取消上传文件的按钮图片，就是个叉叉
		'cancel' : basePath + 'plugins/upload/upbutton.png',
		// 浏览按钮的宽度
		'width' : '48',
		// 浏览按钮的高度
		'height' : '15',
		// 在浏览窗口底部的文件类型下拉菜单中显示的文本
		'fileTypeDesc' : '支持的格式:',
		// 允许上传的文件后缀
		'fileTypeExts' : '*.*',
		// 上传文件的大小限制
		'fileSizeLimit' : '10MB',
		// 上传数量
		'queueSizeLimit' : 25,
		// 开启调试
		'debug' : false,
		// 是否自动上传
		'auto' : true,
		// 上传后是否删除
		'removeComplete' : false,
		// 清除
		removeTimeout : 0,

		langFile : basePath + 'plugins/upload/ZH_cn.js',

		// 超时时间
		'successTimeout' : 99999,
		// flash
		'swf' : basePath + 'plugins/upload/uploadify.swf',
		// 不执行默认的onSelect事件
		'overrideEvents' : ['onDialogClose'],
		// 每次更新上载的文件的进展
		'onUploadProgress' : function(file, bytesUploaded, bytesTotal,
				totalBytesUploaded, totalBytesTotal) {
			// 有时候上传进度什么想自己个性化控制，可以利用这个方法
			onUploadProgressHelp(file, bytesUploaded, bytesTotal);
		},
		// 选择上传文件后调用
		'onSelect' : function(file) {
			if(file.name.length > 30 ){
				art.dialog.alert("附件名称过长，上传失败");
				return false;
			}else{
				onSelectHtml(file, "attachmentList", "file_upload");
			}
			return true;
		},
		// 返回一个错误，选择文件的时候触发
		'onSelectError' : function(file, errorCode, errorMsg) {
			switch (errorCode) {
				case -100 :
					art.dialog.alert("上传的文件数量已经超出系统限制的"
							+ $('#file_upload').uploadify('settings',
									'queueSizeLimit') + "个文件！");
					break;
				case -110 :
					art.dialog.alert("文件 ["
							+ file.name
							+ "] 大小超出系统限制的"
							+ $('#file_upload').uploadify('settings',
									'fileSizeLimit') + "大小！");
					break;
				case -120 :
					art.dialog.alert("文件 [" + file.name + "] 大小异常！");
					break;
				case -130 :
					art.dialog.alert("文件 [" + file.name + "] 类型不正确！");
					break;
			}
		},
		// 检测FLASH失败调用
		'onFallback' : function() {
			art.dialog.alert("您未安装FLASH控件，无法上传图片！请安装FLASH控件后再试。");
		},
		// 上传到服务器，服务器返回相应信息到data里
		'onUploadSuccess' : function(file, data, response) {
			if(data == 'filenametoolong'){
				art.dialog.alert("上传文件的名称过长，保存失败！");
			}else{
				data = eval("(" + data + ")");
				var attachmentId = $("#attachmentId").val();
				if (attachmentId) {
					attachmentId = attachmentId + data.id + ",";
				} else {
					attachmentId = "," + data.id + ",";
				}
				$("#attachmentId").val(attachmentId);
				onUploadSuccess(file,null,"file_upload",data.id);
			}
		},
		// 上传前取消文件
		'onCancel' : function(file) {
			// art.dialog.alert('The file ' + file.name + ' was cancelled.');
		}
	});
	/**
	 * 图片上传
	 */
	$("#image_upload").uploadify({
		// 和存放队列的DIV的id一致
		'queueID' : 'queueImage',
		// 服务器端脚本使用的文件对象的名称 $_FILES个['upload']
		'fileObjName' : 'fileupload',
		// 上传处理程序
		'uploader' : basePath + 'filemanager/uploadfile.action?module=notify',
		// 按钮文字
		'buttonText' : '上传图片...',
		// 附带值
		'formData' : {
			'userid' : '用户id',
			'username' : '用户名',
			'rnd' : '加密密文'
		},
		// 浏览按钮的背景图片路径
		'buttonImage' : basePath + 'plugins/upload/uploadfile.png',
		// 取消上传文件的按钮图片，就是个叉叉
		'cancel' : basePath + 'plugins/upload/upbutton.png',
		// 浏览按钮的宽度
		'width' : '100',
		// 浏览按钮的高度
		'height' : '25',
		// 在浏览窗口底部的文件类型下拉菜单中显示的文本
		'fileTypeDesc' : '支持的格式:',
		// 允许上传的文件后缀
		'fileTypeExts' : '*.bmp;*.jpg;*.gif;*.png',
		// 上传文件的大小限制
		'fileSizeLimit' : '10MB',
		// 上传数量
		'queueSizeLimit' : 25,
		// 开启调试
		'debug' : false,
		// 是否自动上传
		'auto' : true,
		// 上传后是否删除
		'removeComplete' : false,
		// 清除
		removeTimeout : 0,

		langFile : basePath + 'plugins/upload/ZH_cn.js',

		// 超时时间
		'successTimeout' : 99999,
		// flash
		'swf' : basePath + 'plugins/upload/uploadify.swf',
		// 不执行默认的onSelect事件
		'overrideEvents' : ['onDialogClose'],
		// 每次更新上载的文件的进展
		'onUploadProgress' : function(file, bytesUploaded, bytesTotal,
				totalBytesUploaded, totalBytesTotal) {
			// 有时候上传进度什么想自己个性化控制，可以利用这个方法
			onUploadProgressHelp(file, bytesUploaded, bytesTotal);
		},
		// 选择上传文件后调用
		'onSelect' : function(file) {
			onSelectHtml(file, "imageAttachmentList", "image_upload");
			return true;
		},
		// 返回一个错误，选择文件的时候触发
		'onSelectError' : function(file, errorCode, errorMsg) {
			switch (errorCode) {
				case -100 :
					art.dialog.alert("上传的文件数量已经超出系统限制的"
							+ $('#file_upload').uploadify('settings',
									'queueSizeLimit') + "个文件！");
					break;
				case -110 :
					art.dialog.alert("文件 ["
							+ file.name
							+ "] 大小超出系统限制的"
							+ $('#file_upload').uploadify('settings',
									'fileSizeLimit') + "大小！");
					break;
				case -120 :
					art.dialog.alert("文件 [" + file.name + "] 大小异常！");
					break;
				case -130 :
					art.dialog.alert("文件 [" + file.name + "] 类型不正确！");
					break;
			}
		},
		// 检测FLASH失败调用
		'onFallback' : function() {
			art.dialog.alert("您未安装FLASH控件，无法上传图片！请安装FLASH控件后再试。");
		},
		// 上传到服务器，服务器返回相应信息到data里
		'onUploadSuccess' : function(file, data, response) {
			var imageId = $("#imageId").val();
			data = eval("(" + data + ")");
			if (imageId) {
				imageId = imageId + data.id + ",";
			} else {
				imageId = "," + data.id + ",";
			}
			$("#imageId").val(imageId);
			onUploadSuccessHelp(file,null,"image_upload",data.id);
		},
		// 上传前取消文件
		'onCancel' : function(file) {
			// art.dialog.alert('The file ' + file.name + ' was cancelled.');
		}
	});
});

function deleteAttach(attachmentId) {
	var attachmentIdAll = $("#attachmentId").val();
	attachmentIdAll = attachmentIdAll.replace("," + attachmentId + ",", ",");
	$("#attachmentId").val(attachmentIdAll);
	
	var imageIdAll = $("#imageId").val();
	imageIdAll = imageIdAll.replace("," + attachmentId + ",", ",");
	$("#imageId").val(imageIdAll);
}

// 上传成功后点击删除按钮
function removeFile(target) {
	$("#" + target).remove();
	art.dialog.alert("取消上传" + target);
}

/**
*加载通知公告设置信息
*/
function getNotifySetInfo(){
	var instentceid = $("#columnId").val();
	$.ajax({
		type : 'post',
		url : basePath + "notify/notify_loadSetInfo.action",
		data:{"columnId":instentceid},
		dataType : 'json',
		success : function(result) {
			$("#isSmipleText").val(result.isSmipleText);
			if(result.isSmipleText==0){
				$("#text1").show();
				ue = UE.getEditor('contentInfo', {
				initialFrameWidth : "100%"
				});
			}else{$("#text2").show();}
		}
	});
}
function goBack(){
	window.location.href=basePath+'logined/notify/list.jsp?id='+$("#columnId").val();
}
function onUploadSuccess(file, filePath, fileUpload, attachId){
	$("#"+file.id+"_i").parent().remove();
	$("#"+file.id+"_font").remove();
	if(attachId!=null&&attachId!=""&&undefined!=attachId){
		// 修改删除选项
		$("#"+file.id+"_delete").attr("href", 'javascript:cancleUpload(\''+file.id+'\', \''+fileUpload+'\',null,\''+attachId+'\')');
	}else{
		// 修改删除选项
		$("#"+file.id+"_delete").attr("href", 'javascript:cancleUpload(\''+file.id+'\', \''+fileUpload+'\',\''+filePath+'\',\''+attachId+'\')');
	}
}
