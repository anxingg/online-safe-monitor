$(document).ready(function() {
	//业务类别
	var id = $("#businessTypeId").val();
	getBusinessTypeName(id);		
});
/**
 * 业务类别
 */
function getBusinessTypeName(id){
	var paramData = {
			'businessType' :id
		};
	$.ajax({
		url: basePath + "customercalllog/getbusinessTypeName.action",
		type: "post",
		data: paramData,
		success : function(data){
			$("#businessType").html(data);
		}
	});
}