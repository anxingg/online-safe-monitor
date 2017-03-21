// JavaScript Document
// 窗口resize事件
function resizemenu() {
	var wWidth = (window.document.documentElement.clientWidth
			|| window.document.body.clientWidth || window.innerHeight);
	// 一级标签宽度
	var width = wWidth - 220; //减去右边框架宽度
	$('#tabbox').width(width);
	$('#tabList').width(width - 40); // -1是为了兼容iPad
	$('#tabList').triggerHandler('_resize');
	
};
$(window).resize(function() {
	resizemenu();
	setUlWidth();
});
//点击箭头左右滚动
$(document).ready(function($) {
	resizemenu();
	setUlWidth();
	var scrollIncrement = 100;
	var scrollDuration = 200;
	$('#tabs_left_scroll').click(function() {
				var scrollTo = $("#tabList").scrollLeft()
						- scrollIncrement;
				if (scrollTo < scrollIncrement)// 如果不够一个tab宽度，则滚动到头部
					scrollTo = 0;
				$("#tabList").animate({
							scrollLeft : scrollTo
						}, scrollDuration);
			});
	$('#tabs_right_scroll').click(function() {
		var scrollTo = $("#tabList").scrollLeft() + scrollIncrement;
		if (scrollTo + scrollIncrement > $("#tabList")
				.attr('scrollWidth'))
			scrollTo = $("#tabList").attr('scrollWidth');
		$("#tabList").animate({
					scrollLeft : scrollTo
				}, scrollDuration);
	});

});

// 根据选项卡的宽度 判断箭头是否显示
function setUlWidth() {
	var li_width = getULWidth();
	var scrollIncrement = 120;
	var scrollDuration = 200;
	$("#div_tab").width(getULWidth());
	var scrollleft=li_width - $("#tabList").width();
	if (li_width > $("#tabList").width()) {
		$('#tabs_left_scroll').show();
		$('#tabs_right_scroll').show();
		 var scrollTo = $("#div_tab").scrollLeft() - scrollIncrement;
		 if(scrollTo < scrollIncrement)//如果不够一个tab宽度，则滚动到头部
		 scrollTo = 0;
		$("#tabList").animate({
					scrollLeft : scrollleft+115
				}, scrollDuration);
	} else {
		$('#tabs_left_scroll').hide();
		$('#tabs_right_scroll').hide();
	}
}
// 得到选项卡的总宽度
function getULWidth() {
	var totalWidth = 0;
	$("#div_tab > li").each(function() {
		        //var liWidth = 115+5;
				var liWidth = $(this).outerWidth() + 10;
				totalWidth += liWidth;
	});
	return totalWidth;
}