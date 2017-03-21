<%@ page language="java" import="java.util.*"  contentType="text/html; charset=UTF-8" pageEncoding="utf-8"  %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<jsp:include page="../../../common/taglibs.jsp"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<jsp:include page="../head.jsp" />

	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>考试试题</title>
	<script type="text/javascript" src="${ctx}js/common/validate_form.js"></script>
    <script type="text/javascript" src="${ctx}js/common/showError.js"></script>
    <script type="text/javascript" src="${ctx}js/logined/js_lang_cn.js"></script>
    <script type="text/javascript" src="${ctx}wh/js/exam/addExamQuestion.js"></script> 
    <script type="text/javascript" src="${ctx}wh/js/exam/jsList.js"></script>
    <style type="text/css">
       .enter{ padding:0 19px;}
       .inputTable th{ width:100px;}
    </style>
    
</head>
<body>
<form id="form1">
	<div class="bread-line">
		<label>当前位置：</label><a href="#">首页</a>&gt;<a href="#">试题</a>&gt;&nbsp;新增
	</div>
	
                 <div class="formPage">
	 <div class="formbg">
        <div class="big_title">新增<em>（试题）</em></div>
        <div class="content_form">
                <table width="100%" cellspacing="0" cellpadding="0" border="0" class="inputTable" id="tabInfo">
                 <tr>
                 <th><label>试题名称：</label></th>
                <td><input type="text" class="formText" id="title" name="title" valid="required" placeholder="请输入试题名称" errMsg="question.questionTitle_not_null" maxlength="100"/></td>
                </tr>
                <tr><th><label>试题类型：</label></th>
                <td><select id="examType"></select></td>
                </tr>
              <tr>
                <th><label>题目类型：</label></th>
                <td><label class="radio"><input type="radio" name="questionItem" value="0" checked="checked"/>单选题</label>
	                <label class="radio"><input type="radio" name="questionItem" value="1"/>多选题</label></td>
              </tr>
              
              <tr>
              <th>分值：</th>
             <td><input type="text" class="formText" id="grade" maxlength="2" onkeyup="this.value=this.value.replace(/[^0-9]/g,'')"  style="width:60px;"/></td>
              </tr>
               <tr id="answerQuestion" style="display:none">
                <th><label>试题名称：</label></th>
                <td><textarea class="formTextarea" id="otherTitle" placeholder="请输入题干"></textarea></td>
              </tr>
              
              
          </table>  
         <dl class="enter" id="add" >
                     <!-- <dt><label>试题名称：</label><input type="text" class="formText" id="title" name="title" valid="required" placeholder="请输入题干" errMsg="question.questionTitle_not_null" maxlength="50"/></dt> -->
                     <dd><label>选项<span>1</span>：</label><input type="text" class="formText" placeholder="请输入选项内容" id="item1" valid="required" errMsg="question.questionItem_not_null" maxlength="50"/><span class="oper" onclick="setAnswer(this)"  value="1">[设为正确答案]</span></dd>
                     <dd><label>选项<span>2</span>：</label><input type="text" class="formText" placeholder="请输入选项内容" id="item2" valid="required" errMsg="question.questionItem_not_null" maxlength="50"/><span class="oper"  onclick="setAnswer(this)" value="2">[设为正确答案]</span></dd>
                     <dd><label>选项<span>3</span>：</label><input type="text" class="formText" placeholder="请输入选项内容" id="item3" valid="required" errMsg="question.questionItem_not_null" maxlength="50"/><span class="close" onclick="closeItem(this)" ></span><span class="oper"  onclick="setAnswer(this)" value="3">[设为正确答案]</span></dd>
                     <dd><label>选项<span>4</span>：</label><input type="text" class="formText" placeholder="请输入选项内容" id="item4" valid="required" errMsg="question.questionItem_not_null" maxlength="50"/><span class="close" onclick="closeItem(this)" ></span><span class="oper" onclick="setAnswer(this)" value="4">[设为正确答案]</span></dd>
                     <dd class="operate" id="operateAdd"><em id="addInsertItem" onclick="insertItem('add',this)">+插入选项</em></dd>
         </dl>
 
        </div>
   </div>
   
   
    <div class="buttonArea"> 
			<input type="button"class="formButton_green" value="确定" hidefocus="" onclick="addQuestion('add');"/>
			<input type="button" value="取消" class="formButton_grey"  hidefocus="" onClick="javascript :history.back(-1);"/>
</div>
  
  

<div class="clear"></div>
</form>
</body>
</html>
