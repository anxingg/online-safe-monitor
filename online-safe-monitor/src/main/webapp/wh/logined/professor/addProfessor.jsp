<%@ page language="java" import="java.util.*"  contentType="text/html; charset=UTF-8" pageEncoding="utf-8"  %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<% String type = request.getParameter("type");
	request.setAttribute("type",type);
 %>
<jsp:include page="../../../common/taglibs.jsp"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<jsp:include page="../head.jsp" />
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>安全生产专家</title>
	<!-- 验证start -->
	<script type="text/javascript" src="${ctx}js/common/validate_form.js?version=${version}"></script>
	<script type="text/javascript" src="${ctx}common/js/js_lang_cn.js?version=${version}"></script>
	<script type="text/javascript" src="${ctx}js/common/showError.js?version=${version}"></script>
	<!-- 验证end -->
	<script type="text/javascript" src="${ctx}wh/js/professor/addProfessor.js?version=${version}"></script>
    
</head>
<body>
	<input type="hidden" id="whroletype" value='<s:property value="#session.whroletype"/>'/>
	<input type="hidden" id="vid" value="${professor.vid }"/>
	<input type="hidden" id="professorType" value="${professor.professorType }"/>
	<div class="bread-line">
		<input type="hidden" id="type" value="${param.type }"/>
		<s:if test="#request.type==1||#request.professor.professorType==1">
		<label>当前位置：</label><a href="#">首页</a>&gt;<a href="#">职业卫生专家</a>&gt;&nbsp;<s:if test="professor.vid != null">修改</s:if><s:else>新增</s:else>
		</s:if>
		<s:elseif test="#request.type==2||#request.professor.professorType==2">
		<label>当前位置：</label><a href="#">首页</a>&gt;<a href="#">非煤矿山专家</a>&gt;&nbsp;<s:if test="professor.vid != null">修改</s:if><s:else>新增</s:else>
		</s:elseif>
		<s:elseif test="#request.type==3||#request.professor.professorType==3">
		<label>当前位置：</label><a href="#">首页</a>&gt;<a href="#">危险化学品专家</a>&gt;&nbsp;<s:if test="professor.vid != null">修改</s:if><s:else>新增</s:else>
		</s:elseif>
	</div>
	 <div class="formPage">
	 <div class="formbg">
        <div class="big_title"><s:if test="professor.vid != null">修改</s:if><s:else>新增</s:else></div>
        <div class="content_form">
                <form id="form1">
                <table width="100%" cellspacing="0" cellpadding="0" border="0" class="inputTable">
                    <tbody>
                        <tr>
                            <th><em class="requireField">*</em>姓名：</th>
                            <td>
                            	<input type="text" class="formText" id="name" maxlength="32" value="${professor.name }"
                            	valid="required" errmsg="professor.name_not_null" />
                            	
                            </td>
                        </tr>
                        <tr>
                            <th><em class="requireField">*</em>专业特长：</th>
                            <td>
                            	<input type="text" class="formText" id="specialties" maxlength="32" value="${professor.specialties }"
                            	valid="required" errmsg="professor.specialties_not_null" />
                            </td>
                        </tr>
                        <tr>
                        	<th><em class="requireField">*</em>技术职称：</th>
                            <td>
								<input type="text" class="formText" id="title" maxlength="32" value="${professor.title }"
                            	valid="required" errmsg="professor.title_not_null" />
                            </td>
                        </tr>
                        <tr>
                        	<th><em class="requireField">*</em>工作单位：</th>
                            <td>
								<input type="text" class="formText" id="workCompany" maxlength="32" value="${professor.workCompany }"
                            	valid="required" errmsg="professor.workCompany_not_null" />
                            </td>
                        </tr>
                        <tr>
                        	<th><em class="requireField">*</em>联系电话：</th>
                            <td>
								<input type="text" class="formText" id="phone" value="${professor.phone }"
								onkeyup="this.value=this.value.replace(/[^0-9\-]/g,'')" maxlength="12"
                            	valid="required|isPhoneSimple" errmsg="professor.phone_not_null|professor.phone_not_mobile" />
                            </td>
                        </tr>
                        <tr>
                        	<tr>
                            <th>备注：</th>
		                    <td colspan="3">
	                        <textarea class="formTextarea" rows="5" id="memo" maxlength="256" 
	                         maxNumber="256">${professor.memo }</textarea>
	                    	<span style="float:right">0-256字</span>
		                    </td>
                        </tr>
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
