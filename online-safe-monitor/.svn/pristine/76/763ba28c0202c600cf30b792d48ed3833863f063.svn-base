$(document).ready(function() {
	if ($("#type").val() == "new") {
		// 清楚cookie
		$.removeTableCookie('SpryMedia_DataTables_Table_search.jsp');
	}
	getCountdownList();
	// 保存按钮绑定事件
	$(".add").bind("click", function() {
				window.location = basePath
						+ "logined/calendar/countdown/add.jsp";
			});
});

/**
 * 查询日志获取列表
 */
function getCountdownList() {
	$('#Table').dataTable({
		"bDestroy" : true,
		"bProcessing" : true,
		"bStateSave" : true, // 状态保存
		'bServerSide' : true,
		'fnServerParams' : function(aoData) {
			aoData.push();
		},
		"sAjaxSource" : basePath + "calendar/task_getCountdownList.action",// 获取联系人列表
		"sPaginationType" : "full_numbers",
		"bPaginate" : true, // 翻页功能
		"bLengthChange" : false, // 改变每页显示数据数量
		"bFilter" : false, // 过滤功能
		"bSort" : false, // 排序功能
		"bInfo" : true,// 页脚信息
		"bAutoWidth" : false,// 自动宽度
		"iDisplayLength" : 10, // 每页显示多少行
		"aoColumns" : [{
					"sTitle" : '排序号',
					"mDataProp" : "orderNo"
				}, {
					"sTitle" : '名称',
					"mDataProp" : "content",
					"sClass" : "longTxt"
				}, {
					"sTitle" : '开始日期 ',
					"mDataProp" : "beginTime"
				}, {
					"sTitle" : '截止日期',
					"mDataProp" : "endTime"
				}, {
					"sTitle" : '状态 ',
					"mDataProp" : "state"
				}, {
					"sTitle" : '操作 ',
					"mDataProp" : null
				}],
		"oLanguage" : {
			"sUrl" : basePath + "plugins/datatable/cn.txt" // 中文包
		},
		"fnDrawCallback" : function(oSettings) {
			$('#Table tbody  tr td[class="longTxt"]').each(function() {
				this.setAttribute('title', $(this).text());
			});
		},
		"fnInitComplete" : function() {
			// 重置iframe高度
			window.parent.frameResize();
		},
		"aoColumnDefs" : [{
			"aTargets" : [5],
			"fnRender" : function(oObj) {
				var vid = oObj.aData.id;
				var urlUpdate = basePath
						+ "calendar/toCountdownUpdate.action?countdownId="
						+ vid;
				return '<a href="'
						+ urlUpdate
						+ '" class="view_url" id="">修改</a><a href="javascript:void(0);" class="view_url" id="" onclick="delCountdown('
						+ vid + ');">删除</a>';
			}
		}]
	});
}

/**
 * 删除倒计时
 * 
 * @param id
 * @return
 */
function delCountdown(id) {
	art.dialog.confirm('确认要删除吗?', function() {
		var urlStr = basePath
				+ "calendar/task_delCountdown.action?countdownId=" + id;
		$.ajax({
			url : urlStr,
			type : "post",
			dataType : 'json',
			success : function(data) {
				if (data == 0) {
					art.dialog({
						   title:"消息",
						   content:"删除成功。",
						   width : 317,
						   height : 109,
						   icon:"succeed",
						   opacity:0.3,
						   lock:true,
						   ok:function(){},
						   close:function(){
							   var url = basePath
								+ "logined/calendar/countdown/search.jsp";
							   window.document.location = url;
						   }
						});
				} else {
					art.dialog.alert("删除失败！");
					// window.location.reload();
				}
			}
		});
	}, function() {
	});
}