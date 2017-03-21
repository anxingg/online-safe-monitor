<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<jsp:include page="../../common/taglibs.jsp" />
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>编辑队列</title>
	<%-- <jsp:include page="../../common/head.jsp" /> --%>
	<link href="${ctx}common/css/reset.css" rel="stylesheet" type="text/css" />
	<link href="${ctx}common/plugins/form/skins/form_default.css" rel="stylesheet" type="text/css" />
	<link href="${ctx}common/plugins/annex/skins/annex_default.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript">
		var basePath = '${ctx}';
	</script>
	<script type="text/javascript" src="${ctx}js/common/jquery-1.8.0.min.js"></script>
	<script type="text/javascript" src="${ctx}common/js/base.js"></script>
	<!-- 表单验证 开始 -->
	<script type="text/javascript" src="${ctx}js/common/validate_form.js"></script>
	<script type="text/javascript" src="${ctx}js/common/showError.js"></script>
	<script type="text/javascript" src="${ctx}common/js/js_lang_cn.js"></script>
	<!-- 表单验证 结束 -->
	
	<!-- 选择人员的js -->
	<script type="text/javascript" src="${ctx}common/plugins/dialog/artDialog.js?skin=default"></script>
	<script type="text/javascript" src="${ctx}flat/plugins/dialog/iframeTools.js?version=${version}"></script>
	<script type="text/javascript" src="${ctx}js/logined/outCallTask/selectSeatUser.js?version=${version}"></script>
	
	<script type="text/javascript" src="${ctx}/js/logined/queue/queueEdit.js"></script>
</head>
<body >
	<form  id="queueEditForm" name="queueEditForm">
		<input type="hidden" value="<s:property value="msObj.vid"/>" id="vid"/>
		<div class="formPage" >
		<div class="formbg" >
			<div class="big_title">修改队列</div>
			<div class="content_form">
			<table border="0" class="inputTable">
				<tr>
					<th><label>按键状态：</label></th>
					<td><s:if test="msObj.isEnable==1">不可用</s:if><s:else>可用</s:else></td>
				</tr>
				<tr>
					<th><span class="requireField">*</span><label>队列说明：</label></th>
					<td><input type="text" class="formText"  value="<s:property value="msObj.serviceName"/>" id="serviceName"
							maxlength="500"
							valid="required" errmsg="queue.serviceName_not_null"/></td>
				</tr>
				<tr>
					<th><label>队列成员：</label></th>
					<td>
						<textarea disabled="disabled" id="userNameList" cols="" rows=""  class="formTextarea" style="width:88%"><s:property value="userNameList"/></textarea>
						<input type="hidden" value="<s:property value="userIdList"/>" id="userIdList" />
						<span class="addMember" id="adMemberSpan"><a href="javascript:void(0);" class="icon_add  " id="addSeat">添加</a><a href="javascript:void(0);" class="icon_clear" id="clearAllSeat">清空</a></span>
					</td>
				</tr>
			</table>
		</div>
		</div>
			<div class="buttonArea">
				<input type="button" class="formButton_green" value="保 存" id="submitButton"  />
				<input name="" type="button" class="formButton_grey" onclick="javascript:history.back()" value="返 回"  />
			</div>
			</div>
		
	</form>
</body>
</html>