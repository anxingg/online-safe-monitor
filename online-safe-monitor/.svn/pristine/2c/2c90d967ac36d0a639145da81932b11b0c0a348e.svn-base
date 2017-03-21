<%@ page contentType="text/html;charset=UTF-8" language="java"%>

<%@ include file="../../../common/taglibs.jsp"%>

<script>
var intervalTime = null;
    var time;
    var handTime;//挂后处理时长   
    function startTiming(filePath) {
       //来电弹屏后置忙
    //	btnBusy_click();
	    time = 0;
          clearInterval(intervalTime);
        $("#filePath").val(filePath);
		// 设置通话时长
		intervalTime = window.setInterval(setCallTime, 1000);
    }

    function setCallTime() {
	    var a = $("#callLength");
	    a.empty();
	    time = time + 1;
	    a.append(formartTime(time));
    }

    function formartTime(time) {
	    var formatString = "";
	    var result = time;
	    var tempNum = 0;
	    if (result >= 3600) {
		    tempNum = Math.floor(time / 3600);
		    result = time % 3600;
		    if (tempNum < 10) {
			    formatString = formatString + "0" + tempNum + ":";
		    } else {
			    formatString = formatString + tempNum + ":";
		    }
	    } else {
		    result = time;
		    formatString = "00:";
	    }

	    if (result >= 60) {
		    tempNum = Math.floor(time / 60);
		    result = time % 60;
		    if (tempNum < 10) {
			    formatString = formatString + "0" + tempNum + ":";
		    } else {
			    formatString = formatString + tempNum + ":";
		    }
	    } else {
		    result = time;
		    formatString = formatString + "00:";
	    }
	    if (result < 10) {
		    formatString = formatString + "0" + result;
	    } else {
		    formatString = formatString + result;
	    }
	    return formatString;
    }
    
    function handUp(){
        //alert("进来了0");
        // 关闭定时器
        clearInterval(intervalTime);
        //启动话后处理
         handTiming();
     }
     
       function handTiming() {
	    handTime = 0;
		// 设置通话时长
		intervalTime = window.setInterval(sethandTime, 1000);
       }
      function sethandTime() {
	    handTime = handTime + 1;
	    $("#handUpTime").val(handTime);
       }
    
       function addBlacklist() {
	    window.top.addBlacklist($("#phone").html());
    }
</script>
<input type="hidden" id="callBeginTime"
	value='<s:property value="#request.callBeginTime" />'>
<input type="hidden" id="callEndTime"
	value='<s:property value="#request.callEndTime" />'>
<input type="hidden" id="callTimeString" value="<s:property value='#request.callTimeString'/>"/>
<input type="hidden" id="handUpTime" value=""/>
<input type="hidden" id="filePath" value="${paramValues.filePath[0]}"/>
<input type="hidden" value="<%=request.getParameter("cclId")==null?"":request.getParameter("cclId")%>" id="cclId" name="cclId"/>
<input type="hidden" id="fromPage"  value='<%=request.getParameter("fromPage")%>'> 
<%-- <table class="titleTable"  border="0" cellpadding="0" cellspacing="0"  id="showTableCall">
    <tr >
       <td style="width:210px"><label>来电号码：&nbsp;<span class="orange"> <s:property value="#request.phone" /></span></label></td>
       <td style="width:280px">号码归属地：&nbsp;<s:property value='#request.mobileArea'/></td>
       <td style="width:270px"><label >来电时间：&nbsp;<s:property value="#request.callTimeString" /></label></td>
      <td style="width:210px">通话时长：&nbsp;<label  id="callLength"></label></td>
    </tr>
  </table> --%>
  <label style="display:none"  id="callLength"></label>