<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<jsp:include page="../../../common/taglibs.jsp" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>工单列表</title>
<jsp:include page="../../../common/flatHead.jsp" />
<link href="${ctx}flat/css/reset.css" rel="stylesheet" type="text/css" />
<link href="${ctx}flat/css/Reminder.css" rel="stylesheet" type="text/css" />
<link href="${ctx}flat/plugins/form/skins/form_default.css" rel="stylesheet" type="text/css" />
<link href="${ctx}flat/plugins/datatable/skins/datatable_default.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="${ctx}flat/js/base.js"></script>
<script type="text/javascript" src="${ctx}plugins/datatable/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="${ctx}flat/plugins/datatable/selecedForDatatablePagination.js"></script>
<script type="text/javascript" src="${ctx}flat/plugins/dialog/artDialog.js?skin=default"></script>
<script type="text/javascript" src="${ctx}flat/plugins/dialog/iframeTools.js"></script>
<script type="text/javascript" src="${ctx}js/common/placeholder.js"></script>

<script type="text/javascript" src="${ctx}logined/workorderflow/js/customerCallNotList.js?version=${version}"></script>
<script type="text/javascript">
$(document).ready(function(){
  $(".right .on").click(function(){
	  if($(this).hasClass("off")){
		  $(this).removeClass("off");
		  $("#more").hide();
	  }else{
		   $(this).addClass("off");
		   $("#more").show();
	  }
  });
});
</script>
</head>
<body>
	<!-- 区分前后台 -->
	<input type="hidden" value="<%=request.getParameter("fromPage")%>"
		id="fromPage" />
	<!-- 区分待办已办 -->
	<input type="hidden" value="<%=request.getParameter("iscomplete")%>"
		id="iscomplete" />
	<div class="list">
		<div class="searchArea">
			<table cellspacing="0" cellpadding="0">
				<tbody>
					<tr>
						<td class="right">
					   <span  id="accessTypeV">受理方式：
						        <select id="accessType">
						           <option value="-1">全部</option>
						           <option value="1" >电话受理</option>
					            	<option value="2">短信受理</option>
					            	<option value="3">录音受理</option>
						        </select>
						 </span>
						  &nbsp;&nbsp;
						<span  id="businessTypeV">业务类别：
						        <select id="businessType">
						         <option value="-1">全部</option>
						        </select>
						 </span>
						  	 &nbsp;&nbsp;
						<span id="gdlx">工单类别：
						        <select id="type">
						         <option value="-1">全部</option>
						          <option value="1">咨询</option>
						          <option value="2">投诉</option>
								   <option value="3">建议</option>
								   <option value="4">举报坐席</option>
								   <option value="5">其他</option>
						        </select>
						 </span>
						
							<label>关键字：</label> 
							<span class="position:relative;"> 
								<span  id="backgjz">
									<input type="text" id="searchkey1" class="formText searchkey" maxlength="14"
										style="width:250px;" placeholder="工单编号/来电人/联系电话/受理人员"/>
								</span>
								
							</span> 
							 <label class="on">更多</label>
							<input type="button" class="searchButton" value="查询" id="search"/>
							
						</td></tr>
						<tr id="more" style="display:none;" >
						<td class="right"  style="padding-top:5px;">	
						<label>受理时间：</label> <input id="beginTime"
							class="formText Wdate" type="text"
							onfocus="WdatePicker({skin:'default',dateFmt:'yyyy-MM-dd HH:mm',maxDate:'#F{$dp.$D(\'endTime\')}'})"
							style="width:150px;" /> - <input id="endTime"
							class="formText Wdate" type="text" style="width:150px;"
							onfocus="WdatePicker({skin:'default',dateFmt:'yyyy-MM-dd HH:mm',minDate:'#F{$dp.$D(\'beginTime\')}'})" />
						</td>
					</tr>
				</tbody>
			</table>
		</div>
		<table width="100%" cellspacing="0" cellpadding="0" class="pretty"
			id="listTable">
			<thead>
				<tr>
					<th class="num">序号</th>
					<th style="width:100px">工单编号</th>
					<th style="width:80px">工单类别</th>
					<th style="width:80px">来电人</th>
					<th style="width:95px">联系电话</th>
					<th style="width:120px">受理时间</th>
					<th style="width:80px">受理人员</th>
					<!-- <th style="width:110px">办结时间</th>
					<th style="width:70px">工单状态</th> -->
					<th class="right_bdr0" style="width:70px;">操作</th>
				</tr>
			</thead>


		</table>
	</div>
	<script>
		setTimeout(function(){
			funPlaceholder(document.getElementById("searchkey1"));
			funPlaceholder(document.getElementById("searchkey2"));
		}, 200);
	</script>
</body>
</html>
