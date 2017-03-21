<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="../../common/taglibs.jsp"/>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>首页</title>
		<script type="text/javascript" src="${ctx}js/common/jquery-1.8.0.min.js?version=${version}"></script>
	</head>
<script type="text/javascript">
  function forward(){
    //window.location.href=
 	var url=document.getElementById("fwdUrl").value;
 	//alert(url);
    //window.openp://10.100.6.140:8080/hnhwt/mobile/loginAjax.action?cid=31146&uid=10987558&name=admin&sid=tzt");
    //window.open(fdurl);
 	window.location.href=url;
 	//if(url.indexOf("toWhere=1") > 0){
 	//	alert("1");
 	//}
 	//if(url.indexOf("toWhere=2") > 0){
 	//	alert("2");
 	//}
 	//if(url.indexOf("toWhere=3") > 0){
 	//	alert("3");
 	//}
 	//if(url.indexOf("toWhere=4") > 0){
 	//	alert("4");
 	//}
 	//if(url.indexOf("toWhere=5") > 0){
 	//	alert("5");
 	//}
 	
 	var parent=window.top;
// 	var liNodes=$("div.s_menu ul li dt", $(parent.document).find("iframe[name='iframe_main']").contents());
 	//var liNodes=$(parent.document).("div.s_menu ul li dt");
	//alert();
	//liNodes.removeClass("hover");
	//var currentLi=liNodes.filter("#yfsSMS");
	//currentLi.addClass("hover");
  }
</script>
	<body onload="forward();">
	<input name="fwdUrl" id="fwdUrl" value=${forwardUrl} type="hidden"/>
	</body>

</html>
