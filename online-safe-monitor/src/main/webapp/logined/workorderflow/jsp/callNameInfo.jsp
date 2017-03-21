<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:include page="../../../common/taglibs.jsp" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>用户档案-详情</title>
<jsp:include page="../../../common/flatHead.jsp" />
<link href="${ctx}flat/css/reset.css" rel="stylesheet" type="text/css" />
<link href="${ctx}flat/css/Reminder.css" rel="stylesheet" type="text/css" />
<link href="${ctx}flat/plugins/datatable/skins/datatable_default.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx}flat/js/base.js"></script>
<script type="text/javascript" src="${ctx}flat/plugins/dialog/iframeTools.js"></script>
<script type="text/javascript" src="${ctx}plugins/datatable/jquery.dataTables.min.js"></script>
<link href="${ctx}flat/plugins/form/skins/form_default.css" rel="stylesheet" type="text/css" />

<script type="text/javascript">
	var basePath = "${ctx}";
	function closeWin(){
		art.dialog.close();
	}
</script>
<style type="text/css">
.inputTable th{width:80px;}
</style>
</head>
<body style="background:#fff">
<div class="formPage">
  <div class="formbg">
     <s:if test="#request.fromPage=='seat'">
		<div class="big_title">查看客户信息</div>
	</s:if>
    
    <div class="content_form">
      <table width="100%" border="0" cellpadding="0" cellspacing="0"  class="inputTable">
        <tr>
          <th><label>联系电话：</label></th>
          <td>${crm.mobile}</td>
          <th><label>姓名：</label></th>
          <td>${crm.name}</td>
        </tr>
        <tr>
          <th><label>性别：</label></th>
          <td>
          	<s:if test="crm.gender==0">女</s:if>
          	<s:elseif test="crm.gender==1">男</s:elseif>
          </td>
          <th><label>年龄：</label></th>
          <td>${crm.age}</td>
        </tr>
        <tr>
        	<th><label>联系地址：</label></th>
          	<td>${crm.address}</td>
          	<th><label>工作单位：</label></th>
          	<td>${crm.company}</td>
        </tr>
        <tr>
          <th><label>备用号码：</label></th>
          <td>${crm.backPhone}</td>
          <th><label>客户类别：</label></th>
          <td>
          	<s:if test="crm.personType==1">一般客户</s:if>
          	<s:elseif test="crm.personType==2">VIP客户</s:elseif>
          </td>
        </tr>
        <tr>
        	<th><label>身份证号：</label></th>
          <td>${crm.cardId}</td>
          <th><label>户口所在地：</label></th>
          <td>${crm.hkAddress}</td>
        </tr>
        <tr>
          <th><label>职务：</label></th>
          <td>${crm.job}</td>
          <th><label>月收入：</label></th>
          <td><fmt:formatNumber value="${crm.receiveMoney}" pattern="#,###.##"/></td>
        </tr>
        <tr>
          <th><label>备注：</label></th>
          <td colspan="3">${crm.note}</td>
        </tr>
      </table>
    </div>
  </div>
  <div class="buttonArea">
    <s:if test="#request.fromPage=='seat'">
		<div class="buttonArea">
			<input type="button" class="formButton_grey" value="关闭"
				onclick="window.top.closeCurrentTab();" />
		</div>
	</s:if>
	<%-- <s:else>
		<div class="buttonArea">
			<input type="button" class="formButton_grey" value="返回"
				onclick="javascript:history.back();" />
			<!-- <input type="button" class="formButton_grey" value="返回" onclick="javascript:window.location.href =basePath+'logined/crm/jsp/crm_list.jsp';" /> -->
		</div>
	</s:else> --%>
  </div>
</div>
</body>
</html>
