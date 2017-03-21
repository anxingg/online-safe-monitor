/**
 * 坐席工作量统计页面的js 李立泼 2014年2月12日
 */
$(document).ready(function() {
	// alert("seatTaskStatistics.js ...");
	//initQueue();
	$("#beginTime").val(getStratTime(1));
	$("#endTime").val(CurentTime());
	
	var fromPage = $("#fromPage").val();
    if(fromPage!='seat'){
		getDataDayTables();
	}else{
		getSeatDataTables();
	}
	
	//移到初始化队列方法中initQueue()
//	getDataDayTables();

	// 查询按钮
	$("#search").click(function() {
		var granularity = $('#granularity').val();
		// 清除cookie中的分页信息
		// $.removeTableCookie('SpryMedia_DataTables_myTable_seatTaskStatistics.jsp');
		var fromPage = $("#fromPage").val();
		if ('1' == granularity) {
			if(fromPage!='seat'){
				getDataDayTables();
			}else{
				getSeatDataTables();
			}
		} else {
			if(fromPage!='seat'){
				getDataHouseTables();
			}else{
				getSeatDataTables();
			}
		}
	});

	// 回车事件
	document.onkeydown = function(e) {
		var granularity = $('#granularity').val();
		// 兼容FF和IE和Opera
		var theEvent = e || window.event;
		var code = theEvent.keyCode || theEvent.which || theEvent.charCode;
		if (code == 13) {
			// 清除cookie中的分页信息
			// $.removeTableCookie('SpryMedia_DataTables_myTable_seatTaskStatistics.jsp');
			var fromPage = $("#fromPage").val();
			if ('1' == granularity) {
				if(fromPage!='seat'){
					getDataDayTables();
				}else{
					getSeatDataTables();
				}
			} else {
				if(fromPage!='seat'){
					getDataHouseTables();
				}else{
					getSeatDataTables();
				}
			}
		}
	};

	// 初始化开始及结束日期
	var beginTimeStr = getBeginTime();
	$("#beginTimeStr").val(beginTimeStr);
	var endTimeStr = getNowDate();
	$("#endTimeStr").val(endTimeStr);

});

/**
 * 初始化队列
 */
function initQueue() {
	$.ajax({
	    url : basePath + "seatQueue/queue_getValidQueue.action",
	    type : "post",
	    dataType : "json",
	    success : function(data) {
		    if (null != data && data != "") {
			    var selectHtml = '<option value="-1">全部</option>';
			    for ( var i = 0; i < data.length; i++) {
				    selectHtml += '<option value="' + data[i].vid + '">' + data[i].queueName + '</option>';
			    }
			    $("#queueId").html(selectHtml);
		    }
		    else{//当此账号没有队列权限时 
		    	var option = '<option value="-10">全部</option>';
		    	$("#queueId").html(option);
		    }
		    var fromPage = $("#fromPage").val();
		    if(fromPage!='seat'){
				getDataDayTables();
			}else{
				getSeatDataTables();
			}
	    }
	});
}

/**
 * 获取列表数据
 */
