/**
 * 外呼任务中详情页面的结果查看列表的js
 */
$(document).ready(function(){
	//加载表格的数据
	setTimeout(function(){
		getOutCallTaskResultTables();
	},100);
	//从菜单进来清空状态缓存，从查看进来保存缓存
	var stateSaveParam = $("#stateSaveParam").val();
	if(stateSaveParam==1){
		$.removeTableCookie('SpryMedia_DataTables_outCallTaskResultTable_outCallTaskResultList.jsp');
	}
	//查询按钮
	$("#search").click(function(){
		$.removeTableCookie('SpryMedia_DataTables_outCallTaskResultTable_outCallTaskResultList.jsp');
		getOutCallTaskResultTables();
	});
	
	// 回车事件
	document.onkeydown = function(e) {
		// 兼容FF和IE和Opera
		var theEvent = e || window.event;
		var code = theEvent.keyCode || theEvent.which || theEvent.charCode;
		if (code == 13) {
			$.removeTableCookie('SpryMedia_DataTables_outCallTaskResultTable_outCallTaskResultList.jsp');
			getOutCallTaskResultTables();
		}
	};
	$("#outCallTaskResultExport").click(function(){
		exporting();
	});
});

function getOutCallTaskResultTables(){

	var questionnaireId = $("#questionnaireId").val();//问卷id
	var statue = $("#statue").val();//回访结果
	var searchkey = $("#searchkey").val();
	
	$('#outCallTaskResultTable').dataTable({
		"bProcessing" : true,
		'bServerSide' : true,
		'fnServerParams' : function(aoData) {
			aoData.push({
				"name" : "questionnaireId",
				"value" :  questionnaireId
			},{
				"name" : "statue",
				"value" :  statue
			},{
				"name" : "searchkey",
				"value" :  searchkey
			});
		},
		"sAjaxSource" : basePath + "phoneTask/detailResult_list.action",
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
					"mDataProp" : "no" //序号0
				}, {
					"mDataProp" : "outCallObject"//外呼对象1
				}, {
					"mDataProp" : "phone" //电话号码2
				}, {
					"mDataProp" : "seatName" //执行坐席3
				}, {
					"mDataProp" : "workNo" //坐席工号4
				}, {
					"mDataProp" : "callTimeStr" //回访时间5
				}, {
					"mDataProp" : "callResult" //回访结果6
				}, {
					"mDataProp" : null ,//操作7
					"sClass" : "right_bdr0"
				}],
		"oLanguage": {
			   "sUrl": basePath+"plugins/datatable/cn.txt" //中文包
		   },
		"fnDrawCallback": function (oSettings) {
			   $('#outCallTaskResultTable tbody  tr td,#outCallTaskResultTable tbody  tr td a').each(function() {
					this.setAttribute('title', $(this).text());
			   });
		   },
		"fnFooterCallback": function( nFoot, aData, iStart, iEnd, aiDisplay ) {
				var Pagecount=aData.length; //在这里这个没有用到。
		   },
		"aoColumnDefs" : [{
			"aTargets" : [7], //操作
			"fnRender" : function(oObj) {
				var id = oObj.aData.id;
				var statue = oObj.aData.statue;//回访结果状态
				var phoneUserid = oObj.aData.phoneUserid;
				//跳详情页面。
				if(statue==1){
					return '<a href="javascript:;" onclick="toDetail('+id+','+phoneUserid+');">查看</a>';
				}else{
					return "--";
				}
			}
		}]
	}); 
}

/**
 * 跳转到详情页面
 * @param vid
 */
function toDetail(vid,userId){
	var questionnaireId = $("#questionnaireId").val();
	window.location.href=basePath + "phoneTask/detailResult_view.action?userId="+userId+"&questionnaireId="+questionnaireId;
}

function exporting(){
	var questionnaireId = $("#questionnaireId").val();//问卷id
	var statue = $("#statue").val();//回访结果
	var searchkey = $("#searchkey").val();
	
	var url = basePath + "phoneTask/detailResult_exporting.action?questionnaireId="+ questionnaireId + "&statue="+ statue + "&searchkey="+ searchkey;
	url=encodeURI(url);
	window.open(url);
}