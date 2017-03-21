<!-- 工单进度页面 -->
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<link href="${ctx}flat/css/works.css" rel="stylesheet" type="text/css" />
<link href="${ctx}flat/plugins/datatable/skins/datatable_default.css" rel="stylesheet" type="text/css" />
<h3 class="small_title">办理记录</h3>
				<div class="pl10 pr10" id="">
					<table width="100%" border="0" cellpadding="0" cellspacing="0" class="pretty dataTable" id="">
						<thead>
							<tr>
								<th class="num">序号</th>
								<th style="width:20%">办理时间</th>
								<th nowrap="nowrap">步骤</th>
								<th nowrap="nowrap">姓名</th>
								<!-- <th nowrap="nowrap">角色</th> -->
								<th style="width:40%">办理意见</th>
							</tr>
						</thead>
						<tbody>
						<c:forEach items="${processHistoryList }" var="process" varStatus="vs">
							<tr <c:if test="${vs.index%2==0}">class="odd"</c:if><c:if test="${vs.index%2==1}">class="even"</c:if>>
								<td >${vs.index+1 }</td>
								<td>${process.approveTime }</td>
								<td>${process.nodeName }</td>
								<td>${process.approverName }</td>
								<%-- <td class="longTxt">${process.role }</td> --%>
								<td class="longTxt" title="${process.comment }">${process.comment }</td>
							</tr>
						</c:forEach>
						<c:if test="${empty processHistoryList }">
							<tr><td colspan="5">暂无记录!</td></tr>
						</c:if>
						</tbody>
					</table>
				</div>