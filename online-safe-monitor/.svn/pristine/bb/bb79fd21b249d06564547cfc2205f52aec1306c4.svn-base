<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/ninclude/import.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>安全隐患排查</title> 
<%@include file="/ninclude/title.jsp"%>
<script type="text/javascript" src="${ctx}wh/js/js_lang_cn.js"></script>
</head>
<body>
<div class="bread-line">
    <label>当前位置：</label><a href="javascript:void(0);">首页</a>&gt;<a href="javascript:void(0);">安全隐患排查</a>&gt;&nbsp;安全隐患排查整治新增
</div>
<div class="formPage">
   <div class="formbg">
        <div class="big_title">新增档案</div>
        <div class="content_form">
        		<form name="addForm" id="addForm" method="post">
                <table width="100%" cellspacing="0" cellpadding="0" border="0" class="inputTable">
                    <tbody>
                    	<tr>
                    		<th><em class="requireField">*</em>企业名称：</th>
                            <td colspan="3">
                                <select id="companName"><option value="">请选择</option></select>
                                 <c:if test="${sessionScope.whroletype==1}">
                            	<label class="radio"><input id="can_see" type="checkbox" checked="true">企业端可见</label>
                            </c:if>
                            </td>
                           
                    	</tr>
                        <tr>
                            <th><em class="requireField">*</em>安全隐患名称：</th>
                            <td colspan="3"><input type="text" id="danger_name" maxlength="32" valid="required" errmsg="aqyh.aqyh_name_not_null|aqyh.aqyh_name_valid" class="formText" name="danger_name" value=""/><span class="addMember"></span></td>
                            
                        </tr>
                        <tr>
                            <th><em class="requireField">*</em>责任部门：</th>
                            <td><input type="text" id="responsible_department" valid="required" errmsg="aqyh.aqyh_dept_not_null" class="formText" maxlength="32" value=""/></td>
                             <th><em class="requireField">*</em>责任人：</th>
                            <td><input type="text" id="responsible"  valid="required" errmsg="aqyh.aqyh_responsible_not_null"  maxlength="32" class="formText" value=""/></td>
                        </tr>
                        <tr>
                            <th><em class="requireField">*</em>检查日期：</th>
                            <td colspan="3"><input type="text"  style="width:180px" value="" onfocus="WdatePicker({skin:'default',dateFmt:'yyyy-MM-dd'})" class="formText Wdate" id="checkDate"  /></td>
                        </tr>
                        <tr>
                            <th><em class="requireField">*</em>隐患内容：</th>
                            <td colspan="3"><textarea class="formTextarea" valid="required" errmsg="aqyh.aqyh_check_condition_not_null" rows="3" maxlength="256" id="check_condition" value=""></textarea></td>
                        </tr>
                        <tr>
                            <th>整改结束时间：</th>
                            <td colspan="3"><input type="text"  value="" style="width:180px" onfocus="WdatePicker({skin:'default',dateFmt:'yyyy-MM-dd'})" class="formText Wdate" id="rectification_end_date"  /></td>
                        </tr>
                       <tr>
                            <th>整改情况：</th>
                            <td colspan="3"><textarea class="formTextarea" rows="1" maxlength="256" id="rectification" value=""></textarea></td>
                        </tr>
                         <tr>
                            <th>复查责任人：</th>
                            <td><input type="text" id="review_user" class="formText" maxlength="32" value=""/></td>
                            <th>复查时间：</th>
                            <td><input type="text" value="" style="width:180px" onfocus="WdatePicker({skin:'default',dateFmt:'yyyy-MM-dd'})" class="formText Wdate" id="review_time" /></td>
                        </tr>
                        <tr>
                            <th>备注：</th>
                            <td colspan="3"><textarea class="formTextarea" rows="1" id="memo" maxlength="256" value=""></textarea></td>
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
<script type="text/javascript">
var iszf="${sessionScope.whroletype}";
</script>
<script type="text/javascript" src="${ctx}wh/js/aqyh/add.js"></script>
