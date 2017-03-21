<%@ page language="java" import="java.util.*"  contentType="text/html; charset=UTF-8" pageEncoding="utf-8"  %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<jsp:include page="../../../common/taglibs.jsp"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<jsp:include page="../head.jsp" />
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>安全管理人员列表</title>
	<link href="${ctx}wh/plugins/datatable/skins/datatable_default.css?version=${version}" rel="stylesheet" type="text/css"/>
	<!-- 清除分页cookie start -->
	<script type="text/javascript" src="${ctx}js/common/jquery.cookie.js"></script>
	<!-- 清除分页cookie end --> 
	<script type="text/javascript" src="${ctx}wh/js/safetyManagePerson/safetyManagePersonList.js?version=${version}"></script>
</head>
<body>
	<input type="hidden" id="whroletype" value='<s:property value="#session.whroletype"/>'/>
	<input type="hidden" id="groupId" value="${param.groupId }"/>
	<input type="hidden" id="mygroupId" value='<s:property value="#session.adminUser.groupId"/>'/>
	<div class="bread-line">
		<label>当前位置：</label><a href="#">首页</a>&gt;<a href="#">企业管理</a>&gt;&nbsp;安全管理人员列表
	</div>
	<div class="list">
		<div class="searchArea">
			<ul>
				<li id="qymc"><label>企业名称：</label>
					<select class="" id="companName">
						<option value="-1">全部</option>
					</select>
				</li>
				<li><label>编号：</label><input type="text"
					class="formText" id="code" onkeyup="this.value=this.value.replace(/[^0-9]/g,'')" maxlength="20" style="width: 100px;"/></li>
				<li><label>联系方式：</label><input type="text"
					class="formText" id="phone" style="width: 100px;"/></li>
				<li><label>姓名：</label><input type="text"
					class="formText" id="name" style="width: 100px;"/></li>
				<li><label>证件到期日期：</label>
					<input id="minTime" type="text" class="formText Wdate" style="width: 120px;"
             		onclick="WdatePicker({skin:'default',dateFmt:'yyyy-MM-dd',maxDate :'#F{$dp.$D(\'maxTime\')}'})" />
				-
					<input id="maxTime" type="text" class="formText Wdate" style="width: 120px;"
             		onclick="WdatePicker({skin:'default',dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'minTime\')}'})" />
				</li>
				<li><input type="button" class="searchButton" value="查询" id="search"/>
					<div class="fButton greenBtn" id="xz"> <span class="add" onclick="addPerson();">新增</span></div>
				</li>
				<li></li>
			</ul>
		</div>
		<table id="myTable" cellpadding="0" cellspacing="0" class="pretty dataTable">
			<thead>
				<tr>
					<!--  <th style="width:90px;">序号</th>
					<th>公司名称</th>
					<th style="width:130px">姓名</th>
					<th>联系方式</th>
					<th id="hzsj">换证时间</th>
					<th>职务</th>
					<th id="zc">职称</th>
					<th id="cz">操作</th>  -->
				</tr>
			</thead>
		</table>
		
	</div>
	<div class="clear"></div>
</body>
</html>