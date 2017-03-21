$(document).ready(function() {
	var calendar = $('#calendar').fullCalendar({
		theme : true,
		header : {
			left : 'prev,next today',
			center : 'title',
			right : 'month,agendaWeek,agendaDay'
		},
		buttonText : { // 按钮对应的文本
			prevYear : '去年', // 不建议改这个值，因为它本身是含[去年、上一周、前天]三个意思，你就让它默认
			nextYear : '明年', // 同上
			today : '今天',
			month : '月',
			week : '周',
			day : '日'
		},
		firstDay : 1,
		axisFormat : 'HH',
		timeFormat : '(HH:mm )',
		allDaySlot : true,
		monthNames : ['1月', '2月', '3月', '4月', '5月', '6月', '7月', '8月', '9月',
				'10月', '11月', '12月'], // 默认为英文月分，这里可以改成中文
		monthNamesShort : ['1月', '2月', '3月', '4月', '5月', '6月', '7月', '8月',
				'9月', '10月', '11月', '12月'],
		dayNames : ['周日', '周一', '周二', '周三', '周四', '周五', '周六'], // 短格式的星期
		dayNamesShort : ['周日', '周一', '周二', '周三', '周四', '周五', '周六'], // 短格式的星期
		titleFormat : { // 格式化标题
			month : 'yyyy年MMMM', // 如：September 2009
			week : "[yyyy] yyyy年MMMd日{'至'[MMM] d日}", // 如：Sep 7 - 13 2009
			day : 'yyyy年MMMd日, dddd' // 如：Tuesday, Sep 8, 2009
		},
		loading : function(bool) {
			if (bool)
				$('#loading').show();
			else
				$('#loading').hide();
		},
		allDayText : '跨天',
		selectable : true,
		selectHelper : true,
		select : function(start, end, allDay) {
			art.dialog.data('start', start);
			art.dialog.data('end', end);
			art.dialog.data('allDay', allDay);
			
			art.dialog.open(basePath
							+ 'logined/calendar/myCalendar/addCalendar.jsp', {
						title : '新建日程',
						 width : 800,
						 height : 250,
						init : function() {
							var iframe = this.iframe.contentWindow;
						},
						cancelVal : '关闭',
						lock : true,
					    opacity: 0.08,
						ok : function() {
							var iframe = this.iframe.contentWindow;
							var calType = $(iframe.document).find("#calType")
									.val();
							var calLevel = $(iframe.document).find("#calLevel")
									.val();
							var begTime = $(iframe.document).find("#begTime")
									.val();
							var endTime = $(iframe.document).find("#endTime")
									.val();
							var workType = $(iframe.document).find("#workType")
									.val();
							var content = $(iframe.document).find("#content")
									.val();
							var taker = $(iframe.document).find("#taker").val();
							var owner = $(iframe.document).find("#owner").val();
							var days = $(iframe.document).find("#days").val();
							var hours = $(iframe.document).find("#hours").val();
							var minutes = $(iframe.document).find("#minutes")
									.val();
							var isRemaind = $(iframe.document)
									.find("#isRemaind")[0].checked;
							var begTimeObj = $(iframe.document)
									.find("#begTime")[0];
							var endTimeObj = $(iframe.document)
									.find("#endTime")[0];
							if (begTime == null || begTime == "") {
								showObjError($(begTimeObj),
										'calendar.calendar_begTime_not_null');
								return false;
							} else {
								hideError($(begTimeObj));
							}
							if (endTime == null || endTime == "") {
								showObjError($(endTimeObj),
										'calendar.calendar_endTime_not_null');
								return false;
							} else {
								hideError($(endTimeObj));
							}
							if (endTime < begTime) {
								showObjError($(endTimeObj),
										'calendar.calendar_less_than_begTime');
								return false;
							} else {
								hideError($(endTimeObj));
							}
							var contentObj = $(iframe.document)
									.find("#content")[0];
							if (content == null || content == "") {
								showObjError($(contentObj),
										'calendar.calendar_content_not_null');
								return false;
							} else {
								hideError($(contentObj));
							}
							art.dialog.confirm(
									sprintf("calendar.confirm_add_info"),
									function() {
										$.ajax({
											url : basePath
													+ "calendar/addCalendarBean.action",
											data : {
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
											success : function(data) {
												// art.dialog.alert(data);
												// dialog.close();
												// window.location.reload();
												if(data=="保存失败！"){
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
																window.location
																		.reload();
														   }
														});
												}
												
											}
										});
									}, function() {
										return;
									});
							return false;
						},
						cancel : true
					});
		},
		editable : false,
		events : function(start, end, callback) {
			$.ajax({
						url : basePath + "/calendar/getCalendarList.action",
						cache : false,
						success : function(data) {
							eval("var j=" + data);
							var events = [];
							var info = j;
							for (var i = 0; i < info.length; i++) {
								var calendarObj = info[i];
								var content = calendarObj.content;
								var type = calendarObj.calendarType;
								var begTime = '';
								var endTime = '';
								var id = '';
								var status = '';
								var authority = '';
								if (type == 'calendar') {
									begTime = calendarObj.begTime;
									endTime = calendarObj.endTime;
									id = calendarObj.calendarId;
									status = calendarObj.status;
									authority = calendarObj.authority;
								}
								if (type == 'affair') {
									begTime = calendarObj.beginTime;
									endTime = calendarObj.endTime;
									id = calendarObj.affId;
									authority = calendarObj.authority;
									if (endTime == undefined) {
										endTime = new Date(9999, 12, 31);
									}
								}
								var bool = false;
								if (endTime != undefined) {
									var newBeg = new Date(begTime);
									var newEnd = new Date(endTime);
									if (newBeg.getFullYear() != newEnd
											.getFullYear()
											|| newBeg.getMonth() != newEnd
													.getMonth()
											|| newBeg.getDate() != newEnd
													.getDate()) {
										bool = true;
									}
								}
								if (type == 'affair') {

								}
								if (content.length > 10) {
									content = content.substring(0, 10) + "……";
								}
								events.push({
											title : content,
											start : begTime,
											end : endTime,
											id : id,
											type : type,
											status : status,
											authority : authority,
											allDay : bool
										});
							}
							callback(events);
						},
						error : function() {

						}
					})
		},
		eventClick : function(calEvent, jsEvent, view) {
			var id = calEvent.id;
			var type = calEvent.type;
			var title = '';
			var authority = calEvent.authority;
			var button = [];
			if (type == 'calendar') {
				title = '日程详情';
				var status = calEvent.status;
				if (status == 0) {
					button = [{
						name : '便签',
						callback : function() {
							// window.location.href=basePath+'logined/calendar/myCalendar/notePaper.jsp?type='+type+'&id='+id;
							var viewUrl = basePath
									+ 'logined/calendar/myCalendar/notePaper.jsp?type='
									+ type + '&id=' + id;
							window.open(viewUrl);
							return false;
						}
					}, {
						name : '完成',
						callback : function() {
							setCalendarStatus(id, 1);
							window.location.reload();
						}
					}, {
						name : '修改',
						callback : function() {
							// window.location.href=basePath+'logined/calendar/myCalendar/modifyCalendar.jsp?type='+type+'&id='+id;
							var modifyUrl = basePath
									+ 'logined/calendar/myCalendar/modifyCalendar.jsp?type='
									+ type + '&id=' + id;
							window.open(modifyUrl);
							return false;
						}
					}, {
						name : '删除',
						callback : function() {
							art.dialog.confirm('确认要删除吗？删除后不可恢复！', function() {
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
										
										if(data=="删除失败！"){
											art.dialog.alert(data);
											dialog.close();
											window.location.reload();
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
													   window.location.reload();
												   }
												});
										}
									}
								});
							}, function() {
								return;
							});
							return false;
						}
					}, {
						name : '关闭'
					}];
				} else if (status == 1) {
					button = [{
						name : '便签',
						callback : function() {
							// window.location.href=basePath+'logined/calendar/myCalendar/notePaper.jsp?type='+type+'&id='+id;
							var viewUrl = basePath
									+ 'logined/calendar/myCalendar/notePaper.jsp?type='
									+ type + '&id=' + id;
							window.open(viewUrl);
							return false;
						}
					}, {
						name : '未完成',
						callback : function() {
							setCalendarStatus(id, 0);
							window.location.reload();
						}
					}, {
						name : '关闭'
					}];
				}
			} else if (type == 'affair') {
				title = '查看事务';
				if (authority == 'modify') {
					button = [{
						name : '便签',
						callback : function() {
							// window.location.href=basePath+'logined/calendar/myCalendar/notePaper.jsp?type='+type+'&id='+id;
							var viewUrl = basePath
									+ 'logined/calendar/myCalendar/notePaper.jsp?type='
									+ type + '&id=' + id;
							window.open(viewUrl);
							return false;
						}
					}, {
						name : '修改',
						callback : function() {
							// window.location.href=basePath+'logined/calendar/myCalendar/modifyAffair.jsp?affairId='+id;
							var modifyUrl = basePath
									+ 'logined/calendar/myCalendar/modifyAffair_calendar.jsp?affairId='
									+ id;
							window.open(modifyUrl);
							return false;
						}
					}, {
						name : '删除',
						callback : function() {
							art.dialog.confirm('确认要删除吗？删除后不可恢复', function() {
								$.ajax({
									url : basePath
											+ "calendar/deleteAffairBean.action",
									data : {
										'affairBeanVo.affId' : id
									},
									type : 'post',
									dataType : 'json',
									async : false,
									success : function(data) {
										if(data=="删除失败！"){
											art.dialog.alert(data);
											dialog.close();
											window.location.reload();
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
													   window.location.reload();
												   }
												});
										}
									}
								});
							}, function() {
								return;
							});
							return false;
						}
					}, {
						name : '关闭'
					}];
				} else if (authority == 'view') {
					button = [{
						name : '便签',
						callback : function() {
							// window.location.href=basePath+'logined/calendar/myCalendar/notePaper.jsp?type='+type+'&id='+id;
							var viewUrl = basePath
									+ 'logined/calendar/myCalendar/notePaper.jsp?type='
									+ type + '&id=' + id;
							window.open(viewUrl);
							return false;
						}
					}, {
						name : '关闭'
					}];
				}
			}
			var dialog = art.dialog
					.open(
							basePath
									+ 'logined/calendar/myCalendar/viewCalendar.jsp?type='
									+ type + '&id=' + id, {
								title : title,
								 width : 800,
								 height : 250,
								init : function() {
									var iframe = this.iframe.contentWindow;
								},
								button : button
							});
		}
	});
});

