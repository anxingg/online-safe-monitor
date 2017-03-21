<%@ page language="java" import="java.util.*"  contentType="text/html; charset=UTF-8" pageEncoding="utf-8"  %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<jsp:include page="../../../common/taglibs.jsp"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<jsp:include page="../head.jsp" />
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>考试试题</title>
    <script type="text/javascript" src="${ctx}wh/js/exam/viewExamQuestion.js"></script> 
    <script type="text/javascript" src="${ctx }js/common/map.js"></script> 
    <script type="text/javascript" src="${ctx}wh/js/exam/jsList.js"></script>
        <style type="text/css">
       .enter{ padding:0 19px;}
       .inputTable th{ width:100px;}
    </style>
</head>
<body>

	<input type="hidden" id="whroletype" value='<s:property value="#session.whroletype"/>'/>
	<input type="hidden" id="group_id" value='<s:property value="#session.adminUser.groupId"/>'/>
	<input type="hidden" id="questionId" value="${param.questionId }"/>
	<div class="bread-line">
		<label>当前位置：</label><a href="#">首页</a>&gt;<a href="#">试题</a>&gt;&nbsp;查看
	</div>
	
                 <div class="formPage">
	 <div class="formbg">
        <div class="big_title">查看<em>（试题）</em></div>
        <div class="content_form">
                <table width="100%" cellspacing="0" cellpadding="0" border="0" class="inputTable" id="tabInfo">
                 <tr>
                 <th><label>试题名称：</label></th>
                <td id="title"></td>
                </tr>
                	      <tr><th><label>试题类型：</label></th>
                <td style="display:none"><select id="examType"></select></td>
                <td id="examType1"></td>
                </tr>
              <tr>
                <th><label>题目类型：</label></th>
                <td id="questionItem">
	            </td>
              </tr>
              
              <tr>
              <th>分值：</th>
             <td id="grade"></td>
              </tr>
              
          </table>  
         <dl class="enter" id="add" >
         </dl>
 
        </div>
   </div>
   
   
    <div class="buttonArea"> 
			<input type="button" value="返回" class="formButton_grey"  hidefocus="" onClick="javascript :history.back(-1);"/>
</div>
  
  

<div class="choice"></div>
</body>
</html>
