<%@ page language="java" import="java.util.*"  contentType="text/html; charset=UTF-8" pageEncoding="utf-8"  %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<jsp:include page="../../../common/taglibs.jsp"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<jsp:include page="../osmHead.jsp" />
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>通道绑定</title>
	<!-- 验证start -->
	<script type="text/javascript" src="${ctx}js/common/validate_form.js?version=${version}"></script>
	<script type="text/javascript" src="${ctx}common/js/js_lang_cn.js?version=${version}"></script>
	<script type="text/javascript" src="${ctx}js/common/showError.js?version=${version}"></script>
	<!-- 验证end -->
    <script type="text/javascript" src="${ctx}wh/js/devicesensorbind/bind.js?version=${version}"></script>
</head>
<body class="template">
	<input type="hidden" id="vid" value='${paramValues.vid[0]}'/>
	<input type="hidden" id="groupId" value='${paramValues.groupId[0]}'/>
	<input type="hidden" id="collectDeviceId" value='${paramValues.collectDeviceId[0]}'/>
	<div class="formPage">
		<div class="formbg">
		 	<div class="big_title">
		 		<span id="title"><em>通道 绑定</em></span>
		 	</div>	
	       	<div class="content_form">
	               <form id="form1">
	                <table width="100%" cellspacing="0" cellpadding="0" border="0" class="inputTable">
	                    <tbody>
	                    	<tr>
								<th>通道号：</th>
								<td>
									<label id="channelNo">${paramValues.channelName[0]}</label>
								</td>
							</tr>
							<tr>
	                            <th><em class="requireField">*</em>危险源：</th>
	                            <td>
	                            	<select id="dangerSourceId" style="width:50%">
	                            		
	                            	</select>
	                            </td>
	                        </tr>
	                        <tr>
	                            <th><em class="requireField">*</em>传感器：</th>
	                            <td>
	                            	<select id="collectSensorId" style="width:50%">
	   
	                            	</select>
	                            </td>
	                        </tr>
	                        <tr>
	                        	<th>量程：</th>
								<td>
									<label id="range"></label>
								</td>
	                        </tr>
	                        <tr>
	                            <th>模拟量程：</th>
								<td>
									<label id="alogRange"></label>
								</td>
	                        </tr>
	                    </tbody>
	                </table>
	             </form>
	       	</div>
	  </div>
  </div>
</body>
</html>
