$(document).ready(function(){
	//初始化事故性质下拉框
	//initDictType('accidentCharacterType', 'accidentCharacter', undefined);
	
	//当政府端访问时，加载企业下拉框
	//if($("#whroletype").val() == 1){
		//getSelectCompany();
	//}
	
	//保存按钮绑定事件
	$(".searchButton").bind("click", function() {
		// 清除cookie中的分页信息
		$.removeTableCookie('SpryMedia_DataTables_myTable_safeaccidentload_loadCompanyAndAccidentCharacter_safeAccidentListZF.action');
		getTableList();
		return false;
	});
	
	//回车事件
	$(document).keydown(function(event){
		var code=event.which;
		if (code == 13) {
			// 清除cookie中的分页信息
			$.removeTableCookie('SpryMedia_DataTables_myTable_safeaccidentload_loadCompanyAndAccidentCharacter_safeAccidentListZF.action');
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
	if(whroletype == 2){
		groupId = $("#group_id").val();
	}
	
	//事故名称
	var accidentName = $.trim( $('#accidentName').val() );
	
	//事故性质
	var accidentCharacter = $('#accidentCharacter').val();
	
	$('#myTable').dataTable({
		"bDestroy" : true,
		"bProcessing" : true,
		"bStateSave" : true, // 状态保存
		'bServerSide' : true,
		'fnServerParams' : function(aoData) {

			aoData.push({
				"name" : "safeAccident.groupId",
				"value" : groupId
			}, {
				"name" : "safeAccident.accidentName",
				"value" : accidentName
			}, {
				"name" : "safeAccident.accidentCharacter",
				"value" : accidentCharacter
			}, {
				"name" : "whroletype",
				"value" : whroletype
			});
		},
		"sAjaxSource" : basePath + "safeaccident/safeaccident_List_getList.action",
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
			"mDataProp" : 'accidentName',
			"sClass" : "longTxt"
		}, {
			"mDataProp" : 'accidentCharacter'
		}, 
//		{
//			"mDataProp" : 'canSee'
//		},
		{
			"mDataProp" : null,
			"sClass" : "longTxt"
		},
		{
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
				"aTargets" : [4], //附件下载
				"fnRender" : function(oObj) {
					var reportId = oObj.aData.reportId;
					var reportName = oObj.aData.reportName;
					var html = '';
					if(reportId == null || reportId == undefined || reportId == '' || reportId == '-'
						|| reportName == null || reportName == undefined || reportName == '' || reportName == '-'){
						html = '<a href="javascript:;" >'+reportName+'</a>';
					}else {
						html = '<a href="javascript:;" onclick="downloadReport(\''+reportId+'\');">'+reportName+'</a>';
					}
					return html;
				}
			},
			{
				"aTargets" : [5], //操作
				"fnRender" : function(oObj) {
					var vid = oObj.aData.vid;
					var dataSource = oObj.aData.dataSource;
					var html = '<a href="'+basePath+'safeaccident/safeaccidentload_findAafeAccident_safeAccidentDetail.action?safeAccident.vid='+vid+'&operation=4">查看</a>';
					if(dataSource==1&&whroletype==1){
						html += '<a href="javascript:deleteSafeAccident('+vid+');">删除</a>';
					}
					return html;
				}
			}
		]
	});
}



/**
 * 删除操作
 */
function deleteSafeAccident(id) {
	artDialog.confirm("你确定要删除吗？", function(){
		$.ajax({
			url : basePath + "safeaccident/safeaccident_Load_deleteAafeAccident.action",
			type : "post",
			dataType : 'text',
			data : {
				'safeAccident.vid' : id
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
function addSafeAccident() {
	window.location.href = basePath + "safeaccident/safeaccidentload_loadCompanyAndAccidentCharacter_safeAccidentAdd.action";
}


/**
 * 下载附件
 * @param id
 */
function downloadReport(id){
	//var str = new String(id);
	id = id.replace(new RegExp(",","g"),'');
	downLoadAttachment(parseInt(id, 10));
}