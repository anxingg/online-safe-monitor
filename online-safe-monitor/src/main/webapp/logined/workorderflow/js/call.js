var checkSubmitFlg = false; 
var submiting;
var nextActionFlag = false;
$(document).ready(function() {
	dynamicBinding( $("#note").get(0) );
	dynamicBinding( $("#cclContent").get(0) );
	dynamicBinding( $("#cclSeatReplay").get(0) );
	
	//客户信息展开与隐藏
	$("#allshow").click(function(){
		$("#newclick").show();
		$("#allhide").show();
		$(this).hide();
	});
	
	//得到得到业务类别
	getbusinessType();
	$("#allhide").click(function(){
		$("#newclick").hide();
		$("#allshow").show();
		$(this).hide();
	});
	
	if ($("#fromPage").val() != 1) {
		$("#showTableCall").show();
		$("#relateCustomerCallLog").show();
		$("#relateCustomerCallLogTable").show();
	}
	var callPhone = $("#callPhone").val();

	// 点击保存工单
		$("#new_customercall").click(function() {
			if($("#accessSourceId").val()==null){
				submiting = art.dialog.tips("正在保存...",9999);
			}
			//防止重复提交
//			$("#new_customercall").attr("disabled", "disabled");
			if($.trim($("#phone").val())!=null&&$.trim($("#phone").val())!=""){
				window.top.getNowTime();
			}
			checkSubmit();
			saveCustomerCallLog();
			return false;
		});
		//跳转到历史工单页面
		$("#moreList").click(function(){
			var phone = $.trim($("#phone").val());
			/* 李立泼 2015年03月09日添加下面的if语句。修改BUG #21127。因为当暂存工单点“办理”的URL中没有传phone这个参数，所有上面的phone可能会时空值。 */
			if(!phone){
				phone = $.trim($("#uphone").val());
			}
			window.top.addTab("customerCallHistory"+phone,""+basePath+"logined/workorderflow/jsp/historyCustomerCallList.jsp?phone="+phone,"历史工单");
		});

	
	
	/* 当工单类型选择"举报坐席"时，显示选择坐席 */
	$("select[name=type]").live("change",function(){
		if( $(this).val() == 4 ){
			$(this).parent().parent().find(".selectReportSeat").show();
		}else{
			$(this).parent().parent().find(".selectReportSeat").hide();
		}
	});
	
	var callPhoneTwo = $("#callPhoneTwo").html();
	if(callPhoneTwo!=""&&callPhoneTwo!=null){
		getCrmInfoByPhone();
	}
	
	/**
	 * 播放录音
	 */
	var accessSourceId = $("#accessSourceId").val();
	if(accessSourceId!=null){
		var path = $("#path").val();//路径
		var fileName = $("#fileName").val();//文件名
		//listen(path,fileName);
		listenBak(path,fileName);
	}
	
	//add by jiayq,如果选择转回访，则需要选择候选人
	$("input[name='nextAction']").click(function(){
		var show = $(this).attr("show");
		if(show == '转回访'){
			nextActionFlag = true;
			$(".choi").show();
		}else{
			nextActionFlag = false;
			$(".choi").hide();
			$("#callBackUserId").val("");
			$("#callBackUserName").val("");
		}
	});
	// 选择回访坐席
	$("a[name=userSelect]").live( "click", function() {
		openOneSelectUser( 3, selectUserCalljb, $(this).parent().parent().find("input[name=reportSeatUserId]").val() ,$(this));// 选择人员
	});
	
	
	// 选择回访坐席
	$("#userSelect").live( "click", function() {
		openOneSelectUser( 3, selectUserCallBack, $("#callBackUserId").val() );// 选择人员
	});
	/**
	 * 选择举报坐席回调函数
	 * @param data 返回的数据（json）
	 * @param param 暂时没有用到
	 */
	function selectUserCalljb(data, param) {
		if (data) {
			data.forEach(function(value, key) {
				//alert( 'value: ' + JSON.stringify( value ) );
				//alert( value.Id + ',' + value.Name );
				//$("#userId").val( value.Id );
				//$("#userName").val( value.Name );
				
				$.ajax({
					url : basePath + "/user/ajaxUserById.action",
					type : "post",
					dataType : 'json',
					data : {
						userId : value.Id
					},
					success : function(data) {
						if (null != data) {
							var userId = data.id;
							var userName = data.userName;
							var workNo = data.loginName;
							var userPhone = data.phone;
							//$("#callBackUserId").val(userName+"("+workNo+")<a class=\"x\" href=\"javascript:void(0);\" name=\"seat\" value=\""+userId+"\" ><\/a>");
							$("#reportSeatName").val( userName + '(' + workNo + ')' );
							$("#reportSeatUserId").val( userId );
							$("#oldUserId").val( userId );
						}
					}
					
				});
				
			});
		}
	}

	
	/**
	 * 选择回访坐席人员的回调函数
	 * @param data 返回的数据（json）
	 * @param param 暂时没有用到
	 */
	function selectUserCallBack(data, param) {
		if (data) {
			data.forEach(function(value, key) {
				//alert( 'value: ' + JSON.stringify( value ) );
				//alert( value.Id + ',' + value.Name );
				//$("#userId").val( value.Id );
				//$("#userName").val( value.Name );

				$.ajax({
				    url : basePath + "/user/ajaxUserById.action",
				    type : "post",
				    dataType : 'json',
				    data : {
					    userId : value.Id
				    },
				    success : function(data) {
					    if (null != data) {
					    	//alert( data.id + ',' + data.userName + ',' + data.loginName + ',' + data.phone );
					    	var userId = data.id;
					    	var userName = data.userName;
					    	var workNo = data.loginName;
					    	var userPhone = data.phone;
					    	//$("#callBackUserId").val(userName+"("+workNo+")<a class=\"x\" href=\"javascript:void(0);\" name=\"seat\" value=\""+userId+"\" ><\/a>");
					    	$("#callBackUserName").val( userName + '(' + workNo + ')' );
					    	$("#callBackUserId").val( userId );
					    	$("#oldUserId").val( userId );
					    }
				    }

				});

			});
		}
	}
});

