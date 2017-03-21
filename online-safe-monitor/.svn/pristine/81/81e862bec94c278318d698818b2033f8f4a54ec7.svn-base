<%@ page language="java" import="java.util.*"  contentType="text/html; charset=UTF-8" pageEncoding="utf-8"  %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../../../common/taglibs.jsp"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<jsp:include page="../head.jsp" />
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>成绩查询</title>
	<link href="${ctx}wh/plugins/datatable/skins/datatable_default.css?version=${version}" rel="stylesheet" type="text/css"/>
	<script type="text/javascript" src="${ctx}wh/js/exam/userExamScore.js"></script>
</head>
<body>
	<div class="bread-line">
		<s:if test="#session.whroletype==1">
			<label>当前位置：</label><a href="#">首页</a>&gt;<a href="#">安全教育培训</a>&gt;&nbsp;考试成绩
		</s:if>
		<s:else>
			<label>当前位置：</label><a href="#">首页</a>&gt;<a href="#">考试</a>&gt;&nbsp;考试成绩
		</s:else>
	</div>
	<div class="list">
		<div class="searchArea">
			<ul>
				<li><label>试卷名称：</label><input type="text"
					class="formText" placeholder="" id="title"/></li>
				<li><label>试卷类型：</label><select id="examType"><option value="">请选择</option></select></li>
				<li <c:if test="${whroletype==2 }">style="display:none;"</c:if>><label>企业名称：</label><select id="company"><option value="">请选择</option></select></li>
				<li <c:if test="${whroletype==2 }">style="display:none;"</c:if>><label>考试月份：</label><input id="examTime" type="text" class="formText Wdate" onclick="WdatePicker({skin:'default',dateFmt:'yyyy-MM'})"/></li>
				<li><input type="button" class="searchButton" value="查询" id="search"/>
					<div class="fButton greenBtn" id="xz"> <span class="export" id="exportScore">导出</span></div>
				</li>
			</ul>
		</div>
		<table id="myTable" cellpadding="0" cellspacing="0" class="pretty dataTable">
			<thead>
				<tr>
					<th id="no" style="width:90px;">序号</th>
					<th id="paperTitle" class="longTxt">试卷名称</th>
					<th style="width:100px;">操作</th>
					<th id="testType" style="width:130px">试卷类型</th>
					<th id="companyName" class="longTxt">企业名称</th>
					<c:if test="${whroletype==1 }"><th id="examTime" >考试月份</th></c:if>
					<th id="userName" style="width:100px;" class="longTxt">考试人员</th>
					<th id="score" style="width:100px;" class="data_r">成绩</th>
				</tr>
			</thead>
		</table>
		
	</div>
	<div class="clear"></div>
</body>
</html>