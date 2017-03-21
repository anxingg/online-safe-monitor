<%@ page language="java" import="java.util.*"  contentType="text/html; charset=UTF-8" pageEncoding="utf-8"  %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<jsp:include page="../../../common/taglibs.jsp"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<jsp:include page="../head.jsp" />
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>危险化学品目录</title>
	<link href="${ctx}wh/plugins/datatable/skins/datatable_default.css?version=${version}" rel="stylesheet" type="text/css"/>
	<script type="text/javascript">
		var basePath = "${ctx}";
	</script>
	<!-- 清除分页cookie start -->
	<script type="text/javascript" src="${ctx}js/common/jquery.cookie.js"></script>
	<!-- 清除分页cookie end -->
	<script type="text/javascript" src="${ctx}wh/js/dangerChemicals/chemicalsDirectoryList.js"></script>
    
</head>
<body>
	<!-- 隐藏域：角色 -->
	<input type="hidden" id="whroletype" value='<s:property value="#session.whroletype"/>' />
	<!-- 隐藏域：企业ID -->
	<input type="hidden" id="group_id" value='<s:property value="#session.adminUser.groupId"/>' />
	
	<div class="bread-line">
		<label>当前位置：</label><a href="#">危险化学品目录</a>&gt;&nbsp;危险化学品目录列表
	</div>
	<div class="list">
		<div class="searchArea">
			<ul>
				<li><label>编号：</label><input type="text" class="formText" id="code" maxlength="32" /></li>
				<li><label>品名：</label><input type="text" class="formText" id="materialName" maxlength="32" /></li>
				<li><input type="button" class="searchButton"  value="查询" /><s:if test="#session.whroletype == 1"><div class="fButton greenBtn"> <span class="add" onclick="addOne();">新增</span></div></s:if></li>
			</ul>
		</div>
		<table cellpadding="0" cellspacing="0" class="pretty dataTable" id="myTable">
			<thead>
				<tr>
					<th style="width:180px;">编号</th>
					<th>品名</th>
					<th>别名</th>
					<th>CAS号</th>
					<th>备注</th>
					<th style="width:115px;">操作</th>
				</tr>
			</thead>
			<tbody>
			</tbody>
		</table>
	</div>
	<div class="clear"></div>

</body>
</html>
