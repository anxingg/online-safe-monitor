<%@ page language="java" import="java.util.*"  contentType="text/html; charset=UTF-8" pageEncoding="utf-8"  %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../../../common/taglibs.jsp"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<jsp:include page="../head.jsp" />
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>工艺流程列表</title>
	<link href="${ctx}wh/plugins/datatable/skins/datatable_default.css?version=${version}" rel="stylesheet" type="text/css"/>
	<script type="text/javascript" src="${ctx}wh/js/process/processList.js"></script>
</head>
<body>
	<!-- 隐藏域：角色（1、政府； 2、企业） -->
	<input type="hidden" id="whroletype" value="<s:property value="#session.whroletype"/>" />
	<input type="hidden" id="groupId" value="<s:property value="#session.adminUser.groupId"/>" />
	<div class="bread-line">
		<label>当前位置：</label><a href="#">首页</a>&gt;<a href="#">企业管理</a>&gt;工艺流程
	</div>
	<div class="list">
		<div class="searchArea">
			<ul>
				<li <c:if test="${whroletype==2 }">style="display:none;"</c:if>><label>企业名称：</label><select id="company"><option value="">请选择</option></select></li>
				<li><label>工艺名称：</label><input type="text" class="formText" placeholder="" id="title"/></li>
				<li><input type="button" class="searchButton" value="查询" id="search"/>
					<div class="fButton greenBtn" <c:if test="${whroletype==1 || whroletype==3}">style="display:none;"</c:if>> <span class="add" id="add">新增</span></div>
				</li>
				<li></li>
			</ul>
		</div>
		<table id="myTable" cellpadding="0" cellspacing="0" class="pretty dataTable">
			<thead>
				<tr>
					<th id="no" style="width:80px;">序号</th>
					<th id="companyName" class="longTxt">企业名称</th>
					<th id="title" class="longTxt">工艺名称</th>
					<th id="createTime" style="width:80px;">创建时间</th>
					<th style="width:140px;">操作</th>
				</tr>
			</thead>
		</table>
	</div>
	<div class="clear"></div>
</body>
</html>