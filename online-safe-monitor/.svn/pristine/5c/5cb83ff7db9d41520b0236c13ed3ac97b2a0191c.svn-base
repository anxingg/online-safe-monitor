<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<jsp:include page="../../common/taglibs.jsp"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>本地号段维护</title>
	<link href="${ctx}common/css/reset.css" rel="stylesheet" type="text/css" />
	<link href="${ctx}common/plugins/datatable/skins/datatable_default.css" rel="stylesheet" type="text/css" />
	<style type="text/css">
		::-webkit-input-placeholder { /* WebKit browsers */ color: #ccc !important;}
		:-moz-placeholder { /* Mozilla Firefox 4 to 18 */color:#ccc !important;}
		::-moz-placeholder { /* Mozilla Firefox 19+ */color: #ccc !important;}
		:-ms-input-placeholder { /* Internet Explorer 10+ */color:#ccc !important;}
	</style>
	<script type="text/javascript">
		var basePath = '${ctx}';
	</script>
	<script type="text/javascript" src="${ctx}js/common/jquery-1.8.0.min.js"></script>
	<script type="text/javascript" src="${ctx}common/plugins/datatable/jquery.dataTables.min.js?version=${version}"></script>
	<script type="text/javascript"
		src="${ctx}common/plugins/dialog/artDialog.js?skin=default"></script>
	<script type="text/javascript" src="${ctx}flat/plugins/dialog/iframeTools.js?version=${version}"></script>
	<script type="text/javascript" src="${ctx}js/common/ajaxfileupload.js"></script>
	<script type="text/javascript" src="${ctx}js/logined/localsegment/localWeihu.js?version=${version}"></script> 
	<script type="text/javascript" src="${ctx}js/common/placeholder.js"></script>
</head>
<body>
<div class="list">
		<div class="searchArea">
				<table cellspacing="0" cellpadding="0">
						<tbody>
								<tr>
										<td class="right"><label>关键字：</label>
												<input type="text" id="phone" name="phone" onkeyup="this.value=this.value.replace(/[^\d]/g,'') " onafterpaste="this.value=this.value.replace(/[^\d]/g,'') " style="width:200px;" maxlength="8"  class="formText searchkey" placeholder="号段"/>
												<input id="phone" name="phone" type="button" class="searchButton" value="查询"/></td>
										<td style="width:276px;"><div class="fButton greenBtn" onclick="phoneAdd()"> <span  class="add">新增</span> </div>
												<div class="fButton greenBtn" onclick="impPhone();"> <span  class="import">导入</span> </div>
												<div class="fButton greenBtn" onclick="expPhone();"> <span  class="export">导出</span> </div></td>
								</tr>
						</tbody>
				</table>
		</div>
		<table width="100%" class="pretty dataTable"  cellspacing="0" cellpadding="0" id="Table">
				<thead>
						<tr role="row">
								<th class="num">序号</th>
								<th style="width: 50%;">号段</th>
								<th style="width: 50%;">新增时间</th>
								<th class="right_bdr0 data_l" style="width:70px;">操作</th>
						</tr>
				</thead>
				<tbody>
						
				</tbody>
		</table>
		
</div>
</body>
</html>