<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>知识库文件初始化设置</title>
<%@ include file="../../common/taglibs.jsp"%>
<jsp:include page="../../common/head.jsp" />
<link href="${ctx}platform/css/base.css" rel="stylesheet" type="text/css" />
<link href="${ctx}platform/css/public.css" rel="stylesheet" type="text/css" />
<link href="${ctx}platform/css/add_com.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/plugins/datatable/table_style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx}platform/js/jquery-1.6.min.js"></script>
<script type="text/javascript" src="${ctx}platform/js/base.js"></script>
<script type="text/javascript" src="${ctx}platform/js/knowledgeFileSet.js"></script>
<script type="text/javascript" src="${ctx}plugins/dialog/artDialog.js?skin=blue"></script>

<script type="text/javascript" src="${ctx}plugins/dialog/iframeTools.js"></script>
</head>
<body>
<div class="addcon">
		<div class="szlist">
				<p><span>lucene根目录：</span>
						<input name="kfs.lunceneRoot" value="${kfs.lunceneRoot}" id="lunceneRoot" type="text"  class="formText"/>
				</p>
				<p><span>lucene初始化地址：</span>
						<input name="kfs.lunceneInitFile" value="${kfs.lunceneInitFile}" id="lunceneInitFile" type="text" class="formText"/>
				</p>
				<p><span>文件上传路径：</span>
						<input name="kfs.knowledgeUpload" value="${kfs.knowledgeUpload}" id="knowledgeUpload" type="text" class="formText"/>
				</p>
				
		</div>
		<div class="buttonArea">
    <input class="formButton_tab"  id="knowledgeFileSetSave" onclick="" type="submit" value="保 存"/>
    &nbsp; </div>
</div>
</body>
</html>
