<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<jsp:include page="../head.jsp" />
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>企业证照</title>
	<script type="text/javascript" src="${ctx}wh/js/company/companyPhotoUpdate.js?version=${version}"></script>
	<style type="text/css">
	.uploadify{float:left;margin-top:7px;}
	.clen_a{ line-height:28px; padding-left:5px;}
	</style>
</head>
<body>
<div class="bread-line">
  <label>当前位置：</label><a href="#">首页</a>&gt;&nbsp;<a href="#">企业证照</a>
</div>
<div class="tabBox">
  <ul>
    <li><a href="${ctx}companywh/toUpdateCompany.action">企业信息</a></li>
    <li><a href="${ctx}wh/logined/company/updateLegalPerson.jsp">法人信息</a></li>
    <li class="on"><a href="#">企业证照</a></li>
    <li><a href="${ctx}companywh/sis_jmpPage.action?operation=2&whCompany.groupId=<s:property value="#session.adminUser.groupId"/>">安全机构管理</a></li>
  </ul>
</div>

	<input type="hidden" id="groupId" value='<s:property value="#session.adminUser.groupId"/>'/>
   <div class="formPage">
	   <div class="formbg">
	        <div class="big_title">企业证照</div>
	        <div class="content_form">
	         <table width="100%" cellspacing="0" cellpadding="0" border="0" class="inputTable">
                <tbody>
				  <tr>
                    <th rowspan="2"><label>安全生产许可证正本：</label></th>
                    <td>
                    <input type="hidden" id="attachmentId1" name="attachmentId1"/>
                	<!-- 上传队列 -->
					<div id="queue"  style="display:none;"></div>
                	<input id="file_upload1" name="fileupload1" type="file" multiple="true" />
	        		
                    <a href="javascript:void(0);" onclick="deleteImg(1);" class="clen_a">清除</a>
                    </td>
                  </tr>
				   <tr>
                    <td>
                    <img alt="" src="${ctx}wh/images/up.jpg" id="img1" height="200px"/>
                    </td>
                  </tr>
				  <tr>
                    <th rowspan="2"><label>安全生产许可证副本：</label></th>
                    <td>
                    <input type="hidden" id="attachmentId2" name="attachmentId2"/>
                	<!-- 上传队列 -->
					<div id="queue"  style="display:none;"></div>
                	<input id="file_upload2" name="fileupload2" type="file" multiple="true" />
	        	
                    <a href="javascript:void(0);" onclick="deleteImg(2);" class="clen_a">清除</a>
                    </td>
                  </tr>
				   <tr>
                    <td>
                    <img alt="" src="${ctx}wh/images/up.jpg" id="img2" height="200px"/>
                    </td>
                  </tr> 
                  <tr>
                    <th rowspan="2"><label>危险化学用品登记证：</label></th>
                    <td>
                    <input type="hidden" id="attachmentId3" name="attachmentId3"/>
                	<!-- 上传队列 -->
					<div id="queue"  style="display:none;"></div>
                	<input id="file_upload3" name="fileupload3" type="file" multiple="true" />
	        	
                    <a href="javascript:void(0);" onclick="deleteImg(3);" class="clen_a">清除</a>
                    </td>
                  </tr>
				   <tr>
                    <td>
                    <img alt="" src="${ctx}wh/images/up.jpg" id="img3" height="200px"/>
                    </td>
                  </tr>  
                  <tr>
                    <th rowspan="2"><label>三级标准化达标证：</label></th>
                    <td>
                    <input type="hidden" id="attachmentId4" name="attachmentId4"/>
                	<!-- 上传队列 -->
					<div id="queue"  style="display:none;"></div>
                	<input id="file_upload4" name="fileupload4" type="file" multiple="true" />
	        	
                    <a href="javascript:void(0);" onclick="deleteImg(4);" class="clen_a">清除</a>
                    </td>
                  </tr>
				   <tr>
                    <td>
                    <img alt="" src="${ctx}wh/images/up.jpg" id="img4" height="200px"/>
                    </td>
                  </tr> 
                 
                  <tr>
                    <th rowspan="2"><input type="text" id="photoName5" class="formText" placeholder="自定义证件名称" style="width: 140px;" maxlength="32"/>：</th>
                    <td>
                    <input type="hidden" id="attachmentId5" name="attachmentId5"/>
                	<!-- 上传队列 -->
					<div id="queue"  style="display:none;"></div>
                	<input id="file_upload5" name="fileupload5" type="file" multiple="true" />
	        	
                    <a href="javascript:void(0);" onclick="deleteImg(5);" class="clen_a">清除</a>
                    </td>
                  </tr>
				   <tr>
                    <td>
                    <img alt="" src="${ctx}wh/images/up.jpg" id="img5" height="200px"/>
                    </td>
                  </tr>  
                  
                   <tr>
                    <th rowspan="2"><input type="text" id="photoName6" class="formText" placeholder="自定义证件名称" style="width: 140px;" maxlength="32"/>：</th>
                    <td>
                    <input type="hidden" id="attachmentId6" name="attachmentId4" />
                	<!-- 上传队列 -->
					<div id="queue"  style="display:none;"></div>
                	<input id="file_upload6" name="fileupload6" type="file" multiple="true" />
	        	
                    <a href="javascript:void(0);" onclick="deleteImg(6);" class="clen_a">清除</a>
                    </td>
                  </tr>
				   <tr>
                    <td>
                    <img alt="" src="${ctx}wh/images/up.jpg" id="img6" height="200px"/>
                    </td>
                  </tr>             
                 </tbody>
            </table>
	        	
               	
			</div>
		</div>
	   
   </div>

</body>
</html>

