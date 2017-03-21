<%@ page language="java" import="java.util.*"  contentType="text/html; charset=UTF-8" pageEncoding="utf-8"  %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<jsp:include page="../../../common/taglibs.jsp"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<jsp:include page="../head.jsp" />
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>应急预案</title>
    <script type="text/javascript" src="${ctx}wh/js/plans/plansView.js?version=${version}"></script>
    <script type="text/javascript" src="${ctx}wh/js/safeAccident/downloadAttachment.js?version=${version}"></script>
    <style type="text/css">
	.uploadify{float:left;margin-top:7px;}
	</style>
</head>
<body>
	<input type="hidden" id="whroletype" value='<s:property value="#session.whroletype"/>'/>
	<input type="hidden" id="group_id" value='<s:property value="#session.adminUser.groupId"/>'/>
	<input type="hidden" id="vid" value="${param.vid }"/>
	<div class="bread-line">
		<label>当前位置：</label><a href="#">首页</a>&gt;<a href="#">应急预案</a>&gt;&nbsp;查看
	</div>
	 <div class="formPage">
	 <div class="formbg">
        <div class="big_title">查看<em>（应急预案）</em></div>
        <div class="content_form">
                <form id="form1">
                <table width="100%" cellspacing="0" cellpadding="0" border="0" class="inputTable">
                    <tbody>
                        <tr>
                            <th>预案编号：</th>
                            <td>
                            	<span id="planNo"></span>
                            </td>
                            <th>企业名称：</th>
                            <td>
                            	<span id="companyName"></span>
                            </td>
                        </tr>	
                        <tr>
                            <th>预案类型：</th>
                            <td>
                            	<span id="planTypeName"></span>
                            </td>
                            <th>经办人：</th>
                            <td>
                            	<span id="agent"></span>
                            </td>
                        </tr>
                        <tr>
                        	<th>企业法人：</th>
                            <td>
                            	<span id="legalRepresentative"></span>
                            </td>
                            <th>企业电话：</th>
                            <td>
                           		<span id="phone"></span>
                            </td>
                        </tr>
                        <tr>
                        	<th>企业地址：</th>
                            <td colspan="3">
                            	<span id="cityId"></span>
                            </td>
                            
                        </tr>
                        <tr>
                        	<th>备案时间：</th>
                            <td>
                            	<span id="prepareTime"></span>
                            </td>
                            <th>备案到期时间（3年）：</th>
                            <td>
                           		<span id="prepareEndTime"></span>
                            </td>
                        </tr>
                        <tr>
                        	<th>应急内容附件：</th>
                            <td>
								<span id="att"></span>
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
