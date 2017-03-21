//显示工单数量
var cclShow = 5;
//显示通知公告数量
var noticeShow = 5;
//通知公告栏目类型
var columnId = 35;
/**
 * 后台的首页js
 */
$(document).ready(function(){
	var which = $("#which").val();
	if(which=="2"){
		cclShow = 13;
		noticeShow = 6;
	}
	//工单表
	getCustomerCallLogList();
	//公告栏
	getNotice();
	//知识库栏
	getKnowledge();
	//外呼任务表
	getOutCallTask();
	//5分钟一次刷新首页
	refreshWelcome();
});

//5分钟一次刷新首页
function refreshWelcome(){
	setInterval(function(){
		//工单表
		getCustomerCallLogList();
		//公告栏
		getNotice();
		//知识库栏
		getKnowledge();
		//外呼任务表
		getOutCallTask();
	},5*60*1000);
};

//外呼任务列表
function getOutCallTask(){

	$('#outCallTaskTable').dataTable({
		"bProcessing" : true,
		'bServerSide' : true,
		'fnServerParams' : function(aoData) {
			aoData.push({
				"name" : "phoneTask.taskState",
				"value" :  ""
			},{
				"name" : "phoneTask.taskName",
				"value" :  ""
			}
			);
		},
		"sAjaxSource" : basePath + "phoneTask/phonetask_list.action",
		"sServerMethod" : "POST",
		"sPaginationType" : "full_numbers",
		"bPaginate" : true, // 翻页功能
		"bStateSave" : false, // 状态保存
		"bLengthChange" : false, // 改变每页显示数据数量
		"bFilter" : false, // 过滤功能
		"bSort" : false, // 排序功能
		"bInfo" : false,// 页脚信息
		"bAutoWidth" : false,// 自动宽度
		"bDestroy" : true,//用于当要在同一个元素上履行新的dataTable绑订时，将之前的那个数据对象清除掉，换以新的对象设置
		"iDisplayLength" : 5, // 每页显示多少行
		"aoColumns" : [{
					"mDataProp" : "orderNumber" //序号0
				}, {
					"mDataProp" : null, //任务名称1
					"sClass" : "longTxt"
				}, {
					"mDataProp" : "taskTypeStr" //任务类型2
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
					"mDataProp" : "taskStateStr", //任务状态7
					"sClass" : "data_l"
				}, {
					"mDataProp" : null ,//操作8
					"sClass":"right_bdr0 data_l"
				}],
		"oLanguage": {
			   "sUrl": basePath+"plugins/datatable/cn.txt" //中文包
		   },
		"fnDrawCallback": function (oSettings) {
			   $('#outCallTaskTable tbody  tr td,#outCallTaskTable tbody  tr td a').each(function() {
				   if($(this).text() != "正在加载数据"){
					   this.setAttribute('title', $(this).text());
				   }else{
					   $(this),text().css("color","gray");
				   }
			   });
//			   $("#outCallTaskTable").find("div.dataTables_info").hide();
//			   $("#outCallTaskTable").find("div.dataTables_paginate paging_full_numbers").hide();
		   },
		"fnFooterCallback": function( nFoot, aData, iStart, iEnd, aiDisplay ) {
				var Pagecount=aData.length; //在这里这个没有用到。
				$("#outCallTaskTable_paginate").hide();
				   $("#outCallTaskTable_info").hide();
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
					var taskStateStr = oObj.aData.taskStateStr;
					var questionnaireId = oObj.aData.questionnaireId;//问卷ID
					var html='';
					//任务状态	任务状态:1草稿 2 未开始 3 正在进行 4 暂停 5 已结束
					if(taskStateStr=='未开始'){//未开始
						//如果未开始任务则可以编辑,开始
						html +='<a href="javascript:;" onclick="toStartOrPause(\''+vid+'\',\''+1+'\');">开始</a><a href="javascript:;" onclick="toEdit('+vid+');">修改</a><a href="javascript:;" onclick="toDelTask('+vid+');">删除</a>';
					}else if(taskStateStr=='暂停'){
						//如果任务处于暂停状态则可以继续开始
						html='<a href="javascript:;" onclick="toStartOrPause(\''+vid+'\',\''+3+'\');">开始</a><a href="javascript:;" onclick="toDelTask('+vid+');">删除</a>';
						html +='<a href="'+basePath+'logined/outCallTask/outCallTaskResultList.jsp?questionnaireId='+questionnaireId+'">结果查看</a>';
					}
					else if(taskStateStr=='正在进行'){
						//如果任务处于正在进行状态的时候则可以暂停
						html='<a href="javascript:;" onclick="toStartOrPause(\''+vid+'\',\''+2+'\');">暂停</a><a href="javascript:;" onclick="toDelTask('+vid+');">删除</a>';
						html +='<a href="'+basePath+'logined/outCallTask/outCallTaskResultList.jsp?questionnaireId='+questionnaireId+'">结果查看</a>';
					}else if(taskStateStr=='已结束'){
						//如果任务已经结束
						html += '<a href="javascript:;" onclick="toDelTask('+vid+');">删除</a>';
						html +='<a href="'+basePath+'logined/outCallTask/outCallTaskResultList.jsp?questionnaireId='+questionnaireId+'">结果查看</a>';
					}else{
						//如果草稿,只能修改和删除
						html+='<a href="javascript:;" onclick="toEdit('+vid+');">修改</a><a href="javascript:;" onclick="toDelTask('+vid+');">删除</a>';
					}
					return html;
				}
			}
		]
	}); 
}

