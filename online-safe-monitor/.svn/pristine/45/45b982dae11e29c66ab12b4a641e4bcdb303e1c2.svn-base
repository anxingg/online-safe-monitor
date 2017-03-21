$(document).ready(function() {
	_checkedIds="";
			if ($("#type").val() == "new") {
				// 清楚cookie
				$.removeTableCookie('SpryMedia_DataTables_Table_search.jsp');
			}
			getTaskList();
			// 保存按钮绑定事件
			$("#search").bind("click", function() {
						getTaskList();
						$("#searchDiv").css("display", "none");
						$("#gobackDiv").css("display", "block");
					});

			// 保存按钮绑定事件
			$("#goback").bind("click", function() {
						window.location.reload();
					});

			// 保存按钮绑定事件
			$(".add").bind("click", function() {
						window.location = basePath
								+ "logined/calendar/task/add.jsp";
					});

			// 头部全选复选框
			$("#Table").delegate("#total", "click", function(event) {
						checkTotal();
						event.stopPropagation();
					});
			// 删除任务
			$(".delete").live("click", function() {
//						var cks = $("input[name='ck']:checked");

						if (_checkedIds==null||_checkedIds=="") {
							art.dialog.alert("要删除任务,请至少选择其中的一项。 ");
							return;
						}
						art.dialog.confirm('确认要删除吗?', function() {
									deleteObject();
								}, function() {

								});
					});
			
			//回车事件
			$(document).keydown(function(event){
				var code=event.which;
				if (code == 13) {
					_checkedIds="";
					getTaskList();
					$("#searchDiv").css("display", "none");
					$("#gobackDiv").css("display", "block");
					return false;
				}
			});
			
		});

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
 * 删除任务
 * 
 * @return
 */
function deleteObject() {
//	var cks = $("input[name='ck']:checked");
//
//	if (cks.length == 0) {
//		art.dialog.alert("要删除任务,请至少选择其中的一项。 ");
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
				url : basePath + "calendar/task_deleteList.action?deleteIds="
						+ deleteIds,
				type : "post",
				dataType : "html",
				success : function(data) {
					if (data == 0) {
						art.dialog({
							   title:"消息",
							   content:"删除成功！",
							   width : 317,
							   height : 109,
							   icon:"succeed",
							   opacity:0.3,
							   lock:true,
							   ok:function(){},
							   close:function(){
									getTaskList();
							   }
							});
					} else {
						art.dialog.alert("删除失败！");
					}
				}
			});
}

/**
 * 查询日志获取列表
 */
function getTaskList() {
	_checkedIds="";
	$('#Table').dataTable({
		"bDestroy" : true,
		"bProcessing" : true,
		"bStateSave" : true, // 状态保存
		'bServerSide' : true,
		'fnServerParams' : function(aoData) {
			aoData.push({
						"name" : "taskVo.beginDate",
						"value" : $.trim($("#beginDate").val())
					}, {
						"name" : "taskVo.endDate",
						"value" : $("#endDate").val()
					}, {
						"name" : "taskVo.taskStatus",
						"value" : $("#taskStatus").val()
					}, {
						"name" : "taskVo.important",
						"value" : $("#important").val()
					}, {
						"name" : "taskVo.taskType",
						"value" : $("#taskType").val()
					}, {
						"name" : "taskVo.content",
						"value" : $("#content").val()
					});
		},
		"sAjaxSource" : basePath + "calendar/task_getTaskList.action",// 获取联系人列表
		"sPaginationType" : "full_numbers",
		"bPaginate" : true, // 翻页功能
		"bLengthChange" : false, // 改变每页显示数据数量
		"bFilter" : false, // 过滤功能
		"bSort" : false, // 排序功能
		"bInfo" : true,// 页脚信息
		"bAutoWidth" : false,// 自动宽度
		"iDisplayLength" : 10, // 每页显示多少行
		"aoColumns" : [{
					"sTitle" : '<input class="inptradio" type="checkbox" id="total"/>',
					"mDataProp" : null
				}, {
					"sTitle" : '排序号',
					"mDataProp" : "taskNo"
				}, {
					"sTitle" : '开始时间 ',
					"mDataProp" : "beginDate"
				}, {
					"sTitle" : '结束时间',
					"mDataProp" : "endDate"
				}, {
					"sTitle" : '任务类型 ',
					"mDataProp" : "taskType"
				}, {
					"sTitle" : '任务标题 ',
					"mDataProp" : "subject",
					"sClass" : "longTxt"
				}, {
					"sTitle" : '状态 ',
					"mDataProp" : "taskStatus"
				}, {
					"sTitle" : '任务内容 ',
					"mDataProp" : "content",
					"sClass" : "longTxt"
				}, {
					"sTitle" : '完成率',
					"mDataProp" : "rate"
				}, {
					"sTitle" : '操作 ',
					"mDataProp" : null
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
				var isReview = oObj.aData.isReview;
				var vid = oObj.aData.id;
				return '<input type="checkbox" value="' + vid
						+ '" name="ck" onclick="check();"/>';
			}
		}, {
			"aTargets" : [5],
			"fnRender" : function(oObj) {
				var vid = oObj.aData.id;
				var subject = oObj.aData.subject;
				return '<a href="javascript:void(0);" onclick="taskView(' + vid
						+ ');">' + subject + '</a>';
			}
		}, {
			"aTargets" : [8],
			"fnRender" : function(oObj) {
				var rate = oObj.aData.rate;
				return rate + '%';
			}
		}, {
			"aTargets" : [9],
			"fnRender" : function(oObj) {
				var vid = oObj.aData.id;
				var urlUpdate = basePath
						+ "calendar/toTaskUpdate.action?taskId=" + vid;
				return '<a href="'
						+ urlUpdate
						+ '" class="view_url" id="">修改</a><a href="javascript:void(0);" class="view_url" id="" onclick="delTask('
						+ vid + ');">删除</a>';
			}
		}]
	});
}

/**
 * id :日志ID
 * 
 * @return 日志详细信息
 */
function taskView(taskId) {
	var url = basePath + "logined/calendar/task/view.jsp";
	art.dialog.data('taskId', taskId);

	art.dialog.open(url, {
				lock : true,
				opacity : 0,
				title : '任务详情',
				 width : 800,
				 height : 450,
				lock : true,
			    opacity: 0.08
			});
}

function delTask(id) {
	var urlStr = basePath + "calendar/task_delTask.action?taskId=" + id;
	$.ajax({
				url : urlStr,
				type : "post",
				dataType : 'json',
				success : function(data) {
					if (data == 0) {
						art.dialog({
							   title:"消息",
							   content:"删除成功！",
							   width : 317,
							   height : 109,
							   icon:"succeed",
							   opacity:0.3,
							   lock:true,
							   ok:function(){},
							   close:function(){
								   var url = basePath
									+ "logined/calendar/task/search.jsp";
								   window.document.location = url;
							   }
							});
					} else {
						art.dialog.alert("删除失败！");
						// window.location.reload();
					}
				}
			});
}
