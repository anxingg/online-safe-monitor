<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib uri="/affairShow" prefix="affairShow" %>
<%
String id = request.getParameter("id");
request.setAttribute("id",id);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>新建</title>

	<jsp:include page="../../wh/logined/head.jsp" />
	<script>
		window.UEDITOR_HOME_URL = basePath+"plugins/ueditor/";
	</script>
<%-- <link href="${ctx}/flat/css/reset.css" rel="stylesheet" type="text/css" /> --%>
<%-- <link href="${ctx}/flat/plugins/form/skins/form_default.css" rel="stylesheet" type="text/css" /> --%>
<link href="${ctx}/flat/plugins/annex/skins/annex_default.css" rel="stylesheet" type="text/css" />
<%-- <link rel="stylesheet" type="text/css" href="${ctx}wh/css/style.css?version=${version}"/> --%>
<%-- <script type="text/javascript" language="javascript" src="${ctx}plugins/upload/jquery.uploadify.min.js" ></script> --%>
<script type="text/javascript" charset="utf-8" src="${ctx}plugins/ueditor/editor_config.js"></script>
<script type="text/javascript" charset="utf-8" src="${ctx}plugins/ueditor/editor_all_min.js"></script>
<script type="text/javascript" src="${ctx}js/logined/notify/viewAdd.js"></script>
<script type="text/javascript" src="${ctx}js/user/selectuser.js"></script>
<script type="text/javascript" src="${ctx}js/logined/cbb/affairShow/affairShow.js"></script>
<%-- <script language="javascript" type="text/javascript" src="${ctx}plugins/My97DatePicker/WdatePicker.js"></script> --%>

	<!-- 表示验证 start -->
	<script type="text/javascript" src="${ctx}js/common/validate_form.js"></script>
	<script type="text/javascript" src="${ctx}js/common/showError.js"></script>
	<script type="text/javascript" src="${ctx}js/logined/js_lang_cn.js"></script>
	<!-- 表示验证 end -->
	<!-- 上传 start -->
    <script type="text/javascript" src="${ctx}js/logined/upload/attachHelp.js"></script>   
    <script type="text/javascript" src="${ctx}js/logined/cbb/attachment/attachment.js"></script>
<style>
.inputTable th{ width:80px;}
.uploadify{margin:10px 0px;}
.formPage .readOnlyTextarea{
	padding-top:5px;
}
</style> 
</head>
<body>
<div class="bread-line">
		<s:if test="#request.id==1">
		<label>当前位置：</label><a href="#">首页</a>&gt;<a href="#">公告管理</a>&gt;&nbsp;发布
		</s:if>
		<s:elseif test="#request.id==2">
		<label>当前位置：</label><a href="#">首页</a>&gt;<a href="#">政策法规</a>&gt;&nbsp;发布
		</s:elseif>
