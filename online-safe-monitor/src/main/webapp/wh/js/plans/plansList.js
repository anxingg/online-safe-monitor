$(document).ready(function(){
	var whroletype = $("#whroletype").val();
	if(whroletype==1 || whroletype==3){//政府端
		$("#xz").hide();//新增按钮
		//加载企业下拉列表
		getSelectCompany();
	}else if(whroletype==2){//企业端
		$("#qymc").hide();
	}
	// 保存按钮绑定事件
	$("#search").bind("click", function() {
		getTableList();
		return false;
	});
	
	//回车事件
	$(document).keydown(function(event){
		var code=event.which;
		if (code == 13) {
			getTableList();
			return false;
		}
	});
	//初始化预案类型下拉选
	initSelect("planType","planType");
	//加载列表
	getTableList();
	
});

function getSelectCompany(){
	$.ajax({
		url : basePath + "companywh/getCompanyNameList.action",
		type : "post",
		dataType : 'json',
		data : {
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
 * 初始化数据字段下拉框
 * @return
 */
function initSelect(typeName,id) {
	var paramData = {
		'infoType' : typeName,
		'sysTag' : 1
	};
	qytx.app.ajax({
		url : basePath + "dict/getDicts.action",
		type : "post",
		dataType : "text",
		data : paramData,
		success : function(data) {
			//data是一个数组
			var jsonData = eval('(' + data + ')');
			$("#"+id).empty();
			$("#"+id).append("<option value='-1'>全部</option>");
			for (var i = 0; i < jsonData.length; i++) {
				$("#"+id).append("<option value='"
						+ jsonData[i].value + "'>" + jsonData[i].name
						+ "</option>");
			}
		}
	});
}


function getTableList(){
	var companName = $.trim($('#companName').val());
	var planType = $.trim($("#planType").val());
	var prepareTime = $.trim($("#prepareTime").val());
	var prepareEndTime = $.trim($("#prepareEndTime").val());
	
	$('#myTable').dataTable({
		"bDestroy" : true,
		"bProcessing" : true,
		"bStateSave" : true, // 状态保存
		'bServerSide' : true,
		'fnServerParams' : function(aoData) {

			aoData.push(
				{
					"name" : "groupId",
					"value" : companName
				},{
					"name" : "planType",
					"value" : planType
				},{
					"name" : "prepareTime",
					"value" : prepareTime
				},{
					"name" : "prepareEndTime",
					"value" : prepareEndTime
				});
		},
		"sAjaxSource" : basePath + "plans/plans_getList.action",
		"sPaginationType" : "full_numbers",
		"bPaginate" : true, // 翻页功能
		"bLengthChange" : false, // 改变每页显示数据数量
		"bFilter" : false, // 过滤功能
		"bSort" : false, // 排序功能
		"bInfo" : true,// 页脚信息
		"bAutoWidth" : false,// 自动宽度
		"iDisplayLength" : 15, // 每页显示多少行
		"aoColumns" : [{
			"sTitle" : '预案编号',
			"mDataProp" : 'planNo',
			"sClass" : "longTxt"
		}, {
			"sTitle" : '企业名称',
			"mDataProp" : "companyName",
			"sClass" : "longTxt"
		}, {
			"sTitle" : '预案类型',
			"mDataProp" : 'planTypeName'
		}, {
			"sTitle" : '备案时间',
			"mDataProp" : 'prepareTime'
		}, {
			"sTitle" : '备案到期时间',
			"mDataProp" : 'prepareEndTime'
		}, {
			"sTitle" : '应急内容（文件）',
			"sClass" : "longTxt",
			"mDataProp" : null
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
					var attachmentIds = oObj.aData.attachmentIds;
					var attachName = oObj.aData.attachName;
					var html = '';
					if(attachmentIds == null || attachmentIds == undefined || attachmentIds == '' || attachmentIds == '-'){
						html = '无附件';
					}else {
						html = '<a href="javascript:;" onclick="downloadReport(\''+attachmentIds+'\');">'+attachName+'</a>';
					}
					return html;
					
				}
			},
			{
				"aTargets" : [6], //操作
				"fnRender" : function(oObj) {
					var vid = oObj.aData.vid;
					var whroletype = $("#whroletype").val();
					var html = '<a href="'+basePath+'wh/logined/plans/plansView.jsp?vid='+vid+'">查看</a>';
					if(whroletype==2){//企业端
						html+= '<a href="'+basePath+'wh/logined/plans/updatePlans.jsp?vid='+vid+'">修改</a>';
						html+='<a href="javascript:void(0);" onclick="deletePlans('+vid+');">删除</a>';						
					}
					return html;
				}
			}
		]
	});
}

/**
 * 下载附件
 * @param id
 */
function downloadReport(id){
	var attachmentId = new String(id);
	attachmentId = attachmentId.replace(new RegExp(",","g"),'');
	//alert(attachmentId);
	downLoadAttachment(attachmentId);
}

/**
 * 新增操作
 */
function addPlans() {
	window.location.href = basePath + "wh/logined/plans/addPlans.jsp";
}

function deletePlans(vid){
	//确认对话框
	art.dialog.confirm('确定删除该应急预案吗？', function() {
		$.ajax({
			url : basePath + "plans/plans_delete.action",
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
					artDialog.alert("删除失败！");
				}
			}
			
		});
	}, function() {
		return;
	});
	
}

