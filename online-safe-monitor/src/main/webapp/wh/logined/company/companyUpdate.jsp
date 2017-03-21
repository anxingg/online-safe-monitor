<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<jsp:include page="../head.jsp" />
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>企业信息</title>
	<!-- 验证start -->
	<script type="text/javascript" src="${ctx}js/common/validate_form.js?version=${version}"></script>
	<script type="text/javascript" src="${ctx}common/js/js_lang_cn.js?version=${version}"></script>
	<script type="text/javascript" src="${ctx}js/common/showError.js?version=${version}"></script>
	<!-- 验证end -->
	<script type="text/javascript" src="${ctx}wh/js/company/companyUpdate.js?version=${version}"></script>
</head>
<body>
<div class="bread-line">
  <label>当前位置：</label><a href="#">首页</a>&gt;&nbsp;<a href="#">企业信息</a>
</div>
<div class="tabBox">
  <ul>
    <li class="on"><a href="#">企业信息</a></li>
    <li><a href="../wh/logined/company/updateLegalPerson.jsp">法人信息</a></li>
    <li><a href="../wh/logined/company/companyPhotoUpdate.jsp">企业证照</a></li>
    <li><a href="${ctx}companywh/sis_jmpPage.action?operation=2&whCompany.groupId=<s:property value="cpy.groupId"/>">安全机构管理</a></li>
  </ul>
