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
<title>黑名单设置</title>
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
<script type="text/javascript" src="${ctx }platform/js/blacklistFuncion.js?version=${version}"></script>
</head>
<body>
<div class="addcon">
		<div class=" szlist" >
				<div class="select">
						<p>
								<label class="radio" id="notenable">
										<input type="radio" name="isEnableBlacklist" value="1" id="isEnableBlacklist_1" checked />
										不启用黑名单</label>
						</p>
						<p>
								<label class="radio wrd">
										<input type="radio" name="isEnableBlacklist" value="2" id="isEnableBlacklist_2"  />
										启用黑名单</label>
						</p>
						
				</div>
				<div class="buttonArea">
						<input class="formButton_tab" id="saveBlacklist" type="submit" value="保 存">
						&nbsp; </div>
		</div>
</div>

</body>
</html>
