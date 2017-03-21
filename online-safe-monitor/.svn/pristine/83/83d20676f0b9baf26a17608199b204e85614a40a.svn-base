// JavaScript Document
$(document).ready(function(){
	$(".QA_bg p").each(function(){
		toggledn($(this),"span");
	});
	
	
	//状态设置
	$('.state_bg .state_Con').hide();
	$(".state_bg").mouseover(function(){
		$(this).children('span').show();
		$(this).children('span').children('a').click(function(){
			$(this).parent().hide();
		});
	}).mouseout(function() {
        $(this).children('span').hide();
    });
	
	
});
//打开弹窗
function openbox(id)
{
 document.getElementById(id).style.display="block";
	}
function closebox(id)
{
document.getElementById(id).style.display="none";
	}


	function toggledn(o,cl){
		o.children(cl).hide();
		o.hover(function(){
			$(this).children(cl).show();
		},function(){
			$(this).children(cl).hide();
		});	
	}
	function reduceP(o){
		var div = o.parent("p").parent("div");
		o.parent("p").remove();
		var ps = div.children("p");
		for(var i = 0; i<ps.length; i++){
			ps.eq(i).children("em").text("(" + (i+1) + ")");
		}
	}
	function addP(o){
		var div = o.parent("p").parent("div");
		div.append("<p><em>("+ (div.children("p").length+1) +")&nbsp;</em><input type=\"text\" class=\"formText\" size=\"60\" /><span title=\"添加一个答案\" onclick=\"addP($(this));\" class=\"ml5 addP\">+</span><span class=\"reduce ml5\" onclick=\"reduceP($(this));\" title=\"删除选中的答案\">-</span></p>");
		toggledn(div.children("p"),"span");
		/*div.children("p").each(function(){
			$(this).children("span").eq(1).click(function(){
				reduceP($(this));
			});
			$(this).children("span").eq(0).click(function(){
				addP($(this));
			});
		});*/
	}