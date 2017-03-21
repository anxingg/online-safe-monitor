$(document).ready(function() {
	_checkedIds="";
	// 保存按钮绑定事件
	$("#search").bind("click", function() {
				$
						.removeTableCookie('SpryMedia_DataTables_Table_calendarList.jsp');
				getList();
				$("#searchDiv").css("display", "none");
				$("#tableDiv").css("display", "block");
			});

	// 导出按钮绑定事件
	$("#export").bind("click", function() {
				exportDaily();
			});

	// 头部全选复选框
	$("#Table").delegate("#total", "click", function(event) {
				checkTotal();
				event.stopPropagation();
			});
	// 删除
	$(".delete").live("click", function() {
//				var cks = $("input[name='ck']:checked");

				if (_checkedIds==null||_checkedIds=="") {
					art.dialog.alert("请选择待删除的信息! ");
				} else {
					art.dialog.confirm('确认要删除吗?', function() {
								deleteObject();
							}, function() {

							});
				}
			});

	// 导出按钮绑定事件
	$("#returnback").bind("click", function() {
				returnback();
			});

	// 返回按钮绑定事件
	$("#goback").bind("click", function() {
				goback();
			});

	// 列表详情页面返回列表页面
	setTimeout("gobackType()", 100);
});

function gobackType() {
	var gobackType = $("#gobackType").val();
	if (gobackType == 1) {
		jQuery("#search").trigger("click");
	}
}

// 处理事件的函数
function FSubmit(e) {
	if (e == 13) {
		// 获取search内容
		jQuery("#search").trigger("click");
		e.returnValue = false;
		// 返回false，在没有使用document.loginForm.submit()时可用于取消提交
	}
}

/**
 * 头部全选记录
 */
function checkTotal() {
	var isTotalCheck = $("input:checkbox[id='total']").prop("checked");
	var checkNum = 0;
	if (isTotalCheck) {
		$("input:checkbox[name='ck']").prop("checked", function(i, val) {
					checkNum = checkNum + 1;
					return true;
				});
	} else {
		$("input:checkbox[name='ck']").prop("checked", false);
	}
}

/**
 * 选择记录
 */
function check() {
	var checkTotalNum = $("input:checkbox[name='ck']");
	var isAllChecked = true;
	checkTotalNum.each(function(i, val) {
				if ($(checkTotalNum[i]).prop("checked")) {
				} else {
					isAllChecked = false;
				}
			});
	if (!isAllChecked) {
		$("input:checkbox[id='total']").prop("checked", false);
	} else {
		$("input:checkbox[id='total']").prop("checked", true);
	}
}

/**
 * 批量删除
 * 
 * @return
 */
function deleteObject() {
//	var cks = $("input[name='ck']:checked");
//
//	if (cks.length == 0) {
//		art.dialog.alert("请选择待删除的信息! ");
//		return;
//	}
	var deleteIds = "";

	if (_checkedIds!=null&&_checkedIds!="") {
		deleteIds = _checkedIds.substring(0, _checkedIds.length - 1);
	}
	else
	{
		art.dialog.alert("请选择待删除的信息! ");
		return;
	}
	$.ajax({
				url : basePath
						+ "calendar/calendar_deleteList.action?deleteIds="
						+ deleteIds,
				type : "post",
				dataType : "html",
				success : function(data) {
					if (data == 0) {
						art.dialog.alert("删除成功！", function() {
									getList();
								});

					} else {
						art.dialog.alert("删除失败！");
					}
				}
			});
}

/**
 * 查询类型修改
 * 
 * @return
 */
function searchTypeChange() {
	var searchType = $("input[name='searchType']:checked").val();
	if (searchType == 1) {
		$("#userIds").val($("#userId").val());
	} else if (searchType == 2) {
		$("#userIds").val(0);// 0：代表查询全部人员的日志
	}
}
/**
 * 查询日志获取列表
 */