//add by jiayq,临存后，返回再处理此页面的时候，需要先初始化
function initForm(){
	var instanceId = $("#instanceId").val();
	$.get(basePath+"/jbpmworkorder/getTempSaveInfo.action?instanceId="+encodeURI(instanceId),function(data){
		$("#uphone").val(data.phone);
		$("#uname").val(data.name);
		var sex = data.sex;
		$("input[name='gender'][value='"+sex+"']").attr("checked","checked");
		$("#age").val(data.age);
		$("#uaddress").val(data.address);
		$("#company").val(data.company);
		$("select[name='accessType']").val(data.accessType);
		$("#businessType").val(data.businessType);
		$("select[name='type']").val(data.type);
		$("textarea[name='content']").html(data.logInfo);
		$("textarea[name='seatReplay']").html(data.seatReplay);
		$("select[name='type']").change();
		$("input[name='reportSeatUserId']").val(data.userId);
		$("input[name='reportSeatName']").val(data.userName);
		$("#umobile").val(data.backPhone);
		$("#personType").val(data.personType);
		$("#cardId").val(data.cardId);
		$("#hkAddress").val(data.hkAddress);
		$("#job").val(data.job);
		$("#receiveMoney").val(data.receiveMoney);
		$("#note").val(data.note);
		getDataTable();
	},"json");
};
/**
 * 收听录音文件
 * @return
 */
