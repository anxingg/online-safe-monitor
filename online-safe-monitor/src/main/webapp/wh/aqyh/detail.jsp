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
    <label>当前位置：</label><a href="javascript:void(0);">首页</a>&gt;<a href="javascript:void(0);">安全隐患排查</a>&gt;&nbsp;安全隐患排查整治新增
</div>
                 <div class="formPage">
   <div class="formbg">
        <div class="big_title">档案详情</div>
        <div class="content_form">
        		<form name="addForm" id="addForm" method="post">
                <table width="100%" cellspacing="0" cellpadding="0" border="0" class="inputTable">
                    <tbody>
                        <tr>
                            <th>安全隐患名称：</th>
                            <td>${wuhaiSafeDanger.danger_name }</td>
                            <th>企业名称：</th>
                            <td>
                                ${wuhaiSafeDanger.group_name }
                            </td>
                        </tr>
                        <tr>
                            <th>责任部门：</th>
                            <td>${wuhaiSafeDanger.responsible_department }</td>
                             <th>责任人：</th>
                            <td>${wuhaiSafeDanger.responsible }</td>
                        </tr>
                        <tr>
                            <th>检查日期：</th>
                            <td colspan="3"><fmt:formatDate value="${wuhaiSafeDanger.checkdate}" type="date"/></td>
                        </tr>
                        <tr>
                            <th>隐患内容：</th>
                            <td colspan="3">${wuhaiSafeDanger.check_condition}</td>
                        </tr>
                        <tr>
                            <th>整改结束时间：</th>
                            <td colspan="3"><fmt:formatDate value="${wuhaiSafeDanger.rectification_end_date}" type="date"/></td>
                        </tr>
                       <tr>
                            <th>整改情况：</th>
                            <td colspan="3">${wuhaiSafeDanger.rectification}</td>
                        </tr>
                         <tr>
                            <th>复查责任人：</th>
                            <td>${wuhaiSafeDanger.review_user}</td>
                            <th>复查时间：</th>
                            <td><fmt:formatDate value="${wuhaiSafeDanger.review_time}" type="date"/></td>
                        </tr>
                        <c:if test="${sessionScope.whroletype==1}">
                        <tr>
                            <th>企业端是否可见：</th>
                            <td colspan="3">
                            	<c:choose>
                            		<c:when test="${wuhaiSafeDanger.can_see==1}">是</c:when>
                            		<c:otherwise>否</c:otherwise>
                            	</c:choose>
                            </td>
                        </tr>
                        </c:if>
                        <tr>
                            <th>备注：</th>
                            <td colspan="3">${wuhaiSafeDanger.memo }</td>
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