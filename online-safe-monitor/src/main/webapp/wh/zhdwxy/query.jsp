<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/ninclude/import.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>重大危险源</title> 
<%@include file="/ninclude/title.jsp"%>
<!-- link href="${ctx}plugins/datatable/skins/datatable_page.css" rel="stylesheet" type="text/css" /-->
<!--link href="${ctx}plugins/datatable/skins/datatable_default.css" rel="stylesheet" type="text/css" /-->
<link href="${ctx}wh/plugins/datatable/skins/datatable_default.css?version=${version}" rel="stylesheet" type="text/css"/>
<!--script type="text/javascript" src="${ctx}plugins/datatable/jquery.dataTables.min.js"></script-->
<!--script type="text/javascript" src="${ctx}plugins/My97DatePicker/WdatePicker.js"></script-->
 <script type="text/javascript">
		var basePath = "${ctx}";
		var iszf="${sessionScope.whroletype}";
</script>
</head>
<body>
<div class="bread-line">
  <label>当前位置：</label><a href="javascript:void(0);">首页</a>&gt;<a href="javascript:void(0);">重大危险源</a>&gt;&nbsp;重大危险源查询
</div>
<div class="list">
  <div class="searchArea">
       <ul>
       		<c:if test="${sessionScope.whroletype!=2}">
             <li><label>企业名称：<select id="companName"><option value="">请选择</option></select></li>
            </c:if>
			<li><label>危险源名称：</label><input type="text" id="dangerSourcesName" class="formText"></li>
            <!-- <li><label>安全管理措施：</label><input type="text"  id="safetyMeasures" class="formText"></li> -->
            <li><label>危险源分类：</label><select id="grade"><option value="">请选择</option><option value="0">一般</option><option value="1">重大</option></select></li>
            <li><input type="button" class="searchButton" id="zhdwxysearch" value="查询" class="formText">
            <c:if test="${sessionScope.whroletype!=1 && sessionScope.whroletype!=3}">
            <div class="fButton greenBtn"> <span class="add" onclick="addNotify();">新增</span></div>
            </c:if>
            </li>
            <li></li>
       </ul>                        
  </div>
  <table cellpadding="0" cellspacing="0"  class="pretty dataTable" id="myTable">
    <thead>
      <tr>
        <th style="width:70px;">序号</th>
        <th>危险源名称</th>
        <th>企业名称</th>
        <th style="width:100px;">危险源分类</th>
        <th style="width:100px;">危险源级别</th>
        <th>评价机构</th>
        <!-- <th style="width:100px;">评审时间</th>
        <th style="width:100px;">过期时间</th>
        <th>发生事故时紧急措施</th>
        <th>安全管理措施</th> -->
        <c:if test="${whroletype==2}"><th style="width:135px;">操作</th></c:if>
      </tr>
    </thead>
    <tbody>
      
    </tbody>
  </table>
</div>
<div class="clear"></div>
</body>
</html>
<script type="text/javascript" src="${ctx}wh/js/zhdwxy/query.js"></script>