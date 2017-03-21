/**
 * 外呼任务坐席端执行页面的js
 */
var subNum = 0;
$(document).ready(function(){
	//先判断是否还有回访任务
	setTimeout("checkHaveTask()", 0);
	
    $("#result").click(function(){
		$(".cont_addr").hide();
		$(".choice").show();
	});
	$("#result2").click(function(){
		$(".choice").hide();
		$(".cont_addr").show();
	});
	//浏览器中Backspace不可用  
	$(document).keydown(function(e){  
	       var keyEvent;   
	       if(e.keyCode==8){   
	           var d=e.srcElement||e.target;   
	            if(d.tagName.toUpperCase()=='INPUT'||d.tagName.toUpperCase()=='TEXTAREA'){   
	                keyEvent=d.readOnly||d.disabled;   
	            }else{   
	                keyEvent=true;   
	            }   
	        }else{   
	            keyEvent=false;   
	        }   
	        if(keyEvent){   
	            e.preventDefault();   
	        }   
	});  
	//提交结果
	$("#submitbtn").click(function () {
	    
		window.top.clearArrangeTime();
		
		//window.top.getNowTime();//不再刷新整理时长
		$(":button").attr({"disabled":"disabled"});//所有按钮点击后，都先调用这句。把所有的按钮置为不可用。（用于所有按钮点击的第一步操作。）
		if (null == $("#phoneUserid").val() || '' == $("#phoneUserid").val()){
			//$("#submitbtn").attr({"disabled":"disabled"});
			art.dialog.alert("没有回访任务!");
			$(":button").removeAttr("disabled");//所有的按钮按钮置为可用。（用于验证时不通用的情况。用于'提交结果'按钮点击后，响应失败。用于提示没有回访任务的情况。）
		}else{
			//$("#submitbtn").attr({"disabled":"disabled"});
			//$("#nextbtn").removeAttr("disabled");
			subNum = 1;
			taskPhoneSubmit();//提交回访
			return false;
		}
	});
	//下一个
	$("#nextbtn").click(function () {
		//先判断是否还有回访任务
		if (null == $("#phoneUserid").val() || '' == $("#phoneUserid").val()){
			$("#nextbtn").attr({"disabled":"disabled"});
			art.dialog.alert("任务执行完毕！");
		}else{
			if(subNum==0){
		    	art.dialog.alert("请先提交结果!");
		    	return;
		    }else{
		    	$("#nextbtn").attr({"disabled":"disabled"});
		    	var url=basePath+"phoneTask/phoneTaskSeat_toPhoneTaskPerform.action?vid="+ $("#vid").val();  
		        window.location.href=url; 
		    }
		}
	});
});

function questionAnswer(questionId,answer){
	this.questionId = questionId;
	this.answer = answer;
}
/**
 * 提交回访
 */
