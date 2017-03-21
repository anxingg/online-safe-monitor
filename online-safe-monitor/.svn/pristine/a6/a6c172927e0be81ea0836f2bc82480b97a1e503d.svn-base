$(document).ready(function(){
	
	getCompanyInfo();
});

/**
 * 获取公司基本信息
 */
function getCompanyInfo(){
	var groupId = $("#groupId").val();
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
				$("#companyId").html(company.companyId);
				var ent = company.enterpriseScale;
				
				if(ent==1){
					$("#enterpriseScale").html("20人以下");
				}else if(ent==2){
					$("#enterpriseScale").html("20-99人");
				}else if(ent==3){
					$("#enterpriseScale").html("100-499人");
				}else if(ent==4){
					$("#enterpriseScale").html("500-999人");
				}else if(ent==5){
					$("#enterpriseScale").html("1000-9999人");
				}else if(ent==6){
					$("#enterpriseScale").html("10000人以上");
				}
				$("#companyName").html(company.companyName);
				$("#cityId").html(company.cityId);
				$("#legalRepresentative").html(company.legalRepresentative);
				$("#introduction").html(company.introduction);
				
			}
		}
		
	});
}



/*function initSelect(typeName,val) {
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
			for (var i = 0; i < jsonData.length; i++) {
				if(jsonData[i].value==val){
					$("#enterpriseScale").html(jsonData[i].name);
					break;
				}
			}
		}
	});
}
*/



