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
	<title>危险化学品目录</title>
	<%-- <link rel="stylesheet" type="text/css" href="${ctx}wh/jquery-easyui-1.4.3/themes/default/easyui.css"/> --%>
	<%-- <link rel="stylesheet" type="text/css" href="${ctx}wh/jquery-easyui-1.4.3/themes/icon.css"/> --%>
	<%-- <link rel="stylesheet" type="text/css" href="${ctx}wh/css/style.css"/> --%>
	<!-- <link rel="stylesheet" type="text/css" href="plugins/form/skins/form_default.css" /> -->
	<link href="${ctx}wh/plugins/datatable/skins/datatable_default.css?version=${version}" rel="stylesheet" type="text/css"/>
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
	<script type="text/javascript" src="${ctx}wh/js/safeAccident/initType.js"></script>
	<script type="text/javascript" src="${ctx}wh/js/dangerChemicals/chemicalsDirectoryAdd.js"></script>
    
</head>
<body>
	<!-- 隐藏域：角色（1、政府； 2、企业） -->
	<input type="hidden" id="whroletype" value="<s:property value="#session.whroletype"/>" />
	<!-- 隐藏域：企业ID -->
	<input type="hidden" id="group_id" value="<s:property value="#session.adminUser.groupId"/>" />
	<!-- 隐藏域：主键ID -->
	<input type="hidden" id="vid" value="<s:property value="chemicalsDirectory.vid"/>" />
	
	<div class="bread-line">
		<label>当前位置：</label><a href="#">危险化学品</a>&gt;&nbsp;危险化学品目录<s:if test="chemicalsDirectory.vid != null">修改</s:if><s:else>新增</s:else>
	</div>
	<div class="formPage">
		<form id="form1">
		<div class="formbg">
			<div class="big_title"><s:if test="chemicalsDirectory.vid != null">修改</s:if><s:else>新增</s:else></div>
			<div class="content_form">
				<table width="100%" cellspacing="0" cellpadding="0" border="0" class="inputTable">
					<tbody>
						<tr>
							<th><em class="requireField">*</em><label>编号：</label></th>
							<td><input type="text" class="formText" id="code" value="<s:property value="chemicalsDirectory.code"/>" valid="required" errmsg="wuhaiChemicalsDirectory.code_not_null" maxlength="32"/></td>
						</tr>
						<tr>
							<th><em class="requireField">*</em><label>品名：</label></th>
							<td ><input type="text" class="formText" id="materialName" value="<s:property value="chemicalsDirectory.materialName"/>" valid="required" errmsg="wuhaiChemicalsDirectory.materialName_not_null" maxlength="32"/></td>
						</tr>
						<tr>
							<th><em class="requireField">*</em><label>别名：</label></th>
							<td><input type="text" class="formText" id="molecularFormula" value="<s:property value="chemicalsDirectory.molecularFormula"/>" valid="required" errmsg="wuhaiChemicalsDirectory.molecularFormula_not_null" maxlength="32"/></td>
						</tr>
						<tr>
							<th><em class="requireField">*</em><label>CAS号：</label></th>
							<td><input type="text" class="formText" id="cas" value="<s:property value="chemicalsDirectory.cas"/>" valid="required" errmsg="wuhaiChemicalsDirectory.cas_not_null" maxlength="32"/></td>
						</tr>
						<tr>
							<th><label>备注：</label></th>
							<td colspan="3">
								<textarea class="formTextarea" id="other" maxlength="256" ><s:property value="chemicalsDirectory.other"/></textarea>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
		<div class="buttonArea">
			<input type="button" class="formButton_green" value="确定" />
			<input type="button" class="formButton_grey"   value="取消" onclick="javascript:history.go(-1);"/>
		</div>
		</form>
	</div>
	<div class="clear"></div>
</body>
</html>
