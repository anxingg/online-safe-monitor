$(document).ready(function(){
	getDataTable();//获取列表
	//公司名称
	setCompany();
	//增加按钮单击事件
	$("#add").bind("click", function(){
		window.location.href = basePath + "wh/logined/emergencyDepartment/addEmergency.jsp";
	});
	
	//保存按钮绑定事件
	$(".searchButton").bind("click", function() {
		getDataTable();
		return false;
	});
	
	//回车事件
	$(document).keydown(function(event){
		var code=event.which;
		if (code == 13) {
			getDataTable();
			return false;
		}
	});
});

/**
 * 初始化公司数据
 * 
 * @return
 */
function setCompany() {
	qytx.app.ajax({
				url : basePath + "companywh/getCompanyNameList.action",
				type : "post",
				dataType : "html",
				success : function(data) {
				var	jsonData = eval("(" + data + ")");
					$("#company").empty();
					$("#company").append("<option value='' seleted>请选择</option>");
					for (var i = 0; i < jsonData.length; i++) {
						$("#company").append("<option value='"
								+ jsonData[i].groupId + "'>" + jsonData[i].companyName
								+ "</option>");
					}
				}
			});
}
/**
 * 分页查询
 */
function getDataTable(){
	_checkedIds="";
	var whroletype = $("#whroletype").val();
	this.qytx.app.grid({
		id:"myTable",
		url:basePath + "emergencyDepartment/findEmergencyDepList.action",
		selectParam:{"vo.isShow":1,"vo.groupId":$("#company").val()},
		valuesFn:[{
  				"aTargets" : [6], //操作
  				"fnRender" : function(oObj) {
  					var id = oObj.aData.id;
  					var type = oObj.aData.type;
  					var groupId = oObj.aData.groupId;
  					var html = "";
  					if(whroletype==1){//政府端管理用户
  						if(type==3){
  							html += '<a href="'+basePath+'emergencyDepartment/loadEmergencyDepartmentDetail.action?emergencyId='+id+'">修改</a><a href="javascript:void(0)" onclick="deleteEmergency('+id+')">删除</a>';
  						}else{
  							html += '<a href="'+basePath+'wh/logined/emergencyDepartment/emergencyList.jsp?groupId='+groupId+'">查看</a>';
  						}
  					}else{//政府端普通用户
  						if(type==3){
  							html += '--';
  						}else{
  							html += '<a href="'+basePath+'wh/logined/emergencyDepartment/emergencyList.jsp?groupId='+groupId+'">查看</a>';
  						}
  					}
  					
  					return html;
  				}
  			}]	
	});
}


/**
 * 删除
 * */
function deleteEmergency(id){
	//确认对话框
	art.dialog.confirm('确定删除该应急机构吗？', function() {
		$.ajax({
			url : basePath + "emergencyDepartment/deleteEmergencyDepartment.action",
			type : "post",
			dataType : 'json',
			data : {
				"emergencyId" : id
			},
			success : function(data) {
				if (data == 1) {
					//重新加载列表
					getDataTable();
				} else if (data == 0){
					artDialog.alert("删除失败！");
				}
			}
			
		});
	}, function() {
		return;
	});
	
}