/**
 * 外呼任务编辑
 * */
function toEdit(vid){
	window.location.href=basePath + "logined/outCallTask/outCallTaskCreate.jsp?type=save&phoneTaskId="+vid;
}

/**
 * 跳转到外呼任务详情页面
 * */
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
	art.dialog.confirm('确定要删除该任务吗？', function() {
		$.ajax( {
			url : basePath + "phoneTask/phonetask_phoneTaskDel.action",
			type : "post",
			dataType : 'text',
			data : paramData,
			success : function(data) {
				if (data == "0") {
					art.dialog.alert('删除成功！',function(){
						window.location.reload();
					});
				} else {
					art.dialog.alert('删除失败！');
				}
			}
		});
	}, function() {
		return;
	});
	
//	var paramData = {
//	        'phoneTask.vid' : vid
//	    };
//	
//	var title ='删除外呼任务';
//    var content ='是否删除该外呼任务？';
//    var successContent='删除外呼任务成功！';
//	
//    art.dialog.confirm({
//        lock : true,
//        background : '#000',
//        opacity : 0.1,
//        title : title,
//        content : content,
//        icon : 'question',
//        ok : function() {
//            $.ajax({
//                type : 'post',                
//                url : basePath + "phoneTask/phoneTaskDel.action",
//                data : paramData,
//                dataType : 'text',
//                success : function(data) {
//                	if (data == "0") {
//                        art.dialog({
//                            lock : true,
//                            background : '#000',
//                            opacity : 0.1,
//                            title : '提示',
//                            content : successContent,
//                            icon : 'succeed',
//                            ok : function() {
//                            },
//                            close : function() {
//                            	window.location.href=basePath+'logined/outCallTask/outCallTaskList.jsp';
//                            }
//                        });
//                    } else {
//                        art.dialog.alert("操作失败！");
//                    }
//                }
//            });
//        }
//    });
}


/**
 * 获得后台首页中的受理工单列表
 * */
