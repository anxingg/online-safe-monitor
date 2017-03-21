<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="cm" uri="/common-workflow" %>
<jsp:include page="../../../common/taglibs.jsp" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>工单回访</title>
<jsp:include page="../../../common/head.jsp" />
<link href="${ctx}common/css/reset.css" rel="stylesheet" type="text/css" />
<link href="${ctx}common/css/task.css" rel="stylesheet" type="text/css" />
<link href="${ctx}common/css/Reminder.css" rel="stylesheet" type="text/css" />
<link href="${ctx}common/plugins/form/skins/form_default.css" rel="stylesheet" type="text/css" />
<link href="${ctx}flat/plugins/form/skins/form_default.css" rel="stylesheet" type="text/css" />
<link href="${ctx}common/plugins/datatable/skins/datatable_default.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="${ctx}common/js/base.js"></script>
<script type="text/javascript" src="${ctx}plugins/datatable/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="${ctx}common/plugins/datatable/selecedForDatatablePagination.js"></script>
<script type="text/javascript" src="${ctx}common/plugins/dialog/artDialog.js?skin=default"></script>
<script type="text/javascript" src="${ctx}common/plugins/dialog/iframeTools.js"></script>
<script type="text/javascript" src="${ctx}js/common/placeholder.js"></script>


<style type="text/css">
.inputTable th {
	width: 112px;
}
</style>

<!-- 验证框架开始 -->
<script type="text/javascript"
	src="${ctx}js/common/validate_form.js?version=${version}"></script>
<script type="text/javascript"
	src="${ctx}js/common/showError.js?version=${version}"></script>
<!-- 验证框架结束 -->

<script type="text/javascript"
	src="${ctx}logined/workorderflow/js/customerCallList.js"></script>
<!-- 点击电话呼电话 -->
<script type="text/javascript"
	src="${ctx}logined/workorderflow/js/backPhone.js?version=${version}"></script>
<!-- 点击打开发送短信 页面 -->
<script type="text/javascript" src="${ctx}logined/workorderflow/js/openSendMessage.js?version=${version}"></script>
<!-- 通话记录 -->
<script type="text/javascript" src="${ctx}logined/workorderflow/js/phoneRecord.js"></script>

<script type="text/javascript">
	function callNameInfoSeat(vid){
		window.top.addTab("callNameInfoSeat"+vid,basePath+ "jbpmworkorder/callNameInfo.action?fromPage=seat&vid=" + vid
	+"&checkDetain=1","用户档案");
	}
	qy_lang.customerCall = {
	 customerBackSelect_not_null:"请选择未接通原因!"
	};
