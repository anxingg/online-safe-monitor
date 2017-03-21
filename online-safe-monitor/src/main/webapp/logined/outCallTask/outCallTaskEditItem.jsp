<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<jsp:include page="../../common/taglibs.jsp" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>外呼任务-新增</title>
<link href="${ctx}common/css/reset.css" rel="stylesheet" type="text/css" />
<link href="${ctx}common/css/main.css" rel="stylesheet" type="text/css" />
<link href="${ctx}common/css/task.css" rel="stylesheet" type="text/css" />
<link href="${ctx}common/plugins/form/skins/form_default.css" rel="stylesheet" type="text/css" />
<link href="${ctx}common/plugins/selectMember/skins/selectMember_default.css" rel="stylesheet" type="text/css"/>
<link rel="stylesheet" href="${ctx}common/plugins/peopleTree/skins/tree_default.css" type="text/css"/>
<link href="${ctx}common/plugins/Accormenus/skins/Accormenus_default.css" rel="stylesheet" type="text/css" />
<jsp:include page="../../common/head.jsp" />
<!-- 表单验证 开始 -->
<script type="text/javascript" src="${ctx}js/common/showError.js?version=${version}"></script>
<!-- 这个页面的js -->
<script type="text/javascript" src="${ctx}js/logined/outCallTask/outCallTaskEditItem.js?version=${version}"></script>

<style type="text/css">
.inputTable th {
	width: 82px;
}
</style>
</head>
<body>
<input type="hidden" id="questionnaireId" name="questionnaireId" value="${naire.id}"/>
<input type="hidden" name="type" id="type" value="${type}"/> 
<input type="hidden" name="phoneTaskId" id="phoneTaskId" value="<%=request.getParameter("phoneTaskId")%>"/> 
<div class="formPage" >
  <div class="formbg" id="formbg" style="overflow:hidden">
        <div class="content_form">
               <div class="long-steps4 flow-steps mt10">
                       <ul>
                        <li class="done current-prev">
                         <span class="first">1、新增外呼任务</span>
                        </li>
                        <li class="current">
                         <span>2、编辑外呼内容</span>
                        </li>
                        <li class="last">
                         <span>3、发布任务</span>
                        </li>
                      </ul>
               </div>
               <div class="yz_qn" >
                    <div class="menu_left" style="margin-top:10px;">
			            <h2>添加题型</h2>
			            <ul>
			               <li id="addSingle"><a style="display:block"><img src="${ctx}/flat/images/icon/single.png">单选题</a></li>
			               <li id="addMultiple"><a style="display:block"><img src="${ctx}/flat/images/icon/choice.png">多选题</a></li>
			               <li id="addQuestions"><a style="display:block"><img src="${ctx}/flat/images/icon/interlocution.png">问答题</a></li>
			            </ul>
			         </div>
                     <div class="cont_right sj_836"  style="margin-top:10px;margin-bottom:10px;">
                          <h2>${naire.title}</h2>
                          <p class="plan_ques">
                                  ${naire.des}
                          </p>
                          <div class="choice">
                             <s:iterator value="naire.questions" var="question" status="sta">
		                 <s:if test="#question.typeId==1">
			                <dl><input type="hidden" name="id" value="${question.id}"/>
			                	<input type="hidden" name="name" value="${question.name}"/>
			                	<input type="hidden" name="orderList" value="${question.orderList}"/>
			                	<input type="hidden" name="typeId" value="${question.typeId}"/>
			                   <dt><s:property value="#sta.index+1"/>、${question.name}<font>[单选]</font></dt>  
			                     <s:iterator value="#question.items">
				                     <dd><label class="radio"><input type="radio" name="radio${question.id}" readonly="readonly">${content}</label></dd>
			                     </s:iterator>
			                   <dd class="operate">
			                     <span>
			                     	<a class="moveup"><i class="setTop"></i>上移</a>
                                    <a class="movedown"><i class="cancelTop"></i>下移</a>
                                    <a class="edit"><i class="batch_revise"></i>修改</a>
                                    <a class="del"><i class="delete"></i>删除</a>
			                     </span>
			                   </dd>
			                </dl>
		                </s:if>
		                 <s:if test="#question.typeId==2">
			                <dl><input type="hidden" name="id" value="${question.id}"/>
			              	    <input type="hidden" name="name" value="${question.name}"/>
			                	<input type="hidden" name="orderList" value="${question.orderList}"/>
			                	<input type="hidden" name="typeId" value="${question.typeId}"/>
			                   <dt><s:property value="#sta.index+1"/>、${question.name}<font>[多选]</font></dt> 
			                     <s:iterator value="#question.items">
				                     <dd><label class="radio"><input type="checkbox" readonly="readonly">${content}</label></dd>
			                     </s:iterator>
			                   <dd class="operate">
			                     <span>
			                        <a class="moveup"><i class="setTop"></i>上移</a>
                                    <a class="movedown"><i class="cancelTop"></i>下移</a>
                                    <a class="edit"><i class="batch_revise"></i>修改</a>
                                    <a class="del"><i class="delete"></i>删除</a>
			                     </span>
			                   </dd>
			                </dl>
		                </s:if>
		                 <s:if test="#question.typeId==3">
			                <dl><input type="hidden" name="id" value="${question.id}"/>
			               		<input type="hidden" name="name" value="${question.name}"/>
			                	<input type="hidden" name="orderList" value="${question.orderList}"/>
			                	<input type="hidden" name="typeId" value="${question.typeId}"/>
			                   <dt><s:property value="#sta.index+1"/>、${question.name}<font>[问答]</font></dt> 
			                     <!-- <dd class="wd"><textarea class="formTextarea" rows="5"></textarea></dd> -->
			                   <dd class="operate">
			                     <span>
			                      <a class="moveup"><i class="setTop"></i>上移</a>
                                    <a class="movedown"><i class="cancelTop"></i>下移</a>
                                    <a class="edit"><i class="batch_revise"></i>修改</a>
                                    <a class="del"><i class="delete"></i>删除</a>
			                     </span>
			                   </dd>
			                </dl>
		                </s:if>
		                 </s:iterator>     
                          </div>
                     </div>    
               </div>
        </div>
  </div>      
 <div class="buttonArea" id="buttonArea">
       <input type="submit"  value="上一步" class="formButton_grey" onclick="lastStep();"/>
       <input type="submit"  value="发布" class="formButton_green" onclick="save(1);"/>
       <input type="submit"  value="存草稿" class="formButton_grey" onclick="save(2);"/>
       <input type="submit"  value="取 消" class="formButton_grey" onclick="goback();"/>
   </div>
</div>  
</body>
</html>