<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="cn.com.qytx.platform.org.domain.UserInfo"%>
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
 <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
<meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1"/>
<script type="text/javascript" src="${ctx}js/common/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="${ctx}js/common/jquery.lock.js"></script>
<script type="text/javascript" src="${ctx}js/common/jquery.cookie.js"></script>
<script type="text/javascript" src="${ctx}js/common/json2.js"></script>
<script type="text/javascript" src="${ctx}js/common/selected.js"></script>
<script type="text/javascript" src="${ctx}js/common/textareaLen.js"></script>
<script type="text/javascript" src="${ctx}js/common/blur.js"></script>
<script type="text/javascript" src="${ctx}js/common/const.js"></script>
<script type="text/javascript" src="${ctx}js/common/map.js"></script>
<script type="text/javascript" src="${ctx}js/common/hashmap.js"></script>
<script type="text/javascript" src="${ctx}flat/js/common.js"></script>
<!-- 表单验证 开始 -->
<script type="text/javascript" src="${ctx}js/common/validate_form.js"></script>
<script type="text/javascript" src="${ctx}js/common/showError.js"></script>
<script type="text/javascript" src="${ctx}js/logined/js_lang_cn.js"></script>
<!-- 表单验证 结束 -->

<!--  dialog -->
<script type="text/javascript" src="${ctx}flat/plugins/dialog/artDialog.js?skin=default"></script>
<script type="text/javascript" src="${ctx}flat/plugins/dialog/artDialog_alert.js"></script>
<script type="text/javascript" src="${ctx}flat/plugins/dialog/iframeTools.js"></script>
<script type="text/javascript" src="${ctx}js/common/artClose.js"></script>
<!--  dialog end -->
<!-- 富文本编辑器 -->
<script type="text/javascript" src="${ctx}plugins/ckeditor/ckeditor.js"></script>
<!-- 日历控件 -->
<script type="text/javascript" src="${ctx}plugins/My97DatePicker/WdatePicker.js"></script>

<script type="text/javascript" src="${ctx}plugins/datatable/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="${ctx}flat/plugins/datatable/selecedForDatatablePagination.js"></script>
<script type="text/javascript" src="${ctx}flat/plugins/peopleTree/skins/jquery.ztree.all-3.2.min.js"></script>
<script type="text/javascript" src="${ctx}flat/plugins/tree/skins/jquery.ztree.all-3.2.min.js"></script>
<script type="text/javascript" src="${ctx}js/common/treeNode.js"></script>
<script type="text/javascript" src="${ctx}flat/js/qytx-cbb-v1.0.js"></script>
<script type="text/javascript" src="${ctx}flat/js/async.js"></script>

<script type="text/javascript">
	var basePath = "${ctx}";
	var _cssType = "flat";  //扁平化
	 jQuery("html").bind("click",function(){
         $(window.parent.document).find(".menu").hide(); //
         $(window.parent.parent.document).find(".menu").hide();
         $(window.parent.parent.parent.document).find(".menu").hide();
         $(window.top.document).find("#div_menu").hide();
        });  
	 $(document).ready(function() {
		  $("textarea[readonly]").keydown(function(e) {
		        if(e.which==8){
		            return false;       
		        }
		  });
		  $("input[readonly]").keydown(function(e) {
		        if(e.which==8){
		            return false;       
		        }
		  });
		
	  });
</script>