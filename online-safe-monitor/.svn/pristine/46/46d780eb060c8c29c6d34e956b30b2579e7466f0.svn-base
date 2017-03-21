<%@page import="java.net.URLDecoder"%>
<%@page import="cn.com.qytx.workflow.cfg.WorkflowGlobalCfg"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
    String src = request.getParameter("src");
%>
<%@taglib prefix="cm" uri="/common-workflow" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>新建工单</title>
<jsp:include page="../../../common/taglibs.jsp"/>
<%-- <jsp:include page="../../../common/head.jsp" /> --%>
<link href="${ctx}common/css/reset.css" rel="stylesheet" type="text/css" />

<link href="${ctx}common/plugins/datatable/skins/datatable_default.css" rel="stylesheet" type="text/css" />
<link href="${ctx}common/plugins/form/skins/form_default.css" rel="stylesheet" type="text/css" />
<%-- <link href="${ctx}flat/plugins/form/skins/form_default.css" rel="stylesheet" type="text/css" /> --%>
<!-- 这个js用来统计textarea 文本框中的字符数 -->
<script type="text/javascript" src="${ctx}common/js/CheckTextarea.js?version=${version}"></script>

<script type="text/javascript" src="${ctx}js/common/jquery-1.8.0.min.js"></script>
<style type="text/css">
.inputTable th{width:82px;}
.small_title span{ font-size:14px;}
</style>
<script type="text/javascript">
	var basePath = '${ctx}';
</script>
<!-- 验证框架开始 -->
<script type="text/javascript" src="${ctx}common/js/js_lang_cn.js?version=${version}"></script>
<script type="text/javascript" src="${ctx}js/common/validate_form.js?version=${version}"></script>
<script type="text/javascript" src="${ctx}js/common/showError.js?version=${version}"></script>
<!-- 验证框架结束 -->
<script type="text/javascript"
	src="${ctx}common/plugins/dialog/artDialog.js?skin=default"></script>
<script type="text/javascript" src="${ctx}flat/plugins/dialog/iframeTools.js?version=${version}"></script>

<!--来电弹屏的来电电话  显示通话时间 -->
<script type="text/javascript" src="${ctx}logined/workorderflow/js/call.js?version=${version}"></script>
<!--根据来电号码得到相关的工单 -->
<%-- <script type="text/javascript" src="${ctx}logined/workorderflow/js/getRelatedCustomerCallByPhone.js?version=${version}"></script> --%>
<!-- 点击电话呼电话 -->
<script type="text/javascript"
	src="${ctx}logined/workorderflow/js/backPhone.js?version=${version}"></script>
