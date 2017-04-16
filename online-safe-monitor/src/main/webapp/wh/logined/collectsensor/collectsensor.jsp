<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>传感器管理</title>
<jsp:include page="../osmHead.jsp" />
<script type="text/javascript">
	var basePath = "${ctx}";
</script>
<link href="${ctx }flat/plugins/Accormenus/skins/Accormenus_default.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="${ctx }flat/plugins/tree/skins/tree_default.css" type="text/css"/>
<script type="text/javascript" src="${ctx }flat/js/placeholder.js"></script>
<script type="text/javascript" src="${ctx }flat/js/base.js"></script>
<script type="text/javascript" src="${ctx }flat/plugins/tree/skins/jquery.ztree.all-3.2.min.js"></script>
<script type="text/javascript" src="${ctx }flat/plugins/dialog/artDialog.js?skin=default"></script>
<script type="text/javascript" src="${ctx }flat/plugins/dialog/iframeTools.js"></script>
<script type="text/javascript" src="${ctx}js/common/treeNode.js"></script>
<script type="text/javascript" src="${ctx}wh/js/collectsensor/collectsensor.js?version=${version}"></script>
<script type="text/javascript" src="${ctx}wh/js/collectsensor/collectsensor_tree.js?version=${version}"></script>
</head>
<body>
<div class="bread-line">
     <label for="">位置：</label>
     <a href="#">安监在线</a>>
     <a href="#">设备管理</a>>
     <a href="#">传感器管理设置</a>
 </div>
<div class="mainpage">
  <div class="leftMenu">
    <div class="service-menu">
      <h1>所有危险源</h1>
      <input type="text" class="search" placeholder="请输入企业、危险源名称"   id="btnSearch" />
   	  <div class="zTreeDemoBackground">
      	<ul id="groupUserTree" class="ztree" style="margin-top:0px;width:auto;overflow:auto"></ul>
      </div>
    </div>
  </div>
  <iframe frameborder="0" scrolling="auto" border="0" id="page" name="page" class="iframeresize"></iframe>
</div>
</body>
<script>
	funPlaceholder(document.getElementById("searchWord"));
</script>
</html>