$(document).ready(function(){
	//初始化所属地市
	getGroupName();
	//初始化时间
	$("#beginTime").val(getBeginTime());
	$("#endTime").val(getNowDate());
	
	//查询按钮
	$("#search").click(function(){
		getData();
	});
	
	//导出
	$("#areaTableExport").click(function(){
		areaTableExport();
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
 * 获取列表数据
 */
function getData(){
	$("#areaTable_processing").attr("style","visibility: visible;");
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
	var groupId=$.trim($("#groupId").val());
	var beginTimeStr = "";
	var endTimeStr = "";
	if(beginTime != null && beginTime != ""){
		beginTimeStr = beginTime +" 00:00:00";
	}
	if(endTime != null && endTime != ""){
		endTimeStr = endTime +" 23:59:59";
	}
//	alert(beginTimeStr+"<>"+endTimeStr+"<>"+groupId);
	//坐席所属部门
	var paramData = {
		"beginTimeStr" : beginTimeStr,
		"endTimeStr" : endTimeStr,
		"groupId" :groupId
	};
	// 发送ajax请求,获取对应的人员姓名
	$.ajax({
		url : basePath + 'report/reportAreaMisCallLog_findMisCallLogByArea.action',
		type : 'post',
		dataType : 'text',
		data : paramData,
		success : function(data) {
			$("#zzjz").remove();
			if(data!=null){
	            var body = '';
				var list=eval('('+data+')'); //jsonObject是一个数组对象
				var length=list.length;
				for(i=0;i<length;i++){
					var map = list[i];
					if(i==(length-1)){
						body += '<tr class="total"><td colspan="2">'+ map.area +'</td><td class="data_r">'+map.callInNum+'</td><td class="data_r">'+map.acceptNum+'</td>'+
						'<td class="data_r">'+map.giveUpNum+'</td><td class="data_r">'+map.autoQueryNum+'</td></tr>';
					}else{
						if(i%2==0){
							body += '<tr class="odd"><td>' + (i+1) + '</td><td>' + map.area + '</td><td class="data_r">' + 
							map.callInNum + '</td><td class="data_r">' + map.acceptNum + '</td><td class="data_r">' + map.giveUpNum + '</td><td class="data_r">' + map.autoQueryNum+ '</td></tr>';
						}else{
							body += '<tr class="even"><td>' + (i+1) + '</td><td>' + map.area + '</td><td class="data_r">' + 
							map.callInNum + '</td><td class="data_r">' + map.acceptNum + '</td><td class="data_r">' + map.giveUpNum + '</td><td class="data_r">' + map.autoQueryNum+ '</td></tr>';
						}
					}
				}
				
				$("#areaTable_body").html( body);
				$("tr").remove("#zzjz");
			}else{
				body += '<tr class="odd" id="a"><td style="text-align:center;" colspan="6">没有检索到数据</td></tr>';
				$("#areaTable_body").html(body);
				$("tr").remove("#zzjz");
			}
			$("#areaTable_processing").attr("style","visibility: hidden;");

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
					for( i = 0; i < list.length;i++){
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
			getData();
		}
	});
}


//导出
function areaTableExport(){
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
//	alert(beginTimeStr+"<>"+endTimeStr+"<>"+groupId);
	var url = "report/reportAreaMisCallLog_exportMisCallLogByArea.action";
	url += "?beginTimeStr="+beginTimeStr;
	url += "&endTimeStr="+endTimeStr;
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