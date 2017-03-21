/**
 * 登录js
 */
$(document).ready(function() {
	// 保存按钮绑定事件
	$("#addCountdown").bind("click", function() {
				addCountdown();
			});

	// 保存按钮绑定事件
	$("#goback").bind("click", function() {
		window.document.location = basePath
				+ "logined/calendar/countdown/search.jsp";
	});
});

function addCountdown() {
	// 框架校验
	if (!validator(document.getElementById("form1"))) {
		return;
	}
	var countdownId = $("#countdownId").val();
	var content = $("#content").val();
	var endTime = $("#endTime").val();
	var backgroundColor = $("#backgroundColor").val();// 任务类型
	var orderNo = $("#orderNo").val();// 任务状态
	var paramData = {
		'countdown.content' : content,
		'countdown.endTime' : endTime,
		'countdown.backgroundColor' : backgroundColor,
		'countdown.orderNo' : orderNo,
		'countdownId' : countdownId
	};

	var urlStr = basePath + "calendar/task_addCountdown.action";

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
							   content:"添加成功。",
							   width : 317,
							   height : 109,
							   icon:"succeed",
							   opacity:0.3,
							   lock:true,
							   ok:function(){},
							   close:function(){
								   var url = basePath
									+ "logined/calendar/countdown/search.jsp";
								   window.document.location = url;
							   }
							});
					} else if (data == 1) {
						art.dialog({
							   title:"消息",
							   content:"修改成功。",
							   width : 317,
							   height : 109,
							   icon:"succeed",
							   opacity:0.3,
							   ok:function(){},
							   close:function(){
								   var url = basePath
									+ "logined/calendar/countdown/search.jsp";
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
