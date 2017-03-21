<%@ page language="java" import="java.util.*"  contentType="text/html; charset=UTF-8" pageEncoding="utf-8"  %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../../../common/taglibs.jsp"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<jsp:include page="../head.jsp" />
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>岗前教育列表</title>
	<link href="${ctx}wh/plugins/datatable/skins/datatable_default.css?version=${version}" rel="stylesheet" type="text/css"/>
	<script type="text/javascript" src="${ctx}wh/js/training/perserviceTrainingList.js"></script>
</head>
<body>
	<!-- 隐藏域：角色（1、政府； 2、企业） -->
	<input type="hidden" id="whroletype" value="<s:property value="#session.whroletype"/>" />
	<div class="bread-line">
		<label>当前位置：</label><a href="#">首页</a>&gt;<a href="#">安全教育培训</a>&gt;岗前三级教育
	</div>
	<div class="list">
		<div class="searchArea">
			<ul>
				<li <c:if test="${whroletype==2 }">style="display:none;"</c:if>><label>企业名称：</label><select id="company"><option value="">请选择</option></select></li>
				<li><label>姓名：</label><input type="text" class="formText" placeholder="" id="userName"/></li>
				<li><label>性别：</label><select id="sex"><option value="">请选择</option><option value="1">男</option><option value="0">女</option></select></li>
				<li><label>批准人：</label><input type="text" class="formText" placeholder="" id="checker"/></li>
				<li><input type="button" class="searchButton" value="查询" id="search"/>
					<div class="fButton greenBtn" id="xz" <c:if test="${whroletype==1 || whroletype==3}">style="display:none;"</c:if>> <span class="add" id="add">新增</span></div>
				</li>
				<li></li>
			</ul>
		</div>
		<table id="myTable" cellpadding="0" cellspacing="0" class="pretty dataTable">
			<thead>
				<tr>
					<th id="no" style="width:80px;">序号</th>
					<th id="userName" class="longTxt">姓名</th>
					<c:if test="${whroletype==1 || whroletype==3}"><th id="companyName" class="longTxt">企业名称</th></c:if>
					<th id="sex" style="width:80px;">性别</th>
					<th id="age" style="width:80px;">年龄</th>
					<th id="workType" class="longTxt">工种</th>
					<th id="post" class="longTxt">岗位</th>
					<th id="oneScore" style="width:110px;">成绩（企业级）</th>
					<!-- <th id="address" class="longTxt">地址</th>
					<th id="ownerattitude" class="longTxt">个人态度</th>
					<th id="checkerattitude" class="longTxt">准上岗人意见</th> -->
					<th id="checker" class="longTxt"	>批准人</th>
					<th style="width:140px;">操作</th>
				</tr>
			</thead>
		</table>
	</div>
	<div class="clear"></div>
</body>
</html>