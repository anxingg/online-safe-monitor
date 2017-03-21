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
    <script type="text/javascript" src="${ctx}js/user/selectGroup.js"></script>
    <script type="text/javascript" language="javascript" src="${ctx}plugins/upload/jquery.uploadify.min.js" ></script>
    <script type="text/javascript" src="${ctx}js/logined/user/upload.js"></script>
    
    
    <script type="text/javascript" src="${ctx}/js/logined/user/userAdd.js"></script>
</head>
<body>
<form action="#" id="userForm">
<input type="hidden" id="roleId"/>
<input type="hidden" id="assistId"/>
<input type="hidden" id="groupId" value="${param.groupId}"/>
<div class="formPage">
  <div class="formbg">
    <div class="big_title">新增人员</div>
     
    <div class="content_form">
      <table width="100%" border="0" cellpadding="0" cellspacing="0"  class="inputTable">
        <tr>
          <th><em class="requireField">*</em><label>姓名：</label></th>
          <td><input type="text" class="formText" maxlength="6" valid="required"
                           errmsg="user.user_userName_not_null" id="userName"/></td>
          <th rowspan="3">头像：</th>
                            <td rowspan="3">
                                 <dl class="myphoto">
                                 <dt><img src="${ctx }flat/plugins/form/skins/default/meeting.png" width="107" height="107" id="photoImg"/></dt>
                                       <dd><h3><input id="file_upload" name="file_upload" type="file" multiple="true" /></h3></dd>
                                       <dd><p>支持 .jpg .png格式图片，200KB以内</p></dd>
                                       <input type="hidden" id="photo"/>
                                 </dl>
          </td>
        </tr>
<tr style="display: none;">
                <th><em class="requireField">*</em><label>主&nbsp;&nbsp;角&nbsp;&nbsp;色</label></th>
                <td><input type="text" class="readOnlyText blur-not-disabled" size="30" id="role" maxlength="20"
                         /><a
                        class="icon_add" href="#" id="roleSelect">添加</a><a
                        class="icon_clear" href="#" id="roleRemove">清空</a><a href="javascript:void(0)" id="showAssist">指定辅助角色</a>
                </td>
            </tr>
            <tr style="display: none;" id="assistContent">
                <th><label>辅助角色</label></th>
                <td><input type="text" class="readOnlyText" size="30" id="assist" disabled="disabled"/>&nbsp;<a
                        class="icon_add" href="#" id="assistSelect">添加</a><a
                        class="icon_clear" href="#" id="assistRemove">清空</a>
                </td>
            </tr>
        <tr>
        	<th><em class="requireField">*</em><label>单位/部门：</label></th>
            <td> <input id="groupSel" type="text" readonly="readonly" class="formText iconTree" />
                        <span class="selectdot" id="groupSel_div"></span>
							<div id="menuContent" style="position: absolute;z-index: 99;">
								<ul id="groupTree" class="ztree"
									style="position: absolute; margin-top: 0; width: 375px;height:150px;overflow:auto; background: #ffffff;  border: 1px solid #8a9ba5"></ul>
							</div></td>
        </tr>
        <tr>
        	<th><em class="requireField">*</em><label>手机号码：</label></th>
            <td><input name="input" type="text" class="formText" maxlength="11" id="phone"
                           valid="required|isMobile" errmsg="user.user_phone_not_null|user.user_phone_format_error"
                           onkeyup="this.value=this.value.replace(/[^0-9]/g,'')"/></td>
        </tr>
        <tr>
        	<th><label>性别：</label></th>
            <td><label class="radio"><input type="radio" value="1" name="sex" checked="checked" id="sex_1" />男</label>
          	  <label class="radio"><input type="radio" value="0" name="sex"   id="sex_0"/>女</label>
          </td>
          <th><label>别名：</label></th>
          <td><input name="input" type="text" class="formText"  maxlength="16" id="alterName"/></td>
        </tr>
        <tr>
          <th><label>生日：</label></th>
          <td><input name="input" type="text" class="formText Wdate" style="width:196px"
                           onfocus="WdatePicker({skin:'default',dateFmt:'yyyy-MM-dd',maxDate:'%y-%M-%d'})" id="birthDay"/></td>
          <th><label>电子邮件：</label></th>
          <td><input name="input" type="text" class="noSqlLimit formText" size="30" maxlength="50" valid="isEmail"
                           errmsg="user.user_email_format_error" id="email"/></td>
        </tr>
        <tr>
          <th><label>办公电话：</label></th>
          <td><input name="input" type="text" class="formText"  maxlength="12" valid="isPhone"
                           errmsg="user.user_phone2_format_error" id="phone2"
                           onkeyup="this.value=this.value.replace(/[^0-9]/g,'')"/></td>
          <th><label>排序号：</label></th>
          <td><input name="input" type="text" class="formText"  maxlength="8"
                           onkeyup="this.value=this.value.replace(/[^0-9]/g,'')" id="userOrder"/></td>
        </tr>
        <tr>
          <th><label>职务：</label></th>
          <td><input name="input" type="text" class="formText" size="30" maxlength="10" id="job"/></td>
          <th style="display: none;"><label>个性签名：</label></th>
          <td colspan="1" style="display: none;"><select id="userSign" style="float: left">
                        <option value="0">不启用</option>
                        <option value="1">默认</option>
                        <option value="2">图片签名</option>
                    </select>
         <table id="signContent" style="display:none;margin-top: -10px"  class="up_tb fl">
                        <tr>
                            <td align="left"><input type="file" id="userSign_upload" style="float:left"/></td>
                            <td align="left">
                                <img id="imgContent" border="0" width="120px" height="40px"   src="${ctx}images/qm.jpg"/>
                                <input type="hidden" id="imgSignUrl" value="${user.signUrl}"/>
                            </td>
                        </tr>
                    </table>
                    </td>
        </tr>
      </table>
    </div>
    <div class="big_title" style="display: none;">控件设置</div>
    <div class="content_form" style="display: none;">
    	<table width="100%" border="0" cellpadding="0" cellspacing="0"  class="inputTable">
          <tr>
            <th>印章控件：</th>
            <td><select name="sign" id="sign"><option value="0">不启用</option><option value="1">启用</option></select>
            </td>
            <th>OFFICE控件：</th>
            <td><select name="office" id="office"><option value="0">不启用</option><option value="1">启用</option></select>
            </td>
            </tr>
          <tr>
            <th>套打控件：</th>
            <td colspan="3"><select name="print" id="print"><option value="0">不启用</option><option value="1">启用</option></select>
            </td>
          </tr>
        </table>
    </div>
    
  </div>
  
  <div class="buttonArea">
    <input type="button" class="formButton_green" value="确定"  id="userInfoUpdate"/>
    <input type="button" class="formButton_grey" value="返回" id="back" />
  </div>
</div>
</form>
</body>
</html>