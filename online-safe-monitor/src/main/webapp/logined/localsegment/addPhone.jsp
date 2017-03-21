<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<jsp:include page="../../common/taglibs.jsp"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>查看日程</title>
<jsp:include page="../../common/head.jsp" />
<link href="${ctx}common/css/reset.css" rel="stylesheet" type="text/css" />
<link href="${ctx}common/plugins/form/skins/form_default.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx }plugins/datatable/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="${ctx }plugins/dialog/artDialog.js?skin=blue"></script>
<script type="text/javascript" src="${ctx }plugins/dialog/iframeTools.js"></script>
<script type="text/javascript" src="${ctx }js/common/artClose.js"></script>
<!-- 验证start -->
<script type="text/javascript" src="${ctx}js/common/showError.js"></script>
<!-- 验证end -->
<script type="text/javascript" src="${ctx}js/logined/localsegment/addPhone.js"></script>
</head>
<body class="bg_white">
<form id="menu_phoneadd">
	<div class="elasticFrame formPage">
	  <table width="100%" cellspacing="0" cellpadding="0" border="0" class="inputTable">
	    <tbody>
	      <tr>
	        <th><label>号段：</label></th>
	        <td><input type="text" id="phone_Add" class="formText" maxlength="7"/></td>
	      </tr>
	    </tbody>
	  </table>
	</div>
</form>
</body>
</html>