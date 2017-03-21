<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<jsp:include page="../../common/taglibs.jsp" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>行政区域</title>
		<jsp:include page="../../common/head.jsp" />
		<link href="${ctx}common/css/reset.css" rel="stylesheet" type="text/css" />
		<link href="${ctx}common/plugins/form/skins/form_default.css" rel="stylesheet" type="text/css" />
		<link href="${ctx}common/plugins/annex/skins/annex_default.css" rel="stylesheet" type="text/css" />
		<link href="${ctx}common/css/authority.css" rel="stylesheet" type="text/css" />
		<link href="${ctx}common/plugins/datatable/datatable_page.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript">
			var basePath = '${ctx}';
		</script>
		<script type="text/javascript" src="${ctx}js/placeholder.js"></script>
		<script type="text/javascript" src="${ctx}flat/plugins/dialog/iframeTools.js"></script>
		<!-- fiveGradeArea_areaManage.js 这个js用来页面中行政区域的显示 -->
		<script type="text/javascript" src="${ctx}/js/logined/area/fiveGradeArea_areaManage.js"></script>
		<!-- 这个页面的主js -->
		<script type="text/javascript" src="${ctx}/js/logined/area/areaManage.js"></script>
	</head>
	<body>
	<div class="anthority">
			<table width="100%" border="0" cellpadding="10" cellspacing="10" class="addinlist">
				<tr>
						<td style="width:20%"><div class="addres_list">
										<h2>省、自治区、 直辖市</h2>
										<ul id="fiveGradeAreaContentProvince">
												
										</ul>
								</div></td>
						<td style="width:20%"><div class="addres_list">
										<h2><a style="display:none;" class="add_ans fr" href="javascript:;" onclick="addArea(this);" name="city" style=" cursor:pointer;" >新增</a>地市</h2>
										<ul id="fiveGradeAreaContentCity">
												
										</ul>
								</div></td>
						<td style="width:20%"><div class="addres_list">
										<h2><a style="display:none;" class="add_ans fr" href="javascript:;" onclick="addArea(this);" name="county" style=" cursor:pointer;" >新增</a>县区</h2>
										<ul id="fiveGradeAreaContentCounty">
												
										</ul>
								</div></td>
						<td style="width:20%"><div class="addres_list">
										<h2><a style="display:none;" class="add_ans fr" href="javascript:;" onclick="addArea(this);" name="town" style=" cursor:pointer;" >新增</a>乡镇</h2>
										<ul id="fiveGradeAreaContentTown">
												
										</ul>
								</div></td>
						<td style="width:20%"><div class="addres_list">
										<h2><a style="display:none;" class="add_ans fr" href="javascript:;" onclick="addArea(this);" name="village" style=" cursor:pointer;" >新增</a>行政村</h2>
										<ul id="fiveGradeAreaContentVillage">
												
										</ul>
								</div></td>
				</tr>
		</table>
	</div>
	</body>
</html>