$(document).ready(function() {
    // 初始化队列
    //initQueue();

    // 初始化开始及结束日期	
    var beginTimeStr = getBeginTime();
    $("#beginTimeStr").val(beginTimeStr);
    var endTimeStr = getNowDate();
    $("#endTimeStr").val(endTimeStr);

    // 查询列表数据
    getTableData();

    //导出方法
    $("#callblockExport").click(function() {
        exporting();
    });

	// 回车事件
	document.onkeydown = function(e) {
		// 兼容FF和IE和Opera
		var theEvent = e || window.event;
		var code = theEvent.keyCode || theEvent.which || theEvent.charCode;
		if (code == 13) {
			getTableData();
		}
	};
	
	//查询方法
    $("#search").click(function() {
        getTableData();
    });
});

function exporting(){
	// 比较开始和结束日期
    var beginTimeStr = $("#beginTimeStr").val();
    if ("" == beginTimeStr) {
        art.dialog.alert("起始日期不能为空。");
        return;
    }

    beginTimeStr = beginTimeStr.replace(/-/g, "/");
    var beginTime = new Date(beginTimeStr);

    var endTimeStr = $("#endTimeStr").val();
    if ("" == endTimeStr) {
        art.dialog.alert("终止日期不能为空。");
        return;
    }

    endTimeStr = endTimeStr.replace(/-/g, "/");
    var endTime = new Date(endTimeStr);

    if (endTime.getTime() - beginTime.getTime() < 0) {
        art.dialog.alert("起始日期不能大于终止日期。");
        return;
    } else if (endTime.getTime() - beginTime.getTime() > 31 * 24 * 60 * 60 * 1000) {
        art.dialog.alert("时间间隔超过31天。");
        return;
    }
    var searchkey = $.trim($("#searchkey").val());//关键字(包含主叫号码\坐席工号)
    var queueId = -1;//代表全部
    window.open(basePath + "msi/calllog/setup_exportCallBlockList.action?beginTimeStr="
            + $("#beginTimeStr").val() + "&endTimeStr=" + $("#endTimeStr").val() + "&queueId="
            + queueId + "&searchkey=" + searchkey);
    return false;
}

function getTableData() {

	// 比较开始和结束日期
	var beginTimeStr = $("#beginTimeStr").val();
	if ("" == beginTimeStr) {
		art.dialog.alert("起始日期不能为空。");
		return;
	}

	beginTimeStr = beginTimeStr.replace(/-/g, "/");
	var beginTime = new Date(beginTimeStr);

	var endTimeStr = $("#endTimeStr").val();
	if ("" == endTimeStr) {
		art.dialog.alert("终止日期不能为空。");
		return;
	}

	endTimeStr = endTimeStr.replace(/-/g, "/");
	var endTime = new Date(endTimeStr);

	if (endTime.getTime() - beginTime.getTime() < 0) {
		art.dialog.alert("起始日期不能大于终止日期。");
		return;
	} else if (endTime.getTime() - beginTime.getTime() > 31 * 24 * 60 * 60 * 1000) {
		art.dialog.alert("时间间隔超过31天。");
		return;
	}
	var searchkey = $.trim($("#searchkey").val());//关键字(包含主叫号码\坐席工号)
	$('#callblockingTable').dataTable({
	    "bDestroy" : true,
	    "bProcessing" : true,
	    'bServerSide' : true,
	    'fnServerParams' : function(aoData) {
		    aoData.push({
		        "name" : "beginTimeStr",
		        "value" : $.trim($("#beginTimeStr").val())
		    }, {
		        "name" : "endTimeStr",
		        "value" : $.trim($("#endTimeStr").val())
		    }, {
		        "name" : "searchkey",
		        "value" : searchkey
		    }, {
		        "name" : "queueId",
		        "value" : -1
		    });
	    },
	    "sAjaxSource" : basePath + "msi/calllog/setup_getCallBlockList.action",
	    "sServerMethod" : "POST",
	    "sPaginationType" : "full_numbers",
	    "bPaginate" : true, // 翻页功能
	    "bStateSave" : false, // 状态保存
	    "bLengthChange" : false, // 改变每页显示数据数量
	    "bFilter" : false, // 过滤功能
	    "bSort" : false, // 排序功能
	    "bInfo" : true,// 页脚信息
	    "bAutoWidth" : false,// 自动宽度
	    "bDestroy" : true,//用于当要在同一个元素上履行新的dataTable绑订时，将之前的那个数据对象清除掉，换以新的对象设置
	    "iDisplayLength" : 15, // 每页显示多少行
	    "aoColumns" : [ {
	        "sTitle" : '序号',
	        "mDataProp" : "orderNumber",
	        "sClass" : "num",

	    }, {
	        "sTitle" : '主叫号码',
	        "mDataProp" : "call",

	    }, {
	        "sTitle" : '被叫号码',
	        "mDataProp" : "called",
	    }, {
	        "sTitle" : '呼入时间',
	        "mDataProp" : "callInTime",
	    }, {
	        "sTitle" : '振铃时间',
	        "mDataProp" : "talkInTime",
	    }, {
	        "sTitle" : '振铃时长 （秒）',
	        "mDataProp" : "linkCalled",
	        "sClass" : "data_r"
	    }, {
	        "sTitle" : '挂断时间',
	        "mDataProp" : "callEndTime",
	        "sClass" : "data_r"
	    }, 
	   /* {
	        "sTitle" : '队列',
	        "mDataProp" : "queue",
	    }, */
	    {
	        "sTitle" : '坐席工号',
	        "mDataProp" : "workNo",
	    }, {
	        "sTitle" : '呼损原因',
	        "mDataProp" : "endtype"
//	        "sClass" : "data_l"
	    } ],
	    "oLanguage" : {
		    "sUrl" : basePath + "plugins/datatable/cn.txt" // 中文包
	    },
	    "aoColumnDefs" : [],
	    "fnDrawCallback" : function(oSettings) {
		    $('#myTable tbody  tr td').each(function() {
			    this.setAttribute('title', $(this).text());
		    });
	    }
	});
}

function getBeginTime() {
	var now = new Date();
	now.setDate(1);
	now.setHours(0, 0, 0, 0);
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

	// 小时
	var hour = srcdate.getHours();
	if (hour >= 10) {
		formatDate += " " + hour;
	} else {
		formatDate += " 0" + hour;
	}

	// 分钟
	var minutes = srcdate.getMinutes();
	if (minutes >= 10) {
		formatDate += ":" + minutes;
	} else {
		formatDate += ":0" + minutes;
	}
	return formatDate;
}
