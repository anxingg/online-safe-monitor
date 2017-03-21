$(document).ready(function(){
	getDataTable();//获取列表
	//增加按钮单击事件
	$("#add").bind("click", function(){
		window.location.href = basePath + "wh/logined/emergencyDepartment/addEmergency.jsp";
	});
	//增加按钮单击事件
	$("#back").bind("click", function(){
		window.location.href = basePath + "wh/logined/emergencyDepartment/emergencyCompanyList.jsp";
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
 * 分页查询
 */
function getDataTable(){
	_checkedIds="";
	var whroletype = $("#whroletype").val();
	var groupId ;
	if($("#fromGroupId").val()){
		groupId = $("#fromGroupId").val();
	}else{
		groupId = $("#groupId").val();
	}
	this.qytx.app.grid({
		id:"myTable",
		url:basePath + "emergencyDepartment/findEmergencyDepList.action",
		selectParam:{"vo.groupId":groupId,"vo.title":$.trim($("#title").val())},
		valuesFn:[{
  				"aTargets" : [6], //操作
  				"fnRender" : function(oObj) {
  					var id = oObj.aData.id;
  					var type = oObj.aData.type;
  					var groupId = oObj.aData.groupId;
  					var html = "";
  					if(whroletype==1||whroletype==3){//政府端用户
  						html += '--';
  					}else{//政府端普通用户
  						if(type==3){
  							html += '--';
  						}else{
  							html += '<a href="'+basePath+'emergencyDepartment/loadEmergencyDepartmentDetail.action?emergencyId='+id+'">修改</a><a href="javascript:void(0)" onclick="deleteEmergency('+id+')">删除</a>';
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