function getDataDayTables() {
	/* 比较开始和结束日期 start */
	var beginTimeV = $("#beginTime").val();
	var endTimeV = $("#endTime").val();

	/* 比较开始和结束日期 end */
	if (!comptime(beginTimeV, endTimeV)) {
		return false;
	}
	var queueId = -1;// 班组,-1代表全部
	// 查询部门
	// var groupId = $("#acceptedGroupId").val();
	var groupId = "";
	var workIds = $("#workIds").val();
	var workName =null;
	var fromPage = $("#fromPage").val();
	if(fromPage!='seat'){
		workName = $("#workName1").val()
	}else{
		workName = window.top.processworkIds;
	}
	$('#myTable').dataTable({
	    "bProcessing" : true,
	    'bServerSide' : true,
	    'fnServerParams' : function(aoData) {
		    aoData.push({
		        "name" : "searchBeginTime",
		        "value" : beginTimeV
		    }, {
		        "name" : "searchEndTime",
		        "value" : endTimeV
		    }, {
		        "name" : "workIds",
		        "value" : workName
		    }, {
		        "name" : "queueId",
		        "value" : queueId
		    }, {
		        "name" : "groupId",
		        "value" : groupId
		    });
	    },
	    "sAjaxSource" : basePath + "report/seatTaskStatistics_list.action",
	    "sServerMethod" : "POST",
	    "sPaginationType" : "full_numbers",
	    "bPaginate" : true, // 翻页功能
	    "bStateSave" : false, // 状态保存
	    "bLengthChange" : false, // 改变每页显示数据数量
	    "bFilter" : false, // 过滤功能
	    "bSort" : false, // 排序功能
	    "bInfo" : true,// 页脚信息
	    "bAutoWidth" : false,// 自动宽度
	    "bDestroy" : true,// 用于当要在同一个元素上履行新的dataTable绑订时，将之前的那个数据对象清除掉，换以新的对象设置
	    "iDisplayLength" : 15, // 每页显示多少行
	    "aoColumns" : [ {
		    "mDataProp" : "workNo" // 坐席工号0
	    }, {
		    "mDataProp" : "name" // 坐席姓名1
	    }, {
	        "mDataProp" : "totleLoginMinute", // 登录总时长（分）2
	        "sClass" : "data_r"
	    }, {
	        "mDataProp" : "totleBusyMinute", // 置忙总时长（分）3
	        "sClass" : "data_r"
	    }, {
	        "mDataProp" : "totleFreeMinute", // 空闲总时长（分）4
	        "sClass" : "data_r"
	    }, {
	        "mDataProp" : "callIn", // 呼入数5
	        "sClass" : "data_r"
	    }, {
	        "mDataProp" : "answercallIn", // 接听数6
	        "sClass" : "data_r"
	    }, {
	        "mDataProp" : "answercallInRate", // 呼入接听率（%）7
	        "sClass" : "data_r"
	    }, {
	        "mDataProp" : "averageRingingSecond", // 平均振铃时长（秒）8
	        "sClass" : "data_r"
	    }, {
	        "mDataProp" : "averageCallInSecond", // 平均呼入通话时长（秒）9
	        "sClass" : "data_r"
	    }, {
	        "mDataProp" : "totalCallInMinute", // 呼入通话总时长（分）10
	        "sClass" : "data_r"
	    }, {
	        "mDataProp" : "callOut", // 外呼数11
	        "sClass" : "data_r"
	    }, {
	        "mDataProp" : "callOutSuccess",// 外呼成功数12
	        "sClass" : "data_r"
	    }, {
	        "mDataProp" : "callOutSuccessRate", // 外呼成功率(%)13
	        "sClass" : "data_r"
	    }, {
	        "mDataProp" : "averageCallOutSecond", // 平均外呼通话时长（秒）14
	        "sClass" : "data_r"
	    }, {
	        "mDataProp" : "totleCallOutMinute", // 外呼通话总时长（分）15
	        "sClass" : "data_r"
	    } ],
	    "oLanguage" : {
		    "sUrl" : basePath + "plugins/datatable/cn.txt" // 中文包
	    },
	    "fnDrawCallback" : function(oSettings) {
		    $('#myTable tbody  tr td,#myTable tbody  tr td a').each(function() {
			    this.setAttribute('title', $(this).text());
		    });
	    },
	    "fnFooterCallback" : function(nFoot, aData, iStart, iEnd, aiDisplay) {
		    // nFoot.getElementsByTagName('th')[0].innerHTML = "Starting index
			// is "+iStart;
		    // alert("aData.length: "+aData.length); // 打印该页显示多少行记录。
		    var Pagecount = aData.length; // 在这里这个没有用到。
	    }
	});
}
/**
 * 获取列表数据
 */
