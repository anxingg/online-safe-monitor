<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/ninclude/import.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>表单</title> 
<%@include file="/ninclude/title.jsp"%>
<link href="${ctx}plugins/datatable/skins/datatable_page.css" rel="stylesheet" type="text/css" />
<link href="${ctx}plugins/datatable/skins/datatable_default.css" rel="stylesheet" type="text/css" />
<link href="${ctx}wh/plugins/datatable/skins/datatable_default.css?version=${version}" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="${ctx}plugins/datatable/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="${ctx}plugins/My97DatePicker/WdatePicker.js"></script>
 <script type="text/javascript">
		var iszf="${sessionScope.whroletype}";
</script>
</head>
<body>
<input type="hidden" id="whroletype" value='<s:property value="#session.whroletype"/>'/>
<div class="bread-line">
  <label>当前位置：</label><a href="javascript:void(0);">首页</a>&gt;<a href="javascript:void(0);">安全隐患排查</a>&gt;&nbsp;安全隐患排查整治列表
</div>
<div class="list">
  <div class="searchArea">
       <ul>
       		<li id="qymc"><label>企业名称：</label>
				<select class="" id="companName">
					<option value="">全部</option>
				</select>
			</li>
			<li><label>复查日期：</label><input type="text" onfocus="WdatePicker({skin:'default',dateFmt:'yyyy-MM-dd'})" placeholder="按日期查询" class="formText Wdate" id="reportTime" style="width: 120px;"></li>
            <li><label>检查日期：</label><input type="text" onfocus="WdatePicker({skin:'default',dateFmt:'yyyy-MM-dd'})" placeholder="按日期查询" class="formText Wdate" id="happyTime" style="width: 120px;"></li>
            <li><label>责任部门：</label><input type="text" id="dept"  class="formText" style="width: 120px;"></li>
            <li><label>责任人：</label><input type="text"  id="deptor"  class="formText" style="width: 120px;"></li>
            <li><input type="button" class="searchButton" id="aqyhsearch" value="查询"><s:if test="#session.whroletype==1 || #session.whroletype ==2"><div class="fButton greenBtn"> <span class="add" onclick="addNotify();">新增</span></div></s:if></li>
            <li></li>
       </ul>                        
  </div>
  <table cellpadding="0" cellspacing="0"  class="pretty dataTable" id="myTable">
    <thead>
      <tr>
        <th style="width:70px;">序号</th>
        <th>企业名称</th>
        <th>隐患内容</th>
        <th style="width:90px;">整改期限</th>
        <th>责任部门</th>
        <th>责任人</th>
        <th>整改情况</th>
        <th>复查人员</th>
        <th style="width:90px;">复查日期</th>
        <th style="width:135px;">操作</th>
      </tr>
    </thead>
    <tbody>
      
    </tbody>
  </table>
</div>
<div class="clear"></div>
</body>
</html>
<script type="text/javascript" src="${ctx}wh/js/aqyh/aqyh.js"></script>
