$(document).ready(function(){
	//初始化下拉框
	initSelect("watchType","watchType",false);
	initLevelTypeList(1,10);
	if($('#action').val()=='add'){
		$('#title').html('<em>新增</em>');
	}
	else{
		$('#title').html('<em>修改</em>');
		initInfo();
	}
	
	$("#addmore").click(function(){
		displayList(4,10,true);
		$("#addmore").css('display','none');
	});
	
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
 * 设置下拉框选中某数值
 */
function setSelect(id,value)
{
	$("#"+id+" option[value='"+value+"']").attr("selected", true);
}
/**
 * 初始化表单数据
 * @return
 */
function initInfo() {
	var vid = $('#vid').val();
	console.log("vid:"+vid);
	var paramData = {
		'vid' : vid
	};
	qytx.app.ajax({
		url : basePath + "thresholdtemplate/thresholdtemplate_getInfo.action",
		type : "post",
		dataType : "json",
		data : paramData,
		success : function(data) {
			if (data != null) {
				var thresholdTemplate = eval(data);
				$("#templateName").val(thresholdTemplate.templateName);
				setSelect('watchType',thresholdTemplate.watchType);
				$("#rangeLow").val(thresholdTemplate.rangeLow);
				$("#rangeHigh").val(thresholdTemplate.rangeHigh);
				var none=0;
				for(var i=1;i<11;i++){
					if(thresholdTemplate['level'+i+'Type']){
						setSelect('level'+i+'Type',thresholdTemplate['level'+i+'Type']);
						$('#leve'+i+'Low').val(thresholdTemplate['leve'+i+'Low']);
						$('#level'+i+'High').val(thresholdTemplate['level'+i+'High']);
						$("#level"+"i").css('display','');
					}
					else{
						if(i>3)
							$("#level"+"i").css('display','none');
						else{  //3个以内显示空
							$('#leve'+i+'Low').val('');
							$('#level'+i+'High').val('');
						}
						none++;
					}
				}
				if(none>0){
					$("#addmore").css('display','');
				}
				else{
					$("#addmore").css('display','none');
				}
			} else {
				artDialog.alert("加载模板信息失败！");
			}
		}
	});
}

/**
 * 初始化数据字段下拉框
 * @return
 */
function initSelect(typeName,id,isAddTipOption) {
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
			if(isAddTipOption){
				$("#"+id).append("<option value='-1'>请选择</option>");
			}
			for (var i = 0; i < jsonData.length; i++) {
				$("#"+id).append("<option value='"
						+ jsonData[i].value + "'>" + jsonData[i].name
						+ "</option>");
			}
		}
	});
}
/**
 * 控制显示隐藏
 * @param start
 * @param end
 * @param display
 */
function displayList(start,end,display){
	var id;
	for(var i=start;i<=end;i++){
		id='level'+i;
		if($('#'+id)){
			if(!display)
				$('#'+id).css("display","none");
			else
				$('#'+id).css("display","");
		}
	}
}
/**
 * 初始化阈值设置列表的LevelType
 */
function initLevelTypeList(start,end) {
	var id;
	for(var i=start;i<=end;i++){
		id='level'+i+'Type';
		if($('#'+id)){
			initSelect('error_level',id,true);
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
	$('#level'+i+'Type').val(-1);
	$('#leve'+i+'Low').val('');
	$('#level'+i+'High').val('');
}

/**
 * 保存
 */
function submit(){
	var vid = $('#vid').val();
	var parmas = {
		"vid" : vid,
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
		url : basePath + "thresholdtemplate/thresholdtemplate_save.action",
		type : "post",
		data : parmas,
		success : function(data){
			if (data == 1) {
				//artDialog.alert("新增成功！");
				window.location.href = basePath+"wh/logined/thresholdTemplate/templateList.jsp";
			}else{
				artDialog.alert("提交失败！是否一个阈值也没有设置？");
				$(".formButton_green").removeAttr("disabled");
			}
		}
	});
}