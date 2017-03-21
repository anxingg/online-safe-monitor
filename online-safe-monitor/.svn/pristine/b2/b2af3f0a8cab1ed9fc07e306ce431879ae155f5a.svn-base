<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>模块权限配置</title>
<jsp:include page="../../common/flatHead.jsp" />
<link href="${ctx}flat/css/reset.css" rel="stylesheet" type="text/css" />
<link href="${ctx}flat/css/main.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="${ctx}flat/plugins/tree/skins/tree_default.css" type="text/css"/>
<link href="${ctx}flat/plugins/datatable/skins/datatable_default.css" rel="stylesheet" type="text/css" />
<link href="${ctx}flat/plugins/Accormenus/skins/Accormenus_default.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx}plugins/tree/ztree/jquery.ztree.all-3.5.min.js"></script>
<script type="text/javascript" src="${ctx}flat/plugins/datatable/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="${ctx}js/placeholder.js"></script>
<script type="text/javascript" src="${ctx}flat/js/base.js"></script>
<script type="text/javascript" src="${ctx}flat/plugins/tree/skins/jquery.ztree.all-3.2.min.js"></script>
<script type="text/javascript" src="${ctx}plugins/datatable/selecedForDatatablePagination.js"></script>

</head>
<body>
  <div class="list">
    <div class="searchArea">
      <table cellspacing="0" cellpadding="0">
        <tbody>
          <tr>
            <td class="right">
            	<input type="hidden" id="moduleClassName" value="${moduleClassName }" />
             <div class="fButton greenBtn" ><span class="add" id="addButton">新增</span> </div>
             <div class="fButton blueBtn"> <span class="export" id="test">测试</span> </div>
             <div class="fButton blueBtn"> <span class="import" id="back">返回</span> </div>
             </td>
          </td>
          </tr>
        </tbody>
      </table>
    </div>
    <table cellpadding="0" cellspacing="0"  class="pretty dataTable" id="userTable">
     <thead>
      <tr>
       <th  class="num">序号</th>
       <th  class="tdCenter" style="width:30%;">模块类名</th>
       <th  class="right_bdr0" style="width:150px;">范围</th>
       <th  class="right_bdr0" style="">权限</th>
       <th  class="right_bdr0" style="">描述</th>
       <th  class="right_bdr0" style="width:70px;">操作</th>
       </tr>	
      </thead>
      <c:forEach items="${DataFilterList }" var="df" varStatus="vs">
      	<tr>
      		<td>${vs.index }</td>
      		<td>${df.modelClassName }</td>
      		<td>${df.relationId }</td>
      		<td>${df.conditionJpql }</td>
      		<td>${df.name }</td>
      		<td><a href="${ctx }/datafilter/delete.action?id=${df.id}&moduleClassName=${moduleClassName }">删除</a></td>
      	</tr>
      </c:forEach>
    </table>
  </div>
  <script type="text/javascript">
  	jQuery(document).ready(function($){
  		
  		$("#back").click(function(){
  			window.location.href=basePath+"/datafilter/main.action";
  		});
  		
	  	$("#addButton").click(function(){
	  		art.dialog.open("${ctx}/logined/datafilter/addDataFilter.jsp?moduleClassName="+$("#moduleClassName").val(), {
				title : "新增权限配置",
				width : 700,
				height : 400,
				lock : true,
			    opacity:0.08,
			    id:"addDf",
				button : [{
							name : '确定',
							focus:true,
							callback : function() {
								var iframe = this.iframe.contentWindow;
								var r = iframe.getData();
								if(!r){
									return false;
								}else{
									
									$.post("${ctx}/datafilter/addDatafilter.action?config="+JSON.stringify(r)+"&moduleClassName="+$("#moduleClassName").val(),function(result){
										if(result.status=="success"){
											
										}
									},"json")	
									window.location.href=basePath+"/datafilter/manager.action?moduleClassName=${moduleClassName}";
									return false;
								}
							}
						}, {
							name : '取消',
							callback : function() {
								return true;
							}
						}]
			}, false);
	  	});
	  	
	  	$("#test").click(function(){
	  		art.dialog.open("${ctx}/logined/datafilter/testDataFilter.jsp?moduleClassName="+$("#moduleClassName").val(), {
				title : "测试权限配置",
				width : 700,
				height : 400,
				lock : true,
			    opacity:0.08,
				button : [{
							name : '确定',
							focus:true,
							callback : function() {
								return true;
							}
						}]
			}, false);
	  	});
  	});
  </script>
</body>
</html>