function getDataHouseTables() {
	/* 比较开始和结束日期 start */
	var beginTimeV = $("#beginTime").val();
	var endTimeV = $("#endTime").val();
	var workIds = $("#workIds2").val();
	if (workIds.length == 0) {
		art.dialog.alert("请选择坐席！");
		return;
	}
	/* 比较开始和结束日期 end */
	if (!comptime(beginTimeV, endTimeV)) {
		return false;
	}
	var queueId = -1;// 班组,-1代表全部
	// alert(beginTime+" "+endTime+" "+queueId);
	// 查询部门
	// var groupId = $("#acceptedGroupId").val();
	var groupId = "";
	var workName = $("#workName1").val();
	$('#myTableHourse').dataTable({
	    "bProcessing" : true,
	    'bServerSide' : true,
	    'fnServerParams' : function(aoData) {
		    aoData.push({
		        "name" : "searchBeginTime",
		        "value" : beginTimeV
		    }, {
		        "name" : "searchEndTime",
		        "value" : endTimeV
		    }, {
		        "name" : "workIds",
		        "value" : workName
		    }, {
		        "name" : "queueId",
		        "value" : queueId
		    }, {
		        "name" : "groupId",
		        "value" : groupId
		    });
	    },
	    "sAjaxSource" : basePath + "report/seatTaskStatistics_dayList.action",
	    "sServerMethod" : "POST",
	    "sPaginationType" : "full_numbers",
	    "bPaginate" : false, // 翻页功能
	    "bStateSave" : true, // 状态保存
	    "bLengthChange" : false, // 改变每页显示数据数量
	    "bFilter" : false, // 过滤功能
	    "bSort" : false, // 排序功能
	    "bInfo" : false,// 页脚信息
	    "bAutoWidth" : false,// 自动宽度
	    "bDestroy" : true,// 用于当要在同一个元素上履行新的dataTable绑订时，将之前的那个数据对象清除掉，换以新的对象设置
	    "iDisplayLength" : 15, // 每页显示多少行
	    "aoColumns" : [ {
		    "mDataProp" : "workNo" // 坐席工号0
	    }, {
		    "mDataProp" : "name" // 坐席姓名1
	    }, {
	        "mDataProp" : "totleLoginMinute", // 登录总时长（分）2
	        "sClass" : "data_r"
	    }, {
	        "mDataProp" : "totleBusyMinute", // 置忙总时长（分）3
	        "sClass" : "data_r"
	    }, {
	        "mDataProp" : "totleFreeMinute", // 空闲总时长（分）4
	        "sClass" : "data_r"
	    }, {
	        "mDataProp" : "callIn", // 呼入数5
	        "sClass" : "data_r"
	    }, {
	        "mDataProp" : "answercallIn", // 接听数6
	        "sClass" : "data_r"
	    }, {
	        "mDataProp" : "answercallInRate", // 呼入接听率（%）7
	        "sClass" : "data_r"
	    }, {
	        "mDataProp" : "averageRingingSecond", // 平均振铃时长（秒）8
	        "sClass" : "data_r"
	    }, {
	        "mDataProp" : "averageCallInSecond", // 平均呼入通话时长（秒）9
	        "sClass" : "data_r"
	    }, {
	        "mDataProp" : "totalCallInMinute", // 呼入通话总时长（分）10
	        "sClass" : "data_r"
	    }, {
	        "mDataProp" : "callOut", // 外呼数11
	        "sClass" : "data_r"
	    }, {
	        "mDataProp" : "callOutSuccess",// 外呼成功数12
	        "sClass" : "data_r"
	    }, {
	        "mDataProp" : "callOutSuccessRate", // 外呼成功率(%)13
	        "sClass" : "data_r"
	    }, {
	        "mDataProp" : "averageCallOutSecond", // 平均外呼通话时长（秒）14
	        "sClass" : "data_r"
	    }, {
	        "mDataProp" : "totleCallOutMinute", // 外呼通话总时长（分）15
	        "sClass" : "data_r"
	    }  ],
	    "oLanguage" : {
		    "sUrl" : basePath + "plugins/datatable/cn.txt" // 中文包
	    },
	    "fnDrawCallback" : function(oSettings) {
		    $('#myTableHourse tbody  tr td,#myTableHourse tbody  tr td a').each(function() {
			    this.setAttribute('title', $(this).text());
		    });
	    },
	    "fnFooterCallback" : function(nFoot, aData, iStart, iEnd, aiDisplay) {
		    // nFoot.getElementsByTagName('th')[0].innerHTML = "Starting index
			// is "+iStart;
		    // alert("aData.length: "+aData.length); // 打印该页显示多少行记录。
		    var Pagecount = aData.length; // 在这里这个没有用到。
	    }
	});
}

