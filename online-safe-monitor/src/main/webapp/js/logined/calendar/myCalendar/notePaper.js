$(document).ready(function() {
			var type = $("#type").val();
			if (type == 'calendar') {
				getCalendarInfo();
			} else if (type == 'affair') {
				getAffairInfo();
			}
		})

function getCalendarInfo() {
	var calendarId = $("#id").val();
	dataParam = {
		'calendarBeanVo.calendarId' : calendarId
	};
	$.ajax({
				type : 'post',
				url : basePath + "calendar/getCalendarInfo.action",
				data : dataParam,
				dataType : 'json',
				async : false,
				success : function(data) {
					$("#time").text(data.begTime + " - " + data.endTime);
					$("#content").text(data.content);
				}
			});
}

function getAffairInfo() {
	var affId = $("#id").val();
	dataParam = {
		'affairBeanVo.affId' : affId
	};
	$.ajax({
				type : 'post',
				url : basePath + "calendar/getAffairBeanInfo.action",
				data : dataParam,
				dataType : 'json',
				async : false,
				success : function(data) {
					$("#time").text(data.beginTime + " " + data.endTime);
					$("#content").text(data.content);
					$("#remind").text(data.remindTime);
				}
			});
}