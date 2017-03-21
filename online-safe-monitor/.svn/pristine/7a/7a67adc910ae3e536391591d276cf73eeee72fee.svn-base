// JavaScript Document
$(document).ready(function(){
	/*$("ul.l_f_menu li:eq(0)").addClass("");*/
	$("ul.l_f_menu li p").click(function(){	
		$(this).parent().siblings().children("dl").children().removeClass("hover");	
	if ($(this).parent().hasClass("current")){ 
		$(this).parent().has("dt").closest("li").removeClass("current");
	} else {
		$("ul.l_f_menu li").removeClass("current");
		$(this).parent().addClass("current");
			if(!$(this).parent().has("dl")){
				$(this).css("background-image","none");
			}
	}});//一级菜单
	
	$(".l_f_menu dl dt").click(function(){
		if(!$(this).hasClass("hover")){
			$(this).addClass("hover");
			$(this).siblings().removeClass("hover");
		}
	});//二级菜单
});
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
};
$(window).resize(function() {
resizeLayout();
});
$(document).ready(function($) {
// 调整窗口大小
resizeLayout();
});