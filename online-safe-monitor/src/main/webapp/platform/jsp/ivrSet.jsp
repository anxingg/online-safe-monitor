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


<script type="text/javascript" src="${ctx }platform/js/ivrset.js"></script>
<script type="text/javascript">
var basePath="${ctx }";
</script>

</head>

<body>

<div class="addcon">
		<h3>IVR和ACD功能 </h3>
		<div class="szlist select">
				<p>
						<label class="radio ">
								<input type="checkbox" name="isSeatRecord" value="" id="isSeatRecord"  />
								坐席录音开关 </label>
				</p>
				<p>
						<label class="radio ">
								<input type="checkbox" name="isCustomVoice" value="" id="isCustomVoice"  />
								支持自定义语音流程 </label>
				</p>
				<p>
						<label class="radio ">
								<input type="checkbox" name="remotePhoneZero" value="" id="remotePhoneZero"  />
								外呼异地手机时加拨0 </label>
				</p>
				<p>
						<label class="radio ">
								<input type="checkbox" name="isLeaveMessage" value="" id="isLeaveMessage"  />
								留言功能 </label>
				</p>
				<p>
						<label class="radio ">
								<input type="checkbox" name="isSatifyCheck" value="" id="isSatifyCheck"  />
								满意度调查 </label>
				</p>
				<p>
						<label class="radio ">
								<input type="checkbox" name="isMultiAccessNum" value="" id="isMultiAccessNum"  />
								支持多接入号 </label>
				</p>
				<p>
						<label class="radio ">
								<input type="checkbox" name="isBlack" value="" id="isBlack"  />
								支持黑名单功能 </label>
				</p>
		</div>
		<div class="buttonArea">
				<input class="formButton_tab" id="saveIVRSet" type="submit" value="保 存">
				&nbsp; </div>
</div>


</body>
</html>
