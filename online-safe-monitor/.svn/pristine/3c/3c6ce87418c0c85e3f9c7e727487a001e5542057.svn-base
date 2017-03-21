$(document).ready(function(){
	//初始化所属地市
	getGroupName();
	
	//初始化时间
	var hiddenBeginTime = $("#hiddenBeginTime").val();
	var hiddenEndTime = $("#hiddenEndTime").val();
	if( hiddenBeginTime != '' ){
		$("#beginTime").val(hiddenBeginTime);
	}else{
		$("#beginTime").val(getBeginTime());
	}
	if( hiddenEndTime != '' ){
		$("#endTime").val(hiddenEndTime);
	}else{
		$("#endTime").val(getNowDate());
	}
	
	//查询按钮
	$("#search").click(function(){
		getData();
	});
	
	//导出
	$("#timeFrameExport").click(function(){
		timeFrameExport();
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
});

/**
 * 跳转到图表页面
 */
function toTimeFrameGraph(){
	var beginTime=$.trim($("#beginTime").val());
	var endTime=$.trim($("#endTime").val());
	window.location.href = basePath + 'logined/report/timeFrameGraph.jsp?beginTime='+beginTime + '&endTime=' + endTime;
}


/**
 * 获取列表数据
 */
function getData(){
	$("#timeFrameTable_processing").attr("style","visibility: visible;");
//	var head = '<thead><tr><th style="width:25px">序号</th><th style="width:175px">时段</th>';
//	head += '<th style="width:175px">呼入总量</th>';
//		head += '<th style="width:175px">人工受理</th>';
//			head += '<th style="width:175px">放弃</th>';
//				head += '<th style="width:175px">自动查询</th></tr>';
//	head+='</thead>';
//	head += '<tr class="odd" id="zzjz"><td style="text-align:center;" colspan="6">正在加载数据...</td></tr>'
//	$("#timeFrameTable").html(head);
	var beginTime=$.trim($("#beginTime").val());
	var endTime=$.trim($("#endTime").val());
	var groupId=$.trim($("#isForkGroup").val());
	var beginTimeStr = "";
	var endTimeStr = "";
	if(beginTime != null && beginTime != ""){
		beginTimeStr = beginTime +" 00:00:00"
	}
	if(endTime != null && endTime != ""){
		endTimeStr = endTime +" 23:59:59"
	}
	//坐席所属部门
	var paramData = {
		"beginTime" : beginTimeStr,
		"endTime" : endTimeStr,
		"groupId" :groupId
	};
	// 发送ajax请求,获取对应的人员姓名
	$.ajax({
		url : basePath + 'report/reportTimeFrame_getTimeFrame.action',
		type : 'post',
		dataType : 'text',
		data : paramData,
		success : function(data) {
			var sumCallIn = 0;//呼入总量 总计
			var sumAccepted = 0;//人工受理总计
			var sumSurreder = 0;//放弃 总计
			var sumAutoCheck = 0;//自动查询 总计
			$("#zzjz").remove();
			if(data!='null'){
	            var body = '';
				var list=eval('('+data+')'); //jsonObject是一个数组对象
				var length=list.length;
				for(var i=0;i<length;i++){
					var map = list[i];
					if(i%2==0){
						body += '<tr class="odd"><td>' + map.num + '</td><td>' + map.no + '</td><td class="data_r">' + 
						map.callInNum + '</td><td class="data_r">' + map.acceptedNum + '</td><td class="data_r">' + map.surrenderNum + '</td><td class="data_r">' + map.autoCheck+ '</td></tr>';
						sumCallIn += map.callInNum; 
						sumAccepted += map.acceptedNum;
						sumSurreder += map.surrenderNum;
						sumAutoCheck += map.autoCheck;
						
					}else{
						body += '<tr class="even"><td>' + map.num + '</td><td>' + map.no + '</td><td class="data_r">' + 
						map.callInNum + '</td><td class="data_r">' + map.acceptedNum + '</td><td class="data_r">' + map.surrenderNum + '</td><td class="data_r">' + map.autoCheck+ '</td></tr>';
						sumCallIn += map.callInNum; 
						sumAccepted += map.acceptedNum;
						sumSurreder += map.surrenderNum;
						sumAutoCheck += map.autoCheck;
					}
				}
				body += '<tr class="total"><td colspan="2">总计</td><td class="data_r">'+sumCallIn+'</td><td class="data_r">'+sumAccepted+'</td>'+
					'<td class="data_r">'+sumSurreder+'</td><td class="data_r">'+sumAutoCheck+'</td></tr>';
				$("#timeFrameTable_body").html( body);
				$("tr").remove("#zzjz");
			}else{
				var body = '';
				body += '<tr class="odd" id="a"><td style="text-align:center;" colspan="6">没有检索到数据</td></tr>';
				$("#timeFrameTable_body").html(body);
				$("tr").remove("#zzjz");
			}
			$("#timeFrameTable_processing").attr("style","visibility: hidden;");

		}
	}); 
}
function getGroupName(){
	$.ajax({
		url : basePath + "statistics/getGroupName.action",
		type : "post",
		dataType : "json",
		success :function (data){
			var isForkGroup = data.isForkGroup; //权限
			var list = data.list;//
			if(isForkGroup != null){
				if(isForkGroup == 2){
					var option = "<option id=\"2\" value=\"\" onclick=getGroupId('')>全部</option>";;
					for(var i = 0; i < list.length;i++){
						if(list[i] != null && list[i].groupId != 2){
							var id = list[i].groupId;
							var name = list[i].groupName;
							option += "<option id="+id+" value="+id+" onclick=getGroupId("+id+")>"+name+"</option>";
						}
					}
					$("#groupId").html(option);
				}else{
					$("#showDS").hide();
					$("#groupId").hide();
				}
			}
			//初始化查询全部
			getGroupId('');
			getData();
		}
	});
}

//获得isForkGroup地市
function getGroupId(id){
	$("#isForkGroup").val(id);
}

//导出
function timeFrameExport(){
	var beginTime=$.trim($("#beginTime").val());
	var endTime=$.trim($("#endTime").val());
	var groupId=$.trim($("#groupId").val());
	var beginTimeStr = "";
	var endTimeStr = "";
	if(beginTime != null && beginTime != ""){
		beginTimeStr = beginTime +" 00:00:00";
	}
	if(endTime != null && endTime != ""){
		endTimeStr = endTime +" 23:59:59";
	}
	var url = "report/reportTimeFrame_exportTimeFrame.action";
	url += "?beginTime="+beginTimeStr;
	url += "&endTime="+endTimeStr;
	url += "&groupId="+groupId;
	window.open(basePath + url);
	return false;
}

//时间编辑
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