<%@ page language="java" import="java.util.*"  contentType="text/html; charset=UTF-8" pageEncoding="utf-8"  %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<jsp:include page="../../../common/taglibs.jsp"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<jsp:include page="../osmHead.jsp" />
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>预警记录</title>
	<link href="${ctx}wh/plugins/datatable/skins/datatable_default.css?version=${version}" rel="stylesheet" type="text/css"/>
	<!-- 清除分页cookie start -->
	<script type="text/javascript" src="${ctx}js/common/jquery.cookie.js"></script>
	<!-- 清除分页cookie end -->
    <script type="text/javascript" src="${ctx}wh/js/thresholdTemplate/templateList.js?version=${version}"></script>
    <script type="text/javascript" src="${ctx}wh/js/earlywarning/earlyWarningList.js?version=${version}"></script>
</head>
<body>
	<div class="bread-line">
        <label for="">位置：</label>
        <a href="#">安监在线</a>>
        <a href="#">预警监测</a>>
        <a href="#">预警记录</a>
    </div>
	<div class="list">
		<div class="collect_search">
		    <div style="float: right;margin-bottom: 10px;">
            <select name="watchType" class="select" id="watchType">
				<option value="-1">全部</option>
			</select>
            <span class="label">传感器：</span>
            <select name="watchType" class="select" id="watchType">
				<option value="-1">全部</option>
			</select>
           <span class="label">监测对象：</span>
            <select name="watchType" class="select" id="dangerSourceId">
				<option value="-1">全部</option>
			</select> 
           <span class="label">危险源：</span>
            <select name="watchType" class="select" id="companyId" onclick="changeSelect(2)" style="width: 200px;">
				<option value="-1">全部</option>
			</select>
            <span class="label">企业：</span>
            <select name="watchType" class="select" id="cityName" onclick="changeSelect(1)" >
				<option value="-1">全部</option>
			</select>
            <span class="label">县区：</span>
           </div> 
		   <div style="float: right;margin-bottom: 10px;">
		      <span class="search" id="search" style="margin: 0;"> 导出</span>
              <span class="search" id="search" style="margin: 0 10px;">查询</span>
	            <select name="watchType" class="select" id="watchType">
				<option value="-1">全部</option>
			</select>
	            <span class="label">状态：</span>
	           <select name="watchType" class="select" id="watchType">
				<option value="-1">全部</option>
			</select>
	           <span class="label">预警级别：</span>
            </div> 
           
        </div>    
        
		<table id="List" cellpadding="0" cellspacing="0" class="pretty dataTable">
			<thead>
                 <tr>
                     <th style="width: 10%;">县区</th>
                     <th style="width: 10%;">企业</th>
                     <th style="width: 10%;">危险源</th>
                     <th style="width:10%;">监测对象</th>
                     <th style="width:10%;">传感器名称</th>
                     <th style="width:10%;">预警级别</th>
                     <th style="width:10%;">监测类型</th>
                     <th style="width:10%;">状态</th>
                     <th style="width:10%;">预警时间</th>
                     <th style="width: 15%;">操作</th>
                 </tr>
            </thead>
		</table>
	</div>
	<div class="clear"></div>
</body>
</html>