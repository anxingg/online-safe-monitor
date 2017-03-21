/**
 * 排序统计页面的js
 */
$(document).ready(function(){
	var day = getNowDate();
	$("#day").val(day);
	
//	$("#startHour").change(function(){
//		changeEndHourData();
//	});
//	
//	$("#endHour").change(function(){
//		changeStartHourData();
//	});
	
	// 初始化队列
	initQueue();
	
	getData();
	
	//查询按钮
	$("#search").click(function(){
		getData();
	});
	
	// 回车事件
	document.onkeydown = function(e) {
		// 兼容FF和IE和Opera
		var theEvent = e || window.event;
		var code = theEvent.keyCode || theEvent.which || theEvent.charCode;
		if (code == 13) {
			getData();
		}
	};
	
	
	//导出数据
	$("#trafficExport").click(function() {
		var beginTime="";
		var endTime="";
		var day=$("#day").val();//时间（天）
		var startHour=$("#startHour").find("option:selected").html();//时间（小时，开始）
		if(startHour=='--'){
			startHour='00';
		}
		var endHour=$("#endHour").find("option:selected").html();//时间（小时，结束）
		if(endHour=='--'){
			endHour='00';
		}
		if(day!=""){
			beginTime = day+" "+startHour+":00:00";
			endTime = day+" "+endHour+":00:00";
		}else{
			art.dialog.alert("请选择一个日期。");
			return ;
		}
		var startHourNumber=parseInt($("#startHour").find("option:selected").html(), 10);
		var endHourNumber=parseInt($("#endHour").find("option:selected").html(), 10);

		if(startHourNumber>=endHourNumber){
			art.dialog.alert("请选择正确的时间段。");
			return ;
		}
//		var queueId = $("#queue").find("option:selected").val();//队列
		var queueId = -1;//队列代表全部
		//alert(beginTime+"  "+endTime+"  "+queueId);
		//坐席所属部门
		//var groupId = $("#acceptedGroupId").val();
		window.open(basePath + "report/queueStatistics_export.action?beginTime="+beginTime
				+ "&endTime=" + endTime + "&queueId=" +queueId);
		return false;
	});
	
	
});
//var allHour=['<option>00</option>','<option>01</option>','<option>02</option>','<option>03</option>','<option>04</option>','<option>05</option>','<option>06</option>','<option>07</option>','<option>08</option>','<option>09</option>','<option>10</option>','<option>11</option>','<option>12</option>','<option>13</option>','<option>14</option>','<option>15</option>','<option>16</option>','<option>17</option>','<option>18</option>','<option>19</option>','<option>20</option>','<option>21</option>','<option>22</option>','<option>23</option>'];
//function changeEndHourData(){
//	var startHour=parseInt($("#startHour").find("option:selected").html());
//	/* 开始时间选定后，结束时间应大于开始时间 */
//	$("#endHour option").remove();
//	for(i=startHour+1;i<allHour.length;i++){
//		$("#endHour").append(allHour[i]);
//	}
//	if(startHour==23){
//		$("#endHour").append('<option>23</option>');
//	}
//	
//}
//function changeStartHourData(){
//	var endHour=parseInt($("#endHour").find("option:selected").html());
//	$("#startHour option").remove();
//	for(i=0;i<endHour;i++){
//		$("#startHour").append(allHour[i]);
//	}
//	if(endHour==0){
//		$("#startHour").append('<option>00</option>');
//	}
//}

/**
 * 获取列表数据
 */
