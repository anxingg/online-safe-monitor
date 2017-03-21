<!-- 外呼任务-执行/重呼 -->
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:include page="../../common/taglibs.jsp" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>外呼任务-执行</title>
<jsp:include page="../../common/head.jsp" />
<link href="${ctx}common/css/reset.css" rel="stylesheet" type="text/css" />
<link href="${ctx}common/css/task.css" rel="stylesheet" type="text/css" />
<link href="${ctx}common/css/Reminder.css" rel="stylesheet" type="text/css" />
<link href="${ctx}common/plugins/form/skins/form_default.css" rel="stylesheet" type="text/css" />
<link href="${ctx}common/plugins/datatable/skins/datatable_default.css" rel="stylesheet" type="text/css" />
<%-- <script type="text/javascript" src="${ctx}common/js/base.js"></script>
<script type="text/javascript" src="${ctx}plugins/datatable/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="${ctx}common/plugins/datatable/selecedForDatatablePagination.js"></script>
<script type="text/javascript" src="${ctx}common/plugins/dialog/artDialog.js?skin=default"></script>
<script type="text/javascript" src="${ctx}common/plugins/dialog/iframeTools.js"></script>
<script type="text/javascript" src="${ctx}js/common/placeholder.js"></script> --%>
<!-- 引用js -->
<script type="text/javascript" src="${ctx}js/logined/outCallTask/outCallTaskCall.js"></script>

<!-- 引用了通讯录列表中的发送短信 -->
<%-- <script type="text/javascript" src="${ctx}hotline/addressbook/js/sendMsg.js?version=${version}"></script> --%>

<style type="text/css">
.inputTable th{width:90px;}
</style>

<script>
var intervalTime = null;
var time;
function startTiming(filePath) {
    time = 0;
     
    $("#filePath").val(filePath);
	// 设置通话时长
	intervalTime = window.setInterval(setCallTime, 1000);
}

function setCallTime() {
    var a = $("#callLength");
    a.empty();
    time = time + 1;
    a.append(formartTime(time));
}

function formartTime(time) {
    var formatString = "";
    var result = time;
    var tempNum = 0;
    if (result >= 3600) {
	    tempNum = Math.floor(time / 3600);
	    result = time % 3600;
	    if (tempNum < 10) {
		    formatString = formatString + "0" + tempNum + ":";
	    } else {
		    formatString = formatString + tempNum + ":";
	    }
    } else {
	    result = time;
	    formatString = "00:";
    }

    if (result >= 60) {
	    tempNum = Math.floor(time / 60);
	    result = time % 60;
	    if (tempNum < 10) {
		    formatString = formatString + "0" + tempNum + ":";
	    } else {
		    formatString = formatString + tempNum + ":";
	    }
    } else {
	    result = time;
	    formatString = formatString + "00:";
    }
    if (result < 10) {
	    formatString = formatString + "0" + result;
    } else {
	    formatString = formatString + result;
    }
    return formatString;
}

function handUp(){
    //alert("进来了2");
    // 关闭定时器
    clearInterval(intervalTime);

 }
 
 /**
 * 获取服务器时间
 */
var nowLong = 0;
function currentTimeNew(){
	$.ajax({
		url:basePath+"systemtime/getSystemTime.action",
		type:"post",
		dataType:'text',
		success:function(data){
			var jsonObj = eval( '(' + data + ')' );
			nowLong = jsonObj.currentTimeMillis;
			getNowBeg();
		}
	});
};
 
 function getNowBeg() {
 	var nowBeg;
 	var myDate = new Date(nowLong);
 	var year = myDate.getFullYear();
 	var month = myDate.getMonth() + 1;
 	var day = myDate.getDate();
 	var hours = myDate.getHours();
 	var minutes = myDate.getMinutes();
 	var second = myDate.getSeconds();

 	if (month < 10) {
 		month = "0" + month;
 	}
 	if (day < 10) {
 		day = "0" + day;
 	}
 	if (hours < 10) {
 		hours = "0" + hours;
 	}
 	if (minutes < 10) {
 		minutes = "0" + minutes;
 	}
 	if (second < 10) {
 		second = "0" + second;
 	}
 	nowBeg = year + "-" + month + "-" + day + " " + hours + ":" + minutes + ":" + second;
 	$("#outCallTime").html(nowBeg);
 }
