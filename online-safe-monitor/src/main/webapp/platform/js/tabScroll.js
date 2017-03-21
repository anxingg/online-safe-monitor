
function setUlWidth() {
	$("#div_tab").width(getULWidth());
	var ulLeft = $(".tabScroll").width() - $("#div_tab").width() + 115;
	if ($("#div_tab").width() > $(".tabScroll").width()) {
		$(".btnScroll").show();
		//$(".tabScroll").scrollLeft(ulLeft);
	} else {
		$(".btnScroll").hide();
	}
	$(".tabScroll").scroll(function() {
				// left
				if ($(".tabScroll").scrollLeft() > 0) {
					$("a.btnRight").removeClass("btnRightNo");
				}
				if ($(".tabScroll").scrollLeft() == 0) {
					$("a.btnLeft").addClass("btnLeftNo");
				}
				// right
				var scrollWidth = $(this).scrollLeft();
				var ulWidth = getULWidth();
				if ((ulWidth - scrollWidth) < $(this).width()) {
					$("a.btnRight").addClass("btnRightNo");
				}
			})
}
function getULWidth() {
	var totalWidth = 0;
	$(".tabScroll ul > li").each(function() {
				var liWidth = $(this).outerWidth() + 5;
				totalWidth += liWidth;
			});
	return totalWidth;
}// get ul width
function upMove(obj) {
	var dom = $(".tabScroll");
	dom.animate({
				scrollLeft : -105 + dom.scrollLeft()
			}, 500);

}
function downMove(obj) {
	var dom = $(".tabScroll");
	dom.animate({
				scrollLeft : 105 + dom.scrollLeft()
			}, 500)
	if ($(".tabScroll").scrollLeft() > 0) {
		$("a.btnLeft").removeClass("btnLeftNo");
		// $("a.btnRight").removeClass("btnRightNo");
		// $("a.btnRight").addClass("btnRightNo");
	}
}
