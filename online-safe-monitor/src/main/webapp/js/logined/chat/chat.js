jQuery(document).ready(function() {
	deUser();
	getChatHistory();
	findAllChatRoomList();
			// 判断是否有添加用户的权限
			var type = $("#type").val();
			var chatRoomTwo = $("#chatRoomTwo").val();
		
			if(typeof(chatRoomTwo)=='undefined'||chatRoomTwo=='undefined'||chatRoomTwo==null||chatRoomTwo==""||chatRoomTwo=='null'){
            chatRoomTwo="";
            }
			
			if (type == 2) {
				
				document.getElementById("viewShow").style.display = 'none';
				$("#chatRoomName").html(chatRoomTwo);
			}

			// 保存选择树人员的信息,包含房间号,人员ID,人员姓名等
			saveChat();

			// 保存发起人也就是登录者的聊天室信息
			saveChatTwo();

			// 获取历史聊天记录信息
			getChatList();
		});

function getChatList() {
	var rid = $("#rid").val();

	var paramData = {

		'chatRoomId' : rid

	};
	$.ajax({
				url : basePath + "chat/findChat.action",
				type : "post",
				dataType : 'json',
				data : paramData,
				success : function(data) {

					var userList = data.userList;
					var html = "";
					for (var i = 0; i < userList.length; i++) {
						html += "<li>";
					  //html += "[" + userList[i].username + "]:";
						html += "" + userList[i].content + "";
						html += "</li>";

					}
					$("#msgview").html(html);
				}
			});

}
function findAllChatRoomList() {
	
	
	$.ajax({
				url : basePath + "chat/findAllChatRoomList.action",
				type : "post",
				dataType : 'json',
				
				success : function(data) {

					var mapList = data.mapList;
					var html = "";
					for (var i = 0; i < mapList.length; i++) {
						html += "<li>";
					  //html += "[" + userList[i].username + "]:";
						html += "" + mapList[i].chatRoomName + "";
						html += "</li>";

					}
					$("#chatRoomList").html(html);
				}
			});

}
/**
 * 得到聊天记录
 */
function getChatHistory() {
	
	var chatRoomName = $("#chatRoomName").val();
	
    if(typeof(useridsr)=='undefined'||useridsr=='undefined'){

}else{
	var paramData = {

		'chatRoomName' : chatRoomName

	};
	$.ajax({
				url : basePath + "chat/findChat.action",
				type : "post",
				dataType : 'json',
				data : paramData,
				success : function(data) {
                   
					var chatList = data.chatList;
					var html = "";
					for (var i = 0; i < chatList.length; i++) {
						html += "<li>";
					  //html += "[" + userList[i].username + "]:";
						html += "" + chatList[i].content + "";
						html += "</li>";

					}
					$("#msgview").html(html);
				}
			});

}
}
function chatMessage() {

	var rid = $("#rid").val();
	var username = $("#username").val();
	var userids = $("#userids").val();
	var treeType = $("#treeType").val();
    var chatRoomName = $("#chatRoomName").html();
	var paramData = {
		'userIds' : userids,
		'chatRoomId' : rid,
		'userNames' : username,
		'treeType' : treeType,
		'chatRoomName' : chatRoomName

	};

	$.ajax({
				url : basePath + "chat/chatMessage.action",
				type : "post",
				dataType : 'json',
				data : paramData,
				success : function(data) {

				}
			});

}

// 保存聊天室人员信息,包含房间号,人员ID,人员姓名等
function saveChat() {

	var rid = $("#rid").val();
	// 如果是1表现 在线 2表示全部
	var treeType = $("#treeType").val();
	var username = $("#username").val();
	var userids = $("#userids").val();
	// 把聊天人员的信息添加到数据库
	var paramData = {
		'userIds' : userids,
		'chatRoomId' : rid,
		'userNames' : username,
		'treeType' : treeType
	};
	$.ajax({
				url : basePath + "chatlist/saveChatList.action",
				type : "post",
				dataType : 'json',
				data : paramData,
				success : function(data) {

				}
			});

	// 聊天的人员的信息的信息
}
function saveChatTwo() {

	var rid = $("#rid").val();

	var username = $("#usern").val();
	var userids = $("#userd").val();
	// 把聊天人员的信息添加到数据库
	var paramData = {
		'userIds' : userids,
		'chatRoomId' : rid,
		'userNames' : username
	};
	$.ajax({
				url : basePath + "chatlist/saveChatList.action",
				type : "post",
				dataType : 'json',
				data : paramData,
				success : function(data) {

				}
			});

	// 聊天的人员的信息的信息
}
//得到选择的是单个人或是单位的聊天的id 赋给页面上的一个隐含域内
function deUser(){
var finalUserId="";
var useridsr = $("#userids").val();
if(typeof(useridsr)=='undefined'||useridsr=='undefined'){

}else{
if (useridsr.substr(0, 4) == "uid_")
{
   finalUserId=useridsr.substr(4);
   
    $("#finaluserids").val(finalUserId);
   
}else
{
	
	    useridsr=useridsr.substr(4)
	
							var paramData = {'userids' : useridsr};
							$.ajax({
				url : basePath + "chat/getUserByGroupId.action",
				type : "post",
				dataType : 'json',
				data : paramData,
				success : function(data) {
					
                   
                 finalUserId=data.finaluserids; 
                $("#finaluserids").val(finalUserId);
				}
			});


}
}
}