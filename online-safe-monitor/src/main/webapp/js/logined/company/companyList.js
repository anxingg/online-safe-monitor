$(document).ready(function(){
	//加载企业下拉列表
	getSelectCompany();
	
	// 查询按钮绑定事件
	$("#searchCompany").bind("click", function() {
		// 清除cookie中的分页信息
		$.removeTableCookie('SpryMedia_DataTables_myTable_companyList.jsp');
		getTableList();
		return false;
	});
	
	//回车事件
	$(document).keydown(function(event){
		var code=event.which;
		if (code == 13) {
			// 清除cookie中的分页信息
			$.removeTableCookie('SpryMedia_DataTables_myTable_companyList.jsp');
			getTableList();
			return false;
		}
	});
	// 保存按钮绑定事件
	$("#add").bind("click", function() {
		addCompany();
		return false;
	});
	//加载列表
	getTableList();
	
});

function getSelectCompany(){
	$.ajax({
		url : basePath + "companywh/getCompanyNameList.action",
		type : "post",
		dataType : 'json',
		data : {
			parentId:$('#parentId').val()
		},
		success : function(data) {
			if (data != null && data!="") {
				var list = eval(data);
				var html = '';
				for (var i = 0; i < list.length; i++) {
					var companyName = list[i].companyName;
					var groupId = list[i].groupId;
					html += '<option value="'+groupId+'">'+companyName+'</option>';
				}
				$("#companName").html($("#companName").html()+html);
			}
		}
		
	});
}

/**
 * 删除企业
 * @param groupId
 */
function deleteCompany(groupId){
	//确认对话框
	art.dialog.confirm('确定删除该企业吗？', function() {
		$.ajax({
			url : basePath + "companywh/deleteCompany.action",
			type : "post",
			dataType : 'json',
			data : {
				groupId : groupId
			},
			success : function(data) {
				if (data == 1) {
					//artDialog.alert("删除成功！",function(){						
						//重新加载列表
						getTableList();
					//});
				} else if (data == 0){
					artDialog.alert("删除失败！");
				}
			}
			
		});
	}, function() {
		return;
	});
	
}
/**
 * 新增操作
 */
function addCompany() {
	window.location.href = basePath + "companywh/toUpdateCompany.action?action=add&parentId="+$('#parentId').val();
}
function getTableList(){
	var companName = $.trim($('#companName').val());
	var parentId = $('#parentId').val();
	$('#myTable').dataTable({
		"bDestroy" : true,
		"bProcessing" : true,
		"bStateSave" : true, // 状态保存
		'bServerSide' : true,
		'fnServerParams' : function(aoData) {
			aoData.push({
					"name" : "groupId",
					"value" : companName
				}, {
					"name" : "parentId",
					"value" : parentId
				});
		},
		"sAjaxSource" : basePath + "companywh/getWHCompanyList.action",
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
					var groupId = oObj.aData.groupId;
					var parentId = $('#parentId').val();
					var html = '<a href="'+basePath+'companywh/toCompanyView.action?groupId='+groupId+'">查看</a>';
					html += '<a href="'+basePath+'companywh/toUpdateCompany.action?action=edit&groupId='+groupId+'&parentId='+parentId+'">修改</a>';
					html += '<a href="javascript:void(0);" onclick="deleteCompany('+groupId+');">删除</a>';
					return html;
				}
			}
		]
	});
}