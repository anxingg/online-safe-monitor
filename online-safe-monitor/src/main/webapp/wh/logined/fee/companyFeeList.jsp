<%@ page language="java" import="java.util.*"  contentType="text/html; charset=UTF-8" pageEncoding="utf-8"  %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<jsp:include page="../../../common/taglibs.jsp"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<jsp:include page="../head.jsp" />
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>安全管理人员列表</title>
	<!-- 清除分页cookie start -->
	<script type="text/javascript" src="${ctx}js/common/jquery.cookie.js"></script>
	<!-- 清除分页cookie end -->
	<script type="text/javascript" src="${ctx}wh/js/fee/companyFeeList.js?version=${version}"></script>
</head>
<body>

	<input type="hidden" id="whroletype" value='<s:property value="#session.whroletype"/>'/>
	<input type="hidden" id="group_id" value='<s:property value="#session.adminUser.groupId"/>'/>
	<div class="bread-line">
		<label>当前位置：</label><a href="#">首页</a>&gt;<a href="#">安全生产投入管理</a>&gt;&nbsp;企业安全生产统计
	</div>
	<div class="list">
		<div class="searchArea">
			<ul>
				<li><label>企业名称：</label>
					<select class="" id="companName">
						<option value="-1">全部</option>
					</select>
				</li>
				<li><input type="button" class="searchButton" value="查询" id="searchCompany"/>
				</li>
				<li></li>
			</ul>
		</div>
		<table id="myTable" cellpadding="0" cellspacing="0" class="pretty dataTable">
			<thead>
				<tr>
					<th style="width:60px;" id="no">序号</th>
					<th id="companyName" class="longTxt">企业名称</th>
					<th style="width:150px;" id="allMoney">已提取金额（万元）</th>
					<th style="width:150px;" id="usedMoney">已使用金额（万元）</th>
					<th style="width:150px;" id="remainMoney">余额（万元）</th>
					<th style="width:120px;">操作</th>
				</tr>
			</thead>
			<tbody></tbody>
		</table>
		
	</div>
	<div class="clear"></div>

</body>
</html>
