$(document).ready(function() {
			initButton();
			getNotifySetInfo();
			setNotifyType();
			initSubjectContent();
			setAffairCheck("公告通知", "sms_remind", "sms_remind_tr");
			setAffairCheck("公告通知", "is_remind", "is_remind_tr");
		})

var ue;
$(document).ready(function() {
			ue = UE.getEditor('contentInfo', {
						initialFrameWidth : "100%"
					});
		});
/**
 * 初始化按钮
 * 
 * @return
 */
function initButton() {
	$.ajax({
				type : 'post',
				url : basePath + "notify/getAddNotifyPri.action",
				dataType : 'json',
				success : function(data) {
					if (data == '1') {
						$("#tjsp").show();
					} else if (data == '0') {
						$("#fb").show();
					}
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
		'infoType' : "notifyType",
		'sysTag' : 1
	};
	$.ajax({
				url : basePath + "infoType/getInfoTypes.action",
				type : "post",
				dataType : "html",
				data : paramData,
				success : function(data) {
					jsonData = eval("(" + data + ")");
					$("#notifyType").empty();
					$("#notifyType").append("<option value='0'>请选择</option>");

					for (var i = 0; i < jsonData.length; i++) {
						$("#notifyType").append("<option value='"
								+ jsonData[i].value + "'>" + jsonData[i].name
								+ "</option>");
					}
				}
			});
}

/**
 * 获取公告设置初始值
 * 
 * @return
 */
function getNotifySetInfo() {
	$.ajax({
				type : 'post',
				url : basePath + "notify/getNotifySetInfo.action",
				dataType : 'json',
				success : function(data) {
					if (data != null && data != '') {
						var topDays = data.topDays;
						$("#topDays").text(topDays);
						$("#top_days").val(topDays);
					}
				}
			});
}

/**
 * 提交审批操作
 * 
 * @return
 */
function tjSP() {
	// 框架校验
	if (!validator(document.getElementById("form1"))) {
		return;
	}
	var subject = $("#subject").val();
	var roleId = $("#roleId").val();
	var userId = $("#userId").val();
	var groupId = $("#groupId").val();
	var begDate = $("#begDate").val();
	var endDate = $("#endDate").val();
	var isTop = document.getElementById("is_top").checked;
	isTop = isTop == true ? 1 : 0;
	var topDays = $("#top_days").val();
	if (topDays <= 0) {
		art.dialog.alert("置顶时间应为大于0的正整数！");
		return false;
	}
	var daytop = $("#topDays").text();
	if (parseInt(topDays) > parseInt(daytop) && isTop == true) {
		art.dialog.alert('超过了最大置顶日期');
		return false;
	}
	if (parseInt(topDays) > parseInt(daytop) && isTop == false) {
		topDays = $("#topDays").text();

	}
	var summary = $("#summary").val();
	var attachmentId = $("#attachmentId").val();
	if (null == attachmentId || "," == attachmentId || "" == attachmentId) {
		attachmentId = "";
	} else {
		attachmentId = attachmentId.substring(1, attachmentId.length - 1);
	}

	var keyword = $("#keyword").val();
	var content = ue.getContent();
	var auditer = $("#auditer").val();
	var notifyType = $("#notifyType").val();
	var smsRemind = document.getElementById("sms_remind").checked;

	if (subject == '请输入公告标题!') {
		showObjError($("#subject"), 'notify.notify_subject_not_null');
		return;
	} else {
		hideError($("#subject"));
	}
	var subjectColor = $("#subjectColor").val();
	if ((isObjEmpty(roleId) && isObjEmpty(userId) && isObjEmpty(groupId))) {
		showObjError($("#groupName"), 'notify.notify_groupName_not_null');
		return;
	} else {
		hideError($("#groupName"));
	}
	if ((begDate != "" && begDate != null)
			&& (endDate != "" && endDate != null)) {
		if (begDate >= endDate) {
			showObjError($("#endDate"), 'notify.notify_less_than_begDate');
			return;
		} else {
			hideError($("#endDate"));
		}
	}
	if (null == content || '' == content) {
		showObjError($("#contentInfoInput"), 'notify.notify_content_not_null');
		return;
	} else {
		hideError($("#contentInfoInput"));
	}

	$.ajax({
				url : basePath + "notify/getAuditer.action",
				type : "post",
				dataType : "html",
				success : function(data) {
					jsonData = eval("(" + data + ")");
					$("#auditer").empty();
					$("#auditer").append("<option value='0'>请选择</option>");
					for (var i = 0; i < jsonData.length; i++) {
						$("#auditer").append("<option value='"
								+ jsonData[i].userId + "'>"
								+ jsonData[i].userName + "</option>");
					}
				}
			});
	var titleName = "提交审批";
	var contentName = "<table width='100%' border='0' cellpadding='0' cellspacing='1'  class='inputTable'><tr><th><label>审批人：</label></th><td><select id='auditer' style='width:100px'></select></td></tr><tr id='is_remind_tr'><th><label>提醒审批人：</label></th><td><input type='checkbox' id='is_remind'> 发送事务提醒消息</td></tr></table>";
	var dialog = art.dialog({
				title : titleName,
				content : contentName,
				 width : 800,
				 height : 450,
				ok : function() {
					var auditer = $.trim($("#auditer").val());

					if (auditer == '0') {
						art.dialog.alert('请选择审批人！');
						return false;
					}
					$("#auditer").val(auditer);
					var isRemindAuditer = document.getElementById("is_remind").checked;
					dataParam = {
						'notifyVo.subject' : subject,
						'notifyVo.roleId' : roleId,
						'notifyVo.userId' : userId,
						'notifyVo.groupId' : groupId,
						'notifyVo.beginDateStr' : begDate,
						'notifyVo.endDateStr' : endDate,
						'notifyVo.notifyType' : notifyType,
						'notifyVo.topDays' : topDays,
						'notifyVo.isTop' : isTop,
						'notifyVo.summary' : summary,
						'notifyVo.attment' : attachmentId,
						'notifyVo.content' : content,
						'notifyVo.auditer' : auditer,
						'notifyVo.keyword' : keyword,
						'notifyVo.status' : 0,
						'notifyVo.isRemind' : smsRemind,
						'notifyVo.isRemindAuditer' : isRemindAuditer,
						'notifyVo.subjectColor' : subjectColor,
						'daytop' : daytop
					};
					$.ajax({
								type : 'post',
								url : basePath + "notify/addNotify.action",
								data : dataParam,
								dataType : 'json',
								async : false,
								success : function(data) {
									art.dialog
											.alert(sprintf('notify.notify_auditer'));
									dialog.close();
								}
							});
				},
				okVal : '提交',
				cancelVal : '关闭',
				cancel : true
			});
}

/**
 * 发布操作
 * 
 * @return
 */
function fabu() {
	tijiao(1);// 五月8好下午修改
	// tijiao(5);
}

/**
 * 保存操作
 * 
 * @return
 */
function bc() {
	tijiao(4);
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
	var roleId = $("#roleId").val();
	var userId = $("#userId").val();
	var groupId = $("#groupId").val();
	var begDate = $("#begDate").val();
	var endDate = $("#endDate").val();
	var isTop = document.getElementById("is_top").checked;
	isTop = isTop == true ? 1 : 0;
	var topDays = $("#top_days").val();
	if (topDays <= 0) {
		art.dialog.alert("置顶时间应为大于0的正整数！");
		return false;
	}
	var daytop = $("#topDays").text();
	if (parseInt(topDays) > parseInt(daytop) && isTop == true) {
		art.dialog.alert('超过了最大置顶日期');
		return false;
	}
	if (parseInt(topDays) > parseInt(daytop) && isTop == false) {
		topDays = $("#topDays").text();

	}
	var summary = $("#summary").val();
	var attachmentId = $("#attachmentId").val();
	if (null == attachmentId || "," == attachmentId || "" == attachmentId) {
		attachmentId = "";
	} else {
		attachmentId = attachmentId.substring(1, attachmentId.length - 1);
	}
	var keyword = $("#keyword").val();
	var content = ue.getContent();
	var auditer = $("#auditer").val();
	var notifyType = $("#notifyType").val();
	var smsRemind = document.getElementById("sms_remind").checked;
	var subjectColor = $("#subjectColor").val();

	if (subject == '请输入公告标题!') {
		showObjError($("#subject"), 'notify.notify_subject_not_null');
		return;
	} else {
		hideError($("#subject"));
	}
	if ((isObjEmpty(roleId) && isObjEmpty(userId) && isObjEmpty(groupId))) {
		showObjError($("#groupName"), 'notify.notify_groupName_not_null');
		return;
	} else {
		hideError($("#groupName"));
	}
	if ((begDate != "" && begDate != null)
			&& (endDate != "" && endDate != null)) {
		if (begDate >= endDate) {
			showObjError($("#endDate"), 'notify.notify_less_than_begDate');
			return;
		} else {
			hideError($("#endDate"));
		}
	}
	if (null == content || '' == content) {
		showObjError($("#contentInfoInput"), 'notify.notify_content_not_null');
		return;
	} else {
		hideError($("#contentInfoInput"));
	}
	dataParam = {
		'notifyVo.subject' : subject,
		'notifyVo.roleId' : roleId,
		'notifyVo.userId' : userId,
		'notifyVo.groupId' : groupId,
		'notifyVo.beginDateStr' : begDate,
		'notifyVo.endDateStr' : endDate,
		'notifyVo.notifyType' : notifyType,
		'notifyVo.topDays' : topDays,
		'notifyVo.isTop' : isTop,
		'notifyVo.summary' : summary,
		'notifyVo.attment' : attachmentId,
		'notifyVo.content' : content,
		'notifyVo.keyword' : keyword,
		'notifyVo.status' : status,
		'notifyVo.isRemind' : smsRemind,
		'notifyVo.subjectColor' : subjectColor
	};
	$.ajax({
				type : 'post',
				url : basePath + "notify/addNotify.action",
				data : dataParam,
				dataType : 'json',
				async : false,
				success : function(data) {

					if (data == '1') {
						if (status == '1') {
							art.dialog({
								   title:"消息",
								   content:sprintf('notify.notify_fabu'),
								   width : 317,
								   height : 109,
								   icon:"succeed",
								   opacity:0.3,
								   lock:true,
								   ok:function(){},
								   close:function(){
									
								   }
								});
						}
						if (status == '4') {
							art.dialog({
								   title:"消息",
								   content:sprintf('notify.notify_baocun'),
								   width : 317,
								   height : 109,
								   icon:"succeed",
								   opacity:0.3,
								   lock:true,
								   ok:function(){},
								   close:function(){
									
								   }
								});
						}
					}

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
 * 人员、角色显示隐藏控制
 * 
 * @return
 */
function authorDivOption() {
	var userDiv = $("#userDiv").css("display");
	if (userDiv == 'none') {
		$("#userDiv").show();
		$("#roleDiv").show();
	} else {
		$("#userDiv").hide();
		$("#roleDiv").hide();
	}
}

/**
 * 添加按钮
 * 
 * @param obj
 * @return
 */
function selectAuthor(obj) {
	if (obj == 'group') {
		openSelectUser(1, selectAuthorCallBack, null, $("#groupId").val(),
				'notify');
	} else if (obj == 'role') {
		openSelectUser(2, selectAuthorCallBack, null, $("#roleId").val(),
				'notify');
	} else if (obj == 'user') {
		openSelectUser(3, selectAuthorCallBack, null, $("#userId").val(),
				'notify');
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
	} else if (type == 'user') {
		$("#userId").val(ids);
		$("#userName").val(names);
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
		$("#userId").val('');
		$("#userName").val('');
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
		'uploader' : basePath + 'notify/uploadAttacheFile.action',
		// 按钮文字
		'buttonText' : '上传附件...',
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
		},
		// 选择上传文件后调用
		'onSelect' : function(file) {
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
			var domData = eval('(' + data + ')');
			var attachmentId = $("#attachmentId").val();
			var attachmentName = domData.fileName;
			var url = domData.url;
			var fileSize = domData.fileSize;
			if (attachmentId != '' && attachmentId != null) {
				attachmentId = attachmentId + domData.attachmentId + ",";
			} else {
				attachmentId = "," + domData.attachmentId + ",";
			}
			$("#attachmentId").val(attachmentId);

			// 上传成功后，删除页面提示信息
			$("#attachmentView").show();
			var viewStr = createOneAttachmentHTML(file.name,
					domData.attachmentId);
			// var viewStr = '&nbsp;&nbsp;<a href="#" id="'+url+'"
			// name="'+attachmentName+'">'+attachmentName+'('+fileSize+')</a>';
			var attachmentList = $("#attachmentList").html();
			attachmentList += viewStr;
			$("#attachmentList").html(attachmentList);
		},
		// 上传前取消文件
		'onCancel' : function(file) {
			alert('The file ' + file.name + ' was cancelled.');
		}
	});
});

/**
 * 生成一个附件的HTML内容供给显示
 * 
 * @param fileName
 *            文件名称
 * @param filePath
 *            文件路径
 */
function createOneAttachmentHTML(fileName, attachmentId) {
	var html = "<p><span class='filesIco'>"
			+ fileName
			+ "<a class='xxx' href='javascript:void(0)' onclick='deleteAttachment(\""
			+ attachmentId + "\",this)'></a>" + "</span></p>";
	return html;
}

function deleteAttachment(attachmentId, domAObj) {
	$(domAObj).parent().parent().remove();
	var attachmentIdAll = $("#attachmentId").val();
	attachmentIdAll = attachmentIdAll.replace("," + attachmentId + ",", ",");
	$("#attachmentId").val(attachmentIdAll);
}

// 上传成功后点击删除按钮
function removeFile(target) {
	$("#" + target).remove();
	alert("取消上传" + target);
}

/**
 * 设置标题颜色
 */
function setSubjectColor(val) {
	var subjectColor = val;
	$("#subjectColor").val(val);
	$("#subject").css('color', subjectColor);
}

/**
 * 验证数字格式
 * 
 * @param obj
 * @return
 */
function validateNum(obj) {
	var val = obj.value;
	obj.value = val.replace(/[^\d]/g, '');
	if (val > 365) {
		showObjError($("#top_days"), 'notify.notify_topDays_scope');
		return;
	} else {
		hideError($("#top_days"));
	}
}

/**
 * 清空文本
 * 
 * @param obj
 * @return
 */
function clearSubjectContent(obj) {
	var val = obj.value;
	if (val == '请输入公告标题!') {
		$(obj).val('');
		$(obj).removeClass("gray_9");
	}
}

/**
 * 添加文本
 * 
 * @param obj
 * @return
 */
function addSubjectContent(obj) {
	var val = obj.value;
	if (val == '' || val == null) {
		$(obj).val('请输入公告标题!');
		$(obj).addClass("gray_9");
	}
}

/**
 * 初始化公告标题内容
 * 
 * @return
 */
function initSubjectContent() {
	$("#subject").val('');
	$("#subject").val('请输入公告标题!');
	$("#subject").addClass("gray_9");
}