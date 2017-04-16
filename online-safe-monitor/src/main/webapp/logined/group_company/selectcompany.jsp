<%--
   选择人员
  User: 黄普友
  Date: 2017-3-16
  Time: 上午9:04
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>选择单位</title>
<jsp:include page="../../common/flatHead.jsp" />
<link href="${ctx}flat/css/reset.css" rel="stylesheet" type="text/css" />
<link href="${ctx}flat/plugins/selectMember/skins/selectMember_default.css" rel="stylesheet" type="text/css"/>
<link rel="stylesheet" href="${ctx}flat/plugins/peopleTree/skins/tree_default.css" type="text/css" />
<script type="text/javascript" src="${ctx}flat/js/placeholder.js"></script>
<script type="text/javascript" src="${ctx}flat/js/smallTabSelectTree.js"></script>
<script type="text/javascript" src="${ctx}flat/plugins/peopleTree/skins/jquery.ztree.all-3.2.min.js"></script>
<script type="text/javascript" src="${ctx}flat/plugins/Accormenus/skins/jquery.Accormenus.js"></script>
<script type="text/javascript" src="${ctx}flat/plugins/tree/skins/jquery.ztree.all-3.2.min.js"></script>
<script type="text/javascript" src="${ctx}js/common/inputdefault.js"></script>
<script type="text/javascript" src="${ctx}js/common/hashmap.js"></script>
<script type="text/javascript" src="${ctx}js/common/treeNode.js"></script>
</head>
<body  class="bg_white" >
	<input type="hidden" id="defaultSelectId" value='${paramValues.defaultSelectId[0]}' />
	<input type="hidden" id="groupId" value='${paramValues.groupId[0]}'/>
	<div class="selectMember">
	   	<div class="member">
	       <div class="tab">
	         <ul>
	           <li class="current" id="liSelectGroup" ><a  href="javascript:void(0);" id="btnSelectGroup" >企业列表</a></li>
	         </ul>  
	       </div>
	       <div class="tabContent"  >
	          <ul id="groupUserTree" class="ztree"></ul>
	       </div>
	        
		</div>
	</div>
	<div class="clear"  style="display: none;"></div>
	<script type="text/javascript" src="${ctx}js/logined/group_company/selectcompany.js?version=${version}"></script>
	<script>funPlaceholder(document.getElementById("search"));</script>
</body>
</html>