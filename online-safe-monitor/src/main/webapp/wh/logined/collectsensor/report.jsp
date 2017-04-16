<%@ page language="java" import="java.util.*"  contentType="text/html; charset=UTF-8" pageEncoding="utf-8"  %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<jsp:include page="../../../common/taglibs.jsp"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<jsp:include page="../osmHead.jsp" />
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>传感器统计</title>
	<link href="${ctx}wh/plugins/datatable/skins/datatable_default.css?version=${version}" rel="stylesheet" type="text/css"/>
	<!-- 清除分页cookie start -->
	<script type="text/javascript" src="${ctx}js/common/jquery.cookie.js"></script>
	<!-- 清除分页cookie end -->
    <script type="text/javascript" src="${ctx}wh/js/collectsensor/report.js?version=${version}"></script>
</head>
<body>
	<input type="hidden" id="type" value='${paramValues.type[0]}'/>
	<input type="hidden" id="vid" value='${paramValues.vid[0]}'/>
	<div class="list">
		
		<table id="" cellpadding="0" cellspacing="0" class="sersor_tab " aria-describedby="myTable_info">
			<thead>
				<tr>
					<th colspan="2" id="report_title">传感器数据汇总</th>
				</tr>
			</thead>
			<tbody role="alert" aria-live="polite" aria-relevant="all">
				<tr>
					<td>
						<span>在用传感器:</span>
					</td>
					<td>
						<span id="collectSensor_Using">0</span>
					</td>
				</tr>
				<tr>
					<td>
						<span>停用传感器:</span>
					</td>
					<td>
						<span id="collectSensor_Stoped">0</span>
					</td>
				</tr>
				<tr>
					<td>
						<span>合计:</span>
					</td>
					<td>
						<span id="collectSensor_All">0</span>
					</td>
				</tr>
				<tr>
					<td style="border-bottom: 1px solid #E0E0E0;">
						<span>本月新增:</span>
					</td>
					<td style="border-bottom: 1px solid #E0E0E0;">
						<span id="collectSensor_MonthNew">0</span>
					</td>
				</tr>
			</tbody>
		</table>
	</div>
	<div class="clear"></div>
</body>
</html>