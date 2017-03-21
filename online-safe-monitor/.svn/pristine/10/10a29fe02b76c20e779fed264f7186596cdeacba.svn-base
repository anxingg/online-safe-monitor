<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:include page="../../common/taglibs.jsp" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>外呼任务-详情</title>
<jsp:include page="../../common/head.jsp" />
<link href="${ctx}common/css/reset.css" rel="stylesheet" type="text/css" />
<link href="${ctx}common/css/task.css" rel="stylesheet" type="text/css" />
<link href="${ctx}common/css/Reminder.css" rel="stylesheet" type="text/css" />
<link href="${ctx}common/plugins/form/skins/form_default.css" rel="stylesheet" type="text/css" />
<link href="${ctx}common/plugins/datatable/skins/datatable_default.css" rel="stylesheet" type="text/css" />

<%-- <script type="text/javascript" src="${ctx}common/js/base.js"></script>
<script type="text/javascript" src="${ctx}plugins/datatable/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="${ctx}common/plugins/datatable/selecedForDatatablePagination.js"></script>
<script type="text/javascript" src="${ctx}common/plugins/dialog/artDialog.js?skin=default"></script>
<script type="text/javascript" src="${ctx}common/plugins/dialog/iframeTools.js"></script>
<script type="text/javascript" src="${ctx}js/common/placeholder.js"></script> --%>

<style type="text/css">
.inputTable th{width:90px;}
</style>

<script>
	$(document).ready(function(){
		var html=$("#questionListStr").val();
		$("#answerlist").html(html);
	});
</script>

</head>
<body>
<input type="hidden" value="${phoneTask.vid}"/>
<input type="hidden" value="${ questionListStr}" id="questionListStr"/>
<div class="formPage">
  <div class="formbg">
    <div class="big_title">任务详情</div>
    <div class="content_form">
      <table width="100%" border="0" cellpadding="0" cellspacing="0"  class="inputTable">
        <tr>
          <th><label>任务名称：</label></th>
          <td>${phoneTask.taskName}</td>
          <th><label>任务状态：</label></th>
          <td>
          	<s:if test="phoneTask.taskState==1">草稿</s:if>
          	<s:elseif test="phoneTask.taskState==2">未开始</s:elseif>
          	<s:elseif test="phoneTask.taskState==3">正在进行</s:elseif>
          	<s:elseif test="phoneTask.taskState==4">暂停</s:elseif>
          	<s:elseif test="phoneTask.taskState==5">已结束</s:elseif>
          	<s:else></s:else>
          </td>
        </tr>
        <tr>
          <th><label>开始时间：</label></th>
          <td><fmt:formatDate value="${phoneTask.taskStartTime}" pattern="yyyy-MM-dd HH:mm"/></td>
          <th><label>结束时间：</label></th>
          <td><fmt:formatDate value="${phoneTask.taskEndTime}" pattern="yyyy-MM-dd HH:mm"/></td>
        </tr>
        <tr>
          <th><label>任务类型：</label></th>
          <td>${taskTypeName }</td>
          <th><label>外呼结果：</label></th>
          <td>
          	成功<s:if test="phoneTask.taskSuccessCount==null||phoneTask.taskSuccessCount==''">0</s:if><s:else>${phoneTask.taskSuccessCount }</s:else>个，
          	失败<s:if test="phoneTask.taskFailureCount==null||phoneTask.taskFailureCount==''">0</s:if><s:else>${phoneTask.taskFailureCount}</s:else>个&nbsp;&nbsp;
          	<a href="${ctx}logined/outCallTask/outCallTaskResultList.jsp?paramData=1&questionnaireId=${phoneTask.questionnaire.id }">详细结果</a>
          </td>
        </tr>
        <tr>
          <th><label>执行坐席：</label></th>
          <td colspan="3">${seatName}，共${seatNum}个坐席</td>
        </tr>
        <tr>
          <th><label>任务说明：</label></th>
          <td colspan="3">${phoneTask.taskExplain }</td>
        </tr>
        <tr>
          <th><label>问题列表：</label></th>
          <td colspan="3">
          	<ul class="answerlist" id="answerlist">
            </ul>
          </td>
        </tr>
        </table>
    </div>
  </div>
  <div class="buttonArea">
    <input type="button" class="formButton_grey" value="返回" onclick="javascript:history.back();" />
   <%--  <span class="blue">点击回到任务列表</span> --%>
  </div>
</div>
</body>
</html>
