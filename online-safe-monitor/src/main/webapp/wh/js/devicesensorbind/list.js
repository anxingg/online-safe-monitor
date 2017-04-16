$(document).ready(function(){
	
	//加载列表
	getTable();
	
});



function getTable(){
	var collectDeviceId = $.trim($('#collectDeviceId').val());
	$('#myTable').dataTable({
		"bDestroy" : true,
		"bProcessing" : true,
		"bStateSave" : true, // 状态保存
		'bServerSide' : true,
		'fnServerParams' : function(aoData) {
			aoData.push(
				{
					"name" : "collectDeviceId",
					"value" : collectDeviceId
				});
		},
		"sAjaxSource" : basePath + "devicesensorbind/devicesensorbind_getList.action",
		"sPaginationType" : "full_numbers",
		"bPaginate" : true, // 翻页功能
		"bLengthChange" : false, // 改变每页显示数据数量
		"bFilter" : false, // 过滤功能
		"bSort" : false, // 排序功能
		"bInfo" : true,// 页脚信息
		"bAutoWidth" : false,// 自动宽度
		"iDisplayLength" : 15, // 每页显示多少行
		"aoColumns" : [{
			"sTitle" : '通道号',
			"mDataProp" : 'sensorName'
		}, {
			"sTitle" : '危险源名称',
			"mDataProp" : "dangerSourceObject"
		}, {
			"sTitle" : '传感器名称',
			"mDataProp" : 'watchType'
		}, {
			"sTitle" : '模拟量程',
			"mDataProp" : 'setting',
			"sClass" : "longTxt"
		},{
			"sTitle" : '传感器量程',
			"mDataProp" : 'setting',
			"sClass" : "longTxt"
		},{
			"sTitle" : '状态',
			"mDataProp" : 'enableWarning',
			"sClass" : "longTxt"
		}, {
			"sTitle" : '操作',
			"mDataProp" : null
		}],
		"oLanguage" : {
			"sUrl" : basePath + "wh/plugins/datatable/cn.txt" // 中文包
		},
		"fnDrawCallback" : function(oSettings) {
			 $('#myTable tbody  tr td').each(function() {
  				this.setAttribute('title', $(this).text());
  			});
		},
		"fnInitComplete" : function() {
			// 重置iframe高度
//			window.parent.frameResize();
		},
		"aoColumnDefs" : [
			{
				"aTargets" : [6], //操作
				"fnRender" : function(oObj) {
					var vid = oObj.aData.vid;
					var html = '';
					if(oObj.aData.channelStatus=="未预警"){
						html+='<a href="javascript:void(0);" onclick="setChannelStatus('+vid+',0);">解除绑定</a>';
						html+='<a href="javascript:void(0);" onclick="setChannelStatus('+vid+',2);">启用预警</a>';
					}
					else if(oObj.aData.channelStatus=="预警中"){
						html+='<a href="javascript:void(0);" onclick="setChannelStatus('+vid+',0);">解除绑定</a>';
						html+='<a href="javascript:void(0);" onclick="setChannelStatus('+vid+',1);">禁用预警</a>';
					}
					else if(oObj.aData.channelStatus=="未启用"){
						html+='<a href="javascript:void(0);" onclick="openBind('+vid+');">立即绑定</a>';
					}
					return html;
				}
			}
		]
	});
}


/**
 * 设置状态操作
 * @param id
 */
function setChannelStatus(vid,channelStatus){
	//确认对话框
	art.dialog.confirm('确定执行该操作吗？', function() {
		$.ajax({
			url : basePath + "devicesensorbind/devicesensorbind_setChannelStatus.action",
			type : "post",
			dataType : 'json',
			data : {
				"deviceSensorBindId" : vid,
				"channelStatus" : channelStatus
			},
			success : function(data) {
				if (data == 1) {
					//重新加载列表
					getTable();
					//artDialog.alert("删除成功！",function(){						
					//});
				} else if (data == 0){
					artDialog.alert("设置失败！");
				}
			}
			
		});
	}, function() {
		return;
	});
	
}
/**
 * 打开绑定弹出框
 */
function openBind(deviceSensorBindId)
{
	var groupId=$('#groupId').val();
	var collectDeviceId = $('#collectDeviceId').val();
	var url = basePath + "wh/logined/devicesensorbind/bind.jsp?vid="+deviceSensorBindId
	 + "&groupId="+groupId+"&collectDeviceId="+collectDeviceId;
	var title = "通道绑定";
	art.dialog.open(url, {
		title : title,
		width : 360,
		height : 407,
		lock : true,
	    opacity: 0.08,
		button : [{
					name : '确定',
					focus: true,
					callback : function() {	
						var dataMap = art.dialog.data("dataMap");
						bind(dataMap);
						return true;
					}
				}, {
					name : '取消',
					callback : function() {
						return true;
					}
				}]
	}, false);
}
/**
 * 绑定
 */
function bind(dataMap){
	var parmas = {
		"deviceSensorBindId" : dataMap.get("deviceSensorBindId"),
		"collectDeviceId" : dataMap.get("collectDeviceId"),
		"collectSensorId" : dataMap.get("collectSensorId")
	};
	$.ajax({
		url : basePath + "devicesensorbind/devicesensorbind_update.action",
		type : "post",
		data : parmas,
		success : function(data){
			if (data == 1) {
				getTable();
			}else{
				artDialog.alert("提交失败！");
			}
		}
	});
}


