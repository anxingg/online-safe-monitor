<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<jsp:include page="../head.jsp" />
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>法人信息</title>
	<!-- 验证start -->
	<script type="text/javascript" src="${ctx}js/common/validate_form.js?version=${version}"></script>
	<script type="text/javascript" src="${ctx}common/js/js_lang_cn.js?version=${version}"></script>
	<script type="text/javascript" src="${ctx}js/common/showError.js?version=${version}"></script>
	<!-- 验证end -->
	<script type="text/javascript" src="${ctx}wh/js/common.js?version=${version}"></script>
	<script type="text/javascript" src="${ctx}wh/js/company/updateLegalPerson.js?version=${version}"></script>
	<style type="text/css">
	.uploadify{float:left;margin-top:7px;}
	.clen_a{ line-height:28px; padding-left:5px;}
</style>
</head>
<body>
<div class="bread-line">
  <label>当前位置：</label><a href="#">首页</a>&gt;&nbsp;<a href="#">法人信息</a>
</div>
<div class="tabBox">
  <ul>
  	<input type="hidden" id="groupId" value='<s:property value="#session.adminUser.groupId"/>'/>
    <li><a href="${ctx}companywh/toUpdateCompany.action">企业信息</a></li>
    <li class="on"><a href="#">法人信息</a></li>
    <li><a href="${ctx}wh/logined/company/companyPhotoUpdate.jsp">企业证照</a></li>
    <li><a href="${ctx}companywh/sis_jmpPage.action?operation=2&whCompany.groupId=<s:property value="#session.adminUser.groupId"/>">安全机构管理</a></li>
  </ul>
</div>

	   		<div class="formPage">
	 <div class="formbg">
	 <div class="big_title">法人信息</div>
        <div class="content_form">
        		<form id="form1">
                <table width="100%" cellspacing="0" cellpadding="0" border="0" class="inputTable">
                    <tbody>
                        
                        <tr>
                            <th><em class="requireField">*</em>姓名：</th>
                            <td><input type="text" class="formText" id="name" maxlength="32"
                            	valid="required" errmsg="legal.name_not_null"/></td>
                            <th><em class="requireField">*</em>联系电话：</th>
                            <td><input type="text" class="formText" id="phone" 
		          	 			onkeyup="this.value=this.value.replace(/[^0-9\-]/g,'')" maxlength="12"
		          	 			valid="required|isPhoneSimple" errmsg="legal.phone_not_null|legal.phone_not_mobile"/></td>
                        </tr>
                        <tr>
                            <th>职务：</th>
                            <td><input type="text" class="formText" id="job" maxlength="32"/></td>
                            
                        </tr>
                        <tr>
                        	<th><em class="requireField">*</em>证件类型：</th>
                            <td>
                            	<select id="cardType">
                            		
                            	</select>	
                           <input type="hidden" id="hicardType" valid="required" errmsg="legal.cardType_not_null"/>
                            </td>
                        	<th><em class="requireField">*</em>证件号码：</th> 
                            <td><input type="text" class="formText" id="certificateNum" maxlength="32" 
                            	valid="required" errmsg="legal.certificateNum_not_null"/></td>
                        </tr>
                        <tr>
                        	<th>上传证件照片：</th>
                            <td>
                            <input type="hidden" id="attachmentId" name="attachmentId"/>
						    <input id="file_upload" name="fileupload" type="file" multiple="true" />
						    <!-- 上传队列 -->
						    <div id="queue"  style="display:none;"></div>
					        <div class="annex">
						        <ul id="attachmentList">
						        </ul>
					        </div>
                            <input type="hidden" class="formText" id="photo" />
                            <a href="javascript:void(0);" onclick="deleteImg();" class="clen_a">清除</a>
                            </td>
                        </tr>
                        <tr>
                        	<th></th>
                            <td colspan="2" rowspan="3">
                            	<img alt="" src="${ctx}wh/images/up.jpg" id="img" height="200px"/>
							</td>
                        </tr>
                        
                    </tbody>
                </table>
                </form>
        </div>
   </div>
    <div class="buttonArea"> 
			<input type="button" id="submit" class="formButton_green" value="确定" hidefocus=""/>
			<input type="button" value="取消" class="formButton_grey"  hidefocus="" onclick="javascript :history.back(-1);"/>
	</div>
  </div>


</body>
</html>

