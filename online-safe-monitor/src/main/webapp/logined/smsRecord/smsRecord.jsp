<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<jsp:include page="../../common/taglibs.jsp" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>查询统计-短信明细</title>
<jsp:include page="../../common/head.jsp" />
<link href="${ctx}common/css/reset.css" rel="stylesheet" type="text/css" />
<link href="${ctx}common/plugins/form/skins/form_default.css" rel="stylesheet" type="text/css" />
<link href="${ctx}common/plugins/datatable/skins/datatable_default.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx}common/js/base.js"></script>
<script type="text/javascript" src="${ctx}plugins/datatable/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="${ctx}common/plugins/datatable/selecedForDatatablePagination.js"></script>
<script type="text/javascript" src="${ctx}common/plugins/dialog/artDialog.js?skin=default"></script>
<script type="text/javascript" src="${ctx}common/plugins/dialog/iframeTools.js"></script>
<script type="text/javascript" src="${ctx}common/is/placeholder.js"></script>

<script type="text/javascript" src="${ctx}/js/logined/smsRecord/smsRecord.js"></script>

</head>
<body>
<div class="list">
  <div class="searchArea">
    <table cellspacing="0" cellpadding="0">
      <tbody>
        <tr>
          <td class="right"><label>发送时间：</label>
            <input id="sendTimeStart" type="text" class="formText Wdate" style="width: 150px"
             onclick="WdatePicker({skin:'default',dateFmt:'yyyy-MM-dd HH:mm',maxDate:'#F{$dp.$D(\'sendTimeEnd\')}'})" />
			&nbsp;-&nbsp;
			<input id="sendTimeEnd" type="text" class="formText Wdate" style="width: 150px"
			 onclick="WdatePicker({skin:'default',dateFmt:'yyyy-MM-dd HH:mm',minDate:'#F{$dp.$D(\'sendTimeStart\')}'})" />
            <label>关键字：</label>
            <span class="position:relative;">
            <input type="text" id="searchkey"  class="formText searchkey" placeholder="接收号码/操作人员">
            </span>
            <input type="button" id="search"  class="searchButton" value="查询"></td>
        </tr>
      </tbody>
    </table>
  </div>
  <!--列表区-->
  <table width="100%" cellpadding="0" border="0" cellspacing="0" class="pretty" id="myTable">
    <thead>
      <tr role="row">
        <th class="num">序号</th>
        <th style="width:120px">接收号码</th>
        <th style="width:100px">操作人员</th>
        <th style="width:150px">发送时间</th>
        <th style="width:100%;" class="right_bdr0">短信内容</th>
      </tr>
    </thead>
  </table>
  <div class="clear"></div>
</div>
<script>funPlaceholder(document.getElementById("searchkey"));</script>
</body>
</html>
