<%@ page language="java" import="java.util.*"  contentType="text/html; charset=UTF-8" pageEncoding="utf-8"  %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<jsp:include page="../../../common/taglibs.jsp"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<jsp:include page="../head.jsp" />
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>安全生产费用提取</title>
	<link href="${ctx}wh/plugins/datatable/skins/datatable_default.css?version=${version}" rel="stylesheet" type="text/css"/>
	<script type="text/javascript">
		var basePath = "${ctx}";
	</script>
	<!-- 清除分页cookie start -->
	<script type="text/javascript" src="${ctx}js/common/jquery.cookie.js"></script>
	<!-- 清除分页cookie end -->
	
	<script type="text/javascript" src="${ctx}js/common/placeholder.js"></script>
	<script type="text/javascript" src="${ctx}wh/js/fee/feeExtractList.js"></script>
    
</head>
<body>
	<!-- 隐藏域：角色 -->
	<input type="hidden" id="whroletype" value='<s:property value="#session.whroletype"/>' />
	<!-- 隐藏域：企业ID -->
	<input type="hidden" id="group_id" value='<s:property value="#session.adminUser.groupId"/>' />
	
	<div class="bread-line">
		<label>当前位置：</label><a href="#">安全生产费用提取</a>&gt;&nbsp;安全生产费用提取列表
	</div>
	<div class="list">
		<div class="searchArea">
			<ul>
				<s:if test="#session.whroletype != 2">
				<li><label>企业名称：</label>
					<select id="companName">
						<option value="0">请选择</option>
						<s:iterator value="companyMap" id="entry">
							<s:if test="companyDangerChemicals.groupId == key">
							<option value="<s:property value="key"/>" selected="selected"><s:property value="value"/></option>
							</s:if>
							<s:else>
							<option value="<s:property value="key"/>"><s:property value="value"/></option>
							</s:else>
						</s:iterator> 
					</select>
				</li>
				</s:if>
				<li><label>提取时间：</label>
					<input id=searchTimeStart type="text" class="formText Wdate" style="width: 120px;"
             		onclick="WdatePicker({skin:'default',dateFmt:'yyyy-MM-dd',maxDate :'#F{$dp.$D(\'searchTimeEnd\')}'})" />
				-
					<input id="searchTimeEnd" type="text" class="formText Wdate" style="width: 120px;"
             		onclick="WdatePicker({skin:'default',dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'searchTimeStart\')}'})" />
				</li>
				<li><input type="button" class="searchButton" value="查询" /><s:if test="#session.whroletype == 2"><div class="fButton greenBtn"> <span class="add" onclick="addOne();">新增</span></div></s:if></li>
			</ul>
		</div>
		<table cellpadding="0" cellspacing="0" class="pretty dataTable" id="myTable">
			<thead>
				<tr>
					<th style="width:70px;">序号</th>
					<th>企业名称</th>
					<th>提取标准</th>
					<th>提取时间</th>
					<th>上年度营业额（万元）</th>
					<th>本次提取（万元）</th>
					<th>结存金额（万元）</th>
				</tr>
			</thead>
			<tbody>
			</tbody>
		</table>
	</div>
	<div class="clear"></div>
</body>
</html>
