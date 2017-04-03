<%@ page language="java" import="java.util.*"  contentType="text/html; charset=UTF-8" pageEncoding="utf-8"  %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<jsp:include page="../../../common/taglibs.jsp"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<jsp:include page="../osmHead.jsp" />
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>阈值模板</title>
	<!-- 验证start -->
	<script type="text/javascript" src="${ctx}js/common/validate_form.js?version=${version}"></script>
	<script type="text/javascript" src="${ctx}common/js/js_lang_cn.js?version=${version}"></script>
	<script type="text/javascript" src="${ctx}js/common/showError.js?version=${version}"></script>
	<!-- 验证end -->
    <script type="text/javascript" src="${ctx}wh/js/thresholdTemplate/addTemplate.js?version=${version}"></script>
</head>
<body class="template">
	<input type="hidden" id="action" value='${paramValues.action[0]}'/>
	<div class="bread-line">
        <label for="">位置：</label>
        <a href="#">安监在线</a>>
        <a href="#">设备管理</a>>
        <a href="#">阈值模板设置</a>
    </div>
	<div class="formPage">
	 <div class="formbg">
	 	<div class="big_title"><span id="title"><em>新增</em><span/></div>	
        <div class="content_form">
                <form id="form1">
                	<h2 class="small_title">模板信息</h2>
	                <table width="100%" cellspacing="0" cellpadding="0" border="0" class="inputTable">
	                    <tbody>
	                        <tr>
	                            <th><em class="requireField">*</em>模板名称：</th>
	                            <td >
	                            	<input type="text" class="formText" id="templateName" maxlength="6"
	                            	valid="required" errmsg="wuhaiTemplate.templateName_not_null" />
	                            	
	                            </td>
	                            <th></th>
	                            <td></td>
	                        </tr>
	                        <tr>
	                            <th><em class="requireField">*</em>监测类型：</th>
	                            <td>
	                            	<select id="watchType" style="width:50%">
	                            		
	                            	</select>
	                            </td>
	                            <th><em class="requireField">*</em>适用量程：</th>
	                            <td>
	                            	<input type="text" class="formText" style="width:25%" id="rangeLow" maxlength="6"
	                            	valid="required&isNumber" errmsg="wuhaiTemplate.range_not_valid" />
	                        		<span>~</span>
	                            	<input type="text" class="formText" id="rangeHigh" style="width:25%" maxlength="6"
	                            	valid="required&isNumber" errmsg="wuhaiTemplate.range_not_valid" />
	                            	<span>单位</span>
	                            </td>
	                        </tr>
	                    </tbody>
	                </table>
	                <h2 class="small_title">阈值设置</h2>
	                <table width="100%" cellspacing="0" cellpadding="0" border="0" class="inputTable">
	                    <tbody>
	                        <tr id="level1">
	                            <th>报警阈值1：</th>
	                            <td colspan="3">
	                            	<select id="level1Type" style="width:15%" >
	                            	
	                            	</select>
	                            	<input type="text" class="formText" style="width:20%" id="leve1Low" maxlength="6"
	                            	 errmsg="qy_lang.maxLength" />
	                            	<span>~</span> 
	                            	<input type="text" class="formText" style="width:20%" id="level1High" maxlength="6"
	                            	 errmsg="qy_lang.maxLength" />
	                            	<span>单位</span>
	                            	<a href="javascript:void(0);" onclick="deletelevel(1)">删除</a>
	                            </td>
	                        </tr>
	                        <tr id="level2">
	                            <th>报警阈值2：</th>
	                            <td colspan="3">
	                            	<select id="level2Type" style="width:15%">
	                            	
	                            	</select>
	                            	<input type="text" class="formText" style="width:20%" id="leve2Low" maxlength="6"
	                            	 errmsg="qy_lang.maxLength" />
	                            	<span>~</span> 
	                            	<input type="text" class="formText" style="width:20%" id="level2High" maxlength="6"
	                            	 errmsg="qy_lang.maxLength" />
	                            	<span>单位</span>
	                            	<a href="javascript:void(0);" onclick="deletelevel(2)">删除</a>
	                            </td>
	                        </tr>
	                        <tr id="level3">
	                            <th>报警阈值3：</th>
	                            <td colspan="3">
	                            	<select id="level3Type" style="width:15%">
	                            	
	                            	</select>
	                            	<input type="text" class="formText" style="width:20%" id="leve3Low" maxlength="6"
	                            	 errmsg="qy_lang.maxLength" />
	                            	<span>~</span> 
	                            	<input type="text" class="formText" style="width:20%" id="level3High" maxlength="6"
	                            	 errmsg="qy_lang.maxLength" />
	                            	<span>单位</span>
	                            	<a href="javascript:void(0);" onclick="deletelevel(3)">删除</a>
	                            </td>
	                        </tr>
	                        <tr id="addmore">
	                            <th></th>
	                            <td colspan="3">
	                            	<input type="button" class="bt_addmore" 
	                            		value=" + 添加更多" hidefocus="" id="bt_addmore">
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
