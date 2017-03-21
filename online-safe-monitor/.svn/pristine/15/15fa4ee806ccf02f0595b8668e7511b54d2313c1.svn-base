<%@ page language="java" import="java.util.*"  contentType="text/html; charset=UTF-8" pageEncoding="utf-8"  %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<jsp:include page="../../../common/taglibs.jsp"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<jsp:include page="../head.jsp" />
	<title>新增试卷</title>
	<script type="text/javascript" src="${ctx}wh/js/exam/map.js"></script>
    <script type="text/javascript" src="${ctx}wh/js/exam/addExamPaper.js"></script> 
      <style type="text/css">
       .inputTable th{ width:126px;}
    </style>
</head>
<body>
	<div class="bread-line">
		<label>当前位置：</label><a href="#">首页</a>&gt;<a href="#">试卷管理</a>&gt;&nbsp;新增
	</div>
    <div class="formPage">
	 <div class="formbg">
        <div class="big_title">新增<em>（试卷）</em></div>
        <div class="content_form">
                <table width="100%" cellspacing="0" cellpadding="0" border="0" class="inputTable" id="tabInfo">
	              <tr>
	              	<th><label>试卷名称：</label></th>
	             	<td><input type="text" class="formText" id="paperName" maxlength="32"/></td>
	              </tr>
	              <tr>
	                <th><label>试卷类型：</label></th>
	                <td><select id="examType"></select></td>
	              </tr>
	              <tr>
	                <th><label>考试时长（分钟）：</label></th>
	                <td><input type="text" class="formText" id="paperTime" maxlength="4" onkeyup="testNum(this);"/></td>
	              </tr>
          </table>
           <h2 class="small_title">您选择了&nbsp;<font id="questionTotal">0</font>&nbsp;题，共&nbsp;<font id="scoreTotal">0</font>&nbsp;分</h2>
          <table id="myTable" cellpadding="0" cellspacing="0" class="pretty dataTable">
			<thead>
				<tr>
					<th class="chk" style="width:15px;"><input type="checkbox" id="total"/></th>
					<th id="title" class="longTxt">试题名称</th>
					<th id="score" style="width:90px">试题分值</th>
					<th id="questionType" style="width:130px">题目类型</th>
					<th id="titleType" style="width:130px">试题类型</th>
				</tr>
			</thead>
			<tbody></tbody>
		</table>
        </div>
   </div>
   
   
    <div class="buttonArea"> 
			<input type="button"class="formButton_green" value="确定" hidefocus="" onclick="addQuestion('add');"/>
			<input type="button" value="取消" class="formButton_grey"  hidefocus="" onClick="javascript :history.back(-1);"/>
</div>
  
  

<div class="clear"></div>
</body>
</html>
