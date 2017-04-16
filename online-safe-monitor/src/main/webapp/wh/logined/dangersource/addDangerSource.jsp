<%@ page language="java" import="java.util.*"  contentType="text/html; charset=UTF-8" pageEncoding="utf-8"  %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>  
<jsp:include page="../../../common/taglibs.jsp"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<jsp:include page="../osmHead.jsp" />
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>危险源</title>
	<!-- 验证start -->
	<script type="text/javascript" src="${ctx}js/common/validate_form.js?version=${version}"></script>
	<script type="text/javascript" src="${ctx}common/js/js_lang_cn.js?version=${version}"></script>
	<script type="text/javascript" src="${ctx}js/common/showError.js?version=${version}"></script>
	<!-- 验证end -->
    <script type="text/javascript" src="${ctx}wh/js/dangersource/addDangerSource.js?version=${version}"></script>
</head>
<body class="template">
	
	<div class="bread-line">
		<label for="">位置：</label>
		<a href="#">安监在线</a>&gt;
		<a href="#">设备管理</a>&gt;
		<a href="#">添加危险源</a>
	</div>
	<div class="formPage">
		<div class="formbg">
		 	<div class="big_title">
		 		<span id="title">
	 				<c:choose>
						<c:when test="${ds.id == null }">
							<em>新增</em>
						</c:when>
						<c:otherwise>
							<em>修改</em>
						</c:otherwise>
					</c:choose> 
		 		</span>
		 	</div>	
	        <div class="content_form">
				<form id="form1" method="post"
						<c:choose>
							<c:when test="${ds.id == null }">
								action="saveDangerSource.action"
							</c:when>
							<c:otherwise>
								action="updateDangerSource.action"
							</c:otherwise>
						</c:choose> 
					 >
					<!-- 隐藏域：主键ID -->
					<input type="hidden" name="ds.id" value='${ds.id}'/>
					<h2 class="small_title">基本信息</h2>
					<table width="100%" cellspacing="0" cellpadding="0" border="0" class="inputTable">
						<tbody>
							<tr>
								<th><em class="requireField">*</em>所属企业：</th>
								<td>
									<select name="ds.regionId">
										<option value="1">市辖区</option>
										<option value="2">海勃湾区</option>
										<option value="3">海南区</option>
										<option value="4">乌达区</option>
									</select>
									<select name="ds.company.companyId">
										<option value="11604">广宇化工</option>
										<option value="11602">榕鑫能源</option>
									</select>
									<%-- <input type="text" class="formText" name="ds.company.companyId" maxlength="6" 
											value="<s:property value="ds.company.companyId" />"
											valid="required" errmsg="wuhaiDangerSource.templateName_not_null" /> --%>
								</td>
								<th></th>
								<td></td>
							</tr>
							<tr>
								<th><em class="requireField">*</em>重大危险源名称：</th>
								<td>
									<input type="text" class="formText" name="ds.dangerSourceName" maxlength="6" 
											value="<s:property value="ds.dangerSourceName" />"
											valid="required" errmsg="wuhaiDangerSource.dangerSourceName_not_null" />
								</td>
								<th></th>
								<td></td>
							</tr>
							<tr>
								<th><em class="requireField">*</em>危险源级别：</th>
								<td>
									<input type="text" class="formText" name="ds.rvalue" maxlength="6" style="width: 90%;"
											placeholder="请输入危险源的R值"
											value="<s:property value="ds.rvalue" />"
											valid="required|isNumber" errmsg="wuhaiDangerSource.rvalue_not_null|wuhaiDangerSource.rvalue_not_number" />
									<input type="hidden" name="ds.dangerLevel" value="<s:property value="ds.dangerLevel" />" />
									<c:choose>
										<c:when test="${ds.dangerLevel == 1 }">
											<span style="margin-left: 5px;">一级</span>
										</c:when>
										<c:when test="${ds.dangerLevel == 2 }">
											<span style="margin-left: 5px;">二级</span>
										</c:when>
										<c:when test="${ds.dangerLevel == 3 }">
											<span style="margin-left: 5px;">三级</span>
										</c:when>
										<c:when test="${ds.dangerLevel == 4 }">
											<span style="margin-left: 5px;">四级</span>
										</c:when>
										<c:when test="${ds.dangerLevel == 10 }">
											<span style="margin-left: 5px;">其他</span>
										</c:when>
										<c:otherwise></c:otherwise>
									</c:choose>
									<%-- <select name="ds.dangerLevel" >
										<option value="1">一级</option>
										<option value="2">二级</option>
										<option value="3">三级</option>
										<option value="10">其他</option>
									</select> --%>
								</td>
								<th></th>
								<td></td>
							</tr>
							<tr>
								<th>投用时间：</th>
								<td>
									<input type="text" class="Wdate formText" name="ds.beginUseTimeStr" 
											value="<fmt:formatDate value="${ds.beginUseTime }" type="both" pattern="yyyy-MM-dd" />"
											onclick="WdatePicker({skin:'default',dateFmt:'yyyy-MM-dd',minDate:'%y-%M-%d'})" />
								</td>
								<th></th>
								<td></td>
							</tr>
							<tr>
								<th>评价机构：</th>
								<td>
									<input type="text" class="formText" name="ds.evaluteOrg" maxlength="6" 
											value="<s:property value="ds.evaluteOrg" />"  />
								</td>
								<th></th>
								<td></td>
							</tr>
							<tr>
								<th>最新评价时间：</th>
								<td>
									<input  type="text" class="Wdate formText" name="ds.evaluteTimeStr" 
											value="<fmt:formatDate value="${ds.evaluteTime }" type="both" pattern="yyyy-MM-dd" />"
											onclick="WdatePicker({skin:'default',dateFmt:'yyyy-MM-dd',minDate:'%y-%M-%d'})" />
								</td>
								<th></th>
								<td></td>
							</tr>
							<tr>
								<th><em class="requireField">*</em>重大危险源所在地址：</th>
								<td>
									<input type="text" class="formText" name="ds.address" maxlength="6" 
											value="<s:property value="ds.address" />"
											valid="required" errmsg="wuhaiDangerSource.address_not_null" />
								</td>
								<th></th>
								<td></td>
							</tr>
							<tr>
								<th><em class="requireField">*</em>单元内主要装置、设施及生产（存储）规模：</th>
								<td>
									<input type="text" class="formText" name="ds.productScale" maxlength="6" 
											value="<s:property value="ds.productScale" />"
											valid="required" errmsg="wuhaiDangerSource.productScale_not_null" />
								</td>
								<th></th>
								<td></td>
							</tr>
							<tr>
								<th>是否位于化工（工业）园区：</th>
								<td>
									<select name="ds.industryAreaId">
										<option value="0">否</option>
										<option value="1">园区1</option>
										<option value="2">园区2</option>
									</select>
								</td>
								<th></th>
								<td></td>
							</tr>
							<tr>
								<th><em class="requireField">*</em>重大危险源与周边重点防护目标最近距离情况（m）：</th>
								<td>
									<input type="text" class="formText" name="ds.minDistance" 
											value="<s:property value="ds.minDistance" />"
											valid="required|isNumber" errmsg="wuhaiDangerSource.minDistance_not_null|wuhaiDangerSource.minDistance_not_number" />
								</td>
								<th></th>
								<td></td>
							</tr>
							<tr>
								<th>厂区边界外500米范围内人数估算值：</th>
								<td>
									<input type="text" class="formText" name="ds.estimatePeopleCount" 
											value="<s:property value="ds.estimatePeopleCount" />"
											valid="isNumber" errmsg="wuhaiDangerSource.estimatePeopleCount_not_number" />
								</td>
								<th></th>
								<td></td>
							</tr>
							<tr>
								<th><em class="requireField">*</em>近三年内危险化学品事故情况：</th>
								<td>
									<input type="text" class="formText" name="ds.accidentDesc" maxlength="6" 
											value="<s:property value="ds.accidentDesc" />"
											valid="required" errmsg="wuhaiDangerSource.accidentDesc_not_null" />
								</td>
								<th></th>
								<td></td>
							</tr>
							<tr>
								<th>监测对象：</th>
								<td>
									<input type="text" class="formText" name="ds.monitoredObjects" maxlength="6" 
											value="<s:property value="ds.monitoredObjects" />" />
								</td>
								<th></th>
								<td></td>
							</tr>
						</tbody>
					</table>
				</form>
			</div>
		</div>
		<div class="buttonArea"> 
			<input type="button" class="formButton_green" value="确定" hidefocus="" id="submit"/>
			<input type="button" value="取消" class="formButton_grey"  hidefocus="" onclick="javascript :history.back(-1);"/>
		</div>
	</div>
	<div class="clear"></div>
</body>
</html>
