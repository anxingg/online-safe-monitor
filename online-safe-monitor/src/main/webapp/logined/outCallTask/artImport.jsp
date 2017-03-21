<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<jsp:include page="../../common/taglibs.jsp" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>导入</title>
<jsp:include page="../../common/flatHead.jsp" />
<link href="${ctx}flat/css/reset.css" rel="stylesheet" type="text/css" />
<link href="${ctx}flat/css/systemManagement.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx}js/common/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="${ctx}common/plugins/dialog/artDialog.js?skin=default"></script>
<script type="text/javascript" src="${ctx}flat/plugins/dialog/iframeTools.js?version=${version}"></script>
<script type="text/javascript" src="${ctx}js/placeholder.js?version=${version}"></script>
<script type="text/javascript" src="${ctx}js/common/ajaxfileupload.js?version=${version}"></script>
<script type="text/javascript" src="${ctx}js/logined/outCallTask/artImport.js?version=${version}"></script>
<style type="text/css">
.inputTable th{width:100px;}
.tip{padding:0;}
</style>
</head>
<body class="bg_white">
<div class="importBox">
    <h4>选择导入文件：<input type="file" name="fileToUpload" id="fileToUpload" /></h4>
    <p style="text-align:left;padding-left:140px"><span id="msg"></span></p>
    <p style="text-align:left;padding-left:140px"><span>暂时只支持导入Excel格式文件，后缀名为.xls</span><a href="${ctx}down/phoneTaskUser.xls" id="importModule" class="ml10">获取模板？</a></p>
</div>
</body>

</html>
