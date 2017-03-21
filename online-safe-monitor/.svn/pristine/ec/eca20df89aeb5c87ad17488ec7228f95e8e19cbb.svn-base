<%@ page language="java" import="java.util.*"  contentType="text/html; charset=UTF-8" pageEncoding="utf-8"  %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<jsp:include page="../../../common/taglibs.jsp"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<jsp:include page="../head.jsp" />
	<title>进入考试</title>
    <script type="text/javascript" src="${ctx}wh/js/exam/userExam.js"></script> 
</head>
<body>
	<input type="hidden" id="examTestId" value="${examPaper.id }"/>
	<div class="bread-line">
		<label>当前位置：</label><a href="#">首页</a>&gt;<a href="#">人员考试</a>&gt;&nbsp;进入考试
	</div>
    <div class="formPage">
	 <div class="formbg">
        <div class="big_title">考试</div>
        <div class="content_form">
                <table width="100%" cellspacing="0" cellpadding="0" border="0" class="inputTable" id="tabInfo">
	              <tr>
	              	<th><label>企业名称：</label></th>
	             	<td>${companyName }</td>
	              </tr>
	              <tr>
	              <!--	<th><label>考试名称：</label></th>
	             	<td>${examTest.testName }</td>-->
	              <th><label>试卷名称：</label></th>
	             	<td>${examPaper.paperName }</td>
	              </tr>
	              <tr>
	                <th><label>考试人员姓名：</label></th>
	                <td><input type="text" class="formText" id="userName" maxlength="20" /></td>
	              </tr>
	              <tr>
	                <th><label>考试人身份证号：</label></th>
	                <td><input type="text" class="formText" id="idcard" maxlength="18" /></td>
	              </tr>
          </table>
        </div>
   </div>
   
   
    <div class="buttonArea"> 
			<input type="button"class="formButton_green" value="进入考试" hidefocus="" id="beginExam"/>
	</div>
  
  

<div class="clear"></div>
</body>
</html>
