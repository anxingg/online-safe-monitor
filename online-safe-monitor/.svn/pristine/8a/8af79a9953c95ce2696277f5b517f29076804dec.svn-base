<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/ninclude/import.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>危化品</title> 
	<%@include file="/ninclude/title.jsp"%>
	<script type="text/javascript" src="${ctx}common/js/js_lang_cn.js"></script>
	
	<!-- 验证空值 -->
	<script type="text/javascript" src="${ctx}wh/js/safeAccident/verifyEmpty.js"></script>
	
	<script type="text/javascript" src="${ctx}wh/js/zhdwxy/addDSG.js"></script>
</head>
<body>
	<input type="hidden" id="vid" value="<s:property value="dangerSourcesGood.vid"/>"/>
	
	<div class="formPage">
	   <div class="formbg">
	        <div class="content_form">
	        	<form id="form1" >
	                <table width="100%" cellspacing="0" cellpadding="0" border="0" class="inputTable">
	                    <tbody>
	                    	<tr>
	                            <th>危化品名称：</th>
	                            <td><s:if test="operation == 4"><s:property value="dangerSourcesGood.dangerGoodName"/></s:if><s:else><input type="text" class="formText" id="dangerGoodName" maxlength="32"
	                            	 	value="<s:property value="dangerSourcesGood.dangerGoodName"/>"
	                            		valid="required" errmsg="dangersourcesgood.dangerGoodName_not_null" /></s:else></td>
	                        </tr>
	                        <tr>
	                        	<th>危险性类别：</th>
	                            <td><s:if test="operation == 4"><s:property value="dangerSourcesGood.dictIdName"/></s:if><s:else><select id="dictId">
								<option value="0">请选择</option>
								<s:iterator value="dictMap" id="entry">
									<s:if test="dangerSourcesGood.dictId == key">
									<option value="<s:property value="key"/>" selected="selected" ><s:property value="value"/></option>
									</s:if>
									<s:else>
									<option value="<s:property value="key"/>"><s:property value="value"/></option>
									</s:else>
								</s:iterator>
								</select></s:else></td>
	                        </tr>
	                          <tr>
	                            <th>UN编号：</th>
	                            <td><s:if test="operation == 4"><s:property value="dangerSourcesGood.unCode"/></s:if><s:else><input type="text" class="formText" id="unCode" maxlength="32" 
	                            		value="<s:property value="dangerSourcesGood.unCode"/>"
	                            		valid="required" errmsg="dangersourcesgood.unCode_not_null" /></s:else></td>
	                        </tr>
	                        <tr>
	                           <th>生产用途：</th>
	                            <td><s:if test="operation == 4"><s:property value="dangerSourcesGood.purpose"/></s:if><s:else><input type="text" class="formText" id="purpose" maxlength="32" 
	                            		value="<s:property value="dangerSourcesGood.purpose"/>"
	                            		valid="required" errmsg="dangersourcesgood.purpose_not_null" /></s:else></td>
	                        </tr>
	                       
	                       <tr>
	                            <th>生产工艺：</th>
	                            <td><s:if test="operation == 4"><s:property value="dangerSourcesGood.process"/></s:if><s:else><input type="text" class="formText" id="process" maxlength="32" 
	                            		value="<s:property value="dangerSourcesGood.process"/>"
	                            		valid="required" errmsg="dangersourcesgood.process_not_null" /></s:else></td>
	                        </tr>
	                        
	                         <tr>
	                            <th>单个最大容器：</th>
                            	<td >物理状态：<s:if test="operation == 4"><s:property value="dangerSourcesGood.physicalState"/></s:if><s:else><input type="text" class="formText mr10" style="width:50px" id="physicalState" maxlength="32" 
                            			value="<s:property value="dangerSourcesGood.physicalState"/>" 
                            			valid="required" errmsg="dangersourcesgood.physicalState_not_null" /></s:else>
                            	&nbsp;&nbsp;&nbsp;&nbsp;操作温度：<s:if test="operation == 4"><s:property value="dangerSourcesGood.operationTemp"/></s:if><s:else><input type="text" class="formText mr10" style="width:50px" id="operationTemp" maxlength="5" 
                            			value="<s:property value="dangerSourcesGood.operationTemp"/>" 
                            			valid="required|isNumber" errmsg="dangersourcesgood.operationTemp_not_null|dangersourcesgood.operationTemp_not_number" /></s:else>℃
                            	&nbsp;&nbsp;&nbsp;&nbsp;操作压力：<s:if test="operation == 4"><s:property value="dangerSourcesGood.operationPressure"/></s:if><s:else><input type="text" class="formText mr10" style="width:50px" id="operationPressure" maxlength="5" 
                            			value="<s:property value="dangerSourcesGood.operationPressure"/>" 
                            			valid="required|isNumber" errmsg="dangersourcesgood.operationPressure_not_null|dangersourcesgood.operationPressure_not_number" /></s:else>MPa
                            	&nbsp;&nbsp;&nbsp;&nbsp;存量：<s:if test="operation == 4"><s:property value="dangerSourcesGood.simpleStock"/></s:if><s:else><input type="text" class="formText" style="width:50px" id="simpleStock" maxlength="5" 
                            			value="<s:property value="dangerSourcesGood.simpleStock"/>" 
                            			valid="required|isNumber" errmsg="dangersourcesgood.simpleStock_not_null|dangersourcesgood.simpleStock_not_number" /></s:else>&nbsp;&nbsp;t
                            	</td>
	                            
	                        </tr>
	                        
	                         <tr>
	                            <th>单元内危化品存量:</th>
	                            <td><s:if test="operation == 4"><s:property value="dangerSourcesGood.unitStock"/></s:if><s:else><input type="text" class="formText" id="unitStock" maxlength="8" 
	                            		value="<s:property value="dangerSourcesGood.unitStock"/>"
	                            		valid="required|isNumber" errmsg="dangersourcesgood.unitStock_not_null|dangersourcesgood.unitStock_not_number" /></s:else></td>
	                       </tr>
	                       <tr>
	                            <th>临界量：</th>
	                            <td><s:if test="operation == 4"><s:property value="dangerSourcesGood.criticalMass"/></s:if><s:else><input type="text" class="formText" id="criticalMass" maxlength="8" 
	                            		value="<s:property value="dangerSourcesGood.criticalMass"/>"
	                            		valid="required|isNumber" errmsg="dangersourcesgood.criticalMass_not_null|dangersourcesgood.criticalMass_not_number" /></s:else></td>
	                        </tr>
	                    </tbody>
	                </table>
	                </form>
	        </div>
	   </div>
	</div>
	<div class="clear"></div>
	</body>
</html>
