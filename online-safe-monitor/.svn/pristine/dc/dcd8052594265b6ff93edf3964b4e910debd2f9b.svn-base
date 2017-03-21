$(document).ready(function() {
			getCurrTime();
			changeDivDisplay('day');
			setAffairCheck("日程安排", "isRemaind", "isRemaindTr");
		})

function addAffairBean() {
	var beginTime = $("#beginTime").val();
	if (beginTime == null || beginTime == "") {
		beginTime = $("#currTime").val();
	}
	var endTime = $("#endTime").val();
	var beginTimeTime = $("#beginTimeTime").val();
	var endTimeTime = $("#endTimeTime").val();
	var type = $("#type").val();
	var remindType = $("#remindType").val();
	var remindTime = $("#remindTime").val();
	var remindDate = $("#remindDate").val();
	var content = $("#content").val();
	var taker = $("#taker").val();
	var isRemaind = document.getElementById("isRemaind").checked;
	if (endTime != null && endTime != '') {
		if (beginTime > endTime) {
			showObjError($("#endTime"), 'calendar.calendar_less_than_begDate');
			return;
		} else {
			hideError($("#endTime"));
		}
	}
	if (beginTimeTime != "" && endTimeTime != "") {
		if (beginTimeTime > endTimeTime) {
			showObjError($("#endTimeTime"),
					'calendar.calendar_less_than_begTime');
			return;
		} else {
			hideError($("#endTime"));
		}
	}
	if (content == null || content == '') {
		showObjError($("#content"), 'calendar.calendar_content_not_null');
		return;
	} else {
		hideError($("#content"));
	}

	var now = new Date();
	var hh = now.getHours(); // 时
	var mm = now.getMinutes(); // 分
	var ss = now.getSeconds();
	var rtime = "";
	if (hh < 10) {
		rtime += "0" + hh + ":";
	} else {
		rtime += hh + ":";
	}

	if (mm < 10) {
		rtime += "0" + mm + ":";
	} else {
		rtime += mm + ":";
	}

	if (ss < 10) {
		rtime += "0" + ss;
	} else {
		rtime += ss;
	}

	if (remindTime == "") {
		remindTime = rtime;
	}

	dataParam = {
		'affairBeanVo.beginTime' : beginTime,
		'affairBeanVo.endTime' : endTime,
		'affairBeanVo.beginTimeTime' : beginTimeTime,
		'affairBeanVo.endTimeTime' : endTimeTime,
		'affairBeanVo.type' : type,
		'affairBeanVo.remindType' : remindType,
		'affairBeanVo.remindTime' : remindTime,
		'affairBeanVo.remindDate' : remindDate,
		'affairBeanVo.content' : content,
		'affairBeanVo.taker' : taker,
		'affairBeanVo.isRemaind' : getVal(isRemaind)
	};
	art.dialog.confirm(sprintf("calendar.confirm_add_info"), function() {
				$.ajax({
							type : 'post',
							url : basePath + "calendar/addAffairBean.action",
							data : dataParam,
							dataType : 'json',
							async : false,
							beforeSend:function(){
								$("body").lock();
						    },
							complete:function(){
								$("body").unlock();
							},
							success : function(data) {
								if(data=="新增失败！"){
									art.dialog.alert(data);
									window.location.href = basePath
											+ 'logined/calendar/myCalendar/affair.jsp';
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
											   window.location.href = basePath
												+ 'logined/calendar/myCalendar/affair.jsp';
										   }
										});
								}
								
							}
						});
			}, function() {
				return;
			});
}

function changeRemindType() {
	var remindType = $("#remindType").val();
	var remindTime = '';
	var remindDate = '';
	if (remindType == 1) {
		changeDivDisplay('day');
		remindTime = $("#remindTime_day").val();
	} else if (remindType == 2) {
		changeDivDisplay('week');
		remindDate = $("#remindDate_week").val();
		remindTime = $("#remindTime_week").val();
	} else if (remindType == 3) {
		changeDivDisplay('month');
		remindDate = $("#remindDate_month_day").val() + '日';
		remindTime = $("#remindTime_month").val();
	} else if (remindType == 4) {
		changeDivDisplay('year');
		var remindDate_month = $("#remindDate_year_month").val();
		var remindDate_day = $("#remindDate_year_day").val();
		remindDate = remindDate_month + '月' + remindDate_day + '日';
		remindTime = $("#remindTime_year").val();
	} else if (remindType == 5) {
		changeDivDisplay('work_day');
		remindTime = $("#remindTime_work_day").val();
	}
	$("#remindTime").val(remindTime);
	$("#remindDate").val(remindDate);
}

function getCurrTime() {
	var date = new Date();
	var year = date.getFullYear();
	var month = date.getMonth() + 1;
	if (month < 10) {
		month = '0' + month;
	}
	var day = date.getDate();
	if (day < 10) {
		day = '0' + day;
	}
	var week = date.getDay();
	var dateStr = year + "-" + month + "-" + day;
	var hours = date.getHours();
	var minutes = date.getMinutes();
	var addMinutes = minutes;
	var hoursNext = hours + 1;
	if (addMinutes < 10) {
		addMinutes = '0' + addMinutes;
	}
	if (hours < 10) {
		hours = "0" + hours;
	}
	if (hoursNext < 10) {
		hoursNext = "0" + hoursNext;
	}
	if (hoursNext == 24) {
		hoursNext = "01";
	}
	var currTime = hours + ":" + minutes + ":" + "00";
	var addCurrTime = hours + ":" + addMinutes + ":" + "00";
	var nextTime = hoursNext + ":" + addMinutes + ":" + "00";
	$("#beginTime").val(dateStr);
	$("#currTime").val(dateStr);
	$("#beginTimeTime").val(addCurrTime);
	$("#endTimeTime").val(nextTime);
	$("#remindDate_week").val(week);
	$("#remindDate_month_day").val(parseInt(day));
	$("#remindDate_year_month").val(parseInt(month));
	$("#remindDate_year_day").val(parseInt(day));
	setRemindTime(addCurrTime);
}

function changeDivDisplay(obj) {
	var arr = ['day', 'week', 'month', 'year', 'work_day'];
	for (var i = 0; i < arr.length; i++) {
		if (obj == arr[i]) {
			document.getElementById(arr[i]).style.display = '';
		} else {
			document.getElementById(arr[i]).style.display = 'none';
		}
	}
}

function setRemindTime(remindTime) {
	var arr = ['remindTime_day', 'remindTime_week', 'remindTime_month',
			'remindTime_year', 'remindTime_work_day'];
	for (var i = 0; i < arr.length; i++) {
		document.getElementById(arr[i]).value = remindTime;
	}
}

function getVal(obj) {
	if (obj) {
		return 1;
	}
	return 0;
}

/**
 * 添加按钮
 * 
 * @param obj
 * @return
 */
function selectAuthor(obj) {
	openSelectUser(3, selectTakerCallBack, null, $("#taker").val());
}

/**
 * 添加按钮(回调函数)
 * 
 * @param data
 * @return
 */
function selectTakerCallBack(data) {
	var ids = '';
	var names = '';
	data.forEach(function(value, key) {
				ids += value.Id + ',';
				names += value.Name + ',';
			});
	$("#taker").val(ids);
	$("#takerName").val(names);
}

/**
 * 清空操作
 * 
 * @param obj
 * @return
 */
function clearAuthor(obj) {
	if (obj == 'taker') {
		$("#taker").val('');
		$("#takerName").val('');
	}
}