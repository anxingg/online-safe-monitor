// JavaScript Document
// 窗口resize事件
function resizemenu_H() {
	var wWidth = (window.document.documentElement.clientWidth
			|| window.document.body.clientWidth || window.innerHeight);
	// 一级标签宽度
	var width = wWidth - $('.left_m').outerWidth(true); //减去右边框架宽度
	$(".right_m").width(width - 85);
	$(".list_menu").width(width - 85 - 30);  //right_m的总宽度再减去两个按钮的宽度
	
};
$(window).resize(function() {
	resizemenu_H();
	setUlWidth_H();
});

//点击箭头左右滚动
$(document).ready(function($) {
	resizemenu_H();
	setUlWidth_H();
	var scrollIncrement = 68;
	var scrollDuration = 200;
	$('#prev').click(function() {
				var scrollTo = $("#list_menu").scrollLeft()
						- scrollIncrement;
				if (scrollTo < scrollIncrement)// 如果不够一个tab宽度，则滚动到头部
					scrollTo = 0;
				$("#list_menu").animate({
							scrollLeft : scrollTo
						}, scrollDuration);	
	});
	$('#next').click(function() {
		var scrollTo = $(".list_menu").scrollLeft() + scrollIncrement;
		if (scrollTo + scrollIncrement > $("#list_menu")
				.attr('scrollWidth'))
			scrollTo = $("#list_menu").attr('scrollWidth');
		$("#list_menu").animate({
					scrollLeft : scrollTo
		}, scrollDuration); 		
	});

});
/*function a(){
   var wWidth = (window.document.documentElement.clientWidth
			|| window.document.body.clientWidth || window.innerHeight);
	// 一级标签宽度
   var width = wWidth - $('.left_m').outerWidth(true); //减去右边框架宽
   var z_width =width - 85 - 30;
   var n_width = getULWidth_H();
   if($(".list_menu").scrollLeft() == 0){
	   $('#prev').hide();
	   $('#next').show();
   }else if($(".list_menu").scrollLeft() == (n_width - z_width)){
	   $('#prev').show();
	   $('#next').hide();
   }else{
	   $('#prev').show();
	   $('#next').show();
	}	
}*/

// 根据选项卡的宽度 判断箭头是否显示
function setUlWidth_H() {
	var li_width = getULWidth_H();
	var scrollIncrement = 68;
	var scrollDuration = 200;//动画速度
	$("#div_tab1").width(getULWidth_H());
	var scrollleft = li_width - $(".list_menu").width();
	if ( li_width > $(".list_menu").width()){
		$('#prev').show();
		$('#next').show();
		/*$(".list_menu").animate({
					scrollLeft : scrollleft+35
		}, scrollDuration);*/	
	} else {
		$('#prev').hide();
		$('#next').hide();
	}
}

// 得到选项卡的总宽度
function getULWidth_H() {
	var totalWidth = 0;
	$("#div_tab1 > li").each(function() {
		        //var liWidth = 115+5;
				var liWidth = $(this).outerWidth(true);
				totalWidth += liWidth;
	});
	return totalWidth;
}