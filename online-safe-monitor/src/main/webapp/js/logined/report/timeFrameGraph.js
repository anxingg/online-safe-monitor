
/**
 * 页面加载完毕的方法
 */
$(document).ready(function(){
	//初始化时间
	var beginTime = $("#beginTime").val();
	var endTime = $("#endTime").val();
	if( beginTime != '' ){
		$("#beginTimeStr").val(beginTime);
	}
	if( endTime != '' ){
		$("#endTimeStr").val(endTime);
	}
	
});


/**
 * 生成图表的方法
 */
function generateGraph(){
	
	var cks = $(":checkbox[name='colName']:checked");
	if( cks.length == 0 ){
		art.dialog.alert("请至少选择一项显示数据！");
		return;
	}
	
	var beginTimeStr = $("#beginTimeStr").val();
	if( beginTimeStr != null ){
		beginTimeStr += ' 00:00:00';
	}
	
	var endTimeStr = $("#endTimeStr").val();
	if( endTimeStr != null ){
		endTimeStr += ' 23:59:59';
	}
	
	
	//{"呼入总量":0,"人工受理":0, "放弃":0 ,"自动查询":0}
	var colNames = '';
	for( i = 0; i < cks.length; i++ ){
		colNames += cks.eq(i).val();
		colNames += ',';
	}
	var paramData = {
		"showCols" : colNames,
		"beginTime" : beginTimeStr,
		"endTime" : endTimeStr
	};
	// 发送ajax请求,获取对应的人员姓名
	$.ajax({
		url : basePath + 'report/reportTimeFrame_getTimeFrameForPlot.action',
		type : 'post',
		dataType : 'text',
		data : paramData,
		success : function(data) {
			var data = eval( '(' + data + ')' );
			drawGraph(data);
		}
	}); 
}

/**
 * 用jqplot画图表
 * @param data javascript对象 格式：[[数据数组],[X轴刻度数组],[图例数组]] ，其中数据数组可以是单一的一个数组也可以是数组的数组。
 */
function drawGraph(data){
	var plotType = $(":radio[name='plotType']:checked").val();//显示成什么类型的图表
	
	var bajp = new BasicAutoJqPlot();
	bajp.plotType = plotType;//0表示柱状图，1表示折线图，2表示饼状图（2还没有实现）
	bajp.basicArray = data;//基础数据（包括三种数组）
	bajp.divId = 'chart';//显示区域DIV的ID
	bajp.xlabel = '时间';//X轴的名称
	bajp.ylabel = '数量';//Y轴的名称
	bajp.title = '时段分布图';//图表的名称
	
	bajp.basicAutoShow();//调用画图的方法
}

/**
 * 返回列表
 * 功能：要把图表页面中的查询时间传回到列表页面
 */
function toListTable(){
	var beginTime = $("#beginTimeStr").val();
	var endTime = $("#endTimeStr").val();
	window.location.href = basePath + 'logined/report/timeFrame.jsp?hiddenBeginTime='+beginTime + '&hiddenEndTime=' + endTime;
}