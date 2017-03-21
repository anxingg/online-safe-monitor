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
	<link href="${ctx}wh/plugins/datatable/skins/datatable_default.css?version=${version}" rel="stylesheet" type="text/css"/>
	<script type="text/javascript">
		var basePath = "${ctx}";
	</script>
	<%-- <script type="text/javascript" src="${ctx}wh/jquery-easyui-1.4.3/jquery.min.js"></script> --%>
	<%-- <script type="text/javascript" src="${ctx}wh/jquery-easyui-1.4.3/jquery.easyui.min.js"></script> --%>
	<%-- <script type="text/javascript" src="${ctx}wh/plugins/datatable/jquery.dataTables.min.js"></script> --%>
	
</head>
<body>
	<!-- 隐藏域：角色（1、政府； 2、企业） -->
	<input type="hidden" id="whroletype" value="<s:property value="#session.whroletype"/>" />
	<!-- 隐藏域：企业ID -->
	<input type="hidden" id="group_id" value="<s:property value="#session.adminUser.groupId"/>" />
	<!-- 隐藏域：主键ID -->
	<input type="hidden" id="vid" value="<s:property value="dangerChemicalsInfo.vid"/>" />
	
	<div class="bread-line">
		<label>当前位置：</label><a href="#">危险化学品</a>&gt;&nbsp;危险化学品详情
	</div>
	<div class="formPage">
		<form id="form1">
		<div class="formbg">
			<div class="big_title">详情</div>
			<div class="content_form">
				<table width="100%" cellspacing="0" cellpadding="0" border="0" class="inputTable">
					<tbody>
						<tr>
							<th><label>化学品编号：</label></th>
							<td><s:if test="chemicalsDirectory != null"><s:property value="chemicalsDirectory.code"/></s:if></td>
							<th><label>品名：</label></th>
							<td id="pinming"><s:if test="chemicalsDirectory != null"><s:property value="chemicalsDirectory.materialName"/></s:if></td>
						</tr>
						<tr>
							<th><label>别名：</label></th>
							<td id="bieming"><s:if test="chemicalsDirectory != null"><s:property value="chemicalsDirectory.molecularFormula"/></s:if></td>
							<th><label>CAS号：</label></th>
							<td id="cashao"><s:if test="chemicalsDirectory != null"><s:property value="chemicalsDirectory.cas"/></s:if></td>
						</tr>
					</tbody>
				</table>
				<h2 class="small_title"><a href="#" class="shareShow">标识</a></h2>
				<table width="100%" cellspacing="0" cellpadding="0" border="0" class="inputTable">
					<tbody>
						<tr>
							<th><label>物质名称：</label></th>
							<td><s:property value="dangerChemicalsInfo.materialName"/></td>
							<th><label>分子式：</label></th>
							<td><s:property value="dangerChemicalsInfo.molecularFormula"/></td>
						</tr>
					</tbody>
				</table>
				<h2 class="small_title"><a href="#" class="shareShow">物化特性</a></h2>
				<table width="100%" cellspacing="0" cellpadding="0" border="0" class="inputTable">
					<tbody>
						<tr>
							<th><label>熔点（℃）：</label></th>
							<td><fmt:formatNumber value="${dangerChemicalsInfo.meltingPoint}" pattern="#0.00"/></td>
							<th><label>沸点（℃）：</label></th>
							<td><fmt:formatNumber value="${dangerChemicalsInfo.boilingPoint}" pattern="#0.00"/></td>
						</tr>
						<tr>
							<th><label>比重（水=1）：</label></th>
							<td><fmt:formatNumber value="${dangerChemicalsInfo.gravity}" pattern="#0.00"/></td>
							<th><label>饱和蒸气压（kPa）：</label></th>
							<td><fmt:formatNumber value="${dangerChemicalsInfo.saturatedVaporPressure}" pattern="#0.00"/></td>
						</tr>
						<tr>
							<th><label>蒸气密度（空气=1）：</label></th>
							<td><fmt:formatNumber value="${dangerChemicalsInfo.vaporDensity}" pattern="#0.00"/></td>
							<th><label>溶解性：</label></th>
							<td><s:property value="dangerChemicalsInfo.solubility"/></td>
						</tr>
						<tr>
							<th><label>外观与性状：</label></th>
							<td colspan="3"><s:property value="dangerChemicalsInfo.appearance"/></td>
						</tr>
					</tbody>
				</table>
				<h2 class="small_title"><a href="#" class="shareShow">危险特性数据</a></h2>
				<table width="100%" cellspacing="0" cellpadding="0" border="0" class="inputTable">
					<tbody>
						<tr>
							<th><label>危险特性：</label></th>
							<td colspan="3"><s:property value="dangerChemicalsInfo.dangerousCharacteristic"/></td>
						</tr>
						<tr>
							<th><label>处置方法：</label></th>
							<td colspan="3"><s:property value="dangerChemicalsInfo.fireFightingMethods"/></td>
						</tr>
					</tbody>
				</table>
				<h2 class="small_title"><a href="#" class="shareShow">反应活性数据</a></h2>
				<table width="100%" cellspacing="0" cellpadding="0" border="0" class="inputTable">
					<tbody>
						<tr>
							<th><label>稳定性：</label></th>
							<td><label class="radio">
								<s:if test="dangerChemicalsInfo.stability == 0">不稳定</s:if>
								<s:elseif test="dangerChemicalsInfo.stability == 1">稳定</s:elseif>
							</td>
							<th><label>避免条件：</label></th>
							<td><s:property value="dangerChemicalsInfo.stabilityAvoid"/></td>
						</tr>
						<tr>
							<th><label>聚合危险性：</label></th>
							<td><label class="radio">
								<s:if test="dangerChemicalsInfo.aggregateRisk == 0">不存在</s:if>
								<s:elseif test="dangerChemicalsInfo.aggregateRisk == 1">可能存在</s:elseif>
							</td>
							<th><label>避免条件：</label></th>
							<td><s:property value="dangerChemicalsInfo.aggregateRiskAvoid"/></td>
						</tr>
						<tr>
							<th><label>禁忌物：</label></th>
							<td><s:property value="dangerChemicalsInfo.taboo"/></td>
							<th><label>燃烧（分解）产物：</label></th>
							<td><s:property value="dangerChemicalsInfo.breakdownProducts"/></td>
						</tr>
					</tbody>
				</table>
				<h2 class="small_title"><a href="#" class="shareShow">健康危害数据</a></h2>
				<table width="100%" cellspacing="0" cellpadding="0" border="0" class="inputTable">
					<tbody>
						<tr>
							<th><label>侵入途径：</label></th>
							<td colspan="3">
								<s:if test="dangerChemicalsInfo.intrusiveWay.indexOf(\",1,\") != -1">吸入</s:if>
								<s:if test="dangerChemicalsInfo.intrusiveWay.indexOf(\"2\") != -1">皮肤</s:if>
								<s:if test="dangerChemicalsInfo.intrusiveWay.indexOf(\"3\") != -1">误食</s:if>
							</td>
						</tr>
						<tr>
							<th><label>急性毒性：</label></th>
							<td colspan="3"><label>LD50&nbsp;</label><s:property value="dangerChemicalsInfo.toxicityLD"/>&nbsp;
								<label>LC50&nbsp;<s:property value="dangerChemicalsInfo.toxicityLC"/>
							</td>
						</tr>
						<tr>
							<th><label>健康危害（急性和慢性）：</label></th>
							<td colspan="3"><s:property value="dangerChemicalsInfo.healthRisk"/></td>
						</tr>
						<tr>
							<th><label>泄漏紧急处理：</label></th>
							<td colspan="3"><s:property value="dangerChemicalsInfo.leakageHandling"/></td>
						</tr>
						<tr>
							<th><label>储运注意事项：</label></th>
							<td colspan="3"><s:property value="dangerChemicalsInfo.storageTransportationAttenti"/></td>
						</tr>
					</tbody>
				</table>
				<h2 class="small_title"><a href="#" class="shareShow">防护措施</a></h2>
				<table width="100%" cellspacing="0" cellpadding="0" border="0" class="inputTable">
					<tbody>
						<tr>
							<th><label>职业接触限值：</label></th>
							<td colspan="3"><label>MAC&nbsp;</label><s:property value="dangerChemicalsInfo.mac"/></td>
						</tr>
						<tr>
							<th><label>工程控制：</label></th>
							<td colspan="3"><s:property value="dangerChemicalsInfo.engineeringControl"/></td>
						</tr>
						<tr>
							<th><label>呼吸系统防护：</label></th>
							<td><s:property value="dangerChemicalsInfo.respiratoryProtection"/></td>
							<th><label>身体防护：</label></th>
							<td><s:property value="dangerChemicalsInfo.bodyProtection"/></td>
						</tr>
						<tr>
							<th><label>手防护：</label></th>
							<td><s:property value="dangerChemicalsInfo.handProtection"/></td>
							<th><label>眼防护：</label></th>
							<td><s:property value="dangerChemicalsInfo.eyeProtection"/></td>
						</tr>
						<tr>
							<th><label>其它：</label></th>
							<td colspan="3"><s:property value="dangerChemicalsInfo.other"/></td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
		<div class="buttonArea">
			<!-- <input type="button"class="formButton_green" value="确定" hidefocus=""/> -->
			<input type="button" value="返回" class="formButton_grey"  hidefocus="" onclick="javascript:history.go(-1);"/>
		</div>
		</form>
	</div>
	<div class="clear"></div>
<script type="text/javascript">
  $(document).ready(function(){
	    $(".shareShow").click(function(){
		    if($(this).hasClass("shareCls")){
			   	$(this).removeClass("shareCls");
			}else{
			    $(this).addClass("shareCls");
			}
			$(this).parent().next(".inputTable").toggle();
			return false;
		})
  });
</script>
</body>
</html>
