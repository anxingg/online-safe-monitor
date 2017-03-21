$(document).ready(function(){
	// 保存按钮绑定事件
	$("#beginExam").bind("click", function() {
		beginExam();
		return false;
	});
});

/**
 * 初始化考试类型数据
 * 
 * @return
 */
function beginExam() {
	var userName = $.trim($("#userName").val());
	var idcard = $.trim($("#idcard").val());
	if(userName==""){
		art.dialog.alert("请输入考试人员姓名！");
		return;
	}
	if(idcard==""){
		art.dialog.alert("请输入考试人员身份证号！");
		return;
	}
	if(!/(^\d{15}$)|(^\d{17}[0-9Xx]$)/.test(idcard)){
		art.dialog.alert("考试人员身份证号不正确！");
		return;
	}
	var paramData = {
		'examTestId' : $("#examTestId").val(),
		'userName' : userName,
		'idcard' : idcard
	};
	qytx.app.ajax({
				url : basePath + "exam/checkUser.action",
				type : "post",
				dataType : "json",
				data : paramData,
				success : function(data) {
					if(data==0){//没参加过考试
						window.location.href=basePath+"wh/logined/exam/examTestDetail.jsp?testId="+$("#examTestId").val()+"&userName="+userName+"&idcard="+idcard;
					}else if(data==1){//已参加过考试
						art.dialog.confirm("您已参加过此考试，确定要再次考试吗？",function(){
							window.location.href=basePath+"wh/logined/exam/examTestDetail.jsp?testId="+$("#examTestId").val()+"&userName="+userName+"&idcard="+idcard;
						});
					}else{
						art.dialog.alert("操作失败！");
					}
				}
			});
}