function listenBak(link, voxFileName){
	
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
	 $("#voxMailList").html(str);
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

/**
 * 得到业务类别
 */
function getbusinessType(){
	$.ajax({
		url : basePath + "customercalllog/getbusinessType.action",
		type : "post",
		success : function(data){
			var json = eval("("+data+")");
			var html = '';
			for(var i=0;i<json.length;i++){
				var taskName = json[i].name;
				var value = json[i].value;
				html += '<option value=\"'+value+'\">'+taskName+'</option>';
			}
			$("#businessType").append(html);
			initForm();
		}
	});
}
/**
 * 查看客户档案详情
 */
function seeCrmInfo(){
	var linkedId=$("#linkedId").val();
	window.top.addTab('crmdetail'+linkedId, basePath + "crm/crmDetail.action?vid="+linkedId+"&fromPage=seat", '查看客户信息');
	
}

function getCrmInfoByPhone(){
	var mobile = null;
	var callPhoneTwo = $("#callPhoneTwo").html();
	var uphone = $("#uphone").val();
	if(callPhoneTwo!=null&&callPhoneTwo!=""){
		mobile = callPhoneTwo;
	}
	if(uphone!=null&&uphone!=""){
		mobile = uphone;
	}
	if(mobile!=null&&mobile!=''){
		var paramData = {
				'mobile':mobile
		}
		$.ajax( {
			type : 'post',
			url : basePath + "crm/getCrmInfoByPhone.action",
			data : paramData,
			dataType : 'text',
			success : function(data) {
				if( data && data != '' ){
					var jsonData = eval('('+ data +')');
					$("#uname").val(jsonData.name);
					if(jsonData.gender==0){
						var genderHtml = '';
						genderHtml += '<label class="radio"><input name="gender" type="radio" value="1"/>男</label>';
						genderHtml += '<label class="radio"><input name="gender" type="radio" checked="checked" value="0"/>女</label>';
						$("#genderHtml").html(genderHtml);
					}
					var personTypeHtml = '';
					personTypeHtml += '<select id="personType">';
					personTypeHtml += '<option value="0">请选择</option>';
					if(jsonData.personType==1){
						personTypeHtml += '<option value="1" selected="selected">一般客户</option>';
						personTypeHtml += '<option value="2">VIP客户</option>';
						personTypeHtml += '</select>';
						$("#personTypeHtml").html(personTypeHtml);
					}
					if(jsonData.personType==2){
						personTypeHtml += '<option value="1">一般客户</option>';
						personTypeHtml += '<option value="2" selected="selected">VIP客户</option>';
						personTypeHtml += '</select>';
						$("#personTypeHtml").html(personTypeHtml);
					}
					$("#age").val(jsonData.age);
					var linkedId=jsonData.linkedId;
					if(linkedId&&linkedId!=''){
						$("#linkedId").val(linkedId);
						$("#crmInfo").show();
					}
					
					$("#umobile").val(jsonData.backPhone);
					$("#cardId").val(jsonData.cardId);
					$("#hkAddress").val(jsonData.hkAddress);
					$("#uaddress").val(jsonData.address);
					$("#company").val(jsonData.company);
					$("#job").val(jsonData.job);
					$("#receiveMoney").val(jsonData.receiveMoney);
					$("#note").val(jsonData.note);
					if(jsonData.isCRMs>1){
						$("#selectCrm").show();
					}else{
						$("#selectCrm").hide();
					}
				}
			}
		});
	}
}

//防止表单重复提交的方法
function checkSubmit(){ 
	if(checkSubmitFlg ==true){ 
		return false; //当表单被提交过一次后checkSubmitFlg将变为true,根据判断将无法进行提交。 
	} 

	checkSubmitFlg =true; 
	return true; 
	}
// 保存工单
function saveCustomerCallLog() {
	$("#new_customercall").attr("disabled",true);
	if($("#accessSourceId").val()!=null){
		if(window.top.getIsCallOut()){
			art.dialog.alert('正在通话中,请稍后！');
			$("#new_customercall").attr("disabled",false);
			return ;
		}
	}

	
	var cclId = $("#cclId").val();
	
	if ($("#uphone").val() == null || $("#uphone").val() == "") {
		showObjError($("#uphone"), 'customerCall.uphone_not_null');
		$("#new_customercall").attr("disabled",false);
		submiting.close();
		return;
	} else {
		hideError($("#uphone"));
	}
	
	
	var moneyReg =  /^([1-9][0-9]*)+(.[0-9]{1,2})?$/;
	var receiveMoney = $("#receiveMoney").val().replace(/,/g, "");
	//验证收入格式是否正确
	if($("#receiveMoney").val()!=null && $("#receiveMoney").val() != ""){
		if(!receiveMoney.match(moneyReg)){
			showObjError($("#receiveMoney"), 'customerCall.receiveMoney_not_right');
			document.getElementById("receiveMoney").focus();
			$("#new_customercall").attr("disabled",false);
			submiting.close();
			return;

		}else {
			hideError($("#receiveMoney"));
		}
	}
	
	
	if($("#note").html().length > parseInt( $("#note").attr("fmaxlength"), 10) ){
		showObjError($("#note"), 'maxLength.answercontent_max_length');
		$("#nextStep").attr("disabled",false);
		$("#new_customercall").attr("disabled",false);
		return ;
	}else{
		hideError($("#note"));
	}
	
	if($("#cclContent").html().length > parseInt( $("#cclContent").attr("fmaxlength"), 10) ){
		showObjError($("#cclContent"), 'maxLength.answercontent_max_length');
		$("#nextStep").attr("disabled",false);
		$("#new_customercall").attr("disabled",false);
		return ;
	}else{
		hideError($("#cclContent"));
	}
	
	if($("#cclSeatReplay").html().length > parseInt( $("#cclSeatReplay").attr("fmaxlength"), 10) ){
		showObjError($("#cclSeatReplay"), 'maxLength.answercontent_max_length');
		$("#nextStep").attr("disabled",false);
		$("#new_customercall").attr("disabled",false);
		return ;
	}else{
		hideError($("#cclSeatReplay"));
	}
	
	//获取工作流中的 workFlowName
	var workflowName = $("#workflowName").val();
	
	
	
	//客户信息备注最大字数限制
	var rekLength = $("#note").attr("maxNumber");
	var rekRealLength = $("#note").val().replaceAll("\r\n", "").length;// 获取当前已经输入备注的字符数
	if (rekRealLength > rekLength){
		showObjError($("#note"), 'maxLength.answercontent_max_length');
		$("#new_customercall").attr("disabled",false);
		submiting.close();
		return;
	}else{
		hideError($("#note"));
	}
	
	for(var i=0;i<$("textarea[name=content]").length;i++){
		var accessType = $("select[name=accessType]").eq(i);
		if($(accessType).val()==null||$(accessType).val()=="0"){
			showObjError($(accessType), 'customerCall.accessType_not_null');
			$("#new_customercall").attr("disabled",false);
			submiting.close();
			return;
		}else{
			hideError($(accessType));
		}
		var businessType = $("select[name=businessType]").eq(i);
		if($(businessType).val()==null||$(businessType).val()==""){
			showObjError($(businessType), 'customerCall.businessType_not_null');
			$("#new_customercall").attr("disabled",false);
			submiting.close();
			return;
		}else{
			hideError($(businessType));
		}
		var type = $("select[name=type]").eq(i);
		if($(type).val()==null||$(type).val()=="0"){
			showObjError($(type), 'customerCall.type_not_null');
			$("#new_customercall").attr("disabled",false);
			submiting.close();
			return;
		}else{
			hideError($(type));
		}
		var tempContent = $("textarea[name=content]").eq(i);
		// 工单内容不能为空
		/*
		if ($(tempContent).val() == null || $(tempContent).val() == "") {
			showObjError($(tempContent), 'customerCall.content_not_null');
			$("#new_customercall").attr("disabled",false);
			submiting.close();
			return;
		} else {
			hideError($(tempContent));
		}
		*/
		var seatReplay = $("textarea[name=seatReplay]").eq(i);
		// 工单答复不能为空
		/*
		if ($(seatReplay).val() == null || $(seatReplay).val() == "") {
			showObjError($(seatReplay), 'customerCall.seatReplay_not_null');
			$("#new_customercall").attr("disabled",false);
			submiting.close();
			return;
		} else {
			hideError($(seatReplay));
		}
		*/
		
		//工单内容最大字数限制
		var length = $(tempContent).attr("maxNumber");
		// 获取当前已经输入的字符数
		var realLength = $(tempContent).val().replaceAll("\r\n", "").length;
		if (realLength > length){
			showObjError($(tempContent), 'maxLength.answercontent_max_length');
			$("#new_customercall").attr("disabled",false);
			submiting.close();
			return;
		}else{
			hideError($(tempContent));
		}
		//工单内容最大字数限制
		 length = $(seatReplay).attr("maxNumber");
		// 获取当前已经输入的字符数
		 realLength = $(seatReplay).val().replaceAll("\r\n", "").length;
		if (realLength > length){
			showObjError($(seatReplay), 'maxLength.answercontent_max_length');
			$("#new_customercall").attr("disabled",false);
			submiting.close();
			return;
		}else{
			hideError($(tempContent));
		}
	}
	var callBackUserId = $("#callBackUserId").val();//若回访，选择的回访坐席id
	if(!callBackUserId  && nextActionFlag==true){
		art.dialog.alert("请选择坐席!");
		$("#new_customercall").attr("disabled",false);
		return;
	}
	var nextAction = $("input[name='nextAction']:checked").val();
	if(!nextAction){
		art.dialog.alert("请选择受理结果!");
		return;
	}
	var uname = $.trim($("#uname").val());
	if(uname == ''){
		 uname = '未知';
	}
	var linkedId=$("#linkedId").val();
	if(!linkedId){
		linkedId=0;
	}
  
	var sex = $("input[name='gender']:checked").val();//性别
	var uphone = $.trim($("#uphone").val());//联系电话
	var age=$("#age").val();
	var personType = $('#personType option:selected').val();//客户类别
	var umobile = $.trim($("#umobile").val());//备用电话
	var cardId = $.trim($("#cardId").val());
	var hkAddress = $.trim($("#hkAddress").val());
	var address = $.trim($("#uaddress").val());
	var company = $.trim($("#company").val());
	var job = $.trim($("#job").val());
	var note = $.trim($("#note").val());//备注
	//判断是否提交成功
	var subSuccess;
	var accessSourceId = $.trim($("#accessSourceId").val());//受理来源ID 
	//分别提交工单
	for(var j=0;j<$("table[name=ccl]").length;j++){
		var tempccl = $("table[name=ccl]").eq(j);
		var dataParam = {
				"customerCallLog.name" : uname,// 用户姓名
				"customerCallLog.telephone" : umobile,// 来电为座机号码
				"customerCallLog.phone" : uphone,// 来电为手机号码
				"customerCallLog.address" : address,// 用户的地址
				"customerCallLog.sex" : sex,// 选中的值,//用户性别
				"customerCallLog.age" : age,// 选中的值,//用户性别
				/*工单中的crm begin*/
				"customerCallLog.crm.linkedId" : linkedId,
				"customerCallLog.crm.name" : uname,
				"customerCallLog.crm.gender" : sex,
				"customerCallLog.crm.mobile" : uphone,
				"customerCallLog.crm.age" : age,
				"customerCallLog.crm.backPhone" : umobile,
				"customerCallLog.crm.cardId" : cardId,
				"customerCallLog.crm.hkAddress" : hkAddress,
				"customerCallLog.crm.address" : address,
				"customerCallLog.crm.company" : company,
				"customerCallLog.crm.job" : job,
				"customerCallLog.crm.receiveMoney" : receiveMoney,
				"customerCallLog.crm.note" : note,
				"customerCallLog.crm.personType" : personType,
				/*over*/
				'customerCallLog.vid' : cclId,
				'customerCallLog.type' : $(tempccl).find('select[name=type] option:selected').val(), // 工单类别
				'customerCallLog.accessType' : $(tempccl).find('select[name=accessType] option:selected').val(), // 受理方式
				'customerCallLog.businessType' : $(tempccl).find('select[name=businessType] option:selected').val(), // 业务类别
				'customerCallLog.reportSeatUserId' : $(tempccl).find('input[name=reportSeatUserId]').val(), //被举报坐席id
				'callTimeStr' : $("#callTimeString").val(),// 来电时间
				'handUpTime' : $("#handUpTime").val(),
				'customerCallLog.cclContent' : $.trim($(tempccl).find("textarea[name=content]").html()),
				'customerCallLog.seatReplay' : $.trim($(tempccl).find("textarea[name=seatReplay]").html()),
				"customerCallLog.msiSessionId" : $("#filePath").val(),
				"workflowName" : workflowName,
				"accessSourceId" : accessSourceId,
				"nextAction":nextAction,
				"processId":$("#processId").val(),
				"instanceId":$("#instanceId").val(),
				'nextUserId':callBackUserId
			};
			$.ajax( {
				type : 'post',
				url : basePath + "jbpmworkorder/saveCustomercallLog.action?_clientType=wap",
				data : dataParam,
				dataType : 'text',
				async : false,
				success : function(data) {
					subSuccess = data;
				}
			});
	}
	
	if ("1" == subSuccess) {
		if($("#accessSourceId").val()==null){
			submiting.close();
		}
		art.dialog.alert("操作成功！", function() {
			window.top.closeCurrentTab();
			window.top.forcedRelated();
			});
		
	}else{
		if($("#accessSourceId").val()==null){
			submiting.close();
		}
//		art.dialog.alert("保存失败，请联系管理员处理！");
		art.dialog.alert("保存失败，请联系管理员处理！", function() {
			window.top.closeCurrentTab();
			window.top.forcedRelated();
			});
	}
	if($("#filePath").val()){
		//提交成功发送置闲包
		window.top.htmlToForm(5, "");// 置闲包	
	}
	
	window.top.clearArrangeTime();
	
	
}

// function convertType(type1){
// if(type1=='咨询'){
// return "1";
// }else if(type1=='投诉'){
// return "2";
// }else if(type1=='建议'){
// return "3";
// }
// }


/**
 * 选择回访坐席人员窗口（单选）
 * @param showType 显示类型 1 显示部门 2显示角色 3显示人员
 * @param callback 选择后的回调方法
 * @param defaultSelectId 默认选择的坐席id
 */
function openOneSelectUser(showType,callback,defaultSelectId,event) {
	var url = basePath + "/logined/user/selectUserByRole.jsp?showType="+showType+"&defaultSelectId="+defaultSelectId+"&roleName='siter,seatLeader'";
	var title="选择人员";
	if(showType==1){
		title="选择部门";
	}else if(showType==2){
		title="选择角色";
	}
	art.dialog.open(url, {
		title:title,
		width : 360,
		height : 437,
		lock : true,
		opacity: 0.1,
		//resize:false,
		//drag:false,
		button:[
			{
				name:'确定',
				focus: true,
				callback:function () {
					var userMap =art.dialog.data("userMap");
					callback( userMap,event );
					return true;
				}
			},{
				name:'取消',
				callback:function () {
					return true;
				}
			}
		]
	}, false);

}


/**
 * 选择回访坐席人员的回调函数
 * @param data 返回的数据（json）
 * @param param 暂时没有用到
 */
function selectUserCallBack(data, param) {
//	alert(JSON.stringify(data));
	if (JSON.stringify(data).length>12) {
		data.forEach(function(value, key) {
			//alert( 'value: ' + JSON.stringify( value ) );
			//alert( value.Id + ',' + value.Name );

			$.ajax({
			    url : basePath + "/user/ajaxUserById.action",
			    type : "post",
			    dataType : 'json',
			    data : {
				    userId : value.Id
			    },
			    success : function(data) {
				    if (null != data) {
				    	//alert( data.id + ',' + data.userName + ',' + data.loginName + ',' + data.phone );
				    	var userId = data.id;
				    	var userName = data.userName;
				    	var workNo = data.loginName;
				    	var userPhone = data.phone;
				    	
				    	$(param).parent().parent().find("input[name=reportSeatName]").val( userName + '(' + workNo + ')' );
				    	$(param).parent().parent().find("input[name=reportSeatUserId]").val( userId );
				    	//$("#oldUserId").val( userId );
				    }
			    }

			});

		});
	}else{
		$(param).parent().parent().find("input[name=reportSeatName]").val( "" );
    	$(param).parent().parent().find("input[name=reportSeatUserId]").val( "" );
	}
}

/**
 * 一个号码关联多个Crm时弹出选择框
 */
var selectCrmArt = null;
function selectCrms(){
	var phone = $.trim($("#uphone").val());
	var crmId = $("#crmId").val();
	art.dialog.data("phone",phone);
	art.dialog.data("crmId",crmId);
	
	selectCrmArt = art.dialog.open(basePath + "logined/workorderflow/jsp/selectCrms.jsp", {
	    id : "selectCrms",
	    title : "客户信息",
	    width : '800px',
	    height:'450px',
	    lock : true,
	    opacity: 0.1,// 透明度
	    button:[{
	    	name: '确定',
	    	callback : function(){
	    		//不知道什么原因不能用
//	    		var iframe = this.iframe.contentWindow.document;
//	    		var value = $(iframe).find('input[name=crm]:checked').val();
//	    		var vArray = value.split("_");
	    		var radios = this.iframe.contentWindow.getCheckedRadio();
	    		var vArray = radios.split("_");
	    		initCrm(vArray[0],vArray[1],vArray[2],vArray[3],vArray[5],
	    				vArray[4],vArray[6],vArray[7],vArray[8],vArray[9],
	    				vArray[10],vArray[11],vArray[12]);
	    	},
	    	focus: true
	    	},{
			name : '关闭',
			callback : function() {
				return true;
			}
		}]
	});
}
/**
 * 加载选择的CRM信息
 * @param id
 * @param mobile
 * @param backPhone
 * @param name
 * @param gender
 * @param address
 * @param personType
 * @param cardId
 * @param hkAddress
 * @param company
 * @param job
 * @param receiveMoney
 * @param note
 */
function initCrm(id,mobile,backPhone,name,gender,address,personType,cardId,hkAddress,company,job,receiveMoney,note){
	$("#crmId").val(id);
	$("#uname").val(name);
	$("#uphone").val(mobile);
	if(typeof(backPhone)!='undefined'&&backPhone!=null&&backPhone!=""&&backPhone!='undefined'){
		$("#umobile").val(backPhone);
	}else{
		$("#umobile").val("");
	}
	$("input[name=gender][value="+gender+"]").attr("checked","checked");
	$("#personType option[value='"+personType+"']").first().attr("selected","selected");
	if(typeof(cardId)!='undefined'&&cardId!=null&&cardId!=""&&cardId!='undefined'){
		$("#cardId").val(cardId);
	}else{
		$("#cardId").val("");
	}
	
	if(typeof(hkAddress)!='undefined'&&hkAddress!=null&&hkAddress!=""&&hkAddress!='undefined'){
		$("#hkAddress").val(hkAddress);
	}else{
		$("#hkAddress").val("");
	}
	
	if(typeof(address)!='undefined'&&address!=null&&address!=""&&address!='undefined'){
		$("#uaddress").val(address);
	}else{
		$("#uaddress").val("");
	}
	
	if(typeof(company)!='undefined'&&company!=null&&company!=""&&company!='undefined'){
		$("#company").val(company);
	}else{
		$("#company").val("");
	}
	
	if(typeof(job)!='undefined'&&job!=null&&job!=""&&job!='undefined'){
		$("#job").val(job);
	}else{
		$("#job").val("");
	}
	
	if(typeof(receiveMoney)!='undefined'&&receiveMoney!=null&&receiveMoney!=""&&receiveMoney!='undefined'){
		var money = formatMoney(receiveMoney,"###,###,###.##");
		$("#receiveMoney").val(money);
	}else{
		$("#receiveMoney").val("");
	}
	
	if(typeof(note)!='undefined'&&note!=null&&note!=""&&note!='undefined'){
		$("#note").html(note);
	}else{
		$("#note").html("");
	}
}
/**
 * 格式化数字为金额
 * @param strValue
 * @param formatRule
 * @returns
 */
function formatMoney(strValue,formatRule){
	var spString;
	var spFormate;
	var spFormateValue;
	var formatString="";
	var condition="";
	var flg="true";
	var count=0;
	
	
	if(strValue.length==0 || formatRule.split("#").length==0){
		return strValue;
	}
	
	spString=strValue.split("");
	spFormate=formatRule.split(",");
	
	var arr = new Array(spFormate.length);   
	
	for(j=0;j<spFormate.length;j++){
	    spFormateValue=spFormate[j].split("#");
	
	    if(j==spFormate.length-1){
	    	arr[j]=spFormateValue.length-1;
        }else{
            for(k=j;k<spFormate.length;k++){
                spFormateValue=spFormate[k].split("#");
                if(k==j){
                     arr[j]=spFormateValue.length-1;
                }else{
                     arr[j]=arr[j]+spFormateValue.length-1;
                }
            }
        }
	
		if(j==spFormate.length-1){
		       condition=condition+"i=="+arr[j];
	    }else{
	          condition=condition+"i=="+arr[j]+" "+"||"+" "
	    }
	}
	
	for (i = strValue.length-1; i >=0 ; i--){
	
		count=count+1;
	
	     for(j=0;j<spFormate.length;j++){
	         if((count==arr[j]) && count !=strValue.length){
	            formatString=","+spString[i]+formatString;
	            flg="false";
	          
	           }
	      }
	       if(flg=="false"){
	            flg="true";
	           }else{
	            formatString=spString[i]+formatString;
	           }
	         }
	return formatString;
} 

/**
 * 添加新增工单
 */
function addCCL(){
	var num = 1;
	var numStr = $(".addgd_box").find(".small_title").text();
	if(numStr){
		num = parseInt(numStr.substring(numStr.length-1,numStr.length));
		num++;
	}
	
	var tempCCL = "<div class='addgd_box'><span class='del_btn' onclick='delCCL(this)'>删除</span><h3 class='small_title'>工单信息"+num+"</h3><table border='0' class='inputTable' name='ccl'>";
	tempCCL += "<tr><th><em class='requireField'>*</em><label>工单类别：</label></th><td ><select name='type'><option value='1'>咨询</option><option value='2'>投诉</option><option value='3'>建议</option><option value='4'>举报坐席</option><option value='5'>其他</option></select></td>";
	tempCCL += "<th class='selectReportSeat' style='display: none;' ><label>选择坐席：</label></th><td  class='selectReportSeat' style='display: none;' >";
	tempCCL += "<input type='hidden' name='reportSeatUserId' /><input name='reportSeatName' class='readOnlyText' readonly type='text'   style='width:245px;'/>";
	tempCCL += "<span class='addMember auto_addMember'><a name='userSelect' class='icon_add' href='javascript:void(0)'>选择</a></span></td></tr>";
	tempCCL += "<tr><th><em class='requireField'>*</em><label>工单内容：</label></th><td colspan='3'><textarea name='content' class='formTextarea' cols='' rows='5'  valid='required|textareaLength' errmsg='customerCall.content_not_null|maxLength.answercontent_max_length' onkeyup='checkTextarea(this)' onmouseup='checkTextarea(this)' maxNumber='1000'></textarea>";
	tempCCL += "<span  style='float: right'>1-1000字</span></td></tr></table></div>";
	
	if(num>1){
		$("div.addgd_box:last").after(tempCCL);
	}else{
		$("div table.inputTable:last").after(tempCCL);
	}
}
/**
 * 删除新增的工单
 */
function delCCL(event){
	var cclObj = $(event).parent(".addgd_box");
	updateIndex(cclObj);
	cclObj.remove();
}
/**
 * 更新工单标题的序号
 */
function updateIndex(obj){
	var allBox = $(obj).nextAll(".addgd_box");  //得到此后所有的box
	$(allBox).each(function(index, element){
		var tempNum = $(this).find(".small_title").text();
		if(tempNum){
			var num = parseInt(tempNum.substring(tempNum.length-1,tempNum.length));
			num--;
			$(this).find(".small_title").text("工单信息"+num);
		}
	});
}