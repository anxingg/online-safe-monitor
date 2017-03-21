<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html><head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="<%=request.getContextPath()%>/css/base.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/common/css/public.css" rel="stylesheet" type="text/css" />
<link type="text/css"
	href="<%=request.getContextPath()%>/logined/workflow/jsp/myflow/lib/jquery-ui-1.8.4.custom/css/smoothness/jquery-ui-1.8.4.custom.css"
	rel="stylesheet" />

<style type="text/css">
body {
	margin: 0;
	pading: 0;
	text-align: left;
	font-family: Arial, sans-serif, Helvetica, Tahoma;
	font-size: 12px;
	line-height: 1.5;
	color: black;
	background-image: url(<%=request.getContextPath()%>/logined/workflow/jsp/myflow/img/bg.png);
}

.node {
	width: 70px;
	text-align: center;
	vertical-align: middle;
	border: 1px solid #fff;
}

.mover {
	border: 1px solid #ddd;
	background-color: #ddd;
}

.selected {
	background-color: #ddd;
}

.state {
	
}

#myflow_props table {
	
}

#myflow_props th {
	letter-spacing: 2px;
	text-align: left;
	padding: 6px;
	background: #ddd;
}

#myflow_props td {
	background: #fff;
	padding: 6px;
}

#pointer {
	background-repeat: no-repeat;
	background-position: center;
}

#path {
	background-repeat: no-repeat;
	background-position: center;
}

#task {
	background-repeat: no-repeat;
	background-position: center;
}

#state {
	background-repeat: no-repeat;
	background-position: center;
}
</style>
</head>
<body style="cursor: auto;">
<input type="hidden" id="proName" value="<%=request.getContextPath()%>"/>
<input type="hidden" id="processAttributeId" value="${process.id}"/>
<input type="hidden" id="processAttributeName" value="${process.processName}"/>
<input type="hidden" id="processAttributeDirections" value="${process.directions}"/>
<input type="hidden" id="formId" value="${process.formId}"/>
<input type="hidden" id="jsonData" value="${process.procsssDefinByJSON}" />
<input type="hidden" id="hiddenpaths" />
<div id="myflow_tools" style="position: absolute; background-color: rgb(255, 255, 255); width: 70px; cursor: default; padding: 3px; left: 32px; top: 50px;" class="ui-widget-content ui-draggable">
<div id="myflow_tools_handle" style="text-align: center;" class="ui-widget-header">工具集</div>


<div class="node" id="myflow_save"><img src="<%=request.getContextPath() %>/logined/workflow/jsp/myflow/black_files/save.gif">&nbsp;&nbsp;保存</div>
<div class="node" id="myflow_close"><img src="<%=request.getContextPath() %>/logined/workflow/jsp/myflow/img/16/delete.gif">&nbsp;&nbsp;关闭</div>
<div>
<hr>
</div>
<div class="node selectable" id="pointer"><img src="<%=request.getContextPath() %>/logined/workflow/jsp/myflow/black_files/select16.gif">&nbsp;&nbsp;选择</div>
<div class="node selectable" id="path"><img src="<%=request.getContextPath() %>/logined/workflow/jsp/myflow/black_files/flow_sequence.png">&nbsp;&nbsp;转换</div>
<div>
<hr>
</div>
<div class="node state" id="start" type="start"><img
	src="<%=request.getContextPath() %>/logined/workflow/jsp/myflow/img/16/start_event_empty.png" />&nbsp;&nbsp;开始</div>

<div class="node state" id="task" type="task"><img
	src="<%=request.getContextPath() %>/logined/workflow/jsp/myflow/img/16/task_empty.png" />&nbsp;&nbsp;任务</div>
<!--  
<div class="node state" id="mutilSign" type="mutilSign"><img
	src="<%=request.getContextPath() %>/logined/workflow/jsp/myflow/img/16/task_empty.png" />&nbsp;&nbsp;会签</div>
-->	
<!-- <div class="node state" id="fork" type="fork"><img
	src="<%=request.getContextPath() %>/logined/workflow/jsp/myflow/img/16/gateway_parallel.png" />&nbsp;&nbsp;分支</div>
<div class="node state" id="join" type="join"><img
	src="<%=request.getContextPath() %>/logined/workflow/jsp/myflow/img/16/gateway_parallel.png" />&nbsp;&nbsp;合并</div> -->

<!-- <div class="node state" id="decision" type="decision">
	<img src="<%=request.getContextPath() %>/logined/workflow/jsp/myflow/img/16/gateway_exclusive.png" />&nbsp;&nbsp;判断
</div> -->

<div class="node state" id="end" type="end"><img
	src="<%=request.getContextPath() %>/logined/workflow/jsp/myflow/img/16/end_event_terminate.png" />&nbsp;&nbsp;结束</div>

</div>

<div id="myflow_props" style="position: absolute; background-color: rgb(255, 255, 255); width: 320px; padding: 3px; display: block; top: 10px; right: 30px;" class="ui-widget-content ui-draggable ui-resizable">

<div id="myflow_props_handle" class="ui-widget-header">属性</div>
<table border="1" cellpadding="0" cellspacing="0" width="100%" style="table-layout:auto"><tbody><tr><th>名称</th><td><div id="pname" class="editor"><input value="新建流程" style="width:100%;"></div></td></tr><tr><th>标识</th><td><div id="pkey" class="editor"><input style="width:100%;"></div></td></tr><tr><th>描述</th><td><div id="pdesc" class="editor"><input style="width:100%;"></div></td></tr></tbody></table>
<div>&nbsp;</div>
<div style="-moz-user-select: none;" unselectable="on" class="ui-resizable-handle ui-resizable-e"></div><div style="-moz-user-select: none;" unselectable="on" class="ui-resizable-handle ui-resizable-s"></div><div unselectable="on" style="z-index: 1001; -moz-user-select: none;" class="ui-resizable-handle ui-resizable-se ui-icon ui-icon-gripsmall-diagonal-se"></div></div>

<div class="ui-droppable" id="myflow"><svg height="2000px" width="2160px" version="1.1" xmlns="http://www.w3.org/2000/svg"><desc></desc><defs></defs></svg></div>

</body>
	<script type="text/javascript" src="<%=request.getContextPath()%>/logined/workflow/jsp/myflow/lib/raphael-min.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/logined/workflow/jsp/myflow/lib/jquery-ui-1.8.4.custom/js/jquery-1.4.2.min.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/logined/workflow/jsp/myflow/lib/jquery-ui-1.8.4.custom/js/jquery-ui-1.8.4.custom.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/plugins/dialog/artDialog4.1.6/artDialog.js?skin=blue"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/plugins/dialog/artDialog4.1.6/iframeTools.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/logined/workflow/jsp/myflow/myflow.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/logined/workflow/jsp/myflow/myflow.jpdl4.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/logined/workflow/jsp/myflow/myflow.editors.js"></script>
<script type="text/javascript"  Charset="UTF-8"  src="<%=request.getContextPath()%>/logined/workflow/js/workflow.js" >
</script>
</html>