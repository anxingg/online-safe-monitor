<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<jsp:include page="../head.jsp" />
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>企业基本信息</title>
	<script type="text/javascript" src="${ctx}wh/js/company/companyBaseView.js?version=${version}"></script>
</head>
<body>
<div class="bread-line">
  <label>当前位置：</label><a href="#">首页</a>&gt;&nbsp;<a href="#">企业基本信息</a>
</div>
<input type="hidden" id="groupId" value='<s:property value="#session.adminUser.groupId"/>'/>
   <div class="formPage">
   <div class="formbg">
        <div class="big_title">企业基本信息</div>
        <div class="content_form">
                <table width="100%" cellspacing="0" cellpadding="0" border="0" class="inputTable">
                    <tbody>
                        <tr>
                        	
                            <th>企业编号：</th>
                            <td>
                            	<span id="companyId"></span>
                            </td>
                            <th>企业规模：</th>
                            <td>
                            	<span id="enterpriseScale"></span>
                            </td>
                        </tr>
                        <tr>
                            <th>企业名称：</th>
                            <td>
                            	<span id="companyName"></span>
                            </td>
                            <th>企业所在地：</th>
                            <td>
                            	<span id="cityId"></span>
                            </td>
                        </tr>
                         <tr>
                            <th>企业法人：</th>
                            <td>
                            	<span id="legalRepresentative"></span>
                            </td>
                        </tr>
                        <tr>
                            <th>企业简介：</th>
                            <td colspan="3">
                            	<span id="introduction"></span>
							</td>
                        </tr>
                    </tbody>
                </table>
        </div>
        
        
   </div>
    <div class="buttonArea"> 
    	<input type="button" value="信息修改" class="formButton_green"  hidefocus="" onclick="javascript :window.location.href='${ctx}companywh/toUpdateCompany.action'"/>
		<input type="button" value="信息详情" class="formButton_green"  hidefocus="" onclick="javascript :window.location.href='${ctx}companywh/toCompanyView.action'"/>
	</div>
   </div>


</body>
</html>

