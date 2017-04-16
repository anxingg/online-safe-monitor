$(document).ready(function(){
	
	// 保存按钮绑定事件
	$("#add").bind("click", function() {
		add();
		return false;
	});
	
	$("#add_batch").bind("click", function() {
		batchAdd();
		return false;
	});
	

	//加载列表
	getTable();
	
});



function getTable(){
	var dangerSourceId = $.trim($('#dangerSourceId').val());
	$('#myTable').dataTable({
		"bDestroy" : true,
		"bProcessing" : true,
		"bStateSave" : true, // 状态保存
		'bServerSide' : true,
		'fnServerParams' : function(aoData) {
			aoData.push(
				{
					"name" : "dangerSourceId",
					"value" : dangerSourceId
				});
		},
		"sAjaxSource" : basePath + "collectsensor/collectsensor_getList.action",
		"sPaginationType" : "full_numbers",
		"bPaginate" : true, // 翻页功能
		"bLengthChange" : false, // 改变每页显示数据数量
		"bFilter" : false, // 过滤功能
		"bSort" : false, // 排序功能
		"bInfo" : true,// 页脚信息
		"bAutoWidth" : false,// 自动宽度
		"iDisplayLength" : 15, // 每页显示多少行
		"aoColumns" : [{
			"sTitle" : '名称',
			"mDataProp" : 'sensorName'
		}, {
			"sTitle" : '监测对象',
			"mDataProp" : "dangerSourceObject"
		}, {
			"sTitle" : '检测类型',
			"mDataProp" : 'watchType'
		}, {
			"sTitle" : '阈值设置',
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
				"aTargets" : [5], //操作
				"fnRender" : function(oObj) {
					var vid = oObj.aData.vid;
					var html = '';
					html+= '<a href="'+basePath+'wh/logined/collectsensor/addCollectSensor.jsp?action=edit&vid='+id+'">修改</a>';
					if(oObj.aData.enableWarning=="停用")
						html+='<a href="javascript:void(0);" onclick="setCollectSensorEnable('+vid+',1);">启用</a>';	
					else
						html+='<a href="javascript:void(0);" onclick="setCollectSensorEnable('+vid+',0);">停用</a>';	
					html+='<a href="javascript:void(0);" onclick="deleteCollectSensor('+vid+');">删除</a>';	
					return html;
				}
			}
		]
	});
}

/**
 * 新增操作
 */
function add() {
	window.location.href = basePath + "";
}
function batchAdd() {
	window.location.href = basePath + "";
}
/**
 * 删除操作
 * @param id
 */
function deleteData(vid){
	//确认对话框
	art.dialog.confirm('确定删除该传感器吗？', function() {
		$.ajax({
			url : basePath + "collectsensor/collectsensor_deleteCollectSensor.action",
			type : "post",
			dataType : 'json',
			data : {
				"collectSensorId" : vid
			},
			success : function(data) {
				if (data == 1) {
					//重新加载列表
					getTable();
					//artDialog.alert("删除成功！",function(){						
					//});
				} else if (data == 0){
					artDialog.alert("删除失败！");
				}
			}
			
		});
	}, function() {
		return;
	});
	
}
/**
 * 设置状态操作
 * @param id
 */
function setCollectSensorEnable(vid,enableWarning){
	//确认对话框
	art.dialog.confirm('确定设置吗？', function() {
		$.ajax({
			url : basePath + "collectsensor/collectsensor_setCollectSensorEnable.action",
			type : "post",
			dataType : 'json',
			data : {
				"collectSensorId" : vid,
				"enableWarning" : enableWarning
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


