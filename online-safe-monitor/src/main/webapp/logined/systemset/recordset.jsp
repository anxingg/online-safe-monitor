<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>个人信息</title>
<jsp:include page="../../common/flatHead.jsp" />
<link href="${ctx}flat/css/reset.css" rel="stylesheet" type="text/css" />
<link href="${ctx}flat/plugins/form/skins/form_default.css" rel="stylesheet" type="text/css" />
<link href="${ctx}flat/plugins/annex/skins/annex_default.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="${ctx}flat/plugins/upload/uploadify.css"/>
	<script type="text/javascript" src="${ctx}js/logined/systemset/recordset.js"></script>
	<script type="text/javascript" language="javascript" src="${ctx}flat/plugins/upload/jquery.uploadify.min.js" ></script>
	<script type="text/javascript" src="${ctx}js/logined/user/upload.js"></script>
<script type="text/javascript" src="${ctx}js/logined/user/userSign.js"></script>
<script type="text/javascript" src="${ctx}plugins/My97DatePicker/WdatePicker.js"></script>
<style>
.inputTable th{ width:90px;}
</style>
</head>
<body>
<form id="form1">
<input type="hidden" name="userId" id="userId" value="<s:property value='user.userId'/>"/>
	<input type="hidden" name="recordId" id="recordId" value="<s:property value='userRecord.id'/>"/>
	<input type="hidden" name="groupId" id="groupId" value="${user.groupId}"/>
<div class="formPage">
  <div class="formbg">
    <div class="big_title">个人信息</div>
    <div class="content_form">
      <table width="100%" border="0" cellpadding="0" cellspacing="0"  class="inputTable">
      	<tr>
          <th><em class="requireField">*</em><label>姓名：</label></th>
          <td><input style="width: 250px;" type="text" maxlength="16" id="userName" valid="required|isChinese" errmsg="sysset.sysset_userName_not_null|record.must_be_chinese"  class="formText" value="<s:property value="user.userName"/>" size="18"  maxlength="10"/></td>
          <!-- 头像 -->
          	<th rowspan="3">头像：</th>
                <td rowspan="3">
			        <dl class="myphoto">
			   			<dt>
				   			<!-- <a href="javascript:void(0)" class="canclePic" id="deletePhoto" style="display:none"></a> -->
				    		<c:if test="${user.photo == null || user.photo == ''}">
				    			<img src="${ctx}/flat/plugins/form/skins/default/meeting.png"  width="107" height="107"  id="photoImg"/>
			                </c:if>
				    		<c:if test="${user.photo != ''}">
				    			<img src="${ctx}filemanager/prevViewByPath.action?filePath=${requestScope.user.photo}"  width="107" height="107"  id="photoImg"/>
			                </c:if>
						</dt>
			    		<dd>
			        		<input id="file_upload" name="file_upload" type="file" multiple="true" />
			    			<div id="img_queue"></div>
			    			<input type="hidden" id="photo" value="${user.photo}" />
			        	</dd>
			        	<dd><p>支持 .jpg .png .gif格式图片，200K以内</p></dd>
					</dl>
		        </td>
		        <!-- 头像  end -->
        </tr>
        	<th><label>性别：</label></th>
            <td>
	            <s:if test="user.sex==0"><label class="radio"><input type="radio" name="sex" value="1"/>男</label><label class="radio"><input type="radio" name="sex" value="0"  checked="checked"/>女</label></s:if>
		       <s:if test="user.sex==1"><label class="radio"><input type="radio" name="sex" value="1" checked="checked"/>男</label><label class="radio"><input type="radio" name="sex" value="0"/>女</label></s:if>
          	</td>
      	<tr>
        	<th><label>单位/部门：</label></th>
            <%-- <td><input name="" class="formText" readonly="readonly" type="text" value="${requestScope.groupName}" /></td> --%>
            <td>${requestScope.groupName}</td>
        </tr>
        <tr>
        	<th><em class="requireField">*</em><label>手机号码：</label></th>
            <td>
            	<%-- <s:property value="user.phone"/> --%>
            	<input name="input15" type="text"  maxlength="11" id="phone" valid="required|isMobile" errmsg="sysset.sysset_mobile_not_null|record.must_be_right_mobile_no"  value="<s:property value="user.phone"/>" class="formText" size="18" />
            </td>
        </tr>
        <tr>
        	<th><label>联系电话：</label></th>
          <td><input name="" class="formText" type="text" value="${user.phone2}" id="phone2"  size="18" style="width: 250px;"/></td>
          <th><label>别名：</label></th>
          <td><input name="" class="formText" type="text" value="${user.alterName}" id="alterName" /></td>
        </tr>
        <tr>
          <th><label>生日：</label></th>
          <td><input id="birthDay" name="birthDay" readonly="readonly" size="18" value='<s:property value="birthDay"/>' class="Wdate formText"  type="text" onclick="WdatePicker({skin:'default',dateFmt:'yyyy-MM-dd',maxDate:'%y-%M-%d'})"/></td>
          <th><label>电子邮件：</label></th>
          <td><input name="input15" type="text"  valid="isEmail"  maxlength="50" errmsg="record.must_be_right_email" id="email" value="<s:property value="user.email"/>" class="formText" size="18" style="width: 250px;" /></td>
        </tr>
        <tr>
			<th>角色：</th>
			<td><s:property value="roleMain"/></td>
        	
          <%-- <th><label>个性签名：</label></th>
          <td><select id="userSign" class="sl_xz" style="float: left">
                        <option value="0" <c:if test="${user.signType == 0}">selected</c:if>>不启用</option>
                        <option value="1" <c:if test="${user.signType == 1}">selected</c:if>>默认</option>
                        <option value="2" <c:if test="${user.signType == 2}">selected</c:if>>图片签名</option>
                    </select>
                    	<table id="signContent" style="display:none;margin-top: -10px"  class="up_tb fl">
                        <tr>
                            <td align="left"><input type="file" id="userSign_upload" style="float:left"/></td>
                            <td align="left">
                                <img id="imgContent" border="0" width="120px" height="40px" src="${ctx}flat/images/qm.jpg"/>
                        		<input type="hidden" id="imgSignUrl" value="${user.signUrl}"/>
                            </td>
                        </tr>
                    </table>
          </td> --%>

        </tr>
      </table>
    </div>
    <div class="big_title" style="display:none;">控件设置</div>
    <div class="content_form" style="display:none;">
    	<table width="100%" border="0" cellpadding="0" cellspacing="0"  class="inputTable">
          <tr>
            <th>印章控件：</th>
            <td><select name="sign" id="sign"><option value="0" <c:if test="${user.sinWidget == 0 }">selected</c:if>>不启用</option><option value="1" <c:if test="${user.sinWidget == 1}">selected</c:if>>启用</option></select>
            </td>
            <th>OFFICE控件：</th>
            <td><select name="office" id="office"><option value="0" <c:if test="${user.officeWidget == 0 }">selected</c:if>>不启用</option><option value="1" <c:if test="${user.officeWidget == 1 }">selected</c:if>>启用</option></select></td>
            </tr>
          <tr>
            <th>套打控件：</th>
            	       		<td colspan="3"><select name="print" id="print"><option value="0" <c:if test="${user.taoDa == 0 }">selected</c:if>>不启用</option><option value="1" <c:if test="${user.taoDa == 1 }">selected</c:if>>启用</option></select></td>
          </tr>
        </table>
    </div>
  </div>
  <div class="buttonArea">
    <input type="button" class="formButton_green" value="保存" id="submitButton"/>
  </div>
</div>
</form>
</body>
</html>
