<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="req" class="cn.com.qytx.oa.util.RequestParamter" scope="session" />



<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path+"/" ;
    request.setAttribute("ctx", basePath);
    request.setAttribute("version", "3.0.1");
    
    request.setAttribute("workflowName", "工单流转new-1");
%>