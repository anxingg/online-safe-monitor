<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<jsp:include page="../../common/taglibs.jsp" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>查询统计-通话明细</title>
<script type="text/javascript" src="${ctx}js/common/jquery-1.8.0.min.js?version=${version}"></script>
<link id="skinCss" href="" rel="stylesheet" type="text/css" />
<script type="text/javascript"
	src="${ctx}flat/plugins/dialog/artDialog.js?skin=default"></script>
<link href="${ctx}common/css/reset.css" rel="stylesheet" type="text/css" />
<link href="${ctx}common/plugins/datatable/skins/datatable_default.css"
	rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx}flat/plugins/dialog/iframeTools.js?version=${version}"></script>
<script type="text/javascript">
	$(document).ready(function() {
	    var link = art.dialog.data("link");
	    listen(link, 'fileName');

    });

    /**
     * 收听录音文件
     * @return
     */
    function listen(link, voxFileName) {

	    var str = "";
	    str += "<object id='wmp' height=60 width=240 align=center classid=CLSID:6BF52A52-394A-11d3-B153-00C04F79FAA6 item='24394746_3939749829_music'>";
	    str += "<param name='AutoStart' value='true'>";
	    //str += "<!--是否自动播放   "; 
	    str += "<param name='Balance' value='0'>";
	    //str += "<!--调整左右声道平衡,同上面旧播放器代码-->"; 
	    str += "<param name='enabled' value='1'>";
	    //str += "<!--播放器是否可人为控制-->"; 
	    str += "<param name='EnableContextMenu' value='-1'>";
	    //str += "<!--是否启用上下文菜单-->"; 
	    str += "<param name='url' value='" + link + "'>";
	    //str += "<!--播放的文件地址-->"; 
	    str += "<param name='PlayCount' value='1'>";
	    //str += "<!--播放次数控制,为整数-->"; 
	    str += "<param name='rate' value='1'>";
	    //str += "<!--播放速率控制,1为正常,允许小数,1.0-2.0-->"; 
	    str += "<param name='currentPosition' value='0'>";
	    //str += "<!--控件设置:当前位置-->"; 
	    str += "<param name='currentMarker' value='0'>";
	    //str += "<!--控件设置:当前标记-->"; 
	    str += "<param name='defaultFrame' value=''>";
	    //str += "<!--显示默认框架-->"; 
	    str += "<param name='invokeURLs' value='0'>";
	    //str += "<!--脚本命令设置:是否调用URL-->"; 
	    str += "<param name='baseURL' value=''>";
	    //str += "<!--脚本命令设置:被调用的URL-->"; 
	    str += "<param name='stretchToFit' value='0'>";
	    //str += "<!--是否按比例伸展-->"; 
	    str += "<param name='volume' value='50%'>";
	    //str += "<!--默认声音大小0%-100%,50则为50%-->"; 
	    str += "<param name='mute' value='0'>";
	    //str += "<!--是否静音-->"; 
	    str += "<param name='uiMode' value='Full'>";
	    //str += "<!--播放器显示模式:Full显示全部;mini最简化;None不显示播放控制,只显示视频窗口;invisible全部不显示-->"; 
	    str += "<param name='windowlessVideo' value='0'>";
	    //str += "<!--如果是0可以允许全屏,否则只能在窗口中查看-->"; 
	    str += "<param name='fullScreen' value='false'>";
	    //str += "<!--开始播放是否自动全屏-->"; 
	    str += "<param name='enableErrorDialogs' value='-1'>";
	    //str += "<!--是否启用错误提示报告-->"; 
	    str += "<param name='SAMIStyle' value>";
	    //str += "<!--SAMI样式-->"; 
	    str += "<param name='SAMILang' value>";
	    //str += "<!--SAMI语言-->"; 
	    str += "<param name='SAMIFilename' value>";
	    //str += "<!--字幕ID-->"; 
	    str += "</object>";
	    $("#voxMailList").html(str);
    }
</script>
</head>
<body>
	<span id="voxMailList" style="position:absolute;top:50%;left:50%;margin:-30px 0 0 -120px;"></span>
</body>
</html>
