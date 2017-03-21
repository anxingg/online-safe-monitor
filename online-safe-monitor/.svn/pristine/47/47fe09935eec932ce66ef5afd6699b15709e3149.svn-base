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
	<title>安全生产费用提取</title>
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
	<script type="text/javascript" src="${ctx}wh/js/fee/feeExtractAdd.js"></script>
    
</head>
<body>
	<!-- 隐藏域：角色（1、政府； 2、企业） -->
	<input type="hidden" id="whroletype" value="<s:property value="#session.whroletype"/>" />
	<!-- 隐藏域：企业ID -->
	<input type="hidden" id="group_id" value="<s:property value="#session.adminUser.groupId"/>" />
	<!-- 隐藏域：安全生产资金余额 -->
	<input type="hidden" id="safetySurplusMoney" value="<fmt:formatNumber value="${whcompany.safetySurplusMoney}" pattern="#0.00"/>" />
	
	<div class="bread-line"><%-- <s:if test="safeAccident.vid != null">other</s:if><s:else>新增</s:else> --%>
		<label>当前位置：</label><a href="#">安全生产费用提取</a>&gt;&nbsp;安全生产费用提取新增
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
							<th><label>提取标准：</label></th>
							<td><span id="extractStand"><s:property value="whcompany.extractDescription"/></span></td>
						</tr>
						<tr>
							<th><label>上年度营业额（万元）：</label></th>
							<td><input maxlength="10" type="text" class="formText" id="turnover"  valid="required|isNumber" errmsg="wuhaifee.turnover_not_null|wuhaifee.turnover_not_positiveIntegerOrFloat" /></td>
						</tr>
						<tr>
							<th><label>提取时间：</label></th>
							<td><input type="text" class="formText Wdate" id="extractTime" onclick="WdatePicker({skin:'default',dateFmt:'yyyy-MM-dd'})" valid="required" errmsg="wuhaifee.extractTime_not_null" /></td>
						</tr>
						<tr>
							<th><label>本次提取（万元）：</label></th>
							<td><input maxlength="10" type="text" class="formText" id="extractMoney" valid="required|isNumber" errmsg="wuhaifee.extractMoney_not_null|wuhaifee.extractMoney_not_isNumber" /></td>
						</tr>
						<tr>
							<th><label>结存金额：</label></th>
							<td><span id="remainingSum"></span>&nbsp;万元</td>
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
