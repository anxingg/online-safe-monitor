<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<jsp:include page="../../common/taglibs.jsp" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>角色权限管理</title>
<%@ include file="../../../common/taglibs.jsp"%>
<link href="${ctx}css/author_main.css" rel="stylesheet" type="text/css" />
<jsp:include page="../../common/flatHead.jsp" />
<script type="text/javascript">
	var basePath = "${ctx}";
</script>
<link href="${ctx}flat/css/reset.css" rel="stylesheet" type="text/css" />
<link href="${ctx}flat/css/Reminder.css" rel="stylesheet" type="text/css" />
<link href="${ctx}flat/plugins/form/skins/form_default.css" rel="stylesheet" type="text/css" />
<link href="${ctx}flat/plugins/datatable/skins/datatable_default.css" rel="stylesheet" type="text/css" />
<!-- 人员选择  start-->
<script type="text/javascript" src="${ctx}flat/js/base.js"></script>
<script type="text/javascript" src="${ctx}plugins/datatable/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="${ctx}flat/plugins/datatable/selecedForDatatablePagination.js"></script>
<script type="text/javascript" src="${ctx}js/logined/authority/selectUser.js"></script>
<script type="text/javascript" src="${ctx}js/user/selectuser.js"></script>
<script type="text/javascript" src="${ctx}js/common/hashmap.js"></script>
<script type="text/javascript" src="${ctx}js/common/treeNode.js"></script>


<script type="text/javascript" language="javascript" src="${ctx}js/logined/authority/roleList.js"></script>
</head>
<body>
<input name="roleId" id="roleId" type="hidden"/>
<div class="list">
	<div class="searchArea">
				<table cellspacing="0" cellpadding="0">
						<tbody>
								<tr>
                                	<td class="right">&nbsp;</td>
									<td style="width:194px;"><div class="fButton greenBtn" id="addRole"><span class="add" >新增</span></div>
                                    <div class="fButton orangeBtn" id="deleteRole">
                                      <span class="delete" >删除</span>
                                    </div>
                                    </td>
								</tr>
						</tbody>
				</table>
		</div>
		<table cellpadding="0" cellspacing="0" class="pretty dataTable"	id="myTable">
		</table>
	</div>
</body>
</html>