function taskPhoneSubmit(){
	//taskNumber表示当不是重呼时，是否还有要回访的任务
	var haveTask = $("#checkRepeatOrNewCallSubmit").val() == 1 && $("#surplusTasks").val() < 1;
	if ( null == $("#vid").val() || '' == $("#vid").val() || haveTask ){
		art.dialog.alert("没有回访任务!");
		$(":button").removeAttr("disabled");//所有的按钮按钮置为可用。（用于验证时不通用的情况。用于'提交结果'按钮点击后，响应失败。用于提示没有回访任务的情况。）
	}else{
		var vid = $("#vid").val();//任务id
		var phoneUserid = $("#phoneUserid").val();//用户任务id
		var questionnaireId = $("#questionnaireId").val();//试卷id
		var questionnaireUserId = $("#questionnaireUserId").val();//试卷用户id
		var checkRepeatOrNewCallSubmit = $("#checkRepeatOrNewCallSubmit").val();//是执行还是重呼
		var outCallTime = $("#outCallTime").html();//外呼时间
		var callLengthStr = $("#callLength").html();//时长
		var callLength = 0;
		if(callLengthStr!=null&&''!=callLengthStr){
			var callLengthArray =  callLengthStr.split(":");
			callLength = parseInt(callLengthArray[0], 10)*60*60 + parseInt(callLengthArray[1], 10)*60 + parseInt(callLengthArray[2], 10);
		}
		var state = "";//执行/重呼状态(1 回访成功  2 回访失败  3 重复回访成功   4 重复回访失败)
		
		var notTongReason="";
		var stateTong=$("input:radio[name='jietong']:checked").val();
		
		//接通的情况下调用
		if(stateTong==1){
			if(checkRepeatOrNewCallSubmit==1){
				state = 1;
			}else{
				state = 3;
			}
			// 获取用户选择的答案
			var answer = "";//答案
			var questionId = "";//试题ID
			var questionType = "";//试题类型
			var questionAnswerArray = new Array();
			// 获取问题的长度
			var size = $("#questionSize").val();
			for (var i=1; i<=size; i++){
				questionId = $("input[name='question"+i+"']").get(0).value;
				questionType = $("input[name='questionType"+i+"']").get(0).value;
				if(questionType==1){//单选
					var radio = $("input:radio[name='answer"+i+"']:checked").val();
					if(radio!=''&&radio!=null){
						answer = radio;
					}else{
						art.dialog.alert("单选题请选择一项!");
						subNum=0;
						$(":button").removeAttr("disabled");//所有的按钮按钮置为可用。（用于验证时不通用的情况。用于'提交结果'按钮点击后，响应失败。用于提示没有回访任务的情况。）
						return;
					}
				}else if(questionType==2){//多选
					//多选题
					var checkbox='';
					$("input:checkbox[name='answer"+i+"']:checked").each(function(){  
						checkbox+=$(this).val()+",";  
					});  
					if(checkbox==''){
						art.dialog.alert('多选题请至少选择一项!');
						subNum=0;
						$(":button").removeAttr("disabled");//所有的按钮按钮置为可用。（用于验证时不通用的情况。用于'提交结果'按钮点击后，响应失败。用于提示没有回访任务的情况。）
						return;
					}
					answer = checkbox;
				}else if(questionType==3){//问答
					var textarea = $.trim($("textarea[name='answer"+i+"']").val());
					if(textarea!=''&&textarea!=null){
						answer = textarea;
					}else{
						art.dialog.alert("请输入问答题答案!");
						subNum=0;
						$(":button").removeAttr("disabled");//所有的按钮按钮置为可用。（用于验证时不通用的情况。用于'提交结果'按钮点击后，响应失败。用于提示没有回访任务的情况。）
						return;
					}
				}
				var questionAnswerStr = new questionAnswer(questionId,answer);
				questionAnswerArray.push(questionAnswerStr);
			}
			var questionAnswerArrayJSONStr = JSON.stringify(questionAnswerArray);
			var paramData = {
				'questionAnswerArrayJSONStr':questionAnswerArrayJSONStr, 
				'statue':1,	
	 			'state' : state,
	 			'phoneUserid':phoneUserid,
	 			'vid':vid,
	 			'questionnaireId':questionnaireId,
	 			'questionnaireUserId':questionnaireUserId,
	 			'outCallTime':outCallTime,
	 			'callLength':callLength
	 			
			};
			$.ajax({
				url:basePath + "phoneTask/phoneTaskSeat_phoneTaskPerform.action", 
				type:"post", 
				dataType:"json", 
				data:paramData, 
				success:function (data) {
					if(data=='0'){
						if(checkRepeatOrNewCallSubmit==1){
							art.dialog.tips("回访成功!");
							window.top.refreshTableCall();//这个仅刷新列表
							$("#submitbtn").attr({"disabled":"disabled"});
						}else{
//							art.dialog.tips("重呼成功!");
							window.top.closeCurrentTab();
							window.top.refreshTableCall($("#viewPageId").val());
							$("#submitbtn").attr({"disabled":"disabled"});
						}
						//var url=basePath+"phoneTask/toPhoneTaskPerform.action?vid="+ $("#vid").val();  
				        //window.location.href=url; 
						$("#nextbtn").removeAttr("disabled");//'呼叫下一个'按钮置为可用。（用于'提交结果'按钮点击后，响应成功。）
					}else if(data=="2"){
						art.dialog.alert("任务已暂停，不能提交!",function(){
							window.top.closeCurrentTab();
						});
					}else{
						art.dialog.alert("操作失败!");
						//$("#submitbtn").removeAttr("disabled");
						$(":button").removeAttr("disabled");//所有的按钮按钮置为可用。（用于验证时不通用的情况。用于'提交结果'按钮点击后，响应失败。用于提示没有回访任务的情况。）
					}
				}
			});
		}else{
			if(checkRepeatOrNewCallSubmit==1){
				state = 2;
			}else{
				state = 4;
			}
			notTongReason=$("#notJieTong").val();//未接通原因
			if(notTongReason==null || notTongReason==''){
				art.dialog.alert("请选择未接通原因!");
				$(":button").removeAttr("disabled");//所有的按钮按钮置为可用。（用于验证时不通用的情况。用于'提交结果'按钮点击后，响应失败。用于提示没有回访任务的情况。）
				return;
			}else{
				
				var paramData = {
		 			 'statue':notTongReason,	
		 			 'state' : state,
		 			 "phoneUserid":phoneUserid,
		 			 'vid':vid,
		 			 'questionnaireId':questionnaireId,
		 			 'questionnaireUserId':questionnaireUserId,
		 			 'outCallTime':outCallTime,
		 			 'callLength':callLength
				};
				$.ajax({
					url:basePath + "phoneTask/phoneTaskSeat_phoneTaskPerform.action", 
					type:"post", 
					dataType:"json", 
					data:paramData, 
					success:function (data) {
						if(data=='0'){
							if(checkRepeatOrNewCallSubmit==1){
								art.dialog.tips("回访成功!");
								window.top.refreshTableCall();
								//$("#submitbtn").attr({"disabled":"disabled"});
							}else{
//								art.dialog.tips("重呼成功!");
								window.top.closeCurrentTab();
								window.top.refreshTableCall();
								//$("#submitbtn").attr({"disabled":"disabled"});
							}
							//var url=basePath+"phoneTask/toPhoneTaskPerform.action?vid="+ $("#vid").val();  
					        //window.location.href=url; 
							$("#nextbtn").removeAttr("disabled");//'呼叫下一个'按钮置为可用。（用于'提交结果'按钮点击后，响应成功。）
						}else if(data=="2"){
							art.dialog.alert("任务已暂停，不能提交!",function(){
								window.top.closeCurrentTab();
							});
						}else{
							art.dialog.alert("操作失败!");
							$(":button").removeAttr("disabled");//所有的按钮按钮置为可用。（用于验证时不通用的情况。用于'提交结果'按钮点击后，响应失败。用于提示没有回访任务的情况。）
						}
					}
				});
			}
		}
	}  
}


