$(document).ready(function(){
	initSelect("productId","productId");
	
	//材料种类
	$("#materialType").change(function(){
		var materialType = $("#materialType").val();
		if(materialType==1){//原材料
			$("#cp").hide();
			$("#outputYear").removeAttr("valid");
			$("#outputYear").removeAttr("errmsg");
			$("#outputMouth").removeAttr("valid");
			$("#outputMouth").removeAttr("errmsg");
			
			$("#ycl").show();
			$("#useYear").attr("valid","required");
			$("#useYear").attr("errmsg","wuhaiProduct.useYear_not_null");
			$("#useMouth").attr("valid","required");
			$("#useMouth").attr("errmsg","wuhaiProduct.useMouth_not_null");
		}else if(materialType==2){//产品
			$("#cp").show();
			$("#outputYear").attr("valid","required");
			$("#outputYear").attr("errmsg","wuhaiProduct.outputYear_not_null");
			$("#outputMouth").attr("valid","required");
			$("#outputMouth").attr("errmsg","wuhaiProduct.outputMouth_not_null");
			
			$("#ycl").hide();
			$("#useYear").removeAttr("valid");
			$("#useYear").removeAttr("errmsg");
			$("#useMouth").removeAttr("valid");
			$("#useMouth").removeAttr("errmsg");
		}
	});
	$("#materialType").change();
	
	//保存企业信息
	$("#submit").click(function(){
		// 框架校验
		if (!validator(document.getElementById("form1"))) {
			return;
		}
//		art.dialog.confirm('确定要保存吗？', function() {
			submit();
			
//		}, function() {
//			return;
//		});
	});
});


/**
 * 初始化数据字段下拉框
 * @return
 */
function initSelect(typeName,id) {
	var paramData = {
		'infoType' : typeName,
		'sysTag' : 1
	};
	qytx.app.ajax({
		url : basePath + "dict/getDicts.action",
		type : "post",
		dataType : "text",
		data : paramData,
		success : function(data) {
			//data是一个数组
			var jsonData = eval('(' + data + ')');
			$("#"+id).empty();
			for (var i = 0; i < jsonData.length; i++) {
				$("#"+id).append("<option value='"
						+ jsonData[i].value + "'>" + jsonData[i].name
						+ "</option>");
			}
		}
	});
}

/**
 * 初始化物质名称下拉选
 *//*
function initDangerSelect(){
		qytx.app.ajax({
			url : basePath + "companywh/findWHPAll.action",
			type : "post",
			dataType : "text",
			data : {},
			success : function(data) {
				//data是一个数组
				var jsonData = eval('(' + data + ')');
				$("#productId").empty();
				for (var i = 0; i < jsonData.length; i++) {
					$("#productId").append("<option value='"
							+ jsonData[i].vid + "'>" + jsonData[i].materialName
							+ "</option>");
				}
			}
		});
}
*/

/**
 * 保存添加
 */
function submit(){
	
	var materialType = $.trim($("#materialType").val());//材料种类id
	var materialTypeName = $("#materialType").find("option:selected").text();
	var productId = $.trim($("#productId").val());//物质名称id
	var materialName = $("#productId").find("option:selected").text();
	
	var outputYear = "";
	var outputMouth = "";
	var useYear = "";
	var useMouth = "";
	if(materialType==1){
		useYear = $.trim($("#useYear").val());
		useMouth = $.trim($("#useMouth").val());
	}else if(materialType==2){
		outputYear = $.trim($("#outputYear").val());
		outputMouth = $.trim($("#outputMouth").val());
	}
	
	var memo = $.trim($("#memo").val());

	$(".formButton_green").attr("disabled", "disabled");
	//保存
	$.ajax({
		url : basePath + "companywh/saveProduct.action",
		type : "post",
		dataType : 'json',
		data : {
			"product.materialType" : materialType,
			"product.materialTypeName" : materialTypeName,
			"product.productId" : productId,
			"product.materialName" : materialName,
			"product.outputYear" : outputYear,
			"product.outputMouth" : outputMouth,
			"product.useYear" : useYear,
			"product.useMouth" : useMouth,
			"product.memo" : memo
		},
		success : function(data) {
			if (data == 1) {
				//artDialog.alert("新增成功！",function(){					
					//重新加载列表
					window.location.href = basePath+"wh/logined/companyProduct/companyProductList.jsp"; 
				//});
			} else if (data == 0){
				artDialog.alert("新增失败！");
				$(".formButton_green").removeAttr("disabled");
			}
		}
		
	});
}

	
