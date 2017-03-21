<%@ page language="java" import="java.util.*"  contentType="text/html; charset=UTF-8" pageEncoding="utf-8"  %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn"%> 
		<table cellpadding="0" cellspacing="0" class="pretty dataTable">
			<thead>
				<tr>
					<th colspan="7">${company.companyName }</th>
				</tr>
				<tr>
					<th rowspan="2" width="50px">序号</th>
					<th rowspan="2" width="200px">投入项目</th>
					<th rowspan="2" width="350px">投入明细</th>
					<th colspan="4">投入金额</th>
				</tr>
				<tr>
					<th>第一季度</th>
					<th>第二季度</th>
					<th>第三季度</th>
					<th>第四季度</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${resultList}" var="result" varStatus="status">
                        <c:choose>
                        	<c:when test="${result.name=='季度金额累计' }">
                        		<tr>
	                        		<td colspan="3">${result.name}</td>
	                        		<td>${result.oneQuarter}</td>
	                        		<td>${result.twoQuarter}</td>
	                        		<td>${result.threeQuarter}</td>
	                        		<td>${result.fourQuarter}</td>
	                        	</tr>
                        	</c:when>
                        	<c:when test="${result.name=='年金额累计' }">
                        		<tr>
	                        		<td colspan="3">${vo.year}${result.name}</td>
	                        		<td colspan="4">${result.money}</td>
	                        	</tr>
                        	</c:when>
                        	<c:otherwise>
                        		<c:choose>
		                        	<c:when test="${result.child == null || fn:length(result.child) == 0 }">
		                        		<tr>
		                        			<td>${status.index+1 }</td>
		                        			<td>${result.name }</td>
		                        			<td>${result.childName==null?"-":result.childName }</td>
		                        			<td>${result.oneQuarter==null?0:result.oneQuarter }</td>
		                        			<td>${result.twoQuarter==null?0:result.twoQuarter }</td>
		                        			<td>${result.threeQuarter==null?0:result.threeQuarter }</td>
		                        			<td>${result.fourQuarter==null?0:result.fourQuarter }</td>
		                        		</tr>
		                        	</c:when>
		                        	<c:otherwise>
		                        		<tr>
		                        			<td rowspan="${fn:length(result.child)+1}">${status.index+1 }</td>
		                        			<td rowspan="${fn:length(result.child)+1}">${result.name }</td>
		                        			<td>${result.childName }</td>
		                        			<td>${result.oneQuarter==null?0:result.oneQuarter }</td>
		                        			<td>${result.twoQuarter==null?0:result.twoQuarter }</td>
		                        			<td>${result.threeQuarter==null?0:result.threeQuarter }</td>
		                        			<td>${result.fourQuarter==null?0:result.fourQuarter }</td>
		                        		</tr>
		                        		<c:forEach items="${result.child}" var="child">
		                        			<tr>
		                        				<td>${child.name }</td>
		                        				<td>${child.oneQuarter==null?0:child.oneQuarter }</td>
			                        			<td>${child.twoQuarter==null?0:child.twoQuarter }</td>
			                        			<td>${child.threeQuarter==null?0:child.threeQuarter }</td>
			                        			<td>${child.fourQuarter==null?0:child.fourQuarter }</td>
		                        			</tr>
		                        		</c:forEach>
		                        	</c:otherwise>
		                        </c:choose>
                        	</c:otherwise>
                        </c:choose>
                        
					</c:forEach>
			</tbody>
		</table>
