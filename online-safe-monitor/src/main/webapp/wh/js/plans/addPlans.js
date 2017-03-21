$(document).ready(function(){
	//初始化fileupload
	initfileupload();
	//加载企业名称
	//getSelectCompany();
	//初始化预案类型下拉选
	initSelect("planType","planType");
	//获取公司基本信息
	getCompanyInfo();

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


//function getSelectCompany(){
//	$.ajax({
//		url : basePath + "companywh/getCompanyNameList.action",
//		type : "post",
//		dataType : 'json',
//		data : {
//		},
//		success : function(data) {
//			if (data != null && data!="") {
//				var list = eval(data);
//				var html = '';
//				for (var i = 0; i < list.length; i++) {
//						var companyName = list[i].companyName;
//						var groupId = list[i].groupId;
//						html += '<option value="'+groupId+'"';
//						html += '>'+companyName+"</option>";
//					}
//				}
//				$("#companName").html(html);
//			}
//	});
//}


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
 * 保存企业信息
 */
function submit(){

	var parmas = {
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
		url : basePath + "plans/plans_add.action",
		type : "post",
		data : parmas,
		success : function(data){
			if (data == 1) {
				//artDialog.alert("新增成功！");
				window.location.href = basePath+"wh/logined/plans/plansList.jsp";
			}else{
				artDialog.alert("新增失败！");
				$(".formButton_green").removeAttr("disabled");
			}
		}
	});
	
	
}


/**
 * 获取应急预案集合下拉选
 */
//function getSelectPlans(){
//	$.ajax({
//		url : basePath + "plans/plans_getPlansList.action",
//		type : "post",
//		dataType : 'json',
//		data : {
//		},
//		success : function(data) {
//			if (data != null && data!="") {
//				var list = eval(data);
//				var html = '';
//				html += '<option value="-1,-1">请选择</option>';
//				for (var i = 0; i < list.length; i++) {
//						var vid = list[i].vid;//id
//						var planType = list[i].planType;//预案类型
//						var planNo = list[i].planNo;//预案编号
//						html += '<option value="'+vid+","+planType+'"';
//						html += '>'+planNo+"</option>";
//					}
//				}
//				$("#selectPlans").html(html);
//			}
//	});
//}

/**
 * 时间加3年再减一天
 */
function putTime(){
	var prepareTime = $("#prepareTime").val();
	if(prepareTime=="" || prepareTime.length!=10){
		return;
	}
	var beginDate =  new Date(Date.parse(prepareTime.replace(/-/g,   "/"))); //转换成Data();
	var year = beginDate.getFullYear();
	beginDate.setYear(year+3);
	var endDate = new Date(beginDate.getTime()-(24 * 60 * 60 * 1000));
	year = endDate.getFullYear();
	var month = endDate.getMonth()+1;
	var monthStr = "";
	if(month<10){
		monthStr = "0"+month;
	}else{
		monthStr = month;
	}
	var day = endDate.getDate();
	var dayStr = "";
	if(day<10){
		dayStr = "0"+day;
	}else{
		dayStr = day;
	}
	$("#prepareEndTime").val(year+"-"+monthStr+"-"+dayStr);
}