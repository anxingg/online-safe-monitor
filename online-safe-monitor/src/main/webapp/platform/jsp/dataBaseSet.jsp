<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
request.setAttribute("ctx", basePath);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>系统初始化设置</title>
<link href="${ctx }platform/css/base.css" rel="stylesheet" type="text/css" />
<link href="${ctx }platform/css/public.css" rel="stylesheet" type="text/css" />
<link href="${ctx }platform/css/add_com.css" rel="stylesheet" type="text/css" />
<link href="${ctx }platform/plugins/datatable/table_style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx }platform/js/jquery-1.6.min.js"></script>
<script type="text/javascript" src="${ctx }platform/js/base.js"></script>
<script type="text/javascript" src="${ctx }platform/js/dataBaseSet.js"></script>
<script type="text/javascript" src="${ctx }platform/plugins/dialog/artDialog.js?skin=blue"></script>

<script type="text/javascript" src="${ctx }platform/plugins/dialog/iframeTools.js"></script>

<script type="text/javascript">
var basePath="${ctx }";
</script>

</head>
<body>
<div class="addcon">
		<div class="szlist">
				<p><span>数据库服务器地址：</span>
						<input name="dbset.dbHostIp"  id="dbHostIp"  value="${dbset.dbHostIp}" type="text"  class="formText"/>
				</p>
				<p><span>端口：</span>
						<input name="dbset.dbPort" id="dbPort"  value="${dbset.dbPort}"  type="text" class="formText"/>
				</p>
				<div class="line"></div>
				<p><span>数据库用户名：</span>
						<input name="dbset.dbUsername" id="dbUsername"  value="${dbset.dbUsername}" type="text" class="formText"/>
				</p>
				<p><span>密码：</span>
						<input name="dbset.dbPsw" id="dbPsw"  value="${dbset.dbPsw}" type="text" class="formText"/>
				</p>
				<p><span>数据库名：</span>
						<input name="dbset.dbName" id="dbName"  value="${dbset.dbName}" type="text" class="formText"/>
				</p>
		</div>
		<div class="buttonArea">
    <input class="formButton_tab" id="dataBaseSetSave" onclick="" type="submit" value="保 存">
    &nbsp; </div>
</div>
</body>
</html>
