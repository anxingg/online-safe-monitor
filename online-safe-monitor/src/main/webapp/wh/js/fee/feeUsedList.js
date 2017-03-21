$(document).ready(function(){
	
	//查询按钮绑定事件
	$(".searchButton").bind("click", function() {
		// 清除cookie中的分页信息
		$.removeTableCookie('SpryMedia_DataTables_myTable_feeUsedList.jsp');
		getTableList();
		return false;
	});
	
	//回车事件
	$(document).keydown(function(event){
		var code=event.which;
		if (code == 13) {
			// 清除cookie中的分页信息
			$.removeTableCookie('SpryMedia_DataTables_myTable_feeUsedList.jsp');
			getTableList();
			return false;
		}
	});
	
	//加载列表
	getTableList();
	
});


/**
 * 加载列表
 */
function getTableList(){
	//操作人角色
	var whroletype = $("#whroletype").val();
	
	//部门ID（企业端查询时传自已的部门ID，政府端查询时，传要查询的企业的部门ID）
	var groupId = $("#companName").val();
	if(whroletype == 2){
		groupId = $("#group_id").val();
	}
	
	//使用时间开始
	var searchTimeStart = $.trim( $('#searchTimeStart').val() );
	
	//使用时间结束
	var searchTimeEnd = $('#searchTimeEnd').val();
	
	//输入框
	var keyword = $.trim( $('#keyword').val() );
	
	$('#myTable').dataTable({
		"bDestroy" : true,
		"bProcessing" : true,
		"bStateSave" : true, // 状态保存
		'bServerSide' : true,
		'fnServerParams' : function(aoData) {

			aoData.push({
				"name" : "searchvo.groupId",
				"value" : groupId
			}, {
				"name" : "searchvo.searchTimeStart",
				"value" : searchTimeStart
			}, {
				"name" : "searchvo.searchTimeEnd",
				"value" : searchTimeEnd
			}, {
				"name" : "searchvo.keyword",
				"value" : keyword
			});
		},
		"sAjaxSource" : basePath + "fee/fee_Used_list.action",
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
			"mDataProp" : "groupName",
			"sClass" : "longTxt"
		}, {
			"mDataProp" : 'useTime'
		}, {
			"mDataProp" : 'useDirectionName',
			"sClass" : "longTxt"
		}, {
			"mDataProp" : 'useMoney',
			"sClass" : "longTxt"
		}, {
			"mDataProp" : 'remainingSum',
			"sClass" : "longTxt"
		}, {
			"mDataProp" : 'memo',
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
		}
	});
}


/**
 * 新增操作
 */
function addOne() {
	var groupId = $("#group_id").val();
	window.location.href = basePath + "fee/feeToPage_Used_getInitData_feeUsedAdd.action?searchvo.groupId="+groupId;
}