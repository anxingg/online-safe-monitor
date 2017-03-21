$(document).ready(function(){
	//加载预案名称
	getSelectPlans();
	
	// 单击更新
	$("#save").click(function() {
		var cr = checkpara();
		if(cr){
			var valid = validator($("#addForm")[0]);
			if (valid) {
				addorup();
			}
			return false;
		}
	});
	
	//上传按钮
	var fileupload = qytx.app.fileupload({
		id:"file_upload",
		hiddenID:"attachmentId",
		queueID:"queue",
		moduleName:"yjyl",
		fileTypeExts:"*.jpg;*.jpeg;*.png;*.gif",
		callback:function(data){
			var attachmentId = data.id;
			var attachmentPath = downPath + "upload/"+data.attachFile;
			var creatAttachHtml = '<li id="'+attachmentId+'_li"><img src="'+attachmentPath+'"><span class="close" onclick="deleteImg('+attachmentId+')"></span></li>';
    		$("#attachmentList").append(creatAttachHtml);
		}
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


function getSelectPlans(){
	$.ajax({
		url : basePath + "plans/plans_getPlansList.action",
		type : "post",
		dataType : 'json',
		data : {
		},
		success : function(data) {
			if (data != null && data!="") {
				var list = eval(data);
				var html = '';
				html += '<option value="">请选择</option>';
				for (var i = 0; i < list.length; i++) {
						var vid = list[i].vid;//id
						var planType = list[i].planType;//预案类型
						var planNo = list[i].planNo;//预案编号
						html += '<option value="'+vid+","+planType+'"';
						html += '>'+planNo+"</option>";
					}
				}
				$("#plan_id").html(html);
			}
	});
}

function checkpara(){
	var exercise_time = $("#exercise_time").val();
	if(exercise_time==""){
		artDialog.alert("演练时间不能为空");
		return false;
	}
	return true;
}


function  addorup(){
	$.ajax({
		url:basePath+"yjyl/saveorup.action",
		type:"post",
		data:"ac_flag=add&plan_id="+$("#plan_id").val()+"&vid=&organize_group="+$("#organize_group").val()
		+"&exercise_name="+$("#exercise_name").val()+"&exercise_program="+$("#exercise_program").val()+"&exercise_purpose="+$("#exercise_purpose").val()+"&exercise_time="+$("#exercise_time").val()
		+"&exercise_address="+$("#exercise_address").val()+"&exercise_people="+$("#exercise_people").val()+"&exercise_records="+$("#exercise_records").val()
		+"&rescue_reviews="+$("#rescue_reviews").val()+"&reviews="+$("#reviews").val()+"&attachmentId="+$("#attachmentId").val(),
		dataType:"json",
		success:function(data){
			if(data=="0"){
				window.location.href=basePath+"wh/yjyl/query.jsp";
			}else{
				artDialog.alert("应急演练记录新增失败！");
			}
		},error:function(){
			artDialog.alert("应急演练记录新增失败！");
		}
	});
}
