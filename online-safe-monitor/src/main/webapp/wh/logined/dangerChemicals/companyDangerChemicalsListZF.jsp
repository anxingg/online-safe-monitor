<%@ page language="java" import="java.util.*"  contentType="text/html; charset=UTF-8" pageEncoding="utf-8"  %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<jsp:include page="../../../common/taglibs.jsp"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<jsp:include page="../head.jsp" />
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>危险化学品</title>
	<%-- <link rel="stylesheet" type="text/css" href="${ctx}wh/jquery-easyui-1.4.3/themes/default/easyui.css"/> --%>
	<%-- <link rel="stylesheet" type="text/css" href="${ctx}wh/jquery-easyui-1.4.3/themes/icon.css"/> --%>
	<%-- <link rel="stylesheet" type="text/css" href="${ctx}wh/css/style.css"/> --%>
	<%-- <link rel="stylesheet" type="text/css" href="${ctx}wh/plugins/datatable/skins/datatable_default.css" /> --%>
	<link href="${ctx}wh/plugins/datatable/skins/datatable_default.css?version=${version}" rel="stylesheet" type="text/css"/>
	<script type="text/javascript">
		var basePath = "${ctx}";
	</script>
	<%-- <script type="text/javascript" src="${ctx}wh/jquery-easyui-1.4.3/jquery.min.js"></script> --%>
	<%-- <script type="text/javascript" src="${ctx}wh/jquery-easyui-1.4.3/jquery.easyui.min.js"></script> --%>
	<%-- <script type="text/javascript" src="${ctx}wh/plugins/datatable/jquery.dataTables.min.js"></script> --%>
	<%-- <script type="text/javascript" src="${ctx}wh/js/safeAccident/initType.js"></script> --%>
	<!-- 清除分页cookie start -->
	<script type="text/javascript" src="${ctx}js/common/jquery.cookie.js"></script>
	<!-- 清除分页cookie end -->
	<!-- 附件下载 start -->
	<script type="text/javascript" src="${ctx}wh/js/safeAccident/downloadAttachment.js"></script>
	<!-- 附件下载 end -->
	<script type="text/javascript" src="${ctx}wh/js/dangerChemicals/companyDangerChemicalsListZF.js"></script>
    
</head>
<body>
	<!-- 隐藏域：角色 -->
	<input type="hidden" id="whroletype" value='<s:property value="#session.whroletype"/>' />
	<!-- 隐藏域：企业ID -->
	<input type="hidden" id="group_id" value='<s:property value="#session.adminUser.groupId"/>' />
	<!-- 隐藏域：危化品ID -->
	<input type="hidden" id="dangerId" value='<s:property value="companyDangerChemicals.dangerId"/>' />
	
	<div class="bread-line">
		<label>当前位置：</label><a href="#">危险化学品</a>&gt;&nbsp;危险化学品列表
	</div>
	<div class="list">
		<div class="searchArea">
			<ul>
				<li><label>品名：</label><input type="text" class="formText" id="materialName" maxlength="32" value="<s:property value="companyDangerChemicals.materialName"/>" /></li>
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
				<li><input type="button" class="searchButton"  value="查询" /></li>
			</ul>
		</div>
		<!-- <div style="overflow:auto"> -->
		<table cellpadding="0" cellspacing="0" class="pretty dataTable" id="myTable">
			<thead>
				<tr>
					<th style="width:70px;">序号</th>
					<th>品名</th>
					<th>企业名称</th>
					<th>存放地点</th>
					<th style="width:70px;">数量</th>
					<!-- <th>使用地点</th>
					<th>危险性分类</th>
					<th>危规号</th> -->
					<th>包装类别</th>
					<!-- <th>登记号</th>
					<th>技术说明书</th>
					<th>安全标签</th> -->
					<th>备注</th>
					<!-- <th style="width:115px;">操作</th> -->
				</tr>
			</thead>
			<tbody>
<!-- 				<tr class="odd">
					<td>A01B01</td>
					<td>这是项目名称</td>
					<td>2015-06-10 15:26</td>
					<td class="longTxt">本部门生产装置储存场所</td>
					<td class="longTxt">这是检查目的内容</td>
					<td>通过</td>
					<td>王心卫</td>
					<td class="longTxt">需再次检查</td>
					<td class="right_bdr0"><a href="#">修改</a></td>
				</tr>
				<tr class="even">
					<td>A01B01</td>
					<td>这是项目名称</td>
					<td>2015-06-10 15:26</td>
					<td class="longTxt">本部门生产装置储存场所</td>
					<td class="longTxt">这是检查目的内容</td>
					<td>通过</td>
					<td>王心卫</td>
					<td class="longTxt">需再次检查</td>
					<td class="right_bdr0"><a href="#">修改</a></td>
				</tr> -->
			</tbody>
		</table>
		<%-- <div class="dataTables_info" id="Table_info">共 197 条数据 </div>
		<div class="dataTables_paginate paging_full_numbers" id="Table_paginate">
			<a class="previous paginate_button paginate_button_disabled" tabindex="0" id="Table_previous">«</a>
			<span><a class="paginate_active" tabindex="0">1</a><a class="paginate_button" tabindex="0">2</a><a class="paginate_button" tabindex="0">3</a><a class="paginate_button" tabindex="0">4</a><a class="paginate_button" tabindex="0">5</a></span>
			<a class="next paginate_button" tabindex="0" id="Table_next">»</a>
		</div> --%>
    	<!-- </div> -->		
	</div>
	<div class="clear"></div>

</body>
</html>
