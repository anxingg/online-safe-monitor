<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>添加页面</title>
<jsp:include page="../../../common/flatHead.jsp" />
<link href="${ctx}/flat/css/reset.css" rel="stylesheet" type="text/css" />
<link href="${ctx }/flat/plugins/form/skins/form_default.css"
	rel="stylesheet" type="text/css" />
<link href="${ctx }/flat/plugins/annex/skins/annex_default.css"
	rel="stylesheet" type="text/css" />
<link href="${ctx }flat/plugins/tree/skins/tree_default.css"
	rel="stylesheet" type="text/css" />
<script type="text/javascript"
	src="${ctx}/logined/workflow/js/newDefine.js"></script>
<style type="text/css">
.input_new .pageTitle {
	padding: 8px 0;
	margin-bottom: 0;
}

.inputTable th {
	width: 125px
}
</style>
</head>
<body>
	<input type="hidden" id="tabName" value="${tabName}" />
	<input type="hidden" id="pid" value="${processAttributeId}" />
	<form action="${ctx}/workflow/process!saveProcess.action" method="post"
		id="newForm">
		<input type="hidden" name="processAttribute.companyId"
			value="${adminUser.companyId}" />
<div class="formPage"  >
	<div class="formbg">
		<div class="big_title">新建流程</div>
			<div class="content_form">
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="inputTable">
				<tr>
					<th><em class="requireField">*</em> <label>流程名称</label></th>
					<td><input name="processAttribute.processName"
						style="width: 54%" maxlength="30" valid="required"
						errmsg="process.process_name_not_null|process.process_name_not_repate"
						id="processName" type="text" class="formText" /></td>
					<th><em class="requireField">*</em> <label>流程分类</label></th>
					<td><select id="categoryIdSelect"
						name="processAttribute.categoryId">
							<option value="-1">--请选择--</option>
							<c:forEach items="${formCategories}" var="cate">
								<option value="${cate.categoryId}">
									${cate.categoryName}</option>
							</c:forEach>

					</select></td>
				</tr>
				<!--  
					<tr>
						<th>
							<em class="requireField">*</em><label>办理人选择方式</label>
						</th>
						<td colspan="3">
							<select name="processAttribute.selectUserMode">
								<option value="1" selected>
									按人员&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								</option>
								<option value="2">
									按部门
								</option>
							</select>
						</td>
					-->
				<%--<th>
							是否允许修改
						</th>
						<td >
							<SELECT name="processAttribute.processNameCanupdate">
								<OPTION selected value=1>
									允许修改
								</OPTION>
								<OPTION value=0>
									不允许修改
								</OPTION>
							</SELECT>
						</td>
						
					--%>
				</tr>

				<tr>
					<th>说明内容</th>
					<td colspan="3"><textarea name="processAttribute.directions"
							maxlength="200" style="behavior: url(maxlength.htc)"
							class="formTextarea" onkeydown="checklength(this);"
							id="directions"></textarea></td>
				</tr>
			</table>
			</div>
		</div>
			<div class="buttonArea">
				<input hideFocus="" value="确  定" type="button" id="sure"
					class="formButton_green" /> &nbsp;&nbsp;&nbsp;&nbsp; <input
					hideFocus="" id="cancel" value="取 消" type="button"
					class="formButton_grey">
			</div>
		</div>
		<div id="menuContent" class="menuContent"
			style="display: none; position: absolute;">
			<ul id="treeDemo" class="ztree"
				style="margin-top: 0; width: 180px; height: 300px;"></ul>
		</div>
	</form>
</body>
</html>