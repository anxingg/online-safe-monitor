$(document).ready(function(){
	// 查询按钮绑定事件
	$("#search").bind("click", function() {
		// 清除cookie中的分页信息
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
	//加载列表
	getDataTable();
	
});

function getDataTable(){
	$.ajax({
		url : basePath + "fee/findComapnyYearStatistic.action",
		type : "post",
		dataType : 'html',
		data : {
			'vo.year' : $("#year").val(),
			'vo.groupId' : $("#groupId").val()
		},
		success : function(data) {
			if(data){
				$("#statisticTable").html("");
				$("#statisticTable").html(data);
			}
		}
		
	});
}