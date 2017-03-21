var bInfo = true;// 页脚信息
var iDisplayLength = 15;// 每页显示多少行

jQuery(document).ready(function() {

	if($("#from").val()=="tel"){//来电弹屏页面
		bInfo = false;
		iDisplayLength = 5;
	}else{//历史工单页面
		bInfo = true;
		iDisplayLength = 15;
	}
	getDataTable();

});

/**
 * 根据电话获取相关的工单
 */
function getDataTable() {
	$('#myRelateTable')
			.dataTable(
					{
						"bProcessing" : true,
						'bServerSide' : true,
						"bStateSave" : true, // 状态保存
						"bDestroy" : true,
						'fnServerParams' : function(aoData) {
							aoData.push( {
								"name" : "customerCallLog.phone",
								"value" : $.trim($("#uphone").val())
							});
						},
						"sAjaxSource" : basePath
								+ "jbpmworkorder/getRelatedCustomerCallByPhone.action",
						"sServerMethod" : "POST",
						"sPaginationType" : "full_numbers",
						"bPaginate" : true, // 翻页功能
						"bLengthChange" : false, // 改变每页显示数据数量
						"bFilter" : false, // 过滤功能
						"bSort" : false, // 排序功能
						"bInfo" : bInfo,// 页脚信息
						"bAutoWidth" : false,// 自动宽度
						"iDisplayLength" : iDisplayLength, // 每页显示多少行
						"aoColumns" : [ {
							"sTitle" : "序号",
							"mDataProp" : "no",

							"sClass" : "tdCenter"
						}, {
							"sTitle" : '受理时间',
							"mDataProp" : "recordTime",

							"sClass" : "tdCenter"
						}, {
							"sTitle" : '工单编号',
							"mDataProp" : "cclSn",

							"sClass" : "tdCenter"
						}, {
							"sTitle" : '业务类别',
							"mDataProp" : "businessType",
							"sClass" : "tdCenter"
						}, {
							"sTitle" : '工单类别',
							"mDataProp" : "typeName",
							"sClass" : "tdCenter"
						}, {
							"sTitle" : '工单内容',
							"mDataProp" : "content",
							"sClass" : "longTxt"
						}, {
							"sTitle" : '工单状态',
							"mDataProp" : "stateStr"
						}, {
							"sTitle" : '受理人员',
							"mDataProp" : "userName"

						}, {
							"sTitle" : '操作',
							"mDataProp" : null

						}

						],
						"oLanguage" : {
							"sUrl" : basePath + "plugins/datatable/cn.txt" // 中文包
						},
						"fnDrawCallback" : function(oSettings) {
							// 提示
							$('#myRelateTable tbody  tr td').each(function() {
								$(this).attr('title', $(this).text());
							});
							$("#total").prop("checked", false);
							// 根据调用的页面判断是否显示下面的分页的信息
							if($("#from").val()=="tel"){//来电弹屏页面
								$("#myRelateTable_info").hide();
								$("#myRelateTable_paginate").hide();
							}else{//历史工单页面
								$("#myRelateTable_info").show();
								$("#myRelateTable_paginate").show();
							}

						},
						"fnInitComplete" : function() {
							// 重置iframe高度
							// window.parent.frameResize();
//							if($("#from").val()=="tel"){
//								$("#myRelateTable_info").hide();
//								$("#myRelateTable_paginate").hide();
//							}else{
//								$("#myRelateTable_info").show();
//								$("#myRelateTable_paginate").show();
//							}
						},

						"aoColumnDefs" : [ {
							"aTargets" : [ 8 ],// 覆盖第9列
							"fnRender" : function(oObj) {
								var instanceId = oObj.aData.instanceId;
								// var vid = oObj.aData.id;
							// return '<a href=\"javascript:void(0);\"
							// onclick=\"customerCallHistoryView('+ vid +
							// ');\">查看</a>';
							return "<a  href=\"javascript:customerCallHistoryView(\'"
									+ oObj.aData.id + "\',\'"+instanceId+"\');\" >查看</a>";
						}
						} ]

					});
}

/**
 * 详情
 */
function customerCallHistoryView(vid,instanceId) {
	instanceId = encodeURI(instanceId);

	 window.top.addTab('customerCallHistoryView'+vid, basePath +
	 "jbpmworkorder/customerCallViewBack.action?vid="+vid+"&fromPage=prev&checkDetain=1&instanceId="+instanceId,
	 '工单详情');
//	window.location.href = basePath
//			+ "customercalllog/customerCallView.action?vid=" + vid
//			+ "&fromPage=1";

}
