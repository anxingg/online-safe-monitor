/**
 * 外呼任务坐席端列表页面的js
 */
$(document).ready(function(){
	//加载表格的数据
	getOutCallTaskSeatTables();
	
	//查询按钮
	$("#search").click(function(){
		$.removeTableCookie('SpryMedia_DataTables_outCallTaskSeatTable_outCallTaskSeatList.jsp');
		getOutCallTaskSeatTables();
	});
	
	// 回车事件
	document.onkeydown = function(e) {
		// 兼容FF和IE和Opera
		var theEvent = e || window.event;
		var code = theEvent.keyCode || theEvent.which || theEvent.charCode;
		if (code == 13) {
			$.removeTableCookie('SpryMedia_DataTables_outCallTaskSeatTable_outCallTaskSeatList.jsp');
			getOutCallTaskSeatTables();
		}
	};
});

function getOutCallTaskSeatTables(){
	$('#outCallTaskSeatTable').dataTable({
		"bProcessing" : true,
		'bServerSide' : true,
		'fnServerParams' : function(aoData) {
			aoData.push({
					"name" :"fromPage",
					"value":"seat"
			},{
				"name" : "phoneTask.taskStateVo",
				"value" :  "3,4,5"			
			});
		},
		"sAjaxSource" : basePath + "phoneTask/phonetask_list.action",
		"sServerMethod" : "POST",
		"sPaginationType" : "full_numbers",
		"bPaginate" : true, // 翻页功能
		"bStateSave" : true, // 状态保存
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
					"mDataProp" : "taskName", //任务名称1
					"sClass" : "longTxt"
				}, {
					"mDataProp" : "taskExplain", //任务说明2
					"sClass" : "longTxt"
				}, {
					"mDataProp" : null ,//已回访/总数量3
					"sClass" : "data_r"
				}, {
					"mDataProp" : "myCallNum", //我回访的4
					"sClass" : "data_r"
				}, {
					"mDataProp" : "taskStateStr" //任务状态5
				}, {
					"mDataProp" : null, //操作6
					"sClass" : "data_l right_bdr0"
				}],
		"oLanguage": {
			   "sUrl": basePath+"plugins/datatable/cn.txt" //中文包
		   },
		"fnDrawCallback": function (oSettings) {
			   $('#outCallTaskSeatTable tbody  tr td,#outCallTaskSeatTable tbody  tr td a').each(function() {
					this.setAttribute('title', $(this).text());
			   });
		   },
		"fnFooterCallback": function( nFoot, aData, iStart, iEnd, aiDisplay ) {
				var Pagecount=aData.length; //在这里这个没有用到。
		   },
		"aoColumnDefs" : [{
				"aTargets":[3],
				"fnRender":function(oObj) {
//					var taskCallCount = oObj.aData.taskCallCount;//已回访
//					var countTotal = oObj.aData.countTotal;//总数量
//					return taskCallCount + "/" + countTotal;
					return oObj.aData.countTotal;
				}
			},{
				"aTargets" : [6], //操作
				"fnRender" : function(oObj) {
					var vid = oObj.aData.vid;
					var taskState = oObj.aData.taskState;//任务状态
					var questionnaireId = oObj.aData.questionnaireId;//问卷ID
					var html='';
					//任务状态	任务状态:1草稿 2 未开始 3 正在进行 4 暂停 5 已结束
					if(taskState==3){
						//如果任务处于正在进行状态的可以执行
						html="<a href='javascript:outCallTaskCall(" + vid + ");' >执行</a>";
						html +="<a href='javascript:outCallTaskResultList(" + questionnaireId + ");'>结果查看</a>";
					}else{
						html +="<a href='javascript:outCallTaskResultList(" + questionnaireId + ");' >结果查看</a>";
					}
					return html;
				}
			}
		]
	}); 
}
/**
 * 外呼任务坐席端-执行
 * @param vid
 */
function outCallTaskCall(vid){
	$.ajax({
		url : basePath + "phoneTask/phonetask_findById.action",
		data : {"phoneTask.vid":vid},
		type : "post",
		dataType : "text",
		success : function (data){
	
			//任务状态:1草稿 2 未开始 3 正在进行 4 暂停 5 已结束
			if(data == 3){
				window.top.addTab('outCallTaskCall' + vid, basePath
						+ "phoneTask/phoneTaskSeat_toPhoneTaskPerform.action?vid=" + vid
						+ "&checkDetain=1", '执行');
			}
			if(data == 1){
				art.dialog.tips("外乎任务已存为草稿");
				//加载表格的数据
				getOutCallTaskSeatTables();
			}
			if(data == 2){
				art.dialog.tips("外乎任务未开始");
				//加载表格的数据
				getOutCallTaskSeatTables();
			}
			if(data == 4){
				art.dialog.tips("外乎任务已暂停");
				//加载表格的数据
				getOutCallTaskSeatTables();
			}
			if(data == 5){
				art.dialog.tips("外乎任务已结束");
				//加载表格的数据
				getOutCallTaskSeatTables();
			}
		}
	});
	
}

/**
 * 结果查看列表
 */
function outCallTaskResultList(questionnaireId){ 
	window.top.addTab('outCallTaskResultList'+ questionnaireId, basePath
			+ "logined/outCallTask/outCallTaskSRList.jsp?stateSaveParam=1&questionnaireId=" + questionnaireId
			+ "&checkDetain=1", '结果查看');
}