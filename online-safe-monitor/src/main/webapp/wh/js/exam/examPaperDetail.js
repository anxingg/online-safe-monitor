/**
 * 试卷详情
 * @author wuzhou
 */
$(document).ready(function(){
	getDataTable();
	$("#goBack").click(function(){
		window.location.href=basePath+"wh/logined/exam/examPaperList.jsp";
	})
});
/**
 * 分页查询
 */
function getDataTable(){
	_checkedIds="";
	this.qytx.app.grid({
		id:"myTable",
		url:basePath + "exam/findPaperQuestion.action",
		selectParam:{"paperId":$("#paperId").val()}
	});

}
