<%@ page language="java" import="java.util.*"  contentType="text/html; charset=UTF-8" pageEncoding="utf-8"  %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<jsp:include page="../../../common/taglibs.jsp"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<jsp:include page="../head.jsp" />
	<title>修改安全培训记录</title>
    <script type="text/javascript" src="${ctx}wh/js/training/updateTrainingRecord.js"></script> 
    <script type="text/javascript" src="${ctx}js/common/validate_form.js"></script>
<script type="text/javascript" src="${ctx}js/common/showError.js"></script>
<script type="text/javascript" src="${ctx}js/logined/js_lang_cn.js"></script>
</head>
<body>
<input type="hidden" id="trainingId" value="${param.trainingId }"/>
	<div class="bread-line">
		<label>当前位置：</label><a href="#">首页</a>&gt;<a href="#">安全培训记录</a>&gt;修改
	</div>
    <div class="formPage">
	 <div class="formbg">
        <div class="big_title">修改<em>（安全培训记录）</em></div>
                 <form id="form1">
        <div class="content_form">
        <form id="form1">
                <table width="100%" cellspacing="0" cellpadding="0" border="0" class="inputTable" id="tabInfo">
	                         <tr>
                            <th><em class="requireField">*</em>培训名称：</th>
                            
                                      <td><input type="text" class="formText" id="subject" maxlength="32" valid="required" errmsg="training.subject_not_null"/></td>
                        <th><label><em class="requireField">*</em>培训对象：</label></th>
                  		<td>
                  		<input type="text" class="formText" id="trainObject" maxlength="32" valid="required" errmsg="training.trainObject_not_null"/></td>
                        
                        </tr>
                        <tr>
                    		<th><label><em class="requireField">*</em>培训时间：</label></th>
	                      <td><input id="trainTime" type="text" style="width: 240px" 
                            valid="required" errmsg="training.trainTime_not_null"
							class="formText Wdate"
							onclick="WdatePicker({skin:'default',dateFmt:'yyyy-MM-dd'})" /></td>
							<th><label><em class="requireField">*</em>主讲人：</label></th>
							<td><input type="text" class="formText" id="speaker" maxlength="32" valid="required" errmsg="training.speaker_not_null"/></td>
                  		</tr>
                  		<tr>
                  		<th><label><em class="requireField">*</em>培训地点：</label></th>
                  		<td>
                  		<input type="text" class="formText" id="address" maxlength="32" valid="required" errmsg="training.address_not_null"/></td>
                  		<th><label>培训学时(数字)：</label></th>
                  		<td>
                  		<input type="text" class="formText" id="school" maxlength="3" onkeyup="this.value=this.value.replace(/[^0-9]/g,'')"/></td>
                  		</tr>
                  		<tr>
                  		<th><label>培训人数：</label></th>
                  		<td>
                  		<input type="text" class="formText" id="num" maxlength="6" onkeyup="this.value=this.value.replace(/[^0-9]/g,'')"/></td>
                  		</td>
                  		</tr>
                  		<tr>
                 <th><label><em class="requireField">*</em>培训内容：</label></th>
	                    	<td colspan="3">
	                        <textarea class="formTextarea" rows="5" id="details" maxlength="1000" 
	                        valid="required|textareaLength" errmsg="training.details_not_null|training.details_max_length" maxNumber="1000"></textarea>
	                    	<span style="float:right">0-1000字</span>
	                    	</td>
                  		</tr>
                  		      <tr>
                    		<th><label>效果评价和改进措施：</label></th>
	                    	<td colspan="3">
	                       <textarea class="formTextarea" rows="5" id="effect" maxlength="256" 
	                        maxNumber="256"></textarea>
	                    	<span style="float:right">0-256字</span>
	                    	</td>
                  		</tr>
          </table>
          </form>
        </div>
        </form>
   </div>
   
   
    <div class="buttonArea"> 
			<input type="button"class="formButton_green" value="确定" hidefocus="" onclick="updateTraining();"/>
			<input type="button" value="取消" class="formButton_grey"  hidefocus="" onClick="javascript :history.back(-1);"/>
</div>
  
  

<div class="clear"></div>
</body>
</html>
