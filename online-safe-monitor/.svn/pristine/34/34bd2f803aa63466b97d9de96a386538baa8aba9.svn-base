// JavaScript Document
$(document).ready(function(){
	$(".user_tab li").click(function() {
				var index_tab = $(this).parent().children().index($(this));// 选项卡的索引值
				$(this).parent().find(".on").removeClass("on");
				$(this).addClass("on");
				var content_obj = $(this).parents(".input").find(".tabContent");// 内容节点
				content_obj.hide();
				content_obj.eq(index_tab).show();
			});// 选项卡
			
	$(".searchInpt").focus(function(){
		var val = $(this).val();
		$(this).val("").blur(function() {
            $(this).val(val);
        });;
	});
	$("#add_ry").click(function(){
		$("#cont_addr").toggle();
	});//选项卡
	$(".SeniorSearch_a").click(function(){
	$(this).parent().parent("ul").next("ul").toggle();
	$(this).toggleClass("toUp");
	
	if($(".tableScroll").is(":visible"))
		{	
		var wHeight = (window.document.documentElement.clientHeight || window.document.body.clientHeight || window.innerHeight);
		$(".tableScroll").css("height",wHeight-60-$(".doArea").height());
		}
	//.show();	
	});//展开更多查询条件	
	$(".chk_orther").click(function(){
		if($(this).is(":checked"))
		$(this).parent().next(".orther").show();	
		else
		$(this).parent().next(".orther").hide();		
		});	
	  //头像删除
  $(".myphoto").hover(function(){
	  $(this).children(".picdele").show();
	  },function(){
	  $(this).children(".picdele").hide();
	  });
});
//手风琴效果
$(document).ready(function(){
	
	function unfoldMenu(pn, cn){
		var p = $('strong.menu-p'), c = $('div.menu-c'), cc = $('div.menu-c-current');
		if(c.index(cn) != c.index(cc)){
			p.removeClass('menu-p-current');
			cc.hide(200, function(){
				$(this).removeAttr('style').removeClass('menu-c-current');
			})
			pn.addClass('menu-p-current');
			cn.show(200, function(){
				$(this).removeAttr('style').addClass('menu-c-current');
			});
		}
	}
	
	function menuHandle(){
		$('strong.menu-p').click(function(){
			var pn = $(this), cn = pn.next();
			unfoldMenu(pn, cn);
		});
	}
	
	//设置默认下当前展开
	function menuCurrent(){
		var idx = $('input.menu-code-index').val(), m, pn, cn, p = $('strong.menu-p'), c = $('div.menu-c'), cc = $('div.menu-c-current');
		if(/c(\d)+/.test(idx)){ //判断c（十进制）条件
			m = $('a[data-service-index="' + idx + '"]').addClass('current');
			cn = m.parents('div.menu-c');
			pn = cn.prev();
			unfoldMenu(pn, cn);
		}
	}
	
	menuCurrent();
	menuHandle();
	
});