<%@ page language="java" import="java.util.*"  contentType="text/html; charset=UTF-8" pageEncoding="utf-8"  %>
<%@ page import="cn.com.qytx.platform.utils.datetime.Lunar"%>
<%@ page import="cn.com.qytx.cbb.login.action.LogoConfig"%>
<%@ page import="cn.com.qytx.platform.utils.weather.Weather"%>
<%@ page import=" cn.com.qytx.platform.org.domain.ModuleInfo" %>
<jsp:include page="../../common/taglibs.jsp"/>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<s:if test="#session.whroletype==1">
		<title>安全生产管理信息系统</title>
	</s:if>
	<s:if test="#session.whroletype==2">
		<title>企业安全生产自助管理系统</title>
	</s:if>
	<jsp:include page="head.jsp" />
	<script type="text/javascript" src="${ctx}wh/js/welcome.js"></script>
	<script type="text/javascript" src="${ctx}wh/js/main.js"></script>
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
</head>
<body class="easyui-layout">
<input type="hidden" id="whroletype" value='<s:property value="#session.whroletype"/>'/>
	<div data-options="region:'north',border:false" class="mainhead" >
		<s:if test="#session.whroletype==1">
			<div class="logo"><img src="${ctx }wh/images/login/logo.png">安全生产管理信息系统</div>
		</s:if>
		<s:if test="#session.whroletype==3">
			<div class="logo"><img src="${ctx }wh/images/login/logo.png">安全生产管理信息系统</div>
		</s:if>
		<s:if test="#session.whroletype==2">
			<div class="logo"><img src="${ctx }wh/images/login/logo.png">企业安全生产自助管理系统</div>
		</s:if>
		<div class="headMenu">
			<s:if test="#session.whroletype==1">
			<span class="userName">欢迎您 ,${session.adminUser.userName}</span>
			</s:if>
			<s:if test="#session.whroletype==3">
			<span class="userName">欢迎您 ,${session.adminUser.userName}</span>
			</s:if>
			<s:if test="#session.whroletype==2">
			<span class="userName">欢迎您 ,<s:property value="#session.companyName"/></span>
			</s:if>
			<ul>
				<li>
					<!-- 政府 -->
					<s:if test="#session.whroletype==1">
						<%-- <a href="${ctx}wh/logined/company/companyList.jsp" target="mainIframe" class="home">首页</a> --%>
						<a href="${ctx}wh/logined/index/parkIntroduction.html" target="mainIframe" class="home">首页</a>
					</s:if>
					<s:if test="#session.whroletype==3">
						<%-- <a href="${ctx}wh/logined/company/companyList.jsp" target="mainIframe" class="home">首页</a> --%>
						<a href="${ctx}wh/logined/index/parkIntroduction.html" target="mainIframe" class="home">首页</a>
					</s:if>
					<s:if test="#session.whroletype==2">
						<%-- <a href="${ctx}wh/logined/company/companyBaseView.jsp" target="mainIframe" class="home">首页</a> --%>
						<a href="${ctx}wh/logined/index/parkIntroduction.html" target="mainIframe" class="home">首页</a>
					</s:if>
				</li>
				<li><a href="javascript:void(0);" onclick="exit();" class="out">关闭</a></li>
				<s:if test="#session.whroletype==1">
				<li><a href="javascript:void(0)"  class="refresh" id="flush">缓存刷新</a></li>
				</s:if>
			</ul>
		</div>
	</div>
	<div data-options="region:'west',split:true" title="菜单" style="width:200px;" class="leftMenu" id="leftMenu">
    	
        	

    
    </div>
	<div data-options="region:'center'" class="mainbd">
	<s:if test="#session.whroletype==1">
		<%-- <iframe id="mainIframe" src="${ctx}wh/logined/company/companyList.jsp" style="overflow-x:hidden;" class="mainIframe"  frameborder="no" scrolling="auto"  name="mainIframe"></iframe> --%>	
		<iframe id="mainIframe" src="${ctx}wh/logined/index/parkIntroduction.html" style="overflow-x:hidden;" class="mainIframe"  frameborder="no" scrolling="auto"  name="mainIframe"></iframe>	
	</s:if>
	<s:if test="#session.whroletype==3">
		<%-- <iframe id="mainIframe" src="${ctx}wh/logined/company/companyList.jsp" style="overflow-x:hidden;" class="mainIframe"  frameborder="no" scrolling="auto"  name="mainIframe"></iframe> --%>	
		<iframe id="mainIframe" src="${ctx}wh/logined/index/parkIntroduction.html" style="overflow-x:hidden;" class="mainIframe"  frameborder="no" scrolling="auto"  name="mainIframe"></iframe>	
	</s:if>
	<s:if test="#session.whroletype==2">
		<%-- <iframe id="mainIframe" src="${ctx}wh/logined/company/companyBaseView.jsp" style="overflow-x:hidden;" class="mainIframe"  frameborder="no" scrolling="auto"  name="mainIframe"></iframe> --%>	
		<iframe id="mainIframe" src="${ctx}wh/logined/index/parkIntroduction.html" style="overflow-x:hidden;" class="mainIframe"  frameborder="no" scrolling="auto"  name="mainIframe"></iframe>	
	</s:if>
    </div>
</body>
</html>
<script language="javascript">
$(document).ready(function(){
	$.ajax({
		url : basePath + "whmenu/fetchMenu.action",
		type : "post",
		dataType : 'html',
		success : function(data) {
			$("#leftMenu").html(data);
		}
	});
	$("#flush").click(function(){
		flushCache();
		return false;
	});
});
function flushCache(){
	$.ajax({
		url : basePath + "loginwh/flushCache.action",
		type : "post",
		dataType : 'html',
		success : function(data) {
			art.dialog.alert("缓存刷新成功！");
		}
	});
}
</script>