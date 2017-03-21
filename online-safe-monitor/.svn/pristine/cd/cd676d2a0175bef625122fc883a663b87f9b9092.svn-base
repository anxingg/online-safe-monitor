    <%@page import="cn.com.qytx.cbb.file.config.FilePathConfig"%>
	<%@page import="cn.com.qytx.platform.base.application.SpringContextHolder"%>
    <%@page import="cn.com.qytx.platform.utils.spring.SpringUtil"%>
    <%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
    <%@ page import="cn.com.qytx.oa.util.Uploader" %>
    <%
    request.setCharacterEncoding("utf-8");
	response.setCharacterEncoding("utf-8");
    Uploader up = new Uploader(request);
    //String path=SpringContextHolder.getBean(FilePathConfig.class).getFileUploadPath();
    //String upload = path+"upload";
    up.setSavePath("upload");
    String[] fileType = {".gif" , ".png" , ".jpg" , ".jpeg" , ".bmp"};
    up.setAllowFiles(fileType);
    up.setMaxSize(10000); //单位KB
    up.upload();
    response.getWriter().print("{'original':'"+up.getOriginalName()+"','url':'"+up.getUrl()+"','title':'"+up.getTitle()+"','state':'"+up.getState()+"'}");
    %>
