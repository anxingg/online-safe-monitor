$(document).ready(function() {
			
	// 后台工单处理中通话记录的获取
	
	getphoneRecord();
	
});
/**
 * 根据工单的id获取相关联的电话记录
 */
// 得到通话记录
function getphoneRecord() {
// alert( $.trim($("#vid").val()));
	
	$('#phoneRecordTable').dataTable({
		"bProcessing" : true,// 当正在处理数据的时候，是否显示“正在处理”这个提示信息
		'bServerSide' : true,// 在服务器端整理数据
		'fnServerParams' : function(aoData) {
			aoData.push({
				"name" : "customerCallLog.vid",
				"value" : $.trim($("#vid").val())
			}
			);
		},
		
		"bStateSave" : false, // 状态保存
		"bDestroy" : true,
		"sAjaxSource" : basePath + "customercalllog/getPhoneRecord.action",
		"sServerMethod" : "POST",
		"sPaginationType" : "full_numbers",
		"bPaginate" : false, // 翻页功能
		"bLengthChange" : false, // 改变每页显示数据数量
		"bFilter" : false, // 过滤功能
		"bSort" : false, // 排序功能
		"bInfo" : false,// 页脚信息
		"bAutoWidth" : false,// 自动宽度
		"iDisplayLength" : 5, // 每页显示多少行
		"aoColumns" : [{
					"sTitle" : "序号",
					"mDataProp" : "no"
// "sClass" : "tdCenter"
				}, {
					"sTitle" : '坐席工号',
					"mDataProp" : "workNo"
// "sClass" : "tdCenter"
				},{
					"sTitle" : '对方号码',
					"mDataProp" : "otherPhone_callType1"
				},
				{
					"sTitle" : '呼叫类型',
					"mDataProp" : "callTypeStr"
// "sClass" : "tdCenter"
				},
				{
					"sTitle" : '通话时间',
					"mDataProp" : "talkInTime"
// "sClass" : "tdCenter"
				},
				{
					"sTitle" : '通话时长(秒)',
					"mDataProp" : "callLength",
					"sClass" : "data_r"
				},{
					"sTitle":"通话录音",
					"mDataProp":null
				},{
					"sTitle":"服务评价",
					"mDataProp":"customercallSatisfy"
				}

		],
		"oLanguage" : {
			"sUrl" : basePath + "plugins/datatable/cn.txt" // 中文包
		},
		"fnDrawCallback" : function(oSettings) { // 在每次table被draw完后调用，至于做什么就看着办吧
			$('#Table tbody  tr td').each(function() {
// if($(this).text().length>7){
// if($(this).text().indexOf("阅读人员 编辑") <= 0){
// this.setAttribute('title', $(this).text());
// }
// }
					});
		},
		
		"fnInitComplete" : function() {
		
		},
		"aoColumnDefs":[
// {
// "aTargets": [3],
// "fnRender": function ( oObj ) {
// var vid = oObj.aData.vid;
// var callType = oObj.aData.callType;
// var otherPhone_callType1 = oObj.aData.otherPhone_callType1;
// var otherPhone_callType2 = oObj.aData.otherPhone_callType2;
// if(callType==1){
// return otherPhone_callType1;
// }else{
// return otherPhone_callType2;
// }
// }
// }
						{ "aTargets": [6],   // 通话录音 播放
	                    	  "fnRender": function ( oObj ) {
	                    		  var vid = oObj.aData.vid;
	                    		  var voxFile = oObj.aData.voxFile;
	                    		  var voxFileName = oObj.aData.voxFileName;
	 	   	 	
	                    		  if(voxFile=="-"){
	                    			  return '<font color="#999999">[没有录音文件]</font>';
	                    		  }else{
// return "<a style='text-decoration:none;color:#04578D'
// href=javascript:listen('"+voxFile+"','"+voxFileName+"')>"+voxFileName+"</a>";
	                    			  return "<a style='text-decoration:none;color:#04578D' href=javascript:listen('"+voxFile+"','"+voxFileName+"')>播放</a>";
	                    		  }
	                    	  }
	                      }
		            ]
		

	});
}

/**
 * 收听录音文件
 * 
 * @return
 */
function listenBak(link, voxFileName){
	
		 var str="";
		 str += "<object id='wmp' height=60 width=240 align=center classid=CLSID:6BF52A52-394A-11d3-B153-00C04F79FAA6 item='24394746_3939749829_music'>"; 
		 str += "<param name='AutoStart' value='true' />"; 
		 // str += "<!--是否自动播放-->";
		 str += "<param name='Balance' value='0' />"; 
		 // str += "<!--调整左右声道平衡,同上面旧播放器代码-->";
		 str += "<param name='enabled' value='1' />"; 
		 // str += "<!--播放器是否可人为控制-->";
		 str += "<param name='EnableContextMenu' value='-1' />"; 
		 // str += "<!--是否启用上下文菜单-->";
		 str += "<param name='url' value='" + link + "' />"; 
		 // str += "<!--播放的文件地址-->";
		 str += "<param name='PlayCount' value='1' />"; 
		 // str += "<!--播放次数控制,为整数-->";
		 str += "<param name='rate' value='1' />"; 
		 // str += "<!--播放速率控制,1为正常,允许小数,1.0-2.0-->";
		 str += "<param name='currentPosition' value='0' />"; 
		 // str += "<!--控件设置:当前位置-->";
		 str += "<param name='currentMarker' value='0' />"; 
		 // str += "<!--控件设置:当前标记-->";
		 str += "<param name='defaultFrame' value='' />"; 
		 // str += "<!--显示默认框架-->";
		 str += "<param name='invokeURLs' value='0' />"; 
		 // str += "<!--脚本命令设置:是否调用URL-->";
		 str += "<param name='baseURL' value='' />"; 
		 // str += "<!--脚本命令设置:被调用的URL-->";
		 str += "<param name='stretchToFit' value='0' />"; 
		 // str += "<!--是否按比例伸展-->";
		 str += "<param name='volume' value='50%' />"; 
		 // str += "<!--默认声音大小0%-100%,50则为50%-->";
		 str += "<param name='mute' value='0' />"; 
		 // str += "<!--是否静音-->";
		 str += "<param name='uiMode' value='Full' />"; 
		 // str +=
			// "<!--播放器显示模式:Full显示全部;mini最简化;None不显示播放控制,只显示视频窗口;invisible全部不显示-->";
		 str += "<param name='windowlessVideo' value='0' />"; 
		 // str += "<!--如果是0可以允许全屏,否则只能在窗口中查看-->";
		 str += "<param name='fullScreen' value='false' />"; 
		 // str += "<!--开始播放是否自动全屏-->";
		 str += "<param name='enableErrorDialogs' value='-1' />"; 
		 // str += "<!--是否启用错误提示报告-->";
		 str += "<param name='SAMIStyle' />"; 
		 // str += "<!--SAMI样式-->";
		 str += "<param name='SAMILang' />"; 
		 // str += "<!--SAMI语言-->";
		 str += "<param name='SAMIFilename' />"; 
		 // str += "<!--字幕ID-->";
		 str += "</object>"; 
		 art.dialog({
			    id: 'outcalling',
			    title: "播放录音",
			    content: str,
			    close: function () {
			    	wmp.controls.stop();//关闭窗口时停止播放
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