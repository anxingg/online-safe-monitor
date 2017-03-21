var _inputID;
$(document).ready(function() {
	$("#beginTimeSpan").hide();
	$("#endTimeSpan").hide();	

	 document.onclick=function(){
         document.getElementById("setHour").style.display="none";
         document.getElementById("setMinute").style.display="none";
     }

	
	// 初始化小时选择项
	for ( var i = 0; i < 24; i++) {
		var hourLi = '<LI class=menu_item value="' + i + '"><A href="javascript:;"><I></I>' + i + '</A></LI>'
		$("#hourUl").append(hourLi);
	}

	$(".menu_obj ul li").click(function() {		

		$("#"+_inputID).val($(this).val());
		$(".menu_obj").hide();
	})

	
	// 结束时间点击事件
	$("#checkBoxEndTime").click(function() {
		if($(this).attr("checked")=='checked'){
			$("#endTimeTr").show();
		}else{
			$("#endTimeTr").hide();
		}
	});
	
	// 开始时间点击事件
	$("#checkBoxAllDay").click(function() {
		if($(this).attr("checked")=='checked'){
			$("#beginTimeSpan").hide();
			$("#endTimeSpan").hide();			
		}else{
			$("#beginTimeSpan").show();
			$("#endTimeSpan").show();
		}
	});
	
	$("#remaindType").change( function() {
		var remaindType = $("#remaindType").val();
		if(remaindType == 0){
			$("#remaidSpan").hide();			
		}else{
			$("#remaidSpan").show();
		}
	});
	
	
//	var start = art.dialog.data('start');
//	var end = art.dialog.data('end');
//	var allDay = art.dialog.data('allDay');
//	if (allDay) {
//		end = new Date(end.getTime() + 24 * 60 * 60 * 1000 - 1000);
//	} else {
//		end = new Date(end.getTime() - 1000);
//	}
//
//	setInitTime(start, end);
//	setAffairCheck("日程安排", "isRemaind", "isRemaindTr");
})

function msg(divID, inputID) {
	_inputID=inputID;
	$(".menu_obj").hide();
	$("#" + divID + "").show();
	var inputTop = $("#" + inputID + "").position();
	$("#" + divID + "").css("top", inputTop.top + 16);
	$("#" + divID + "").css("left", inputTop.left - 6)
	if ($.browser.msie){
		event.cancelBubble = true ;
	}else{
		event.stopPropagation();
	}
}


function clickDiv(div){
    div.style.display="";
    var b = window.event;
    b.cancelBubble = true;
}


































