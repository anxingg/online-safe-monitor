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
	<title>${applicationScope.systemBasisSet.backstageName}</title>
	<jsp:include page="osmHead.jsp" />
</head>
<body>
	<div class="wrap">
		<div class="mainhead">
			<%@include file="osmMainHead.jsp"%>	
		</div>
		<div class="parkCont" id="parkCont">
		</div>
		<div class="foot">
			<span>${applicationScope.systemBasisSet.unitInformation}   @2016</span>
		</div>
	</div>
</body>
<script src="${ctx}wh/js/safeOnline.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript" src="${ctx}wh/js/osmIndex.js"></script>
<script src="${ctx }wh/js/calendar.js" type="text/javascript"></script>
<script type="text/javascript">
	var basePath = "${ctx}";
 		function exit() {
 			art.dialog.confirm('确定关闭吗？', function() {
 				 window.document.location = basePath + "logout.jsp";
 				 //window.opener=null;
			 //window.open('','_self');
			 //window.close();
 			});
 		};
</script>
</html>
