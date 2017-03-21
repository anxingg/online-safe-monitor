<%@ page language="java" import="java.util.*"  contentType="text/html; charset=UTF-8" pageEncoding="utf-8"  %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<jsp:include page="../../../common/taglibs.jsp"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<jsp:include page="../head.jsp" />
	<title>修改工艺流程</title>
    <script type="text/javascript" src="${ctx}wh/js/process/processUpdate.js"></script> 
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
<input type="hidden" id="infoId" value="${param.infoId }"/>
	<div class="bread-line">
		<label>当前位置：</label><a href="#">首页</a>&gt;<a href="#">工艺流程管理</a>&gt;修改
	</div>
    <div class="formPage">
	 <div class="formbg">
        <div class="big_title">修改<em>（工艺流程）</em></div>
                 <form id="form1">
        <div class="content_form">
        <form id="form1">
                <table width="100%" cellspacing="0" cellpadding="0" border="0" class="inputTable" id="tabInfo">
                        <tr>
	                  		<th><label><em class="requireField">*</em>工艺名称：</label></th>
	                  		<td>
	                  			<input type="text" class="formText" id="title" maxlength="100" valid="required" errmsg="process.processName_not_null"/></td>
                  		</tr>
                        <tr>
                    		<th><label><em class="requireField">*</em>工艺介绍：</label></th>
	                    	<td>
				            <div>
				                 <input id="contentInfoInput" type="hidden"/>
							     <script id="contentInfo" type="text/plain" style="width:100%"></script>
							</div>
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
			<input type="button"class="formButton_green" value="确定" hidefocus="" id="save"/>
			<input type="button" value="取消" class="formButton_grey"  hidefocus="" onClick="javascript :history.back(-1);"/>
</div>
  
  

<div class="clear"></div>
</body>
</html>
