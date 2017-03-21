<%@ page language="java" import="java.util.*"  contentType="text/html; charset=UTF-8" pageEncoding="utf-8"  %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<jsp:include page="../../../common/taglibs.jsp"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<jsp:include page="../head.jsp" />
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>企业注册</title>
    <!-- 验证start -->
	<script type="text/javascript" src="${ctx}js/common/validate_form.js?version=${version}"></script>
	<script type="text/javascript" src="${ctx}common/js/js_lang_cn.js?version=${version}"></script>
	<script type="text/javascript" src="${ctx}js/common/showError.js?version=${version}"></script>
	<!-- 验证end -->
    <script type="text/javascript" src="${ctx}wh/js/company/companyReg.js?version=${version}"></script>
    
</head>
<body>
	<input type="hidden" id="whroletype" value='<s:property value="#session.whroletype"/>'/>
	<input type="hidden" id="group_id" value='<s:property value="#session.adminUser.groupId"/>'/>
	<div class="bread-line">
		<label>当前位置：</label><a href="#">首页</a>&gt;<a href="#">企业管理</a>&gt;&nbsp;企业注册
	</div>
	 <div class="formPage">
	 <div class="formbg">
        <div class="big_title">注册<em>（企业）</em></div>
        <div class="content_form">
                <form id="form1">
                <table width="100%" cellspacing="0" cellpadding="0" border="0" class="inputTable">
                    <tbody>
                        
                        <tr>
                            <th><em class="requireField">*</em>企业名称：</th>
                            <td><input type="text" class="formText" id="companyName" maxlength="32"
                            	valid="required" errmsg="comreg.name_not_null"/></td>
                            
                        </tr>
                        <tr>
                        	<th>行业类型：</th>
                            <td colspan="3">
		                   		<label class="radio"><input name="hylx" type="checkbox" id="1" value="氯碱工业"/>氯碱工业</label>
		                   		<label class="radio"><input name="hylx" type="checkbox" id="2" value="电石工业"/>电石工业</label>
		                   		<label class="radio"><input name="hylx" type="checkbox" id="3" value="冶金"/>冶金</label>
		                   		<label class="radio"><input name="hylx" type="checkbox" id="4" value="精细化工"/>精细化工</label>
		                   		<label class="radio"><input name="hylx" type="checkbox" id="5" value="煤化工"/>煤化工</label>
		                   		<label class="radio"><input name="hylx" type="checkbox" id="6" value="其他"/>其他</label>
		                   	</td>
                        </tr>
                        <tr>
                            <th><em class="requireField">*</em>企业账号：</th>
                            <td><input type="text" class="formText" id="loginName" maxlength="32"
                            	valid="required" errmsg="comreg.loginName_not_null"/></td>
                            <th><em class="requireField">*</em>登录密码：</th>
                            <td><input type="password" class="formText" id="loginPass" maxlength="20" onkeyup="value=value.replace(/[^\w\.\*\@\#\%\^\&\(\)\?\/]/ig,'')"
                            	valid="required" errmsg="comreg.loginPass_not_null"/></td>
                        </tr>
                        <tr>
                            <th><em class="requireField">*</em>联系人：</th>
                            <td><input type="text" class="formText" id="userName" maxlength="32"
                            	valid="required" errmsg="comreg.userName_not_null"/></td>
                            <th><em class="requireField">*</em>联系电话：</th>
                            <td><input type="text" class="formText" id="phone" 
		          	 			onkeyup="this.value=this.value.replace(/[^0-9\-]/g,'')" maxlength="12"
		          	 			valid="required|isPhoneSimple" errmsg="comreg.phone_not_null|comreg.phone_not_mobile"/></td>
                        </tr>
                        <tr>
                    		<th><label>备注：</label></th>
	                    	<td colspan="3">
	                        <textarea class="formTextarea" rows="5" id="memo" maxlength="256" 
	                        valid="textareaLength" errmsg="safety.text_max_length" maxNumber="256"></textarea>
	                    	<span style="float:right">0-256字</span>
	                    	</td>
                  		</tr>
                    </tbody>
                </table>
                </form>
        </div>
   </div>
    <div class="buttonArea"> 
			<input type="button"class="formButton_green" value="确定" hidefocus="" onclick="addCompany();"/>
</div>
  </div>

<div class="clear"></div>
</body>
</html>
