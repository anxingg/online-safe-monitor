<%@ page language="java" import="java.util.*"  contentType="text/html; charset=UTF-8" pageEncoding="utf-8"  %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../../../common/taglibs.jsp"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<jsp:include page="../head.jsp" />
	<title>做试题</title>
    
<script type="text/javascript" src="${ctx}wh/js/exam/map.js"></script>
<script type="text/javascript" src="${ctx}wh/js/exam/examTestDetail.js"></script>
</head>
<body>
	<input type="hidden" value="${paper.id }" id="paperId" />
	<input type="hidden" value="${param.testId }" id="testId" />
	<input type="hidden" value="${param.userName }" id="userName" />
	<input type="hidden" value="${param.idcard }" id="idcard" />
    <div class="lookQue">
   	<div class="carries">
		<h1 class="bigTitle">
		<label id="paperName"></label>
			<span class="num"><label id="time"></label></span><span id="paperTime"></span>
		</h1>
		<h2 class="smalTitle" id="questionInfo">
			企业名称：<label id="companyName"></label>答题人：<label id="userNameDT"></label>身份证号：<label
				id="idcardNum"></label>
		</h2>
		</div>
    <div class="buttonArea"> 
		<input type="button" hidefocus="" id="submitExam" value="提交"
				class="formButton_green">
			<input type="button" value="取消" class="formButton_grey"  hidefocus="" onClick="javascript :history.back(-1);"/>
	</div>
	</div>
<div class="clear"></div>
</body>
</html>