function getList() {
	_checkedIds="";
	$("#gobackType").val("1");
	$('#Table').dataTable({
		"bDestroy" : true,
		"bProcessing" : true,
		"bStateSave" : true, // 状态保存
		'bServerSide' : true,
		'fnServerParams' : function(aoData) {
			aoData.push({
						"name" : "calendarBeanVo.begTime",
						"value" : $("#begTime").val()
					}, {
						"name" : "calendarBeanVo.endTime",
						"value" : $.trim($("#endTime").val())
					}, {
						"name" : "calendarBeanVo.calLevel",
						"value" : $("#calLevel").val()
					}, {
						"name" : "calendarBeanVo.workType",
						"value" : $("#workType").val()
					}, {
						"name" : "calendarBeanVo.content",
						"value" : $("#content").val()
					});
		},
		"sAjaxSource" : basePath
				+ "calendar/calendar_getCalendarListNew.action",
		"sPaginationType" : "full_numbers",
		"bPaginate" : true, // 翻页功能
		"bLengthChange" : false, // 改变每页显示数据数量
		"bFilter" : false, // 过滤功能
		"bSort" : false, // 排序功能
		"bInfo" : true,// 页脚信息
		"bAutoWidth" : false,// 自动宽度
		"iDisplayLength" : 10, // 每页显示多少行
		"aoColumns" : [{
			"sTitle" : '<input class="inptradio" type="checkbox"  id="total"/>',
			"mDataProp" : null
		}, {
			"sTitle" : '开始时间',
			"mDataProp" : "begTimeStr"
		}, {
			"sTitle" : '结束时间 ',
			"mDataProp" : "endTimeStr"
		}, {
			"sTitle" : '事务类型',
			"mDataProp" : null
		}, {
			"sTitle" : '事务内容',
			"mDataProp" : "content",
			"sClass" : "longTxt"
		}, {
			"sTitle" : '安排人',
			"mDataProp" : "createUserName"
		}],
		"oLanguage" : {
			"sUrl" : basePath + "plugins/datatable/cn.txt" // 中文包
		},
		"fnDrawCallback" : function(oSettings) {
			$('#Table tbody  tr td[class="longTxt"]').each(function() {
				this.setAttribute('title', $(this).text());
			});
			_getChecked();
		},
		"fnInitComplete" : function() {
			// 重置iframe高度
			window.parent.frameResize();
		},
		"aoColumnDefs" : [{
			"aTargets" : [0],
			"fnRender" : function(oObj) {
				var vid = oObj.aData.calendarId;
				return '<input type="checkbox" value="' + vid
						+ '" name="ck"  onclick="check();" />';
			}
		}, {
			"aTargets" : [3],
			"fnRender" : function(oObj) {
				var workType = oObj.aData.workType;
				var workTypeName = "";
				if (workType == 1) {
					workTypeName = "工作事务";
				} else {
					workTypeName = "个人事务";
				}
				return workTypeName;
			}
		}, {
			"aTargets" : [4],
			"fnRender" : function(oObj) {
				var vid = oObj.aData.calendarId;
				var content = oObj.aData.content;
				return '<a href="javascript:void(0);" class="view_url" onclick="calendarView('
						+ vid + ')" id="openShow">' + content + '</a>';
			}
		}

		]
	});
}

/**
 * id :日志ID
 * 
 * @return 日志详细信息
 */
function calendarView(calendarId) {
	var url = basePath + "calendar/toCalendarView.action?calendarId="
			+ calendarId;
	// art.dialog.data('taskId',taskId);

	art.dialog.open(url, {
				lock : true,
				opacity : 0,
				title : '日程详情',
				 width : 800,
				 height : 450,
				lock : true,
			    opacity: 0.08
			});
}

/**
 * id :日志ID
 * 
 * @return 日志详细信息
 */
function dailyView(id) {
	window.top.closeTab('dailyView');
	window.top.addTab('dailyView', basePath
					+ 'daily/getDailyView.action?doUpdateType=2&dailyId=' + id,
			'日志详情', 1, '');
}

function exportDaily() {
	// 导出文件
	// var exportUrl = '<a
	// href="'+basePath+"address/s.action?addressVo.addressGroupId="+id+'"
	// >[导出]</a>';
	var searchType = $("input[name='searchType']:checked").val();
	var startTime = $.trim($("#startTime").val());
	var userIds = $("#userIds").val();
	var userId = $("#userId").val();
	var endTime = $("#endTime").val();
	var type = $("#type").val();
	var str1 = $("#str1").val();
	var str2 = $("#str2").val();
	var str3 = $("#str3").val();
	var attachName = $("#attachName").val();
	document.location = basePath
			+ "address/setup_exportDaily.action?dailySearch.searchType="
			+ searchType + "&dailySearch.startTime=" + startTime
			+ "&dailySearch.endTime=" + endTime + "&dailySearch.type=" + type
			+ "&dailySearch.userIds=" + userIds + "&dailySearch.str1=" + str1
			+ "&dailySearch.str2=" + str2 + "&dailySearch.str3=" + str3
			+ "&dailySearch.attachName=" + attachName + "&dailySearch.userId="
			+ userId;
}

/**
 * 返回搜索页面
 * 
 * @return
 */
function returnback() {
	// $("#searchDiv").css("display","block");
	// $("#tableDiv").css("display","none");
	var url = basePath + "logined/calendar/myCalendar/calendarList.jsp";
	window.document.location = url;
}

/**
 * id :日志ID
 * 
 * @return 日志详细信息
 */
function dailyReadUsers(dailyId) {
	var url = basePath + "logined/daily/dailyReadUsers.jsp";

	art.dialog.data('dailyId', dailyId);
	art.dialog.open(url, {
				lock : true,
				opacity : 0,
				title : '阅读人员',
				 width : 800,
				 height : 450,
				lock : true,
			    opacity: 0.08
			});
}

/**
 * 返回
 * 
 * @return
 */
function goback() {
	var url = basePath + "logined/daily/latestDailyList.jsp";
	window.document.location = url;
}
