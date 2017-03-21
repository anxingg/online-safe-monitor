$(document).ready(function(){
	//初始化事故性质下拉框
	//initDictType('accidentCharacterType', 'accidentCharacter', undefined);
	
	//保存按钮绑定事件
	$(".searchButton").bind("click", function() {
		//清除cookie中的分页信息
		$.removeTableCookie('SpryMedia_DataTables_myTable_safeaccidentload_loadCompanyAndAccidentCharacter_safeAccidentStatistics.action');
		getTableList();
		return false;
	});
	
	//回车事件
	$(document).keydown(function(event){
		var code=event.which;
		if (code == 13) {
			//清除cookie中的分页信息
			$.removeTableCookie('SpryMedia_DataTables_myTable_safeaccidentload_loadCompanyAndAccidentCharacter_safeAccidentStatistics.action');
			getTableList();
			return false;
		}
	});
	
	//加载列表
	getTableList();
	
});


/**
 * 统计安全生产事故列表
 */
function getTableList(){
	//操作人角色
	var whroletype = $("#whroletype").val();
	
	//部门ID（企业端查询时传自已的部门ID，政府端查询时，传要查询的企业的部门ID）
	var groupId;
	if(whroletype == 2){
		groupId = $("#group_id").val();
	}
	
	//事故性质
	var accidentCharacter = $('#accidentCharacter').val();
	
	//事故发生开始时间
	var beginTime = $('#beginTime').val();
	if(beginTime != ''){
		beginTime += ' 00:00:00';
	}
	
	//事故发生结束时间
	var endTime = $('#endTime').val();
	if(endTime != ''){
		endTime += ' 23:59:59';
	}
	
	$('#myTable').dataTable({
		"bDestroy" : true,
		"bProcessing" : true,
		"bStateSave" : false, // 状态保存
		'bServerSide' : true,
		'fnServerParams' : function(aoData) {

			aoData.push({
				"name" : "safeAccident.groupId",
				"value" : groupId
			}, {
				"name" : "safeAccident.accidentCharacter",
				"value" : accidentCharacter
			}, {
				"name" : "whroletype",
				"value" : whroletype
			}, {
				"name" : "beginTime",
				"value" : beginTime
			}, {
				"name" : "endTime",
				"value" : endTime
			});
		},
		"sAjaxSource" : basePath + "safeaccident/safeaccident_List_getStatisticsList.action",
		"sPaginationType" : "full_numbers",
		"bPaginate" : true, // 翻页功能
		"bLengthChange" : false, // 改变每页显示数据数量
		"bFilter" : false, // 过滤功能
		"bSort" : false, // 排序功能
		"bInfo" : true,// 页脚信息
		"bAutoWidth" : false,// 自动宽度
		"iDisplayLength" : 15, // 每页显示多少行
		"aoColumns" : [{
			"mDataProp" : 'no'
		}, {
			"mDataProp" : 'accidentCharacterName'
		}, {
			"mDataProp" : 'count',
			"sClass" : "data_r"
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
		}
	});
}

