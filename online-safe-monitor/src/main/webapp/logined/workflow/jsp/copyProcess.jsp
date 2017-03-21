<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>复制</title>
<jsp:include page="customJPDLHead.jsp"/>
<script type="text/javascript" src="${ctx}/logined/workflow/js/copyProcess.js"></script>
</head>
<body>

	<form action="${ctx}/workflow/manager!rebuildJpdlByProcessName.action" method="post" id="newForm">
		<input type="hidden" name="option" value="copy"/>
		<input type="hidden" id="processAttributeId" name="processAttribute.id" value="${processAttribute.id}"/>
		<input type="hidden" name="processAttribute.processDefineId" value="${processAttribute.processDefineId}"/>
		<input type="hidden" name="processAttribute.companyId" value="${processAttribute.companyId}"/>		
		<input type="hidden" id="jsonJpdl" name="processAttribute.procsssDefinByJSON" value="${processAttribute.procsssDefinByJSON}"/>

<div class="input" > 
		<div class="pageTitle"><em class="iconAdd mr10">基本属性</em></div>
		<table width="100%" border="0" cellpadding="0" cellspacing="1"  class="inputTable">
				<tr>
						<th><label class="requireField">*</label><label>流程名称</label></th>
						<td><input name="processAttribute.processName" maxlength="30"  valid="required" errmsg="customJpdl.process_name_not_null" id="pname" value="${processAttribute.processName}" type="text" class="formText"   />
								</td>
						<th><label class="requireField">*</label><label>表&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;单</label></th>
						<td>
							<!-- <select disabled="disabled"  id="formId" <c:if test="${processAttribute.processState!=1}">disabled</c:if>>
						<c:forEach items="${formList}" var="form">
							<option value="${form.formId}" <c:if test="${form.formId==processAttribute.formId}">selected</c:if> >${form.formName }</option>
						</c:forEach>
						</select> -->
						<input type="text" id="formCategory" class="formText iconTree" value="${formAttribute.formName}" size="30" readonly/>
						<input type="hidden" name="processAttribute.formId" value="${processAttribute.formId}"	 id="formCategory_hidden"/>
						<a href="#" id="prepView">预览表单</a></td>
				</tr>
				<tr>
						<th><em class="requireField">*</em><label>流程分类</label></th>
						<td><select name="processAttribute.categoryId">
							<c:forEach items="${categories}" var="cate">
							<option value="${cate.categoryId}" <c:if test="${cate.categoryId ==processAttribute.categoryId}">selected</c:if> >${cate.categoryName}</option>
						</c:forEach>
							
						</select></td>
						<th>允许附件</th><td><select style="margin-top: 10" name="processAttribute.isAttach">
										<option value="1" <c:if test="${processAttribute.isAttach == 1}">selected</c:if>>是</option>
										<option value="0" <c:if test="${processAttribute.isAttach == 0}">selected</c:if>>否</option>
								</select></td>
						<!-- <th><label>流程排序号</label></th>
						<td>
							<input type="text"  valid="isNumber|range" min="0"  errmsg="customJpdl.process_order_must_int" class="formText" size="10" name="processAttribute.processOrder" value="${processAttribute.processOrder}"/>
								<em class="gray_9">控制同一分类下流程的排序</em></td> -->
				</tr>
				
				
			
		</table>
	<div class="pageTitle">
	<em class="iconAdd">
	<!--  <a href="javascript:void(0)"  onclick="showHide('explainTable')" class="shareShow">流程说明</a> -->
	 流程说明   </em>
	</div>
	<table width="100%" border="0" cellpadding="0" cellspacing="1"  class="inputTable" id="explainTable">
	<tr><th>说明内容</th><td><textarea maxlength="200" name="processAttribute.directions" id="directions" style="behavior:url(maxlength.htc);"  class="formTextarea">${processAttribute.directions}</textarea></td></tr>
	<!--
	<tr>
			<th>说明文档</th>
			<td>&nbsp;</td>
	</tr>
	<tr>
			<th>上传文档</th>
			<td><a href="" class="icon_add">添加附件</a></td>
	</tr>
