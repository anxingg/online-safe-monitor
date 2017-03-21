	jQuery(document).ready(function(){
		$.ajaxSetup({
			contentType: "application/x-www-form-urlencoded; charset=utf-8"
		});
		var data = $("#jsonData").val();
		if(!data){
			data='{}';
		}
		$('#myflow').myflow({
							basePath : "",
							restore : eval("("+data+")"),
							tools : {
								save : {
									onclick : function(data) {
										//验证
										var dataObj = eval("("+data+")");

										//验证任务名称是否重复
										var nodeNames = {};
										var nodes = dataObj.states;
										for(var key in nodes){
											var temp = nodes[key].text.text;
											if(!nodeNames[temp]){
												nodeNames[temp] = 1;
											}else{
												art.dialog.alert("任务节点名称不能重复,请重新命名!");
												return null;
											}
										}

										//验证是否有一个开始节点并只有一个任务节点
										var flag = 0;
										for(var key in nodes){
											var type = nodes[key].type;
							
											if(type == "start"){
												flag++;
											}
										}
										if(flag ==0 ){
											art.dialog.alert("流程必须以【开始】节点开始!");
											return null;
										}
										if(flag>1){
											art.dialog.alert("流程只能有一个【开始】节点!");
											return null;
										}

										//验证流程是否有分支
										// var paths = dataObj.paths;
										// var froms = new Array();
										// for(var key in paths){
										// 	var from = paths[key].from;
										// 	for(var i=0;i<froms.length;i++){
										// 		var temp = froms[i];
										// 		if(temp == from){
										// 			art.dialog.alert("流程暂不支持分支，请删除分支！");
										// 			return ;
										// 		}
										// 	}
										// 	froms.push(from);
										// }


                                        $.post($("#proName").val() + "/workflow/jpdl!parseJsonToJpdl.action", {jsonTypeJpdl: data,processAttributeId:$("#processAttributeId").val()},
                                            function(result) {
                                                result = eval('('+result+')');
                                                if(result.states == "success"){
                                                    art.dialog({
                                                        title: '消息',
                                                        content: '保存成功！',
                                                        height : 109,
                                                        width : 317,
                                                        icon: 'succeed',
                                                        ok: function(){
                                                            //关闭流程设计器
                                                            window.close();
                                                        }
                                                    });
                                                }else{
                                                    art.dialog.alert("保存失败!");
                                                }
                                            }
                                        );

									}
								}
							}
							});

		$("#myflow_close").click(function(){
			art.dialog.confirm("确认关闭吗?",function(){
                javascript:history.back();
			});
		});
	}) ;
				

