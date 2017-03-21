/**
 * @author REN
 */
$(document).ready(function() {
			$
					.removeTableCookie('SpryMedia_DataTables_myTable_searchChat.jsp');
			// 处理事件的函数
			function FSubmit(e) {
				if (e == 13) {
					// 获取search内容
					jQuery("#search").trigger("click");
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
					$("#input").hide();
					$("#list").show();
					getDataTable();
				}

			}

			// document.getElementById("endtime").value=getNowFormatDate();

			$("#list").hide();

			// getDataTable();

			$("#search").click(function() {
						$("#input").hide();
						$("#list").show();
						getDataTable();
						return false;
					});
			// 单击返回
			$("#backBtn").click(function() {
						$("#input").show();
						$("#list").hide();
					});
						/** 选择 */
	$("#deleteChatList").click(function() {
				deleteChatList();
			/*	$("#input").hide();
				 $("#list").show();
				getDataTable();*/

				return false;
			});

		});

/**
 * 获取列表
 */
function getDataTable() {
	$('#myTable').dataTable({
				"bProcessing" : true,
				'bServerSide' : true,
				"bStateSave" : true, // 状态保存
				"bDestroy" : true,
				'fnServerParams' : function(aoData) {
					aoData.push({
								"name" : "acceptNameId",
								"value" : $.trim($("#acceptNameId").val())
							}, {
								"name" : "starttimeStr",
								"value" : $.trim($("#starttime").val())
							}, {
								"name" : "endtimeStr",
								"value" : $.trim($("#endtime").val())
							}, {
								"name" : "content",
								"value" : $.trim($("#content").val())
							}, {
								"name" : "userType",
								"value" : $.trim($("#userType").val())
							});
				},
				"sAjaxSource" : basePath + "chat/findChatAllList.action",// 获取车辆列表
				"sServerMethod" : "POST",
				"sPaginationType" : "full_numbers",
				"bPaginate" : true, // 翻页功能
				"bLengthChange" : false, // 改变每页显示数据数量
				"bFilter" : false, // 过滤功能
				"bSort" : false, // 排序功能
				"bInfo" : true,// 页脚信息
				"bAutoWidth" : false,// 自动宽度
				"iDisplayLength" : 10, // 每页显示多少行
				"aoColumns" : [

				{
							"sTitle" : '序号',
							"mDataProp" : "no",
							"sWidth" : "50px",
							"sClass" : "tdCenter"
						}, {
							"sTitle" : '聊天时间',
							"mDataProp" : "chatTime",
							"sWidth" : "130px",
							"sClass" : "tdCenter"
						}, {
							"sTitle" : '内容',
							"mDataProp" : "content",
							"sWidth" : "300px",
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

				}

			});

}
/**
 * 批量聊天纪录
 */

function deleteChatList() {

	var acceptNameId = $.trim($("#acceptNameId").val());
	var starttimeStr = $.trim($("#starttime").val());
	var endtimeStr = $.trim($("#endtime").val());
	var content = $.trim($("#content").val());
    var userType = $.trim($("#userType").val());
    
	var paramData = {
		'acceptNameId' : acceptNameId,
		'starttimeStr' : starttimeStr,
		'endtimeStr' : endtimeStr,
		'content' : content,
		'userType' : userType
		
	};

	art.dialog.confirm('确定删除指定范围内的聊天记录吗？<br/>删除后将不能删除！', function() {
				$.ajax({
							url : basePath + "chat/deleteChatList.action",
							type : "post",
							dataType : 'text',
							data : paramData,
							success : function(data) {
								if (data != "") {
									// getDataTable();
									art.dialog({
										   title:"消息",
										   content:"删除成功！",
										   width : 317,
										   height : 109,
										   icon:"succeed",
										   opacity:0.3,
										   lock:true,
										   ok:function(){},
										   close:function(){
											
										   }
										});
								} else {
									art.dialog.alert('删除失败！');
									// getDataTable();
								}
							}
						});
			}, function() {
				return;
			});
}
function delPerson() {
	// $("#acceptName").val();
	document.getElementById("acceptName").value = "";

}



/**
 * 添加文件夹信息
 */
function exportChat2() {
	
	
   
	var paramData = {
		'acceptNameId' : acceptNameId,
		'starttimeStr' : starttimeStr,
		'endtimeStr' : endtimeStr,
		'content' : content,
		'userType' : userType
		
	};
	alert(paramData);
	$.ajax({
				type : 'post',
				url : basePath + "chat/exportChat.action",
				data : paramData,
				dataType : 'json',
				success : function(data) {

					
					

				}

			});

}
function exportChat() {
	var acceptNameId = $.trim($("#acceptNameId").val());
	var starttimeStr = $.trim($("#starttime").val());
	var endtimeStr = $.trim($("#endtime").val());
	var content = $.trim($("#content").val());
    var userType = $.trim($("#userType").val());
  //  content = encodeURIComponent(content);
	var url = basePath
			+ "chat/exportChat.action?acceptNameId="
			+ acceptNameId + "&starttimeStr="
			+ starttimeStr + "&endtimeStr="
			+ endtimeStr + "&content="
			+ content + "&userType="
			+ userType;
	window.open(url);
}