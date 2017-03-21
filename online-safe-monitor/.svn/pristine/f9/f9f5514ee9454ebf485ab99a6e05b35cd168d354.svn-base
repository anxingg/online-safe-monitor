<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="../../common/taglibs.jsp" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>查询统计-坐席工作量</title>
<jsp:include page="../../common/head.jsp" />
<link href="${ctx}common/css/reset.css" rel="stylesheet" type="text/css" />
<link href="${ctx}common/plugins/datatable/skins/datatable_default.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx}common/js/base.js"></script>
<script type="text/javascript" src="${ctx}plugins/datatable/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="${ctx}common/plugins/datatable/selecedForDatatablePagination.js"></script>
<script type="text/javascript" src="${ctx}common/plugins/dialog/artDialog.js?skin=default"></script>
<script type="text/javascript" src="${ctx}common/plugins/dialog/iframeTools.js"></script>
<script type="text/javascript" src="${ctx}common/is/placeholder.js"></script>

<script type="text/javascript" src="${ctx}js/logined/seat/initAcceptedGroup.js?version=${version}"></script>
<script type="text/javascript" src="${ctx}js/logined/seat/selectSeatMutile.js?version=${version}"></script>
<script type="text/javascript" src="${ctx}js/common/hashmap.js"></script>
<script type="text/javascript" src="${ctx}js/common/treeNode.js"></script>

<!-- 引用js -->
<script type="text/javascript" src="${ctx}js/logined/report/seatTaskStatistics.js?version=${version}"></script>
</head>
<body>
<!-- 前后台区分 -->
<input type="hidden" id="fromPage" value="${param.fromPage }" />

<!-- granularity 这个以前的一个颗粒度的查询项，一个日颗粒度，一个时颗粒度。现在没有了，所以，把这个值放隐藏域中，值设为1。 -->
<input id="granularity" type="hidden"   class="formText" value='1'/>

<div class="list">
	<div class="searchArea">
        <table cellspacing="0" cellpadding="0">
              <tbody>
              <tr>
                <td class="right">
                 <input id="workIds" type="hidden"    class="formText" value=''/>
     				<!-- <label>坐席人员：</label>
                    
				  	 <input id="workIds2" type="hidden"   class="formText" value=''/>
                   <em id="treeContent">
                        <input id="workName" type="text" style="width: 120px;" 
                        class="formText iconTree" size="30"/>
                        <span class="selectdot" id="groupSel_div"></span>
                        <div id="menuContent" style="position: absolute; ">
                            <p id="industryTree" class="ztree"
                                style="position: absolute;z-index:1; margin-top: 0; width: 186px;height:150px;overflow:auto; background: #ffffff;  border: 1px solid #8a9ba5"></p>
                        </div>
                    </em>  -->
                   <%--  <c:if test="${param.fromPage!='seat' }">
	                    <label>队列：</label>
						<select id="queueId">
						</select>
					</c:if> --%>
     				<label>时间范围：</label>
					<input id="beginTime" type="text" class="formText Wdate" style="width:165px"onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'endTime\')}',skin:'default',dateFmt:'yyyy-MM-dd HH:mm:ss'})" />
					&nbsp;-&nbsp;
					<input id="endTime" type="text" class="formText Wdate" style="width:165px" onfocus="WdatePicker({minDate:'#F{$dp.$D(\'beginTime\')}',skin:'default',dateFmt:'yyyy-MM-dd HH:mm:ss'})" />
					<c:if test="${param.fromPage!='seat' }">
	                <label>关键字：</label>
                   <em id="treeContent1">
                        <input id="workName1" type="text" style="width: 120px;" class="formText searchkey" placeholder="坐席姓名" maxlength="30"/>
                    </em> 
                    </c:if>
	                <input type="button" id="search" class="searchButton" value="查询"/>
                </td>
                <c:if test="${param.fromPage!='seat' }">
	                <td style="width:100px;">
	                    <div class="fButton greenBtn" onclick="exporting();">
	                      <span class="export">导出</span>
	                    </div>
	                </td>
                </c:if>
               </tr>
               </tbody>
         </table>
	</div>
	<!--列表区-->
	<table width="100%" border="0" cellpadding="0" cellspacing="0" class="pretty" id="myTable">
			<thead>
					<tr role="row">
							<th style="width:60px" rowspan="2">坐席工号</th>
							<th style="width:80px" rowspan="2">坐席姓名</th>
							<th style="width:90px" rowspan="2">登录<br/>总时长（分） </th>
							<th style="width:90px" rowspan="2">置忙<br/>总时长（分） </th>
							<th style="width:90px" rowspan="2">空闲<br/>总时长（分） </th>
							<th style="width:620px" colspan="6">接电</th>
							<th style="width:510px" colspan="5">外呼</th>
					</tr>
					<tr role="row">
						<th style="width:50px">呼入数</th>
						<th style="width:50px">接听数</th>
						<th style="width:100px">接听率（%）</th>
						<th style="width:150px">平均振铃时长<br/>（秒） </th>
						<th style="width:150px">平均通话时长<br/>（秒） </th>
						<th style="width:120px">呼入总时长<br/>（分） </th>
						
						<th style="width:50px">外呼数 </th>
						<th style="width:80px">成功数 </th>
						<th style="width:100px">成功率(%) </th>
						<th style="width:150px">平均通话时长<br/>（秒） </th>
						<th style="width:120px">通话总时长<br/>（分） </th>
					</tr>
			</thead>
	</table>
    <div class="clear"></div>
</div>   
<script>
		setTimeout(function(){
			funPlaceholder(document.getElementById("workName1"));
		}, 200);
	</script>
</body>
</html>
