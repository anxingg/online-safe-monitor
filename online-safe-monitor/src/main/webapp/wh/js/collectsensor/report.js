$(document).ready(function(){
	
	// 保存按钮绑定事件
	$("#add").bind("click", function() {
		add();
		return false;
	});
	
	$("#add_batch").bind("click", function() {
		batchAdd();
		return false;
	});
	

	//加载列表
	getTable();
	
});



function getTable(){
	var type = $.trim($('#type').val());
	var vid = $.trim($("#vid").val());
	var paramData = {
			'report_type' : type,
			'report_vid' : vid
		};
	qytx.app.ajax({
		url : basePath + "collectsensor/collectsensor_getReport.action",
		type : "post",
		dataType : "text",
		data : paramData,
		success : function(data) {
			var jsonData = eval('(' + data + ')');
			$("#collectSensor_Using").val(data.collectSensor_Using);
		}
	});
}

/**
 * 新增操作
 */
function add() {
	window.location.href = basePath + "";
}
function batchAdd() {
	window.location.href = basePath + "";
}

