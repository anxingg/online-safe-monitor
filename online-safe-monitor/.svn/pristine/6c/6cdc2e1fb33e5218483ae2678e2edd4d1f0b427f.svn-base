<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<jsp:include page="../../common/taglibs.jsp" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>外呼任务</title>
<jsp:include page="../../common/head.jsp" />
<link href="${ctx}common/css/reset.css" rel="stylesheet" type="text/css" />
<link href="${ctx}common/css/Reminder.css" rel="stylesheet" type="text/css" />
<link href="${ctx}common/plugins/form/skins/form_default.css" rel="stylesheet" type="text/css" />
<link href="${ctx}common/plugins/datatable/skins/datatable_default.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="${ctx}js/common/placeholder.js"></script>

<script type="text/javascript" src="${ctx}js/logined/outCallTask/outCallTaskList.js?version=${version}"></script>

</head>
<body>
<div class="list">
	<div class="searchArea">
        <table cellspacing="0" cellpadding="0">
             <tbody>
              <tr>
                <td class="right">
	           		<label>任务状态：</label>
	                <select id="taskState">
	                   	<option value="-1">全部</option>
	                   	<option value="1">草稿</option>
	                   	<option value="2">未开始</option>
	                   	<option value="3">正在进行</option>
	                   	<option value="4">暂停</option>
	                   	<option value="5">已结束</option>
	                </select>
	                <label>关键字：</label>
	                <span class="position:relative;">
	                   	<input type="text" id="searchkey"  class="noSqlLimit formText searchkey" placeholder="任务名称"/>
	                </span>
	                   <input type="button" class="searchButton" id="search" value="查询"/>
                </td>
                <td style="width:92px;" id="addButton" style="display:none;">
                    <div id="addOutCallTask" class="fButton greenBtn">
                          <span class="add" >新增</span>
                    </div>
                </td>
              </tr>
            </tbody>
         </table>
	</div>
     <table cellpadding="0" cellspacing="0"  class="pretty dataTable" id="outCallTaskTable">
          <thead>
                <tr>
                  <th class="num">序号</th>
                  <th style="width:100%;">任务名称</th>
                  <th style="width:120px;">任务类型</th>
                  <th style="width:60px;">总数量</th>
                  <th style="width:60px;">已回访</th>
                  <th style="width:60px;">成功数</th>
                  <th style="width:80px;">未成功数</th>
                  <th style="width:120px">任务状态</th>
                  <th class="right_bdr0" style="width:180px;">操作</th>
                </tr>
          </thead>
      </table>
      <div class="clear"></div>
</div>   
<script>
	setTimeout(function(){
		funPlaceholder(document.getElementById("searchkey"));
	}, 200);
</script>
</body>
</html>