</script>




</head>
<body>
<!-- 任务总数 -->
<input type="hidden" id="taskTotal" value="${pt.taskTotal }" />
<!-- 已回访的任务数 -->
<input type="hidden" id="taskCallCount" value="${pt.taskCallCount }" />
<!-- 任务总数 - 已回访的任务数 -->
<input type="hidden" id="surplusTasks" value="<s:property value="pt.taskTotal-pt.taskCallCount" />" />
<!-- 任务的id -->
<input id="vid" type="hidden" value="${pt.vid }"/>
<!-- 用户任务的id -->
<input id="phoneUserid" type="hidden" value="${ptu.vid}"/>
<!-- 试卷id -->
<input id="questionnaireId" type="hidden" value="${pt.questionnaire.id}"/>
<!-- 试卷用户id -->
<input id="questionnaireUserId" type="hidden" value="${ptu.surveyUser.id}"/>
<!-- 问题的条数 -->
<input id="questionSize" type="hidden" value="<s:property value='pt.questionnaire.questions.size'/>" />
<!-- 判断是新建的问题或是重呼的提交 -->						
<s:if test="phoneUserid!=null&&phoneUserid!=''&&phoneUserid!=0">
	<!-- 重呼是2，不是重呼是1 -->
	<input type="hidden" name="checkRepeatOrNewCallSubmit" id="checkRepeatOrNewCallSubmit" value="2" />
</s:if>
<s:else>
	<input type="hidden" name="checkRepeatOrNewCallSubmit" id="checkRepeatOrNewCallSubmit" value="1" />
