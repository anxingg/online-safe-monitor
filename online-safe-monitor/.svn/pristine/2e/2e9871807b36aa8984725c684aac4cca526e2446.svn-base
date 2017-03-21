<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<jsp:include page="../../../common/taglibs.jsp" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>工单详情</title>
<jsp:include page="../../../common/flatHead.jsp" />
<link href="${ctx}flat/css/reset.css" rel="stylesheet" type="text/css" />
<link href="${ctx}flat/css/Reminder.css" rel="stylesheet"
	type="text/css" />
<link href="${ctx}flat/plugins/datatable/skins/datatable_default.css"
	rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx}plugins/datatable/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="${ctx}flat/js/base.js"></script>
<script type="text/javascript"
	src="${ctx}flat/plugins/dialog/iframeTools.js"></script>

<link href="${ctx}flat/plugins/form/skins/form_default.css"
	rel="stylesheet" type="text/css" />
<script type="text/javascript"
	src="${ctx}flat/plugins/dialog/artDialog.js?skin=default"></script>

<!-- 通话记录 -->
<script type="text/javascript"
	src="${ctx}logined/workorderflow/js/phoneRecord.js"></script>
<style type="text/css">
.inputTable th {
	width: 90px;
}
</style>

</head>
<body style="background:#f1f5f8">

	<input type="hidden" value="<s:property value='#request.fromPage'/>"
		id="checkDetain" />
	<input type="hidden" value="<%=request.getParameter("fromPage")%>"
		id="fromPage" />

	<div class="formPage">
		<div class="formbg">
			<div class="big_title" style="font-size:16px">
				<span class="fr">工单状态：<font class="txt_green">${
						customerCallLog.stateStr }</font>
				</span>工单编号 ： ${customerCallLog.cclSn }
			</div>
			<div class="content_form">
				<h3 class="small_title">工单详情</h3>
				<table width="100%" border="0" cellpadding="0" cellspacing="0"
					class="inputTable">
					<tr>
						<th><label>受理时间：</label>
						</th>
						<td><s:date format="yyyy-MM-dd HH:mm"
								name="#request.customerCallLog.recordTime" />
						</td>
						<th><label>受理人员：</label>
						</th>
						<td>${customerCallLog.recordUser.userName }</td>
					</tr>
					<tr>
						<th><label>联系电话：</label>
						</th>
						<td>${customerCallLog.phone }<img class="tel" width="20" height="20" src="../../../common/images/tel.png"></td>
						<th><label>来电人姓名：</label>
						</th>
						<td>${customerCallLog.name }</td>
					</tr>
					<tr>
						<th><label>工单类别：</label>
						</th>
						<td colspan="3"><s:if test="customerCallLog.type==1">咨询</s:if>
							<s:if test="customerCallLog.type==2">投诉</s:if>
							<s:if test="customerCallLog.type==3">建议</s:if>
						</td>
					</tr>
					<tr>
						<th><label>工单内容：</label>
						</th>
						<td colspan="3">${customerCallLog.logInfo }</td>
					</tr>
					<tr>
						<th><label>答复时间：</label>
						</th>
						<td colspan="3"><s:date format="yyyy-MM-dd HH:mm"
								name="#request.customerCallLog.answerTime" />
						</td>
					</tr>
					<tr>
						<th><label>答复内容：</label>
						</th>
						<td colspan="3">${customerCallLog.replyInfo }</td>
					</tr>
					<tr>
						<th><label>回访时间：</label>
						</th>
						<td><s:date format="yyyy-MM-dd HH:mm"
								name="#request.customerCallLog.callBackTime" />
						</td>
						<th><label>回访状态：</label>
						</th>
						<td><s:if test="customerCallLog.visitResult==0">成功</s:if>
							<s:if test="customerCallLog.visitResult==1">不成功</s:if>
						</td>
					</tr>
					<tr>
						<th><label>回访结果：</label>
						</th>
						<td colspan="3"><s:if test="customerCallLog.visitResult==0">${customerCallLog.callBackSuccessResult }</s:if>
							<s:if test="customerCallLog.visitResult==1">
								<s:if test="customerCallLog.visitFaildReason==1">无人接听</s:if>
								<s:if test="customerCallLog.visitFaildReason==2">电话正忙</s:if>
								<s:if test="customerCallLog.visitFaildReason==3">呼叫转移</s:if>
								<s:if test="customerCallLog.visitFaildReason==4">电话关机</s:if>
								<s:if test="customerCallLog.visitFaildReason==5">电话停机</s:if>
								<s:if test="customerCallLog.visitFaildReason==6">空号</s:if>
								<s:if test="customerCallLog.visitFaildReason==7">用户拒访</s:if>
							</s:if></td>
					</tr>
				</table>
			</div>
		</div>
		<div class="buttonArea">
			<input type="button" class="formButton_grey" value="返回"
				onclick="window.top.closeCurrentTab();" />
			<%-- <span class="blue">点击回到工单列表</span> --%>
		</div>
	</div>
</body>
</html>
