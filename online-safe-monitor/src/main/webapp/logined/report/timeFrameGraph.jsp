<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="../../common/taglibs.jsp" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	    <title>查询统计-来电时段分布图表</title>
	    <jsp:include page="../../common/head.jsp"/>
		<link href="${ctx }common/css/reset.css" rel="stylesheet" type="text/css" />
		<link href="${ctx }common/plugins/form/skins/form_default.css" rel="stylesheet" type="text/css" />
		<link href="${ctx }common/plugins/annex/skins/annex_default.css" rel="stylesheet" type="text/css" />
		<link href="${ctx }common/plugins/tree/skins/tree_default.css" rel="stylesheet" type="text/css" />
	    <!-- 柱状图开始 -->
		<link rel="stylesheet" type="text/css" href="${ctx}plugins/jqplot/jquery.jqplot.min.css" />	
		<script type="text/javascript" src="${ctx}plugins/jqplot/excanvas.min.js"></script>
		<script type="text/javascript" src="${ctx}plugins/jqplot/jquery.jqplot.min.js"></script>
		<script type="text/javascript" src="${ctx}plugins/jqplot/plugins/jqplot.barRenderer.min.js"></script>
		<script type="text/javascript" src="${ctx}plugins/jqplot/plugins/jqplot.pieRenderer.min.js"></script>
		<script type="text/javascript" src="${ctx}plugins/jqplot/plugins/jqplot.categoryAxisRenderer.min.js"></script>
		<script type="text/javascript" src="${ctx}plugins/jqplot/plugins/jqplot.pointLabels.min.js"></script>
		<script type="text/javascript" src="${ctx}plugins/jqplot/plugins/jqplot.dateAxisRenderer.min.js"></script>
		<script type="text/javascript" src="${ctx}plugins/jqplot/plugins/jqplot.canvasTextRenderer.min.js"></script>
		<script type="text/javascript" src="${ctx}plugins/jqplot/plugins/jqplot.canvasAxisTickRenderer.min.js"></script>
		<!-- 柱状图结束 -->
		<!-- 展示图表的js -->
		<script type="text/javascript" src="${ctx}js/common/jqplotAuto.js?version=${version}"></script>
		
		<script type="text/javascript" src="${ctx}js/logined/report/timeFrameGraph.js?version=${version}"></script>
	</head>
	<body>
		<!-- 从列表页面传过来的查询开始时间 -->
		<input type="hidden" id="beginTime" value="${paramValues.beginTime[0]}" />
		<!-- 从列表页面传过来的查询结束时间 -->
		<input type="hidden" id="endTime" value="${paramValues.endTime[0]}" />
		
		<div class="formPage">
			<div class="formbg">
		  
				<div class="content_form">
					<table width="100%" border="0" cellpadding="0" cellspacing="0"  class="inputTable">
						<tr>
							<th><label  style="padding-left:10px;">时间范围：</label></th>
							<td>
								<input name="" type="text" class="formText Wdate" id="beginTimeStr"  name="beginTimeStr"  onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'endTimeStr\')}',skin:'default',dateFmt:'yyyy-MM-dd',isShowClear:false,readOnly:true})"/>
								-
								<input name="" type="text" class="formText Wdate" id="endTimeStr"  name="beginTimeStr"  onfocus="WdatePicker({minDate:'#F{$dp.$D(\'beginTimeStr\')}',skin:'default',dateFmt:'yyyy-MM-dd',isShowClear:false,readOnly:true})"/>
							</td>
							<th><label>图表类型：</label></th>
							<td>
								<label class="radio"><input type="radio" value="0" name="plotType" checked="checked" />柱状图</label>
								<label class="radio"><input type="radio" value="1" name="plotType" />折线图</label>
							</td>
						</tr>
						<tr>
							<th><label>显示数据：</label></th>
							<td colspan="3">
								<label class="radio"><input type="checkbox" value="呼入总量" name="colName" />呼入总量</label>
								<label class="radio"><input type="checkbox" value="人工受理" name="colName" />人工受理</label>
								<label class="radio"><input type="checkbox" value="放弃" name="colName" />放弃</label>
								<label class="radio"><input type="checkbox" value="自动查询" name="colName" />自动查询</label>
							</td>
						</tr>
					</table>
				</div>
			    
				<div class="buttonArea" style="text-align:center">
					<input type="button" class="formButton_green" value="生成图表"  onclick="generateGraph();"/>
					<input type="button" class="formButton_grey" value="列表视图" onclick="toListTable();" />
				</div>
				
				<!-- 图标 -->
				<div class="list" id="chartDiv" style="width:980px; margin:auto; overflow: hidden">
					<h3 class="small_title"></h3>
					<div id="chart" style="margin-top:20px; margin-left:20px; height:350px;"></div>
				</div>
			</div>
		</div>
	</body>
</html>