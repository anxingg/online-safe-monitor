$(document).ready(function(){
	initGroup("areaId",0,-1);
	//初始化下拉框
	initSelect("deviceModel","deviceModel",true);
	initSelect("deviceStatus","deviceStatus",true);
	initSelect("deviceType","deviceType",true);
	if($('#action').val()=='add'){
		$('#title').html('<em>新增</em>');
	}
	else{
		$('#title').html('<em>修改</em>');
		initInfo();
	}
	//区域下拉框改变
	$("#areaId").change(function(){
		var areaId = $.trim($("#areaId").val());
		if(areaId!=-1){
			initGroup("groupId",0,areaId);
		}
	});
	//保存新增
	$("#submit").click(function(){
		// 框架校验
		if (!validator(document.getElementById("form1"))) {
			return;
		}
		submit();
	});
	
});

/**
 * 设置下拉框选中某数值
 */
function setSelect(id,value)
{
	$("#"+id+" option[value='"+value+"']").attr("selected", true);
}
/**
 * 初始化表单数据
 * @return
 */
function initInfo() {
	var vid = $('#vid').val();
	console.log("vid:"+vid);
	var paramData = {
		'vid' : vid
	};
	qytx.app.ajax({
		url : basePath + "collectdevice/collectdevice_getInfo.action",
		type : "post",
		dataType : "json",
		data : paramData,
		success : function(data) {
			if (data != null) {
				var collectdevice = eval(data);
				setSelect('areaId',collectdevice.areaId);
				initGroup("groupId",0,collectdevice.areaId);
				setSelect('groupId',collectdevice.company.groupId);
				$("#installPosition").val(collectdevice.installPosition);
				setSelect('deviceModel',collectdevice.deviceModel);
				$("#channelCount").val(collectdevice.channelCount);
				$("#deviceAddress").val(collectdevice.deviceAddress);
				setSelect('deviceStatus',collectdevice.deviceStatus);
				setSelect('deviceType',collectdevice.deviceType);
				$("#installPerson").val(collectdevice.installPerson);
				var installDate = collectdevice.installDate.format("yyyy-MM-dd");    
				$("#installDate").val(installDate);
				$("#memo").val(memo);
			} else {
				artDialog.alert("加载信息失败！");
			}
		}
	});
}

/**
 * 初始化数据字段下拉框
 * @return
 */
function initSelect(typeName,id,isAddTipOption) {
	var paramData = {
		'infoType' : typeName,
		'grade' : 0
	};
	qytx.app.ajax({
		url : basePath + "dict/setup_getDictsByParentId.action",
		type : "post",
		dataType : "text",
		data : paramData,
		success : function(data) {
			//data是一个数组
			var jsonData = eval('(' + data + ')');
			$("#"+id).empty();
			if(isAddTipOption){
				$("#"+id).append("<option value='-1'>请选择</option>");
			}
			for (var i = 0; i < jsonData.length; i++) {
				$("#"+id).append("<option value='"
						+ jsonData[i].value + "'>" + jsonData[i].name
						+ "</option>");
			}
		}
	});
}

/**
 * 初始化区域/群组下拉框
 * @return
 */
function initGroup(id,groupType,parentId) {
	var paramData = {
		'groupType' : groupType,
		'parentId' : parentId
	};
	qytx.app.ajax({
		url : basePath + "companywh/getGroupSelect.action",
		type : "post",
		dataType : "text",
		data : paramData,
		success : function(data) {
			//data是一个数组
			var jsonData = eval('(' + data + ')');
			$("#"+id).empty();
			if(isAddTipOption){
				$("#"+id).append("<option value='-1'>请选择</option>");
			}
			for (var i = 0; i < jsonData.length; i++) {
				$("#"+id).append("<option value='"
						+ jsonData[i].value + "'>" + jsonData[i].name
						+ "</option>");
			}
		}
	});
}

/**
 * 保存
 */
function submit(){
	var vid = $('#vid').val();
	var groupId = $.trim($("#groupId").val());
	if(!group){
		artDialog.alert("请选择单位");
	}
	var parmas = {
		"collectDeviceId" : vid,
		"groupId" : groupId,
		"collectDevice.installPosition" : $.trim($("#installPosition").val()),
		"collectDevice.deviceModel" : $.trim($("#deviceModel").val()),
		"collectDevice.channelCount" : $.trim($("#channelCount").val()),
		"collectDevice.deviceAddress" : $.trim($("#deviceAddress").val()),
		"collectDevice.deviceType" : $.trim($("#deviceType").val()),
		"collectDevice.installPerson" : $.trim($("#installPerson").val()),
		"installDate" : $.trim($("#installDate").val()),
		"collectDevice.memo" : $.trim($("#memo").val())
	};
	
	$(".formButton_green").attr("disabled", "disabled");
	$.ajax({
		url : basePath + "collectdevice/collectdevice_save.action",
		type : "post",
		data : parmas,
		success : function(data){
			if (data == 1) {
				//artDialog.alert("新增成功！");
				window.location.href = basePath+"wh/logined/collectdevice/list.jsp";
			}else{
				artDialog.alert("提交失败！");
				$(".formButton_green").removeAttr("disabled");
			}
		}
	});
}