<%@ page language="java" import="java.util.*"  contentType="text/html; charset=UTF-8" pageEncoding="utf-8"  %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../../../common/taglibs.jsp"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<jsp:include page="../head.jsp" />
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>安全生产事故</title>
	<%-- <link rel="stylesheet" type="text/css" href="${ctx}wh/jquery-easyui-1.4.3/themes/default/easyui.css"/> --%>
	<%-- <link rel="stylesheet" type="text/css" href="${ctx}wh/jquery-easyui-1.4.3/themes/icon.css"/> --%>
	<%-- <link rel="stylesheet" type="text/css" href="${ctx}wh/css/style.css"/> --%>
	<!-- <link rel="stylesheet" type="text/css" href="plugins/form/skins/form_default.css" /> -->
	<script type="text/javascript">
		var basePath = "${ctx}";
	</script>
	<%-- <script type="text/javascript" src="${ctx}wh/jquery-easyui-1.4.3/jquery.min.js"></script> --%>
	<%-- <script type="text/javascript" src="${ctx}wh/jquery-easyui-1.4.3/jquery.easyui.min.js"></script> --%>
	<%-- <script type="text/javascript" src="${ctx}wh/plugins/datatable/jquery.dataTables.min.js"></script> --%>
	<script type="text/javascript" src="${ctx}wh/js/safeAccident/initType.js"></script>
	<script type="text/javascript" src="${ctx}wh/js/safeAccident/downloadAttachment.js"></script>
	<script type="text/javascript" src="${ctx}wh/js/safeAccident/safeAccidentDetail.js"></script>
    
</head>
<body>
	<!-- 隐藏域：角色（1、政府； 2、企业） -->
	<input type="hidden" id="whroletype" value="<s:property value="#session.whroletype"/>" />
	<!-- 隐藏域：企业ID -->
	<input type="hidden" id="group_id" value="<s:property value="#session.adminUser.groupId"/>" />
	<!-- 隐藏域：ID -->
	<input type="hidden" id="vid" value="<s:property value="safeAccident.vid"/>"/>
	<!-- 隐藏域：安全生产事故的性质 -->
	<input type="hidden" id="hiddenAccidentCharacter" value="<s:property value="safeAccident.accidentCharacter"/>"/>
	<!-- 隐藏域：操作类型（2、修改； 4、查看） -->
	<input type="hidden" id="operation" value="<s:property value="operation"/>"/>
	
	<div class="bread-line"><%-- <s:if test="safeAccident.vid != null">other</s:if><s:else>新增</s:else> --%>
		<label>当前位置：</label><a href="#">安全生产事故</a>&gt;&nbsp;安全生产事故<s:if test="safeAccident.vid != null"><s:if test="operation==4">详情</s:if><s:else>修改</s:else></s:if><s:else>新增</s:else>
	</div>
	<div class="formPage">
		<div class="formbg">
			<div class="big_title"><s:if test="safeAccident.vid != null"><s:if test="operation==4">详情</s:if><s:else>修改</s:else></s:if><s:else>新增</s:else></div>
			<div class="content_form">
				<!-- <h2 class="small_title">基本信息</h2> -->
				<table width="100%" cellspacing="0" cellpadding="0" border="0" class="inputTable">
					<tbody>
						<tr>
							<th><label>事故名称：</label></th>
							<td><s:property value="safeAccident.accidentName"/></td>
							<th><label>事故调查人/部门：</label></th>
							<td><s:property value="safeAccident.responsible"/></td>
						</tr>
						<tr>
							<th><label>事故发生时间：</label></th>
							<td colspan="3"><s:date format="yyyy-MM-dd" name="safeAccident.occurredTime" /></td>
						</tr>
						<tr>
							<th><label>事故性质：</label></th>
							<td><s:property value="accidentCharacterName"/></td>
							<th><label>事故地点：</label></th>
							<td><s:property value="safeAccident.occurredAddress"/></td>
						</tr>
						<tr>
							<th><label>事故简介：</label></th>
							<td colspan="3"><s:property value="safeAccident.occurredDescription"/></td>
						</tr>
						<tr>
							<th><label>事故原因：</label></th>
							<td colspan="3"><s:property value="safeAccident.occurredReason"/></td>
						</tr>
						<tr>
							<th><label>事故后果：</label></th>
							<td colspan="3"><s:property value="safeAccident.occurredConsequence"/></td>
						</tr>
						<tr>
							<th><label>事故处理情况：</label></th>
							<td colspan="3"><s:property value="safeAccident.processCondition"/></td>
						</tr>
						<tr>
							<th><label>备注：</label></th>
							<td colspan="3"><s:property value="safeAccident.memo"/></td>
						</tr>
						<tr>
							<th><label>事故报告：</label></th>
							<td>
								<s:if test="attachment != null">
								<input type="hidden" id="attachmentId" value="<s:property value="attachment.id"/>"/>
								<a href="javascript:;" id="attachmentName"><s:property value="attachment.attachName"/></a>
								</s:if>
								<s:else>
								没有事故报告。
								</s:else>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
		<div class="buttonArea">
			<s:if test="operation==4">
			<input type="button" value="返回" class="formButton_grey"  hidefocus="" onclick="goback();return false;"/>
			</s:if>
			<s:else>
			<input type="button"class="formButton_green" value="确定" hidefocus=""/>
			<input type="button" value="取消" class="formButton_grey"  hidefocus="" onclick="goback();return false;"/>
			</s:else>
		</div>
	</div>
	<div class="clear"></div>

</body>
</html>