</div>

   <div class="formPage">
   <div class="formbg">
	<input type="hidden" id="groupId" value='<s:property value="#session.adminUser.groupId"/>'/>
        		<form id="form1">
        <div class="big_title">企业信息修改</div>
        <div class="content_form">
          <table width="100%" cellspacing="0" cellpadding="0" border="0" class="inputTable">
              <tbody>
                  <tr>
                      <th>企业名称：</th>
                      <td><s:property value="cpy.companyName"/>
                      </td>
                      <th>工商注册地址：</th>
                      <td><s:property value="cpy.registrationAddress"/></td>
                  </tr>
                  <tr>
                      <th>经纬度：</th>   
                      <td>
                      	<s:property value="cpy.precision"/>&nbsp;&nbsp;
                      	<s:property value="cpy.dimension"/>
                      </td>
                      <th>省市县：</th>
                      <td>
                      	<s:property value="cpy.cityId"/>
                      </td>
                  </tr>
                   <tr>
                      <th>单位性质：</th>
                      <td><s:property value="cpy.companyProperty"/></td>
                      <th>成立时间：</th>
                      <td>
                      	<s:date format="yyyy-MM-dd" name="#request.cpy.establishmentTime" />
	</td>
                  </tr>
                   <tr>
                      <th>营业执照注册号：</th>
                      <td><s:property value="cpy.businessLicence"/></td>
                       <th>营业执照生产范围：</th>
                      <td><s:property value="cpy.productionScope"/></td>
                  </tr>
                   <tr>
                      <th>法定代表人：</th>
                      <td><s:property value="cpy.legalRepresentative"/></td>
                      <th>经济类型：</th>
                      <td>
                      	<s:property value="cpy.economicType"/>
                      </td>
                  </tr>
                   <tr>
                      <th>组织机构代码：</th>
                      <td><s:property value="cpy.unitCode"/></td>
                      <th>生产场所地址：</th>
                      <td><s:property value="cpy.productAddress"/></td>
                  </tr>
                   <tr>
                      <th>企业网站：</th>
                      <td><s:property value="cpy.website"/></td>
                      <th>邮政编码：</th>
                      <td><s:property value="cpy.postalcode"/></td>
                  </tr>
                   <tr>
                      <th>是否在工业园区：</th>
                      <td>
                      <s:if test="cpy.isIn==1 ">
                     	是
                      </s:if>
                      <s:if test="cpy.isIn==0 ">
                     	否
                      </s:if>
                      </td>
               <th></th>
               <td>
               	</td>
             </tr>
             <tr>
             <th>行业类型：</th>
               <td colspan="3">
               <s:property value="cpy.industryClassification"/>
               	</td>
             </tr>
              </tbody>
          </table>
        </div>
        
        
        <div class="big_title">企业其他信息</div>
        <div class="content_form">
           <table width="100%" cellspacing="0" cellpadding="0" border="0" class="inputTable">
               <tbody>
                   <tr>
                       <th><em class="requireField">*</em>单位代码：</th>
                       <td><input type="text" id="dwdm" class="formText" value="${cpy.companyCode }" maxlength="32"
                       	valid="required" errmsg="wuhaicom.dwdm_not_null"/></td>
                       <th>销售收入（万元）：</th>
                       <td><input type="text" id="xssr" class="formText" value="${cpy.sales }"
                       	onkeyup="this.value=this.value.replace(/[^0-9]/g,'')" maxlength="10"/></td>
                   </tr>
                    <tr>
                       <th>职工人数：</th>
                       <td><input type="text" id="jgrs" class="formText" value="${cpy.workersNum }"
                       	onkeyup="this.value=this.value.replace(/[^0-9]/g,'')" maxlength="10"/></td>
                  
                       <th><em class="requireField">*</em>企业规模：</th>
                       <td>
                       	<input type="hidden" id="hiqygm"  value="${cpy.enterpriseScale }" /> 
                       	<select id="qygm">
                        	<option value="1">20人以下</option>
                        	<option value="2">20-99人</option>
                        	<option value="3">100-499人</option>
                        	<option value="4">500-999人</option>
                        	<option value="5">1000-9999人</option>
                        	<option value="6">10000人以上</option>
                       	</select>
                       </td>
                       </tr>
                    <tr>
                       <th><em class="requireField">*</em>生产状况：</th>
                       <td>
                       <input type="hidden" id="hisczk" value="${cpy.productType }"/>
                       	<select id="sczk">
                        	
                       	</select>
                       </td>
                       <th></th><td></td>
                    </tr>
                    
              		<tr>
                       <th><em class="requireField">*</em>生产投入提取标准：</th>
                 	   <td colspan="3">
		                    <textarea class="formTextarea" rows="5" id="extractDescription" maxlength="500" 
		                    valid="required|textareaLength" errmsg="wuhaicom.extractDescription_not_null|wuhaicom.text_max_length" maxNumber="500">${cpy.extractDescription }</textarea>
		                	<span style="float:right">0-500字</span>
		                 </td>
                   </tr>
               </tbody>
           </table>
        </div>
        
        <div class="big_title">安全生产管理人员信息</div>
        <div class="content_form">
                <table width="100%" cellspacing="0" cellpadding="0" border="0" class="inputTable">
                    <tbody>
                        <tr>
                         	<th>管理人员人数：</th>
                            <td><input type="text" id="acscrs" class="formText" value="${cpy.safeManageUserNum }"
                            	onkeyup="this.value=this.value.replace(/[^0-9]/g,'')" maxlength="6"/></td>
                            <th><em class="requireField">*</em>负责人：</th>
                            <td><input type="text" id="acscfzr" class="formText" value="${cpy.safeManageUserName }" maxlength="32"
                            	valid="required" errmsg="wuhaicom.fzr_not_null"/></td>
                        </tr>
                         <tr>
                            <th>负责人移动电话：</th>
                            <td><input type="text" id="acscyddh" class="formText" value="${cpy.safeManageUserPhone }" 
                            	onkeyup="this.value=this.value.replace(/[^0-9\-]/g,'')" maxlength="12" 
                            	valid="isPhoneSimple" errmsg="wuhaicom.phone_not_mobile"/></td>
                            <th>负责人办公电话：</th>
                            <td><input type="text" id="acscbgdh" class="formText"value="${cpy.safeManageUserTel }"
                            	onkeyup="this.value=this.value.replace(/[^0-9\-]/g,'')" maxlength="12"
                            	valid="isPhoneSimple" errmsg="wuhaicom.phone_not_mobile"/></td>
                        </tr>
                         <tr>
                            <th>负责人电子邮箱：</th>
                            <td><input type="text" id="acscdzyx" class="formText" value="${cpy.safeManageUserEmail }" maxlength="32"
	                            valid="isEmail" errmsg="wuhaicom.this_not_email"/></td>
                        </tr>
                    </tbody>
                </table>
        </div>
        
        
        <div class="big_title">特种作业信息</div>
        <div class="content_form">
                <table width="100%" cellspacing="0" cellpadding="0" border="0" class="inputTable">
                    <tbody>
                       <tr>
                            <th>特种作业人员人数：</th>
                            <td><input type="text" id="tzzyrs" class="formText" value="${cpy.specialUserNum }"
                            	onkeyup="this.value=this.value.replace(/[^0-9]/g,'')" maxlength="6"/></td>
                            <th>安全生产标准等级：</th>
                            <td>
                            	<input type="hidden" id="hibzhdj" value="${cpy.safeProductGrade }"/>
                            	<select id="bzhdj">
                            	
                            	</select>
                            </td>
                        </tr>
                         <tr>
                            <th>应急咨询服务号码：</th>
                            <td><input type="text" id="zxhm" class="formText" value="${cpy.emergencyPhone }" 
                            	onkeyup="this.value=this.value.replace(/[^0-9\-]/g,'')" maxlength="12"
                            	valid="isPhoneSimple" errmsg="wuhaicom.phone_not_mobile"/></td>
                            <th>安全值班电话：</th>
                            <td><input type="text" id="zbdh" class="formText" value="${cpy.safeDutyPhone }"
                                onkeyup="this.value=this.value.replace(/[^0-9\-]/g,'')" maxlength="12"
                                valid="isPhoneSimple" errmsg="wuhaicom.phone_not_mobile"/></td>
                        </tr>
                    </tbody>
                </table>
        </div>
        
        <div class="big_title">企业相关资质信息</div>
        <div class="content_form">
                <table width="100%" cellspacing="0" cellpadding="0" border="0" class="inputTable">
                    <tbody>
                       <tr>
                            <th>进口企业资质证明名称：</th>
                            <td>
                            	<input type="hidden" id="hizmmc" value="${cpy.importCompanyQualification }"/>
                            	<select id="zmmc">
                            	
                            	</select>
                            </td>
                            <th>进口企业资质证明编号：</th>
                            <td><input type="text" id="zzbh" class="formText" value="${cpy.importCompanyQualificationNum }" maxlength="32"/></td>
                        </tr>
                        <tr>
                            <th>主要产品及生产规模：</th>
                            <td colspan="3">
		                        <textarea class="formTextarea" rows="5" id="zycp" maxlength="256" 
	                       	 	valid="textareaLength" errmsg="wuhaicom.text_max_length" maxNumber="256">${cpy.product }</textarea>
	                    		<span style="float:right">0-256字</span>
		                    </td>
                        </tr>
                        <tr>
                            <th>企业简介：</th>
		                    <td colspan="3">
	                        <textarea class="formTextarea" rows="5" id="qyjj" maxlength="1000" 
	                        valid="textareaLength" errmsg="wuhaicom.text_max_length" maxNumber="1000">${cpy.introduction }</textarea>
	                    	<span style="float:right">0-1000字</span>
		                    </td>
                        </tr>
                        <tr>
		                   <th>单位或设备情况：</th>
		                   <td colspan="3" style="width: 200px">
		                   <input type="hidden" id="hidwsb" value="${cpy.outsideSituation }"/>
		                   		<label class="radio"><input id="1" name="dwsb" type="checkbox" value="医院"/>医院</label>
		                   		<label class="radio"><input id="2" name="dwsb" type="checkbox" value="学校" />学校</label>
		                   		<label class="radio"><input id="3" name="dwsb" type="checkbox" value="高速公路" />高速公路</label>
		                   		<label class="radio"><input id="4" name="dwsb" type="checkbox" value="林区" />林区</label>
		                   		<label class="radio"><input id="5" name="dwsb" type="checkbox" value="河流 山" />河流 山</label>
		                   		<label class="radio"><input id="6" name="dwsb" type="checkbox" value="居民区" />居民区</label>
		                   		<label class="radio"><input id="7" name="dwsb" type="checkbox" value="工厂" />工厂</label>
		                   		<label class="radio"><input id="8" name="dwsb" type="checkbox" value="铁路" />铁路</label>
		                   		<label class="radio"><input id="9" name="dwsb" type="checkbox" value="其他" />其他</label>
		                   		<br/><em class="tip">(厂区边界外1000米范围内)</em>
		                   	</td>
		                 </tr>
                    </tbody>
                </table>
        </div>
        
        </form>
   </div>
    <div class="buttonArea"> 
			<input type="button"class="formButton_green" value="确定" id="submit" />
			<input type="button" value="取消" class="formButton_grey"  hidefocus="" onclick="javascript :history.back(-1);"/>
	</div>
   </div>

</body>
</html>

