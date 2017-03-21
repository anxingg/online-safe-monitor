<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<jsp:include page="../head.jsp" />
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>安全管理机构</title>
		<!-- 下载附件 start -->
		<script type="text/javascript" src="${ctx}wh/js/safeAccident/downloadAttachment.js"></script>
		<!-- 下载附件 end -->
		<script type="text/javascript" src="${ctx}wh/js/company/safetyInstitutions.js?version=${version}"></script>
	</head>
	<body>
		<!-- 隐藏域：企业ID -->
		<input type="hidden" id="groupId" value="<s:property value="#session.adminUser.groupId"/>" />
		<!-- 隐藏域：操作类型，4表示查看、2表示修改 -->
		<input type="hidden" id="operation" value="<s:property value="operation"/>" />
		
		<div class="bread-line">
			<label>当前位置：</label><a href="#">首页</a>&gt;&nbsp;<a href="#">安全管理机构</a>
		</div>
		<div class="tabBox">
			<ul>
				<s:if test="operation == 2">
					<li><a href="${ctx}companywh/toUpdateCompany.action">企业信息</a></li>
				    <li><a href="../wh/logined/company/updateLegalPerson.jsp">法人信息</a></li>
    				<li><a href="../wh/logined/company/companyPhotoUpdate.jsp">企业证照</a></li>
    				<li class="on"><a href="javascript:;">安全管理机构</a></li>
				</s:if>
				<s:else>
				<li><a href="${ctx}companywh/toCompanyView.action?groupId=${whCompany.groupId}" >企业信息</a></li>
				<li><a href="${ctx}wh/logined/company/legalPersonView.jsp?groupId=${whCompany.groupId}" >法人信息</a></li>
				<li><a href="${ctx}companywh/toPhotoView.action?groupId=${whCompany.groupId}" >企业证照</a></li>
				<li class="on"><a href="javascript:;">安全管理机构</a></li>
				</s:else>
			</ul>
		</div>
		<div class="formPage">
			<div class="formbg">
				<div class="big_title">安全管理机构</div>
				<div class="content_form">
					<table width="100%" cellspacing="0" cellpadding="0" border="0" class="inputTable">
						<tbody>
							<s:if test="operation == 4">
							<tr>
								<th><label>企业名称：</label></th>
								<td><s:property value="whCompany.companyName"/></td>
							</tr>
							</s:if>
							<tr>
								<th><label>安委会成立原件：</label></th>
								<td>
									<s:if test="operation == 2">
									<input type="hidden" id="attachmentId1" name="attachmentId1" value="<s:property value="sis.createFileIds"/>"/>
									<!-- 上传队列 -->
									<div id="queue"  style="display:none;"></div>
									<input id="file_upload1" name="fileupload1" type="file" multiple="true" />
									</s:if>
									<div class="mydata">
										<s:iterator value="cflist" id="cfentry">
										<p><s:property value="#cfentry.attachName"/>
										<s:if test="operation == 2">&nbsp;<a href="javascript:void(0);" name="<s:property value="#cfentry.id"/>" class="clen_a">清除</a></s:if>
										<s:if test="operation == 4">&nbsp;<a href="javascript:void(0);" onclick="downloadFile(this);" name="<s:property value="#cfentry.id"/>" >下载</a></s:if>
										</p>
										</s:iterator> 
										<!-- <p>XXX.doc<a href="javascript:void(0);" onclick="deleteImg(1);" class="clen_a">清除</a></p>
										
										<p>XXX.doc &nbsp;<a href="javascript:void(0);" name="123" class="clen_a">清除</a></p>
										
										<p>XXX.doc<a href="javascript:void(0);" onclick="deleteImg(1);" class="clen_a">清除</a></p> -->
									</div>
								</td>
							</tr>
							<tr>
								<th><label>安全管理部门：</label></th>
								<td>
									<s:if test="operation == 2">
									<input type="hidden" id="attachmentId2" name="attachmentId2" value="<s:property value="sis.departmentFileIds"/>"/>
									<!-- 上传队列 -->
									<div id="queue"  style="display:none;"></div>
									<input id="file_upload2" name="fileupload2" type="file" multiple="true" />
									</s:if>
									<div class="mydata">
										<s:iterator value="dflist" id="dfentry">
										<p><s:property value="#dfentry.attachName"/>
										<s:if test="operation == 2">&nbsp;<a href="javascript:void(0);" name="<s:property value="#dfentry.id"/>" class="clen_a">清除</a></s:if>
										<s:if test="operation == 4">&nbsp;<a href="javascript:void(0);" onclick="downloadFile(this);" name="<s:property value="#dfentry.id"/>" >下载</a></s:if>
										</p>
										</s:iterator>
									</div>
								</td>
							</tr>
							<tr>
								<th><label>组织机构：</label></th>
								<td>
									<s:if test="operation == 2">
									<input type="hidden" id="attachmentId3" name="attachmentId3" value="<s:property value="sis.groupFileIds"/>"/>
									<!-- 上传队列 -->
									<div id="queue"  style="display:none;"></div>
									<input id="file_upload3" name="fileupload3" type="file" multiple="true" />
									</s:if>
									<div class="mydata">
										<s:iterator value="gflist" id="gfentry">
										<p><s:property value="#gfentry.attachName"/>
										<s:if test="operation == 2">&nbsp;<a href="javascript:void(0);" name="<s:property value="#gfentry.id"/>" class="clen_a">清除</a></s:if>
										<s:if test="operation == 4">&nbsp;<a href="javascript:void(0);" onclick="downloadFile(this);" name="<s:property value="#gfentry.id"/>" >下载</a></s:if>
										</p>
										</s:iterator>
									</div>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
			<div class="buttonArea">
				<s:if test="operation == 2">
				<input type="button" value="保存" class="formButton_green" hidefocus=""/>
				</s:if>
				<input type="button" value="返回" class="formButton_grey"  hidefocus="" onclick="javascript :history.back(-1);"/>
			</div>
		</div>
		<div class="clear"></div>
	</body>
</html>