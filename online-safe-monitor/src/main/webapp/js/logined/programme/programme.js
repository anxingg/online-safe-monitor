//format date style
function getdate(begTime, begHour, begMinu) {
	if (begTime == " " || begTime == undefined||begTime=="") {
		return "2100-11-11 23:59:23";
	}
	// alert(begTime)
	var yyyy_MM_dd = begTime.substring(0, 10);
	// alert(yyyy_MM_dd)
	if (begHour.length < 2) {
		begHour = "0" + begHour;
	}
	if (begMinu.length < 2) {
		begMinu = "0" + begMinu;
	}
	var result = yyyy_MM_dd + " " + begHour + ":" + begMinu + ":00";
	return result;
}

// format time
function getTime(hour, minu) {
	if (minu.length == 1) {
		minu = "0" + minu;
	}
	return hour + ":" + minu;
}

// list programme show the detail
function openArtDialogOnlyCancel(titleName, url, widthSet, heightSet) {
	art.dialog.open(url, {
		id : url,
		opacity : 0,
		title : titleName,
		width : widthSet,
		height : heightSet,
		cancelVal : '关闭',
		cancel : true,
		lock : true,
		opacity : 0.3,
	});
}

function addOrUpdateProgrammeLogical(iframe, id, dialog) {
	
	var content = $(iframe.document).find("#dailyName").val();
	var begTime = $(iframe.document).find("#begTime").val();
	var begHour = $(iframe.document).find("#setHourInput").val();
	var begMinu = $(iframe.document).find("#setMinuteInput").val();
	
	var checkBoxAllDay = $(iframe.document).find("#checkBoxAllDay")[0].checked;
	if (checkBoxAllDay) {
		begHour = "0";
		begMinu = "0";
	}
	var endTime = $(iframe.document).find("#endTime").val();
	var endHour = $(iframe.document).find("#setEndHourInput").val();
	var endMinu = $(iframe.document).find("#setEndMinuteInput").val();
	if (checkBoxAllDay) {
		endHour = "23";
		endMinu = "59";
	}

	var remaindType = $(iframe.document).find("#remaindType").val();
	var remaindHour = $(iframe.document).find("#setRemaidHourInput").val();
	var remaindMinu = $(iframe.document).find("#setRemaidMinuteInput").val();

	var repeatType = $(iframe.document).find("#repeatType").val();
	// var isSysRemaind =
	// $(iframe.document).find("#isSysRemaind")[0].checked?1:0;

	var begTimeObj = $(iframe.document).find("#begTime")[0];
	var endTimeObj = $(iframe.document).find("#endTime")[0];
	var contentObj = $(iframe.document).find("#dailyName")[0];
	var remaindTypeObj = $(iframe.document).find("#remaindType")[0];
	if (content == null || $.trim(content) == "") {
		showObjError($(contentObj), 'calendar.calendar_content_not_null');
		return false;
	} else if (content.length > 2000) {
		showObjError($(contentObj), 'calendar.calendar_content_too_long');
		return false;
	} else {
		hideError($(contentObj));
	}
	if (begTime == null || begTime == "") {
		showObjError($(begTimeObj), 'calendar.calendar_begTime_not_null');
		return false;
	} else {
		hideError($(begTimeObj));
	}

	if (endTime != null && endTime != " " && endTime != '') {
		if (endTime < begTime) {
			showObjError($(endTimeObj), 'calendar.calendar_less_than_begTime');
			return false;
		} else {
			hideError($(endTimeObj));
		}
	}
	
	//判断结束时间与开始时间，结束时间不能小于开始时间
	var checkBoxHasEnd = $(iframe.document).find("#checkBoxEndTime")[0].checked;
	if(!checkBoxAllDay && checkBoxHasEnd && begTime == endTime){
		if(Number(begHour) > Number(endHour)){
			showObjError($(endTimeObj), 'calendar.calendar_less_than_begTime');
			return;
		}else{
			if(Number(begMinu) > Number(endMinu)){
				showObjError($(endTimeObj), 'calendar.calendar_less_than_begTime');
				return;
			}
		}
	}
	
	//判断提醒时间，当同一天的提醒时，提醒时间不能大于开始时间。
	if(!checkBoxAllDay && remaindType==1){
		if(Number(begHour) < Number(remaindHour)){
			showObjError($(remaindTypeObj), 'calendar.remaind_less_than_begTime');
			return;
		}else{
			if(Number(begMinu) < Number(remaindMinu)){
				showObjError($(remaindTypeObj), 'calendar.remaind_less_than_begTime');
				return;
			}
		}
	}
	
	art.dialog.confirm(sprintf("calendar.confirm_add_info"), function() {
		$.ajax({
			url : basePath + "calendar/addProgramme.action",
			data : {
				"bean.content" : content,
				"bean.repeatType" : repeatType,
				"bean.remaindTime" : getTime(remaindHour, remaindMinu),
				"bean.remaindType" : remaindType,
				// "bean.isSysRemaind" : isSysRemaind,
				"bean.isAllDay" : checkBoxAllDay ? 1 : 0,
				"isUpdate" : id,
				"bean.begTime":getdate(begTime, begHour, begMinu),
				"bean.endTime":getdate(endTime, endHour, endMinu)
			},
			type : 'post',
			  beforeSend:function(){
					$("body").lock();
				},
				complete:function(){
					$("body").unlock();
				},
			success : function(data) {
				if (dialog != null && dialog != undefined)
					dialog.close();// close the art dialog
				var yMd = begTime.substring(0, 10);
				var year=yMd.split("-")[0];
				var month=yMd.split("-")[1];
				month=Number(month)-1;
				window.location.href=basePath+"logined/programme/myProgramme.jsp?year="+year+"&month="+month;
			}
		});
	}, function() {
		return true;
	});

	return false;

}

