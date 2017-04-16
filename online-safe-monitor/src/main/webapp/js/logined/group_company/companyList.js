$(document).ready(function(){
	// 新增企业绑定
	$("#add").bind("click", function() {
		bindSelect(bindSelectCallBack);
		return false;
	});
	//加载列表
	getTableList();
	
});
/**
 * 绑定选择回调
 */
function bindSelectCallBack(data)
{
	if (data) {
		var companyGroupIds = '';
		data.forEach(function(value, key) {
			companyGroupIds += value.Id + ',';
		});
		console.log("companyGroupIds:"+companyGroupIds);
		bind(companyGroupIds);
	}
}
/**
 * 绑定
 */
function bindSelect(callback)
{
	var groupId = $('#groupId').val();
	console.log("groupId:"+groupId);
	if(groupId>0){
		var url = basePath + "logined/group_company/selectcompany.jsp?groupId="+groupId
		 + "&defaultSelectId=";
		var title = "选择部门";
		art.dialog.open(url, {
			title : title,
			width : 360,
			height : 407,
			lock : true,
		    opacity: 0.08,
			button : [{
						name : '确定',
						focus: true,
						callback : function() {
//							var iframe = this.iframe.contentWindow;
//					    	alert(iframe.userMap);		
							var userMap = art.dialog.data("userMap");
							callback(userMap);
							return true;
						}
					}, {
						name : '取消',
						callback : function() {
							return true;
						}
					}]
		}, false);
	}
	else {
		art.dialog.alert("请选择区域");
	}
	
}

/**
 * 绑定企业
 * @param groupId
 */
function bind(companyGroupIds){
	var groupId = $('#groupId').val();
	//确认对话框
	$.ajax({
		url : basePath + "companywh/bind.action",
		type : "post",
		dataType : 'json',
		data : {
			companyGroupIds : companyGroupIds,
			groupId : groupId
		},
		success : function(data) {
			if (data == 1) {
				//artDialog.alert("删除成功！",function(){						
					//重新加载列表
					getTableList();
				//});
			} else if (data == 0){
				artDialog.alert("绑定失败！");
			}
		}
		
	});
	
}
/**
 * 解除绑定企业
 * @param groupId
 */
function unBind(vid){
	//确认对话框
	art.dialog.confirm('确定解除该企业绑定吗？', function() {
		$.ajax({
			url : basePath + "companywh/unBind.action",
			type : "post",
			dataType : 'json',
			data : {
				vid : vid
			},
			success : function(data) {
				if (data == 1) {
					//artDialog.alert("删除成功！",function(){						
						//重新加载列表
						getTableList();
					//});
				} else if (data == 0){
					artDialog.alert("解除失败！");
				}
			}
			
		});
	}, function() {
		return;
	});
	
}

function getTableList(){
	var groupId = $('#groupId').val();
	$('#myTable').dataTable({
		"bDestroy" : true,
		"bProcessing" : true,
		"bStateSave" : true, // 状态保存
		'bServerSide' : true,
		'fnServerParams' : function(aoData) {
			aoData.push({
					"name" : "groupId",
					"value" : groupId
				});
		},
		"sAjaxSource" : basePath + "companywh/getGroupCompanyList.action",
		"sPaginationType" : "full_numbers",
		"bPaginate" : true, // 翻页功能
		"bLengthChange" : false, // 改变每页显示数据数量
		"bFilter" : false, // 过滤功能
		"bSort" : false, // 排序功能
		"bInfo" : true,// 页脚信息
		"bAutoWidth" : false,// 自动宽度
		"iDisplayLength" : 15, // 每页显示多少行
		"aoColumns" : [{
			"sTitle" : '企业编号',
			"mDataProp" : 'companyId'
		}, {
			"sTitle" : '企业名称',
			"mDataProp" : "companyName",
			"sClass" : "longTxt"
		}, {
			"sTitle" : '经济类型',
			"mDataProp" : 'economicType',
			"sClass" : "longTxt"
		}, {
			"sTitle" : '成立时间',
			"mDataProp" : 'establishmentTime'
		}, {
			"sTitle" : '生产场所地址',
			"mDataProp" : 'productAddress'
		},{
			"sTitle" : '操作',
			"mDataProp" : null
		}],
		"oLanguage" : {
			"sUrl" : basePath + "wh/plugins/datatable/cn.txt" // 中文包
		},
		"fnDrawCallback" : function(oSettings) {

			 $('#myTable tbody  tr td').each(function() {
  				this.setAttribute('title', $(this).text());
  			});
			
			
		},
		"fnInitComplete" : function() {
			// 重置iframe高度
//			window.parent.frameResize();
		},
		"aoColumnDefs" : [
			{
				"aTargets" : [5], //操作
				"fnRender" : function(oObj) {
					var vid = oObj.aData.vid;
					var html = '';
					html += '<a href="javascript:void(0);" onclick="unBind('+vid+');">删除</a>';
					return html;
				}
			}
		]
	});
}