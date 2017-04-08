<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<jsp:include page="../../common/osmHead.jsp" />
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>企业信息设置</title>
	<link href="${ctx }flat/plugins/tree/skins/tree_default.css" rel="stylesheet" type="text/css" />
	<!-- 验证start -->
	<script type="text/javascript" src="${ctx}js/common/validate_form.js?version=${version}"></script>
	<script type="text/javascript" src="${ctx}common/js/js_lang_cn.js?version=${version}"></script>
	<script type="text/javascript" src="${ctx}js/common/showError.js?version=${version}"></script>
	<!-- 验证end -->
	<script type="text/javascript" src="${ctx}/js/logined/company/companyUpdate.js?version=${version}"></script>
	<!-- 上级组织  start-->
	<script type="text/javascript" src="${ctx }flat/plugins/tree/skins/jquery.ztree.all-3.2.min.js"></script>
	<script type="text/javascript" src="${ctx}js/common/hashmap.js"></script>
	<script type="text/javascript" src="${ctx}js/common/treeNode.js"></script>
	<script type="text/javascript" src="${ctx}js/logined/group/selectArea.js"></script>
	<!-- 上级组织  end-->
</head>
<body>
<div class="bread-line">
  <label>当前位置：</label><a href="#">首页</a>&gt;&nbsp;<a href="#">企业信息设置</a>
