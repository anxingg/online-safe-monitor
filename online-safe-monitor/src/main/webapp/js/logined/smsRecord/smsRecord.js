/**
 * 短信发送记录列表页面的js
 */
$(document).ready(function(){
	//alert("outCallTask_commonAdmin.js ...");
	//加载表格的数据
	getDataTables();
	
	//查询按钮
	$("#search").click(function(){
		getDataTables();
	});
	
	// 回车事件
	document.onkeydown = function(e) {
		// 兼容FF和IE和Opera
		var theEvent = e || window.event;
		var code = theEvent.keyCode || theEvent.which || theEvent.charCode;
		if (code == 13) {
			getDataTables();
		}
	};
});


function getDataTables(){
	//var fromPage=$("#fromPage").val();
	//alert(fromPage);
//	var createUserIdName = $("#createUserIdName").val();//操作人员
//	var phone = $("#phone").val();//接收号码
	var sendTimeStart = $("#sendTimeStart").val();//发送开始时间
	if(sendTimeStart!=""){
		sendTimeStart +=":00"; 
	}
	var sendTimeEnd = $("#sendTimeEnd").val();//发送结束时间
	if(sendTimeEnd!=""){
		sendTimeEnd += ":59";
	}
	var searchkey = $.trim($("#searchkey").val());//关键字(包含操作人员,接受号码)
	//alert(taskStartTime+"  "+taskEndTime+"  "+taskState);
	$('#myTable').dataTable({
		"bProcessing" : true,
		'bServerSide' : true,
		'fnServerParams' : function(aoData) {
			aoData.push({
				"name" : "searchkey",
				"value" :  searchkey
			},{
				"name" : "sendTimeStart",
				"value" :  sendTimeStart
			},{
				"name" : "sendTimeEnd",
				"value" :  sendTimeEnd
			},{
				"name" : "customerCallLogId",
				"value" :  ""
			});
		},
		"sAjaxSource" : basePath + "smsRecord/list_list.action",
		"sServerMethod" : "POST",
		"sPaginationType" : "full_numbers",
		"bPaginate" : true, // 翻页功能
		"bStateSave" : false, // 状态保存
		"bLengthChange" : false, // 改变每页显示数据数量
		"bFilter" : false, // 过滤功能
		"bSort" : false, // 排序功能
		"bInfo" : true,// 页脚信息
		"bAutoWidth" : false,// 自动宽度
		"bDestroy" : true,//用于当要在同一个元素上履行新的dataTable绑订时，将之前的那个数据对象清除掉，换以新的对象设置
		"iDisplayLength" : 15, // 每页显示多少行
		"aoColumns" : [{
					"mDataProp" : "orderNumber" //序号0
				}, {
					"mDataProp" : "phone" //接收号码1
				}, {
					"mDataProp" : "createUserIdName" //操作人员2
				}, {
					"mDataProp" : "sendTimeStr" //发送时间3
				}, {
					"mDataProp" : "content", //短信内容4
					"sClass" : "longTxt"
				}],
		"oLanguage": {
			   "sUrl": basePath+"plugins/datatable/cn.txt" //中文包
		   },
		"fnDrawCallback": function (oSettings) {
			   $('#myTable tbody  tr td,#myTable tbody  tr td a').each(function() {
					this.setAttribute('title', $(this).text());
			   });
		   },
		"fnFooterCallback": function( nFoot, aData, iStart, iEnd, aiDisplay ) {
				//nFoot.getElementsByTagName('th')[0].innerHTML = "Starting index is "+iStart;
				//alert("aData.length:  "+aData.length); // 打印该页显示多少行记录。
				var Pagecount=aData.length; //在这里这个没有用到。
		   }
	}); 
}