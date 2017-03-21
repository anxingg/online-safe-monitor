$(document).ready(function(e) {
	// 查询按钮绑定事件
	$("#search").bind("click", function() {
		var startTime = $("#startTime").val();
		var endTime = $("#endTime").val();
		if(startTime && endTime){
			if(startTime>endTime){
				art.dialog.alert("请正确输入开始日期和结束日期！");
				return;
			}else{
				getLogList();
			}
		}
		getLogList();
	});
	
	//回车事件
	$(document).keydown(function(event){
		var code=event.which;
		if (code == 13) {
			getLogList();
			return false;
		}
	});
	
	$("#exportLog").bind("click", function() {
		exportLog();
	});
	// 查询按钮绑定事件
	$("#searchNew").bind("click", function() {
				var max = $("#max").val();
				if (max <= 0 || max > 300) {
					$("#max").val(300);
					art.dialog.alert("请输入0--300内的整数！");
					return;
				}
				getLogList();
			//	$("#searchDiv").css("display", "none");
			//	$("#tableDiv").css("display", "block");
			});
	// 头部全选复选框
	$("#Table").delegate("#total", "click", function(event) {
				checkTotal();
				event.stopPropagation();
			});
	// 删除
	$(".delete").live("click", function() {
				var cks = $("input[name='ck']:checked");
	
				if (cks.length == 0) {
					art.dialog.alert("请选择待删除的信息! ");
					return;
				}
				art.dialog.confirm('确认要删除吗?', function() {
							deleteObject();
						}, function() {
	
						});
			});
	
	// 列表页返回
	$("#returnback").bind("click", function() {
				returnback();
			});
	
	getLogList();
			
});

// 处理事件的函数
function FSubmit(e) {
	if (e == 13) {
		// 获取search内容
		jQuery("#search").trigger("click");
		e.returnValue = false;
		// 返回false，在没有使用document.loginForm.submit()时可用于取消提交
	}
}

/**
 * 确定按钮
 * 
 * @return
 */
function searchMethod() {
	var searchType = $("input[name='searchType']:checked").val();
	if (searchType == 1) {// 查询
		getLogList();
		$("#searchDiv").css("display", "none");
		$("#searchDiv").css("display", "none");
		$("#tableDiv").css("display", "block");
	} else if (searchType == 2) {// 导出
		exportLog();
	} else if (searchType == 3) {// 删除
		deleteListByParam();
	}
}
/**
 * 头部全选记录
 */
function checkTotal() {
	var isTotalCheck = $("input:checkbox[id='total']").prop("checked");
	var checkNum = 0;
	if (isTotalCheck) {
		$("input:checkbox[name='ck']").prop("checked", function(i, val) {
					checkNum = checkNum + 1;
					return true;
				});
	} else {
		$("input:checkbox[name='ck']").prop("checked", false);
	}
}

/**
 * 选择记录
 */
function check() {
	var checkTotalNum = $("input:checkbox[name='ck']");
	var isAllChecked = true;
	checkTotalNum.each(function(i, val) {
				if ($(checkTotalNum[i]).prop("checked")) {
				} else {
					isAllChecked = false;
				}
			});
	if (!isAllChecked) {
		$("input:checkbox[id='total']").prop("checked", false);
	} else {
		$("input:checkbox[id='total']").prop("checked", true);
	}
}

/**
 * 批量删除
 * 
 * @return
 */
function deleteObject() {
	var cks = $("input[name='ck']:checked");

	if (cks.length == 0) {
		art.dialog.alert("请选择待删除的信息! ");
		return;
	}
	var deleteIds = "";
	// 修改选中行
	for (var i = 0; i < cks.length; i++) {
		deleteIds += $(cks[i]).val() + ",";
	}
	if (deleteIds != "") {
		deleteIds = deleteIds.substring(0, deleteIds.length - 1);
	}

	$.ajax({
				url : basePath + "log/log_deleteListByIds.action?deleteIds="
						+ deleteIds,
				type : "post",
				dataType : "html",
				success : function(data) {
					if (data == 0) {
						//art.dialog.alert("删除成功！", function() {
									getLogList();
						//		});

					} else {
						art.dialog.alert("删除失败！");
					}
				}
			});
}

/**
 * 查询日志获取列表
 */
