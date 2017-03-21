$(document).ready(function(){
	getDataTable();//获取列表
	//公司名称
	setCompany();
	//增加按钮单击事件
	$("#add").bind("click", function(){
		window.location.href = basePath + "wh/logined/training/addPerserviceTraining.jsp";
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
	if(whroletype==1 || whroletype==3){//政府
		this.qytx.app.grid({
			id:"myTable",
			url:basePath + "training/findPreserviceList.action",
			selectParam:{"vo.sex":$("#sex").val(),"vo.userName":$.trim( $("#userName").val() ),"vo.checkerName":$.trim( $("#checker").val() ),"vo.groupId":$("#company").val()},
			valuesFn:[{
	  				"aTargets" : [9], //操作
	  				"fnRender" : function(oObj) {
	  					var id = oObj.aData.id;
	  					var html = '<a href="'+basePath+'training/loadPreserviceDetail.action?opt=detail&trainId='+id+'">查看</a>';
	  					return html;
	  				}
	  			}]	
		});
	}else{//企业
		this.qytx.app.grid({
			id:"myTable",
			url:basePath + "training/findPreserviceList.action",
			selectParam:{"vo.sex":$("#sex").val(),"vo.userName":$.trim( $("#userName").val() ),"vo.checkerName":$.trim( $("#checker").val() )},
			valuesFn:[{
	  				"aTargets" : [8], //操作
	  				"fnRender" : function(oObj) {
	  					var id = oObj.aData.id;
	  					var html = '<a href="'+basePath+'training/loadPreserviceDetail.action?opt=detail&trainId='+id+'">查看</a>';
	  					html += '<a href="'+basePath+'training/loadPreserviceDetail.action?opt=update&trainId='+id+'">修改</a>';
	  					html += '<a href="javascript:deletePreservice(' + id + ');">删除</a>';
	  					return html;
	  				}
	  			}]	
		});
	}
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
 * 删除岗前培训记录
 * */
function deletePreservice(id){
	artDialog.confirm("你确定要删除吗？", function(){
		$.ajax({
			url : basePath + "training/deletePreservice.action",
			type : "post",
			data : {
				'trainId' : id
			}, 
			dataType : "text",
			success : function(data) {
				if(data == 1){
					//artDialog.alert("删除成功！", function(){
						getDataTable();
					//});
				}else {
					artDialog.alert("删除失败！", function(){
						getDataTable();
					});
				}
			}
		});
	});
}

