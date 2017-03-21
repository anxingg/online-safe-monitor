<%@ page language="java" import="java.util.*"  contentType="text/html; charset=UTF-8" pageEncoding="utf-8"  %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<jsp:include page="../../../common/taglibs.jsp"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<jsp:include page="../head.jsp" />
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>安全管理人员查看</title>
	
    <script type="text/javascript" src="${ctx}wh/js/safetyManagePerson/safetyManagePersonView.js?version=${version}"></script>
    
</head>
<body>
	<input type="hidden" id="personId" value="${param.personId }"/>
	
	<div class="bread-line">
		<label>当前位置：</label><a href="#">首页</a>&gt;<a href="#">企业管理</a>&gt;&nbsp;安全管理人员查看
	</div>
	<div class="formPage">
	 <div class="formbg">
        <div class="big_title">查看<em>（安全管理人员）</em></div>
        <div class="content_form">
                <table width="100%" cellspacing="0" cellpadding="0" border="0" class="inputTable">
                    <tbody>
                        
                        <tr>
                            <th>姓名：</th>
                            <td><span id="name"></span></td>
                            <th>性别：</th>
                            <td>
                            	<span id="sex"></span>
                            	
                            </td>
                        </tr>
                        <tr>
                            <th>身份证号：</th>
                            <td><span id="idNumber"></span></td>
                            <th>文化程度：</th>
                            <td><span id="educationDegree"></span></td>
                        </tr>
                        <tr>
                            <th>证件类型：</th>
                            <td><span id="cardTypeName"></span></td>
                            <th>证书号码：</th>
                            <td><span id="certificateNum"></span></td>
                        </tr>
                        <tr>
                            <th>换证日期：</th>
                            <td><span id="replaceDate"></span></td>
                            <th>联系电话：</th>
                            <td><span id="phone"></span></td>
                        </tr>
                        <tr>
                        	<th>职务：</th>
                            <td><span id="job"></span></td>
                            <th>职称：</th>
                            <td>
                            	<span id="jobTitle"></span>
                            </td>
                        </tr>
                        <tr>
                    		<th><label>备注：</label></th>
	                    	<td colspan="3">
	                    	<span id="memo"></span>
	                    	</td>
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