function getCustomerCallLogList(){
	$('#customerLog').dataTable({
		"bProcessing" : true,
		'bServerSide' : true,
		'fnServerParams' : function(aoData) {
			aoData.push({
				"name" : "customerCallLog.searchScope",
				"value" :  1
			},{
				"name" : "customerCallLog.state",
				"value" :  1
			},{
				"name" : "customerCallLog.searchKey",
				"value" :  ""
			},{
				"name" : "customerCallLog.endTime",
				"value" :  ""
			},{
				"name" : "customerCallLog.beginTime",
				"value" :  ""
			},{
				"name" : "showRowCount",
				"value" :  cclShow
			},{
				"name" : "seatWelcome",
				"value" :  "true"
			},{
				"name" : "fromPage",
				"value" : "back"
			},{
				"name" : "iscomplete",
				"value" : "candidate"
			});
		},
		"sAjaxSource" : basePath + "jbpmworkorder/list.action",
		"sServerMethod" : "POST",
		"sPaginationType" : "full_numbers",
		"bPaginate" : false, // 翻页功能
		"bStateSave" : false, // 状态保存
		"bLengthChange" : false, // 改变每页显示数据数量
		"bFilter" : false, // 过滤功能
		"bSort" : false, // 排序功能
		"bInfo" : false,// 页脚信息
		"bAutoWidth" : false,// 自动宽度
		"bDestroy" : true,//用于当要在同一个元素上履行新的dataTable绑订时，将之前的那个数据对象清除掉，换以新的对象设置
		"iDisplayLength" : 5, // 每页显示多少行
		"aoColumns" : [{
					"mDataProp" : "orderNumber" //序号0
				}, {
					"mDataProp" : null //工单编号1
				}, {
					"mDataProp" : "typeStr" //工单类型2
				}, {
					"mDataProp" : "stateStr" //工单状态3
				}, {
					"mDataProp" : "recordTimeStr" //受理时间4
				}, {
					"mDataProp" : "recordUserIdName", //受理人员6
					"sClass":"longTxt"
				}, {
					"mDataProp" : null, //操作7
					"sClass":"right_bdr0"
				}],
		"oLanguage": {
			   "sUrl": basePath+"plugins/datatable/cn.txt" //中文包
		   },
		"fnDrawCallback": function (oSettings) {
			   $('#customerLog tbody  tr td,#customerLog tbody  tr td a').each(function() {
				   if($(this).text() != "正在加载数据"){
					   this.setAttribute('title', $(this).text());
				   }else{
					   $(this),text().css("color","gray");
				   }
			   });
		   },
		"fnFooterCallback": function( nFoot, aData, iStart, iEnd, aiDisplay ) {
				//nFoot.getElementsByTagName('th')[0].innerHTML = "Starting index is "+iStart;
				//alert("aData.length:  "+aData.length); // 打印该页显示多少行记录。
				var Pagecount=aData.length; //在这里这个没有用到。
		   },
		"aoColumnDefs" : [
		   { "aTargets" : [1], //工单编号1
					 "fnRender" : function(oObj) {
						 	var vid = oObj.aData.vid;
						 	var fp = oObj.aData.toPage;
						 	var cclSn = oObj.aData.cclSn;
						 	var instanceId = oObj.aData.instanceId;
//							var mystate = oObj.aData.state;// 工单的状态
							
							var html='';
							
							if (fp !== "prev") {
//										if (mystate == 1 || mystate == 3) { // 对于已受理和待办结的单子，
//											// 可以处理
//											html = "<a  href='javascript:void(0);' onclick='customerCallDeal("
//													+ vid + ");'>" + cclSn + "</a>";
//											
//										} else { // 对于待回访和已办结的单子，只能呢查看
//											html = "<a  href='javascripcustomerCallViewBackt:void(0);' onclick='customerCallViewBack(" + vid + ");'>" + cclSn + "</a>";
//										}
								//超级管理员、普通管理员点击工单编号，只能查看，不能处理
								html = "<a  href='javascript:void(0);' onclick=\"customerCallViewBack(" + vid + ",'"+instanceId+"');\">" + cclSn + "</a>";
								
							}  else{
								html = "<td>" + cclSn + "</td>";
							}
							 return html;
					 	}
			}             
		    ,{
			"aTargets" : [6], //操作
			"fnRender" : function(oObj) {
				var instanceId = oObj.aData.instanceId;
				var vid = oObj.aData.vid;
				var processId = oObj.aData.processId;
				var operateUrl = oObj.aData.operateUrl+"?instanceId="+encodeURI(instanceId)+"&vid="+vid+"&_clientType=wap&processId="+processId;
				var operateName = oObj.aData.operateName;
				return "<a href='"+basePath+operateUrl+"'>"+operateName+"</a>";
			}
		}]
	}); 
}

/**
 * 后台工单详情
 */
function customerCallViewBack(vid,instanceId) {
	window.location.href = basePath
			+ "jbpmworkorder/customerCallViewBack.action?vid=" + vid+"&instanceId="+encodeURI(instanceId);
}

/**
 * 工单删除
 */
function delCustomerCallLog(vid) {

	// alert(basePath);
	art.dialog.confirm('确定要删除该工单吗？', function() {
		$.ajax( {
			url : basePath + "jbpmworkorder/delCustomerCallLog.action?vid="
					+ vid,
			type : "post",
			dataType : 'text',
			// data : vid,
			success : function(data) {
				if (data == 1) {
					art.dialog.alert('删除成功！',function(){
						window.location.reload();
					});
					
				} else {
					art.dialog.alert('删除失败！');
				}
			}
		});
	}, function() {
		return;
	});

}

/**
 * 后台公告栏列表
 */
