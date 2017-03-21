<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<jsp:include page="../../common/taglibs.jsp" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>外呼任务-修改</title>
<link href="${ctx}common/css/reset.css" rel="stylesheet" type="text/css" />
<link href="${ctx}common/css/main.css" rel="stylesheet" type="text/css" />
<link href="${ctx}common/css/task.css" rel="stylesheet" type="text/css" />
<link href="${ctx}common/plugins/form/skins/form_default.css" rel="stylesheet" type="text/css" />
<link href="${ctx}common/plugins/selectMember/skins/selectMember_default.css" rel="stylesheet" type="text/css"/>
<link rel="stylesheet" href="${ctx}common/plugins/peopleTree/skins/tree_default.css" type="text/css"/>
<link href="${ctx}common/plugins/Accormenus/skins/Accormenus_default.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
	var basePath = '${ctx}';
</script>
<script type="text/javascript" src="${ctx}js/common/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="${ctx}js/placeholder.js"></script>
<script type="text/javascript" src="${ctx}js/base.js"></script>
<script type="text/javascript" src="${ctx}js/add.js"></script>
<script type="text/javascript" src="${ctx}common/plugins/dialog/artDialog.js?skin=default"></script>
<script type="text/javascript" src="${ctx}flat/plugins/dialog/iframeTools.js?version=${version}"></script>
<script type="text/javascript" src="${ctx}common/plugins/peopleTree/skins/jquery.ztree.all-3.2.min.js"></script>
<script type="text/javascript" src="${ctx}js/logined/outCallTask/outCallTaskEdit.js?version=${version}"></script>
<script>
	function disp_alert2(){
		art.dialog.open('00选择人员.html', {title: '选择人员' ,width:400,height:380,button: [{name: '确定',focus: true},{name: '取消'}]});
   }
</script>
<style type="text/css">
.inputTable th {
	width: 112px;
}
</style>
</head>
<body>
<input type="hidden" id="questionnaireId" name="questionnaireId" value="${naire.id}"/>
<input type="hidden" name="type" id="type" value="${type}"/> 
<div class="formPage ">
  <div class="formbg">
    <div class="content_form">
        <div class="flow-steps mt10">
               <ul>
                  <li class="done current-prev">
                 <span class="first">1、新增外呼任务</span>
                </li>
                 <li class="current">
                 <span>2、修改外呼内容</span>
                </li>
                <li class="last">
                 <span>3、发布任务</span>
                </li>
              </ul>
        </div>
      <div class="yz_qn" style="margin-top:10px;">
                    <div class="menu_left">
			            <h2>添加题型</h2>
			            <ul>
			               <li id="addSingle"><a style="display:block"><img src="${ctx}/flat/images/icon/single.png"/>单选题</a></li>
			               <li id="addMultiple"><a style="display:block"><img src="${ctx}/flat/images/icon/choice.png"/>多选题</a></li>
			               <li id="addQuestions"><a style="display:block"><img src="${ctx}/flat/images/icon/interlocution.png"/>问答题</a></li>
			            </ul>
			         </div>
                     <div class="cont_right sj_836">
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
				                     <dd><label class="radio"><input type="radio" readonly="readonly">${content}</label></dd>
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
			                     <dd class="wd"><textarea class="formTextarea" rows="5"></textarea></dd>
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
  <div class="buttonArea">
  	<input type="button" class="formButton_grey" value="上一步" onclick="lastStep();" />
    <input type="submit" class="formButton_green" value="下一步" onclick="save(1);" />
    <input type="button" class="formButton_grey" value="存草稿" onclick="saveTemp();"/>
    <input type="button" class="formButton_grey" value="取消" onclick="goback();"/>
  </div>
</div>
</body>
</html>