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
	<title>危险化学品</title>
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
	<script type="text/javascript" src="${ctx}wh/js/safeAccident/downloadAttachment.js"></script>
	<script type="text/javascript" src="${ctx}wh/js/dangerChemicals/companyDangerChemicalsDetail.js"></script>
    
</head>
<body>
	<!-- 隐藏域：角色（1、政府； 2、企业） -->
	<input type="hidden" id="whroletype" value="<s:property value="#session.whroletype"/>" />
	<!-- 隐藏域：企业ID -->
	<input type="hidden" id="group_id" value="<s:property value="#session.adminUser.groupId"/>" />
	<!-- 隐藏域：ID -->
	<input type="hidden" id="vid" value="<s:property value="companyDangerChemicals.vid"/>" />
	
	<div class="bread-line">
		<label>当前位置：</label><a href="#">危险化学品</a>&gt;&nbsp;危险化学品详情
	</div>
	<div class="formPage">
		<form id="form1">
		<div class="formbg">
			<div class="big_title">详情</div>
			<div class="content_form">
				<!-- <h2 class="small_title">基本信息</h2> -->
				<table width="100%" cellspacing="0" cellpadding="0" border="0" class="inputTable">
					<tbody>
						<tr>
							<th><label>化学品名称：</label></th>
							<td><s:property value="companyDangerChemicals.materialName"/></td>
							<th><label>存放地点：</label></th>
							<td><s:property value="companyDangerChemicals.storagePlace"/></td>
						</tr>
						<tr>
							<th><label>数量：</label></th>
							<td><fmt:formatNumber value="${companyDangerChemicals.num}" pattern="#0.00"/></td>
							<th><label>使用地点：</label></th>
							<td><s:property value="companyDangerChemicals.userPlace"/></td>
						</tr>
						<tr>
							<th><label>危险性分类：</label></th>
							<td><s:property value="companyDangerChemicals.riskType"/></td>
							<th><label>危规号：</label></th>
							<td><s:property value="companyDangerChemicals.riskNum"/></td>
						</tr>
						<tr>
							<th><label>包装类别：</label></th>
							<td><s:property value="companyDangerChemicals.packagingCategory"/></td>
							<th><label>登记号：</label></th>
							<td><s:property value="companyDangerChemicals.registrationNO"/></td>
						</tr>
						<tr>
							<th><label>技术说明书：</label></th>
							<td colspan="3">
								<s:if test="companyDangerChemicals.technicalId != null">
								<input type="hidden" id="technicalId" value="<s:property value="companyDangerChemicals.technicalId"/>"/>
								<a href="javascript:;" id="technicalName"><s:property value="companyDangerChemicals.technicalName"/></a>
								</s:if>
								<s:else>
								没有技术说明书。
								</s:else>
							</td>
						</tr>
						<tr>
							<th><label>安全标签：</label></th>
							<td colspan="3">
								<s:if test="companyDangerChemicals.securityId != null">
								<input type="hidden" id="securityId" value="<s:property value="companyDangerChemicals.securityId"/>"/>
								<a href="javascript:;" id="securityName"><s:property value="companyDangerChemicals.securityName"/></a>
								</s:if>
								<s:else>
								没有安全标签。
								</s:else>
							</td>
						</tr>
						<tr>
							<th><label>备注：</label></th>
							<td colspan="3"><s:property value="companyDangerChemicals.memo"/></td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
		<div class="buttonArea">
			<input type="button" value="返回" class="formButton_grey"  hidefocus="" onclick="javascript:history.go(-1);"/>
		</div>
		</form>
	</div>
	<div class="clear"></div>

</body>
</html>