function getNotice() {
	var load = '<li title="load">正在加载数据...</li>';
	$("#notice").html(load);
	var paramData = {
			"columnId":columnId,//栏目类型
			"notifyType":0,
			"isShowOut":1
//			"rowCount":noticeShow
		};
		$.ajax({
			url : basePath + "notify/notify_viewList.action",
			type : "post",
			dataType :'text',
			data:paramData,
			success : function(data) {
				var jsonData = eval('('+data+')');
//				jsonData = data;
				var arr=jsonData.aaData;
				var html='';
//				alert(arr.length+"  "+jsonData.length);
				if(arr.length>0){
					for(i=0;( (i < arr.length) && (i < noticeShow) );i++){
						var id = arr[i].notifyId;
						var title= arr[i].subject;
						var ulWidth = $("#notice").width();
						var liWidth = ulWidth-200;
						var subTitle = "";
						var counting = arr[i].counting;
						var fontWeight = "";
						if(counting == 0){
							fontWeight = "font-weight:bold;";
						}
						if(title.length*10>liWidth){
							var count = liWidth/10;
							subTitle = title.substring(0,count)+"...";
						}else{
							subTitle = title;
						}
						var isTop= arr[i].isTop;
						
						if(i%2!=0){
							if(isTop==1){
								html += '<li class="gray" title="'+title+'"><a style="'+fontWeight+'" href="javascript:rediectNoticeDetain('+id+');" title="'+title+'"><font class="red">[置顶]</font>'+subTitle+'</a></li>';
							}else{
								html += '<li class="gray" title="'+title+'"><a style="'+fontWeight+'" href="javascript:rediectNoticeDetain('+id+');"  title="'+title+'">'+subTitle+'</a></li>';
							}
						}else{
							if(isTop==1){
								html += '<li title="'+title+'"><a style="'+fontWeight+'" href="javascript:rediectNoticeDetain('+id+');" title="'+title+'" ><font class="red">[置顶]</font>'+subTitle+'</a></li>';
							}else{
								html += '<li title="'+title+'"><a style="'+fontWeight+'" href="javascript:rediectNoticeDetain('+id+');"  title="'+title+'" >'+subTitle+'</a></li>';
							}
						}
					}
				}else{
					html += '<li style="color:gray">没有检索到数据</li>';
				}
				$("#notice").html(html);
			}
		});
}
/**
 * 公告详情
 * @param id
 */
function rediectNoticeDetain(id) {
	window.location.href=basePath+"logined/notify/view.jsp?notifyId="+id+"&columnId="+columnId+"&returnType=1";
}

/**
 * 知识库列表
 */
function getKnowledge(){
	var load = '<li title="load">正在加载数据...</li>';
	$("#knowledge").html(load);
	var paramData = {
			//iDisplayStart / iDisplayLength + 1;
			"knowledge.knowledgeType.vid":"",
			"knowledge.search":"",
			"knowledge.auditSign":2,
			"iDisplayStart":1,
			"iDisplayLength":5
		};
		var html='';
		$.ajax({
			url : basePath + "knowledge/knowledge_getTable.action",
			type : "post",
			dataType :'text',
			data:paramData,
			success : function(data) {
				if(data != "") {
					//alert(data);
					jsonData = eval('('+data+')');
					var arr=jsonData.aaData;
					
					var ulWidth = $("#knowledge").width();
					var liWidth = ulWidth-200;
					if(arr.length>0){
						for(i=0;i<arr.length;i++){
							var vid= arr[i].vid;
							var title= arr[i].title;
							var subTitle = "";
							if(title.length*10>liWidth){
								var count = liWidth/10;
								subTitle = title.substring(0,count)+"...";
							}else{
								subTitle = title;
							}
							if(i%2!=0){
//								html += '<li class="gray" title="'+title+'"><a href="'+basePath+'knowledge/toViewOrUpdate.action?fromPage=backWelcome&isWelcome=0&knowledge.vid='+vid+'" title="'+title+'">'+subTitle+'</a></li>';
								html += '<li class="gray" title="'+title+'"><a href="'+basePath+'knowledge/knowledge_toViewOrUpdate.action?isToView=0&fromPage=backWelcome&knowledge.vid='+vid+'" title="'+title+'" style="color:#369;">'+subTitle+'</a></li>';
							}else{
								html += '<li title="'+title+'"><a href="'+basePath+'knowledge/knowledge_toViewOrUpdate.action?isToView=0&fromPage=backWelcome&knowledge.vid='+vid+'" title="'+title+'" style="color:#369;">'+subTitle+'</a></li>';
							}
						}
					}else{
						html += '<li style="color:gray">没有检索到数据</li>';
					}
					$("#knowledge").html(html);
					//return '<a href="'+basePath+'knowledge/toViewOrUpdate.action?isBeforeOrAfter=1&isToView=0&knowledge.vid='+vid+'">'+title+'</a>';
					
				} else {
					html += '<li style="color:gray">没有检索到数据</li>';
					$("#knowledge").html(html);
				}
			}
		});
}
