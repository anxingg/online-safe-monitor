<%@ page language="java" import="java.util.*"  contentType="text/html; charset=UTF-8" pageEncoding="utf-8"  %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:include page="../../../common/taglibs.jsp"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<jsp:include page="../head.jsp" />
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>安全生产费用使用</title>
	<style type="text/css">
		.uploadify{float:left;margin-top:7px;}
	</style>
	<script type="text/javascript">
		var basePath = "${ctx}";
	</script>
	<!-- 表示验证 start -->
	<script type="text/javascript" src="${ctx}js/common/validate_form.js"></script>
	<script type="text/javascript" src="${ctx}js/common/showError.js"></script>
	<script type="text/javascript" src="${ctx}common/js/js_lang_cn.js"></script>
	<!-- 表示验证 end -->
	<!-- 限制textarea框的最大长度 start -->
	<script type="text/javascript" src="${ctx}wh/js/safeAccident/behaviour_min.js"></script>
	<script type="text/javascript" src="${ctx}wh/js/safeAccident/textarea_maxlen_min.js"></script>
	<!-- 限制textarea框的最大长度 end -->
	<script type="text/javascript" src="${ctx}wh/js/safeAccident/verifyEmpty.js"></script>
	<script type="text/javascript" src="${ctx}wh/js/fee/feeUsedAdd.js"></script>
    
</head>
<body>
	<!-- 隐藏域：角色（1、政府； 2、企业） -->
	<input type="hidden" id="whroletype" value="<s:property value="#session.whroletype"/>" />
	<!-- 隐藏域：企业ID -->
	<input type="hidden" id="group_id" value="<s:property value="#session.adminUser.groupId"/>" />
	<!-- 隐藏域：安全生产资金余额 -->
	<input type="hidden" id="safetySurplusMoney" value="<fmt:formatNumber value="${whcompany.safetySurplusMoney}" pattern="#0.00"/>" />
	
	<div class="bread-line"><%-- <s:if test="safeAccident.vid != null">other</s:if><s:else>新增</s:else> --%>
		<label>当前位置：</label><a href="#">安全生产费用使用</a>&gt;&nbsp;安全生产费用使用新增
	</div>
	<div class="formPage">
		<form id="form1">
		<div class="formbg">
			<div class="big_title">新增</div>
			<div class="content_form">
				<!-- <h2 class="small_title">基本信息</h2> -->
				<table width="100%" cellspacing="0" cellpadding="0" border="0" class="inputTable">
					<tbody>
						<tr>
							<th><label>使用日期：</label></th>
							<td><input type="text" class="formText Wdate" id="useTime" onclick="WdatePicker({skin:'default',dateFmt:'yyyy-MM-dd'})" valid="required" errmsg="wuhaifee.useTime_not_null" /></td>
						</tr>
						<tr>
							<th><label>资金用向：</label></th>
							<td>
								<select id="useDirection1">
									<option value="" >请选择</option>
									<s:iterator value="feeTypeMap" id="entry">
										<option value="<s:property value="key"/>" ><s:property value="value"/></option>
									</s:iterator>
								</select>
								&nbsp;
								<select id="useDirection2">
									<option value="" >请选择</option>
								</select>
							</td>
						</tr>
						<tr>
							<th><label>费用提取金额（万元）：</label></th>
							<td><input maxlength="10" type="text" class="formText" style="width: 50%" id="useMoney" valid="required|isNumber" errmsg="wuhaifee.useMoney_not_null|wuhaifee.useMoney_not_isNumber" />
								&nbsp;<label class=""><input type="checkbox" id="plus"/>补正</label>
							</td>
						</tr>
						<tr>
							<th><label>结存金额：</label></th>
							<td><span id="remainingSum"></span>&nbsp;万元</td>
						</tr>
						<tr>
							<th><label>备注：</label></th>
							<td><textarea rows="5" class="formTextarea" id="memo" maxlength="256" ></textarea></td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
		<div class="buttonArea">
			<input type="button" value="确定" class="formButton_green" />
			<input type="button" value="取消" class="formButton_grey" onclick="javascript:history.go(-1);"/>
		</div>
		</form>
	</div>
	<div class="clear"></div>

</body>
</html>
