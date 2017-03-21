/**
 * 登录js
 */
$(document).ready(function() {
	// 保存按钮绑定事件
	$("#addTask").bind("click", function() {
				addTask();
			});

	// 保存按钮绑定事件
	$("#goback").bind("click", function() {
		window.document.location = basePath
				+ "logined/calendar/task/search.jsp";
	});
});

function addTask() {
	// 框架校验
	if (!validator(document.getElementById("form1"))) {
		return;
	}

	var taskId = $("#taskId").val();
	var taskNo = $("#taskNo").val();
	var subject = $("#subject").val();
	var taskType = $("#taskType").val();// 任务类型
	var taskStatus = $("#taskStatus").val();// 任务状态
	var color = $("#color").val();// 任务颜色
	var important = $("#important").val();// 重要程度
	var beginDate = $("#beginDate").val();// 开始时间
	var endDate = $("#endDate").val();// 结束时间
	var content = $("#content").val();// 内容
	if (beginDate != null && beginDate != "") {
		if (endDate != null && endDate != "") {
			if (beginDate > endDate) {
				showObjError($("#endDate"), 'task.task_startDate_endDate');
				return;
			}
		}
	}

	var rate = $("#rate").val();// 完成率
	var finishTime = $("#finishTime").val();// 完成时间
	var totalTime = $("#totalTime").val();// 总工作量
	var useTime = $("#useTime").val();// 已完成工作量
	var isSend = 0;// 是否发送事务短消息
	if ($("input[type='checkbox']").is(':checked')) {
		isSend = 1;
	}
	var remindTime = $("#remindTime").val();// 提醒时间
	if (isSend == 1) {
		// 验证文本编辑框
		if (null == remindTime || '' == remindTime) {
			showObjError($("#remindTime"), 'task.task_remindTime_not_null');
			return;
		} else {
			hideError($("#remindTime"));
		}
	}

	var paramData = {
		'task.taskNo' : taskNo,
		'task.subject' : subject,
		'task.taskType' : taskType,
		'task.taskStatus' : taskStatus,
		'task.color' : color,
		'task.important' : important,
		'task.beginDate' : beginDate,
		'task.endDate' : endDate,
		'task.content' : content,
		'task.rate' : rate,
		'task.finishTime' : finishTime,
		'task.totalTime' : totalTime,
		'task.useTime' : useTime,
		'time' : remindTime,
		'taskId' : taskId,
		'isSend' : isSend
	};

	var urlStr = basePath + "calendar/task_addTask.action";

	$.ajax({
				url : urlStr,
				type : "post",
				dataType : 'json',
				data : paramData,
				beforeSend:function(){
					$("body").lock();
			    },
				complete:function(){
					$("body").unlock();
				},
				success : function(data) {
					if (data == 0) {
						art.dialog({
							   title:"消息",
							   content:"添加成功！",
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
					} else if (data == 1) {
						art.dialog({
							   title:"消息",
							   content:"修改成功！",
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
						art.dialog.alert("添加失败！");
						// window.location.reload();
					}
				}
			});
}
