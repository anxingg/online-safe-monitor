/**
 * 外呼任务列表页面的js
 */
$(document).ready(function(){
	//加载表格的数据
	setTimeout(function(){
		getOutCallTaskTables();
	}, 100);
	
	//查询按钮
	$("#search").click(function(){
		$.removeTableCookie('SpryMedia_DataTables_outCallTaskTable_outCallTaskList.jsp');
		getOutCallTaskTables();
	});
	
	// 回车事件
	document.onkeydown = function(e) {
		// 兼容FF和IE和Opera
		var theEvent = e || window.event;
		var code = theEvent.keyCode || theEvent.which || theEvent.charCode;
		if (code == 13) {
			$.removeTableCookie('SpryMedia_DataTables_outCallTaskTable_outCallTaskList.jsp');
			getOutCallTaskTables();
		}
	};
	//新增
	$("#addOutCallTask").click(function(){
		toSave();
	});
	
	if(window.top.getLoginIsFG()==2){
		$("#addButton").hide();
	}else{
		$("#addButton").show();
	}
});

function getOutCallTaskTables(){
	var taskName_t= $("#searchkey").val(); //查询条件中的任务名称
	var taskState = $("#taskState").val();//任务状态
	$('#outCallTaskTable').dataTable({
		"bProcessing" : true,
		'bServerSide' : true,
		'fnServerParams' : function(aoData) {
			aoData.push({
				"name" : "phoneTask.taskStateVo",
				"value" :  taskState
			},{
				"name" : "phoneTask.taskName",
				"value" :  taskName_t
			},{
				"name" :"fromPage",
				"value":"back"
			}
			);
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
					"mDataProp" : null, //任务名称1
					"sClass" : "longTxt"
				}, {
					"mDataProp" : "taskTypeStr", //任务类型2
					"sClass" : "longTxt"
				}, {
					"mDataProp" : "taskTotal", //总数量3
					"sClass" : "data_r"
				}, {
					"mDataProp" : "taskCallCount", //已回访4
					"sClass" : "data_r"
				}, {
					"mDataProp" : "taskSuccessCount", //成功数5
					"sClass" : "data_r"
				}, {
					"mDataProp" : "taskFailureCount", //未成功数6
					"sClass" : "data_r"
				}, {
					"mDataProp" : "taskStateStr"//任务状态7
//					"sClass" : "data_l"
				}, {
					"mDataProp" : null ,//操作8
					"sClass" : "data_l right_bdr0"
				}],
		"oLanguage": {
			   "sUrl": basePath+"plugins/datatable/cn.txt" //中文包
		   },
		"fnDrawCallback": function (oSettings) {
			   $('#outCallTaskTable tbody  tr td,#outCallTaskTable tbody  tr td a').each(function() {
					this.setAttribute('title', $(this).text());
			   });
		   },
		"fnFooterCallback": function( nFoot, aData, iStart, iEnd, aiDisplay ) {
				var Pagecount=aData.length; //在这里这个没有用到。
		   },
		"aoColumnDefs" : [{
			"aTargets" : [1], //任务名称1
			"fnRender" : function(oObj) {
				var vid = oObj.aData.vid;
				var taskName = oObj.aData.taskName;
				//跳详情页面。
				var html='<a href="javascript:;" onclick="toDetail('+vid+');">'+taskName+'</a>';
				return html;
			}
		},{
				"aTargets" : [8], //操作
				"fnRender" : function(oObj) {
					var vid = oObj.aData.vid;
					var taskState = oObj.aData.taskState;//任务状态:1草稿 2 未开始 3 正在进行 4 暂停 5 已结束
					var questionnaireId = oObj.aData.questionnaireId;//问卷ID
					var loginIsForkg = window.top.getLoginIsFG();
					var html='';
					if(taskState==2){//未开始
						//如果未开始任务则可以编辑,开始
						if(loginIsForkg==2){
							html +='<a href="javascript:;" onclick="toStartOrPause(\''+vid+'\',\''+1+'\');">开始</a><a href="javascript:;" onclick="toDelTask('+vid+');">删除</a>';
						}else{
							html +='<a href="javascript:;" onclick="toStartOrPause(\''+vid+'\',\''+1+'\');">开始</a><a href="javascript:;" onclick="toEdit('+vid+');">修改</a><a href="javascript:;" onclick="toDelTask('+vid+');">删除</a>';
						}
					}else if(taskState==4){
						//如果任务处于暂停状态则可以继续开始
						html='<a href="javascript:;" onclick="toStartOrPause(\''+vid+'\',\''+3+'\');">开始</a><a href="javascript:;" onclick="toDelTask('+vid+');">删除</a>';
						html +='<a href="'+basePath+'logined/outCallTask/outCallTaskResultList.jsp?paramData=1&questionnaireId='+questionnaireId+'">结果查看</a>';
					}
					else if(taskState==3){
						//如果任务处于正在进行状态的时候则可以暂停
						html='<a href="javascript:;" onclick="toStartOrPause(\''+vid+'\',\''+2+'\');">暂停</a>';
						html +='<a href="'+basePath+'logined/outCallTask/outCallTaskResultList.jsp?paramData=1&questionnaireId='+questionnaireId+'">结果查看</a>';
					}else if(taskState==5){
						//如果任务已经结束
						html += '<a href="javascript:;" onclick="toDelTask('+vid+');">删除</a>';
						html +='<a href="'+basePath+'logined/outCallTask/outCallTaskResultList.jsp?paramData=1&questionnaireId='+questionnaireId+'">结果查看</a>';
					}else{
						//如果草稿,只能修改和删除
						if(loginIsForkg==2){
							html+='<a href="javascript:;" onclick="toDelTask('+vid+');">删除</a>';
						}else{
							html+='<a href="javascript:;" onclick="toEdit('+vid+');">修改</a><a href="javascript:;" onclick="toDelTask('+vid+');">删除</a>';
						}
					}
					return html;
				}
			}
		]
	}); 
}
//新增
function toSave(){
	window.location.href = basePath + "logined/outCallTask/outCallTaskCreate.jsp?type=save";
}
//编辑
function toEdit(vid){
	window.location.href=basePath + "logined/outCallTask/outCallTaskCreate.jsp?type=save&phoneTaskId="+vid;
}

