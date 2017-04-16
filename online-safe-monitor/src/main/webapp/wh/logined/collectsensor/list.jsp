<%@ page language="java" import="java.util.*"  contentType="text/html; charset=UTF-8" pageEncoding="utf-8"  %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<jsp:include page="../../../common/taglibs.jsp"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<jsp:include page="../osmHead.jsp" />
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>传感器统计</title>
	<link href="${ctx}wh/plugins/datatable/skins/datatable_default.css?version=${version}" rel="stylesheet" type="text/css"/>
	<!-- 清除分页cookie start -->
	<script type="text/javascript" src="${ctx}js/common/jquery.cookie.js"></script>
	<!-- 清除分页cookie end -->
    <script type="text/javascript" src="${ctx}wh/js/collectsensor/list.js?version=${version}"></script>
</head>
<body>
	<input type="hidden" id="dangerSourceId" value='${paramValues.dangerSourceId[0]}'/>
	<div class="list">
		<div class="collect_search">
			<span class="add" id="add">添加传感器</span>
			<span class="add" id="add_batch" style="margin-left:15px">批量添加</span>
        </div>
		<table id="myTable" cellpadding="0" cellspacing="0" class="pretty dataTable">
			<thead>
                 <tr>
                     <th style="width: 10%;">名称</th>
                     <th style="width: 10%;">监测对象</th>
                     <th style="width: 10%;">检测类型</th>
                     <th style="width:45%;">阈值设置</th>
                     <th style="width: 10%;">状态</th>
                     <th style="width: 15%;">操作</th>
                 </tr>
            </thead>
		</table>
	</div>
	<div class="clear"></div>
</body>
</html>