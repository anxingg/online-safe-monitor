/**
 * 查看详情
 */
$(document).ready(function() {
	var infoId = $("#infoId").val();
	getDetail(infoId);
})
	
/**
 * 加载工业流程信息
 * @param infoId
 */
function getDetail(infoId) {
	dataParam = {
		'infoId' : infoId
	};
	$.ajax({
		type : 'post',
		url : basePath + "technologicalProcess/loadProcess.action",
		data : dataParam,
		dataType : 'json',
		async : false,
		success : function(data) {
			$("#title").text(data.title);
			$("#content").html(data.introduction);
			if (null != data.fileIds && "" != data.fileIds) {
					$("#attachmentId").val(data.fileIds);
					for (var i = 0; i < data.attachmentList.length; i++) {
						$("#attachmentList")
								.append("<li><div class=\"icon\"><em class=\"doc\">" 
										+"</em></div><div class=\"txt\"><p>"
										+ data.attachmentList[i]["attachName"]
										+ "</p><p><a  href=\"javascript:void(0);\"  onclick=\"downFileById("
										+ data.attachmentList[i]["id"]
										+ ",this);\">下载</a></p></li>");
					}
				
			} else {
				$("#fileListDiv").css("display", "none");
			}
		}
	});
}

function downFileById(id) {
	window.open(basePath + 'filemanager/downfile.action?attachmentId=' + id);
}
