<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>政策法规统计</title>
		<jsp:include page="../../wh/logined/head.jsp" />
		
		<link href="${ctx}wh/plugins/datatable/skins/datatable_default.css?version=${version}" rel="stylesheet" type="text/css"/>
		<script type="text/javascript" src="${ctx}js/logined/notify/ZCFGlist.js"></script>
	</head>
	<body>
		<input type="hidden" id="columnId" name="columnId" value="${param.id}"/>
		<div class="bread-line">
			<label>当前位置：</label><a href="#">首页</a>&gt;<a href="#">政策法规</a>&gt;&nbsp;政策法规统计
		</div>
		<div class="list">
			<div class="searchArea">
				<ul>
					<li><label>政策法规类型：</label><select id="notifyType"><option value="0">全部</option></select></li>
					<li><label>开始时间：</label>
						<input id="beginDate" type="text" class="formText Wdate" onclick="WdatePicker({maxDate: '#F{$dp.$D(\'endDate\')}' ,dateFmt:'yyyy-MM-dd HH:mm'})" />
					</li>
					<li><label>结束时间：</label>
						<input id="endDate" type="text" class="formText Wdate" onclick="WdatePicker({minDate: '#F{$dp.$D(\'beginDate\')}' ,dateFmt:'yyyy-MM-dd HH:mm'})" />
					</li>
					<li><input type="button" class="searchButton" id="search" value="查询" /></li>
				</ul>
			</div>
		
			<table cellpadding="0" cellspacing="0"  class="pretty dataTable" id="myTable">
				<thead>
					<tr>
						<th style="width:70px">序号</th>
						<th>政策法规类型</th>
						<th>发布次数</th>
					</tr>
				</thead>
			</table>
		</div>
	</body>
</html>