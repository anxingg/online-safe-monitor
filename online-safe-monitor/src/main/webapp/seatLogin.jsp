<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path+"/" ;
    request.setAttribute("ctx", basePath);
    request.setAttribute("version", "3.1");
    response.setHeader("Cache-Control","no-cache"); //HTTP 1.1    
    response.setHeader("Pragma","no-cache"); //HTTP 1.0    
    response.setDateHeader ("Expires", 0); //prevents caching at the proxy server   
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1"/>
<script type="text/javascript" src="${ctx}js/common/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="${ctx}js/common/jquery.cookie.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>登录页</title> 
<link type="text/css" rel="stylesheet" href="${ctx}common/css/reset.css?version=${version}" />
<link type="text/css" rel="stylesheet" href="${ctx}common/css/Slogin.css?version=${version}" /> 
<script type="text/javascript" src="${ctx}common/js/placeholder.js?version=${version}"></script>
<script type="text/javascript" src="${ctx}common/js/js_lang_cn.js?version=${version}"></script>
<script type="text/javascript" src="${ctx}js/common/cookie1.js?version=${version}"></script>
<script type="text/javascript" src="${ctx}js/common/validate_form.js?version=${version}"></script>
<script type="text/javascript" src="${ctx}js/common/showError.js?version=${version}"></script>
<script type="text/javascript" src="${ctx}seat/js/keyJs.js?version=${version}"></script>
<script type="text/javascript" src="${ctx}seat/js/config.js?version=${version}"></script>
<script type="text/javascript">
var basePath = "${ctx}";
$(document).ready(function($) {
		resizeLayout();//内容上下居中
});
function resizeLayout() {
	// 主操作区域高度
      var wHeight = (window.document.documentElement.clientHeight || window.document.body.clientHeight || window.innerHeight);
      var cHeight = wHeight - 450;
      $("#box").css("padding-top",cHeight/2);
};
$(window).resize(function() {
			resizeLayout();
		});
