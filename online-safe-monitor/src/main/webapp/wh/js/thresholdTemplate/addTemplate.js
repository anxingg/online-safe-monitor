$(document).ready(function(){
	if($('#action').val()=='add'){
		$('#title').html('<em>新增</em>');
	}
	else{
		$('#title').html('<em>修改</em>');
	}
	initSelect("watchType","watchType");
	$("#addmore").click(function(){
		var html='';
		for(var i=4;i<11;i++){
			html += '<tr id="level'+i+'">'+'<th>报警阈值'+i+'：</th>'+'<td colspan="3">'+
	        	'<select id="level'+i+'Type" style="width:15%">'+'</select>'+
	        	'<input type="text" class="formText" style="width:20%" id="leve'+i+'Low" maxlength="6" '+
	        	'errmsg="qy_lang.maxLength" />'+'<span>~</span> '+
	        	'<input type="text" class="formText" style="width:20%" id="level'+i+'High" maxlength="6" '+
	        	' errmsg="qy_lang.maxLength" />'+'<span>单位</span>'+
	        	 '<a href="javascript:void(0);" onclick="deletelevel('+i+')">删除</a>'+
	        	'</td>'+'</tr>';
		}
		$("#addmore").before(html);
		initLevelTypeList(4,10);
		$("#addmore").css('display','none');
	});
	initLevelTypeList(1,3);
	//保存新增
	$("#submit").click(function(){
		// 框架校验
		if (!validator(document.getElementById("form1"))) {
			return;
		}
		submit();
//		art.dialog.confirm('确定要保存修改吗？', function() {
//			submit();
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
		'grade' : 0
	};
	qytx.app.ajax({
		url : basePath + "dict/setup_getDictsByParentId.action",
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
 * 初始化阈值设置列表的LevelType
 */
function initLevelTypeList(start,end) {
	var id;
	for(var i=start;i<=end;i++){
		id='level'+i+'Type';
		if($('#'+id)){
			initSelect('error_level',id);
		}
	}
}
/**
 * 删除指定的Level
 * @param i
 * @returns
 */
function deletelevel(i){
	$('#level'+i).css('display','none');
}
/**
 * 保存
 */
function submit(){
	var parmas = {
		"thresholdTemplate.templateName" : $.trim($("#templateName").val()),
		"thresholdTemplate.watchType" : $.trim($("#watchType").val()),
		"thresholdTemplate.rangeLow" : $.trim($("#rangeLow").val()),
		"thresholdTemplate.rangeHigh" : $.trim($("#rangeHigh").val()),
		"thresholdTemplate.level1Type" : $.trim($("#level1Type").val()),
		"thresholdTemplate.leve1Low" : $.trim($("#leve1Low").val()),
		"thresholdTemplate.level1High" : $.trim($("#level1High").val()),
		"thresholdTemplate.level2Type" : $.trim($("#level2Type").val()),
		"thresholdTemplate.leve2Low" : $.trim($("#leve2Low").val()),
		"thresholdTemplate.level2High" : $.trim($("#level2High").val()),
		"thresholdTemplate.level3Type" : $.trim($("#level3Type").val()),
		"thresholdTemplate.leve3Low" : $.trim($("#leve3Low").val()),
		"thresholdTemplate.level3High" : $.trim($("#level3High").val()),
		"thresholdTemplate.level4Type" : $.trim($("#level4Type").val()),
		"thresholdTemplate.leve4Low" : $.trim($("#leve4Low").val()),
		"thresholdTemplate.level4High" : $.trim($("#level4High").val()),
		"thresholdTemplate.level5Type" : $.trim($("#level5Type").val()),
		"thresholdTemplate.leve5Low" : $.trim($("#leve5Low").val()),
		"thresholdTemplate.level5High" : $.trim($("#level5High").val()),
		"thresholdTemplate.level6Type" : $.trim($("#level6Type").val()),
		"thresholdTemplate.leve6Low" : $.trim($("#leve6Low").val()),
		"thresholdTemplate.level6High" : $.trim($("#level6High").val()),
		"thresholdTemplate.level7Type" : $.trim($("#level7Type").val()),
		"thresholdTemplate.leve7Low" : $.trim($("#leve7Low").val()),
		"thresholdTemplate.level7High" : $.trim($("#level7High").val()),
		"thresholdTemplate.level8Type" : $.trim($("#level8Type").val()),
		"thresholdTemplate.leve8Low" : $.trim($("#leve8Low").val()),
		"thresholdTemplate.level8High" : $.trim($("#level8High").val()),
		"thresholdTemplate.level9Type" : $.trim($("#level9Type").val()),
		"thresholdTemplate.leve9Low" : $.trim($("#leve9Low").val()),
		"thresholdTemplate.level9High" : $.trim($("#level9High").val()),
		"thresholdTemplate.level10Type" : $.trim($("#level10Type").val()),
		"thresholdTemplate.leve10Low" : $.trim($("#leve10Low").val()),
		"thresholdTemplate.level10High" : $.trim($("#level10High").val()),
	};
	
	$(".formButton_green").attr("disabled", "disabled");
	$.ajax({
		url : basePath + "plans/thresholdtemplate_add.action",
		type : "post",
		data : parmas,
		success : function(data){
			if (data == 1) {
				//artDialog.alert("新增成功！");
				window.location.href = basePath+"wh/logined/thresholdTemplate/templateList.jsp";
			}else{
				artDialog.alert("新增失败！");
				$(".formButton_green").removeAttr("disabled");
			}
		}
	});
}