</script>
</head>
<body style="background:#f1f5f8">
<input type="hidden" id="instanceId" value="${instanceId }"/>
	<input type="hidden" value="<s:property value='#request.checkDetain'/>"
		id="checkDetain" />
	<input type="hidden" id="vid" value="${customerCallLog.vid }" />


	<div class="formPage">
		<div class="formbg">
			<div class="big_title" style="font-size:16px">
				<%-- <span class="fr">工单状态：<font class="txt_green">${workflowVar.stateShow}</font>
				</span>工单编号 ： ${customerCallLog.cclSn } --%>
				<span class="fr pr10">工单状态：<font style="color:#ff6600;">${workflowVar.stateShow}</font>
			  </span><span id="cclSn">工单编号 ：<font class="#888">${customerCallLog.cclSn }</font></span> 
			
				
			</div>
			<div class="content_form">
				<jsp:include page="customerCallLogProcess.jsp" />
				<h3 class="small_title">工单详情</h3>
				<form action="" name="customercallbackform1"
					id="customercallbackform1">
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
							<td id="recordUser">${customerCallLog.recordUser.userName }</td>
						</tr>
						<tr>
							<th><label>联系电话：</label>
							</th>
							<td id="callPhone"><span id="callPhoneTwo">${customerCallLog.phone}</span>
							 <span><em class="tel"id="phonePng" onclick="callCustomer()"></em>&nbsp;
							 	<em class="message" onclick="sendMessage(2)"></em>
							 </span>
							</td>
							<th><label>来电人姓名：</label>
							</th>
							<td id="uname">
								<a href="javascript:void(0);" onclick="callNameInfoSeat(${customerCallLog.vid})" style="margin-left:0px;">${customerCallLog.name }</a>
							</td>
						</tr>
						<tr>
							<th><label>工单类别：</label>
							</th>
							<td colspan="3" id="type"><s:if
									test="customerCallLog.type==1">咨询</s:if>
								<s:if test="customerCallLog.type==2">投诉</s:if>
								<s:if test="customerCallLog.type==3">建议</s:if>
								<s:if test="customerCallLog.type==4">举报坐席</s:if>
								<s:if test="customerCallLog.type==5">其他</s:if>
							</td>
						</tr>
						<s:if test="reportSeat!=null">
							<tr>
								<th><label>被举报坐席信息：</label></th>
								<td colspan="3"><s:property value="reportSeat.userName" />（<s:property value="reportSeat.loginName" />）</td>
							</tr>
						</s:if>
						<tr>
							<th><label>工单内容：</label>
							</th>
							<s:if test="customerCallLog.logInfo==''">
								<td colspan="3" id="content">无</td>
							</s:if>
							<s:else>
								<td colspan="3" id="content"><s:property value="customerCallLog.logInfo"/></td>
							</s:else>
						</tr>
						<tr>
				      <th><label>工单答复：</label></th>
				      <s:if test="customerCallLog.seatReplay=='' ">
				      	<td colspan="3">无</td>
				      </s:if>
				      <s:else>
				      	 <td colspan="3"><s:property value="customerCallLog.seatReplay"/></td>
				      </s:else>
				    </tr>
				     <c:if test="${ customerCallLog.replyInfo!=null}"> 
				     <tr>
				           <th><label>处理意见：</label></th>
				           <td colspan="3"> <s:property value="customerCallLog.replyInfo"/> </td>
				        </tr>
				    </c:if>
				
				
						 
						<%-- <tr>
							<th><label>答复时间</label>
							</th>
							<td colspan="3"><s:date format="yyyy-MM-dd HH:mm"
									name="#request.customerCallLog.answerTime" />
							</td>
						</tr>
						<tr>
							<th><label>答复内容</label>
							</th>
							<td colspan="3">${customerCallLog.replyInfo }</td>
						</tr> --%>
					</table>
					<h3 class="small_title">通话记录</h3><br />
				  <div class="pl10 pr10" id="systemPhoneRecordDiv" > 
						<table width="100%" border="0" cellpadding="0" cellspacing="0" class="pretty" id="phoneRecordTable">
							<thead>
						        <tr >
						          <th class="num">序号</th>
						          <th style="width:70px;">坐席工号</th>
						          <th style="width:105px;">对方号码</th>
						          <th style="width:60px;">呼叫类型</th>
						          <th  >通话时间</th>
						          <th style="width:80px;">通话时长(秒)</th>
						          <th style="width:80px;">通话录音</th>
						          <th style="width:70px;">服务评价</th>
						         </tr>
						      </thead>
						</table>
					</div>
						<h3 class="small_title">
							回访结果<span class="ml20">
								 <label class="radio" id="hiddenLable">
									<input type="radio" name="RadioGroup1" value="0"
									id="RadioGroup1_0" checked="checked" /> 成功</label>
								 <label class="radio" id="showLable"> <input type="radio" name="RadioGroup1"
									value="1" id="RadioGroup1_1" /> 不成功</label> </span>
						</h3>
						<table id="choiceDiv"  border="0" class="inputTable" >
							<tr>
								<th><label>备注：</label>
								</th>
								<td>
									<textarea id="callBackSuccessResult" name="callBackSuccessResult" class="formTextarea" cols="" rows="5" valid="textareaLength" errmsg="maxLength.answercontent_max_length"
						          		 onkeyup="checkTextarea(this)" onmouseup="checkTextarea(this)" maxNumber="1000"></textarea>
						          		 <span  style="float: right">0-1000字</span>
								</td>
							</tr>
						</table>
						<div class="cont_addr" style="display:none">
							<em class="requireField">*</em>
							<label>未接通原因：</label>
							<select id="failResult" valid="required" errmsg="customerCall.customerBackSelect_not_null">
								<option value="">请选择</option>
								<option value="1">无人接听</option>
								<option value="2">电话正忙</option>
								<!-- <option value="3">电话秘书</option> -->
								<option value="3">呼叫转移</option>
								<option value="4">电话关机</option>
								<option value="5">电话停机</option>
								<option value="6">空号</option>
								<option value="7">用户拒访</option>
							</select>
						</div>	
						<h3 class="small_title">
						流转信息<span class="ml20"><cm:showOperation processId="${workflowVar.processId }" nodeName="${currentTask.name }" /></span>
						</h3>
						<jsp:include page="processHistoryList.jsp" />
				</form>
			</div>
		</div>
		<div class="buttonArea">
			<input name="" class="formButton_green" id="cclBack" value="提交"
				type="button" />
			<input type="button" class="formButton_grey" value="关闭"
				onclick="window.top.closeCurrentTab();" />
		</div>
	</div>
</body>
</html>
