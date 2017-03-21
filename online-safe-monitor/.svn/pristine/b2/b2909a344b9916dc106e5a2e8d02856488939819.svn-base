<%@ page language="java" import="java.util.*"  contentType="text/html; charset=UTF-8" pageEncoding="utf-8"  %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<jsp:include page="../../../common/taglibs.jsp"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<jsp:include page="../head.jsp" />
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>应急预案</title>
	<link href="${ctx}wh/plugins/datatable/skins/datatable_default.css?version=${version}" rel="stylesheet" type="text/css"/>
	<!-- 清除分页cookie start -->
	<script type="text/javascript" src="${ctx}js/common/jquery.cookie.js"></script>
	<!-- 清除分页cookie end -->
	<script type="text/javascript" src="${ctx}wh/js/safeAccident/downloadAttachment.js?version=${version}"></script>
    <script type="text/javascript" src="${ctx}wh/js/plans/plansList.js?version=${version}"></script>
</head>
<body>
	<input type="hidden" id="whroletype" value='<s:property value="#session.whroletype"/>'/>
	
	<input type="hidden" id="groupId" value="${param.groupId }"/>
	
	<input type="hidden" id="mygroupId" value='<s:property value="#session.adminUser.groupId"/>'/>
	
	
	<div class="bread-line">
		<label>当前位置：</label><a href="#">首页</a>&gt;<a href="#">应急预案</a>&gt;&nbsp;列表
	</div>
	<div class="list">
		<div class="searchArea">
			<ul>
				<li id="qymc"><label>企业名称：</label>
					<select class="" id="companName">
						<option value="-1">全部</option>
					</select>
				</li>
				<li><label>备案时间：</label>
					<input id="prepareTime" type="text" class="formText Wdate" 
             		onclick="WdatePicker({skin:'default',dateFmt:'yyyy-MM-dd'})" />
				</li>
				<li><label>备案到期时间：</label>
					<input id="prepareEndTime" type="text" class="formText Wdate" 
             		onclick="WdatePicker({skin:'default',dateFmt:'yyyy-MM-dd'})" />
				</li>
				<li><label>预案类型：</label>
					<select id="planType">
                    </select>					
				</li>
				<li><input type="button" class="searchButton" value="查询" id="search"/>
					<div class="fButton greenBtn" id="xz"> <span class="add" onclick="addPlans();">新增</span></div>
				</li>
				<li></li>
			</ul>
		</div>
		<table id="myTable" cellpadding="0" cellspacing="0" class="pretty dataTable">
			<thead>
				<tr>
					<th style="width:150px;">预案编号</th>
					<th>企业名称</th>
					<th>预案类型</th>
					<th>备案时间</th>
					<th>备案到期时间</th>
					<th>应急内容（文件）</th>
					<th style="width:115px;">操作</th>
				</tr>
			</thead>
		</table>
		
	</div>
	<div class="clear"></div>
</body>
</html>