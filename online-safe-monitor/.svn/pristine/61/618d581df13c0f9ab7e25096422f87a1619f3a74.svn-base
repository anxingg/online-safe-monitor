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
	<style type="text/css">
		.uploadify{float:left;margin-top:7px;}
	</style>
	<script type="text/javascript">
		var basePath = "${ctx}";
	</script>
	<%-- <script type="text/javascript" src="${ctx}wh/jquery-easyui-1.4.3/jquery.min.js"></script> --%>
	<%-- <script type="text/javascript" src="${ctx}wh/jquery-easyui-1.4.3/jquery.easyui.min.js"></script> --%>
	<%-- <script type="text/javascript" src="${ctx}wh/plugins/datatable/jquery.dataTables.min.js"></script> --%>
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
	<script type="text/javascript" src="${ctx}wh/js/dangerChemicals/companyDangerChemicalsAdd.js"></script>
    
</head>
<body>
	<!-- 隐藏域：角色（1、政府； 2、企业） -->
	<input type="hidden" id="whroletype" value="<s:property value="#session.whroletype"/>" />
	<!-- 隐藏域：企业ID -->
	<input type="hidden" id="group_id" value="<s:property value="#session.adminUser.groupId"/>" />
	<!-- 隐藏域：ID -->
	<input type="hidden" id="vid" value="<s:property value="companyDangerChemicals.vid"/>" />
	
	<div class="bread-line">
		<label>当前位置：</label><a href="#">危险化学品</a>&gt;&nbsp;危险化学品<s:if test="companyDangerChemicals.vid != null">修改</s:if><s:else>新增</s:else>
	</div>
	<div class="formPage">
		<form id="form1">
		<div class="formbg">
			<div class="big_title"><s:if test="companyDangerChemicals.vid != null">修改</s:if><s:else>新增</s:else></div>
			<div class="content_form">
				<!-- <h2 class="small_title">基本信息</h2> -->
				<table width="100%" cellspacing="0" cellpadding="0" border="0" class="inputTable">
					<tbody>
						<tr>
							<th><em class="requireField">*</em><label>化学品名称：</label></th>
							<td><select id="dangerId">
								<option value="0">请选择</option>
								<s:iterator value="dangerChemicalsInfoList" id="dcl">
									<s:if test="companyDangerChemicals.dangerId == #dcl.vid">
									<option value="<s:property value="#dcl.vid"/>" selected="selected"><s:property value="#dcl.materialName"/></option>
									</s:if>
									<s:else>
									<option value="<s:property value="#dcl.vid"/>"><s:property value="#dcl.materialName"/></option>
									</s:else>
								</s:iterator>
							</select></td>
							<th><em class="requireField">*</em><label>存放地点：</label></th>
							<td><input type="text" class="formText" id="storagePlace" value="<s:property value="companyDangerChemicals.storagePlace"/>" valid="required" errmsg="wuhaiCompanyDangerChemicals.storagePlace_not_null" maxlength="32"/></td>
						</tr>
						<tr>
							<th><em class="requireField">*</em><label>数量（吨）：</label></th>
							<td><input type="text" class="formText" id="num" value="<fmt:formatNumber value="${companyDangerChemicals.num}" pattern="#0.00"/>" valid="required|isPositiveIntegerOrFloat" errmsg="wuhaiCompanyDangerChemicals.num_not_null|wuhaiCompanyDangerChemicals.num_not_PositiveIntegerOrFloat" maxlength="11"/></td>
							<th><em class="requireField">*</em><label>使用地点：</label></th>
							<td><input type="text" class="formText" id="userPlace" value="<s:property value="companyDangerChemicals.userPlace"/>" valid="required" errmsg="wuhaiCompanyDangerChemicals.userPlace_not_null" maxlength="32"/></td>
						</tr>
						<tr>
							<th><em class="requireField">*</em><label>危险性分类：</label></th>
							<td><input type="text" class="formText" id="riskType" value="<s:property value="companyDangerChemicals.riskType"/>" valid="required" errmsg="wuhaiCompanyDangerChemicals.riskType_not_null" maxlength="32"/></td>
							<th><em class="requireField">*</em><label>危规号：</label></th>
							<td><input type="text" class="formText" id="riskNum" value="<s:property value="companyDangerChemicals.riskNum"/>" valid="required" errmsg="wuhaiCompanyDangerChemicals.riskNum_not_null" maxlength="32" /></td>
						</tr>
						<tr>
							<th><em class="requireField">*</em><label>包装类别：</label></th>
							<td><input type="text" class="formText" id="packagingCategory" value="<s:property value="companyDangerChemicals.packagingCategory"/>" valid="required" errmsg="wuhaiCompanyDangerChemicals.packagingCategory_not_null" maxlength="32"/></td>
							<th><em class="requireField">*</em><label>登记号：</label></th>
							<td><input type="text" class="formText" id="registrationNO" value="<s:property value="companyDangerChemicals.registrationNO"/>" valid="required" errmsg="wuhaiCompanyDangerChemicals.registrationNO_not_null" maxlength="32"/></td>
						</tr>
						<tr>
							<th><label>技术说明书：</label></th>
							<td colspan="3">
								<input type="hidden" id="attachmentId" name="attachmentId" value="<s:property value="companyDangerChemicals.technicalId"/>"/>
								<input id="file_upload" name="fileupload" type="file" multiple="true" />
								<!-- 上传队列 -->
							    <div id="queue"  style="display:none;"></div>
						        <div class="annex">
							        <ul id="attachmentList">
							        <s:if test="technicalAttachment != null">
							        <li>
										<div class="icon"><em class="<s:property value="technicalAttachment.attacthSuffix"/>" ></em></div>
										<div class="txt" style="width:700px">
											<p><s:property value="technicalAttachment.attachName"/></p>
											<p><a style="cursor:pointer"  class="deleteAttachment">删除</a></p>
										</div>
									</li>
							        </s:if>
							        </ul>
							    </div>
							</td>
						</tr>
						<tr>
							<th><label>安全标签：</label></th>
							<td colspan="3">
								<input type="hidden" id="attachmentId2" name="attachmentId" value="<s:property value="companyDangerChemicals.securityId"/>"/>
								<input id="file_upload2" name="fileupload" type="file" multiple="true" />
								<!-- 上传队列 -->
							    <div id="queue2"  style="display:none;"></div>
						        <div class="annex">
							        <ul id="attachmentList2">
							        <s:if test="securityAttachment != null">
							        <li>
										<div class="icon"><em class="<s:property value="securityAttachment.attacthSuffix"/>" ></em></div>
										<div class="txt" style="width:700px">
											<p><s:property value="securityAttachment.attachName"/></p>
											<p><a style="cursor:pointer"  class="deleteAttachment">删除</a></p>
										</div>
									</li>
							        </s:if>
							        </ul>
							    </div>
							</td>
						</tr>
						<tr>
							<th><label>备注：</label></th>
							<td colspan="3">
							<textarea class="formTextarea" id="memo" maxlength="256" ><s:property value="companyDangerChemicals.memo"/></textarea>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
		<div class="buttonArea">
			<input type="button"class="formButton_green" value="确定" hidefocus=""/>
			<input type="button" value="取消" class="formButton_grey"  hidefocus="" onclick="javascript:history.go(-1);"/>
		</div>
		</form>
	</div>
	<div class="clear"></div>

</body>
</html>
