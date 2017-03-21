<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>编辑节点属性</title>

<jsp:include page="../../../common/flatHead.jsp" />
<link href="${ctx}/flat/css/reset.css" rel="stylesheet" type="text/css" />
<link href="${ctx }/flat/plugins/form/skins/form_default.css"
	rel="stylesheet" type="text/css" />
<link href="${ctx }/flat/plugins/annex/skins/annex_default.css"
	rel="stylesheet" type="text/css" />
<link href="${ctx }flat/plugins/tree/skins/tree_default.css"
	rel="stylesheet" type="text/css" />
<link href="${ctx }flat/plugins/datatable/skins/datatable_default.css"
	rel="stylesheet" type="text/css" />
	
    <script type="text/javascript" src="${ctx}/js/user/selectuser.js"></script>
    <script type="text/javascript" src="${ctx}/js/common/hashmap.js"></script>
    <script type="text/javascript" src="${ctx}/js/common/treeNode.js"></script>
    <script type="text/javascript" src="${ctx}/logined/workflow/js/candidate.js"></script>
    <style>.inputTable th{width:130px;}</style>
</head>
<body>
    <form id="nodeForm" action="${ctx}/workflow/nodeManager!update.action" method="post">
        <input type="hidden" id="nodeId" name="node.id" value="${node.id}"/>
        <input type="hidden" id="userIds" name="node.candidate" value="${node.candidate}"/>
        <input type="hidden" id="groupIds" name="node.depts" value="${node.depts}"/>
        <input type="hidden" id="roleIds" name="node.roles" value="${node.roles}"/>
        <input type="hidden" name="node.processAttribute.id" value="${node.processAttribute.id}"/>
        <input type="hidden" name="node.nodeName" value="${node.nodeName}" />
        <input type="hidden" name="node.writeableProperties" value="${node.writeableProperties}"/>
        <input type="hidden" name="node.secretProperties" value="${node.secretProperties}"/>
        <input type="hidden" name="node.nodeType" value="${node.nodeType}"/>
        <input type="hidden" name="node.descri" value="${node.descri}"/>
        <input type="hidden" name="node.nodeOrder" value="${node.nodeOrder}"/>
        <input type="hidden" name="processAttributeId" value="${node.processAttribute.id}"/>



<div class="formPage">
  <div class="formbg">
    <div class="big_title">${node.nodeName } : 属性定义</div>
     
     <div class="content_form" >  
    <table width="100%" border="0" cellpadding="0" cellspacing="0"  class="inputTable">
        <tr>
            <th style="width:100px"><label>该节点办理地址:</label></th>
            <td>
                <input name="node.operateUrl" style="width:60%"  valid="required" class="formText" errmsg="process.process_OperateUrl_not_null" maxlength="60" value="${node.operateUrl}">
            </td>
        </tr>
        <tr>
            <th style="width:100px"><label>该节点状态:</label></th>
            <td>
                <input name="node.state" style="width:60%" class="formText"  maxlength="60" value="${node.state}">
            </td>
        </tr>
        </table>
        </div>
        <div class="m_all_10">
        <table width="100%" class="pretty dataTable" border="0" cellspacing="0" cellpadding="0">
        <thead>
        	<tr>
        		<th>操作代码</th>
        		<th>操作名称</th>
        		<th>排序</th>
        	</tr>
        </thead>
        <c:forEach items="${nextActions }" var="nextAction" varStatus="vs">
        <tr>
            <td>
                	<input type="text" class="formText" style="width:80%" name="operationCode_${vs.index }" value="${nextAction.actionCode}" readonly="readonly"/>
            </td>
            <td>
                	<input name="operationShow_${vs.index }" class="formText"  style="width:80%"value="${nextAction.actionShow }">
            </td>
            <td>
            		<input type="text" name="operationIndex_${vs.index }" class="formText" style="width:60px" value="${nextAction.actionIndex }"/>
            </td>
        </tr>
        </c:forEach>
    </table>
    <div class="top10"></div>
    </div>
    </div>
    <div class="buttonArea">
        <input hideFocus="" value="确 定" type="submit" id="nodeUpdate" class="formButton_green"/>&nbsp;&nbsp;
        <input hideFocus="" onclick="window.history.back(); return false;" value="返 回" type="button" class="formButton_grey" />
        </div>
</div></form>
</body>
</html>