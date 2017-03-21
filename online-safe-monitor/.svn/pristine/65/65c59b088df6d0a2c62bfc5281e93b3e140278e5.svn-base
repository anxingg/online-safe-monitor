<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>修改</title>
<jsp:include page="../../../common/flatHead.jsp"/>
<link href="${ctx }flat/css/reset.css" rel="stylesheet" type="text/css" />
<link href="${ctx }flat/plugins/form/skins/form_default.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx }flat/js/base.js"></script>
<style type="text/css">
  .inputTable th{
    width:135px
  }
</style>
</head>
<body>
<form action="${ctx}/workflow/process!saveProcess.action" method="post" id="newForm">
<input type="hidden" name="option" value="update"/>
		<input type="hidden" name="processAttribute.id" id="pid" value="${processAttribute.id}"/>
		<input type="hidden" name="processAttribute.processDefineId" value="${processAttribute.processDefineId}"/>
		<input type="hidden" name="processAttribute.companyId" value="${processAttribute.companyId}"/>
		<input type="hidden" id="processState" name="processAttribute.processState" value="${processAttribute.processState}" />
		<input type="hidden" name="processAttribute.procsssDefinByJSON" value="${processAttribute.procsssDefinByJSON}"/>
		<input type="hidden" name="processAttribute.processDefineByXML" id="xmlDefine" value='${processAttribute.processDefineByXML}'/>
<div class="formPage">
  <div class="formbg">
    <div class="big_title">编辑流程</div>
		<table width="100%" border="0" cellpadding="0" cellspacing="0"  class="inputTable">
				<tr>
						<th><em class="requireField">*</em><label>流程名称</label></th>
						<td><input name="processAttribute.processName" style="width:54%" maxlength="30" valid="required" errmsg="process.process_name_not_null|process.process_name_not_repate" id="processName" value="${processAttribute.processName}" type="text" class="formText"  />
								</td>
						<th><em class="requireField">*</em><label>流程分类</label></th>
						<td><select name="processAttribute.categoryId">
							<c:forEach items="${formCategories}" var="cate">
							<option value="${cate.categoryId}" <c:if test="${cate.categoryId ==processAttribute.categoryId}">selected</c:if> >${cate.categoryName}</option>
						</c:forEach>
							
						</select></td>
				</tr>
				<!-- 	
				<tr>
						<th><em class="requireField">*</em><label>办理人选择方式</label></th>
						<td colspan="3"><select name="processAttribute.selectUserMode">
                                        <option value="1"  <c:if test="${processAttribute.selectUserMode == 1}">selected</c:if>>按人员&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</option>
                                        <option value="2"  <c:if test="${processAttribute.selectUserMode == 2}">selected</c:if>>按部门</option>
                                </select></td>
						<%--<th>是否允许修改</th><td>
						       <SELECT name="processAttribute.processNameCanupdate">
										<OPTION value="1" <c:if test="${processAttribute.processNameCanupdate == 1}">selected</c:if>>允许修改</OPTION>
										<OPTION value="0" <c:if test="${processAttribute.processNameCanupdate == 0}">selected</c:if>>不允许修改</OPTION>
								</SELECT></td>
				--%></tr>
				
				 -->
			   <tr><th>说明内容</th><td colspan="3"><textarea name="processAttribute.directions" maxlength="200" onkeydown="checklength(this);" id="directions"  class="formTextarea">${processAttribute.directions}</textarea></td></tr>
		</table>
	<!--	<table style="border="0" cellpadding="0" cellspacing="0"  class="inputTable" id="explainTable">
	

	<tr>
			<th>说明文档</th>
			<td>&nbsp;</td>
	</tr>
	<tr>
			<th>上传文档</th>
			<td><a href="" class="icon_add">添加附件</a></td>
	</tr>

	</table>-->
		<div class="buttonArea"> <input hideFocus="" value="确  定" type="button" id="sure" class="formButton_green" />&nbsp;&nbsp;<input hideFocus="" id="cancelUpdate" value="取 消" type="button" class="formButton_grey" /></div>
</div>
<div id="menuContent" class="menuContent" style="display:none; position: absolute;">
		<ul id="treeDemo" class="ztree" style="margin-top:0; width:180px; height: 			300px;"></ul>
</div>
</form>
</body>
<script type="text/javascript" src="${ctx}/logined/workflow/js/newDefine.js"></script>
<script type="text/javascript">
	if($("#xmlDefine").val()){
		$("#processName").attr("readonly","readonly");
	}
</script>
</html>