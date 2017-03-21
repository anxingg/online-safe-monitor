<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="../../common/taglibs.jsp" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>查询统计-工作汇总表</title>
<jsp:include page="../../common/head.jsp" />
<link href="${ctx}common/css/reset.css" rel="stylesheet" type="text/css" />
<link href="${ctx}common/plugins/datatable/skins/datatable_default.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx}common/js/base.js"></script>
<script type="text/javascript" src="${ctx}plugins/datatable/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="${ctx}common/plugins/datatable/selecedForDatatablePagination.js"></script>
<script type="text/javascript" src="${ctx}common/plugins/dialog/artDialog.js?skin=default"></script>
<script type="text/javascript" src="${ctx}common/plugins/dialog/iframeTools.js"></script>
<script type="text/javascript" src="${ctx}common/is/placeholder.js"></script>
<!-- 引用js -->
<script type="text/javascript" src="${ctx}js/logined/report/workSummary.js?version=${version}"></script>
</head>
<body>
<input type="hidden" id="loginIsFork" value="${loginIsFork}"/>
<div class="list">
  <div class="searchArea">
    <table cellspacing="0" cellpadding="0">
      <tbody>
        <tr>
          <td class="right">
            <!-- <label id="localCity" style="display: none">所属地市：</label>
	            <select id="isForkGroup" style="display: none">
					<option selected="selected" value="-1">全部</option>
				</select> -->
			<label>统计时间：</label>
            <input id="beginTime" type="text" class="formText Wdate" style="width:120px;"
             onfocus="WdatePicker({skin:'default',dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'endTime\')}'})" />
			&nbsp;-&nbsp;
			<input id="endTime" type="text" class="formText Wdate" style="width:120px;"
			 onfocus="WdatePicker({skin:'default',dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'beginTime\')}'})" />
            <input type="button" id="search" class="searchButton" value="查询"/></td>
          <td style="width:92px;"><div class="fButton greenBtn" onclick="exporting();"> <span  class="export">导出</span> </div></td>
        </tr>
      </tbody>
    </table>
  </div>
  <!--列表区-->
  <div class="dataTables_wrapper" role="grid">
	<div class="dataTables_processing" id="timeFrameTable_processing" style="visibility: hidden;">正在加载数据...</div>
  <table width="100%" cellpadding="0" border="0" cellspacing="0" class="pretty" id="myTable">
  </table>
  </div>
  <div class="clear"></div>
</div>
<script>funPlaceholder(document.getElementById("searchkey"));</script>
</body>
</html>
