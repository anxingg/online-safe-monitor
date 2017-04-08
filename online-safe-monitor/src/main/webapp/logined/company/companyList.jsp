<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<jsp:include page="../../common/osmHead.jsp" />
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>企业列表</title>
	<!-- 清除分页cookie start -->
	<script type="text/javascript" src="${ctx}js/common/jquery.cookie.js"></script>
	<!-- 清除分页cookie end -->
	<script type="text/javascript" src="${ctx}js/logined/company/companyList.js?version=${version}"></script>
</head>
<body>
	<div class="list">
		<div class="searchArea">
			<ul>
				<li><label>企业名称：</label>
					<select class="" id="companName">
						<option value="-1">全部</option>
					</select>
				</li>
				<li><input type="button" class="searchButton" value="查询" id="searchCompany"/>
				</li>
				<li></li>
			</ul>
		</div>
		<table id="myTable" cellpadding="0" cellspacing="0" class="pretty dataTable">
			<thead>
				<tr>
					<th style="width:90px;">企业编号</th>
					<th>企业名称</th>
					<th>经济类型</th>
					<th>成立时间</th>
					<th  style="width:190px;">生产场所地址</th>
					<th style="width:150px;">操作</th>
				</tr>
			</thead>
			<tbody></tbody>
		</table>
		
	</div>
	<div class="clear"></div>

</body>
</html>
