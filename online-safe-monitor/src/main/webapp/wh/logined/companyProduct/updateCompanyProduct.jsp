<%@ page language="java" import="java.util.*"  contentType="text/html; charset=UTF-8" pageEncoding="utf-8"  %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<jsp:include page="../../../common/taglibs.jsp"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<jsp:include page="../head.jsp" />
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>企业产品</title>
	<!-- 验证start -->
	<script type="text/javascript" src="${ctx}js/common/validate_form.js?version=${version}"></script>
	<script type="text/javascript" src="${ctx}common/js/js_lang_cn.js?version=${version}"></script>
	<script type="text/javascript" src="${ctx}js/common/showError.js?version=${version}"></script>
	<!-- 验证end -->
    <script type="text/javascript" src="${ctx}wh/js/companyProduct/updateCompanyProduct.js?version=${version}"></script>
    
</head>
<body>
	<input type="hidden" id="id" value="${param.id }"/>
	
	<div class="bread-line">
		<label>当前位置：</label><a href="#">首页</a>&gt;<a href="#">企业管理</a>&gt;&nbsp;企业产品修改
	</div>
	 <div class="formPage">
	 <div class="formbg">
        <div class="big_title">修改<em>（企业产品）</em></div>
        <div class="content_form">
                <form id="form1">
                <table width="100%" cellspacing="0" cellpadding="0" border="0" class="inputTable">
                    <tbody>
                        <tr>
                            <th><em class="requireField">*</em>材料种类：</th>
                            <td>
                            	<select id="materialType">
                            		<option value="1">原材料</option>
                            		<option value="2">产品</option>
                            	</select>
                            </td>
                            <th><em class="requireField">*</em>物质名称：</th>
                            <td>
                            	<select id="productId">
                            		<option value="">请选择</option>
                            	</select>
                            </td>
                        </tr>
                        <tr id="cp">
                            <th><em class="requireField">*</em>年设计产量(吨)：</th>
                            <td><input type="text" class="formText" id="outputYear" maxlength="6" onkeyup="this.value=this.value.replace(/[^0-9]/g,'')"
                            	 valid="required" errmsg="wuhaiProduct.outputYear_not_null" /></td>
                            <th><em class="requireField">*</em>月设计产量(吨)：</th>
                            <td><input type="text" class="formText" id="outputMouth" maxlength="6" onkeyup="this.value=this.value.replace(/[^0-9]/g,'')"
                            	 valid="required" errmsg="wuhaiProduct.outputMouth_not_null"/></td>
                        </tr>
                        <tr id="ycl">
                            <th><em class="requireField">*</em>年设计消耗量(吨)：</th>
                            <td><input type="text" class="formText" id="useYear" maxlength="6" onkeyup="this.value=this.value.replace(/[^0-9]/g,'')"
                            	valid="required" errmsg="wuhaiProduct.useYear_not_null"/></td>
                            <th><em class="requireField">*</em>月设计消耗量(吨)：</th>
                            <td><input type="text" class="formText" id="useMouth" maxlength="6" onkeyup="this.value=this.value.replace(/[^0-9]/g,'')"
                            	valid="required" errmsg="wuhaiProduct.useMouth_not_null"/></td>
                        </tr>
                        
                        <tr>
                    		<th><label>备注：</label></th>
	                    	<td colspan="3">
	                        <textarea class="formTextarea" rows="5" id="memo" maxlength="256" 
	                        valid="textareaLength" errmsg="safety.text_max_length" maxNumber="256"></textarea>
	                    	<span style="float:right">0-256字</span>
	                    	</td>
                  		</tr>
                    </tbody>
                </table>
                </form>
        </div>
   </div>
    <div class="buttonArea"> 
			<input type="button"class="formButton_green" value="修改" hidefocus="" id="submit"/>
			<input type="button" value="取消" class="formButton_grey"  hidefocus="" onclick="javascript :history.back(-1);"/>
</div>
  </div>

<div class="clear"></div>
</body>
</html>
