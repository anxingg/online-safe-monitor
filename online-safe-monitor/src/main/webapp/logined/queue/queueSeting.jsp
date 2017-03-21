<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<jsp:include page="../../common/taglibs.jsp" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>坐席队列设置</title>
		<link href="${ctx}common/css/reset.css" rel="stylesheet" type="text/css" />
		<link href="${ctx}common/css/main.css" rel="stylesheet" type="text/css" />
		<link href="${ctx}common/plugins/datatable/skins/datatable_default.css" rel="stylesheet" type="text/css" />
		<link href="${ctx}common/plugins/datatable/skins/datatable_default.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript">
			var basePath = '${ctx}';
		</script>
		<script type="text/javascript" src="${ctx}js/common/jquery-1.8.0.min.js"></script>
		<script type="text/javascript" src="${ctx}common/js/base.js"></script>
		<script type="text/javascript" src="${ctx}common/plugins/datatable/jquery.dataTables.min.js?version=${version}"></script>
		<script type="text/javascript"
			src="${ctx}common/plugins/dialog/artDialog.js?skin=default"></script>
		<script type="text/javascript" src="${ctx}flat/plugins/dialog/iframeTools.js?version=${version}"></script>
		<script type="text/javascript" src="${ctx}js/placeholder.js"></script>
		<script type="text/javascript" src="${ctx}/js/logined/queue/queueSeting.js"></script>
	</head>
	<body>
		<div class="list">
			<div class="prettyList">
				<table cellpadding="0" cellspacing="0"  class="pretty dataTable" id="myTable">
					<thead>
						<!-- <tr>
							<th style="width:100px;">对应按键</th>
							<th style="width:100px;">按键状态</th>
							<th style="width:40%;">队列说明</th>
							<th style="width:60%;" >队列成员</th>
							<th style="width:90px;" class="right_bdr0">操作</th>
						</tr> -->
						<tr>
				          <th class="num">序号</th>
				          <th style="width:40%;">队列说明</th>
				          <th style="width:100px;">队列人数</th>
				          <th style="width:60%;" >队列成员</th>
				          <th style="width:90px;" class="right_bdr0">操作</th>
				        </tr>
					</thead>
					<!-- <tbody>
						<tr>
							<td>1</td>
							<td>可用</td>
							<td class="data_l">专门接听水稻收割机咨询电话</td>
							<td class="data_l">张文文，李乐乐，王欣欣，赵燕燕，芜湖固话1，芜湖固话2</td>
							<td class="right_bdr0"><a href="11系统设置-坐席队列设置-编辑队列.html">编辑</a></td>
						</tr>
						<tr>
							<td>2</td>
							<td>可用</td>
							<td class="data_l">专门接听水稻收割机咨询电话</td>
							<td class="data_l">&nbsp;</td>
							<td class="right_bdr0"><a href="11系统设置-坐席队列设置-编辑队列.html">编辑</a></td>
						</tr>
					</tbody> -->
				</table>
			</div>
		</div>
	</body>
</html>