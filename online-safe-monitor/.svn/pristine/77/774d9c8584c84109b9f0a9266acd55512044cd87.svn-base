<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
request.setAttribute("ctx", basePath);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>公告管理</title>
<link href="${ctx }platform/css/base.css" rel="stylesheet" type="text/css" />
<link href="${ctx }platform/css/public.css" rel="stylesheet" type="text/css" />
<link href="${ctx }platform/css/add_com.css" rel="stylesheet" type="text/css" />
<link href="${ctx }platform/plugins/datatable/table_style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx }platform/js/jquery-1.6.min.js"></script>
<script type="text/javascript" src="${ctx }platform/plugins/dialog/artDialog.js?skin=blue"></script>
<script type="text/javascript" src="${ctx }platform/js/base.js"></script>
<script type="text/javascript" src="${ctx }platform/plugins/dialog/iframeTools.js"></script>
                      
<script>
$(document).ready(function(){
  $(".wrd").click(function(){
    $(".shos").show();
  });

  $("#notenable").click(function(){
	    $(".shos").hide();
	  });
});
</script>

<script type="text/javascript">
var basePath="${ctx }";
</script>

<script type="text/javascript" src="${ctx }platform/js/outcallfun.js"></script>


</head>

<body>

<div class="addcon">
		<div class=" szlist" >
				<div class="select">
						<p>
								<label class="radio" id="notenable">
										<input type="radio" name="isEnableOutcall" value="1" id="isEnableOutcall_1" checked />
										不启用外呼任务</label>
						</p>
						<p>
								<label class="radio wrd">
										<input type="radio" name="isEnableOutcall" value="2" id="isEnableOutcall_2"  />
										启用外呼任务</label>
						</p>
						<div class="ml20 shos" style="display:none" >
						<p>
								<label class="radio">
										<input type="checkbox" name="customScript" value="" id="customScript"  />
										支持自定义脚本
 </label>
						</p>
						
						<p>
								<label class="radio">
										<input type="checkbox" name="customData" value="" id="customData" />
										支持导入自定义资料
 </label>
						</p>
						
						<p>
								<label class="radio ">
										<input type="checkbox" name="definePhone" value="" id="definePhone"  />
										支持号码手动分配
 </label>
						</p>
						<p>
								<label class="radio ">
										<input type="checkbox" name="forecastPhone" value="" id="forecastPhone"  />
										支持预测拨号功能

 </label>
						</p>
				</div>
				</div>
				<div class="buttonArea">
						<input class="formButton_tab" id="saveOutcall" type="submit" value="保 存">
						&nbsp; </div>
		</div>
</div>

</body>
</html>
