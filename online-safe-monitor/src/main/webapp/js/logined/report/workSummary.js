$(document).ready(function(){
	//获取当前日期以及本月的第一天
	var beginTimeStr = getBeginTime();
	$("#beginTime").val(beginTimeStr);	
	var endTimeStr = getNowDate();
	$("#endTime").val(endTimeStr);
	
	//根据登录用户所属地市
	var loginIsFork = $("#loginIsFork").val();
	if(loginIsFork==2){
		$("#localCity").show();
		$("#isForkGroup").show();
	}else{
		$("#localCity").hide();
		$("#isForkGroup").hide();
	}
	
	getDataTables()
	//加载所属地市
	loadCityName();
	//查询按钮
	$("#search").click(function(){
		getDataTables();
	});
	
	// 回车事件
	document.onkeydown = function(e) {
		// 兼容FF和IE和Opera
		var theEvent = e || window.event;
		var code = theEvent.keyCode || theEvent.which || theEvent.charCode;
		if (code == 13) {
			getDataTables();
		}
	};
	
});
/**
 * 加载所属地市
 */
function loadCityName(){
	$.ajax({
		url:basePath+"report/loadCityName.action",
		type:"post",
		dataType:"text",
		success:function(data){
			var jsonData = eval('('+data+')');
			if(data!=null){
				var selectHtml = "";
				selectHtml += '<option selected="selected" value="-1">全部</option>';
				for(var i=0;i<jsonData.length;i++){
					if(jsonData[i].isForkGroup!=2){
						selectHtml += '<option value="'+jsonData[i].isForkGroup+'">'+jsonData[i].name+'</option>';
					}
				}
				$("#isForkGroup").html(selectHtml);
			}
		}
	});
}
/**
 * 获取列表数据
 */
function getDataTables(){
	$("#timeFrameTable_processing").attr("style","visibility: visible;");
	var isForkGroup = -1;//所属地市,-1代表全部
	var beginTime = $.trim($("#beginTime").val());//开始时间
	if(beginTime!=null&&beginTime!=''){
		beginTime = $.trim($("#beginTime").val())+" 00:00:00";
	}
	var endTime = $.trim($("#endTime").val());//结束时间
	if(endTime!=null&&endTime!=''){
		endTime = $.trim($("#endTime").val())+" 23:59:59";
	}
	var paramData = {
			"wss.isForkGroup":isForkGroup,
			"wss.startTime":beginTime,
			"wss.endTime":endTime
	}
	$.ajax({
		url:basePath+"report/list_workSummaryList.action",
		data:paramData,
		type:"post",
		dataType:"text",
		success:function(data){
			var jsonData = eval('('+data+')');
			var html = "";
			html += '<thead>';
			html += '<tr role="row">';
			html += '<th class="num" rowspan="2">序号</th>';
			html += '<th style="width:130px;" rowspan="2">业务类别</th>';
			html += '<th style="width:200px;" colspan="2">电话咨询</th>';
			html += '<th style="width:200px;" colspan="2">语音留言</th>';
			html += '<th style="width:200px;" colspan="2">短信平台</th>';
			html += '<th style="width:200px;color:blue;" colspan="2">合计</th>';
			html += '</tr>';
			html += '<tr role="row">';
			html += '<th style="width:100px;">数量</th>';
			html += '<th style="width:100px;">百分比</th>';
			html += '<th style="width:100px;">数量</th>';
			html += '<th style="width:100px;">百分比</th>';
			html += '<th style="width:100px;">数量</th>';
			html += '<th style="width:100px;">百分比</th>';
			html += '<th style="width:100px;color:blue;">数量</th>';
			html += '<th style="width:100px;color:blue;">百分比</th>';
			html += '</tr>';
			html += '</thead>';
			if(jsonData.length<=1){
				html += '<tr role="row"><td colspan="10">没有检索到数据</td></tr>';
			}else{
				for(var i=0;i<(jsonData.length);i++){
					if(i==(jsonData.length-1)){
						html += "<tr class='total'>";
						html +="<td style='border-right:none;'></td>";
						html += "<td>总计</td>";
						html += "<td style='border-right:none;'>"+jsonData[i].phoneTotal+"</td>";
						html +="<td></td>";
						html += "<td style='border-right:none;'>"+jsonData[i].soundTotal+"</td>";
						html +="<td></td>";
						html += "<td style='border-right:none;'>"+jsonData[i].messageTotal+"</td>";
						html +="<td></td>";
						html += "<td style='border-right:none;'>"+jsonData[i].allCountTotal+"</td>";
						html +="<td></td>";
						html += "</tr>";
					}else if(i==0 || i%2==0){
						html += "<tr class='odd'>";
						html += "<td>"+jsonData[i].no+"</td>";
						html += "<td>"+jsonData[i].businessName+"</td>";
						html += "<td>"+jsonData[i].phoneNum+"</td>";
						html += "<td>"+jsonData[i].phoneNumPercent+"</td>";
						html += "<td>"+jsonData[i].soundNum+"</td>";
						html += "<td>"+jsonData[i].soundNumPercent+"</td>";
						html += "<td>"+jsonData[i].messageNum+"</td>";
						html += "<td>"+jsonData[i].messageNumPercent+"</td>";
						html += "<td style='color:blue;'>"+jsonData[i].allCount+"</td>";
						html += "<td style='color:blue;'>"+jsonData[i].allCountPercent+"</td>";
						html += "</tr>"
					}else{
						html += "<tr class='even'>"
						html += "<td>"+jsonData[i].no+"</td>";
						html += "<td>"+jsonData[i].businessName+"</td>";
						html += "<td>"+jsonData[i].phoneNum+"</td>";
						html += "<td>"+jsonData[i].phoneNumPercent+"</td>";
						html += "<td>"+jsonData[i].soundNum+"</td>";
						html += "<td>"+jsonData[i].soundNumPercent+"</td>";
						html += "<td>"+jsonData[i].messageNum+"</td>";
						html += "<td>"+jsonData[i].messageNumPercent+"</td>";
						html += "<td style='color:blue;'>"+jsonData[i].allCount+"</td>";
						html += "<td style='color:blue;'>"+jsonData[i].allCountPercent+"</td>";
						html += "</tr>"
					}
				}
			}
			$("#timeFrameTable_processing").attr("style","visibility: hidden;");
			$("#myTable").html(html);
		}
	});
}
/**
 * 导出数据
 */
function exporting(){
	var isForkGroup = -1;//所属地市,-1代表全部
	var beginTime = $.trim($("#beginTime").val());//开始时间
	if(beginTime!=null&&beginTime!=''){
		beginTime = $.trim($("#beginTime").val())+" 00:00:00";
	}
	var endTime = $.trim($("#endTime").val());//结束时间
	if(endTime!=null&&endTime!=''){
		endTime = $.trim($("#endTime").val())+" 23:59:59";
	}
	window.open(basePath +"report/list_exporting.action?wss.isForkGroup="+isForkGroup+"&wss.startTime="+beginTime+"&wss.endTime="+endTime);
}

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