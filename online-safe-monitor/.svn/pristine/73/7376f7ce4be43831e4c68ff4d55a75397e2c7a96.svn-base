<%@ page language="java" import="java.util.*"  contentType="text/html; charset=UTF-8" pageEncoding="utf-8"  %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<% String type = request.getParameter("type");
	request.setAttribute("type",type);
 %>
<jsp:include page="../../../common/taglibs.jsp"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<jsp:include page="../head.jsp" />
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>安全生产专家</title>
</head>
<body>
	<div class="bread-line">
		<s:if test="#request.type==1||#request.professor.professorType==1">
		<label>当前位置：</label><a href="#">首页</a>&gt;<a href="#">职业卫生专家</a>&gt;&nbsp;查看
		</s:if>
		<s:elseif test="#request.type==2||#request.professor.professorType==2">
		<label>当前位置：</label><a href="#">首页</a>&gt;<a href="#">非煤矿山专家</a>&gt;&nbsp;查看
		</s:elseif>
		<s:elseif test="#request.type==3||#request.professor.professorType==3">
		<label>当前位置：</label><a href="#">首页</a>&gt;<a href="#">危险化学品专家</a>&gt;&nbsp;查看
		</s:elseif>
	</div>
	 <div class="formPage">
	 <div class="formbg">
        <div class="big_title">查看</div>
        <div class="content_form">
                <table width="100%" cellspacing="0" cellpadding="0" border="0" class="inputTable">
                    <tbody>
                        <tr>
                            <th>姓名：</th>
                            <td>
                            	<span>${professor.name }</span>
                            </td>
                        </tr>
                        <tr>
                            <th>专业特长：</th>
                            <td>
                            	<span>${professor.specialties }</span>
                            </td>
                        </tr>
                        <tr>
                        	<th>技术职称：</th>
                            <td>
                            	<span>${professor.title }</span>
                            </td>
                        </tr>
                        <tr>
                        	<th>工作单位：</th>
                            <td>
                            	<span>${professor.workCompany }</span>
                            </td>
                        </tr>
                        <tr>
                        	<th>联系电话：</th>
                            <td>
                            	<span>${professor.phone }</span>
                            </td>
                        </tr>
                        <tr>
                        	<tr>
                            <th>备注：</th>
		                    <td colspan="3">
		                    	<span>${professor.memo }</span>
		                    </td>
                        </tr>
                        </tr>
                    </tbody>
                </table>
        </div>
   </div>
    <div class="buttonArea"> 
			<input type="button" value="返回" class="formButton_grey"  hidefocus="" onclick="javascript :history.back(-1);"/>
</div>
  </div>

<div class="clear"></div>
</body>
</html>