/**
 * 弹出添加层
 * 
 * @return
 */
function addCalendar() {
	var dialog = art.dialog.open(basePath
					+ 'logined/calendar/myCalendar/addCalendar.jsp', {
				title : '新建日程',
				 width : 800,
				 height : 250,
				init : function() {
					var iframe = this.iframe.contentWindow;
				},
				cancelVal : '关闭',
				lock : true,
			    opacity: 0.08,
				ok : function() {
					var iframe = this.iframe.contentWindow;
					var calType = $(iframe.document).find("#calType").val();
					var calLevel = $(iframe.document).find("#calLevel").val();
					var begTime = $(iframe.document).find("#begTime").val();
					var endTime = $(iframe.document).find("#endTime").val();
					var workType = $(iframe.document).find("#workType").val();
					var content = $(iframe.document).find("#content").val();
					var taker = $(iframe.document).find("#taker").val();
					var owner = $(iframe.document).find("#owner").val();
					var days = $(iframe.document).find("#days").val();
					var hours = $(iframe.document).find("#hours").val();
					var minutes = $(iframe.document).find("#minutes").val();
					var isRemaind = $(iframe.document).find("#isRemaind")[0].checked;
					var begTimeObj = $(iframe.document).find("#begTime")[0];
					var endTimeObj = $(iframe.document).find("#endTime")[0];
					if (begTime == null || begTime == "") {
						showObjError($(begTimeObj),
								'calendar.calendar_begTime_not_null');
						return false;
					} else {
						hideError($(begTimeObj));
					}
					if (endTime == null || endTime == "") {
						showObjError($(endTimeObj),
								'calendar.calendar_endTime_not_null');
						return false;
					} else {
						hideError($(endTimeObj));
					}
					if (endTime < begTime) {
						showObjError($(endTimeObj),
								'calendar.calendar_less_than_begTime');
						return false;
					} else {
						hideError($(endTimeObj));
					}
					var contentObj = $(iframe.document).find("#content")[0];
					if (content == null || content == "") {
						showObjError($(contentObj),
								'calendar.calendar_content_not_null');
						return false;
					} else {
						hideError($(contentObj));
					}
					art.dialog.confirm(sprintf("calendar.confirm_add_info"),
							function() {
								$.ajax({
									url : basePath
											+ "calendar/addCalendarBean.action",
									data : {
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
									success : function(data) {
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
												   window.location.reload();
											   }
											});
									}
								});
							}, function() {
								return;
							});
					return false;
				},
				cancel : true
			});
}

function toCalendarSearch() {
	window.location.href = basePath
			+ "logined/calendar/myCalendar/calendarList.jsp?type=new";
}

function setCalendarStatus(id, status) {
	dataParam = {
		'calendarBeanVo.calendarId' : id,
		'calendarBeanVo.status' : status
	};
	$.ajax({
				type : 'post',
				url : basePath + "calendar/setCalendarStatus.action",
				data : dataParam,
				dataType : 'json',
				async : false,
				success : function(data) {

				}
			});
}

function getVal(obj) {
	if (obj) {
		return 1;
	}
	return 0;
}
