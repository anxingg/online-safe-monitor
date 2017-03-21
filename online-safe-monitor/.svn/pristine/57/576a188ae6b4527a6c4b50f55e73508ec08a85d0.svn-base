<%@ page language="java" import="java.util.*"  contentType="text/html; charset=UTF-8" pageEncoding="utf-8"  %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../../../common/taglibs.jsp"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<jsp:include page="../head.jsp" />
	<title>试卷详情</title>
    <script type="text/javascript" src="${ctx}wh/js/exam/examPaperDetail.js"></script> 
        <style type="text/css">
       .inputTable th{ width:126px;}
    </style>
</head>
<body>
	<input type="hidden" value="${paper.id }" id="paperId" />
	<div class="bread-line">
		<label>当前位置：</label><a href="#">首页</a>&gt;<a href="#">试卷管理</a>&gt;&nbsp;查看
	</div>
    <div class="formPage">
	 <div class="formbg">
        <div class="big_title">查看<em>（试卷）</em></div>
        <div class="content_form">
                <table width="100%" cellspacing="0" cellpadding="0" border="0" class="inputTable" id="tabInfo">
	              <tr>
	              	<th><label>试卷名称：</label></th>
	             	<td>${paper.paperName }</td>
	              </tr>
	              <tr>
	                <th><label>试卷类型：</label></th>
	                <td>${examType }</td>
	              </tr>
	              <tr>
	                <th><label>考试时长：</label></th>
	                <td>${paper.paperTime }分钟</td>
	              </tr>
          </table>
          <table id="myTable" cellpadding="0" cellspacing="0" class="pretty dataTable">
			<thead>
				<tr>
					<th id="no" style="width:80px">题号</th>
					<th id="questionType" style="width:130px">题目类型</th>
					<th id="title" class="longTxt">试题名称</th>
					<th id="score" style="width:90px">试题分值</th>
					<th id="items">题目选项</th>
				</tr>
			</thead>
			<tbody></tbody>
		</table>
        </div>
   </div>
   
   
    <div class="buttonArea"> 
			<input type="button"class="formButton_grey" value="返回" hidefocus="" id="goBack"/>
	</div>
  
  

<div class="clear"></div>
</body>
</html>
