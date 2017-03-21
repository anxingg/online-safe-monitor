

$(document).ready(function() {
			if ($("#type").val() == "new") {
				// 清楚cookie
				$.removeTableCookie('SpryMedia_DataTables_Table_affair.jsp');
			}
			getAffairList();
			// 保存按钮绑定事件
			$("#search").bind("click", function() {
						$
								.removeTableCookie('SpryMedia_DataTables_Table_affair.jsp');
						getAffairList();
						$("#searchDiv").css("display", "none");
						$("#returnback").css("display", "");
					});
			$("#returnback").bind("click", function() {
						$("#returnback").css("display", "none");
						$("#searchDiv").css("display", "block");
					});
			
			//回车事件
			$(document).keydown(function(event){
				var code=event.which;
				if (code == 13) {
					$.removeTableCookie('SpryMedia_DataTables_Table_affair.jsp');
					getAffairList();
					$("#searchDiv").css("display", "none");
					$("#returnback").css("display", "");
					return false;
				}
			});
		})

/**
 * 获取未读公告列表
 * 
 * @return
 */
function getAffairList() {
	var beginTime = $.trim($("#beginTime").val());
	var endTime = $.trim($("#endTime").val());
	if ((beginTime != null && beginTime != '')
			&& (endTime != null && endTime != '')) {
		if (beginTime > endTime) {
			showObjError($("#endTime"), 'calendar.calendar_less_than_begDate');
			return;
		}
	}
	$('#myTable').dataTable({
		"bDestroy" : true,
		"bProcessing" : true,
		"bStateSave" : true, // 状态保存
		'bServerSide' : true,
		'fnServerParams' : function(aoData) {
			aoData.push({
						"name" : "affairBeanVo.beginTime",
						"value" : $.trim($("#beginTime").val())
					}, {
						"name" : "affairBeanVo.endTime",
						"value" : $.trim($("#endTime").val())
					}, {
						"name" : "affairBeanVo.content",
						"value" : $.trim($("#content").val())
					});
		},
		"sAjaxSource" : basePath + "calendar/getAffairList.action",
		"sServerMethod" : "POST",
		"sPaginationType" : "full_numbers",
		"bPaginate" : true, // 翻页功能
		"bLengthChange" : false, // 改变每页显示数据数量
		"bFilter" : false, // 过滤功能
		"bSort" : false, // 排序功能
		"bInfo" : true,// 页脚信息
		"bAutoWidth" : false,// 自动宽度
		"iDisplayLength" : 10, // 每页显示多少行
		"aoColumns" : [{
					"sTitle" : '起始日期',
					"mDataProp" : "begTime"
				}, {
					"sTitle" : '结束日期',
					"mDataProp" : "endTime"
				}, {
					"sTitle" : '开始时间',
					"mDataProp" : "beginTimeTime"
				}, {
					"sTitle" : '结束时间',
					"mDataProp" : "endTimeTime"
				}, {
					"sTitle" : '提醒类型',
					"mDataProp" : "remindType"
				}, {
					"sTitle" : '提醒日期',
					"mDataProp" : "remindDate"
				}, {
					"sTitle" : '提醒时间',
					"mDataProp" : "remindTime"
				}, {
					"sTitle" : '事务内容',
					"mDataProp" : "content",
					"sClass" : "longTxt"
				}, {
					"sTitle" : '操作',
					"mDataProp" : null
				}],
		"oLanguage" : {
			"sUrl" : basePath + "plugins/datatable/cn.txt" // 中文包
		},
		"fnDrawCallback" : function(oSettings) {
			$('#myTable tbody  tr td[class="longTxt"]').each(function() {
				this.setAttribute('title', $(this).text());
			});

			$("#total").prop("checked", false);
		},
		"fnInitComplete" : function() {
			// 重置iframe高度
			window.parent.frameResize();
		},
		"aoColumnDefs" : [{
			"aTargets" : [7],
			"fnRender" : function(oObj) {
				return '<a style="text-decoration:none;color:#04578D;text-align:left;" onclick="viewAffair('
						+ oObj.aData.affairId
						+ ')" href="javascript:void(0);" >'
						+ oObj.aData.content + '</a>';
			}
		}, {
			"aTargets" : [8],
			"fnRender" : function(oObj) {
				var affairId = "'" + oObj.aData.affairId + "'";
				var authority = oObj.aData.authority;
				var switchStatus = '';
				var optionStr = '<a style="text-decoration:none;color:#04578D" href="javascript:modifyAffairBean('
						+ affairId
						+ ')">修改</a><a style="text-decoration:none;color:#04578D" href="javascript:deleteAffair('
						+ affairId + ')">删除</a>';
				if (authority == 'modify') {
					switchStatus = optionStr;
				} else if (authority == 'view') {
					// switchStatus = '<a
					// style="text-decoration:none;color:#04578D;text-align:left;"
					// target=”_blank"
					// href="'+basePath+'logined/calendar/myCalendar/viewAffairBean.jsp?affairId='
					// + oObj.aData.affairId + '">查看</a>';
					switchStatus = '<a style="text-decoration:none;color:#04578D;text-align:left;" onclick="viewAffair('
							+ oObj.aData.affairId
							+ ')" href="javascript:void(0);" >查看</a>';
				}
				return switchStatus;
			}
		}]
	});
}

function viewAffair(id) {
	var url = basePath
			+ 'logined/calendar/myCalendar/viewAffairBean.jsp?affairId=' + id;
	art.dialog.open(url, {
				lock : true,
				opacity : 0,
				title : '周期性事务详情',
				 width : 800,
				 height : 450,
				lock : true,
			    opacity: 0.08
			});
}
function modifyAffairBean(affairId) {
	window.location.href = basePath
			+ 'logined/calendar/myCalendar/modifyAffair.jsp?affairId='
			+ affairId;
}

function viewAffairBean(affairId) {
	window.location.href = basePath
			+ 'logined/calendar/myCalendar/viewAffairBean.jsp?affairId='
			+ affairId;
}

function deleteAffair(affairId) {
	dataParam = {
		'affairBeanVo.affId' : affairId
	};
	art.dialog.confirm(sprintf('calendar.confirm_delete_info'), function() {
				$.ajax({
							type : 'post',
							url : basePath + "calendar/deleteAffairBean.action",
							data : dataParam,
							dataType : 'json',
							success : function(data) {
								if(data=="删除失败！"){
									art.dialog.alert(data);
									getAffairList();
								}else{
									art.dialog({
										   title:"消息",
										   content:data,
										   width : 317,
										   height : 109,
										   icon:"succeed",
										   opacity:0.3,
										   lock:true,
										   ok:function(){},
										   close:function(){
											   getAffairList();
										   }
										});
								}
							}
						});
			}, function() {
				return;
			});
}

function addAffairBean() {
	window.location.href = basePath
			+ 'logined/calendar/myCalendar/addAffairBean.jsp';
}
