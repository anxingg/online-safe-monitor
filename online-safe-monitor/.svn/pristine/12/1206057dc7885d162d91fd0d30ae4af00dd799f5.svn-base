var ue;
$(document).ready(function() {
	ue = UE.getEditor('contentInfo', {
		initialFrameWidth : "100%"
		});
	//初始化fileupload
	initfileupload();
});

function initfileupload(){
	var fileupload = qytx.app.fileupload({
		id:"file_upload",
		hiddenID:"attachmentId",
		queueID:"queue",
		moduleName:"training",
		queueSizeLimit:"1",
		fileTypeExts:"*.doc;*.docx;*.pdf",
		callback:function(data){
			$("#attachmentId").val(','+data.id+',');
			//$("#path").val(data.attachFile);
			$("#attachName").val(data.attachName);
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
}

/**
 * 附件删除
 * @param domAObj 传this
 */
function deleteAtta(domAObj) {
	//把li删除
	$(domAObj).parent().parent().parent().remove();
	//
	$("#attachmentId").val("");
	//$("#path").val("");
	$("#attachName").val("");
}

/**
 * 保存添加
 */
function addTraining(){
	
	var content=ue.getContent();
	if (null == content || '' == content) {
		showObjError($("#contentInfoInput"), 'notify.notify_content_not_null');
		return;
	}else {
		hideError($("#contentInfoInput"));
	}
	if(!validator(document.getElementById("form1"))){
		return;
	}
	var trainYear = $("#trainYear").val();
    //var details = $("#details").val();
    var charge = $("#charge").val();
    var trainRate = $("#trainRate").val();
    var trainObject = $("#trainObject").val();
    var trainForm = $("#trainForm").val();
    var memo = $("#memo").val();
	$(".formButton_green").attr("disabled", "disabled");
	//保存
	$.ajax({
		url : basePath + "training/addOrUpdateTraining.action",
		type : "post",
		dataType : 'json',
		data : {
			"training.trainYear" : trainYear,
			"training.details" : content,
			"training.charge" : charge,
			"training.trainRate" : trainRate,
			"training.trainObject" : trainObject,
			"training.trainForm" : trainForm,
			"training.attachmentIds" : $.trim($("#attachmentId").val()),
			"training.attachName" : $.trim($("#attachName").val()),
			"training.memo" : memo,
			"operType":"add",
			"trainingType":0
		},
		success : function(data) {
			if (data == 1) {
				window.location.href = basePath+"wh/logined/training/trainingList.jsp"; 
				//artDialog.alert("新增成功！",function(){					
					//重新加载列表
//					window.location.href = basePath+"wh/logined/training/companyProductList.jsp"; 
				//});
			} else if (data == 0){
				artDialog.alert("新增失败！");
				$(".formButton_green").removeAttr("disabled");
			}
		}
		
	});
}

	
