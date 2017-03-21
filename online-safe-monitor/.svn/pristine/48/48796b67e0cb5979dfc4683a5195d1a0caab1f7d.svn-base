<%@ page language="java" import="java.util.*"  contentType="text/html; charset=UTF-8" pageEncoding="utf-8"  %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../../../common/taglibs.jsp"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<jsp:include page="../head.jsp" />
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>政府端救援物资列表</title>
	<link href="${ctx}wh/plugins/datatable/skins/datatable_default.css?version=${version}" rel="stylesheet" type="text/css"/>
	<script type="text/javascript" src="${ctx}wh/js/reliefGoods/reliefGoodsCompanyList.js"></script>
    
</head>
<body>
<input type="hidden" id="whroletype" value="${sessionScope.whroletype }">
	<div class="bread-line">
		<label>当前位置：</label><a href="#">首页</a>&gt;<a href="#">救援物资管理</a>&gt;&nbsp;救援物资列表
	</div>
	<div class="list">
		<div class="searchArea">
			<ul>
				<!-- <li><label>培训内容：</label><input type="text"
					class="formText" placeholder="" id="details"/></li> -->
				<li><label>企业名称：</label><select id="company"><option value="">请选择</option></select></li>
				<li><input type="button" class="searchButton" value="查询" id="search"/>
				</li>
				<li></li>
			</ul>
		</div>
		<table id="myTable" cellpadding="0" cellspacing="0" class="pretty dataTable">
			<thead>
				<tr>
					<th id="no" style="width:80px;">序号</th>
					<th id="companyName" class="longTxt">企业名称</th>
					<th id="title" class="longTxt">装备名称</th>
					<th id="titleType">所属类别</th>
					<th id="unit" style="width:80px;">单位</th>
					<th id="count" style="width:100px;">数量</th>
					<th id="place" class="longTxt">配备地点</th>
					<th id="keeper" style="width:120px;">保管人</th>
					<th id="phone" style="width:120px;">电话</th>
					<th style="width:120px;">操作</th>
				</tr>
			</thead>
		</table>
		
	</div>
	<div class="clear"></div>
</body>
</html>