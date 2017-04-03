<%@ page language="java" import="java.util.*"  contentType="text/html; charset=UTF-8" pageEncoding="utf-8"  %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<jsp:include page="../../../common/taglibs.jsp"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<jsp:include page="../osmHead.jsp" />
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>阈值模板设置</title>
	<link href="${ctx}wh/plugins/datatable/skins/datatable_default.css?version=${version}" rel="stylesheet" type="text/css"/>
	<!-- 清除分页cookie start -->
	<script type="text/javascript" src="${ctx}js/common/jquery.cookie.js"></script>
	<!-- 清除分页cookie end -->
    <script type="text/javascript" src="${ctx}wh/js/thresholdTemplate/templateList.js?version=${version}"></script>
</head>
<body>
	<div class="bread-line">
        <label for="">位置：</label>
        <a href="#">安监在线</a>>
        <a href="#">设备管理</a>>
        <a href="#">阈值模板设置</a>
    </div>
	<div class="list">
		<div class="collect_search">
			<span class="add" id="add">添加模板</span>
            <span class="search" id="search">查询</span>
            <input type="text" class="search_inp" name="keyWord" placeholder="请输入模板名称" id="keyWord" />
            <span class="label">关键字：</span>
            <select name="watchType" class="select" id="watchType">
				<option value="">全部</option>
			</select>
            <span class="label">监测类型：</span>
        </div>
		<table id="myTable" cellpadding="0" cellspacing="0" class="pretty dataTable">
			<thead>
                 <tr>
                     <th style="width: 10%;">模板名称</th>
                     <th style="width: 10%;">监测类型</th>
                     <th style="width: 10%;">适用量程</th>
                     <th style="width:55%;">阈值设置</th>
                     <th style="width: 15%;">操作</th>
                 </tr>
            </thead>
		</table>
	</div>
	<div class="clear"></div>
</body>
</html>