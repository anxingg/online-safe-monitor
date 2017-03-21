$(document).ready(function() {
			getAffairInfo();
			setAffairCheck("日程安排", "isRemaind", "isRemaindTr");
		})

function updateAffairBean() {
	var affairId = $("#affairId").val();
	var beginTime = $("#beginTime").val();
	if (beginTime == null || beginTime == "") {
		beginTime = getCurrTime();
	}
	var endTime = $("#endTime").val();
	var beginTimeTime = $("#beginTimeTime").val();
	var endTimeTime = $("#endTimeTime").val();
	var type = $("#type").val();
	var remindType = $("#remindType").val();
	var remindTime = '';
	var remindDate = '';
	if (remindType == 1) {
		remindTime = $("#remindTime_day").val();
	} else if (remindType == 2) {
		remindTime = $("#remindTime_week").val();
		remindDate = $("#remindDate_week").val();
	} else if (remindType == 3) {
		remindTime = $("#remindTime_month").val();
		remindDate = $("#remindDate_month_day").val();
	} else if (remindType == 4) {
		remindTime = $("#remindTime_year").val();
		var remindDate_month = $("#remindDate_year_month").val();
		var remindDate_day = $("#remindDate_year_day").val();
		remindDate = remindDate_month + ',' + remindDate_day;
	} else if (remindType == 5) {
		remindTime = $("#remindTime_work_day").val();
	}
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
	if (content == null || content == '') {
		showObjError($("#content"), 'calendar.calendar_content_not_null');
		return;
	} else {
		hideError($("#content"));
	}

	dataParam = {
		'affairBeanVo.affId' : affairId,
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
	art.dialog.confirm(sprintf("calendar.confirm_modify_info"), function() {
				$.ajax({
							type : 'post',
							url : basePath + "calendar/modifyAffairBean.action",
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
								if(data=="修改失败！"){
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
		remindDate = $("#remindDate_month_day").val();
		remindTime = $("#remindTime_month").val();
	} else if (remindType == 4) {
		changeDivDisplay('year');
		var remindDate_month = $("#remindDate_year_month").val();
		var remindDate_day = $("#remindDate_year_day").val();
		remindDate = remindDate_month + ',' + remindDate_day;
		remindTime = $("#remindTime_year").val();
	} else if (remindType == 5) {
		changeDivDisplay('work_day');
		remindTime = $("#remindTime_work_day").val();
	}
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

function getVal(obj) {
	if (obj) {
		return 1;
	}
	return 0;
}

function getAffairInfo() {
	var affId = $("#affairId").val();
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
					$("#beginTime").val(data.beginTime);
					$("#endTime").val(data.endTime);
					$("#beginTimeTime").val(data.beginTimeTime);
					$("#endTimeTime").val(data.endTimeTime);
					$("#type").val(data.type);
					$("#remindType").val(data.remindType);
					$("#content").val(data.content);
					$("#taker").val(data.taker);
					$("#takerName").val(data.takerName);
					if (data.isRemaind == 1) {
						document.getElementById("isRemaind").checked = true;
					} else if (data.isRemaind == 0) {
						document.getElementById("isRemaind").checked = false;
					}
					var remindTime = data.remindTime;
					var remindDate = data.remindDate;
					if (data.remindType == 1) {
						changeDivDisplay('day');
						$("#remindTime_day").val(remindTime);
					} else if (data.remindType == 2) {
						changeDivDisplay('week');
						$("#remindDate_week").val(remindDate);
						$("#remindTime_week").val(remindTime);
					} else if (data.remindType == 3) {
						changeDivDisplay('month');
						$("#remindDate_month_day").val(remindDate);
						$("#remindTime_month").val(remindTime);
					} else if (data.remindType == 4) {
						changeDivDisplay('year');
						var remindDateArr = remindDate.split(",");
						if (remindDateArr.length == 2) {
							$("#remindDate_year_month").val(remindDateArr[0]);
							$("#remindDate_year_day").val(remindDateArr[1]);
						}
						$("#remindTime_year").val(remindTime);
					} else if (data.remindType == 5) {
						changeDivDisplay('work_day');
						$("#remindTime_work_day").val(remindTime);
					}
				}
			});
}

/**
 * 获取当前日期
 * 
 * @return
 */
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
	return year + "-" + month + "-" + day;
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