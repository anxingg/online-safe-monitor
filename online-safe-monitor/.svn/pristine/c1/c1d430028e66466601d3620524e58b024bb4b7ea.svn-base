/**
 * 新增岗前三级培训
 * @author wuzhou
 */
$(document).ready(function(){
	//上传按钮
	var fileupload = qytx.app.fileupload({
		id:"file_upload",
		hiddenID:"attachmentId",
		queueID:"queue",
		moduleName:"preservice",
		fileTypeExts:"*.jpg;*.jpeg;*.png;*.gif",
		callback:function(data){
			var attachmentId = data.id;
			var attachmentPath = downPath + "upload/"+data.attachFile;
			var creatAttachHtml = '<li id="'+attachmentId+'_li"><img src="'+attachmentPath+'"><span class="close" onclick="deleteImg('+attachmentId+')"></span></li>';
    		$("#attachmentList").append(creatAttachHtml);
		}
	});
	//点击确定
	$("#save").click(function(){
		savePerservice();
	});
	//鼠标经过事件
	$("#attachmentList").delegate("li", "mouseover", function(){ 
		$(this).find(".close").show();
	});
	$("#attachmentList").delegate("li", "mouseout", function(){ 
		$(this).find(".close").hide();
	});
});
function deleteImg(id){
	$('#file_upload').uploadify('cancel', id);
	$('#'+id+"_li").remove();
	var attachmentIds = $("#attachmentId").val();
	attachmentIds = attachmentIds.replace(","+id+",",",");
	if(attachmentIds == ","){
		attachmentIds = "";
	}
	$("#attachmentId").val(attachmentIds);
}

function savePerservice(){
	// 框架校验
	if (!validator(document.getElementById("trainform"))) {
		return;
	}
	var params = $("#trainform").serializeObject(); //将表单序列化为JSON对象 
	var param = {"opt":JSON.stringify(params)};
	var url = basePath+"training/saveOrUpdatePreservice.action";
	$.ajax({
		url:url,
		type:'POST',
		data:param,
		dataType:'json',
		success:function(data){
			if(data==1){
				window.location.href=basePath+"wh/logined/training/perserviceTrainingList.jsp";
			}else{
				art.dialog.alert("保存失败！");
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
$.fn.serializeObject = function() {
	var o = {};
	var a = this.serializeArray();
	$.each(a, function() {
		if (o[this.name]) {
			if (!o[this.name].push) {
				o[this.name] = [ o[this.name] ];
			}
			o[this.name].push(this.value || '');
		} else {
			o[this.name] = this.value || '';
		}
	});
	return o;
}