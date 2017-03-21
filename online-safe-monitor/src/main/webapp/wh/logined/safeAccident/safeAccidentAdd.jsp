<%@ page language="java" import="java.util.*"  contentType="text/html; charset=UTF-8" pageEncoding="utf-8"  %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../../../common/taglibs.jsp"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<jsp:include page="../head.jsp" />
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>安全生产事故</title>
	<%-- <link rel="stylesheet" type="text/css" href="${ctx}wh/jquery-easyui-1.4.3/themes/default/easyui.css"/> --%>
	<%-- <link rel="stylesheet" type="text/css" href="${ctx}wh/jquery-easyui-1.4.3/themes/icon.css"/> --%>
	<%-- <link rel="stylesheet" type="text/css" href="${ctx}wh/css/style.css"/> --%>
	<!-- <link rel="stylesheet" type="text/css" href="plugins/form/skins/form_default.css" /> -->
	<style type="text/css">
		.uploadify{float:left;margin-top:7px;}
	</style>
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
	<%-- <script type="text/javascript" src="${ctx}/wh/js/safeAccident/initType.js"></script> --%>
	<script type="text/javascript" src="${ctx}wh/js/safeAccident/safeAccidentAdd.js"></script>
    
</head>
<body>
	<!-- 隐藏域：角色（1、政府； 2、企业） -->
	<input type="hidden" id="whroletype" value="<s:property value="#session.whroletype"/>" />
	<!-- 隐藏域：企业ID -->
	<input type="hidden" id="group_id" value="<s:property value="#session.adminUser.groupId"/>" />
	<!-- 隐藏域：ID -->
	<input type="hidden" id="vid" value="<s:property value="safeAccident.vid"/>"/>
	<!-- 隐藏域：安全生产事故的性质 -->
	<input type="hidden" id="hiddenAccidentCharacter" value="<s:property value="safeAccident.accidentCharacter"/>"/>
	<!-- 隐藏域：操作类型（2、修改； 4、查看） -->
	<input type="hidden" id="operation" value="<s:property value="operation"/>"/>
	
	<div class="bread-line"><%-- <s:if test="safeAccident.vid != null">other</s:if><s:else>新增</s:else> --%>
		<label>当前位置：</label><a href="#">安全生产事故</a>&gt;&nbsp;安全生产事故<s:if test="safeAccident.vid != null"><s:if test="operation==4">详情</s:if><s:else>修改</s:else></s:if><s:else>新增</s:else>
	</div>
	<div class="formPage">
		<form id="form1">
		<div class="formbg">
			<div class="big_title"><s:if test="safeAccident.vid != null"><s:if test="operation==4">详情</s:if><s:else>修改</s:else></s:if><s:else>新增</s:else></div>
			<div class="content_form">
				<!-- <h2 class="small_title">基本信息</h2> -->
				<table width="100%" cellspacing="0" cellpadding="0" border="0" class="inputTable">
					<tbody>
						<tr>
							<th><em class="requireField">*</em><label>事故名称：</label></th>
							<td><input type="text" class="formText" id="accidentName" value="<s:property value="safeAccident.accidentName"/>" <c:if test="${operation==4 }">disabled="disabled"</c:if> valid="required" errmsg="wuhaiSafeAccident.accidentName_not_null" maxlength="32"/></td>
							<th><em class="requireField">*</em><label>事故调查人/部门：</label></th>
							<td><input type="text" class="formText" id="responsible" value="<s:property value="safeAccident.responsible"/>" <c:if test="${operation==4 }">disabled="disabled"</c:if> valid="required" errmsg="wuhaiSafeAccident.responsible_not_null" maxlength="32"/></td>
						</tr>
						<tr>
							<th><em class="requireField">*</em><label>事故发生时间：</label></th>
							<td colspan="3"><input type="text" class="formText Wdate" id="occurredTime" value="<s:date format="yyyy-MM-dd" name="safeAccident.occurredTime" />" onclick="WdatePicker({skin:'default',dateFmt:'yyyy-MM-dd'})" <c:if test="${operation==4 }">disabled="disabled"</c:if> valid="required" errmsg="wuhaiSafeAccident.occurredTime_not_null" /></td>
						</tr>
						<s:if test="#session.whroletype == 1">
						<tr>
							<th><label>企业是否可见：</label></th>
							<td  colspan="3"><label class="radio"><input type="radio" name="canSee" checked="checked" value="1"/>可见</label> <label class="radio"><input type="radio" name="canSee" value="0"/>不可见</label></td>
						</tr>
						<tr>
							<th><em class="requireField">*</em><label>企业名称：</label></th>
							<td  colspan="3"><select id="companName">
								<option value="0">请选择</option>
								<s:iterator value="companyMap" id="entry">
									<s:if test="safeAccident.groupId == key">
									<option value="<s:property value="key"/>" selected="selected" ><s:property value="value"/></option>
									</s:if>
									<s:else>
									<option value="<s:property value="key"/>"><s:property value="value"/></option>
									</s:else>
									
								</s:iterator> 
							</select></td>
						</tr>
						</s:if>
						<tr>
							<th><em class="requireField">*</em><label>事故性质：</label></th>
							<td>
								<select id="accidentCharacter" >
									<option value="0">请选择</option>
									<s:iterator value="accidentCharacterTypemap" id="entry2">
										<s:if test="safeAccident.accidentCharacter == key">
										<option value="<s:property value="key"/>" selected="selected"><s:property value="value"/></option>
										</s:if>
										<s:else>
										<option value="<s:property value="key"/>" ><s:property value="value"/></option>
										</s:else>
									</s:iterator>
								</select>
							</td>
							<th><em class="requireField">*</em><label>事故地点：</label></th>
							<td><input type="text" class="formText" id="occurredAddress" value="<s:property value="safeAccident.occurredAddress"/>" <c:if test="${operation==4 }">disabled="disabled"</c:if> valid="required" errmsg="wuhaiSafeAccident.occurredAddress_not_null" maxlength="32" /></td>
						</tr>
						<tr>
							<th><label>事故简介：</label></th>
							<td  colspan="3">
								<textarea rows="5" class="formTextarea" id="occurredDescription" <c:if test="${operation==4 }">disabled="disabled"</c:if> maxlength="1000" ><s:property value="safeAccident.occurredDescription"/></textarea>
								<span style="float:right">0-1000字</span>
							</td>
						</tr>
						<tr>
							<th><label>事故原因：</label></th>
							<td  colspan="3">
								<textarea rows="5" class="formTextarea" id="occurredReason" <c:if test="${operation==4 }">disabled="disabled"</c:if> maxlength="1000" ><s:property value="safeAccident.occurredReason"/></textarea>
								<span style="float:right">0-1000字</span>
							</td>
						</tr>
						<tr>
							<th><label>事故后果：</label></th>
							<td colspan="3">
							<textarea rows="5" class="formTextarea" id="occurredConsequence" <c:if test="${operation==4 }">disabled="disabled"</c:if> maxlength="1000" ><s:property value="safeAccident.occurredConsequence"/></textarea>
							<span style="float:right">0-1000字</span>
							</td>
						</tr>
						<tr>
							<th><label>事故处理情况：</label></th>
							<td colspan="3">
							<textarea rows="5" class="formTextarea" id="processCondition" <c:if test="${operation==4 }">disabled="disabled"</c:if> maxlength="1000" ><s:property value="safeAccident.processCondition"/></textarea>
							<span style="float:right">0-1000字</span>
							</td>
						</tr>
						<tr>
							<th><label>备注：</label></th>
							<td colspan="3">
							<textarea class="formTextarea" id="memo" <c:if test="${operation==4 }">disabled="disabled"</c:if> maxlength="256" ><s:property value="safeAccident.memo"/></textarea>
							</td>
						</tr>
						<tr>
							<th><label>事故报告：</label></th>
							<td>
								<s:if test="operation==4">
						        </s:if>
								<s:else>
								<input type="hidden" id="attachmentId" name="attachmentId" value="<s:property value="safeAccident.reportId"/>"/>
								<input id="file_upload" name="fileupload" type="file" multiple="true" />
								<!-- 上传队列 -->
							    <div id="queue"  style="display:none;"></div>
						        <div class="annex">
							        <ul id="attachmentList">
							        <s:if test="attachment != null">
							        <li>
										<div class="icon"><em class="<s:property value="attachment.attacthSuffix"/>" ></em></div>
										<div class="txt" style="width:700px">
											<p><s:property value="attachment.attachName"/></p>
											<p><a style="cursor:pointer"  class="deleteAttachment">删除</a></p>
										</div>
									</li>
							        </s:if>
							        </ul>
						        </div>
								</s:else>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
		<div class="buttonArea">
			<s:if test="operation==4">
			<!-- <input type="button" value="返回" class="formButton_grey"  hidefocus="" onclick="goback();return false;"/> -->
			<input type="button" value="返回" class="formButton_grey"  hidefocus="" onclick="javascript:history.go(-1);"/>
			</s:if>
			<s:else>
			<input type="button" value="确定" class="formButton_green" hidefocus=""/>
			<input type="button" value="取消" class="formButton_grey"  hidefocus="" onclick="javascript:history.go(-1);"/>
			</s:else>
		</div>
		</form>
	</div>
	<div class="clear"></div>

</body>
</html>
