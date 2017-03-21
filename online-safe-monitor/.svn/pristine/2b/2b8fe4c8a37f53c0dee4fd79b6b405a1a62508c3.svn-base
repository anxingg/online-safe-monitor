<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<jsp:include page="../../common/taglibs.jsp" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>人员添加</title>
<jsp:include page="../../common/flatHead.jsp" />
<link href="${ctx}flat//css/reset.css" rel="stylesheet" type="text/css" />
<link href="${ctx}flat//plugins/form/skins/form_default.css" rel="stylesheet" type="text/css" />
<style type="text/css">
.inputTable th{width:85px;}
.pla_label label{ margin-top:-2px !important;}
</style>
</style>
<script type="text/javascript" src="${ctx}js/user/selectuser.js"></script>
<script type="text/javascript" src="${ctx}js/logined/user/loginUserAddOrUpdate.js"></script>
<script type="text/javascript" src="${ctx}js/common/placeholder.js"></script>
</head>
<body class="bg_white">
<input id="type" type="hidden" value='${type}'/>
<div class="elasticFrame formPage">
	<form id="userInfoFomr">
  <h2 class="small_title" style="padding-top:0">登录信息</h2>
  <table width="100%" cellspacing="0" cellpadding="0" border="0" class="inputTable">
    <tbody>
      <tr>
        <th><em class="requireField">*</em><label>登录名：</label></th>
        <td><input id="loginName" name="" type="text" class="formText" size="30"
						valid="required|isLoginName" errmsg="user.user_loginName_not_null|user.user_loginName_format_error"
						value="${user.loginName}"  maxlength="16"/>
		</td>
      </tr>
      <s:if test="user.userId!=null">
         <tr>
	        <th><label>密码：</label></th>
	        <td><span class="pla_label"><input id="password"   name="" type="password" class="formText" size="30" 
						   valid="limit"     errmsg="user.password_not_null|user.password_limit" onkeyup="value=value.replace(/[^\w\.\*\@\#\%\^\&\(\)\?\/]/ig,'')" placeholder="为空不修改密码" min="6" maxlength="16"/></span>
			</td>
	      </tr>
	      <tr>
	        <th><label>确认密码：</label></th>
	        <td><input id="confirmPass"  name="" type="password" class="formText" size="30" 
						     valid="limit" errmsg="user.confirm_password_not_null|user.confirm_password_limit" onkeyup="value=value.replace(/[^\w\.\*\@\#\%\^\&\(\)\?\/]/ig,'')" min="6" maxlength="16"/>
			</td>
	      </tr>
      </s:if>
      <s:else>
          <tr>
	        <th><em class="requireField">*</em><label>密码：</label></th>
	        <td><input id="password"   name="" type="password" class="formText" size="30" 
						     valid="required|limit"  errmsg="user.password_not_null|user.password_limit" onkeyup="value=value.replace(/[^\w\.\*\@\#\%\^\&\(\)\?\/]/ig,'')" min="6" maxlength="16"/>
			</td>
	      </tr>
	      <tr>
	        <th><em class="requireField">*</em><label>确认密码：</label></th>
	        <td><input id="confirmPass"  name="" type="password" class="formText" size="30" 
						     valid="required|limit" errmsg="user.confirm_password_not_null|user.confirm_password_limit" onkeyup="value=value.replace(/[^\w\.\*\@\#\%\^\&\(\)\?\/]/ig,'')" min="6" maxlength="16"/>
			</td>
	      </tr>
      </s:else>
      
      <tr>
        <th><label>选择角色：</label></th>
        <td><input id="roleNames"  name="" type="text" class="formText" style="width:78%;"
					     value="${roleNames}"  readonly="readonly"/> 
				<input id="roleIds" name="" type="hidden" value="${roleIds}" /> 
				<input id="roleCodes" name="" type="hidden" value="${roleCodes}" /> 
				<span class="addMember"><a id="selectRole" class="icon_add" href="javascript:void(0)">
					<s:if test="type=='update'">修改</s:if>
					<s:else>添加</s:else>
				</a>
				</span>
		</td>
      </tr>
      	<tr id="seatTypeTr" style="display: none;">
					<th><label>坐席类别：</label>
					</th>
					<td><label><input id="firstSeatType" type="radio" value="1" name="seatType" <s:if test="(#request.msi==null)||(#request.msi.msitype!=null && #request.msi.msitype==0)">
						checked="checked" </s:if> />&nbsp;内线</label>&nbsp;&nbsp;
					<label><input type="radio" value="2" name="seatType" <s:if test="(#request.msi!=null)&&(#request.msi.msitype!=null && #request.msi.msitype==1 && #request.msi.msiWorkType!=null && #request.msi.msiWorkType==1)">
						checked="checked" </s:if> />&nbsp;外线在线</label>
						&nbsp;&nbsp;
					<label><input type="radio" value="3" name="seatType" <s:if test="(#request.msi!=null)&&(#request.msi.msitype!=null && #request.msi.msitype==1 && #request.msi.msiWorkType!=null && #request.msi.msiWorkType==6)">
						checked="checked" </s:if> />&nbsp;外线离线</label>
					
				</tr>
      <tr>
        <th><label>登录状态：</label></th>
        <td><label class="radio"><input name="state" type="radio" 
					      <s:if test="(#request.user==null)||(#request.user.userState!=null && #request.user.userState==0)">
						checked="checked" </s:if> value="0"/>启用</label>
						<label class="radio"><input name="state" type="radio" 
						    <s:if test="(#request.user.userState==null||#request.user.userState==1)">checked="checked"</s:if>
						  value="1" />不启用</label></td>
      </tr>
    </tbody>
  </table>
  <h2 class="small_title">用户信息</h2>
  <table width="100%" cellspacing="0" cellpadding="0" border="0" class="inputTable">
    <tbody>
      <tr>
        <th><em class="requireField">*</em><label>选择人员：</label></th>
        <td><input id="userName" name="userName" type="text" value="${user.userName}"  readonly="readonly"
						class="formText" style="width:78%;" /> <input id="userId" type="hidden" value="${user.userId}" 
						class="formText" size="30" />  
						<input id="oldUserId" type="hidden" value="${user.userId}" 
						class="formText" size="30" />  
						<span class="addMember"><a id="userSelect" class="icon_add" href="javascript:void(0)">
							<s:if test="type=='update'">修改</s:if>
							<s:else>添加</s:else>
						</a></span>
		</td>
      </tr>
      <tr style="display: none;">
					<th><label>性&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;别</label>
					</th>
					<td><input id="sex" name="" type="text" readonly="readonly"
					      <s:if test="(#request.user.sex!=null&&#request.user.sex==0)">value="女"</s:if>
					      <s:if test="(#request.user.sex!=null&&#request.user.sex==1)">value="男"</s:if>
						class="formText" size="30" />
					</td>
				</tr>
      <tr>
        <th><label>单位/部门：</label></th>
        <td><input id="groupName" name="" type="text" readonly="readonly" value="${groupName}" 
						class="formText" size="30" />
		</td>
      </tr>
      <tr>
        <th><label>职务：</label></th>
        <td><input id="job" name="" type="text" readonly="readonly" value="${user.job}" 
						class="formText" size="30" />
		</td>
      </tr>
      <tr>
        <th><label>手机：</label></th>
        <td><input id="phone" name="" type="text" readonly="readonly" value="${user.phone}" 
						class="formText" size="30" />
		</td>
      </tr>
    </tbody>
  </table>
  </form>
</div>
<script>funPlaceholder(document.getElementById("password"));</script>
</body>
</html>