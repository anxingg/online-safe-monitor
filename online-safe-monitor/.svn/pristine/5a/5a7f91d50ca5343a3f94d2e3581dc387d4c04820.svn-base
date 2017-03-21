/**
 * 人员选择
 */
function selectUser(obj) {
    
	openSelectUser(3, function(data, param) {
		var neiUserIds = "";
				var userIds = ",";
				var userNames = "";
				data.forEach(function(value, key) {
						    neiUserIds += value.Id + ",";
							userIds = value.Id;
							userNames = value.Name;
							//根据id判断跟该人的性别
							var sex="";
							var paramData = {'userId' : userIds};
							$.ajax({
				url : basePath + "chat/getUserById.action",
				type : "post",
				dataType : 'json',
				data : paramData,
				success : function(data) {
					
                    sex=data.sex
                  
                     
				}
			});

							// 如果用户已经在聊天室中,则不再发送事务请求
							var userInfo = _userList.get(userIds + "");
							if (null == userInfo) {
								var user = new User(0, userIds, userNames);
								_userList.put(userIds, user);
								
								var image="";
                                     if(sex==1)
     						         {
     						         alert(sex);
     						         image=basePath+'images/luntanimages/chatmen.png';
     						       
     						         }
                                     if(sex==0)
     						         {
     						        
     						          image=basePath+'images/luntanimages/chatWomen.png';
     						        
     						         }
								var tbBody = '<li class="onlinelist" value='
										+ userIds + ' ><span></span><img src='+image+' style="margin-right:10px"></img>'
										+ userNames + ' </li>';
								$("#userListUL").append(tbBody);

								var paramData = {
									'userIds' : userIds,
									'chatRoomId' : $("#rid").val(),
									'userNames' : userNames,
									'sex':sex
								};

								$.ajax({
											url : basePath
													+ "chat/chatMessage.action",
											type : "post",
											dataType : 'json',
											data : paramData,
											success : function(data) {

											}
										});

								$.ajax({
											url : basePath
													+ "chatlist/saveChatList.action",
											type : "post",
											dataType : 'json',
											data : paramData,
											success : function(data) {

											}
										});
							}

						});
						$("#neiUserIds").val(neiUserIds);
			}, "user", $("#userIds").val());// 选择人员
			
			
}
