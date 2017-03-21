$(document).ready(function(){
	
	//保存按钮绑定事件
	$(".searchButton").bind("click", function() {
		// 清除cookie中的分页信息
		$.removeTableCookie('SpryMedia_DataTables_myTable_companyDangerchemicalsToPage_toSPage_companyDangerChemicalsStatisticsAll.action');
		getTableListAll();
		return false;
	});
	
	//回车事件
	$(document).keydown(function(event){
		var code=event.which;
		if (code == 13) {
			// 清除cookie中的分页信息
			$.removeTableCookie('SpryMedia_DataTables_myTable_companyDangerchemicalsToPage_toSPage_companyDangerChemicalsStatisticsAll.action');
			getTableListAll();
			return false;
		}
	});
	
	$("#statisticsType").change(function(){
		if($(this).val() == 1){
			//园区
			window.location.href = basePath + "dangerchemicals/companyDangerchemicalsToPage_toSPage_companyDangerChemicalsStatisticsSub.action";
		}else {
			//企业
			window.location.href = basePath + "dangerchemicals/companyDangerchemicalsToPage_toSPage_companyDangerChemicalsStatisticsAll.action";
		}
	});
	
	//加载列表
	getTableListAll();
	
});


/**
 * 加载列表（全）
 */
function getTableListAll(){
	//操作人角色
	var whroletype = $("#whroletype").val();
	
	//部门ID（企业端查询时传自已的部门ID，政府端查询时，传要查询的企业的部门ID）
	var groupId;
	if(whroletype == 2){
		groupId = $("#group_id").val();
	}
	
	//统计类型（1、园区； 2、企业）
	var statisticsType = $("#statisticsType").val();
	
	//企业ID
	var groupId = $("#companName").val();
	
	//物质名称
	var materialName = $.trim( $('#materialName').val() );
	
	$('#myTable').dataTable({
		"bDestroy" : true,
		"bProcessing" : true,
		"bStateSave" : true, // 状态保存
		'bServerSide' : true,
		'fnServerParams' : function(aoData) {

			aoData.push({
				"name" : "companyDangerChemicals.groupId",
				"value" : groupId
			}, {
				"name" : "companyDangerChemicals.materialName",
				"value" : materialName
			}, {
				"name" : "statisticsType",
				"value" : statisticsType
			});
		},
		"sAjaxSource" : basePath + "dangerchemicals/companyDangerchemicals_List_statistics.action",
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
			"mDataProp" : "materialName"
		}, {
			"mDataProp" : 'num',
			"sClass" : "data_r"
		}, {
			"mDataProp" : 'groupName',
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
		"aoColumnDefs" : [
			{
				"aTargets" : [3], //
				"fnRender" : function(oObj) {
					var groupName = oObj.aData.groupName;
					var groupId = oObj.aData.groupId;
					var html = '<a href="javascript:jumpSPage('+groupId+');">'+groupName+'</a>';
					return html;
				}
			}
		]
	});
}

function jumpSPage(groupId){
	window.location.href = basePath+'dangerchemicals/companyDangerchemicalsToPage_toSPage_companyDangerChemicalsListZF.action?companyDangerChemicals.groupId='+groupId;
	window.top.selectdd('危险化学品', '危险化学品明细');
}