function openArtDialog(titleName, url, widthSet, heightSet, id) {
	var dialog = art.dialog.open(url, { // open params begin
		id : url,
		lock : true,
		opacity : 0,
		title : titleName,
		width : 800,
		height : 250,
		cancelVal : '关闭',
		ok : function() {
			// alert("lkj")
			var iframe = this.iframe.contentWindow;
			addOrUpdateProgrammeLogical(iframe, id, dialog);
			return false;
		},// open pragrams end
		cancel : true
	});
}

function deleteProgramme(id, repeatType,iframe) {
	var prompt = '确认要删除整个重复日程吗？删除后不可恢复！';
	if (repeatType == 0) {
		prompt = '确认要删除吗？删除后不可恢复！';
	}
	var dialog = art.dialog.confirm(prompt, function() {
		$.ajax({
			url : basePath + "calendar/deleteProgramms.action",
			data : {
				'ids' : id
			},
			type : 'post',
			dataType : 'json',
			async : false,
			success : function(data) {
				var begTime=iframe.document.getElementById("begTime").innerHTML;
				var year=begTime.substring(0,4);
				var month=begTime.substring(5,7);
				month=parseInt(month)-1;
				dialog.close();		
				window.location.href=basePath+"logined/programme/myProgramme.jsp?year="+year+"&month="+month;
			}
		});
		return true;
	}, function() {
		return;
	});
}

function openArtDialogOnCalendar(start, end) {
	art.dialog.data('start', start);
	art.dialog.data('end', end);

	art.dialog.open(basePath + 'logined/programme/addProgramme.jsp', {
		title : '新建日程',
		 width : 800,
		 height : 450,
		lock : true,
		opacity : 0.3,
		init : function() {
			var iframe = this.iframe.contentWindow;
		},
		cancelVal : '关闭',
		ok : function() {
			var frame = this.iframe.contentWindow;
			addOrUpdateProgrammeLogical(frame, -1);// -1 代表不是 添加，而是新增
			return false;
		},
		cancel : true
	});
}

var Programme = {
	modifyTitle : '修改日程',
	addTitle : '添加日程',
	detailTitle : '日程详情',

	add : openArtDialog, // 添加日程
	modify : openArtDialog,// 修改日程
	show : openArtDialogOnlyCancel,// show detail programme
	dele : deleteProgramme, // delete
	addOnCalendar : openArtDialogOnCalendar
// add programme on calendar page
}