/**
 * 跳转到详情页面
 * @param vid
 */
function toDetail(vid){
	window.location.href=basePath + "phoneTask/phonetask_phoneTaskDetail.action?vid="+vid;
}
/**
 * 后台设置任务开始/暂停
 * @param vid
 */
function toStartOrPause(vid,state){
	var paramData = {
	        'phoneTask.vid' : vid,
	        'state':state
	    };
	
	var title ='';
    var content ='';
    var successContent='';
    if(state!=null&&state!=''&&state==2){
    	content = '是否暂停外呼任务?';
    	successContent = '暂停外呼任务成功!';
    }else{
        content ='是否开始外呼任务？';
        successContent='开始外呼任务成功！';
    }
	
//    art.dialog.confirm(content, function() {
    	$.ajax({
            type : 'post',                
            url : basePath + "phoneTask/phonetask_phoneTaskStartOrPause.action",
            data : paramData,
            dataType : 'text',
            success : function(data) {
            	if (data =="0" ) {
//            		art.dialog.alert(successContent,function(){
            			window.location.href=basePath+'logined/outCallTask/outCallTaskList.jsp';
//            		});
                } else {
                    art.dialog.alert("操作失败,或者该任务已结束！");
                }
            }
        });
//	});
}

/**
 * 删除外呼任务
 * @param vid
 */
function toDelTask(vid){
	var paramData = {
	        'phoneTask.vid' : vid
	    };
	
    var content ='确定要删除吗？';
    var successContent='删除外呼任务成功！';
    art.dialog.confirm(content, function() {
    	 $.ajax({
             type : 'post',                
             url : basePath + "phoneTask/phonetask_phoneTaskDel.action",
             data : paramData,
             dataType : 'text',
             success : function(data) {
             	if (data == "0") {
//             		art.dialog.alert(successContent,function(){
             			window.location.href=basePath+'logined/outCallTask/outCallTaskList.jsp';
//            		});
                 } else {
                     art.dialog.alert("操作失败！");
                 }
             }
         });
	});
}

