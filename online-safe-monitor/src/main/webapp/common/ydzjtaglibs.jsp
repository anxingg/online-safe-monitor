<%@ page import="cn.com.qytx.oa.news.util.SystemConfig" %>
<%@page import="org.springframework.context.ApplicationContext"%>
<%@page import="org.springframework.web.context.support.WebApplicationContextUtils"%>

<%
    ApplicationContext context =  WebApplicationContextUtils. getWebApplicationContext(application);
    SystemConfig systemConfig=(SystemConfig)context.getBean("systemConfig");
    request.setAttribute("downPath",systemConfig.getDownPath());
%>
