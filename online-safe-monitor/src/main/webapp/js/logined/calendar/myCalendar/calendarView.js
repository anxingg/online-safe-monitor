$(document).ready(function() {
			var status = $("#status").val();
			if (status == 0) {
				$("#statusButton").val("完成");
			} else if (status == 1) {
				$("#statusButton").val("进行中");
			}
		})

function statusChange() {
	var status = $("#status").val();
	// 修改按钮字样
	if (status == 0) {
		$("#statusButton").val("进行中");
		$("#status").val(1);
		$("#statusView").html("<font color='#00aa00'>完成</font>");
	} else if (status == 1) {
		$("#statusButton").val("完成");
		$("#status").val(0);
		$("#statusView").html("<font color='#0000ff'>进行中</font>");
	}

	var calendarId = $("#calendarId").val();
	var urlStr = basePath + "calendar/calendar_statusChange.action?calendarId="
			+ calendarId;
	$.ajax({
				url : urlStr,
				type : "post",
				dataType : 'json',
				success : function(data) {
				}
			});
}

// 修改日程
function openUpdate(id) {
	var modifyUrl = basePath
			+ 'logined/calendar/myCalendar/modifyCalendar.jsp?type=calendar&id='
			+ id;
	window.open(modifyUrl);
	return false;
}

// 删除
function deleteCalendar(id) {
	art.dialog.confirm('确认要删除吗？删除后不可恢复', function() {
				$.ajax({
							url : basePath
									+ "calendar/deleteCalendarBean.action",
							data : {
								'calendarBeanVo.calendarId' : id
							},
							type : 'post',
							dataType : 'json',
							async : false,
							success : function(data) {
								var win = art.dialog.open.origin;
								win.location.reload();
								art.dialog.close();

							}
						});
			}, function() {
				return;
			});
}
