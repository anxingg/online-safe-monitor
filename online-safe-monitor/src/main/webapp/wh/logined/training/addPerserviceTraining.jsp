<%@ page language="java" import="java.util.*"  contentType="text/html; charset=UTF-8" pageEncoding="utf-8"  %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../../../common/taglibs.jsp"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<jsp:include page="../head.jsp" />
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>岗前三级培训新增</title>
	<link href="${ctx}wh/plugins/datatable/skins/datatable_default.css?version=${version}" rel="stylesheet" type="text/css"/>
	<!-- 表示验证 start -->
	<script type="text/javascript" src="${ctx}js/common/validate_form.js"></script>
	<script type="text/javascript" src="${ctx}js/common/showError.js"></script>
	<script type="text/javascript" src="${ctx}common/js/js_lang_cn.js"></script>
	<!-- 表示验证 end -->
	<!-- 限制textarea框的最大长度 start -->
	<script type="text/javascript" src="${ctx}wh/js/safeAccident/behaviour_min.js"></script>
	<script type="text/javascript" src="${ctx}wh/js/safeAccident/textarea_maxlen_min.js"></script>
	<!-- 限制textarea框的最大长度 end -->
	<script type="text/javascript" src="${ctx}wh/js/training/addPerserviceTraining.js"></script>
    <style type="text/css">
	.uploadify{float:left;margin-top:7px;}
	.clen_a{ line-height:28px; padding-left:5px;}
	</style>
