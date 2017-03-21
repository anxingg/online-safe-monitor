$(document).ready(function(){
	currentTimeNew();
});

function currentTimeNew(){
	
	$.ajax({
		url:basePath+"systemtime/getSystemTime.action",
		type:"post",
		dataType:'text',
		success:function(data){
		}
	});
};




setInterval('currentTimeNew()', 60000);