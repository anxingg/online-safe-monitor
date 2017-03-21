<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/ninclude/import.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>应急演练</title> 
<%@include file="/ninclude/title.jsp"%>
</head>
<body>
<div class="bread-line">
    <label>当前位置：</label><a href="javascript:void(0);">首页</a>&gt;<a href="javascript:void(0);">应急演练</a>&gt;&nbsp;应急演练详情
</div>

                 <div class="formPage">
   <div class="formbg">
        <div class="big_title">详情档案</div>
        <div class="content_form">
        		<form name="addForm" id="addForm" method="post">
               <table width="100%" cellspacing="0" cellpadding="0" border="0" class="inputTable">
                    <tbody>
                    	<tr>
                            <th>应急预案：</th>
                            <td>
                            	${whContingencyPlansExe.plan_no }
                            </td>
                            <th>演练组织部门：</th>
                            <td>${whContingencyPlansExe.organize_group }</td>
                        </tr>
                        <tr>
                            <th>应急演练名称：</th>
                            <td>${whContingencyPlansExe.exercise_name }</td>
                            <th>应急演练项目：</th>
                            <td>${whContingencyPlansExe.exercise_program }</td>
                        </tr>
                        <tr>
                            <th>演练目的：</th>
                            <td colspan="3">
                            	${whContingencyPlansExe.exercise_purpose}
                            </td>
                        </tr>
                          
                        <tr>
                           <th>演练时间：</th>
                           <td colspan="3"><fmt:formatDate value="${whContingencyPlansExe.exercise_time}" type="date"/>  </td>
                         </tr>
                       <tr>
                           <th>演练地点：</th>
                           <td colspan="3">${whContingencyPlansExe.exercise_address}</td>
                         </tr>
                       
                       
                        <tr>
                            <th>参演人员：</th>
                            <td colspan="3">
                            	${whContingencyPlansExe.exercise_people}
                            </td>
                        </tr>
                        
                         <tr>
                            <th>演练记录：</th>
                            <td colspan="3">
                            	${whContingencyPlansExe.exercise_records}
                            </td>
                        </tr>
                         <tr>
                            <th>现场救援讲评：</th>
                            <td colspan="3">
                            	${whContingencyPlansExe.rescue_reviews}
                            </td>
                        </tr>
                         <tr>
                           <th>现场救援讲评负责人：</th>
                           <td colspan="3">
                           ${whContingencyPlansExe.reviews}
                           </td>
                         </tr>
                         <tr>
                         	<th></th>
                         	<td>
			                    <input type="hidden" id="attachmentId" name="attachmentId" value="<s:property value="whContingencyPlansExe.attachment_ids"/>"/>
			                	<!-- <input id="file_upload" name="fileupload" type="file" multiple="true" /> -->
			                    <!-- 上传队列 -->
								<div id="queue"  style="display:none;"></div>
			                	<div class="imgUpload">
		                          <ul id="attachmentList">
		                          	<s:if test="fileList!=null && !fileList.isEmpty()">
		                          		<s:iterator  value="fileList" id="file" >
							           		<li id="${file.id }_li"><img src="${downPath }upload/${file.attachFile}" /><span style="display: none;" class="close" onclick="deleteImg(${file.id })"></span></li>
							           </s:iterator>  
		                          	</s:if>
		                          </ul>
		                         </div>
		                    </td>
                    	</tr>
                    </tbody>
                </table>
                </form>
        </div>
   </div>
    <div class="buttonArea"> 
			<input type="button" class="formButton_grey" value="返回" onclick="javascript:history.go(-1);"/>
</div>
   </div>
<div class="clear"></div>
</body>
</html>
