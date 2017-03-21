$(document).ready(function() {
		$("#showLable").live( "click", function() {

			$("#choiceDiv").hide();
			$(".cont_addr").show();
		});
		$("#hiddenLable").click(function() {
	
			$(".cont_addr").hide();
			$("#choiceDiv").show();
		});

		
	
//		funPlaceholder(document.getElementById("searchkey"));
		getbusinessType();
		setTimeout(function(){
			getHasDataTables();
		}, 100);

		// 查询事件
		$("#search").click(function() {
			$.removeTableCookie('SpryMedia_DataTables_listTable_customerCallHasList.jsp');
			getHasDataTables();
		});

		// 回车事件
		document.onkeydown = function(e) {
			// 兼容FF和IE和Opera
			var theEvent = e || window.event;
			var code = theEvent.keyCode || theEvent.which || theEvent.charCode;
			if (code == 13) {
				$.removeTableCookie('SpryMedia_DataTables_listTable_customerCallHasList.jsp');
				getHasDataTables();
			}
		};

		// 回访结果提交保存
		$("#cclBack").live("click", function() {
			saveCustomerCallBack();
		});

	});

/**
 * 得到业务类别
 */
function getbusinessType(){
	$.ajax({
		url : basePath + "customercalllog/getbusinessType.action",
		type : "post",
		success : function(data){
			var json = eval("("+data+")");
			var html = '';
			for(var i=0;i<json.length;i++){
				var taskName = json[i].name;
				var value = json[i].value;
				html += '<option value=\"'+value+'\">'+taskName+'</option>';
			}
			$("#businessType").append(html);
		}
	});
}
function getHasDataTables() {
	var fromPage = $("#fromPage").val();
	var iscomplete = $("#iscomplete").val();
	
	var beginTime = $("#beginTime").val();// 受理时间开始
	if (beginTime != "") {
		beginTime += ":00";
	}
	var endTime = $("#endTime").val();// 受理时间结束
	if (endTime != "") {
		endTime += ":59";
	}
	var searchScope = $("#searchScope").val();// 显示范围
	if ($("#fromPage").val() == 'back') {
		searchScope = 1;
	}
	var searchKey = "";
	if(fromPage!=null&&fromPage=='back'){
		searchKey = $.trim($("#searchkey1").val());// 后台关键字
	}else{
		searchKey = $.trim($("#searchkey2").val());// 前台关键字
	}
	var type = $('#type option:selected').val();// 1咨询 2投诉 3建议 4举报坐席 5其他
	var accessType = $('#accessType option:selected').val();// 1电话 2短信 3录音
	var businessType = $('#businessType option:selected').val();//数据字典维护
	$('#listTable').dataTable(
					{
						"bProcessing" : true,
						"bStateSave" : true, // 状态保存
						'bServerSide' : true,
						'fnServerParams' : function(aoData) {
							aoData.push( {
								"name" : "customerCallLog.searchScope",
								"value" : searchScope
							}, {
								"name" : "customerCallLog.searchKey",
								"value" : searchKey
							}, {
								"name" : "customerCallLog.endTimeStrLong",
								"value" : endTime
							}, {
								"name" : "customerCallLog.beginTimeStrLong",
								"value" : beginTime
							}, {
								"name" : "customerCallLog.type",
								"value" : type
							}, {
								"name" : "customerCallLog.accessType",
								"value" : accessType
							}, {
								"name" : "customerCallLog.businessType",
								"value" : businessType		
							}, {
								"name" : "fromPage",
								"value" : fromPage
							},{
								"name" : "iscomplete",
								"value" : iscomplete
							},{
								"name" : "allList",
								"value" : 1
							},{
								"name":"customerCallLog.state",
								"value":2
							});
						},
						"sAjaxSource" : basePath
//								+ "customercalllog/list.action",
								+ "jbpmworkorder/list.action",
						"sServerMethod" : "POST",
						"sPaginationType" : "full_numbers",
						"bPaginate" : true, // 翻页功能
						"bLengthChange" : false, // 改变每页显示数据数量
						"bFilter" : false, // 过滤功能
						"bSort" : false, // 排序功能
						"bInfo" : true,// 页脚信息
						"bAutoWidth" : false,// 自动宽度
						"bDestroy" : true,// 用于当要在同一个元素上履行新的dataTable绑订时，将之前的那个数据对象清除掉，换以新的对象设置
						"iDisplayLength" : 15, // 每页显示多少行
						"iDisplayStart" : 0, // 每页显示多少行
						"aoColumns" : [ {
							"mDataProp" : "orderNumber" // 序号
							}, {
								"mDataProp" : null      // 工单编号
							}, {
								"mDataProp" : "typeStr" // 工单类型
							}, {
								"mDataProp" : "name" // 用户姓名
							}, {
								"mDataProp" : "phone" // 联系电话
							}, {
								"mDataProp" : "recordTimeStr" // 受理时间
							}, {
								"mDataProp" : "recordUserIdName" // 受理人员
							}, {
								"mDataProp" : "dealOverTimeStr" // 办结时间
//								"sClass" : "longTxt"
							}, {
								"mDataProp" : "stateStr" // 工单状态
							}, {
								"mDataProp" : null,
								"sClass" : "right_bdr0"
							// 操作9
								} ],
						"oLanguage" : {
							"sUrl" : basePath + "plugins/datatable/cn.txt" // 中文包
						},
						"fnDrawCallback" : function(oSettings) {
							$(
									'#listTable tbody  tr td,#listTable tbody  tr td a')
									.each(
											function() {
												this.setAttribute('title', $(
														this).text());
											});
						},
						"fnFooterCallback" : function(nFoot, aData, iStart,
								iEnd, aiDisplay) {
							// nFoot.getElementsByTagName('th')[0].innerHTML =
							// "Starting index is "+iStart;
							// alert("aData.length: "+aData.length); //
							// 打印该页显示多少行记录。
							var Pagecount = aData.length; // 在这里这个没有用到。
						},
						"aoColumnDefs" : [
						 { "aTargets" : [1], //工单编号1
						   "fnRender" : function(oObj) {
						 	var vid = oObj.aData.vid;
						 	var fp = oObj.aData.toPage;
						 	var cclSn = oObj.aData.cclSn;
							
							var instanceId = oObj.aData.instanceId;
							//var operateUrl = "/jbpmworkorder/customerCallViewBack.action"+"?vid="+vid+"&instanceId="+encodeURI(instanceId);
							var html='';
							if (fp == "back") {
								html = "<a  href='javascript:void(0);' onclick=\"customerCallViewBack(" + vid + ",'"+instanceId+"');\">" + cclSn + "</a>";
							}
							else{//前台
								html = "<a  href='javascript:void(0);' onclick='customerCallViewPrev(" + vid + ");'>" + cclSn + "</a>";
							}
							 return html;
					 	}
					 },{
							"aTargets" : [ 9 ],
							"fnRender" : function(oObj) {
								var vid = oObj.aData.vid;
								var instanceId = oObj.aData.instanceId;
								var operateUrl = oObj.aData.operateUrl+"?vid="+vid+"&instanceId="+encodeURI(instanceId);
								var operateName = oObj.aData.operateName;
								return "<a href='"+basePath+"/"+operateUrl+"'>"+operateName+"</a>";
						}
						} ]
					});
}



