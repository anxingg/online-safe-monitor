<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<jsp:include page="../../common/taglibs.jsp" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>行政区域新增</title>
		<jsp:include page="../../common/head.jsp" />
		<link href="${ctx}common/css/reset.css" rel="stylesheet" type="text/css" />
		<link href="${ctx}common/plugins/form/skins/form_default.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="${ctx}flat/plugins/dialog/iframeTools.js"></script>
		<!-- 验证start -->
		<script type="text/javascript" src="${ctx}common/js/js_lang_cn.js"></script>
	    <script type="text/javascript" src="${ctx}js/common/validate_form.js"></script>
		<script type="text/javascript" src="${ctx}js/common/showError.js"></script>
	    <!-- 验证end -->
		<script type="text/javascript">
			$(document).ready(function(){
				$("#areaName").focus();
				var grade=art.dialog.data("grade");
				if(grade=="city"){
					$("#mingcheng").html("地市名称：");
				}else if(grade=="county"){
					$("#mingcheng").html("县区名称：");
				}else if(grade=="town"){
					$("#mingcheng").html("乡镇名称：");
				}else if(grade=="village"){
					$("#mingcheng").html("行政村名称：");
				}
			});
			function addSubmit(){
				var valid=validator($("#addAreaForm")[0]);
				if(valid){
					var obj=art.dialog.data("obj");
					var code=art.dialog.data("code");
					var areaName=$("#areaName").val();
					var grade=art.dialog.data("grade");
					var orderNum=$("#orderNum").val();
					var isRealName="y";
					var newArea=art.dialog.data("newAreaFun");
					newArea(obj,code,areaName,grade,orderNum,isRealName);
					art.dialog.close();
				}
			}
		</script>
	</head>
	<body class="bg_white">
		<form id="addAreaForm">
			<div class="elasticFrame formPage">
				<table border="0" cellpadding="0" cellspacing="0"  class="inputTable">
					<tr>
						<th><label id="mingcheng">地市名称</label></th>
						<td><input name="" type="text" class="formText" id="areaName" maxlength="20" valid="required" errmsg="area.areaName_not_null"/></td>
					</tr>
					<tr>
						<th><label>排序号：</label></th>
						<td><input name="" type="text" class="formText" id="orderNum" maxlength="3" valid="required" errmsg="area.orderNum_not_null"  onKeyUp="value=value.replace(/[^\d]/g,'')" onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))"/></td>
					</tr>
				</table>
			</div>
		</form>
	</body>
</html>