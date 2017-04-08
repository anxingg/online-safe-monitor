// 部门管理页面
$(document).ready(function() {
//	// 重置iframe高度
//	window.parent.frameResize();
	// 初始化区域树
	openSelectTreeArea(zTreeOnCheckResult);

	$("#page").attr("src", basePath + "logined/company/companyList.jsp");
	
});

var tempGroupId = '';
function zTreeOnCheckResult(data) {
	var groupId = data.Id;
	tempGroupId = groupId;
	if (groupId != 0) {
		$("#page").attr("src", basePath + "logined/company/companyList.jsp?groupId=" + groupId);
	}
}
