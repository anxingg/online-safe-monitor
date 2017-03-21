/**
 * 登录js
 */
$(document).ready(function() {

			// 判断是否从其他页面跳转过来
			var error = $.query.get('loginFailure');
			if (error != undefined && error != "") {
				if (error == "failure") {
					$("#perror").html("用户名或密码不正确，请重新输入！");
				} else if (error == "loginNameError") {
					$("#perror").html("对不起,用户名不存在！");
				} else if (error == "loginForbid") {
					$("#perror").html("对不起,本用户禁止登录！");
				} else if (error == "codeError") {
					$("#perror").html("对不起,验证码不正确！");
					rememberUser();
				}
			} else {
				rememberUser();
			}
			// 登录按钮绑定事件
			$("#btnLogin").bind("click", function() {
						submit_form();
					});
		});
function submit_form() {
	var loginName = $.trim($("#j_username").val());
	var loginPass = $.trim($("#j_password").val());
	var checkCode = $.trim($("#checkCode").val());
	if (loginName == "") {
		$("#perror").html("请输入用户名！");
		return;
	}
	if (loginPass == "") {
		$("#perror").html("请输入密码！");
		return;
	}
	if (checkCode == "") {
		$("#perror").html("请输入验证码！");
		return;
	}
//	if (loginPass.length < 6) {
//		$("#perror").html("对不起,密码长度不能少于6位！");
//		return;
//	}
	// 判断是否需要记住用户名和密码
	if ($("#cb_remember").attr("checked") == "checked") {
		$.cookie('cbb_loginName', loginName, {
					expires : 365
				});// 登录名保存到cookie ，有效期365天
		$.cookie('cbb_loginPass', loginPass, {
					expires : 365
				});// 登录密码保存到cookie ，有效期365天
	} else {
		// 移除cookie
		$.removeCookie('cbb_loginName');
		$.removeCookie('cbb_loginPass');
	}
	// 提交登录
	$("#loginForm").submit();
}

// 回车事件
document.onkeydown = function(e) {
	// 兼容FF和IE和Opera
	var theEvent = e || window.event;
	var code = theEvent.keyCode || theEvent.which || theEvent.charCode;
	if (code == 13) {
		submit_form();// 要触发的方法
	}
	return true;
}
// 记住用户名
function rememberUser() {
	var loginName = $.cookie('cbb_loginName'); // 名称需要根据具体情况修改
	var loginPass = $.cookie("cbb_loginPass"); // 名称需要根据具体情况修改
	if (loginName != undefined && loginPass != undefined) {
		// 读取到cookie后给登录名和密码赋值
		$("#j_username").val(loginName);
		$("#j_password").val(loginPass);
		$("#cb_remember").attr("checked", "true");
	}
}

function loadimage() {
	document.getElementById("randImage").src = basePath + "common/code.jsp?"
			+ Math.random();
}
