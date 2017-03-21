jQuery(document).ready(function(){
	$("#beginTime").val( $("#beginTimeStr").val());
	$("#endTime").val( $("#endTimeStr").val());
	$("#createPlot").click(function(){
		getTableData();
	});
	//返回列表按钮点击
	$("#backList").click(function(){
		var url = "logined/report/telephonetraffic.jsp?beginTime="+ $("#beginTime").val()+"&endTime="+$("#endTime").val();
		window.location.href(basePath + url);
	});
});

function getTableData() {
	var checkStr = "";
	var obj = $("[name=showData]:checked");
	if(obj.length == 0){
		art.dialog.alert("请选择您要显示的数据");
		return;
	}
	for(var i= 0;i<obj.length;i++){
		checkStr += obj.eq(i).val();
	}
	
	
	// 比较开始和结束日期
	var beginTimeStr = $("#beginTime").val();
	if ("" == beginTimeStr){
		art.dialog.alert("起始日期不能为空。");
		return;
	}
	
	beginTimeStr = beginTimeStr.replace(/-/g,"/");
	var beginTime = new Date(beginTimeStr);
	
	var endTimeStr = $("#endTime").val();
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
	    'beginTime' : $("#beginTime").val(),
	    'endTime' : $("#endTime").val(),
	};
	$.ajax({
	            url : basePath + "report/reportTelephonetraffic_getCallQuantity.action",
	            type : "post",
	            dataType : "json",
	            data : paramData,
	            success : function(data) {
	            	//第一个图标数据对象
	            	var plotArr = new Array();
	            	//第二个图标数据对象
	            	var plotArr2 = new Array();
	            	//第一个图标数据对象中数据
	            	var value = new Array();
	            	//第二个图标数据对象中数据
	            	var value2 = new Array();
	            	//第一个图标数据对象中数据对应字段名称
	            	var name = new Array();
	            	//第二个图标数据对象中数据对应字段名称
	            	var name2 = new Array();
	            	
	            	var showWhich = false;
		            var showWhich2 = false;
	            	
	            	var line1_data1 = new Array();
		            var line1_data2 = new Array();
		            var line1_data3 = new Array();
		            var line1_data4 = new Array();
		            var line1_data5 = new Array();
		            var line1_data6 = new Array();
		            var line1_data7 = new Array();
		            var line1_data8 = new Array();
		            var line1_data9 = new Array();
		            var ticks = new Array();
		            if (null != data && data != "") {
			            for ( var i = 0; i < data.length; i++) {
			            	//去除总计
				            if (i != data.length - 1) {
				            	if(checkStr.indexOf("1") >= 0){
				            		line1_data1.push(data[i].callInNum);
				            	}
				            	if(checkStr.indexOf("2") >= 0){
				            		line1_data2.push(data[i].callOutNum);
				            	}
				            	if(checkStr.indexOf("3") >= 0){
				            		line1_data3.push(data[i].totleCallNum);
				            	}
				            	if(checkStr.indexOf("4") >= 0){
				            		line1_data4.push(data[i].totleCallLoss);
				            	}
				            	if(checkStr.indexOf("5") >= 0){
				            		line1_data5.push(data[i].totleCallInTime);
				            	}
				            	if(checkStr.indexOf("6") >= 0){
				            		line1_data6.push(data[i].totleCallOutTime);
				            	}
				            	if(checkStr.indexOf("7") >= 0){
				            		line1_data7.push(data[i].totleLoginTime);
				            	}
				            	if(checkStr.indexOf("8") >= 0){
				            		line1_data8.push(data[i].totleBosyTime);
				            	}
				            	if(checkStr.indexOf("9") >= 0){
				            		line1_data9.push(data[i].totleNoBosyTime);
				            	}
				            	ticks.push(data[i].callDate);
				            } 
			            }
			            
			            if(checkStr.indexOf("1") >= 0){
			            	value.push(line1_data1);
		            		name.push("呼入量");
		            		showWhich = true;
		            	}
		            	if(checkStr.indexOf("2") >= 0){
		            		value.push(line1_data2);
		            		name.push("呼出量");
		            		showWhich = true;
		            	}
		            	if(checkStr.indexOf("3") >= 0){
		            		value.push(line1_data3);
		            		name.push("总话务量");
		            		showWhich = true;
		            	}
		            	if(checkStr.indexOf("4") >= 0){
		            		value.push(line1_data4);
		            		name.push("呼损总量");
		            		showWhich = true;
		            	}
		            	if(checkStr.indexOf("5") >= 0){
		            		value2.push(line1_data5);
		            		name2.push("呼入通话总时长");
		            		showWhich2 = true;
		            	}
		            	if(checkStr.indexOf("6") >= 0){
		            		value2.push(line1_data6);
		            		name2.push("呼出通话总时长");
		            		showWhich2 = true;
		            	}
		            	if(checkStr.indexOf("7") >= 0){
		            		value2.push(line1_data7);
		            		name2.push("登录总时长");
		            		showWhich2 = true;
		            	}
		            	if(checkStr.indexOf("8") >= 0){
		            		value2.push(line1_data8);
		            		name2.push("置忙总时长");
		            		showWhich2 = true;
		            	}
		            	if(checkStr.indexOf("9") >= 0){
		            		value2.push(line1_data9);
		            		name2.push("空闲总时长");
		            		showWhich2 = true;
		            	}
			            //收集9个字段的值
			            plotArr.push(value);
			            plotArr2.push(value2);
			            //横轴值
			            plotArr.push(ticks);
			            plotArr2.push(ticks);
			            //字段名称
			            plotArr.push(name);
			            plotArr2.push(name2);
			            
		            }
//		            alert(JSON.stringify(plotArr));
		            //量的显示
		            if(showWhich){
		            	$("#chartDiv").show();
		            	drawGraph(plotArr,"1");
		            }else{
		            	$("#chartDiv").hide();
		            }
		            //时长的显示
		            if(showWhich2){
		            	$("#chartDiv2").show();
		            	drawGraph(plotArr2,"2");
		            }else{
		            	$("#chartDiv2").hide();
		            }
	            }
	        });
}

//图表显示
function drawGraph(data,showPlace){
	var plotType = $("[name='plotType']:checked").val();
	var bajp = new BasicAutoJqPlot();
	bajp.plotType = plotType;
	bajp.basicArray = data;
	bajp.xlabel = '日期';
	bajp.ylabel = '数量';
	bajp.title = '综合统计表';//图表的名称
	if(showPlace == "1"){
		bajp.divId = 'chart1';
	}
	if(showPlace == "2"){
		bajp.divId = 'chart2';
	}
	bajp.basicAutoShow();
}








