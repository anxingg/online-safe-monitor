<%@ page language="java" import="java.util.*"  contentType="text/html; charset=UTF-8" pageEncoding="utf-8"  %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<jsp:include page="../../../common/taglibs.jsp"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<jsp:include page="../head.jsp" />
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>安全管理人员新增</title>
	<!-- 验证start -->
	<script type="text/javascript" src="${ctx}js/common/validate_form.js?version=${version}"></script>
	<script type="text/javascript" src="${ctx}common/js/js_lang_cn.js?version=${version}"></script>
	<script type="text/javascript" src="${ctx}js/common/showError.js?version=${version}"></script>
	<!-- 验证end -->
	<script type="text/javascript" src="${ctx}wh/js/common.js?version=${version}"></script>
    <script type="text/javascript" src="${ctx}wh/js/specialWorkPerson/addSpecialWorkPerson.js?version=${version}"></script>
    
</head>
<body>

	<input type="hidden" id="whroletype" value='<s:property value="#session.whroletype"/>'/>
	<input type="hidden" id="group_id" value='<s:property value="#session.adminUser.groupId"/>'/>
	<div class="bread-line">
		<label>当前位置：</label><a href="#">首页</a>&gt;<a href="#">企业管理</a>&gt;&nbsp;特种作业人员新增
	</div>
	
                 <div class="formPage">
	 <div class="formbg">
        <div class="big_title">新增<em>（特种作业人员）</em></div>
        <div class="content_form">
        		<form id="form1">
                <table width="100%" cellspacing="0" cellpadding="0" border="0" class="inputTable">
                    <tbody>
                        
                        <tr>
                            <th><em class="requireField">*</em>姓名：</th>
                            <td><input type="text" class="formText" id="name" maxlength="32" 
                            	valid="required" errmsg="special.name_not_null"/></td>
                        </tr>
                        <tr>
                            <th>部门：</th>
                            <td><input type="text" class="formText" id="groupName" maxlength="32"/></td>
                            <th><em class="requireField">*</em>身份证号：</th>
                            <td><input type="text" class="formText" id="idNumber" maxlength="18" onkeyup="this.value=this.value.replace(/[^0-9xX]/g,'')"
                            	valid="required" errmsg="special.idNumber_not_null"/></td>
                        </tr>
                        <tr>
                            <th><em class="requireField">*</em>上岗时间：</th>
                            <td><input id="startTime" type="text" style="width: 240px"
                            valid="required" errmsg="special.startTime_not_null"
							class="formText Wdate"
							onclick="WdatePicker({skin:'default',dateFmt:'yyyy-MM-dd'})" /></td>
                            <th><em class="requireField">*</em>工种：</th>
                            <td><input type="text" class="formText" id="workType" maxlength="32"
                            	valid="required" errmsg="special.workType_not_null"/></td>
                        </tr>
                        <tr>
                        	<th>取证日期：</th>
                            <td><input id="certificateGetTime" type="text" style="width: 240px"
							class="formText Wdate"
							onclick="WdatePicker({skin:'default',dateFmt:'yyyy-MM-dd'})" /></td>
                            <th><em class="requireField">*</em>发证机关：</th>
                            <td><input type="text" class="formText" id="issuingAuthority" maxlength="32"
                            	valid="required" errmsg="special.issuingAuthority_not_null"/></td>
                        </tr>
                        
                        <tr>
                            <th>复审日期：</th>
                            <td><input id="replaceDate" type="text" style="width: 240px"
							class="formText Wdate"
							onclick="WdatePicker({skin:'default',dateFmt:'yyyy-MM-dd'})" /></td>
							<th><em class="requireField">*</em>证书编号：</th>
                            <td><input type="text" class="formText" id="certificateNum" maxlength="32"
                            	valid="required" errmsg="special.certificateNum_not_null"/></td>
                        </tr>
                        <tr>
                    		<th><label>备注：</label></th>
	                    	<td colspan="3">
	                        <textarea class="formTextarea" rows="5" id="memo" maxlength="256" 
	                        valid="textareaLength" errmsg="safety.text_max_length" maxNumber="256"></textarea>
	                    	<span style="float:right">0-256字</span>
	                    	</td>
                  		</tr>
                    </tbody>
                </table>
                </form>
        </div>
   </div>
   
   
    <div class="buttonArea"> 
			<input type="button"class="formButton_green" value="确定" hidefocus="" onclick="addPerson();"/>
			<input type="button" value="取消" class="formButton_grey"  hidefocus="" onclick="javascript :history.back(-1);"/>
</div>
</div>
  
  

<div class="clear"></div>
</body>
</html>
