$(document).ready(function(){
	$.ajax({
		url : basePath + "whmenu/fetchSubSystemMenu.action",
		type : "post",
		dataType : 'html',
		data : 'subSystem=osm_systemmanage',
		success : function(data) {
			console.log("success:"+data);
			$("#leftMenu").html(data);
		}
	});
});