</s:else>
<!-- 结果查看页面id -->
<input id="viewPageId" type="hidden" value="${param.viewPageId }"/>
<div class="formPage">
  <div class="formbg">
  <div class="big_title">外呼任务</div>
    <div class="content_form">
	    <%-- <table border="0" cellpadding="0" cellspacing="0" class="titleTable">
		    <tr>
		      <td style="width:370px;"><label>外呼号码：<span class="orange">${ptu.phone }&nbsp;&nbsp;${mobileArea}</span></label></td>
		      <td style="width:350px;"><label>外呼时间：&nbsp;<span id="outCallTime"></span></label></td>
		      <td style="width:300px;" >通话时长：&nbsp;<label  id="callLength"></label></td>
		    </tr>
	    </table> --%>
	    <span style="display: none;"  id="outCallTime"></span>
	    <span style="display: none;" id="callLength"></span>
    	<h3 class="small_title">用户信息</h3>
      <table width="100%" border="0" cellpadding="0" cellspacing="0"  class="inputTable">
        <tr>
          <th><label>客户姓名：</label></th>
          <td>${ptu.userName}</td>
          <th><label>联系电话：</label></th>
          <td>
          	<s:if test="ptu.phone!=null&&ptu.phone!=''">
          		<span id="callPhone">${ptu.phone}</span><a class="tel" href="javascript:outCallPhone()"></a>
          		<%-- <a href="javascript:sendMessage(${ptu.phone})" class="message"></a> --%>
          	</s:if>
          </td>
        </tr>
        <tr>
          <th><label>备用号码：</label></th>
          <td>
          	<s:if test="ptu.telephone!=null&&ptu.telephone!=''">
	          	<span id="callPhoneTwo">${ptu.telephone}</span><em class="tel" onclick="outCallTelephone()" />
          	</s:if>
          </td>
          <th><label>客户类别：</label></th>
          <td>
          	<s:if test="ptu.userType==1">一般客户</s:if>
          	<s:elseif test="ptu.userType==2">VIP客户</s:elseif>
          </td>
        </tr>
        <tr>
          <th><label>居住地：</label></th>
          <td colspan="3">${ptu.address}</td>
        </tr>
      </table>
      <h3 class="small_title">
      	<span class="pr20">回访结果</span>
      	<label class="radio" id="result"><input checked="checked" type="radio" name="jietong" value="1" />接通</label>
      	<label id="result2" class="radio"><input type="radio" name="jietong" value="2" />未接通</label>
      </h3>
      <div class="choice">
      	<s:iterator value="pt.questionnaire.questions" id="question" status="pql">
      		<input type="hidden" name='question<s:property value="#pql.index+1" />' value='<s:property value="#question.id"/>' />
      		<input type="hidden" name='questionType<s:property value="#pql.index+1" />' value='<s:property value="#question.typeId"/>'/>
      	   <dl>
             <dt>
             	<s:property value="#pql.index+1" />、<s:property value="#question.name" />
             	<font>
             		<s:if test="#question.typeId==1">[单选]</s:if>
             		<s:if test="#question.typeId==2">[多选]</s:if>
             		<s:if test="#question.typeId==3">[问答]</s:if>
             	</font>
             </dt>
             <s:if test="#question.typeId==1">
           		<s:iterator value="#question.items" id="items" status="pqi">
	           		<dd>
		             	<label class="radio">
		              		<input type="radio" id='answer<s:property value="#pql.index+1" />'
		              			 name='answer<s:property value="#pql.index+1" />' value='<s:property value="#items.id" />'/>
		              			<s:property value="#items.content" />
		             	</label>
	               </dd>
           	   </s:iterator>
             </s:if>
             <s:elseif test="#question.typeId==2">
              	<s:iterator value="#question.items" id="items" status="pqi">
	            	<dd>
		               	<label class="radio">
		               		<input type="checkbox" id='answer<s:property value="#pql.index+1" />'
			               		 name='answer<s:property value="#pql.index+1" />' value='<s:property value="#items.id" />'/>
			               		<s:property value="#items.content" />
		               	</label>
	              </dd>
              </s:iterator>
             </s:elseif>
             <s:elseif test="#question.typeId==3">
             	<dd class="wd">
             		<textarea class="formTextarea" rows="5" id='answer<s:property value="#pql.index+1" />'
             		 name='answer<s:property value="#pql.index+1" />'></textarea>
             	</dd>
             </s:elseif>
          </dl>
         </s:iterator>
      </div>
		<div class="cont_addr" style="display:none">
			<em class="requireField">*</em><label>未接通原因：</label>
				<%-- <select id="notJieTong" name="notJieTong">
					<option value="">请选择</option>
					<option value="2">无人接听</option>
					<option value="3">电话正忙</option>
					<option value="4">呼叫转移</option>
					<option value="5">停机</option>
					<option value="6">关机</option>
					<option value="7">空号</option>
				</select> --%>
				<select id="notJieTong"  name="notJieTong" >
					<option value="">请选择</option>
					<option value="2">无人接听</option>
					<option value="3">电话正忙</option>
					<!-- <option value="3">电话秘书</option> -->
					<option value="4">呼叫转移</option>
					<option value="5">电话关机</option>
					<option value="6">电话停机</option>
					<option value="7">空号</option>
					<option value="8">用户拒访</option>
				</select>
		</div>				  
    </div>
  </div>
  <div class="buttonArea">
  	<input type="button" class="formButton_green" value="提交结果" id="submitbtn"/>
  	<s:if test="phoneUserid!=null&&phoneUserid!=''&&phoneUserid!=0">
  	</s:if>
  	<s:else>
  		<input type="button" class="formButton_grey" value="呼叫下一个"  id="nextbtn"/>
  	</s:else>
  </div>
</div>
</body>
</html>