/**
 * 坐席端数据加载
 * @returns
 */
function getSeatDataTables(){
	/* 比较开始和结束日期 start */
	var beginTimeV = $("#beginTime").val();
	var endTimeV = $("#endTime").val();

	/* 比较开始和结束日期 end */
	if (!comptime(beginTimeV, endTimeV)) {
		return false;
	}
	var queueId = -1;// 班组,-1代表全部
	// 查询部门
	// var groupId = $("#acceptedGroupId").val();
	var groupId = "";
	var workIds = $("#workIds").val();
	var workName =null;
	var fromPage = $("#fromPage").val();
	if(fromPage!='seat'){
		workName = $("#workName1").val()
	}else{
		workName = window.top.processworkIds;
	}
	$('#myTable').dataTable({
	    "bProcessing" : true,
	    'bServerSide' : true,
	    'fnServerParams' : function(aoData) {
		    aoData.push({
		        "name" : "searchBeginTime",
		        "value" : beginTimeV
		    }, {
		        "name" : "searchEndTime",
		        "value" : endTimeV
		    }, {
		        "name" : "workIds",
		        "value" : workName
		    }, {
		        "name" : "queueId",
		        "value" : queueId
		    }, {
		        "name" : "groupId",
		        "value" : groupId
		    }, {
		        "name" : "fromPage",
		        "value" : fromPage
		    });
	    },
	    "sAjaxSource" : basePath + "report/seatTaskStatistics_list.action",
	    "sServerMethod" : "POST",
	    "sPaginationType" : "full_numbers",
	    "bPaginate" : true, // 翻页功能
	    "bStateSave" : false, // 状态保存
	    "bLengthChange" : false, // 改变每页显示数据数量
	    "bFilter" : false, // 过滤功能
	    "bSort" : false, // 排序功能
	    "bInfo" : true,// 页脚信息
	    "bAutoWidth" : false,// 自动宽度
	    "bDestroy" : true,// 用于当要在同一个元素上履行新的dataTable绑订时，将之前的那个数据对象清除掉，换以新的对象设置
	    "iDisplayLength" : 15, // 每页显示多少行
	    "aoColumns" : [ {
		    "mDataProp" : "workNo" // 坐席工号0
	    }, {
		    "mDataProp" : "name" // 坐席姓名1
	    }, {
	        "mDataProp" : "totleLoginMinute", // 登录总时长（分）2
	        "sClass" : "data_r"
	    }, {
	        "mDataProp" : "totleBusyMinute", // 置忙总时长（分）3
	        "sClass" : "data_r"
	    }, {
	        "mDataProp" : "totleFreeMinute", // 空闲总时长（分）4
	        "sClass" : "data_r"
	    }, {
	        "mDataProp" : "callIn", // 呼入数5
	        "sClass" : "data_r"
	    }, {
	        "mDataProp" : "answercallIn", // 接听数6
	        "sClass" : "data_r"
	    }, {
	        "mDataProp" : "answercallInRate", // 呼入接听率（%）7
	        "sClass" : "data_r"
	    }, {
	        "mDataProp" : "averageRingingSecond", // 平均振铃时长（秒）8
	        "sClass" : "data_r"
	    }, {
	        "mDataProp" : "averageCallInSecond", // 平均呼入通话时长（秒）9
	        "sClass" : "data_r"
	    }, {
	        "mDataProp" : "totalCallInMinute", // 呼入通话总时长（分）10
	        "sClass" : "data_r"
	    }, {
	        "mDataProp" : "callOut", // 外呼数11
	        "sClass" : "data_r"
	    }, {
	        "mDataProp" : "callOutSuccess",// 外呼成功数12
	        "sClass" : "data_r"
	    }, {
	        "mDataProp" : "callOutSuccessRate", // 外呼成功率(%)13
	        "sClass" : "data_r"
	    }, {
	        "mDataProp" : "averageCallOutSecond", // 平均外呼通话时长（秒）14
	        "sClass" : "data_r"
	    }, {
	        "mDataProp" : "totleCallOutMinute", // 外呼通话总时长（分）15
	        "sClass" : "data_r"
	    }  ],
	    "oLanguage" : {
		    "sUrl" : basePath + "plugins/datatable/cn.txt" // 中文包
	    },
	    "fnDrawCallback" : function(oSettings) {
		    $('#myTable tbody  tr td,#myTable tbody  tr td a').each(function() {
			    this.setAttribute('title', $(this).text());
		    });
	    },
	    "fnFooterCallback" : function(nFoot, aData, iStart, iEnd, aiDisplay) {
		     
	    }
	});
}



