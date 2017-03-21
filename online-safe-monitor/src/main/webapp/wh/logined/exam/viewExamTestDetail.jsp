<%@ page language="java" import="java.util.*"  contentType="text/html; charset=UTF-8" pageEncoding="utf-8"  %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib prefix='fmt' uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:include page="../../../common/taglibs.jsp"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<jsp:include page="../head.jsp" />
	<title>查看试卷</title>
    
</head>
<body>
  <div class="lookQue">

		<h1 class="bigTitle">
		<span class="num"><label><fmt:formatNumber value="${examUserTest.score}" pattern="#0"/>分</label></span>
		<label>${examUserTest.paperName}</label>
		</h1>
		<h2 class="smalTitle" id="questionInfo">
			企业名称：<label>${companyName}</label>答题人：<label>${examUserTest.userName}</label>身份证号：<label
				>${examUserTest.idCardNum}</label>
		</h2>
		   <s:iterator value="#request.questionList" var="question" status="s">
   		 <dt><s:property value="#s.index+1"/>、<s:property value="#question.questName"/></dt>
 
   		  <s:iterator value="#request.question.examQuestionItemList" var="questionItem" status="s">
   		 	<s:if test="%{('-'+#question.rightItem+'-').indexOf('-'+#questionItem.itemCode+'-')>-1}">
   		 		<s:if test="%{('-'+#question.userCheckItem+'-').indexOf('-'+questionItem.itemCode+'-')>-1}">
   		 			<dd class="reply"><s:property value="#questionItem.itemName"/></dd>
   		 		</s:if>
   		 		<s:else>
   		 			<dd class="reply"><s:property value="#questionItem.itemName"/></dd>
   		 		</s:else>
   		 	</s:if>
   		 	<s:else>
   		 		<s:if test="%{('-'+#question.userCheckItem+'-').indexOf('-'+#questionItem.itemCode+'-')>-1}">
   		 			 <dd class="error"><s:property value="#questionItem.itemName"/></dd>
   		 		</s:if>
   		 		<s:else>
   		 			<dd><s:property value="#questionItem.itemName"/></dd>
   		 		</s:else>
   		 	</s:else>
   		 </s:iterator>

   		
   		 </dl>
   </s:iterator>
		    <div class="buttonArea"> 
			<input type="button" value="返回" class="formButton_grey"  hidefocus="" onClick="javascript :history.back(-1);"/>
</div>
	
	</div>
</body>
</html>
