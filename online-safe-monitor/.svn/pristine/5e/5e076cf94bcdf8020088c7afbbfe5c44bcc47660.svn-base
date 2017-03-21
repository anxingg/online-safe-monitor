<%@ page language="java" import="java.util.*"  contentType="text/html; charset=UTF-8" pageEncoding="utf-8"  %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<% String type = request.getParameter("type");
	request.setAttribute("type",type);
 %>
<jsp:include page="../../../common/taglibs.jsp"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<jsp:include page="../head.jsp" />
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>安全生产专家</title>
	<link href="${ctx}wh/plugins/datatable/skins/datatable_default.css?version=${version}" rel="stylesheet" type="text/css"/>
	<!-- 清除分页cookie start -->
	<script type="text/javascript" src="${ctx}js/common/jquery.cookie.js"></script>
	<!-- 清除分页cookie end -->
	<script type="text/javascript" src="${ctx}wh/js/professor/professorList.js?version=${version}"></script>
</head>
<body>
	<input type="hidden" id="whroletype" value='<s:property value="#session.whroletype"/>'/>
	
	<div class="bread-line">
		<input type="hidden" id="type" value="${param.type }"/>
		<s:if test="#request.type==1">
		<label>当前位置：</label><a href="#">首页</a>&gt;<a href="#">职业卫生专家</a>&gt;&nbsp;列表
		</s:if>
		<s:elseif test="#request.type==2">
		<label>当前位置：</label><a href="#">首页</a>&gt;<a href="#">非煤矿山专家</a>&gt;&nbsp;列表
		</s:elseif>
		<s:elseif test="#request.type==3">
		<label>当前位置：</label><a href="#">首页</a>&gt;<a href="#">危险化学品专家</a>&gt;&nbsp;列表
		</s:elseif>
	</div>
	<div class="list">
		<div class="searchArea">
			<ul>
				<li><label>姓名：</label>
					<input id="name" type="text" class="formText" />
				</li>
				<li><label>专业特长：</label>
					<input id="specialties" type="text" class="formText" />
				</li>
				<li><input type="button" class="searchButton" value="查询" id="search"/>
					<div class="fButton greenBtn" id="xz"> <span class="add" onclick="addProfessor();">新增</span></div>
				</li>
				<li></li>
			</ul>
		</div>
		<table id="myTable" cellpadding="0" cellspacing="0" class="pretty dataTable">
			<thead>
				<tr>
					<th style="width:90px;">序号</th>
					<th>姓名</th>
					<th>专业特长</th>
					<th>技术职称</th>
					<th>工作单位</th>
					<th>联系电话</th>
					<th>备注</th>
					<th style="width:115px;">操作</th>
				</tr>
			</thead>
		</table>
		
	</div>
	<div class="clear"></div>
</body>
</html>