<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
request.setAttribute("ctx", basePath);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>公告管理</title>

<link href="${ctx }platform/css/base.css" rel="stylesheet" type="text/css" />
<link href="${ctx }platform/css/public.css" rel="stylesheet" type="text/css" />
<link href="${ctx }platform/css/add_com.css" rel="stylesheet" type="text/css" />
<link href="${ctx }platform/plugins/datatable/table_style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx }platform/js/jquery-1.6.min.js"></script>
<script type="text/javascript" src="${ctx }platform/plugins/dialog/artDialog.js?skin=blue"></script>
<script type="text/javascript" src="${ctx }platform/js/base.js"></script>
<script type="text/javascript" src="${ctx }platform/plugins/dialog/iframeTools.js"></script>
<script type="text/javascript">
var basePath="${ctx }";
</script>

</head>

<body>

<div class="main_head">呼叫中心配置系统</div>
<div class="main_content">
		<div class="main_left" style="height:662px;">
				<ul class="m_l_f_menu">
						<li class="current"><p><a href="${ctx }databaseSet/dataBaseSetView.action" target="iframe_main" >数据库设置</a></p></li>
						<li><p><a href="${ctx }voxSet/voxFileSetView.action" target="iframe_main" >语音服务器设置</a></p></li>
				</ul>
		</div>
		<div  class="main_right">
				<iframe src="${ctx }databaseSet/dataBaseSetView.action" id="iframe_main" class="meetingIframe" name="iframe_main" frameborder="no" scrolling="auto" hidefocus ></iframe>
		</div>
</div>

</body>
</html>
