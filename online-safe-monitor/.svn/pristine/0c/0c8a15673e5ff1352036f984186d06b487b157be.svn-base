<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<jsp:include page="../../common/taglibs.jsp" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>排队统计</title>
<jsp:include page="../../common/head.jsp" />
<link href="${ctx}flat/css/reset.css" rel="stylesheet" type="text/css" />
<link href="${ctx}flat/plugins/form/skins/form_default.css" rel="stylesheet" type="text/css" />
<link href="${ctx}flat/plugins/datatable/skins/datatable_default.css" rel="stylesheet" type="text/css" />
<style type="text/css">
.inputTable th{width:82px;}
.searchArea{padding-top:25px;}
</style>

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

<script type="text/javascript" src="${ctx}js/logined/report/queueStatistics.js?version=${version}"></script>
<script type="text/javascript" src="${ctx}js/logined/seat/initAcceptedGroup.js?version=${version}"></script>

<body>
<div class="formPage">
  <div class="formbg">
    <div class="big_title">排队统计</div>
    <div class="content_form">
   	  <div class="searchArea">
      <table cellspacing="0" cellpadding="0" width="100%">
        <tbody>
          <tr>
            <td class="right" >
              <label  style="padding-left:10px;">日期：</label>
              <input id="day" class="formText Wdate" style="width:110px;" type="text" onfocus="WdatePicker({skin:'default',dateFmt:'yyyy-MM-dd'})"/>
			   <label style="padding-left:10px;">时间段：</label>
               <select id="startHour">
					<option selected="selected">00</option>
					<option>01</option>
					<option>02</option>
					<option>03</option>
					<option>04</option>
					<option>05</option>
					<option>06</option>
					<option>07</option>
					<option>08</option>
					<option>09</option>
					<option>10</option>
					<option>11</option>
					<option>12</option>
					<option>13</option>
					<option>14</option>
					<option>15</option>
					<option>16</option>
					<option>17</option>
					<option>18</option>
					<option>19</option>
					<option>20</option>
					<option>21</option>
					<option>22</option>
					<option>23</option>
				</select>
              -
               <select id="endHour">
				<option>00</option>
				<option>01</option>
				<option>02</option>
				<option>03</option>
				<option>04</option>
				<option>05</option>
				<option>06</option>
				<option>07</option>
				<option>08</option>
				<option>09</option>
				<option>10</option>
				<option>11</option>
				<option>12</option>
				<option>13</option>
				<option>14</option>
				<option>15</option>
				<option>16</option>
				<option>17</option>
				<option>18</option>
				<option>19</option>
				<option>20</option>
				<option>21</option>
				<option>22</option>
				<option selected="selected">23</option>
			  </select>
              <!-- <label  style="padding-left:10px;">所属区域：</label>
              <select id="queue"><option value="-1">全部</option></select> -->
              <input type="button" id="search" class="searchButton" value="查询"/>
              </td>
              <td style="width:102px; "><div class="fButton blueBtn" style="margin-left:10px" id="trafficExport">
                <span  class="export">导出</span>
              </div>
              </td>
          </tr>
        </tbody>
      </table>
    </div>
   	  <h3 class="small_title">折线图</h3>
        <div class="list" id="chartDiv" style="width:850px; margin:auto; overflow: hidden">
        <h3 class="small_title">
		</h3>
		<div id="chart" style="margin-top:20px; margin-left:20px; height:350px;"></div>
	</div>
      <h3 class="small_title">数据表</h3>
		<table id="teleTrafficTable"  cellspacing="0" cellpadding="0" class="pretty">
		</table>
    </div>
  </div>
</div>
</body>
</html>
