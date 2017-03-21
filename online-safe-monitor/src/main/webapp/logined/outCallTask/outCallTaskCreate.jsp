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
<link href="${ctx}common/plugins/Accormenus/skins/Accormenus_default.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="${ctx}common/plugins/peopleTree/skins/tree_default.css" type="text/css"/>
<jsp:include page="../../common/head.jsp" />

<!-- 这个js用来统计textarea 文本框中的字符数 -->
<script type="text/javascript" src="${ctx}common/js/CheckTextarea.js?version=${version}"></script>

<script type="text/javascript" src="${ctx}common/js/add.js?version=${version}"></script>
<!-- 表单验证 开始 -->
<script type="text/javascript" src="${ctx}js/common/showError.js?version=${version}"></script>
<!-- 表单验证 结束 -->
<script type="text/javascript" src="${ctx}js/common/ajaxfileupload.js?version=${version}"></script>
<!-- 选择人员的js -->
<script type="text/javascript" src="${ctx}js/logined/outCallTask/selectSeatUser.js?version=${version}"></script>
<!-- 这个页面的js -->
<script type="text/javascript" src="${ctx}js/logined/outCallTask/importPhoneTaskUser.js?version=${version}"></script>
<script type="text/javascript" src="${ctx}js/logined/outCallTask/outCallTaskCreate.js?version=${version}"></script>

<style type="text/css">
.inputTable th {
	width: 82px;
}
#pla label{margin-top:-2px !important;}
.formTextarea2{min-height:70px;padding:8px 10px;border:1px solid #e4e4e4;color:#555555;text-indent:10px;background:#fff;text-indent:10px;vertical-align:middle; line-height:22px;}
</style>
</head>
<body>
<form id="addroomtable" name="addroomtable">
<input type="hidden" id="phoneTaskId" name="phoneTaskId" value="<%=request.getParameter("phoneTaskId")==null?"":request.getParameter("phoneTaskId")%>"/>
<input  type="hidden" id="type" name="type" value="<%=request.getParameter("type")%>"/>
<div class="formPage ">
  <div class="formbg">
    <div class="content_form">
    <div class="flow-steps mt10">
           <ul>
              <li class="current">
             <span class="first">1、新增外呼任务</span>
            </li>
             <li>
             <span>2、编辑外呼内容</span>
            </li>
            <li class="last">
             <span>3、发布任务</span>
            </li>
          </ul>
    </div>
      <table class="inputTable" cellspacing="0" cellpadding="0">
        <tr>
          <th><em class="requireField">*</em><label>任务名称：</label></th>
          <td colspan="3" style="position: relative;" id="pla"><input  class="formText searchkey" type="text" id="title" maxlength="25" placeholder="请输入任务名称（25个字以内）" valid="required" errmsg="outCallTask.title_not_null"/></td>
        </tr>
        <tr>
          <th><em class="requireField">*</em><label>任务类型：</label></th>
          <td colspan="3">
          	<select id="taskType" name="" valid="required" errmsg="outCallTask.taskType_not_null">
          		<option value="">请选择</option>
          		<!-- <option value="1">三包单回访</option>
          		<option value="2">其他回访</option> -->
          	</select>
          </td>
        </tr>
        <tr>
          <th><label>任务说明：</label></th>
          <td colspan="3">
          		<textarea class="formTextarea2 area area01" id="des" rows="4" cols="123" name="" 
          		  		 fmaxlength="1000" ></textarea>
          		 <span class="msg-text" style="float:right">0-1000字</span>
          </td>
        </tr>
        <tr>
          <th><em class="requireField">*</em><label>执行坐席：</label></th>
          <td colspan="3"><div class="td_person fl" style="width:90.5%">
                          <ol id="seatUserNames">
                                  
                          </ol>
                  </div>
                  <input type="hidden" value="" id="seatUserIds" />
            <span class="addMember pt5"><a href="javascript:void(0)" class="icon_add" id="addSeat">添加</a><a href="javascript:void(0)" id="clearSeat" class="icon_clear">清空</a></span>
            <div class="clear"></div>
  			<p style="padding-right:80px;"><span class="tip fr">共添加了<font class="red" id="seatUserIdsCount">0</font>个坐席</span></p>
  			</td>
        </tr>
        <tr>
          <th><em class="requireField">*</em><label>外呼对象：</label></th>
          <td colspan="3"><div class="td_person fl" style="width:90.5%">
                          <ol id="outCallPeopleNames">
                                  
                          </ol>
                  </div>
                  <!-- crmUserIds, 从CRM中读取的外呼对象 -->
                  <input type="hidden" id="crmUserIds" />
                  <!-- exportUserIds, 导入的外呼对象 -->
				  <input type="hidden"  id="exportUserIds" />
                  <!-- taskUserIds, 已经保存过的外呼对象 -->
				  <input type="hidden" value="" id="taskUserIds" />
				  <!-- <input type="hidden" id="phoneTaskUser" valid="required" errmsg="outCallTask.phoneTaskUser_not_null"/> -->
              <span class="addMember pt5"><a id="addPeople" href="javascript:void(0)" class="icon_add">添加</a><a href="javascript:void(0)" id="clearTaskUser" class="icon_clear">清空</a></span>
              <div class="clear"></div>
  			  <p style="padding-right:80px;"><a href="javascript:void(0)" id="import">导入</a><span class="tip">添加系统外人员</span><span class="tip fr">共添加了<font class="red" id="outCallPeopleCount">0</font>个人</span></p>
              </td>
             </tr>
      </table>
    </div>
  </div>
  <div class="buttonArea">
    <input type="button" class="formButton_grey" value="存草稿"  id="saveTemp"/>
    <input type="button" class="formButton_green" value="下一步"  id="nextStep"/>
    <input type="button" class="formButton_grey" value="取消" onclick="goback();"/>
  </div>
</div>
</form>
<script> 
// setTimeout(function(){
// 	funPlaceholder(document.getElementById("title"));
// 	funPlaceholder(document.getElementById("des"));
// },200);
</script>
</body>
</html>