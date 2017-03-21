/**
 * 通话明细统计页面的js
 * 李立泼
 * 2014年2月12日
 */
$(document).ready(function(){
	// 初始化开始及结束日期	
    var beginTimeStr = getBeginTime();
    $("#beginTime").val(beginTimeStr);
    var endTimeStr = getNowDate();
    $("#endTime").val(endTimeStr);
	
	getDataTables();
	
	//查询按钮
	$("#search").click(function(){
//		$.removeTableCookie('SpryMedia_DataTables_myTable_callDetails.jsp');
		getDataTables();
	});
	
	// 回车事件
	document.onkeydown = function(e) {
		// 兼容FF和IE和Opera
		var theEvent = e || window.event;
		var code = theEvent.keyCode || theEvent.which || theEvent.charCode;
		if (code == 13) {
//			$.removeTableCookie('SpryMedia_DataTables_myTable_callDetails.jsp');
			getDataTables();
		}
	};
	
	// 初始化开始及结束日期
	var beginTimeStr = getBeginTime();
	$("#beginTimeStr").val(beginTimeStr);	
	var endTimeStr = getNowDate();
	$("#endTimeStr").val(endTimeStr);
	
});

/**
 * 获取列表数据
 */
function getDataTables(){
	/* 比较开始和结束日期 start */
	var beginTimeV = $("#beginTime").val();
	beginTimeV = beginTimeV.replace(/-/g,"/");
	var beginTimeDate = new Date(beginTimeV);
	
	var endTimeV = $("#endTime").val();
	endTimeV = endTimeV.replace(/-/g,"/");
	var endTimeDate = new Date(endTimeV);
	
	if (endTimeDate.getTime() - beginTimeDate.getTime() < 0){
		art.dialog.alert("起始日期不能大于终止日期。");
		return;
	} else if (endTimeDate.getTime() - beginTimeDate.getTime() > 31 * 24 * 60 * 60 * 1000){
		art.dialog.alert("时间间隔超过31天。");
		return;
	}
	/* 比较开始和结束日期 end */
	
	var beginTime = $("#beginTime").val();//开始时间
	if(beginTime!=""){
		beginTime +=" 00:00:00"; 
	}
	var endTime = $("#endTime").val();//结束时间
	if(endTime!=""){
		endTime += " 23:59:59";
	}
	var callType=$("#callType").find("option:selected").val();//呼叫类型（下拉）
	
	var searchkey = $.trim($("#searchkey").val());//关键字
	//alert(beginTime+"  "+endTime+"  "+call+"  "+workNo+"  "+callType);

	$('#myTable').dataTable({
		"bProcessing" : true,
		'bServerSide' : true,
		'fnServerParams' : function(aoData) {
			aoData.push({
				"name" : "beginTime",
				"value" :  beginTime
			},{
				"name" : "endTime",
				"value" :  endTime
			},{
				"name" : "searchkey",
				"value" :  searchkey
			},{
				"name" : "callType",
				"value" :  callType
			});
		},
		"sAjaxSource" : basePath + "callDetails/callDetails_list.action",
		"sServerMethod" : "POST",
		"sPaginationType" : "full_numbers",
		"bPaginate" : true, // 翻页功能
		"bStateSave" : false, // 状态保存
		"bLengthChange" : false, // 改变每页显示数据数量
		"bFilter" : false, // 过滤功能
		"bSort" : false, // 排序功能
		"bInfo" : true,// 页脚信息
		"bAutoWidth" : false,// 自动宽度
		"bDestroy" : true,//用于当要在同一个元素上履行新的dataTable绑订时，将之前的那个数据对象清除掉，换以新的对象设置
		"iDisplayLength" : 15, // 每页显示多少行
		"aoColumns" : [{
					"mDataProp" : "orderNumber" //序号0
				},  {
					"mDataProp" : "call" //主叫号码1
				}, {
					"mDataProp" : "called" //被叫号码2
				},{
					"mDataProp" : "callTypeName" //呼叫类型3
				}, {
					"mDataProp" : "callInTimeStr" //开始时间4
				}, {
					"mDataProp" : "callEndTimeStr" //结束时间5
				}, {
					"mDataProp" : "timeStr", //时长（秒） 6
					"sClass" : "data_r"
				},{
					"mDataProp" : "workNo" //坐席工号7
				}, {
					"mDataProp" : "name" //坐席姓名8
				},{
					"mDataProp" : null, //录音文件9
					"sClass" : "right_bdr0"
				}],
		"oLanguage": {
			   "sUrl": basePath+"plugins/datatable/cn.txt" //中文包
		   },
		"fnDrawCallback": function (oSettings) {
			   $('#myTable tbody  tr td,#myTable tbody  tr td a').each(function() {
					this.setAttribute('title', $(this).text());
			   });
		   },
		"fnFooterCallback": function( nFoot, aData, iStart, iEnd, aiDisplay ) {
				//nFoot.getElementsByTagName('th')[0].innerHTML = "Starting index is "+iStart;
				//alert("aData.length:  "+aData.length); // 打印该页显示多少行记录。
				var Pagecount=aData.length; //在这里这个没有用到。
		   },
	    "aoColumnDefs":[{ "aTargets": [9],//录音文件9
	   	         	   	 "fnRender": function ( oObj ) {
	   	         	   	 	var voxFile = oObj.aData.voxFile;
	   	         	   	 	var voxFileName = oObj.aData.voxFileName;
	   	         	   	    var timeStr = oObj.aData.timeStr;
	   	         	   	
	   	         	   	 	  if(voxFile=="" || voxFile=="-" || 0 == timeStr){
	   	         	   	 		  	return '';
	   	         	   	 	  }else{
	   	         	   	 		  	return "<a style='text-decoration:none;color:#04578D' href=javascript:listen('"+voxFile+"','"+voxFileName+"')>播放</a><a style='text-decoration:none;color:#04578D' href='"
	   	         	   	 		  	+ basePath +"callDetails/callDetails_downLoadUrl.action?urlString="+voxFile+"')>下载</a>";
	   	         	   	 	  }
	   	         	      }
	   	         	  }]
	}); 
}

