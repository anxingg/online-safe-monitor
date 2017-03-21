<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/ninclude/import.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>重大危险源</title> 
<%@include file="/ninclude/title.jsp"%>
<script type="text/javascript" src="${ctx}wh/js/js_lang_cn.js"></script>
</head>
<body>
<div class="bread-line">
    <label>当前位置：</label><a href="javascript:void(0);">首页</a>&gt;<a href="javascript:void(0);">重大危险源</a>&gt;&nbsp;重大危险源新增
</div>
                 <div class="formPage">
   <div class="formbg">
        <div class="big_title">新增档案</div>
        <div class="content_form">
        		<form name="addForm" id="addForm" method="post">
                <table width="100%" cellspacing="0" cellpadding="0" border="0" class="inputTable">
                    <tbody>
                    	<tr>
                            <th><em class="requireField">*</em>填报单位名称：</th>
                            <td>${sessionScope.companyName}</td>
                            <th>评价机构：</th>
                            <td><input type="text" id="mechanism" maxlength="32" class="formText" name="mechanism" value=""/></td>
                        </tr>
                        <tr>
                            <th><em class="requireField">*</em>重大危险源名称：</th>
                            <td><input type="text" id="dangerSourcesName" maxlength="32" valid="required" errmsg="zhdwxy.zhdwxy_name_not_null|zhdwxy.zhdwxy_name_valid" class="formText" name="dangerSourcesName" value=""/></td>
                        	<th><em class="requireField">*</em>重大危险源级别：</th>
                            <td>
                            	<select id="danger_grade" valid="required" errmsg="zhdwxy.zhdwxy_danger_grade_valid" style="width:100px" class="formSelect" name="danger_grade"><option value="">请选择</option></select>
                            </td>
                        </tr>
                        <tr>
                            <th>重大危险源投用时间：</th>
                            <td><input type="text" value="" onfocus="WdatePicker({skin:'default',dateFmt:'yyyy-MM-dd'})" class="formText Wdate" id="use_time"  /></td>
                        	<th>R值：</th>
                            <td>
                            	<input type="text" id="r_value" maxlength="32" class="formText" name="r_value" value=""/>
                            </td>
                        </tr>
                          <tr>
                            <th><em class="requireField">*</em>重大危险源所在地址：</th>
                            <td><input type="text" id="address" maxlength="64" valid="required" errmsg="zhdwxy.zhdwxy_address_not_null" class="formText" name="address" value=""/></td>
                        	<th>危险源分类</th>
                        	<td><select class="formSelect" name="grade" id="grade"><option value="0">一般</option><option value="1" selected="selected">重大</option></select></td>
                        </tr>
                        <!-- <tr>
                           <th><em class="requireField">*</em>评审时间：</th>
                            <td><input type="text"   value="" onfocus="WdatePicker({skin:'default',dateFmt:'yyyy-MM-dd'})" onblur="putTime();" class="formText Wdate" id="review_time"  /></td>
                           <th><em class="requireField">*</em>过期时间（3年）：</th>
                            <td><input type="text"   value="" onfocus="WdatePicker({skin:'default',dateFmt:'yyyy-MM-dd'})" class="formText Wdate" id="review_end_time"  /></td>
                        </tr> -->
                       
                       <tr>
                            <th><em class="requireField">*</em>单元内主要装置、设施及生产（存储）规模：</th>
                            <td colspan="3"><textarea valid="required" errmsg="zhdwxy.zhdwxy_product_scale_not_null"  maxlength="256" class="formTextarea" rows="3" id="product_scale" value=""></textarea></td>
                        </tr>
                        
                        <!--  <tr>
                            <th><em class="requireField">*</em>安全管理措施：</th>
                            <td colspan="3"><textarea valid="required" errmsg="zhdwxy.zhdwxy_safety_measures_not_null"  maxlength="256" class="formTextarea" rows="3" id="safety_measures" value=""></textarea></td>
                        </tr> -->
                        <tr>
                            <th>是否位于化工（工业）园区:</th>
                            <td colspan="3"><textarea maxlength="256" id="is_park" class="formTextarea" rows="1" value=""></textarea></td>
                       </tr>
                         <tr>
                            <th><em class="requireField">*</em>重大危险源与周边重点防护目标最近距离情况（m）:</th>
                            <td colspan="3"><input valid="required" errmsg="zhdwxy.zhdwxy_distance_not_null"  maxlength="5" type="text" id="distance" class="formText" value=""/></td>
                       </tr>
                       <tr>
                            <th>厂区边界外500米范围内人数估算值：</th>
                            <td colspan="3"><textarea maxlength="256" class="formTextarea" rows="1" id="men" value=""></textarea></td>
                        </tr>
                       <tr>
                            <th><em class="requireField">*</em>近三年内危险化学品事故情况：</th>
                            <td colspan="3"><textarea valid="required" errmsg="zhdwxy.zhdwxy_three_year_accident_not_null" maxlength="256" class="formTextarea" rows="1" id="three_year_accident" value=""></textarea></td>
                        </tr>
                        <!-- <tr>
                            <th><em class="requireField">*</em>紧急措施：</th>
                            <td colspan="3"><textarea valid="required" errmsg="zhdwxy.zhdwxy_accident_measures_not_null"  maxlength="256" class="formTextarea" rows="1" id="accident_measures" value=""></textarea></td>
                        </tr> -->
                    </tbody>
                </table>
                </form>
        </div>
   </div>
    <div class="buttonArea"> 
			<input type="button" class="formButton_green" value="确定" id="save"/>
			<input type="button" value="取消" class="formButton_grey"  onclick="javascript:history.go(-1);"/>
</div>
   </div>
<div class="clear"></div>
</body>
</html>
<script type="text/javascript" src="${ctx}wh/js/zhdwxy/add.js"></script>
