$(document).ready(function(){
	//获取应急预案信息
	getPlansInfo();
		
	
});


/**
 * 获取应急预案信息
 */
function getPlansInfo(){
	var vid = $.trim($("#vid").val());//应急预案ID
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
				$("#planTypeName").html(plans.planTypeName);
				$("#planNo").html(plans.planNo);
				$("#agent").html(plans.agent);
				$("#phone").html(plans.phone);
				$("#prepareTime").html(plans.prepareDate);
				$("#prepareEndTime").html(plans.prepareEndDate);
				var attachmentIds = plans.attachmentIds;
				var html = '';
				if(attachmentIds == null || attachmentIds == undefined || attachmentIds == '' || attachmentIds == '-'){
					html = '无附件';
				}else {
					html = '<a href="javascript:;" onclick="downloadReport(\''+attachmentIds+'\');">'+plans.attachName+'</a>';
				}
				$("#att").html(html);
				
				//异步获取人员信息
				$.ajax({
					url : basePath + "companywh/getWHCompanyInfo.action",
					type : "post",
					dataType : 'json',
					data : {
						groupId : plans.groupId
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
				
			} else {
				artDialog.alert("加载应急预案信息！");
			}
		}
		
	});
}


/**
 * 下载附件
 * @param id
 */
function downloadReport(id){
	var attachmentId = new String(id);
	attachmentId = attachmentId.replace(new RegExp(",","g"),'');
	downLoadAttachment(attachmentId);
}

