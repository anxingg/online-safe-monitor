<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>人员添加</title>
    <jsp:include page="../../common/flatHead.jsp"/>
    <link href="${ctx }flat/css/reset.css" rel="stylesheet" type="text/css" />
	<link href="${ctx }flat/plugins/form/skins/form_default.css" rel="stylesheet" type="text/css" />
	<link href="${ctx }flat/plugins/annex/skins/annex_default.css" rel="stylesheet" type="text/css" />
	<link href="${ctx }flat/plugins/tree/skins/tree_default.css" rel="stylesheet" type="text/css" />
   <script type="text/javascript" src="${ctx}plugins/tree/ztree/jquery.ztree.all-3.5.min.js"></script>
   <link rel="stylesheet" type="text/css" href="${ctx}plugins/upload/uploadify.css"/>
    <script type="text/javascript" src="${ctx}js/logined/user/userSign.js"></script>
    <script type="text/javascript" src="${ctx}js/common/treeNode.js"></script>
    <script type="text/javascript" src="${ctx}js/logined/user/userTree.js"></script>

    <script type="text/javascript" src="${ctx}/js/logined/user/selectUser.js"></script>
    <script type="text/javascript" src="${ctx}/js/user/selectuser.js"></script>
    <script type="text/javascript" src="${ctx}js/common/hashmap.js"></script>
    <script type="text/javascript" src="${ctx}plugins/My97DatePicker/WdatePicker.js"></script>
    <script type="text/javascript" src="${ctx}js/logined/group_ext/selectGroup.js"></script>
    <script type="text/javascript" language="javascript" src="${ctx}plugins/upload/jquery.uploadify.min.js" ></script>
    <script type="text/javascript" src="${ctx}js/logined/user/upload.js"></script>
    
    
    <script type="text/javascript" src="${ctx}/js/logined/group_ext/userAdd.js"></script>
</head>
<body>
<form action="#" id="userForm">
<input type="hidden" id="roleId"/>
<input type="hidden" id="assistId"/>
<input type="hidden" id="groupId" value="${param.groupId}"/>
<div class="formPage" style="width:550px;">
  <div class="formbg">
    <div class="content_form">
      <table width="100%" border="0" cellpadding="0" cellspacing="0"  class="inputTable">
        <tr>
          <th><em class="requireField">*</em><label>姓名：</label></th>
          <td><input type="text" class="formText" maxlength="6" valid="required"
                           errmsg="user.user_userName_not_null" id="userName"/></td>
        </tr>
		<tr style="display: none;">
                <th><em class="requireField">*</em><label>主&nbsp;&nbsp;角&nbsp;&nbsp;色</label></th>
                <td><input type="text" class="readOnlyText blur-not-disabled" size="30" id="role" maxlength="20"
                         /><a
                        class="icon_add" href="#" id="roleSelect">添加</a><a
                        class="icon_clear" href="#" id="roleRemove">清空</a><a href="javascript:void(0)" id="showAssist">指定辅助角色</a>
                </td>
            </tr>
        <tr style="display:none;">
        	<th><em class="requireField">*</em><label>单位/部门：</label></th>
            <td> <input id="groupSel" type="text" readonly="readonly" class="formText iconTree" />
                        <span class="selectdot" id="groupSel_div"></span>
							<div id="menuContent" style="position: absolute;z-index: 99;">
								<ul id="groupTree" class="ztree"
									style="position: absolute; margin-top: 0; width: 375px;height:120px;overflow:auto; background: #ffffff;  border: 1px solid #8a9ba5"></ul>
							</div></td>
        </tr>
        <tr>
        	<th><em class="requireField">*</em><label>手机号码：</label></th>
            <td><input name="input" type="text" class="formText" maxlength="11" id="phone"
                           valid="required|isMobile" errmsg="user.user_phone_not_null|user.user_phone_format_error"
                           onkeyup="this.value=this.value.replace(/[^0-9]/g,'')"/></td>
        </tr>
        <tr>
        	<th><em class="requireField">*</em><label>登录账号：</label></th>
            <td><input valid="required" errmsg="user.loginName_not_null" name="input" type="text" class="formText"  maxlength="16" id="loginName"/>
          </td>
        </tr>
        <tr>
          <th><em class="requireField">*</em><label>登录密码：</label></th>
          <td><input name="input" type="password" class="formText" valid="required|limit" min="6" errmsg="user.password_not_null|user.password_limit" maxlength="16" id="password" onkeyup="value=value.replace(/[^\w\.\*\@\#\%\^\&\(\)\?\/]/ig,'')"/></td>
        </tr>
        <tr>
          <th><em class="requireField">*</em><label>角色：</label></th>
          <td><select id="myroleId">
          	<option value="manageadmin">管理员</option>
          	<option value="managecommon">普通用户</option>
          </select></td>
        </tr>
        
      </table>
    </div>
  </div>
</div>
</form>
</body>
</html>