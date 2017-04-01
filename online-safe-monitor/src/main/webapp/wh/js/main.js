// JavaScript Document
// 窗口resize事件
function resizeLayout() {
	// 主操作区域高度
	var wHeight = (window.document.documentElement.clientHeight || window.document.body.clientHeight || window.innerHeight);
	var nHeight = $('.head').is(':visible') ? $('.head').outerHeight() : 0;
	var cHeight = wHeight - nHeight - $('.bottom').outerHeight();
	var meetingHeight=cHeight;
	$('#mainContainer').height(cHeight);
	$('#menu').height(cHeight);
	$(".mainIframe").css({height: cHeight});
	$(".meetingIframe").css({height: meetingHeight});
	$("#mainIframe").css("height",wHeight-64);
};

$(window).resize(function() {
	resizeLayout();
});

$(document).ready(function($) {
	// 调整窗口大小
	resizeLayout();
});

