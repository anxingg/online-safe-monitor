<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>法人信息</title>
<jsp:include page="../head.jsp" />
<script type="text/javascript" src="${ctx}wh/js/company/legalPersonView.js"></script>
</head>
<body>
<div class="bread-line">
  <label>当前位置：</label><a href="#">首页</a>&gt;&nbsp;<a href="#">法人信息</a>
</div>
<div class="tabBox">
  <ul>
    <li><a href="${ctx}companywh/toCompanyView.action?groupId=${param.groupId }">企业信息</a></li>
    <li class="on"><a href="#">法人信息</a></li>
    <li><a href="${ctx}companywh/toPhotoView.action?groupId=${param.groupId }">企业证照</a></li>
    <li><a href="${ctx}companywh/sis_jmpPage.action?operation=4&whCompany.groupId=${param.groupId }" >安全机构管理</a></li>
  </ul>
</div>
	
		<input type="hidden" id="groupId" value="${param.groupId }"/>
	  <div class="formPage">
	 <div class="formbg">
	  <div class="big_title">法人信息</div>
        <div class="content_form">
                <table width="100%" cellspacing="0" cellpadding="0" border="0" class="inputTable">
                    <tbody>
                        
                        <tr>
                        	
                            <th>姓名：</th>
                            <td><span id="name"></span></td>
                            <th>联系电话：</th>
                            <td><span id="phone"></span></td>
                        </tr>
                        <tr>
                            <th>职务：</th>
                            <td><span id="job"></span></td>
                            
                        </tr>
                        <tr>
                        	<th>证件类型：</th>
                            <td><span id="cardType"></span></td>
                        	<th>证件号码：</th>
                            <td><span id="certificateNum"></span></td>
                        </tr>
                        <tr>
                        	<th>上传证件照片：</th>
                            <td colspan="2" rowspan="3">
                            <img alt="" id="img" src="${ctx}wh/images/up.jpg" height="200px"/>
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



<div class="clear"></div>
</body>
</html>

