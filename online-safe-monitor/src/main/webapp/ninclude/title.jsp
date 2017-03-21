<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<link rel="stylesheet" type="text/css" href="${ctx}wh/jquery-easyui-1.4.3/themes/default/easyui.css?version=${version}"/>
<link rel="stylesheet" type="text/css" href="${ctx}wh/jquery-easyui-1.4.3/themes/icon.css?version=${version}"/>
<link rel="stylesheet" type="text/css" href="${ctx}wh/css/style.css?version=${version}"/>
<link href="${ctx}wh/plugins/datatable/skins/datatable_default.css?version=${version}" rel="stylesheet" type="text/css"/>
<link href="${ctx}wh/plugins/form/skins/form_default.css?version=${version}" rel="stylesheet" type="text/css"/>
<link id="skinCss" href="${ctx}skins/green/green.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx}js/common/jquery-1.8.0.min.js?version=${version}"></script>
<script type="text/javascript" src="${ctx}flat/js/qytx-cbb-v1.0.js"></script>
<!--  dialog -->
<script type="text/javascript" src="${ctx}plugins/upload/jquery.uploadify.min.js" ></script>
<script type="text/javascript" src="${ctx}flat/plugins/dialog/artDialog.js?skin=default"></script>
<script type="text/javascript" src="${ctx}flat/plugins/dialog/artDialog_alert.js"></script>
<script type="text/javascript" src="${ctx}flat/plugins/dialog/iframeTools.js"></script>
<script type="text/javascript" src="${ctx}js/common/artClose.js"></script>
<script type="text/javascript" src="${ctx}wh/js/common.js"></script>
<!--  dialog end -->
<script type="text/javascript" src="${ctx}plugins/My97DatePicker/WdatePicker.js?version=${version}"></script>
<script type="text/javascript" src="${ctx}wh/jquery-easyui-1.4.3/jquery.easyui.min.js?version=${version}"></script>
<script type="text/javascript" src="${ctx}wh/plugins/datatable/jquery.dataTables.min.js?version=${version}"></script>
<script type="text/javascript" src="${ctx}js/common/validate_form.js"></script>
<script type="text/javascript" src="${ctx}js/common/showError.js"></script>

<script type="text/javascript">
	var basePath = "${ctx}";
	var basePathFull = "${ctxFull}";
	var downPath = "${downPath}";
</script>
   <script type="text/javascript">
 	 $(document).ready(function() {
	 $(".l_menu li h2").click(function(){
			if($(this).next().is(":visible"))
					$(this).parent().removeClass("current");
				else
					$(this).parent().addClass("current"); 
	})
	})
   </script>