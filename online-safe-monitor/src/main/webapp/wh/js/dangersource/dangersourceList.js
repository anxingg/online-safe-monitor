$(document).ready(function(){
	
	// 新增按钮绑定事件
	$("#add").bind("click", function() {
		addData();
		return false;
	});
	
	// 查询按钮绑定事件
	$("#search").bind("click", function() {
		getTableList();
		return false;
	});
	
	// 回车事件
	$(document).keydown(function(event){
		var code=event.which;
		if (code == 13) {
			getTableList();
			return false;
		}
	});

	//initSelect("watchType","watchType");
	//加载列表
	getTableList();
	
});

/**
 * 初始化数据字段下拉框(县区)
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


/**
 * 查询列表
 */
function getTableList(){
	var keyword = $.trim($("#keyword").val());
	
	$('#myTable').dataTable({
		"bDestroy" : true,
		"bProcessing" : true,
		"bStateSave" : true, // 状态保存
		'bServerSide' : true,
		'fnServerParams' : function(aoData) {
			aoData.push(
				{
					"name" : "dsq.keyword",
					"value" : keyword
				});
		},
		"sAjaxSource" : basePath + "dangersource/findByPage.action",
		"sPaginationType" : "full_numbers",
		"bPaginate" : true, // 翻页功能
		"bLengthChange" : false, // 改变每页显示数据数量
		"bFilter" : false, // 过滤功能
		"bSort" : false, // 排序功能
		"bInfo" : true,// 页脚信息
		"bAutoWidth" : false,// 自动宽度
		"iDisplayLength" : 15, // 每页显示多少行
		"aoColumns" : [{
			"sTitle" : '危险源名称',
			"mDataProp" : 'dangerSourceName'
		}, {
			"sTitle" : '县区',
			"mDataProp" : "regionName"
		}, {
			"sTitle" : '所属企业',
			"mDataProp" : 'companyName'
		}, {
			"sTitle" : '危险源级别',
			"mDataProp" : 'dangerLevelStr'
		}, {
			"sTitle" : '评价机构',
			"mDataProp" : 'evaluteOrg',
			"sClass" : "longTxt"
		}, {
			"sTitle" : '最新评价时间',
			"mDataProp" : null
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
				"aTargets" : [5], //最新评价时间
				"fnRender" : function(oObj) {
					var evaluteTime = oObj.aData.evaluteTime;
					var Overdue = oObj.aData.Overdue;
					if(Overdue == 1){
						evaluteTime += '(到期)';
					}	
					return evaluteTime;
				}
			}, {
				"aTargets" : [6], //操作
				"fnRender" : function(oObj) {
					var id = oObj.aData.id;
					var html = '';
					html+= '<a href="'+basePath+'dangersource/toUpdatePage.action?dsq.id='+id+'">修改</a>';
					html+='<a href="javascript:void(0);" onclick="deleteData('+id+');">删除</a>';	
					return html;
				}
			}
		]
	});
}

/**
 * 新增操作
 */
function addData() {
	window.location.href = basePath + "dangersource/toAddPage.action";
}

/**
 * 删除操作
 * @param id
 */
function deleteData(id){
	//确认对话框
	art.dialog.confirm('确定删除该危险源吗？', function() {
		$.ajax({
			url : basePath + "dangersource/toUpdatePage.action",
			type : "post",
			dataType : 'json',
			data : {
				"dsq.id" : id
			},
			success : function(data) {
				if (data == 1) {
					//重新加载列表
					getTableList();
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

