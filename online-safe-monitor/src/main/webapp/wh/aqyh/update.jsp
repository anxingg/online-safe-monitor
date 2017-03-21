<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/ninclude/import.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>表单</title> 
<%@include file="/ninclude/title.jsp"%>
<script type="text/javascript" src="${ctx}wh/js/js_lang_cn.js"></script>
</head>
<body>
<div class="bread-line">
    <label>当前位置：</label><a href="javascript:void(0);">首页</a>&gt;<a href="javascript:void(0);">安全隐患排查</a>&gt;&nbsp;安全隐患排查整治修改
</div>
                 <div class="formPage">
   <div class="formbg">
        <div class="big_title">更新档案</div>
        <div class="content_form">
        		<form name="addForm" id="addForm" method="post">
                <table width="100%" cellspacing="0" cellpadding="0" border="0" class="inputTable">
                    <tbody>
                    	<tr>
                    		<th>企业名称：</th>
                            <td colspan="3">
                                <select id="companName"><option value="">请选择</option></select>
                                 <c:if test="${sessionScope.whroletype==1}">
                            		<label class="radio"><input id="can_see" type="checkbox"<c:if test="${ wuhaiSafeDanger.can_see==1}">checked="true"</c:if> >企业端可见</label>
                            	</c:if>
                            </td>
                           
                    	</tr>
                        <tr>
                            <th>安全隐患名称：</th>
                            <td colspan="3"><input type="text"  maxlength="32" id="danger_name" <c:if test="${wuhaiSafeDanger.data_source==1&&sessionScope.whroletype==2 }">readonly="readonly"</c:if>maxlength="32" valid="required" errmsg="aqyh.aqyh_name_not_null|aqyh.aqyh_name_valid" class="formText" name="danger_name" value="${wuhaiSafeDanger.danger_name }"/><span class="addMember"></span></td>
                        </tr>
                        <tr>
                            <th>责任部门：</th>
                            <td><input type="text"  maxlength="32" id="responsible_department" <c:if test="${wuhaiSafeDanger.data_source==1&&sessionScope.whroletype==2 }">readonly="readonly"</c:if>valid="required" errmsg="aqyh.aqyh_dept_not_null" class="formText" maxlength="32" value="${wuhaiSafeDanger.responsible_department }"/></td>
                             <th>责任人：</th>
                            <td><input type="text"  maxlength="32" id="responsible"  <c:if test="${wuhaiSafeDanger.data_source==1&&sessionScope.whroletype==2 }">readonly="readonly"</c:if>valid="required" errmsg="aqyh.aqyh_responsible_not_null"  maxlength="32" class="formText" value="${wuhaiSafeDanger.responsible }"/></td>
                        </tr>
                        <tr>
                            <th>检查日期：</th>
                            <td colspan="3"><input type="text"   style="width:180px"
                            <c:choose><c:when test="${wuhaiSafeDanger.data_source==1&&sessionScope.whroletype==2 }">readonly="readonly"</c:when> <c:otherwise>onfocus="WdatePicker({skin:'default',dateFmt:'yyyy-MM-dd'})" class="formText Wdate"</c:otherwise></c:choose>
                            value="<fmt:formatDate value='${wuhaiSafeDanger.checkdate }' type='date'/>"   
                            id="checkDate"/></td>
                        </tr>
                        <tr>
                            <th>隐患内容：</th>
                            <td colspan="3"><textarea  maxlength="256" class="formTextarea" valid="required" errmsg="aqyh.aqyh_check_condition_not_null" <c:if test="${wuhaiSafeDanger.data_source==1&&sessionScope.whroletype==2 }">readonly="readonly"</c:if> rows="3" maxlength="256" id="check_condition" >${wuhaiSafeDanger.check_condition}</textarea></td>
                        </tr>
                        <tr>
                            <th>整改结束时间：</th>
                            <td colspan="3"><input type="text"  style="width:180px" onfocus="WdatePicker({skin:'default',dateFmt:'yyyy-MM-dd'})" class="formText Wdate" 
                            value="<fmt:formatDate value='${wuhaiSafeDanger.rectification_end_date }' type='date'/>" 
                            id="rectification_end_date" class="formText" />
                            
                            </td>
                        </tr>
                       <tr>
                            <th>整改情况：</th>
                            <td colspan="3"><textarea class="formTextarea" maxlength="256" rows="1" id="rectification" >${wuhaiSafeDanger.rectification}</textarea></td>
                        </tr>
                         <tr>
                            <th>复查责任人：</th>
                            <td><input type="text" id="review_user"  maxlength="32" class="formText" value="${wuhaiSafeDanger.review_user}"/></td>
                            <th>复查时间：</th>
                            <td><input type="text" style="width:180px" value="<fmt:formatDate value="${wuhaiSafeDanger.review_time}" type="date"/>" onfocus="WdatePicker({skin:'default',dateFmt:'yyyy-MM-dd'})" class="formText Wdate" id="review_time" class="formText" /></td>
                        </tr>
                        <tr>
                            <th>备注：</th>
                            <td colspan="3"><textarea class="formTextarea" maxlength="256" rows="1" id="memo" >${wuhaiSafeDanger.memo }</textarea></td>
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
<script type="text/javascript" src="${ctx}wh/js/aqyh/update.js"></script>
<script type="text/javascript">
var _company = "${wuhaiSafeDanger.group_id}";
var _vid = "${wuhaiSafeDanger.vid}";
var ac_flag = "mod";
var iszf="${sessionScope.whroletype}";
</script>
