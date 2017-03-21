<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String src = request.getParameter("src");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>新建工单</title>
<jsp:include page="../../../common/taglibs.jsp"/>
<script type="text/javascript">
	var basePath = '${ctx}';
</script>
<link href="${ctx}common/css/reset.css" rel="stylesheet" type="text/css" />
<link href="${ctx}common/plugins/form/skins/form_default.css" rel="stylesheet" type="text/css" />
<link href="${ctx}common/plugins/datatable/skins/datatable_default.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="${ctx}js/common/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="${ctx}/js/base.js"></script>
<script type="text/javascript"
	src="${ctx}common/plugins/dialog/artDialog.js?skin=default"></script>
<script type="text/javascript" src="${ctx}flat/plugins/dialog/iframeTools.js?version=${version}"></script>
<script type="text/javascript" src="${ctx}common/plugins/datatable/jquery.dataTables.min.js?version=${version}"></script>
<!--根据来电号码得到相关的工单 -->
<script type="text/javascript" src="${ctx}logined/workorderflow/js/getRelatedCustomerCallByPhone.js?version=${version}"></script>
</head>
<body>
<input type="hidden" id="from" value="history"/>
<input type="hidden" id="uphone" value="${param.phone}"/>
<div class="list">
     <table cellpadding="0" cellspacing="0"  class="pretty" id="myRelateTable">
          <thead>
                <tr>
                  <th class="num">序号</th>
                  <th style="width:130px;">受理时间</th>
                  <th style="width:115px;">工单编号</th>
                  <th style="width:100px;">业务类别</th>
                  <th style="width:100px;">工单类别</th>
                  <th style="width:100%;">工单内容</th>
                  <th style="width:100px">工单状态</th>
                  <th style="width:100px;">受理人员</th>
                  <th class="right_bdr0" style="width:70px;">操作</th>
                </tr>
          </thead>
          <tbody>
             
          </tbody>
      </table>
      
</div>   
</body>
</html>
