<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>设计流程</title>
<jsp:include page="../../../common/flatHead.jsp" />
<link href="${ctx }flat/css/reset.css" rel="stylesheet" type="text/css" />
<link href="${ctx }flat/css/main.css" rel="stylesheet" type="text/css" />
<link href="${ctx }flat/plugins/form/skins/form_default.css" rel="stylesheet" type="text/css" />
<link href="${ctx }flat/plugins/datatable/skins/datatable_default.css" rel="stylesheet" type="text/css" />
<link href="${ctx }flat/plugins/Accormenus/skins/Accormenus_default.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx }flat/plugins/Accormenus/skins/jquery.Accormenus.js"></script>
<script type="text/javascript" src="${ctx }flat/js/base.js"></script>
<script type="text/javascript" src="${ctx }flat/js/placeholder.js"></script>
<script type="text/javascript" src="${ctx }/logined/workflow/js/index.js"></script>
<script type="text/javascript" src="${ctx }/logined/workflow/js/jquery.autocomplete.min.js"></script>
<link rel="stylesheet" type="text/css" href="${ctx}/logined/workflow/js/jquery.autocomplete.css">
	<script>
		function prgleft_height() {
			//新工作左侧菜单的长度自适应屏幕
			var wHeight = (window.document.documentElement.clientHeight
					|| window.document.body.clientHeight || window.innerHeight);
			$(".leftMenu .service-menu").height(wHeight - 80);
		}
	</script>
</head>
<body>
	<input type="hidden" id="processAttributeId"
		value="${processAttributeId}" />
	<div class="mainpage">
		<div class="leftMenu">
			<!--    <h1>选择流程<em class="fr fresh" id="refresh"  onclick="javascript:window.location.reload();" ><span></span>更新缓存</em></h1> -->
			<!-- <div class="blockBox">  -->
			<!-- <div class="treeSearch"><input name="" class="formText" type="text" id="searchProcess"  style="width:175px"/></div>
					<div class="top4"></div> -->
			<div class="service-menu">
				<h1>
					选择流程<em class="fr fresh" id="refresh"
						onclick="javascript:window.location.reload();"><span></span>更新缓存</em>
				</h1>
				<p class="search">
					<input id="searchProcess" maxlength="20" type="text"
						class="formText" />
				</p>
				<c:forEach items="${procssCategorys}" var="cate" varStatus="pc">
					<div class="menu_l">
						<p class="menu-p">
						    <i class="menu-i"></i>
							<a href="javascript:void(0)">${cate.categoryName}</a>
							<!--  <a href="${ctx}workflow/process!editProcess.action?processAttributeId=${processAttributeId}" target="page">${cate.categoryName}</a>-->
						</p>
						<div class="menu-c">
							<ul>
								<c:forEach items="${cate.processAttributeList }" var="proc"
									varStatus="pr">
									<li><a title="${proc.processName }"
										cateType='${cate.categoryName }' target="page"
										href="${ctx }/workflow/process!editProcess.action?processAttributeId=${proc.id}">${proc.processName }</a></li>
								</c:forEach>
							</ul>
						</div>
					</div>
				</c:forEach>
			</div>
		</div>
		<!-- 		<iframe id="page" name="page" src="${ctx}/index.jsp" border="0" frameBorder="0" scrolling="auto" style="width: 100%; height:100%" ></iframe> -->
		<iframe id="page" class="iframeresize" name="page" src="" border="0"
			frameBorder="0" scrolling="auto" style="width: 100%; height:100%"></iframe>

	</div>
</body>
</html>