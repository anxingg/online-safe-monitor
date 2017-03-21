var nextActionFlag = false;
$(document).ready(function() {
	dynamicBinding( $("#answerContent").get(0) );
	//业务类别
	var id = $("#businessTypeId").val();
	getBusinessTypeName(id);
	
	// 后台工单处理---转回访
	$("#reCallback").bind("click", function() {
	
		// alert("ooooooooooo");
		reCallback();
	});
	
	//完结工单时办结结果字数限制
	$("#dealOverResult").maxLength(500);
	
	// 后台工单处理---完结
	$("#dealover").bind("click", function() {
		dealOver();
	});
	
	//后台已受理工单答复内容字数限制
//	$("#answerContent").maxLength(500);
	
	// 后台 已受理 工单处理结果保存
	$("#reply").bind("click", function() {
	
		// alert("ooooooooooo");
		saveCustomerCallDeal();
	});

	// 选择回访坐席
	$("#userSelect").live( "click", function() {
		openOneSelectUser( 3, selectUserCallBack, $("#callBackUserId").val() );// 选择人员
	});
	
	$(":radio[name='isCallBack']").click(function(){
		//var callback = $(":radio[name='isCallBack']:checked").first().val();
		if( $(":radio[name='isCallBack']:checked").first().val() == 1 ){
			$(".choi").show();
		}else{
			$(".choi").hide();
		}
		
	});
	
	//add by jiayq,如果选择转回访，则需要选择候选人
	$("input[name='nextAction']").click(function(){
		var instanceId = $("#instanceId").val();
		var show = $(this).attr("show");
		if(show == '转回访'){
			$(".choi").show();
			nextActionFlag = true;
			$.get(basePath+"/jbpmworkorder/getTheSeat.action?instanceId="+encodeURI(instanceId),function(data){
				if (null != data){
					$("#callBackUserId").val(data.id);
					$("#callBackUserName").val(data.name);
				}				
			},"json");
		}else{
			nextActionFlag = false;
			$(".choi").hide();
			$("#callBackUserId").val("");
			$("#callBackUserName").val("");
		}
	});
});

/**
 * 保存后台 已受理 工单处理结果
 */
function saveCustomerCallDeal() {
	// 验证后台工单处理时答复内容不能为空。
	if (!validator(document.getElementById("customercallbackform"))) {
		return;
	}
	
		if(!verifyEmptyContent("answerContent","答复内容不能为空")){
			return;
		};
	
	if($("#answerContent").html().length > parseInt( $("#answerContent").attr("fmaxlength"), 10) ){
		showObjError($("#answerContent"), 'customerCall.answercontent_not_null|maxLength.answercontent_max_length');
		$("#nextStep").attr("disabled",false);
		return ;
	}else{
		hideError($("#answerContent"));
	}
	var nextAction = $("input[name='nextAction']:checked").val();
	if(!nextAction){
		art.dialog.alert("请选择下一步处理方式!");
		return;
	}
	var instanceId = $("#instanceId").val();
	var isCallBack = $(":radio[name='isCallBack']:checked").first().val();
	
	var callBackUserId = $("#callBackUserId").val();//若回访，选择的回访坐席id
	if(!callBackUserId  && nextActionFlag == true){
		art.dialog.alert("请选择坐席!");
		return;
	}
	
	var paramData = null;
	if(isCallBack==0){
		paramData = {
				'customerCallLog.dealOverResult' : $("#answerContent").val(),
				'customerCallLog.callBackUserId' : callBackUserId,
				'vid' : $("#vid").val(),
				'checkCallBack' : isCallBack
		};
	}else{
		paramData = {
				'customerCallLog.replyInfo' : $("#answerContent").val(),
				'customerCallLog.callBackUserId' : callBackUserId,
				'vid' : $("#vid").val(),
				'checkCallBack' : isCallBack,
				'instanceId':instanceId,
				'nextAction':nextAction,
				'nextUserId':callBackUserId
			};
	}
	$.ajax( {
				url : basePath + "jbpmworkorder/saveCustomerCallDeal.action",
				type : "post",
				dataType : 'json',
				data : paramData,
				success : function(data) {
				if (data == 1) {
					var fromPage = $("#fromPage").val();
					if(fromPage=='prev'){
						window.top.closeCurrentTab();//前台处理完成，关闭处理页面，并刷新工单列表页面
						window.top.refreshTableCall();
//						window.location.href=basePath+"logined/workorderflow/jsp/customerCallList.jsp?fromPage=prev";
					}else{
						window.location.href =basePath + "logined/workorderflow/jsp/customerCallNotList.jsp?fromPage=back&iscomplete=candidate&_clientType=wap";
					}
				} else {
					art.dialog.alert('保存失败！');
				}
			}
			});

}
/**
 * ????
 * @param id
 * @param errorMsg
 * @returns {Boolean}
 */
