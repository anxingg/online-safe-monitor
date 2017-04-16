<%@ page language="java" import="java.util.*"  contentType="text/html; charset=UTF-8" pageEncoding="utf-8"  %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<jsp:include page="../../../common/taglibs.jsp"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<jsp:include page="../osmHead.jsp" />
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>危险源列表</title>
	<link href="${ctx}wh/plugins/datatable/skins/datatable_default.css?version=${version}" rel="stylesheet" type="text/css"/>
	<!-- 清除分页cookie start -->
	<script type="text/javascript" src="${ctx}js/common/jquery.cookie.js"></script>
	<!-- 清除分页cookie end -->
    <script type="text/javascript" src="${ctx}wh/js/dangersource/dangersourceList.js?version=${version}"></script>
</head>
<body>
	<div class="bread-line">
        <label for="">位置：</label>
        <a href="#">安监在线</a>&gt;
        <a href="#">危险源管理</a>&gt;
        <a href="#">危险源列表</a>
    </div>
	<div class="list">
		<div class="collect_search">
			<span class="add" id="add">添加危险源</span>
            <span class="search" id="search">查询</span>
            <input type="text" class="search_inp" name="keyword" placeholder="请输入危险源名称、企业名称" id="keyword" />
            <span class="label">关键字：</span>
            <select name="level" class="select" id="level">
				<option value="-1">全部</option>
				<option value="1">一级</option>
				<option value="2">二级</option>
				<option value="3">三级</option>
				<option value="4">四级</option>
				<option value="10">其他</option>
			</select>
            <span class="label">危险源级别：</span>
        </div>
		<table id="myTable" cellpadding="0" cellspacing="0" class="pretty dataTable">
			<thead>
                 <tr>
                     <th style="width: 20%;">危险源名称</th>
                     <th style="width: 10%;">县区</th>
                     <th style="width: 15%;">所属企业</th>
                     <th style="width: 10%;">危险源级别</th>
                     <th style="width: 15%;">评价机构</th>
                     <th style="width: 10%;">最新评价时间</th>
                     <th style="width: 20%;">操作</th>
                 </tr>
            </thead>
		</table>
	</div>
	<div class="clear"></div>
</body>
</html>