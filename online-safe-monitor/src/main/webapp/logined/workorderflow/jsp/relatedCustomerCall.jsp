<%@ page contentType="text/html;charset=UTF-8" language="java"%>

<%@ include file="../../../common/taglibs.jsp"%>
<%-- <script type="text/javascript" src="${ctx}logined/workorderflow/js/getRelatedCustomerCallByPhone.js?version=${version}"></script> --%>
<input type="hidden" id="phone"  value='<s:property value="#request.phone" />'> 

<h3 class="small_title"  id="relateCustomerCallLog">历史工单<a class="allshow" href="javascript:void(0);" id="moreList">更多&gt;&gt;</a></h3>
<input type="hidden" id="from" value="tel"/>
  <div class="pl10 pr10" >
    <table cellpadding="0" cellspacing="0"  class="pretty" id="myRelateTable">
     <thead>
        <tr>
          	<th class="num">序号</th>
			<th style="width:130px;">受理时间</th>
			<th style="width:115px;">工单编号</th>
			<th style="width:80px;">业务类别</th>
			<th style="width:80px;">工单类别</th>
			<th >工单内容</th>
			<th style="width:80px;">工单状态</th>
			<th style="width:100px;">受理人员</th>
         	<th class="right_bdr0" style="width:70px;">操作</th>
        </tr>
      </thead>
    </table>
  </div>