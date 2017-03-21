jQuery(document).ready(function($){

	//新建流程
/* 不在使用向导功能 直接跳转设计流程
$("#flow_creat").click(function(){
		//window.parent.parent.parent.addTab("newCreate",basePath+"/workflow/manager!createDefine.action","新建流程");
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
	});
*/
	//新建
	$("#flow_creat").click(function(){
		window.location.href=basePath+"workflow/process!createDefine.action?processAttributeId="+$("#processId").val();
		
	});
	//进入流程设计器界面
    $("#process_designer").click(function(){
        if($("#processState").val() == 2){
            art.dialog.alert("请先停用该流程!");
            return ;
        }
        if($("#instanceNum").html() == 0){

            window.location = basePath+"/workflow/jpdl!define.action?processAttributeId="+$("#processId").val();
//            art.dialog.open(basePath+"/workflow/jpdl!define.action?processAttributeId="+$("#processId").val(),{
//                title:"流程设计器",
//                width:"1000px",
//                height:"600px",
//                lock : true,
//                opacity: 0.3
//                // ok:function(){
//                // 	this.time(0.5);
//                // 	window.parent.parent.addTab("newCreate", basePath+"/workflow/manager!createDefine.action", "新建流程");
//                // 	return false;
//                // },
//                // okVal:"新建",
//                // cancel:true
//            });

        }else{
            art.dialog.alert("该流程下的工作不为0，请先结束工作再设计流程!");
        }
    })

	//进入节点属性编辑界面，进入前先验证流程是否已经设计好
	$("#node_designer").click(function(){
		//先验证
		$.get(basePath+"/workflow/jpdl!isDefine.action?processAttributeId="+$("#processId").val()+"&random="+Math.random(),function(result){
			if(result=="success"){
                window.location = basePath+"/workflow/jpdl!nodeEdit.action?processAttributeId="+$("#processId").val();
			}else{
				art.dialog.alert("请先使用流程设计器设计流程!");
			}
		});
	});

	//编辑流程
	$("#flow_edit").click(function(){
		var processId = $("#processId").val();
		window.location.href=basePath+"/workflow/process!update.action?processAttributeId="+processId;
	});

	//预览表单
	$("#flow_view").click(function(){
		var formId = $("#formId").val();
		window.parent.parent.addTab(Math.random(),basePath+"workflowForm/viewForm.action?formId="+formId,"预览表单");
	});

	//删除
	$("#flow_delete").click(function(){
		art.dialog.confirm("确认删除该流程定义?",function(){
			var processId = $("#processId").val();
			//先验证是否可删除
			$.get(basePath+"/workflow/process!isCanDelete.action?processAttributeId="+processId+"&rd="+Math.random(),function(data){
				if(data == "success"){
					$.get(basePath+"/workflow/process!deleteProcess.action?processAttributeId="+processId,function(result){
						if(result == "success"){
								art.dialog({
									title: '',
									content: '删除成功!',
									height : 109,
									width : 317,
									icon: 'succeed',
									ok: function(){
										window.parent.location.href=basePath+"workflow/manager.action";
									}
								});
						}
					});
				}else if(data == "false"){
					art.dialog.alert("请先停用该流程，并确保该流程下没有在用实例!");
				}
			});
			
		});

	});

	//校验
	$("#process_flowCheck").click(function(){
		var processId = $("#processId").val();
		
		art.dialog.open(basePath+"/workflow/process!check.action?processAttributeId="+processId,{
			title:"校验",
			width:"600px",
			height:"300px",
			lock : true,
		    opacity: 0.3,
			ok:function(){
				//window.location.href=basePath+"/workflow/process!editProcess.action?processAttributeId="+processId;
			}
		});
	});

	//发布
	$("#deploy").click(function(){
		var processId = $("#processId").val();
		$.get(basePath+"/workflow/process!isCanDelete.action?processAttributeId="+processId+"&rd="+Math.random(),function(data){
			if(data == "success"){
				$.post(basePath+"/workflow/process!deploy.action?processAttributeId="+processId,function(result){
					if(result == "success"){
						art.dialog({
										title: '消息',
										height : 109,
										width : 317,
										content: '操作成功!',
										icon: 'succeed',
										ok: function(){
											window.location.href=basePath+"/workflow/process!editProcess.action?processAttributeId="+processId;
										}
									});
					}else{
						art.dialog.alert("发布失败!");
					}
				});
			}else{
				art.dialog.alert("请先停用该流程，并确保该流程下没有在用实例!");
			}
		});
	});

	//停用
	$("#stop").click(function(){
		var processId = $("#processId").val();
		$.post(basePath+"/workflow/process!stop.action?processAttributeId="+processId,function(result){
			if(result == "success"){
				art.dialog({
								title: '消息',
								height : 109,
								width : 317,
								content: '操作成功!',
								icon: 'succeed',
								height : 109,
								width : 317,
								ok: function(){
									window.location.href=basePath+"/workflow/process!editProcess.action?processAttributeId="+processId;
								}
							});
			}
		});
	});

	//启用
	$("#start").click(function(){
		var processId = $("#processId").val();
		$.post(basePath+"/workflow/process!start.action?processAttributeId="+processId,function(result){
			if(result == "success"){
				art.dialog({
								title: '消息',
								height : 109,
								width : 317,
								content: '操作成功!',
								icon: 'succeed',
								ok: function(){
									window.location.href=basePath+"/workflow/process!editProcess.action?processAttributeId="+processId;
								}
							});
			}
		});
	});

	//预览流程
	$("#process_view").click(function(){
		var processId = $("#processId").val();
		/* art.dialog.open(basePath+"/workflow/process!view.action?processAttributeId="+processId,{
		 	title:"预览流程",
		 	width:"100%",
			height:"100%",
		 	ok:function(){}
		 });*/
		window.open(basePath+"/workflow/process!view.action?processAttributeId="+processId);
	});

});