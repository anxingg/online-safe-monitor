<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/ninclude/import.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>应急演练</title> 
<%@include file="/ninclude/title.jsp"%>
<script type="text/javascript" src="${ctx}wh/js/js_lang_cn.js"></script>
<script type="text/javascript" >
	var _plan_no = "${whContingencyPlansExe.plan_id}";
	var _vid = "${whContingencyPlansExe.vid}";
</script>
<script type="text/javascript" src="${ctx}wh/js/yjyl/update.js"></script>
</head>
<body>
<div class="bread-line">
    <label>当前位置：</label><a href="javascript:void(0);">首页</a>&gt;<a href="javascript:void(0);">应急演练</a>&gt;&nbsp;应急演练更新
</div>
                 <div class="formPage">
   <div class="formbg">
        <div class="big_title">更新档案</div>
        <div class="content_form">
        		<form name="addForm" id="addForm" method="post">
                <table width="100%" cellspacing="0" cellpadding="0" border="0" class="inputTable">
                    <tbody>
                    	<tr>
                            <th><em class="requireField">*</em>应急预案：</th>
                            <td>
                            	<select id="plan_id" valid="required" errmsg="yjyl.yjyl_plan_id_valid">
                            		<option value="">请选择</option>
                            	</select>
                            </td>
                            <th><em class="requireField">*</em>演练组织部门：</th>
                            <td><input type="text" id="organize_group" maxlength="32" valid="required" errmsg="yjyl.yjyl_organize_group_not_null" class="formText" value="${whContingencyPlansExe.organize_group }"/></td>
                        </tr>
                        <tr>
                            <th><em class="requireField">*</em>应急演练名称：</th>
                            <td><input type="text" id="exercise_name" maxlength="32" valid="required" errmsg="yjyl.yjyl_exercise_name_not_null" class="formText" value="${whContingencyPlansExe.exercise_name }"/></td>
                            <th><em class="requireField">*</em>应急演练项目：</th>
                            <td><input type="text" id="exercise_program" maxlength="32" valid="required" errmsg="yjyl.yjyl_exercise_program_not_null" class="formText" value="${whContingencyPlansExe.exercise_program }"/></td>
                        </tr>
                        <tr>
                            <th><em class="requireField">*</em>演练目的：</th>
                            <td colspan="3">
                            	<textarea valid="required" errmsg="yjyl.yjyl_exercise_purpose_not_null"  maxlength="256" class="formTextarea" rows="3" id="exercise_purpose" value="">${whContingencyPlansExe.exercise_purpose }</textarea>
                            </td>
                        </tr>
                          
                        <tr>
                           <th><em class="requireField">*</em>演练时间：</th>
                           <td colspan="3"><input type="text" value="<fmt:formatDate value="${whContingencyPlansExe.exercise_time}" type="date"/>" onfocus="WdatePicker({skin:'default',dateFmt:'yyyy-MM-dd'})" class="formText Wdate" id="exercise_time"  /></td>
                         </tr>
                       <tr>
                           <th><em class="requireField">*</em>演练地点：</th>
                           <td colspan="3"><input valid="required" maxlength="64" errmsg="yjyl.yjyl_exercise_address_not_null" type="text" value="${whContingencyPlansExe.exercise_address }" class="formText" id="exercise_address"  /></td>
                         </tr>
                       
                       
                        <tr>
                            <th><em class="requireField">*</em>参演人员：</th>
                            <td colspan="3">
                            	<textarea valid="required" errmsg="yjyl.yjyl_exercise_people_not_null"  maxlength="256" class="formTextarea" rows="3" id="exercise_people" value="">${whContingencyPlansExe.exercise_people }</textarea>
                            </td>
                        </tr>
                        
                         <tr>
                            <th><em class="requireField">*</em>演练记录：</th>
                            <td colspan="3">
                            	<textarea valid="required" errmsg="yjyl.yjyl_exercise_records_not_null"  maxlength="256" class="formTextarea" rows="3" id="exercise_records" value="">${whContingencyPlansExe.exercise_records }</textarea>
                            </td>
                        </tr>
                         <tr>
                            <th><em class="requireField">*</em>现场救援讲评：</th>
                            <td colspan="3">
                            	<textarea valid="required" errmsg="yjyl.yjyl_rescue_reviews_not_null"  maxlength="256" class="formTextarea" rows="3" id="rescue_reviews" value="">${whContingencyPlansExe.rescue_reviews }</textarea>
                            </td>
                        </tr>
                         <tr>
                           <th><em class="requireField">*</em>现场救援讲评负责人：</th>
                           <td colspan="3"><input valid="required" maxlength="32" errmsg="yjyl.yjyl_reviews_not_null" type="text" value="${whContingencyPlansExe.reviews }" class="formText" id="reviews"  /></td>
                         </tr>
                         <tr>
                           <th>图片压缩工具下载：</th>
                           <td colspan="3">
                           	<a href="${ctx }down/compress.rar">图片压缩工具</a>
                           </td>
                         </tr>
                         <tr>
                         	<th></th>
                         	<td>
			                    <input type="hidden" id="attachmentId" name="attachmentId" value="<s:property value="whContingencyPlansExe.attachment_ids"/>"/>
			                	<input id="file_upload" name="fileupload" type="file" multiple="true" />
			                    <!-- 上传队列 -->
								<div id="queue"  style="display:none;"></div>
			                	<div class="imgUpload">
		                          <ul id="attachmentList">
		                          	<s:if test="fileList!=null && !fileList.isEmpty()">
		                          		<s:iterator  value="fileList" id="file" >
							           		<li id="${file.id }_li"><img src="${downPath }upload/${file.attachFile}" /><span class="close" onclick="deleteImg(${file.id })"></span></li>
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
			<input type="button" class="formButton_green" value="确定" id="save"/>
			<input type="button" value="取消" class="formButton_grey"  onclick="javascript:history.go(-1);"/>
</div>
   </div>
<div class="clear"></div>
</body>
</html>
