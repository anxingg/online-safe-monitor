$(document).ready(function() {
			var affairId = $("#affairId").val();
			getAffairBeanInfo(affairId);
		})

function getAffairBeanInfo(affairId) {
	dataParam = {
		'affairBeanVo.affId' : affairId
	};
	$.ajax({
				type : 'post',
				url : basePath + "calendar/getAffairBeanInfo.action",
				data : dataParam,
				dataType : 'json',
				async : false,
				success : function(data) {
					if (!validateIsEmpty(data.remindTime)) {
						$("#remindTimeStr").text(data.remindTime);
					}
					if (!validateIsEmpty(data.createUserName)) {
						$("#createUserName").text("创建者：" + data.createUserName);
					}
					if (!validateIsEmpty(data.takerName)) {
						$("#takerName").text("参与者：" + data.takerName);
					}
					if (!validateIsEmpty(data.content)) {
						$("#content").text(data.content);
					}
				}
			});
}

function validateIsEmpty(obj) {
	if (obj != null && obj != "") {
		return false;
	}
	return true;
}
