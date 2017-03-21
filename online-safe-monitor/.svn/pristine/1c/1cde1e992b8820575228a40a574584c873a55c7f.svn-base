$(document).ready(function(){
	initSelect("productId","productId");
	
	//材料种类
	$("#materialType").change(function(){
		var materialType = $("#materialType").val();
		if(materialType==1){
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
		}else if(materialType==2){
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
	
	
	//保存企业信息
	$("#submit").click(function(){
		// 框架校验
		if (!validator(document.getElementById("form1"))) {
			return;
		}
//		art.dialog.confirm('确定要保存修改吗？', function() {
			submit();
			
//		}, function() {
//			return;
//		});
	});
	
	
	//获取数据
	getProductInfo();
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
 */
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

/**
 * 修改
 */
function submit(){
	
	var id = $("#id").val();
	
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
		url : basePath + "companywh/updateProduct.action",
		type : "post",
		dataType : 'json',
		data : {
			"product.vid" : id,
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
				//artDialog.alert("修改成功！",function(){					
					//重新加载列表
					window.location.href = basePath+"wh/logined/companyProduct/companyProductList.jsp";
				//});
			} else if (data == 0){
				artDialog.alert("修改失败！");
				$(".formButton_green").removeAttr("disabled");
			}
		}
		
	});
}


function getProductInfo(){
	var id = $.trim($("#id").val());
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
				
				$("#materialType").val(product.materialType);
				$("#materialType").change();//调用change方法
				$("#productId").val(product.productId);
				
				$("#outputYear").val(product.outputYear);
				$("#outputMouth").val(product.outputMouth);
				$("#useYear").val(product.useYear);
				$("#useMouth").val(product.useMouth);
				
				$("#memo").val(product.memo);
			} else {
				artDialog.alert("加载产品信息失败！");
			}
		}
		
	});
}
	
