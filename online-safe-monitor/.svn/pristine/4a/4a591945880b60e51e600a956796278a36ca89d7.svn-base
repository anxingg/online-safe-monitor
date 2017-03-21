<%@ page language="java" import="java.util.*"  contentType="text/html; charset=UTF-8" pageEncoding="utf-8"  %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<jsp:include page="../../../common/taglibs.jsp"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<jsp:include page="../head.jsp" />
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>试卷列表</title>
	<link href="${ctx}wh/plugins/datatable/skins/datatable_default.css?version=${version}" rel="stylesheet" type="text/css"/>
	<script type="text/javascript" src="${ctx}wh/js/exam/examPaperList.js"></script>
</head>
<body>
	<div class="bread-line">
		<label>当前位置：</label><a href="#">首页</a>&gt;<a href="#">试卷管理</a>&gt;&nbsp;试卷管理列表
	</div>
	<div class="list">
		<div class="searchArea">
			<ul>
				<li><label>试卷名称：</label><input type="text"
					class="formText" placeholder="" id="papertitle"/></li>
				<li><label>试卷类型：</label><select id="examType"></select></li>
				<li><input type="button" class="searchButton" value="查询" id="search"/>
					<div class="fButton greenBtn" id="xz"> <span class="add" id="addPaper">新增</span></div>
				</li>
				<li></li>
			</ul>
		</div>
		<table id="myTable" cellpadding="0" cellspacing="0" class="pretty dataTable">
			<thead>
				<tr>
					<th id="no" style="width:70px;">序号</th>
					<th id="title" class="longTxt">试卷名称</th>
					<th id="titleType" style="width:130px">试卷类型</th>
					<th id="questionNum" style="width:100px" class="data_r">题目数量</th>
					<th style="width:100px">是否生效</th>
					<th style="width:150px">操作</th>
				</tr>
			</thead>
		</table>
		
	</div>
	<div class="clear"></div>
</body>
</html>