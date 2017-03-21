$(document).ready(function(){

	var whroletype = $("#whroletype").val();
	if(whroletype==2){//企业端登录
		gonggao();
	}
});
 function gonggao(){
	 art.dialog.open(basePath + "wh/logined/viewList.jsp?id=1", {
		    id : "openAffairs",
		    title : "通知公告",
		    width : 1100,
		    minwitch : 1100,
		    height : 505,
		    lock : true,
		    opacity: 0.1,// 透明度
		    button:[//自定义按钮
	                {
	                    name:'关闭',
	                    callback:function () {
	                        return true;
	                    }
	                }
	            ]
	});
 }
