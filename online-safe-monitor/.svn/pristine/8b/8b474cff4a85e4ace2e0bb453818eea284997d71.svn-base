<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<jsp:include page="../../common/taglibs.jsp" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>外呼记录</title>
<jsp:include page="../../common/head.jsp" />
<link href="${ctx}common/css/reset.css" rel="stylesheet" type="text/css" />
<link href="${ctx}common/css/Reminder.css" rel="stylesheet" type="text/css" />
<link href="${ctx}common/plugins/form/skins/form_default.css" rel="stylesheet" type="text/css" />
<link href="${ctx}common/plugins/datatable/skins/datatable_default.css" rel="stylesheet" type="text/css" />

<%-- <script type="text/javascript" src="${ctx}common/js/base.js"></script>
<script type="text/javascript" src="${ctx}plugins/datatable/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="${ctx}common/plugins/datatable/selecedForDatatablePagination.js"></script>
<script type="text/javascript" src="${ctx}common/plugins/dialog/artDialog.js?skin=default"></script>
<script type="text/javascript" src="${ctx}common/plugins/dialog/iframeTools.js"></script>
<script type="text/javascript" src="${ctx}js/common/placeholder.js"></script> --%>

<script type="text/javascript" src="${ctx}js/logined/outCallTask/outCallTaskSRList.js?version=${version}"></script>

</head>
<body>
<input type="hidden" value="${param.stateSaveParam }" id="stateSaveParam" />
<input type="hidden" value="${param.questionnaireId }" id="questionnaireId"/>
<div class="list">
     <table cellpadding="0" cellspacing="0"  class="pretty dataTable" id="outCallTaskSRTable">
          <thead>
                <tr>
                  <th class="num">序号</th>
                  <th style="width:120px;">客户姓名</th>
                  <th style="width:120px;">客户号码</th>
                  <th style="width:130px;">开始时间</th>
                  <th style="width:130px;">结束时间</th>
                  <th style="width:100px">时长（秒）</th>
                  <th style="width:100px">回访结果</th>
                  <th style="width:120px;">操作</th>
                </tr>
          </thead>
      </table>
      <div class="clear"></div>
</div>   
</body>
</html>