<style type="text/css">
.inputTable th{width:82px;}
.allshow {margin-left:20px; font-size:12px;}
a.allshow { color:#06c;}
a#allshow{background:url("${ctx}flat/images/arrow_downNew.png") no-repeat right center; width:65px;}
a#allhide{background:url("${ctx}flat/images/arrow_upNew.png") no-repeat right center; width:45px;}

.formTextarea2{width:96%;min-height:70px;padding:8px 10px;border:1px solid #e4e4e4;color:#555555;text-indent:10px;background:#fff;text-indent:10px;vertical-align:middle; line-height:22px;}
</style>

<!-- 引用了通讯录列表中的发送短信 -->
<script type="text/javascript" src="${ctx}hotline/addressbook/js/sendMsg.js?version=${version}"></script>

</head>
<body>
<%
	String instanceId = request.getParameter("instanceId");
	if(instanceId == null){
		instanceId = "";
	}
%>
<input type="hidden" id="instanceId" value="<%=instanceId %>"/>
<input type="hidden" id="processId" value="<%=request.getParameter("processId")%>"/>
<form method="post" name="form1" id="form1">
<div class="formPage">
<input type="hidden"  name="cclId" id="cclId"/>
<input type="hidden"  name="linkedId" id="linkedId"/>
<input type="hidden"  name="workflowName" id="workflowName" value="${workflowName }"/>
<!-- 留言电话 -->
<input type="hidden" id="voxMailPhone" value="${voxMail.phone}"/>
<!-- 留言Id -->
<input type="hidden" id="accessSourceId" value="${voxMail.vid }"/>
  <div class="formbg">
    <div class="big_title">新增工单</div>
    <div class="content_form">
      <s:if test="voxMail.vid!=null">
	      <h3 class="small_title">播放录音</h3>
	      <input type="hidden" id="path" value="${path }"/>
	      <input type="hidden" id="fileName" value="${fileName }"/>
	      <table width="100%" border="0" cellpadding="0" cellspacing="0"  class="inputTable">
        	<tr>
        		<td>c<span id="voxMailList"></span></td>
        	</tr>
          </table>
      </s:if>
      <h3 class="small_title">客户信息<a class="allshow" style="cursor:pointer" id="allshow">展开全部</a><a class="allshow" style="cursor:pointer; display:none;" id="allhide">收起</a>
      <s:if test="voxMail.vid!=null&&crms==1">
      	  <lable id="selectCrm" style="display:none">
	      	<span class="click_qh" onclick="selectCrms()">点击切换</span>
	      	<span class="cue">本号码有多个用户资料</span>
	      </lable>
      </s:if>
      <a   href="javascript:void(0);" class="view_url" id ="crmInfo" style="display:none" onclick="seeCrmInfo()">查看详情</a>
      </h3>
      <table width="100%" border="0" cellpadding="0" cellspacing="0"  class="inputTable">
        <tr>
          <th><em class="requireField">*</em><label>联系电话：</label></th>
          <td>
          <s:if test="voxMail.vid!=null">
          	<span id="callPhoneTwo">${voxMail.phone }</span>
          	<%-- <span><img src="${ctx}common/images/tel.png" class="ml20 tel" id="phonePng" onclick="callCustomer()" /></span> --%>
          	<span><em class="tel" id="phonePng" onclick="callCustomer()"></em></span><a href="javascript:sendMessage(${voxMail.phone})" class="message"></a>
          	<input type="hidden" value="${voxMail.phone }" id="uphone"/>
          	<!-- 添加crmId用来区别多个用户资料的切换 -->
          	<input type="hidden" id="crmId" value="${crm.vid }" />
          </s:if>
          <s:else>
          	<input name="" onblur="getCrmInfoByPhone();" class="formText" type="text" value="<s:property value="#request.phoneOne" />"
          	 	id="uphone" maxlength="12" onkeyup="this.value=this.value.replace(/[^0-9]/g,'')" />
          </s:else>
          </td>
          <th><label>姓名：</label></th>
          <td><input name="" value="" class="formText" type="text" id="uname"  maxlength="10" /></td>
        </tr>
        <tr>
          <th><label>性别：</label></th>
          <td>
          	<span id="genderHtml">
          		<label class="radio"><input name="gender" type="radio" checked="checked" value="1"/>男</label>
          	  	<label class="radio"><input name="gender" type="radio" value="0"/>女</label>
          	</span>
          </td>
          <th><label>年龄：</label></th>
	      <td><input name="" class="formText" type="text" id="age" maxlength="3"
				onkeyup="this.value=this.value.replace(/[^0-9]/g,'')" />
		  </td>
        </tr>
        <tr>
          <th><label>联系地址：</label></th>
          <td><input name="" class="formText" type="text" id="uaddress" maxlength="50" valid="required" errmsg="customerCall.uaddress_not_null"/></td>
          <th><label>工作单位：</label></th>
          <td><input class="formText" type="text" id="company" maxlength="50"/></td>
        </tr>
		</table>
		<div id="newclick" style="display:none;">
		<table width="100%" border="0" cellpadding="0" cellspacing="0"  class="inputTable" style="padding-top:0px;" >
        <tr>
          <th><label>备用号码：</label></th>
          <td><input name="" class="formText" type="text" value="<s:property value="#request.phoneTwo" />" id="umobile" maxlength="13" onkeyup="this.value=this.value.replace(/[^0-9]/g,'')"  /></td>
          <th><label>客户类别：</label></th>
          <td>
	          <span id="personTypeHtml">
	          	  <select id="personType">
		          	<option value="0">请选择</option>
		          	<option value="1">一般客户</option>
		          	<option value="2">VIP客户</option>
		          </select>
	          </span>
          </td>
        </tr>
        <tr>
          <th><label>身份证号：</label></th>
          <td><input name="" class="formText" type="text" id="cardId" maxlength="18" onkeyup="this.value=this.value.replace(/[^0-9xX]/g,'')"/></td>
          <th><label>户口所在地：</label></th>
          <td><input class="formText" type="text" id="hkAddress" maxlength="50"/></td>
        </tr>
        <tr>
          <th><label>职务：</label></th>
          <td><input class="formText" type="text" id="job" maxlength="20"/></td>
          <th><label>月收入：</label></th>
          <td><input class="formText" type="text" id="receiveMoney" maxlength="10" onkeyup="this.value=this.value.replace(/[^0-9.]/g,'')"/></td>
        </tr>
        <tr>
          <th><label>备注：</label></th>
          <td colspan="3">
          	<textarea id="note" name="note" class="formTextarea2 area area01" cols="120" rows="5" 
          		 	fmaxlength="1000"></textarea>
          		 <span class="msg-text" style="float: right">0-1000字</span>
          </td>
        </tr>
      </table></div>
      <h3 class="small_title">工单信息<%-- <span class="add_gd" onclick="addCCL()">新增工单</span> --%></h3>
      <table border="0" class="inputTable" name="ccl">
      	<tr>
			<th><em class="requireField">*</em><label>受理方式：</label>
			</th>
			<td>
				<s:if test="voxMail.vid!=null">
					<select name="accessType" disabled="disabled">
					     <option value="0">请选择</option>
						<option value="1">电话受理</option>
						<option value="2">短信受理</option>
						<option value="3" selected="selected">录音受理</option>
					</select>
				</s:if>
				<s:else>
					<select name="accessType" id="accessType"    valid="required" errmsg="customerCall.accessType_not_null">
					<option value="0">请选择</option>
						<option value="1" >电话受理</option>
						<option value="2">短信受理</option>
						<option value="3">录音受理</option>
					</select>
				</s:else>
			</td>
			<th><em class="requireField">*</em><label>业务类别：</label>
			</th>
			<td><select id="businessType" name="businessType" valid="required" errmsg="customerCall.businessType_not_null">
      		                    <option value="">请选择</option>
                        	</select>
			</td>
		
		</tr>
        <tr>
			<th><em class="requireField">*</em><label>工单类别：</label></th>
			<td ><select name="type" valid="required" errmsg="customerCall.type_not_null">
				<option value="0">请选择</option>
			<option value="1">咨询</option><option value="2">投诉</option><option value="3">建议</option><option value="4">举报坐席</option><option value="5">其他</option>
			</select></td>
			<th class="selectReportSeat" style="display: none;" ><label>选择坐席：</label></th>
			<td  class="selectReportSeat" style="display: none;" >
				<input type="hidden" name="reportSeatUserId" id="reportSeatUserId" />
				<input name="reportSeatName" class="formText" id="reportSeatName" readonly="readonly" type="text"   style="width:245px;"/>
				<span class="addMember auto_addMember"><a name="userSelect"  id="jbuserSelect" class="icon_add" href="javascript:void(0)">选择</a></span>
			</td>
			
			
        </tr>
        <tr>
          <th><label>工单内容：</label></th>
          <td colspan="3">
          	<textarea name="content" class="formTextarea2 area area01" cols="120" rows="5"  id="cclContent"
          		 	fmaxlength="1000"></textarea>
          			<!-- valid="required|textareaLength" errmsg="customerCall.content_not_null|maxLength.answercontent_max_length" -->
          		 <span class="msg-text" style="float: right">0-1000字</span>
          </td>
        </tr>
        	<tr>
							<th><label>工单答复：</label>
							</th>
							<td colspan="3">
								<textarea name="seatReplay" class="formTextarea2 area area01" cols="120" rows="5" id="cclSeatReplay"
									fmaxlength="1000"></textarea>
									<!-- valid="required|textareaLength" errmsg="customerCall.seatReplay_not_null|maxLength.answercontent_max_length" -->
								<span class="msg-text" style="float: right">0-1000字</span>
							</td>
						</tr>
				<!-- 		<tr>
							
							<th class="choi"  style="display:none"><label>选择坐席：</label></th>
							<td class="choi" style="display:none">
							<input type="hidden" id="callBackUserId" value="" />
							<input id="callBackUserName" type="text" class="formText" style="width:200px;" value="" />
							<span class="addMember" >
							<a id="userSelect" class="icon_add" href="javascript:void(0)">选择</a></span></td>
						</tr> -->
      </table>
     
		
					<h3 class="small_title">
						流转信息<span class="ml20"><cm:showOperation processId='<%=Integer.parseInt(request.getParameter("processId")) %>' nodeName="发起流程" />
						</span>
						<label class="choi" style="display:none">选择坐席：</label>
						<label style="display:none" class="choi" >
							<input type="hidden" id="callBackUserId" value="" />
							<input id="callBackUserName" type="text" readonly="readonly" class="formText" style="width:200px;" value="" />
							<span class="addMember" >
							<a id="userSelect" class="icon_add" href="javascript:void(0)">选择</a></span>
						</label>
					
						
					</h3>
							
							
						
			
					
    </div>
  </div>
  <div class="buttonArea">
    <input type="button" id="new_customercall" class="formButton_green" value="提交" />
  </div>
</div>
</form>
</body>
</html>

