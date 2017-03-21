
$(document).ready(function() {
	
	//确定按钮绑定事件
	$(".formButton_green").bind("click", function() {
		// 框架校验
		if (validator(document.getElementById("form1"))) {
			addOne();
		}
		return false;
	});
	
	//确定按钮绑定事件
	$("#extractMoney").keyup(function(){
		var safetySurplusMoney = $("#safetySurplusMoney").val();
		if(safetySurplusMoney == null  || safetySurplusMoney == undefined || safetySurplusMoney == ''){
			safetySurplusMoney = 0;
		}
		var extractMoney = $("#extractMoney").val();
		if(extractMoney == null  || extractMoney == undefined){
			extractMoney = 0;
		}
		if(!parseFloat(extractMoney)){
			//alert(0);
			if(parseInt(extractMoney, 10) != 0){
				return;
			}
			//alert(1);
		}
		$("#remainingSum").html(parseFloat(safetySurplusMoney) + parseFloat(extractMoney));
	});
	
});


/**
 * 新增
 */
function addOne() {
	$(".formButton_green").attr("disabled", "disabled");
	
	//部门ID
	var groupId = $("#group_id").val();
	
	//提取标准
	var extractStand = $.trim( $("#extractStand").html() );
	
	//上年度营业额
	var turnover = $.trim( $("#turnover").val() );
	
	//提取时间
	var extractTime = $.trim( $("#extractTime").val() );
//	if(occurredTimeStr != ''){
//		occurredTimeStr += ' 00:00:00.0';
//	}
	
	//本次提取
	var extractMoney = $("#extractMoney").val();
	
	//结存金额
	var remainingSum = $.trim( $("#remainingSum").html() );
	
	$.ajax({
		url : basePath + "fee/fee_Extract_saveOrUpdate.action",
		type : "post",
		dataType : 'json',
		data : {
			'feeExtract.id' : -1,
			'feeExtract.groupId' : groupId,
			'feeExtract.extractStand' : extractStand,
			'feeExtract.turnover' : turnover,
			'feeExtract.extractTime' : extractTime,
			'feeExtract.extractMoney' : extractMoney,
			'feeExtract.remainingSum' : remainingSum
		},
		success : function(data) {
			if (data == 1) {
				window.location.href = basePath + "wh/logined/fee/feeExtractList.jsp";
			}
		}
		
	});
	
}
