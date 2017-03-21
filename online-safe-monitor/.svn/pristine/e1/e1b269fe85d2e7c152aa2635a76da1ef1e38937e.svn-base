<%@ page language="java" import="java.util.*"  contentType="text/html; charset=UTF-8" pageEncoding="utf-8"  %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<jsp:include page="../../../common/taglibs.jsp"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<jsp:include page="../head.jsp" />
	<title>岗前培训查看</title>
</head>
<body>
	<input type="hidden" id="trainId" value="<s:property value="#session.trainId"/>"/>
	<div class="bread-line">
		<label>当前位置：</label><a href="#">首页</a>&gt;<a href="#">岗前培训</a>&gt;&nbsp;查看
	</div>
    <div class="formPage">
		<div class="big_title">岗前培训基本信息</div>
		<div class="content_form">
		 	 <table width="100%" cellspacing="0" cellpadding="0" border="0" class="inputTable">
                   <tbody>
                       <tr>
                           <th><label>姓名：</label></th>
                           <td><s:property value="train.userName"/></td>
                           <th><label>性别：</label></th>
                           <td>
                           		<s:if test="train.sex==1">男</s:if>
                           		<s:elseif test="train.sex==0">女</s:elseif>
                           		<s:else></s:else>
                           </td>
                       </tr>
                       <tr>
                       </tr>
                       <tr>
                           <th><label>年龄：</label></th>
                           <td><s:property value="train.age"/></td>
                           <th><label>录用形式：</label></th>
                           <td><s:property value="train.receiveType"/></td>
                       </tr>
                       <tr>
                           <th><label>工种：</label></th>
                           <td><s:property value="train.workType"/></td>
                           <th><label>岗位：</label></th>
                           <td><s:property value="train.post"/></td>
                       </tr>
                       <tr>
                           <th><label>体检结果：</label></th>
                           <td><s:property value="train.testResult"/></td>
                           <th><label>从何处来：</label></th>
                           <td><s:property value="train.nativePlace"/></td>
                       </tr>
                     </tbody>
                </table>
                
                <h2 class="small_title"><a href="#" class="shareShow">公司级教育(一级)</a></h2>
                <table width="100%" cellspacing="0" cellpadding="0" border="0" class="inputTable">
                	<tbody>
                		<tr>
							<th><label>教育内容：</label></th>
							<td colspan="5">
								<s:property value="train.oneTeachContent"/>
							</td>
						</tr>
						<tr>
							<th><label>考试成绩：</label></th>
							<td><s:property value="train.oneScore"/></td>
							<th><label>阅卷人：</label></th>
							<td><s:property value="train.oneExaminer"/></td>
							<th><label>安全负责人：</label></th>
							<td><s:property value="train.oneHeader"/></td>
						</tr>
                	</tbody>
                </table>
                
                <h2 class="small_title"><a href="#" class="shareShow">公司级教育(二级)</a></h2>
                <table width="100%" cellspacing="0" cellpadding="0" border="0" class="inputTable">
                	<tbody>
                		<tr>
							<th><label>教育内容：</label></th>
							<td colspan="5">
								<s:property value="train.twoTeachContent"/>
							</td>
						</tr>
						<tr>
							<th><label>考试成绩：</label></th>
							<td><s:property value="train.twoScore"/></td>
							<th><label>主考人：</label></th>
							<td><s:property value="train.twoExaminer"/></td>
							<th><label>负责人：</label></th>
							<td><s:property value="train.twoHeader"/></td>
						</tr>
                	</tbody>
                </table>
                
                <h2 class="small_title"><a href="#" class="shareShow">公司级教育(三级)</a></h2>
                <table width="100%" cellspacing="0" cellpadding="0" border="0" class="inputTable">
                	<tbody>
                		<tr>
							<th><label>教育内容：</label></th>
							<td colspan="5">
								<s:property value="train.threeTeachContent"/>
							</td>
						</tr>
						<tr>
							<th><label>时间：</label></th>
							<td><s:date format="yyyy-MM-dd" name="train.threeTeachTime"/></td>
							<th><label>掌握情况：</label></th>
							<td><s:property value="train.threeStudySituation"/></td>
							<th><label>负责人：</label></th>
							<td><s:property value="train.threeHeader"/></td>
						</tr>
                	</tbody>
                </table>
                
                <h2 class="small_title"><a href="#" class="shareShow">其他</a></h2>
                <table width="100%" cellspacing="0" cellpadding="0" border="0" class="inputTable">
                  <tbody>
                       <tr>
                          <th><label>个人态度：</label></th>
                          <td><s:property value="train.ownerAttitude"/></td>
                      </tr>
                       <tr>
                          <th><label>准上岗人意见：</label></th>
                          <td><s:property value="train.checkAttitude"/></td>
                      </tr>
                       <tr>
                          <th><label>批准人：</label></th>
                          <td><s:property value="train.checker"/></td>
                      </tr>
                      <tr>
						<th><label>备注：</label></th>
						<td>
							<s:property value="train.memo"/>
						</td>
					 </tr>
					 <tr>
					 	<th><label>证件照：</label></th>
					 		
					 	</td>
					 	<td>
					 		 <div class="imgUpload">
	                          <ul id="attachmentList">
	                          	<s:if test="fileList!=null&&fileList.size>0">
	                          		<s:iterator  value="fileList" id="file" >
						           		<li id="${file.id }_li"><img src="${downPath }upload/${file.attachFile}"></li>
						           </s:iterator>  
	                          	</s:if>
	                          </ul>
	                         </div>
					 	</td>
					 </tr>
                  </tbody>
              </table>
		</div>
	    <div class="buttonArea"> 
			<input type="button" value="返回" class="formButton_grey"  hidefocus="" onClick="javascript :history.back(-1);"/>
		</div>
	</div>
	<div class="clear"></div>
	<script type="text/javascript">
  $(document).ready(function(){
	    $(".shareShow").click(function(){
		    if($(this).hasClass("shareCls")){
			   	$(this).removeClass("shareCls");
			}else{
			    $(this).addClass("shareCls");
			}
			$(this).parent().next(".inputTable").toggle();
			return false;
		})
  });
</script>
</body>
</html>
