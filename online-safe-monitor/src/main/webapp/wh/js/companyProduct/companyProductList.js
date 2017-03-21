$(document).ready(function(){
	
	//initSelect("materialType","materialType");
	
	var whroletype = $("#whroletype").val();
	
	if(whroletype==1 || whroletype==3){//政府端登录
		//加载企业下拉列表
		getSelectCompany();
		$("#xz").hide();//新增按钮
		
	}else if(whroletype==2){//企业端登录
		$("#qymc").hide();//隐藏企业下拉选
		getTableList();
	}
	
	// 保存按钮绑定事件
	$("#search").bind("click", function() {
		// 清除cookie中的分页信息
		$.removeTableCookie('SpryMedia_DataTables_myTable_companyProductList.jsp');
		getTableList();
		return false;
	});
	
	//回车事件
	$(document).keydown(function(event){
		var code=event.which;
		if (code == 13) {
			// 清除cookie中的分页信息
			$.removeTableCookie('SpryMedia_DataTables_myTable_companyProductList.jsp');
			getTableList();
			return false;
		}
	});
	
});


function getTableList(){
	
	var whroletype = $("#whroletype").val();
	var groupId = -1;
	if(whroletype==1){//政府端
		groupId = $.trim($("#companName").val());//部门ID
	}else if(whroletype==2){//企业端
		groupId = $.trim($("#mygroupId").val());//部门ID
	}
	
	var materialType=$.trim($("#materialType").val());//材料标识
	
	var materialName=$.trim($("#materialName").val());//物质名称
	
	$('#myTable').dataTable({
		"bDestroy" : true,
		"bProcessing" : true,
		"bStateSave" : true, // 状态保存
		'bServerSide' : true,
		'fnServerParams' : function(aoData) {

			aoData.push({
					"name" : "materialType",
					"value" : materialType
				}, {
					"name" : "materialName",
					"value" : materialName
				}, {
					"name" : "groupId",
					"value" : groupId
				});
		},
		"sAjaxSource" : basePath + "companywh/listWHCompanyProduct.action",
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
			"sTitle" : '企业名称',
			"mDataProp" : "companyName",
			"sClass" : "longTxt"
		}, {
			"sTitle" : '材料种类',
			"mDataProp" : 'materialTypeName'
		}, {
			"sTitle" : '物质名称',
			"mDataProp" : 'materialName'
		}, {
			"sTitle" : '年设计产量（吨）',
			"mDataProp" : 'outputYear',
			"sClass" : "data_r"
		}, {
			"sTitle" : '月设计产量（吨）',
			"mDataProp" : 'outputMouth',
			"sClass" : "data_r"
		}, {
			"sTitle" : '年设计消耗量（吨）',
			"mDataProp" : 'useYear',
			"sClass" : "data_r"
		}, {
			"sTitle" : '月设计消耗量（吨）',
			"mDataProp" : 'useMouth',
			"sClass" : "data_r"
		}, {
			"sTitle" : '备注',
			"mDataProp" : 'memo',
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
				"aTargets" : [9], //操作
				"fnRender" : function(oObj) {
					var id = oObj.aData.id;
					var html = "";
					if(whroletype==1 || whroletype==3){//政府端
						html = '<a href="'+basePath+'wh/logined/companyProduct/companyProductView.jsp?id='+id+'">查看</a>';
					}else if(whroletype==2){//企业端
						html += '<a href="'+basePath+'wh/logined/companyProduct/companyProductView.jsp?id='+id+'">查看</a>';
						html += '<a href="'+basePath+'wh/logined/companyProduct/updateCompanyProduct.jsp?id='+id+'">修改</a>';
						html += '<a href="javascript:void(0);" onclick="deleteProduct('+id+');">删除</a>';
					}
					
					return html;
				}
			}
		]
	});
}


function deleteProduct(id){
	//确认对话框
	art.dialog.confirm('确定删除该企业产品吗？', function() {
		$.ajax({
			url : basePath + "companywh/ajaxDeleteProductById.action",
			type : "post",
			dataType : 'json',
			data : {
				id : id
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
			$("#"+id).append('<option value="-1">请选择</option>');
			for (var i = 0; i < jsonData.length; i++) {
				$("#"+id).append("<option value='"
						+ jsonData[i].value + "'>" + jsonData[i].name
						+ "</option>");
			}
		}
	});
}


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

