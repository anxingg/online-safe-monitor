<%@ page language="java" import="java.util.*"  contentType="text/html; charset=UTF-8" pageEncoding="utf-8"  %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<jsp:include page="../../../common/taglibs.jsp"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<jsp:include page="../head.jsp" />
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>企业产品列表</title>
	<link href="${ctx}wh/plugins/datatable/skins/datatable_default.css?version=${version}" rel="stylesheet" type="text/css"/>
	<!-- 清除分页cookie start -->
	<script type="text/javascript" src="${ctx}js/common/jquery.cookie.js"></script>
	<!-- 清除分页cookie end -->
    <script type="text/javascript" src="${ctx}wh/js/companyProduct/companyProductList.js?version=${version}"></script>
</head>
<body>
	<input type="hidden" id="whroletype" value='<s:property value="#session.whroletype"/>'/>
	<input type="hidden" id="groupId" value="${param.groupId }"/>
	<input type="hidden" id="mygroupId" value='<s:property value="#session.adminUser.groupId"/>'/>
	<div class="bread-line">
		<label>当前位置：</label><a href="#">首页</a>&gt;<a href="#">企业管理</a>&gt;&nbsp;企业产品列表
	</div>
	<div class="list">
		<div class="searchArea">
			<ul>
				<li id="qymc"><label>企业名称：</label>
					<select class="" id="companName">
						<option value="-1">全部</option>
					</select>
				</li>
				<li><label>材料种类：</label>
					<select id="materialType">
						<option value="">请选择</option>
						<option value="1">原材料</option>
                        <option value="2">产品</option>
					</select>
				</li>
				<li><label>物质名称：</label><input type="text"
					class="formText" placeholder="" id="materialName"/></li>
				<li><input type="button" class="searchButton" value="查询" id="search"/>
					<div class="fButton greenBtn" id="xz"> <span class="add" onclick="javascript:window.location.href='addCompanyProduct.jsp'" >新增</span></div>
				</li>
				<li></li>
			</ul>
		</div>
		<table id="myTable" cellpadding="0" cellspacing="0" class="pretty dataTable">
			<thead>
				<tr>
					<th style="width:70px;">序号</th>
					<th>企业名称</th>
					<th style="width:90px;">类别</th>
					<th>物质名称</th>
					<th style="width:90px;">年设计产量（吨）</th>
					<th style="width:90px;">月设计产量（吨）</th>
					<th style="width:90px;">年设计消耗量（吨）</th>
					<th style="width:90px;">月设计消耗量（吨）</th>
					<th>备注</th>
					<th style="width:125px;">操作</th>
				</tr>
			</thead>
		</table>
		
	</div>
	<div class="clear"></div>
</body>
</html>