$(document).ready(function(){
	//初始化fileupload
	initfileupload();
	//加载企业名称
	//getSelectCompany();
	//初始化预案类型下拉选
	initSelect("planType","planType");
	//获取公司基本信息
	getCompanyInfo();
	//获取应急预案信息
	getPlansInfo();
		
	
	
	//保存修改
	$("#submit").click(function(){
		// 框架校验
		if (!validator(document.getElementById("form1"))) {
			return;
		}
		//art.dialog.confirm('确定要保存修改吗？', function() {
			submit();
		/*}, function() {
			return;
		});*/
	});
	
});

function initfileupload(){
	var fileupload = qytx.app.fileupload({
		id:"file_upload",
		hiddenID:"attachmentId",
		queueID:"queue",
		moduleName:"plans",
		queueSizeLimit:"1",
		fileTypeExts:"*.doc;*.docx;*.pdf",
		callback:function(data){
			$("#attachmentId").val(','+data.id+',');
			$("#path").val(data.attachFile);
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
	$("#path").val("");
	$("#attachName").val("");
}


/*function getSelectCompany(){
	$.ajax({
		url : basePath + "companywh/getCompanyNameList.action",
		type : "post",
		dataType : 'json',
		data : {
		},
		success : function(data) {
			if (data != null && data!="") {
				var list = eval(data);
				var html = '';
				for (var i = 0; i < list.length; i++) {
						var companyName = list[i].companyName;
						var groupId = list[i].groupId;
						html += '<option value="'+groupId+'"';
						html += '>'+companyName+"</option>";
					}
				}
				$("#companName").html(html);
			}
	});
}
*/

/**
 * 初始化数据字段下拉框
 * @return
 */
function initSelect(typeName,id) {
	var paramData = {
		'infoType' : typeName,
		'sysTag' : 1
	};
	qytx.app.ajax({
		url : basePath + "dict/getDicts.action",
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
 * 获取公司基本信息
 */
function getCompanyInfo(){
	var groupId = $.trim($("#group_id").val());
	//异步获取人员信息
	$.ajax({
		url : basePath + "companywh/getWHCompanyInfo.action",
		type : "post",
		dataType : 'json',
		data : {
			groupId : groupId
		},
		success : function(data) {
			if (data != null) {
				var company = eval(data);
				//企业法人
				$("#legalRepresentative").html(company.legalRepresentative);
				$("#cityId").html(company.cityId);
				$("#companyName").html(company.companyName);
			}
		}
		
	});
}

/**
 * 获取应急预案信息
 */
function getPlansInfo(){
	var vid = $("#vid").val();//应急预案ID
	$.ajax({
		url : basePath + "plans/plans_getInfo.action",
		type : "post",
		dataType : 'json',
		data : {
			vid : vid
		},
		success : function(data) {
			if (data != null) {
				var plans = eval(data);
				$("#planType").val(plans.planType);
				$("#planNo").val(plans.planNo);
				$("#agent").val(plans.agent);
				$("#phone").val(plans.phone);
				$("#prepareTime").val(plans.prepareDate);
				$("#prepareEndTime").val(plans.prepareEndDate);
				
				var attachmentIds = plans.attachmentIds;
				if(attachmentIds == null || attachmentIds == undefined || attachmentIds == '' || attachmentIds == '-'){
					$("#attachmentList").html("");
				}else{
					$("#path").val(plans.path);
					$("#attachmentId").val(plans.attachmentIds);
					$("#attachName").val(plans.attachName);
					$("#attachName1").html(plans.attachName);
					$("#li").show();
				}
				
				
			} else {
				artDialog.alert("加载应急预案信息！");
			}
		}
		
	});
}

/**
 * 保存企业信息
 */
function submit(){

	var parmas = {
		"plans.vid" : $.trim($("#vid").val()),
		"plans.planNo" : $.trim($("#planNo").val()),
		"plans.planType" : $.trim($("#planType").val()),
		"plans.agent" : $.trim($("#agent").val()),
		"plans.phone" : $.trim($("#phone").val()),
		"plans.path" : $.trim($("#path").val()),
		"plans.attachmentIds" : $.trim($("#attachmentId").val()),
		"plans.attachName" : $.trim($("#attachName").val()),
		"prepareTime":$.trim($("#prepareTime").val())+" 00:00:00",
		"prepareEndTime":$.trim($("#prepareEndTime").val())+" 00:00:00"
	};
	
	$(".formButton_green").attr("disabled", "disabled");
	$.ajax({
		url : basePath + "plans/plans_update.action",
		type : "post",
		data : parmas,
		success : function(data){
			if (data == 1) {
				//artDialog.alert("新增成功！");
				window.location.href = basePath+"wh/logined/plans/plansList.jsp";
			}else{
				artDialog.alert("修改失败！");
				$(".formButton_green").removeAttr("disabled");
			}
		}
	});
	
	
}


/**
 * 时间加3年再减一天
 */
function putTime(){
	var prepareTime = $("#prepareTime").val();
	if(prepareTime=="" || prepareTime.length!=10){
		return;
	}
	var beginDate = new Date(prepareTime);
	var year = beginDate.getYear()+1900;
	beginDate.setYear(year+3);
	var endDate = new Date(beginDate.getTime()-(24 * 60 * 60 * 1000));
	year = endDate.getYear()+1900;
	var month = endDate.getMonth()+1;
	if(month<10){
		month = "0"+month;
	}
	var day = endDate.getDate();
	if(day<10){
		day = "0"+day;
	}
	$("#prepareEndTime").val(year+"-"+month+"-"+day);
}