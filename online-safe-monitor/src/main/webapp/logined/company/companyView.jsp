<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<jsp:include page="../../common/osmHead.jsp" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>企业信息</title>
</head>
<body>

<input type="hidden" id="groupId" value='${paramValues.groupId[0]}'/>
   <div class="formPage">
   <div class="formbg">
	    <div class="big_title">企业基本信息</div>
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
                            <th>区域：</th>
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
   </div>
    <div class="buttonArea"> 
    		<input type="button" value="返回" class="formButton_grey"  hidefocus="" onclick="javascript :history.back(-1);"/>
	</div>
   </div>


</body>
</html>

