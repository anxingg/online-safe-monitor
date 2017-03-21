<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<jsp:include page="../head.jsp" />
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>企业证照</title>
</head>
<body>
<div class="bread-line">
  <label>当前位置：</label><a href="#">首页</a>&gt;&nbsp;<a href="#">企业证照</a>
</div>
<div class="tabBox">
  <ul>
    <li><a href="${ctx}companywh/toCompanyView.action?groupId=${param.groupId }">企业信息</a></li>
    <li><a href="${ctx}wh/logined/company/legalPersonView.jsp?groupId=${param.groupId }">法人信息</a></li>
    <li class="on"><a href="#">企业证照</a></li>
    <li><a href="${ctx}companywh/sis_jmpPage.action?operation=4&whCompany.groupId=${param.groupId}" >安全机构管理</a></li>
  </ul>
</div>

	<input type="hidden" id="groupId" value='<s:property value="#session.adminUser.groupId"/>'/>
   <div class="formPage">
	   <div class="formbg">
	        <div class="big_title">企业证照</div>
	        <div class="content_form">
	        <s:if test="list.size()==0">
	        <h2 class="small_title">企业暂未上传证照</h2>
	        </s:if>
	         <table width="100%" cellspacing="0" cellpadding="0" border="0" class="inputTable">
                <tbody>
                <s:iterator value="list" status="stuts">
                	<tr>
                    	<td><label><s:property value="photoName"/>：</label></td>
                  	</tr>
				   	<tr>
	                    <td>
	                    <img alt="<s:property value="photoName"/>" src="<s:property value="downPath"/>upload/<s:property value="attachmentPath"/>" height="200px"/>
	                    </td>
                  	</tr>
                 </s:iterator>
                </tbody>
             </table>
	        
			
		</div>
	   
   </div>
   <div class="buttonArea"> 
    <input type="button" value="返回" class="formButton_grey"  hidefocus="" onclick="javascript :history.back(-1);"/>
	</div>
</div>

<div class="clear"></div>
</body>
</html>

