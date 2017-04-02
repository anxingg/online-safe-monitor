$(document).ready(function(){
	
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
		'sysTag' : 0
	};
	qytx.app.ajax({
		url : basePath + "dict/getDicts.action",
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
			"sTitle" : '预案编号',
			"mDataProp" : 'planNo',
			"sClass" : "longTxt"
		}, {
			"sTitle" : '企业名称',
			"mDataProp" : "companyName",
			"sClass" : "longTxt"
		}, {
			"sTitle" : '预案类型',
			"mDataProp" : 'planTypeName'
		}, {
			"sTitle" : '备案时间',
			"mDataProp" : 'prepareTime'
		}, {
			"sTitle" : '备案到期时间',
			"mDataProp" : 'prepareEndTime'
		}, {
			"sTitle" : '应急内容（文件）',
			"sClass" : "longTxt",
			"mDataProp" : null
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
				"aTargets" : [5], //操作
				"fnRender" : function(oObj) {
					var attachmentIds = oObj.aData.attachmentIds;
					var attachName = oObj.aData.attachName;
					var html = '';
					if(attachmentIds == null || attachmentIds == undefined || attachmentIds == '' || attachmentIds == '-'){
						html = '无附件';
					}else {
						html = '<a href="javascript:;" onclick="downloadReport(\''+attachmentIds+'\');">'+attachName+'</a>';
					}
					return html;
					
				}
			},
			{
				"aTargets" : [6], //操作
				"fnRender" : function(oObj) {
					var vid = oObj.aData.vid;
					var whroletype = $("#whroletype").val();
					var html = '<a href="'+basePath+'wh/logined/plans/plansView.jsp?vid='+vid+'">查看</a>';
					if(whroletype==2){//企业端
						html+= '<a href="'+basePath+'wh/logined/plans/updatePlans.jsp?vid='+vid+'">修改</a>';
						html+='<a href="javascript:void(0);" onclick="deletePlans('+vid+');">删除</a>';						
					}
					return html;
				}
			}
		]
	});
}

/**
 * 下载附件
 * @param id
 */
function downloadReport(id){
	var attachmentId = new String(id);
	attachmentId = attachmentId.replace(new RegExp(",","g"),'');
	//alert(attachmentId);
	downLoadAttachment(attachmentId);
}

/**
 * 新增操作
 */
function addPlans() {
	window.location.href = basePath + "wh/logined/plans/addPlans.jsp";
}

function deletePlans(vid){
	//确认对话框
	art.dialog.confirm('确定删除该应急预案吗？', function() {
		$.ajax({
			url : basePath + "plans/plans_delete.action",
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

