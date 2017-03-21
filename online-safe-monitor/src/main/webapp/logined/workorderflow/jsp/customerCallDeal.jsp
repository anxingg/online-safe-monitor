<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@taglib prefix="cm" uri="/common-workflow" %>
<jsp:include page="../../../common/taglibs.jsp" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>工单处理</title>
<jsp:include page="../../../common/flatHead.jsp" />
<link href="${ctx}flat/css/reset.css" rel="stylesheet" type="text/css" />
<link href="${ctx}flat/css/Reminder.css" rel="stylesheet"
	type="text/css" />
<link href="${ctx}flat/plugins/datatable/skins/datatable_default.css"
	rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx}plugins/datatable/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="${ctx}flat/js/base.js"></script>

<link href="${ctx}flat/plugins/form/skins/form_default.css"
	rel="stylesheet" type="text/css" />

<!-- 这个js用来统计textarea 文本框中的字符数 -->
<script type="text/javascript" src="${ctx}common/js/CheckTextarea.js?version=${version}"></script>

<script type="text/javascript"
	src="${ctx}common/plugins/dialog/artDialog.js?skin=default"></script>
<script type="text/javascript" src="${ctx}flat/plugins/dialog/iframeTools.js?version=${version}"></script>
<style type="text/css">
	.inputTable th {
		width:112px;
	}
	.formTextarea2{min-height:70px;padding:8px 10px;border:1px solid #e4e4e4;color:#555555;text-indent:10px;background:#fff;text-indent:10px;vertical-align:middle; line-height:22px;}
</style>

<%-- <script type="text/javascript"
	src="${ctx}logined/workorderflow/js/customerCallList.js"></script> --%>
<script type="text/javascript"
	src="${ctx}logined/workorderflow/js/customerCallDeal.js"></script>

<!-- 通话记录 -->
<script type="text/javascript"
	src="${ctx}logined/workorderflow/js/phoneRecord.js"></script>
<script type="text/javascript">
	function callNameInfoBack(vid){
		//window.location.href = basePath + "jbpmworkorder/callNameInfo.action?fromPage=back&vid="+vid;
		var url  = "jbpmworkorder/callNameInfo.action?fromPage=back&vid="+vid;
		art.dialog.open(basePath + url, {title: '查看客户信息' ,width:1055,height:427,button: [{name: '关闭',focus: true}]});
	}
	function callNameInfoSeat(vid){
				window.top.addTab("callNameInfoSeat"+vid,basePath+ "jbpmworkorder/callNameInfo.action?fromPage=seat&vid=" + vid
			+"&checkDetain=1","用户档案");
			}
</script>
</head>
<body style="background:#f1f5f8">

	<input type="hidden" id="vid" value="${customerCallLog.vid }" />
	
	<input type="hidden" id="instanceId" value="<%=request.getParameter("instanceId")%>"/>
	<input type="hidden" id="fromPage" value="${fromPage }"/>
	<!-- 隐藏域：存放工单受理人的id -->
	<input type="hidden" id="recordUserId" value="${customerCallLog.recordUser.userId }" />

	<div class="formPage">
		<div class="formbg">
			<div class="big_title" style="font-size:16px">
				<span class="fr pr10" >工单状态：<font style="color:#ff6600;">${workflowVar.stateShow}</font> </span><span id="cclSn">工单编号 ：<font class="#888">${customerCallLog.cclSn }</font></span> 
				<%-- <span>工单处理</span> --%>
			</div>
			<div class="content_form">
				<jsp:include page="customerCallLogProcess.jsp" />
				<h3 class="small_title">工单详情</h3>
				<form action="" name="customercallbackform"
					id="customercallbackform">
					<table width="100%" border="0" cellpadding="0" cellspacing="0"
						class="inputTable">
						<tr>
							<th><label>工单编号：</label></th>
							<td id="cclSn">${customerCallLog.cclSn }</td>
							<th><label>受理时间：</label></th>
							<td><s:date format="yyyy-MM-dd HH:mm"
									name="#request.customerCallLog.recordTime" /></td>
						</tr>
						<tr>
							<th><label>受理人员：</label></th>
							<td id="recordUser">${customerCallLog.recordUser.userName }</td>
							<th><label>联系电话：</label></th>
							<td id="callPhone">${customerCallLog.phone }</td>
						</tr>
						<tr>
							<th><label>来电人姓名：</label></th>
							<td id="uname">
								<s:if test="customerCallLog.name!=null&&customerCallLog.name!=''">
								<s:if test="#request.fromPage=='prev'">
					      		<a href="javascript:void(0);" onclick="callNameInfoSeat(${customerCallLog.vid})" style="margin-left:0px;">${customerCallLog.name }</a>
					      		</s:if>
					      		<s:else>
					      		<a href="javascript:void(0);" onclick="callNameInfoBack(${customerCallLog.vid});" style="margin-left:0px;">${customerCallLog.name }</a>
					      		</s:else>
					      		</s:if>
					      		<s:else>
							    <s:if test="#request.fromPage=='prev'">
							    <a href="javascript:void(0);" onclick="callNameInfoSeat(${customerCallLog.vid})" style="margin-left:0px;">未知</a>
							    </s:if>
							    <s:else>
							    <a href="javascript:void(0);" onclick="callNameInfoBack(${customerCallLog.vid});" style="margin-left:0px;">未知</a>
							    </s:else>
							    </s:else>
							</td>
							<th><label>受理方式：</label></th>
							<td id="accessType"><s:if
									test="customerCallLog.accessType==1">电话受理</s:if> <s:if
									test="customerCallLog.accessType==2">短信受理</s:if> <s:if
									test="customerCallLog.accessType==3">录音受理</s:if> </td>
						</tr>
						<tr>
							<th><label>业务类别：</label></th>
							<td id="businessType"><input id="businessTypeId" value="${customerCallLog.businessType}" type="hidden"/>
							</td>
							<th><label>工单类别：</label></th>
							<td id="type"><s:if
									test="customerCallLog.type==1">咨询</s:if> <s:if
									test="customerCallLog.type==2">投诉</s:if> <s:if
									test="customerCallLog.type==3">建议</s:if> <s:if
									test="customerCallLog.type==4">举报坐席</s:if><s:if
									test="customerCallLog.type==5">其他</s:if></td>
						</tr>
						<s:if test="reportSeat!=null">
							<tr>
								<th><label>被举报坐席信息：</label></th>
								<td colspan="3"><s:if test="reportSeat.userName != null"><s:property value="reportSeat.userName" />（<s:property value="reportSeat.loginName" />）</s:if></td>
							</tr>
						</s:if>
						<tr>
							<th><label>工单内容：</label></th>
							<td colspan="3" id="content"><s:if test="customerCallLog.logInfo != ''"><s:property value="customerCallLog.logInfo"/></s:if><s:else>无</s:else></td>
						</tr>
						 <tr>
				      <th><label>工单答复：</label></th>
				      <td colspan="3"><s:if test="customerCallLog.seatReplay != ''"><s:property value="customerCallLog.seatReplay"/></s:if><s:else>无</s:else></td>
				    </tr>

							<tr>
								<th><em class="requireField">*</em><label>答复内容：</label></th>
								<td colspan="3">
									<textarea name="answerContent"
										id="answerContent" cols="118" rows="5" class="formTextarea2 area area01"
										  fmaxlength="1000"></textarea>
										 <span class="msg-text" style="float:right">1-1000字</span>
								</td>
							</tr>
					</table>
					<h3 class="small_title">
						流转信息<span class="ml20"><cm:showOperation processId="${workflowVar.processId }" nodeName="${currentTask.name }" />
						<label class="choi" style="display:none">选择坐席：</label>
						<label style="display:none" class="choi" >
						<input type="hidden"id="callBackUserId" value="" /><input id="callBackUserName" type="text" class="formText" style="width:200px;" value="" />
						<span class="addMember" ><a id="userSelect" class="icon_add" href="javascript:void(0)">选择</a></span></span>
						</label>
						</h3>
					<%-- <table width="100%" border="0" cellpadding="0" cellspacing="0" class="inputTable">
						<tr>
							<th class="choi"  style="display:none"><label>选择坐席：</label></th>
							<td class="choi" style="display:none"><input type="hidden" id="callBackUserId" value="" /><input id="callBackUserName" type="text" class="formText" style="width:200px;" value="" /><span class="addMember" ><a id="userSelect" class="icon_add" href="javascript:void(0)">选择</a></span></td>
						</tr>
					</table> --%>
				</form>
				
				<h3 class="small_title">通话记录</h3>
				<div class="pl10 pr10" id="systemPhoneRecordDiv">
					<table width="100%" border="0" cellpadding="0" cellspacing="0"
						class="pretty" id="phoneRecordTable">
						<thead>
							<tr>
								<th class="num">序号</th>
								<th style="width:70px;">坐席工号</th>
								<th style="width:105px;">对方号码</th>
								<th style="width:60px;">呼叫类型</th>
								<th>通话时间</th>
								<th style="width:80px;">通话时长(秒)</th>
								<th style="width:80px;">通话录音</th>
								<th style="width:70px;">服务评价</th>
							</tr>
						</thead>
					</table>
				</div>
				<jsp:include page="processHistoryList.jsp" />
			</div>
		</div>
		<div class="buttonArea">
				<input name="" class="formButton_green" id="reply" value="提交"
					type="button" />
				<s:if test="#request.fromPage=='prev'">
				 <input name="" class="formButton_grey" value="关闭" onclick="window.top.closeCurrentTab();" type="button" />
				</s:if>
				<s:else>
					<input type="button" class="formButton_grey" value="取消" onclick="javascript:history.back();"/>
				</s:else>

		</div>
	</div>
	
</body>
</html>
