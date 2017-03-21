<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<title>可写字段</title>
<jsp:include page="customJPDLHead.jsp"></jsp:include>
<script type="text/javascript" src="${ctx}/logined/workflow/js/writeable.js"></script>
<style>
.selectMember{width:600px}
.selectMember  .mem_listbox {width:200px}
</style>
</head>

<body>
	<form action="${ctx}/workflow/nodeManager!update.action" method="post">
	<input type="hidden" id="nodeId" name="node.id" value="${nodeId}"/>
    <input type="hidden" id="userIds" name="node.candidate" value="${node.candidate}"/>
    <input type="hidden" id="groupIds" name="node.depts" value="${node.depts}"/>
    <input type="hidden" id="roleIds" name="node.roles" value="${node.roles}"/>
    <input type="hidden" name="node.processAttribute.id" value="${node.processAttribute.id}"/>
    <input type="hidden" name="node.nodeName" value="${node.nodeName}" />
    <input type="hidden" name="node.writeableProperties" value="${node.writeableProperties}" id="writeableProperties"/>
    <input type="hidden" name="node.secretProperties" value="${node.secretProperties}"/>
    <input type="hidden" name="node.nodeType" value="${node.nodeType}"/>
    <input type="hidden" name="node.descri" value="${node.descri}"/>
    <input type="hidden" name="node.nodeOrder" value="${node.nodeOrder}"/>
    <input type="hidden" name="node.isMydepCanAccept" value="${node.isMydepCanAccept}"/>
    <input type="hidden" name="processAttributeId" value="${node.processAttribute.id}"/>
<div   class="input">
<div style="margin-left: 88px" class="pageTitle"><em class="iconAdd">步骤 :${node.nodeName } </em><i class="red ml5">编辑可写字段(可写字段对于本步骤主办人、经办人均为可写)
</i></div>
<div align="center"  class="selectMember" style="background:#fff; height: 50%" id="selectFiled" >
				<div class="mem_listbox listbox fl" >
				<h2 class="center">本步骤可写字段</h2>		
				<div class="m_all_5"><select size="1" multiple="multiple" class="selelist" id="writeAbles" style="height:200px"><c:forEach items="${writeAbleProperties}" var="formp"><option value="${formp.propertyId}">${formp.propertyNameCh}<option></c:forEach></select>
					
				</div>
				<p class="center">
						<input name="" type="button" value="全 选" id="allW" class="formButton" />
				</p>
				</div>
				<div class="btn_box fl">
						<input name="" type="button" value=" ← " id="leftSelect" class="formButton" style="margin-top:80px"/>
						<input name="" type="button" value=" → "  id="rightSelect" class="formButton"  style="margin-top:50px"/>
						</div>
				<div class="mem_listbox listbox fl">
				<h2 class="center">备选字段</h2>
						<div class="m_all_5"><select multiple="multiple" id="props" class="selelist" style="height:200px">
								<c:forEach items="${formProperties}" var="fp">
									<option value="${fp.propertyId}">${fp.propertyNameCh}</option>
								</c:forEach>
						</select>
					</div>
					<p class="center">
						<input name="" type="button" value="全 选" id="allProps" class="formButton" />
					</p>
				</div>
				<div class="clear"></div>
			<div class="center">点击条目时，可以组合CTRL或SHIFT键进行多选</div>
		</div>


	<div class="buttonArea">
		<input hideFocus="" value="确 定" type="button" id="sure" class="formButton_green"/>&nbsp;&nbsp;
		<input hideFocus="" onclick="window.history.back(); return false;" value="返 回" type="button" class="formButton_grey"/>
		</div>
</div>
</form>
</body>
</html>