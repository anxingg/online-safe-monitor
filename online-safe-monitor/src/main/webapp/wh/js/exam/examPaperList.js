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
		url:basePath + "exam/findPaperList.action",
		selectParam:{"vo.title":$.trim( $("#papertitle").val() ),"vo.examType":$("#examType").val()},
		valuesFn:[{
					"aTargets" : [4],
					"fnRender" : function(oObj) {
						var state = oObj.aData.state;
						if(state==0){
							return "生效";
						}else{
							return "失效";
						}
					}          
				},{
					"aTargets" : [5],
					"fnRender" : function(oObj) {
						var state = oObj.aData.state;
						var id = oObj.aData.id;
						if(state==0){//生效
							return '<a style="cursor:pointer" title="查看"  href="'+ basePath + 'exam/loadPaperDetail.action?paperId='+ id+ '">查看</a>'+'<a style="cursor:pointer" title="失效" onclick="updateState('+id+',1)"><font color="red">失效</font></a>'+'<a style="cursor:pointer" title="删除" onclick="del('+id+')">删除</a>';
						}else{
							return '<a style="cursor:pointer" title="查看"  href="'+ basePath + 'exam/loadPaperDetail.action?paperId='+ id+ '">查看</a>'+'<a style="cursor:pointer" title="生效" onclick="updateState('+id+',0)">生效</a>'+'<a style="cursor:pointer" title="删除" onclick="del('+id+')">删除</a>';
						}
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

