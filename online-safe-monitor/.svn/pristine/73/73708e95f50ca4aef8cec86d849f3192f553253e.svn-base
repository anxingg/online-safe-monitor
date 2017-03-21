<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<jsp:include page="../../common/taglibs.jsp" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>外呼任务-结果</title>
<jsp:include page="../../common/head.jsp" />
<link href="${ctx}common/css/reset.css" rel="stylesheet" type="text/css" />
<link href="${ctx}common/css/Reminder.css" rel="stylesheet" type="text/css" />
<link href="${ctx}common/plugins/form/skins/form_default.css" rel="stylesheet" type="text/css" />
<link href="${ctx}common/plugins/datatable/skins/datatable_default.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="${ctx}common/plugins/datatable/selecedForDatatablePagination.js"></script>
<script type="text/javascript" src="${ctx}common/plugins/dialog/artDialog.js?skin=default"></script>
<script type="text/javascript" src="${ctx}common/plugins/dialog/iframeTools.js?version=${version}"></script>
<script type="text/javascript" src="${ctx}js/common/placeholder.js"></script>

<script type="text/javascript" src="${ctx}js/logined/outCallTask/outCallTaskResultList.js?version=${version}"></script>

</head>
<body>
<input type="hidden" id="stateSaveParam" value="${param.paramData }"/>
<input type="hidden" value="${param.questionnaireId }" id="questionnaireId"/>
<div class="list">
	<div class="searchArea">
        <table cellspacing="0" cellpadding="0">
              <tbody>
              <tr>
                <td class="right">
            		<label>回访结果：</label>
                    <!-- <select id="statue">
                    	<option value="-1">全部</option>
                    	<option value="1">成功</option>
                    	<option value="2">无人接听</option>
						<option value="3">电话正忙</option>
						<option value="4">呼叫转移</option>
						<option value="5">停机</option>
						<option value="6">关机</option>
						<option value="7">空号</option>
						<option value="10">回访中</option>
                    </select> -->
                    <select id="statue">
					<option value="-1">全部</option>
					<option value="1">成功</option>
					<option value="2">无人接听</option>
					<option value="3">电话正忙</option>
					<!-- <option value="3">电话秘书</option> -->
					<option value="4">呼叫转移</option>
					<option value="5">电话关机</option>
					<option value="6">电话停机</option>
					<option value="7">空号</option>
					<option value="8">用户拒访</option>
				</select>
                    <label>关键字：</label>
                    <span class="position:relative;"><input type="text" id="searchkey"  class="formText searchkey" placeholder="电话号码/坐席工号"/></span>
                    <input type="button" class="searchButton" id="search" value="查询"/>
                </td>
                <td style="width:92px;">
                    <div id="outCallTaskResultExport" class="fButton greenBtn">
                          <span class="export">导出</span>
                    </div>
                </td>
               </tr>
               </tbody>
         </table>
	</div>
     <table cellpadding="0" cellspacing="0"  class="pretty dataTable" id="outCallTaskResultTable">
          <thead>
                <tr>
                  <th class="num">序号</th>
                  <th style="width:20%;">外呼对象</th>
                  <th style="width:20%;">电话号码</th>
                  <th style="width:20%;">执行坐席</th>
                  <th style="width:20%;">坐席工号</th>
                  <th style="width:20%;">回访时间</th>
                  <th style="width:130px;">回访结果</th>
                  <th class="right_bdr0" style="width:70px;">操作</th>
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
