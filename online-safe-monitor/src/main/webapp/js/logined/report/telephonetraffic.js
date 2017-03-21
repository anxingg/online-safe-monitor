
jQuery(document).ready(function() {
	// 初始化队列
	initQueue();
	
	var beginTimeStr = getBeginTime();
	var endTimeStr = getNowDate();
	if($("#plotBeginTime").val() != ''){
		$("#beginTimeStr").val($("#plotBeginTime").val());	
	}else{
		$("#beginTimeStr").val(beginTimeStr);	
	}
	if($("#plotEndTime").val() != ''){
		$("#endTimeStr").val($("#plotEndTime").val());	
	}else{
		// 初始化开始及结束日期
		$("#endTimeStr").val(endTimeStr);
	}

	// 查询列表数据
	getTableData();
	
	// 回车事件
	document.onkeydown = function(e) {
		// 兼容FF和IE和Opera
		var theEvent = e || window.event;
		var code = theEvent.keyCode || theEvent.which || theEvent.charCode;
		if (code == 13) {
			getTableData();
		}
	};
	
	
	$("#trafficExport").click(function() {
		// 比较开始和结束日期
		var beginTimeStr = $("#beginTimeStr").val();
		if ("" == beginTimeStr){
			art.dialog.alert("起始日期不能为空。");
			return;
		}
		
		beginTimeStr = beginTimeStr.replace(/-/g,"/");
		var beginTime = new Date(beginTimeStr);
		
		var endTimeStr = $("#endTimeStr").val();
		if ("" == endTimeStr){
			art.dialog.alert("终止日期不能为空。");
			return;
		}
		
		endTimeStr = endTimeStr.replace(/-/g,"/");
		var endTime = new Date(endTimeStr);
		
		if (endTime.getTime() - beginTime.getTime() < 0){
			art.dialog.alert("起始日期不能大于终止日期。");
			return;
		} else if (endTime.getTime() - beginTime.getTime() > 30 * 24 * 60 * 60 * 1000){
			art.dialog.alert("时间间隔超过31天。");
			return;
		}
		//var queueId = $("#queue").val();//查询的队列id
		var queueId = -1;
		window.open(basePath + "report/reportTelephonetraffic_exportCallQuantity.action?beginTime="+$("#beginTimeStr").val() + "&endTime=" + $("#endTimeStr").val() + "&queueId=" + queueId);
		return false;
	});
	
	//图标视图按钮点击
	$("#trafficPlot").click(function(){
		var url = "logined/report/telephonetrafficPlot.jsp?beginTime="+ $("#beginTimeStr").val()+"&endTime="+$("#endTimeStr").val();
		window.location.href(basePath + url);
	});
	
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
			    $("#queue").html(selectHtml);
		    }
	    }
	});
}

