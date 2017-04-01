$(document).ready(function(){
	$.ajax({
		url : basePath + "whmenu/fetchOSMIndexMenu.action",
		type : "post",
		dataType : 'html',
		success : function(data) {
			console.log("success:"+data);
			$("#parkCont").html(data);
		}
	});
	
});
