<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<jsp:include page="../../common/taglibs.jsp" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>查询统计-话务量综合统计</title>
<jsp:include page="../../common/head.jsp" />
<link href="${ctx}flat/css/reset.css" rel="stylesheet" type="text/css" />
<link href="${ctx}flat/plugins/form/skins/form_default.css" rel="stylesheet" type="text/css" />
<link href="${ctx}flat/plugins/datatable/skins/datatable_default.css" rel="stylesheet" type="text/css" />
<style type="text/css">
.inputTable th{width:82px;}
.searchArea{padding-top:25px;}
</style>

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

<!-- 引用js -->
<script type="text/javascript" src="${ctx}js/logined/report/telephonetraffic.js?version=${version}"></script>
</head>
<body>
<input type="hidden" id="plotBeginTime" value="${param.beginTime}"/>
<input type="hidden" id="plotEndTime" value="${param.endTime}"/>
<div class="formPage">
  <div class="formbg">
    <div class="big_title">话务量综合统计表</div>
    <div class="content_form">
   	  <div class="searchArea">
      <table cellspacing="0" cellpadding="0" >
        <tbody>
          <tr>
            <td class="right">
	            <label  style="padding-left:10px;">时间范围：</label>
	            <input name="" type="text" class="formText Wdate" id="beginTimeStr"  name="beginTimeStr"  onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'endTimeStr\')}',skin:'default',dateFmt:'yyyy-MM-dd'})"/>
	            -
	            <input name="" type="text" class="formText Wdate" id="endTimeStr"  name="beginTimeStr"  onfocus="WdatePicker({minDate:'#F{$dp.$D(\'beginTimeStr\')}',skin:'default',dateFmt:'yyyy-MM-dd'})"/>
	            <!-- <label  style="padding-left:10px;">所属区域：</label>
	            <select id="queue"><option value="-1">全部</option></select> -->
	            <input type="button" onclick="getTableData()" class="searchButton" value="查询"/>
             </td>
             <td style="width:204px; ">
             	<div id="trafficExport" class="fButton blueBtn" style="margin-left:10px;">
               	<div> 
               		<span class="export">导出</span> 
               	</div>
             	</div>
             	<div id="trafficPlot" class="fButton blueBtn" style="margin-left:10px;">
               	<div> 
               		<span class="">图表视图</span> 
               	</div>
             	</div>
             </td>
          </tr>
        </tbody>
      </table>
    </div>
      	<!--列表区-->
		<div class="pl10 pr10">
			<table id="teleTrafficTable"  cellspacing="0" cellpadding="0" class="pretty">
	
			</table>
		</div>
    </div>
  </div>
</div>
</body>
</html>
