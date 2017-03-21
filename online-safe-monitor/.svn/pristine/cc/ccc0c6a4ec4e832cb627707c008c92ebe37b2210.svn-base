<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<% String id = request.getParameter("id");
	request.setAttribute("id",id);
 %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>公告管理页面</title>
<jsp:include page="../../wh/logined/head.jsp" />
<%-- <script type="text/javascript" src="${ctx}plugins/datatable/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="${ctx}js/common/jquery.cookie.js"></script>
<script language="javascript" type="text/javascript" src="${ctx}plugins/My97DatePicker/WdatePicker.js"></script> --%>
<script type="text/javascript" src="${ctx}js/logined/notify/list.js"></script>
<script type="text/javascript" src="${ctx}js/common/placeholder.js"></script>
<%-- <link href="${ctx}/flat/css/reset.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/flat/plugins/datatable/skins/datatable_page.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/flat/plugins/datatable/skins/datatable_default.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="${ctx}wh/css/style.css?version=${version}"/> --%>
<link href="${ctx}wh/plugins/datatable/skins/datatable_default.css?version=${version}" rel="stylesheet" type="text/css"/>
<style type="text/css">
	.fButton{border:none}
	/*.searchArea li{padding:4px 0px 4px 0px !important}*/
</style>
</head>
<body>
	<input type="hidden" id="whroletype" value="<s:property value="#session.whroletype"/>"/>
	<input type="hidden" id="columnId" name="columnId" value="${id}"/>
	<div class="bread-line">
		<s:if test="#request.id==1">
		<label>当前位置：</label><a href="#">首页</a>&gt;<a href="#">公告管理</a>&gt;&nbsp;公告发布
		</s:if>
		<s:elseif test="#request.id==2">
		<label>当前位置：</label><a href="#">首页</a>&gt;<a href="#">政策法规</a>&gt;&nbsp;政策法规发布
		</s:elseif>
	</div>
	<div class="list">
	<div class="searchArea">
		<ul>
			<li><label>类型：</label><select id="notifyType"><option value="">全部</option></select></li>
			<li><label>创建时间：</label><input id="beginDate" type="text" class="formText Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm', maxDate: '#F{$dp.$D(\'endDate\')}'})" style="width:140px;"/>-
				<input id="endDate" type="text" class="formText Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm', minDate: '#F{$dp.$D(\'beginDate\')}'})" style="width:140px;"/>
			</li>
			<li><label>关键字 ：</label><input type="text" id="subject" placeholder="标题" class="formText" maxlength="50"/></li>
			<li>
				<input type="button" class="searchButton" value="查询" />
				<s:if test="#session.whroletype == 1">
				<div class="fButton greenBtn"><span class="add" onclick="addNotify();">新增</span></div>
				<div class="fButton orangeBtn"><span class="delete" onclick="deleteAll();">删除</span></div>
				<div class="fButton blueBtn"><span class="setTop" onclick="topSet(1);">置顶</span></div>
				<div class="fButton blueBtn"><span class="cancelTop" onclick="canceltopSet(0);">取消置顶</span></div>
				</s:if>
			</li>
		</ul>
	</div>
<%-- 	<div class="searchArea">
	<input type="hidden" id="columnId" name="columnId" value="${id}"/>	
			<table cellspacing="0" cellpadding="0">
			<tbody>
			   <tr>
			   <td class="right">
				  <label>类型：</label><select id="notifyType">
				  		<option value="">全部</option>
                    </select>          
                  <label>创建时间：</label>
				<input id="beginDate" type="text" class="formText Wdate" onclick="WdatePicker({maxDate: '#F{$dp.$D(\'endDate\')}'})" style="width:110px;"/>-
				<input id="endDate" type="text" class="formText Wdate" onclick="WdatePicker({minDate: '#F{$dp.$D(\'beginDate\')}'})" style="width:110px;"/>  
				<label>关键字 ：</label><span style="position:relative;">
				<input type="text" id="subject" placeholder="标题" class="formText" maxlength="50"/>
				</span>
				<input hideFocus="" value="查 询" class="searchButton" type="button"/>                                
				</td>
			   	<td style="width:400px;"><!-- pxd修改宽度 -->
					<div onclick="addNotify();" class="fButton greenBtn">
						<span class="add">新增</span>
					</div>
					<div onclick="deleteAll();" class="fButton orangeBtn">
						<span class="delete">删除</span>
					</div>
					<div class="btnseparator"></div>
					<div onclick="topSet(1);" class="fButton blueBtn">
						 <div><span class="setTop">置顶</span></div>
					</div>
					<div onclick="canceltopSet(0);" class="fButton blueBtn">
						<div> <span class="cancelTop">取消置顶</span></div>
					</div>
				</td>
				</tr>
				</tbody>
			</table>
	</div> --%>
	<table cellpadding="0" cellspacing="0"  class="pretty dataTable" id="myTable">
		<thead>
		 <tr>
		 	<s:if test="#request.id==1">
			<th class="chk"><input type="checkbox"/></th>
        	<th style="width:100%">标题</th>
       		<th style="width:100px;">类型</th>
        	<th style="width:150px;">创建时间</th>
       	 	<th style="width:90px;">浏览人数</th>
        	<th style="width:250px;">备注</th>
       	 	<th style="width:70px;">状态</th>
        	<th style="width:190px;" class="right_bdr0">操作</th>
			</s:if>
			<s:elseif test="#request.id==2">
        	<th class="chk"><input type="checkbox"/></th>
        	<th style="width:100%">法律、法规及其他要求名称</th>
        	<th style="width:150px;">颁布时间</th>
        	<th style="width:150px;">实施时间</th>
       	 	<th style="width:150px;">颁布部门</th>
       		<th style="width:100px;">类型</th>
       		<!-- <th style="width:70px;">状态</th> -->
        	<th style="width:190px;" class="right_bdr0">操作</th>			
			</s:elseif>
      	</tr>	
		</thead>
	</table>
</div>
<script>funPlaceholder(document.getElementById("subject"));</script>
</body>
</html>
