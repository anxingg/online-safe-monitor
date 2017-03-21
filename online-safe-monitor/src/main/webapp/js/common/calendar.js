$(document).ready(function(){
	currentTimeNew();
});

var nowLong = 0;
function currentTimeNew(){
	
	$.ajax({
		url:basePath+"systemtime/getSystemTime.action",
		type:"post",
		dataType:'text',
		success:function(data){
			var jsonObj = eval( '(' + data + ')' );
			
			nowLong = jsonObj.currentTimeMillis;
			refreshCalendarClock();
		}
	});
};


/**
 * 日历js
 */
function Year_Month() {
	var now = new Date(nowLong);
	var yy = now.getFullYear();
	var mm = now.getMonth() + 1;
	if(mm<10){
		mm = '0'+mm;
	}

	var cl = '';
	if (now.getDay() == 0)
		cl = '';
	if (now.getDay() == 6)
		cl = '';
	return (cl + yy + '年' + mm + '月');
}
function Date_of_Today() {
	var now = new Date(nowLong);
	var cl = '';
	if (now.getDay() == 0)
		cl = '';
	if (now.getDay() == 6)
		cl = '';
	return (cl + now.getDate() + '');
}
function Day_of_Today() {
	var day = new Array();
	day[0] = "星期日";
	day[1] = "星期一";
	day[2] = "星期二";
	day[3] = "星期三";
	day[4] = "星期四";
	day[5] = "星期五";
	day[6] = "星期六";
	var now = new Date(nowLong);
	var cl = '';
	if (now.getDay() == 0)
		cl = '';
	if (now.getDay() == 6)
		cl = '';
	return (cl + day[now.getDay()] + '');
}
function CurentTime() {
	var now = new Date(nowLong);
	var hh = now.getHours();
	var mm = now.getMinutes();
	var ss = now.getTime() % 60000;
	ss = (ss - (ss % 1000)) / 1000;
	var clock = hh + ':';
	if (mm < 10)
		clock += '0';
	clock += mm + ':';
	if (ss < 10)
		clock += '0';
	clock += ss;
	return (clock);
}

function CurentWel() {
	var wel;
	var now = new Date(nowLong);
	var hour = now.getHours();
	if (hour < 1)
		wel = '子时';
	else if (hour < 3)
		wel = '丑时';
	else if (hour < 5)
		wel = '寅时';
	else if (hour < 7)
		wel = '卯时';
	else if (hour < 9)
		wel = '辰时';
	else if (hour < 11)
		wel = '巳时';
	else if (hour < 13)
		wel = '午时';
	else if (hour < 15)
		wel = '未时';
	else if (hour < 17)
		wel = '申时';
	else if (hour < 19)
		wel = '酉时';
	else if (hour < 21)
		wel = '戌时';
	else if (hour < 23)
		wel = '亥时';
	else {
		wel = '子时';
	}
	return (wel);
}

function refreshCalendarClock() {
	nowLong += 1000;
	var year_month = Year_Month();
	var date = Date_of_Today();
	if(date<10){
		date = '0'+date;
	}
	var week = Day_of_Today();
	var time = CurentTime();
	var str = year_month + date + "日  " + week;
//	$("#clock").html("<p><font class=\"time\">"+time+"</font></p><p>"+str+"&nbsp;</p>");
	$("#clock").html("<span>"+str+"</span><span class='time'>"+time+"</span>");
}

setInterval('refreshCalendarClock()', 1000);