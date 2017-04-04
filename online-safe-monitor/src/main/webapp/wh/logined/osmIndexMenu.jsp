<%@ page language="java" import="java.util.*"  contentType="text/html; charset=UTF-8" pageEncoding="utf-8"  %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn"%>
<%@include file="/ninclude/import.jsp"%>
<div class="con_list">
	<c:forEach var="moduleInfo" items="${showModuleList}" varStatus="menu">
		<c:choose>
			<c:when test="${(moduleInfo.moduleLevel==1) &&(moduleInfo.sysName=='osm')}">
				<c:if test="${menu.index==0}">
					<div class="list_top">
				</c:if>
				<c:if test="${menu.index==5}">
					</div>
					<div class="list_bottom">
				</c:if>
				<div class="list_detail" moduleUrl="${moduleInfo.url}">
					<img src="../img/${moduleInfo.icon}"/>
					<p>${moduleInfo.moduleName}</p>
				</div>
				<c:if test="${menu.last==true}">
					</div>
				</c:if>
			</c:when>
		</c:choose>	
	</c:forEach>
</div>
<script type="text/javascript">
  	 $(document).ready(function() {
  	 	$(".list_detail").click(function(){
			 var moduleUrl=$(this).attr("moduleUrl");
			 if(!moduleUrl.startsWith('http://')){
			 	moduleUrl=basePath+moduleUrl;
			 }
			 console.log("open:"+moduleUrl);
			 if(moduleUrl){
			 	window.open(moduleUrl); 
			 }
		});	
	});
</script>
				    