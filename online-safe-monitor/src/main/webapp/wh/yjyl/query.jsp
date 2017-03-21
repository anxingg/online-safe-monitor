<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/ninclude/import.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>应急演练</title> 
<%@include file="/ninclude/title.jsp"%>
<link href="${ctx}plugins/datatable/skins/datatable_page.css" rel="stylesheet" type="text/css" />
<link href="${ctx}plugins/datatable/skins/datatable_default.css" rel="stylesheet" type="text/css" />
<link href="${ctx}wh/plugins/datatable/skins/datatable_default.css?version=${version}" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="${ctx}plugins/datatable/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="${ctx}plugins/My97DatePicker/WdatePicker.js"></script>
 <script type="text/javascript">
		var basePath = "${ctx}";
		var iszf="${sessionScope.whroletype}";
</script>
<script type="text/javascript" src="${ctx}wh/js/yjyl/query.js"></script>
</head>
<body>
<div class="bread-line">
  <label>当前位置：</label><a href="javascript:void(0);">首页</a>&gt;<a href="javascript:void(0);">应急演练</a>&gt;&nbsp;应急演练查询
</div>
<div class="list">
  <div class="searchArea">
       <ul>
       		<c:if test="${sessionScope.whroletype==1||sessionScope.whroletype==3}">
             <li><label>企业名称：</label><select id="companName"><option value="">请选择</option></select></li>
            </c:if>
			<li><label>演练项目：</label><input type="text" id="exercise_name" class="formText" /></li>
            <li><label>应急预案：</label>
            					<select id="plan_id">
                            		<option value="">请选择</option>
                            	</select></li>
            <li><label>预案类型：</label><select  id="plan_type">
            	<option value="-1">全部</option>
            	
            </select></li>
            <li><input type="button" class="searchButton" id="yjylsearch" value="查询" />
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
        <th>演练项目</th>
        <th style="width:80px">演练时间</th>
        <th>演练地点</th>
        <th>组织部门</th>
        <th>负责人</th>
        <th style="width:140px;">操作</th>
      </tr>
    </thead>
    <tbody>
      
    </tbody>
  </table>
</div>
<div class="clear"></div>
</body>
</html>

