<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<jsp:include page="../../common/taglibs.jsp" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>查询统计-坐席考勤表</title>
<jsp:include page="../../common/head.jsp" />
<link href="${ctx}common/css/reset.css" rel="stylesheet" type="text/css" />
<link href="${ctx}common/plugins/datatable/skins/datatable_default.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx}common/js/base.js"></script>
<script type="text/javascript" src="${ctx}common/plugins/datatable/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="${ctx}common/plugins/datatable/selecedForDatatablePagination.js"></script>
<script type="text/javascript" src="${ctx}common/plugins/dialog/artDialog.js?skin=default"></script>
<script type="text/javascript" src="${ctx}common/plugins/dialog/iframeTools.js"></script>
<script type="text/javascript" src="${ctx}common/is/placeholder.js"></script>

<script type="text/javascript" src="${ctx}js/logined/report/seatAttendance.js?version=${version}"></script>
</head>
<body>
<div class="list">
	<div class="searchArea">
		<table cellspacing="0" cellpadding="0">
      <tbody>
        <tr>
          <td class="right">
           <!-- <label>所属地市：</label>
            <select id="queue">
           		<option value="-1">全部</option>
			</select> -->
			<label>时间范围：</label>
            <input name="" type="text" class="formText Wdate" id="beginTimeStr" name="beginTimeStr" 
				onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'endTimeStr\')}',skin:'default',dateFmt:'yyyy-MM-dd'})" style="width: 150px"/>
			&nbsp;-&nbsp; <input name="" type="text" class="formText Wdate" id="endTimeStr"
				onfocus="WdatePicker({minDate:'#F{$dp.$D(\'beginTimeStr\')}',skin:'default',dateFmt:'yyyy-MM-dd'})" style="width: 150px"/>
            <label>关键字：</label>
            <span class="position:relative;">
            <input type="text" id="searchkey"  class="formText searchkey" placeholder="坐席工号/坐席姓名"/>
            </span>
            <input type="button" class="searchButton" id="search" value="查询"/></td>
          <td style="width:92px;"><div class="fButton greenBtn" id="callblockExport"> <span class="export">导出</span> </div></td>
        </tr>
      </tbody>
    </table>
	</div>
	<!--列表区-->
  <table width="100%" cellpadding="0" border="0" cellspacing="0"class="pretty" id="callblockingTable">
    	<thead>
	      <tr>
	        <th class="num">序号</th>
	       <!--  <th style="width:120px">所属地市</th> -->
	        <th style="width:120px">坐席工号</th>
	        <th style="width:160px">坐席姓名</th>
	        <th style="width:160px">登录时间</th>
	        <th style="width:160px">退出时间</th>
	        <th style="width:160px">在线时长(分钟)</th>
	        <th style="width:160px">置忙时长(分钟)</th>
	        <th style="width:160px">空闲时长(分钟)</th>
	        <th style="width:160px">置忙次数</th>
	      </tr>
    	</thead>
 	</table>
	<div class="clear"></div>
</div>
</body>
<script>funPlaceholder(document.getElementById("searchkey"));</script>
</html>