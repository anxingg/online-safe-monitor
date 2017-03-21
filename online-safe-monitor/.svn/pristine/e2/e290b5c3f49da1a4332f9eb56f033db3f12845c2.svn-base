$(document).ready(function(){
	setCompany();
	var whroletype = $("#whroletype").val();
	if(whroletype==1 || whroletype==3){
		getTrainingListZF();
	}else{
		getTrainingList();
	}
		// 保存按钮绑定事件
		$("#search").bind("click", function() {
			var whroletype = $("#whroletype").val();
			if(whroletype==1 || whroletype==3){
				getTrainingListZF();
			}else{
				getTrainingList();
			}
			return false;
		});
		//回车事件
		$(document).keydown(function(event){
			var code=event.which;
			if (code == 13) {
				var whroletype = $("#whroletype").val();
				if(whroletype==1 || whroletype==3){
					getTrainingListZF();
				}else{
					getTrainingList();
				}
				return false;
			}
		});
});

/**
 * 初始化公司数据
 * 
 * @return
 */
function setCompany() {
	qytx.app.ajax({
				url : basePath + "companywh/getCompanyNameList.action",
				type : "post",
				dataType : "html",
				success : function(data) {
				var	jsonData = eval("(" + data + ")");
					$("#company").empty();
					$("#company").append("<option value='' seleted>请选择</option>");
					for (var i = 0; i < jsonData.length; i++) {
						$("#company").append("<option value='"
								+ jsonData[i].groupId + "'>" + jsonData[i].companyName
								+ "</option>");
					}
				}
			});
}

function getTrainingList() {
	var details = $.trim( $("#details").val() );
	var speaker = $.trim( $("#speaker").val() );
	var trainTime = $.trim( $("#trainTime").val() );
	$('#myTable').dataTable({
		"bDestroy" : true,
		"bProcessing" : true,
		"bStateSave" : true, // 状态保存
		'bServerSide' : true,
		'fnServerParams' : function(aoData) {

			aoData.push({
					"name" : "search.trainingContext",
					"value" : details
				}, {
					"name" : "search.speaker",
					"value" : speaker
				},{
					"name" : "search.trainingTime",
					"value" : trainTime
				},{
					"name" : "search.trainingType",
					"value" : 1
				});
		},
		"sAjaxSource" : basePath + "training/listTraining.action",
		"sPaginationType" : "full_numbers",
		"bPaginate" : true, // 翻页功能
		"bLengthChange" : false, // 改变每页显示数据数量
		"bFilter" : false, // 过滤功能
		"bSort" : false, // 排序功能
		"bInfo" : true,// 页脚信息
		"bAutoWidth" : false,// 自动宽度
		"iDisplayLength" : 15, // 每页显示多少行
		"aoColumns" : [{
			"sTitle" : '序号',
			"mDataProp" : 'no'
		}, {
			"sTitle" : '培训名称',
			"mDataProp" : "subject",
			"sClass" : "longTxt"
		}, {
			"sTitle" : '培训对象 ',
			"mDataProp" : 'trainObject',
			"sClass" : "longTxt"
				
		}, {
			"sTitle" : '培训时间',
			"mDataProp" : 'trainTime',
		}, {
			"sTitle" : '主讲人',
			"mDataProp" : 'speaker',
			"sClass" : "longTxt"
		},{
			"sTitle" : '培训内容',
			"mDataProp" : 'details',
			"sClass" : "longTxt"
		}, {
			"sTitle" : '效果评价和改进措施',
			"mDataProp" : 'effect',
			"sClass" : "longTxt"
		},{
			"sTitle" : '培训地点',
			"mDataProp" : 'address',
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
				"aTargets" : [8], //操作
				"fnRender" : function(oObj) {
					var trainingId = oObj.aData.id;
					var html = '<a href="viewTrainingRecord.jsp?trainingId='+trainingId+'">查看</a>';
					 html += '<a href="updateTrainingRecord.jsp?trainingId='+trainingId+'">修改</a>';
					 html += '<a href="javascript:void(0);" onclick="deleteTrainingRecord('+trainingId+');">删除</a>';
					return html;
				}
			}
		]
	});
}


function getTrainingListZF() {
	var details = $.trim( $("#details").val() );
	var speaker = $.trim( $("#speaker").val() );
	var trainTime = $.trim( $("#trainTime").val() );
	$('#myTable').dataTable({
		"bDestroy" : true,
		"bProcessing" : true,
		"bStateSave" : true, // 状态保存
		'bServerSide' : true,
		'fnServerParams' : function(aoData) {

			aoData.push({
					"name" : "search.trainingContext",
					"value" : details
				}, {
					"name" : "search.speaker",
					"value" : speaker
				},{
					"name" : "search.trainingTime",
					"value" : trainTime
				},{
					"name" : "search.trainingType",
					"value" : 1
				},{
					"name" : "search.groupId",
					"value" : $("#company").val()
				});
		},
		"sAjaxSource" : basePath + "training/listTraining.action",
		"sPaginationType" : "full_numbers",
		"bPaginate" : true, // 翻页功能
		"bLengthChange" : false, // 改变每页显示数据数量
		"bFilter" : false, // 过滤功能
		"bSort" : false, // 排序功能
		"bInfo" : true,// 页脚信息
		"bAutoWidth" : false,// 自动宽度
		"iDisplayLength" : 15, // 每页显示多少行
		"aoColumns" : [{
			"sTitle" : '序号',
			"mDataProp" : 'no'
		},  {
			"sTitle" : '企业名称',
			"mDataProp" : "companyName",
			"sClass" : "longTxt"
		},{
			"sTitle" : '培训名称',
			"mDataProp" : "subject",
			"sClass" : "longTxt"
		}, {
			"sTitle" : '培训对象 ',
			"mDataProp" : 'trainObject',
			"sClass" : "longTxt"
				
		}, {
			"sTitle" : '培训时间',
			"mDataProp" : 'trainTime',
		}, {
			"sTitle" : '主讲人',
			"mDataProp" : 'speaker',
			"sClass" : "longTxt"
		},{
			"sTitle" : '培训内容',
			"mDataProp" : 'details',
			"sClass" : "longTxt"
		}, {
			"sTitle" : '效果评价和改进措施',
			"mDataProp" : 'effect',
			"sClass" : "longTxt"
		},{
			"sTitle" : '培训地点',
			"mDataProp" : 'address',
			"sClass" : "longTxt"
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
		"aoColumnDefs" : []
	});
}

/**
 * 删除
 * */
function deleteTrainingRecord(trainingId){
	//确认对话框
	art.dialog.confirm('确定删除该安全培训记录吗？', function() {
		$.ajax({
			url : basePath + "/training/deleteTraining.action",
			type : "post",
			dataType : 'json',
			data : {
				"trainingId" : trainingId,
				"trainingType" : 1
			},
			success : function(data) {
				if (data == 1) {
					//重新加载列表
					window.location.href="trainingRecordList.jsp";
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
 * 新增
 */
function addTrainingRecord(){
	
	window.location.href="addTrainingRecord.jsp"; 
}

