<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/ninclude/import.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>重大危险源</title> 
		<%@include file="/ninclude/title.jsp"%>
		<script type="text/javascript" src="${ctx}wh/js/js_lang_cn.js"></script>
		<!-- 清除分页cookie start -->
		<script type="text/javascript" src="${ctx}js/common/jquery.cookie.js"></script>
		<!-- 清除分页cookie end -->
		<script type="text/javascript" src="${ctx}wh/js/zhdwxy/detail.js"></script>
	</head>
<body>
	<!-- 隐藏域：角色（1、政府； 2、企业） -->
	<input type="hidden" id="whroletype" value="<s:property value="#session.whroletype"/>" />
	<!-- 隐藏域：企业ID -->
	<input type="hidden" id="group_id" value='<s:property value="#session.adminUser.groupId"/>' />
	
	<div class="bread-line">
	    <label>当前位置：</label><a href="javascript:void(0);">首页</a>&gt;<a href="javascript:void(0);">重大危险源</a>&gt;&nbsp;重大危险源详情
	</div>

	<div class="formPage">
		<div class="formbg">
			<div class="tabBox">
				<ul>
					<li class="on"><a href="#">重大危险源基本信息</a></li>
					<li><a href="#">危化品信息</a></li>
				</ul>
			</div>
			<div class="tabContent" style="display:block;">
		        <!-- <div class="big_title">重大危险源基本信息</div> -->
		        <div class="content_form">
					<form name="addForm" id="addForm" method="post">
		                <table width="100%" cellspacing="0" cellpadding="0" border="0" class="inputTable">
		                    <tbody>
		                    	<tr>
		                            <th>填报单位名称：</th>
		                            <td>
		                            <%-- <c:if test="${sessionScope.whroletype==1}">${whDangerSources.group_name }</c:if> --%>
		                            <%-- <c:if test="${sessionScope.whroletype==2}">${sessionScope.companyName}</c:if> --%>
		                            <c:choose>
		                            	<c:when test="${sessionScope.whroletype==2}">${sessionScope.companyName}</c:when>
		                            	<c:otherwise>${whDangerSources.group_name }</c:otherwise>
		                            </c:choose>
		                            </td>
		                            <th>评价机构：</th>
		                            <td>${whDangerSources.mechanism }</td>
		                        </tr>
		                        <tr>
		                            <th>重大危险源名称：</th>
		                            <td>${whDangerSources.danger_sources_name }</td>
		                        	<th>重大危险源级别：</th>
		                            <td>
		                            	${whDangerSources.danger_grade_name }
		                            </td>
		                        </tr>
		                        <tr>
		                            <th>重大危险源投用时间：</th>
		                            <td><fmt:formatDate value="${whDangerSources.use_time }" type="both" pattern="yyyy-MM-dd"/></td>
		                        	<th>R值：</th>
		                            <td>
		                            	${whDangerSources.r_value }
		                            </td>
		                        </tr>
		                          <tr>
		                            <th>重大危险源所在地址：</th>
		                            <td>${whDangerSources.address }</td>
		                            <th>危险源分类</th>
                        			<td>
                        				<s:if test="whDangerSources.grade==0 ">一般</s:if>
			                            <s:else>重大</s:else>
			                        </td>
		                        </tr>
		                        <%-- <tr>
		                           <th>评审时间：</th>
		                            <td><fmt:formatDate value="${whDangerSources.review_time }" type="date"/> </td>
		                           <th>过期时间（3年）：</th>
		                            <td><fmt:formatDate value="${whDangerSources.review_end_time }" type="date"/></td>
		                        </tr> --%>
		                       
		                       <tr>
		                            <th>单元内主要装置、设施及生产（存储）规模：</th>
		                            <td colspan="3">${whDangerSources.product_scale }</td>
		                        </tr>
		                        
		                      <%--    <tr>
		                            <th>安全管理措施：</th>
		                            <td colspan="3">${whDangerSources.safety_measures }</td>
		                        </tr> --%>
		                        <tr>
		                            <th>是否位于化工（工业）园区:</th>
		                            <td colspan="3">${whDangerSources.is_park }</td>
		                       </tr>
		                         <tr>
		                            <th>重大危险源与周边重点防护目标最近距离情况（m）:</th>
		                            <td colspan="3">${whDangerSources.distance }</td>
		                       </tr>
		                       <tr>
		                            <th>厂区边界外500米范围内人数估算值：</th>
		                            <td colspan="3">${whDangerSources.men }</td>
		                        </tr>
		                       <tr>
		                            <th>近三年内危险化学品事故情况：</th>
		                            <td colspan="3">${whDangerSources.three_year_accident }</td>
		                        </tr>
		                       <%--  <tr>
		                            <th>紧急措施：</th>
		                            <td colspan="3">${whDangerSources.accident_measures }</td>
		                        </tr> --%>
		                    </tbody>
		                </table>
					</form>
		        </div>
			    <div class="buttonArea"> 
					<input type="button" class="formButton_grey" value="返回" onclick="javascript:history.go(-1);"/>
				</div>
	        </div>
			<div class="tabContent">
				<div class="list">
					<div class="searchArea">
						<ul>
							<li><label>危化品名称：</label><input type="text" id="dangerGoodName" class="formText"></li>
							<li><label>危险性类别：<select id="dictId">
								<option value="">请选择</option>
								<s:iterator value="dangerObjTypeMap" id="entry">
									<option value="<s:property value="key"/>"><s:property value="value"/></option>
								</s:iterator>
								</select></li>
							<li><input type="button" class="searchButton" value="查询" class="formText">
<%-- 							<c:if test="${sessionScope.whroletype!=1 && sessionScope.whroletype!=3}">
							<div class="fButton greenBtn"> <span class="add" onclick="addOne();">新增</span></div>
							</c:if> --%>
							</li>
						</ul>                        
					</div>
					<table cellpadding="0" cellspacing="0"  class="pretty dataTable" id="myTable">
						<thead>
							<tr>
								<th style="width:70px;">序号</th>
								<th>危化品名称</th>
								<th style="width:80px;">危险性类别</th>
								<th style="width:100px;">UN编号</th>
								<th style="width:100px;">生产用途</th>
								<th>生产工艺</th>
								<th>单元内存量</th>
								<th style="width:135px;">操作</th>
							</tr>
						</thead>
						<tbody>
						</tbody>
					</table>
				</div>
		   </div>
		</div>
	</div>
	<div class="clear"></div>
</body>
</html>

<script type="text/javascript" >
	var _vid = "${whDangerSources.vid}";
</script>