</head>
<body>
	<!-- 隐藏域：角色（1、政府； 2、企业） -->
	<input type="hidden" id="whroletype" value="<s:property value="#session.whroletype"/>" />
	<div class="bread-line">
		<label>当前位置：</label><a href="#">安全培训教育</a>&gt;&nbsp;岗前三级培训<s:if test="train.id != null">修改</s:if><s:else>新增</s:else>
	</div>
	<div class="formPage">
		<form id="trainform">
		<!-- 隐藏域：企业ID -->
		<input type="hidden" id="group_id" name="groupId" value="<s:property value="#session.adminUser.groupId"/>" />
		<!-- 隐藏域：主键ID -->
		<input type="hidden" id="vid" name="id" value="<s:property value="train.id"/>" />
		<div class="formbg">
			<div class="big_title"><s:if test="train.id != null">修改</s:if><s:else>新增</s:else></div>
			<div class="content_form">
				<table width="100%" cellspacing="0" cellpadding="0" border="0" class="inputTable">
					<tbody>
						<tr>
							<th><em class="requireField">*</em><label>姓名：</label></th>
							<td><input type="text" class="formText" id="userName" name="userName" value="<s:property value="train.userName"/>" valid="required" errmsg="train.userName_not_null" maxlength="20"/></td>
							<th><em class="requireField">*</em><label>性别：</label></th>
							<td><s:if test="train.sex==0">
									<input type="radio" name="sex" value="1" /> 男&nbsp;&nbsp;
                            		<input type="radio" name="sex" value="0" checked="checked" /> 女
                            	</s:if> 
								<s:else>
									<input type="radio" name="sex" value="1" checked="checked"/> 男&nbsp;&nbsp;
                            		<input type="radio" name="sex" value="0" /> 女
								</s:else>
							</td>
						</tr>
						<tr>
							<th><em class="requireField">*</em><label>年龄：</label></th>
							<td><input type="text" class="formText" onkeyup="testNum(this);" id="age" name="age" value="<s:property value="train.age"/>" valid="required" errmsg="train.age_not_null" maxlength="3"/></td>
							<th><em class="requireField">*</em><label>录用形式：</label></th>
							<td><input type="text" class="formText" id="receiveType" name="receiveType" value="<s:property value="train.receiveType"/>" valid="required" errmsg="train.receiveType_not_null" maxlength="20"/>
							</td>
						</tr>
						<tr>
							<th><label>工种：</label></th>
							<td><input type="text" class="formText" id="workType" name="workType" value="<s:property value="train.workType"/>"  maxlength="20"/></td>
							<th><label>岗位：</label></th>
							<td><input type="text" class="formText" id="post" name="post" value="<s:property value="train.post"/>" maxlength="20"/>
							</td>
						</tr>
						<tr>
							<th><label>体检结果：</label></th>
							<td><input type="text" class="formText" id="testResult" name="testResult" value="<s:property value="train.testResult"/>"  maxlength="20"/></td>
							<th><label>从何处来：</label></th>
							<td><input type="text" class="formText" id="nativePlace" name="nativePlace" value="<s:property value="train.nativePlace"/>" maxlength="20"/>
							</td>
						</tr>
					</tbody>
				</table>
				<h2 class="small_title"><a href="#" class="shareShow">公司级教育（一级）</a></h2>
				<table width="100%" cellspacing="0" cellpadding="0" border="0" class="inputTable">
					<tbody>
						<tr>
							<th><label>教育内容：</label></th>
							<td colspan="5">
								<textarea class="formTextarea" id="oneTeachContent" name="oneTeachContent" maxlength="500"><s:property value="train.oneTeachContent"/></textarea>
							</td>
						</tr>
						<tr>
							<th><label>考试成绩：</label></th>
							<td><input type="text" class="formText" id="oneScore" onkeyup="testNum(this);" name="oneScore" value="<s:property value="train.oneScore"/>" maxlength="5"/></td>
							<th><label>阅卷人：</label></th>
							<td><input type="text" class="formText" id="oneExaminer" name="oneExaminer" value="<s:property value="train.oneExaminer"/>"  maxlength="20" /></td>
							<th><label>安全负责人：</label></th>
							<td><input type="text" class="formText" id="oneHeader" name="oneHeader" value="<s:property value="train.oneHeader"/>"  maxlength="20" /></td>
						</tr>
					</tbody>
				</table>
				<h2 class="small_title"><a href="#" class="shareShow">公司级教育（二级）</a></h2>
				<table width="100%" cellspacing="0" cellpadding="0" border="0" class="inputTable">
					<tbody>
						<tr>
							<th><label>教育内容：</label></th>
							<td colspan="5">
								<textarea class="formTextarea" id="twoTeachContent" name="twoTeachContent" maxlength="500"><s:property value="train.twoTeachContent"/></textarea>
							</td>
						</tr>
						<tr>
							<th><label>考试成绩：</label></th>
							<td><input type="text" onkeyup="testNum(this);" class="formText" id="twoScore" name="twoScore" value="<s:property value="train.twoScore"/>" maxlength="5"/></td>
							<th><label>主考人：</label></th>
							<td><input type="text" class="formText" id="twoExaminer" name="twoExaminer" value="<s:property value="train.twoExaminer"/>"  maxlength="20" /></td>
							<th><label>负责人：</label></th>
							<td><input type="text" class="formText" id="twoHeader" name="twoHeader" value="<s:property value="train.twoHeader"/>"  maxlength="20" /></td>
						</tr>
					</tbody>
				</table>
				<h2 class="small_title"><a href="#" class="shareShow">公司级教育（三级）</a></h2>
				<table width="100%" cellspacing="0" cellpadding="0" border="0" class="inputTable">
					<tbody>
						<tr>
							<th><label>教育内容：</label></th>
							<td colspan="5">
								<textarea class="formTextarea" id="threeTeachContent" name="threeTeachContent" maxlength="500"><s:property value="train.threeTeachContent"/></textarea>
							</td>
						</tr>
						<tr>
							<th><label>时间：</label></th>
							<td><input type="text" class="formText Wdate" onfocus="WdatePicker({skin:'default',dateFmt:'yyyy-MM-dd'})" id="threeTeachTime" name="threeTeachTime" value="<s:date name="train.threeTeachTime" format="yyyy-MM-dd"/>" /></td>
							<th><label>掌握情况：</label></th>
							<td><input type="text" class="formText" id="threeStudySituation" name="threeStudySituation" value="<s:property value="train.threeStudySituation"/>"  maxlength="100" /></td>
							<th><label>负责人：</label></th>
							<td><input type="text" class="formText" id="threeHeader" name="threeHeader" value="<s:property value="train.threeHeader"/>"  maxlength="20" /></td>
						</tr>
					</tbody>
				</table>
				<h2 class="small_title"><a href="#" class="shareShow">其他</a></h2>
				<table width="100%" cellspacing="0" cellpadding="0" border="0" class="inputTable">
					<tbody>
						<tr>
							<th><label>个人态度：</label></th>
							<td>
								<textarea class="formTextarea" id="ownerAttitude" name="ownerAttitude" maxlength="500"><s:property value="train.ownerAttitude"/></textarea>
							</td>
						</tr>
						<tr>
							<th><label>准上岗人意见：</label></th>
							<td>
								<textarea class="formTextarea" id="checkAttitude" name="checkAttitude" maxlength="500"><s:property value="train.checkAttitude"/></textarea>
							</td>
						</tr>
						<tr>
							<th><label>批准人：</label></th>
							<td>
								<input type="text" class="formText" id="checker" name="checker" value="<s:property value="train.checker"/>"  maxlength="20" />
							</td>
						</tr>
						<tr>
							<th><label>备注：</label></th>
							<td>
								<textarea class="formTextarea" id="memo" name="memo" maxlength="500"><s:property value="train.memo"/></textarea>
							</td>
						</tr>
						<tr>
							<th><label>上传证件照：</label></th>
							<td>
			                    <input type="hidden" id="attachmentId" name="attachmentId" value="<s:property value="train.attachmentId"/>"/>
			                	<input id="file_upload" name="fileupload" type="file" multiple="true" />
			                    <!-- 上传队列 -->
								<div id="queue"  style="display:none;"></div>
			                	<div class="imgUpload">
		                          <ul id="attachmentList">
		                          	<s:if test="fileList!=null&&fileList.size>0">
		                          		<s:iterator  value="fileList" id="file" >
							           		<li id="${file.id }_li"><img src="${downPath }upload/${file.attachFile}"><span class="close" onclick="deleteImg(${file.id })"></span></li>
							           </s:iterator>  
		                          	</s:if>
		                          </ul>
		                         </div>
		                    </td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
		<div class="buttonArea">
			<input type="button"class="formButton_green" value="确定" id="save" hidefocus=""/>
			<input type="button" value="取消" class="formButton_grey"  hidefocus="" onclick="javascript:history.go(-1);"/>
		</div>
		</form>
	</div>
	<div class="clear"></div>
<script type="text/javascript">
  $(document).ready(function(){
	    $(".shareShow").click(function(){
		    if($(this).hasClass("shareCls")){
			   	$(this).removeClass("shareCls");
			}else{
			    $(this).addClass("shareCls");
			}
			$(this).parent().next(".inputTable").toggle();
			return false;
		})
  });
</script>
</body>
</html>