</script>
<script type="text/javascript">
	$(document).ready(function() {
		// 判断坐席端是否需要更新
//		if(refreshClient){
//			htmlToForm(30, '');
//		}
		
	    rememberUser();
	    var msg = $('#msgg').val();
	    if (msg != "") {
		    showObjError($("#s_pass"), 'seatlogin.password_wrong');
	    }
	    //回车事件
	    $(document).keydown(function(event) {
		    var code = event.which;
		    if (code == 13) {
			    form_submit();//要触发的方法
		    }
	    });
    });

    function form_submit() {
	    var workNo = $.trim($("#s_workNo").val());
	    var pass = $("#s_pass").val();
	    var remember;
	    if (!validator(document.getElementById("loginForm"))) {
		    return;
	    } else {
		    if (document.getElementById('remember').checked == true) {
			    document.getElementById('cookie').value = 1;
		    } else {
			    delCookie("s_workNo");
			    delCookie("s_pass");
		    }
	    }
	    ;
	    if (document.getElementById('remember').checked == true) {
		    remember = 1;
	    } else {
		    remember = 0;
	    }
	    var paramData = {
	        'workNo' : workNo,
	        'pass' : pass,
	        'remember' : remember
	    };

	    $.ajax({
	        url : basePath + "seat/user/loginAjax.action",
	        type : "post",
	        dataType : 'json',
	        data : paramData,
	        success : function(data) {
	        	
		        if (null != data && null != data.companyInfo && null != data.adminUser) {
			        // 获取登录状态
			        var state = data.adminUser.state;

			        if (null == state) {
				        showObjError($("#msgg"), 'seatlogin.seat_state_abnormal');
				        return;
			        } else if (state == '1') {
				        showObjError($("#s_workNo"), 'seatlogin.seat_logined');
				        return;
			        } else if (state == '2') {
				        $('#passError').html("");
				        showObjError($("#s_workNo"), 'seatlogin.seat_disabled');
				        return;
			        } else if (state != '0') {
				        $('#passError').html("");
				        showObjError($("#s_workNo"), 'seatlogin.seat_logined');
				        return;
			        }

			        if (null == data.user || "0" != data.user.userState) {
				        showObjError($("#s_pass"), 'seatlogin.');
				        return;
			        }

			        // 判断公司信息
			        var companyName = data.companyInfo.companyName;
			        var companyType = data.companyInfo.companyType;
			        var companyHotCode = data.companyInfo.companyHotCode;
			        if (null == companyName || "" == companyName || null == companyType || "" == companyType
			                || null == companyHotCode || "" == companyHotCode) {
				        showObjError($("#s_workNo"), 'seatlogin.compang_wrong');
				        return;
			        }
			        // 向IVR发送登陆成功请求	  
			        var brecord = data.adminUser.brecord;
			        if (null == brecord) {
				        brecord = 1;
			        }

			        // 判断是否需要记住用户名和密码
			        if ($("#remember").attr("checked") == "checked") {
				        $.cookie('s_workNo', data.adminUser.workNo, {
					        expires : 365
				        });// 登录名保存到cookie ，有效期365天
				        $.cookie('s_pass', pass, {
					        expires : 365
				        });// 登录密码保存到cookie ，有效期365天
			        } else {
				        // 移除cookie
				        $.removeCookie('s_pass');
				        $.removeCookie('s_workNo');
			        }
			        // 判断是否为坐席
			        var isZxbz = data.isZxbz;
			        if (null != isZxbz && "1" == isZxbz) {
				        //window.open("${ctx}login.jsp?loginName=" + workNo + "&loginPass=" + pass+"&remember" + remember);
			        }
			        var msg = data.adminUser.vid + "," + data.adminUser.userNum + "," + data.adminUser.workNo + ","
			                + data.adminUser.pass + "," + data.adminUser.name + "," + data.adminUser.msitype + ","
			                + data.adminUser.msiphone + "," + data.adminUser.msiWorkType + "," + data.adminUser.state
			                + "," + brecord + ", , ," + companyName + "," + companyType + "," + companyHotCode + ","
			                + data.adminUser.companyid;

			        setTimeout(function() {
				        htmlToForm(19, msg);
			        }, 100);

		        } else if (null != data && 'NameorPassError' == data) { 
			        showObjError($("#s_pass"), 'seatlogin.work_pass_wrong');
		        }  else if (null != data && 'forbid' == data) {
			        showObjError($("#s_workNo"), 'seatlogin.seat_state_forbit');
		        }else {
			        showObjError($("#s_workNo"), 'seatlogin.login_forbid');
		        }
	        }
	    });
    }

    //记住用户名
    function rememberUser() {
	    var s_workNo = $.cookie('s_workNo'); // 名称需要根据具体情况修改
	    var s_pass = $.cookie("s_pass"); // 名称需要根据具体情况修改
	    if (s_workNo != undefined && s_pass != undefined) {
		    // 读取到cookie后给登录名和密码赋值
		    $("#s_workNo").val(s_workNo);
		    $("#s_pass").val(s_pass);
		    $("#remember").attr("checked", "true");
		    
	    }else{
  funPlaceholder(document.getElementById("s_workNo"));
  funPlaceholder(document.getElementById("s_pass"));
  funPlaceholder(document.getElementById("remember"));
	    }
    }

    /**
     * 调用form方法
     * @param type
     * @param msg
     */
    function htmlToForm(type, msg) {
	    try {
		    //alert("type="+type+",msg="+msg);
		    window.external.htmlToForm(type, msg);
	    } catch (err) {
		    //alert(err);
	    }
    }

    /**
     * 接受form方法
     * @param type
     * @param msg
     */
    function formToHtml(type, msg) {
	    switch (type) {
	    case 8: // IVR登录
		    loginSubmit(msg);
		    break;
	    case 9: // IVR登录失败
		    loginFaild(msg);
		    break;
	    case 99: // 登录失败
		    loginFaild(msg);
		    break;
	    }
    }

    function loginSubmit(msg) {
	    window.open("${ctx}login.jsp");
	    loginForm.action = '${ctx}seat/user/login.action';
	    loginForm.method = 'POST';
	    loginForm.submit();
    }

    function loginFaild(msg) {
	    $('#passError').html("登录失败！");
    }

    function closeSeat() {
	    htmlToForm(99, '');
    }
    
    
    function showObjError(JObjEle,errMsg) {
    	if(!JObjEle){
    		return;
    	}
    	if(!errMsg){
    		return;
    	}
    	$(JObjEle).focus();
//    	$(document).scrollTop(0);
    	
    	var objEle=JObjEle;
    	
    	//设置元素边框
    	objEle.addClass("inputError");
    	if(objEle.is(":input")||objEle.is("textarea")){
    		var firstValue=$.trim(objEle.val());
    		objEle.keyup(function(){
    			var nextValue=$.trim(objEle.val());
    			if(firstValue!=nextValue){
    				hideError(JObjEle);
    			}
    		});
    		objEle.focusout(function(){
    			var nextValue=$.trim(objEle.val());
    			if(firstValue!=nextValue){
    				hideError(JObjEle);
    			}
    		});		
    	}
        $("#errorP").html(sprintf(errMsg));
    }
    
    function hideError(JObjEle){
    	$("#errorP").html("");
    }
    
    /**
     * 最小化窗口
     */
    function minimize_window(){
        htmlToForm(98,'');
    }
</script>

</head>

<body>
		<div class="login_wrap">
		
		<input type="hidden" id="msgg"
		value='<s:property value="#request.passerror"/>' />
	<input type="hidden" name="cookie" value="0" id="cookie" />
   <div class="login_top">
     <span class="closelogin fr"><a href="javascript:minimize_window();" > </a>
     <a href="javascript:closeSeat();" ></a></a></span>
     <span class="login_topl">${applicationScope.systemBasisSet.receptionName}</span>
   </div> 
   <div class="login_comm">
     <p><img src="${ctx}common/images/Slogin/imgtu.jpg" /></p>
	 <div class="login_comm_login">
	   <span class="login_comm_login_l fl"></span>
	   <ul class="middle">
 <form action="${ctx}user/login.action" method="post" id="loginForm">
  <p id="errorP" class="tishi"></p>
 <li>  <input class="user"  id="s_workNo" name="workNo" placeholder="工号"
							type="text" valid="required" errmsg="seatlogin.workno_not_null"
							value='<s:property value="#request.workNo" />'
							onkeyup="this.value=this.value.replace(/[^0-9]/g,'')" type="text" />
                  </li>
                  <li>
                  <input class="user" id="s_pass" name="pass" placeholder="密码"
							type="password" value='<s:property value="#request.pass" />'
							maxlength="20"
							valid="required|limit"
							errmsg="seatlogin.password_not_null"  />
                  </li>
                  
                  <li>
                  <input name="btnLogin" onclick="form_submit();"
							class="login_btn" value="登录" type="button" /> <label class="radio" >
                   <input id="remember" name="remember" type="checkbox" value="" />记住密码
                  </label></li>
                </ul>
	   </form>
	 </div> 
   </div> 	
  </div>
	
</body>
<script>
</script>	
</html>