function verifyEmptyContent(id, errorMsg){
	var pass=true;
	var obj=$("#"+id);
	var val=obj.val();
	if(val==''){
		if(!obj.hasClass("inputError") ){
			obj.addClass("inputError");
			obj.after('<p class="requireField">'+errorMsg+'</p>');
			pass=false;
		}else{
			pass=false;
		}
	}else{
		obj.removeClass("inputError");
		obj.next('p').remove();
		pass=true;
	}
	obj.change(function(){
		if(obj.hasClass("inputError") ){
			obj.removeClass("inputError");
			obj.next('p').remove();
		}
	});
	return pass;
}
/**
 * 完结工单
 * 
 */
function dealOver() {

	// 完结后台工单时办结结果不能为空。
	if (!validator(document.getElementById("customercallbackform"))) {
		return;
	}

	var paramData = {
		'vid' : $("#vid").val(),
		'customerCallLog.dealOverResult' : $("#dealOverResult").val()
	};
	$.ajax( {
				url : basePath
						+ "jbpmworkorder/saveCustomerCallDeal.action?forward=dealover",
				type : "post",
				dataType : 'json',
				data : paramData,
				success : function(data) {
					if (data == 1) {

						art.dialog.alert("工单已办结，跳转到工单列表页！",function() {
							window.location.href = basePath
									+ "logined/workorderflow/jsp/customerCallNotList.jsp?fromPage=back&iscomplete=candidate";
						});

					} else {
						art.dialog.alert('工单办结失败！');
					}

				}
			});

}

/**
 * 业务类别
 */
function getBusinessTypeName(id){
	var paramData = {
			'businessType' :id
		};
	$.ajax({
		url: basePath + "customercalllog/getbusinessTypeName.action",
		type: "post",
		data: paramData,
		success : function(data){
			$("#businessType").html(data);
		}
	});
}
/**
 * 转回访
 * 
 */
function reCallback() {

	var paramData = {
		'vid' : $("#vid").val()
	};
	$.ajax( {
				url : basePath+ "jbpmworkorder/saveCustomerCallDeal.action?forward=recallback",
				type : "post",
				dataType : 'json',
				data : paramData,
				success : function(data) {
					if (data == 1) {
						art.dialog.alert("工单转回访成功，跳转到工单列表页！",
							function() {
								// window.top.closeCurrentTab();
								window.location.href =
									basePath+ "logined/workorderflow/jsp/customerCallNotList.jsp?fromPage=back&iscomplete=candidate";
	
							});

					} else {
						// art.dialog.alert(data);
						art.dialog.alert('工单转回访失败！');
					}

				}
			});
}

/**
 * 选择回访坐席人员窗口（单选）
 * @param showType 显示类型 1 显示部门 2显示角色 3显示人员
 * @param callback 选择后的回调方法
 * @param defaultSelectId 默认选择的坐席id
 */
function openOneSelectUser(showType,callback,defaultSelectId) {
	var url = basePath + "/logined/user/selectUserByRole.jsp?showType="+showType+"&defaultSelectId="+defaultSelectId+"&roleName='siter,seatLeader'&fromPage="+$("#fromPage").val();
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
					callback( userMap );
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