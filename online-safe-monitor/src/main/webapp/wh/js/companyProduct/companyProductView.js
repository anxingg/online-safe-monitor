$(document).ready(function(){
	
	//获取数据
	getProductInfo();
});


function getProductInfo(){
	var id = $("#id").val();
	//异步获取产品信息
	$.ajax({
		url : basePath + "companywh/getProductInfo.action",
		type : "post",
		dataType : 'json',
		data : {
			id : id
		},
		success : function(data) {
			if (data != null) {
				var product = eval(data);
				
				$("#materialTypeName").html(product.materialTypeName);
				$("#materialName").html(product.materialName);
				
				$("#outputYear").html(product.outputYear);
				$("#outputMouth").html(product.outputMouth);
				$("#useYear").html(product.useYear);
				$("#useMouth").html(product.useMouth);
				if(product.materialType==1){
					$("#cp").hide();
				}else{
					$("#ycl").hide();
				}
				
				$("#memo").html(product.memo);
			} else {
				artDialog.alert("加载产品信息失败！");
			}
		}
		
	});
}
	