//function getNowBeg() {
//	var nowBeg;
//	var myDate = new Date();
//	var year = myDate.getFullYear();
//	var month = myDate.getMonth() + 1;
//	var day = myDate.getDate();
//	var hours = myDate.getHours();
//	var minutes = myDate.getMinutes();
//	var second = myDate.getSeconds();
//
//	if (month < 10) {
//		month = "0" + month;
//	}
//	if (day < 10) {
//		day = "0" + day;
//	}
//	if (hours < 10) {
//		hours = "0" + hours;
//	}
//	if (minutes < 10) {
//		minutes = "0" + minutes;
//	}
//	if (second < 10) {
//		second = "0" + second;
//	}
//	nowBeg = year + "-" + month + "-" + day + " " + hours + ":" + minutes + ":" + second;
//	return nowBeg;
//}
//
//function getNowEnd() {
//	var nowEnd;
//	var myDate = new Date();
//	var year = myDate.getFullYear();
//	var month = myDate.getMonth() + 1;
//	var day = myDate.getDate();
//	if (month < 10) {
//		month = "0" + month;
//	}
//	if (day < 10) {
//		day = "0" + day;
//	}
//
//	nowEnd = year + "-" + month + "-" + day + " 23:59:59"
//	return nowEnd;
//}
//
//// 得到每周的第一天(周日)
//function getFirstDateOfWeek() {
//	var theDate = new Date();
//	var firstDateOfWeek;
//	theDate.setDate(theDate.getDate() - theDate.getDay() + 1); //         
//	firstDateOfWeek = theDate;
//	var fdw = firstDateOfWeek.toLocaleString().replace("年", "-").replace("月", "-").replace("日 ", " ");
//
//	var fdws = fdw.split(' ');
//	var f = fdws[0].toString();
//	var fs = f.split('-');
//	var month = fs[1].toString();
//	var day = fs[2].toString();
//
//	var f1 = fdws[1].toString();
//	var fs1 = f1.split(':');
//	var h = fs1[0].toString();
//	var m = fs1[1].toString();
//	var s = fs1[2].toString();
//	if (month < 10) {
//		month = "0" + month;
//	}
//	if (day < 10) {
//		day = "0" + day;
//	}
//	if (h < 10) {
//		h = "0" + h;
//	}
//	// if(m<10){
//	// m = "0"+m;
//	// }
//	// if(s<10){
//	// s = "0"+s;
//	// }
//	return fs[0].toString() + "-" + month + "-" + day + " " + h + ":" + m + ":" + s;
//}
//// 得到每周的最后一天(周六)
//function getLastDateOfWeek() {
//	var theDate = new Date();
//	var lastDateOfWeek;
//	theDate.setDate(theDate.getDate() + 7 - theDate.getDay()); //    
//	lastDateOfWeek = theDate;
//	var fdw = lastDateOfWeek.toLocaleString().replace("年", "-").replace("月", "-").replace("日 ", " ");
//
//	var fdws = fdw.split(' ');
//	var f = fdws[0].toString();
//	var fs = f.split('-');
//	var month = fs[1].toString();
//	var day = fs[2].toString();
//
//	var f1 = fdws[1].toString();
//	var fs1 = f1.split(':');
//	var h = fs1[0].toString();
//	var m = fs1[1].toString();
//	var s = fs1[2].toString();
//	if (month < 10) {
//		month = "0" + month;
//	}
//	if (day < 10) {
//		day = "0" + day;
//	}
//	if (h < 10) {
//		h = "0" + h;
//	}
//	// if(m<10){
//	// m = "0"+m;
//	// }
//	// if(s<10){
//	// s = "0"+s;
//	// }
//
//	return fs[0].toString() + "-" + month + "-" + day + " " + h + ":" + m + ":" + s;
//}
//
//function getFirstDay() {
//	var myDate = new Date();
//	var year = myDate.getFullYear();
//	var month = myDate.getMonth() + 1;
//	if (month < 10) {
//		month = "0" + month;
//	}
//	var firstDay = year + "-" + month + "-" + "01";
//	return firstDay;
//}
//
//function getLastDay() {
//	var myDate = new Date();
//	var year = myDate.getFullYear();
//	var month = myDate.getMonth() + 1;
//	if (month < 10) {
//		month = "0" + month;
//	}
//	myDate = new Date(year, month, 0);
//	var lastDay = year + "-" + month + "-" + myDate.getDate();
//	return lastDay;
//}
//
//function typeOnchange() {
//	var calType = $("#calType").val();
//	alert(calType);
//	var begTime = "";
//	var endTime = "";
//	if (calType == 1) {
//		begTime = getNowBeg();
//		endTime = getNowEnd();
//	} else if (calType == 2) {
//		begTime = getFirstDateOfWeek();
//		endTime = getLastDateOfWeek();
//	} else if (3) {
//		begTime = getFirstDay();
//		endTime = getLastDay();
//	}
//
//	$("#begTime").val(begTime);
//	$("#endTime").val(endTime);
//}
//
//Date.prototype.format = function(format) {
//	var o = {
//	    "M+" : this.getMonth() + 1, // month
//	    "d+" : this.getDate(), // day
//	    "h+" : this.getHours(), // hour
//	    "m+" : this.getMinutes(), // minute
//	    "s+" : this.getSeconds(), // second
//	    "q+" : Math.floor((this.getMonth() + 3) / 3), // quarter
//	    "S" : this.getMilliseconds()
//	// millisecond
//	}
//
//	if (/(y+)/.test(format)) {
//		format = format.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
//	}
//
//	for ( var k in o) {
//		if (new RegExp("(" + k + ")").test(format)) {
//			format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k] : ("00" + o[k]).substr(("" + o[k]).length));
//		}
//	}
//	return format;
//}
//
//function changeColor(obj) {
//	var calLevelStr = '';
//	if (obj == 0) {
//		calLevelStr = '<a href="#" class="m_btn">未指定</a>';
//		$("#calLevel").val(0);
//	} else if (obj == 1) {
//		calLevelStr = '<a style="color: red" href="#">重要/紧急</a>';
//		$("#calLevel").val(1);
//	} else if (obj == 2) {
//		calLevelStr = '<a style="color:#FF9933" href="#">重要/不紧急</a>';
//		$("#calLevel").val(2);
//	} else if (obj == 3) {
//		calLevelStr = '<a style="color: #0F0;" href="#">不重要/紧急</a>';
//		$("#calLevel").val(3);
//	} else if (obj == 4) {
//		calLevelStr = '<a class="gray_9" href="#">不重要/不紧急</a>';
//		$("#calLevel").val(4);
//	}
//	$("#cal_level").html(calLevelStr);
//}
//
//function setInitTime(begin, end) {
//	var date = new Date();
//	var year = date.getFullYear();
//	var month = date.getMonth() + 1;
//	if (month < 10) {
//		month = "0" + month;
//	}
//	var day = date.getDate();
//	if (day < 10) {
//		day = '0' + day;
//	}
//	var week = date.getDay();
//	var dateStr = year + "-" + month + "-" + day;
//	var hours = date.getHours();
//	var minutes = date.getMinutes();
//	var second = date.getSeconds();
//	var addMinutes = minutes;
//	if (hours < 10) {
//		hours = '0' + hours;
//	}
//	if (second < 10) {
//		second = '0' + second;
//	}
//	if (addMinutes >= 60) {
//		addMinutes = addMinutes - 60;
//	}
//	if (addMinutes < 10) {
//		addMinutes = '0' + addMinutes;
//	}
//	var begTime = dateStr + " " + hours + ":" + addMinutes + ":" + second;
//	var endTime = dateStr + " 23:59:59";
//	$("#begTime").val(getFormatDate(begin));
//	$("#endTime").val(getFormatDate(end));
//}
//
///**
// * 添加按钮
// * 
// * @param obj
// * @return
// */
//function selectAuthor(obj) {
//	if (obj == 'taker') {
//		openSelectUser(3, selectTakerCallBack, null, $("#taker").val());
//	} else if (obj == 'owner') {
//		openSelectUser(3, selectOwnerCallBack, null, $("#owner").val());
//	}
//}
//
///**
// * 添加按钮(回调函数)
// * 
// * @param data
// * @return
// */
//function selectTakerCallBack(data) {
//	var ids = '';
//	var names = '';
//	data.forEach(function(value, key) {
//		ids += value.Id + ',';
//		names += value.Name + ',';
//	});
//	$("#taker").val(ids);
//	$("#takerName").val(names);
//}
//
///**
// * 添加按钮(回调函数)
// * 
// * @param data
// * @return
// */
//function selectOwnerCallBack(data) {
//	var ids = '';
//	var names = '';
//	data.forEach(function(value, key) {
//		ids += value.Id + ',';
//		names += value.Name + ',';
//	});
//	$("#owner").val(ids);
//	$("#ownerName").val(names);
//}
//
///**
// * 清空操作
// * 
// * @param obj
// * @return
// */
//function clearAuthor(obj) {
//	if (obj == 'taker') {
//		$("#taker").val('');
//		$("#takerName").val('');
//	} else if (obj == 'owner') {
//		$("#ownerId").val('');
//		$("#ownerName").val('');
//	}
//}
//
///**
// * 验证数字格式
// * 
// * @param obj
// * @return
// */
//function validateNum(obj, type) {
//	var val = obj.value;
//	obj.value = val.replace(/[^\d]/g, '');
//	if (type == 'day') {
//		if (val > 365) {
//			showObjError($("#days"), 'calendar.calendar_days_scope');
//			return;
//		} else {
//			hideError($("#days"));
//		}
//	} else if (type == 'hours') {
//		if (val > 24) {
//			showObjError($("#hours"), 'calendar.calendar_hours_scope');
//			return;
//		} else {
//			hideError($("#hours"));
//		}
//	} else if (type == 'minutes') {
//		if (val > 59) {
//			showObjError($("#minutes"), 'calendar.calendar_minutes_scope');
//			return;
//		} else {
//			hideError($("#minutes"));
//		}
//	}
//}
//
//function getFormatDate(srcDate) {
//	var nowBeg;
//	var myDate = srcDate;
//	var year = myDate.getFullYear();
//	var month = myDate.getMonth() + 1;
//	var day = myDate.getDate();
//	var hours = myDate.getHours();
//	var minutes = myDate.getMinutes();
//	var second = myDate.getSeconds();
//
//	if (month < 10) {
//		month = "0" + month;
//	}
//	if (day < 10) {
//		day = "0" + day;
//	}
//	if (hours < 10) {
//		hours = "0" + hours;
//	}
//	if (minutes < 10) {
//		minutes = "0" + minutes;
//	}
//	if (second < 10) {
//		second = "0" + second;
//	}
//	nowBeg = year + "-" + month + "-" + day + " " + hours + ":" + minutes + ":" + second;
//	return nowBeg;
//}
