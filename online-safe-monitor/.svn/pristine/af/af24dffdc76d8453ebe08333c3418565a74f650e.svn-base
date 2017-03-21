<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<jsp:include page="../../../common/taglibs.jsp" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
		<script type="text/javascript" src="${ctx}flat/js/base.js"></script>
		<script type="text/javascript" src="${ctx}plugins/datatable/jquery.dataTables.min.js"></script>
		<link href="${ctx}flat/plugins/form/skins/form_default.css"
			rel="stylesheet" type="text/css" />
		<script type="text/javascript"
			src="${ctx}flat/plugins/dialog/artDialog.js?skin=default"></script>
		<script type="text/javascript"
			src="${ctx}flat/plugins/dialog/iframeTools.js"></script>
		<style type="text/css">
		.inputTable th {
			width: 112px;
		}
		.inputTable td{
			padding-top:3px;
		}
		</style>
		<!-- 通话记录 -->
		<script type="text/javascript" src="${ctx}logined/workorderflow/js/phoneRecord.js"></script>
		<!--业务类别  -->
		<script type="text/javascript" src="${ctx}logined/workorderflow/js/customerCallDetail_back.js"></script>
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
	
	<input type="hidden" value="<%=request.getParameter("fromPage") %>" id="fromPage"/>
	<input type="hidden" value="${customerCallLog.vid }" id="vid"/>
	
	<div class="formPage">
		<div class="formbg">
				<div class="big_title" style="font-size:16px">
					工单编号  ：  <font class="#888">${customerCallLog.cclSn }</font>
					<span class="fr pr10">工单状态：
						<font style="color:#ff6600;">${workflowVar.stateShow }</font>
					</span>
				</div>
			<s:else>
				<div class="big_title" >
					<span>工单查看</span>
				</div>
			</s:else>
			<div class="content_form">
					<jsp:include page="customerCallLogProcess.jsp" />
				<h3 class="small_title">工单详情</h3>
				<table width="100%" border="0" cellpadding="0" cellspacing="0"
					class="inputTable">
					<s:if test="#request.fromPage=='prev'">
						<tr>
					      <th><label>受理时间：</label></th>
					      <td><s:date format="yyyy-MM-dd HH:mm" name="#request.customerCallLog.recordTime"/></td>
					      <th><label>受理人员：</label></th>
					      <td>${customerCallLog.recordUser.userName }</td>
					    </tr>
					    <tr>
					      <th><label>联系电话：</label></th>
					      <td>${customerCallLog.phone }</td>
					      <th><label>来电人姓名：</label></th>
					      <td>
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
					    </tr>
					   	<tr>
					   		<th><label>受理方式：</label></th>
					    	<td id="accessType"><s:if
									test="customerCallLog.accessType==1">电话受理</s:if> <s:if
									test="customerCallLog.accessType==2">短信受理</s:if> <s:if
									test="customerCallLog.accessType==3">录音受理</s:if> </td>
					   		<th><label>业务类别：</label></th>
					    	<td id="businessType"><input id="businessTypeId" value="${customerCallLog.businessType}" type="hidden"/></td>
					   	</tr>
						 <tr>
					      <th><label>工单类别：</label></th>
					      <td ><s:if test="customerCallLog.type==1">咨询</s:if>
					      <s:if test="customerCallLog.type==2">投诉</s:if><s:if test="customerCallLog.type==3">建议</s:if>
					      <s:if test="customerCallLog.type==4">举报坐席</s:if>
					      <s:if test="customerCallLog.type==5">其他</s:if></td>
					    </tr>
					    <s:if test="reportSeat!=null">
							<tr>
								<th><label>被举报坐席信息：</label></th>
								<td colspan="3"><s:if test="reportSeat.userName != null"><s:property value="reportSeat.userName" />（<s:property value="reportSeat.loginName" />）</s:if></td>
							</tr>
						</s:if>
					</s:if>
					<s:else>
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
								<a href="javascript:void(0);" onclick="callNameInfoBack(${customerCallLog.vid});" style="margin-left:0px;">${customerCallLog.name }</a>
								</s:if>
								<s:else>
								<a href="javascript:void(0);" onclick="callNameInfoBack(${customerCallLog.vid});" style="margin-left:0px;">无</a>
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
					    	<td id="businessType"><input id="businessTypeId" value="${customerCallLog.businessType}" type="hidden"/></td>
					    	<th><label>工单类别：</label></th>
							<td  id="type"><s:if
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
					</s:else>
				
					 <tr>
				      <th><label>工单内容：</label></th>
				      <td colspan="3"><s:if test="customerCallLog.logInfo != ''"><s:property value="customerCallLog.logInfo"/></s:if><s:else>无</s:else></td>
				    </tr>
					 <tr>
				      <th><label>工单答复：</label></th>
				      <td colspan="3"><s:if test="customerCallLog.seatReplay != ''"><s:property value="customerCallLog.seatReplay"/></s:if><s:else>无</s:else></td>
				    </tr>
				    <s:if test="customerCallLog.answerTime!=null">
					 <tr>
				      <th><label>处理意见：</label></th>
				      <td colspan="3"> <s:property value="customerCallLog.replyInfo"/> </td>
				    </tr>
				    </s:if>
				    <s:if test="customerCallLog.callBackTime!=null">
				    			 <tr>
				      <th><label>回访时间：</label></th>
				      <td><s:date format="yyyy-MM-dd HH:mm" name="#request.customerCallLog.callBackTime"/></td>
				      <th><label>回访结果：</label></th>
				      <td><s:if test="customerCallLog.visitResult==0">成功</s:if><s:if test="customerCallLog.visitResult==1">不成功</s:if></td>
				    </tr>
					 <tr>
				      <th><label>备注：  </label></th>
				      <td colspan="3">
				      <s:if test="customerCallLog.visitResult==0">
				      <s:property value="customerCallLog.callBackSuccessResult"/> 
				      </s:if>    
				      <s:if test="customerCallLog.visitResult==1">
				     	 <s:if test="customerCallLog.visitFaildReason==1">无人接听</s:if>
				      	 <s:if test="customerCallLog.visitFaildReason==2">电话正忙</s:if>
				      	 <s:if test="customerCallLog.visitFaildReason==3">呼叫转移</s:if>
				      	 <s:if test="customerCallLog.visitFaildReason==4">电话关机</s:if>
				      	 <s:if test="customerCallLog.visitFaildReason==5">电话停机</s:if>
				      	 <s:if test="customerCallLog.visitFaildReason==6">空号</s:if>
				      	 <s:if test="customerCallLog.visitFaildReason==7">用户拒访</s:if>
				      </s:if>
					</td>
				    </tr>
				    
				    </s:if>
		
				    
				    
				     <s:if test="customerCallLog.stateStr=='已办结'">
					 <tr>
					<th><label>办结人员：</label></th>
				      <td>${customerCallLog.dealoverUser.userName }</td>
				      <th><label>办结时间：</label></th>
				      <td><s:date format="yyyy-MM-dd HH:mm" name="#request.customerCallLog.dealOverTime"/></td>
				      
				    </tr>
					 <tr>
				      <th><label>办结结果：</label></th>
				      <td colspan="3"><s:property value="customerCallLog.dealOverResult"/></td>
				    </tr>
				    </s:if>
				</table>
				<h3 class="small_title">通话记录</h3>
				  <div class="pl10 pr10" id="systemPhoneRecordDiv" > 
						<table width="100%" border="0" cellpadding="0" cellspacing="0" class="pretty" id="phoneRecordTable">
							<thead>
						        <tr >
						          <th class="num">序号</th>
						          <th style="width:70px;">坐席工号</th>
						          <th style="width:105px;">对方号码</th>
						          <th style="width:60px;">呼叫类型</th>
						          <th style="width:150px;">通话时间</th>
						          <th style="width:70px;">通话时长(秒)</th>
						          <th style="width:80px;">通话录音</th>
						          <th style="width:70px;">服务评价</th>
						         </tr>
						      </thead>
						</table>
					</div>
					<jsp:include page="processHistoryList.jsp" />
			</div>
		</div>
		<!-- 李贺修改 改为弹出新页面 -->
		<s:if test="#request.fromPage=='prev'">
			<div class="buttonArea">
				<input type="button" class="formButton_grey" value="关闭"
					onclick="window.top.closeCurrentTab();" />
			</div>
		</s:if>
		<s:else>
			<div class="buttonArea">
				<input type="button" class="formButton_grey" value="返回"
					onclick="javascript:history.back();" />
				<%-- <span class="blue">点击回到工单列表</span> --%>
			</div>
		</s:else>
	</div>
	</body>
</html>
