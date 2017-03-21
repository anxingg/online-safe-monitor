$(document).ready(function(){
	//加载企业下拉列表
	getSelectCompany();
	
	// 查询按钮绑定事件
	$("#searchCompany").bind("click", function() {
		// 清除cookie中的分页信息
		$.removeTableCookie('SpryMedia_DataTables_myTable_companyFeeList.jsp');
		getDataTable();
		return false;
	});
	
	//回车事件
	$(document).keydown(function(event){
		var code=event.which;
		if (code == 13) {
			// 清除cookie中的分页信息
			$.removeTableCookie('SpryMedia_DataTables_myTable_companyFeeList.jsp');
			getDataTable();
			return false;
		}
	});
	//加载列表
	getDataTable();
	
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
 * 分页查询
 */
function getDataTable(){
	_checkedIds="";
	var groupId = $.trim($('#companName').val());
	this.qytx.app.grid({
		id:"myTable",
		url:basePath + "companywh/getWHCompanyList.action",
		selectParam:{"groupId":groupId},
		valuesFn:[{
  				"aTargets" : [5], //操作
  				"fnRender" : function(oObj) {
  					var groupId = oObj.aData.groupId;
  					var html ='<a href="'+basePath+'wh/logined/fee/companyYearStatistic.jsp?groupId='+groupId+'">查看年度统计</a>';
  					return html;
  				}
  			}]	
	});
}