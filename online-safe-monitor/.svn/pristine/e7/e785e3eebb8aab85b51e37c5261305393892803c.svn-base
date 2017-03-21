<%@ page language="java" import="java.util.*"  contentType="text/html; charset=UTF-8" pageEncoding="utf-8"  %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<jsp:include page="../../../common/taglibs.jsp"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<jsp:include page="../head.jsp" />
	<title>新增救援物资</title>
    <script type="text/javascript" src="${ctx}wh/js/reliefGoods/addReliefGoods.js"></script> 
    <script type="text/javascript" src="${ctx}js/common/validate_form.js"></script>
	<script>
		window.UEDITOR_HOME_URL = basePath+"plugins/ueditor/";
	</script>
    <script type="text/javascript" charset="utf-8" src="${ctx}plugins/ueditor/editor_config.js"></script>
	<script type="text/javascript" charset="utf-8" src="${ctx}plugins/ueditor/editor_all_min.js"></script>
	<script type="text/javascript" src="${ctx}js/common/showError.js"></script>
	<script type="text/javascript" src="${ctx}js/logined/js_lang_cn.js"></script>
	<style type="text/css">
	.uploadify{float:left;margin-top:7px;}
	</style>
</head>
<body>
<input type="hidden" id="whroletype" value="${sessionScope.whroletype }">
<input type="hidden" id="goodsId" value="${info.id }"/>
<input type="hidden" id="oldGoodType" value="${info.goodType }"/>
	<div class="bread-line">
		<label>当前位置：</label><a href="#">首页</a>&gt;<a href="#">应急管理</a>&gt;<s:if test="info != null && info.id != null">修改</s:if><s:else>新增</s:else>
	</div>
    <div class="formPage">
	 <div class="formbg">
        <div class="big_title"><s:if test="info != null && info.id != null">修改</s:if><s:else>新增</s:else><em>（救援物资）</em></div>
                 <form id="form1">
        <div class="content_form">
        <form id="form1">
                <table width="100%" cellspacing="0" cellpadding="0" border="0" class="inputTable" id="tabInfo">
	                         <tr>
                            <th><em class="requireField">*</em>装备名称：</th>
                            <td><input id="goodName" maxlength="32" type="text" style="width:80%" valid="required" errmsg="relief.goodName_not_null" class="formText" value="${info.goodName }"/></td>
                        </tr>
                        <tr>
                    		<th><label><em class="requireField">*</em>所属类别：</label></th>
	                    	<td>
				            	<select id="goodType"></select>
				            </td>
                  		</tr>
                        <tr>
                    		<th><label><em class="requireField">*</em>单位：</label></th>
	                    	<td>
				            	<input id="goodUnit" maxlength="20" style="width:80%" type="text" valid="required" errmsg="relief.goodUnit_not_null" class="formText" value="${info.goodUnit }"/> 
				            </td>
                  		</tr>
                  		<tr>
                  		<th><label><em class="requireField">*</em>数量：</label></th>
                  		<td>
                  		<input type="text" class="formText" style="width:80%" id="goodNum" maxlength="8" value="${info.goodNum }" valid="required" errmsg="relief.goodNum_not_null" onkeyup="testNum(this);"/></td>
                  		</tr>
                  		
                  		<tr>
                  		<th><label>配备地点：</label></th>
                  		<td>
                  		<input type="text" class="formText" id="equippedPlace" style="width:80%" maxlength="32" value="${info.equippedPlace }"/></td>
                  		</tr>
                  		
                  		<tr>
                  		<th><label>保管人：</label></th>
                  		<td>
                  		<input type="text" class="formText" style="width:80%" id="keeper" maxlength="32" value="${info.keeper }" /></td>
                  		</tr>
                  		
                  		
                  		<tr>
                  		<th><label>电话：</label></th>
                  		<td>
                  		<input type="text" class="formText" style="width:80%" id="phone" maxlength="20" value="${info.phone }" /></td>
                  		</tr>
          </table>
          </form>
        </div>
        </form>
   </div>
   
   
    <div class="buttonArea"> 
			<input type="button"class="formButton_green" value="确定" hidefocus="" id="save"/>
			<input type="button" value="取消" class="formButton_grey"  hidefocus="" onClick="javascript :history.back(-1);"/>
</div>
  
  

<div class="clear"></div>
</body>
</html>
