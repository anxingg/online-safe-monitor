$(document).ready(function(){
	
	//查询按钮绑定事件
	$(".searchButton").bind("click", function() {
		// 清除cookie中的分页信息
		$.removeTableCookie('SpryMedia_DataTables_myTable_companyDangerchemicalsToPage_toSPage_companyDangerChemicalsListZF.action');
		getTableList();
		return false;
	});
	
	//回车事件
	$(document).keydown(function(event){
		var code=event.which;
		if (code == 13) {
			// 清除cookie中的分页信息
			$.removeTableCookie('SpryMedia_DataTables_myTable_companyDangerchemicalsToPage_toSPage_companyDangerChemicalsListZF.action');
			getTableList();
			return false;
		}
	});
	
	//加载列表
	getTableList();
	
});


/**
 * 加载安全生产事故列表
 */
function getTableList(){
	//操作人角色
	var whroletype = $("#whroletype").val();
	
	//部门ID（企业端查询时传自已的部门ID，政府端查询时，传要查询的企业的部门ID）
	var groupId = $("#companName").val();
//	if(whroletype == 2){
//		groupId = $("#group_id").val();
//	}
	
	//物质名称（危险化学品名称）
	var materialName = $.trim( $('#materialName').val() );
	
	//物质ID（危险化学品ID），因现在开始传物质名称这个参数了，所有ID这个传数就用不着了。
	var dangerId = undefined;//$("#dangerId").val();
	//alert('materialName='+materialName+', dangerId='+dangerId+', groupId='+groupId);
	
	$('#myTable').dataTable({
		"bDestroy" : true,
		"bProcessing" : true,
		"bStateSave" : true, // 状态保存
		'bServerSide' : true,
		'fnServerParams' : function(aoData) {

			aoData.push({
				"name" : "companyDangerChemicals.groupId",
				"value" : groupId
			},{
				"name" : "companyDangerChemicals.dangerId",
				"value" : dangerId
			}, {
				"name" : "materialName",
				"value" : materialName
			}, {
				"name" : "whroletype",
				"value" : whroletype
			});
		},
		"sAjaxSource" : basePath + "dangerchemicals/companyDangerchemicals_List_list.action",
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
			"mDataProp" : "materialName",
			"sClass" : "longTxt"
		}, {
			"mDataProp" : 'companyName',
			"sClass" : "longTxt"
		},
		{
			"mDataProp" : 'storagePlace',
			"sClass" : "longTxt"
		}, 
		{
			"mDataProp" : 'num',
			"sClass" : "data_r"
		},
//		{
//			"mDataProp" : 'userPlace',
//			"sClass" : "longTxt"
//		}, {
//			"mDataProp" : 'riskType',
//			"sClass" : "longTxt"
//		}, {
//			"mDataProp" : 'riskNum',
//			"sClass" : "longTxt"
//		}, 
		{
			"mDataProp" : 'packagingCategory',
			"sClass" : "longTxt"
		}, 
//		{
//			"mDataProp" : 'registrationNO',
//			"sClass" : "longTxt"
//		}, {
//			"mDataProp" : null,//10
//			"sClass" : "longTxt"
//		}, {
//			"mDataProp" : null,//11
//			"sClass" : "longTxt"
//		}, 
		{
			"mDataProp" : 'memo',
			"sClass" : "longTxt"
		}
		],
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
		,
		"aoColumnDefs" : [
//			{
//				"aTargets" : [10], //技术说明书
//				"fnRender" : function(oObj) {
//					var reportId = oObj.aData.technicalId;
//					var reportName = oObj.aData.technicalName;
//					var html = '';
//					if(reportId == null || reportId == undefined || reportId == '' || reportId == '-'
//						|| reportName == null || reportName == undefined || reportName == '' || reportName == '-'){
//						html = '<a href="javascript:;" >'+reportName+'</a>';
//					}else {
//						html = '<a href="javascript:;" onclick="downloadReport(\''+reportId+'\');">'+reportName+'</a>';
//					}
//					return html;
//				}
//			},
//		    {
//		    	"aTargets" : [11], //安全标签
//		    	"fnRender" : function(oObj) {
//		    		var reportId = oObj.aData.securityId;
//		    		var reportName = oObj.aData.securityName;
//		    		var html = '';
//					if(reportId == null || reportId == undefined || reportId == '' || reportId == '-'
//						|| reportName == null || reportName == undefined || reportName == '' || reportName == '-'){
//						html = '<a href="javascript:;" >'+reportName+'</a>';
//					}else {
//						html = '<a href="javascript:;" onclick="downloadReport(\''+reportId+'\');">'+reportName+'</a>';
//					}
//					return html;
//		    	}
//		    }
		]
	});
}



/**
 * 删除操作
 */
function deleteById(id) {
	artDialog.confirm("你确定要删除吗？", function(){
		$.ajax({
			url : basePath + "dangerchemicals/companyDangerchemicals_Load_deleteOne.action",
			type : "post",
			dataType : 'text',
			data : {
				'companyDangerChemicals.vid' : id
			},
			success : function(data) {
				if(data == 1){
					//artDialog.alert("删除成功！", function(){
						getTableList();
					//});
				}else {
					//artDialog.alert("删除失败！", function(){
						getTableList();
					//});
				}
			}
			
		});
	});
}

/**
 * 新增操作
 */
function addOne() {
	window.location.href = basePath + "dangerchemicals/dangerchemicalsToPage_findAll_companyDangerChemicalsAdd.action";
}

/**
 * 下载附件（技术说明书、安全标签）
 * @param id
 */
function downloadReport(id){
	//var str = new String(id);
	id = id.replace(new RegExp(",","g"),'');
	downLoadAttachment(parseInt(id, 10));
}