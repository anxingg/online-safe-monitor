<%@ page language="java" import="java.util.*"  contentType="text/html; charset=UTF-8" pageEncoding="utf-8"  %>
<%@ page import="cn.com.qytx.platform.utils.datetime.Lunar"%>
<%@ page import="cn.com.qytx.cbb.login.action.LogoConfig"%>
<%@ page import="cn.com.qytx.platform.utils.weather.Weather"%>
<%@ page import=" cn.com.qytx.platform.org.domain.ModuleInfo" %>

<jsp:include page="../common/taglibs.jsp"/>
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
<script type="text/javascript">
var basePath = "${ctx}";
function exit() {
	art.dialog.confirm('确定退出吗？', function() {
		 window.document.location = basePath + "logout.jsp";
	});
   };
function hasTodoCCL(){
	$.ajax({
        url : basePath+"customercall/customercall!hasTodoCCL.action",
        type : "post",
        dataType :'text',
        success : function(data) {
            if ("-1" != data){
            	$("#marqueeStr").html("您有"+data+"条工单待处理！");
            	$("#scrollPrompt").show();
            }else{
            	$("#scrollPrompt").hide();
            	$("#marqueeStr").html("");
            }
        }});
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
             <a  target="iframe_main" href="${ctx}hotline/welcome.jsp"  class="icon_home">首页</a><i></i> <a target="iframe_main" href="${ctx}sysset/toRecordSet.action" class="icon_info" title="个人信息">个人信息</a><i></i> <a target="iframe_main" href="${ctx}sysset/toPwdSet.action" class="icon_help" title="密码修改">密码修改</a><i></i> <a href="javascript:void(0);" class="icon_quit" onclick="exit();" title="退出">退出系统</a>
          </div>
        </td>
      </tr>
   </table>
</div><!--头部 end-->
<div id="mainContainer" class="mainbody">
  <div id="menu">
    <div class="s_menu" id="s_menu">
    <!-- 
    	<ul class="l_f_menu">
        <li>
          <p class="open noborder_top"><em class="gdgl"></em>工单管理</p>
          <dl>
            <dt class="hover"> <span><a target="iframe_main" href="01工单管理-我的待办.html">我的待办</a></span> </dt>
            <dt> <span><a target="iframe_main" href="01工单管理-我办过的.html">我办过的</a></span> </dt>
          </dl>
        </li>
        <li>
          <p><em class="yhda"></em><a target="iframe_main" href="02用户档案.html">客户档案</a></p>
        </li>
        <li>
          <p><em class="whrw"></em><a target="iframe_main" href="03外呼任务.html">外呼任务</a></p>
        </li>
        <li>
          <p><em class="ssjk"></em><a target="iframe_main" href="04实时监控.html">实时监控</a></p>
        </li>
        <li>
          <p><em class="gggl"></em><a target="iframe_main" href="公告通知-管理-管理员.html">公告管理</a></p>
        </li>
        <li>
          <p><em class="zshk"></em><a target="iframe_main" href="06知识库.html">知识库</a></p>
        </li>
        <li>
          <p class="open"><em class="cxtj"></em>查询统计</p>
          <dl>
            <dt class="hover"> <span><a target="iframe_main" href="07查询统计-话务量综合统计表.html">话务量综合统计</a></span> </dt>
            <dt> <span><a target="iframe_main" href="07查询统计-坐席工作量.html">坐席工作量</a></span> </dt>
            <dt> <span><a target="iframe_main" href="07查询统计-通话明细.html">通话明细</a></span> </dt>
            <dt> <span><a target="iframe_main" href="07查询统计-短信明细.html">短信明细</a></span> </dt>
            <dt> <span><a target="iframe_main" href="07查询统计-呼损清单.html">呼损清单</a></span> </dt>
            <dt> <span><a target="iframe_main" href="07查询统计-排队统计.html">排队统计</a></span> </dt>
          </dl>
        </li>
        <li>
          <p class="open"><em class="zzjg"></em>组织机构管理</p>
          <dl>
            <dt class="hover"> <span><a target="iframe_main" href="08组织机构管理-部门管理.html">部门管理</a></span> </dt>
            <dt> <span><a target="iframe_main" href="08组织机构管理-人员管理.html">人员管理</a></span> </dt>
          </dl>
        </li>
        <li>
          <p class="open"><em class="xtsz"></em>系统设置</p>
          <dl>
            <dt class="hover"> <span><a target="iframe_main" href="09系统设置-流程管理.html">流程管理</a></span> </dt>
            <dt> <span><a target="iframe_main" href="09系统设置-登录用户管理.html">登录用户管理</a></span> </dt>
            <dt> <span><a target="iframe_main" href="09系统设置-角色权限管理.html">角色权限管理</a></span> </dt>
            <dt> <span><a target="iframe_main" href="09系统设置-数据字典设置.html">数据字典维护</a></span> </dt>
            <dt> <span><a target="iframe_main" href="09系统设置-坐席队列设置.html">坐席队列设置</a></span> </dt>
            <dt> <span><a target="iframe_main" href="09系统设置-行政区域设置.html">行政区域管理</a></span> </dt>
            <dt> <span><a target="iframe_main" href="09系统设置-本地号码维护.html">本地号码维护</a></span> </dt>
            <dt> <span><a target="iframe_main" href="09系统设置-黑名单管理.html">黑名单管理</a></span> </dt>
            <dt> <span><a target="iframe_main" href="09系统设置-个人信息.html">个人信息</a></span> </dt>
            <dt> <span><a target="iframe_main" href="09系统设置-密码设置.html">密码修改</a></span> </dt>
          </dl>
        </li>
      </ul>
       -->
       
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