var ue;
$(document).ready(function() {
	
	initButton();
	getNotifySetInfo();
	setNotifyType();
	var fileupload = new qytx.app.fileupload({
		id:"file_upload",
		hiddenID:"attachmentId",
		queueID:"queue",
		ulName:"attachmentList",
		fileSizeLimit:"20MB",
		deleteFun:function(attachId,filePath){
			var attachmentIdAll = $("#attachmentId").val();
			attachmentIdAll = attachmentIdAll.replace("," + attachId + ",", ",");
			$("#attachmentId").val(attachmentIdAll);
		}
	});
	//发送提醒
	var s = $("#hidAffair").val();
	var a = s.split("</th>");
	var b = a[1].split("</tr>")[0];
	var c = b.split("<td>");
	var d = c[1].split("</td>")[0];
	$("#sendRemind").html(d);
	
	$("#showDate").hide();

})
/**
 * 初始化按钮
 * 
 * @return
 */
function initButton() {
	var columnId = $("#columnId").val();
	qytx.app.ajax({
		type : 'post',
			url : basePath + "notify/notify_initButtion.action",
			data:{"columnId":columnId},
			dataType : 'html',
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
		'infoType' : "notifyType"+$("#columnId").val(),
		'sysTag' : 1
	};
	qytx.app.ajax({
				url : basePath + "dict/getDicts.action",
				type : "post",
				dataType : "html",
				data : paramData,
				success : function(data) {
					jsonData = eval("(" + data + ")");
					$("#notifyType").empty();
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
	if (!qytx.app.valid.check({"form":document.getElementById("form1")})) {
		return;
	}
	var subject = $("#subject").val();
	var limitNum = $("input[name='limit']:checked").val();
	var begDate = "";
	var endDate = "";
	if(limitNum==0){
		begDate = $("#begDate").val();
		if(begDate==null || begDate==""){
			qytx.app.valid.showObjError($("#begDate"), 'notify.notify_begDate_not_null');
			return ;
		}
		endDate = $("#endDate").val();
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
	if (isObjEmpty(publishScopeUserIds)) {
		qytx.app.valid.showObjError($("#publishScopeUserNames"), 'notify.notify_userName_not_null');
		return;
	} else {
		qytx.app.valid.hideError($("#publishScopeUserNames"));
	}
	var notifyType = $("#notifyType").val();
	if($.trim(notifyType)==""){
		qytx.app.valid.showObjError($("#notifyType"), 'notify.notify_notifyType_not_null');
		return false;
	}
	var isSmipleText=$("#isSmipleText").val();
	var content
	if(isSmipleText==0){
	   content=ue.getContent();
	   if (null == content || '' == content) {
		   qytx.app.valid.showObjError($("#contentInfoInput"), 'notify.notify_content_not_null');
			return;
		}else {
			qytx.app.valid.hideError($("#contentInfoInput"));
		}
	}else{
		content = $("#contentInfo2").val();
		if (null == content || '' == content) {
			qytx.app.valid.showObjError($("#contentInfo2"), 'notify.notify_content_not_null');
			return;
		}else if(content.length > 1000){
			qytx.app.valid.showObjError($("#contentInfo2"), 'notify.notify_content_length');
			return;
		} else {
			qytx.app.valid.hideError($("#contentInfo2"));
		}	
	}
	
	var columnId = $("#columnId").val();

	dataParam = {
		'notify.subject' : subject,
		'notify.content' : content,
		'notify.summary' : summary,
		'startDateStr':begDate,
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
		'notify.sendType':getSendType()
	};
	qytx.app.ajax({
				type : 'post',
				url : basePath + "notify/notify_save.action",
				data : dataParam,
				dataType : 'json',
				async : false,
				shade:true,
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
	openSelectUser(3, selectAuthorCallBack, null, $("#publishScopeUserIds").val());
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
	$(data).each(function(i,item){
			ids += item.id + ',';
			names += item.name + ',';
	});
	$("#publishScopeUserIds").val(ids);
	$("#publishScopeUserNames").val(names);
	qytx.app.valid.hideError($("#publishScopeUserNames"));
}

/**
*加载通知公告设置信息
*/
function getNotifySetInfo(){
	var instentceid = $("#columnId").val();
	qytx.app.ajax({
		type : 'post',
		url : basePath + "/notify/notify_loadSetInfo.action",
		data:{"columnId":instentceid},
		dataType : 'json',
		success : function(result) {
			$("#isSmipleText").val(result.isSmipleText);
			if(result.isSmipleText==0){
				$("#text1").show();
				ue = UE.getEditor('contentInfo', {
				initialFrameWidth : "100%",
				elementPathEnabled :false
				});
			}else{$("#text2").show();}
			}
	});
}
function goBack(){
	window.location.href=basePath+'/logined/notify/list.jsp?id='+$("#columnId").val();
}
/**
 * 清空操作
 * 
 * @param obj
 * @return
 */
function clearAuthor(obj) {
	$("#publishScopeUserIds").val('');
	$("#publishScopeUserNames").val('');
}