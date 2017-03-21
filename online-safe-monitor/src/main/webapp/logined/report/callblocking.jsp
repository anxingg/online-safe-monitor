<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<jsp:include page="../../common/taglibs.jsp" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>查询统计-呼损清单</title>
<jsp:include page="../../common/head.jsp" />
<link href="${ctx}common/css/reset.css" rel="stylesheet" type="text/css" />
<link href="${ctx}common/plugins/datatable/skins/datatable_default.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx}common/plugins/datatable/selecedForDatatablePagination.js?version=${version}"></script>
<script type="text/javascript" src="${ctx}common/plugins/dialog/artDialog.js?skin=default"></script>
<script type="text/javascript" src="${ctx}common/is/placeholder.js?version=${version}"></script>

<script type="text/javascript" src="${ctx}js/logined/report/callblocking.js?version=${version}"></script>
</head>
<body>
<div class="list">
  <div class="searchArea">
    <table cellspacing="0" cellpadding="0">
      <tbody>
        <tr>
          <td class="right"><label>时间范围：</label>
            <input name="" type="text" class="formText Wdate" id="beginTimeStr" name="beginTimeStr" 
				onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'endTimeStr\')}',skin:'default',dateFmt:'yyyy-MM-dd HH:mm'})" style="width: 150px"/>
			&nbsp;-&nbsp; <input name="" type="text" class="formText Wdate" id="endTimeStr"
				onfocus="WdatePicker({minDate:'#F{$dp.$D(\'beginTimeStr\')}',skin:'default',dateFmt:'yyyy-MM-dd HH:mm'})" style="width: 150px"/>
            <!-- <label>所属区域：</label>
            <select id="queue">
           		<option value="-1">全部</option>
			</select> -->
            <label>关键字：</label>
            <span class="position:relative;">
            <input type="text" id="searchkey"  class="formText searchkey" placeholder="主叫号码/坐席工号"/>
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
        <th style="width:120px">主叫号码</th>
        <th style="width:120px">被叫号码</th>
        <th style="width:160px">呼入时间</th>
        <th style="width:160px">振铃时间</th>
        <th style="width:120px">振铃时长 （秒）</th>
        <th style="width:160px">挂断时间</th>
       <!--  <th style="width:150px">队列</th> -->
        <th style="width:120px">坐席工号</th>
        <th style="width:180px;">呼损原因</th>
      </tr>
    </thead>
  </table>
  <div class="clear"></div>
</div>
<script>funPlaceholder(document.getElementById("searchkey"));</script>
</body>
</html>
