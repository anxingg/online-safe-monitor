<%@ page language="java" import="java.util.*"  contentType="text/html; charset=UTF-8" pageEncoding="utf-8"  %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<jsp:include page="../../../common/taglibs.jsp"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<jsp:include page="../../../common/flatHead.jsp" />
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>试题列表</title>
	<link rel="stylesheet" type="text/css" href="${ctx}wh/jquery-easyui-1.4.3/themes/default/easyui.css"/>
	<link rel="stylesheet" type="text/css" href="${ctx}wh/jquery-easyui-1.4.3/themes/icon.css"/>
	<link rel="stylesheet" type="text/css" href="${ctx}wh/css/style.css"/>
	<link href="${ctx}wh/plugins/datatable/skins/datatable_default.css" rel="stylesheet" type="text/css"/>
	<script type="text/javascript">
		var basePath = "${ctx}";
	</script>
	<script type="text/javascript" src="${ctx}wh/jquery-easyui-1.4.3/jquery.min.js"></script>
	<script type="text/javascript" src="${ctx}wh/jquery-easyui-1.4.3/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="${ctx}wh/plugins/datatable/jquery.dataTables.min.js"></script>
	<script type="text/javascript" src="${ctx}wh/js/exam/examQuestionList.js"></script>
    
</head>
<body>
	<input type="hidden" id="whroletype" value='<s:property value="#session.whroletype"/>'/>
	<input type="hidden" id="group_id" value='<s:property value="#session.adminUser.groupId"/>'/>
	<div class="bread-line">
		<label>当前位置：</label><a href="#">首页</a>&gt;<a href="#">试题管理</a>&gt;&nbsp;试题管理列表
	</div>
	<div class="list">
		<div class="searchArea">
			<ul>
				<li><label>试题名称：</label><input type="text"
					class="formText" placeholder="" id="title"/></li>
				<li><label>试题类型：</label><select id="examType"></select></li>
				<li><input type="button" class="searchButton" value="查询" id="search"/>
					<div class="fButton greenBtn" id="xz"> <span class="add" onclick="addExamQuestion()">新增</span></div>
				</li>
				<li></li>
			</ul>
		</div>
		<table id="myTable" cellpadding="0" cellspacing="0" class="pretty dataTable">
			<thead>
				<tr>
					<th style="width:70px;">序号</th>
					<th>试题名称</th>
					<th style="width:130px">试题类型</th>
					<th>题目类型</th>
					<th>是否生效</th>
					<th id="cz">操作</th>
				</tr>
			</thead>
		</table>
		
	</div>
	<div class="clear"></div>
</body>
</html>