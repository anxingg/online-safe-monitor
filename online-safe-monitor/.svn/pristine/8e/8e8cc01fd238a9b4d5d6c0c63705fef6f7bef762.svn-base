/**
 * 我的任务详情
 */
$(document).ready(function() {
			$("#taskId").val(art.dialog.data('taskId'));
			getTaskView();

		});

/**
 * 任务详情
 * 
 * @return
 */
function getTaskView() {
	var taskId = $("#taskId").val();
	var paramData = {
		'taskId' : taskId
	};

	var urlStr = basePath + "calendar/task_getTaskView.action";

	$.ajax({
				url : urlStr,
				type : "post",
				dataType : 'json',
				data : paramData,
				success : function(data) {
					$("#beginTime").html(data.beginTime);
					$("#endTime").html(data.endTime);
					$("#rate").html(data.rate);
					$("#subject").html(data.subject);
					$("#content").html(data.content);
				}
			});
}

/**
 * 删除
 * 
 * @param id
 * @return
 */
function delTask() {
	var id = $("#taskId").val();
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

/**
 * 删除
 * 
 * @return
 */
function toTaskUpdate() {
	var id = $("#taskId").val();
	var url = basePath + "calendar/toTaskUpdate.action?taskId=" + id;
	window.document.location = url;
}

/**
 * 关闭
 * 
 * @return
 */
function closeWin() {
	var url = basePath + "logined/calendar/task/view.jsp";
	art.dialog.close(url);
}
