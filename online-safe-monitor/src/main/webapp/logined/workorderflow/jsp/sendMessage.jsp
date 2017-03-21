<%@page import="java.net.URLDecoder"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<% 
	String moduleName=request.getParameter("moduleName")==null?"":request.getParameter("moduleName").toString();
   	moduleName= URLDecoder.decode(moduleName,"UTF-8"); 
%>
<jsp:include page="../../../common/taglibs.jsp" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>发送短信</title>
<jsp:include page="../../../common/head.jsp" />
<link href="${ctx}common/css/reset.css" rel="stylesheet" type="text/css" />
<link href="${ctx}common/plugins/form/skins/form_default.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="${ctx}common/plugins/dialog/artDialog.js?skin=default"></script>
<script type="text/javascript" src="${ctx}common/plugins/dialog/iframeTools.js"></script>
<!-- 验证start -->
<script type="text/javascript" src="${ctx}js/common/showError.js"></script>
<!-- 验证end -->

<script type="text/javascript" src="${ctx}logined/workorderflow/js/sendMessage.js?version=${version}"></script>

<style type="text/css">
.inputTable th{width:80px;}
.readOnlyText{border:1px solid #e4e4e4;line-height:26px;color:#c2c2c2;height:26px; text-indent:10px;background:#fafafa;vertical-align:middle;}
</style>
</head>
<body style="background:#fff">
<!-- 模块名称 -->
<input type="hidden" id="moduleName" value="<%=moduleName%>"/>
<form id="sendMessage">
	<div class="content_form">
	      <table style="width:550px;padding-top:20px;" border="0" cellpadding="0" cellspacing="0"  class="inputTable">
	      	<tr>
	      		<th><label>接收手机：</label></th>
	      		<td>
	      			<input style="width:432px" class="readOnlyText" type="text" id="sendPhone" value="${param.sendPhone }" readonly="readonly"/>
	      		</td>
	      	</tr>
	        <tr>
	          <th><label>短信内容：</label></th>
	          <td>
	          	<textarea class="formTextarea" style="height:150px;" id="contentInfo"
	          	 valid="required|textareaLength" errmsg="sendmessage.content_not_null|sendmessage.content_max_length"
	          	 onkeyup="checkTextarea(this)" onmouseup="checkTextarea(this)" maxNumber="300"></textarea>
	            <span style="float:right">1-300字</span>
	          </td>
	        </tr>
	      </table>
		</div>
</form>
</body>
</html>
