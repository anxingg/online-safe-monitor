jQuery(document).ready(function() {
	
	var username=$("#username").val();
	var userId=$("#userId").val();
	findAllChatRoomList();
	  setInterval(function(){
	        	findAllChatRoomList();
	        }, 1*1*1000);
		
		});

/**
 * 显示所有的聊天室记录
 */
function findAllChatRoomList() {
	
	
	$.ajax({
				url : basePath + "chat/findAllChatRoomList.action",
				type : "post",
				dataType : 'json',
				success : function(data) {

					var mapList = data.mapList;
					var html = "";
					for (var i = 0; i < mapList.length; i++) {
						
					  //html += "[" + userList[i].username + "]:";
						html +=mapList[i].room+"";
						
					}
					$("#chatRoomList").html(html);
				}
			});

}
/** iframe的适应 */
function dyniframesize(down) {
	var pTar = null;
	if (document.getElementById) {
		pTar = document.getElementById(down);
	} else {
		eval('pTar = ' + down + ';');
	}
	if (pTar && !window.opera) {
		// begin resizing iframe
		pTar.style.display = "block"
		if (pTar.contentDocument && pTar.contentDocument.body.offsetHeight) {
			// ns6 syntax
			pTar.height = pTar.contentDocument.body.offsetHeight + 20;
			// pTar.width = pTar.contentDocument.body.scrollWidth + 20;
		} else if (pTar.Document && pTar.Document.body.scrollHeight) {
			// ie5+ syntax
			pTar.height = pTar.Document.body.scrollHeight;
			// pTar.width = pTar.Document.body.scrollWidth;
		}
	}

}
/*var rediectTalk = basePath + 'logined/chat/client.jsp?rid=' + RndNum(20)
			+ '&username=' + $("#username").val() + '&userids=' +  $("#userId").val() ;*/
//var rediectTalk = basePath + 'logined/chat/client.jsp?rid=' + RndNum(20);
			
function RndNum(n) {
	var rnd = "";
	for (var i = 0; i < n; i++)
		rnd += Math.floor(Math.random() * 10);
	return rnd;
};

function openChatTab() {
	var userId='uid_'+$("#userId").val();
	if ($.browser.msie) {
		event.cancelBubble = true;
	} else {
		event.stopPropagation();
	}
	var rediectTalk = basePath + 'logined/chat/client.jsp?rid=' + RndNum(20)
			+ '&username=' + encodeURI($("#username").val()) + '&userids=' + userId ;
			
			
	window.top.addTab(RndNum(15), rediectTalk, "聊天室", true, null);
};