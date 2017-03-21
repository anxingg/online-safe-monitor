<%@ page language="java" import="java.util.*"  contentType="text/html; charset=UTF-8" pageEncoding="utf-8"  %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<jsp:include page="../../../common/taglibs.jsp"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<jsp:include page="../head.jsp" />
	<title>查看年度培训计划</title>
    <script type="text/javascript" src="${ctx}wh/js/training/viewTraining.js"></script> 
    <script type="text/javascript" src="${ctx}js/common/validate_form.js"></script>
	<script type="text/javascript" src="${ctx}js/common/showError.js"></script>
	<script type="text/javascript" src="${ctx}js/logined/js_lang_cn.js"></script>
	<script type="text/javascript" src="${ctx}wh/js/safeAccident/downloadAttachment.js?version=${version}"></script>
</head>
<body>
<input type="hidden" id="trainingId" value="${param.trainingId }"/>
	<div class="bread-line">
		<label>当前位置：</label><a href="#">首页</a>&gt;<a href="#">年度培训计划管理</a>&gt;查看
	</div>
    <div class="formPage">
	 <div class="formbg">
        <div class="big_title">查看<em>（年度培训计划）</em></div>
                 <form id="form1">
        <div class="content_form">
        <form id="form1">
                <table width="100%" cellspacing="0" cellpadding="0" border="0" class="inputTable" id="tabInfo">
	                         <tr>
                            <th>培训年度：</th>
                                      <td> <span id="trainYear"></span></td>
                        </tr>
                        <tr>
                    		<th><label>培训内容：</label></th>
	                    	<td colspan="3">
	                    	<span id="details"></span></td>
	                   
                  		</tr>
                  		<tr>
                  		<th><label>负责人：</label></th>
                  		<td>
                  		 <span id="charge"></span>
                  	
                  		<th><label>培训时间：</label></th>
                  		<td>
                  		 <span id="trainRate"></span>
                  		
                  		</td>
                  		</tr>
                  		
                  		<tr>
                  		<th><label>培训对象：</label></th>
                  		<td>
                  	<span id="trainObject"></span>
                  		
                  		<th><label>培训形式：</label></th>
                  		<td>
                  	<span id="trainForm"></span>
                  	
                  		</td>
                  		</tr>
                  		      <tr>
                    		<th><label>备注：</label></th>
	                    	<td colspan="3">
	                    	<span id="memo"></span>
	              
	                    	</td>
                  		</tr>
                  		<tr>
                        	<th>附件：</th>
                            <td>
								<span id="att"></span>
                            </td>
                        </tr>
          </table>
          </form>
        </div>
        </form>
   </div>
   
   
    <div class="buttonArea"> 
			<input type="button" value="返回" class="formButton_grey"  hidefocus="" onClick="javascript :history.back(-1);"/>
</div>
  
  

<div class="clear"></div>
</body>
</html>