function getBeginTime() {
	var now = new Date();
	now.setDate(1);
	return getFormatDate(now);
}
function getNowDate() {
	var now = new Date();
	return getFormatDate(now);
}
function getFormatDate(srcdate) {

	var formatDate = "";
	// 初始化时间
	var Year = srcdate.getFullYear();// ie火狐下都可以
	var Month = srcdate.getMonth() + 1;
	formatDate += Year;
	if (Month >= 10) {
		formatDate += "-" + Month;
	} else {
		formatDate += "-0" + Month;
	}

	var Day = srcdate.getDate();
	if (Day >= 10) {
		formatDate += "-" + Day;
	} else {
		formatDate += "-0" + Day;
	}
	return formatDate;
}
/**
 * 导出操作
 * 
 * @returns {Boolean}
 */
function exporting() {
	/* 比较开始和结束日期 start */
	var beginTimeV = $("#beginTime").val();
	var endTimeV = $("#endTime").val();
	/* 比较开始和结束日期 end */
	if (!comptime(beginTimeV, endTimeV)) {
		return false;
	}
	var queueId = -1;// 班组,-1代表全部
	// 查询部门
	// var groupId = $("#acceptedGroupId").val();
	var groupId = "";
	var granularity = $('#granularity').val();
	granularity = 1;
	var workIds = "";
	var workName = $("#workName1").val();
	if(workName!=null&&workName!=''){
		workName = encodeURI(workName);
	}
	//alert(workName);
	if (granularity == '1') {
		workIds = $("#workIds").val();
		
		var url = basePath + "report/seatTaskStatistics_exporting.action?searchBeginTime=" + beginTimeV + "&searchEndTime="
        + endTimeV + "&queueId=" + queueId + "&groupId=" + groupId + "&workIds=" + workName;
		
		url=encodeURI(url);
		window.open(url);
		// alert(beginTime+" "+endTime+" "+queueId);
	} else {
		workIds = $("#workIds2").val();
		if (workIds.length == 0) {
			art.dialog.alert("请选择坐席！");
			return;
		}
		var url = basePath + "report/seatTaskStatistics_exportingHourse.action?beginTime=" + beginTimeV
        + "&endTime=" + endTimeV + "&queueId=" + queueId + "&groupId=" + groupId + "&workIds=" + workName;
		
		url=encodeURI(url);
		window.open(url);
		// alert(beginTime+" "+endTime+" "+queueId);
	}

	// return false;
}
function getBeginTime() {
	var now = new Date();
	now.setDate(1);
	return getFormatDate(now);
}
function getNowDate() {
	var now = new Date();
	return getFormatDate(now);
}
function getFormatDate(srcdate) {

	var formatDate = "";
	// 初始化时间
	var Year = srcdate.getFullYear();// ie火狐下都可以
	var Month = srcdate.getMonth() + 1;
	formatDate += Year;
	if (Month >= 10) {
		formatDate += "-" + Month;
	} else {
		formatDate += "-0" + Month;
	}

	var Day = srcdate.getDate();
	if (Day >= 10) {
		formatDate += "-" + Day;
	} else {
		formatDate += "-0" + Day;
	}
	return formatDate;
}