function getLogList() {
	$('#Table').dataTable({
		"bDestroy" : true,
		"bProcessing" : true,
		'bServerSide' : true,
		'fnServerParams' : function(aoData) {
			aoData.push({
						"name" : "logType",
						"value" : $("#logType").val()
					}, {
						"name" : "userIds",
						"value" : $.trim($("#userIds").val())
					}, {
						"name" : "startTime",
						"value" : $.trim($("#startTime").val())
					}, {
						"name" : "endTime",
						"value" : $.trim($("#endTime").val())
					}, {
						"name" : "ip",
						"value" : $.trim($("#ip").val())
					}, {
						"name" : "max",
						"value" : $("#max").val()
					}, {
						"name" : "userName",
						"value" : $("#loginUserName").val()
					}, {
						"name" : "remark",
						"value" : $.trim($("#remark").val())
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
		"iDisplayLength" : 300, // 每页显示多少行
		"aoColumns" : [ {
					"sTitle" : '时间',
					"mDataProp" : "insertTime",
					"sWidth" : "150px",
				}, {
					
					"sTitle" : '用户姓名',
					"mDataProp" : "userName"
				}, {
					"sTitle" : 'IP地址',
					"mDataProp" : "ip",
					"sClass" : "longTxt"
				}, {
					"sTitle" : '日志类型',
					"mDataProp" : "logType"
				}, {
					"sTitle" : '备注',
					"mDataProp" : "remark",
					"sClass" : "longTxt"
				}],
		"oLanguage" : {
			"sUrl" : basePath + "plugins/datatable/cn.txt" // 中文包
		},
		"fnDrawCallback" : function(oSettings) {
			$('#Table tbody  tr td[class="longTxt"]').each(function() {
  				this.setAttribute('title', $(this).text());
  			});
			$("#total").prop("checked", false);
		},
		"fnInitComplete" : function() {
			// 重置iframe高度
			window.parent.frameResize();
		},
		"aoColumnDefs" : [ {
			"aTargets" : [3],
			"fnRender" : function(oObj) {
				var logType = oObj.aData.logType;
				var logTypeName = "";
				if (logType == 0) {
					logTypeName = "全部日志";
				} /*else if (logType == 1) {
					logTypeName = "登录日志";
				} else if (logType == 2) {
					logTypeName = "密码错误";
				} 
				else if (logType == 3) {
					logTypeName = "用户名错误";
				}  else if (logType == 4) {
					logTypeName = "清除密码";
				} else if (logType == 5) {
					logTypeName = "非法IP登录";
				} else if (logType == 6) {
					logTypeName = "退出系统";
				} */
				else if (logType == 7) {
					logTypeName = "用户添加";
				} else if (logType == 8) {
					logTypeName = "用户修改";
				} else if (logType == 9) {
					logTypeName = "用户删除";
				} 
				/*
				else if (logType == 10) {
					logTypeName = "用户离职";
				} 
				 */
				else if (logType == 11) {
					logTypeName = "登录密码修改";
				} 
				/*
				else if (logType == 12) {
					logTypeName = "部门添加";
				} else if (logType == 13) {
					logTypeName = "部门修改";
				} else if (logType == 14) {
					logTypeName = "部门删除";
				} else if (logType == 15) {
					logTypeName = "公告删除";
				}  else if (logType == 16) {
					logTypeName = "邮件发送";
				} else if (logType == 17) {
					logTypeName = "邮件删除";
				} else if (logType == 18) {
					logTypeName = "按模块设置管理范围";
				}
				*/
				 else if (logType == 25) {
					logTypeName = "重置单位密码";
				}else if (logType == 26) {
					logTypeName = "事故新增";
				} else if (logType == 27) {
					logTypeName = "事故修改";
				} else if (logType == 28) {
					logTypeName = "事故删除";
				} else if (logType == 29) {
					logTypeName = "公司危险化学品新增";
				} else if (logType == 30) {
					logTypeName = "公司危险化学品修改";
				} else if (logType == 31) {
					logTypeName = "公司危险化学品删除";
				} else if (logType == 32) {
					logTypeName = "重大危险源新增";
				} else if (logType == 33) {
					logTypeName = "重大危险源修改";
				} else if (logType == 34) {
					logTypeName = "重大危险源删除";
				} else if (logType == 35) {
					logTypeName = "安全隐患排查新增";
				} else if (logType == 36) {
					logTypeName = "安全隐患排查修改";
				} else if (logType == 37) {
					logTypeName = "安全隐患排查删除";
				} else if (logType == 38) {
					logTypeName = "年度培训新增";
				} else if (logType == 39) {
					logTypeName = "年度培训修改";
				} else if (logType == 40) {
					logTypeName = "年度培训删除";
				} else if (logType == 41) {
					logTypeName = "安全培训记录新增";
				} else if (logType == 42) {
					logTypeName = "安全培训记录修改";
				} else if (logType == 43) {
					logTypeName = "安全培训记录删除";
				} else if (logType == 44) {
					logTypeName = "岗前三级培训新增";
				} else if (logType == 45) {
					logTypeName = "岗前三级培训修改";
				} else if (logType == 46) {
					logTypeName = "岗前三级培训删除";
				} else if (logType == 47) {
					logTypeName = "题库管理新增";
				} else if (logType == 48) {
					logTypeName = "题库管理修改";
				} else if (logType == 49) {
					logTypeName = "题库管理删除";
				} else if (logType == 50) {
					logTypeName = "题库管理生效";
				} else if (logType == 51) {
					logTypeName = "题库管理失效";
				} else if (logType == 52) {
					logTypeName = "试卷管理新增";
				} else if (logType == 53) {
					logTypeName = "试卷管理生效";
				} else if (logType == 54) {
					logTypeName = "试卷管理失效";
				} else if (logType == 55) {
					logTypeName = "试卷管理删除";
				} else if (logType == 56) {
					logTypeName = "应急演练新增";
				} else if (logType == 57) {
					logTypeName = "应急演练修改";
				} else if (logType == 58) {
					logTypeName = "应急演练删除";
				} else if (logType == 59) {
					logTypeName = "应急预案新增";
				} else if (logType == 60) {
					logTypeName = "应急预案修改";
				} else if (logType == 61) {
					logTypeName = "应急预案删除";
				} else if (logType == 62) {
					logTypeName = "企业注册";
				} else if (logType == 63) {
					logTypeName = "企业信息修改";
				} /*else if (logType == 64) {
					logTypeName = "企业法人新增";
				} */else if (logType == 65) {
					logTypeName = "企业法人修改";
				} else if (logType == 66) {
					logTypeName = "企业证照修改";
				} else if (logType == 67) {
					logTypeName = "企业证照删除";
				} else if (logType == 68) {
					logTypeName = "特种作业人员新增";
				} else if (logType == 69) {
					logTypeName = "特种作业人员修改";
				} else if (logType == 70) {
					logTypeName = "特种作业人员删除";
				} else if (logType == 71) {
					logTypeName = "安全管理人员新增";
				} else if (logType == 72) {
					logTypeName = "安全管理人员修改";
				} else if (logType == 73) {
					logTypeName = "安全管理人员删除";
				} else if (logType == 74) {
					logTypeName = "企业产品新增";
				} else if (logType == 75) {
					logTypeName = "企业产品修改";
				} else if (logType == 76) {
					logTypeName = "企业产品删除";
				} else if (logType == 77) {
					logTypeName = "危险化学品新增";
				} else if (logType == 78) {
					logTypeName = "危险化学品修改";
				} else if (logType == 79) {
					logTypeName = "危险化学品删除";
				} else if (logType == 80) {
					logTypeName = "职业卫生专家新增";
				} else if (logType == 81) {
					logTypeName = "职业卫生专家修改";
				} else if (logType == 82) {
					logTypeName = "职业卫生专家删除";
				} else if (logType == 83) {
					logTypeName = "数据字典新增";
				} else if (logType == 84) {
					logTypeName = "数据字典修改";
				} else if (logType == 85) {
					logTypeName = "数据字典删除";
				} else if (logType == 86) {
					logTypeName = "公告发布";
				} else if (logType == 87) {
					logTypeName = "公告存草稿";
				} else if (logType == 88) {
					logTypeName = "公告修改";
				} else if (logType == 89) {
					logTypeName = "公告删除";
				} else if (logType == 90) {
					logTypeName = "公告置顶";
				} else if (logType == 91) {
					logTypeName = "公告取消置顶";
				} else if (logType == 92) {
					logTypeName = "公告终止";
				} else if (logType == 93) {
					logTypeName = "公告生效";
				} else if (logType == 94) {
					logTypeName = "政策法规发布";
				} else if (logType == 95) {
					logTypeName = "政策法规存草稿";
				} else if (logType == 96) {
					logTypeName = "政策法规修改";
				} else if (logType == 97) {
					logTypeName = "政策法规删除";
				} else if (logType == 98) {
					logTypeName = "政策法规置顶";
				} else if (logType == 99) {
					logTypeName = "政策法规取消置顶";
				} else if (logType == 100) {
					logTypeName = "政策法规终止";
				} else if (logType == 101) {
					logTypeName = "政策法规生效";
				} else if (logType == 102) {
					logTypeName = "危险化学品目录新增";
				} else if (logType == 103) {
					logTypeName = "危险化学品目录修改";
				} else if (logType == 104) {
					logTypeName = "危险化学品目录删除";
				} else if (logType == 105) {
					logTypeName = "应急机构管理新增";
				} else if (logType == 106) {
					logTypeName = "应急机构管理修改";
				} else if (logType == 107) {
					logTypeName = "应急机构管理删除";
				} else if (logType == 108) {
					logTypeName = "救援物资新增";
				} else if (logType == 109) {
					logTypeName = "救援物资修改";
				} else if (logType == 110) {
					logTypeName = "救援物资删除";
				} else if (logType == 111) {
					logTypeName = "安全管理机构修改";
				} else if (logType == 112) {
					logTypeName = "工艺流程新增";
				} else if (logType == 113) {
					logTypeName = "工艺流程修改";
				} else if (logType == 114) {
					logTypeName = "工艺流程删除";
				} else if (logType == 115) {
					logTypeName = "重大危险源危化品目录对象新增";
				} else if (logType == 116) {
					logTypeName = "重大危险源危化品目录对象修改";
				} else if (logType == 117) {
					logTypeName = "重大危险源危化品目录对象删除";
				} else if (logType == 118) {
					logTypeName = "安全生产费用提取新增";
				} else if (logType == 119) {
					logTypeName = "安全生产费用使用新增";
				} else if (logType == 120) {
					logTypeName = "非煤矿山专家新增";
				} else if (logType == 121) {
					logTypeName = "非煤矿山专家修改";
				} else if (logType == 122) {
					logTypeName = "非煤矿山专家删除";
				} else if (logType == 123) {
					logTypeName = "危险化学品专家新增";
				} else if (logType == 124) {
					logTypeName = "危险化学品专家修改";
				} else if (logType == 125) {
					logTypeName = "危险化学品专家删除";
				}
				
				return logTypeName;
			}
		}]
	});
}

/**
 * 导出
 * 
 * @return
 */
function exportLog() {
	// 导出文件
	var logType = $("#logType").val();
	var userIds = $.trim($("#userIds").val());
	var startTime = $.trim($("#startTime").val());
	var endTime = $.trim($("#endTime").val());
	var ip = $.trim($("#ip").val());
	var remark = $.trim($("#remark").val());
	var userName = $("#loginUserName").val();
	var max = 10000000;

	document.location = basePath + "log/log_exportLog.action?logType="
			+ logType + "&userIds=" + userIds + "&startTime=" + startTime
			+ "&endTime=" + endTime + "&ip=" + ip + "&remark=" + remark
			+ "&max=" + max + "&userName=" + userName;
}

/**
 * 查询删除
 * 
 * @return
 */
function deleteListByParam() {
	var logType = $("#logType").val();
	var userIds = $.trim($("#userIds").val());
	var startTime = $.trim($("#startTime").val());
	var endTime = $.trim($("#endTime").val());
	var ip = $.trim($("#ip").val());
	var remark = $.trim($("#remark").val());
	var max = $("#max").val();
	var paramData = {
		'logType' : logType,
		'userIds' : userIds,
		'startTime' : startTime,
		'endTime' : endTime,
		'ip' : ip,
		'remark' : remark,
		'max' : max
	};

	var urlStr = basePath + "log/log_deleteListByParam.action";
	$.ajax({
				url : urlStr,
				type : "post",
				dataType : 'json',
				data : paramData,
				success : function(data) {
					//art.dialog.alert("删除成功！", function() {
								getLogList();
					//		});
				}
			});
}
/**
 * 返回搜索页面
 * 
 * @return
 */
function returnback() {
	$("#max").val(300);
	$("#searchDiv").css("display", "block");
	$("#tableDiv").css("display", "none");
}

/**
 * 清空共享人员
 * 
 * @return
 */
function clearUser() {
	$("#userIds").val("");
	$("#userNames").val("");
}
document.onkeydown = function(){
	if(event.keyCode == 13){
		getLogList();
	}
}