<%@ page language="java" import="java.util.*"  contentType="text/html; charset=UTF-8" pageEncoding="utf-8"  %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<jsp:include page="../../../common/taglibs.jsp"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<jsp:include page="../head.jsp" />
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>考试修改</title>
	
		<script type="text/javascript" src="${ctx}js/common/validate_form.js"></script>
    <script type="text/javascript" src="${ctx}js/common/showError.js"></script>
    <script type="text/javascript" src="${ctx}js/logined/js_lang_cn.js"></script>
        <script type="text/javascript" src="${ctx}wh/js/exam/updateExamTest.js"></script> 
    
</head>
<body>
	<div class="bread-line">
		<label>当前位置：</label><a href="#">首页</a>&gt;<a href="#">考试</a>&gt;&nbsp;修改
	</div>
	 <div class="formPage">
	 <div class="formbg">
        <div class="big_title">修改<em>（考试）</em></div>
        <div class="content_form">
                <form id="form1">
                <table width="100%" cellspacing="0" cellpadding="0" border="0" class="inputTable">
                    <tbody>
                    	<input type="hidden" id="group_id" value='<s:property value="#session.adminUser.groupId"/>'/>
                    	<input type="hidden" id="examTestId" value="${param.examTestId}" />
            <tr>
            <th><label>企业名称：</label></th>
            <td id="companyName"></td>
            </tr>
                    <tr><th><em class="requireField">*</em><label>考试类型：</label></th>
                <td><select id="examType"></select></td>
                </tr>
                
             <th><em class="requireField">*</em>考试名称：</th>
                            <td><input type="text" class="formText" id="testName" maxlength="32"
                            	valid="required" errmsg="test.test_not_null"/></td>
                 
            
                        <tr>
                    		<th><em class="requireField">*</em><label>所选试卷：</label></th>
	               
     	                  <td colspan="3">
                          <select id="testParper" multiple style="height:300px;width:250px">
                          </select>                       
	           
	                    	</td>
	                    	
                  		</tr>
                    </tbody>
                </table>
                </form>
        </div>
   </div>
    <div class="buttonArea"> 
			<input type="button"class="formButton_green" value="修改" hidefocus="" onclick="addExamTest('update');"/>
			<input type="button" value="取消" class="formButton_grey"  hidefocus="" onClick="javascript :history.back(-1);"/>
</div>
  </div>

<div class="clear"></div>
</body>
</html>
