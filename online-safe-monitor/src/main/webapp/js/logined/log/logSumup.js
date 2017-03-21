$(document).ready(function() {
			getAllLogNums();
			getLogList();
		});

/**
 * 查询日志获取列表
 */
function getLogList() {
	if (typeof oTable == 'undefined') {
		oTable = $('#Table').dataTable({
					"bProcessing" : true,
					'bServerSide' : true,
					'fnServerParams' : function(aoData) {
						aoData.push({
									"name" : "logType",
									"value" : 0
								}, {
									"name" : "userIds",
									"value" : ""
								}, {
									"name" : "startTime",
									"value" : ""
								}, {
									"name" : "endTime",
									"value" : ""
								}, {
									"name" : "ip",
									"value" : ""
								}, {
									"name" : "max",
									"value" : $("#max").val()
								}, {
									"name" : "remark",
									"value" : ""
								});
					},
					"sAjaxSource" : basePath + "log/log_getLogList.action",
					"sServerMethod" : "POST",
					"sPaginationType" : "full_numbers",
					"bPaginate" : false, // 翻页功能
					"bLengthChange" : false, // 改变每页显示数据数量
					"bFilter" : false, // 过滤功能
					"bSort" : false, // 排序功能
					"bInfo" : false,// 页脚信息
					"bAutoWidth" : false,// 自动宽度
					"iDisplayLength" : 10, // 每页显示多少行
					"aoColumns" : [{
								"sTitle" : '时间',
								"mDataProp" : "insertTime"
								
							}, {
								
								"sTitle" : '用户姓名',
								"mDataProp" : "userName"
							}, {
								"sTitle" : 'IP地址',
								"mDataProp" : "ip",
								"sClass" : ""
							}, {
								"sTitle" : '日志类型',
								"mDataProp" : "logType",
							}, {
								"sTitle" : '备注',
								"mDataProp" : "remark",
								"sClass" : "longTxt"
							}],
					"oLanguage" : {
						"sUrl" : basePath + "plugins/datatable/cn.txt" // 中文包
					},
					"fnDrawCallback" : function(oSettings) {
						if (oTable.fnGetData().length > 0) {
							// 重置iframe高度
							window.parent.frameResize();
						}
						 $('#Table tbody  tr td[class="longTxt"]').each(function() {
				  				this.setAttribute('title', $(this).text());
				  			});
					},
					"fnInitComplete" : function() {
						// 重置iframe高度
						window.parent.frameResize();
					},
					"aoColumnDefs" : [{
								"aTargets" : [3],
								"fnRender" : function(oObj) {
									var logType = oObj.aData.logType;
									var logTypeName = "";
									if (logType == 0) {
										logTypeName = "全部日志";
									} else if (logType == 1) {
										logTypeName = "登录日志";
									} else if (logType == 2) {
										logTypeName = "密码错误";
									} else if (logType == 3) {
										logTypeName = "用户名错误";
									} else if (logType == 4) {
										logTypeName = "清除密码";
									} else if (logType == 5) {
										logTypeName = "非法IP登录";
									} else if (logType == 6) {
										logTypeName = "退出系统";
									} else if (logType == 7) {
										logTypeName = "用户添加";
									} else if (logType == 8) {
										logTypeName = "用户修改";
									} else if (logType == 9) {
										logTypeName = "用户删除";
									} else if (logType == 10) {
										logTypeName = "用户离职";
									} else if (logType == 11) {
										logTypeName = "用户修改登录密码";
									} else if (logType == 12) {
										logTypeName = "部门添加";
									} else if (logType == 13) {
										logTypeName = "部门修改";
									} else if (logType == 14) {
										logTypeName = "部门删除";
									} else if (logType == 15) {
										logTypeName = "公告通知管理";
									} else if (logType == 16) {
										logTypeName = "公共文件柜";
									} else if (logType == 17) {
										logTypeName = "邮件删除";
									} else if (logType == 18) {
										logTypeName = "按模块设置管理范围";
									}
									return logTypeName;
								}
							}]
				});
	} else {
		var oSettings = oTable.fnSettings();
		oSettings._iDisplayStart = 0;
		oTable.fnClearTable();
	}
}
/**
 * 统计结果
 * 
 * @return
 */
function getAllLogNums() {
	var urlStr = basePath + "log/log_getAllLogNums.action";
	$.ajax({
				url : urlStr,
				type : "post",
				dataType : 'json',
				success : function(data) {
					$("#allDay").html(data.allDay);
					$("#allNum").html(data.allNum);
					$("#thisYearNum").html(data.thisYearNum);
					$("#thisMonthNum").html(data.thisMonthNum);
					$("#todayNum").html(data.todayNum);
					$("#averageNum").html(data.averageNum);
				}
			});
}