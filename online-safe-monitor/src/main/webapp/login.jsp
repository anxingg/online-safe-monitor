<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<jsp:include page="common/taglibs.jsp" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>${applicationScope.systemBasisSet.backstageName}</title>
	<link rel="stylesheet" type="text/css" href="${ctx }wh/css/style.css" />
	<script type="text/javascript">
		var basePath = '${ctx}';
	</script>
	<script type="text/javascript" src="${ctx}js/common/jquery-1.8.0.min.js"></script>
	<script type="text/javascript" src="${ctx}js/common/jquery.lock.js"></script>
	<script type="text/javascript" src="${ctx}js/common/jquery.cookie.js"></script>
	<script type="text/javascript" src="${ctx}common/js/placeholder_login.js"></script>
	<script type="text/javascript" src="${ctx}js/common/jquery.query.js"></script>
	<script type="text/javascript" src="${ctx}js/login.js"></script>
	<script type="text/javascript">
		//防止被加载到框架中
	    if (self.location != top.location) {
		    top.location = self.location;
	    }
	</script>
</head>
<body class="loginBody">
<div class="login_head">
    <span class="logo"><img src="${ctx}wh/images/login/logo.png"/>${applicationScope.systemBasisSet.backstageName}</span>
    <span class="help"><a href="javascript:void(0);">常见问题</a>|<a href="javascript:void(0);">帮助中心</a></span>
 </div>
 <div class="loginBox">
 <form action="j_spring_security_check" method="post" name="loginForm" id="loginForm">
   <div class="loginForm">
      <h2>用户登录</h2>
           <ul>
             <li><input class="ipt" placeholder="用户名" type="text"  id="j_username" name="j_username"/></li>
             <li><input class="ipt" placeholder="密码" type="password" id="j_password" name="j_password" onkeyup="value=value.replace(/[^\w\.\*\@\#\%\^\&\(\)\?\/]/ig,'')" /></li>
             <li><input class="ipt mr5" style="width:150px;" placeholder="验证码" id="checkCode" name="checkCode" type="text"/>
             <img alt="点击更换验证码" title="点击更换验证码"  onclick="loadimage()" name="randImage" id="randImage" src="${ctx}common/code.jsp" height="33" width="88" />
             <a href="javascript:void(0);" onclick="loadimage();" class="gray_9"> 换一张</a></li>
             <li class="operate"><label class="radio"><input id="cb_remember" type="checkbox" checked="checked" />记住用户名和密码</label></li>
             <li id="errorLi" class="error" style="display:none;"><em></em><label id="perror" >用户名错误</label>&nbsp;</li>
           </ul>
           <p class="login_btn"><input id="btnLogin" class="bt_logo" value="登 录" type="button" /></p>
   </div>
   </form>
   <div class="copy">版权所有：${applicationScope.systemBasisSet.unitInformation}</div>
 </div>

</body>
</html>