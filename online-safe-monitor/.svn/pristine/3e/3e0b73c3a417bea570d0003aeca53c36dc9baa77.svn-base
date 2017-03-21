<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>系统基础设置</title>
<%@ include file="../../common/taglibs.jsp"%>
<jsp:include page="../../common/head.jsp" />
<link href="${ctx}platform/css/base.css" rel="stylesheet" type="text/css" />
<link href="${ctx}platform/css/public.css" rel="stylesheet" type="text/css" />
<link href="${ctx}platform/css/add_com.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/plugins/datatable/table_style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx}platform/js/jquery-1.6.min.js"></script>
<script type="text/javascript" src="${ctx}platform/js/base.js"></script>
<script type="text/javascript" src="${ctx}platform/js/systemBasisSet.js"></script>
<script type="text/javascript" src="${ctx}plugins/dialog/artDialog.js?skin=blue"></script>

<script type="text/javascript" src="${ctx}plugins/dialog/iframeTools.js"></script>

</head>
<body>
<div class="addcon">
		<h3>系统初始化设置</h3>
		<div class="szlist">
				<p><span>前台名称：</span>
						<input name="sysSet.receptionName"  id="receptionName" value="${sysSet.receptionName}" type="text"  class="formText" />
				</p>
				<p><span>后台名称：</span>
						<input name="sysSet.backstageName" id="backstageName" value="${sysSet.backstageName}" type="text" class="formText" />
				</p>
				<p><span>组织机构根目录名称：</span>
						<input name="sysSet.organizationsRootName" id="organizationsRootName" value="${sysSet.organizationsRootName}" type="text" class="formText"/>
				</p>
				<p><span>版权信息：</span>
						<input name="sysSet.copyrightInformation" id="copyrightInformation" value="${sysSet.copyrightInformation}" type="text" class="formText"/>
				</p>
				<p><span>服务号码：</span>
						<input name="sysSet.servicePhoneNumber" id="servicePhoneNumber" value="${sysSet.servicePhoneNumber}" type="text" class="formText"/>
				</p>
				<p><span>热线号码：</span>
						<input name="sysSet.telephoneHotline" id="telephoneHotline" value="${sysSet.telephoneHotline}" type="text" class="formText"/>
				</p>
		</div>
		<div class="line"></div>
		<h3>组织机构设置 </h3>
		<div class="szlist">
				<p><span>个人信息字段：</span>
						<input name="sysSet.personalInformation" id="personalInformation" value="${sysSet.personalInformation}" type="text"  class="formText"/>
				</p>
				<p><span>单位信息字段：</span>
						<input name="sysSet.unitInformation" id="unitInformation" value="${sysSet.unitInformation}" type="text" class="formText" />
				</p>
				<p>
						<label class="radio" style="margin-left:67px;">
								<input type="checkbox" name="sysSet.isSubUnits" id="isSubUnits" value="1" <c:if test="${sysSet.isSubUnits==1}">checked="checked"</c:if>  id="CheckboxGroup1_0"  />
								支持二级单位 </label>
				</p>
		</div>
		<div class="line"></div>
		<h3>CRM设置 </h3>
		<div class="szlist">
		<p><span>CRM版本：</span><select name=""><option>使用标准CRM </option><option>使用扩展CRM </option></select></p>
		</div>
		<div class="buttonArea">
				<input type="submit" class="formButton_tab"  id="systemBasisSetSave" onclick="" value="保  存"  />
				&nbsp; </div>
</div>
</body>
</html>