function getData(){
	var beginTime="";
	var endTime="";
	var day=$("#day").val();//时间（天）
	var startHour=$("#startHour").find("option:selected").html();//时间（小时，开始）
	if(startHour=='--'){
		startHour='00';
	}
	var endHour=$("#endHour").find("option:selected").html();//时间（小时，结束）
	if(endHour=='--'){
		endHour='00';
	}
	if(day!=""){
		beginTime = day+" "+startHour+":00:00";
		endTime = day+" "+endHour+":00:00";
	}else{
		art.dialog.alert("请选择一个日期。");
		return ;
	}
	var startHourNumber=parseInt($("#startHour").find("option:selected").html(), 10);
	var endHourNumber=parseInt($("#endHour").find("option:selected").html(), 10);

	if(startHourNumber>=endHourNumber){
		art.dialog.alert("请选择正确的时间段。");
		return ;
	}
	var queueId = $("#queue").find("option:selected").val();//队列
	var queueId = -1;//队列代表全部
	//alert(beginTime+"  "+endTime+"  "+queueId);
	//坐席所属部门
	var groupId = $("#acceptedGroupId").val();
	var paramData = {
		"beginTime" : beginTime,
		"endTime" : endTime,
		"queueId" : queueId,
		"groupId" : groupId
	};

	// 发送ajax请求,获取对应的人员姓名
	$.ajax({
		url : basePath + 'report/queueStatistics_getChart.action',
		type : 'post',
		dataType : 'text',
		data : paramData,
		success : function(data) {
			if(data!='null'){
				var head = '<thead><tr><th style="width:230px"><div id="u186"><div id="u186_rtf"><p>时间</p></div></div></th><th style="width:175px">排队数</th></tr></thead>';
	            var body = '';
				var jsonObject=eval('('+data+')'); //jsonObject是一个数组对象
				
				var length=jsonObject.length;
				var line1_data2 = new Array();
	            var ticks = new Array();
	            var totleNum = 0;
				for(i=0;i<length;i++){
					var line1_data1 = new Array();
					var totleInQueueNum=jsonObject[i].totleInQueueNum;
					//alert(line1_data1+"  "+line1_data2);
					if(i!=length-1){
						var inQueueHour=jsonObject[i].inQueueHour;
						line1_data1.push(inQueueHour);
						line1_data1.push(totleInQueueNum);
						line1_data2.push(line1_data1);
						ticks.push(inQueueHour);
						if(i==0 || i%2==0){
							body += '<tr class="odd"><td>' + inQueueHour + '</td><td class="data_r">' + totleInQueueNum
							+ '</td></tr>';
						}else{
							body += '<tr class="even"><td>' + inQueueHour + '</td><td class="data_r">' + totleInQueueNum
							+ '</td></tr>';
						}
					}else{
						body += '<tr class="total"><td>总计</td><td class="right_bdr0 data_r">'
							+ totleInQueueNum + '</td>'; 
					}
					
				}
				
				$("#teleTrafficTable").html(head + body);
				
				if (line1_data2.length > 0){
					getBarChart(line1_data2,ticks);
				}
			}

		}
	}); 
}

function getBarChart(s1,ticks) {
	//alert(s1);
	$('#chart').empty();

    $.jqplot('chart', [ s1], {
    	legend: {show:true},//设置是否出现分类名称框（即所有分类的名称出现在图的某个位置）
    	pointLabels: { show:true},//用于在数据点所在位置显示相关信息（非提示框性质）
    	showMarker:true,//是否强调显示图中的数据节点
        axes:{
          xaxis:{//时间
        	  min:0,
        	  max:23,
        	  ticks:ticks,
	          tickOptions:{ 
	            angle: 0
	          },
	          tickRenderer:$.jqplot.CanvasAxisTickRenderer,
	          label:'时间段', 
	          labelOptions:{
	            fontFamily:'Helvetica',
	            fontSize: '14pt'
	          },
	          labelRenderer: $.jqplot.CanvasAxisLabelRenderer
          }, 
          yaxis:{//数量
        	min:0,
			renderer:$.jqplot.LogAxisRenderer,
			tickOptions:{
			    labelPosition: 'middle', 
			    angle:0
			},
			tickRenderer:$.jqplot.CanvasAxisTickRenderer,
			labelRenderer: $.jqplot.CanvasAxisLabelRenderer,
			labelOptions:{
			    fontFamily:'Helvetica',
			    fontSize: '14pt'
			},
			label:'排队次数'
          }
        },
        series : [ {
		    label : "排队次数"
	    }],
	    legend:{
			show:true, //设置是否出现分类名称框（即所有分类的名称出现在图的某个位置）
			placement: 'outsideGrid',
			location: 'ne', // 分类名称框出现位置, nw, n, ne, e, se, s, sw, w.
			xoffset: 50, // 分类名称框距图表区域上边框的距离（单位px）
			yoffset: 50 // 分类名称框距图表区域左边框的距离(单位px)
	    }
	});
}

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

function exporting(){
	var beginTime="";
	var endTime="";
	var day=$("#day").val();//时间（天）
	var startHour=$("#startHour").find("option:selected").html();//时间（小时，开始）
	if(startHour=='--'){
		startHour='00';
	}
	var endHour=$("#endHour").find("option:selected").html();//时间（小时，结束）
	if(endHour=='--'){
		endHour='00';
	}
	if(day!=""){
		beginTime = day+" "+startHour+":00:00";
		endTime = day+" "+endHour+":00:00";
	}
	var queueId = $("#queue").find("option:selected").val();//队列
	//alert(beginTime+"  "+endTime+"  "+queueId);
	
	window.location.href=basePath+"logined/report/queueStatisticsExport.jsp?beginTime="+beginTime+"&endTime="+endTime+"&queueId="+queueId;
}