jQuery(document).ready(function($){
/*	$("#flow_creat").click(function(){
		art.dialog.open(basePath+"/workflow/manager!prepCreate.action",{
			title:"新建向导",
			width:"850px",
			height:"550px",
			lock : true,
		    opacity: 0.3
			// ok:function(){
			// 	this.time(0.5);
			// 	window.parent.parent.addTab("newCreate", basePath+"/workflow/manager!createDefine.action", "新建流程");
			// 	return false;
			// },
			// okVal:"新建",
			// cancel:true
		});
	});*/
	
	//新建
	$("#flow_creat").click(function(){
		var proId="";
		if($("#processId").val()!=undefined)
			proId=$("#processId").val();
		window.location.href=basePath+"/workflow/process!createDefine.action?processAttributeId="+proId;
	});
});