<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>密码设置</title>
<jsp:include page="../../common/flatHead.jsp" />
<link href="${ctx}flat/css/reset.css" rel="stylesheet" type="text/css" />
<link href="${ctx}flat/plugins/form/skins/form_default.css" rel="stylesheet" type="text/css" />
<link href="${ctx}flat/plugins/annex/skins/annex_default.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx}js/logined/systemset/pwdset.js"></script>
<style>
.inputTable th{ width:100px;}
</style>
</head>
<body>
<form id="form1">
<input type="hidden" name="userId" id="userId" value="<s:property value='user.userId'/>"/>
<div class="formPage" style="width:600px;">
  <div class="formbg">
    <div class="big_title" style="display:none;">密码修改</div>
    <div class="content_form">
      <table width="100%" border="0" cellpadding="0" cellspacing="0"  class="inputTable">
        <tr>
          <th><em class="requireField" style="display: none;">*</em><label>用户名：</label></th>
          <td><s:property value="user.loginName"/></td>
        </tr>
        <tr>
        	<th><em class="requireField">*</em><label>原密码：</label></th>
            <td>
            	<input name="input15" id="oldPass" style="width:50%" valid="required" errmsg="sysset.sysset_oldPass_not_null" onkeyup="value=value.replace(/[^\w\.\*\@\#\%\^\&\(\)\?\/]/ig,'')"  onFocus="" onBlur="oldPassBlur();" type="password" class="formText" size="18" maxLength="20"/><span class="tip">输入当前使用的密码</span>
            </td>
       </tr>
        <tr>
        	<th><em class="requireField">*</em><label>新密码：</label></th>
            <td>
            	<input name="input15" id="newPass" style="width:50%" onkeyup="value=value.replace(/[^\w\.\*\@\#\%\^\&\(\)\?\/]/ig,'')" valid="required|limit" errmsg="sysset.sysset_newPass_not_null|sysset.sysset_newPass_limit" min="6"  onFocus="newPassFocus();" type="password" class="formText" size="18" onpaste="return false"  maxLength="20"/><span class="tip">输入6-20位密码</span>	
            </td>
        </tr>
        <tr>
        	<th><em class="requireField">*</em><label>确认新密码：</label></th>
            <td>
            	<input name="input15" id="newPass1" style="width:50%" valid="required|limit" onkeyup="value=value.replace(/[^\w\.\*\@\#\%\^\&\(\)\?\/]/ig,'')" errmsg="sysset.sysset_newPass1_not_null|sysset.sysset_newPass_limit"  min="6" type="password" class="formText" size="18" maxLength="20" onpaste="return false" /><span class="tip">请再次输入新密码</span>	
            </td>
        </tr>
        <tr>
        	<th><label>上次修改时间：</label></th>
            <td><s:property value="time"/></td>
        </tr>
      </table>
    </div>
  </div>
  <div class="buttonArea" style="display:none;">
    <input type="button" class="formButton_green" id="submitButton" value="保存" />
  </div>
</div>
</form>
</body>
</html>
