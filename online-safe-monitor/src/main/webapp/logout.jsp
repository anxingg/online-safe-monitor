
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
   <%
       session.invalidate(); //清空Session
      response.sendRedirect("login.jsp");
%>