//判断是否还有任务
function checkHaveTask(){
	//taskNumber表示当不是重呼时，是否还有要回访的任务
	var haveTask = $("#checkRepeatOrNewCallSubmit").val() == 1 && $("#surplusTasks").val() < 1;
	if ( haveTask || null == $("#phoneUserid").val() || '' == $("#phoneUserid").val() ){
		$(":button").removeAttr("disabled");//所有的按钮按钮置为可用。（用于验证时不通用的情况。用于'提交结果'按钮点击后，响应失败。用于提示没有回访任务的情况。）
		art.dialog.alert("没有回访任务！", function() {
			window.top.closeCurrentTab();
		});
	}else{
		//$("#submitbtn").removeAttr("disabled");
	}
}

//回访的电话
function outCallPhone() {
	var callPhone = "";
	var vid = $("#vid").val();// 外呼任务的ID
	var phoneUserid = $("#phoneUserid").val();//任务用户id
	callPhone = $("#callPhone").html(); //电话
	if (callPhone == null || callPhone == "" || callPhone == "-") {
		art.dialog.alert("号码不存在，不能回访！");
		return;
	} else {
		window.top.outCall(callPhone, window.top.getCurrentTabId(), vid,phoneUserid);
	}

}

//回访的备用电话
function outCallTelephone() {
	var callPhoneTwo = "";
	var vid = $("#vid").val();// 外呼任务的ID
	var phoneUserid = $("#phoneUserid").val();//任务用户id
	callPhoneTwo = $("#callPhoneTwo").html(); //备用电话
	if (callPhoneTwo == null || callPhoneTwo == "" || callPhoneTwo == "-") {
		art.dialog.alert("号码不存在，不能回访！");
		return;
	} else {
		window.top.outCall(callPhoneTwo, window.top.getCurrentTabId(), vid,phoneUserid);
	}

}