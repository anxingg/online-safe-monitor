<link href="${ctx}common/images/favicon.ico" mce_href="favicon.ico" rel="bookmark" type="image/x-icon" />
<link href="${ctx}common/images/favicon.ico" mce_href="favicon.ico" rel="icon" type="image/x-icon" />
<link href="${ctx}common/images/favicon.ico" mce_href="favicon.ico" rel="shortcut icon" type="image/x-icon" />
<script type="text/javascript" src="${ctx}js/switchskin.js?version=${version}"></script>
<script type="text/javascript" src="${ctx}js/common/jquery-1.8.0.min.js?version=${version}"></script>
<script type="text/javascript" src="${ctx}js/common/jquery.lock.js?version=${version}"></script>
<script type="text/javascript" src="${ctx}js/common/jquery.cookie.js?version=${version}"></script>
<script type="text/javascript" src="${ctx}js/common/stringutil.js?version=${version}"></script>
<script type="text/javascript" src="${ctx}js/common/validate.js?version=${version}"></script>
<script type="text/javascript" src="${ctx}js/common/bookmark.js?version=${version}"></script>
<script type="text/javascript" src="${ctx}js/common/artClose.js?version=${version}"></script>
<script type="text/javascript" src="${ctx}common/js/placeholder.js?version=${version}"></script>
<script type="text/javascript" src="${ctx}common/js/js_lang_cn.js?version=${version}"></script>

<%@ page import="cn.com.qytx.platform.org.domain.UserInfo"%>
<%
    UserInfo userInfo = (UserInfo) session.getAttribute("adminUser");
			java.util.Date today = new java.util.Date();
			java.text.SimpleDateFormat simp = new java.text.SimpleDateFormat(
					"yyyy-MM-dd");
			String todayStr = simp.format(today);
			request.setAttribute("today", todayStr);
%>
<%
    Integer skin = userInfo == null ? null : userInfo.getSkinLogo();
			if (skin == null || skin == 1) {
%>
<link id="skinCss" href="" rel="stylesheet" type="text/css" />
<script type="text/javascript"
	src="${ctx}flat/plugins/dialog/artDialog.js?skin=default"></script>
<%
} else if (skin == 2) {
%>
<link id="skinCss" href="${ctx}skins/green/green.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx}flat/plugins/dialog/artDialog.js?skin=green"></script>
<%
}
%>
<script type="text/javascript" src="${ctx}common/plugins/dialog/iframeTools.js?version=${version}"></script>
<script type="text/javascript" src="${ctx}js/common/json2.js?version=${version}"></script>
<script type="text/javascript" src="${ctx}common/plugins/datatable/jquery.dataTables.min.js?version=${version}"></script>
<script type="text/javascript" src="${ctx}plugins/tree/ztree/jquery.ztree.all-3.5.min.js?version=${version}"></script>
<script type="text/javascript" src="${ctx}plugins/My97DatePicker/WdatePicker.js?version=${version}"></script>
<script type="text/javascript" src="${ctx}plugins/tab/tab.js?version=${version}"></script>
<script type="text/javascript" src="${ctx}js/common/stringbuilder.js?version=${version}"></script>
<script type="text/javascript" src="${ctx}common/js/base.js?version=${version}"></script>
<script type="text/javascript" src="${ctx}js/common/selected.js?version=${version}"></script>
<script type="text/javascript" src="${ctx}js/common/textareaLen.js?version=${version}"></script>
<script type="text/javascript" src="${ctx}js/common/blur.js?version=${version}"></script>
<script type="text/javascript" src="${ctx}js/common/const.js?version=${version}"></script>
<script type="text/javascript" src="${ctx}plugins/ckeditor/ckeditor.js?version=${version}"></script>
<%-- <script type="text/javascript" src="${ctx}js/logined/customForm/formEvent.js?version=${version}"></script> --%>
<script type="text/javascript" src="${ctx}plugins/upload/jquery.uploadify.min.js?version=${version}"></script>
<script type="text/javascript" src="${ctx}js/common/jquery.loadmask.js?version=${version}"></script>
<script type="text/javascript" src="${ctx}js/common/map.js?version=${version}"></script>
<script type="text/javascript" src="${ctx}js/common/hashmap.js?version=${version}"></script>
<script type="text/javascript" src="${ctx}js/common/validate_form.js?version=${version}"></script>
<script type="text/javascript" src="${ctx}js/common/async.js?version=${version}"></script>
<script type="text/javascript" src="${ctx}js/common.js?version=${version}"></script>
<script type="text/javascript" src="${ctx}js/logined/cbb/affairShow/affairShow.js?version=${version}"></script>
<script type="text/javascript">
	var basePath = "${ctx}";
    var currentUserName = "${sessionScope.adminUser.userName }";
    jQuery("html").bind("click", function() {
	    $(window.parent.document).find(".menu").hide(); //
	    $(window.parent.parent.document).find(".menu").hide();
	    $(window.parent.parent.parent.document).find(".menu").hide();
	    $(window.top.document).find("#div_menu").hide();
    });
    var _today = "${today}";

    $(document).ready(function() {
	    $("textarea[readonly]").keydown(function(e) {
		    if (e.which == 8) {
			    return false;
		    }
	    });
	    $("input[readonly]").keydown(function(e) {
		    if (e.which == 8) {
			    return false;
		    }
	    });
    });
</script>