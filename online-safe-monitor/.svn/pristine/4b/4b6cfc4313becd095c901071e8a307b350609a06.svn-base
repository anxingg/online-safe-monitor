

$(document).ready(function() {

	//确定按钮绑定事件
	$(".formButton_green").bind("click", function() {
		// 框架校验
		if (validator(document.getElementById("form1"))) {
			addOne();
		}
		return false;
	});
	
});


/**
 * 新增或修改
 */
function addOne() {
	$(".formButton_green").attr("disabled", "disabled");
	
	//主键
	var vid = $("#vid").val();
	if(!vid){
		vid = -1;
	}
	
	//编号
	var code = $.trim( $("#code").val() );
	
	//品名
	var materialName = $.trim( $("#materialName").val() );
	
	//别名
	var molecularFormula = $.trim( $("#molecularFormula").val() );
	
	//CAS号
	var cas = $.trim( $("#cas").val() );
	
	//备注
	var other = $.trim( $("#other").val() );
	
	$.ajax({
		//url : basePath + "dangerchemicals/dangerchemicals_Add_saveOrUpdate.action",
		url : basePath + "dangerchemicals/chemicalsDirectory_Add_saveOrUpdate.action",
		type : "post",
		dataType : 'json',
		data : {
			'chemicalsDirectory.vid' : vid,
			'chemicalsDirectory.code' : code,
			'chemicalsDirectory.materialName' : materialName,
			'chemicalsDirectory.molecularFormula' : molecularFormula,
			'chemicalsDirectory.cas' :cas,
			'chemicalsDirectory.other' : other
		},
		success : function(data) {
			if (data == 1) {
				/*
				if(vid == -1){
					artDialog.alert("新增成功！", function(){
						//window.location.href = basePath + "wh/logined/chemicalsDirectory/chemicalsDirectoryList.jsp";
					});
				}else {
					artDialog.alert("修改成功！", function(){
						//window.location.href = basePath + "wh/logined/chemicalsDirectory/chemicalsDirectoryList.jsp";
					});
				}
				*/
				window.location.href = basePath + "wh/logined/dangerChemicals/chemicalsDirectoryList.jsp";
			} else {
				artDialog.alert(tipStatement(vid, data));
				$(".formButton_green").removeAttr("disabled");
			}
		}
		
	});
	
}

/**
 * 新增或修改操作后的提示语
 * @param id
 * @param result
 * @returns
 */
function tipStatement(id, result){
	var oper = id == -1? '新增':'修改';
	var tip = result == 1?'成功':(result == -1?'时，品名已经使用':(result == -2?'时，别名已经使用':'失败'));
	return oper + tip;
}


/**
 * 取消操作
 * @returns {Boolean}
 */
function goback(){
	var url = document.referrer;
	window.location.href = url;
	return false;
}