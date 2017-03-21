$(document).ready(function(){
	getDataTable();//获取列表
	//公司名称
	setCompany();
	//增加按钮单击事件
	$("#add").bind("click", function(){
		window.location.href = basePath + "wh/logined/process/addProcess.jsp";
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
	var groupId = 0;
	if(whroletype==2){//企业
		groupId = $("#groupId").val();
	}else{
		groupId = $("#company").val();
	}
	this.qytx.app.grid({
		id:"myTable",
		url:basePath + "technologicalProcess/findProcessList.action",
		selectParam:{"vo.title":$.trim( $("#title").val() ),"vo.groupId":groupId},
		valuesFn:[{
  				"aTargets" : [4], //操作
  				"fnRender" : function(oObj) {
  					var id = oObj.aData.id;
  					var html = '';
  					if(whroletype==2){
  						html += '<a href="'+basePath+'wh/logined/process/processView.jsp?infoId='+id+'">查看</a><a href="'+basePath+'wh/logined/process/processUpdate.jsp?infoId='+id+'">修改</a><a href="javascript:void(0)" onclick="deleteProcess('+id+');">删除</a>';
  					}else{
  						html += '<a href="'+basePath+'wh/logined/process/processView.jsp?infoId='+id+'">查看</a>';
  					}
  					return html;
  				}
  			}]	
	});
}
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
 * 删除工艺流程
 * */
function deleteProcess(id){
	artDialog.confirm("你确定要删除吗？", function(){
		$.ajax({
			url : basePath + "technologicalProcess/deleteProcess.action",
			type : "post",
			data : {
				'infoId' : id
			}, 
			dataType : "text",
			success : function(data) {
				if(data == 1){
					getDataTable();
				}else {
					artDialog.alert("删除失败！", function(){
						getDataTable();
					});
				}
			}
		});
	});
}

