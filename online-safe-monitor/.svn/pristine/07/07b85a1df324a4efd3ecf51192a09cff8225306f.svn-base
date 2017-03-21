<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="../../../common/taglibs.jsp"/>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<jsp:include page="../head.jsp" />
		<title>公司证照</title>
	</head>
	<body>
		<input type="hidden" id="attachmentId" name="attachmentId"/>
	    <input id="file_upload" name="fileupload" type="file" multiple="true" />
	    <!-- 上传队列 -->
	    <div id="queue"  style="display:none;"></div>
        <div class="annex">
	        <ul id="attachmentList">
	        </ul>
        </div>
	</body>

</html>
<script>
$(document).ready(function() {
	var fileupload = qytx.app.fileupload({
		id:"file_upload",
		hiddenID:"attachmentId",
		queueID:"queue",
		ulName:"attachmentList",
		deleteFun:function(id,path){
			var attachmentIds = $("#attachmentId").val();
			attachmentIds = attachmentIds.replace(","+id+",",",");
			if(attachmentIds == ","){
				attachmentIds = "";
			}
			 $("#attachmentId").val(attachmentIds);
		}
	});
});
</script>
