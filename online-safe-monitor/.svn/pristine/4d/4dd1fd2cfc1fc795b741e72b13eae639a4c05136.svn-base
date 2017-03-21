<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<jsp:include page="../../common/taglibs.jsp" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>查询统计-通话明细</title>
		<jsp:include page="../../common/head.jsp" />
		<link href="${ctx}common/css/reset.css" rel="stylesheet" type="text/css" />
		<link href="${ctx}common/plugins/datatable/skins/datatable_default.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="${ctx}plugins/datatable/jquery.dataTables.min.js?version=${version}"></script>
		<script type="text/javascript" src="${ctx}common/plugins/datatable/selecedForDatatablePagination.js?version=${version}"></script>

		<script type="text/javascript" src="${ctx}common/is/placeholder.js?version=${version}"></script>
		<!-- 引用js -->
		<script type="text/javascript" src="${ctx}js/logined/report/callDetails.js?version=${version}"></script>
	</head>
	<body>
		<div class="list">
			<div class="searchArea">
				<table cellspacing="0" cellpadding="0">
					<tbody>
						<tr>
							<td class="right">
								<label>时间范围：</label>
								<input id="beginTime" type="text" class="formText Wdate" onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'endTime\')}',skin:'default',dateFmt:'yyyy-MM-dd'})" />
								&nbsp;-&nbsp;
								<input id="endTime" type="text" class="formText Wdate" onfocus="WdatePicker({minDate:'#F{$dp.$D(\'beginTime\')}',skin:'default',dateFmt:'yyyy-MM-dd'})" />
								<label>呼叫类型：</label>
								<select id="callType">
									<option selected="selected" value="0">全部</option>
									<option value="1">呼入</option>
									<option value="2">呼出</option>
								</select>
								<label>关键字：</label>
								<span class="position:relative;">
									<input type="text" id="searchkey"  class="formText searchkey" placeholder="主叫号码/坐席工号"/>
								</span>
								<input type="button" id="search" class="searchButton" value="查询"/>
							</td>
							<td style="width:92px;"><div class="fButton greenBtn" onclick="exporting();"> <span  class="export">导出</span> </div></td>
						</tr>
					</tbody>
				</table>
			</div>
			<!--列表区-->
			<table width="100%" cellpadding="0" border="0" cellspacing="0" class="pretty" id="myTable">
				<thead>
					<tr role="row">
						<th class="num" style="width:40px;">序号</th>
						<th style="width:130px;">主叫号码</th>
						<th style="width:130px;">被叫号码</th>
						<th style="width:70px;">呼叫类型</th>
						<th style="width:150px;">开始时间</th>
						<th style="width:150px;">结束时间</th>
						<th style="width:80px">时长（秒） </th>
						<th style="width:100px">坐席工号 </th>
						<th style="width:80px">坐席姓名 </th>
						<th style="width:80px;" class="right_bdr0">通话录音</th>
					</tr>
				</thead>
			</table>
			<div class="clear"></div>
		</div>
		<script>funPlaceholder(document.getElementById("searchkey"));</script>
	</body>
</html>
