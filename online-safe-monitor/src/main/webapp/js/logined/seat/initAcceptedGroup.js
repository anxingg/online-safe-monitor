
$(document).ready(function() {
	// 初始化坐席下属部门
	var dataParam = {
			'groupId':276
	};
	$.ajax({
	    type : 'post',
	    url : basePath + "group/findSeatGroupList.action",
	    data : dataParam,
	    dataType : 'json',
	    success : function(data) {
		    if (null != data) {
		    	var result = data.aaData; 
		    	for (var i=0;i<result.length;i++){
		    		$("#acceptedGroupId").append('<option value="'+result[i].groupId+'">'+result[i].groupName+'</option>');
		    	}
		    }
	    }
	});

});
