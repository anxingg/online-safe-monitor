$(document).ready(function(){
	
	// 保存按钮绑定事件
	$("#add").bind("click", function() {
		addTemplate();
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
	//初始化预案类型下拉选
	initSelect("watchType","watchType");
	//加载列表
	getTableList();
	
});

/**
 * 初始化数据字段下拉框
 * @return
 */
function initSelect(typeName,id) {
	var paramData = {
		'infoType' : typeName,
		'grade' : 0
	};
	qytx.app.ajax({
		url : basePath + "dict/setup_getDictsByParentId.action",
		type : "post",
		dataType : "text",
		data : paramData,
		success : function(data) {
			//data是一个数组
			var jsonData = eval('(' + data + ')');
			$("#"+id).empty();
			$("#"+id).append("<option value='-1'>全部</option>");
			for (var i = 0; i < jsonData.length; i++) {
				$("#"+id).append("<option value='"
						+ jsonData[i].value + "'>" + jsonData[i].name
						+ "</option>");
			}
		}
	});
}


function getTableList(){
	var watchType = $.trim($('#watchType').val());
	var keyWord = $.trim($("#keyWord").val());
	
	$('#myTable').dataTable({
		"bDestroy" : true,
		"bProcessing" : true,
		"bStateSave" : true, // 状态保存
		'bServerSide' : true,
		'fnServerParams' : function(aoData) {
			aoData.push(
				{
					"name" : "watchType",
					"value" : watchType
				},
				{
					"name" : "keyWord",
					"value" : keyWord
				});
		},
		"sAjaxSource" : basePath + "thresholdtemplate/thresholdtemplate_getList.action",
		"sPaginationType" : "full_numbers",
		"bPaginate" : true, // 翻页功能
		"bLengthChange" : false, // 改变每页显示数据数量
		"bFilter" : false, // 过滤功能
		"bSort" : false, // 排序功能
		"bInfo" : true,// 页脚信息
		"bAutoWidth" : false,// 自动宽度
		"iDisplayLength" : 15, // 每页显示多少行
		"aoColumns" : [{
			"sTitle" : '模板名称',
			"mDataProp" : 'templateName'
		}, {
			"sTitle" : '监测类型',
			"mDataProp" : "watchType"
		}, {
			"sTitle" : '适用量程',
			"mDataProp" : 'range'
		}, {
			"sTitle" : '阈值设置',
			"mDataProp" : 'level',
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
				"aTargets" : [4], //操作
				"fnRender" : function(oObj) {
					var vid = oObj.aData.vid;
					var html = '';
					html+= '<a href="'+basePath+'wh/logined/thresholdtemplate/updateTemplate.jsp?vid='+vid+'">修改</a>';
					html+='<a href="javascript:void(0);" onclick="deleteTemplate('+vid+');">删除</a>';	
					return html;
				}
			}
		]
	});
}

/**
 * 新增操作
 */
function addTemplate() {
	window.location.href = basePath + "wh/logined/thresholdtemplate/addTemplate.jsp";
}

function deleteTemplate(vid){
	//确认对话框
	art.dialog.confirm('确定删除该模板吗？', function() {
		$.ajax({
			url : basePath + "thresholdtemplate/thresholdtemplate_delete.action",
			type : "post",
			dataType : 'json',
			data : {
				vid : vid
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

