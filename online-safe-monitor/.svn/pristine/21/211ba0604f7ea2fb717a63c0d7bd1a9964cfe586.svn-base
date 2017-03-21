$(document).ready(function(){
	
	var whroletype = $("#whroletype").val();
	if(whroletype==1 || whroletype==3){//政府端登录
		
		$("#xz").hide();//新增按钮
		//加载企业下拉列表
		getSelectCompany();
	}else if(whroletype==2){//企业端登录
		
		getTableList();
		$("#xz").show();//新增按钮
		$("#qymc").hide();//隐藏企业下拉选
		
	}
	
	// 查询按钮绑定事件
	$("#search").bind("click", function() {
		// 清除cookie中的分页信息
		$.removeTableCookie('SpryMedia_DataTables_myTable_specialWorkPersonList.jsp');
		getTableList();
		return false;
	});
	
	//回车事件
	$(document).keydown(function(event){
		var code=event.which;
		if (code == 13) {
			// 清除cookie中的分页信息
			$.removeTableCookie('SpryMedia_DataTables_myTable_specialWorkPersonList.jsp');
			getTableList();
			return false;
		}
	});
	
});


/**
 * 加载企业下拉列表
 */
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
					if(groupId==$("#groupId").val()){
						html += '<option value="'+groupId+'" selected="selected">'+companyName+'</option>';
					}else{
						html += '<option value="'+groupId+'">'+companyName+'</option>';
					}
				}
				$("#companName").html($("#companName").html()+html);
			}
			getTableList();
		}
		
	});
}

/**
 * 查询特种作业人员列表
 */
function getTableList() {

	var groupId = $.trim($("#companName").val());
	
	var whroletype = $.trim($("#whroletype").val());
	
	if(whroletype==2){//企业端登录
		
		groupId =  $.trim($("#mygroupId").val());
	}
	
	var workType=$.trim($("#workType").val());//工种
	
	var group=$.trim($("#group").val());//部门
	
	var name=$.trim($("#name").val());//姓名
	
	var minTime=$.trim($("#minTime").val());
	
	var maxTime=$.trim($("#maxTime").val());
	
	$('#myTable').dataTable({
		"bDestroy" : true,
		"bProcessing" : true,
		"bStateSave" : true, // 状态保存
		'bServerSide' : true,
		'fnServerParams' : function(aoData) {

			aoData.push({
					"name" : "name",
					"value" : name
				}, {
					"name" : "groupId",
					"value" : groupId
				},{
					"name" : "groupName",
					"value" : group
				}, {
					"name" : "workType",
					"value" : workType
				}, {
					"name" : "minTime",
					"value" : minTime
				}, {
					"name" : "maxTime",
					"value" : maxTime
				}, {
					"name" : "personType",
					"value" : 3
				});
		},
		"sAjaxSource" : basePath + "companywh/listSpecialWorkPerson.action",
		"sPaginationType" : "full_numbers",
		"bPaginate" : true, // 翻页功能
		"bLengthChange" : false, // 改变每页显示数据数量
		"bFilter" : false, // 过滤功能
		"bSort" : false, // 排序功能
		"bInfo" : true,// 页脚信息
		"bAutoWidth" : false,// 自动宽度
		"iDisplayLength" : 15, // 每页显示多少行
		"aoColumns" : [{
			"sTitle" : '序号',
			"mDataProp" : 'no'
		}, {
			"sTitle" : '姓名',
			"mDataProp" : "name",
			"sClass" : "longTxt"
		}, {
			"sTitle" : '部门 ',
			"mDataProp" : 'groupName',
			"sClass" : "longTxt"
		}, {
			"sTitle" : '工种',
			"mDataProp" : 'workType',
			"sClass" : "longTxt"
		}, {
			"sTitle" : '证书编号',
			"mDataProp" : 'certificateNum',
			"sClass" : "longTxt"
		}, {
			"sTitle" : '发证机关',
			"mDataProp" : 'issuingAuthority',
			"sClass" : "longTxt"
		}, {
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
				"aTargets" : [6], //操作
				"fnRender" : function(oObj) {
					var personId = oObj.aData.personId;
					var html = '';
					var whroletype = $("#whroletype").val();
					if(whroletype==1 || whroletype==3){//政府端登录
						html = '<a href="'+basePath+'wh/logined/specialWorkPerson/specialWorkPersonView.jsp?personId='+personId+'">查看</a>';
					}else if(whroletype==2){//企业端登录						
						html = '<a href="'+basePath+'wh/logined/specialWorkPerson/specialWorkPersonView.jsp?personId='+personId+'">查看</a>'; 
						html +='<a href="'+basePath+'wh/logined/specialWorkPerson/updateSpecialWorkPerson.jsp?personId='+personId+'">修改</a>';
						html +='<a href="javascript:void(0);" onclick="deletePerson('+personId+');">删除</a>';
					}
					
					return html;
				}
			}
		]
	});
}

/**
 * 删除
 * */
function deletePerson(personId){
	//确认对话框
	art.dialog.confirm('确定删除该特种作业人员吗？', function() {
		$.ajax({
			url : basePath + "companywh/ajaxDeletePersonById.action",
			type : "post",
			dataType : 'json',
			data : {
				"personId" : personId,
				"personType" : 3//用来区分操作日志。3表示特种作业人员
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
 * 新增
 */
function addPerson(){
	
	window.location.href="../specialWorkPerson/addSpecialWorkPerson.jsp"; 
}

