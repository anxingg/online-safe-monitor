<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<jsp:include page="../head.jsp" />
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>密码重置</title>
	<!-- 验证start -->
	<script type="text/javascript" src="${ctx}js/common/validate_form.js?version=${version}"></script>
	<script type="text/javascript" src="${ctx}common/js/js_lang_cn.js?version=${version}"></script>
	<script type="text/javascript" src="${ctx}js/common/showError.js?version=${version}"></script>
	<!-- 验证end -->
	<script type="text/javascript" src="${ctx}wh/js/company/passWordReset.js?version=${version}"></script>
</head>
<body>
<div class="bread-line">
  <label>当前位置：</label><a href="#">首页</a>&gt;&nbsp;<a href="#">密码管理</a>
</div>
<input type="hidden" id="groupId" value="${param.groupId }"/>
	   		<div class="formPage">
	 <div class="formbg">
	 <div class="big_title">密码重置</div>
        <div class="content_form">
        		<form id="form1">
                <table width="100%" cellspacing="0" cellpadding="0" border="0" class="inputTable">
                    <tbody>
                        
                        <tr>
                            <th>企业编号：</th>
                            <td><span id="companyId"></span></td>
                        </tr>
                        <tr>
                            <th>企业名称：</th>
                            <td><span id="companyName"></span>
                            </td>
                            
                        </tr>
                        <tr>
                            <th>企业账号：</th>
                            <td><span id="loginName"></span>
                            </td>
                            
                        </tr>
                        <tr>
                        	<th><em class="requireField">*</em>新密码：</th>
                            <td>
                            <input type="password" id="password1" class="formText" maxlength="20" onkeyup="value=value.replace(/[^\w\.\*\@\#\%\^\&\(\)\?\/]/ig,'')"
                            valid="required" errmsg="comreg.newLoginPass_not_null"/>
                            </td>
                        </tr>
                        <tr>
                        	<th><em class="requireField">*</em>确认新密码：</th>
                            <td>
                            <input type="password" id="password2" class="formText" maxlength="20" onkeyup="value=value.replace(/[^\w\.\*\@\#\%\^\&\(\)\?\/]/ig,'')"
                            valid="required" errmsg="comreg.newAgainLoginPass_not_null"/>
                            </td>
                        </tr>
                        
                    </tbody>
                </table>
                </form>
        </div>
   </div>
    <div class="buttonArea"> 
			<input type="button" id="submit" class="formButton_green" value="确定" hidefocus=""/>
			<input type="button" value="返回" class="formButton_grey"  hidefocus="" onclick="javascript :history.back(-1);"/>
	</div>
  </div>


</body>
</html>

