<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../../common/taglibs.jsp" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>人员管理</title>
<style type="text/css">
 .inputTable th{width:150px;}
</style>
<jsp:include page="../../common/taglibs.jsp" />
<jsp:include page="../../common/flatHead.jsp" />
<script type="text/javascript">
	var basePath = "${ctx}";
</script>
<link href="${ctx}flat/css/reset.css" rel="stylesheet" type="text/css" />
<link href="${ctx}flat/css/main.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="${ctx}flat/plugins/tree/skins/tree_default.css" type="text/css"/>
<link href="${ctx}flat/plugins/Accormenus/skins/Accormenus_default.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx}plugins/tree/ztree/jquery.ztree.all-3.5.min.js"></script>
<script type="text/javascript" src="${ctx}js/common/treeNode.js"></script>
<script type="text/javascript" src="${ctx}js/logined/user/userTree.js"></script>
<script type="text/javascript" src="${ctx}js/logined/user/importuserFather.js"></script>
<script type="text/javascript" src="${ctx}js/common/ajaxfileupload.js"></script>
<script type="text/javascript" src="${ctx}js/common/validate_form.js"></script>
<script type="text/javascript" src="${ctx}js/common/showError.js"></script>
<script type="text/javascript" src="${ctx}flat/plugins/dialog/iframeTools.js"></script>
<script type="text/javascript" src="${ctx}flat/js/base.js"></script>
<script type="text/javascript">
    //openSelectTreeUser(zTreeOnCheckResult);
    function zTreeOnCheckResult(data)
    {
    	//类型
    	var type=data.getType();
    	var from = "&type=" + $("#type").val();
    	var source = "&source=tree";
    	if(type=="group"){
        	//组id
        	var groupId=data.Id;
        	if(groupId){
        		$("#page").attr("src",basePath+"logined/user/userList.jsp?clickTree=1&groupId="+groupId+from);
        	}
    	}else{
        	//人员id
        	var userId=data.Id;
        	if(userId){
        		$("#page").attr("src",basePath+"user/getUserById.action?userId="+userId+from+source);
        	}
    	}
    }
    $(document).ready(function() {
    	var type = "?type=" + $("#type").val();
    	//查询用户
    	$("#userSelect").click(function(){
    		
    		$("#page").attr("src",basePath+"logined/user/userSelect.jsp"+type);
    		
    		refreshTree("gid_0");
    	});
    	//点击导入
    	$("#importUser").click(function(){
    		uploadUser();
    		return false;
    	});
    	//$("#page").attr("src",basePath+"logined/user/userList.jsp"+type);
    	(function(){
    	    $("#page").attr("src",basePath+"logined/user/userList.jsp"+type);
            
            refreshTree("gid_0");
    	})();
    });
    
    function refreshTree(id){
    	openSelectTreeUser(zTreeOnCheckResult, null, id);
    }
</script>
</head>
<body>
<input id="type" type="hidden" value="${paramValues.type[0]}" />
<div class="mainpage">
<!--左侧begin--><%--

				
			<c:if test="${paramValues.type[0]=='view'}">
				--%><div class="leftMenu">
					<div class="service-menu">
						<h1>组织结构</h1>
						<div class="zTreeDemoBackground">
							<ul id="groupUserTree" class="ztree">
							</ul>
						</div>
					</div>
				</div><%--

			</c:if>
		
--%><!--左侧end-->
    <iframe frameborder="0" scrolling="auto" border="0" id="page" name="page" class="iframeresize" ></iframe>
</div>
</body>
</html>