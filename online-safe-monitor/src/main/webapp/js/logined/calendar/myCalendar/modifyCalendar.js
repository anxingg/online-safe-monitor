$(document).ready(function() {
			getCalendarInfo();
			setAffairCheck("日程安排", "isRemaind", "isRemaindTr");
		})

function changeColor(obj) {
	var calLevelStr = '';
	if (obj == 0) {
		calLevelStr = '<a href="#" class="m_btn">未指定</a>';
		$("#calLevel").val(0);
	} else if (obj == 1) {
		calLevelStr = '<a style="color: red" href="#">重要/紧急</a>';
		$("#calLevel").val(1);
	} else if (obj == 2) {
		calLevelStr = '<a style="color:#FF9933" href="#">重要/不紧急</a>';
		$("#calLevel").val(2);
	} else if (obj == 3) {
		calLevelStr = '<a style="color: #0F0;" href="#">不重要/紧急</a>';
		$("#calLevel").val(3);
	} else if (obj == 4) {
		calLevelStr = '<a class="gray_9" href="#">不重要/不紧急</a>';
		$("#calLevel").val(4);
	}
	$("#cal_level").html(calLevelStr);
}

function getCalendarInfo() {
	var id = $("#id").val();
	dataParam = {
		'calendarBeanVo.calendarId' : id
	};
	$.ajax({
				type : 'post',
				url : basePath + "calendar/getCalendarInfo.action",
				data : dataParam,
				dataType : 'json',
				async : false,
				success : function(data) {
					// $("#calType").val(data.calType);
					changeColor(data.calLevel);
					$("#begTime").val(data.begTimeStr);
					$("#endTime").val(data.endTimeStr);
					$("#workType").val(data.workType);
					$("#takerName").val(data.takerName);
					$("#taker").val(data.takerId);
					$("#ownerName").val(data.ownerName);
					$("#ownerId").val(data.ownerId);
					$("#days").val(data.days);
					$("#hours").val(data.hours);
					$("#minutes").val(data.minutes);
					$("#content").val(data.content);
				}
			});
}

function modifyCalendar() {
	var calendarId = $("#id").val();
	var calType = $("#calType").val();
	var calLevel = $("#calLevel").val();
	var begTime = $("#begTime").val();
	var endTime = $("#endTime").val();
	var workType = $("#workType").val();
	var content = $("#content").val();
	var taker = $("#taker").val();
	var owner = $("#owner").val();
	var days = $("#days").val();
	var hours = $("#hours").val();
	var minutes = $("#minutes").val();
	var isRemaind = $("#isRemaind")[0].checked;
	if (content == null || content == "") {
		showObjError($("#content"), 'calendar.calendar_content_not_null');
		return false;
	} else {
		hideError($("#content"));
	}
	art.dialog.confirm(sprintf("calendar.confirm_modify_info"), function() {
				$.ajax({
							url : basePath
									+ "calendar/modifyCalendarBean.action",
							data : {
								'calendarBeanVo.calendarId' : calendarId,
								'calendarBeanVo.calType' : calType,
								'calendarBeanVo.calLevel' : calLevel,
								'calendarBeanVo.begTime' : begTime,
								'calendarBeanVo.endTime' : endTime,
								'calendarBeanVo.workType' : workType,
								'calendarBeanVo.content' : content,
								'calendarBeanVo.taker' : taker,
								'calendarBeanVo.owner' : owner,
								'calendarBeanVo.days' : days,
								'calendarBeanVo.hours' : hours,
								'calendarBeanVo.minutes' : minutes,
								'calendarBeanVo.isRemaind' : getVal(isRemaind)
							},
							type : 'post',
							dataType : 'json',
							async : false,
							beforeSend:function(){
								$("body").lock();
						    },
							complete:function(){
								$("body").unlock();
							},
							success : function(data) {
								if(data=="修改失败！"){
									art.dialog.alert(data);
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
										   }
										});
								}
								// window.location.href=basePath+'logined/calendar/myCalendar/myCalendar.jsp';
							}
						});
			}, function() {
				return;
			});
}

function getVal(obj) {
	if (obj) {
		return 1;
	}
	return 0;
}

function guanbi() {
	window.location.href = basePath
			+ 'logined/calendar/myCalendar/myCalendar.jsp';
}

/**
 * 添加按钮
 * 
 * @param obj
 * @return
 */
function selectAuthor(obj) {
	if (obj == 'taker') {
		openSelectUser(3, selectTakerCallBack, null, $("#taker").val());
	} else if (obj == 'owner') {
		openSelectUser(3, selectOwnerCallBack, null, $("#owner").val());
	}
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
 * 添加按钮(回调函数)
 * 
 * @param data
 * @return
 */
function selectOwnerCallBack(data) {
	var ids = '';
	var names = '';
	data.forEach(function(value, key) {
				ids += value.Id + ',';
				names += value.Name + ',';
			});
	$("#owner").val(ids);
	$("#ownerName").val(names);
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
	} else if (obj == 'owner') {
		$("#ownerId").val('');
		$("#ownerName").val('');
	}
}

/**
 * 验证数字格式
 * 
 * @param obj
 * @return
 */
function validateNum(obj, type) {
	var val = obj.value;
	obj.value = val.replace(/[^\d]/g, '');
	if (type == 'day') {
		if (val > 365) {
			showObjError($("#days"), 'calendar.calendar_days_scope');
			return;
		} else {
			hideError($("#days"));
		}
	} else if (type == 'hours') {
		if (val > 24) {
			showObjError($("#hours"), 'calendar.calendar_hours_scope');
			return;
		} else {
			hideError($("#hours"));
		}
	} else if (type == 'minutes') {
		if (val > 59) {
			showObjError($("#minutes"), 'calendar.calendar_minutes_scope');
			return;
		} else {
			hideError($("#minutes"));
		}
	}
}