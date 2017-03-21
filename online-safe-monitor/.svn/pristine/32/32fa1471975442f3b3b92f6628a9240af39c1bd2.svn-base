<%@ page language="java" import="java.util.*"  contentType="text/html; charset=UTF-8" pageEncoding="utf-8"  %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../../../common/taglibs.jsp"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<jsp:include page="../head.jsp" />
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>企业端应急机构列表</title>
	<link href="${ctx}wh/plugins/datatable/skins/datatable_default.css?version=${version}" rel="stylesheet" type="text/css"/>
	<script type="text/javascript" src="${ctx}wh/js/emergencyDepartment/emergencyList.js"></script>
    
</head>
<body>
<input type="hidden" id="fromGroupId" value="${param.groupId }"/>
<input type="hidden" id="whroletype" value="${sessionScope.whroletype }"/>
<input type="hidden" id="groupId" value="${adminUser.groupId }"/>
	<div class="bread-line">
		<label>当前位置：</label><a href="#">首页</a>&gt;<a href="#">应急机构管理</a>&gt;&nbsp;应急机构列表
	</div>
	<div class="list">
		<div class="searchArea">
			<ul>
				<li><label>机构/人名称：</label><input type="text" class="formText" placeholder="" id="title"/></li>
				<li><input type="button" class="searchButton" value="查询" id="search"/>
				<c:if test="${whroletype==2 }"><div class="fButton greenBtn" id="add"> <span class="add" >新增</span></div></c:if>
				<c:if test="${whroletype==1||whroletype==3 }"><div class="fButton greenBtn" id="back"> <span class="back" >返回</span></div></c:if>
				</li>
				<li></li>
			</ul>
		</div>
		<table id="myTable" cellpadding="0" cellspacing="0" class="pretty dataTable">
			<thead>
				<tr>
					<th id="no" style="width:80px;">序号</th>
					<th id="companyName" class="longTxt">企业名称</th>
					<th id="title" class="longTxt">机构/人名称</th>
					<th id="phone" style="width:120px;">联系电话</th>
					<th id="groupNum" style="width:120px;">集团号码</th>
					<th id="job" class="longTxt">职务</th>
					<th style="width:120px;">操作</th>
				</tr>
			</thead>
		</table>
		
	</div>
	<div class="clear"></div>
</body>
</html>