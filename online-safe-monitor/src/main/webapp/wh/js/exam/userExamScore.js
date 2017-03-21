$(document).ready(function(){
	//列表数据
	getDataTable();	
	//考试类型
	setExamType();
	//公司名称
	setCompany();
	// 保存按钮绑定事件
	$("#search").bind("click", function() {
		getDataTable();
		return false;
	});
	// 点击新增按钮事件
	$("#exportScore").bind("click", function() {
		window.location.href = basePath + "exam/exportUserScore.action?vo.title="+$.trim($("#title").val())+"&vo.examType="+$("#examType").val()+"&vo.groupId="+$("#company").val()+"&vo.examTime="+$("#examTime").val();
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
 * 初始化考试类型数据
 * 
 * @return
 */
function setExamType() {
	var paramData = {
		'infoType' : "examType",
		'sysTag' : 1
	};
	qytx.app.ajax({
				url : basePath + "dict/getDicts.action",
				type : "post",
				dataType : "html",
				data : paramData,
				success : function(data) {
				var	jsonData = eval("(" + data + ")");
					$("#examType").empty();
					$("#examType").append("<option value='' seleted>请选择</option>");
					for (var i = 0; i < jsonData.length; i++) {
						$("#examType").append("<option value='"
								+ jsonData[i].value + "'>" + jsonData[i].name
								+ "</option>");
					}
				}
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
 * 分页查询
 */
function getDataTable(){
	_checkedIds="";
	this.qytx.app.grid({
		id:"myTable",
		url:basePath + "exam/findUserExamScore.action",
		selectParam:{"vo.title":$.trim( $("#title").val() ),"vo.examType":$("#examType").val(),"vo.groupId":$("#company").val(),"vo.examTime":$("#examTime").val()},	
	    valuesFn:[{
		"aTargets" : [2],
		"fnRender" : function(oObj) {
			var id = oObj.aData.id;
			return '<a style="cursor:pointer" title="查看考卷"  href="'+ basePath + 'exam/examTestView.action?userTestId='+ id+ '">查看考卷</a>';
		}
	}]	
	
	});

}