-->
	</table>
	<div class="pageTitle">
	<em class="iconAdd">
	<!--  <a href="javascript:void(0)"   onclick="showHide('txtNumTable')" class="shareShow">工作名称/文号</a>  -->
	工作名称/文号</em>
	</div>
		<table width="100%" border="0" cellpadding="0" cellspacing="0"  class="inputTable" id="txtNumTable">
				<tr>
						<th><em class="requireField">*</em>文号表达式</th>
						<td ><input name="processAttribute.processNameExpr" valid="required" errmsg="customJpdl.process_name_expr_not_null" value="${processAttribute.processNameExpr}" type="text" class="formText"  />
								<a  href="javascript:void()" onclick="showHide('showTxt')">查看说明</a>
								<div class="l22" id="showTxt" style="display:none"> 文号表达式说明
										<p class="l22">表达式中可以使用以下特殊标记</p>
										<p class="l22"> {Y}：表示年 </p>
										<p class="l22">{M}：表示月 </p>
										<p class="l22">{D}：表示日 </p>
										<p class="l22">{H}：表示时 </p>
										<p class="l22">{I}：表示分 </p>
										<p class="l22">{S}：表示秒 </p>
										<p class="l22">{F}：表示流程名 </p>
										<p class="l22">{FS}：表示流程分类名称 </p>
										<p class="l22">{U}：表示用户姓名 </p>
										<p class="l22">{DE}：表示部门 </p>
										<p class="l22">{N}：表示编号，通过编号计数器取值并自动增加计数值 </p>
										<p class="l22">例如，表达式为：成建委发[{Y}]{N}号 </p>
										<p class="l22">同时，设置自动编号显示长度为4 </p>
										<p class="l22">则自动生成的文号如下：成建委发[2006]0001号 </p>
										<p class="l22">例如，表达式为：BH{N} </p>
										<p class="l22">同时，设置自动编号显示长度为3 </p>
										<p class="l22">则自动生成的文号如下：BH001 </p>
										<p class="l22">例如，表达式为：{F}流程（{Y}年{M}月{D}日{H}:{I}）{U} </p>
										<p class="l22">自动生成文号如：请假流程（2006年01月01日10:30）张三 </p>
										<p class="l22">系统默认按以下格式，如： </p>
										<p class="l22">请假流程(2006-01-01 10:30:30) </p>
								</div></td>
								</tr>
							<tr><th><em class="requireField">*</em>编号计数器</th>
								<td><input name="processAttribute.processNameBeginNum" style="width: 80px" maxlength="4" valid="required|isNumber|range" min="0" errmsg="customJpdl.process_name_expr_begin_not_null|customJpdl.process_name_expr_begin_int" type="text" class="formText" value="${processAttribute.processNameBeginNum}" />
						<em class="gray_9">用于表达式编号标记 </em></td>
				</tr>
				<tr>
									<th><em class="requireField">*</em>编&nbsp;号&nbsp;位&nbsp;数</th>
						<td><input style="width: 80px" name="processAttribute.processNamLength" maxlength="1" valid="required|isNumber|range" errmsg="customJpdl.process_name_expr_length_not_null|customJpdl.process_name_expr_length" min="0" value="${processAttribute.processNamLength}" type="text" class="formText" size="10" />
								<em class="gray_9">为0表示按实际编号位数显示</em></td></tr>
					<tr>	<th>是否允许修改</th>
						<td><SELECT name="processAttribute.processNameCanupdate">
										<OPTION value="1" <c:if test="${processAttribute.processNameCanupdate == 1}">selected</c:if>>允许修改</OPTION>
										<OPTION value="0" <c:if test="${processAttribute.processNameCanupdate == 0}">selected</c:if>>不允许修改</OPTION>
										<!--
										<OPTION value=2>仅允许输入前缀</OPTION>
										<OPTION value=3>仅允许输入后缀</OPTION>
										<OPTION value=4>仅允许输入前缀和后缀</OPTION>-->
								</SELECT></td>
				</tr>
		</table>
		<div style="margin-bottom: 20" class="buttonArea"> <input hideFocus="" value="确  定" id="sure" type="button" class="formButton_green"/> &nbsp;&nbsp;<input hideFocus="" id="cancelCopy" value="取 消" type="button" class="formButton_grey"/></div>
</div>
</form>
</body>
</html>