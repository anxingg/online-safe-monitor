
$(document).ready(function() {
	
	//确定按钮绑定事件
	$(".formButton_green").bind("click", function() {
		if(!verifyEmptyContent('useDirection2', '请选择资金用向！', 0)){
			return;
		}
		// 框架校验
		if (validator(document.getElementById("form1"))) {
			addOne();
		}
		return false;
	});
	
	//确定按钮绑定事件
	$("#useMoney").keyup(function(){
		calculate();
	});
	
	$("#useDirection1").change(function(){
		initUseDirection2($(this).val());
	});
	
	$("#plus").click(function(){
		calculate();
	});
	
});

function calculate(){
	var safetySurplusMoney = $("#safetySurplusMoney").val();
	if(safetySurplusMoney == null  || safetySurplusMoney == undefined || safetySurplusMoney == ''){
		safetySurplusMoney = 0;
	}
	var useMoney = $("#useMoney").val();
	if(useMoney == null  || useMoney == undefined){
		useMoney = 0;
	}
	if(!parseFloat(useMoney)){
		//alert(0);
		if(parseInt(useMoney, 10) != 0){
			return;
		}
		//alert(1);
	}
	var plus = $("#plus").prop("checked");
	var result = 0;
	if(plus){
		result = parseFloat(safetySurplusMoney) + parseFloat(useMoney);
	}else{
		result = parseFloat(safetySurplusMoney) - parseFloat(useMoney);
	}
	result = new String(result);
	if(result.indexOf('.')!=-1){
		result = result.substring(0, result.indexOf('.')+3);
	}
	$("#remainingSum").html(result);
}

function initUseDirection2(parentId){
	$.ajax({
		url : basePath + "fee/fee_Used_getSubDictMap.action",
		type : "post",
		dataType : 'text',
		data : {
			'dict.infoType' : 'feeType',
			'dict.parentId' : parentId
		},
		success : function(data) {
			var jsonData = eval('(' + data + ')');
			$("#useDirection2").empty();
			$("#useDirection2").append('<option value="">请选择</option>');
			for (var i = 0; i < jsonData.length; i++) {
					$("#useDirection2").append('<option value="'+ jsonData[i].id + '">' + jsonData[i].name + '</option>');
			}
		}
		
	});
}


/**
 * 新增
 */
function addOne() {
	$(".formButton_green").attr("disabled", "disabled");
	
	//部门ID
	var groupId = $("#group_id").val();
	
	//使用时间
	var useTime = $.trim( $("#useTime").val() );
//	if(useTime != ''){
//		useTime += ' 00:00:00.0';
//	}
	
	//资金用向
	var useDirection = $.trim( $("#useDirection2").val() );
	
	//费用提取金额
	var useMoney = $("#useMoney").val();
	
	//结存金额
	var remainingSum = $.trim( $("#remainingSum").html() );
	
	//备注
	var memo = $.trim( $("#memo").val() );
	
	//补正
	var plus = $("#plus").prop("checked");
	if(plus){
		plus = 1;
	}else{
		plus = 0;
	}
	
	$.ajax({
		url : basePath + "fee/fee_Used_saveOrUpdate.action",
		type : "post",
		dataType : 'json',
		data : {
			'feeUsed.id' : -1,
			'feeUsed.groupId' : groupId,
			'feeUsed.useTime' : useTime,
			'feeUsed.useDirection' : useDirection,
			'feeUsed.useMoney' : useMoney,
			'feeUsed.remainingSum' : remainingSum,
			'feeUsed.memo' : memo,
			'feeUsed.plus' : plus
		},
		success : function(data) {
			if (data == 1) {
				window.location.href = basePath + "wh/logined/fee/feeUsedList.jsp";
			}
		}
		
	});
	
}