function getBeginTime(){
	var now = new Date();
	now.setDate(1);
	return getFormatDate(now);
}
function getNowDate(){
	var now = new Date();
	return getFormatDate(now);
}
function getFormatDate(srcdate) {

    var formatDate = "";
    //初始化时间 
    var Year = srcdate.getFullYear();//ie火狐下都可以 
    var Month = srcdate.getMonth() + 1;
    formatDate += Year;
    if (Month >= 10) {
	    formatDate += "-" + Month;
    } else {
	    formatDate += "-0" + Month;
    }

    var Day = srcdate.getDate();
    if (Day >= 10) {
	    formatDate += "-" + Day;
    } else {
	    formatDate += "-0" + Day;
    }
    return formatDate;
}

/**
 * 导出操作
 * @returns {Boolean}
 */
function exporting(){
	/* 比较开始和结束日期 start */
	var beginTimeV = $("#beginTime").val();
	beginTimeV = beginTimeV.replace(/-/g,"/");
	var beginTimeDate = new Date(beginTimeV);
	
	var endTimeV = $("#endTime").val();
	endTimeV = endTimeV.replace(/-/g,"/");
	var endTimeDate = new Date(endTimeV);
	
	if (endTimeDate.getTime() - beginTimeDate.getTime() < 0){
		art.dialog.alert("起始日期不能大于终止日期。");
		return;
	} else if (endTimeDate.getTime() - beginTimeDate.getTime() > 31 * 24 * 60 * 60 * 1000){
		art.dialog.alert("时间间隔超过31天。");
		return;
	}
	/* 比较开始和结束日期 end */
	
	var beginTime = $("#beginTime").val();//开始时间
	if(beginTime!=""){
		beginTime +=" 00:00:00"; 
	}
	var endTime = $("#endTime").val();//结束时间
	if(endTime!=""){
		endTime += " 23:59:59";
	}
	var callType=$("#callType").find("option:selected").val();//呼叫类型（下拉）
	
	var searchkey = $.trim($("#searchkey").val());//关键字
	window.open(basePath + "callDetails/callDetails_exporting.action?beginTime="+ beginTime + "&endTime=" + endTime + "&searchkey=" + searchkey+ "&callType=" + callType);
}

/**
 * 收听录音文件
 * @return
 */
function listen1(link, voxFileName){
	
	 var str="";
	 str += "<object id='wmp' height=60 width=240 align=center classid=CLSID:6BF52A52-394A-11d3-B153-00C04F79FAA6 item='24394746_3939749829_music'>"; 
	 str += "<param name='AutoStart' value='true'>"; 
	 //str += "<!--是否自动播放-->"; 
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
	 art.dialog({
			id: 'outcalling',
			title: "播放录音",
			content: str,
			close: function () {
				wmp.controls.stop();
				return true;
			}
	});
	 
	 
}


function listen(link, voxFileName){
	//window.location.href = basePath + "jbpmworkorder/callNameInfo.action?fromPage=back&vid="+vid;
	var url  = "logined/report/listenCalllog.jsp";

	art.dialog.data("link", link);
	art.dialog.open(basePath + url, {title: '播放录音' ,
             width:299,height:165,
             close: function () {	
            	 var iframe = this.iframe.contentWindow;
            	 iframe.wmp.controls.stop();
 				return true;
 			}});
}

function getBeginTime() {
	var now = new Date();
	now.setDate(1);
	now.setHours(0, 0, 0, 0);
	return getFormatDate(now);
}
function getNowDate() {
	var now = new Date();
	return getFormatDate(now);
}

function getFormatDate(srcdate) {

	var formatDate = "";
	// 初始化时间
	var Year = srcdate.getFullYear();// ie火狐下都可以
	var Month = srcdate.getMonth() + 1;
	formatDate += Year;
	if (Month >= 10) {
		formatDate += "-" + Month;
	} else {
		formatDate += "-0" + Month;
	}

	var Day = srcdate.getDate();
	if (Day >= 10) {
		formatDate += "-" + Day;
	} else {
		formatDate += "-0" + Day;
	}

	return formatDate;
}