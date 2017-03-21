<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String src = request.getParameter("src");
%>
<jsp:include page="../../../common/taglibs.jsp"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>用户资料-弹窗</title>
<link href="${ctx}common/css/reset.css" rel="stylesheet" type="text/css" />
<link href="${ctx}common/plugins/datatable/skins/datatable_default.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
	var basePath = '${ctx}';
</script>
<script type="text/javascript" src="${ctx}js/common/jquery-1.8.0.min.js"></script>
<script type="text/javascript"
	src="${ctx}common/plugins/dialog/artDialog.js?skin=default"></script>
<script type="text/javascript" src="${ctx}flat/plugins/dialog/iframeTools.js?version=${version}"></script>
<!--获得crms -->
<script type="text/javascript" src="${ctx}logined/workorderflow/js/selectCrms.js?version=${version}"></script>
</head>
<body class="bg_white">
<div class="elasticFrame formPage">
  <table cellpadding="0" cellspacing="0"  class="pretty dataTable" id="listTable">
    <thead>
      <tr>
        <th class="chk"></th>
        <th>姓名</th>
        <th>性别</th>
        <th class="right_bdr0">客户类别</th>
      </tr>
    </thead>
    <tbody >
      
    </tbody>
  </table>
</div>
</body>
</html>
