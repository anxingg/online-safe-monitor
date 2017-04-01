$(function(){
	$('.headMenu ul li').click(function(){
		$('.headMenu ul li').removeClass('headMenu_liActive');
		$(this).addClass('headMenu_liActive');
	});
	$('.county span').click(function(){
		$('.county span').removeClass('check_spanActive');
		$(this).addClass('check_spanActive');
	});
	$('.park span').click(function(){
		$('.park span').removeClass('check_spanActive');
		$(this).addClass('check_spanActive');
	});
});
