<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<jsp:include page="../../common/taglibs.jsp" />
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>登录用户管理</title>
<jsp:include page="../../common/flatHead.jsp" />
<link href="${ctx}flat/css/reset.css" rel="stylesheet" type="text/css" />
<link href="${ctx}flat/css/Reminder.css" rel="stylesheet" type="text/css" />
<link href="${ctx}flat/plugins/datatable/skins/datatable_default.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx}flat/js/base.js"></script>
<script type="text/javascript" src="${ctx}plugins/datatable/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="${ctx}plugins/datatable/selecedForDatatablePagination.js"></script>
<script type="text/javascript" src="${ctx}flat/plugins/dialog/iframeTools.js"></script>
<script type="text/javascript" src="${ctx}js/common/placeholder.js"></script>

<script type="text/javascript" src="${ctx}js/logined/user/loginUserList.js"></script>
</head>
<body>
<div class="list">
		<div class="searchArea">
				<table cellspacing="0" cellpadding="0">
						<tbody>
								<tr>
                                    <td class="right">
                                    <span class="fr"><label style="display: none">姓名：</label><input style="display:none" type="text" id='userName'  class="formText Name" name=""/>
									<label style="display: none">登录名：</label><input style="display: none" id="loginName" type="text"  class="formText Name" name=""/>
									关键字：<input id="key_word" type="text" placeholder="姓名/登录名/手机号码" style="width : 200px" class="formText" maxlength="35" name=""/>&nbsp;&nbsp;
									<input id="searchLoginUser" type="button" class="searchButton" value="查询"/></span>
                                    </td>
                                    <td style="width:97px;"><div class="fButton greenBtn" id="addLoginUser"><span  class="add">新增</span></div></td>
								</tr>
						</tbody>
				</table>
		</div>
		
		<table id="userTable" cellpadding="0" cellspacing="0"  class="pretty dataTable">
			<thead>
				<tr>
					<th class="num">序号</th>
					<th style="width: 110px;">登录名</th>
                    <th style="width: 90px;">姓名</th>
					<th style="width: 50px;">性别</th>
					<th style="width: 100px;">手机号码</th>
					<th style="width: 30%;">单位/部门</th>
					<th style="width: 30%;">职务</th>
                    <th style="width: 40%;">角色</th>
                    <th style="width:140px;">创建日期</th>
                    <th style="width:70px;">状态</th>
                    <th class="right_bdr0" style="width:90px;">操作</th>
				</tr>
			</thead>
		</table>
	</div>
</div>
<script>funPlaceholder(document.getElementById("key_word"));</script>
</body>
</html>