</div>
<input type="hidden" id="groupId" value='${paramValues.groupId[0]}'/>
<input type="hidden" id="parentId" value='${paramValues.parentId[0]}'/>
<input type="hidden" id="action" value='${paramValues.action[0]}'/>
<input type="hidden" id="ssx" value="${cpy.cityId }" />
   <div class="formPage">
   <div class="formbg">
      <form id="form1">
        <div class="big_title" id="title">
        	<c:choose>
			   <c:when test="${paramValues.action[0] == 'add'}">  
			  		企业信息新增
			   </c:when>
			   <c:otherwise> 
			   		企业信息修改
			   </c:otherwise>
			</c:choose>
        </div>
        <div class="content_form">
                <table width="100%" cellspacing="0" cellpadding="0" border="0" class="inputTable">
                    <tbody>
                        <tr>
	                            <c:choose>
								   <c:when test="${paramValues.action[0] == 'add'}">  
								  		<th><em class="requireField">*</em>企业名称：</th>
      									<td><input type="text" class="formText" id="companyName" maxlength="32"
                            					valid="required" errmsg="comreg.name_not_null"/></td>
								   </c:when>
								   <c:otherwise> 
								   		<th>企业名称：</th>
                            			<td>
										   	<input type="hidden"  id="qymc" value="${cpy.companyName }"/>
			                            	<lable>${cpy.companyName }</lable>
		                            	</td>
								   </c:otherwise>
								</c:choose>
                            
                            <th>工商注册地址：</th>
                            <td><input id="gszcdz" type="text" class="formText" value="${cpy.registrationAddress }" maxlength="100"/></td>
                        </tr>
                        <tr>
                            <th><em class="requireField">*</em>单位代码：</th>
                            <td><input type="text" id="dwdm" class="formText" value="${cpy.companyCode }" maxlength="32"
                            	valid="required" errmsg="wuhaicom.dwdm_not_null"/></td>
                            <th><em class="requireField">*</em>区域：</th>
                            <td>
                            	<div id="treeContent" style="z-index:66;position:relative">
								<input id="groupSel" type="text" readonly="readonly" class="formText iconTree" style="width:100%" valid="required" errmsg="group.parent_group_not_null" />
								<!-- <a class="icon_clear" href="#" id="parentRemove">清空</a> 
								<span class="selectdot" id="groupSel_div"></span> -->
								<div id="menuContent" style="position: absolute;display: none;">
									<ul id="groupTree" class="ztree" style="position: absolute; margin-top: 0; width: 375px;height:150px;overflow:auto; background: #ffffff;  border: 1px solid #8a9ba5"></ul>
								</div>
							</div>
                            </td>
                        </tr>
                         <tr>
                            <th>单位性质：</th>
                            <td><input id="dwxz" type="text" class="formText" value="${cpy.companyProperty }" maxlength="32"/></td>
                            <th><em class="requireField">*</em>成立时间：</th>
                            <td>
                            	<input id="clsj" type="text" class="formText Wdate" style="width: 240px"
             						onclick="WdatePicker({skin:'default',dateFmt:'yyyy-MM-dd'})" value='<s:date format="yyyy-MM-dd" name="#request.cpy.establishmentTime" />'
             						valid="required" errmsg="wuhaicom.clsj_not_null"/>
							</td>
                        </tr>
                         <tr>
                            <th>营业执照注册号：</th>
                            <td><input id="yyzzch" type="text" class="formText" value="${cpy.businessLicence }" maxlength="32"/></td>
                             <th>营业执照生产范围：</th>
                            <td><input id="yyzzscfw" type="text" class="formText" value="${cpy.productionScope }" maxlength="32"/></td>
                        </tr>
                         <tr>
                            <th><em class="requireField">*</em>法定代表人：</th>
                            <td><input id="fdr" type="text" class="formText" value="${cpy.legalRepresentative }" maxlength="32"
                            	valid="required" errmsg="wuhaicom.fddbr_not_null"/></td>
                            <th>经济类型：</th>
                            <td>
                            	<input id="economicType" type="hidden" value="${cpy.economicType }"/>
                            	<select id="jjlx">
                            		<option value="-1">请选择</option>
                            		<option value="国有经济">国有经济</option>
                            		<option value="集体经济">集体经济</option>
                            		<option value="私营经济">私营经济</option>
                            		<option value="个体经济">个体经济</option>
                            		<option value="联营经济">联营经济</option>
                            		<option value="股份制">股份制</option>
                            		<option value="外商投资">外商投资</option>
                            		<option value="港澳台投资">港澳台投资</option>
                            		<option value="其它经济">其它经济</option>
                            	</select>
                            	<select id="jjlxfl">
                            	</select>
                            </td>
                        </tr>
                         <tr>
                            <th>组织机构代码：</th>
                            <td><input id="zzjgdm" type="text" class="formText" value="${cpy.unitCode }" maxlength="32"/></td>
                            <th>生产场所地址：</th>
                            <td><input id="sccsdz" type="text" class="formText" style="width:60%" value="${cpy.productAddress }" maxlength="100"/><em class="tip">(多条用“,”分割)</em></td>
                        </tr>
                         <tr>
                            <th>企业网站：</th>
                            <td><input type="text" id="qywz" class="formText" value="${cpy.website }" maxlength="32"/></td>
                            <th>邮政编码：</th>
                            <td><input type="text" id="yzbm" class="formText" value="${cpy.postalcode }" 
								onkeyup="this.value=this.value.replace(/[^0-9]/g,'')" maxlength="6"/></td>
                        </tr>
                         <tr>
                            <th>经纬度：</th>   
                            <td><input type="text" style="width: 110px" id="jd" class="formText" value="${cpy.precision }"
                            	onkeyup="this.value=this.value.replace(/[^0-9\.]/g,'')" maxlength="8"/>&nbsp;&nbsp;
                            	<input type="text" style="width: 110px" id="wd" class="formText" value="${cpy.dimension }"
                            	onkeyup="this.value=this.value.replace(/[^0-9\.]/g,'')" maxlength="8"/>
                            </td>
                        
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
                            <th>是否在工业园区：</th>
                            <td>
                            <input type="hidden" id="hizyq" value="${cpy.isIn }"/>
                            	<select id="zyq">
	                            	<option value="1">是</option>
	                            	<option value="0">否</option>
                            	</select>
                            </td>
                            <th><em class="requireField">*</em>生产状况：</th>
                            <td>
                            <input type="hidden" id="hisczk" value="${cpy.productType }"/>
                            	<select id="sczk">
	                            	
                            	</select>
                            </td>
                         </tr>
                         <tr>
		                   <th>行业类型：</th>
		                   <td colspan="3">
		                   	<input type="hidden" id="hihylx" value="${cpy.industryClassification }"/>
		                   		<label class="radio"><input name="hylx" type="checkbox" id="1" value="氯碱工业" />氯碱工业</label>
		                   		<label class="radio"><input name="hylx" type="checkbox" id="2" value="电石工业" />电石工业</label>
		                   		<label class="radio"><input name="hylx" type="checkbox" id="3" value="冶金" />冶金</label>
		                   		<label class="radio"><input name="hylx" type="checkbox" id="4" value="精细化工" />精细化工</label>
		                   		<label class="radio"><input name="hylx" type="checkbox" id="5" value="煤化工" />煤化工</label>
		                   		<label class="radio"><input name="hylx" type="checkbox" id="6" value="其他" />其他</label>
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