// 比较时间
function comptime(startDate, endDate) {
	var granularity = $('#granularity').val();
	if (startDate.length > 0 && endDate.length > 0) {
		var startDateTemp = startDate.split(" ");
		var endDateTemp = endDate.split(" ");

		var arrStartDate = startDateTemp[0].split("-");
		var arrEndDate = endDateTemp[0].split("-");

		var arrStartTime = startDateTemp[1].split(":");
		var arrEndTime = endDateTemp[1].split(":");

		var allStartDate = new Date(arrStartDate[0], arrStartDate[1], arrStartDate[2], arrStartTime[0],
		        arrStartTime[1], arrStartTime[2]);
		var allEndDate = new Date(arrEndDate[0], arrEndDate[1], arrEndDate[2], arrEndTime[0], arrEndTime[1],
		        arrEndTime[2]);
		if (allStartDate.getTime() > allEndDate.getTime()) {
			art.dialog.alert("起始时间不能大于终止时间。");
			return false;
		}
		if (granularity == '1') {
			if (allEndDate - allStartDate > 31 * 24 * 60 * 60 * 1000) {
				art.dialog.alert("时间间隔超过31天。");
				return false;
			}
		} else {
			if (allEndDate - allStartDate > 24 * 60 * 60 * 1000) {
				art.dialog.alert("时间间隔超过24小时。");
				return false;
			}
		}

	} else {
		if (granularity == '2' && startDate.length == 0) {
			art.dialog.alert("起始时间不能为空。");
			return false;
		} else if (granularity == '2' && endDate.length == 0) {
			art.dialog.alert("结束时间不能为空");
			return false;
		}
	}
	return true;
}
/**
 * 粒度变更事件
 */
function granularityChange() {
	var granularity = $('#granularity').val();
	if ('1' == granularity) {
		$('#treeContent').show();
		$('#treeContent2').hide();
		$('#myTableDiv').show();
		$('#myTableHourseDiv').hide();
		// getIndustryTree();
		$("#beginTime").val(getStratTime(1));
	} else if ('2' == granularity) {
		$('#treeContent').hide();
		$('#treeContent2').show();
		$('#myTableDiv').hide();
		$('#myTableHourseDiv').show();
		$("#beginTime").val(getStratTime(2));
		getDataHouseTables();
	}
}
// 获取当前时间
function CurentTime() {
	var now = new Date();

	var year = now.getFullYear(); // 年
	var month = now.getMonth() + 1; // 月
	var day = now.getDate(); // 日

	var hh = now.getHours(); // 时
	var mm = now.getMinutes(); // 分
	var ss = now.getSeconds(); // 秒

	var clock = year + "-";

	if (month < 10)
		clock += "0";

	clock += month + "-";

	if (day < 10)
		clock += "0";

	clock += day + " ";

	if (hh < 10)
		clock += "0";
	clock += hh + ":";
	if (mm < 10)
		clock += '0';
	clock += mm + ":";
	if (ss < 10)
		clock += '0';
	clock += ss;
	return (clock);
}
// 获取起始时间
function getStratTime(type) {
	var now = new Date();

	var year = now.getFullYear(); // 年
	var month = now.getMonth() + 1; // 月
	var day = now.getDate(); // 日

	var clock = year + "-";

	if (month < 10)
		clock += "0";

	clock += month + "-";
	if (type == 1) {
		clock += "01 ";
	} else {
		if (day < 10)
			clock += "0";
		clock += day + " ";
	}
	clock += "00:00:00";
	return (clock);
}
