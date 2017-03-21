$(document).ready(function() {
	//初始化队列
	//loadCityName();
	
	// 初始化开始及结束日期	
    var beginTimeStr = getBeginTime();
    $("#beginTimeStr").val(beginTimeStr);
    var endTimeStr = getNowDate();
    $("#endTimeStr").val(endTimeStr);

    // 查询列表数据
    getTableData();
    
    /* 导出按钮绑订的方法 */
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

	/* 查询按钮绑订的方法 */
    $("#search").click(function() {
        getTableData();
    });
    
});

/**
 * 导出的方法
 */
function exporting(){
	var beginTime = $.trim($("#beginTimeStr").val());//开始时间
	if(beginTime!=null&&beginTime!=''){
		beginTime = $.trim($("#beginTimeStr").val())+" 00:00:00";
	}
	var endTime = $.trim($("#endTimeStr").val());//结束时间
	if(endTime!=null&&endTime!=''){
		endTime = $.trim($("#endTimeStr").val())+" 23:59:59";
	}
    var searchkey = $.trim($("#searchkey").val());//关键字(包含主叫号码\坐席工号)
    var isForkGroup = -1;//代表全部
    window.open(basePath + "statistics/exportSeatAttendanceList.action?beginTimeStr="
            + beginTime + "&endTimeStr=" + endTime + "&isForkGroup="
            + isForkGroup + "&searchKey=" + searchkey);
    //return false;
}
///**
// * 初始化队列
// */
/*function initQueue() {
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
			    $("#queue").html(selectHtml);
		    }
		    else{//当此账号没有队列权限时 
		    	var option = '<option value="-10">全部</option>';
		    	$("#queue").html(option);
		    }
		    var fromPage = $("#fromPage").val();
		    if(fromPage!='seat'){
				getDataDayTables();
			}else{
				getSeatDataTables();
			}
	    }
	});
}*/

///**
// * 加载所属地市
// */
//function loadCityName(){
//	$.ajax({
//		url:basePath+"worksummary/loadCityName.action",
//		type:"post",
//		dataType:"text",
//		success:function(data){
//			var jsonData = eval('('+data+')');
//			if(data!=null){
//				var selectHtml = "";
//				selectHtml += '<option selected="selected" value="-1">全部</option>';
//				for(var i=0;i<jsonData.length;i++){
//					if(jsonData[i].isForkGroup!=2){
//						selectHtml += '<option value="'+jsonData[i].isForkGroup+'">'+jsonData[i].name+'</option>';
//					}
//				}
//				$("#queue").html(selectHtml);
//			}
//		}
//	});
//}


///**
// * 初始化队列
// */
//function initQueue() {
//	$.ajax({
//	    url : basePath + "seatQueue/queue_getValidQueue.action",
//	    type : "post",
//	    dataType : "json",
//	    success : function(data) {
//		    if (null != data && data != "") {
//			    var selectHtml = '<option value="-1">全部</option>';
//			    for ( var i = 0; i < data.length; i++) {
//				    selectHtml += '<option value="' + data[i].vid + '">' + data[i].queueName + '</option>';
//			    }
//			    $("#queue").html(selectHtml);
//		    }
//	    }
//	});
//}

function getTableData() {

	var beginTime = $.trim($("#beginTimeStr").val());//开始时间
	if(beginTime!=null&&beginTime!=''){
		beginTime = $.trim($("#beginTimeStr").val())+" 00:00:00";
	}
	var endTime = $.trim($("#endTimeStr").val());//结束时间
	if(endTime!=null&&endTime!=''){
		endTime = $.trim($("#endTimeStr").val())+" 23:59:59";
	}

	var searchkey = $.trim($("#searchkey").val());//关键字(工号)
	$('#callblockingTable').dataTable({
	    "bDestroy" : true,
	    "bProcessing" : true,
	    'bServerSide' : true,
	    'fnServerParams' : function(aoData) {
		    aoData.push({
		        "name" : "beginTimeStr",
		        "value" : beginTime
		    }, {
		        "name" : "endTimeStr",
		        "value" : endTime
		    }, {
		        "name" : "searchKey",
		        "value" : searchkey
		    }, {
		        "name" : "isForkGroup",
		        "value" : -1
		    });
	    },
	    "sAjaxSource" : basePath + "statistics/seatAttendance.action",
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
	        "mDataProp" : "num",
	        "sClass" : "num",

	    }, 
	    /*{
	        "sTitle" : '所属地市',
	        "mDataProp" : "isForkGroupName",

	    }, */
	    {
	        "sTitle" : '坐席工号',
	        "mDataProp" : "workNo",
	    }, {
	        "sTitle" : '坐席姓名',
	        "mDataProp" : "workName",
	    }, {
	        "sTitle" : '登录时间',
	        "mDataProp" : "checkInTimeStr",
	    }, {
	        "sTitle" : '退出时间',
	        "mDataProp" : "checkOutTimeStr"
	    }, {
	        "sTitle" : '在线时长(分钟)',
	        "mDataProp" : "onlineTime"
	    }, {
	        "sTitle" : '置忙时长(分钟)',
	        "mDataProp" : "busyTime"
	    }, {
	        "sTitle" : '空闲时长(分钟)',
	        "mDataProp" : "restTime"
	    }, {
	        "sTitle" : '置忙次数',
	        "mDataProp" : "changeBusyTimes"
	    }],
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

//	// 小时
//	var hour = srcdate.getHours();
//	if (hour >= 10) {
//		formatDate += " " + hour;
//	} else {
//		formatDate += " 0" + hour;
//	}
//
//	// 分钟
//	var minutes = srcdate.getMinutes();
//	if (minutes >= 10) {
//		formatDate += ":" + minutes;
//	} else {
//		formatDate += ":0" + minutes;
//	}
//	
//	//秒
//	var second = srcdate.getSeconds();
//	if(second>=10){
//		formatDate += ":"+second;
//	}else{
//		formatDate += ":0"+second;
//	}
	return formatDate;
}