function getTableData() {

	// 比较开始和结束日期
	var beginTimeStr = $("#beginTimeStr").val();
	if ("" == beginTimeStr){
		art.dialog.alert("起始日期不能为空。");
		return;
	}
	
	beginTimeStr = beginTimeStr.replace(/-/g,"/");
	var beginTime = new Date(beginTimeStr);
	
	var endTimeStr = $("#endTimeStr").val();
	if ("" == endTimeStr){
		art.dialog.alert("终止日期不能为空。");
		return;
	}
	
	endTimeStr = endTimeStr.replace(/-/g,"/");
	var endTime = new Date(endTimeStr);
	
	if (endTime.getTime() - beginTime.getTime() < 0){
		art.dialog.alert("起始日期不能大于终止日期。");
		return;
	} else if (endTime.getTime() - beginTime.getTime() > 30 * 24 * 60 * 60 * 1000){
		art.dialog.alert("时间间隔超过31天。");
		return;
	}
	var paramData = {
	    'beginTime' : $("#beginTimeStr").val(),
	    'endTime' : $("#endTimeStr").val(),
	    'queueId' : -1//-1表示全部队列
	};
	$.ajax({
	            url : basePath + "report/reportTelephonetraffic_getCallQuantity.action",
	            type : "post",
	            dataType : "json",
	            data : paramData,
	            success : function(data) {
		            var head = '<thead><tr><th style="width:80px"><div id="u186"><div id="u186_rtf"><p>日期</p></div></div></th><th>呼入量</th><th>呼出量</th><th>总话务量</th>';
		            head += '<th>呼损总量</th>';
		            head += '<th>呼入通话总<p>时长(分)</p></th>';
		            head += '<th>呼出通话总<p>时长(分)</p></th>';
		            head += '<th>登录总时长<p>(分)</p></th>';
		            head += '<th>置忙总时长<p>(分)</p></th>';
		            head += '<th>空闲总时长<p>(分)</p></th>';
		            head += '</tr></thead>'
		            var body = '';
		            var barData = "";
		            var tickData = "";
		            var line1_data1 = new Array();
		            var line1_data2 = new Array();
		            var ticks = new Array();
		            if (null != data && data != "") {
			            for ( var i = 0; i < data.length; i++) {
				            if (i != data.length - 1) {
				            	line1_data1.push(data[i].callInNum);
				            	line1_data2.push(data[i].callOutNum);
				            	ticks.push(data[i].callDate);
				            	if(i==0 || i%2==0){
				            		body += '<tr class="odd"><td>' + data[i].callDate + '</td><td class="data_r">' + data[i].callInNum
				                    + '</td><td class="data_r">' + data[i].callOutNum + '</td><td class="data_r">'
				                    + data[i].totleCallNum + '</td>';
				            		body += '<td class="data_r">'+ data[i].totleCallLoss + '</td>';
				            		body += '<td class="data_r">'+ data[i].totleCallInTime + '</td>';
				            		body += '<td class="data_r">'+ data[i].totleCallOutTime + '</td>';
				            		body += '<td class="data_r">'+ data[i].totleLoginTime + '</td>';
				            		body += '<td class="data_r">'+ data[i].totleBosyTime + '</td>';
				            		body += '<td class="right_bdr0 data_r">'+ data[i].totleNoBosyTime + '</td>';
				            		body += '</tr>';
				            	}else{
				            		body += '<tr class="even"><td>' + data[i].callDate + '</td><td class="data_r">' + data[i].callInNum
				                    + '</td><td class="data_r">' + data[i].callOutNum + '</td><td class="data_r">'
				                    + data[i].totleCallNum + '</td>';
				            		body += '<td class="data_r">'+ data[i].totleCallLoss + '</td>';
				            		body += '<td class="data_r">'+ data[i].totleCallInTime + '</td>';
				            		body += '<td class="data_r">'+ data[i].totleCallOutTime + '</td>';
				            		body += '<td class="data_r">'+ data[i].totleLoginTime + '</td>';
				            		body += '<td class="data_r">'+ data[i].totleBosyTime + '</td>';
				            		body += '<td class="right_bdr0 data_r">'+ data[i].totleNoBosyTime + '</td>';
				            		body += '</tr>';
				            	}
					            
				            } else {
					            body += '<tr class="total"><td>总计</td><td class="data_r">'
					                    + data[i].callInNum + '</td>';
					            body += '<td class="data_r">' + data[i].callOutNum + '</td>';
					            body += '<td class="data_r">' + data[i].totleCallNum + '</td>';
					            body += '<td class="data_r">' + data[i].totleCallLoss + '</td>';
					            body += '<td class="data_r">' + data[i].totleCallInTime + '</td>';
					            body += '<td class="data_r">' + data[i].totleCallOutTime + '</td>';
					            body += '<td class="data_r">' + data[i].totleLoginTime + '</td>';
					            body += '<td class="data_r">' + data[i].totleBosyTime + '</td>';
					            body +='<td class="right_bdr0 data_r">' + data[i].totleNoBosyTime + '</td></tr>';
				            }
			            }
			            barData = barData.substr(0, barData.length - 1);
			            tickData = tickData.substr(0, tickData.length - 1);
                      
			            $("#teleTrafficTable").html(head + body);
//			            if (line1_data1.length > 0){
//			            	getBarChart(line1_data1, line1_data2, ticks, "呼入量", "呼出量");
//			            }
		            }
	            }
	        });
}

//function getBarChart(s1, s2, ticks, s1Name, s2Name) {
//	$('#chart').empty();
//
//    $.jqplot('chart', [ s1, s2 ], {
//    	captureRightClick: true,
//	    stackSeries : true,
//	    seriesDefaults : {
//	    	renderer:$.jqplot.BarRenderer,
//	    	shadowAngle: 135,
//	        renderer : $.jqplot.BarRenderer,
//	        rendererOptions : {
//	        	highlightMouseDown: true,
//	            barPadding : 0, // 设置同一分类两个柱状条之间的距离（px）
//	            // barMargin: 30, //设置不同分类的两个柱状条之间的距离（px）（同一个横坐标表点上）
//	            // barDirection: 'vertical', //设置柱状图显示的方向：垂直显示和水平显示
//	            // ，默认垂直显示 vertical or horizontal.
//	            barWidth : 13
//	        // 设置柱状图中每个柱状条的宽度
//	        // shadowOffset: 2, // 同grid相同属性设置
//	        // shadowDepth: 5, // 同grid相同属性设置
//	        // shadowAlpha: 0.8, // 同grid相同属性设置
//	        },
//	        pointLabels : {
//	            show : true,
//	        }
//	    },
//	    series : [ {
//		    label : s1Name
//	    }, {
//		    label : s2Name
//	    } ],
//	    legend : {
//	        show : true,
//	        placement : 'outsideGrid'
//	    },
//	    axes : {
//		    xaxis : {
//		        ticks : ticks,
//		        tickRenderer : $.jqplot.CanvasAxisTickRenderer,
//		        renderer : $.jqplot.CategoryAxisRenderer,
//
//		        tickOptions : {
//			        angle : -60
//		        }
//		    }
//	    },
//	    yaxis : {
//		    min : 0
//	    }
//	});
//
//}

function getBeginTime(){
	var now = new Date();
	now.setDate(1);
	return getFormatDate(now);
}
function getNowDate(){
	var now = new Date();
	return getFormatDate(now);
}

function getFormatDate(srcdate) {

    var formatDate = "";
    //初始化时间 
    var Year = srcdate.getFullYear();//ie火狐下都可以 
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

