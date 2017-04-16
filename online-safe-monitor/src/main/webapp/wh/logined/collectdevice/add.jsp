<%@ page language="java" import="java.util.*"  contentType="text/html; charset=UTF-8" pageEncoding="utf-8"  %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<jsp:include page="../../../common/taglibs.jsp"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<jsp:include page="../osmHead.jsp" />
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>采集设备维护</title>
	<!-- 验证start -->
	<script type="text/javascript" src="${ctx}js/common/validate_form.js?version=${version}"></script>
	<script type="text/javascript" src="${ctx}common/js/js_lang_cn.js?version=${version}"></script>
	<script type="text/javascript" src="${ctx}js/common/showError.js?version=${version}"></script>
	<!-- 验证end -->
    <script type="text/javascript" src="${ctx}wh/js/collectdevice/add.js?version=${version}"></script>
</head>
<body class="template">
	<input type="hidden" id="action" value='${paramValues.action[0]}'/>
	<input type="hidden" id="vid" value='${paramValues.vid[0]}'/>
	<div class="bread-line">
        <label for="">位置：</label>
        <a href="#">安监在线</a>>
        <a href="#">设备管理</a>>
        <a href="#">采集设备管理</a>
    </div>
	<div class="formPage">
	 <div class="formbg">
	 	<div class="big_title"><span id="title"><em>新增</em></span></div>	
        <div class="content_form">
                <form id="form1">
                	<h2 class="small_title">基本信息</h2>
	                <table width="100%" cellspacing="0" cellpadding="0" border="0" class="inputTable">
	                    <tbody>
	                    	<tr>
								<th><em class="requireField">*</em>企业：</th>
								<td>
									<select name="areaId">
										<option value="-1">请选择</option>
									</select>
									<select name="groupId">
										<option value="-1">请选择</option>
									</select>
								</td>
								<th><em class="requireField">*</em>安装位置：</th>
	                            <td >
	                            	<input type="text" class="formText" id="installPosition" maxlength="50"
	                            	valid="required" errmsg="wuhaiCollectDevice.installPosition_not_null" />
	                            	
	                            </td>
							</tr>
							<tr>
	                            <th>型号：</th>
	                            <td>
	                            	<select id="deviceModel" style="width:50%">
	                            		
	                            	</select>
	                            </td>
	                            <th><em class="requireField">*</em>通道数：</th>
	                            <td>
	                            	<select id="channelCount" style="width:50%">
	                            		<option value="8">8通道</option>
										<option value="16">16通道</option>
	                            	</select>
	                            </td>
	                        </tr>
	                        <tr>
	                            <th>IP地址：</th>
	                            <td >
	                            	<input type="text" class="formText" id="deviceAddress" maxlength="20"
	                            		/>
	                            </td>
	                            <th>状态：</th>
	                            <td>
	                            	<select id="deviceStatus" style="width:50%">
	                            		
	                            	</select>
	                            </td>
	                        </tr>
	                        <tr>
	                        	<th>设备类型：</th>
	                        	<td>
	                            	<select id="deviceType" style="width:50%">
	                            		
	                            	</select>
	                            </td>
	                            <th></th>
	                            <td>
	                            </td>
	                        </tr>
	                        <tr>
	                            <th>安装人员：</th>
	                            <td >
	                            	<input type="text" class="formText" id="installPerson" maxlength="40"
	                            		/>
	                            </td>
	                            <th>安装日期：</th>
	                            <td>
	                            	<input type="text" class="Wdate formText" name="installDate" 
											onclick="WdatePicker({skin:'default',dateFmt:'yyyy-MM-dd',minDate:'%y-%M-%d'})" />
	                            </td>
	                        </tr>
	                        <tr>
	                            <th>备注：</th>
	                            <td colspan="3">
	                            	<textarea class="formTextarea2 area area01"  id="memo"  cols="122" rows="4" name=""
										fmaxlength="200" style="width:720px"></textarea>
	                            </td>
	                            
	                        </tr>
	                    </tbody>
	                </table>
                </form>
        </div>
   </div>
   <div class="buttonArea"> 
		<input type="button" class="formButton_green" value="确定" hidefocus="" id="submit"/>
		<input type="button" value="取消" class="formButton_grey"  hidefocus="" onclick="javascript :history.back(-1);"/>
  </div>
  </div>

<div class="clear"></div>
</body>
</html>
