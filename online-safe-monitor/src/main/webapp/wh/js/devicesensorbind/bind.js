$(document).ready(function(){
	var vid=$('#vid').val();
	initInfo(vid);
	var groupId=$('#groupId').val();
	initDangerSource(groupId);
	//危险源下拉框改变
	$("#dangerSourceId").change(function(){
		var dangerSourceId = $.trim($("#dangerSourceId").val());
		if(dangerSourceId>0)
			initCollectSensor(dangerSourceId);
	});
	//传感器下拉框改变
	$("#collectSensorId").change(function(){
		var collectSensorId = $.trim($("#collectSensorId").val());
		if(collectSensorId>0){
			initCollectSensorInfo(collectSensorId);
			dataMap.set("collectDeviceId",$('#collectDeviceId').val());
			dataMap.set("collectSensorId",$('#collectSensorId').val());
			art.dialog.data("dataMap", dataMap);
		}
	});
});

/**
 * 初始化信息
 * @param groupId
 */
function initInfo(vid)
{
	var param={};
	param={"deviceSensorBindId":vid};
	url=basePath + "devicesensorbind/getInfo.action";
	$.ajax({
		url : url,
		type : 'post',
		dataType : 'json',
		data : param,
		success : function(data) {
			if(data){
				$('#channelName').val(data.channelName);
			}
		}
	});
}

/**
 * 初始化危险源下拉框
 * @param groupId
 */
function initDangerSource(groupId)
{
	var param={};
	param={"groupId":groupId};
	url=basePath + "dangersource/getSelectList.action";
	$.ajax({
		url : url,
		type : 'post',
		dataType : 'json',
		data : param,
		success : function(data) {
			if(data){
				var html = "";
				for(var i=0;i<data.length;i++){
					if(parentId!=-1){
						if(i==0){
							html +="<option value='"+data[0].value+"' selected = 'selected'>"+data[0].name+"</option>";
						}else{
							html +="<option value='"+data[i].value+"'>"+data[i].name+"</option>";
						}
					}else{
						html +="<option value='"+data[i].value+"'>"+data[i].name+"</option>";
					}
				}
				
				$("#dangerSourceId").html(html);
				
			}
			
		}
	});
}

/**
 * 初始化传感器下拉框
 * @param groupId
 */
function initCollectSensor(dangerSourceId)
{
	var param={};
	param={"dangerSourceId":dangerSourceId};
	url=basePath + "devicesensorbind/getCollectSensorSelect.action";
	$.ajax({
		url : url,
		type : 'post',
		dataType : 'json',
		data : param,
		success : function(data) {
			if(data){
				var html = "";
				for(var i=0;i<data.length;i++){
					if(parentId!=-1){
						if(i==0){
							html +="<option value='"+data[0].value+"' selected = 'selected'>"+data[0].name+"</option>";
						}else{
							html +="<option value='"+data[i].value+"'>"+data[i].name+"</option>";
						}
					}else{
						html +="<option value='"+data[i].value+"'>"+data[i].name+"</option>";
					}
				}
				
				$("#collectSensorId").html(html);
				
			}
			
		}
	});
}
/**
 * 初始化传感器信息
 * @param groupId
 */
function initCollectSensorInfo(collectSensorId)
{
	var param={};
	param={"collectSensorId":collectSensorId};
	url=basePath + "collectsensor/getInfo.action";
	$.ajax({
		url : url,
		type : 'post',
		dataType : 'json',
		data : param,
		success : function(data) {
			if(data){
				var range=data.rangeLow+"~"+data.rangeHigh;
				var alogRange=data.alogRangeLow+"~"+data.alogRangeHith
				$('#range').val(range);
				$('#alogRange').val(alogRange);				
			}
			
		}
	});
}
