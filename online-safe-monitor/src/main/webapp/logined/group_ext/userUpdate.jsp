<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>人员添加</title>
    <jsp:include page="../../common/flatHead.jsp"/>
    <link href="${ctx }flat/css/reset.css" rel="stylesheet" type="text/css" />
	<link href="${ctx }flat/plugins/form/skins/form_default.css" rel="stylesheet" type="text/css" />
	<link href="${ctx }flat/plugins/annex/skins/annex_default.css" rel="stylesheet" type="text/css" />
	<link rel="stylesheet" href="${ctx}flat/plugins/tree/skins/tree_default.css" type="text/css"/>
   <script type="text/javascript" src="${ctx}plugins/tree/ztree/jquery.ztree.all-3.5.min.js"></script>
   <link rel="stylesheet" type="text/css" href="${ctx}plugins/upload/uploadify.css"/>
    <script type="text/javascript" src="${ctx}js/logined/user/userSign.js"></script>
    <script type="text/javascript" src="${ctx}/js/logined/group_ext/userUpdate.js"></script>
    <script type="text/javascript" src="${ctx}js/common/treeNode.js"></script>
    <script type="text/javascript" src="${ctx}js/logined/user/userTree.js"></script>

    <script type="text/javascript" src="${ctx}/js/logined/user/selectUser.js"></script>
    <script type="text/javascript" src="${ctx}/js/user/selectuser.js"></script>
    <script type="text/javascript" src="${ctx}js/common/hashmap.js"></script>
    <script type="text/javascript" src="${ctx}plugins/My97DatePicker/WdatePicker.js"></script>
    <script type="text/javascript" src="${ctx}js/logined/group_ext/selectGroup.js"></script>
    <script type="text/javascript" language="javascript" src="${ctx}plugins/upload/jquery.uploadify.min.js" ></script>
    <script type="text/javascript" src="${ctx}js/logined/user/upload.js"></script>
</head>
<body>
 <form action="#" id="userForm">
<input type="hidden" id="userId" value="${requestScope.user.userId}"/>
<input type="hidden" id="roleId" value="${requestScope.roleIds}"/>
<input type="hidden" id="assistId" value="${requestScope.assistIds}"/>
<input type="hidden" id="groupId" value="${requestScope.groupId}"/>
<input type="hidden" id="defaultgroupId" value="${requestScope.groupId}"/>
<div class="formPage" style="width:550px;">
  <div class="formbg">
    <div class="content_form">
      <table width="100%" border="0" cellpadding="0" cellspacing="0"  class="inputTable">
        <tr>
          <th><em class="requireField">*</em><label>姓名：</label></th>
          <td><input type="text" class="formText" maxlength="6" valid="required"
                           errmsg="user.user_userName_not_null" id="userName" value="${requestScope.user.userName}"/></td>
        </tr>
 <tr style="display: none;">
                <th><em class="requireField">*</em><label>主&nbsp;&nbsp;角&nbsp;&nbsp;色</label></th>
                <td><input type="text" class="readOnlyText blur-not-disabled" size="30" id="role" maxlength="20"
                          value="${requestScope.roleNames}"/>
                        <c:if test="${requestScope.user.isDefault!=0}">
                        <a  class="icon_add" href="#" id="roleSelect">添加</a><a
                        class="icon_clear" href="#" id="roleRemove">清空</a><a href="javascript:void(0)" id="showAssist">指定辅助角色</a>
                        </c:if>
                </td>
            </tr>
        <tr style="display:none;">
        	<th><em class="requireField">*</em><label>单位/部门：</label></th>
            <td><input id="groupSel" type="text" readonly="readonly" class="formText iconTree" />
                        <span class="selectdot" id="groupSel_div"></span>
							<div id="menuContent" style="position: absolute;">
								<ul id="groupTree" class="ztree"
									style="position: absolute; margin-top: 0; width: 375px;height:150px;overflow:auto; 
background: #ffffff;  border: 1px solid #8a9ba5;z-index:1000"></ul>
							</div>
            </td>
        </tr>
        <tr>
        	<th><em class="requireField">*</em><label>手机号码：</label></th>
            <td><input name="input" type="text" class="formText" maxlength="11" id="phone"
                           valid="required|isMobile" errmsg="user.user_phone_not_null|user.user_phone_format_error"
                           value="${requestScope.user.phone}"
                           onkeyup="this.value=this.value.replace(/[^0-9]/g,'')"/></td>
        </tr>
        <tr>
        	<th><em class="requireField">*</em><label>登录账号：</label></th>
            <td><input value="${requestScope.user.loginName}" valid="required" errmsg="user.loginName_not_null" name="input" type="text" class="formText"  maxlength="16" id="loginName"/>
            </td>
        </tr>
      		</table>
    </div>
    
  </div>
  
</div>
</form>
</body>
</html>