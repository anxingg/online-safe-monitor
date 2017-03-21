<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<% String id = request.getParameter("id");
	request.setAttribute("id",id);
 %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>公告查看</title>
<jsp:include page="../../wh/logined/head.jsp" />

<%-- <script type="text/javascript" src="${ctx}plugins/datatable/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="${ctx}js/common/jquery.cookie.js"></script>
<script language="javascript" type="text/javascript" src="${ctx}plugins/My97DatePicker/WdatePicker.js"></script> --%>
<script type="text/javascript" src="${ctx}js/logined/notify/viewList.js"></script>
<script type="text/javascript" src="${ctx}js/common/placeholder.js"></script>
<%-- <link href="${ctx}/flat/css/reset.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/flat/plugins/datatable/skins/datatable_page.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/flat/plugins/datatable/skins/datatable_default.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="${ctx}wh/css/style.css?version=${version}"/> --%>
<link href="${ctx}wh/plugins/datatable/skins/datatable_default.css?version=${version}" rel="stylesheet" type="text/css"/>
</head>
<body>
<div class="bread-line">
		<s:if test="#request.id==1">
		<label>当前位置：</label><a href="#">首页</a>&gt;<a href="#">公告管理</a>&gt;&nbsp;公告列表
		</s:if>
		<s:elseif test="#request.id==2">
		<label>当前位置：</label><a href="#">首页</a>&gt;<a href="#">政策法规</a>&gt;&nbsp;政策法规列表
		</s:elseif>
		

</div>
	<div class="list">
	<input type="hidden" id="columnId" name="columnId" value="${id}"/>
	<div class="searchArea">
		<ul>
			<li><label>类型：</label><select id="notifyType"><option value="0">全部</option></select></li>
			<li><label>关键字：</label><input type="text" id="subject" placeholder="标题" class="formText searchkey" maxlength="50" /></li>
			<li><input hideFocus="" value="查询" class="searchButton" type="button" id="searchButton" /></li>
		</ul>
	</div>
<%-- 	<div class="searchArea">
			<table cellspacing="0" cellpadding="0">
			<tbody>
			   <tr>
			    <td style="width: 20%"></td>
				<td class="right" style="width: 80%">
				  <label>类型：</label>
				  <select id="notifyType">
				  	<option value="0">全部</option>
                  </select>
				<label>关键字：</label>
				<span style="position:relative;">
				<input type="text" id="subject" placeholder="标题" class="formText searchkey" maxlength="50" />
				</span>
				<input hideFocus="" value="查询" class="searchButton" type="button" id="searchButton" />                               
				</td>
			</tr>
			</tbody>
		</table>
	</div> --%>
	<table cellpadding="0" cellspacing="0"  class="pretty dataTable" id="myTable">
		<thead>
		<tr>
       	 	<th>标题</th>
        	<th style="width:100px;">类型</th>
        	<th style="width:90px;">发布人</th>
       		<th style="width:150px;">发布时间</th>
        	<th style="width:90px;" class="right_bdr0">浏览次数</th>
     	 </tr>
		</thead>
	</table>
</div>
<script>funPlaceholder(document.getElementById("subject"));</script>
</body>
</html>
