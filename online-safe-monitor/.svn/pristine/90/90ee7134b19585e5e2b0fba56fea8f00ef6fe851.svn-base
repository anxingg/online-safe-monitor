jQuery(document).ready(function() {

//	getChatHistory();
	getDataTable() ;
	/** 全选 */
	$("#searchChat").click(function() {
	
				//getChatHistory();
				getDataTable() ;
				return false;
			});
			
			
			
				// 处理事件的函数
			function FSubmit(e) {
				if (e == 13) {
					// 获取search内容
					jQuery("#searchChat").trigger("click");
					e.returnValue = false;
					// 返回false，在没有使用document.loginForm.submit()时可用于取消提交
				}
			}

			// 回车事件
			document.onkeydown = function(e) {
				// 兼容FF和IE和Opera
				var theEvent = e || window.event;
				var code = theEvent.keyCode || theEvent.which
						|| theEvent.charCode;

				if (code == 13) {
					
					getDataTable();
				}

			}

	
		});


/**
 * 得到聊天记录
 */
function getChatHistory() {
	var chatRoomName = $("#chatRoomName").val();
	var content = $("#content").val();
	var chatTime = $("#chatTime").val();
    chatRoomName=encodeURIComponent(chatRoomName);
	var paramData = {

		'chatRoomName' : chatRoomName,
		'content':content,
		'chatTime':chatTime

	};
	$.ajax({
				url : basePath + "chat/getChatHistory.action",
				type : "post",
				dataType : 'json',
				data : paramData,
				success : function(data) {
                   
					var chatList = data.aaData;
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

function getDataTable() {
	$('#myTable').dataTable({
		"bProcessing" : false,
		'bServerSide' : true,
		"bStateSave" : false, // 状态保存
		"bDestroy" : true,
		'fnServerParams' : function(aoData) {
			aoData.push({
						"name" : "chatRoomName",
						"value" : $("#chatRoomName").val()
					}, {
						"name" : "content",
						"value" : $.trim($("#content").val())
					}, {
						"name" : "chatTime",
						"value" : $.trim($("#chatTime").val())
					}
			);
		},
		"sAjaxSource" : basePath + "chat/getChatHistory.action",
		"sServerMethod" : "POST",
		"sPaginationType" : "full_numbers",
		"bPaginate" : true, // 翻页功能
		"bLengthChange" : false, // 改变每页显示数据数量
		"bFilter" : false, // 过滤功能
		"bSort" : false, // 排序功能
		"bInfo" : true,// 页脚信息
		"bAutoWidth" : false,// 自动宽度
		"iDisplayLength" : 20, // 每页显示多少行
		"aoColumns" : [
				{			
					"mDataProp" : "content",
					"sWidth" : "150px",
					"sClass" : "longTxt"
				}

		],
		"oLanguage" : {
			"sUrl" : basePath + "plugins/datatable/cn.txt" // 中文包
		},
		"fnDrawCallback" : function(oSettings) {
			// 提示
			$('#myTable tbody  tr td[class="longTxt"]').each(function() {
				this.setAttribute('title', $(this).text());
			});
			$("#total").prop("checked", false);
			window.parent.frameResize();

		},
		"fnInitComplete" : function() {
			// 重置iframe高度
			window.parent.frameResize();
		}

		

	});
}
function setChatRoomValue()
{
	$("#chatRoomName").val("");
	
}