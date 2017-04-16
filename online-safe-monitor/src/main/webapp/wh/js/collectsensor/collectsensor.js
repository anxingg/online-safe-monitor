// 部门管理页面
$(document).ready(function() {
//	// 重置iframe高度
//	window.parent.frameResize();
	// 初始化区域树
	openCollectSensorTree(zTreeOnCheckResult);

	$("#page").attr("src", basePath + "wh/logined/collectsensor/report.jsp?type=all&vid=-1");
	
});

function zTreeOnCheckResult(data) {
	var vid = data.getId();
	var type = data.getType();
	if(type=="did"){//这里进入编辑页面
		$("#page").attr("src", basePath + 
				"wh/logined/collectsensor/list.jsp?dangerSourceId="+vid);
	}
	else{
		$("#page").attr("src", basePath + 
				"wh/logined/collectsensor/report.jsp?type="+type+"&vid="+vid);
	}
}
