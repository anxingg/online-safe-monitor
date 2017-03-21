$(document).ready(function(){
	setNotifyType(list);
	
	//回车事件
	$(document).keydown(function(event){
		var code=event.which;
		if (code == 13) {
			list();
			return false;
		}
	}); 
	
	$("#search").click(function(){
		list();
		return false;
	});
	
});


/**
 * 初始公告类型
 * 
 * @return
 */
function setNotifyType( callback ) {
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
				$("#notifyType").append("<option value=''>全部</option>");
				for (var i = 0; i < jsonData.length; i++) {
					$("#notifyType").append("<option value='"
							+ jsonData[i].value + "'>" + jsonData[i].name
							+ "</option>");
				}
				
				if(callback != null && callback != undefined){
					callback();
				}
			}
		});
}

/**
 * 获取公告列表
 * 
 * @return
 */
function list() {
	var beginDateStr = $.trim($("#beginDate").val());
	if(beginDateStr != ''){
		beginDateStr += ':00';
	}
	
	var endDateStr = $.trim($("#endDate").val());
	if(endDateStr != ''){
		endDateStr += ':00'
	}
	
	var notifyType = $("#notifyType").val();
	var columnId = $("#columnId").val();
	
	$('#myTable').dataTable({
		"bProcessing" : true,
		'bServerSide' : true,
		"bStateSave" : false, // 状态保存
		"bDestroy" : true,
		'fnServerParams' : function(aoData) {
			aoData.push({
				"name" : "nsr.beginDateStr",
				"value" : beginDateStr
			},{
				"name" : "nsr.endDateStr",
				"value" : endDateStr
			},{
				"name" : "nsr.notifyType",
				"value" : notifyType
			},{
				"name" : "nsr.columnId",
				"value" : columnId
			});
		},
		"sAjaxSource" : basePath + "notify/notify_findStatisticsResultByPage.action",
		"sServerMethod" : "POST",
		"sPaginationType" : "full_numbers",
		"bPaginate" : true, // 翻页功能
		"bLengthChange" : false, // 改变每页显示数据数量
		"bFilter" : false, // 过滤功能
		"bSort" : false, // 排序功能
		"bInfo" : true,// 页脚信息
		"bAutoWidth" : false,// 自动宽度
		"iDisplayLength" : 15, // 每页显示多少行
		"aoColumns" : [{
					"mDataProp" : "no"
				},{
					"mDataProp" : "notifyTypeName"
				},{					
					"mDataProp" : "count",
					"sClass" : "data_r"
				}],
		"oLanguage" : {
			"sUrl" : basePath + "wh/plugins/datatable/cn.txt" // 中文包
		},
		"aoColumnDefs" :[]
	});
}
