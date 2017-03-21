/**
 * 下载附件
 * @param id int类型，没有前后逗号
 */
function downLoadAttachment(id) {
	window.location.href = basePath + "safeaccident/safeaccident_List_downFileByFilePath.action?attachmentId="+id;
}