

$(document).ready(function() {
	
	//技术说明书附件上传（使用CBB封装后的东西）
	qytx.app.fileupload({
		id:"file_upload",
		hiddenID:"attachmentId",
		queueID:"queue",
		//ulName:"attachmentList",
		fileTypeExts:"*.doc;*.docx;*.pdf",
		queueSizeLimit:"1",
		moduleName:'dangerChemicals',
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
	
	//安全标签附件上传（使用CBB封装后的东西）
	qytx.app.fileupload({
		id:"file_upload2",
		hiddenID:"attachmentId2",
		queueID:"queue2",
		//ulName:"attachmentList2",
		fileTypeExts:"*.doc;*.docx;*.pdf",
		queueSizeLimit:"1",
		moduleName:'dangerChemicals',
		callback:function(data){
			$("#attachmentId2").val(','+data.id+',');
			var html='<li><div><p>';
			html+=data.attachName;
			html+='</p><p>';
			html+='<a href="javascript:void(0);" class="deleteAttachment">删除</a></p>';
			html+='<p class="clear"></p>';
			html+='</div></li>';
			$("#attachmentList2").html(html);
		}
	});
	
	//动态绑定删除附件事件
	$(".deleteAttachment").live("click", function() {
		deleteAtta(this);
	});
	
	//确定按钮绑定事件
	$(".formButton_green").bind("click", function() {
		if(!verifyEmptyContent('dangerId', '请选择化学品名称！', 0)){
			return false;
		}
		// 框架校验
		if (validator(document.getElementById("form1"))) {
			addOne();
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
function addOne() {
	$(".formButton_green").attr("disabled", "disabled");
	
	//主键
	var vid = $("#vid").val();//$.trim("  hello, how are you?  ");
	if(!vid){
		vid = -1;
	}
	
	//化学品名称
	var dangerId = $("#dangerId").val();
	
	//部门ID
	var groupId = $("#group_id").val();
	
	//存放地点
	var storagePlace = $.trim( $("#storagePlace").val() );
	
	//数量
	var num = $("#num").val();
	
	//使用地点
	var userPlace = $.trim( $("#userPlace").val() );
	
	//危险性分类
	var riskType = $.trim( $("#riskType").val() );
	
	//危规号
	var riskNum = $.trim( $("#riskNum").val() );
	
	//包装类别
	var packagingCategory = $.trim( $("#packagingCategory").val() );
	
	//登记号
	var registrationNO = $.trim( $("#registrationNO").val() );
	
	//技术说明书ID
	var technicalId = $("#attachmentId").val();
	
	//安全标签ID
	var securityId = $("#attachmentId2").val();
	
	//备注
	var memo = $.trim( $("#memo").val() );
	
//	if($("#whroletype").val() == 1 && !verifyEmptyContent('companName', '请选择企业名称！', 0)){
//		return;
//	}
//	if(!verifyEmptyContent('accidentCharacter', '请选择事故性质！', 0)){
//		return;
//	}
	
	$.ajax({
		url : basePath + "dangerchemicals/companyDangerchemicals_Add_saveOrUpdate.action",
		type : "post",
		dataType : 'json',
		data : {
			'companyDangerChemicals.vid' : vid,
			'companyDangerChemicals.groupId' : groupId,
			'companyDangerChemicals.dangerId' : dangerId,
			'companyDangerChemicals.storagePlace' : storagePlace,
			'companyDangerChemicals.num' : num,
			'companyDangerChemicals.userPlace' : userPlace,
			'companyDangerChemicals.riskType' : riskType,
			'companyDangerChemicals.riskNum' : riskNum,
			'companyDangerChemicals.packagingCategory' : packagingCategory,
			'companyDangerChemicals.registrationNO' : registrationNO,
			'companyDangerChemicals.technicalId' : technicalId,
			'companyDangerChemicals.securityId' : securityId,
			'companyDangerChemicals.memo' : memo
		},
		success : function(data) {
			if (data == 1) {
				if(vid == -1){
					//artDialog.alert("新增成功！", function(){
						window.location.href = basePath + "wh/logined/dangerChemicals/companyDangerChemicalsList.jsp";
					//});
				}else {
					//artDialog.alert("修改成功！", function(){
						window.location.href = basePath + "wh/logined/dangerChemicals/companyDangerChemicalsList.jsp";
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