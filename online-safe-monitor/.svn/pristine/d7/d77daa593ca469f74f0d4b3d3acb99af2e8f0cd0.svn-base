<%@ page language="java" import="java.util.*"  contentType="text/html; charset=UTF-8" pageEncoding="utf-8"  %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<jsp:include page="../../../common/taglibs.jsp"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<jsp:include page="../head.jsp" />
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>应急预案</title>
	<!-- 验证start -->
	<script type="text/javascript" src="${ctx}js/common/validate_form.js?version=${version}"></script>
	<script type="text/javascript" src="${ctx}common/js/js_lang_cn.js?version=${version}"></script>
	<script type="text/javascript" src="${ctx}js/common/showError.js?version=${version}"></script>
	<!-- 验证end -->
    <script type="text/javascript" src="${ctx}wh/js/plans/addPlans.js?version=${version}"></script>
    <style type="text/css">
	.uploadify{float:left;margin-top:7px;}
	</style>
</head>
<body>
	<input type="hidden" id="whroletype" value='<s:property value="#session.whroletype"/>'/>
	<input type="hidden" id="group_id" value='<s:property value="#session.adminUser.groupId"/>'/>
	<div class="bread-line">
		<label>当前位置：</label><a href="#">首页</a>&gt;<a href="#">应急预案</a>&gt;&nbsp;新增
	</div>
	 <div class="formPage">
	 <div class="formbg">
        <div class="big_title">新增<em>（应急预案）</em></div>
        <div class="content_form">
                <form id="form1">
                <table width="100%" cellspacing="0" cellpadding="0" border="0" class="inputTable">
                    <tbody>
                        <tr>
                            <th><em class="requireField">*</em>预案编号：</th>
                            <td>
                            	<input type="text" class="formText" id="planNo" maxlength="32"
                            	valid="required" errmsg="wuhaiPlans.planNo_not_null" />
                            	
                            </td>
                            <th><em class="requireField">*</em>企业名称：</th>
                            <td>
                            	<span id="companyName"></span>
                            </td>
                        </tr>
                        <tr>
                            <th><em class="requireField">*</em>预案类型：</th>
                            <td>
                            	<select id="planType">
                            		
                            	</select>
                            </td>
                            <th><em class="requireField">*</em>经办人：</th>
                            <td>
                            	<input type="text" class="formText" id="agent" maxlength="32"
                            	valid="required" errmsg="wuhaiPlans.agent_not_null" />
                            </td>
                        </tr>
                        <tr>
                        	<th>企业法人：</th>
                            <td>
                            	<span id="legalRepresentative"></span>
                            </td>
                            <th><em class="requireField">*</em>企业电话：</th>
                            <td>
                            	<input type="text" class="formText" id="phone" 
                            	onkeyup="this.value=this.value.replace(/[^0-9\-]/g,'')" maxlength="12"
                            	valid="required|isPhoneSimple" errmsg="wuhaiPlans.phone_not_null|wuhaiPlans.phone_not_mobile" />
                            </td>
                        </tr>
                        <tr>
                        	<th>企业地址：</th>
                            <td colspan="3">
                            	<span id="cityId"></span>
                            </td>
                            
                        </tr>
                        <tr>
                        	<th><em class="requireField">*</em>备案时间：</th>
                            <td>
                            	<input id="prepareTime" type="text" class="formText Wdate" style="width: 240px"
             						onclick="WdatePicker({skin:'default',dateFmt:'yyyy-MM-dd'})" onblur="putTime();" 
             						valid="required" errmsg="wuhaiPlans.prepareTime_not_null" maxlength="10"/>
                            </td>
                            <th><em class="requireField">*</em>备案到期时间（3年）：</th>
                            <td>
                            	<input id="prepareEndTime" type="text" class="formText Wdate" style="width: 240px"
             						onclick="WdatePicker({skin:'default',dateFmt:'yyyy-MM-dd'})" 
             						valid="required" errmsg="wuhaiPlans.prepareEndTime_not_null" maxlength="10"/>
                            </td>
                        </tr>
                        <tr>
                        	<th>应急内容附件上传：</th>
                            <td>
								<input type="hidden" id="attachmentId" name="attachmentId" />
								<input type="hidden" id="path" />
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
                    </tbody>
                </table>
                </form>
        </div>
   </div>
    <div class="buttonArea"> 
			<input type="button"class="formButton_green" value="确定" hidefocus="" id="submit"/>
			<input type="button" value="取消" class="formButton_grey"  hidefocus="" onclick="javascript :history.back(-1);"/>
</div>
  </div>

<div class="clear"></div>
</body>
</html>
