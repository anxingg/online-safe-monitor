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
					$("#createUser").text("创建者：" + data.createUserName);
					$("#taker").text("参与者：" + data.takerName);
					$("#statusName").text(data.statusName);
					$("#ownerName").text("所属者：" + data.ownerName);
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
					$("#time").text(data.beginTime + " 至 " + data.endTime);
					$("#taker").text(data.takerName);
					$("#createUser").text(data.createUserName);
					$("#remind").text(data.remindTime);
					$("#content").text(data.content);
				}
			});
}
