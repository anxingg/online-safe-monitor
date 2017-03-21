$(document).ready(function() {
	setDictType();
	//增加按钮单击事件
	$("#save").bind("click", function(){
		save();
	});
});


/**
 * 保存添加
 */
function save(){
	if(!validator(document.getElementById("form1"))){
		return;
	}
	var goodsId = $("#goodsId").val();
	var goodName = $("#goodName").val();
	//救援物资类型
	var goodType = $("#goodType").val();
	var goodUnit = $("#goodUnit").val();
	var goodNum = $("#goodNum").val();
	var equippedPlace = $("#equippedPlace").val();
	var keeper = $("#keeper").val();
    var phone = $("#phone").val();
    var url = basePath + "reliefGoods/saveOrUpdateGoods.action";
	$(".formButton_green").attr("disabled", "disabled");
	//保存
	$.ajax({
		url : url,
		type : "post",
		dataType : 'json',
		data : {
			"info.id" : goodsId,
			"info.goodName" : goodName,
			"info.goodType" : goodType,
			"info.phone" : phone,
			"info.goodUnit" : goodUnit,
			"info.goodNum" : goodNum,
			"info.equippedPlace" : equippedPlace,
			"info.keeper" : keeper,
			"info.isShow" : 0
		},
		success : function(data) {
			if (data == 1) {
				window.location.href = basePath+"wh/logined/reliefGoods/reliefGoodsList.jsp";
			} else if (data == 0){
				artDialog.alert("新增失败！");
				$(".formButton_green").removeAttr("disabled");
			}
		}
		
	});
}
function setDictType() {
	var paramData = {
		'infoType' : "goodsType",
		'sysTag' : 1
	};
	qytx.app.ajax({
				url : basePath + "dict/getDicts.action",
				type : "post",
				dataType : "html",
				data : paramData,
				success : function(data) {
					var	jsonData = eval("(" + data + ")");
					$("#goodType").empty();
					for (var i = 0; i < jsonData.length; i++) {
						$("#goodType").append("<option value='"
								+ jsonData[i].value + "'>" + jsonData[i].name
								+ "</option>");
					}
					if($("#oldGoodType").val()){
						 $("#goodType").find("option[value="+$("#oldGoodType").val()+"]").attr("selected",true);
					}
				}
			});
}

	
/**
 * 输入框只能输入数字
 * @param obj
 */
function testNum(obj){
	if(!/^(\d)*$/.test(obj.value)){//验证需要增加别的字符的时候/^(\d|;|,)*$/
		obj.value = obj.value.replace(/[^\d]/g,'');
	}
}