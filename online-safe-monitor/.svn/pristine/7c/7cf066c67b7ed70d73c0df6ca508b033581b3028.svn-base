<%@ page language="java" import="java.util.*"  contentType="text/html; charset=UTF-8" pageEncoding="utf-8"  %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../../../common/taglibs.jsp"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<jsp:include page="../head.jsp" />
	<title>新增应急机构</title>
    <script type="text/javascript" src="${ctx}wh/js/emergencyDepartment/addEmergency.js"></script> 
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
<input type="hidden" id="whroletype" value="${sessionScope.whroletype }">
<input type="hidden" id="emergencyId" value="${info.id }"/>
	<div class="bread-line">
		<label>当前位置：</label><a href="#">首页</a>&gt;<a href="#">应急管理</a>&gt;<s:if test="info != null && info.id != null">修改</s:if><s:else>新增</s:else>
	</div>
    <div class="formPage">
	 <div class="formbg">
        <div class="big_title"><s:if test="info != null && info.id != null">修改</s:if><s:else>新增</s:else><em>（应急机构）</em></div>
                 <form id="form1">
        <div class="content_form">
        <form id="form1">
                <table width="100%" cellspacing="0" cellpadding="0" border="0" class="inputTable" id="tabInfo">
	                         <tr>
                            <th><label><em class="requireField">*</em>机构/人名称：</label></th>
                            <td><input id="departName" maxlength="32" type="text" style="width:80%" valid="required" errmsg="emergency.departName_not_null" class="formText" value="${info.departName }"/>
                            <c:if test="${whroletype==2 }"> <span><input <c:if test="${whroletype==2&&info.departType==2 }">checked="checked"</c:if> type="checkbox" name="isDepart" value="2" />&nbsp;机构</span></c:if></td>
                        </tr>
                        <tr>
                    		<th><label><em class="requireField">*</em>联系电话：</label></th>
	                    	<td>
				            	<input id="phone" maxlength="20" style="width:80%" type="text" valid="required" errmsg="emergency.phone_not_null" class="formText" value="${info.phone }"/> 
				            </td>
                  		</tr>
                  		<tr>
                  		<th><label>集团号码：</label></th>
                  		<td>
                  		<input type="text" class="formText" style="width:80%" id="groupNum" maxlength="32" value="${info.groupNumber }"/></td>
                  		</tr>
                  		
                  		<tr>
                  		<th><label>职务：</label></th>
                  		<td>
                  		<input type="text" class="formText" id="job" style="width:80%" maxlength="32" value="${info.job }"/></td>
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
