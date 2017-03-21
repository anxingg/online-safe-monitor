

$(document).ready(function() {
	//当修改页面时，读取事故性质的值
	//var hiddenAccidentCharacter = $("#hiddenAccidentCharacter").val();
	//初始化事故性质下拉框
	//initDictType('accidentCharacterType', 'accidentCharacter', hiddenAccidentCharacter == ''?undefined:hiddenAccidentCharacter);
	
	//当政府端访问时，加载企业下拉框
	//if($("#whroletype").val() == 1){
		//getSelectCompany();
	//}
	
	//附件上传（使用CBB封装后的东西）
	var fileupload = qytx.app.fileupload({
		id:"file_upload",
		hiddenID:"attachmentId",
		queueID:"queue",
		//ulName:"attachmentList",
		moduleName:'safeAccident',
		fileTypeExts:"*.doc;*.docx;*.pdf",
		queueSizeLimit:"1",
		callback:function(data){
			$("#attachmentId").val(','+data.id+',');
			var html='<li><div><p>';
			html+=data.attachName;
			html+='</p><p>';
			html+='<a href="javascript:void(0);" class="deleteAttachment">删除</a></p>';
			html+='<p class="clear"></p>';
			html+='</div></li>';
			$("#attachmentList").html(html);
		}
	});
	
	//动态绑定删除附件事件
	$(".deleteAttachment").live("click", function() {
		deleteAtta(this);
	});
	
	//确定按钮绑定事件
	$(".formButton_green").bind("click", function() {
		// 框架校验
		if (validator(document.getElementById("form1"))) {
			addSafeAccident();
		}
		return false;
	});
});


/**
 * 附件删除
 * @param domAObj 传this
 */
function deleteAtta(domAObj) {
	$(domAObj).parent().parent().parent().parent().parent().prev().prev().prev().val(',');
	//把li删除
	$(domAObj).parent().parent().parent().remove();
}


/**
 * 新增或修改
 */
function addSafeAccident() {
	$(".formButton_green").attr("disabled", "disabled");
	
	//主键
	var vid = $("#vid").val();
	if(!vid){
		vid = -1;
	}
	
	//部门ID（当政府端访问时，应取选择的企业）
	var groupId = $("#group_id").val();
	if($("#whroletype").val() == 1){
		groupId = $("#companName").val();
	}
	
	//安全事故名称
	var accidentName = $.trim( $("#accidentName").val() );
	
	//责任人
	var responsible = $.trim( $("#responsible").val() );
	
	//事故发生时间
	var occurredTimeStr = $.trim( $("#occurredTime").val() );
	if(occurredTimeStr != ''){
		occurredTimeStr += ' 00:00:00.0';
	}
	
	//事故性质
	var accidentCharacter = $("#accidentCharacter").val();
	
	//事故地点
	var occurredAddress = $.trim( $("#occurredAddress").val() );
	
	//事故简介
	var occurredDescription = $.trim( $("#occurredDescription").val() );
	
	//事故原因
	var occurredReason = $.trim( $("#occurredReason").val() );
	
	//事故后果
	var occurredConsequence = $.trim( $("#occurredConsequence").val() );
	
	//事故处理情况
	var processCondition = $.trim( $("#processCondition").val() );
	
	//备注
	var memo = $.trim( $("#memo").val() );
	
	//事故报告ID
	var reportId = $("#attachmentId").val();
	
	//数据来源
	var dataSource = $("#whroletype").val();
	
	//企业是否可见（当是政府的新增或修改页面时，这里改成从页面上取值。）
	var canSee = $(":radio[name='canSee']:checked").first().val();
	if(!canSee){
		canSee = 0;
	}
	if(dataSource == 2) {
		canSee = 1;//1表示可见
	}
	
	if($("#whroletype").val() == 1 && !verifyEmptyContent('companName', '请选择企业名称！', 0)){
		$(".formButton_green").removeAttr("disabled");
		return;
	}
	if(!verifyEmptyContent('accidentCharacter', '请选择事故性质！', 0)){
		$(".formButton_green").removeAttr("disabled");
		return;
	}
	
	$.ajax({
		url : basePath + "safeaccident/safeaccident_Add_saveOrUpdate.action",
		type : "post",
		dataType : 'json',
		data : {
			'safeAccident.vid' : vid,
			'safeAccident.groupId' : groupId,
			'safeAccident.accidentName' : accidentName,
			'safeAccident.responsible' : responsible,
			'safeAccident.occurredTimeStr' : occurredTimeStr,
			'safeAccident.accidentCharacter' : accidentCharacter,
			'safeAccident.occurredAddress' : occurredAddress,
			'safeAccident.occurredDescription' : occurredDescription,
			'safeAccident.occurredReason' : occurredReason,
			'safeAccident.occurredConsequence' : occurredConsequence,
			'safeAccident.processCondition' : processCondition,
			'safeAccident.memo' : memo,
			'safeAccident.canSee' : canSee,
			'safeAccident.dataSource' : dataSource,
			'safeAccident.reportId' : reportId
		},
		success : function(data) {
			if (data == 1) {
				if(vid == -1){
					//artDialog.alert("新增成功！", function(){
						if($("#whroletype").val() == 1){
							//政府
							window.location.href = basePath + "safeaccident/safeaccidentload_loadCompanyAndAccidentCharacter_safeAccidentListZF.action";
						}else {
							//企业
							window.location.href = basePath + "safeaccident/safeaccidentload_loadCompanyAndAccidentCharacter_safeAccidentList.action";
						}
					//});
				}else {
					//artDialog.alert("修改成功！", function(){
						if($("#whroletype").val() == 1){
							//政府
							window.location.href = basePath + "safeaccident/safeaccidentload_loadCompanyAndAccidentCharacter_safeAccidentListZF.action";
						}else {
							//企业
							window.location.href = basePath + "safeaccident/safeaccidentload_loadCompanyAndAccidentCharacter_safeAccidentList.action";
						}
					//});
				}
			} else if (data == 0){
				if(vid == -1){
					artDialog.alert("新增失败！");
					$(".formButton_green").removeAttr("disabled");
				}else {
					artDialog.alert("修改失败！");
					$(".formButton_green").removeAttr("disabled");
				}
			}
		}
		
	});
	
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