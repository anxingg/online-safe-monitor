$(document).ready(function(){
	getDataTable();//获取列表
	//公司名称
	setCompany();
	
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
		url:basePath + "reliefGoods/findGoodsList.action",
		selectParam:{"vo.title":$.trim($("#title").val()),"vo.groupId":$("#company").val()}	
	});
}

