$(document).ready(function(){
	
	// 保存按钮绑定事件
	$("#add").bind("click", function() {
		add();
		return false;
	});
	
	// 保存按钮绑定事件
	$("#search").bind("click", function() {
		getTableList();
		return false;
	});
	
	//回车事件
	$(document).keydown(function(event){
		var code=event.which;
		if (code == 13) {
			getTableList();
			return false;
		}
	});

	//加载列表
	getTableList();
	
});

function getTableList(){
	var keyWord = $.trim($("#keyWord").val());
	
	$('#myTable').dataTable({
		"bDestroy" : true,
		"bProcessing" : true,
		"bStateSave" : true, // 状态保存
		'bServerSide' : true,
		'fnServerParams' : function(aoData) {
			aoData.push(
				{
					"name" : "searchKey",
					"value" : keyWord
				});
		},
		"sAjaxSource" : basePath + "collectdevice/collectdevice_getList.action",
		"sPaginationType" : "full_numbers",
		"bPaginate" : true, // 翻页功能
		"bLengthChange" : false, // 改变每页显示数据数量
		"bFilter" : false, // 过滤功能
		"bSort" : false, // 排序功能
		"bInfo" : true,// 页脚信息
		"bAutoWidth" : false,// 自动宽度
		"iDisplayLength" : 15, // 每页显示多少行
		"aoColumns" : [{
			"sTitle" : '企业',
			"mDataProp" : 'companyName'
		}, {
			"sTitle" : '安装位置',
			"mDataProp" : "installPosition"
		}, {
			"sTitle" : '型号',
			"mDataProp" : 'deviceModel'
		}, {
			"sTitle" : '通道数',
			"mDataProp" : 'channelCount',
			"sClass" : "longTxt"
		}, {
			"sTitle" : '设备地址',
			"mDataProp" : 'deviceAddress',
			"sClass" : "longTxt"
		}, {
			"sTitle" : '状态',
			"mDataProp" : 'deviceStatus',
			"sClass" : "longTxt"
		},{
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
					html+= '<a href="'+basePath+'wh/logined/collectdevice/add.jsp?action=edit&vid='+vid+'">修改</a>';
					if(oObj.aData.deviceStatus=="停用")
						html+='<a href="javascript:void(0);" onclick="setStatus('+vid+',1);">启用</a>';	
					else if(oObj.aData.deviceStatus=="启用")
						html+='<a href="javascript:void(0);" onclick="setStatus('+vid+',0);">停用</a>';	
					else if(oObj.aData.deviceStatus=="维修中")
						html+='<a href="javascript:void(0);" onclick="setStatus('+vid+',1);">恢复</a>';	
					else if(oObj.aData.deviceStatus=="网络中断")
						html+='<a href="javascript:void(0);" onclick="setStatus('+vid+',2);">维修</a>';
					html+='<a href="javascript:void(0);" onclick="deleteCollectDevice('+vid+');">删除</a>';	
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
	window.location.href = basePath + "wh/logined/collectdevice/add.jsp?action=add";
}

function deleteCollectDevice(vid){
	//确认对话框
	art.dialog.confirm('确定删除该设备吗？', function() {
		$.ajax({
			url : basePath + "collectdevice/collectdevice_delete.action",
			type : "post",
			dataType : 'json',
			data : {
				collectDeviceId : vid
			},
			success : function(data) {
				if (data == 1) {
					//artDialog.alert("删除成功！",function(){						
						//重新加载列表
						getTableList();
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
function setStatus(vid,status){
	//确认对话框
	art.dialog.confirm('确定设置吗？', function() {
		$.ajax({
			url : basePath + "collectdevice/collectdevice_setCollectDeviceStatus.action",
			type : "post",
			dataType : 'json',
			data : {
				"collectDeviceId" : vid,
				"deviceStatus" : enableWarning
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
