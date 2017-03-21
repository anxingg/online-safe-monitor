<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<jsp:include page="../../common/taglibs.jsp" />
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>人员列表</title>
<style type="text/css">
.inputTable th {
	width: 150px;
}
</style>
<jsp:include page="../../common/flatHead.jsp" />
<link rel="stylesheet" type="text/css" href="${ctx}wh/jquery-easyui-1.4.3/themes/default/easyui.css?version=${version}"/>
<link rel="stylesheet" type="text/css" href="${ctx}wh/jquery-easyui-1.4.3/themes/icon.css?version=${version}"/>
<link rel="stylesheet" type="text/css" href="${ctx}wh/css/style.css?version=${version}"/>
<link href="${ctx}wh/plugins/datatable/skins/datatable_default.css?version=${version}" rel="stylesheet" type="text/css"/>

<script type="text/javascript">
	//点击导入
	jQuery(document).ready(function($) {
		$("#importUser").click(function() {
			uploadUser();
			return false;
		});
	});
</script>
<script type="text/javascript" src="${ctx}js/common/placeholder.js"></script>
<script type="text/javascript"  src="${ctx}js/common/treeNode.js?version=${version}"></script>
<script type="text/javascript" src="${ctx}js/logined/user/userTree.js?version=${version}"></script>
<script type="text/javascript" src="${ctx}js/common/ajaxfileupload.js?version=${version}"></script>
<script type="text/javascript" src="${ctx}flat/js/base.js?version=${version}"></script>

<script type="text/javascript" src="${ctx}flat/plugins/datatable/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="${ctx}plugins/datatable/selecedForDatatablePagination.js"></script>
<!-- 人员选择  start-->
<script type="text/javascript" src="${ctx}/js/user/selectuser.js"></script>
<script type="text/javascript" src="${ctx}js/common/hashmap.js"></script>
<script type="text/javascript"  src="${ctx}js/logined/group_ext/userList.js?version=${version}"></script>
</head>
<body>
	<!-- 隐藏域：角色类型 -->
	<input type="hidden" id="whroletype" value="<s:property value="#session.whroletype"/>"/>
	<!-- 隐藏域：当前登录人ID -->
	<input type="hidden" id="currentUserId" value="<s:property value="#session.adminUser.userId"/>"/>
	
<div class="bread-line">
	<label>当前位置：</label><a href="#">首页</a>&gt;<a href="#">系统管理</a>&gt;&nbsp;人员管理
</div>
	<form id="userSelectFrom">
		<input type="hidden" id="type" value="${param.type}" /> 
		<input name="groupId" type="hidden" id="groupId" value="${param.groupId}" /> 
		<input type="hidden" id="loginName" value="${param.loginName}" /> 
		<input type="hidden" id="alterName" value="${param.alterName}" /> 
		<input name="roleId" type="hidden" id="roleId" value="${param.roleId}" /> 
		<input name="loginOrder" type="hidden" id="loginOrder" value="${param.loginOrder}" />
		<input type="hidden" id="userIds" />
		<input type="hidden" id="userNames"/>
		<input id="mobileViewState" type="hidden" value="-1"/>
	</form>
	
 
	<div class="list">
    <div class="searchArea">
      <ul>
          <li>
            <label style="display:none;">性别：</label>
              <select name="sex" id="sex" style="display:none;">
                <option value="">全部</option>
                <option value="1">男</option>
                <option value="0">女</option>
              </select>
           </li>
           <li>
              <label>关键字：</label>
              <input type="text" class="formText  searchkey" maxlength="30" placeholder="姓名/手机号码" id="searchName" />
           </li>
           <li>
           		<input type="button" value="查询" class="searchButton" id="search"/>
           		<s:if test="#session.whroletype == 1">
           		<div class="fButton greenBtn" id="xz"> <span class="add" id="btnAddUser">新增</span></div>
           		<div class="fButton orangeBtn"> <span class="delete" id="btnDeleteUser">删除</span></div>
           		</s:if>
           </li>
         </ul>
    </div>
 <table cellpadding="0" cellspacing="0" id="userTable"  class="pretty dataTable">
      <thead>
        <tr>
          <th class="num"> <input type="checkbox" name="checkbox" id="checkbox" />
          </th>
          <th class="num">序号</th>
          <th style="width:10%;">姓名</th>
          <th style="width:100px;">登录账号</th>
          <th style="width:20%;">手机号码</th>
          <th style="width:15%;">企业名称</th>
          <th style="width:15%;" class="right_bdr0">操作</th>
        </tr>
      </thead>
      <tbody>
      </tbody>
    </table>
  </div>
 
  
	 <script>funPlaceholder(document.getElementById("searchName"));</script>
</body>
</html>