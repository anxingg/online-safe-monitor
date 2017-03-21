/**
 * 坐席端外呼任务结果查看列表
 */
$(document).ready(function(){
	//从菜单进来清空状态缓存，从查看进来保存缓存
	var stateSaveParam = $("#stateSaveParam").val();
	if(stateSaveParam==1){
		$.removeTableCookie('SpryMedia_DataTables_outCallTaskSRTable_outCallTaskSRList.jsp');
	}
	//加载表格的数据
	getOutCallTaskSRTables();
	
});

function getOutCallTaskSRTables(){
	var questionnaireId = $("#questionnaireId").val();//问卷id
	$('#outCallTaskSRTable').dataTable({
		"bProcessing" : true,
		'bServerSide' : true,
		'fnServerParams' : function(aoData) {
			aoData.push({
				"name" : "questionnaireId",
				"value" : questionnaireId
			},{
				"name":"statue",
				"value":-1
			},{
				"name":"searchkey",
				"value":""
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
					"mDataProp" : "outCallObject"//客户姓名1
				}, {
					"mDataProp" : "phone" //客户号码2
				}, {
					"mDataProp" : "callTimeStr" //开始时间3
				}, {
					"mDataProp" : "endTimeStr" //结束时间4
				}, {
					"mDataProp" : "callLength", //时长（秒）5
					"sClass" : "data_r"
				}, {
					"mDataProp" : null //回访结果6
				}, {
					"mDataProp" : null, //操作7
					"sClass" : "data_l right_bdr0"
					
				}],
		"oLanguage": {
			   "sUrl": basePath+"plugins/datatable/cn.txt" //中文包
		   },
		"fnDrawCallback": function (oSettings) {
			   $('#outCallTaskSRTable tbody  tr td,#outCallTaskResultTable tbody  tr td a').each(function() {
					this.setAttribute('title', $(this).text());
			   });
		   },
		"fnFooterCallback": function( nFoot, aData, iStart, iEnd, aiDisplay ) {
				var Pagecount=aData.length; //在这里这个没有用到。
		   },
		"aoColumnDefs" : [{
			"aTargets" : [6], //回访结果
			"fnRender" : function(oObj) {
				var statue = oObj.aData.statue;//回访结果状态
				if(statue==1){
					return '成功';
				}else if(statue==2){
					return '无人接听';
				}else if(statue==3){
					return '电话正忙';
				}else if(statue==4){
					return '呼叫转移';
				}else if(statue==5){
					return '电话关机';
				}else if(statue==6){
					return '电话停机';
				}else if(statue==7){
					return '空号';
				}else if(statue=8){
					return '用户拒访';
				}else{
					;
				}
			}
		},{
			"aTargets" : [7], //操作
			"fnRender" : function(oObj) {
				var statue = oObj.aData.statue;//回访结果状态
				var phoneUserid = oObj.aData.phoneUserid;//任务用户ID
				var vid = oObj.aData.vid;//任务id
				var html="";
				if(statue==1){
					html += '<a style="color:gray;" href="#">重呼</a><a href="javascript:toDetail('+vid+','+phoneUserid+');" >查看</a>';
				}else{
					html += '<a href="javascript:outCallTaskCall(\'' + vid + '\',\''+phoneUserid+'\');" >重呼</a>';
				}
				return html;
			}
		}]
	}); 
}
/**
 * 外呼任务坐席端-重呼
 * @param vid
 */
function outCallTaskCall(vid,phoneUserid){
	var viewPageId = window.top.getCurrentTabId();
	window.top.addTab('outCallTaskCallrecall'+ vid, basePath
			+ "phoneTask/phoneTaskSeat_toPhoneTaskPerform.action?vid="+vid+"&phoneUserid=" + phoneUserid
			+ "&checkDetain=1&viewPageId="+viewPageId, '重呼');
}

/**
 * 跳转到详情页面
 * @param vid
 */
function toDetail(vid,userId){
	var questionnaireId = $("#questionnaireId").val();
	window.top.addTab("outCallTaskResultDetialReCall"+userId,basePath+"phoneTask/detailResult_view.action?userId="+userId+"&questionnaireId="+questionnaireId
			+"&checkDetain=1",'查看');
}