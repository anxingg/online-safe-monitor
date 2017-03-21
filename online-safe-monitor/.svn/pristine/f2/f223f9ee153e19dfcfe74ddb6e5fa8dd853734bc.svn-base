$(document).ready(function(){
		setExamType();
		// 保存按钮绑定事件
		$("#search").bind("click", function() {
			getDataTable();
			return false;
		});
		// 点击新增按钮事件
		$("#addPaper").bind("click", function() {
			window.location.href = basePath + "wh/logined/exam/addExamPaper.jsp";
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
					getDataTable();
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
		url:basePath + "exam/findPaperList.action?state=0",
		selectParam:{"vo.title":$.trim( $("#papertitle").val() ),"vo.examType":$("#examType").val()},
		valuesFn:[{
					"aTargets" : [4],
					"fnRender" : function(oObj) {
						var state = oObj.aData.state;
						var id = oObj.aData.id;
						return '<a style="cursor:pointer" title="考试"  href="'+ basePath + 'exam/joinExam.action?examTestId='+ id+ '">考试</a>';
					}
				}]	
	});

}


/**
 * 删除
 * */
function del(paperId){
	//确认对话框
	art.dialog.confirm('确定删除该试卷吗？', function() {
		$.ajax({
			url : basePath + "exam/deletePaper.action",
			type : "post",
			dataType : 'json',
			data : {
				"paperId" : paperId
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

/**
 * 更改试卷状态
 * */
function updateState(paperId,state){
	var content = "";
	if(state==0){
		content = "确定使该试卷生效吗？";
	}else{
		content = "确定使该试卷失效吗？";
	}
	//确认对话框
	art.dialog.confirm(content, function() {
		$.ajax({
			url : basePath + "exam/updateState.action",
			type : "post",
			dataType : 'json',
			data : {
				"paperId" : paperId,
				"state" : state
			},
			success : function(data) {
				if (data == 1) {
						//重新加载列表
						getDataTable();
				} else if (data == 0){
					artDialog.alert("操作失败！");
				}
			}
			
		});
	}, function() {
		return;
	});
	
}

