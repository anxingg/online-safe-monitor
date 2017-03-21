<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<% String type = request.getParameter("type");
	request.setAttribute("type",type);
%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>投票调查-题型预览</title>
<jsp:include page="../../common/flatHead.jsp" />
<link href="${ctx}flat/css/reset.css" rel="stylesheet" type="text/css" />
<link href="${ctx}flat/css/testPaper.css" rel="stylesheet" type="text/css" />
<link href="${ctx}flat/plugins/form/skins/form_default.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx}js/logined/question/viewNaire.js?version=${version}"></script>
<script>
  $(document).ready(function() {
	 $(".choice dd").hover(function() {
			$(this).css("background","#fcfcfc");
	  }, function() {
			$(this).css("background","none");
	 });
  });
</script>
	
</head>
<body>
<div class="formPage">
   <div class="formbg">
          <div class="content_form">
               <div class="census_t">
                     <div class="cont_right pt10">
              <h2>${questionnaire.title}</h2>
              <s:if test="questionnaire.state==4">
                 <p class="center">(提示：本问卷调查已结束，您只能浏览问卷)</p>
              </s:if>
              <p class="subject">
                     ${questionnaire.des}
              </p>
              <div class="choice">
              	<s:iterator value="questionnaire.questions" status="stat">
                   <dl>
                      <dt>${stat.index+1}、${name}<font>[<s:if test="typeId==1">单选</s:if><s:elseif test="typeId==2">多选</s:elseif><s:elseif test="typeId==3">问答</s:elseif>]</font></dt>
                       	<s:if test="typeId==3">
                       		 <dd><strong>回答：</strong>${answer}</dd>
                       	</s:if>
                       	<s:elseif test="typeId==1">
                       		<s:iterator value="items">
                       			<dd><label class="radio"><input type="radio" disabled="disabled" value="${id}" <s:if test="answer==true">checked="checked"</s:if>/>${content}</label></dd>
                       		</s:iterator>
                       	</s:elseif>
                        <s:elseif test="typeId==2">
                        	<s:iterator value="items">
                       			 <dd><label class="radio"><input type="checkbox" disabled="disabled" value="${id}" <s:if test="answer==true">checked="checked"</s:if>/>${content}</label></dd>
                       		</s:iterator>
                        </s:elseif>
                   </dl>
               </s:iterator>
	            	  </div>
             	 </div>
              </div>
              <div class="clear"></div>
         </div>
         <div class="clear"></div>
   </div>
         <div class="buttonArea">
         	<s:if test="checkDetain==1">
         		<input type="submit"  value="关闭" class="formButton_grey" onclick="window.top.closeCurrentTab();"/>
         	</s:if>
         	<s:else>
              <input type="submit"  value="返回" class="formButton_grey"
               onclick="javascript:window.location.href='${ctx}logined/outCallTask/outCallTaskResultList.jsp?paramData=0&questionnaireId=${questionnaire.id }'"/>
         	</s:else>
        <%-- <span class="blue"> 点击回到结果查看列表</span> --%>
         </div>
        
</div>
</body>
</html>
