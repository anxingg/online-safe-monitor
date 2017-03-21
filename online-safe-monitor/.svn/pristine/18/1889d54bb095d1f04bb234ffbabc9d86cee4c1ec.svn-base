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
	<script type="text/javascript" src="${ctx}wh/js/dangerChemicals/dangerChemicalsAdd.js"></script>
    
</head>
<body>
	<!-- 隐藏域：角色（1、政府； 2、企业） -->
	<input type="hidden" id="whroletype" value="<s:property value="#session.whroletype"/>" />
	<!-- 隐藏域：企业ID -->
	<input type="hidden" id="group_id" value="<s:property value="#session.adminUser.groupId"/>" />
	<!-- 隐藏域：主键ID -->
	<input type="hidden" id="vid" value="<s:property value="dangerChemicalsInfo.vid"/>" />
	
	<div class="bread-line">
		<label>当前位置：</label><a href="#">危险化学品</a>&gt;&nbsp;危险化学品<s:if test="dangerChemicalsInfo.vid != null">修改</s:if><s:else>新增</s:else>
	</div>
	<div class="formPage">
		<form id="form1">
		<div class="formbg">
			<div class="big_title"><s:if test="dangerChemicalsInfo.vid != null">修改</s:if><s:else>新增</s:else></div>
			<div class="content_form">
				<table width="100%" cellspacing="0" cellpadding="0" border="0" class="inputTable">
					<tbody>
						<tr>
							<th><em class="requireField">*</em><label>化学品编号：</label></th>
							<td><select id="catalogId">
								<option value="0">请选择</option>
								<s:iterator value="chemicalsDirectoryList" id="entry">
									<s:if test="dangerChemicalsInfo.catalogId == #entry.vid">
									<option value="<s:property value="#entry.vid"/>" name="<s:property value="#entry.materialName"/>@<s:property value="#entry.molecularFormula"/>@<s:property value="#entry.cas"/>" selected="selected" ><s:property value="#entry.code"/></option>
									</s:if>
									<s:else>
									<option value="<s:property value="#entry.vid"/>" name="<s:property value="#entry.materialName"/>@<s:property value="#entry.molecularFormula"/>@<s:property value="#entry.cas"/>" ><s:property value="#entry.code"/></option>
									</s:else>
									
								</s:iterator> 
							</select></td>
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
							<th><em class="requireField">*</em><label>物质名称：</label></th>
							<td><input type="text" class="formText" id="materialName" value="<s:property value="dangerChemicalsInfo.materialName"/>" valid="required" errmsg="wuhaiDangerChemicals.materialName_not_null" maxlength="32"/></td>
							<th><label>分子式：</label></th>
							<td><input type="text" class="formText" id="molecularFormula" value="<s:property value="dangerChemicalsInfo.molecularFormula"/>" maxlength="32"/></td>
						</tr>
					</tbody>
				</table>
				<h2 class="small_title"><a href="#" class="shareShow">物化特性</a></h2>
				<table width="100%" cellspacing="0" cellpadding="0" border="0" class="inputTable">
					<tbody>
						<tr>
							<th><em class="requireField">*</em><label>熔点（℃）：</label></th>
							<td><input type="text" class="formText" id="meltingPoint" value="<fmt:formatNumber value="${dangerChemicalsInfo.meltingPoint}" pattern="#0.00"/>" valid="required|isIntegerOrFloat" errmsg="wuhaiDangerChemicals.meltingPoint_not_null|wuhaiDangerChemicals.meltingPoint_not_number" maxlength="32"/></td>
							<th><em class="requireField">*</em><label>沸点（℃）：</label></th>
							<td><input type="text" class="formText" id="boilingPoint" value="<fmt:formatNumber value="${dangerChemicalsInfo.boilingPoint}" pattern="#0.00"/>" valid="required|isIntegerOrFloat" errmsg="wuhaiDangerChemicals.boilingPoint_not_null|wuhaiDangerChemicals.boilingPoint_not_number" maxlength="32"/></td>
						</tr>
						<tr>
							<th><em class="requireField">*</em><label>比重（水=1）：</label></th>
							<td><input type="text" class="formText" id="gravity" value="<fmt:formatNumber value="${dangerChemicalsInfo.gravity}" pattern="#0.00"/>" valid="required|isIntegerOrFloat" errmsg="wuhaiDangerChemicals.gravity_not_null|wuhaiDangerChemicals.gravity_not_number" maxlength="32"/></td>
							<th><em class="requireField">*</em><label>饱和蒸气压（kPa）：</label></th>
							<td><input type="text" class="formText" id="saturatedVaporPressure" value="<fmt:formatNumber value="${dangerChemicalsInfo.saturatedVaporPressure}" pattern="#0.00"/>" valid="required|isIntegerOrFloat" errmsg="wuhaiDangerChemicals.saturatedVaporPressure_not_null|wuhaiDangerChemicals.saturatedVaporPressure_not_number" maxlength="32" /></td>
						</tr>
						<tr>
							<th><em class="requireField">*</em><label>蒸气密度（空气=1）：</label></th>
							<td><input type="text" class="formText" id="vaporDensity" value="<fmt:formatNumber value="${dangerChemicalsInfo.vaporDensity}" pattern="#0.00"/>" valid="required|isIntegerOrFloat" errmsg="wuhaiDangerChemicals.vaporDensity_not_null|wuhaiDangerChemicals.vaporDensity_not_number" maxlength="32"/></td>
							<th><em class="requireField">*</em><label>溶解性：</label></th>
							<td><input type="text" class="formText" id="solubility" value="<s:property value="dangerChemicalsInfo.solubility"/>" valid="required" errmsg="wuhaiDangerChemicals.solubility_not_null" maxlength="32"/></td>
						</tr>
						<tr>
							<th><em class="requireField">*</em><label>外观与性状：</label></th>
							<td colspan="3">
								<textarea class="formTextarea" id="appearance" valid="required" errmsg="wuhaiDangerChemicals.appearance_not_null" maxlength="256"><s:property value="dangerChemicalsInfo.appearance"/></textarea>
							</td>
						</tr>
					</tbody>
				</table>
				<h2 class="small_title"><a href="#" class="shareShow">危险特性数据</a></h2>
				<table width="100%" cellspacing="0" cellpadding="0" border="0" class="inputTable">
					<tbody>
						<tr>
							<th><em class="requireField">*</em><label>危险特性：</label></th>
							<td colspan="3">
								<textarea class="formTextarea" id="dangerousCharacteristic" valid="required" errmsg="wuhaiDangerChemicals.dangerousCharacteristic_not_null" maxlength="256"><s:property value="dangerChemicalsInfo.dangerousCharacteristic"/></textarea>
							</td>
						</tr>
						<tr>
							<th><em class="requireField">*</em><label>处置方法：</label></th>
							<td colspan="3">
							<textarea class="formTextarea" id="fireFightingMethods" valid="required" errmsg="wuhaiDangerChemicals.fireFightingMethods_not_null" maxlength="256" ><s:property value="dangerChemicalsInfo.fireFightingMethods"/></textarea>
							</td>
						</tr>
					</tbody>
				</table>
				<h2 class="small_title"><a href="#" class="shareShow">反应活性数据</a></h2>
				<table width="100%" cellspacing="0" cellpadding="0" border="0" class="inputTable">
					<tbody>
						<tr>
							<th><label>稳定性：</label></th>
							<td>
								<s:if test="dangerChemicalsInfo.stability == null || dangerChemicalsInfo.stability == 0">
								<label class="radio"><input type="radio" name="stability" checked="checked" value="0"/>不稳定</label>&nbsp;
								<label class="radio"><input type="radio" name="stability" value="1"/>稳定</label>
								</s:if>
								<s:else>
								<label class="radio"><input type="radio" name="stability" value="0"/>不稳定</label>&nbsp;
								<label class="radio"><input type="radio" name="stability" checked="checked" value="1"/>稳定</label>
								</s:else>
							</td>
							<th><label>避免条件：</label></th>
							<td>
							<textarea class="formTextarea" id="stabilityAvoid" maxlength="256" ><s:property value="dangerChemicalsInfo.stabilityAvoid"/></textarea>
							</td>
						</tr>
						<tr>
							<th><label>聚合危险性：</label></th>
							<td>
								<s:if test="dangerChemicalsInfo.aggregateRisk == null || dangerChemicalsInfo.aggregateRisk == 0">
								<label class="radio"><input type="radio" name="aggregateRisk" checked="checked" value="0"/>不存在</label>&nbsp;
								<label class="radio"><input type="radio" name="aggregateRisk" value="1"/>可能存在</label>
								</s:if>
								<s:else>
								<label class="radio"><input type="radio" name="aggregateRisk" value="0"/>不存在</label>&nbsp;
								<label class="radio"><input type="radio" name="aggregateRisk" checked="checked" value="1"/>可能存在</label>
								</s:else>
							</td>
							<th><label>避免条件：</label></th>
							<td>
							<textarea class="formTextarea" id="aggregateRiskAvoid" maxlength="256" ><s:property value="dangerChemicalsInfo.aggregateRiskAvoid"/></textarea>
							</td>
						</tr>
						<tr>
							<th><em class="requireField">*</em><label>禁忌物：</label></th>
							<td><textarea class="formTextarea" id="taboo" valid="required" errmsg="wuhaiDangerChemicals.taboo_not_null" maxlength="256" ><s:property value="dangerChemicalsInfo.taboo"/></textarea></td>
							<th><em class="requireField">*</em><label>燃烧（分解）产物：</label></th>
							<td>
								<textarea class="formTextarea" id="breakdownProducts" valid="required" errmsg="wuhaiDangerChemicals.breakdownProducts_not_null" maxlength="256" ><s:property value="dangerChemicalsInfo.breakdownProducts"/></textarea>
							</td>
						</tr>
					</tbody>
				</table>
				<h2 class="small_title"><a href="#" class="shareShow">健康危害数据</a></h2>
				<table width="100%" cellspacing="0" cellpadding="0" border="0" class="inputTable">
					<tbody>
						<tr>
							<th><em class="requireField">*</em><label>侵入途径：</label></th>
							<td colspan="3">
								<s:if test="dangerChemicalsInfo.intrusiveWay.indexOf(\",1,\") != -1">
								<label class="radio"><input type="checkbox" name="intrusiveWay" checked="checked" value="1"/>吸入</label>&nbsp;
								</s:if>
								<s:else>
								<label class="radio"><input type="checkbox" name="intrusiveWay" value="1"/>吸入</label>&nbsp;
								</s:else>
								<s:if test="dangerChemicalsInfo.intrusiveWay.indexOf(\"2\") != -1">
								<label class="radio"><input type="checkbox" name="intrusiveWay" checked="checked" value="2"/>皮肤</label>&nbsp;
								</s:if>
								<s:else>
								<label class="radio"><input type="checkbox" name="intrusiveWay" value="2"/>皮肤</label>&nbsp;
								</s:else>
								<s:if test="dangerChemicalsInfo.intrusiveWay.indexOf(\"3\") != -1">
								<label class="radio"><input type="checkbox" name="intrusiveWay" checked="checked" value="3"/>误食</label>
								</s:if>
								<s:else>
								<label class="radio"><input type="checkbox" name="intrusiveWay" value="3"/>误食</label>
								</s:else>
								<input type="hidden" id="hideIntrusiveWay"/>
							</td>
						</tr>
						<tr>
							<th><label>急性毒性：</label></th>
							<td colspan="3"><label>LD50&nbsp;</label><input type="text" class="formText" id="toxicityLD" value="<s:property value="dangerChemicalsInfo.toxicityLD"/>" maxlength="32" style="width:200px;" />&nbsp;
								<label>LC50&nbsp;</label><input type="text" class="formText" id="toxicityLC" value="<s:property value="dangerChemicalsInfo.toxicityLC"/>" maxlength="32" style="width:200px;"/>
							</td>
						</tr>
						<tr>
							<th><label style="position: relative;right: 20px;"><em class="requireField">*</em>健康危害（急性和慢性）：</label></th>
							<td colspan="3">
								<textarea class="formTextarea" id="healthRisk" valid="required" errmsg="wuhaiDangerChemicals.healthRisk_not_null" maxlength="256" ><s:property value="dangerChemicalsInfo.healthRisk"/></textarea>
							</td>
						</tr>
						<tr>
							<th><em class="requireField">*</em><label>泄漏紧急处理：</label></th>
							<td colspan="3">
								<textarea class="formTextarea" id="leakageHandling" valid="required" errmsg="wuhaiDangerChemicals.leakageHandling_not_null" maxlength="256" ><s:property value="dangerChemicalsInfo.leakageHandling"/></textarea>
							</td>
						</tr>
						<tr>
							<th><em class="requireField">*</em><label>储运注意事项：</label></th>
							<td colspan="3">
								<textarea class="formTextarea" id="storageTransportationAttenti" valid="required" errmsg="wuhaiDangerChemicals.storageTransportationAttenti_not_null" maxlength="256" ><s:property value="dangerChemicalsInfo.storageTransportationAttenti"/></textarea>
							</td>
						</tr>
					</tbody>
				</table>
				<h2 class="small_title"><a href="#" class="shareShow">防护措施</a></h2>
				<table width="100%" cellspacing="0" cellpadding="0" border="0" class="inputTable">
					<tbody>
						<tr>
							<th><em class="requireField">*</em><label>职业接触限值：</label></th>
							<td colspan="3"><label>MAC&nbsp;</label><input type="text" class="formText" id="mac" value="<s:property value="dangerChemicalsInfo.mac"/>" valid="required" errmsg="wuhaiDangerChemicals.mac_not_null" maxlength="32" style="width:200px;"/></td>
						</tr>
						<tr>
							<th><em class="requireField">*</em><label>工程控制：</label></th>
							<td colspan="3">
								<textarea class="formTextarea" id="engineeringControl" valid="required" errmsg="wuhaiDangerChemicals.engineeringControl_not_null" maxlength="256" ><s:property value="dangerChemicalsInfo.engineeringControl"/></textarea>
							</td>
						</tr>
						<tr>
							<th><em class="requireField">*</em><label>呼吸系统防护：</label></th>
							<td>
								<textarea class="formTextarea" id="respiratoryProtection" valid="required" errmsg="wuhaiDangerChemicals.respiratoryProtection_not_null" maxlength="256" ><s:property value="dangerChemicalsInfo.respiratoryProtection"/></textarea>
							</td>
							<th><em class="requireField">*</em><label>身体防护：</label></th>
							<td>
								<textarea class="formTextarea" id="bodyProtection" valid="required" errmsg="wuhaiDangerChemicals.bodyProtection_not_null" maxlength="256" ><s:property value="dangerChemicalsInfo.bodyProtection"/></textarea>
							</td>
						</tr>
						<tr>
							<th><em class="requireField">*</em><label>手防护：</label></th>
							<td>
								<textarea class="formTextarea" id="handProtection" valid="required" errmsg="wuhaiDangerChemicals.handProtection_not_null" maxlength="256" ><s:property value="dangerChemicalsInfo.handProtection"/></textarea>
							</td>
							<th><em class="requireField">*</em><label>眼防护：</label></th>
							<td>
								<textarea class="formTextarea" id="eyeProtection" valid="required" errmsg="wuhaiDangerChemicals.eyeProtection_not_null" maxlength="256" ><s:property value="dangerChemicalsInfo.eyeProtection"/></textarea>
							</td>
						</tr>
						<tr>
							<th><label>其它：</label></th>
							<td colspan="3">
								<textarea class="formTextarea" id="other" maxlength="256" ><s:property value="dangerChemicalsInfo.other"/></textarea>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
		<div class="buttonArea">
			<input type="button"class="formButton_green" value="确定" hidefocus=""/>
			<input type="button" value="取消" class="formButton_grey"  hidefocus="" onclick="javascript:history.go(-1);"/>
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
