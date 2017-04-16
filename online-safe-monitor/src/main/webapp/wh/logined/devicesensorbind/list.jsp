<%@ page language="java" import="java.util.*"  contentType="text/html; charset=UTF-8" pageEncoding="utf-8"  %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<jsp:include page="../../../common/taglibs.jsp"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<jsp:include page="../osmHead.jsp" />
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>通道列表</title>
	<link href="${ctx}wh/plugins/datatable/skins/datatable_default.css?version=${version}" rel="stylesheet" type="text/css"/>
	<!-- 清除分页cookie start -->
	<script type="text/javascript" src="${ctx}js/common/jquery.cookie.js"></script>
	<!-- 清除分页cookie end -->
    <script type="text/javascript" src="${ctx}wh/js/devicesensorbind/list.js?version=${version}"></script>
</head>
<body>
	<input type="hidden" id="collectDeviceId" value='${paramValues.collectDeviceId[0]}'/>
	<input type="hidden" id="groupId" value='${paramValues.groupId[0]}'/>
	<div class="list">
		<table id="myTable" cellpadding="0" cellspacing="0" class="pretty dataTable">
			<thead>
                 <tr>
                     <th style="width: 10%;">通道号</th>
                     <th style="width: 15%;">危险源名称</th>
                     <th style="width: 15%;">传感器名称</th>
                     <th style="width:15%;">模拟量程</th>
                     <th style="width:15%;">传感器量程</th>
                     <th style="width: 10%;">状态</th>
                     <th style="width: 20%;">操作</th>
                 </tr>
            </thead>
		</table>
	</div>
	<div class="clear"></div>
</body>
</html>