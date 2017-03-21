<%@ page language="java" import="java.util.*"  contentType="text/html; charset=UTF-8" pageEncoding="utf-8"  %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<jsp:include page="../../../common/taglibs.jsp"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<jsp:include page="../head.jsp" />
	<title>查看安全培训记录</title>
    <script type="text/javascript" src="${ctx}wh/js/training/viewTrainingRecord.js"></script> 
    <script type="text/javascript" src="${ctx}js/common/validate_form.js"></script>
<script type="text/javascript" src="${ctx}js/common/showError.js"></script>
<script type="text/javascript" src="${ctx}js/logined/js_lang_cn.js"></script>
</head>
<body>
<input type="hidden" id="trainingId" value="${param.trainingId }"/>
	<div class="bread-line">
		<label>当前位置：</label><a href="#">首页</a>&gt;<a href="#">安全培训记录</a>&gt;查看
	</div>
    <div class="formPage">
	 <div class="formbg">
        <div class="big_title">查看<em>（安全培训记录）</em></div>
                 <form id="form1">
        <div class="content_form">
        <form id="form1">
                <table width="100%" cellspacing="0" cellpadding="0" border="0" class="inputTable" id="tabInfo">
	                         <tr>
                            <th>培训名称：</th>
                            
                                      <td>
                                      <span id=subject></span>
                                     </td>
                        <th><label>培训对象：</label></th>
                  		<td>
                  		 <span id=trainObject></span>
                  </td>
                        </tr>
                        <tr>
                    		<th><label>培训时间：</label></th>
	                      <td>
	                       <span id=trainTime></span>
	                    </td>
							<th><label>主讲人：</label></th>
							<td>
							<span id=speaker></span>
					</td>
                  		</tr>
                  		<tr>
                  		<th><label>培训地点：</label></th>
                  		<td>
                  		<span id=address></span>
                  		</td>
                  		<th><label>培训学时：</label></th>
                  		<td>
                  		<span id=school></span>
                  		</td>
                  		</td>
                  		</tr>
                  		<tr>
                  		<th><label>培训人数：</label></th>
                  		<td>
                  		<span id=num></span>
                  		</td>
                  		</tr>
                  		<tr>
                 <th><label>培训内容：</label></th>
	                    	<td colspan="3">
	                    	<span id=details></span>
	                    	</td>
                  		</tr>
                  		      <tr>
                    		<th><label>效果评价和改进措施：</label></th>
	                    	<td colspan="3">
	                    	<span id=effect></span>
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
