// 部门管理页面
$(document).ready(function() {
//	// 重置iframe高度
//	window.parent.frameResize();
	// 初始化区域树
	openCollectDeviceTree(zTreeOnCheckResult);

	$("#page").attr("src", basePath + 
			"wh/logined/devicesensorbind/list.jsp?collectDeviceId=-1");
	
});

function zTreeOnCheckResult(data) {
	var vid = data.getId();
	var type = data.getType();
	var groupId = data.getData();
	if(type=="cid"){//这里进入编辑页面
		$("#page").attr("src", basePath + 
			"wh/logined/devicesensorbind/list.jsp?collectDeviceId="+vid+"&groupId="+groupId);
	}
}
