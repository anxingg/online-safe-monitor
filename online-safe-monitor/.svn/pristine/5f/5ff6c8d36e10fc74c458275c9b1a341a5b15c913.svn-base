$(document).ready(function() {
	
	//获取数据
	getImg();
	
	//上传按钮1
	var fileupload1 = qytx.app.fileupload({
		id:"file_upload1",
		hiddenID:"attachmentId1",
		queueID:"queue",
		moduleName:"company",
		queueSizeLimit:"1",
		fileTypeExts:"*.jpg;*.jpeg;*.png;*.gif",
		callback:function(data){
			var attachmentId = data.id;
			saveImg("安全生产许可证正本",attachmentId,data.attachFile,"1");
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
		moduleName:"company",
		queueSizeLimit:"1",
		fileTypeExts:"*.jpg;*.jpeg;*.png;*.gif",
		callback:function(data){
			var attachmentId = data.id;
			saveImg("安全生产许可证副本",attachmentId,data.attachFile,"2");
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
		moduleName:"company",
		queueSizeLimit:"1",
		fileTypeExts:"*.jpg;*.jpeg;*.png;*.gif",
		callback:function(data){
			var attachmentId = data.id;
			saveImg("危险化学用品登记证",attachmentId,data.attachFile,"3");
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
	
	
	//上传按钮4
	var fileupload4 = qytx.app.fileupload({
		id:"file_upload4",
		hiddenID:"attachmentId4",
		queueID:"queue",
		moduleName:"company",
		queueSizeLimit:"1",
		fileTypeExts:"*.jpg;*.jpeg;*.png;*.gif",
		callback:function(data){
			var attachmentId = data.id;
			saveImg("三级标准化达标证",attachmentId,data.attachFile,"4");
		},
		deleteFun:function(id,path){
			var attachmentIds = $("#attachmentId4").val();
			attachmentIds = attachmentIds.replace(","+id+",",",");
			if(attachmentIds == ","){
				attachmentIds = "";
			}
			$("#attachmentId4").val(attachmentIds);
		}
	});
	
	
	//上传按钮5
	var fileupload5 = qytx.app.fileupload({
		id:"file_upload5",
		hiddenID:"attachmentId5",
		queueID:"queue",
		moduleName:"company",
		queueSizeLimit:"1",
		fileTypeExts:"*.jpg;*.jpeg;*.png;*.gif",
		callback:function(data){
			var photoName = $.trim($("#photoName5").val());
			if(photoName==""){
				photoName="附件5";
			}
			var attachmentId = data.id;
			saveImg(photoName,attachmentId,data.attachFile,"5");
		},
		deleteFun:function(id,path){
			var attachmentIds = $("#attachmentId5").val();
			attachmentIds = attachmentIds.replace(","+id+",",",");
			if(attachmentIds == ","){
				attachmentIds = "";
			}
			$("#attachmentId5").val(attachmentIds);
		}
	});
	
	
	//上传按钮6
	var fileupload6 = qytx.app.fileupload({
		id:"file_upload6",
		hiddenID:"attachmentId6",
		queueID:"queue",
		moduleName:"company",
		queueSizeLimit:"1",
		fileTypeExts:"*.jpg;*.jpeg;*.png;*.gif",
		callback:function(data){
			var photoName = $.trim($("#photoName6").val());
			if(photoName==""){
				photoName="附件6";
			}
			var attachmentId = data.id;
			saveImg(photoName,attachmentId,data.attachFile,"6");
		},
		deleteFun:function(id,path){
			var attachmentIds = $("#attachmentId6").val();
			attachmentIds = attachmentIds.replace(","+id+",",",");
			if(attachmentIds == ","){
				attachmentIds = "";
			}
			$("#attachmentId6").val(attachmentIds);
		}
	});
});


/**
 * @param photoName  附件名称
 * @param attachmentId   附件id
 * @param attachmentPath  附件相对路径
 * @param i  
 */
function saveImg(photoName,attachmentId,attachmentPath,i){
	
	var parmas = {
			'photoName': photoName,
			'photoType' : i,
			'attachmentId':attachmentId,
			'attachmentPath' : attachmentPath
	};
	$.ajax({
		url : basePath + "companywh/saveOrUpdatePhoto.action",
		type : "post",
		data : parmas,
		success : function(data){
			if (data == 1) {
				artDialog.alert("上传成功！",function(){					
					var src = downPath+"upload/"+attachmentPath;
					$("#img"+i).attr("src",src);
				});
			}else{
				artDialog.alert("上传失败！");
			}
		}
	});
}


function getImg(){
	$.ajax({
		url : basePath + "companywh/getPhonePath.action",
		type : "post",
		data : {
			'photoType' : 1
		},
		dataType : "json",
		success : function(data){
			if (data != null) {
				var map = data;
				if(map.isNull==0){
					var src = downPath+"upload/"+map.attachmentPath;
					$("#img1").attr("src",src);
					
					//alert(map.photoName);
				}
			}
		}
	});
	
	$.ajax({
		url : basePath + "companywh/getPhonePath.action",
		type : "post",
		data : {
			'photoType' : 2
		},
		dataType : "json",
		success : function(data){
			if (data != null) {
				var map = data;
				if(map.isNull==0){
					var src = downPath+"upload/"+map.attachmentPath;
					$("#img2").attr("src",src);
					
					//alert(map.photoName);
				}
			}
		}
	});
	
	$.ajax({
		url : basePath + "companywh/getPhonePath.action",
		type : "post",
		data : {
			'photoType' : 3
		},
		dataType : "json",
		success : function(data){
			if (data != null) {
				var map = data;
				if(map.isNull==0){
					var src = downPath+"upload/"+map.attachmentPath;
					$("#img3").attr("src",src);
					
					//alert(map.photoName);
				}
			}
		}
	});
	
	$.ajax({
		url : basePath + "companywh/getPhonePath.action",
		type : "post",
		data : {
			'photoType' : 4
		},
		dataType : "json",
		success : function(data){
			if (data != null) {
				var map = data;
				if(map.isNull==0){
					var src = downPath+"upload/"+map.attachmentPath;
					$("#img4").attr("src",src);
					
					//alert(map.photoName);
				}
			}
		}
	});
	
	
	$.ajax({
		url : basePath + "companywh/getPhonePath.action",
		type : "post",
		data : {
			'photoType' : 5
		},
		dataType : "json",
		success : function(data){
			if (data != null) {
				var map = data;
				if(map.isNull==0){
					var src = downPath+"upload/"+map.attachmentPath;
					$("#img5").attr("src",src);
					$("#photoName5").val(map.photoName);
					//alert(map.photoName);
				}
			}
		}
	});
	
	$.ajax({
		url : basePath + "companywh/getPhonePath.action",
		type : "post",
		data : {
			'photoType' : 6
		},
		dataType : "json",
		success : function(data){
			if (data != null) {
				var map = data;
				if(map.isNull==0){
					var src = downPath+"upload/"+map.attachmentPath;
					$("#img6").attr("src",src);
					$("#photoName6").val(map.photoName);
					//alert(map.photoName);
				}
			}
		}
	});
}



function deleteImg(id){
	$.ajax({
		url : basePath + "companywh/deleteImg.action",
		type : "post",
		data : {
			'photoType' : id
		},
		dataType : "json",
		success : function(data){
			if (data == 1) {
				$("#img"+id).attr("src",basePath+"wh/images/up.jpg");
			}
		}
	});
}