</div>
<input type="hidden" id="isSmipleText" name="isSmipleText"/>
<div class="formPage">
	<form id="form1">
	<div class="formbg">
	    <div class="big_title">发布内容</div>
  		 <div class="content_form">
  	<s:if test="#request.id==1">
		<table width="100%" border="0" cellpadding="0" cellspacing="0"  class="inputTable" >
		<tbody>
      	<tr class="firstEmpty">
        	<th></th>
            <td></td>
            <th></th>
            <td></td>
        </tr>
		<tr>
			<th><label>标题：</label></th>
			<td colspan="3"><input id="subject" name="subject" type="text" maxlength="100" class="formText gray_9" valid="required" errmsg="notify.notify_subject_not_null"></input>
			</td>
        </tr>
        <tr id="userDiv" style="display: none;">
			<th><label>发布范围：</label></th>
			<td colspan="3">
				<input type="hidden" id="publishScopeUserIds" name="publishScopeUserIds"/>
				<input type="hidden" id="columnId" name="columnId" value="${id}"/>
				<textarea class="readOnlyTextarea" style="width:88%" rows="5" id="publishScopeUserNames" name="publishScopeUserNames" readonly="readonly"></textarea>
				<span class="addMember">
				<a class="icon_add" href="#" onclick="selectAuthor('user');">添加</a>
				<a class="icon_clear" href="#" onclick="clearAuthor('user')">清空</a>
				</span>
			</td>
		</tr>
		<tr>
			<th><label>类型：</label></th>
			<td colspan="3">
				<select id="notifyType"></select>
			</td>
		</tr>
		<tr>
        	<th><label>有效期：</label></th>
            <td colspan="3">
            <label class="radio"><input type="radio" name="limit" onclick="isLimit(1)" value="1" checked="checked"/>不限制</label>
            <label class="radio"><input type="radio" name="limit"  onclick="isLimit(0)" value="0"/>启用</label>
            <span id="showDate" >
            <input  type="text" class="Wdate formText" value="<%=new java.text.SimpleDateFormat("yyyy-MM-dd").format(new Date()) %>" size="20" name="begin_date" id="begDate" onclick="WdatePicker({maxDate :'#F{$dp.$D(\'endDate\')}',skin:'default',dateFmt:'yyyy-MM-dd',minDate:'%y-%M-%d'})" style="width:130px;"/>
			-
			<input  type="text" class="Wdate formText" size="20" name="end_date" id="endDate" onclick="WdatePicker({skin:'default',dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'begDate\') || \'%y-%M-%d\'}'})" style="width:130px;" />
			<span class="tip">不选择结束日期为手动终止</span>
			</span>
            </td>
        </tr>
		<tr>
			<th><label>置顶设置：</label></th>
			<td><label class="radio"><input type="checkbox" id="isTop" value="1" />置顶本记录</label></td>
			<input type="hidden" id="hidAffair" value="<affairShow:affairShow moduleCode="ggtz"/>"/>
			<%-- 
			pxd 注销
			<th><label>发送提醒：</label></th>			
			<td id="sendRemind">
				<label class="radio">
				<input type="checkbox" id="affairRemind" name="affairRemind" checked="checked"/>在线消息</label>
				<label class="radio">
				<input type="checkbox" id="smsRemind" name="smsRemind"/>短信提醒</label>
				<affairShow:affairShow moduleCode="ggtz" />
			
			
			</td> --%>
			
		</tr>

		<tr style="display: none">
			<th><label>图片</label></th>
			<td>
			   <p class="top5"></p>
			    <input type="hidden" id="imageId" name="imageId" value=""/>
			    <input id="image_upload" name="image_upload" type="file" multiple="true" />
			    <!-- 上传队列 -->
			    <div id="queue"  style="display:none;"></div>
               <div class="annex">
                <ul id="imageAttachmentList">
                </ul>
               </div>
            </td>
         </tr>
        <tr>
        <th><label>内容：</label></th>
	            <td colspan="3" >
	            <div id="text1" style="display:none">
	                 <input id="contentInfoInput" type="hidden"/>
				     <script id="contentInfo" type="text/plain" style="width:100%"></script>
				</div>
				<div id="text2" style="display:none">
				     <textarea class="formTextarea" rows="5" id="contentInfo2"style="width:100%"></textarea>
				</div>
	            </td>
	      </tr>
	      
	     <s:if test="#request.id==1">
			<tr>
				<th>备注：</th>
				<td colspan="3">
					<textarea class="formTextarea" rows="5" id="memo" maxlength="256" 
						valid="textareaLength" errmsg="wuhaicom.text_max_length" maxNumber="256"></textarea>
					<span style="float:right">0-256字</span>
				</td>
			</tr>
		</s:if>
	    <tr>
			<th><label>附件：</label></th>
			<td colspan="3">
			    <input type="hidden" id="attachmentId" name="attachmentId"/>
			    <input id="file_upload" name="fileupload" type="file" multiple="true" />
			    <!-- 上传队列 -->
			    <div id="queue"  style="display:none;"></div>
               <div class="annex">
                <ul id="attachmentList">
               </ul>
               </div>
            </td>
         </tr>
         </tbody>
	</table>
	</s:if>
	<s:elseif test="#request.id==2">
		<table width="100%" border="0" cellpadding="0" cellspacing="0"  class="inputTable" >
		<tbody>
      	<tr class="firstEmpty">
        	<th></th>
            <td></td>
            <th></th>
            <td></td>
        </tr>
		<tr>
			<th><label>政策名称：</label></th>
			<td colspan="3"><input id="subject" name="subject" type="text" maxlength="32" class="formText gray_9" valid="required" errmsg="notify.notify_zcmc_not_null"></input>
			</td>
        </tr>
        <tr id="userDiv" style="display: none;">
			<th><label>发布范围：</label></th>
			<td colspan="3">
				<input type="hidden" id="publishScopeUserIds" name="publishScopeUserIds"/>
				<input type="hidden" id="columnId" name="columnId" value="${id}"/>
				<textarea class="readOnlyTextarea" style="width:88%" rows="5" id="publishScopeUserNames" name="publishScopeUserNames" readonly="readonly"></textarea>
				<span class="addMember">
				<a class="icon_add" href="#" onclick="selectAuthor('user');">添加</a>
				<a class="icon_clear" href="#" onclick="clearAuthor('user')">清空</a>
				</span>
			</td>
		</tr>
		<tr>
			<th><label>类型：</label></th>
			<td>
				<select id="notifyType"></select>
			</td>
			<th><label>颁布部门：</label></th>
			<td>
				<input id="banbuGroup" type="text" maxlength="32" class="formText gray_9" ></input>
			</td>
		</tr>
		<tr>
        	<th><label>颁布时间：</label></th>
            <td>
            <input  type="text" class="Wdate formText" size="20" id="banbuDate" onclick="WdatePicker({maxDate :'#F{$dp.$D(\'beginDate\')}',skin:'default',dateFmt:'yyyy-MM-dd'})" style="width:240px;"/>
            </td>
            <th><label>实施时间：</label></th>
            <td>
			<input  type="text" class="Wdate formText" size="20" id="beginDate" onclick="WdatePicker({skin:'default',dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'banbuDate\')}'})" style="width:240px;" />
            </td>
        </tr>
		<tr>
			<th><label>置顶设置：</label></th>
			<td><label class="radio"><input type="checkbox" id="isTop" value="1" />置顶本记录</label></td>
			<input type="hidden" id="hidAffair" value="<affairShow:affairShow moduleCode="ggtz"/>"/>
			<%-- 
			pxd 注销
			<th><label>发送提醒：</label></th>			
			<td id="sendRemind">
				<label class="radio">
				<input type="checkbox" id="affairRemind" name="affairRemind" checked="checked"/>在线消息</label>
				<label class="radio">
				<input type="checkbox" id="smsRemind" name="smsRemind"/>短信提醒</label>
				<affairShow:affairShow moduleCode="ggtz" />
			
			
			</td> --%>
			
		</tr>

		<tr style="display: none">
			<th><label>图片</label></th>
			<td>
			   <p class="top5"></p>
			    <input type="hidden" id="imageId" name="imageId" value=""/>
			    <input id="image_upload" name="image_upload" type="file" multiple="true" />
			    <!-- 上传队列 -->
			    <div id="queue"  style="display:none;"></div>
               <div class="annex">
                <ul id="imageAttachmentList">
                </ul>
               </div>
            </td>
         </tr>
        <tr>
        <th><label>内容：</label></th>
	            <td colspan="3" >
	            <div id="text1" style="display:none">
	                 <input id="contentInfoInput" type="hidden"/>
				     <script id="contentInfo" type="text/plain" style="width:100%"></script>
				</div>
				<div id="text2" style="display:none">
				     <textarea class="formTextarea" rows="5" id="contentInfo2"style="width:100%"></textarea>
				</div>
	            </td>
	      </tr>
	      
	     <s:if test="#request.id==1">
			<tr>
				<th>备注：</th>
				<td colspan="3">
					<textarea class="formTextarea" rows="5" id="memo" maxlength="256" 
						valid="textareaLength" errmsg="wuhaicom.text_max_length" maxNumber="256"></textarea>
					<span style="float:right">0-256字</span>
				</td>
			</tr>
		</s:if>
	    <tr>
			<th><label>附件：</label></th>
			<td colspan="3">
			    <input type="hidden" id="attachmentId" name="attachmentId"/>
			    <input id="file_upload" name="fileupload" type="file" multiple="true" />
			    <!-- 上传队列 -->
			    <div id="queue"  style="display:none;"></div>
               <div class="annex">
                <ul id="attachmentList">
               </ul>
               </div>
            </td>
         </tr>
         </tbody>
	</table>
	</s:elseif>
	
	</div>
	</div>
	 <%--<div class="pageTitle" style="margin-top:15px"><a class="shareShow" onclick="showHide('shareTr')" href="#">更多操作</a></div>
	 <table width="100%" cellspacing="0" cellpadding="0" border="0" class="inputTable" id="shareTr" style="display:none;">
		<tbody>
	       <tr>
			<th><label>有效期</label></th>
			<td>
			<input name="input" type="text" class="Wdate formText" value="<%=new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date()) %>" size="20" name="begin_date" id="begDate" onclick="WdatePicker({maxDate :'#F{$dp.$D(\'endDate\')}',skin:'default',dateFmt:'yyyy-MM-dd HH:mm',minDate:'%y-%M-%d %H:%m'})"/>
			-
			<input name="input" type="text" class="Wdate formText" size="20" name="end_date" id="endDate" onclick="WdatePicker({skin:'default',dateFmt:'yyyy-MM-dd HH:mm',minDate:'#F{$dp.$D(\'begDate\') || \'%y-%M-%d %H:%m\'}'})" />
			&nbsp;<span style="width:auto;color:#0647a8">不选择结束日期为手动终止</span></td>
		</tr>
	        <tr>    
	            <th><label>提醒设置</label></th>
				<td><label class="radio"><input type="checkbox" id="affairRemind" name="affairRemind">发送事务提醒消息</label><label class="radio"><input type="checkbox" id="smsRemind" name="smsRemind">发送短信提醒消息</label></td>
			</tr>
			<tr id="sms_remind_tr">
				<th><label>审批人</label></th>
				<td><select id="auditer" name="auditer"></select></td>
	        </tr>
	       <tr>
				<th><label>内容简介</label></th>
				<td><input id="summary" type="text" class="formText"  style="width:80%"  maxlength="30"/>
						<em class="gray_9">(最多输入30个字)</em>
				</td>
			</tr>
		</tbody></table>
	 
	--%><div class="buttonArea"> 
			<input  value="提交" class="formButton_green" type="button" onclick="tijiao(1);" id="tjsp" style="display:none"/>
			<input hideFocus="" value="发  布" class="formButton_green" type="button" onclick="tijiao(2)" id="fb" style="display:none"/>
			<input hideFocus="" value="存草稿" class="formButton_grey" type="button" onclick="tijiao(0);"/>
			<input hideFocus="" onclick="goBack();" class="formButton_grey" value="取消" type="button" />
	</div>
	</form>
</div>
<script type="text/javascript">
	function isLimit(num){
		if(num==1){
			$("#showDate").hide();
		}else{
			$("#showDate").show();
		}
	}
</script>
</body>
</html>
