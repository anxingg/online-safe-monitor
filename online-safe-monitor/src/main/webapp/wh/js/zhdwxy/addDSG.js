var addOneSubmit = null;

$(document).ready(function(){
	addOneSubmit = art.dialog.data("addOneSubmit");
});


/**
 * 保存
 */
function save(){
	$(".formButton_green").attr("disabled", "disabled");
	
	if(!verifyEmptyContent('dictId', '请选择危险性类别！', 0)){
		$(".formButton_green").removeAttr("disabled");
		return;
	}
	if (!validator(document.getElementById("form1"))) {
		$(".formButton_green").removeAttr("disabled");
		return;
	}
	
	//主键
	var vid = $("#vid").val();
	if(!vid){
		vid = -1;
	}
	
	//危化品名称
	var dangerGoodName = $.trim( $('#dangerGoodName').val() );
	
	//危险性类别
	var dictId = $('#dictId').val();
	
	//UN编号
	var unCode = $.trim( $('#unCode').val() );
	
	//生产用途
	var purpose = $.trim( $('#purpose').val() );
	
	//生产工艺
	var process = $.trim( $('#process').val() );
	
	//物理状态
	var physicalState = $.trim( $('#physicalState').val() );
	
	//操作温度
	var operationTemp = $.trim( $('#operationTemp').val() );
	
	//操作压力
	var operationPressure = $.trim( $('#operationPressure').val() );
	
	//存量
	var simpleStock = $.trim( $('#simpleStock').val() );
	
	//单元内危化品存量
	var unitStock = $.trim( $('#unitStock').val() );
	
	//临界量
	var criticalMass = $.trim( $('#criticalMass').val() );
	
	//调用父页面中的 addOneSubmit 方法，来完成保存。
	addOneSubmit(vid, dangerGoodName, dictId, unCode, purpose, 
			process, physicalState, operationTemp, operationPressure, 
			simpleStock, unitStock, criticalMass);
}