<%@ page language="java" import="java.util.*"  contentType="text/html; charset=UTF-8" pageEncoding="utf-8"  %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../../../common/taglibs.jsp"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<jsp:include page="../head.jsp" />
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>年度培训列表</title>
	<link href="${ctx}wh/plugins/datatable/skins/datatable_default.css?version=${version}" rel="stylesheet" type="text/css"/>
	<script type="text/javascript" src="${ctx}wh/js/training/trainingList.js"></script>
    
</head>
<body>
<input type="hidden" id="whroletype" value="${sessionScope.whroletype }">
	<div class="bread-line">
		<label>当前位置：</label><a href="#">首页</a>&gt;<a href="#">年度培训管理</a>&gt;&nbsp;年度培训列表
	</div>
	<div class="list">
		<div class="searchArea">
			<ul>
				<!-- <li><label>培训内容：</label><input type="text"
					class="formText" placeholder="" id="details"/></li> -->
				<li><label>负责人：</label><input type="text"
					class="formText" placeholder="" id="charge"/></li>
						<li <c:if test="${whroletype==2 }">style="display:none;"</c:if>><label>企业名称：</label><select id="company"><option value="">请选择</option></select></li>
				<li><label>年度</label>
				<input id="trainYear" type="text" style="width: 80px" 
							class="formText Wdate"
							onfocus="WdatePicker({skin:'default',dateFmt:'yyyy'})" /></td>
				</li>
				<li><input type="button" class="searchButton" value="查询" id="search"/>
				<c:if test="${whroletype==2 }"><div class="fButton greenBtn" id="xz"> <span class="add" onclick="addTraining()">新增</span></div></c:if>
					
				</li>
				<li></li>
			</ul>
		</div>
		<table id="myTable" cellpadding="0" cellspacing="0" class="pretty dataTable">
			<thead>
				<tr>
					<th style="width:70px;">序号</th>
					<c:if test="${whroletype==1 || whroletype==3}"><th>企业名称</th></c:if>
					<!-- <th>培训内容</th> -->
					<th>负责人</th>
					<th>培训时间</th>
					<th>培训对象</th>
					<th>培训形式</th>
					<th>年度</th>
					<th>备注</th>
					<th>操作</th>
				</tr>
			</thead>
		</table>
		
	</div>
	<div class="clear"></div>
</body>
</html>