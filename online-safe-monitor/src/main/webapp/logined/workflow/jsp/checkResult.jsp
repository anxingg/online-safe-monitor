<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>设计流程--向导</title>
<jsp:include page="../../../common/flatHead.jsp"></jsp:include>
<link href="${ctx}/flat/css/reset.css" rel="stylesheet" type="text/css" />
<link href="${ctx }/flat/plugins/form/skins/form_default.css" rel="stylesheet" type="text/css" />
<link href="${ctx }/flat/plugins/annex/skins/annex_default.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx}/logined/workflow/js/wizard.js"></script>
</head>
<body >
		<div  class="list" >
		<table width="100%" border="0" cellpadding="0" cellspacing="0"  class="inputTable">
				<tr>
						<th>流程文件</th>
						<td><c:if test="${fileCheck}">合法</c:if><c:if test="${!fileCheck}"><i class="red">不合法</i></c:if></td>
				</tr>
				<c:if test="${fileCheck}">
				<tr>
						<th>经办权限</th>
						<td><c:if test="${check.auth==null}">已设置</c:if><c:if test="${check.auth!=null }"><i class="red">未设置</i>
						(<c:forEach items="${check.auth}" var="auth" varStatus="vs">
						<i class="red">${auth}&nbsp;</i>
						</c:forEach>)</c:if>
						</td>
				</tr>
				<tr>
				     <th>节点属性</th>
				     <td>
				     <c:if test="${check.value==null}">已设置</c:if><c:if test="${check.value!=null }"><i class="red">未设置</i>
						(<c:forEach items="${check.value}" var="value" varStatus="vs">
						<i class="red">${value}&nbsp;</i>
						</c:forEach>)</c:if>
				     </td>
				</tr>
				</c:if>
		</table>
</div>
</body>
</html>