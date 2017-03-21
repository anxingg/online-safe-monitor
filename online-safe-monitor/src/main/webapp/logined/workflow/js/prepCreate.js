jQuery(document).ready(function($){
	var createButton = function(){
		var api  = art.dialog.open.api;
		api.button(
			{
				name:"新建",
				callback: function () {
				var d = new Date();
				var seconds = d.getSeconds();
				var tabName = "newCreate"+seconds;
				window.parent.parent.addTab(tabName, basePath+"/workflow/manager!createDefine.action?tabName="+tabName, "新建流程");
				},
				focus: true
			},
			{
				name:"复制",
				disabled:true
			},
			{
				name:"取消",
				callback:function(){
					art.dialog.close();
				}
			}
		);
	}
	var copyButton = function(id){
		var api = art.dialog.open.api;
		api.button(
			{
				name:"新建",
				disabled:true
			},
			{
				name:"复制",
				callback: function () {
					var processId = $(".icon-selected").attr("processId");
					if(!processId){
						art.dialog.alert("没有流程可供复制!");
						return false;
					}else{
					window.parent.parent.addTab("copyProcess", 
						basePath+"/workflow/manager!copyProcess.action?processAttributeId="+processId, "复制流程");
				}
				},
				focus: true
			},
			{
				name:"取消",
				callback:function(){
					art.dialog.close();
				}
			}
		);
	}
	createButton();
	$("li.item").click(function(){
		$("li.item").each(function(){
			$(this).removeClass("active");
		});
		var cateId = $(this).attr("id");
		var name = $(this).html();
		$("#ullist").html("");
		$(this).addClass("active");
		$("#showTitle").html(name);
		if(!cateId){
			createButton();
			$("#processNums").html("空白流程(1)");
			$("#ullist").append('<li class="icon-selected" isfolder=""><div class="icocontainer"><div class="selectcontainer" title="空白流程"><div class="ico ico-blank"></div><a>空白流程</a></div></div></li>');
			return ;
		}
		copyButton();
		$.get(basePath+"/workflow/manager!findProcessListByCategoryId.action?categoryId="+cateId,function(result){		
			if(!result){
				return;
			}	
			result = eval('('+result+')');
			$("#processNums").html(name+"("+result.length+")");
			for(var i=0; i<result.length; i++){
				var temp = result[i];
				var li = document.createElement("li");
				$(li).attr("processId",temp.id);
				if(i ==0){
					$(li).addClass("icon-selected");
				}
				$(li).append("<div class='icocontainer'><div class='selectcontainer' "
					+"title='"+temp.name+"'><div class='ico ico-item'></div><a>"+temp.name+"</a></div></div>");
				
				$(li).hover(function(){
				$(this).addClass("icon-hover");
				},function(){
				$(this).removeClass("icon-hover");
				});

				$(li).click(function(){
					$(".icon-selected").removeClass("icon-selected");
					$(this).addClass("icon-selected");
				});
				$("#ullist").append(li);
			}
		});
	});
});