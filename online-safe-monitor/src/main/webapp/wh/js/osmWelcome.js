$(document).ready(function(){
	//osm_systemmanage
	var sys_name=$('#sys_name').val();
	$.ajax({
		url : basePath + "whmenu/fetchSubSystemMenu.action",
		type : "post",
		dataType : 'html',
		data : 'subSystem='+sys_name,
		success : function(data) {
			console.log("success:"+data);
			$("#leftMenu").html(data);
		}
	});
});