/**
 * 前台工单回访
 */
function customerCallBack(vid,instanceId) {
	window.top.addTab('customerCallView' + vid, basePath
			+ "customercalllog/customerCallBack.action?vid=" + vid
			+ "&checkDetain=1&fromPage=back&instanceId="+encodeURI(instanceId), '工单回访');
	// window.location.href = basePath +
	// "customercalllog/customerCallBack.action?vid="+vid;
}
/**
 * 前台工单详情
 * @param vid
 */
function customerCallViewPrev(vid){
	window.top.addTab('customerCallViewPrev'+vid,basePath 
			+ "jbpmworkorder/customerCallViewBack.action?fromPage=seat&vid=" + vid
			+"&checkDetain=1",'工单详情');
}

/**
 * 保存回访结果
 */
function saveCustomerCallBack() {
	// 获得action值 0:成功 1：失败
	var issuccess = $('input[name="RadioGroup1"]:checked').first().val();

	// alert(issuccess);
	if (issuccess == 1) {

		// 成功时 验证后台工单处理时回访结果内容不能为空。
		if (!validator(document.getElementById("customercallbackform1"))) {
			return;
		}

	} else {
		// alert(theaction);
		// issuccess=2;
	}
	// 获取失败原因
	/*var failResult = $('input[name="RadioGroup2"]:checked').first().val();*/
	var failResult = $("#failResult").val();

	// alert($.trim($("#callBackSuccessResult").val()));

	var paramData = {
		'customerCallLog.visitFaildReason' : failResult,
		'customerCallLog.callBackSuccessResult' : $.trim($("#callBackSuccessResult").val()),
		'vid' : $("#vid").val(),
		'customerCallLog.visitResult' : issuccess
	// 'checkType':wanjie
	};
	$.ajax( {
		url : basePath + "jbpmworkorder/saveCustomerCallBack.action",
		type : "post",
		dataType : 'json',
		data : paramData,
		success : function(data) {
			if (data == 1) {
				art.dialog({content: "操作成功！",width: "170px",
				     ok: function() {
							window.top.closeCurrentTab();
							window.top.refreshTableCall();
					}
				});

			} else {
				// art.dialog.alert(data);
		/* art.dialog.alert('回访失败！'); */
	}

}
	});

}

/**
 * 详情
 */
function customerCallView(vid) {
	// alert("view.......");
	window.top.addTab('customerCallView' + vid, basePath
			+ "customercalllog/customerCallView.action?vid=" + vid, '工单详情');
	// window.location.href = basePath +
	// "customercalllog/customerCallView.action?vid="+vid;
}

/**
 * 后台工单详情
 */
function customerCallViewBack(vid,instanceId) {
	window.location.href = basePath 
			+ "jbpmworkorder/customerCallViewBack.action?vid=" + vid+"&instanceId="+encodeURI(instanceId);
}

/**
 * 后台工单处理
 */
function customerCallDeal(vid) {
	
	window.location.href = basePath + operateUrl + vid;
//			+ "jbpmworkorder/backdealCustomerCallLog.action?vid=" ;
//	+ "customercalllog/backdealCustomerCallLog.action?vid=" + vid;
}

/**
 * 工单删除
 */
function delCustomerCallLog(vid) {

	// alert(basePath);
	art.dialog.confirm('确定要删除该工单吗？', function() {
		$.ajax( {
//			url : basePath + "customercalllog/delCustomerCallLog.action?vid="
			url : basePath + "jbpmworkorder/delCustomerCallLog.action?vid="
					+ vid,
			type : "post",
			dataType : 'text',
			// data : vid,
			success : function(data) {
				if (data == 1) {
					art.dialog.alert('删除成功！');
					getHasDataTables();
				} else {
					art.dialog.alert('删除失败！');
				}
			}
		});
	}, function() {
		return;
	});

}