<%@ page language="java" import="java.util.*"  contentType="text/html; charset=UTF-8" pageEncoding="utf-8"  %>
<%@ page import="cn.com.qytx.platform.utils.datetime.Lunar"%>
<%@ page import="cn.com.qytx.cbb.login.action.LogoConfig"%>
<%@ page import="cn.com.qytx.platform.utils.weather.Weather"%>
<%@ page import=" cn.com.qytx.platform.org.domain.ModuleInfo" %>

<jsp:include page="../../common/taglibs.jsp"/>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%
			//获取农历日期
			Calendar today = Calendar.getInstance();
			Date date = new Date();
			today.setTime(date);
			Lunar lunar = new Lunar(today);
			String basePath2 = request.getScheme() + "://"
		            + request.getServerName() + ":" + request.getServerPort() ;
 %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%-- <jsp:include page="../common/head.jsp"/> --%>
<title>呼叫中心后台主框架</title>
<link href="${ctx}common/css/reset.css" rel="stylesheet" type="text/css" />
<link href="${ctx}common/css/main.css" rel="stylesheet" type="text/css" />
<link href="${ctx}common/plugins/dialog/skins/default.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx}js/common/jquery-1.8.0.min.js"></script>
<script type="text/javascript"
	src="${ctx}flat/plugins/dialog/artDialog.js?skin=default"></script>
<script type="text/javascript"
	src="${ctx}flat/plugins/dialog/iframeTools.js"></script>
<script type="text/javascript" src="${ctx}js/common/weather.js"></script>
<script type="text/javascript" src="${ctx}js/common/calendar.js"></script>
<script type="text/javascript" src="${ctx}hotline/initMondleInfo.js"></script>
<script src="${ctx }common/js/main.js" type="text/javascript"></script>
<script src="${ctx }js/logined/indexIsFork.js" type="text/javascript"></script>
<script src="${ctx }wh/js/calendar.js" type="text/javascript"></script>
<script type="text/javascript">
var basePath = "${ctx}";
function exit() {
	art.dialog.confirm('确定关闭吗？', function() {
		 //window.document.location = basePath + "logout.jsp";
		 window.opener=null;
		 window.open('','_self');
		 window.close();
	});
   };
</script>
<style type="text/css">
  html{ overflow:hidden;}
</style>
</head>
<body  class="frame_class">
<!--头部 begin-->
<div class="head">
<input type="hidden" id="loginIsForkGroup" value="<s:property value='#session.adminUser.isForkGroup'/>"/>
   <table>
      <tr>
        <td style="width:350px;"><h1>${applicationScope.systemBasisSet.backstageName}</h1></td>
        <td valign="top">
           <div class="top_info">
            <span>欢迎您，<em class="name"><s:property value='#session.adminUser.userName'/></em></span>
           <span id="w_weather" style="display:none;"></span>
			<%-- <span id="clock"> --%>
				<span class="time" id="clock"><%=Integer.parseInt( (new java.text.SimpleDateFormat("HH").format(new Date()) ) ) %>:<%=new java.text.SimpleDateFormat("mm:ss").format(new Date()) %></span>
			<%-- </span> --%>
          </div>
          <div class="down_operate">
             <a  target="iframe_main" href="${ctx}hotline/welcome.jsp"  class="icon_home">首页</a><i></i> <a target="iframe_main" href="${ctx}sysset/toRecordSet.action" class="icon_info" title="个人信息">个人信息</a><i></i> <a target="iframe_main" href="${ctx}sysset/toPwdSet.action" class="icon_help" title="密码修改">密码修改</a><i></i> <a href="javascript:void(0);" class="icon_quit" onclick="exit();" title="关闭">关闭页面</a>
          </div>
        </td>
      </tr>
   </table>
</div><!--头部 end-->
<div id="mainContainer" class="mainbody">
  <div id="menu">
    <div class="s_menu" id="s_menu">
    </div>
  </div>
  <!--右侧内容 begin-->
  <div  id="mainFrameContainer">
    <iframe src="${ctx}hotline/welcome.jsp" id="iframe_main" class="meetingIframe" name="iframe_main" frameborder="no" scrolling="auto" ></iframe>
  </div>
  <!--右侧内容 end--> 
</div>
<!--底部信息begin-->
<div class="bottom"><span>技术支持：${applicationScope.systemBasisSet.copyrightInformation}</span></div>
<!--底部信息end-->
</body>
</html>