<%@ page language="java" import="java.util.*"  contentType="text/html; charset=UTF-8" pageEncoding="utf-8"  %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<jsp:include page="../../../common/taglibs.jsp"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<jsp:include page="../head.jsp" />
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>企业产品</title>
    <script type="text/javascript" src="${ctx}wh/js/companyProduct/companyProductView.js?version=${version}"></script>
    
</head>
<body>
	<input type="hidden" id="id" value="${param.id }"/>
	
	<div class="bread-line">
		<label>当前位置：</label><a href="#">首页</a>&gt;<a href="#">企业管理</a>&gt;&nbsp;企业产品查看
	</div>
	 <div class="formPage">
	 <div class="formbg">
        <div class="big_title">查看<em>（企业产品）</em></div>
        <div class="content_form">
                <form id="form1">
                <table width="100%" cellspacing="0" cellpadding="0" border="0" class="inputTable">
                    <tbody>
                        <tr>
                            <th>材料种类：</th>
                            <td>
                            	<span id="materialTypeName"></span>
                            </td>
                            <th>物质名称：</th>
                            <td>
                            	<span id="materialName"></span>
                            </td>
                        </tr>
                        <tr id="cp">
                            <th>年设计产量(吨)：</th>
                            <td>
                            	<span id="outputYear"></span>
                           	</td>
                            <th>月设计产量(吨)：</th>
                            <td>
                            	<span id="outputMouth"></span>
                            </td>
                        </tr>
                        <tr id="ycl">
                            <th>年设计消耗量(吨)：</th>
                            <td>
                            	<span id="useYear"></span>
                            </td>
                            <th>月设计消耗量(吨)：</th>
                            <td>
                            	<span id="useMouth"></span>
                            </td>
                        </tr>
                        
                        <tr>
                    		<th><label>备注：</label></th>
	                    	<td colspan="3">
	                    		<span id="memo"></span>
	                    	</td>
                  		</tr>
                    </tbody>
                </table>
                </form>
        </div>
   </div>
    <div class="buttonArea"> 
			<input type="button" value="返回" class="formButton_grey"  hidefocus="" onclick="javascript :history.back(-1);"/>
</div>
  </div>

<div class="clear"></div>
</body>
</html>
