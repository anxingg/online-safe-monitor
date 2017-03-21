<%@ page language="java" import="java.util.*"  contentType="text/html; charset=UTF-8" pageEncoding="utf-8"  %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<jsp:include page="../../../common/taglibs.jsp"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<jsp:include page="../head.jsp" />
	<title>修改年度培训计划</title>
    <script type="text/javascript" src="${ctx}wh/js/training/updateTraining.js"></script> 
    <script type="text/javascript" src="${ctx}js/common/validate_form.js"></script>
	<script>
		window.UEDITOR_HOME_URL = basePath+"plugins/ueditor/";
	</script>
    <script type="text/javascript" charset="utf-8" src="${ctx}plugins/ueditor/editor_config.js"></script>
	<script type="text/javascript" charset="utf-8" src="${ctx}plugins/ueditor/editor_all_min.js"></script>
	<script type="text/javascript" src="${ctx}js/common/showError.js"></script>
	<script type="text/javascript" src="${ctx}js/logined/js_lang_cn.js"></script>
	<style type="text/css">
	.uploadify{float:left;margin-top:7px;}
	</style>
</head>
<body>
	<input type="hidden" id="trainingId" value="${param.trainingId }"/>
	<div class="bread-line">
		<label>当前位置：</label><a href="#">首页</a>&gt;<a href="#">年度培训计划管理</a>&gt;修改
	</div>
    <div class="formPage">
	 <div class="formbg">
        <div class="big_title">修改<em>（年度培训计划）</em></div>
                 <form id="form1">
        <div class="content_form">
        <form id="form1">
                <table width="100%" cellspacing="0" cellpadding="0" border="0" class="inputTable" id="tabInfo">
	                         <tr>
                            <th><em class="requireField">*</em>培训年度：</th>
                            
                                      <td><input id="trainYear" type="text" style="width: 80px" 
                            valid="required" errmsg="training.trainYear_not_null"
							class="formText Wdate"
							onclick="WdatePicker({skin:'default',dateFmt:'yyyy'})" /></td>
                        </tr>
                        <tr>
                    		<th><label><em class="requireField">*</em>培训内容：</label></th>
	                    	<td colspan="3" >
				            <div>
				                 <input id="contentInfoInput" type="hidden"/>
							     <script id="contentInfo" type="text/plain" style="width:100%"></script>
							</div>
				            </td>
	                    	<%-- <td colspan="3">
	                        <textarea class="formTextarea" rows="5" id="details" maxlength="1000" 
	                        valid="required|textareaLength" errmsg="training.details_not_null|training.details_max_length" maxNumber="1000"></textarea>
	                    	<span style="float:right">0-1000字</span>
	                    	</td> --%>
                  		</tr>
                  		<tr>
                  		<th><label><em class="requireField">*</em>负责人：</label></th>
                  		<td>
                  		<input type="text" class="formText" id="charge" maxlength="32" valid="required" errmsg="training.charge_not_null"/></td>
                  		<th><label><em class="requireField">*</em>培训时间：</label></th>
                  		<td>
                  		<input type="text" class="formText" id="trainRate" maxlength="32" valid="required" errmsg="training.trainRate_not_null"/></td>
                  		</td>
                  		</tr>
                  		
                  		<tr>
                  		<th><label><em class="requireField">*</em>培训对象：</label></th>
                  		<td>
                  		<input type="text" class="formText" id="trainObject" maxlength="32" valid="required" errmsg="training.trainObject_not_null"/></td>
                  		<th><label><em class="requireField">*</em>培训形式：</label></th>
                  		<td>
                  		<input type="text" class="formText" id="trainForm" maxlength="32" valid="required" errmsg="training.trainForm_not_null"/></td>
                  		</td>
                  		</tr>
                  		      <tr>
                    		<th><label>备注：</label></th>
	                    	<td colspan="3">
	                        <textarea class="formTextarea" rows="5" id="memo" maxlength="256" 
	                        valid="textareaLength" errmsg="training.memo_max_length" maxNumber="256"></textarea>
	                    	<span style="float:right">0-256字</span>
	                    	</td>
                  		</tr>
                  		<tr>
                        	<th>附件上传：</th>
                            <td>
								<input type="hidden" id="attachmentId" name="attachmentId" />
								<input type="hidden" id="attachName" />
								<input id="file_upload" name="fileupload" type="file" multiple="true" />
								<!-- 上传队列 -->
							    <div id="queue"  style="display:none;"></div>
						        <div class="annex">
							        <ul id="attachmentList">
							        <li style="display: none;" id="li">
							        
										<div class="icon"></div>
										<div class="txt" style="width:700px">
											<p><span id="attachName1"></span></p>
											<p><a style="cursor:pointer"  class="deleteAttachment">删除</a></p>
										</div>
									</li>
							        </ul>
						        </div>
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
