$(document).ready(function() {
	//操作类型
	var operation = $("#operation").val();
	if(operation == 2){
		
		//上传按钮1
		var fileupload1 = qytx.app.fileupload({
			id:"file_upload1",
			hiddenID:"attachmentId1",
			queueID:"queue",
			moduleName:"safetyInstitutions",
			queueSizeLimit:"1",
			fileTypeExts:"*.jpg;*.jpeg;*.png;*.gif;*.doc;*.docx;*.pdf",
			callback:function(data){
				var attachmentId = data.id;
				//alert(JSON.stringify(data));
				saveAttachment(data.attachName,data.id,data.attachFile,"attachmentId1");
			},
			deleteFun:function(id,path){
				var attachmentIds = $("#attachmentId1").val();
				attachmentIds = attachmentIds.replace(","+id+",",",");
				if(attachmentIds == ","){
					attachmentIds = "";
				}
				$("#attachmentId1").val(attachmentIds);
			}
		});
		
		//上传按钮2
		var fileupload2 = qytx.app.fileupload({
			id:"file_upload2",
			hiddenID:"attachmentId2",
			queueID:"queue",
			moduleName:"safetyInstitutions",
			queueSizeLimit:"1",
			fileTypeExts:"*.jpg;*.jpeg;*.png;*.gif;*.doc;*.docx;*.pdf",
			callback:function(data){
				saveAttachment(data.attachName,data.id,data.attachFile,"attachmentId2");
			},
			deleteFun:function(id,path){
				var attachmentIds = $("#attachmentId2").val();
				attachmentIds = attachmentIds.replace(","+id+",",",");
				if(attachmentIds == ","){
					attachmentIds = "";
				}
				$("#attachmentId2").val(attachmentIds);
			}
		});
		
		
		//上传按钮3
		var fileupload3 = qytx.app.fileupload({
			id:"file_upload3",
			hiddenID:"attachmentId3",
			queueID:"queue",
			moduleName:"safetyInstitutions",
			queueSizeLimit:"1",
			fileTypeExts:"*.jpg;*.jpeg;*.png;*.gif;*.doc;*.docx;*.pdf",
			callback:function(data){
				saveAttachment(data.attachName,data.id,data.attachFile,"attachmentId3");
			},
			deleteFun:function(id,path){
				var attachmentIds = $("#attachmentId3").val();
				attachmentIds = attachmentIds.replace(","+id+",",",");
				if(attachmentIds == ","){
					attachmentIds = "";
				}
				$("#attachmentId3").val(attachmentIds);
			}
		});
	}
	
	//附件的清除按钮绑定事件
	$(".clen_a").live("click", function(){
		var id = $(this).attr("name");
		deleteOneAttachment(id, $(this));
	});
	
	//确定按钮绑定事件
	$(".formButton_green").bind("click", function() {
		updateDataBase();
		return false;
	});
});

/**
 * 保存方法（保存到数据库中）
 */
function updateDataBase(){
	var groupId = $("#groupId").val();
	var createFileIds = $("#attachmentId1").val();
	var departmentFileIds = $("#attachmentId2").val();
	var groupFileIds = $("#attachmentId3").val();
	
	$.ajax({
		url : basePath + "companywh/sis_update.action",
		type : "post",
		dataType : 'json',
		data : {
			'whCompany.groupId' : groupId,
			'sis.createFileIds' : createFileIds,
			'sis.departmentFileIds' : departmentFileIds,
			'sis.groupFileIds' : groupFileIds
		},
		success : function(data) {
			$("#attachmentId1").siblings(".mydata").html("");
			var cflist = data.cf;
			for(var i = 0; i < cflist.length; i++){
				$("#attachmentId1").siblings(".mydata").append('<p>'+cflist[i].name+' &nbsp;<a href="javascript:void(0);" name="'+cflist[i].id+'" class="clen_a">清除</a></p>');
			}
			$("#attachmentId2").siblings(".mydata").html("");
			var dflist = data.df;
			for(var i = 0; i < dflist.length; i++){
				$("#attachmentId2").siblings(".mydata").append('<p>'+dflist[i].name+' &nbsp;<a href="javascript:void(0);" name="'+dflist[i].id+'" class="clen_a">清除</a></p>');
			}
			$("#attachmentId3").siblings(".mydata").html("");
			var gflist = data.gf;
			for(var i = 0; i < gflist.length; i++){
				$("#attachmentId3").siblings(".mydata").append('<p>'+gflist[i].name+' &nbsp;<a href="javascript:void(0);" name="'+gflist[i].id+'" class="clen_a">清除</a></p>');
			}
			/*
			if(data == 1){
				$("#"+domId).siblings(".mydata").append('<p>'+name+' &nbsp;<a href="javascript:void(0);" name="'+id+'" class="clen_a">清除</a></p>');
			}
			*/
			art.dialog.alert("保存成功");
		}
	});
}

/**
 * 上传后在页面上添加附件
 * @param name
 * @param id
 * @param path
 * @param domId
 */
function saveAttachment(name, id, path, domId) {
	var groupId = $("#groupId").val();
	var createFileIds = null;
	var departmentFileIds = null;
	var groupFileIds = null;
	
	if(domId == 'attachmentId1'){
		createFileIds = $("#"+domId).val();
	}else if(domId == 'attachmentId2'){
		departmentFileIds = $("#"+domId).val();
	}else if(domId == 'attachmentId3'){
		groupFileIds = $("#"+domId).val();
	}
	
	$("#"+domId).siblings(".mydata").append('<p>'+name+' &nbsp;<a href="javascript:void(0);" name="'+id+'" class="clen_a">清除</a></p>');
	return;
}

/**
 * 点击"清除"后，在页面上删除
 * @param id
 * @param $domObj
 */
function deleteOneAttachment(id, $domObj) {
	var domId = $domObj.parent().parent().siblings().first().attr("id");
	//alert(domId+', '+id+', '+$domObj);
	$("#"+domId).val($("#"+domId).val().replace(','+id, ''));
	$domObj.parent().remove();
	
	/* 下面把删除保存数据库 */
	var groupId = $("#groupId").val();
	var createFileIds = null;
	var departmentFileIds = null;
	var groupFileIds = null;
	
	if(domId == 'attachmentId1'){
		createFileIds = $("#"+domId).val();
	}else if(domId == 'attachmentId2'){
		departmentFileIds = $("#"+domId).val();
	}else if(domId == 'attachmentId3'){
		groupFileIds = $("#"+domId).val();
	}
	
	return;
}

/**
 * 下载附件
 * @param id
 */
function downloadFile(obj){
	var id = $(obj).attr("name");
	id = id.replace(new RegExp(",","g"),'');
	downLoadAttachment(parseInt(id, 10));
}